# Maven 介绍

+ <span style="font-size:22px">应用场景：</span>

    **一个 Java 项目需要的东西：**

    1. 确定引入哪些依赖包
    2. 确定项目结构，例如`src`存放 Java 源码，`resources`目录存放配置文件，`bin`存放编译生成的`.class`文件
    3. 配置环境，如 JDK 版本、编译打包流程、代码版本号
    4. 能通过命令行工具编译，这样才能让项目在一个独立的服务器上编译、测试、部署

    这些步骤琐碎耗时，为了避免每一个项目都自己搞一套配置需要一个标准化的Java项目管理和构建工具

    Maven就是是专门为 Java 项目打造的<span style="color:red">管理和构建工具</span>

+ <span style="font-size:22px">主要功能：</span>

    - 提供了一套标准化的项目结构；
    - 提供了一套标准化的构建流程（编译，测试，打包，发布……）；
    - 提供了一套依赖管理机制。

+ <span style="font-size:22px">Maven 项目结构：</span>

    一个使用Maven管理的普通的Java项目，它的目录结构默认如下：

    ```
    a-maven-project
    ├── pom.xml
    ├── src
    │   ├── main
    │   │   ├── java
    │   │   └── resources
    │   └── test
    │       ├── java
    │       └── resources
    └── target
    ```

    + 项目描述文件——`pom.xml`
    + 存放 Java 源码目录——`src/main/java`
    + 存放测试资源——`src/test/resources`
    + 所有编译、打包生成的文件——`target`目录

+ <span style="font-size:22px">项目描述文件`pom.xml`：</span>

    关键的项目描述文件`pom.xml`，它的内容大体如下：

    ```xml
    <project ...>
    	<modelVersion>4.0.0</modelVersion>
    	<groupId>com.itranswarp.learnjava</groupId>
    	<artifactId>hello</artifactId>
    	<version>1.0</version>
    	<packaging>jar</packaging>
    	<properties>
            ...
    	</properties>
    	<dependencies>
            <dependency>
                <groupId>commons-logging</groupId>
                <artifactId>commons-logging</artifactId>
                <version>1.2</version>
            </dependency>
    	</dependencies>
    </project>
    ```

    一个Maven工程就是由`groupId`，`artifactId`和`version`作为唯一标识。

+ <span style="font-size:22px">引用第三方库</span>

    引用第三方库时，通过`groupId`、`artifactId`、`version`确定。

    使用`<dependency>`声明一个依赖后，Maven 就会自动下载这个依赖包并放入 classpath。

    ```xml
    <dependency>
        <groupId>commons-logging</groupId>
        <artifactId>commons-logging</artifactId>
        <version>1.2</version>
    </dependency>
    ```

+ <span style="font-size:22px">安装 Maven：</span>

    1. [Maven 官方下载页面](https://maven.apache.org/download.cgi)下载
    2. 解压
    3. 设置环境变量：
        1. 新建系统变量 MAVEN_HOME，变量值`E:\Maven\apache-maven-3.8.4`
        2. 编辑系统变量 Path，添加变量值：`;%MAVEN_HOME%\bin`
    
    4. cmd 窗口测试：`mvn-v`



# 依赖管理

如果项目依赖于第三方 jar 包，如 JUnit，JavaMail，MySQL 驱动等



# 基础概念

+ <span style="font-size:20px">仓库：</span>

    用于存储资源，包含各种 jar 包

+ <span style="font-size:20px">坐标：</span>

    Maven 中坐标用于描述仓库中资源位置

    查找网址：https://repo1.maven.org/maven2/

    + 主要组成：

        groupId：当前 Maven 项目隶属组织名称（通常域名反写，如 'org.mybatis'

        artifactId：当前项目名称（通常是模块名称，如 'CRM'、'SMS'

        version

+ <span style="font-size:20px">仓库配置：</span>

    Maven 启动后会自动保存下载的资源到本地仓库

    ```xml
    <!-- 默认位置： -->
    <localRepository>${user.home}/.m2/repository</localRepository>
    <!-- 自定义位置： -->
    <localRepository>E:\Maven\repository</localRepository>
    ```



# 项目构建——IDEA

+ <span style="font-size:20px">IDEA Maven 配置：</span>

    ![image-20211215112646479](https://gitee.com/ethereal-bang/images/raw/master/20211215112653.png)

+ 如果 Maven 不见，可以双击 shift，

    ![image-20211223220346435](https://gitee.com/ethereal-bang/images/raw/master/20211223220353.png)

    

1. 新建 Maven Project

2. 在 pom.xml 中引入 jar 包：（Maven Change 后自动下载依赖）

    ```xml
    <dependencies>
      <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.3.15</version>
      </dependency>
    </dependencies>
    ```

    ![image-20220205102736180](https://gitee.com/ethereal-bang/images/raw/master/20220205102743.png)

3. 项目根目录下新建 Module

4. Module 下三层架构

    + Junit 测试使用方法：

        在 Test 类中相应方法上注解`@Test`
