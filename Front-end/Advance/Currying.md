# 柯里化（Currying）

## 理解柯里化

+ 柯里化**只转换函数**，并**不调用**该函数

+ 只是将**函数调用方式改变**

    从可调用的 `f(a, b, c)` 转换为可调用的 `f(a)(b)(c)`。

+ 柯里化返回个——**可以逐次调用参数并返回接收剩下参数的函数** 的函数

+ 柯里化的函数等待**传参完**（传参个数）才返回的是最终原函数返回值

    见下例 6、7 行代码。

    

```js
function add (a, b, c) {
    return a + b + c;
}
const curriedAdd = curry(add);
curriedAdd(1)(2)	// [Function (anonymous)]
curriedAdd(1)(2)()	// [Function (anonymous)]
ret()(3)	// 6
```



## 自定义实现柯里化

利用递归与闭包思想

```js
// 调用后返回一个可多次传参的函数
function curry(fn) {
    return function curriedAfterJudge (...args) {   // 每次传的参
        if (args.length === fn.length) {    // 传参数和函数形参个数
            return fn(...args); // 相等时直接调用fn
        } else {
            return (...res) => {
                return curriedAfterJudge(...args, ...res);  // 上一次传的参，新一次传的参 一起传给下次
            }
        }
    }
}
```

测试：

```js
// 测试：
function add (a, b, c) {
    return a + b + c;
}
const curriedAdd = curry(add);
const ret = curriedAdd(1)(2, 3)
console.log("ret", ret)
```

上面第 6 行传到第二个参数`(2, 3)`时传给下一次的`...args`长度 = `fn.length`(*3* )，所以返回 6。



## 柯里化意义

柯里化让我们能够更容易地获取**偏函数**==？==



# 参考链接

[死磕 36 个手写题：柯里化 - 掘金](https://juejin.cn/post/6946022649768181774#heading-16)

