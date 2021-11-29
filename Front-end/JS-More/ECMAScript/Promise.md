# Promise

## 概述

**异步编程的解决方案**，语法上来说是<span style="color:red">构造函数</span>，功能上来说：Promise 对象用来封装一个异步操作并可以获取其结果

![image-20211016221415173](https://gitee.com/ethereal-bang/images/raw/master/20211016221422.png)

`Promise`是异步编程的一种解决方案，ES6 原生提供了`Promise`对象。

Promise 对象用于一个异步操作的最终完成或失败和其结果值 的表示。简单来说，就是用于处理异步操作，异步处理成功就执行成功的操作；失败就捕获错误或者停止后续操作。

Promise 就是把一层一层向内嵌套调用的方法，给拉成一串连续调用的方法。每个嵌套调用的方法都向调用者返回 this，也就是返回自身；然后带着上一次调用的结果进入下一个嵌套调用的环节



`Promise`对象有以下俩个**特点**：

1. `Promise`对象的<span style="color:red">状态不受外界影响</span>。`Promise`对象代表一个异步操作，有三种状态：`pending`（*进行中*）、`fulfilled`（*已成功*）和`rejected`（*已失败*）。只有异步操作的结果，可以决定当前是哪一种状态，任何其他操作都无法改变这个状态。这也是`Promise`名字的由来，表示其他手段无法改变。

2. <span style="color:red">状态一旦改变，就不会再变</span>，任何时候都可以得到这个结果。`Promise`对象的状态改变只有俩种可能：从**`pending`**变为**`fulfilled`**和从**`pending`**变为**`rejected`**。只要这两种情况发生，状态就凝固了，不会再变了，会一直保持这个结果，这时就称为 resolved（*已定型*）。  
    如果改变已经发生了，再对`Promise`对象添加回调函数，也会立即得到这个结果。这与事件（*Event*）不同，事件的特点是，如果你错过了它，再去监听是得不到结果的。



`Promise`也有一些**缺点**。首先，无法取消`Promise`，一旦新建它就会立即执行，无法中途取消。其次，如果不设置<span style="color:red">回调函数</span>，`Promise`内部抛出的错误，不会被`try/catch`捕获。代码一旦开始以异步模式执行，则唯一与之交互的方式就是`Promise`的方法。



如果某些事件不断地反复发生，使用 [Stream](https://nodejs.org/api/stream.html) 模式是比部署`Promise`更好的选择。



## 基本用法

ES6 规定，`Promise`对象是一个构造函数，用来生成`Promise`实例。

``` js
// 1. 创建一个新的Promise对象
const p = new Promise( (resolve, reject) => {	// 执行器函数
    /* 2. 执行异步操作任务 */
    setTimeout(() => {
        const time = Date.now();
        if (time % 2 == 0) {
    		/* 如果成功，调用resolve(value) */
            resolve("成功数据" + time)
        } else {
          	/* 3.2如果失败，调用reject(reason) */  
            reject("失败数据" + time)
        }
    }) 	      
})

p.then(
	value => {	// 接收得到成功的value数据 函数名：onResolved
		console.log("成功的回调" + value);     
    }, 
    reason => { // 接收得到失败的reason数据 		 onRejected
    	console.log("失败的回调" + reason);
    }
)
```

以上代码任意两次执行结果：`成功的回调 成功数据 1617361662422`；`失败的回调 失败数据 1617361704103`



`new Promise`的时候里面内容会立即执行。因而为了能实现调用时执行，Promise 一般都是作为函数的返回值	==？==



## Promise.resolve()

Promise 并非要一开始就处于 pending 状态，再通过执行器函数转换状态。

通过调用`Promise.resolve()`或`Promise.reject()`可以直接实例化一个 resolved 或 rejected 的 Promise

也就是说下面两种写法是一样的。

``` js
let p1 = new Promise( (resolve, reject) => resolve() );

let p2 = Promise.resolve();
```

这个 resolved 的 Promise 对应着传给`Promise.resolve()`的第一个参数。使用这个方法，能将现有任何值转为 Promise 对象

参数分为四种情况：

1. 参数是一个 Promise 实例

    `Promise.resolve()`将不做任何修改、原封不动地返回这个实例。

    因此`Promise.resolve()`是一个**幂等方法**。

    > 幂等方法
    >
    > 指可以使用相同参数重复执行并获得相同结果的函数

2. 参数是一个`thenable`对象

    ==。。。==

3. 参数不是具有`then()`的对象，或根本不是对象

    `Promise.resolve()`返回一个新的 Promise 状态为 resolved 的对象

    ``` js
    let p = Promise.resolve("Hello");
    
    setTimeout(console.log, 0, p);	// Promise {<fulfilled>: "Hello"}
    ```

4. 不带任何参数

    直接返回一个`resolved`状态的 Promise 对象

    所以，如果希望得到一个 Promise 对象，比较方便的是直接调用`Promise.resolve()`

    ``` js
    const p = Promise.resolve();
    ```



## Promise.reject()

**实例化**一个 rejected 的 Promise并**抛出一个异步错误**。类似于`throw()`，因为它们都代表一种程序状态，即需要中断或者特殊处理。    

这个错误不能被 try/catch 捕获，而只能通过拒绝处理程序(*reject handler*)捕获。对应的错误对象会成为拒绝的理由

``` js
let p = Promise.reject(3);
setTimeout(console.log, 0, p);	// Promise {<rejected>: 3}

p.then(undefined, (reason) => setTimeout(console.log, 0, reason) );	// 3
```



在 Promise 中抛出错误时，因为错误实际上是从消息队列中异步抛出的，所以并**不会阻止运行时继续执行同步**指令

下面例子**对比**了同步错误处理于异步错误处理。

``` js
/* 同步错误处理 */
console.log('begin synchronous execution');
try {
    throw Error('foo');
} catch (e) {
    console.log(`caught error ${e}`);
}
console.log('continue synchronous execution');
// begin synchrounous execution
// caught error Error: foo
// continue synchronous execution


/* 异步错误处理 */
new Promise( (resolve, reject) => {
    console.log('begin asynchronous exection');
    reject( Error('bar') );
} ).catch( (erorr) => {
    console.log(`caught error${erorr}`);
} ).then( () => {
   	console.log("continue asynchronous exection");
});
// begin asynchronous exection
// caught error Error: bar
// continue asynchronous exection
```



## Promise 的实例方法

Promise 实例的方法是**连接外部同步代码与内部异步代码**之间的桥梁。  

这些方法可以：

+ **访问异步操作返回数据**
+ **处理** Promise 成功和失败的**结果**
+ **连续**对 Promise **求值**
+ 添加只有 Promise 进入**终止状态时才会执行的代码**



### Promise.prototype.then()

>  实现 Thenable 接口
>
>  在暴露的异步结构中，任何对象都有一个`then()`方法。这个方法实现 Thenable 接口



+ **作用**：

    `Promise.prototype.then()`是为 Promise 实例**添加处理程序**（*Promise 状态改变时的回调函数*）的主要方法



+ **参数**：

    `Promise.prototype.then()`最多接收两个**函数类型**的参数：onResolve 处理程序、onRejected 处理程序。这两个参数都是可选的。会在 Promise 分别进入 resolved、rejected 状态时执行

    如果只提供 onRejected 参数，那就要在 onResolved 参数位置上传入`undefined`  

    > null 和 undefined
    >
    > 目前，null 和 undefined 基本是同义的，只有一些细微的差别。
    >
    > + null 表示**没有对象**，即该处不应该有值
    >
    >     典型用法：
    >
    >     1. 作为函数的参数，表示该函数的参数不是对象。
    >     2. 作为对象原型链的终点。
    >
    > + undefined 表示**缺少值**，即此处应该有值，但还没定义
    >
    >     典型用法：
    >
    >     1. 变量被声明还没赋值
    >     2. 调用函数时，应该提供的参数没有提供
    >     3. 对象没有赋值的属性
    >     4. 函数没有返回值时，默认返回 `undefined`



`then()`返回一个新的`Promise`实例，注意不是原来那个`Promise`实例。因此可以采用链式写法，即`then()`后面再调用另一个`then()`

``` javascript
getJSON("/posts.json").then(function(json) {
    return json.post;
}).then(function(post) {
    // ...
});
```

上面的代码使用`then`方法，依次指定了俩个回调函数。第一个回调函数完成以后，会将 return 结果作为参数传入第二个回调函数。



例子：

```js
let p = new Promise(resolve => {
    resolve("1")
})
let then = p.then((data) => {
    console.log(p)     // Promise {<fulfilled>: '1'}
    console.log(then)    // Promise {<pending>}
})
console.log(p);   // Promise {<fulfilled>: '1'}
console.log(then)   // Promise {<pending>}
setTimeout(console.log, 0, then) // Promise {<fulfilled>: undefined}
```





### Promise.prototype.catch()

`Promise.prototype.catch()`方法用于给 Promise 添加**拒绝处理程序**。

这个方法只接收一个参数，oneRejected 处理程序。

事实上，这个方法相当于`Promise.prototype.then(null, onRejected)`。就是一个**语法糖**。



### Promise.prototype.finally()

+ 作用：`Promise.prototype.finally()`这个方法用于给 Promise 添加 **onFinally 处理程序**。这个处理程序在 Promise 转换为 resolved 或 rejected 状态时都会执行。



+ 用途：主要用于添加清理代码。因为 onFinally 处理程序没有办法知道 Promise  的状态具体是 resolved 还是 rejected



+ `Promise.prototype.finally()`**大多情况表现为父 Promise 的传递**。对于 resolved 和 rejected 状态都是如此。

``` js
let p1 = Promise.resolve("foo");

// 这里都会原样后传
let p2 = p1.finally();
let p3 = p1.finally( () => Promise.resolve() );
let p4 = p1.finally( () => 'bar');

setTimeout(console.log, 0, p2);		// Promise {<fulfilled>: "foo"}
setTimeout(console.log, 0, p3);		// Promise {<fulfilled>: "foo"}
setTimeout(console.log, 0, p4);		// Promise {<fulfilled>: "foo"}
```



+ 如果**返回**（*通过方法向下传递的值*）的是一个 pending 状态的 Promise，或者 onFinally 处理程序**抛出了错误**（*显示抛出或返回了一个 rejected Promise*），则会返回相应的 Promise（*pending 或 rejected*）。

    ``` js
    let p1 = Promise.resolve('foo');
    
    // 返回的是 pending 状态的 Promise
    let p2 = p1.finally( () => new Promise( () => {} ) );
    setTimeout(console.log, 0, p2)	// Promise {<pending>}
    ```



## Promise 连锁与 Promise 合成

多个 Promise 组合在一起可以构成强大的代码逻辑。可以通过两种方式实现：Promise 连锁与 Promise 合成。前者是一个 Promise 接一个 Promise 地拼接，后者将多个 Promise 组合成一个 Promise。



<span style="font-size:20px">Promise Promise 连锁</span>

`then`式链式写法的本质其实是一直**往下传递返回一个新的Promise**，也就是说**then在下一步接收的是上一步返回的Promise**

把 Promise 逐个串联起来是一种非常有用的编程模式。因为每个 Promise 实例的方法都会返回一个新的 Promise 对象，而这个新的 Promise 对象又有自己的实例方法。这样连缀方法调用就可以构成“Promise 连锁”。



``` js
let p1 = new Promise( (resolve, reject) => {
    console.log("p1 exector");
    setTimeout(resolve, 1000);
});

p1.then( () => new Promise( (resolve, reject) => {
    console.log("p2 exector");
    setTimeout(resolve, 1000);
} ) )
  .then( () => new Promise( (resolve, reject) => {
    console.log("p3 exector");
    setTimeout(resolve, 1000);
} ) )
  .then( () => new Promise( (resolve, reject) => {
    console.log("p4 exector");
    setTimeout(resolve, 1000);
} ) );	// 注意最后才有 ; 

/* 
	p1 exector // 1秒后
 	p2 exector	// 2秒后
	。。。
	。。。
*/
```

执行异步任务，让每个执行器都返回一个 Promise 实例。

把生成 Promise 的代码提取到一个**工厂函数**中，可以写成这样。

``` js
function delayedResolve(str) {	/* ? */
    return new Promise( (resolve, reject) => {
        console.log(str);
        setTimeout(resolve, 1000);
    });
}

delayedResolve('p1 exector')
	.then( () => delayedResolve('p2 exector') )
	.then( () => delayedResolve('p3 exector') )
	.then( () => delayedResolve('p4 exector') )

// 执行结果同上例
```

> **工厂函数**
>
> 工厂函数是一个能返回对象的函数，它既不是类也不是构造函数。在 JavaScript 中，任何函数都可以返回一个对象，如果函数前面没有使用 `new` 关键字，却又返回一个对象，那这个函数就是一个工厂函数
>
> 原文链接：[[译] ES6+ 中的 JavaScript 工厂函数（第八部分）](https://juejin.cn/post/6844903497842819085)



## Promise.all()

`Promise.all()`方法用于将多个 Promise 实例，包装成一个新的 Promise 实例。

``` js
const p = Promise.all([p1, p2, p3]);
```

参数：

上面代码中，`Promise.all()`接受一个**数组作为参数**，`p1`、`p2`、`p3`都是 Promise 实例。如果不是，就会调用`Promise.resolve()`先将参数转为 Promise 实例。如果参数可以不是数组，但必须具有 Iterator 接口，且返回的每个成员都是 Promise 实例。



`p`的状态由`p1`、`p2`、`p3`决定，分成两种情况。



# Promise 实现

+ **用法：**

    ```js
    new Promise((resolve, reject) => {
      // ...
      // 成功则执行resolve，否则指定reject
    }).then(
      res => {
        // resolve对应触发函数的执行
      },
      err => {
        // reject对应触发函数的执行
      }
    ).then(
      // 支持链式调用
      res => {
      
      }
    ).catch(
      err => console.log(err)
    )
    Promise.resolve();
    Promise.reject();
    Promise.all([promise1, promise2, ...]).then();
    Promise.race([promise1, promise2, ...]).then();
    ```

    通过用法可以看出：

    1. Promise 构造函数接收一个函数参数 executor，executor 接收`resolve`、`rejecte`两个函数后立即执行
    2. Promise 通过`resolve()`、`rejected()`改变状态
    3. 状态改变后，触发==原型链上==？`then`/`catch`方法
    4. Promise 拥有静态方法：`resolve`、`rejecte`、`all`、`race`

## 初版

1. **初步结构：**

    根据用法，写出初步结构如下：

    ```js
    class Promise {
        constructor(executor) {
            const resolve = () => {};
            const reject = () => {};
            executor(resolve, reject);
        }
        then() {}
        catch() {}
        static resolve() {}
        static reject() {}
        static all() {}
        static race() {}
    }
    ```

2. **引入状态，完善`resolve`、`reject`：**

3. 

# 参考：

+ Promise 实现：

    [详细的Promise源码实现，再被面试问到轻松解答 - 掘金](https://juejin.cn/post/6860037916622913550)

