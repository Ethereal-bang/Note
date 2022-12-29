# 配置

> 以 IDEA 配置为例

准备：

1. Java SDK

2. Android SDK

    > 在 IDEA 选择新建 Android 项目后按提示安装

新建 Android 项目：

1. 选择项目模板

2. 配置项目：

    Language 可选 Kotlin / Java

运行 Android 项目：

1. 构建 Gradle 配置
2. 选择 Device——AVD Manager：
    1. Create Virtual Device
    2. Choose a device definition（选择 ADP1，小、启动快）
    3. Select a system image 选择镜像
3. 运行 Gradle Build Running



# 项目结构

build.gradle 项目配置文件