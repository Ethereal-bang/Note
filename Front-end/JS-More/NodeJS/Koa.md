> **Doc:**
>
> [koajs](https://koa.bootcss.com/)

> **教程:**
>
> [koa - 廖雪峰](https://www.liaoxuefeng.com/wiki/1022910821149312/1023025933764960)
>
> [给你一个开箱即用，功能完善的koa项目 - 掘金](https://juejin.cn/post/6844903855772303367)
>
> [guo-yu/koa-guide 中文教程 - GitHub](https://github.com/guo-yu/koa-guide)
>
> [chenshenhai/koa2-note 进阶学习笔记 - GitHub](https://github.com/chenshenhai/koa2-note)
>
> [Koa从零搭建到Api实现项目的搭建方法（koa2项目部署）](https://www.eolink.com/news/post/326.html)

> **EG:**

> [koajs/examples - GitHub](https://github.com/koajs/examples/)
>
> [lfb/nodejs-koa-blog - GitHub](https://github.com/lfb/nodejs-koa-blog)

**Express vs Koa:**

+ NodeJS:
    + Express 是对 Node 的增强，内置很多强大中间件；
    + Koa 是对 Node 的修复和代替，只是对 http 模块的抽象。
+ 异步: 
    + 虽然 Express 的 API 很简单，但它基于 ES5 ，要实现异步代码，只能回调
    + Express 的团队基于 ES6 的 generator 重新编写了下一代 web 框架koa



# Project Init

```js
// app.js
const Koa = require('koa');
const app = new Koa();
app.listen(5000);
```



# koa-router middleware

需要引入 `koa-router` 这个 middleware，负责处理 URL 映射



