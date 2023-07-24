# 浏览器运行机制

## 进程 & 线程

**进程**：

系统进行<span style="color:red">资源分配</span>、调度的一个独立单位，可简单理解为<span style="color:red">主任务</span>。

**线程**：

操作系统可识别的、<span style="color:red">安排 CPU 执行</span>的最小单位，可理解为<span style="color:red">子任务</span>

**进程与线程**：

线程依附于进程——一个 进程 可有多个 线程 并发执行，任意一个 线程 执行出错，都会导致整个 进程 的崩溃

线程 间共享 进程 中数据

进程 间内容相隔离，但可通信



## 多进程浏览器

**五个进程：**

+ **浏览器进程**——主进程，只一个。主要负责界面显示、用户交互、子进程管理、存储

+ **GPU进程**——初衷时实现 3D CSS 效果，随后网页、Chrome 的 UI 界面都选择采用 GPU 来绘制

- **插件进程**——因插件易崩溃，所以需要通过插件进程来隔离，以保证插件进程崩溃不会对浏览器和页面造成影响
- **网络进程**——网络资源加载

+ **渲染进程**——核心任务是将 HTML、CSS 和 JavaScript 转换为用户可以与之交互的网页。出于安全考虑，渲染进程都是运行在沙箱模式下。每开一个 Tab 页就多个渲染进程

    > **沙箱模式：**
    >
    > 一个虚拟系统程序。与外界隔绝的一个环境，内外环境互不影响，外界无法修改该环境内任何信息

**渲染机制：**浏览器渲染网页流程大致划分为

