# Express

Express 是最流行的 Node 框架，是许多其他流行 Node 框架的底层库

Express 是一个功能极简，完全是路由和中间件构成的 Web 开发框架，从本质上来说，一个 express 应用就是在调用各种中间件



## 配置

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



# 项目结构

![image-20220121143455375](https://gitee.com/ethereal-bang/images/raw/master/20220121143455.png)

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
    
    > ```js
    > function onListening() {
    >   // ...
    >   console.log(`Server is listening on http://localhost:${addr.port}`)
    > }
    > ```



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
    app.set('views', path.join(__dirname, 'views'));
    // 设置views指定模板的存储文件夹/views
    app.set('view engine', 'pug');
    // 设置view engine指定模板库pug
    ```
    
4. **`app.use()`将中间库添加进==请求处理链？==**：

    ```js
    app.use(logger('dev'));
    app.use(express.json());
    app.use(express.urlencoded({ extended: false }));
    app.use(cookieParser());
    app.use(sassMiddleware({
      src: path.join(__dirname, 'public'),
      dest: path.join(__dirname, 'public'),
      indentedSyntax: true, // true = .sass and false = .scss
      sourceMap: true
    }));
    app.use(express.static(path.join(__dirname, 'public')));	// express.static中间件将静态文件托管至根目录？
    ```

5. **==路由处理器添加到请求处理链？==：**

    ```js
    app.use('/', indexRouter);
    app.use('/users', usersRouter);
    ```

6. **错误和 404 响应添加处理方法的中间件：**

    ```js
    // catch 404 and forward to error handler
    app.use(function(req, res, next) {
      next(createError(404));
    });
    
    // error handler
    app.use(function(err, req, res, next) {
      // set locals, only providing error in development
      res.locals.message = err.message;
      res.locals.error = req.app.get('env') === 'development' ? err : {};
    
      // render the error page
      res.status(err.status || 500);
      res.render('error');
    });
    ```

7. **将`app`对象导出：**——使它可以通过`/bin/www`导入

    ```js
    module.exports = app;
    ```

    

## /routes——路由

**/routes** 目录中用不同模块保存应用路由

路由文件均使用类似结构，以 /routes/users.js 为例：

1. **获取`express.Router`对象**：

    ```js
    var express = require('express');
    var router = express.Router();
    ```

2. **为`router`对象指定路由并导出：**——就可以导入 app.js 了

    ```js
    router.get('/', function(req, res, next) {	// next涉及到多个路由处理器时
      res.statusCode = 200;
      res.send('respond with a resource');
    });
    
    module.exports = router;
    ```
    
    ```js
    // app.js:
    app.use('/users', usersRouter);
    ```
    
    在收到`/users/`URL 时将使用此路由。
    
    > 启动应用并访问 http://localhost:3000/users/ ，浏览器会响应："respond with a resource" 

> 路由相当于接口的不同网址，请求到哪个网址（*routes 中定义*）就返回哪些数据或是作出相应数据库操作（*controller* 中定义）

+ **发送数据：**

    ```js
    res.status(200)
      .json({
      flag: true,
      data: "book!!",
    })
    // ...
    res.statusCode = 200;
    res.send("book")
    ```

    

## /views——视图（模板）

