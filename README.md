# `FinalPageGenerator`

## 🚀 简介 

​		`FinalPageGenerator` 是一个方便的 Java 工具类，用于将文本和音频文件合并到统一的页面中，并生成相应的 `index.html` 索引页。

​		这对于构建多媒体学习资源、在线课程以及其他需要整合多种媒体格式的项目非常有用。

## 🎯 功能特性

- 🔗 设置基础目录 - 设置公共父目录、原始文件目录以及目标文件目录。
- 📂 配置文件后缀 - 指定要处理的原始文件（如文本、音频）的后缀名。
- 🤝 合并音频与页面 - 将指定目录下的音频和HTML页面内容进行智能合并。
- 📑 生成索引页 - 根据指定规则自动生成一个友好的 `index.html `索引页，便于用户快速浏览和访问合并后的资源。

## 🚀 使用示例

~~~java
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        // 设置公共父目录
        ConcourseFile.setPARENTCATALOGUE("D:/BaiduNetdiskDownload/");
        // 设置原始文件目录
        ConcourseFile.setSUBCATALOGUE("01-数据结构与算法之美");
        // 设置生成目标文件目录
        ConcourseFile.setTargetCatelogue("数据结构与算法之美");
        // 设置原始文件(文本, 音频)后缀
        ConcourseFile.setTargetSuffix(new String[]{".html", ".mp3"});
        
        // 执行最终页面的生成过程
        FinalPageGenerator.generateFinalPage();
    }
}

~~~

## 💡 注意事项

- 请确保提供的目录路径正确且存在。

- 在执行 `generateFinalPage` 方法之前，请先完成所有必要的目录及后缀配置。

  

## 🛠️ 安装与运行

1. 将 `FinalPageGenerator` 类引入您的项目。
2. 调用 `FinalPageGenerator` 中的方法，根据项目需求配置相关参数。

3. 运行包含调用 `generateFinalPage` 方法的主函数。
4. 

## 📄 许可证

此工具类遵循 MIT 许可证。有关详细信息，请参阅 LICENSE 文件。
希望 `FinalPageGenerator `能为您的项目提供助力，如有任何问题或建议，请随时联系我🎉!