![image.png](https://cdn.nlark.com/yuque/0/2022/png/12501428/1644843707536-28786934-17b5-4d2d-a4de-6c4f28a8fc26.png)

**浏览器渲染顺序: **

构建 DOM 树；构建 CSS 树；构建渲染树；节点布局；页面绘制

> **DOM**——网络传给渲染引擎的 <span style="color:orange">HTML 文件字节流</span> 转化成渲染引擎能够理解的 <span style="color:orange">内部结构</span>
>
> **render 树**——主要作用是把 HTML 按照一定的布局与样式显示出来
>
> **布局（回流）**——浏览器要弄清各个节点再页面中的确切位置和大小
>
> **绘制**——调用 GPU 绘制，合成图层，显示在屏幕上

> **重绘与回流：**重绘不一定引起回流，回流必将引起重绘
>
> 重绘——当元素属性发生改变且不影响布局时（背景颜色、透明度、字体样式等），产生重绘，相当于 不刷新页面，动态更新内容
>
> 回流——当元素属性发生改变且影响布局时，产生回流，相当于 刷新页面



# 输入 URL 后发生了什么

1. **合成 URL：**
    1. 浏览器进程判断搜索内容还是请求 URL
    2. 若符合 URL 规则，组装协议，构成完整 URL
2. **DNS[^ 1] 解析 ：**——从域名解析出 IP 地址，实际是寻找所需资源的过程
3. **建立 TCP[^ 2] 连接：**
4. **发起 HTTP 请求，服务器处理请求返回响应结果：**
5. **关闭 TCP 连接，四次挥手**
6. **浏览器渲染：**



# JS 如何影响 DOM 树创建

+ JS 的加载、解析，执行 会**阻塞** DOM 的构建

    > 在构建 DOM 时，HTML 解析器若遇到了 JavaScript，那么它会暂停构建，将控制权移交给 JavaScript 引擎，等其运行完毕，浏览器再从中断的地方恢复 DOM 构建

+ JS 文件也会导致 CSSOM **阻塞** DOM 的构建

    > 因为 JavaScript 不只是可以改 DOM，它还可以更改样式，即更改 CSSOM

+ **script 标签**的 **async、defer**: 

    defer 不阻塞 HTML 解析 ( *见下图 3 红色部分在绿色部分之后* )

    加载多个 JS 脚本，async 无序加载

    ![wfL.png](https://segmentfault.com/img/bVWhRl?w=801&h=814)



# 缓存

**浏览器缓存机制：**

浏览器缓存机制有四个方面，它们按照获取资源时请求的优先级依次排列如下：

1. Memory Cache

2. Service Worker Cache

3. HTTP Cache

    > HTTP 缓存是最主要、最具有代表性的缓存策略。即 Cache-Control、expires 等字段控制的缓存

4. Push Cache

> 除了 Memory Cache、HTTP Cache，其他两种不常用

**浏览器缓存机制关键：**

![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2018/4/19/162db6359673e7d0~tplv-t2oaga2asx-zoom-in-crop-mark:3024:0:0:0.awebp)

- 浏览器每次发起请求，都会先在浏览器缓存中查找该请求的结果以及缓存标识
- 浏览器每次拿到返回的请求结果都会将该结果和缓存标识存入浏览器缓存中



## HTTP Cache

> 主要通过 HTTP 中的 Header 字段发挥作用

一般而言，HTTP 缓存机制是指<span style="color: orange">服务器与浏览器之间，就响应 Header 中的不同字段，做出的不同缓存决策</span>

由 是否需要向服务器重新发起 HTTP 请求 将缓存过程分为两个部分，分别是<span style="color:red">强制缓存</span>和<span style="color:red">协商缓存</span> ( *优先级较高的是强缓存，在命中强缓存失败的情况下，才会走协商缓存* )

**流程图：**

<img src="https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2018/9/20/165f701820fafcf8~tplv-t2oaga2asx-zoom-in-crop-mark:3024:0:0:0.awebp" alt="img" style="zoom:53%;" />



### 强缓存

利用 HTTP Header 中的 <span style="color:red">expires</span> 和 <span style="color:red">cache-control</span> 两个字段来控制

**expires:**

> HTTP 1.1 以前一直用 expires

1. 服务器返回响应时，在 Response Headers 中将<span style="color:orange">过期时间写入 expires 字段</span>
2. 再次向服务器请求资源，浏览器就会先<span style="color:orange">对比本地时间和 expires</span> 的时间戳，如果本地时间小于 expires 设定的过期时间，那么就直接去缓存中取这个资源

**cache-control:**

> 考虑到 expires 的局限性 ( *依赖本地时间* )，HTTP1.1 新增了 cache-control 字段作为 expires 的完全替代方案
>
> Cache-Control: max-age=600，意味着该资源在 600 秒内有效

### 协商缓存

**步骤：**

1. 只有在强缓存失效的时候，才会走协商缓存
2. 浏览器需要向服务器去询问缓存的相关信息，进而判断是重新发起请求、下载完整的响应，还是从本地获取缓存的资源
3. 如果服务端提示缓存资源未改动（Not Modified），资源会被重定向到浏览器缓存，这种情况下网络请求对应的状态码是 304



## MemoryCache

内存中的缓存

从**优先级**上来说，它是浏览器最先尝试去命中的一种缓存

从**效率**上来讲，它是响应速度最快的一种缓存



## 缓存策略

> Cookie, Session, Token, LocalStorage, SessionStorage

> Cookie 存储数据的功能已经很难满足开发所需，逐渐被 WebStorage 和 IndexedDB 取代

### Cookie

本职工作并非是本地存储，而是状态维持

Cookie 就是一个<span style="color: orange">存储在浏览器</span>里的一个小小的文本文件，它附着在 HTTP 请求上，在浏览器和服务器之间“飞来飞去”。它可以携带用户信息，当服务器检查 Cookie 的时候，便可以获取到客户端的状态

![浏览器提供的几种存储_数据](https://s2.51cto.com/images/blog/202108/14/d2a8254bcf2204b99492a41bd8f2b683.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_30,g_se,x_10,y_10,shadow_20,type_ZmFuZ3poZW5naGVpdGk=)

**缺陷：**

+ 只能用于<Span style="color:orange">存取少量信息</span>，单个 Cookie 大小限制 4KB 左右

+ 过多 Cookie 会带来巨大 <Span style="color:orange">性能/资源 浪费</span>

    > Cookie 紧跟域名的，同一个域名下的所有请求都会携带 Cookie

+ 仍有<Span style="color:orange">不安全性</span>



### WebStorage

> WebStorage 是 HTML5 中新增的本地存储解决方案。有了 WebStorage 之后，Cookie 就能够安心只作为客户端与服务器交互的通道（保持客户端状态）了，不用再操心存储的事情。

+ 仅在客户端使用，不和服务端进行通信

+ LocalStorage 与 SessionStorage 存储要求：

    <img src="https://s2.51cto.com/images/blog/202108/14/8d1302bf394aad52eab7704c5f7c47a6.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_30,g_se,x_10,y_10,shadow_20,type_ZmFuZ3poZW5naGVpdGk=" alt="浏览器提供的几种存储_客户端_03" style="zoom:43%;" />

<span style="font-size:20px">LocalStorage: </span>

+ 持久化的本地存储，下一次访问该网站的时候，网页可以直接读取之前保存的数据

**适用场景：**

考虑到 LocalStorage 的特点之一是持久，因此有时候我们更倾向于用它来存储一些内容稳定的资源。比如图片内容丰富的电商网站可以使用LocalStorage 来存储 Base64 格式的图片字符串

<span style="font-size:20px">SessionStorage: </span>

+ 临时性的本地存储，保存的数据只用于浏览器的一次会话

**适用场景：**

存储会话级别的信息



### IndexedDB

> WebStorage 可以看作是对 Cookie 的拓展，只能用于存储少量的简单数据。当遇到大规模的、结构复杂的数据时，就需要用到 IndexedDB。

IndexedDB 可看作是一个运行在浏览器上的非关系型数据库。用于客户端需要存储大量结构化数据（包括文件和blobs）的场景



# 内存泄漏

> 不再用到的内存，没有及时释放

JavaScript 是在创建变量（对象，字符串等）时自动进行了分配内存，并且在不使用它们时“自动”释放



## V8 垃圾回收

V8 垃圾回收策略主要是基于 **分代式垃圾回收机制**——根据<span style="color:orange">对象的存活时间</span>将内存的垃圾回收进行不同的分代，然后对不同的分代采用不同的垃圾回收算法

**V8 引擎的堆结构组成:**

新生代 new_space

老生代：新生代中的对象在存活一段时间后就会被转移到老生代内存区，相对于新生代该内存区域的垃圾回收频率较低

<span style="font-size:20px">垃圾回收算法:</span>

**新生代**回收采用的是 **Scavenge 算法**

把对象区域中所有的活动对象有序移动至空闲区域（这样解决了内存碎片的问题），然后释放对象区域的内存，接着对象区域和空闲区域互换

当一个对象经过多次复制后依然存活，它将会被认为是生命周期较长的对象，随后会被移动到老生代中

**老生代**采用的是**标记清除算法**

从一组根元素开始，递归遍历这组根元素，遍历过程中能到达的元素称为活动对象，没有到达的元素就可以判断为非活动对象在清理阶段直接清理



# 路由

**网页 URL 组成部分：**

```js
//http://127.0.0.1:8001/h1.html?a=100&b=20#/aaa/bbb
location.protocal // 协议 http:
localtion.hostname // 主机名 127.0.0.1
location.host // 主机 127.0.0.1:8001
location.port // 端口
location.pathname // 访问页面 h1.html
location.serach // 搜索页面 ?a=100&b=20
location.hash // 哈希值 #/aaa/bbb
```



## Hash 模式

把前端路由的路径用 <span style="color:red">#</span> 拼接在真实 URL 后的模式

**特点：**

+ hash 变化会<span style="color:orange">触发网页跳转</span>，即浏览器前进后退

+ hash 变化<span style="color:orange">不会触发页面重新加载</span>，即所有页面的跳转都是在客户端进行操作，并不算一次 HTTP 优化。因此这种模式不利 SEO 优化

    > \# 后面的路径发生变化时，浏览器并不会重新发起请求，而是会触发 `onhashchange` 事件



## history 模式

`history API` 是 `H5` 提供的新特性，允许开发者<span style="color:red">直接更改前端路由</span>，即更新浏览器 `URL` 地址<span style="color:red">而不重新发起请求</span>。



# REF

[浏览器运行机制 · 语雀](https://www.yuque.com/ldfgqb/fpkor3/mxkdfk)

[review at main · Martinyua/review](https://github.com/Martinyua/review/blob/main/15.Browser-Safe-Performance)

[浏览器进程与线程 - 掘金](https://juejin.cn/post/6906462594001960974)

[史上最详细的经典面试题 从输入URL到看到页面发生了什么？- 掘金](https://juejin.cn/post/6844903832435032072)

[面试官：浏览器输入URL后发生了什么？](https://mp.weixin.qq.com/s?__biz=MzI0MzIyMDM5Ng==&mid=2649826404&idx=1&sn=e4ccac5fe9d96b26ca1d8d347276d2b1&scene=19#wechat_redirect)

[Dom树 CSS树 渲染树(render树) 规则、原理 - 腾讯云开发者社区 - 腾讯云](https://cloud.tencent.com/developer/article/1715276)

[web性能优化（五）：浏览器缓存策略](https://juejin.cn/post/6844904065462190088)

[浏览器提供的几种存储 - 51CTO 博客](https://blog.51cto.com/u_15060462/3903574)

[js中的垃圾回收机制（v8引擎） - 掘金](https://juejin.cn/post/7019626125814923278)

[浅谈前端路由原理hash和history - 掘金](浅谈前端路由原理hash和history)



[^ 1]: Domain Name System
[^ 2]: 传输控制协议 Transmission Control Protocol，提供面向连接的、可靠的数据传输服务，数据传输的单位是报文段（*segment*）

