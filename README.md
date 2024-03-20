# `FinalPageGenerator`

## 🚀 简介 

​		`FinalPageGenerator` 是一个方便的 Java 工具类，用于将文本和音频文件合并到统一的页面中，并生成相应的 `index.html` 索引页。

## 🌱起源

​		去年学习极客时间的设计模式之美, 网盘资料感觉比较乱, 看起来也不方便, 就随手写了一个工具类, 今年朋友谈及才想起来, 拍拍灰, 发到仓库上分享给给需要的朋友

​		因为是随手写的, 所以当时没考虑那么多, 也就没什么扩展性可言, 有特殊需要求可以自己改改或者提个`issue`

## 🎯 功能特性

- 🔗 设置基础目录 - 设置公共父目录、原始文件目录以及目标文件目录。
- 📂 配置文件后缀 - 指定要处理的原始文件（如文本、音频）的后缀名。
- 🤝 合并音频与页面 - 将指定目录下的音频和HTML页面内容进行智能合并。
- 📑 生成索引页 - 根据指定规则自动生成一个友好的 `index.html `索引页，便于用户快速浏览和访问合并后的资源。

## 🚀 使用示例

~~~java
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // D:\Download\BaiduNetdiskDownload\81-浏览器工作原理与实践
        // ->  D:\Download\BaiduNetdiskDownload\浏览器工作原理与实践
        // 设置公共父目录
        ConcourseFile.setPARENTCATALOGUE("D:\\BaiduNetdiskDownload\\");
        // 设置原始文件目录
        ConcourseFile.setSUBCATALOGUE("81-浏览器工作原理与实践");
        // 设置生成目标文件目录
        ConcourseFile.setTargetCatelogue("浏览器工作原理与实践");
        // 设置原始文件(文本, 音频)后缀
        ConcourseFile.setTargetSuffix(new  String[]{".html", ".mp3"});
    }
}
~~~



![image-20240319232606951](https://ohtoai-images.oss-cn-beijing.aliyuncs.com/imgs/image-20240319232606951.png)

生成的文件目录默认是在**公共父目录下的**

![image-20240320104914261](https://ohtoai-images.oss-cn-beijing.aliyuncs.com/imgs/image-20240320104914261.png)



![image-20240320104307964](https://ohtoai-images.oss-cn-beijing.aliyuncs.com/imgs/image-20240320104307964.png)

可以看到生成目标文件目录中文件只保留了`.html`和 `.mp3`文件, 这点是可配置的

![image-20240320105506001](https://ohtoai-images.oss-cn-beijing.aliyuncs.com/imgs/image-20240320105506001.png)

生成的`index.html`

![image-20240320105130571](https://ohtoai-images.oss-cn-beijing.aliyuncs.com/imgs/image-20240320105130571.png)

![image-20240320104415749](https://ohtoai-images.oss-cn-beijing.aliyuncs.com/imgs/image-20240320104415749.png)

每个`.html` 末尾左下角都有一个播放对应文章音频的小按钮

![image-20240320104459517](https://ohtoai-images.oss-cn-beijing.aliyuncs.com/imgs/image-20240320104459517.png)



## 💡 注意事项

- 请确保提供的目录路径正确且存在。

- 在执行 `generateFinalPage` 方法之前，请先完成所有必要的目录及后缀配置。

  

## 🛠️ 安装与运行

1. 将 `FinalPageGenerator` 类引入您的项目。
2. 调用 `FinalPageGenerator` 中的方法，根据项目需求配置相关参数。
3. 运行包含调用 `generateFinalPage` 方法的主函数。

