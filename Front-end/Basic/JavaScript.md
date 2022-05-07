# ECMAScript

## script标签

`<script src= type=`

1. 行内式

2. 内嵌式

3. 外部.js文件

    放在`</body>`前，等待整个文档解析完执行。

> script 标签有两个属性——**defer、async：**
>
> + 都是异步加载，Async是在外部 JS 加载完成后，浏览器空闲时，Load 事件触发前执行；而 Defer 是在JS加载完成后，整个文档解析完成后执行。
>     Defer 更像是将`<script>`标签放在`</body>`之后的效果，但是它由于是异步加载 JS 文件，所以可以节省时间。



## 注释

JSDoc 注释：

```js
/**
 * Returns the sum of a and b
 * @param {number} a
 * @param {number} b
 * @returns {number}
 */
function sum(a, b) {
    return a + b;
}
```

> JSDoc 是一个根据javascript文件中注释信息，生成JavaScript应用程序或库、模块的API文档 的工具



## 操作符 / 运算符

+ **?? 空值合并运算符**——左边为 null、undefined 时返回右侧数，否则返回左边

    ```js
    null ?? "foo"	// 1
    0 ?? "bar"	// 0
    ```

+ **比较运算符**：

    ![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607582362136-55308eac-4815-473b-8121-819f2f7aa94f.png)



## 变量

+ **变量提升：**
    + **var**——变量声明提升，赋值不会提升
    + **function**——块级作用域外：函数名提升，不能使用（is not a function）；块级作用域内能使用

+ **暂时性死区 let const：**如果存在全局变量 `tmp`，但是块级作用域内又声明了一个局部变量 `tmp`，导致后者绑定这个块级作用域，那么在 let 声明变量前，（该块级作用域内）使用 `tmp` 会报错（声明的是个 function 也如此



### 数据类型

+ <span style="font-size:20px">类型：</span>
    + 原始类型：Boolean、Number、String、Undefined、Null、Symbol、BigInt
    + 引用类型：Array、Object、Function、Date、RegExp

+ <span style="font-size:22px">检验数据类型:</span>

    + **typeof**——返回字符串

        ```js
        // 原始类型
        typeof 123n	// bigint
        typeof null	// object
        // 引用类型
        typeof [1]	// object
        	// ... object
        typeof new Number(1)	// object
        ```

        > **Note:**
        >
        > + typeof null 为 object
        > + 构造函数为 function（包括内置或自定义 function、class）
        > + 包装对象为 object
        > + 无法区分数组对象、普通对象

    + **instanceof**——原型链判断，返回 boolean

        ```js
        let arr = [1, 2];
        
        console.log(arr instanceof Array);  //true
        ```

        > **Notes:** 只是利用它<span style="color:orange">检测某个实例是否属于这个类</span>的特征来完成数据检测
        >
        > + 不能检测出基本数据类型, 构造函数创建的可以检测

    + **Object.prototype.toString.call()**：

        ```js
        Object.prototype.toString.call(1)  // [object Number]
        ```

        > **Notes:**
        >
        > + 内置类都能检测

  

#### String

字符串的加减乘除

+：字符串拼接

 \-  *  / : 转换为数值运算

字符串中特殊字符/转义符

![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607578347323-24899f12-12a2-403d-9eed-f534b58cf05e.png)



## 函数

### arguments：

**特点**——伪数组，可遍历，具 length 属性，索引方式储存数据，不具有数组的 `push`，`pop` 等方法；

**使用：**不确定多少参数传递的时候：arguments获取

> **Eg**——利用函数求任意个数的最大值：
>
> ```js
> function maxValue() {
>       var max = arguments[0];
>       for (var i = 0; i < arguments.length; i++) {
>          if (max < arguments[i]) {
>            max = arguments[i];
>          }
>       }
>       return max;
> }
> maxValue(12, 4, 9);	// 12
> ```



### 高阶函数

HOC——Higher-order function**一个函数接收另一个函数作为参数**，这种函数就称为高阶函数

> **Eg**——一个最简单的高阶函数：
>
> ```js
> function add(x, y, f) {
>   return f(x) + f(y);
> }
> add(-5, 6, Math.abs)
> ```



## 数组

数组的创建

```js
var arr = new Array();   // 创建一个新的空数组
var arr = [1, 2];	// 字面量创建
```



> Eg——map 搭配箭头函数、三目运算符：
>
> ```js
> let score = [19, 85, 99, 25];
> let result = score.map(item => item>=60 ? '及格' : '不及格'); 
> console.log(score);   //19，   85，  99， 25
> console.log(result);  //不及格，及格，及格，不及格
> ```



## 对象

分为自定义对象、内置对象、浏览器对象

+ <span style="font-size:20px">深拷贝、浅拷贝：</span>

    浅拷贝——指向同一地址，深拷贝——不同地址

    最简便的方法：`JSON.parse`&`JSON.stringify`——<span style="color:red">`undefined`会变成`null`</span>



