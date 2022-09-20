#  Webpack

Webpack 是一个**模块打包器**（*构建工具*）

**作用：**

+ 模块打包

    当 Webpack 处理应用程序时，会在内部从一个或多个入口点构建一个依赖图，然后将项目中所需的每一个模块组合成一个或多个 bundles，均为静态资源，用于展示内容

    ![image-20210301072300860](E:%5CTypora%5Cupload%5Cimage-20210301072300860.png)

+ 编译兼容——通过 Loader 机制

    转换成浏览器识别的语言，提高开发效率

+ 能力扩展——Plugin



+ **打包流程：**

    ![image-20210421125257233.png](https://cdn.nlark.com/yuque/0/2022/png/12496906/1643563690762-0cffc35d-d4b8-4db8-8912-7a5678ec8da8.png)

    所有要被打包的资源都要根入口产生直接/间接引用关系。



## Why Webpack

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

最好将 Webpack 本身也当作一个依赖放到项目里，这样就可以选择一个本地的 Webpack 版本而不用使用全局的了

1. 添加配置文件`package.json`：

    ```
    $npm init
    ```

2. 安装 Webpack 并加到`package.json`里：

    ```
    $npm install webpack --save-dev
    ```
    
3. 安装 `webpack-cli`（用于在命令行中运行 webpack）：

4. 安装`webpack-dev-server`==？==



# 核心概念

## 入口 entry

入口起点指示 Webpack 应该使用哪个模块，来作为**构建其内部依赖图的开始**。进入入口起点后，webpack 会找出有哪些模块和库时入口起点**直接或间接依赖**

+ **默认值**：`./src/index.js`
+ **配置**：可以在`webpack.config.js`中配置**`entry`**属性指定一个或多个入口起点



## 输出 output

`output`属性告诉 Webpack 在哪里**输出它所创建的 bundle**，以及如何命名这些文件

+ **默认值**：主要输出文件`./dist/main.js`；其他生成文件`./dist`文件夹
+ **配置**：指定`output`字段，配置这些处理



## plugins

Loader 用于转换某些类型的模块；而 plugin 基于事件机制工作，会监听 webpack 打包过程中的某些节点，执行广泛的任务

+ <span style="font-size:20px">自动生成 HTML 文件：</span>

    html-webpack-plugin 插件，自动帮我们把 html 和 js 一起打包

    ```shell
    $ yarn add html-webpack-plugin -D
    ```
    
    ```js
    plugins: [
      new HtmlWebpackPlugin({
        template: "./public/index.html",
        // 告诉webpack使用插件时以我们自己的html文件作为模板生成dist/html文件
      })
    ]
    ```
    
    > Notes：
    >
    > 原 html 文件中无需有任何引用语句，打包生成的文件会自动引用打包后文件。

## loader

Webpack 只能理解 JS 和 JSON 文件，这是 webpack 开箱可用的自带能力。loader 让其能去处理其他类型的文件，并将它们转换成有效模块供应用程序使用及被添加到依赖图

+ <span style="font-size:20px">处理 CSS 文件：</span>

    css-loader 让webpack能处理 css 类型文件，style-loader把css插入到 DOM。

    ```bash
    $ yarn add style-loader css-loader -D
    ```

    ```js
    module.exports = {
        // ...其他代码
        module: { 
            rules: [ // loader的规则
              {
                test: /\.css$/, // 匹配所有的css文件
                // use数组里从右向左运行
                // 先用 css-loader 让webpack能够识别 css 文件的内容并打包
                // 再用 style-loader 将样式, 把css插入到dom中
                use: [ "style-loader", "css-loader"]
              }
            ]
        }
    }
    ```

    > 模块 **loader 可链式调用**，链中的每个 loader 都将对资源进行转换。第一个 loader 将其结果（被转换后的资源）传递给下一个 loader，链中的最后的 loader 返回 JavaScript。
    >
    > 应保证 loader 的**先后顺序**：`style-loader`在`css-loader`之前，不然报错

    将 css 文件在 js 中引用：

    ```js
    import "./css/index.css"
    ```

+ <span style="font-size:20px">处理 sass 文件：</span>

    [`sass-loader`](https://webpack.docschina.org/loaders/sass-loader) 加载并编译 SASS/SCSS 文件

    ```bash
    $ yarn add sass sass-loader -D
    ```

    ```js
     module: {
       rules: [
         {
           test: /\.s[ac]ss$/i,    // 匹配sass/scss文件(忽略大小写
           use: [ "style-loader", "css-loader", "sass-loader" ]
         }
       ]
     }
    ```

    > 可以和 css 文件共存

+ <span style="font-size:20px">处理图片文件：</span>

    Webpack 可以使用内置的 [asset modules](https://webpack.docschina.org/guides/asset-modules/) 而无需配置额外 loader

    ```diff
    module: {
    	rules: [
    +    {
    +      test: /\.(png|svg|jpg|jpeg|gif)$/i,
    +      type: 'asset',	
    +    },
      ],
    },
    ```

    > **asset module:**
    >
    > 资源模块类型(asset module type)，通过添加 4 种新的模块类型，来替换 Webpack5 之前的 loader：
    >
    > - **`asset/resource`** 发送一个单独的文件并导出 URL。之前通过使用 `file-loader` 实现。
    > - **`asset/inline`** 导出一个资源的 data URI。之前通过使用 `url-loader` 实现。
    > - **`asset/source`** 导出资源的源代码。之前通过使用 `raw-loader` 实现。
    > - **`asset`** 在导出一个 data URI 和发送一个单独的文件之间自动选择。之前通过使用 `url-loader`，并且配置资源体积限制实现。
    
    ```js
    // 引入
    import Icon from './icon.jpg';
    
    // 图像添加到 div 中。
    const myIcon = new Image();
    myIcon.src = Icon;
    element.appendChild(myIcon);
    ```
    
    对图片有两种处理方案
    
    - 小于8KB的图片转化成base64字符串进入js文件中
    - 大于8KB的图片直接输出文件
    
    > **图片转化成 base64** 的好处是浏览器不用发请求了，直接可以获取。坏处就是如果图片太大，再转 base64 就会让图片的体积增大 30%。
    
    > 可以看到，同样是在 js 内引用图片，大的直接输出，小的转化：
    >
    > ![image-20220206165704537](https://gitee.com/ethereal-bang/images/raw/master/20220206165704.png)
    



# 配置



# 搭建指南

## Demo

+ 

    ```shell
    $ yarn add webpack webpack-cli -D
    ```

+ 配置 scripts：

    ```json
      "scripts": {
        "test": "echo \"Error: no test specified\" && exit 1",
        "build": "webpack"
      },
    ```

+ 新建待打包文件

+ `yarn build`：

    执行后如图<img src="https://gitee.com/ethereal-bang/images/raw/master/20220205221753.png" alt="image-20220205221753552" style="zoom:53%;" />

    > **默认入口:** ./src/index.js
    >
    > **默认出口:** ./dist/main.js




## 2. 管理资源

这一节将学习如何通过 webpack 来管理资源，例如 images、fonts。

+ <span style="font-size:20px">加载 fonts:</span>

    ```js
    // webpack.config.js
    	{
        test: /\.(woff|woff2|eot|ttf|otf)$/i,
        type: 'asset/resource',
      },
    ```



## 3.管理输出

前面的步骤里，都是在`index.html`中手动引入所有资源，然而随着应用程序增长若继续手动管理`index.html`就会变得很困难。通过一些插件能让这个过程变得更容易管控。

这一节将学会如何向 HTML 动态添加 bundle。

### 1.准备

+ <span style="font-size:20px">Project: </span>

    ```diff
    |- package.json
      |- webpack.config.js
      |- /dist
      |- /src
        |- index.js
    +   |- print.js
      |- /node_modules
    ```

在`print.js`中添加一些逻辑，并在`index.js`中使用这个函数。



+ <span style="font-size:20px">更新`dist/index.html`：</span>

    来为 Webpack 分离入口做好准备。

    ```diff
    <!DOCTYPE html>
     <html>
       <head>
         <meta charset="utf-8" />
     	   <title>管理输出</title>
    +    <script src="./print.bundle.js"></script>
       </head>
       <body>
    -    <script src="bundle.js"></script>
    +    <script src="./index.bundle.js"></script>
       </body>
     </html>
    ```

+ <span style="font-size:20px">调整配置`webpack.config.js`：</span>

    在 **entry** 添加 `src/print.js` 作为新的入口起点（`print`），然后修改 **output**，以便根据入口起点定义的名称，动态地产生 bundle 名称。

    ```diff
     module.exports = {
    -  entry: './src/index.js',
    +  entry: {
    +    index: './src/index.js',
    +    print: './src/print.js',
    +  },
       output: {
    -    filename: 'bundle.js',
    +    filename: '[name].bundle.js',
         path: path.resolve(__dirname, 'dist'),
       },
     };
    ```



执行后看到生成如下：

![image-20211030151203585](https://gitee.com/ethereal-bang/images/raw/master/20211030151210.png)

可以看到 Webpack 生成 `print.bundle.js` 和 `index.bundle.js` 文件，这和在 `index.html` 文件中指定的文件名称相对应。



若更改了一个入口起点的名称，甚至添加了一个新的入口，会出现什么问题：构建时重新命名生成的 bundle，但是我们的 `index.html` 文件仍然引用旧的名称。可以用[`HtmlWebpackPlugin`](https://webpack.docschina.org/plugins/html-webpack-plugin) 来解决这个问题：

> **文件名对应关系**：
>
> 若将`webpack.config.js` output 的 filename 字段名改为`[name]test.bundle.js`，生成如下：
>
> ![image-20211030151753279](https://gitee.com/ethereal-bang/images/raw/master/20211030151753.png)
>
> 这时`index.html`script 标签不改变`src`的话会找不到文件。



### 3.清理 dist 文件夹

Webpack 将生成文件并放置在 `/dist` 文件夹中，不用的文件也会堆积。

通常较推荐的做法是 在每次构建前清理 `/dist` 文件夹，这样只会生成用到的文件。可以使用 [`output.clean`](https://webpack.docschina.org/configuration/output/#outputclean) 配置项实现这个需求：

+ <span style="font-size:20px">webpack.config.js：</span>

    ```diff
    const path = require('path');
     const HtmlWebpackPlugin = require('html-webpack-plugin');
    
     module.exports = {
       entry: {
         index: './src/index.js',
         print: './src/print.js',
       },
       plugins: [
         new HtmlWebpackPlugin({
           title: 'Output Management',
         }),
       ],
       output: {
         filename: '[name].bundle.js',
         path: path.resolve(__dirname, 'dist'),
    +    clean: true,
       },
     };
    ```

    

## 4.设置开发环境

设置一个开发环境，使我们的开发体验变得更轻松一些。这一节的工具**仅用于开发环境**，但不要在生产环境中使用它们。

1. <span style="font-size:20px">设置 mode：</span>

    ```diff
     const path = require('path');
     const HtmlWebpackPlugin = require('html-webpack-plugin');
    
     module.exports = {
    +  mode: 'development',
       entry: {
         index: './src/index.js',
         print: './src/print.js',
       },
       
    ```



#### <span style="font-size:20px">使用 scource map：</span>

适用场景：当 webpack 打包源代码时，可能会很难追踪到 error(错误) 和 warning(警告) 在源代码中的原始位置。

>  将三个源文件（`a.js`, `b.js` 和 `c.js`）打包到一个 bundle（`bundle.js`）中，而其中一个源文件包含一个错误，那么堆栈跟踪就会直接指向到 `bundle.js`。
>
> 这种提示这通常不会提供太多帮助。

[Devtool](https://webpack.docschina.org/configuration/devtool/)控制是否生成，以及如何生成 source map。

这里使用`inline-source-map`选项：

```diff
module.exports = {
   mode: 'development',
   entry: {
     index: './src/index.js',
     print: './src/print.js',
   },
+  devtool: 'inline-source-map',

```

测试：

没设置时的报错：![image-20211030184411765](https://gitee.com/ethereal-bang/images/raw/master/20211030184411.png)

设置后：![image-20211030184442455](https://gitee.com/ethereal-bang/images/raw/master/20211030184442.png)



#### <span style="font-size:20px">开发工具</span>

Webpack 提供几种可选方式，在代码发生变化后自动编译代码：

1. webpack's [Watch Mode](https://webpack.docschina.org/configuration/watch/#watch)
2. [webpack-dev-server](https://github.com/webpack/webpack-dev-server)
3. [webpack-dev-middleware](https://github.com/webpack/webpack-dev-middleware)

多数场景中会使用 `webpack-dev-server`。

+ <span style="font-size:20px">watch mode(*观察者模式* )</span>

    **使用场景：**指示 webpack "watch" 依赖图中所有文件的更改。如果其中一个文件被更新，代码将被重新编译，不必再去手动运行整个构建。

    **使用：**在`package,json`中添加一个用于启动 webpack watch mode 的 npm scripts：`"watch": "webpack --watch"`

    

+ <span style="font-size:20px">webpack-dev-server：</span>

    `webpack-dev-server` 提供了一个基本的 web server，并且具有 live reloading(***热重载*** ) 功能

    > **热重载与热更新：**
    >
    > 热重载：修改文件后，webpack自动编译，浏览器自动刷新。并不能够保存应用的状态，当刷新页面后，应用之前状态丢失
    >
    > 热更新：不会刷新浏览器，而是运行时对模块进行热替换，保证了应用状态不会丢失，提升了开发效率

    ```bash
    npm install --save-dev webpack-dev-server
    ```

    在`webpack.config.js`中设置`devTools`字段，告知 dev server，从什么位置查找文件：

    ```js
    devTools: {
      static: './dist',
    }
    ```

    以上配置告知 `webpack-dev-server`，将 `dist` 目录下的文件 serve(*将资源作为 server 的可访问文件* ) 到 `localhost:8080` 下。

    添加一个可以直接运行 dev server 的 script：

    ```json
    "scripts": {
      "test": "echo \"Error: no test specified\" && exit 1",
      "start": "webpack serve --open",
      "build": "webpack"
    },
    ```

    现在`npm start`：

    ![image-20211030194925867](https://gitee.com/ethereal-bang/images/raw/master/20211030194925.png)

    

## 5.代码分离

代码分离能把代码分离到不同的 bundle 中，然后可以按需加载或并行加载这些文件

代码分离可用于获取更小的 bundle，以及控制资源加载优先级，如果使用合理，会极大影响加载时间

常用代码分离有三种：

- **入口起点**：使用 [`entry`](https://webpack.docschina.org/configuration/entry-context) 配置手动地分离代码
- **防止重复**：使用 [Entry dependencies](https://webpack.docschina.org/configuration/entry-context/#dependencies) 或者 [`SplitChunksPlugin`](https://webpack.docschina.org/plugins/split-chunks-plugin) 去重和分离 chunk
- **动态导入**：通过模块的内联函数调用来分离代码



### 入口起点

这是最简单直观的分离代码的方式

隐患：

- 如果入口 chunk 之间包含一些重复的模块，那些重复模块都会被引入到各个 bundle 中。

    > 例如，在多个 chunk 里都引用 lodash，这样就在两个 bundle 中造成重复引用，在下一节会移除重复的模块。

- 这种方法不够灵活，并且不能动态地将核心应用程序逻辑中的代码拆分出来。



### 防止重复(prevent duplication)

+ <span style="font-size:20px">入口依赖：</span>

    配置 [`dependOn` option](https://webpack.docschina.org/configuration/entry-context/#dependencies) 选项，这样可以在多个 chunk 之间共享模块：

    ```diff
    module.exports = {
        mode: 'development',
        entry: {
    -				index: './src/index.js',
    +				index: {
    +            import: './src/index.js',
    +            dependOn: 'shared'
    +        },
    -				print: './src/print.js',
    +       print: {
    +           import: './src/print.js',
    +           dependOn: 'shared',
    +       },
    +       shared: 'lodash',
        },
    ```

    如果我们要在一个 HTML 页面上使用多个入口，还需设置以下字段：

    ```js
    optimization: {
      runtimeChunk: 'single',
    },
    ```

    应尽可能避免使用多入口的入口，如此，在使用 `async` 脚本标签时，会有更好的优化以及一致的执行顺序。



+ <span style="font-size:20px">SplitChunksPlugin：</span>



# 原理

<span style="font-size:22px">Webpack 打包流程：</span>

1. 读取 Webpack **配置参数**
2. 启动 Webpack，**创建 Complier 对象**开始解析项目
3. 从入口文件 entry 开始解析，找到其导入依赖模块，递归遍历分析，形成**依赖关系树**
4. 不同依赖模块对应 **Loader** 进行编译，转为 JS 文件
5. 整个过程 Webpack 通过发布订阅模式向外抛出一些 hooks。而 webpack 的插件通过**监听这些关键事件节点执行插件任务**达到干预输出结果目的

其中文件的解析与构建是一个比较复杂的过程，在 webpack 源码中主要依赖于 <span style="color:orange">compiler</span> 和 <span style="color:orange">compilation</span> 两个核心对象实现



<span style="font-size:22px">文件解析与构建——complier, compilation：</span>

complier——全局单例，负责把控整个 Webpack 打包的构建流程。暴露 Webpack 整个生命周期相关的钩子 [compiler-hooks](https://link.juejin.cn/?target=https%3A%2F%2Fwebpack.js.org%2Fapi%2Fcompiler-hooks%2F)

compliation——每一次构建的上下文对象，它包含了当次构建所需要的所有信息。暴露模块和依赖有关的粒度更小的事件钩子 [Compilation Hooks](https://link.juejin.cn/?target=https%3A%2F%2Fwebpack.js.org%2Fapi%2Fcompilation-hooks%2F)

每次热更新和重新构建，compiler 都会重新生成一个新的 compilation 对象，负责此次更新的构建过程



<span style="font-size:22px">模块间依赖关系——AST</span>

 每个模块文件在通过 `Loader` 解析完成之后，会通过 `acorn` 库生成模块代码的 AST 语法树，通过语法树就可以分析这个模块是否还有依赖的模块，进而继续循环执行下一个模块的编译解析



## 事件机制——Tapable 事件流

![img](https://pic4.zhimg.com/80/v2-360b0b1bd8a6d5daffb6be712972b89f_720w.jpg)

Tapable 库提供许多钩子类，为 plugins 创建钩子。

```shell
$ npm i -S tapable
```

Usage：

```js
class Car {
	constructor() {
		// 最佳实践是将所有 hooks 暴露在一个变量
		this.hooks = {
			accelerate: new SyncHook(["newSpeed"]),
			brake: new SyncHook(),
			calculateRoutes: new AsyncParallelHook(["source", "target", "routesList"])
		};
	}
  // Hook会以最有效的方式构建一个运行你插件的方法
  setSpeed = (newSpeed) => {
    this.hooks.accelerate.call(newSpeed);
  }
}
// 使用这些hook
const myCar = new Car();
myCar.hooks.brake.tap("WarningLampPlugin", () => warningLamp.on());
```

+ 所有 Hook 类的构造函数接收一组可选数组参数——参数的字符串名字列表
+ `tap` 的第一个参数是标识这个插件的名字



### Hook Types

+ 如何运行被 tap 的函数（plugin）取决于钩子类型：
    + Basic hook——仅仅依次调用每一个 plugin
    + **Waterfall**——上一函数的返回值作下一函数的参数
    + **Bail**——允许提前退出，当有函数返回值时停止执行剩余函数
    + **Loop**—— 有返回值时重新运行第一个 plugin，直至所有 plugin return undefined



## Compiler

负责把控整个 Webpack 打包的构建流程

Extends 自 Tapable 的对象，用来注册和调用插件

<span style="font-size:22px">钩子函数:</span>

+ 访问：

    ```js
    compiler.hooks.someHook.tap("MyPlugin", (params) => {
      	// ...
    });
    ```

    > + [`someHook`](https://webpack.docschina.org/api/compiler-hooks/#hooks) 决定 Plugin 钩入到哪个阶段
    > + 取决不同钩子类型，不止有 `tap` 方法（*见 Tapable*）



# 实现

## Compiler 类

**调用：**

```js
// lib/index.js
const Compiler = require("./compiler");
const options = require("../myWebpack.config");
new Compiler(options).run();	// 开始编译
```

**主体结构：**

```js
module.exports = class Compiler {
  // 接收配置参数
  constructor(options) {
    // ...entry, output
    this.modules = [];
  }
  // 开始编译
  run() {}
  // 构建模块
  buildModule(filename, isEntry) {}
  // 输出文件
  emitFiles() {}
}
```

**run():**——开始编译

```js
run() {
 	// 1.加入入口文件到modules
  this.modules.push(this.buildModule(this.entry, true));
  // 2.递归加入依赖
  this.modules.map((_module) => {
    _module.dependencies.map(dependency => {
      this.modules.push(this.buildModule(dependency, false));
    })
  })
  // 3.输出文件
  this.emitFiles();  
}
```

**buildModule():**——构建模块

```js
buildModule(filename, isEntry) {
  // ...将获取到的module转化成AST
  return {
    filename,
    dependencies: getDepencies(ast),	// 依赖列表
    transformCode: transform(ast),  // 转化后代码
  }
}
```

> getAst, getDependencies, transform 来自 parser.js

**emitFiles():**——输出文件

```js
emitFiles() {
  // 1.对每个module处理成该格式
  this.modules.map((_module) => {
    modules += `"${_module.filename}" : function(require, module, exports) {${_module.transformCode}},`;
  });
	// 2.输出内容为IIFE-匿名闭包函数
  const bundle = `/*...*/`;
  // 3.输出文件
  fs.writeFileSync(outputPath, bundle, "utf-8");
}
```





## Loader

**开发原则：**

+ 返回值是标准的 JS 代码字符串——保证下一个 loader 能正常工作
+ 单一职责——只关心输入输出

**this 对象：**

loader 函数中的 `this` 上下文由 webpack 提供（ *指向一个叫`loaderContext` 的 `loader-runner` 特有对象* ），可以通过 `this` 对象提供的相关属性，获取当前 loader 需要的各种信息数据



## Plugin

使用阶段式的构建回调，可在 Webpack 构建流程中引入自定义行为

**开发原则：**

+ 必须是一个函数或者是一个包含 `apply` 方法的对象——才能访问 compiler 实例
+ 不能修改传给 plugin 的 compiler, compilation 对象——传给每个插件的都是同一个引用，若在一个插件中修改了它们身上的属性，会影响后面的插件
+ 异步事件需在 plugin 处理完调用回调通知 webpack 进入下一流程——不然会卡住



**Plugin 的构成：**

1. JS 函数或类
2. 插件函数的 prototype 上定义一个 `apply` 方法



# DEBUG

<span style="color: red">输出文件内容为空：</span>

```js
const fn = () => {
  console.log("你好babel");
}
console.log(fn) // 不能调用/不使用
// webpack不会编译未使用的代码
```

<span style="color: red">编译警告 [Webpack: "there are multiple modules with names that only differ in casing" but modules referenced are identical](https://stackoverflow.com/questions/47534267/webpack-there-are-multiple-modules-with-names-that-only-differ-in-casing-but): </span>

```js
// when引用时大小写乱用
import Vue from "vue"
```



# Ref

+ 总：

    [概念 | webpack 中文文档](https://webpack.docschina.org/concepts/)

+ 核心概念：

    [你必须知道的webpack插件原理分析](https://segmentfault.com/a/1190000038338386)

+ 搭建指南：

    [webpack入门 · 语雀](https://www.yuque.com/ldfgqb/fpkor3/qvop63)

+ 原理：

    [当面试官问Webpack的时候他想知道什么 - 掘金](https://juejin.cn/post/6943468761575849992)
    
    [tapable/README.md at master · webpack/tapable](https://github.com/webpack/tapable/blob/master/README.md)
