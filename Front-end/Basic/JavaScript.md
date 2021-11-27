

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

### 事件委托

原理不是每个子节点单独设置事件监听器，而是事件监听器设置在其父节点上，利用冒泡原理影响设置每个子节点

*
*

*点击每个 li 都会弹出对话框，以前需要给每个 li 注册事件，是非常辛苦的，而且访问 DOM 的次数越多，这就会延长整个页面的交互就绪时间。* 

*给 ul 注册点击事件，然后利用事件对象的 target 来找到当前点击的 li，因为点击 li，事件会冒泡到 ul 上， ul 有注册事件，就会触发事件监听器*

*
*

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

## JS执行机制

JS一大特点：单线程  同一时间只能做一件事

单线程导致：如果JS执行时间过长会造成页面的渲染不连贯，导致页面渲染加载堵塞

执行机制

1. 1. 执行执行栈中的同步任务
   2. 异步任务回调函数放入任务队列中  等待状态
   3. 执行栈中所有同步任务执行完毕，系统按次序读取任务队列中异步任务。被读取的异步任务结束等待状态，进入执行栈，开始执行

由于主线程不断的重复获得任务、执行任务、再获取任务、再执行，所以这种机制被称为事件循环

### 同步和异步

HTML5允许JS脚本创建多个线程。于是出现 同步 和 异步

#### 同步

前一个任务结束后执行后一个任务，程序执行顺序与任务排列顺序是一致、同步的

任务都在主线程上执行，形成执行线

#### 异步

本质区别流水线上各个流程的执行顺序不同

异步通过回调函数实现  相关回调函数添加到任务队列 也称消息队列

一般分为三类型：

- - - - - - - - 普通事件  *click  resize*
              - 资源加载  *load  error*
              - 定时器  *setInterval  setTimeout*



# 正则表达式

[---菜鸟教程---](https://www.runoob.com/js/js-regexp.html)、[---知乎---](https://zhuanlan.zhihu.com/p/67936788)、[RegExp - 廖雪峰的官方网站](https://www.liaoxuefeng.com/wiki/1022910821149312/1023021582119488)

正则表达式是一种用来匹配字符串的强有力的武器。它的设计思想是用一种描述性的语言来给字符串定义一个规则，凡是符合规则的字符串，我们就认为它“匹配”了，否则，该字符串就是不合法的。

# PC端网页特效

三大系列对比

- - - offset 系列常用于获得元素位置  *offsetLeft、offsetTop*
    - client           获取元素大小  *clientWidth、clientHeight*
    - scroll         获取滚动距离  *scrollTop、scrollLeft  页面滚动距离通过window.pageXOffset 获取*

## ![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607946763920-1b2cdc53-c095-48e6-8a5f-2180c5abaf60.png)

## 元素偏移量 offset 系列

offset系列相关属性可以动态地得到

- - - - - - - - 元素距离带有定位父元素的位置
              - 元素自身的宽度高度
              - 返回的数值不带单位

![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607945087360-dc1d6912-1848-4c9f-bc59-b22bf32cee14.png)

offset 系列常用属性

​              ![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607944986409-b967442e-7bbb-491d-909f-a0a127f14bc3.png)

offset 与 style 区别

​              ![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607945050043-e848545b-50ba-485b-8e90-675d40e587c2.png)

*
*

*获取鼠标在盒子内的坐标：**我们在盒子内点击，想要得到鼠标距离盒子左右的距离*

*首先得到鼠标在页面中的坐标（e.pageX, e.pageY）*

*其次得到盒子在页面中的距离 ( box.offsetLeft, box.offsetTop)*

​    *用鼠标距离页面的坐标减去盒子在页面中的距离，得到 鼠标在盒子内的坐标*

*如果想要移动一下鼠标，就要获取最新的坐标，使用鼠标移动事件 mousemove* 

```
var box = document.querySelector('.box');
box.addEventListener('mousemove', function(e) {
var x = e.pageX - this.offsetLeft;
var y = e.pageY - this.offsetTop;
this.innerHTML = 'x坐标是' + x + ' y坐标是' + y;
})
```

## 元素可视区 client 系列

client:客户端，通过 client 系列相关属性可动态得到该元素的边框大小、元素大小等

![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607946085558-e717e4d2-0bd5-478c-964e-416832eab6be.png)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607946104729-15e057a0-0c47-4931-9e46-929cb519b0b1.png)

