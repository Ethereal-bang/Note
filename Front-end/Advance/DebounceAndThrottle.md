# Debounce、Throttle

防抖、节流都是优化高频率执行 JS 的手段，总的来说，是在时间轴上控制函数发生的次数



## 函数防抖（debounce）

在**事件被触发n秒后再执行回调**，如果在这n秒内又被触发，则重新计时

### 自定义实现 debounce

主要利用定时器的延迟执行，判断是否存在定时器（闭包）。

```js
// fn在等待wait时间后才会执行，重复执行则刷新wait
let debounce = function (fn, wait, ...args) {
    let timer = null;   // 利用闭包设置多次执行函数的全局timer
    return () => {
        // 如果已有函数在等待执行，清除定时器后重新计时
        if (timer) {
            clearTimeout(timer)
            timer = null;    // 重置等待时间
        }
        timer = setTimeout(fn, wait, ...args);
    }
}
```

测试：

```js
console.log("start")
const foo = (...args) => console.log("foo", ...args);
const debounced = debounce(foo, 500);

setInterval(debounced, 300)	// 这样永远不会执行foo
setInterval(debounce, 1000)	// 第一次1500ms后，后面每1000ms执行次
```

### 应用场景

1. 表单的连续点击，防止重复提交。比如重复发送一篇文章。
2. 搜索，连续输入等输入停止后再搜索。
3. 一直拖动浏览器窗口，只想触发一次事件等。



## 函数节流（throttle）

在某**单位时间内，只能有一次触发事件的回调函数执行**（单位时间内有事件被多次触发，只生效一次）

### 自定义实现 throttle

```js
let throttle = function(fn, gapTime, ...args) {
    let flag = false;   // 标示是否已触发函数
    return () => {
        // 还未触发函数：
        if (!flag) {
            // 执行函数且gapTime后重置标示变量
            fn(...args);
            flag = true;
            setTimeout(() => {
                flag = false;
            }, gapTime)
        }
    }
}
```

测试：

```js
const foo = (...args) => {console.log(...args)};
const throttled = throttle(foo, 1000, "first");
setInterval(throttled, 10);
```

执行结果：`start`后立马`first`，后每隔一秒打印。

### 应用场景

- 游戏中的刷新率
- 自动保存草稿功能，当用户在输入的时候(一直触发事件),单位时间内只保存一次草稿。
- DOM元素拖拽