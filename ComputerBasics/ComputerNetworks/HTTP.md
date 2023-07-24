# HTTP

HTTP 定义了浏览器如何向万维网服务器请求 www 文档，以及服务器怎样把文档传送给浏览器

+ 默认端口号 **80**
+ 面向**事务** (*要么所有信息交换都完成，要么依次交换都不进行*) 的应用层协议
+ HTTP 的报文通常都使用 **TCP 连接**传送
+ **无连接**——通信双方交换 HTTP 报文前不需先建立 HTTP 连接
+ **无状态**——协议对于事务处理没有记忆能力



## HTTP1.1

+ <span style="font-size:22px">长连接：</span>

    HTTP1.0 需要使用`keep-alive`参数来告知服务器端要建立一个长连接，而 HTTP1.1 **默认支持长连接**，一定程度上弥补了 HTTP1.0 每次请求都要创建连接的缺点。



## HTTP2.0

HTTP1.X 版本的缺陷概括来说是：线程阻塞，在同一时间，同一域名的请求有一定的数量限制，超过限制数目的请求会被阻塞。



# HTTP 报文结构

行 + 头 + 体

**请求报文：**

1. 请求行——描述基本信息 请求方法、请求资源的 URL、HTTP 版本：

    ```http
    GET http://www.xyz.com HTTP/1.1
    ```

2. 请求头 header——使用 key-value 形式更详细的说明报文：

    + HOST——指明主机域名 + 端口号
    + Connection——Keep-Alive 告诉服务器是否使用持续连接
    + Content-Type——`application/json`、...
    + ...

3. 请求体 body（消息正文）

<hr>
**响应报文：**

+ 响应行

    ```http
    HTTP/1.1 404 Not Found
    ```

+ 响应头
    + Content-Type
    + Server
    + ..
+ 响应体



+ <span style="font-size:22px">状态码:</span>

    | 状态码 | 说明                |
    | ------ | ------------------- |
    | 1xx    | 正在处理            |
    | 2xx    | 成功 处理完毕       |
    | 3xx    | 重定向              |
    | 400    | 客户端错误 无法处理 |
    | 5xx    | 服务端错误 处理出错 |



# HTTP 无状态

> Cookie，Session，JWT，Token 针对 HTTP 无状态的解决方法，用来保存一些状态。例如保存登录状态，在一段时间内可以自动登录。本质上是在浏览器中存一个小文件，同一域名下发送请求都会自带Cookie，服务器可以通过`Set-Cookie`来进行设置

**常见默认端口号：**

+ Tomcat——8080
+ mysql——3306
+ http——80
+ https——443

## Cookie

+ <span style="font-size:20px">cookie 属性：</span>

    > 获取 cookie 属性：`document.cookie`（只能获取非 HTTPOnly 类型 cookie）

    + Expires——设置失效时间

+ <span style="font-size:20px">Server side设置：</span>

    服务端可以设置cookie 的所有选项：expires、domain、path、secure、HttpOnly

    ```js
    res.writeHead(200, {
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin": "*", // 解决跨域
      "Set-Cookie": `username:${responseData.query.username},pwd:${responseData.query.pwd}`
    });
    ```

    > 但是这样设置后不知道客户端如何拿到这个 cookie。==？==

+ <span style="font-size:20px">Client side设置：</span>

    一个关于如何实现登录持久化的 demo：（*第一次登录后存储 cookie，后续直接判断相关 cookie 存在* ）

    ```js
    // 设置 c
    alert("登录成功");
    document.cookie = `username=${username}`
    document.cookie = `pwd=${pwd}`
    ```

    ```js
    // 判断是否有相关cookie
    const cookies = document.cookie.split("; ");
    cookies.map((cookie) => {
        cookie = cookie.split("=");
        if (cookie[0] === "username") {
            status.innerText = "状态：已登录";
            location.hash = "/person";
        }
    })
    ```



## Session

+ 存储到**服务器端**
+ Session 中可以保存**任意类型数据**
+ 为防止内存溢出，服务器会把长时间内没有活跃的Session从内存删除。这个时间就是 Session 的**有效时间**。超过了超时时间没访问过服务器，Session就自动失效——默认时间 30 min

<span style="font-size:20px">原理：</span>