## 元素滚动 scroll 系列

scroll：滚动

使用 scroll 系列相关属性可可动态得到该元素的大小、滚动距离

![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1607946388594-712d0f95-ac99-40f7-9e22-20a675599ca1.png)

### 页面被卷去的头部

如果浏览器的高（或宽）度不足以显示整个页面时，会自动出现滚动条

当滚动条向下滚动时，页面上面被隐藏掉的高度，我们就称为页面被卷去的头部

滚动条在滚动时会触发 onscroll 事件

## 动画函数封装

## 常见网页特效案例

### 网页轮播图



# 随堂代码练习

## 数组

- 求数组最大值

for、if

```
<script>
        var arr = [2,6,1,77,52,25,7];
        var max = arr[0];
        for (var index; index<arr.length; index++){
            if (arr[index] > max){
                max = arr[index];
            }
        }
        console.log('数组最大值为' + max);
</script>
```

- 数组转换为字符串

数组.join（''）

```
<script>
        var arr = ['red', 'green', 'blue', 'pink'];
        var str = arr.join('');
        console.log(str);
</script>
```

字符串相加

```
var arr = ['red', 'green', 'blue', 'pink'];
var str = '';
for (var i = 0; i < arr.length; i++) {
    str += arr[i];
}
console.log(str);
```

- 数组元素相加为分隔符字符串

字符串相加

```
<script>
       var arr = ['red','green','blue','pink'];
       var str = '';
       for (var index = 0; index < arr.length; index++) {
            str += arr[index] + '|';
        }
        console.log(str); 
</script>
```

- 筛选数组

遍历筛选

```
var arr = [2,0,6,1,77,0,52,0,25,7];
        var newArr = [];
        for (let index = 0,j = 0; index < arr.length; index++) {
            if (arr[index] >= 10) {
                newArr[j] = arr[index];
                j++;
            }
        }
        console.log(newArr);
var arr = [2,0,6,1,77,0,52,0,25,7];
        var newArr = [];
        for (let index = 0,j = 0; index < arr.length; index++) {
            if (arr[index] >= 10) {
                newArr[newArr.length] = arr[i];     //妙啊
            }
        }
        console.log(newArr);
```

- 筛选删除指定数组元素

```
var arr = [2, 0, 6, 1, 77, 0, 52, 0, 25, 7];
var newArr = [];    //定义空的新数组，初始长度值为0
for (var i=0; i<arr.length; i++){
  if (arr[i] != 0){
    newArr[newArr.length] = arr[i];     //以长度值为下标索引
  }
}
console.log(newArr);
```

- 数组去重

数组[‘c’, ‘a’, ‘z’, ‘a’, ‘x’, ‘a’, ‘x’, ‘c’, ‘b’]，要求去除数组中重复的元素

```
    var arr = ['c','a','z','a','x','a','x','c','b'];
    var newArr = [];
    for (var i = 0; i < arr.length; i++)
    {
        var find = true;    //标志变量
        for (var j = i+1; j < arr.length; j++)
        {
            if (arr[i] == arr[j])
                find = false;
        }
        if (find)
            newArr[newArr.length] = arr[i];
    }
    console.log(newArr);
```

- 翻转数组

```
        var arr = ['red', 'green', 'blue', 'pink', 'purple'];
        var newArr = [];
        for (var index = arr.length-1; index >= 0; index--){
            newArr[newArr.length] = arr[index];
        }
        console.log(newArr);
```

- 数组冒泡排序

