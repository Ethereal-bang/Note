# Windows

## 常用命令

+ 打开 **AppData/Local 文件夹**：Win + R, 输入 `%localappdata`  

+ **管理员身份运行：** Win + R，输入 cmd，Ctrl + Shift + Enter 打开

+ **创建目录链接：**管理员身份下

  ```shell
  mklink /j C:\Users\hp\AppData\Local\Spotify\Data D:\ hp\Spotify\Data
  为 C:\Users\hp\AppData\Local\Spotify\Data <<===>> D:\Spotify\Data 创建的联接
  ```

  > 上例通过建立目录连接，原始文件夹存储自动移动到新位置，并镜像到原始位置

+ **用户目录：** `cd \Users\%UserName%\.ssh`



## 目录结构

**host 文件：**\windows\system32\drivers\etc