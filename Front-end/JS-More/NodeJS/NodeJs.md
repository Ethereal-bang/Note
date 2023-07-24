

# Node.js

**更新 node 版本：**

+ Linux——n 模块

+ Windows：

    ```shell
    $ where node
    ```

    在该路径覆盖安装所需版本



## 配置

[下载 | Node.js](https://nodejs.org/zh-cn/download/)

```shell
node -v
npm -v
```



**环境变量：**

1. 添加 NODE_PATH 变量值指向安装目录
2. Path 变量中添加 `%NODE_PATH%`

> 不生效尝试重启



## nvm 管理 node 版本

有些项目执行 `yarn install` 时会提示 "The engine "node" is incompatible with this module."。这时需要切换 node 版本

nvm 是 Mac 下的 node 管理工具



**安装：**

1. 卸载全局安装的 node/npm（安装程序里选择 remove）
2. Windows 下安装 [nvm-windows](https://github.com/coreybutler/nvm-windows)，选择 nvm-setup 免去自行配置（nvm -v 安装成功，不成功尝试重启）

3. 设置 node & npm 镜像地址

   在 nvm/settings.txt 文件添加：

   ```
   node_mirror: https://npm.taobao.org/mirrors/node/
   npm_mirror: https://npm.taobao.org/mirrors/npm/
   ```

   

<span style="font-size:20px">使用</span>

+ nvm ls available——显示可下载版本列表
+ nvm install <版本号>
+ nvm use <>——切换版本
+ nvm uninstall <>
+ nvm ls——列出已安装版本



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

## fs

 file system 模块支持以标准 POSIX 函数建模的方式与文件系统进行交互

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

<span style="font-size:20px">读：</span>

| API       | Description                  |
| --------- | ---------------------------- |
| .readFile | 同步                         |
| .stat     | 返回文件或目录的信息         |
| .readdir  | 获取路径下文件和目录返回数组 |

+ **buffer**：数据将写入的缓冲区

    > **Buffer 转 string:**
    >
    > ```js
    > fs.readFile("myFile.txt", (err, buff) => {
    >   if (err) {
    >     console.error(err);
    >     return;
    >   }
    > 
    >   console.log(buff.toString());
    > });
    > 
    > fs.readFile("b.md").toString();
    > ```

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



<span style="font-size:20px">写文件：</span>

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
    __dirname // F:\FE-Develop\test-node
    __filename	// F:\FE-Develop\test-node\test-path.js
    ```

## http

1. 创建服务器：服务器可以监听客户端的请求
2. 接收请求与响应请求：客户端可以使用浏览器或终端发送 HTTP 请求，服务器接收请求后返回响应数据

+ **.createServer() 返回 server 对象：**

    ```js
    http.createServer(function (request, response) {
        response.writeHead(200, {'Content-Type': 'text/plain'});
        response.end('Hello World\n');
    })
      .listen(8888);
    ```



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



## net 网络

提供了一些用于底层的网络通信 API，用于创建基于流的 TCP 或 [IPC](http://nodejs.cn/api/net.html#ipc-support) 服务器 ([`net.createServer()`](http://nodejs.cn/api/net.html#netcreateserveroptions-connectionlistener)) 和客户端 ([`net.createConnection()`](http://nodejs.cn/api/net.html#netcreateconnection))

+ <span style="font-size:22px">net.Server:</span>

    创建一个 TCP或==IPC?==或本地服务器。



# Other

## nodemon

检测文件改变自动重启 server



## [marked](https://github.com/markedjs/marked)

Markdown to html

```js
// Koa
const path = "/Users/srf/Documents/README.md";
const md = fs.readFileSync(path).toString();
marked.setOptions({
  renderer: new marked.Renderer(),
  gfm: true,
  tables: true,
  breaks: true,
  pedantic: false,
  sanitize: true,
  smartLists: true,
  smartypants: false
});
ctx.type = "html";
ctx.body = marked(md);
```



# REF

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
    
+ net 网络：

    [net 网络 | Node.js API 文档](http://nodejs.cn/api/net.html#net)

    [Node.js Net 模块 | 菜鸟教程](https://www.runoob.com/nodejs/nodejs-net-module.html)