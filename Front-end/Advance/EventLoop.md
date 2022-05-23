# JS 事件循环机制

**JS 单线程**靠**事件循环** (*event loop*) 实现异步



## Task Queue

```js
console.log(1)
setTimeout(() => {
  console.log(2)
}, 0)
Promise.resolve().then(() => {
  console.log(3)
})
console.log(4)
// 1 4 3 2
```

> setTimout 进入 宏任务队列，Promise.then 进入 微任务队列

+ **异步操作将回调添加到任务队列**



## 任务和异步任务

> JavaScript 设计者在广义上将所有任务分成这两种。
>
> 和宏微任务的关系——同步异步决定了加入到 宏/微 任务队列的时机

执行机制：

1. 主线程执行所有的同步任务
2. 主线程外存在一 Task queue，异步将回调添加到任务队列

![流程图](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/3ecb53c3573a40c799d7c759820b15cf~tplv-k3u1fbpfcp-zoom-in-crop-mark:1304:0:0:0.awebp)



## 宏任务与微任务

将任务进行更精确的定义：任务队列的任务又分为宏任务和微任务

+ MacroTask 宏任务：

    可以将<span style="color:orange">每一次执行栈执行的代码</span>当做是一个宏任务(包括每次从事件队列中获取一个事件回调并放到执行栈中执行)

+ MicroTask 微任务：

    单个宏任务退出执行上下文前在该<span style="color:orange">执行上下文</span>维护的微任务队列

    > 宏任务 -> 微任务 -> GUI渲染 -> 宏任务 -> ...

![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/1/18/16fb7adf5afc036d~tplv-t2oaga2asx-zoom-in-crop-mark:1304:0:0:0.awebp)

+ 常见**宏任务**：script、setTimeout、事件绑定、Ajax、回调函数、Node 中 fs 可进行异步的 I/O 操作、UI 交互事件
+ 常见**微任务：**Promise.then、async、await、process.nextTick

**执行机制：**

1. 执行一个宏任务后，紧接着执行当前执行栈产生的微任务完毕
2. 再次检查有无宏任务，重复执行



## 完整流程

主线程不断从消息队列获取消息、执行消息，这个过程成为事件循环

1. 同步任务进入主线程，异步任务进入 宏/微 任务队列
2. 同步执行完毕后读取任务队列
3. 任务队列先微后宏

![流程图](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/9d505ac5f51f4a9396504c18091971c7~tplv-k3u1fbpfcp-zoom-in-crop-mark:1304:0:0:0.awebp)



# EG

+ **Promise**、**Promise.then**

    ```js
    let p = new Promise(resolve => {
        console.log("promise 1")
        resolve(1)
    }).then((res) => {
        console.log(res)
    })
    console.log("2")
    
    // promise1
    // 2
    // 1
    ```

    `new Promise()` 为同步， `.then` 为异步微任务



+ 综合流程：

    ```js
    const p = new Promise(resolve => {
        console.log(4)
        resolve(5)
    }) 
    
    function func1() { console.log(1) }
    
    function fun2() {
        setTimeout(() => {
            console.log(2);
        });
        func1()
        console.log(3);
        p.then(res => {
            console.log(res);
        })
        .then(() => {
            console.log(6);
        })
    }
    
    fun2()
    ```

    > 输出结果：4 1 3 5 6 2



+ async、await：==?==

    > async/await本质上是基于 Promise 的一些封装，而 Promise 是属于微任务的一种
    >
    > **使用 await 与 Promise.then 效果类似**，<span style="color:orange">await `exp()` 即是在执行 resolve()</span>，resolve() 后才执行.then() 即 await 语句之后的代码 可以理解为，`await` 以前的代码，相当于与 `new Promise` 的同步代码，`await` 以后的代码相当于 `Promise.then`的异步

    ```js
    setTimeout(() => console.log(4))
    async function test() {
      console.log(1)
      await Promise.resolve()	// await以后为异步微任务
      console.log(3)
    }
    test()
    console.log(2)
    // 1 2 3 4
    ```

    ```js
    async function async1() {
      console.log("async1 start");
      await async2();	// async2()是同步
      console.log("async1 end");
    }
    async function async2() {
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

    > script start 、async1 start 、async2 、promise1、script end 、async1 end、promise2、settimeout



# REF

[JavaScript：彻底理解同步、异步和事件循环 - 思否](https://segmentfault.com/a/1190000004322358)

[「硬核JS」一次搞懂JS运行机制](https://juejin.cn/post/6844904050543034376#heading-19)