+ **项目默认视图 .pug 文件：**![image-20220125095252104](https://gitee.com/ethereal-bang/images/raw/master/20220125095259.png)

    包括主页（**index.pug**）和基本模板（**layout.pug**）的视图。

+ **`response.render()`**用某对象的变量值渲染一个特定的模板，将结果作为响应发送：

    ```js
    /* GET home page. */
    router.get('/', function(req, res, next) {
      res.render('index', { title: 'Express' });
    });
    ```

+ <span style="font-size:22px">模板语法：</span>

    > 使用 Pug 的缺点，是它对<span style="color:red">缩进</span>和<span style="color:red">空格</span>敏感（如果在错误的位置添加额外的空格，可能会得到没什么帮助的错误代码）。但是，模板到位，它们就很容易阅读和维护。

    + **元素属性**定义在关联元素之后的括号中。括号内，属性定义在以逗号或空格分隔的属性名称和属性值对的列表中：

        ```jade
        link(rel='stylesheet', href='/stylesheets/style.css')
        meta(name='viewport' content='width=device-width initial-scale=1')
        ```

    + 标记（*HTML*元素）后跟着等号则视为 **JavaScript 表达式**：

        ```jade
        h1= title	// 标题变量，文件中定义或Express传递到模板
        p= 'Evaluated and <em>escaped expression</em>:' + title
        ```

    + **纯文本中插入转义和非转义数据**使用`#{}` 和`!{}`语法：

        以下代码显示（`title`在`res.render`传到模板）：![image-20220125101307871](https://gitee.com/ethereal-bang/images/raw/master/20220125101307.png)

        ```jade
        p Welcome to #{title}
        p This is a line with #[em some emphasis] and #[strong strong text] markup.
        p This line has an un-escaped string: !{'<em> is emphasised</em>'}, an escaped string: #{'<em> is not emphasised</em>'}, and escaped variables: #{title}.
        ```

        > 最好总是转义来自用户的数据（通过**`#{}`**语法。可信任的数据（例如，生成的记录计数等）可以不先转义就显示。

    + `|`开头表示**纯文本**：

        ```jade
        each book in book_list
            li
            a(href=book.url) #{book.title}
            |  (#{book.author.name})
        
        else
        		li There are no books.
        ```

        如果这里位于上一行而不是使用`|`，会成为超链接的一部分。

    + 使用`if`, `else` , `else if` 和 `unless`执行**条件操作**；`each-in` 或 `while`语法执行**循环/迭代操作**（*常用*）：

        ```jade
        ul
          each author in author_list
            li
              a(href=author.url) #{author.name}
              | (#{author.date_of_birth} - #{author.date_of_death})
        
          else
            li There are no authors.
        ```

        

    + mixins 创建**可重用的代码块**

    + **case 语句**

+ <span style="font-size:22px">扩展模板==？==：</span>

    一个站点中，通常所有页面都有一个共同的结构，包括页首，页脚，导航等的标准HTML标记。

    Pug 允许你声明一个基本模板，然后扩展它，只替换每个特定页面不同的地方，例如基本模板 **layout.pug**：

    ```jade
    // layout.pug
    doctype html
    html
      head
        title= title
        link(rel='stylesheet', href='/stylesheets/style.css')
      body
        block content
    ```

    ```jade
    // index.pug
    extends layout
    
    block content
      h1= title
      p Welcome to #{title}
    ```

    + 基本模板中 <span style="color:red">`block`</span>用于标记 “可在派生模板中替换的内容部分“
    + 扩展的模板中<span style="color:red">`extends`</span>标识使用的模板；<span style="color:red">`block section_name`</span>指示覆盖部分的新内容



# 一、创建站点框架

+ <span style="font-size:20px">项目构成：</span>

    <img src="https://mdn.mozillademos.org/files/16453/Express_MVC.png" alt="Express HTTP 请求/响应 路径" style="zoom:50%;" />

    + **路由：**把需要支持的请求（以及请求 URL 中包含的任何信息）转发到适当的控制器函数。
    + **Controller:** 是实际的请求处理函数，与路由请求代码是分开的。
    + **视图：**供控制器用来渲染数据。

+ <span style="font-size:20px">视图引擎 view</span>

    Express 应用生成器支持多款流行的视图/模板引擎，包括 [EJS](https://www.npmjs.com/package/ejs)、[Hbs](http://github.com/donpark/hbs)、[Pug](https://pugjs.org/api/getting-started.html) (Jade)、[Twig](https://www.npmjs.com/package/twig) 和 [Vash](https://www.npmjs.com/package/vash)，缺省选项是 Jade

+ <span style="font-size:20px">CSS 引擎</span>

    Express 应用生成器支持最常见的 CSS 引擎：[LESS](https://lesscss.org/), [SASS](https://sass-lang.com/), [Compass](http://compass-style.org/), [Stylus](http://stylus-lang.com/)

    CSS 引擎提供了更强大的语法来定义 CSS，然后将定义编译为纯 CSS 供浏览器使用

+ <span style="font-size:20px">数据库</span>

    Express 应用可以使用 Node 支持的所有 [数据库](https://expressjs.com/en/guide/database-integration.html)，Express 本身不提供数据库管理机制

1. **WebStorm 直接选择新建 Express 项目**

    <img src="https://gitee.com/ethereal-bang/images/raw/master/20220121142835.png" alt="image-20220121142835129" style="zoom:43%;" />

2. **运行骨架网站：**

    ```
    npm start
    ```

    进入`http://localhost:3000/`，终端显示：![image-20220121143846464](https://gitee.com/ethereal-bang/images/raw/master/20220121143846.png)

3. **自动化更新服务器：**

    找到 package.json 的 `scripts` 部分。在 `"start"` 添加 `"devstart"`，用`devstart`启动

    现在，如果编辑项目中的任何文件，服务器将自动重启（或随时使用 rs 命令来重启）。查看更新后的页面需要刷新浏览器。

4. **添加新路由：**

    ```js
    router.get('/', function(req, res, next) {
      res.send('respond with a resource');
    });
    router.get('/cool', (req, res, next) => {
      res.send('cool😎!');
    })
    ```

    访问 http://localhost:3000/users/cool 显示“cool😎!”。
    
    > 而 /cool 的一系列子路径定义在 routes/cool.js 文件下。
    
    > 路径大小写不区分



# 二、使用数据库

```js
// app.js
// 设置默认mongoose连接
const mongoDB = 'mongodb+srv://root:theday1012@cluster0.6rnte.mongodb.net/test?retryWrites=true&w=majority/test';
mongoose.connect(mongoDB)
// 让mongoose使用全局Promise库？
mongoose.Promise = global.Promise
// 取得默认连接
const db = mongoose.connection;
// 将连接与错误事件绑定（获得连接错误提示
db.on('error', console.error.bind(console, "MongoDB 连接错误："));
```



# 三、路由 Routes 和控制器 Controller

为站点所需资源端点（*Endpoint*）配置路由

确定页面中应显示哪些信息，然后定义适当的 URL 返回这些资源，随后创建路由（URL 处理器）和视图（模板）来显示这些页面。

路由是段 Express 代码，它将 <span style="color:red">HTTP 动词</span>（`GET`、`POST`、`PUT`、`DELETE` 等）、<span style="color:red">URL 路径/模式</span>和<span style="color:red">处理函数</span>三者关联起来。

+ 路由路径——路由路径除了字符串外还可以是正则表达式

+ 路由参数——命名段以冒号为前缀，然后是名称。捕获的值保存在 `req.params` 对象中

    ```js
    app.get('/users/:userId/books/:bookId', (req, res) => {
      // 通过 req.params.userId 访问 userId
      // 通过 req.params.bookId 访问 bookId
      res.send(req.params);
    });
    ```

    

1. <span style="font-size:20px">使用 Router 定义路由：</span>

    `Router.get()`定义的路由仅响应 HTTP GET 请求。

    ```js
    const express = require('express');
    const router = express.Router();
    
    // 主页路由
    router.get('/', (req, res) => {
      res.send('catalog 主页');
    })
    router.get('/about', (req, res) => {
      res.send('关于');
    })
    
    module.exports = router;
    ```

    这里的路由处理回调直接定义在了路由函数中，回调最好定义在单独的控制器模块 controller

    > **[响应方法](https://expressjs.com/en/guide/routing.html#response-methods)：**
    >
    > 除了`res.send()`，可调用 `res.json()` 来发送 JSON 响应，或调用 `res.sendFile()` 来发送文件。常使用的响应方法是 `render()`——使用模板和数据创建并返回 HTML 文件

2. <span style="font-size:20px">主应用中使用该路由模块:</span>

    ```js
    const catalog = require('./catalog.js');
    
    app.use('/catalog', catalog);
    ```

    这时 `catalog` 模块中定义的两个路由就可以从 `/catalog/` 和 `/catalog/about/` 访问了。

3. <span style="font-size:20px">创建路由处理器回调函数：</span>

    新建 /controllers 文件夹存放控制器，为每个模型创建单独的控制器文件（模块）

    ```js
    // authorController.js
    
    const Author = require('../models/author');
    
    // 显示完整的作者列表
    exports.author_list = (req, res) => { res.send('未实现：作者列表'); };
    
    // 为每位作者显示详细信息的页面
    exports.author_detail = (req, res) => { res.send('未实现：作者详细信息：' + req.params.id); };
    ```

    为每个需要处理的 URL 导出一个函数。

    所有函数都遵循 Express 中间件函数的标准形式，三个参数依次为：请求 `req`、响应 `res`、当前方法无法完成请求循环时（这里不存在这种情况）调用的 `next` 函数

4. <span style="font-size:20px">创建路由模组：</span>

    导入各模块，定义各路由

    ```js
    const express = require('express');
    const router = express.Router();
    
    // 导入控制器模块
    const book_controller = require('../controllers/bookController');
    const author_controller = require('../controllers/authorController');
    
    /// 藏书路由 ///
    
    // GET 获取藏书编目主页
    router.get('/', book_controller.index);
    
    // POST 请求添加新的藏书
    router.post('/book/create', book_controller.book_create_post);
    
    module.exports = router;
    ```

5. <span style="font-size:20px">在 app.js 中将路由添加到中间件链：</span>

    ```js
    const catalogRouter = require('./routes/catalog');
    
    app.use('/catalog', catalogRouter);  // 将 catalog 路由添加进中间件链
    ```

+ **获取路由参数：**

    `req.params.id`，根据id获取：

    ```js
    exports.genre_detail = (req, res, next) => {
    	Genre.findById(req.params.id)
      	.exec(function(err, genre) => {
              
              })
    ```

    

# 四、呈现数据

## conteoller 中 async 非同步流控制

+ **使用时机：**控制器代码中会依赖多重非同步要求的结果，需要以某种特定次序运行。为例管理流控制并在所有需要的2信息都可以取用时再绘制网页。

+ **安装：**

    ```shell
    $ npm i async
    ```

+ node async 模组有很多有用的方法，最重要的功能是：

    | 方法             | For                                      |
    | ---------------- | ---------------------------------------- |
    | async.parallel() | 并行执行操作                             |
    | async.series     | 序列执行的异步操作                       |
    | async.waterfall  | 序列运行的操作且每个操作取决前面操作结果 |
    
+ Eg:

    ```js
    exports.genre_detail = (req, res, next) => {
        async.parallel({    // 并行查询类型及其对应书本
            genre: (callback) => {
                Genre.findById(req.params.id)
                    .exec(callback);    // 相当于执行的是把查询记录存入genre的操作
            },
    
            genre_books: (callback) => {
                Book.find({ "genre": req.params.id })   
                    .exec(callback);
            },
        }, function (err, result) {    // 两个请求都完成时执行回调 result对象包括上述genre、genre_books
            // ...渲染到模板
        })
    }
    
    /// 与不使用async的写法对比：///
    exports.bookinstance_list = function(req, res, next) {
        BookInstance.find()
      			// 是在下面的回调里将查询记录存入第二个参数
            .exec(function (err, list_bookinstances) {
                if (err) { return next(err); }
                res.render('bookinstance_list', { title: 'Book Instance List', bookinstance_list: list_bookinstances });
            });
    };
    ```

    

## 模板

1. 设置站点的**基本模板** layout.pug、style.css

2. 在 conteoller 文件中**渲染到对应视图模板：**（*会去寻找`res.render()`的第一个参数对应文件*）

    ```js
    // bookController.js
    exports.book_list = function(req, res, next) {
        Book.find({}, 'title author')
            .populate('author')
            .exec(function (err, list_books) {
                if (err) { return next(err); }
                //Successful, so render
                res.render('book_list', { title: 'Book List', book_list: list_books });
            });
    };
    ```
    
3. 在路由文件中添加该路径：

    ```js
    // catalog.
    router.get('/books', book_controller.book_list);
    ```



# 五、==使用表单?== 

## [express-validator](https://www.npmjs.com/package/express-validator)——表单数据的验证和消毒

+ **验证和消毒:**

    在储存表单中的数据之前，必须对其进行验证和消毒：

    验证检查输入的值，适用于每个字段（范围，格式等），并且已为所有必填字段提供了值。

    清理删除/替换数据中的字符，可能用于将恶意内容发送到服务器。

+ 控制器中使用验证器，**从 "express-validator" 导入函数：**

### 验证

`body(fields[, message])`——验证`fields`字段，验证失败显示`message`：

```js
body('family_name')
  .isLength({min: 1}).trim().withMessage('必须指定姓氏。')
  .isAlphanumeric().withMessage('姓氏包含非字母数字字符。'),
body('date_of_birth', '无效生辰日期').optional({checkFalsy: true}).isISO8601(),
```

+ 提供特定错误消息——菊花链式连接验证器，`.withMessage()`。
+ 验证可选字段——`optional()`函数，仅在输入字段时运行后续验证

### 消毒——无害化处理

`sanitizeBody(fileds)`

```js
sanitizeBody('*').trim().escape(),	// 修剪、转义
sanitizeBody('genre.*').escape(),
```

+ `*`通配符，一次性处理所有字段

## 1.路由

为了实现表单处理代码，需要 2 个具有相同 URL 模式的路由。

1. GET 路由——显示创建对象的新空表单
2. POST 路由——验证用户输入的数据，保存信息，重定向详细信息页面 或 重新显示有错的表单

```js
// GET request for creating a Genre. NOTE This must come before route that displays Genre (uses id).
router.get('/genre/create', genre_controller.genre_create_get);

// POST request for creating Genre.
router.post('/genre/create', genre_controller.genre_create_post);
```

## 2.控制器

GET 路由——显示创建对象的新空表单：

```js
exports.genre_create_get = function(req, res, next) {
    res.render('genre_form', { title: 'Create Genre' });
};
```

<hr>

POST 路由——处理创建操作：验证用户输入的数据，保存信息，重定向详细信息页面 或 重新显示有错的表单：

```js
exports.genre_create_post = [   // 指定一组中间件函数。数组传递给路由器函数，并按顺序调用每个方法。（注意每个方法间有,
]
```

1. **运行验证器**——执行验证之前调用`trim()`，以删除任何尾随/前导空格：

    ```js
    // 验证器body——检查name字段是否为空
    body('name', 'Genre name required').isLength({ min: 1 }).trim(),
    ```

2. **创建清理程序**——调用`trim()`修剪名称字段和调用`escape()`转义任何危险的 HTML 字符：

    ```js
    sanitizeBody("name").trim().escape(),
    ```

    > 验证期间运行的清洁器不会修改请求。所以必须在上面的两个步骤中调用`trim()`。

3. **创建中间件函数检查错误**——来提取任何验证错误：

    ```js
    (req, res, next) => {
      	// 提取错误信息
        const errors = validationResult(req);
    
        var genre = new Genre(
          { name: req.body.name }
        );
    
        if (!errors.isEmpty()) {
            res.render('genre_form', { title: 'Create Genre', genre: genre, errors: errors.array()});
    		    return;
        }
        else {
        }
    }
    ```

    + 如有错误：再次渲染表单，传入清理种类对象和错误消息的数组（`errors.array()`）

    + 如种类名称数据有效，那么检查，是否已存在具有相同名称的种类`Genre`（因为我们不想创建重复项）
        + 重复项：重定向到现有种类的详细信息页面。
        + 未创建：保存新种类，并重定向到其详细信息页面。

## 3.视图

```js
// GET情况下：
res.render('genre_form', { title: 'Create Genre'});
// POST情况下：
res.render('genre_form', { title: 'Create Genre', genre: genre, errors: errors.array()});
```

+ <span style="font-size:20px">最终效果：</span>

    GET 路由显示：

    <img src="https://gitee.com/ethereal-bang/images/raw/master/20220127141737.png" alt="image-20220127141729928" style="zoom:33%;" />

    如果输入空格提交，将会显示错误信息：（POST 路由显示）

    <img src="https://gitee.com/ethereal-bang/images/raw/master/20220127141810.png" alt="image-20220127141810521" style="zoom:33%;" />

# 六、部署生产环境

+ 守护后台进程：

    ```shell
    $ nohup node app
    ```



# Debug

+ <span style="font-size:20px">404 not found:</span>

    + Q Desc：设置路由后仍显示该页面 404。

    + Reason：路径设置时成了`./`
    + Solution：改为`/`

+ <span style="font-size:20px">Cast to ObjectId failed:</span>

    + Q：Cast to ObjectId failed for value " 59347139895ea23f9430ecbb" at path "_id" for model "Genre"

    + S：将 id 转换成可用的

        ```js
        const mongoose = require("mongoose");
        
        exports.genre_detail = (req, res, next) => {
          const id = mongoose.Types.ObjectId(req.params.id);
        }
        ```

+ <span style="font-size:20px">id undefined:</span>

    + Q:  虚拟属性`url`用到 `this._id`但是为`undefined`。

    + R：箭头函数导致 this 丢失

    + S：改为普通函数

        ```js
        GenreSchema
            .virtual("url")
            .get(function() {
                return `/catalog/genre/${this._id}`;
            });
        ```

+ <span style="font-size:20px">[No write concern mode named](https://stackoverflow.com/questions/57179043/error-while-saving-data-in-mongodb-atlas-with-mongoose):</span>

    + Q：当操作数据库记录时，报错。

    + R：连接数据库选项设置出错。

    + S：

        ```js
        const mongoDB = 'mongodb+srv://root:theday1012@cluster0.6rnte.mongodb.net/test?retryWrites=true&w=majority';
        mongoose.connect(mongoDB)
        ```

+ <span style="font-size:20px">跨域：</span>

  ```js
  // app.js
  app.use((req, res, next) => {
    res.setHeader("Access-Control-Allow-Origin", "*");
    next();
  })
  ```
  
  > Note：
  >
  > 请求头设置要在路由之前。
  
+ <span style="font-size:20px">Cannot set headers after they are sent to the client：</span>

    + S_Desc：writeHeader 改为 setHeader 

    + S：

        ```js
        app.use((req, res, next) => {
          res.setHeader("Access-Control-Allow-Origin", "*");
          res.setHeader("Content-Type", "application/json");
          next();
        })
        ```

+ <span style="font-size:20px">[304](https://stackoverflow.com/questions/18811286/nodejs-express-cache-and-304-status-code)：</span>

    + Q_Desc:同一页面中第一次请求得到 200，重复请求返回 304（*显式设置状态码也不行* ）
    + R: Express 的缓存机制

# Refs

+ 新建一个 Express 项目:

    [Express 教程 2: 创建站点框架 - 学习 Web 开发 | MDN](https://developer.mozilla.org/zh-CN/docs/learn/Server-side/Express_Nodejs/skeleton_website)

