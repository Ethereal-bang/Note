# 配置

> **dependencies:**
>
> package.json 中`dependencies`，包括 express 包，和选用的视图引擎包（*pug*）外还有以下一些实用的包：
>
> - [cookie-parser](https://www.npmjs.com/package/cookie-parser)：用于解析 cookie 头来填充 `req.cookies`（提供了访问 cookie 信息的便捷方法）。
> - [debug](https://www.npmjs.com/package/debug)：一个小型 node 调试程序，仿照 node 核心的调试技术建立。
> - [http-errors](https://www.npmjs.com/package/http-errors)：处理错误中间件。
> - [morgan](https://www.npmjs.com/package/morgan)：node 专用 HTTP 请求记录器中间件。



## [nodemon](https://github.com/remy/nodemon)

只有重启服务器才能看到 Express 网站所做的改动。 nodemon 是最简便的自动化工具之一。

+ <span style="font-size:22px">安装</span>
    + 全局安装——可以在命令行直接使用

        ```shell
        $ sudo npm install -g nodemon
        ```

    + 添加到开发依赖——使用这个项目的开发人员只要安装这个应用就能自动获得

        ```shell
        $ npm install --save-dev nodemon
        ```

+ <span style="font-size:22px">使用</span>

    + 命令行启动

    + NPM 脚本中调用：

        package.json 的 `scripts` 部分添加如下：

        ```json
        "scripts": {
          "start": "node ./bin/www",
          "devstart": "nodemon ./bin/www"
        },
        ```



# 目录结构

## /bin/www

文件 **/bin/www** 是应用入口

1.  **`require()` 真实应用入口 app.js **，app.js 会设置并返回 `express()`应用对象。

    > **`express()`应用对象：**
    >
    > ```js
    > // app.js
    > 
    > /*
    > * app.set()
    > * app.use()
    > */
    > module.exports = app;
    > ```

    ```js
    #!/usr/bin/env node
    
    /**
     * Module dependencies.
     * 模块依赖项
     */
    
    var app = require('../app');
    ```

2. **为` app`设置端口:**

    ```js
    /**
     * Get port from environment and store in Express.
     * 从环境变量中的预定义值或默认值3000
     */
    
    var port = normalizePort(process.env.PORT || '3000');
    app.set('port', port);
    function normalizePort(val) {
      // ...
    }
    ```

3. **创建 HTTP 服务器：**

    ```js
    /**
     * Create HTTP server.
     */
    
    var server = http.createServer(app);
    ```

4. **监听请求，报告服务器错误和连接信息:**

    ```js
    /**
     * Listen on provided port, on all network interfaces.
     */
    
    server.listen(port);
    server.on('error', onError);
    server.on('listening', onListening);
    ```



## /app.js

创建一个 `express` 应用对象（依照惯例命名为 `app`），通过各种设置选项和中间件来设置这个应用，然后从该模块中导出

1. **`require`了一些实用 node 库:**

    ```js
    var createError = require('http-errors');
    var express = require('express');
    var path = require('path');// 用于解析文件和目录的核心 node 库
    var cookieParser = require('cookie-parser');
    var logger = require('morgan');
    var sassMiddleware = require('node-sass-middleware');
    ```

2. **`require` 用户路由目录中的模块:**

    ```js
    var indexRouter = require('./routes/index');
    var usersRouter = require('./routes/users');
    ```

3. **创建`app`对象——用导入的`express`模块：**

    ```js
    var app = express();
    
    // view engine setup
    app.set('views', path.join(__dirname, 'views'));	// 设置views指定模板的存储文件夹/vie
    app.set('view engine', 'pug');
    ```

    **然后用`app`对象设置==视图（模板）？==引擎：**



# 快速上手

+ 创建基础纯 Node 服务器：

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



# 新建一个 Express 项目

+ <span style="font-size:20px">视图引擎 view</span>

    Express 应用生成器支持多款流行的视图/模板引擎，包括 [EJS](https://www.npmjs.com/package/ejs)、[Hbs](http://github.com/donpark/hbs)、[Pug](https://pugjs.org/api/getting-started.html) (Jade)、[Twig](https://www.npmjs.com/package/twig) 和 [Vash](https://www.npmjs.com/package/vash)，缺省选项是 Jade

+ <span style="font-size:20px">CSS 引擎</span>

    Express 应用生成器支持最常见的 CSS 引擎：[LESS](https://lesscss.org/), [SASS](https://sass-lang.com/), [Compass](http://compass-style.org/), [Stylus](http://stylus-lang.com/)

    CSS 引擎提供了更强大的语法来定义 CSS，然后将定义编译为纯 CSS 供浏览器使用

+ <span style="font-size:20px">数据库</span>

    Express 应用可以使用 Node 支持的所有 [数据库](https://expressjs.com/en/guide/database-integration.html)，Express 本身不提供数据库管理机制

1. **WebStorm 直接选择新建 Express 项目**

    <img src="https://gitee.com/ethereal-bang/images/raw/master/20220121142835.png" alt="image-20220121142835129" style="zoom:43%;" />

    初始化如下：![image-20220121143455375](https://gitee.com/ethereal-bang/images/raw/master/20220121143455.png)

2. **运行骨架网站：**

    ```
    npm start
    ```

    进入`http://localhost:3000/`，终端显示：![image-20220121143846464](https://gitee.com/ethereal-bang/images/raw/master/20220121143846.png)

3. **自动化更新服务器：**

    找到 package.json 的 `scripts` 部分。在 `"start"` 添加 `"devstart"`，用`devstart`启动

    现在，如果编辑项目中的任何文件，服务器将自动重启（或随时使用 rs 命令来重启）。查看更新后的页面需要刷新浏览器。



# 参考

+ 新建一个 Express 项目:

    [Express 教程 2: 创建站点框架 - 学习 Web 开发 | MDN](https://developer.mozilla.org/zh-CN/docs/learn/Server-side/Express_Nodejs/skeleton_website)