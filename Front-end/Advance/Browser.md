# 浏览器运行机制

## 进程 & 线程

+ **进程**：

    系统进行<span style="color:red">资源分配</span>、调度的一个独立单位，可简单理解为<span style="color:red">主任务</span>

+ **线程**：

    操作系统可识别的、<span style="color:red">安排 CPU 执行</span>的最小单位，可理解为<span style="color:red">子任务</span>

+ **进程与线程**：

    线程依附于进程——一个 进程 可有多个 线程 并发执行，任意一个 线程 执行出错，都会导致整个 进程 的崩溃

    线程 间共享 进程 中数据

    进程 间内容相隔离



## 多进程浏览器

+ **五个进程：**

    + 浏览器进程

    - 渲染进程
    - 网络进程
    - 插件进程
    - GPU进程



## 渲染机制

+ **浏览器渲染网页流程**大致划分为：

    ![image.png](https://cdn.nlark.com/yuque/0/2022/png/12501428/1644843707536-28786934-17b5-4d2d-a4de-6c4f28a8fc26.png)



# JS 事件循环机制

## JS 单线程

那么 **JS 单线程是如何实现异步**的呢：

就是通过**事件循环**(*event loop*)实现异步



## 任务队列

```js
console.log(1)
setTimeout(() => {
  console.log(2)
}, 0)
console.log(3)
// 1 3 2
```

上面的问题里，`setTimout`内匿名函数没有立即执行，而是添加到**任务队列**，等满足一定条件取执行



+ 异步任务操作会将相关回调添加到任务队列
+ 而不同的异步操作添加到任务队列的时机也不同，如`onclick`、`setTimeout`、ajax，这些异步操作是由浏览器内核执行



## 事件循环

1. 执行一个**宏任务**后，**其中同步任务立即执行、异步任务放入事件队列**
2. 执行完取**微任务队列执行完**毕
3. 再次检查有无宏任务，重复执行以上步骤
4. 若无宏任务，执行下一事件循环

即，主线程不断从消息队列获取消息、执行消息，这个过程成为事件循环



<span style="font-size:20px">同步任务和异步任务</span>

+ JS 里的一种任务分类方式就是将任务分为：同步任务和异步任务

+ 同步就是后一个任务等待前一个任务执行完毕后，再执行，**执行顺序和任务的排列顺序一致**

+ 对于耗时的操作或时间不确定的操作，使用异步成了必然的选择



<span style="font-size:20px">宏任务与微任务</span>

将任务进行更精确的定义：任务队列的任务又分为宏任务和微任务

1. 执行一个宏任务后，执行完取微任务队列执行完毕
2. 再次检查有无宏任务，重复执行
3. 若无宏任务，执行下一事件循环

+ 常见**宏任务**：
    + script
    + 定时器 setTimeout
    + 事件绑定
    + Ajax
    + 回调函数
    + Node 中 fs 可进行异步的 I/O 操作
    + UI 交互事件
+ 常见**微任务：**
    + Promise.then
    + async、await
    + process.nextTick
    + MutationObserver（HTML5 新特性，监控某个节点）	



# 例子

+ **Promise**、**Promise.then**

    ```js
    let p = new Promise(resolve => {
        console.log("promise 1")
        resolve(1)
    }).then(() => {
        console.log("then")
    })
    console.log("1")
    
    // promise1
    // 1
    // then
    ```

    `.then`为异步、微任务，待同步代码执行后执行

    > Promise
    >
    > 若未`resolve()`后面的`then`不会执行



![image-20211013133650888](https://gitee.com/ethereal-bang/images/raw/master/20211013133658.png)

输出结果：4 1 3 5 6 2



```js
async function async1() {
    console.log("async1 start");
    await  async2();
    console.log("async1 end");
}
async  function async2() {
    console.log( 'async2');
}
console.log("script start");
setTimeout(function () {
    console.log("settimeout");
},0);
async1();
new Promise(function (resolve) {
    console.log("promise1");
    resolve();
}).then(function () {
    console.log("promise2");
});
console.log('script end');
```

> 执行顺序：
>
> script start
> async1 start
> async2
> promise1
> script end
> async1 end
> promise2
> settimeout



# REF

+ 浏览器运行机制：

    [浏览器运行机制 · 语雀](https://www.yuque.com/ldfgqb/fpkor3/mxkdfk)

    [review/浏览器原理初探.md at main · Martinyua/review](https://github.com/Martinyua/review/blob/main/15.Browser-Safe-Performance/%E6%B5%8F%E8%A7%88%E5%99%A8%E5%8E%9F%E7%90%86%E5%88%9D%E6%8E%A2.md)

+ JS 事件循环机制：

    [JavaScript：彻底理解同步、异步和事件循环 - 思否](https://segmentfault.com/a/1190000004322358)

    [js异步.pptx]()

<hr>

+ 待看：

    [【事件循环】【前端】事件原理讲解，超级硬核，忍不住转载_哔哩哔哩](https://www.bilibili.com/video/BV1K4411D7Jb/?spm_id_from=333.788.recommend_more_video.3)

    [一文看懂Chrome浏览器运行机制 - 知乎](https://zhuanlan.zhihu.com/p/102149546)
