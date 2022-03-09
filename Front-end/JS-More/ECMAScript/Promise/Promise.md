# Promise

## 概述

**异步编程的解决方案**，功能上来说：Promise 对象用来封装一个异步操作并可以获取其结果——一个异步操作的最终完成或失败和其结果值 的表示

![image-20211016221415173](https://gitee.com/ethereal-bang/images/raw/master/20211016221422.png)

Promise 就是把一层一层向内嵌套调用的方法，给拉成一串连续调用的方法。每个嵌套调用的方法都向调用者返回 this，也就是返回自身；然后带着上一次调用的结果进入下一个嵌套调用的环节



+ `Promise`对象**特点**：
    1. Promise`对象的<span style="color:green">状态不受外界影响</span>。`Promise`对象代表一个异步操作，有三种状态：pending、fulfilled 和 rejected。只有异步操作的结果，可以决定当前是哪一种状态
    2. <span style="color:green">状态一旦改变，就不会再变</span>。`Promise`对象的状态改变只有俩种可能：从**`pending`**变为**`fulfilled`**和从**`pending`**变为**`rejected`**。只要这两种情况发生，会一直保持这个结果，这时就称为 resolved（*已定型*）。  
    3. <span style="color:green">如改变已经发生，再对`Promise`对象添加回调函数，也会立即得到该状态</span>。这与事件（*Event*）不同，事件的特点是，如果你错过了它，再去监听是得不到结果的。


+ `Promise`也有一些**缺点**：
    1. <span style="color:red">无法取消`Promise`</span>，一旦新建它就会立即执行，无法中途取消。
    2. 如不设置回调函数，<span style="color:red">`Promise`内部抛出的错误，不会被`try/catch`捕获</span>。代码一旦开始以异步模式执行，则唯一与之交互的方式就是`Promise`的方法。

+ 对于某些不断发生的事件，使用 [Stream](https://nodejs.org/api/stream.html) 模式是比部署`Promise`更好的选择。



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



`new Promise`的时候里面内容会立即执行。因而为了能实现调用时执行，Promise 一般都是作为函数的返回值，如：

```js
const ajax = (options) => {
  return new Promise((resolve, reject) => {
```



### executor

Promise 构造函数接收一个**函数参数 executor**，executor 接收**`resolve`、`rejecte`**两个函数后立即执行

+ **resolve 作用:**

    1. **改变状态**——将当前 Promise 状态由 pending 变为 resolved

    2. **遍历 onResolved 回调**——遍历 之前通过 then 给这个 Promise 注册的所有回调，并放入微任务队列

        注意：不是由 then 来触发保存回调，事实是由 Promise 的 resolve 触发，then 只负责注册回调



## Promise 方法

### Promise.resolve()

通过调用`Promise.resolve()`或`Promise.reject()`可以直接实例化一个 resolved 或 rejected 的 Promise

也就是说下面两种写法是一样的。

``` js
let p1 = new Promise( (resolve, reject) => resolve() );

let p2 = Promise.resolve();
```

这个 resolved 的 Promise 对应着传给`Promise.resolve()`的第一个参数。使用这个方法，能<span style="color:red">将现有任何值转为 Promise 对象</span>

参数分为四种情况：

1. 参数是一个 Promise 实例

    `Promise.resolve()`将不做任何修改、原封不动地返回这个实例。

    因此`Promise.resolve()`是一个<span style="color:red">幂等方法</span>。

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



### Promise.reject()	

实例化一个 rejected 的 Promise并**抛出一个异步错误**。类似于`throw()`，因为它们都代表一种程序状态，即需要中断或者特殊处理。    

这个错误不能被 try/catch 捕获，而只能通过拒绝处理程序(*reject handler*)捕获。对应的错误对象会成为拒绝的 reason	==？==

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



## Promise.all()

`Promise.all()`方法用于将多个 Promise 实例，包装成一个新的 Promise 实例。

``` js
const p = Promise.all([p1, p2, p3]);
```

参数：

上面代码中，`Promise.all()`接受一个**数组作为参数**，`p1`、`p2`、`p3`都是 Promise 实例。如果不是，就会调用`Promise.resolve()`先将参数转为 Promise 实例。如果参数可以不是数组，但必须具有 Iterator 接口，且返回的每个成员都是 Promise 实例。



`p`的状态由`p1`、`p2`、`p3`决定，分成两种情况。



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
    > + null 典型用法：
    >
    >     1. 作为函数的参数，表示该函数的参数不是对象。
    >    2. 作为对象原型链的终点。
    >     
    > + undefined 典型用法：
    >
    >     1. 变量被声明还没赋值
    >    2. 调用函数时，应该提供的参数没有提供
    >     3. 对象没有赋值的属性
    >    4. 函数没有返回值时，默认返回 `undefined`

#### 返回一个 Promise

`then()`返回一个<span style="color:red">新的`Promise`实例</span>，注意不是原来那个`Promise`实例。因此可以采用链式写法，即`then()`后面再调用另一个`then()`

例子：

```js
let p = new Promise(resolve => {
    resolve("1")
})
let then = p.then(data => {
    console.log(p)     // Promise {<fulfilled>: '1'}
    console.log(then)    // Promise {<pending>}
})
console.log(p);   // Promise {<fulfilled>: '1'}
console.log(then)   // Promise {<pending>}
setTimeout(console.log, 0, then) // Promise {<fulfilled>: undefined}
```

+ **then 返回的 Promise 状态**

    + **Resolved**：
        + **then 中回调函数** 返回某值——则回调函数参数为该返回值
        + 无返回值——回调函数参数值 undefined
        + resolved promise——回调函数参数值为该 Promise onResolved 回调函数参数值 
    + **Rejected**：
        + rejected promise——回调函数参数值为该 Promise onRejected 回调函数参数值

    + **Pending**：
        + pending promise——终态与该 Promise 终态相同
    
    实例：
    
    ```js
    // 返回pending promise的情况——then也返回pending promise
    new Promise((resolve, reject) => {
      resolve(1)
    }).then((data) => {
      return new Promise((resolve, reject) => {
        setTimeout(resolve, 1000, data);
      })
    })
    // 返回值——then返回 Promise { <该返回值> }
    let p2 = new Promise((resolve, reject) => {
      resolve(2)
    }).then(data => {
      return 3;
    })
    setTimeout(console.log, 0, p)    // Promise { 3 } 参数为该返回值
    // 返回resolved promise——返回 Promise { <> }
    let p3 = new Promise((resolve, reject) => {
      resolve(3);
    }).then(data => {
      return Promise.resolve(4);
    })
    setTimeout(console.log, 1000, p3)   // Promise { 4 } 参数为onResolved参数
    // 无返回值——返回 Promise { undefined }
    let p4 = new Promise((resolve, reject) => {
      resolve(4);
    }).then(data => {
      console.log("无返回值");
    }).then(data => {console.log(data)})
    setTimeout(console.log, 1000, p4);  // Promise { undefined }
    ```
    
    > **`Prmise { 1 }`** 
    >
    > 首先要了解打印 Promise 对象一般是这样的形式：`Promise { 1 }`、`Promise { undefined }`、`Promise { pending }`，当括号内有值而不是`pending`时，该值即是执行 onResolved 或 onRejected 回调函数收到的参数值
    



### Promise.prototype.catch()

`Promise.prototype.catch()`方法用于给 Promise 添加**拒绝处理程序**。

这个方法只接收一个参数，oneRejected 处理程序。

事实上，这个方法相当于`Promise.prototype.then(null, onRejected)`。就是一个**语法糖**。



### Promise.prototype.finally()

+ 作用：`Promise.prototype.finally()`这个方法用于给 Promise 添加 **onFinally 处理程序**。这个处理程序在 Promise 转换为 resolved 或 rejected 状态时都会执行。

+ 用途：主要用于添加清理代码。因为 onFinally 处理程序没有办法知道 Promise  的状态具体是 resolved 还是 rejected

+ `Promise.prototype.finally()`**大多情况表现为父 Promise 的传递**。对于 resolved 和 rejected 状态都是如此。==？==

    ```js
    let p1 = Promise.resolve("foo");
    
    // 这里都会原样后传
    let p2 = p1.finally();
    let p3 = p1.finally( () => Promise.resolve() );
    let p4 = p1.finally( () => 'bar');
    
    setTimeout(console.log, 0, p2);		// Promise {<fulfilled>: "foo"}
    setTimeout(console.log, 0, p3);		// Promise {<fulfilled>: "foo"}
    setTimeout(console.log, 0, p4);		// Promise {<fulfilled>: "foo"}
    ```


+ 如果**返回**（*通过方法向下传递的值*）的是一个 pending 状态的 Promise，或者 onFinally 处理程序**抛出了错误**（*显示抛出或返回了一个 rejected Promise*），则会返回相应的 Promise（*pending 或 rejected*）。==？==

    ``` js
    let p1 = Promise.resolve('foo');
    
    // 返回的是 pending 状态的 Promise
    let p2 = p1.finally( () => new Promise( () => {} ) );
    setTimeout(console.log, 0, p2)	// Promise {<pending>}
    ```



## Promise 链式调用

### 写法

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
} ) );	// 注意最后才有 ; 
/* 
	p1 exector // 1秒后
 	p2 exector	// 2秒后
	。。。
*/
```

执行异步任务，让每个执行器都返回一个 Promise 实例。



### 链式调用执行顺序

+ **`then`执行时回调函数的处理由 Promise 状态决定：**

    `then`方法执行时，如果前面的 Promise 已是 **resolved** 状态——直接将回调放入<span style="color:red">微任务队列</span>中；前面前面 Promise 为 **pending** 状态——回调<span style="color:red">存储</span>在promise内部，promise被resolved后才将回调放入微任务队列

    ```js
    new Promise((resolve, reject) => {
      resolve('1');
      console.log("promise1")
    }).then((data) => {
      console.log("then1", data)
    })
    console.log("外部1")// 执行顺序: promise 外部 then1
    
    new Promise((resolve, reject) => {
      console.log("promise2")
      setTimeout(() => resolve(2), 1000);
    }).then(data => {
      console.log("then2", data);
    })
    console.log("外部2")   // 顺序: promise 外部 then2(1s后)
    ```



+ **Promise 被 resolve 时回调执行顺序：**

    当一个 promise 被 resolve 时，会<span style="color:red">遍历之前通过 then 给这个 promise 注册的所有回调，将它们依次放入微任务队列</span>中

    ```js
    let p = new Promise((resolve, reject) => {
      setTimeout(resolve, 1000, 1);
    })
    p.then(data => {
      console.log("then1", data);
    })
    p.then(data => {
      console.log("then2", data);
    })
    console.log("外部")   // 顺序: 外部 (then1 1  then2 1)1s后
    ```

    一秒后`p`才会被 resolve，在 resolve 前 then 给它注册了 3 个回调，此时这 3 个回调不会被放入微任务队列中执行，而是等到`p` resolve 后。



+ **嵌套 then 的执行：**

  ```js
  new Promise((resolve, reject) => {
      console.log("外部promise");
      resolve();
  })
      .then(() => {
          console.log("外部第一个then");
          new Promise((resolve, reject) => {
              resolve();
          })
              .then(() => {
                  console.log("内部第一个then");
              })
              .then(() => {
                  console.log("内部第二个then");
              });
      })
      .then(() => {
          console.log("外部第二个then");
      });
  // 外部promise
  // 外部第一个then
  // 内部第一个then
  // 外部第二个then
  // 内部第二个then
  ```
  
  这样的打印顺序说明，外层的`then1`要等待内层 then 返回的 Promise 被 resolve，内层`then2`要 resolve 首先等`then1`。



+ 把生成 Promise 的代码提取到一个**工厂函数**中，可以写成这样。

    ```js
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
    3. 状态改变后，触发原型链上(this 上)`then`/`catch`方法
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

2. **引入状态，完善`resolve`、`reject`并在构造函数执行 executor：**

3. **用`then`进行捕获**——`then`接收两个函数分别对应`fulfiied`、`reject`状态：

    ```js
    const p = new Promise((resolve, reject) => {
      
    }).then((res) => {}, (err) => {});
    ```

4. **`then`、`catch`微任务**——setTimeout模拟

5. 测试：

    ```js
    const p = new Promise((resolve, reject) => {
        Math.random() < 0.5 ? resolve('resolve') : reject('reject');
    });
    p.then((value) => {	// 现在还未实现链式调用
        console.log(value);
    }, (err) => {
        console.log(err);
    });
    ```

## 异步、链式调用、值穿透

此时初版还有三个方面需完善：

1. Promise 内部的异步代码执行
2. Promise 的链式调用
3. 值穿透

### 支持异步代码

+ **问题：**初版代码，`Promise`内部有异步代码的话，等待其执行完后才会改变状态，此时`then`内状态是 pending 因此不会触发`then`的回调(*`onFulfilled`/`onRejected`*)。

+ **解决思路：**新增成功态任务队列，`then`时状态 pending 则将`then`参数`onFulfilled`放入队列<span style="color:red">等会执行</span>——`resolve`执行中依次执行成功态函数且改变状态

+ **新增代码：**（以`resolve`为例）

    ```js
    this.onFulfilledCallbackFns = [];	// 存放onFulfilled函数
    const resolve = (value) => {
    	// ...
    	// 依次执行队列中onFulfilled函数：
    	this.onFulfilledCallbackFns.forEach(fn => fn(this.value));	
    };
    
    then(onFulfilled, onReject) {	// 接收成功态函数做参数
      setTimeout(() => {
        if (this.status === 'pending') {
          this.onFulfilledCallbackFns.push(onFulfilled);
        }
      }, 0);
    }

+ **测试：**

    ```js
    const p = new Promise((resolve, reject) => {
        console.log('同步代码')
        setTimeout(() => {
            resolve(1);
        }, 1000)
    });
    p.then((value) => {
        console.log("then1:", value);
    }, (err) => {
        console.log(err);
    });
    p.then((value) => {
        console.log("then2:", value);
    })
    ```

    控制台：

    1. "executor执行"；"同步代码"；"then时pending状态"；"then时pending状态"
    2. （一秒后）"resolve函数执行"；"then1: 1"；"then2: 1"

### 实现链式调用

Promise 的一大优势是支持链式调用，具体来说是`then`方法的具体实现实际上是返回了一个 Promise

+ **注意点：**
    1. 保存之前 Promise 实例引用——this 为`self`
    2. 根据`then`回调函数执行的返回值`ret`
        + 如果是 Promise 实例——返回的下一个 Promise 实例会等待这个 Promise 状态发生变化
        + 如果不是 Promise 实例——根据目前情况直接执行`resolve`或`reject` (见前文`Promise.prototype.then()`返回值)
    3. 注意区分此时`self`与`ret`的状态与关系



# 参考：

+ 总：

    [Promise 对象 · 语雀](https://www.yuque.com/ostwind/es6/docs-promise#b267cd07)

+ Promise.prototype.then():

    [Promise.prototype.then() - JavaScript | MDN](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Promise/then)

+ Promise 链式调用：

    [Promise链式调用顺序引发的思考 | 明日之事 事事难求](https://libin1991.github.io/2019/10/20/Promise%E9%93%BE%E5%BC%8F%E8%B0%83%E7%94%A8%E9%A1%BA%E5%BA%8F%E5%BC%95%E5%8F%91%E7%9A%84%E6%80%9D%E8%80%83/)

+ Promise 实现：

    [详细的Promise源码实现，再被面试问到轻松解答 - 掘金](https://juejin.cn/post/6860037916622913550)



```js
class myPromise {
  constructor(executor) {
    // 初始化状态：
    this.status = PENDING;
    this.value = undefined;
    this.reason = undefined;

    // 成功态回调函数队列：
    this.onFulfilledCallbackFns = [];
    this.onRejectedCallbackFns = [];
    // 定义resolve、reject用户自行在executor内调用
    const resolve = (value) => {
      // console.log("resolve函数执行")
      if (this.status === PENDING) {  // 只有处于pending状态才能更改状态
        this.status = FULFILLED;
        this.value = value;
        // 依次执行成功态函数
        this.onFulfilledCallbackFns.forEach(fn => fn(this.value));
      }
    };
    const reject = (reason) => {
      // console.log("reject")
      if (this.status === PENDING) {  // 只有pending状态才能更改状态
        this.status = REJECTED;
        this.reason = reason;
      }

      this.onRejectedCallbackFns.forEach(fn => fn(this.reason));
    };
    // 立即执行executor，并捕获用户使用时可能出现的异常
    try {
      // console.log('executor执行')
      executor(resolve, reject);
    } catch (e) {
      reject(e);
    }
  }
  then(onFulfilled, onReject) {   // 接收两函数为参——成功/失败调用的函数
    // 保存this
    const self = this;
    // 返回Promise——实现链式调用
    return new Promise((resolve, reject) => {
      if (self.status === FULFILLED) { // fulfilled 状态下才执行
        setTimeout(() => {
          try {
            const res = onFulfilled(this.value);
            res instanceof Promise ? res.then(resolve, reject) : resolve(res);
          } catch (e) {
            reject(e);
          }
        })
      } else if (self.status === REJECTED) {
        setTimeout(() => {
          // console.log('then时reject状态')
          try {
            const res = onReject(this.value);
            res instanceof Promise ? res.then(resolve, reject) : reject(res);
          } catch (e) {
            reject(e);
          }
        })
      } else if (self.status === PENDING) {
        // 说明promise内部有异步代码执行，还未改变状态
        this.onFulfilledCallbackFns.push(() => {
          try {
            setTimeout(() => {
              const res = onFulfilled(self.value);
              // 分情况：回调函数返回值是否是 Promise ？
              res instanceof Promise ? res.then(resolve, reject) : resolve(res);
            })
          } catch (e) {
            reject(e);
          }
        });
        this.onRejectedCallbackFns.push(() => {
          try {
            setTimeout(() => {
              const res = onReject(self.reason);
              // 分情况：回调函数返回值是否是 Promise ？
              res instanceof Promise ? res.then(resolve, reject) : reject(res);
            })
          } catch (e) {
            reject(e);
          }
        });
      }
    })
  }
  catch() {}
  static resolve() {}
  static reject() {}
  static all() {}
  static race() {}
}

const p = new myPromise((resolve, reject) => {
  // 测试异步：
  setTimeout(() => {
    resolve(1);
  }, 1000)
}).then((value) => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      // resolve(value + 2);
    }, 1000)
  })
}).then(value => {
  console.log(value)  // 3
})
```

