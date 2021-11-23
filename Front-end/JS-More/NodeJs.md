

# Node.js 介绍

+ 在 Node.js 里运行 JavaScript，跟在浏览器里运行 JavaScript 有什么不同？

    二者采用的是同样的 JS 引擎。在 Node.js 里写 JS，和在前端写 JS，几乎没有不同。在写法上的区别在于：Node.js 没有浏览器、页面标签相关的 API，但是新增了一些 Node.js 相关的 API。通俗来说，对于开发者而言，在前端写 JS 是用于控制浏览器；而 **Node.js 环境写 JS 可以控制整个计算机**。

    JS 的组成分为三个部分：

    + ECMAScript：JS 的语法
    + DOM：标签元素相关的 API
    + BOM：浏览器相关的 API

    而 Node.js 的组成分为：

    + ECMAScript：ES 的所有语法都可以在 Node 环境中使用
    + Node 环境提供的一些**附加 API**（*包括文件、网络相关的 API*）



+ Node.js 使得 JS 既可以在浏览器前端进行 DOM 操作，又可以在后端调用操作系统资源，是目前最简单的全栈式语言



# 认识 npm

npm 其实是 Node.js 的包管理工具（*package manager*）

+ 必要性：

    > 为啥我们需要一个包管理工具呢，因为我们在Node.js上开发时，会用到很多别人写的JavaScript代码。如果我们要使用别人写的某个包，每次都根据名称搜索一下官方网站，下载代码，解压，再使用，非常繁琐。于是一个集中管理的工具应运而生：大家都把自己开发的模块打包后放到npm官网上，如果要使用，直接通过npm安装就可以直接用，不用管代码存在哪，应该从哪下载。
    >
    > 更重要的是，如果我们要使用模块A，而模块A又依赖于模块B，模块B又依赖于模块X和模块Y，npm可以根据依赖关系，把所有依赖的包都下载下来并管理起来。否则，靠我们自己手动管理，肯定既麻烦又容易出错。



# 执行 Node 程序

JS 的后端开发，我们编写的程序不再能在浏览器中执行，而是以命令行方式运行。因此需要编写 `.js`文件并且保存到某个目录，才能够执行。

例子：

``` js
// E:\nodeTest 文件夹内startTest.js文件
console.log("Hello nodeJs");

// cmd
C:\Users\HP>cd /d e:\nodeTest

e:\nodeTest>node startTest.js
Hello nodeJs
```



+ 使用严格模式

    在 JS 文件开头写上`'use strict';`，那么 Node 在执行该 JS 时将使用严格模式。



+ <span style="font-size:18px">示例：创建一个 Web 服务器</span>

    以下示例将创建一个 Web 服务器，监听对URL `http://127.0.0.1:8000/` 所有种类的 HTTP 请求。

    1. 在项目文件夹存入文件：

        ```js
        // 调用 HTTP 模块
        const http = require("http");
        
        // 创建 HTTP 服务器并监听 8000 端口的所有请求
        http.createServer((request, response) => {
        
           // 用 HTTP 状态码和内容类型来设定 HTTP 响应头
           response.writeHead(200, {'Content-Type': 'text/plain'});
        
           // 发送响应体 "Hello World"
           response.end('Hello World\n');
        }).listen(8000);
        
        // 在控制台打印访问服务器的 URL
        console.log('服务器运行于 http://127.0.0.1:8000/');
        ```

    2. 终端进入项目文件夹

    3. 终端输入：

        ```
        node "hello.js"
        ```

        浏览器查看 http://localhost:8000，可以看到一个写有”Hello World“的网页。



# 模块

文件里代码越长就会越不容易维护，为了编写可维护的代码，我们把很多函数分组，分别放到不同的文件里，这样每个文件包含的代码就相对较少。在 Node 环境中，一个`.js`文件就称为一个模块（*module*）



+ 使用模块的好处：
    1. 最大的好处时大大提高了代码的可维护性。
    2. 其次是编写代码不用从零开始，当一个模块编写完毕就可以被其他地方引用。我们编写程序时经常需引用其他模块，包括 Node 内置的模块和来自第三方的模块。
    3. 使用模块还可以避免函数名和变量名冲突。相同名字的函数和变量可以分别存在不同的模块中



模块名就是文件名去掉后缀`.js`

把`hello.js`文件改造一下，创建一个函数，这样我们就可以在其他地方调用这个函数：

``` js
'use strict';
let s = 'Hello';

function greet(name) {
    console.log(s + ',' + name + '!');
}

module.exports = greet;
```

函数`greet()`是我们在`hello`模块中定义的，最后一行这个赋值语句的意思是，把函数`greet`作为模块的输出暴露出去，这样其他模块就可以使用`greet`函数了	

其他模块如何使用`hello`模块的`greet`函数呢。我们再编写一个`main.js`文件，调用`hello`模块的`greet`函数：

``` js
'use strict'

// 引入 hello 模块
let greet = require('./hello');

let s = 'Jack';
greet(s);	// Hello,Jack!
```

引入`hello`模块用 Node 提供的**`require`函数**

上例**引入的模块作为**`greet`**变量保存**。其实`greet`变量就是在`hello.js`文件中用`module.exports = greet`输出的`greet`函数。

在使用`require()`引入模块时，要注意模块的**相对路径**。  
上例`main.js`和`hello.js`位于同一目录，所以我们使用**当前目录`.`**

``` js
let greet = require('./hello');
```

引入的对象具体是什么取决于该引入模块输出的对象



