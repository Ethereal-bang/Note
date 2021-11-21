

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



##  Node.js配置

由于 Node.js平台是在后端运行 JS 代码，所以须先在本机安装 Node 环境。



亲身安装过程：

第一次：

在一系列瞎搞失败后我选择重头来过。

于是自认为彻底地删除掉了 node 及 npm 相关文件后再次操作。



1. 首先在官网选择下载安装![image-20210304144212176](https://i.loli.net/2021/04/11/IeXKnA8HPwsNbm9.png) 这一版本。

2. 将下载好的 node 安装在提前准备的文件夹里`E:\Nodejs\`

3. 参考 http://www.manongjc.com/detail/13-vpgpqsubgiktjmh.html 安装设置，保留默认安装设置，勾选了 Tools for Native Modules，也就是自动下载 本机模块工具。我也不是很懂，但上次没勾它就失败了，这次试试总没错。

4. 在完成 Node.js 安装后自动出现了一个 cmd，提示开始安装模块工具。在按几次 任意键继续后，窗口关闭。我推测应该是下载完成。

    这时候看到文件夹里已经出现了这些内容

<img src="C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20210304150119927.png" alt="image-20210304150119927" style="zoom: 50%;" />

5. **检查是否安装成功**可在 cmd 里输入`node -v`和`npm -v`，出现版本号。![image-20210304150417915](https://i.loli.net/2021/04/11/KlbE6RPfk5VnYLF.png)

6. **修改npm全局安装路径**。查阅[这个](https://blog.csdn.net/gaoqiang1112/article/details/80366250?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.control&dist_request_id=1328593.24612.16148396187116033&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.control)和[这个](https://blog.csdn.net/qq_36256590/article/details/114125780?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.control&dist_request_id=&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.control)的安装教程后发现要在同级文件夹里新建俩个文件夹。

这个是新建前使用`npm config ls`命令得到的结果：<img src="C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20210304151110695.png" alt="image-20210304151110695" style="zoom:50%;" />

在node.exe 同级下新建文件夹：node_global 和 node_cache。

7.根据新建的文件夹路径在**cmd 中修改**。![image-20210304151838452](https://i.loli.net/2021/04/11/KLpqE2GgBO8sn7h.png)

修改后使用`npm config ls`命令<img src="C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20210304152015156.png" alt="image-20210304152015156" style="zoom: 50%;" />

和博主的顺序不一样，但我想应该没关系。

8. **npm 的淘宝镜像**

    似乎是为了提高速度。在 cmd 里输入`npm config set registry https://registry.npm.taobao.org`。

    这样后再使用 `npm config ls`会发现那几个地址变成上述地址。

9. **配置环境变量**。查看网上教程发现自己遗漏了这一步，毕竟安装 node 时不是已经勾选了`Add to path`吗。也许这不是一回事吧。

    新建环境变量`NODE_PATH`，至于变量值设置成什么却有了疑义。在我看的三篇博客里都指向了不同的路径。不单单是简单的路径不同，而是新建的文件夹与原有的文件夹 node_modules 的关系混乱。于是我的进程止步于此。

    在查阅更多博客后又发现了新的说法，现在似乎不需要再新建环境变量`NODE_PATH`。

    上面的方法我不能证实，因此还是选择了老方法。

    新建系统环境变量，路径我怀疑地打下 `E:\Nodejs\node_global\node_modules`，但我真的难以理解node_global 这个空文件夹哪来的下一个文件夹。

    修改用户变量，path。改为`E:\Nodejs\node_global`

    10. 此时我依然不知道自己成功没有。在[另一篇博文](https://blog.csdn.net/qq_40646143/article/details/103237095?utm_term=%E5%8D%B8%E8%BD%BDnodejs&utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~all~sobaiduweb~default-3-103237095&spm=3001.4430)，看到关于淘宝镜像的另一种安装方式照着它那样试了之后，显示

        ![image-20210304191428418](https://i.loli.net/2021/04/11/8Nltym9JPivcR1f.png)

        按他说的使用`cnpm -v`命令后依然显示

        ![image-20210304191525626](https://i.loli.net/2021/04/11/qJPx7dyo6uOglEe.png)

10. 最后揭晓时刻。输入`npm install webpack -g`，没有出现报错。但是发现在 E 盘甚至 C 盘使用`webpack -v`都报错：`‘webpack’ 不是内部或外部命令，也不是可运行的程序或批处理文件`。后来发现是第七步设置错了地方，我的莫名其妙地创建到了`D:\Develop\nodejs\node_global`这里来。在这个目录下执行`webpack -v`它提示安装 webpack-cil，但我不敢轻举妄动，我想要卸载重来。

11. 决定卸载重装后，返回去看环境变量发现似乎没有完成以上步骤所应做的改动，这下我完全懵了。

12. 于是我又决定将就现在的路径，以后懂了再瞎搞回来。发现全局安装 webpack-cil 后才不会出现诸如`‘webpack’ 不是内部或外部命令，也不是可运行的程序或批处理文件`这样的报错。至此我的 webpack 总算安装完成。



在命令行内输入`node`进入 Node.js 的交互环境。在交互环境下可以输入任意 JS 语句，回车后得到输出结果。退出交互环境则连按两次 ctrl + c。



<hr>



在创建 react 脚手架时出错，于是卸载了 node 重装。

1. msi 后缀文件点击安装。
2. 勾选了某个不太清楚的选项。
3. 跳出一个命令行窗口，按几下提示安装完毕。
4. `node -v`、`npm -v`测试安装成功



5. 发现`D:\Develop\nodejs`文件夹下含有`node_global`和`node_cache`文件夹
6. 设置系统环境变量`NODE_PATH`值为`D:\Develop\nodejs\node_global\node_modules`
7. 命令行内执行`npm config set prefix "D:\Develop\nodejs\node_global"`和`npm config set cache "D:\Develop\nodejs\node_cache`
8. 测试：执行`npm install express -g`后，`node_global\node_modules`文件夹出现`experss`
9. 结果：依旧不能全局安装。



<hr>



[node环境变量配置 - CSDN](https://blog.csdn.net/jianleking/article/details/79130667)中的新方法，试验成功。





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

`fs`模块支持以标准 POSIX 函数建模的方式与文件系统进行交互

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



# Stream 流

Stream 是用于在 Node.js 中处理流数据的抽象接口，`stream`模块提供了用于实现流接口的 API

+ Stream 分为以下类型：
    + **Readable Stream**: 可读流，数据的产生者，(比如`fs.createReadStream()`)
    + **Writable Stream**: 可写流，数据的消费者，(比如`fs.createWriteStream()`)
    + **Duplex Stream**: 双向流，即可读也可写
    + **Transform Stream**: 转化流，数据的转化者

+ 最大的作用：读取大文件时不会一次性读入到内存中，每次只读取数据源的一个数据块，可立即处理该数据块后进入垃圾回收机制，而不用等待所有的数据
+ **Options：**
    - `flags` ：文件操作方式，默认值`r`——可读。
    - `encoding`：编码格式，默认值`null`
    - `fd` ：默认值`null`
    - `mode` ：文件模式，默认值`0o666`
    - `autoClose` ：默认值`true`
    - `emitClose` ：默认值`true`——流将在销毁后触发`close`事件
    - `start` 
    - `end` ：默认值`Infinity`
    - `highWaterMark` ：每次读取个数，默认值`64 * 1024`
    - `fs` ：默认值`null`



# Express 框架

Express 是最流行的 Node 框架，是许多其他流行 Node 框架的底层库

Express 是一个功能极简，完全是路由和中间件构成的 Web 开发框架，从本质上来说，一个 express 应用就是在调用各种中间件



# 参考链接

[Node.js - 廖雪峰的官方网站](https://www.liaoxuefeng.com/wiki/1022910821149312/1023025235359040)

[Web/01-Node.js介绍 Github](https://github.com/qianguyihao/Web/blob/master/10-Node.js%E5%92%8C%E6%95%B0%E6%8D%AE%E5%BA%93/01-Node.js%E4%BB%8B%E7%BB%8D.md#%E5%AE%98%E6%96%B9%E5%AE%9A%E4%B9%89)

[node安装后的设置(node_global和node_cache) - CSDN](https://blog.csdn.net/weixin_42752574/article/details/104878811)

+ fs 文件系统：

    [fs 文件系统 | Node.js API 文档](http://nodejs.cn/api/fs.html)

+ Stream 流：

    [stream 流 | Node.js API 文档](http://nodejs.cn/api/stream.html)

    [node学习之理解流（stream）- 掘金](https://juejin.cn/post/6921978173726916616)

    