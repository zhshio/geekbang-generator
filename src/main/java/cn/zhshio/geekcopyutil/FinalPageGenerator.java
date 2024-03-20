package cn.zhshio.geekcopyutil;
import java.io.IOException;

/**
 * @description:
 * @author: zs
 * @time: 2023/11/27 8:32
 */

/**
 * 最终页面生成器类。
 * 本类提供了一个静态主方法来执行整个页面和音频合并以及索引页生成的过程。
 */
public class FinalPageGenerator {

    /**
     * 程序的主入口方法。
     * @param args 命令行参数（未使用）。
     * @throws IOException 当发生输入/输出错误时抛出。
     * @throws InterruptedException 当线程执行被中断时抛出。
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        // 设置基础目录和文件后缀
        setConfigurations();
        // 执行最终页面的生成
        generateFinalPage();
    }

    /**
     * 配置生成过程的相关参数。
     * 包括公共父目录、原始文件目录、目标文件目录以及目标文件后缀。
     */
    private static void setConfigurations() {
        // D:\Download\BaiduNetdiskDownload\81-浏览器工作原理与实践
        // 设置公共父目录
        ConcourseFile.setPARENTCATALOGUE("D:\\BaiduNetdiskDownload\\");
        // 设置原始文件目录
        ConcourseFile.setSUBCATALOGUE("81-浏览器工作原理与实践");
        // 设置生成目标文件目录
        ConcourseFile.setTargetCatelogue("浏览器工作原理与实践");
        // 设置原始文件(文本, 音频)后缀
        ConcourseFile.setTargetSuffix(new  String[]{".html", ".mp3"});
    }

    /**
     * 生成最终页面。
     * 包括合并音频与页面以及生成索引页两个主要步骤。
     * @throws IOException 当发生输入/输出错误时抛出。
     * @throws InterruptedException 当线程执行被中断时抛出。
     */
    private static void generateFinalPage() throws IOException, InterruptedException {
        // 整理生成合并音频与页面
        ConcourseFile.mergePageAndAudio();
        // 生成index.html索引页
        GenerateIndexPage.generateIndexHtml();
    }
}
