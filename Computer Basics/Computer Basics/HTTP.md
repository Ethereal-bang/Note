# HTTP

+ <span style="font-size:22px">状态码:</span>

    | 状态码 | 说明       |
    | ------ | ---------- |
    | 1xx    | 一般       |
    | 2xx    | 成功响应   |
    | 3xx    | 重定向     |
    | 400    | 客户端错误 |
    | 5xx    | 服务端错误 |

    

## HTTP 无状态

> Cookie，Session，JWT，Token针对HTTP无状态的解决方法，用来保存一些状态。例如保存登录状态，在一段时间内可以自动登录。本质上是在浏览器中存一个小文件，同一域名下发送请求都会自带Cookie，服务器可以通过`Set-Cookie`来进行设置

### Cookie

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



+ **常见默认端口号：**
    + Tomcat——8080
    + mysql——3306
    + http——80
    + https——443




### JWT

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



# 跨域

+ <span style="font-size:22px">源：</span>

    + **源 = 协议 + 域名 + 端口号**

    + 两个 url 的协议、域名、端口号完全一致，那么这两个 url 就是同源的

    + `window.origin`或`location.origin`得到当前源：

        ![image-20220303191239857](https://gitee.com/ethereal-bang/images/raw/master/20220303191246.png)

        ![image-20220303191411990](https://gitee.com/ethereal-bang/images/raw/master/20220303191412.png)

    + **同源策略：**不同源之间的页面，不准互相访问数据

+ <span style="font-size:22px">跨域：</span>

    由于同源策略的限制，发出的请求服务端收到后的响应被浏览器拦截

# REF

+ HTTP 无状态：

    [HTTP · 语雀](https://www.yuque.com/ldfgqb/fpkor3/ygl3b6)

+ Cookie：

    [一文带你看懂cookie，面试前端不用愁 - SegmentFault 思否](https://segmentfault.com/a/1190000017332168)

+ JWT:

    [Node.js 使用 express-jwt 解析 JWT - 掘金](https://juejin.cn/post/6844903988970651662)
    
    [node笔记：node express 下 jsonwebtoken+express-jwt实现token登录验证两种方式](https://blog.csdn.net/weixin_45295262/article/details/111828505)
    
    [前后端分离之JWT（JSON Web Token）的使用 - SegmentFault](https://segmentfault.com/a/1190000010444825)