1. **客户端第一次访问服务器**，请求中不携带任何标识，所以此时<span style="color:orange">服务器</span>无法找到与之对应的 session，因此<span style="color:orange">新建 session 对象</span>
2. **服务器进行响应时**，<span style="color:orange">服务器将 session 标识</span>放到响应头的 Set-Cookie中，以 key-value 的形式<span style="color:orange">返回给客户端</span>，例： `JSESSIONID=${val}`。其中 `val` 对应该客户端创建的 session 的 ID
3. **客户端再次访问服务器**，客户端将此 key-value <span style="color:orange">放到 cookie 中一并请求服务器</span>，服务器就根据此 ID 寻找对应 session 对象



## JWT

JWT——JSON Web Token，代替传统 session 认证的解决方案

> 目前最流行的跨域认证解决方案，是一种认证授权机制，基于json的开放标准。
>
> JWT 的声明一般被用来在身份提供者和服务提供者间传递被认证的用户身份信息，以便于从资源服务器获取资源。比如用在用户登录上。

+ **原理：**服务端生成一个包含用户唯一标识的 JSON 对象，颁发给客户端。客户端请求需要权限的接口时，只要把这个 JSON 再原样发回给服务端，服务器通过解析就可识别用户。

+ <span style="font-size:22px">express-jwt：</span>

    专用于 express 框架下解析 JWT 的中间件。

    1. **加入中间件:**

        ```js
        const expressJwt = require("express-jwt");
        
        app.use(expressJwt({
          secret: "secret1",
          algorithms: ["HS256"],  // ?这个字段不知什么用但required
        })
            .unless({ // 指定不经token解析的路径
              path: ['/login'],	// 支持正则
            }))
        ```

    2. **生成 token**——使用“jsonwebtoken”: 

        ```js
        const token = "Bearer " + jwt.sign(
        	{   // payload——自定义信息
            username: req.query.name,
            admin: true,
          },
          secret,	// secretOrPrivateKey
          {	// [options]可选
            expiresIn: 3600 * 24,    // 设置24h过期
          },	// [callback]
        )
        ```

    3. **发送 token 到前端:**

        ```js
        // eg
        res.json({
          flag: true,
          token: token,	// 上步创建的token
          msg: "注册成功",
        })
        ```

    4. **前端获取 token 并存储：**——将获取到的 token 存储后拦截请求加入响应头 Authorization。

        ```js
        // eg
        axios.get("/register")
        	.then(res => {
          	localStorage.setItem("token", res.data.token)
        })
        
         // 请求拦截
        axios.interceptors.request.use(config => {
          // 若存在token，加入响应头
          if (localStorage.getItem("token")) {
            config.headers.Authorization = `token ${localStorage.getItem("token")}`;
          }
          return config;
        })
        ```

    5. **获取解析内容**——收到带 token 请求解析成功后通过`req.user`访问:

        ```js
        app.get("/protected", (req, res) => {
        	if(!req.user.admin)
            return res.sendStatus(401);
        })
        ```

        > `req.user`实际就是上一步设置的 Token 的 payload 部分



### 前端

1. 从相应内容**获取 token**

2. 对 **token 解码**（选用 `jwt-decode` 插件）：

    ```shell
    yarn add jwt-decode
    ```

    ```js
    import jwt_decode from "jwt-decode";
    const token = jwt_decode(jwt); // 即后端传递的payload
    console.log(token.user)
    ```



# CORS

Access-Control-Allow-Origin



# REF

+ HTTP：

    [计算机网络（第七版）——谢希仁]()

    [review/4.1.HTTP协议.md at main · Martinyua/review](https://github.com/Martinyua/review/blob/main/4.NetWork/4.1.HTTP%E5%8D%8F%E8%AE%AE.md)

+ HTTP 报文结构：

    [review/4.1.HTTP协议.md at main · Martinyua/review](https://github.com/Martinyua/review/blob/main/4.NetWork/4.1.HTTP%E5%8D%8F%E8%AE%AE.md)

+ HTTP 无状态：

    [HTTP · 语雀](https://www.yuque.com/ldfgqb/fpkor3/ygl3b6)

    [一文带你看懂cookie，面试前端不用愁 - SegmentFault 思否](https://segmentfault.com/a/1190000017332168)

    [Node.js 使用 express-jwt 解析 JWT - 掘金](https://juejin.cn/post/6844903988970651662)

    [node笔记：node express 下 jsonwebtoken+express-jwt实现token登录验证两种方式](https://blog.csdn.net/weixin_45295262/article/details/111828505)

    [前后端分离之JWT（JSON Web Token）的使用 - SegmentFault](https://segmentfault.com/a/1190000010444825)
    
    [HttpSession详解 - myseries - 博客园](https://www.cnblogs.com/myseries/p/11588267.html)