冒泡排序：是一种算法，把一系列的数据按照一定的顺序进行排列显示(从小到大或从大到小）

```
                var arr = [5,8,4,2,1];
        var temp;
        for (var i = 0; i<arr.length; i++){
            for (var j = i+1; j<arr.length; j++){
                if(arr[i] > arr[j]){
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        console.log(arr);
```

## 函数

- 利用函数计算1-100之间的累加和 

```
            var sum = 0;
       function multiply (x,y) {
           for (; x <= y; x++){
               sum += x;
           }
           return sum;
       }
       var sum = multiply(1,100);
       console.log(sum)
```

- 利用函数求任意俩数之和

```
           var sum;
       function add(x,y) {
            sum = x + y;
            alert(sum);
       }
       add(1,3);
```

- 利用函数求任意俩数最大值

```
                function maxize (x,y){
            if (x<y){
                x = y;
                return x;
            }
        }
        alert(maxize (2, 4.2));
```

- 利用函数求任意一个数组中的最大值

```
                function maxize (arr){
            var maxNum = arr[0];
            for (var i = 0; i<arr.length; i++){
                if (maxNum < arr[i]){
                    maxNum = arr[i];
                }
            }
            return maxNum;
        }
        console.log(maxize([15,2,99,101,67,77]));
```

- 创建一个函数，实现对用户输入的两个数之间的加减乘除运算，并将结果返回

Number函数将字符串转换为数值

```
                var a = Number(prompt('请输入第一个数',0));    //将弹出的输入框内返回的字符串强转为数值
        var b = Number(prompt('请输入第二个数',0));
        function compute (x,y){
            return [x + y,x - y,x * y,x / y];
        }
        alert(compute(a,b));
```

- 利用函数求任意个数的最大值

arguments

```
                function maxize () {        //arguments，不需要形参
            var maxNum = arguments[0];
            for (var i = 0; i<arguments.length; i++){
                if (maxNum < arguments[i]) {
                    maxNum = arguments[i];
                }
            }
        return maxNum;
        }
        console.log(maxize(3,5,8,0,6));
```

- 利用函数封装方式，翻转任意一个数组 

不用arguments：类数组，把参数作为一个元素存入

![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1608031245503-491ec9d6-681f-49bb-b4d2-93f18893dfa2.png)![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1608031260648-92c83f2d-0924-4f78-86ae-29004ca5db32.png)

```
                function reverse (arr) {
            var newArr = [];
            for (var i = arr.length - 1; i >= 0; i--) {
                newArr[newArr.length] = arr[i]
            }
        return newArr;
        }
        console.log(reverse([1,3,5,7,9]));
```

- 利用函数封装方式，对数组排序 -- 冒泡排序 

```
function vary (arr) {
  var temp;
  // 相邻俩元素比较，将大的排在前位置
  for (var i = 0; i < arr.length - 1; i++){
    for (var j = i+1; j < arr.length; j++){
      if(arr[i] < arr[j]){  //大到小排序
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }
    }
  }
  return arr;
}
console.log(vary([4,5,8,3,2,1,8]));
```

- 判断闰年

```
    function judge (year) {
        if(year%4 == 0 && (year%100 != 0 || year%400 == 0)){
            return true;
        }
        else{
            return false;
        }
    }
    if(judge(2020))
        console.log('闰年！');
```

- 用户输入年份，输出当前年份2月份的天数 

函数内调用另一函数

```
function judgeYear (year)
    {
        if(year%4 == 0 && (year%100 != 0 || year%400 == 0)){
            return true;
        }
        else{
            return false;
        }
    }
    function judgeDay (year) 
    {
        var day;
        judgeYear (year);   //调用判断闰年函数
        if(judgeYear(year))
            day = 29;
        else
            day = 28;
    alert(year + '年二月份有' + day + '天');
    }
    judgeDay (2023);
```

- 作用域

```
var a = 1;
function fn1() {
    var a = 2;
    var b = '22';
    fn2();
    function fn2() {
        var a = 3;
        fn3();
        function fn3() {
            var a = 4;
            console.log(a); //a的值 ?
            console.log(b); //b的值 ?
        }
    }
}
fn1();  //输出结果:4;22
```

## 对象

### 创建对象

- 字面量形式创建对象

请用对象字面量的形式创建一个名字为可可的狗对象。

 具体信息如下： 姓名：可可  

类型(type)：阿拉斯加犬    

 年龄：5岁

技能：汪汪汪(bark) ，演电影 (showFilm)

```
   var keKe = 
    {
        name : '可可',
        type : '阿拉斯加犬',
        age : 5,
        bark : function () {
            alert('汪汪！');
        },
        showFilm : function () {
            alert('我会演电影！');
        }
    }
    console.log(keKe);
    keKe.showFilm();
    keKe.bark();    //调用keKe的bark()方法
```

![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1608044260913-518179a6-af09-4eaa-94b3-defbda6af1bf.png)

- new Object形式创建对象

```
        var obj = new Object();
    obj.name = 'ming';
    obj.gender = 'male';
    obj.age = 19;
    obj.skill = function () {
        alert('分身！');
    }
    obj.skill();    //调用obj的skill()方法
```

- 利用构造函数创建

```
    function Hero (name, gender) {
        this.name = name;
        this.gender = gender;
        this.attack = function() {
            alert(this.name + '是' + gender);
        }
    }
    var nianPo = new Hero('nianPo', 'male');
    var houYi = new Hero('houYi', 'male');
    nianPo.attack();
    houYi.attack();
```

### 内置对象

- 程序随机生成一个 1~ 10 之间的整数，并让用户输入一个数字，

1. 如果大于该数字，就提示，数字大了，继续猜；
2. 如果小于该数字，就提示数字小了，继续猜；
3. 如果等于该数字，就提示猜对了， 结束程序

```
        num = Math.ceil((Math.random())*10 + 1);    //Math.ceil()向上取整，使产生的随机数为整数
    guess();
    function guess () {
        guessNum = prompt('输入一个1-10之间的数字');
        if (guessNum == num) {
            alert('猜对啦！');
            return 0;
        }
        else if(guessNum >= num) {
            alert('数字大啦');
            return guess ();
        }
        else 
        {
            alert('数字小啦');
            return guess ();    
        }
    }
```

- 输出当前日期

```
        var myDate = new Date();    //实例化
    year = myDate.getFullYear();
    month = myDate.getMonth();
    date = myDate.getDate();
    day = myDate.getDay();
    console.log(myDate);    //输出格式：Wed Dec 16 2020 22:04:38 GMT+0800 (中国标准时间)
    console.log(year + '年' + month + '月' + date + ' ' + day);
```

- 返回字符位置

查找字符串"abcoefoxyozzopp"中所有o出现的位置以及次数 

```
        var str = 'abcoefoxyozzopp';
    var count = 0;
    for (var i = 0; i < str.length; i++)    //遍历字符串
    {
        if (str[i] == 'o')
        {
            console.log(i);
            count++;
        }
    }
    console.log("'o'出现了" + count + '次');
    var str = 'abcoefoxyozzopp';
    var count = 0;
    var j = 0;
    for (var i = 0; i < str.length; i++){
        var find = str.indexOf('o',j);  //因为indexOf只返回找到的第一个值，直接从上一个o出现的位置之后一个开始查找
        if (find == -1){
            break;
        }
        j = find;
        j++;
        console.log(find);
        count++;
    }
    console.log("'o'总共出现了" + count + '次');
```

- 判断一个字符串 'abcoefoxyozzopp' 中出现次数最多的字符，并统计其次数

- - - 函数

```
    var str = 'abcoefoxyozzopp';
    var count = 0;
    function countTimes(str,char){  //函数功能：输出str里char出现的次数
        for (var i = 0; i < str.length; i++){
            if (char == str[i])
                count++;
        }
        console.log(char + '共出现了' + count + '次');
    }
    countTimes(str,'a');
    countTimes(str,'b');
    countTimes(str,'c');
    countTimes(str,'o');
    countTimes(str,'e');
    countTimes(str,'f');
    countTimes(str,'x');
    countTimes(str,'y');
    countTimes(str,'p');
```

- - - 对象属性

```
    var str = 'abcoefoxyozzopp';
    var obj = {};
    for (var i = 0; i < str.length; i++) {
       var char = str.charAt(i);    //char：属性名
       if (obj[char]) {
            obj[char]++;    //该属性值（字符出现次数）加一
       }
       else     
            obj[char] = 1;
    }
    console.log(obj);
    var maxTimes = 0
    for (var k in obj) {    //遍历对象属性值
        if (maxTimes < obj[k]) {    //比较次数
            maxTimes = obj[k];
            mostChar = k;   //k指属性名
        }
    }
    console.log('出现最多的字符是' + mostChar);
```

## DOM

### 事件基础

- 页面中有一个按钮，当 鼠标点击按钮的时候，弹出“你好”警示框

- - getElementsByTagName()方法

```
<body>
    <button>点我</button>
</body>
</html>
<script>
    var btn = document.getElementsByTagName('button');
    btn[0].onclick = function () {  //注意getElementsByTagName()这种方法获取得到的是数组，单独使用需查询
        alert('你好吗');
    }
</script>
```

- - getElementById()方法  优

```
<body>
    <button id="btn">点我</button>
</body>
</html>
<script>
    var btn = document.getElementById('btn');
    btn.onclick = function () {
        alert('你好吗');
    }
</script>
```

### 操作元素

- 分时显示不同图片,显示不同问候语 

```
    <div id="greet">
        <p>问候</p>
        <img>
    </div>
</body>
</html>
<script>
    var now = new Date(); 
    hour = now.getHours();
    var greet = document.getElementsByTagName('p');
    var img = document.getElementsByTagName('img');
    if (hour < 12 && hour > 0) {
        greet[0].innerText = '早上好'; //通过标签名得到的是集合，单独操作元素需索引！
        img[0].src = "早安.png";  //对img的src属性设置
    }
    else if (hour >= 12 && hour <18) {
        greet[0].innerText = '中午好';
        img[0].src = "午安.png";
    }
    else {
        greet[0].innerText = '晚上好';
        img[0].src = "晚安.png";
    }
```

- 显示密码 

鼠标点击事件；修改表单元素属性*type、value、checked、selected、disabled* 

```
    var eye = document.getElementsByTagName('img')[0];  //眼睛
    var password = document.getElementsByTagName('input')[1];   //输入框
    var flag = 0;   //初始为隐藏密码状态
    eye.onclick = function () {
        if (flag == 0) {
            password.type = 'text';
            flag = 1;   //表示当前是显示密码状态
        }
        else {
            password.type = 'password'
            flag = 0;   //表示当前是隐藏密码状态
        }
    }
```

- 点击关闭二维码

```
    var btn = document.getElementById('btn');
    btn.onclick = function () { //注册事件：程序处理
        document.getElementById('QR').style.display = 'none';   //记得''号
    }
```

- 循环精灵图背景

利用 for 循环设置一组元素的精灵图背景 

![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1608297177696-dfe0b6d1-7ab1-49b1-b04d-789ce412a3f6.png)

- 显示隐藏文本框内容 

当鼠标点击文本框时，里面的默认文字隐藏，当鼠标离开文本框时，里面的文字显示

```
    var tip = document.getElementById('input');
    tip.onfocus = function () {     //onfocus获得焦点时事件
        tip.placeholder = '';       //若不设置，本应输入内容后占位符才消失；现在输不输只要点了就消失
    }
    tip.onblur = function () {      //onblur失去焦点时事件
        if (tip.placeholder == '') {
            tip.placeholder = '手机';
        }
    }
```

- 密码框格式提示错误信息 

用户如果离开密码框，里面输入个数不是6~16，则提示错误信息，否则提示输入正确信息 

```
    <style type="text/css">
        .wrong {
        color: red;
        }
    </style>

<body>    
    <p>*设置密码：</p>
    <input id='input' type="password">
    <p class='message'>请输入6-16位密码</p>
</body>

<script>    
    var input = document.getElementById('input');
    var message = document.querySelector('.message');
    input.onblur = function () {
        if (this.value.length < 6 || this.value.length > 16) {  //通过input.value值长度判断
            message.className = 'wrong';    //改变类名，间接设置为该类名样式（当需设置的样式过多时采用此方法
        }
        else {
            message.className = 'message';  //改回去，否则第二次才输时字体还是红色
            message.innerHTML = '可！';
        }
    }
```

- 百度换肤

4个小图片中任选一个将背景图片切换成该图

```
    var imgs = document.getElementsByTagName('img');
    for (var i = 0; i < imgs.length ;i++) {
        imgs[i].onclick = function () {
            document.getElementById('content').style.backgroundImage = 'url(' + this.src + ')';
        }
    }
```

- 表单全选、取消全选  （未完待续  ？？

```
    var checkboxs = document.getElementsByTagName('input');
    var flag = '未勾选';
    checkboxs[0].onclick = function () {
        console.log(flag);
        if (flag == '未勾选') {
            for (var i = 1; i < checkboxs.length; i++) {
                if (! checkboxs[i].checked) {
                    checkboxs[i].checked = true;
                }
            }
            flag = '已勾选';
        }
        else {
            for (var i = 1; i < checkboxs.length; i++) {
                if (checkboxs[i].checked) {
                    checkboxs[i].checked = false;
                }
            }
            flag = '未勾选';
        }
        console.log(flag);
    } 
```

-  当鼠标点击上面相应的选项卡（**tab**），下面页面的**内容也随之而改变**

[参考链接](https://www.cnblogs.com/nyw1983/p/12004172.html)

```
        var tab_lists = document.querySelector('.tab_list');
    var lis = document.querySelectorAll('li');
    var items = document.querySelectorAll('.item');
    for(var i = 0; i < lis.length; i++) {
        lis[i].setAttribute('index',i);
        lis[i].onclick = function() {
            for(var i = 0;i < lis.length; i++) {
                lis[i].className = '';  //清楚tab栏中其余栏样式
            }
            this.className = 'current';  //设置被点击[i]tab栏样式
            var index = this.getAttribute('index')
            console.log(index); //打印该对象（栏）对应属性值
            for(var i = 0; i < items.length; i++) {
                items[i].style.display = 'none';    //先隐藏所有的item
            }
            
            items[index].style.display = 'block';   //显示该对应item内容
        }
    }
```

### 节点操作  

- 下拉菜单

- 发布留言案例

核心思路： 点击按钮之后，就动态创建一个li，添加到ul 里面

创建li 的同时，把文本域里面的值通过li.innerHTML 赋值给 li，多添加一个删除的链接a标签

如果想要新的留言后面显示就用 appendChild 如果想要前面显示就用insertBefore ；

需要把所有的链接获取过来，

当我们点击当前的链接的时候，删除当前链接所在的li 

阻止链接跳转需要添加 javascript:void(0); 或者  javascript:; 

```
   <body>
    <textarea id="text" cols="30" rows="10"></textarea>
    <button id="btn">留言</button>
    <ul id="board">留言板</ul>

    </body>
<script>
        var text = document.getElementById('text');
    var btn = document.getElementById('btn');
    var board = document.getElementById('board');
    btn.onclick = function () {
        if(text.value == '') {
            alert('请输入留言内容后提交')
        }
        else {
            var li = document.createElement('li');  //创建li元素
            li.innerHTML = text.value + "<a href='javascript:;'>删除</a>";  
//对li内容进行赋值文本域上输入的内容,因为含HTML标签所以选用innerHTML; href='javascript:;' 用来防止页面跳转
            text.value = '';    //留言后清空文本域                     
            board.insertBefore(li,board.children[0])    //插入到指定节点的前面
            var as = document.querySelectorAll('a') //查找所有a标签；
            for(let i = 0; i <as.length; i++) {     //如果用var声明，下面的as[i]必须改为this
                as[i].onclick = function() {
                    board.removeChild(as[i].parentNode); //删除a的父节点 也就是对应的li
                                //或 (board.children[i])
                        //此情况不能 (board.childNodes[i])  因为childNodes包含其他节点(此处是留言板这个文本节点)
                 }
            }
        }
    }
```

- 动态生成表格 

因为里面的数据都是动态的，我们需要**js 动态生成**

数据我们采取**对象形式存储**

所有的数据都是放到tbody里面的行里面因为行很多，我们需要循环创建多个行（对应多少人） 

每个行里面又有很多单元格（对应里面的数据），我们还继续使用循环创建多个单元格，

并且把数据存入里面（双重for循环）

 最后一列单元格是删除，需要**单独创建**单元格。

 最后添加删除操作，单击删除，可以删除当前行

```
    <table>
        <thead>
            <tr>
                <th>姓名</th>
                <th>科目</th>
                <th>成绩</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody></tbody>
    </table>
<script>
    var datas = [{  //先创建好所需数据
            name:'a',
            subject:'JS',
            score:100
        },{
            name:'b',
            subject:'JS',
            score:66
        }
    ];
    var tbody = document.querySelector('tbody');    
    console.log(datas.length)
//循环创建行数，多少个数据就创建多少行
    for (var i = 0; i < datas.length; i++) {   
        var tr = document.createElement('tr');
        tbody.appendChild(tr);  //创建行
//行里创建数据的单元格
        for (var k in datas[i]) {
            var td = document.createElement('td'); 
            td.innerHTML = datas[i][k]; //对象里的属性值给td；i为第几个对象，k为第几个属性
            tr.appendChild(td); //td加到tr的子节点
        }
//创建删除俩字的单元格
        var td = document.createElement('td');
        td.innerHTML = "<a href = 'javascript:;'>删除</a>"
        tr.append(td);
    }
//删除操作
    var as = document.querySelectorAll('a');
    for (var i = 0; i < as.length; i++) {
        as[i].onclick = function() {
            tbody.removeChild(this.parentNode.parentNode);  //a标签父节点的父节点就是tr行
        }
    }
```

![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1608617599088-7249c029-8d44-4b7b-9bc1-22d5fb8e13c6.png)