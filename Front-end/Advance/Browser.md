# 浏览器运行机制

## 进程 & 线程

+ **进程**：

    系统进行<span style="color:red">资源分配</span>、调度的一个独立单位，可简单理解为<span style="color:red">主任务</span>。

+ **线程**：

    操作系统可识别的、<span style="color:red">安排 CPU 执行</span>的最小单位，可理解为<span style="color:red">子任务</span>

+ **进程与线程**：

    线程依附于进程——一个 进程 可有多个 线程 并发执行，任意一个 线程 执行出错，都会导致整个 进程 的崩溃

    线程 间共享 进程 中数据

    进程 间内容相隔离，但可通信



## 多进程浏览器

+ **五个进程：**

    + **浏览器进程**——主进程，只一个。主要负责界面显示、用户交互、子进程管理、存储

    + **GPU进程**——初衷时实现 3D CSS 效果，随后网页、Chrome 的 UI 界面都选择采用 GPU 来绘制
    
    - **插件进程**——因插件易崩溃，所以需要通过插件进程来隔离，以保证插件进程崩溃不会对浏览器和页面造成影响
    - **网络进程**——网络资源加载
    
    + **渲染进程**——核心任务是将 HTML、CSS 和 JavaScript 转换为用户可以与之交互的网页。出于安全考虑，渲染进程都是运行在沙箱模式下。每开一个 Tab 页就多个渲染进程
    
        > **沙箱模式：**
        >
        > 一个虚拟系统程序。与外界隔绝的一个环境，内外环境互不影响，外界无法修改该环境内任何信息
    
+ **渲染机制：**浏览器渲染网页流程大致划分为

    ![image.png](https://cdn.nlark.com/yuque/0/2022/png/12501428/1644843707536-28786934-17b5-4d2d-a4de-6c4f28a8fc26.png)



## 输入 URL 后发生了什么

1. **合成 URL：**
    1. 浏览器进程判断搜索内容还是请求 URL
    2. 若符合 URL 规则，组装协议，构成完整 URL
2. **DNS[^ 1] 解析 ：**——从域名解析出 IP 地址，实际是寻找所需资源的过程
3. **建立 TCP[^ 2] 连接：**
4. **发起 HTTP 请求，服务器处理请求返回响应结果：**
5. **关闭 TCP 连接，四次挥手**
6. **浏览器渲染：**



# REF

[浏览器运行机制 · 语雀](https://www.yuque.com/ldfgqb/fpkor3/mxkdfk)

[review at main · Martinyua/review](https://github.com/Martinyua/review/blob/main/15.Browser-Safe-Performance)

[浏览器进程与线程 - 掘金](https://juejin.cn/post/6906462594001960974)

[史上最详细的经典面试题 从输入URL到看到页面发生了什么？- 掘金](https://juejin.cn/post/6844903832435032072)

[面试官：浏览器输入URL后发生了什么？](https://mp.weixin.qq.com/s?__biz=MzI0MzIyMDM5Ng==&mid=2649826404&idx=1&sn=e4ccac5fe9d96b26ca1d8d347276d2b1&scene=19#wechat_redirect)https://zhuanlan.zhihu.com/p/102149546)



[^ 1]: Domain Name System
[^ 2]: 传输控制协议 Transmission Control Protocol，提供面向连接的、可靠的数据传输服务，数据传输的单位是报文段（*segment*）