# fs 文件系统

`fs`( *file system* )模块支持以标准 POSIX 函数建模的方式与文件系统进行交互

> **POSIX：**
>
> 一般情况下，应用程序通过应用 API 而不是直接通过系统调用来编程。
>
> POSIX标准定义了操作系统应该为应用程序提供的接口标准。其出现是为了解决程序可移植性——完成同一功能因不同内核提供的 api 调用不同，导致不同操作系统下程序移植的问题
>
> [我从来没有真正明白：什么是 POSIX？- 问答 - 云+社区 - 腾讯云](https://cloud.tencent.com/developer/ask/26856)

+ <span style="font-size:20px">使用：</span>

    所有文件系统操作都具有同步、回调和基于 Promise 的形式

    ```js
    // CommonJS 语法：
    
    // 使用基于 Promise 的 api：
    const fs = require('fs/promise');
    
    // 使用回调和同步的 api：
    const fs = require('fs');
    ```

+ <span style="font-size:20px">常用 API：</span>

    + `fs.createWriteStream`：创建文件可写流
    + `fs.readFile`：同步读文件



# Events 模块

Node.js 大部分核心 API 都围绕**异步事件驱动架构**构建——某种类型的对象（*触发器* ）触发命名事件，使`Function`对象（*监听器* ）被调用

例如，`fs.readStream`打开文件时触发事件； Stream 在每当有数据可供读取时触发事件



## EventEmitter 类

events 模块只提供了一个对象：`events.EventEmitter`所有触发事件的对象都是`EventEmitter`类的实例

```js
# EventEmitter类由events 模块定义和暴露：
const EventEmitter = require('events');
```



+ **事件监听器`.on()`**：

    允许将多个函数绑定到对象触发的命名事件(*`on`传入的第一个参数* )

+ **触发事件`emit()`**：

    `EventEmitter`对象触发事件时，所有绑定的函数会被同步调用

+ **命名事件：**

    添加新的监听器时触发事件<span style="color:brown">`newListener`</span>，当删除现有监听器时触发事件<span style="color:brown">`removeListener`</span>

    

    







# Stream 流

Stream 是用于在 Node.js 中处理流数据的抽象接口，`stream`模块提供了用于实现流接口的 API

+ Stream 分为以下类型：
    + **Readable Stream**: 可读流，数据的产生者，(比如`fs.createReadStream()`)
    + **Writable Stream**: 可写流，数据的消费者，(比如`fs.createWriteStream()`)
    + **Duplex Stream**: 双向流，即可读也可写
    + **Transform Stream**: 转化流，数据的转化者
+ 最大的作用：读取大文件时不会一次性读入到内存中，每次只读取数据源的一个数据块，可立即处理该数据块后进入垃圾回收机制，而不用等待所有的数据
+ **Options：**
    - `flags`：文件操作方式，默认`r`
    - `encoding`：编码格式，默认值`null`
    - `fd` ：默认值`null`
    - `mode` ：文件模式，默认值`0o666`
    - `autoClose` ：默认值`true`
    - `emitClose` ：默认值`true`——流将在销毁后触发`close`事件
    - `start` 
    - `end` ：默认值`Infinity`
    - `highWaterMark` ：每次读取个数，默认值`64 * 1024`
    - `fs` ：默认值`null`

## 可读流

可读流有两种模式，随时可切换，可以通过监听可读流的事件操作它：

+ 流动模式：可读流自动读取数据，通过`EventEmitter`接口事件将数据提供给应用
+ 暂停模式，须显式调用==`stream.read`？==方法从流中读取数据片段

暂停模式切换到流动模式的 API：

+ 监听`data`事件
+ 调用`stream.resume()`
+ 调用`stream.pipe()`将数据发送到可写流

流动模式切换到暂停模式：

1. 不存==管道目标？==：调用`stream.pause()`
2. 存管道目标：调用`stream.unipipe()`取消`data`事件监听

可读流事件：`data`、`readable`、`error` 、`close` 、`end`



# Express 框架

Express 是最流行的 Node 框架，是许多其他流行 Node 框架的底层库

Express 是一个功能极简，完全是路由和中间件构成的 Web 开发框架，从本质上来说，一个 express 应用就是在调用各种中间件



# 参考链接

[Node.js - 廖雪峰的官方网站](https://www.liaoxuefeng.com/wiki/1022910821149312/1023025235359040)

[Web/01-Node.js介绍 Github](https://github.com/qianguyihao/Web/blob/master/10-Node.js%E5%92%8C%E6%95%B0%E6%8D%AE%E5%BA%93/01-Node.js%E4%BB%8B%E7%BB%8D.md#%E5%AE%98%E6%96%B9%E5%AE%9A%E4%B9%89)

[node安装后的设置(node_global和node_cache) - CSDN](https://blog.csdn.net/weixin_42752574/article/details/104878811)

+ fs 文件系统：

    [fs 文件系统 | Node.js API 文档](http://nodejs.cn/api/fs.html)

+ Events 模块：

    [events 事件触发器 | Node.js API 文档](http://nodejs.cn/api/events.html)

    [Node.js EventEmitter | 菜鸟教程](https://www.runoob.com/nodejs/nodejs-event.html)

+ Stream 流：

    [stream 流 | Node.js API 文档](http://nodejs.cn/api/stream.html)

    [node学习之理解流（stream）- 掘金](https://juejin.cn/post/6921978173726916616)

    [说说node中可读流和可写流](https://juejin.cn/post/6844903557066391565)
    
    