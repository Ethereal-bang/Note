# Generator

## Generatator函数

Generator 函数是 ES6 提供的一种异步编程解决方案 

状态机——执行 Generator 会返回一个遍历器对象，可依次遍历函数内部的每一个状态。

Generator 函数有两个**特征**：

+ `function`关键字与函数名间有一个**`*`**
+ 函数体内部使用`yield`表达式定义不同的内部状态



**Generator 函数的调用：**

与普通函数不同的是，调用 Generator 后该函数并不执行，**返回**的是一个指向内部状态的指针对象，即**遍历器对象**。

然后每次调用`next`指针：就从函数头部或上一次停下来的地方开始执行直到下一个`yield`表达式，并返回有着`value`、`done`两属性的对象。即是说 Generator 函数分段执行，`yield`是暂停执行的标记，`next`可以恢复执行

```js
function* helloWorld() {
  yield 'hello';
  yield 'world';
  return 'ending';
}
let hw = helloWorld()
hw.next() // { value: 'hello', done: false }
hw.next() // { value: 'world', done: false }
hw.next() // { value: 'ending', done: true }
hw.next() // { value: undefined, done: true }
```

若该函数无`return`。返回的对象`value`值为`undefined`



最**适用**场景：代码中间需请求数据时 *`ajax`*



## 与 Iterator 接口的关系

`for...of`可以自动遍历 Generator 函数生成的 Iterator 对象，且不需调用`next`方法

```js
function* foo() {
  yield 1;
  yield 2;
  yield 3;
  return 4;
}

for (let v of foo()) {
  console.log(v);
}	// 1 2 3
```

注意一旦返回的`done`为`true`循环就终止且不包含该返回对象。所以上面的`return`语句返回的 4 也不包括在`for...of`中。

> 区分上例执行 .next 后返回 retrun 的值。



## yeild 表达式

需要注意的是，`yield`后面的表达式只有调用`next`方法才会执行，相当于为 JS 提供了手动的“惰性求值”语法功能



`yeild`语句可以返回中间结果

```js
function *show() {
  yeild 12;
  return 5;
}

let gen = show();
let res1 = gen.next();	// {value：12， done：false}
let res2 = gen.next();	//{value: 5, done：true}	
```

上面代码中，`res1`的结果由`yeild`返回，`res2`——最后一步的结果由`generator`函数的`return`返回。

返回的是个 JSON



## next()、throw()、return()

### next 方法的参数

`yield`表达式本身没有返回值，`next`可以带一个参数，会被当作**上一个`yield`表达式的返回值**。

```js
function* f() {
    console.log(1)
    let res = yield;  
    console.log(res)
} 

let g = f()
g.next()    // 1
g.next(2)   // 2 因为yield返回值为2
```

> next 方法会执行到下一 yield 停止，因此上例中`console.log(res)`也会执行。

由于`next`方法的参数表示上一个`yield`表达式的返回值，所以在第一次使用`next`方法时传递参数是无效的



### Generator.prototype.throw()

Generator 函数返回的遍历器对象都有一个`throw`方法，在函数体外抛出错误，然后在 Generator 函数体内捕获



### Generator.prototype.return()

Generator 函数返回的遍历器对象，还有一个`return`方法，可以**返回给定值**，且**终结遍历** Generator 函数

```js
function* gen() {
  yield 1;
  yield 2;
  yield 3;
}

let g = gen();
g.next()        // { value: 1, done: false }
g.return('foo') // { value: "foo", done: true }
g.next()        // { value: undefined, done: true }
```



+ **try...finally**：

    若函数内部有`try...finally`代码块，`return()`方法会**推迟**到`finally`代码块执行完再执行：

    

### 共同点

`next()`、`throw()`、`return()`这三个方法本质上是同一件事，可以放在一起理解。它们的**作用都是让 Generator 函数恢复执行**，且**使用不同语句替换`yield`表达式**

+ **next()**：

    `next()`是将`yield`表达式替换成一个值——`next()`的参数

+ **throw()**：

    将`yield`表达式替换成一个`throw`语句

+ **return()**：

    替换成一个`return`语句



## yield* 表达式

**Generator 函数内部，调用另一个 Generator 函数**，默认情况下没有效果

这就需要用到`yield*`表达式

从语法角度看，若`yield`表达式后跟的是一个遍历器对象，需要在`yield`表达式后加上一个`*`表明返回的是个遍历器对象，这即是`yield*`表达式



实际上，任何有 Iterator 接口的数据结构都可以被`yield*`遍历



## 作为对象属性的 Generator 函数

如果一个对象的属性是`Generator`函数，可简写为：

```js
let obj = {
  * myGeneratorMethod() {
    // ...
  }
}
```



## Generator 函数的异步应用

### 异步任务的封装

```js
function* gen() {
  let url = 'https://api.github.com/users/github';
  let result = yield fetch(url);
  console.log(result.bio)
}
```

上面代码中 Generator 函数封装了一个异步操作，该操作先读取一个远程接口，然后从返回的 JSON 格式的数据解析信息。

执行这段代码的方法如下：

```js
let g = gen();
let result = g.next();
result.value.then(data => {
  return data.json();
}).then(data => {
  g.next(data);
})
```

上面的代码，使用`next()`执行异步任务的第一阶段，由于`fetch`模块返回的是一个 Promise 对象，因此需要用`then`调用下一个`next`。



可以看出，虽然 Generator 函数将异步操作表示得很简洁，但流程管理却不方便



### Thunk 函数

编译器的“传名调用”的实现，往往是将**参数放到一个临时函数**之中，再将这个临时函数传入函数体。这种临时函数就叫做 Thunk 函数

> **传名调用 与 传值调用**：
>
> 这有关于参数的求值策略——函数的参数应何时求值。一种意见就是“传名调用”，直接将表达式传入函数体，只在用到它的时候求值
>
> ```js
> function f(m) {
> return m * 2;
> }
> f(1 + 5)
> 
> // 传值调用等同于：
> f(6)
> // 传名调用：
> f(1 + 5)
> ```

Thunk 函数是实现“传名调用”的一种实现策略，用来替换某个表达式



JS 语言是传值调用，它的 thunk 函数含义有所不同。JS 中，thunk 函数替换的不是表达式，而是多参数函数替换为一个**只接受回调函数作为参数的单参数函数**



### 基于 Thunk 的 Generator 执行器

Thunk 真正的威力在于可以自动执行 Generator 执行器

==?==



## co 模块==？==

[co 函数库的含义和用法 - 阮一峰的网络日志](https://www.ruanyifeng.com/blog/2015/05/co.html)
