# ECMAScript

## 位置

### 1. 行内式

不推荐

### 2.内嵌式

```
<script>
    alert('Hello  World~!');
 </script>
```

### 3.外部.js文件

```
<script src="my.js"></script>
```

放在`</body>`前。

## 注释

单行注释：ctrl+/

多行注释：alt+shift+a

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



## 变量

预解析/变量提升js代码执行前，浏览器默认把带有var声明的变量进行提提前定义 变量的赋值不会提升

[变量提升底层原理](https://www.cnblogs.com/echo-hui/p/9230942.html)

*变量提升*

![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1608546490131-8b3f696e-f77e-4b07-a5c6-97d3db74b2e4.png)

## var 与 let

let会有**暂时性死区**：如果存在全局变量tmp，但是块级作用域内let又声明了一个局部变量tmp，导致后者绑定这个块级作用域，那么在let声明变量前，（该块级作用域内）使用`tmp`会报错



博文链接：**[for 循环中的 var 与 let](https://blog.csdn.net/weixin_44249754/article/details/111467804)**

JS中的for循环体比较特殊，每次执行都是一个全新的独立的块作用域，用let声明的变量传入到 for循环体的作用域后，不会发生改变，不受外界的影响

![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1608547157163-538a10cd-f115-4b40-bfb6-0ba5e4857eaf.png)![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1608547179295-2b50a945-5593-41f4-a904-edf81815f9f6.png)



### 数据类型

![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607578228825-82bb3f72-d6ab-4820-9db0-4ed60926ed48.png)

**检验复杂数据类型**

- - **typeof**
  - **instanceof**

```
let arr = [1, 2];

console.log(arr instanceof Array);  //true
```

- - **constructor** ?
  - **prototype**  ?

#### 字符串型String

字符串的加减乘除

+：字符串拼接

 \-  *  / : 转换为数值运算

字符串中特殊字符/转义符

![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607578347323-24899f12-12a2-403d-9eed-f534b58cf05e.png)

字符串拼接

数值与字符串：数值相加 ，字符相连 

```
//1. 字符串 "相加"
alert('hello' + ' ' + 'world'); // hello world
//1. 数值字符串 "相加"
alert('100' + '100'); // 100100
//1. 数值字符串 + 数值
alert('11' + 12);     // 1112 
```

字符串与变量

变量是不能添加引号的，因为加引号的变量会变成字符串

```
var age = 18;
// console.log('pinkage岁啦');       // 这样不行哦
console.log('pink' + age);          // pink18
console.log('pink' + age + '岁啦');  // pink18岁啦
```

#### 布尔型Boolean

值true  false

布尔型加数字型

 true 的值为 1 ，false 的值为 0

```
console.log(true + 1);  // 2
console.log(false + 1); // 1
```

#### Undefined

一个声明后没有被赋值的变量会有一个默认值 undefined

进行相连或者相加时，注意结果

```
var variable;
console.log(variable);           // undefined
console.log('你好' + variable);  // 你好undefined
console.log(11 + variable);     // NaN
console.log(true + variable);   //  NaN
```

#### Null

声明变量给 null 值，里面存的值为空

进行相连或者相加时，注意结果

```
var vari = null;
console.log('你好' + vari);  // 你好null
console.log(11 + vari);     // 11
console.log(true + vari);   //  1
```

### 数组

概念

数组是指一组数据的集合，其中的每个数据被称作元素，在数组中可以存放任意类型的元素。数组是一种将一组数据存储在单个变量名下的优雅方式

数组的创建

- 利用new创建数组**var 数组名 = new Array() ；**

```
var arr = new Array();   // 创建一个新的空数组
```

- 利用数组字面量创建数组使用最多

```
var  arr = []； //1. 使用数组字面量方式创建空的数组
var  arr = ['小白','小黑','大黄','瑞奇'];
             //2. 使用数组字面量方式创建带初始值的数组
```

数组元素的类型数组中可以存放任意类型的数据，例如字符串，数字，布尔值等

数组的长度**数组名.length**可以访问元素的数量

获取数组元素数组名[下标]

遍历数组

数组中新增元素 可以通过修改数组索引的方式追加数组元素  

不能直接给数组名赋值，否则会覆盖掉以前的数据 



<span style='font-size:20px'>方法</span>

**map**

映射   *一个对一个*，返回数组。



<span style='font-size: 18px'>map 搭配箭头函数</span>

```javascript
let arr = [12, 5];
let doubleResult = arr.map(item=>item*2);	// 相当于arr在给括号里的函数多次传参

console.log(doubleResult); //24,10
```



<span style='font-size: 18px'>map 搭配箭头函数、三目运算符</span>

```javascript
let score = [19, 85, 99, 25];
let result = score.map(item => item>=60 ? '及格' : '不及格'); 

console.log(score);   //19，   85，  99， 25
console.log(result);  //不及格，及格，及格，不及格
```

上面代码中的`item`为函数参数名



**reduce**

汇总   *一堆得出一个*，返回数值。



<span style='font-size: 18px'>求和</span>

注意：三个参数次序。

```javascript
let arr = [1, 2, 5];

let result = arr.reduce( function(tmp, item, index) {
  return tmp + item;  //作用：求和
} ); 

console.log(result);

// 箭头函数
let result = arr.reduce( (tmp, item, index) => tmp + item );
```



<span style='font-size: 18px'>求均值</span>

注意：三个参数次序。

```javascript
let arr = [2, 5, 5];
let result = arr.reduce((tmp, item, index) => {
    if(index != arr.length-1)
        return tmp + item;	// 不是最后次时仅求和
    else
        return (tmp + item) / arr.length;	// 求和并求均值
});
    console.log(arr);
    console.log(result);
```



**filter**

过滤器 *依据条件筛选*，返回数组。



```javascript
let arr = [12, 5, 9];

let result = arr.filter(item => item % 3 == 0); //保留能被3整除的数

console.log(result);	// [12, 9]
```

从上例还可以看出，`return`还能接这样的语句`item % 3 == 0`。

> `return [[expression]];`表达式的值会被返回。如果忽略，则返回 `undefined`。

显然，返回的值也包括了布尔值。



**forEach**

循环 *迭代*



## 标识符

命名

字母(A-Za-z)、数字(0-9)、下划线(_)、美元符号( $ )组成

不可是关键字、保留字

区分大小写

驼峰命名法

见名知意



## 操作符/运算符

作用实现赋值、比较和执行算数运算等功能

规范操作符的左右两侧各保留一个空格

运算符优先级

![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607582564942-d8bc1e78-4fdf-413d-abbe-d3574ad9c34a.png)

### 算术运算符

浮点数的精度问题

不要直接判断两个浮点数是否相等

```
var result = 0.1 + 0.2;    // 结果不是 0.3，而是：0.30000000000000004
console.log(0.07 * 100);   // 结果不是 7，  而是：7.000000000000001
```



### 比较运算符

规则

![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607582362136-55308eac-4815-473b-8121-819f2f7aa94f.png)



## 语句

### 输入输出语句

![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607577500839-3f2330a7-5b48-41a9-b7d4-f04b3ea7dade.png)

prompt(输入框显示信息，默认输入值 可省)  返回值为字符串

## 流程控制

断点调试

指自己在程序的某一行设置一个断点，调试时，程序运行到这一行就会停住，然后可以一步一步往下调试，调试过程中可以看各个变量当前的值，出错的话，调试到出错的代码行即显示错误，停下。

断点调试可以帮我们观察程序的运行过程 浏览器中按 F12--> sources -->找到需要调试的文件-->在程序的某一行设置断点

Watch: 监视，通过watch可以监视变量的值的变化，非常的常用

F11: 程序单步执行，让程序一行一行的执行，这个时候，观察watch中变量的值的变化



## 函数

函数：就是封装了一段可被重复调用执行的代码块。通过此代码块可以实现大量代码的重复使用



### arguments：

展示形式：伪数组，可遍历

特点：具length属性；

按索引方式储存数据；

不具有数组的push，pop等方法；

使用：不确定多少参数传递的时候：arguments获取   所有函数都内置arguments对象，arguments存储了传递的所有实参

​    *利用函数求任意个数的最大值*

```
function maxValue() {
      var max = arguments[0];
      for (var i = 0; i < arguments.length; i++) {
         if (max < arguments[i]) {
                    max = arguments[i];
         }
      }
      return max;
}
 console.log(maxValue(2, 4, 5, 9));
 console.log(maxValue(12, 4, 9));
```

函数与方法异同函数：单独存在，需声明，通过“函数名()”的方式就可以调用

方法：对象里面的函数称为方法，方法不需要声明，使用“对象.方法名()”的方式就可以调用，方法        用来描述该对象的行为和功能。  



### 高阶函数

#### 高阶函数的概念

高阶函数英文叫 Higher-order function 。那么**什么是高阶函数：**

JS的函数其实都指向某个变量。既然变量可以指向函数，函数的参数能接收变量。那么，**一个函数就可以接收另一个函数作为参数**，这种函数就称为高阶函数



一个最简单的高阶函数

```
function add(x, y, f) {
  return f(x) + f(y);
}

add(-5, 6, Math.abs)
```

当我们调用`add(-5, 6, Math.ads)`时，根据函数定义，我们可以推导计算过程为：

```
x = -5;
y = 6;
f = Math.abs;       // Math对象，abs()返回数的绝对值

f(x) + f(y) ==> Math.abs(-5) + Math.abs(6) ==> 11
return 11;
```



#### map() 和 reduce()

**map()**

**
**

比如说，我们有一个函数f(X)=x2，要把这个函数作用在一个数组`[1, 2, 3, 4, 5, 6, 7, 8, 9]`上，就可以用`map()`实现

![image.png](https://cdn.nlark.com/yuque/0/2021/png/2617721/1613357393450-b9c9a64a-eb14-4ea5-8f1a-3f16609436a5.png)

由于`map()`定义在JS的`Array`中，我们调用`Array`的`map()`方法，传入我们自己的函数，就得到了一个新的`Array`作为结果：

```
let pow = function (x) {
  return x * x;
};
let arr = [1, 2, 3, 4, 5, 6, 7, 8, 9];

arr.map(pow);   // [1, 4, 9, 16, 25, 36, 49, 64, 81]
```

注意：`map()`传入的**参数**是`pow`，即**函数对象本身**

`map()`作为高阶函数，事实上它把运算规则抽象了，因此我没不但可以计算简单的f(x)=x2，还可以计算任何复杂的函数，比如，把数组的所有数字转为字符串

```
let arr = [1, 2, 3];

arr.map(String);    // ['1', '2', '3']
```



**reduce()**

再看`reduce()`的用法。。Array的`reduce()`把一个函数作用在这个`Array`的`[x1, x2, x3...]`上，这个函数必须接收**两个参数**，`reduce()`把结果继续和序列的下一个元素做**累积计算**，其效果就是：  

```
[x1, x2, x3, x4].reduce(f) == f( f( f( x1, x2 ),x3 ), x4 )
```



比如说对一个`arr`求和，就可以用`reduce`实现

```
let arr = [1, 3, 5];
let f = function (x, y) {
    return x + y;
};

arr.reduce(f);
```



### 闭包

**函数作为返回值**

高阶函数除了可以接受函数作参数外，还可把函数作为结果值返回



实现一个对 Array 的求和。通常情况，求和的函数是这样定义的：

```
function sum(arr) {
  return arr.reduce(function (x, y) {   // 此处function (x,y)为匿名函数
    return x + y;
  });
}

sum([1, 2, 3, 4]); // 10
```

但是，如果不需要立刻求和，而是在后面的代码中，根据需要再计算怎么办。可以不返回求和的结果，而是返回求和的函数。    ?

```
function lazySum(arr) {
  let sum = function () {
    return arr.reduce(function (x, y) {
      return x + y;
    });
  }
  return sum;
}

lazySum([1, 2, 3]); // 返回函数：sum()

let f = lazySum([1, 2, 3]);
f();    // 求和结果：6
// 或者
lazySum([1, 2, 3])();   // 6
```

当我们调用`lazySum`时，返回的并不是求和结果，而是求和函数。调用函数`f`时，才真正计算求和的结果。

在这个例子中，我们在函数`lazySum`中又定义了函数`sum`，并且**内部函数**`sum`可以引用**外部函数**`lazySum`的参数`arr`和局部变量，当`lazySum`返回函数`sum`时，相关参数和变量都保存在返回的函数中，这种称为闭包的程序结构拥有极大的威力。



注意：当我们调用闭包函数时，每次调用都会返回一个新的函数，即使传入相同的函数  why?

```
let f1 = lazySum([1, 2, 3, 4, 5]);
let f2 = lazySum([1, 2, 3, 4, 5]);
f1 === f2; // false
f1 == f2;   // false
```



闭包除了返回一个函数延迟执行还有非常强大的功能。举个例子：

在面向对象的程序设计语言里，比如Java和C++，要在**对象内部封装一个私有变量**？，可以用`private`修饰一个成员变量。  ？

在没有`class`机制，只有函数的语言里，借助闭包，同样可以封装一个私有变量

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




### 内置对象

+ <span style="font-size:20px">Math对象</span>  

+ <span style="font-size:20px">Date对象</span>

- <span style="font-size:20px">数组Array对象</span>

+ <span style="font-size:20px">String对象</span>



### JSON

#### JSON 概述

JSON 是 JavaScript Object Notation 的缩写，是一种数据交换格式



在 JSON 出现之前，大家一直用 XML 传递数据。因为 XML 是一种纯文本格式，适合在网络上交换数据。XML 本身不复杂，但加上DTD、XSD、XPath、XSLT等一大堆复杂的规范以后令人头大。

于是出现了 JSON 这种轻量级的数据交换格式



JSON 实际上是 JavaScript 的一个子集。在JSON中，一共就这么几种数据类型：

• number：和JavaScript的number完全一致

• boolean：就是JavaScript的true或false

• string：就是JavaScript的string

• null：就是JavaScript的null

• array：就是JavaScript的Array表示方式——[]；

• object：就是JavaScript的{ ... }表示方式。

以及上面的任意组合



# DOM——文档对象模型

文档对象模型，是document object model的简称DOM（文档对象模型）：规定了访问 HTML 和 XML 的应用程序接口

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

事件流从页面中接受事件的顺序

DOM事件流事件发生时在元素节点之间按照特定的顺序传播，这个传播过程即DOM事件流

三个阶段：1.捕获阶段  由DOM最顶层节点开始逐级向下传播到DOM最顶层节点的过程

​    2.当前目标阶段

​    3.冒泡阶段  事件开始时由最具体的元素接受后逐级向上传播到DOM最顶层节点的过程

![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607826947254-1047eabf-71bf-403e-8092-e5a74e3391d2.png)![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607827215374-7e13973d-d5e7-4698-98e5-fe5d9e5f1bb3.png)

js代码中只能执行捕获或冒泡其中的一个阶段

- onclick 和 attachEvent 只能得到冒泡阶段
- addEventListener(type, listener[, useCapture])第三个参数若为true，表示在事件捕获阶段调用事件处理程序；fasle默认表示在事件冒泡阶段调用事件处理程序
- 实际开发很少使用事件捕获
- 有些事件无冒泡  *onblur、onfocus、onmouseenter、onmouseleave*
- 冒泡有时带来麻烦；有时帮助很巧妙地做某些事件



### 注册事件

给元素添加事件

#### 传统注册方法  

on开头的事件

​     注册事件的唯一性：同一元素同一事件只能设置同一个处理函数，后注册的会覆盖前面

```
<button onclick=“alert('hi~')”></button>
btn.onclick = function() {} 
```

#### 事件监听注册方式

eventTarget.addEventListtener(type, listener[, useCapture])

   type:事件类型字符串，如click、mouseover

listener:事件处理函数，事件发生时调用该监听函数

useCapture:可选参数，布尔值，默认false

eventTarget.attachEvent(eventNameWitchOn, callback)

eventNameWithOn：事件类型字符串，如onclick、onmouseover

callback:事件处理函数，目标触发事件时回调函数被调用

### 删除事件

### 阻止事件冒泡

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

上例中，添加`e.stopPropagation`后点击内层`closeBtn`触发`inner`后将不再触发`outer`

### 事件委托

原理不是每个子节点单独设置事件监听器，而是事件监听器设置在其父节点上，利用冒泡原理影响设置每个子节点

*点击每个 li 都会弹出对话框，以前需要给每个 li 注册事件，是非常辛苦的，而且访问 DOM 的次数越多，这就会延长整个页面的交互就绪时间。* 

*给 ul 注册点击事件，然后利用事件对象的 target 来找到当前点击的 li，因为点击 li，事件会冒泡到 ul 上， ul 有注册事件，就会触发事件监听器*

作用只操作了一次DOM，提高了程序性能

# BOM

## BOM概述

BOM即浏览器对象模型，提供了独立于内容而与浏览器窗口进行交互的对象，核心对象window

BOM由一系列相关的对象构成，为每个对象提供了很多方法与属性

DOM与BOM

​          ![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607831610389-7afde62c-8b5d-41c1-aeb0-ae63b7359f3d.png)

BOM的构成

BOM 比 DOM更大，包含DOM![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607834956646-9d1b4418-8d2d-4167-8a90-f978c066eb43.png)

### window对象 

window 对象是浏览器的顶级对象，具有双重角色

1.js访问浏览器窗口的一个接口

2.它是一个全局对象。定义在全局作用域中的变量、函数都会变成window对象的属性和方法

调用时可省略window，前面学习的对话框都属于window对象方法  *alert()、prompt()*

window下的一特殊属性 window.name

#### window对象的常见事件

窗口加载事件

- - **window.onload = function () {}**  window.onload是窗口(页面)加载事件，当文档内容加载完成会触发事件包括图像、脚本文件、CSS文件就调用的处理函数

注

有了 window.onload 就可以把js代码写到页面元素的上方，因为 onload 是等页面内容全部加载完毕再执行处理函数

window.onload 传统注册事件方式只能写一次，若多个，以最后一个 window.onload 为准

若使用 addEventListener 没有限制

- - **window.addEventListener(****"****DOMContentLoaded** **"****,function() {} ) ;**

注  DOMContentLoaded 事件仅当DOM加载完成时触发，不包括样式表、图片、flash等

若页面内容很多，从用户访问到 onload  触发需较长时间，此时用 DOMContentLoaded  事件较合适

调整窗口大小事件

- - **window.onresize = funtion () {  }**  window.onresize 是调整窗口大小加载事件触发时就调用的处理函数

常用来完成响应式布局 *window.innerWidth 当前屏幕的宽度*

- - **window.addEventLIstenner(****"resize"****,funtion () { } );**

#### setTimeout()定时器

**window.setTimeout (调用函数， [延迟的毫秒数]）;

setTimeout() 方法用于设置一个定时器，在定时器到期后执行调用函数

这个调用函数我们也称为回调函数 callback  普通函数按照代码顺序直接调用，而这个函数时间到了才去调用

window可省略

调用函数可以直接写函数，或写函数名，或字符串 '函数名()'不推荐 三种形式

延迟的毫秒数省略默认是0

定时器可能很多，常给定时器赋值一个标识符

停止 setTimeout() 定时器

  **window.clearTimeout(****timeoutID(定时器标识符)****)**

window可省

#### setInterval() 定时器

**window.setInterval(****回调函数****, [****间隔的毫秒数****]);**

setInterval() 方法每隔这个时间，就去调用一次回调函数

window 可省

调用函数有三种形式  同上

间隔的毫秒数  同上

标识符

第一次执行也是间隔毫秒数之后执行

停止 setInterval() 定时器

**window.clearInterval (****intervalID****) ;**

clearInterval ()方法取消了通过调用 setInterval ()建立的定时器

### location对象

window对象提供 location 属性用于获取或设置窗体的URL，也可用于解析URL。因为这个属性返回的是一个对象，所以也将这个属性称为 location 对象

URL统一资源定位符：网上标准资源的地址，包含的信息指出文件的位置及浏览器应该怎么处理

一般语法格式： **protocol://host[:port]/path/[?query]#fragment**

​     [**http://www.itcast.cn/index.html?name=andy&age=18#link**](http://www.itcast.cn/index.html?name=andy&age=18#link) 

####     ![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607942300479-ec02fda1-7018-43c8-82ae-fc4a685d63db.png)

#### loacation 对象的属性

​      重点：href、search

![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607942416810-c4442265-19f7-452c-8d95-a3347ae883fa.png)

#### location 对象的方法

#### ![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607943026547-daaffd15-cfc0-4be8-bac6-fd43f21ce0ff.png)

#### navigator 对象

- 包含有关浏览器的信息
- 有很多属性，最常用 userAgent 可返回由客户机发送服务器的 user-agent 头部的值

*
*

*判断用户哪个终端打开页面，实现跳转*

```
if((navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i))) {
    window.location.href = "";     //手机
 } else {
    window.location.href = "";     //电脑
 }
```

#### history 对象

- 与浏览器历史记录进行交互
- 对象包含用户（在浏览器窗口）访问过的URL

![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607944297803-2e442824-f923-465b-a25d-d8023337071b.png)



