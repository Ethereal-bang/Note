

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
    > 更重要的是，如果我们要使用模块A，而模块A又依赖于模块B，模块B又依赖于模块X和模块Y，npm可以根据依赖关系，把所有依赖的包都下载下来并管理起来。否则，靠我们自己手动管理，既麻烦又容易出错。



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

    > 127.0.0.1 指本机

    1. 在项目文件夹存入文件：
    
        ```js
        // 加载 HTTP 模块
        const http = require("http");
        const hostname = '127.0.0.1';
        const port = 3000;
        
        // 创建 HTTP 服务器
        const server = http.createServer((req, res) => {
        
          // 用 HTTP 状态码和内容类型（Content-Type）设置 HTTP 响应头
          res.statusCode = 200;
          res.setHeader('Content-Type', 'text/plain');
        
          // 发送响应体
          res.end('Hello World\n');
        });
        
        // 监听 3000 端口的请求，注册一个回调函数记录监听开始
        server.listen(port, hostname, () => {
          console.log(`服务器运行于 http://${hostname}:${port}/`);
        });
        ```
    
    2. 终端进入项目文件夹
    
    3. 终端输入：
    
        ```
        node "hello.js"
        ```
    
        浏览器查看 http://localhost:8000，可以看到一个写有”Hello World“的网页。



# 模块导入导出

 Node 环境中，一个`.js`文件就称为一个模块（*module*），遵循 CommonJS 语法

+ 模块名就是文件名去掉后缀`.js`，**引入的模块作为变量保存**

+ **导出模块：（方法与函数）**

    ```js
    // 一：在module.exports加了一个add方法
    module.exports.add = (x, y) => x + y;
    
    // 二：将整个模块赋值为一个函数
    module.exports = (x, y) => x - y;
    ```

+ **引入模块：**

    ```js
    const fs = require("fs");
    
    // 对应第一种导出方式
    const add = require("./add.js");
    add.add(1, 2);
    
    // 对应第二种导出方式
    const minus = require("./minus");
    minus(1, 2);
    ```



# Node 内置基本模块

## fs——文件系统模块

`fs`( *file system* )模块支持以标准 POSIX 函数建模的方式与文件系统进行交互

> **POSIX：**
>
> 一般情况下，应用程序通过应用 API 而不是直接通过系统调用来编程。
>
> POSIX标准定义了操作系统应该为应用程序提供的接口标准。其出现是为了解决程序可移植性——完成同一功能因不同内核提供的 api 调用不同，导致不同操作系统下程序移植的问题
>
> [我从来没有真正明白：什么是 POSIX？- 问答 - 云+社区 - 腾讯云](https://cloud.tencent.com/developer/ask/26856)

### 形式

所有文件系统操作都具有<span style="color:red">同步</span>、<span style="color:red">回调</span>和<span style="color:red">基于 Promise</span> 的形式

> 基于 Promise 的操作——返回 Promise
>
> 回调形式——将完成回调作为最后参数异步调用，传给回调参数取决方法，但第一个参数始终为异常（最大性能时基于回调的 API 都比使用 Promise API 更好）
>
> 同步 API——立即抛出异常，可以用`try...catch`处理或==使其冒泡？==

同步方法就是在异步 API 后加一`Sync`后缀

```js
// CommonJS 语法：

// 使用回调和同步的 api：
const fs = require('fs');

// 使用基于 Promise 的 api：
const fs = require('fs/promise');
fs.stat("./2.json")
    .then(res => {
        console.log(res);
    }, err => {
        console.log("返回信息时出错：", err);
    })
