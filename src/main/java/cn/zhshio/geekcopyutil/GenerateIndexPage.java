package cn.zhshio.geekcopyutil;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Auther: 张帅
 * @Date: 2023/11/5 - 11 - 05 - 21:50
 * @Description: com.study.nio.reactor
 * @version: 1.0
 */

/**
 * 生成索引页面的工具类。
 */
public class GenerateIndexPage {

    /**
     * 生成并保存索引HTML文件。
     * @throws IOException 如果在保存文件时发生IO异常。
     */
    public static void generateIndexHtml() throws IOException {
        String directoryPath = ConcourseFile.PARENTCATALOGUE + ConcourseFile.TARGET_CATELOGUE;
        // 设置索引页面的目录路径
        String htmlContent = generateIndexHtml(directoryPath);
        // 将HTML内容保存到文件中
        saveToFile(htmlContent, directoryPath + "/index.html");
    }

    /**
     * 根据目录路径生成索引HTML的内容。
     * @param directoryPath 目录路径，用于生成索引内容。
     * @return 生成的HTML字符串内容。
     */
    private static String generateIndexHtml(String directoryPath) {
        StringBuilder htmlBuilder = new StringBuilder();
        // 构建HTML页面的基本结构
        htmlBuilder.append("<html>")
                .append("<head>")
                .append("<title>Index</title>")
                .append("</head>")
                .append("<body>")
                .append("<h1>Index</h1>")
                .append("<ul>");

        // 递归生成目录的导航HTML
        File directory = new File(directoryPath);
        generateNavigationHtml(directory, htmlBuilder,"");
        // 结束HTML页面的构建
        htmlBuilder.append("</ul>")
                .append("</body>")
                .append("</html>");

        return htmlBuilder.toString();
    }

    /**
     * 递归生成目录结构的导航HTML。
     * @param directory 当前处理的目录文件对象。
     * @param htmlBuilder 用于构建HTML内容的StringBuilder。
     * @param parent 当前目录的父目录路径。
     */
    private static void generateNavigationHtml(File directory, StringBuilder htmlBuilder,String parent) {
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                // 为目录生成嵌套的列表项和子导航
                htmlBuilder.append("<li>")
                        .append(file.getName())
                        .append("<ul>");
                generateNavigationHtml(file, htmlBuilder, file.getName());
                htmlBuilder.append("</ul>")
                        .append("</li>");
            } else if (file.isFile()) {
                // 为文件生成列表项和链接
                htmlBuilder.append("<li>")
                        .append("<a href=\"")
                        .append(parent +"/"+ file.getName())
                        .append("\" target=\"_blank\">")
                        .append(file.getName())
                        .append("</a>")
                        .append("</li>");
            }
        }

    }

    /**
     * 将字符串内容保存到指定路径的文件中。
     * @param content 要保存到文件的字符串内容。
     * @param filePath 文件的保存路径。
     * @throws IOException 如果在写入文件时发生IO异常。
     */
    private static void saveToFile(String content, String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write(content);
        writer.close();
    }
}
