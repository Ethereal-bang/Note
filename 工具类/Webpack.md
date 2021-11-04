#  webpack 概述

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
    
3. 安装 `webpack-cli`（用于在命令行中运行 webpack）：

4. 安装`webpack-dev-server`==？==



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



# Webpack 搭建指南

## 1.基础构建

### 1.项目初始化

```diff
  webpack-demo
  |- package.json
+ |- index.html
+ |- /src
+   |- index.js
```

+ <span style="font-size:20px">package.json：</span>

    `npm init`后生成

    另外，我们需要调整`package.json`确保安装包是`private`（私有的），且移除`main`入口防止意外发布代码

    ```diff
    -  "main": "index.js",
    +  "private": true,
    ```

+ <span style="font-size:20px">src/index.js：</span>

    ```js
    function component() {
      const element = document.createElement('div');
    
      element.innerHTML = _.join(['Hello', 'webpack'], ' ');
    
      return element;
    }
    
    document.body.appendChild(component());
    ```

    其中，lodash 对于第 4 行的代码运行是必需的，而 lodash 目前在`index.html`中通过 script 标签引入。

在这个 demo 中，`<script>`存在**隐式依赖关系**。`index.js`执行前还需先在页面引入 [lodash](https://www.lodashjs.com/)。这是因为`index.js`未显式声明它需要 lodash。

总结来说这种方式去管理 JS 项目会有以下问题：

+ 没有直接体现——脚本执行依赖于外部库

+ 若依赖不存在、引入顺序错误：应用程序无法正常运行

+ 依赖被引入但没有使用：浏览器将被迫下载无用代码

    

接下来使用 Webpack 管理这些脚本。



### 2. 创建一个 bundle

1. <span style="font-size:20px">调整目录结构：</span>

    其中，`/dist`分发代码文件夹、`/src`源代码文件夹

    分发代码指构建过程中，经过最小化和优化后产生的输出结果，最终将在浏览器中加载

    ```diff
    webpack-demo
      |- package.json
    + |- /dist
    +   |- index.html
    - |- index.html
      |- /src
        |- index.js
    ```

    这里尽管`index.html`存放在`/dist`，但它是手动创建的。后续步骤将涉及如何生成 `index.html` 而非手动编辑它。如此做，便可安全地清空 `dist` 目录并重新生成目录中的所有文件。

2. <span style="font-size:20px">打包 lodash 依赖：</span>

    要在`index.js`中打包 lodash 依赖，需在本地安装 ==library？==

    ```bash
    npm install --save lodash
    ```

    > **--save、--save-dev：**
    >
    > 安装一个要打包到生产环境 bundle 中的 package 时，应使用 `npm install --save`
    >
    > 安装一个用于开发环境的 package 时（例如，linter, 测试库等），应使用 `npm install --save-dev`

3. <span style="font-size:20px">Script 中 import lodash：</span>

    src/index.js：

    ```diff
    +import _ from 'lodash';
    ```

    ES6 中的`import`、`export`语句能被 Webpack 很好地支持

    这里`index`显式要求 lodash 必须存在，且将其绑定为`_`避免全局污染。

    通过声明模块所需的依赖，Webpack 能够利用这些信息去**构建依赖图**，然后使用**图** **生成**一个优化过的且以正确顺序执行的 **bundle**

4. <span style="font-size:20px">更新`index.html`：</span>

    ```diff
    -	<script src="https://unpkg.com/lodash@4.17.20"></script>
    </head>
    <body>
    -    <script src="./src/index.js"></script>
    +    <script src="main.js"></script> 	<!--y加载 bundle-->
    ```

    现在，我们将会打包所有脚本，我们必须更新 `index.html` 文件：由于现在是通过 `import` 引入 lodash，所以要将 lodash `<script>` 删除，然后修改另一个 `<script>` 标签来**加载 bundle**，而不是原始的 `./src` 文件。

5. <span style="font-size:20px">运行 Webpack：</span>

    ```bash
    $ npx webpack
    ```

    执行以上命令经历了哪些过程：会将`src/index.js`作为**入口起点**，生成`dist/main.js`作为**输出**。（运行安装的 Webpack package 中的 webpack 二进制文件（即 `./node_modules/.bin/webpack`）

6. <span style="font-size:20px">测试：</span>

    浏览器里打开`index.html`，看到`Hello webpack`。



### 3. 使用一个配置文件

Webpack 中可以无须任何配置，然后大多数项目会需要很复杂的配置，所以 Webpack 仍要支持配置文件，这比终端里输入大量命令高效的多

1. <span style="font-size:20px">Project：</span>

    ```diff
    	|- package.json
    + |- webpack.config.js
      |- /dist
        |- index.html
      |- /src
        |- index.js
    ```

2. <span style="font-size:20px">webpack.config.js：</span>

    ```js
    const path = require('path');
    
    module.exports = {
      entry: './src/index.js',
      output: {
        filename: 'main.js',
        path: path.resolve(__dirname, 'dist'),
      },
    };
    ```

3. <span style="font-size:20px">通过新的配置文件再次执行构建：</span>

    ```bash
    $ npx webpack --config webpack.config.js
    ```

    > **--config：**
    >
    > 如果 `webpack.config.js` 存在，则 `webpack` 命令将默认选择使用它。
    >
    > 这里使用 `--config` 选项只是表明可以传递任何名称的配置文件，这对于需要拆分成多个文件的复杂配置非常有用

    比起 CLI 简单直接的使用方式，配置文件更具灵活性。可以通过配置方式指定 loader 规则(loader rule)、plugin(插件)、resolve 选项，以及许多其他增强功能



### 4. npm scripts

用 CLI 运行本地的 Webpack 副本不是很方便，可以设置一个**快捷方式**。

+ <span style="font-size:20px">调整 package.json：</span>

    调整 package.json 文件，添加一个 [npm script](https://docs.npmjs.com/misc/scripts)：

    ```diff
    	"scripts": {
        "test": "echo \"Error: no test specified\" && exit 1",
    +   "build": "webpack"
       },
    ```

+ <span style="font-size:20px">npm run build：</span>

    现在，可以使用 `npm run build` 命令，来替代我们之前使用的 `npx` 命令。

    使用 npm `scripts`，我们可以像使用 `npx` 那样通过模块名引用本地安装的 npm packages。

    ```bash
    $ npm run build
    ```

    > Tip
    >
    > 若第一步配置失败，此时会显示`npm ERR! missing script: build`。



## 2. 管理资源

这一节将学习如何通过 webpack 来管理资源，例如 images、fonts。



+ <span style="font-size:20px">修改输出名：</span>

    将前面设置的`main.js`改为`bundle.js`。对应的`webpack.config.js`和`index.html`中都要修改。



### 加载 CSS

+ <span style="font-size:20px">添加 loader：</span>

    为了在 JavaScript 模块中 `import` 一个 CSS 文件，需要安装 [style-loader](https://webpack.docschina.org/loaders/style-loader) 和 [css-loader](https://webpack.docschina.org/loaders/css-loader)，并在 [`module` 配置](https://webpack.docschina.org/configuration/module) 中添加这些 loader：

    ```bash
    npm install --save-dev style-loader css-loader
    ```

+ <span style="font-size:20px">webpack.config.js：</span>

    在`webpack.config.js`字段加入 loader。

    ```diff
    const path = require('path');
    
     module.exports = {
       entry: './src/index.js',
       output: {
         filename: 'bundle.js',
         path: path.resolve(__dirname, 'dist'),
       },
    +  module: {
    +    rules: [
    +      {
    +       test: /\.css$/i,
    +        use: ['style-loader', 'css-loader'],
    +     },
    +    ],
    +  },
     };
    ```

    > **`test`字段：**
    >
    > Webpack 根据正则表达式，来确定应该查找哪些文件，并将其提供给指定的 loader
    >
    > 在这个示例中，所有以 `.css` 结尾的文件，都将被提供给 `style-loader` 和 `css-loader`。

    模块 **loader 可链式调用**，链中的每个 loader 都将对资源进行转换。第一个 loader 将其结果（被转换后的资源）传递给下一个 loader，链中的最后的 loader 返回 JavaScript。

    应保证 loader 的**先后顺序**：`style-loader`在`css-loader`之前，不然报错

    

    现在尝试一下：

+ <span style="font-size:20px">引入 CSS 文件：</span>

    project：

    ```diff
    webpack-demo
      |- package.json
      |- webpack.config.js
      |- /dist
        |- bundle.js
        |- index.html
      |- /src
    +   |- style.css
        |- index.js
      |- /node_modules
    ```

    `npm run build`后，可以发现`Hello webpack`样式成功更改。（若 loader 配置失败，编译 CSS 文件会报错，因为无法编译）



### 加载 Images

+ <span style="font-size:20px">Webpack.config.js：</span>

    与 CSS 文件的区别是，Webpack 可以使用内置的 [asset modules](https://webpack.docschina.org/guides/asset-modules/) 而无需配置额外 loader。

    ```diff
    module: {
    	rules: [
      	{
        	test: /\.css$/i,
          use: ['style-loader', 'css-loader'],
         },
    +    {
    +      test: /\.(png|svg|jpg|jpeg|gif)$/i,
    +      type: 'asset/resource',
    +    },
      ],
    },
    ```



现在尝试添加图片资源在项目中：

+ <span style="font-size:20px">project：</span>

    ```diff
    |- package.json
      |- webpack.config.js
      |- /dist
        |- bundle.js
        |- index.html
      |- /src
    +   |- icon.png
        |- style.css
        |- index.js
      |- /node_modules
    ```

+ <span style="font-size:20px">index.js：</span>

    这里在`index.js`文件中将图像添加：

    ```js
    // 引入
    import Icon from './icon.jpg';
    
    // 将图像添加到我们已经存在的 div 中。
    const myIcon = new Image();
    myIcon.src = Icon;
    element.appendChild(myIcon);
    ```

重新构建，可以看到`dist`下重命名的 jpg 文件。



### 加载 fonts

```js
// webpack.config.js
	{
    test: /\.(woff|woff2|eot|ttf|otf)$/i,
    type: 'asset/resource',
  },
```





### 加载数据



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



### 2.设置 HtmlWebpackPlugin

+ <span style="font-size:20px">安装插件：</span>

    ```bash
    npm install --save-dev html-webpack-plugin
    ```

+ <span style="font-size:20px">Webpack.config.js：</span>

    ```diff
    +const HtmlWebpackPlugin = require('html-webpack-plugin');
    
     module.exports = {
       entry: {
         index: './src/index.js',
         print: './src/print.js',
       },
    +  plugins: [
    +   new HtmlWebpackPlugin({
    +      title: '管理输出',
    +    }),
    +  ],
       output: {
         filename: '[name].bundle.js',
         path: path.resolve(__dirname, 'dist'),
       },
     };
    ```



+ <span style="font-size:20px">构建：</span>

    执行`npm run build`后发生了什么：

    ![image-20211030153712719](https://gitee.com/ethereal-bang/images/raw/master/20211030153712.png)

    可以看出，虽然在 `dist/` 文件夹我们已经有了 `index.html` 这个文件，然而 `HtmlWebpackPlugin` 还是会默认生成它自己的 `index.html` 文件。

    打开`index.html`可以发现，已经被该插件生成的替换为了全新的文件，所有的 bundle 自动添加到 html 中。



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

测试：==？==

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

    

