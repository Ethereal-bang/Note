# 闭包

**闭包：** **函数**和对**其词法环境**[^1]的引用 的组合——这个环境包含了函数**创建时**能访问的所有局部变量

**效果：**可以在一个内层函数中访问到其外层函数的作用域



# 闭包表现形式

<span style="font-size:20px">函数作为返回值：</span>

```js
function create() {
    let a = 1;
    return function () {
        console.log(++a);
    }
}

const f = create();
f() // 2
f() // 3
```



<span style="font-size:20px">函数作参数传递：</span>

```js
function print(fn) {
    const a = 1;
    fn(a);  // 2
}
const a = 2;
function fn() {
    console.log(a)  
}
print(fn);
```

> `fn` 创建时就与词法环境中外层的 `a` 变量绑定了，因此 `print` 内调用 `fn` 时



# 闭包用途

<span style="font-size:20px">读取函数内部的变量</span>



<span style="font-size:20px">让变量值始终保持在内存</span>



<span style="font-size:20px">数据隐藏、封装——模拟私有变量和私有方法:</span>

```js
const counterObj = (function() {
    let private_cnt = 0;
    function private_changeBy(val) {
        private_cnt += val;
    }
    return {    // 公有方法
        increment: () => {
            private_changeBy(1);
        },
        value: () => {
            return private_cnt;
        }
    }
})();
counterObj.increment();
counterObj.value();   // 1
```

> 上例中两个公共函数共享同一环境的闭包，访问其私有变量。而外部不能访问到它们。



# EG

<span style="font-size:20px">经典问题 for 循环:</span>

```js
for (var i = 0; i < 5; i++) {
  setTimeout(() => {  
    console.log("原: ", i);  // 一秒后一次性打印55555
  }, 1000);
}
```

> 问题在于——尽管循环中的五个函数是在各个迭代中分别定义的，但是它们都被封闭在一个共享的全局作用域中共享一个 `i` 的引用
>
> 上面的 for 循环等价于：
>
> ```js
> var i = 1;
> // 定时器将其回调函数加入任务列表，执行栈清空一秒后执行
> var i = 2;
> // 定时器将其回调函数加入任务列表，执行栈清空一秒后执行
> // 重复如上...
> 
> // 循环结束后，线程读取任务列表，将定时事件对应的异步任务（回调函数）入栈执行
> console.log(i);	// 执行5次
> ```
>
> > 这也解释了为什么上例中 setTimeout 的效果是一秒后一次性打印

> 错误的闭包解决：
>
> ```js
> for (var i = 0; i < 5; i++) {
>   (function () { // 相当于利用IFE产生了一个块级作用域
>     setTimeout(() => {
>       console.log(i)
>     }, 1000);
>   })(i)
> ```
>
> 结果还是一样，因为新加的 IFE 作用域内没有变量 `i`，执行栈情空后读取回调时仍是向上找到唯一的全局变量 `i`

正确的 IFE 闭包：

```js
for (var i = 0; i < 5; i++) {
  (function (i) { 	// 在每次迭代捕获i的副本
    setTimeout(() => {
      console.log("s1: ", i)
    }, 1000);
  })(i)
```



# REF

[闭包 - JavaScript | MDN](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Closures)

[review/闭包.md at main · Martinyua/review](https://github.com/Martinyua/review/blob/main/1.JS-Basic/%E9%97%AD%E5%8C%85.md)

[循环与闭包 之 for循环经典问题解释 / 结合《你不知道的JS》与《高程》案例 - CSDN](https://blog.csdn.net/Beijiyang999/article/details/74188430)



[^1]: lexical environment