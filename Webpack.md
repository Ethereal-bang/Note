# webpack 概述

Webpack 是一个**模块打包器**（*构建工具*）

当 Webpack 处理应用程序时，会在内部从一个或多个入口点构建一个依赖图(*dependency praph*)，然后将项目中所需的每一个模块组合成一个或多个 bundles，均为静态资源，用于展示内容

主要目标是将 JS 文件打包在一起，打包后的文件用于在浏览器中使用，它同时也能够胜任 转换（*transform*）、打包（*bundle*）、包裹（*package*） 任何资源

![image-20210301072300860](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20210301072300860.png)

将可扩展的语言转换成浏览器识别的语言，大大提高开发效率



Webpack 有以下**核心概念**：

+ <a href="#entry">入口</a>



## 为什么需要 Webpack

+ <span style="font-size:22px">模块系统</span>

    客户端的代码量越来越大，而模块系统提供了把代码分隔成不同模块的功能

    模块化规范(*关于怎样定义依赖和导出接口* )有多种标准：script、CommonJS、AMD、ES6 模块......

+ <span style="font-size:22px">模块的传输</span>

    模块需要在客户端执行，因此需从服务器传输到浏览器上

    有两个极端方法来传输模块：

    1. 每传输一个模块发起一个请求
    2. 所有模块放在一个请求

    但这都不是最优解

+ <span style="font-size:22px">分块传输</span>

    我们需要一种更灵活的传输方式，在上述两种极端方法中找到一个平衡：

    **编译所有模块时把模块分隔成很多小模块**。这样就可以把请求分成很多小请求，而分隔后模块只有在需要时才会被请求

    所以初始的请求不会包含所有代码，从而减少传输压力。

    而代码怎么分割由程序员决定。

    

+ <span style="font-size:22px">为什么需要一个新的模块打包工具</span>

    现有模块打包工具不适合大型项目开发，最重要的还是缺少代码分割功能，以及静态资源需要通过模块化来无缝衔接

    Webpack 的目标：

    + 依赖树按需分割
    + 初始加载时间控制在较低水平
    + 每个静态资源都应该能成为一个模块
    + 能把第三方库继承到项目里成为一个模块
    + 能定制模块打包器的每个部分
    + 适用于大型项目

+ <span style="font-size:20px">Webpack 有哪些特别的方法</span>

    + **代码分割**：

        Webpack 的依赖树有两种类型的依赖：同步依赖和异步依赖。异步依赖会成为一个代码分割点且组成一个新的代码块

    + **加载器 loader**：

        Webpack 原生只能处理 JS，而 loader 将其它代码转换成 JS 代码，这样所有种类代码都能组成一个模块

    + **智能解析**：

        其智能解析器能处理几乎所有第三方库，能处理大多数模块系统比如 CommonJS、AMD。

    + **插件系统**：

        Webpack 有丰富插件系统，使我们可以定制 Webpack，将其打造成能满足我们需求的工具





## Webpack 安装

1. node.js

2. 通过 npm 安装：

    ```
    $npm install webpack -g
    ```

    Webpack 就全局安装了，可与在命令行里用 webpack 命令了。



<span style="font-size:20px">在项目里使用 Webpack：</span>

最好将 Webpack 本身也当作一个依赖放到项目里，这样就可以选择一个本地的 Webpack 版本而不用使用全局的了	==？==

1. 添加配置文件`package.json`：

    ```
    $npm init
    ```

2. 安装 Webpack 并加到`package.json`里：

    ```
    $npm install webpack --save-dev
    ```



# 核心概念

## 入口 entry

入口起点指示 Webpack 应该使用哪个模块，来作为**构建其内部依赖图的开始**。进入入口起点后，webpack 会找出有哪些模块和库时入口起点**直接或间接依赖**的



+ **默认值**：`./src/index.js`
+ **配置**：可以在`webpack.config.js`中配置**`entry`**属性指定一个或多个入口起点



## 输出 output

`output`属性告诉 Webpack 在哪里**输出它所创建的 bundle**，以及如何命名这些文件



+ **默认值**：主要输出文件`./dist/main.js`；其他生成文件`./dist`文件夹
+ **配置**：指定`output`字段，配置这些处理



## loader

Webpack 只能理解 JS 和 JSON 文件，这是 webpack 开箱可用的自带能力。loader 让其能去处理其他类型的文件，并将它们转换成有效模块供应用程序使用及被添加到依赖图



## 插件 plugin

loader 用于转换某些类型的模块



