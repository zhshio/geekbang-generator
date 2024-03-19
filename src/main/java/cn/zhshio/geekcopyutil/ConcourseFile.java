package cn.zhshio.geekcopyutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;


/**
 * @Auther: 张帅
 * @Date: 2023/11/5 - 11 - 05 - 19:54
 * @Description: com.study.nio
 * @version: 1.0
 */

/**
 * ConcourseFile类用于处理文件和音频的合并。
 * 主要功能是将指定目录下的.html文件和对应的.mp3文件合并，
 * 在.html文件中添加播放音频的按钮，并且使文本可选。
 */
public class ConcourseFile {

    // 定义父目录路径
    public static String PARENTCATALOGUE = "D:/BaiduNetdiskDownload/";

    // 待转换文件所在的子目录
    public static String SUBCATALOGUE = "01-数据结构与算法之美";

    // 转换文件所在的目标目录（相对于源文件）
    public static String TARGET_CATELOGUE = "数据结构与算法之美";

    // 存储文件名的列表
    public static List<String> fileNames  = new LinkedList<>();

    // 定义目标文件的后缀名
    public static String[] TARGET_SUFFIX = {".html", ".mp3"};

    // 日志记录器
    public static final Logger LOGGER = Logger.getLogger(ConcourseFile.class.getName());

    /**
     * 将源目录下的.html和.mp3文件合并，并在.html文件中添加功能。
     * @throws InterruptedException
     */
    public static void mergePageAndAudio() throws InterruptedException {

        // 获取文件名
        getFilesName(PARENTCATALOGUE + SUBCATALOGUE);

        // 使用固定大小的线程池进行文件复制和处理
        ExecutorService executor = Executors.newFixedThreadPool(8);
        CountDownLatch latch = new CountDownLatch(fileNames.size());

        long startTime = System.currentTimeMillis();
        for (String name : fileNames) {
            executor.execute(() -> {
                copyTo(name, name.replace(SUBCATALOGUE, TARGET_CATELOGUE));
                latch.countDown();
            });
        }

        latch.await();
        executor.shutdown();

        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        System.out.println("所有线程执行完成所花费的时间为：" + timeElapsed + " 毫秒");
    }

    /**
     * 将文件从源路径复制到目标路径，并在.html文件中添加功能。
     * @param sourceFile 源文件路径
     * @param destinationFile 目标文件路径
     * @return 操作成功返回true，失败返回false
     */
    private static boolean copyTo(String sourceFile, String destinationFile) {
        try {
            File source = new File(sourceFile);
            File dest = new File(destinationFile);

            // 创建目标文件的父目录
            if (!dest.exists()) {
                dest.getParentFile().mkdirs();
                dest.createNewFile();
            }

            try (FileChannel sourceChannel = new FileInputStream(source).getChannel();
                 FileChannel destChannel = new FileOutputStream(dest).getChannel()) {

                destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

                // 如果是.html文件，添加功能
                if (sourceFile.endsWith(TARGET_SUFFIX[0])) {
                    addJavaScriptFunctionCallToHtmlFile(dest);
                    addAudioButtonToHtmlFile(dest);
                }

                System.out.println(Thread.currentThread().getName() + ": " + sourceFile + " copied to " + destinationFile + " successfully!");

            } catch (Exception e) {
                LOGGER.warning(Thread.currentThread().getName() + ": " + sourceFile + " copied to " + destinationFile + " FAILED!!!!");
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 向.html文件中添加JavaScript函数调用，以允许文本选择。
     * @param htmlFile 要修改的HTML文件
     */
    private static void addJavaScriptFunctionCallToHtmlFile(File htmlFile) {
        try {
            // 读取并修改HTML内容
            String originalContent = new String(Files.readAllBytes(htmlFile.toPath()), StandardCharsets.UTF_8);
            String modifiedContent = originalContent.replace("</head>", "<script>\nfunction addTextSelection() {\n  var eles = document.getElementsByTagName('*');\n  for (var i = 0; i < eles.length; i++) {\n    eles[i].style.userSelect = 'text';\n  }\n}\n</script>\n</head>");
            modifiedContent = modifiedContent.replace("</body>", "<script>addTextSelection();</script>\n</body>");
            Files.write(htmlFile.toPath(), modifiedContent.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            LOGGER.warning("Failed to add JavaScript code to HTML file: " + htmlFile.getAbsolutePath());
            e.printStackTrace();
        }
    }

    /**
     * 向.html文件中添加播放音频的按钮。
     * @param htmlFile 要修改的HTML文件
     */
    private static void addAudioButtonToHtmlFile(File htmlFile) {
        try {
            // 读取并修改HTML内容
            String originalContent = new String(Files.readAllBytes(htmlFile.toPath()), StandardCharsets.UTF_8);
            String audioFileName = "file:///" + htmlFile.getPath().replace(TARGET_SUFFIX[0], TARGET_SUFFIX[1]).replace("\\","/");
            String audioButton = "<button onclick=\"playAudio('" + audioFileName + "')\">播放音频</button>";
            String modifiedContent = originalContent.replace("</head>", "<script>\nfunction playAudio(audioFileName) {\n  var audio = new Audio(audioFileName);\n  audio.play();\n}\n</script>\n</head>");
            modifiedContent = modifiedContent.replace("</body>", audioButton + "\n</body>");
            Files.write(htmlFile.toPath(), modifiedContent.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            LOGGER.warning("Failed to add audio button to HTML file: " + htmlFile.getAbsolutePath());
            e.printStackTrace();
        }
    }

    /**
     * 遍历指定目录下的文件，将匹配的.html和.mp3文件的路径添加到fileNames列表中。
     * @param rootFile 根目录文件
     */
    private static void getFilesName(String rootFile) {
        traversal(new File(rootFile));
    }

    /**
     * 递归遍历文件夹，查找.html和.mp3文件。
     * @param file 当前文件或文件夹
     */
    private static void traversal(File file) {
        if (file.isFile()) {
            // 如果是文件，判断是否为.html或.mp3文件，是则添加到列表中
            for (String suffix : TARGET_SUFFIX) {
                if (file.getName().endsWith(suffix)) {
                    fileNames.add(file.getPath());
                }
            }
            return;
        }

        if (file.isDirectory()) {
            // 如果是文件夹，递归遍历其子文件夹
            for (File file1 : file.listFiles()) {
                traversal(file1);
            }
        }
    }

    // 以下为公共方法，用于外部设置相关目录和后缀名

    public static void setPARENTCATALOGUE(String PARENTCATALOGUE) {
        ConcourseFile.PARENTCATALOGUE = PARENTCATALOGUE;
    }

    public static void setSUBCATALOGUE(String SUBCATALOGUE) {
        ConcourseFile.SUBCATALOGUE = SUBCATALOGUE;
    }

    public static void setTargetCatelogue(String targetCatelogue) {
        TARGET_CATELOGUE = targetCatelogue;
    }


    public static void setTargetSuffix(String[] targetSuffix) {
        TARGET_SUFFIX = targetSuffix;
    }
}