```

### API

+ <span style="font-size:20px">读：</span>

    | API       | Description                  |
    | --------- | ---------------------------- |
    | .readFile | 同步                         |
    | .stat     | 返回文件或目录的信息         |
    | .readdir  | 获取路径下文件和目录返回数组 |

    + **buffer**：数据将写入的缓冲区

    + **offset:**要写入数据的`buffer`中位置

    + **length**：读取字节数；

    + **fd:** (*file descriptor* )：represents a **file descriptor**, useful to access that opened file inside the callback.

    + **stats 对象:**包含很多字段，常用有：

        ```typescript
        export interface StatsBase<T> {
          isFile(): boolean // 判断是否是一个文件
          isDirectory(): boolean // 判断是否一个目录
        
          size: T // 大小（字节数）
          atime: Date // 访问时间
          mtime: Date // 上次文件内容修改时间
          ctime: Date // 上次文件状态改变时间
          birthtime: Date // 创建时间
        }
        ```

        常用来取文件大小，做一些判断逻辑如发布时检测文件大小是否符合规范。

    > **fs.read()、fs.readFile()：**
    >
    > `fs.readFile()`方法是对`fs.read()`方法的进一步封装，`fs.readFile()`方法可以方便的读取文件的全部内容。

+ <span style="font-size:20px">写文件：</span>

    | API        | Desc |
    | ---------- | ---- |
    | .writeFile |      |

## path

| API      | Desc         | Use                                                      |
| -------- | ------------ | -------------------------------------------------------- |
| .join    | 连接路径     | 正确使用当前系统的路径分隔符                             |
| .resolve | 构建绝对路径 | 保证无论在什么目录下执行 node 程序都可以正确找到文件地址 |

+ **系统的路径分隔符：**Unix 系统是"`/`"，Windows 系统是" `\`"

+ **__dirname:**当前模块的目录名

    ```js
    console.log(__dirname, " ", __filename)
    // F:\FE-Develop\test-node  F:\FE-Develop\test-node\test-path.js
    ```

## http

1. 创建服务器：服务器可以监听客户端的请求
2. 接收请求与响应请求：客户端可以使用浏览器或终端发送 HTTP 请求，服务器接收请求后返回响应数据

+ **.createServer() 创建服务器：**

    ```js
    http.createServer(function (request, response) {
    
        // 发送 HTTP 头部 
        // HTTP 状态值: 200 : OK
        // 内容类型: text/plain
        response.writeHead(200, {'Content-Type': 'text/plain'});
    
        // 发送响应数据 "Hello World"
        response.end('Hello World\n');
    }).listen(8888);
    
    // 终端打印如下信息
    console.log('Server running at http://127.0.0.1:8888/');
    ```

### 实现后端

+ 服务端（==接口应该也是这样实现的吧？==：

    服务端写好并运行后，浏览器里访问对应网址服务端会作出反应。

    ![image-20220123150432201](https://gitee.com/ethereal-bang/images/raw/master/20220123150439.png)

    <img src="https://gitee.com/ethereal-bang/images/raw/master/20220123150800.png" alt="image-20220123150800534" style="zoom:53%;" />

+ 客户端：

    客户端是向服务端发送请求

    

## buffer——缓冲区

`buffer`对象用于表示固定长度的字节序列

+ <span style="font-size:20px">Buffer 与 字符串：</span>

    Buffer 与 字符串间利用`.toString`、`Buffer.from()`转换，可以指定字符编码（默认 UTF-8）

+ <span style="font-size:20px">Buffer.alloc(size[, fill[, encoding]])：</span>

    分配`size`字节的新 Buffer。若`fill = undefined`，Buffer 将以 0 填充：

    ```js
    const { Buffer } = require('buffer');
    
    Buffer.alloc(5)	// <Buffer 00 00 00 00 00>
      
    Buffer.alloc(5, 'a')	// <Buffer 61 61 61 61 61>	
    ```



## events——事件触发器

Node.js 大部分核心 API 都围绕**异步事件驱动架构**构建——某种类型的对象（*触发器* ）触发命名事件，使`Function`对象（*监听器* ）被调用

例如，`fs.readStream`打开文件时触发事件； Stream 在每当有数据可供读取时触发事件



### EventEmitter 类

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

    添加新的监听器时(*第一个事件不算* ）触发事件<span style="color:brown">`newListener`</span>，当删除现有监听器时触发事件<span style="color:brown">`removeListener`</span>



## Stream 流

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

### 可读流

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



# Refs

[Node.js - 廖雪峰的官方网站](https://www.liaoxuefeng.com/wiki/1022910821149312/1023025235359040)

[Web/01-Node.js介绍 Github](https://github.com/qianguyihao/Web/blob/master/10-Node.js%E5%92%8C%E6%95%B0%E6%8D%AE%E5%BA%93/01-Node.js%E4%BB%8B%E7%BB%8D.md#%E5%AE%98%E6%96%B9%E5%AE%9A%E4%B9%89)

[node安装后的设置(node_global和node_cache) - CSDN](https://blog.csdn.net/weixin_42752574/article/details/104878811)

[Node.js入门 · 语雀](https://www.yuque.com/ldfgqb/fpkor3/feq47y)

+ fs——文件系统：

    [fs 文件系统 | Node.js API 文档](http://nodejs.cn/api/fs.html)

    [Node.js文件系统模块fs的两种文件读取方式比较：fs.read()和fs.readFile() - IT笔录](https://itbilu.com/nodejs/core/4kSWXYWGg.html)

+ http：

    [Node.js 创建第一个应用 | 菜鸟教程](https://www.runoob.com/nodejs/nodejs-http-server.html)

+ Events 模块：

    [events 事件触发器 | Node.js API 文档](http://nodejs.cn/api/events.html)

    [Node.js EventEmitter | 菜鸟教程](https://www.runoob.com/nodejs/nodejs-event.html)

+ Stream 流：

    [stream 流 | Node.js API 文档](http://nodejs.cn/api/stream.html)

    [node学习之理解流（stream）- 掘金](https://juejin.cn/post/6921978173726916616)

    [说说node中可读流和可写流](https://juejin.cn/post/6844903557066391565)
    
    