+ [**可选链操作符 `?.`、`?.[]`、`?.()`**](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Operators/Optional_chaining)：

    允许读取位于连接对象链深处的属性的值，而不必明确验证链中的每个引用是否有效。在引用为空([nullish](https://developer.mozilla.org/zh-CN/docs/Glossary/Nullish) ) ([`null`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/null) 或者 [`undefined`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/undefined)) 的情况下不会引起错误，该表达式短路返回值是 `undefined`。与函数调用一起使用时，如果给定的函数不存在，则返回 `undefined`。



+ <span style="font-size:20px">对象属性的遍历：</span>

    + <span style="font-size:20px">[for...in](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Statements/for...in)：</span>

        会遍历自身以及继承的可枚举属性，不包含 Symbol 属性。 

    + <span style="font-size:20px">[Object.keys()](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Object/keys)：</span>

        遍历自身可枚举属性，不包含 Symbol 属性。

    + <span style="font-size:20px">[Object.getOwnPropertyNames()](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Object/getOwnPropertyNames)：</span>

        返回对象所有自身属性键，包含不可枚举属性、Symbol 组成的数组。

    + <span style="font-size:20px">[Reflect.ownKeys()](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Reflect/ownKeys)：</span>

        返回对象所有自身键，包含不可枚举、Symbol 组成的数组

    + <span style="font-size:20px">[Object.getOwnPropertySymbols()](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Object/getOwnPropertySymbols)：</span>

        返回所有 Symbol 属性的数组



## JSON[^ 1]

一种数据交换格式，实际上是 JavaScript 的一个子集。

JSON.parse()、JSON.stringify()

> 在JSON中，一共就这么几种**数据类型：**
>
> • number：和JavaScript的number完全一致
>
> • boolean：就是JavaScript的true或false
>
> • string：就是JavaScript的string
>
> • null：就是JavaScript的null
>
> • array：就是JavaScript的Array表示方式——[]；
>
> • object：就是JavaScript的{ ... }表示方式。



## 方法

+ <span style="font-size:20px">Array:</span>

    map()——返回一个新的 Array，每个元素为调用 func 的结果
    filter()——返回一个符合 func 条件的元素数组
    some()——返回一个 boolean，判断是否有元素是否符合 func 条件
    every()——返回一个 boolean，判断每个元素是否符合 func 条件
    forEach()——没有返回值，只是针对每个元素调用 func
    reduce()——有返回值，重点是计算数组，返回一个值



## 遍历

### 字符串

+ for...of——得到单个字符
+ for...in——得到字符索引



# DOM[^ 2]

规定了访问 HTML 和 XML 的应用程序接口



## 节点

### 操作 DOM 节点——增删改查

+ <span style="font-size:22px">获取：</span>

    + `getElementById`、`getElementsByTagName`、`getElementsByClassName`

    + HTML5 新增两种获取节点的方法：

        `querySelector`、`querySelectorAll`参数都为CSS选择器字符串

    + 获取完一次元素节点之后，我们还可以通过元素节点的**父子关系**，来更准确的获取元素节点：

        `.childNodes/children`、`.parentNode`、`.previousSibling`、`.firstChild`等。

+ <span style="font-size:22px">添加：</span>

    - `document.createElement()`创建一个元素节点
    - `document.createTextNode()`创建一个文本节点

    - `appendChild()`  向节点的子节点列表的末尾添加新的子节点
    - `insertBefore()` 节点任意位置插入

+ <span style="font-size:22px">删除：</span>

    `parentNode.removeChild ()`返回被删除节点

+ <span style="font-size:22px">修改：</span>

    替换内容：  `innerHTML`/`innerText`属性



## 事件

### DOM事件流

事件流——DOM 事件发生时在元素节点之间按照特定的顺序传播：

![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607827215374-7e13973d-d5e7-4698-98e5-fe5d9e5f1bb3.png)

js 代码中只能执行捕获或冒泡其中的一个阶段

- **onclick** 和 attachEvent 只能得到**冒泡阶段**
- addEventListener(type, listener[, useCapture]) 第三个参数若为true，表示在<span style="color:red">事件捕获阶段调用</span>事件处理程序；fasle 默认，<span style="color:red">事件冒泡阶段调用</span>
- 实际开发很少使用事件捕获
- 有些事件无冒泡  *onblur、onfocus、onmouseenter、onmouseleave*



+ <span style="font-size:22px">阻止事件冒泡:</span>

    标准写法  利用事件对象里的stopPropagation()方法 **e.stopPropagation()**

    ```js
    outer.addEventListener("click", () => {
        console.log("click outer")
    })
    inner.addEventListener("click", (e) => {
        e.stopPropagation();    // 阻止事件冒泡到上层 触发下载
    		console.log("click inner")
    }, true)
    ```

    > 上例中，添加`e.stopPropagation`后点击内层`closeBtn`触发`inner`后将不再触发`outer`

+ <span style="font-size:22px">事件委托:</span>

    原理不是每个子节点单独设置事件监听器，而是事件监听器设置在其父节点上，利用冒泡原理影响设置每个子节点

    > Eg——ul 和 li：
    >
    > 给 ul 注册点击事件，然后利用事件对象的 target 来找到当前点击的 li，因为点击 li，事件会冒泡到 ul 上， ul 有注册事件，就会触发事件监听器

    作用——只操作了一次DOM，提高了程序性能
    
+ <span style="font-size:22px">e.target VS e.currentTarget：</span>

    target 指触发的元素，而 currentTarget 是绑定事件的元素



# BOM

BOM即浏览器对象模型，提供了独立于内容而与浏览器窗口进行交互的对象，核心对象 window

BOM由一系列相关的对象构成，为每个对象提供了很多方法与属性

DOM与BOM

​          ![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607831610389-7afde62c-8b5d-41c1-aeb0-ae63b7359f3d.png)

BOM的构成

BOM 比 DOM更大，包含DOM![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607834956646-9d1b4418-8d2d-4167-8a90-f978c066eb43.png)

## window对象

window 对象是浏览器的顶级对象，具有双重角色

1.js访问浏览器窗口的一个接口

2.它是一个全局对象。定义在全局作用域中的变量、函数都会变成window对象的属性和方法

调用时可省略window，例如  `alert()`、`prompt()`

window下的一特殊属性 window.name



+ <span style="font-size:22px">window对象的常见事件</span>

    +  **window.onload** ——当文档内容加载完成会触发事件包括图像、脚本文件、CSS文件就调用的处理函数

        > **DOMContentLoaded 事件**仅当DOM加载完成时触发，不包括样式表、图片、flash等
        >
        > 若页面内容很多，从用户访问到 onload  触发需较长时间，此时用 DOMContentLoaded  事件较合适

    + **window.onresize** 

        > 常用来完成响应式布局 *window.innerWidth 当前屏幕宽度*

        > window.addEventLIstenner("resize",funtion () { } );

        

+ <span style="font-size:22px">setTimeout()、setInterval()</span>

    > **清除定时器：**
    >
    >   `window.clearTimeout(<定时器标识符>)`



## location对象

window 对象提供 location 属性用于获取或设置窗体的URL，也可用于解析URL。因为这个属性返回的是一个对象，所以也将这个属性称为 location 对象

> **URL** 统一资源定位符：网上标准资源的地址，包含的信息指出文件的位置及浏览器应该怎么处理
>
> 一般语法格式： <span style="color:red">protocol://host[:port]/path/[?query]#fragment</span>
>
> 如，http://www.itcast.cn/index.html?name=andy&age=18#link![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607942300479-ec02fda1-7018-43c8-82ae-fc4a685d63db.png)

+ <span style="font-size:22px">loacation 对象的属性</span>

    > 使用 location 实现简易路由效果：
    >
    > ```js
    > if (...) {
    >     location.hash = "/person";
    > }
    > 
    > window.addEventListener("hashchange", (e) => {
    >     console.log("hashchange: ", location.hash)
    >     switch (location.hash) {
    >         case "":
    >             route.person.style = "display: none";
    >             route.home.style = "display:block";
    >             break;
    >         case "#/person":
    >             route.home.style = "display: none";
    >             route.person.style = "display:block";
    >             break;
    >     }
    > })
    > ```
    >
    
    ​		

+ <span style="font-size:22px">location 对象的方法</span>![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607943026547-daaffd15-cfc0-4be8-bac6-fd43f21ce0ff.png)



## navigator 对象

- 包含有关浏览器的信息
- 有很多属性，最常用 userAgent 可返回由客户机发送服务器的 user-agent 头部的值



## history 对象

- 与浏览器历史记录进行交互
- 对象包含用户（在浏览器窗口）访问过的URL

![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607944297803-2e442824-f923-465b-a25d-d8023337071b.png)



# Ref

+ ECMAScript:

    + script 标签：

        [JS脚本加载中，defer和async的区别 - 知乎](https://zhuanlan.zhihu.com/p/30898865)	

    + 变量：

        [JS中数据类型检测四种方式的优缺点](https://juejin.cn/post/6844904115097567239)
        
    + 方法：

        [[js数组方法forEach、map、filter、reduce、every、some总结](https://segmentfault.com/a/1190000016025949)](https://segmentfault.com/a/1190000016025949)

+ DOM：

    [currentTarget VS target - 掘金](https://juejin.cn/post/6844904047913205767)


[^ 1]: JavaScript Object Notation
[^ 2]: document object model，文档对象模型

