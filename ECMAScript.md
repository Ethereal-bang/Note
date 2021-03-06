

# 目录

## 索引

[TOC]

## 主要内容

- 变量
- 函数
- 数组
- 字符串
- 面向对象
- Promise
- generator
- 模块化



## 初步自检

+ EScript和JavaScript区别和联系
+ 基本类型的扩展
+ 新的数据类型 - Map/Set/WeakMap/WeakSet、Symbol
+ Proxy的使用和应用场景
+ 如何处理异步请求
+ 实现面向对象的语法糖
+ Module模块化
+ 类和函数的扩展
+ 函数式编程
+ 装饰器和迭代器



# let 和 const命令

## let 命令

作用域：所在的代码块



### 基本用法

**for循环**：let 声明的变量 `i `，当前的` i `只在本轮循环有效，所以每一次循环的 i 其实都是一个新的变量。



for循环含俩个作用域，设置循环变量的部分是一个父作用域，循环体内部是一个单独的子作用域。

```javascript
for (let i = 0; i < 3; i++) {
  let i = 'abc';
  console.log(i);
}
// abc
// abc
// abc
```

上面代码正常运行，表面函数内部变量 `i` 与循环变量 `i` 不在同一个作用域，有各自单独的作用域



### 不存在变量提升



**`var `**命令会发生变量提升现象，即变量可在声明前使用。



为了纠正这种现象，**`let`**命令改变了语法行为，它所声明的变量一定要在声明后使用，否则报错

```javascript
// var 的情况
console.log(foo); // 输出undefined
var foo = 2;

// let 的情况
console.log(bar); // 报错ReferenceError
let bar = 2;
```

上例中，变量`foo`用`var`声明，发生变量提升。即脚本开始运行时，变量`foo`已经存在但没有值，输出`undefined`。

>  ​	在计算机程序设计与计算机技术的相关文档中，术语foobar是一个常见的无名氏化名，常被作为“伪变量”使用。
>
>  ​	单词“foobar”或分离的“foo”与“bar”常出现于程序设计的案例中，如同Hello World程序一样，它们常被用于向学习者介绍某种程序语言。“foo”常被作为函数／方法的名称，而“bar”则常被用作变量名。



### 暂时性死区



只要块级作用域内存在`let`命令，它所声明的变量就“**绑定**”（binding）这个区域，不再受外部的影响。



```javascript
var tmp = 123;

if (true) {
  tmp = 'abc'; // ReferenceError
  let tmp;
}
```

上面代码中，存在全局变量`tmp`，但块级作用域内`let`又声明了一个局部变量`tmp`，绑定这个块级作用域，所以在`let`声明前，对`tmp`赋值会报错



```javascript
if (true) {
  // TDZ开始
  tmp = 'abc'; // ReferenceError
  console.log(tmp); // ReferenceError

  let tmp; // TDZ结束
  console.log(tmp); // undefined

  tmp = 123;	// 变量声明前都是暂时性死区
  console.log(tmp); // 123
}
```



总之，代码块内，使用`let`声明变量前，该变量都是不可用的，这在语法上，称为“**暂时性死区**”（*temporal dead zone，简称 TDZ）*



暂时性死区也意味着**`typeof`**不再是百分百安全的操作

```javascript
typeof x; // ReferenceError
let x;
```

变量`x`使用`let`声明，所以声明前只要用到该变量就会报错

作为比较，如果一个变量根本没被声明，使用**`typeof`**反而不会报错

```javascript
typeof undeclared_variable // "undefined"
```

`undeclared_variable`是一个不存在的变量名，结果返回“undefined”。所以，在没有`let`之前，`typeof`运算符是百分之百安全的，永远不会报错。现在这一点不成立了。这样的设计是为了让大家养成良好的编程习惯，变量一定要在声明之后使用，否则就报错。



较隐蔽的死区



```javascript
function bar(x = y, y = 2) {
  return [x, y];
}

bar(); // 报错
```

调用`bar`函数之所以报错（某些实现可能不报错），是因为参数`x`默认值等于另一个参数`y`，而此时`y`还没有声明，属于死区



```javascript
function bar(x = 2, y = x) {
  return [x, y];
}
bar(); // [2, 2]
```

此时`x`已经声明了，所以不报错



下面的代码也会报错，与`var`的行为不同

```javascript
// 不报错
var x = x;

// 报错
let x = x;
// ReferenceError: x is not defined
```

上面代码报错，也是因为暂时性死区。使用`let`声明变量时，只要变量在还没有声明完成前使用，就会报错。上面这行就属于这个情况，在变量`x`的声明语句还没有执行完成前，就去取`x`的值，导致报错“x 未定义”。



ES6 规定暂时性死区和`let`、`const`语句不出现变量提升，主要是为了减少运行时错误，防止在变量声明前就使用这个变量，从而导致意料之外的行为。这样的错误在 ES5 是很常见的，现在有了这种规定，避免此类错误就很容易了。



总之，暂时性死区的本质就是，**只要一进入当前作用域，所要使用的变量就已经存在了，但是不可获取**，只有等到声明变量的那一行代码出现，才可以获取和使用该变量。



### 不允许重复声明



`let`不允许在**相同作用域重复声明**同一变量

```javascript
// 报错
function func() {
  let a = 10;
  var a = 1;
}

// 报错
function func() {
  let a = 10;
  let a = 1;
}
```



### 块级作用域

#### 块级作用域存在意义

ES5 只有全局作用域和函数作用域，没有块级作用域，这带来很多不合理的场景。



- 内层变量可能会**覆盖外层变量**

```javascript
var tmp = new Date();

function f() {
  console.log(tmp);
  if (false) {
    var tmp = 'hello world';
  }
}

f(); // undefined
```

上面代码的原意是，`if`代码块的外部使用外层的`tmp`变量，内部使用内层的`tmp`变量。

但是，函数`f`执行后，输出结果为`undefined`，原因在于**变量提升**，导致内层的`tmp`变量覆盖了外层的`tmp`变量。



- 用来计数的**循环变量泄露**为全局变量

```javascript
var s = 'hello';

for (var i = 0; i < s.length; i++) {
  console.log(s[i]);
}

console.log(i); // 5
```

上面代码中，变量`i`只用来控制循环，但是循环结束后，它并没有消失，泄露成了全局变量。



#### ES6 的块级作用域

`let`实际上为 JavaScript 新增了块级作用域



```javascript
function f1() {
  let n = 5;
  if (true) {
    let n = 10;
  }
  console.log(n); // 5
}
```

上面的函数有两个代码块，都声明了变量`n`，运行后输出 5。这表示**外层代码块不受内层代码块的影响**。如果两次都使用`var`定义变量`n`，最后输出的值才是 10。



内层与外层作用域变量关系

**外层作用域无法读取内层作用域**变量。

```javascript
{{{{
  {let insane = 'Hello World'}
  console.log(insane); // 报错
}}}};
```

内层作用域可以定义外层作用域的同名变量

```javascript
{{{{
  let insane = 'Hello World';
  {let insane = 'Hello World'}
}}}};
```



块级作用域的出现，实际上使得获得广泛应用的立即执行函数表达式（IIFE）不再必要了   

因为立即执行函数的变量不会提升

```javascript
// IIFE 写法
(function () {
  var tmp = ...;
  ...
}());

// 块级作用域写法
{
  let tmp = ...;
  ...
}
```



#### 块级作用域与函数声明



函数能不能在块级作用域之中声明？这是一个相当令人混淆的问题。



**ES5** 规定，函数只能在**顶层作用域**和**函数作用域**之中声明，不能在块级作用域声明。

```javascript
// 情况一
if (true) {
  function f() {}
}

// 情况二
try {
  function f() {}
} catch(e) {
  // ...
}
```

上面两种函数声明，根据 ES5 的规定都是非法的。

但是，浏览器没有遵守这个规定，为了兼容以前的旧代码，还是支持在块级作用域之中声明函数，因此上面两种情况实际都能运行，不会报错。



ES6 引入了块级作用域，明确允许在**块级作用域之中声明函数**

ES6 规定，块级作用域之中，函数声明语句的行为类似于`let`，在块级作用域之外不可引用



```javascript
function f() { console.log('I am outside!'); }

(function () {
  if (false) {
    // 重复声明一次函数f
    function f() { console.log('I am inside!'); }
  }
  f();
}());
```

上面代码在 **ES5** 中运行，会得到`“I am inside!”`，因为在`if`内声明的函数`f`会被**提升**到函数头部，实际运行的代码如下

```javascript
// ES5 环境
function f() { console.log('I am outside!'); }

(function () {
  function f() { console.log('I am inside!'); }
  if (false) {
  }
  f();
}());
```

**ES6** 就完全不一样了，理论上会得到“I am outside!”。因为块级作用域内声明的函数类似于`let`，对作用域之外没有影响。但是，如果你真的在 ES6 浏览器中运行上面的代码，是会报错的，这是为什么呢？



原来，如果改变了块级作用域内声明的函数的处理规则，显然会对老代码产生很大影响。为了**减轻**因此产生的**不兼容问题**，ES6 在[附录 B](http://www.ecma-international.org/ecma-262/6.0/index.html#sec-block-level-function-declarations-web-legacy-compatibility-semantics)里面规定，浏览器的实现可以不遵守上面的规定，有自己的[行为方式](http://stackoverflow.com/questions/31419897/what-are-the-precise-semantics-of-block-level-functions-in-es6)。

- 允许在**块级作用域内声明函数**。
- 函数声明类似于`var`，即会**提升**到**全局作用域**或**函数作用域的头部**。
- 同时，函数声明还会**提升**到所在的**块级作用域的头部**。

注意，上面三条规则只对 ES6 的浏览器实现有效，其他环境的实现不用遵守，还是将块级作用域的函数声明当作`let`处理。



根据这三条规则，在浏览器的 ES6 环境中，**块级作用域内声明的函数**，行为类似于`var`声明的变量。

```javascript
// 浏览器的 ES6 环境
function f() { console.log('I am outside!'); }
(function () {
  if (false) {
    // 重复声明一次函数f
    function f() { console.log('I am inside!'); }
  }
  f();
}());
// Uncaught TypeError: f is not a function
```

上面的代码在符合 ES6 的浏览器中，都会报错，因为实际运行的是下面的代码。  ==?==

```javascript
// 浏览器的 ES6 环境
function f() { console.log('I am outside!'); }
(function () {
  var f = undefined;	// ?
  if (false) {
    function f() { console.log('I am inside!'); }
  }
  f();
}());
// Uncaught TypeError: f is not a function
```



考虑到环境导致的行为差异太大，应该**避免在块级作用域内声明函数**。如果确实需要，也应该写成**函数表达式**，而不是函数声明语句

```javascript
// 函数声明语句
{
  let a = 'secret';
  function f() {
    return a;
  }
}

// 函数表达式
{
  let a = 'secret';
  let f = function () {
    return a;
  };
}
```



另外，还有一个需要注意的地方。ES6 的块级作用域允许声明函数的规则，只在使用大括号的情况下成立，如果没有使用大括号，就会报错

```javascript
// 不报错
'use strict';
if (true) {	// 需要大括号
  function f() {}
}

// 报错
'use strict';
if (true)
  function f() {}
```



## const命令

### 基本用法



`const`声明一个只读的常量。一旦声明，常量的值就不能改变

```javascript
const PI = 3.1415;
PI // 3.1415

PI = 3;
// TypeError: Assignment to constant variable.
```

上面代码表明改变常量的值会报错。



`const`声明的变量不得改变值，这意味着，`const`一旦声明变量，就必须**立即初始化**，不能留到以后赋值

```javascript
const foo;
// SyntaxError: Missing initializer in const declaration
```

上面代码表示，对于`const`来说，只声明不赋值，就会报错。



`const`的**作用域**与`let`命令相同：只在声明所在的**块级作用域**内有效

```javascript
if (true) {
  const MAX = 5;
}
MAX // Uncaught ReferenceError: MAX is not defined
```



**暂时性死区**`const`命令声明的常量也是不提升，同样存在暂时性死区，**只能在声明的位置后面使用**

```javascript
if (true) {
  console.log(MAX); // ReferenceError
  const MAX = 5;
}
```



`const`声明的常量，也与`let`一样**不可重复声明**

```javascript
var message = "Hello!";
let age = 25;
// 以下两行都会报错
const message = "Goodbye!";
const age = 30;
```



### 本质

`const`实际上保证的，并不是变量的值不得改动，而是变量指向的那个内存地址不得改动

对于简单类型的数据（数值、字符串、布尔值），值就保存在变量指向的那个内存地址，因此等同于常量

但对于复合类型的数据（主要是对象和数组），变量指向的内存地址，保存的只是一个指针，`const`只能保证这个指针是固定的，至于它指向的数据结构是不是可变的，就完全不能控制了



因此，将一个**对象**声明为常量必须非常小心。

```javascript
const foo = {};

// 为 foo 添加一个属性，可以成功
foo.prop = 123;
foo.prop // 123

// 将 foo 指向另一个对象，就会报错
foo = {}; // TypeError: "foo" is read-only
```

上面代码中，常量`foo`储存的是一个地址，这个地址指向一个对象。不可变的只是这个地址，即不能把`foo`指向另一个地址，但对象本身是可变的，所以依然可以为其添加新属性。



下面是另一个例子。 **数组**

```javascript
const a = [];
a.push('Hello'); // 可执行
a.length = 0;    // 可执行
a = ['Dave'];    // 报错
```

上面代码中，常量`a`是一个数组，这个数组本身是可写的，但是如果将另一个数组赋值给`a`，就会报错。



如果真的想将**对象冻结**，应该使用**`Object.freeze`**方法

```javascript
const foo = Object.freeze({});	// 这什么写法?

// 常规模式时，下面一行不起作用；
// 严格模式时，该行会报错
foo.prop = 123;
```

上面代码中，常量`foo`指向一个冻结的对象，所以添加新属性不起作用，严格模式时还会报错。



除了将对象本身冻结，对象的属性也应该冻结。下面是一个**将对象彻底冻结**的函数。   ？

```javascript
var constantize = (obj) => {	// 函数表达式
  Object.freeze(obj);
  Object.keys(obj).forEach( (key, i) => {
    if ( typeof obj[key] === 'object' ) {	// 对象的属性是对象
      constantize( obj[key] );
    }
  });
};
```



# ES6 声明变量的六种方法

ES5 只有两种声明变量的方法：`var`命令和`function`命令

ES6 除了添加`let`和`const`命令，后面章节还会提到，另外两种声明变量的方法：`import`命令和`class`命令。所以，ES6 一共有 6 种声明变量的方法。



# 顶层对象

## 顶层对象的属性

**顶层对象**，在浏览器环境指的是`window`对象，在 Node 指的是`global`对象

ES5 之中，**顶层对象的属性与全局变量**是等价的

```javascript
window.a = 1;
a // 1

a = 2;
window.a // 2
```

上面代码中，顶层对象的属性赋值与全局变量的赋值，是同一件事。



顶层对象的属性与全局变量挂钩，被认为是 JavaScript 语言最大的设计败笔之一。这样的设计带来了几个很大的问题，

1. 首先是没法在编译时就**报出变量未声明的错误**，只有运行时才能知道（因为全局变量可能是顶层对象的属性创造的，而属性的创造是动态的）；
2. 其次，程序员很容易**不知不觉地就创建了全局变量**（比如打字出错）；
3. 最后，**顶层对象的属性是到处可以读写**的，这非常不利于模块化编程。
4. 另一方面，`window`对象有**实体含义**，指的是浏览器的窗口对象，顶层对象是一个有实体含义的对象，也是不合适的。



ES6 为了改变这一点，

- 一方面规定，为了保持兼容性，`var`命令和`function`命令声明的全局变量，依旧是顶层对象的属性；
- 另一方面规定，`let`命令、`const`命令、`class`命令声明的全局变量，不属于顶层对象的属性。也就是说，从 ES6 开始，**全局变量将逐步与顶层对象的属性脱钩**。

```javascript
var a = 1;
// 如果在 Node 的 REPL 环境，可以写成 global.a
// 或者采用通用方法，写成 this.a
window.a // 1

let b = 1;
window.b // undefined
```

上面代码中，全局变量`a`由`var`命令声明，所以它是顶层对象的属性；全局变量`b`由`let`命令声明，所以它不是顶层对象的属性，返回`undefined`。



## global 对象   ？



ES5 的顶层对象，本身也是一个问题，因为它在各种实现里面是不统一的

- 浏览器里面，顶层对象是`window`，但 Node 和 Web Worker 没有`window`。
- 浏览器和 Web Worker 里面，`self`也指向顶层对象，但是 Node 没有`self`。
- Node 里面，顶层对象是`global`，但其他环境都不支持。



同一段代码为了能够在各种环境，都能取到顶层对象，现在一般是使用**`this`**变量，但是有局限性

- **全局环境**中，`this`会返回顶层对象。但是，**Node 模块**和 **ES6 模块**中，`this`返回的是当前模块。
- **函数**里面的`this`，如果函数不是作为对象的方法运行，而是单纯作为函数运行，`this`会指向顶层对象。但是，严格模式下，这时`this`会返回`undefined`。
- 不管是严格模式，还是普通模式，`new Function('return this')()`，总是会返回全局对象。但是，如果浏览器用了 CSP（Content Security Policy，内容安全政策），那么`eval`、`new Function`这些方法都可能无法使用。



综上所述，很难找到一种方法，可以在所有情况下，都**取到顶层对象**。下面是两种勉强可以使用的方法。	==?==

```javascript
// 方法一
(typeof window !== 'undefined'	// ?
   ? window   // 三目表达式
   : (typeof process === 'object' &&
      typeof require === 'function' &&
      typeof global === 'object')
     ? global
     : this);

// 方法二
var getGlobal = function () {
  if (typeof self !== 'undefined') { return self; }
  if (typeof window !== 'undefined') { return window; }
  if (typeof global !== 'undefined') { return global; }
  throw new Error('unable to locate global object');
};
```



# 变量的解构赋值



ES6 允许按照一定模式，从数组和对象中提取值，对变量进行赋值，这被称为解构（Destructuring）

以前，为变量赋值，只能直接指定值。

```javascript
let a = 1;
let b = 2;
let c = 3;
```



## 数组的解构赋值

### 数组的解构赋值基本用法

ES6 允许写成下面这样。

```javascript
let [a, b, c] = [1, 2, 3];
```

上面代码表示，可以从数组中提取值，按照对应位置，对变量赋值



本质上，这种写法属于“**模式匹配**”，只要等号两边的模式相同，左边的变量就会被赋予对应的值。下面是一些使用嵌套数组进行解构的例子

```javascript
let [foo, [[bar], baz]] = [1, [[2], 3]];
foo // 1
bar // 2
baz // 3

let [ , , third] = ["foo", "bar", "baz"];
third // "baz"

let [x, , y] = [1, 2, 3];
x // 1
y // 3

let [head, ...tail] = [1, 2, 3, 4];	// ...为数组扩展，收集剩余参数
head // 1
tail // [2, 3, 4]

let [x, y, ...z] = ['a'];	
x // "a"
y // undefined
z // []
```



如果解构不成功，变量的值就等于`undefined`

```javascript
let [foo] = [];
let [bar, foo] = [1];
```

以上两种情况都属于解构不成功，`foo`的值都会等于`undefined`。



另一种情况是**不完全解构**，即等号左边的模式，只匹配一部分的等号右边的数组。这种情况下，解构依然可以成功。	（*左边参数少于右边时*）

```javascript
let [x, y] = [1, 2, 3];
x // 1
y // 2

let [a, [b], d] = [1, [2, 3], 4];
a // 1
b // 2
d // 4
```

上面两个例子，都属于不完全解构，但是可以成功。



如果等号的右边不是**可遍历的结构**（*参见《Iterator》一章*），那么将会报错。

```javascript
// 报错
let [foo] = 1;
let [foo] = false;
let [foo] = NaN;
let [foo] = undefined;
let [foo] = null;
let [foo] = {};
```

上面的语句都会报错，因为等号右边的值，要么转为对象以后不具备 Iterator 接口（前五个表达式），要么本身就不具备 Iterator 接口（最后一个表达式）。



对于 **Set 结构**，也可以使用数组的解构赋值。   ？

> ES6提供了新的数据结构set。它类似于数组，但是成员的值都是唯一的,没有重复的值。

```javascript
let [x, y, z] = new Set(['a', 'b', 'c']);
x // "a"
```



事实上，只要某种数据结构具有 **Iterator** 接口，都可以采用数组形式的解构赋值

遍历**`Array`**可以采用下标循环，遍历**`Map`**和**`Set`**就无法使用下标。为了统一集合类型，ES6标准引入了新的**`iterable`**类型，**`Array`**、**`Map`**和**`Set`**都属于**`iterable`**类型。



```javascript
function* fibs() {
  let a = 0;
  let b = 1;
  while (true) {
    yield a;	// yield 来返回中间结果
    [a, b] = [b, a + b];
  }
}

let [first, second, third, fourth, fifth, sixth] = fibs();	// 依次取每一次yield返回的结果
sixth // 5
```

上面代码中，`fibs`是一个 Generator 函数（参见《Generator 函数》一章），**Generator 函数原生具有 Iterator 接口**。解构赋值会依次从这个接口获取值。



### 默认值   



[ES6中函数的默认值 - 简书](https://www.jianshu.com/p/9c38ad9af25b)



解构赋值允许指定默认值   

```javascript
let [foo = true] = [];
foo // true
let [x, y = 'b'] = ['a']; // x='a', y='b'
let [x, y = 'b'] = ['a', undefined]; // x='a', y='b'
```

注意，ES6 内部使用**严格相等运算符**（`===`），判断一个位置是否有值。

所以，只有当一个数组成员严格等于`undefined`，默认值才会生效



`null`与`undefined`

```javascript
let [x = 1] = [undefined];
x // 1

let [x = 1] = [null];
x // null
```

上面代码中，如果一个数组成员是`null`，默认值就不会生效，因为`null`不严格等于`undefined`。



如果默认值是一个表达式,那么这个表达式是惰性求值的，即只有在用到的时候，才会求值。

```javascript
function f() {
  console.log('aaa');
}

let [x = f()] = [1];
```

上面代码中，因为`x`能取到值，所以函数`f`根本不会执行。上面的代码其实等价于下面的代码。

```javascript
let x;
if ([1][0] === undefined) {
  x = f();
} else {
  x = [1][0];
}
```



默认值可以引用解构赋值的其他变量，但该变量必须已经声明

```javascript
let [x = 1, y = x] = [];     // x=1; y=1
let [x = 1, y = x] = [2];    // x=2; y=2
let [x = 1, y = x] = [1, 2]; // x=1; y=2
let [x = y, y = 1] = [];     // ReferenceError: y is not defined
```

上面最后一个表达式之所以会报错，是因为`x`用`y`做默认值时，`y`还没有声明。



## 对象的解构赋值



解构不仅可以用于数组,还可用于对象。



```javascript
let { foo, bar } = { foo: "aaa", bar: "bbb" };
foo // "aaa"
bar // "bbb"
```



对象的解构与数组有一个重要的不同。  
数组的元素是按次序排列的，变量的取值由它的位置决定；  
而对象的属性没有次序，**变量必须与属性同名**，才能取到正确的值。

```javascript
let { bar, foo } = { foo: "aaa", bar: "bbb" };
foo // "aaa"
bar // "bbb"

let { baz } = { foo: "aaa", bar: "bbb" };
baz // undefined
```

上面代码的第一个例子，等号左边的两个变量的次序，与等号右边两个同名属性的次序不一致，但是对取值完全没有影响。第二个例子的变量没有对应的同名属性，导致取不到值，最后等于`undefined`。



如果**变量名与属性名不一致**，必须写成下面这样

```javascript
let { foo: baz } = { foo: 'aaa', bar: 'bbb' };
baz // "aaa"
 
let { first: x, last: y } = { first: 'hello', last: 'world' };	// 模式匹配
x // 'hello'
y // 'world'
```



这实际上说明，对象的解构赋值是下面形式的简写*（参见《对象的扩展》一章）。*

```javascript
let { foo: foo, bar: bar } = { foo: "aaa", bar: "bbb" };
```

也就是说，对象的解构赋值的**内部机制**，是**先找到同名属性，然后再赋给对应的变量**。真正被赋值的是后者，而不是前者。  
*解释见下例*



```javascript
let { foo: baz } = { foo: "aaa", bar: "bbb" };
baz // "aaa"
foo // error: foo is not defined
```

上面代码中，`foo`是匹配的**模式**，`baz`才是**变量**。真正被赋值的是变量`baz`，而不是模式`foo`。



与数组一样，解构也可用于**嵌套解构的对象**

注意：模式与变量的辨别

```javascript
let obj = {
  p: [
    'Hello',
    { y: 'World' }
  ]
};

let { p: [x, { y }] } = obj;	// p只是模式
x // "Hello"
y // "World"
```

注意，这时`p`是模式，不是变量，因此不会被赋值。

如果`p`也要作为变量赋值，可以写成下面这样。

```javascript
let obj = {
  p: [
    'Hello',
    { y: 'World' }
  ]
};

let { p, p: [x, { y }] } = obj;
x // "Hello"
y // "World"
p // ["Hello", {y: "World"}]
```



下面是另一个例子   

```javascript
const node = {
  loc: {
    start: {
      line: 1,
      column: 5
    }
  }
};
let {
  loc,	// 对loc也进行赋值
  loc: { start },
  loc: {
    start: {
      line 
    }
  }
} = node;
line // 1
loc  // Object {start: Object}
start // Object {line: 1, column: 5}
```

上面代码有三次解构赋值，分别是对`loc`、`start`、`line`三个属性的解构赋值。注意，最后一次对`line`属性的解构赋值之中，只有`line`是变量，`loc`和`start`都是模式，不是变量。



下面是**嵌套赋值**的例子

```javascript
let obj = {};
let arr = [];

({ foo: obj.prop, bar: arr[0] } = { foo: 123, bar: true });	//  外面的圆括号作什么用？

obj // {prop:123}
arr // [true]
```



对象的解构也可以指定**默认**

```javascript
var {x = 3} = {};
x // 3

var {x, y = 5} = {x: 1};
x // 1
y // 5

var {x: y = 3} = {};
y // 3

var {x: y = 3} = {x: 5};
y // 5

var { message: msg = 'Something went wrong' } = {};
msg // "Something went wrong"
```



**默认值产生条件**：对象的属性值严格等于`undefined`

```javascript
var {x = 3} = {x: undefined};
x // 3

var {x = 3} = {x: null};
x // null
```

上面代码中，属性`x`等于`null`，因为`null`与`undefined`不严格相等，所以是个有效的赋值，导致默认值`3`不会生效。                                       



如果解构失败，变量的值等于`undefined`。

```javascript
let {foo} = {bar: 'baz'};
foo // undefined
```



如果解构模式是**嵌套的对象**，而且子对象所在的父属性不存在，那么就会报错   

```javascript
let { foo: bar } = { foo: 1 }
console.log(bar)  // undefined
// 对比
let { foo: {bar} } = { baz: 1 }
console.log(foobar) 	// Uncaught TypeError: Cannot read property 'foobar' of undefined
```

上面代码中，等号左边对象的`foo`属性，对应一个**子对象**。该子对象的`bar`属性，解构时会报错。是因为`foo`这时等于`undefined`，再取子属性就会报错。



如果要将一个**已经声明的变量用于解构赋值**，必须非常小心。

```javascript
// 错误的写法
let x;
{x} = {x: 1};
// SyntaxError: syntax error 语法错误的意思
```

上面代码的写法会报错，因为 JavaScript 引擎会将`{x}`理解成一个代码块，从而发生语法错误。只有**不将大括号写在行首**，避免 JavaScript 将其解释为代码块，才能解决这个问题。

```javascript
// 正确的写法
let x;
({x} = {x: 1});
```

上面代码将整个解构赋值语句，放在一个**圆括号**里面，就可以正确执行。关于圆括号与解构赋值的关系，参见下文。



解构赋值允许等号左边的模式中不放置任何变量名，  
因此可以写出非常古怪的表达式。

```javascript
({} = [true, false]);
({} = 'abc');
({} = []);
```

上面的表达式虽然毫无意义，但是语法是合法的，可以执行。



对象的解构赋值，可以很方便地**将现有对象的方法，赋值到对应的变量**   

```javascript
let { log, sin, cos } = Math;	// 右边为什么省略：因为相当于已经定义了MAth对象，这个语句即将该对象的已存在的同名方法赋值给左边
```

上面代码将`Math`对象的 `log`、`sin`、`cos`三个方法，赋值到对应的变量上，使用起来就会方便很多。



由于数组本质是特殊的对象，因此可以**对数组进行对象属性的解构**

机制是左边对象属性对应右边数组下标。

```javascript
let arr = [1, 2, 3];
let {0 : first, [arr.length - 1] : last} = arr;
first // 1
last // 3
```

上面代码对数组进行对象解构。数组`arr`的`0`键对应的值是`1`，`[arr.length - 1]`就是`2`键，对应的值是`3`。

方括号*`[arr.length - 1]`*这种写法，属于“**属性名表达式**”*（参见《对象的扩展》一章）*	==？==



## 字符串的解构赋值

字符串也可以解构赋值

是因为此时，字符串被转换成了一个类似数组的对象

```javascript
const [a, b, c, d, e] = 'hello';
a // "h"
b // "e"
c // "l"
d // "l"
e // "o"
```



类似数组的对象都有一个`length`属性，因此还可以对这个属性解构赋值。   

```javascript
let {length : len} = 'hello';	// 右边具有length属性
len // 5
```



## 数值和布尔值的解构赋值

解构赋值时，如果等号右边是**数值**和**布尔值**，则会先**转为对象**

```javascript
let {toString: s} = 123;
s === Number.prototype.toString // true

let {toString: s} = true;
s === Boolean.prototype.toString // true
```

上面代码中，数值和布尔值的包装对象都有`toString`属性，因此变量`s`都能取到值。  
即是说，上面代码中等式右边被转为对象后都具有`toString`属性，因此与左边匹配，赋值成功。



解构赋值的规则是，只要等号右边的值不是对象或数组，就先将其转为对象

由于**`undefined`**和**`null`**无法转为对象，所以对它们进行解构赋值，都会报错

```javascript
let { prop: x } = undefined; // TypeError
let { prop: y } = null; // TypeError
```



## 函数参数的解构赋值

```javascript
function add([x, y]){
  return x + y;
}

add([1, 2]); // 3
```

上面代码中，函数`add`的参数表面上是一个数组，但在传入参数的那一刻，数组参数就被解构成变量`x`和`y`。    
对于函数内部的代码来说，它们能感受到的参数就是`x`和`y`。



函数参数解构赋值与 **map() **的配合运用。

下面是另一个例子。   

```javascript
[[1, 2], [3, 4]].map( ([a, b]) => a + b );
// [3, 7]

[ [1, 2], [3, 4], [5, 6] ].map( ([a, b]) => a+b );
// [3, 7, 11]
```

> map() 方法创建一个新数组，其结果是**该数组中的每个元素**是调用一次提供的函数后的返回值



函数参数的解构也可以使用**默认值	**	==？==

```javascript
function move( {x = 0, y = 0} = {} ) {	// 对象的解构赋值
  return [x, y];
}

move({x: 3, y: 8}); // [3, 8]
move({x: 3}); // [3, 0]
move({}); // [0, 0]
move(); // [0, 0]
```

上面代码中，函数`move`参数是一个对象，通过对这个对象进行解构，得到变量`x`和`y`的值。如果解构失败，`x`和`y`等于默认值。

注意，下面的写法会得到不一样的结果。

```javascript
function move({x, y} = { x: 0, y: 0 }) {
  return [x, y];
}

move({x: 3, y: 8}); // [3, 8]
move({x: 3}); // [3, undefined]
move({}); // [undefined, undefined]
move(); // [0, 0]
```

上面代码是为函数`move`的参数指定默认值，而不是为变量`x`和`y`指定默认值，所以会得到与前一种写法不同的结果。



`undefined`就会触发函数参数的默认值。

```javascript
[1, undefined, 3].map((x = 'yes') => x);
// [ 1, 'yes', 3 ]
```



## 圆括号问题	==？==

<span style='font-size: 13px'>可没事干嘛要写圆括号呢==？==</span>

解构赋值虽然很方便，但是解析起来并不容易。对于编译器来说，一个式子到底是模式，还是表达式，没有办法从一开始就知道，必须解析到（或解析不到）等号才能知道。

由此带来的问题是，如果模式中出现圆括号怎么处理。ES6 的规则是，只要有可能导致解构的歧义，就不得使用圆括号。

但是，这条规则实际上不那么容易辨别，处理起来相当麻烦。因此，建议只要有可能，就不要在模式中放置圆括号。



### 不能使用圆括号的情况	



以下三种解构赋值不得使用圆括号

- **变量声明语句**

```javascript
// 全部报错
let [(a)] = [1];

let {x: (c)} = {};
let ({x: c}) = {};
let {(x: c)} = {};
let {(x): c} = {};

let { o: ({ p: p }) } = { o: { p: 2 } };
```

上面 6 个语句都会报错，因为它们都是变量声明语句，模式不能使用圆括号。



- **函数参数**

函数参数也属于变量声明，因此不能带有圆括号

```
// 报错
function f([(z)]) { return z; }
// 报错
function f([z,(x)]) { return x; }
```



- **赋值语句的模式**



```
// 全部报错
({ p: a }) = { p: 42 };
([a]) = [5];
```

上面代码将整个模式放在圆括号之中，导致报错。



```
// 报错
[({ p: a }), { x: c }] = [{}, {}];
```

上面代码将一部分模式放在圆括号之中，导致报错。



### 可以使用圆括号的情况



可以使用圆括号的情况只有一种：**赋值语句的非模式部分**，可以使用圆括号。



```
[(b)] = [3]; // 正确
({ p: (d) } = {}); // 正确
[(parseInt.prop)] = [3]; // 正确
```

上面三行语句都可以正确执行，因为首先它们都是赋值语句，而不是声明语句；其次它们的圆括号都不属于模式的一部分。

第一行语句中，**模式**是取数组的第一个成员，跟圆括号无关；第二行语句中，模式是`p`，而不是`d`；第三行语句与第一行语句的性质一致。



## 用途

变量的解构赋值用途很多



+ <span style='font-size:18px'>交换变量的值</span>

```javascript
let x = 1;
let y = 2;

[x, y] = [y, x];
```

上面代码交换变量`x`和`y`的值，这样的写法不仅简洁，而且易读，语义非常清晰。



- <span style='font-size:18px'>从函数返回多个值</span>

函数只能返回一个值，如果要返回多个值，只能将它们放在数组或对象里返回。有了解构赋值，取出这些值就非常方便。

```
// 返回一个数组
function example() {
  return [1, 2, 3];
}
let [a, b, c] = example();

// 返回一个对象
function example() {
  return {
    foo: 1,
    bar: 2
  };
}
let { foo, bar } = example();
```



- <span style='font-size:18px'>定义函数参数</span>

解构赋值可以方便地将一组参数与变量名对应起来。   

```javascript
// 参数是一组有次序的值
function f([x, y, z]) { ... }
f([1, 2, 3]);

// 参数是一组无次序的值
function f({x, y, z}) { ... }
f({z: 3, y: 2, x: 1});
```



- <span style='font-size: 18px'>提取 JSON对象中数据</span>

解构赋值对提取 JSON 对象中的数据，尤其有用。

```javascript
let jsonData = {
  id: 42,
  status: "OK",
  data: [867, 5309]
};

let { id, status, data: number } = jsonData;	// 此时data只是模式，不会被赋值

console.log(id, status, number);
// 42, "OK", [867, 5309]
```



- <span style='font-size: 18px'>函数参数的默认值</span>

    下面的例子。==？==

```javascript
jQuery.ajax = function (url, {
  async = true,
  beforeSend = function () {},
  cache = true,
  complete = function () {},
  crossDomain = false,
  global = true,
  // ... more config
} = {}) {
  // ... do stuff
};
```

指定参数的默认值，就避免了在函数体内部再写`var foo = config.foo || 'default foo';`这样的语句。



+ <span style='font-size: 18px'>遍历 Map 结构</span>

> Map 类似于对象，也是键值对的集合，但键的范围包括各种类型的值 *包括对象*

任何部署了 Iterator 接口的对象，都可以用`for...of`循环遍历。Map 结构原生支持 Iterator 接口，配合变量的解构赋值，获取键名和键值就非常方便。

```javascript
const map = new Map();	// 定义一个map结构变量
map.set('first', 'hello');
map.set('second', 'world');

for (let [key, value] of map) {	// 这里用到解构赋值？
  console.log(key + " is " + value);
}
// first is hello
// second is world
```



如果只想获取键名，或者只想获取键值，可以写成下面这样。	// ==？==

```javascript
// 获取键名
for (let [key] of map) {
  // ...
}

// 获取键值
for (let [,value] of map) {
  // ...
}
```



+ <span style='font-size: 18px'>输入模块的指定方法</span>

加载模块时，往往需要**指定输入哪些方法**。==？== 解构赋值使得输入语句非常清晰。

```javascript
const { SourceMapConsumer, SourceNode } = require("source--map");
```



# 字符串的扩展	==？==

ES6 加强了对 Unicode 的支持，并且扩展了字符串对象。

[---字符串的扩展·语雀---](https://cn.bing.com/?FORM=Z9FD1)



## 字符的 Unicode 表示法   ==？==

> <span style='font-size: 18px'>字符编码、Unicode 背景</span>
>
> 字符串比较特殊的还有一个编码问题。  
> 因为计算机只能处理数字，如果想处理文本则必须先把文本转换为数字才能处理。
>
> 由于计算机是美国人发明的，因此最早只有 127 个字符被编码到计算机里，即大小写字母、数字、一些符号，这个编码表被称为 **ASCII **编码。比如大写字母`A`的编码是 65，`a`的编码是 122.
>
> 但是要处理中文显然一个字节是不够的，至少需要俩个字节，而且不能与 ASCII 编码冲突。  
> 所以中国制定了 GB2312 编码，用来把中文编进去。
>
> 但多语言混合的文本中，显示出来就会有乱码。  
> 因此， **Unicode **字符集应运而生，把所有语言都统一到一套编码里，这样就不会再有乱码问题了。
>
> 新的问题又出现了：如果统一成 Unicode 编码，乱码问题消失了。但如果写的文本全是英文的话，用 Unicode 编码比 ASCII编码需要多一倍的存储空间。
>
> 所以本着节约的精神，又出现了把 Unicode 编码转化为“可变长编码”的 **UTF-8 **编码。如果要传输的文本包含大量英文字符，用 UTF-8 编码就能节省空间。



JavaScript 允许采用`\uxxxx`形式表示一个字符，  
其中`xxxx`表示字符的 **Unicode 码点**==？==

```javascript
"\u0061"
// "a"
```



## codePointAt()   ?

JavaScript 内部，字符以 UTF-16 ?的格式储存，每个字符固定为`2`个字节。对于那些需要`4`个字节储存的字符（Unicode 码点大于`0xFFFF`的字符），JavaScript 会认为它们是两个字符。



## String.fromCodePoint()   ?

ES5 提供`String.fromCharCode`方法，用于从码点返回对应字符，但是这个方法不能识别 32 位的 UTF-16 字符（Unicode 编号大于`0xFFFF`）。



## 字符串的遍历器接口   

ES6 为字符串添加了**遍历器接口**（详见《Iterator》一章），  
使得字符串可以被`for...of`循环遍历

```javascript
for (let codePoint of 'foo') {
  console.log(codePoint)
}
// "f"
// "o"
// "o"
```

 

除了遍历字符串，这个遍历器最大的优点是可以识别大于`0xFFFF`的码点，传统的`for`循环无法识别这样的码点。

## 俩个新方法

### startsWith

### endsWith

用法与`startsWith`类似

```
let str = '1.jpg';

if(str.endsWith('txt')) 
  console.log('文本文件');
 else if(str.endsWith('jpg')) 
   console.log('JPG图片');
 else
   console.log('其他');
```



## at()   ?

ES5 对字符串对象提供`charAt`方法，返回字符串给定位置的字符。该方法不能识别码点?大于`0xFFFF`的字符。



```
'abc'.charAt(0) // "a"
'𠮷'.charAt(0) // "\uD842"
```



## normalize()   ?



## includes(), startsWith(), endsWith()   ?



## repeat()   



`repeat`方法返回一个新字符串，表示将原字符串重复`n`次。



`repeat`参数如果是小数，会被取整



`repeat`参数如果是负数或者`Infinity`，会报错

```
'na'.repeat(Infinity)
// RangeError
'na'.repeat(-1)
// RangeError
```



但是，如果参数是 0 到-1 之间的小数，则等同于 0

这是因为会先进行取整运算。0 到-1 之间的小数，取整以后等于`-0`，`repeat`视同为 0。

```
'na'.repeat(-0.9) // ""
```



参数`NaN`等同于 0   *NaN：not a number*

```
'na'.repeat(NaN) // ""
```



如果`repeat`的参数是字符串，则会先转换成数字

```
'na'.repeat('na') // ""
'na'.repeat('3') // "nanana"
```



## padStart()，padEnd()



ES2017 引入了**字符串补全长度**的功能。如果某个字符串不够指定长度，会在头部或尾部补全。`padStart()`用于头部补全，`padEnd()`用于尾部补全。



```
'x'.padStart(5, 'ab') // 'ababx'
'x'.padStart(4, 'ab') // 'abax'

'x'.padEnd(5, 'ab') // 'xabab'
'x'.padEnd(4, 'ab') // 'xaba'
```

上面代码中，`padStart`和`padEnd`一共接受两个参数，第一个参数用来指定字符串的最小长度，第二个参数是用来补全的字符串。



如果原字符串的长度，**等于或大于指定的最小长度**，则返回原字符串。

```
'xxx'.padStart(2, 'ab') // 'xxx'
'xxx'.padEnd(2, 'ab') // 'xxx'
```



如果用来补全的字符串与原字符串，两者的长度之和超过了指定的最小长度，则会截去超出位数的补全字符串

```
'abc'.padStart(10, '0123456789')
// '0123456abc'
```



如果**省略第二个参数**，默认使用空格补全长度

```
'x'.padStart(4) // '   x'
'x'.padEnd(4) // 'x   '
```



`padStart`的常见用途是为**数值补全指定位数**

下面代码生成 10 位的数值字符串。

```
'1'.padStart(10, '0') // "0000000001"
'12'.padStart(10, '0') // "0000000012"
'123456'.padStart(10, '0') // "0000123456"
```



另一个用途是提示**字符串格式**

```
'12'.padStart(10, 'YYYY-MM-DD') // "YYYY-MM-12"
'09-12'.padStart(10, 'YYYY-MM-DD') // "YYYY-09-12"
```



## matchAll()



`matchAll`方法返回一个正则表达式在当前字符串的所有匹配，详见《正则的扩展》的一章。



## 模板字符串   



传统的 JavaScript 语言，**输出模板**通常是这样写的。



```
$('#result').append(
  'There are <b>' + basket.count + '</b> ' +
  'items in your basket, ' +
  '<em>' + basket.onSale +
  '</em> are on sale!'
);
```



上面这种写法相当繁琐不方便，ES6 引入了模板字符串解决这个问题。

```
$('#result').append(`
  There are <b>${basket.count}</b> items
   in your basket, <em>${basket.onSale}</em>
  are on sale!
`);
```



模板字符串（template string）是增强版的字符串，用`` `*反引号* 标识。它可以当作普通字符串使用，也可以用来定义多行字符串，或者在字符串中嵌入变量。

- 可直接把变量塞到字符串  **`${`*****`变量`*****`}`**
- 可换行

```
// 普通字符串
`In JavaScript '\n' is a line-feed.`

// 多行字符串
`In JavaScript this is
 not legal.`

console.log(`string text line 1
string text line 2`);

// 字符串中嵌入变量
let name = "Bob", time = "today";
`Hello ${name}, how are you ${time}?`
```



## String.raw()   ?



ES6 还为原生的 String 对象，提供了一个`raw`方法

`String.raw`方法，往往用来**充当模板字符串的处理****函****数**，返回一个斜杠都被转义（即斜杠前面再加一个斜杠）的字符串，对应于替换变量后的模板字符串



# 正则的扩展  ==？==

## RegExp 构造函数  

> [---RegExp(正则表达式) - JavaScript | MDN---](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/RegExp)
>
> **`RegExp`** **对象**用于将文本与一个模式匹配。
>
> 
>
> 有两种方法可以**创建一个** **`RegExp`** **对象**：一种是**字面量**，另一种是**构造函数**



在 ES5 中，`RegExp`构造函数的参数有俩种情况：

1. 参数是**字符串**，这时第二个参数表示**正则表达式的修饰符**（flag）

```
var regex = new RegExp('xyz', 'i');
// 等价于
var regex = /xyz/i;
```





1. 参数是一个**正则表达式**，这时会返回一个**原则表达式的拷贝**

    正则表达式解析： /[aeiou]$/i

    （1） [aeiou]是字符类，表达式可以匹配这类的字符

    （2） [aeiou]$：以元音字符类结束

    （3） /[aeiou]$/i忽略大小写 ，匹配过程中大小写都符合

```
var regex = new RegExp(/xyz/i);
// 等价于
var regex = /xyz/i;
```



但是，ES5 不允许此时使用第二个参数添加修饰符，否则会报错

```
var regex = new RegExp(/xyz/, 'i');
// Uncaught TypeError: Cannot supply flags when constructing one RegExp from another
```



ES6 改变了这种行为。如果`RegExp`构造函数第一个参数是一个正则对象，那么可以使用第二个参数指定修饰符。而且，返回的正则表达式会忽略原有的正则表达式的修饰符，只使用新指定的修饰符。

```
new RegExp(/abc/ig, 'i').flags
// "i"
```

上面代码中，原有正则对象的修饰符是`ig`，它会被第二个参数`i`覆盖



## 字符串的正则方法



字符串对象共有4个方法可以使用正则表达式：`match()`、`replace()`、`search()`、`split()`



ES6 将这 4 个方法，在语言内部全部调用`RegExp`的实例方法，从而做到所有与正则相关的方法，全都定义在`RegExp`对象上   ?

- `String.prototype.match` 调用 `RegExp.prototype[Symbol.match``?``]`
- `String.prototype.replace` 调用 `RegExp.prototype[Symbol.replace]`
- `String.prototype.search` 调用 `RegExp.prototype[Symbol.search]`
- `String.prototype.split` 调用 `RegExp.prototype[Symbol.split]`



## u 修饰符

ES6 对正则表达式添加了`u`修饰符，含义为“**Unicode** 模式”，用来正确处理大于`\uFFFF`的 Unicode 字符。也就是说，会正确处理**四个字节的 UTF-16 编码**。

```
/^\uD83D/u.test('\uD83D\uDC2A') // false
/^\uD83D/.test('\uD83D\uDC2A') // true
```

上面代码中，`\uD83D\uDC2A`是一个四个字节的 UTF-16 编码，代表一个字符。但是，ES5 不支持四个字节的 UTF-16 编码，会将其识别为两个字符，导致第二行代码结果为`true`。加了`u`修饰符以后，ES6 就会识别其为一个字符，所以第一行代码结果为`false`。



一旦加上`u`修饰符号，就会修改下面这些正则表达式的行为。

1. 点字符
2. Unicode 字符表示法
3. 量词
4. 预定义模式
5. i 修饰符

## RegExp.prototype.unicode 属性



正则对象新增`unicode`属性，表示是否设置了`u`修饰符

```
const r1 = /hello/;
const r2 = /hello/u;

r1.unicode // false
r2.unicode // true
```



## y 修饰符



除了`u`修饰符，ES6 还为正则表达式添加了`y`修饰符，叫做“粘连”（sticky）修饰符。



`y`修饰符的作用与`g`修饰符类似，也是**全局匹配**，后一次匹配都从上一次匹配成功的下一个位置开始

不同之处在于，`g`修饰符只要剩余位置中存在匹配就可，而**`y`****修饰符确保匹配必须从剩余的第一个位置开始**，这也就是“粘连”的涵义。   ？



```
var s = 'aaa_aa_a';
var r1 = /a+/g;
var r2 = /a+/y;

r1.exec(s) // ["aaa"]
r2.exec(s) // ["aaa"]

r1.exec(s) // ["aa"]
r2.exec(s) // null
```

上面代码有俩个正则表达式，一个使用`g`修饰符，另一个使用`y`修饰符。这俩个正则表达式各执行了俩次，第一次执行的时候，俩者行为相同，剩余字符串都是`_aa_a`。？由于`g`修饰没有位置要求，所以第二次执行会返回结果，而`y`修饰符要求匹配必须从头部开始，所以返回`null`。



如果改一下正则表达式，保证每次都能头部匹配，`y`修饰符就会返回结果了。

```
var s = 'aaa_aa_a';
var r = /a+_/y;

r.exec(s) // ["aaa_"]
r.exec(s) // ["aa_"]
```

上面代码每次匹配，都是从剩余字符串的头部开始。



使用`lastIndex`属性，可以更好地说明`y`修饰符。

```
const REGEX = /a/g;

// 指定从2号位置（y）开始匹配
REGEX.lastIndex = 2;

// 匹配成功
const match = REGEX.exec('xaya');

// 在3号位置匹配成功
match.index // 3

// 下一次匹配从4号位开始
REGEX.lastIndex // 4

// 4号位开始匹配失败
REGEX.exec('xaya') // null
```

上面代码中，`lastIndex`属性指定每次搜索的开始位置，`g`修饰符从这个位置开始向后搜索，直到发现匹配为止。



`y`修饰符同样遵守`lastIndex`属性，但是要求必须在`lastIndex`指定的位置发现匹配。

```
const REGEX = /a/y;

// 指定从2号位置开始匹配
REGEX.lastIndex = 2;

// 不是粘连，匹配失败
REGEX.exec('xaya') // null

// 指定从3号位置开始匹配
REGEX.lastIndex = 3;

// 3号位置是粘连，匹配成功
const match = REGEX.exec('xaya');
match.index // 3
REGEX.lastIndex // 4
```

实际上，`y`修饰符号隐含了头部匹配的标志`^`



```
/b/y.exec('aba')
// null
```

上面代码由于不能保证头部匹配，所以返回`null`。

`y`修饰符的设计本意，就是让头部匹配的标志`^`在全局匹配中都有效



下面是字符串对象的`replace`方法的例子。

```
const REGEX = /a/gy;
'aaxa'.replace(REGEX, '-') // '--xa'
```

上面代码中，最后一个`a`因为不是出现在下一次匹配的头部



# 数值的扩展

## Math 对象的扩展

### 普通数值

<span style='font-size: 20px'>Math.trunc()</span>

`Math.trunc()`方法用于去除一个数的小数部分，返回整数部分

+ 对于非数值，内部使用`Number()`将其转为数值
+ 对于空值和无法截取整数的值，返回`NaN`

``` javascript
Math.trunc(4.12)	// 4

Math.trunc('12.2')	// 12
Math.trunc(true)	// 1
Math.trunc(false)	// 0
Math.trunc(null)	// 0

Math.trunc(NaN);	// NaN
Math.trunc('foobar')	// NaN
Math.trunc();	// NaN
Math.trunc(undefined)	// NaN
```



<span style='font-size: 20px'>Math.sign()</span>

`Math.sign()`用来判断一个数到底是正数、复数、还是零

返回值有五种：

+ 参数为正，返回`+1`
+ 参数为负，返回`-1`
+ 参数为 0，返回`0`
+ 参数为 -0，返回`-0`
+ 其他值,返回`NaN`

如果参数是非数值,会自动转为数值  
对于无法转为数值的值，返回`NaN`



<span style='font-size: 20px'>Math.cbrt()</span>

`Math.cbrt()`用于计算一个数的立方根

对于非数值，内部先使用`Number()`将其转为数值。



<span style='font-size: 20px'>Math.hypot()</span>

`Math.hypot()`返回所有参数的平方和的平方根。

``` javascript
Math.hypot(3, 4)	// 5
```

如果参数不是数值，`Math.hypot()`会将其转为数值，只要有一个参数无法转为数值，就返回`NaN`



### 32 位	==？==

<span style='font-size: 20px'>Math.clz32()</span>

> `clz32`这个函数名就来自”count leading zero bits in 32-bit binary representation of a number“（计算一个数的 32 位二进制形式的前导 0 的个数）的缩写。

JS 的整数使用 **32 位二进制形式**表示，`Math.clz32()`返回：一个数的 32 位无符号整数形式有多少个前导 0

左移运算符`<<`也与`Math.clz32()`直接相关

> [左移运算符_百度百科](https://baike.baidu.com/item/%E5%B7%A6%E7%A7%BB%E8%BF%90%E7%AE%97%E7%AC%A6)



<span style='font-size: 20px'>Math.imul</span>

`Math.imul()`返回俩个数以 32 位带符号整数形式相乘的结果，返回的也是一个 32 位的带符号整数

``` javascript
Math.imul(2, 4)   // 8
Math.imul(-1, 8)  // -8
Math.imul(-2, -2) // 4
```

如果只考虑最后 32 位，大多数情况下，`Math.imul(a, b)`与`a * b`的结果是相同的，即该方法等同于`(a * b)|0`（*将`a*b`结果转为 32 位整数，超过 32 位的部分溢出*）的效果。  
之所以需要部署这个方法，是因为 JavaScript 有精度限制，超过 2 的 53 次方的值无法精确表示。这就是说，对于那些很大的数的乘法，低位数值往往都是不精确的，`Math.imul`方法可以返回正确的低位数值。



<span style='font-size: 20px'>Math.fround()</span>

`Math.fround()`返回一个数的 32 位**单精度浮点数**==？==形式



### 对数

<span style='font-size: 20px'>Math.expm1()</span>

`Math.expm1(x)`返回 e<sup>x</sup> - 1，即`Math.exp(x) - 1`。



<span style='font-size: 20px'>Math.log1p()</span>

`Math.log1p(x)`返回 `1 + x` 的对数，即`Math.log(1 + x)`，若`x`小于 -1，返回`NaN`。



<span style='font-size: 20px'>Math.log10()</span>

`Math.log10(x)`返回以 10 为底的`x`的对数。如果`x`小于 0，则返回 `NaN`。



<span style='font-size: 20px'>Math.log2()</span>

`Math.log2(x)`返回以 2 为底的`x`的对数。如果`x`小于 0，则返回 NaN。



### 双曲函数

- `Math.sinh(x)` 返回`x`的双曲正弦（hyperbolic sine）

- `Math.cosh(x)` 返回`x`的双曲余弦（hyperbolic cosine）

- `Math.tanh(x)` 返回`x`的双曲正切（hyperbolic tangent）

- `Math.asinh(x)` 返回`x`的反双曲正弦（inverse hyperbolic sine）

- `Math.acosh(x)` 返回`x`的反双曲余弦（inverse hyperbolic cosine）

- `Math.atanh(x)` 返回`x`的反双曲正切（inverse hyperbolic tangent）



## 指数运算符

ES6 新增了一个指数运算符`**`。（*类似Python*）

`x ** y`表示`x`的`y`次幂。



指数运算符可与等号结合，形成一个新的赋值运算符`**=`

``` javascript
let a = 2;
a **= 2;
// 等同于 a = a * a
```



注意：在 V8 引擎中，指数运算符与`Math.pow`的实现不相同，对特别大的运算结果，俩者会有细微的差异



# 函数的扩展

## 函数参数的默认值

ES6 允许为函数的参数设置默认值，即直接写在参数定义的后面

``` javascript
function log(x, y = 'world') {
    console.log(x, y);
}

log('Hello');	// Hello world
log('Hello', '');	// Hello
```

注意上面代码第六行`log('Hello', '')`也算给`y`赋值了。



<span style='font-size: 18px'>解构赋值的默认值</span>

参数默认值能够与解构赋值默认值结合使用



1. 只使用了解构赋值默认值的情况

``` javascript
function foo({ x, y = 1 }) {
    console.log(x, y);
}

foo({}); // undefined 1
foo();	// TypeError: Cannot read property 'x' of undefined
```

上面代码只使用了对象的解构赋值默认值，没有使用函数参数的默认值。  
只有当`foo`的参数是一个对象时，变量`x`和`y`才会通过解构赋值生成。  
如果函数调用时没提供参数，变量`x`和`y`就不会生成，从而报错。通过提供函数参数默认值可以避免这种情况。



2. 解构赋值与函数参数默认值结合使用

``` javascript
function foo({ x, y = 1 } = {}) {
    console.log(x, y);
}

foo();	// undefined 1
```

上面代码指定，如果没提供参数，`foo`的参数默认为一个空对象；而上一个写法是报错。

上例详解：`= {}`是设置函数参数默认值，而`{ x, y = 1 }`是设置解构赋值默认值。因为没提供参数，因此使用参数默认值，也就是空对象。但`y`有解构赋值默认值`1`，所以`y`有值。



另一个更复杂的例子。

1. 只解构赋值默认值

``` javascript
function fetch(url, { body = '', method = 'get', headers = {} }) {
    console.log(method);
}

fetch('baidu.com', {});	// get
fetch('baidu.com');	// 报错
```

上面代码中，函数`fetch`第 2 个参数是个对象，就可以为它的 3 个属性设置默认值。  
这种写法不能省略第二个参数，因为没有传参就根本不会解构，也不会取到解构的默认值。但若结合函数参数的默认值，就可以省略第二个参数。



2. 解构赋值与函数参数结合

``` javascript
function fetch(url, { body = '', method = 'get', headers = {} } = {}) {
	console.log(method);
}

fetch('baidu.com');	// get
```

上面代码中，函数`fetch`没有第二个参数时，**函数参数的默认值就会生效，然后才是解构赋值的默认值生效**（*所以函参默认值生效会引起解构赋值默认值生效*），变量`method`才会取到默认值`get`。



另一个都用到解构赋值但默认值设置有差别的例子。

``` javascript
// 第一种写法
function f1({ x = 0, y = 0 } = {}) {
    return [x, y];
}

// 写法二
function f2({ x, y } = { x: 0, y: 0 }) {
    return [x, y];
}

// 函数没有参数的情况
f1();	// [0, 0]	先函数参数默认值生效空对象，然后解构赋值默认值生效[0, 0]
f2();	// [0, 0]	函数参宿默认值[0, 0]

// x 和 y 都有值的情况
f1({x: 3, y: 8}) // [3, 8]
f2({x: 3, y: 8}) // [3, 8]

// x有值， y 无值
f1({ x: 3 });	// [3, 0]	
f2({ x: 3 });	// [3, undefined]	函数参数默认值是一个对象，但是没有传参y所以取不到里面的y属性值

// 不传入同名属性 不同名，模式不匹配
f1({ z: 3 })	// [0, 0]
f2({ z: 3 })	// [undefined, undefined]
```

写法一：函数默认值空对象`{}`加解构赋值默认值`{ x = 0, y = 0 }`。

写法二：函数参数的默认值是有具体属性的对象，但是没有设置对象解构赋值的默认值

由上例可以看出，不推荐第二种写法。



### 参数默认值位置

通常情况，定义了默认值的参数应作尾参数。因为这样比较容易看出来到底省略了哪些参数。  
如果非尾部的参数设置默认值，实际上这个参数是没法省略的。



下面是一个默认值参数不是尾参数的例子。

``` javascript
function f(x = 1, y) {
    return [x, y];
}

f()	// [1, undefined]
f(2)	// [2, undefined]
f(, 2)	// 报错
f(undefined, 1)	// [1, 1]	
```

上面代码中，有默认值的参数不是尾参数。这时，无法只省略该参数而不省略它后面的参数，除非显示输入`undefined`。



### 函数的`length`属性

指定了默认值以后，函数的`length`属性将返回没有指定默认值的参数个数。也就是说，指定了默认值以后，`length`属性将失真。



这是因为`length`属性的含义是，该函数预期传入的参数个数。某个参数指定默认值以后，预期传入的参数个数就不包括这个参数了。  
同理，` rest`参数也不会计入`length`属性。



如果设置了默认值的参数不是尾参数，那么`length`属性也不再计入后面的参数了



### 作用域

一旦设置了参数的默认值，函数进行声明初始化时，参数会形成一个单独的作用域（*context*）。等到初始化结束，这个作用域就会消失。  
这种行为，在不设置参数默认值时是不会出现的。



``` javascript
var x = 1;

function f(x, y = x) {
    console.log(y);
}

f(2);	// 2
```

上面代码中，参数`y`的默认值等于变量`x`。调用函数`f`时，参数会形成一个单独的作用域，在这个作用域内，默认变量`x`指向第一个参数`x`，而不是全局变量`x`，所以输出是`2`。



再看下面的例子。

``` javascript
let x = 1;

function f(y = x) {
    let x = 2;
    console.log(y);
}

f();	// 1
```

上面代码中，函数`f`被调用时，参数`y = x`形成一个单独的作用域。这个作用域里面，变量`x`本身没有定义，所以指向外层的全局变量`x`。  
函数调用时，函数体内的局部变量`x`影响不到默认值变量`x`。



如果此时，全局变量`x`不存在，就会报错。`ReferenceError: x is not defined`。



下面这样写，也会报错。

``` javascript
var x = 1;

function foo(x = x) {
    // ...
}

foo()	// ReferenceError: x is not defined
```

上面代码中，参数`x = x`形成一个单独作用域。实际执行的是`let x = x`，由于暂时性死区，这时会报错 “x 未定义”。



如果参数的默认值是一个函数，该函数的作用域也遵守这个规则。



### 应用

利用参数默认值，可以指定某一个参数不得省略，如果省略就抛出一个错误。

实例。

``` javascript
function throwIfMissing() {
    throw new Error('Missing parameter');	// 抛出创建的 Error 实例化对象
}

function foo(mustBeProvided = throwIfMissing()) {	// 函数参数默认值
    return mustBeProvided;
}

foo();	// Error: Missing parameter
```

上面代码实现了当没有传参时显示错误`Missing parameter`。

上面代码的`foo`函数，如果调用的时候没有参数，就会调用默认值`throwIfMissing`函数，从而抛出一个错误。

从上面代码中还可以看到，参数`mustBeProvided`的默认值等于`throwIfMissing`函数的运行结果（注意函数名`throwIfMissing`之后有一对圆括号），这表明参数的默认值不是在定义时执行，而是在运行时执行。如果参数已赋值，默认值中的函数就不会运行。

> `throw`用来抛出一个用户自定义的异常在控制台，当前函数的执行将被停止（*`throw`之后的语句将不会执行* ）
>
> 而`console.error()`只会在控制台输出红色信息
>
> `error` 详细介绍查阅[Error - JavaScript | MDN](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Error)



## rest 参数

ES6 引入 rest 参数（形式为**`...变量名`**），用于**获取函数的多余参数**，这样就不需使用`arguments`对象（*伪数组*）了。rest 参数搭配的变量是一个**数组**，该变量**将多余的参数放入数组**中。



``` javascript
function add(...values) {
    let sum = 0;
    for (let val of values) {	// 数组遍历
        sum += val;
    }
    return sum;
}

add(2, 5, 3);	// 10
```

上面代码的`add`函数是一个求和函数，利用 rest 参数，可以向该函数传入任意数目的参数。



下面是一个 rest 参数代替`arguments`变量的例子。	

``` javascript
// arguments 变量的写法
function sortNumbers() {
    return Array.prototype.slice.call(arguments).sort();	// call?
}

// rest 参数的写法
const sortNumbers = (...numbers) => numbers.sort();		
```

上面代码的俩种写法，比较后可以发现，rest 参数的写法更自然也更加简洁。

`arguments`对象不是数组，而是个类似数组的对象。所以为了使用数组的方法，必须使用`Array.prototype.slice.call`先将其转为数组。rest 参数就不存在这个问题，它是一个真正的数组，数组特有的方法都可以使用。

> `call()` 允许为不同的对象分配和调用属于一个对象的函数/方法
>
> `call()` 提供新的 **this** 值给当前调用的函数/方法。你可以使用 `call` 来实现继承：写一个方法，然后让另外一个新的对象来继承它（而不是在新对象中再写一次这个方法）。
>
> 

## 严格模式

从 ES5 开始，函数内部可以设定为严格模式。

``` javascript
function foo(a, b) {
    'use strict';
    // ...
}
```



ES6 做了一点修改，规定只要函数参数使用了 默认值、解构赋值、扩展运算符，那么函数内部就不能显式设定为严格模式，否则会报错。

**原因**： 
这样规定的原因是，函数内部的严格模式，同时适用于函数体和函数参数。  
但是函数执行的时候，先执行函数参数，然后再执行函数体。这样就有一个不合理的地方，只有从函数体之中，才能知道是否应该以严格模式执行，但参数却应该先于函数体执行。

如下例。

``` javascript
// 报错
function foo(value = 070) {
    'use strict';
    return value;
}
```

上面代码中，参数`value`的默认值是八进制数`070`，但严格模式下不能用前缀`0`表示八进制，所以应该报错。但实际上，JS 引擎会先成功执行`value = 070`，然后进入函数体内部，发现需要用严格模式执行，这时才会报错。  

虽然可以先解析函数体代码，再执行参数代码，但这样无疑增加了复杂性。因此，标准索性禁止了这种用法，只要参数使用了 默认值、解构赋值、扩展运算符，就不能显式指定严格模式。



规避这种限制：

+ 设定全局性的严格模式，这是合法的。
+ 把函数包在一个无参数的立即执行函数里面。



## name 属性

函数的`name`属性，返回该函数的函数名。

``` javascript
function foo() {}
foo.name;	// foo
```



这个属性早就被浏览器广泛支持，但是直到 ES6，才将其写入了标准。

需要注意的是，ES6 对这个属性的行为做出了一些修改。如果将匿名函数赋值给一个变量，ES5 的`name`属性会返回空字符串，ES6 的`name`属性会返回实际的函数名



如果将一个具名函数赋值给一个变量，则 ES5 和 ES6 的`name`属性都返回这个具名函数原本的名字

``` javascript
const bar = function baz() {};

bar.name;	// baz
```



`Function`构造函数==?==返回的函数实例，`name`属性的值为`annonymous`。

``` javascript
(new Function).name;	// anonymous （匿名的）
```



`bind`==?==返回的函数，`name`属性的值为会加上`bound`前缀。

``` javascript
function foo() {};
foo.bind({}).name;	// bound foo

(function(){}).bind({}).name;	// bound
```



## 箭头函数

ES6标准新增了一种新的函数：Arrow Function（箭头函数）。

为什么叫Arrow Function，是因为它的定义用的就是一个箭头。

### 基本用法

箭头函数相当于**匿名函数**，并且简化了函数定义。箭头函数有两种格式：

+ 一种是只包含一个表达式，连{ ... }和return都省略掉：

    ``` javascript
    x => x * x
    
    // 等价于
    
    function (x) {
        return x * x;
    }
    ```

    

+ 还有一种可以包含多条语句，这时候就不能省略`{ ... }`和`return`：

``` javascript
x => {
    if (x > 0) {
        return x * x;
    }
    else {
        return - x * x;
    }
}
```

- 如果只有**一个参数**，可省**（）**
- 如果只有**一个return**，可省 **{ }**



### 使用注意

+ 函数体内的`this`对象，就是定义时所在的对象，而不是使用时所在的对象	
+ 不可以作构造函数，也就是说，不可以使用`new`命令，否则会抛出一个错误
+ 不可以使用`arguments`对象，该对象在函数体内不存在，要用可用 rest 参数代替
+ 不可使用`yield`命令，因此箭头函数不能用作 Generator 函数



上面四点中，尤其第一点值得注意。  
因为`this`对象的指向是可变的，但是在箭头函数中，它是固定的。

``` javascript
var id = 21;
function foo() {
    setTimeout(() => {
        console.log('id:', this.id);
    }, 100);
}

foo.call({ id: 42 });	// id: 42
```

上面代码中，`setTimeout`的参数是一个箭头函数，这个箭头函数的**定义生效**是在`foo`函数生成时，而它的**真正执行**要等到 100 毫秒后。如果是普通函数，执行时`this`应该指向使用时所在的对象全局对象`window`==？==，输出`21`。但是，箭头函数导致`this`总是指向函数定义生效时所在的对象`{id: 42}`，所以输出的是`42`。



箭头函数可以让`this`指向固定化，这种特性很有利于**封装回调函数**==？==

见下例。

``` javascript
let handler = {
    id: '123',
    init: function() {
        document.addEventListener(
            'click', event => this.doSomething(event.type), false);
    },
    doSomething: function(type) {
        console.log('Handling ' + type + ' for ' +  this.id);
    }
};
```

上面代码的`init`方法中，使用了箭头函数，这导致这个箭头函数里面的`this`，总是指向`handler`对象。否则，回调函数运行时，`this.doSomething`这一行会报错，因为此时`this`指向`document`对象



## 尾调用优化

### 什么是尾调用

尾调用（Tail Call）是函数式编程的一个重要概念，就是指某个函数的最后一步是调用另一个函数。

``` javascript
function f(x) {
    return g(x);
}
```

但以下三种情况，都不属于尾调用。

``` javascript
// 1 调用函数 g 后还有赋值操作
function f(x) {
    let y = g(x);
    return y;
}

// 2 调用后还有操作
function f(x) {
    return g(x) + 1;
}

// 3
function f(x) {
    g(x);
}
// 情况3等同于以下代码
function f(x) {
    g(x);
    return undefined;
}
```



尾调用不一定出现在函数尾部，只要是最后一步操作即可

```javascript
function f(x) {
  if (x > 0) {
    return m(x)
  }
  return n(x);
}
```

上面代码中，函数`m`和`n`都属于尾调用，因为它们都是函数`f`的最后一步操作。



### 尾调用优化

尾调用之所以与其他调用不同，就在于它的特殊的调用位置。

函数调用会在内存形成一个“调用记录”，又称“调用帧”，保存调用位置和内部变量等信息。  
如果在函数`A`的内部调用函数`B`，那么在`A`的调用帧上方还会形成一个`B`的调用帧。等到`B`运行结束，将结果返回到`A`，`B`的调用帧才会消失。（*也就是说越后调用的越在上方即外层形成调用帧*）  
以此类推，所有的调用帧，就形成一个“调用栈”（*call stack*）。

尾调用由于是函数的最后一步操作，所以不需要保留外层函数的调用帧。因为调用位置、内部信息等都不会再用到了，只要直接用内层函数的调用帧，取代外层函数的调用帧就可以了。



<span style='font-size: 19px'>意义</span>

尾调用优化即只保留内层函数的调用帧。如果所有函数都是尾调用，那么完全可以做到每次执行时调用帧只有一项，这将大大**节省内存**。这就是尾调用优化的意义。



注意：只有不再用到外层函数的内部变量，内层函数的调用帧才会取代外层函数的调用帧，否则就无法进行尾调用优化。

``` javascript
function addOne(a) {
    let one = 1;
    function inner(x) {	
        return x + one;
    }
    return inner(a);
}
```

上面的函数不会尾调用优化，因为内层函数`inner`用到了外层函数`addOne`的内部变量`one`。



### 尾递归

函数调用自身，称为递归。如果尾调用自身，就称为尾递归。

递归非常耗费内存，因为需要同时保存成前上百个调用帧，很容易发生**栈溢出**错误（*stack overflow*)。但对于尾递归来说，由于只存在一个调用帧，所以不会发生栈溢出错误。



下例计算`n`的阶乘（*factorial*），最多需要保存`n`个调用记录。

``` javascript
function factorial(n) {
    if (n === 1)
        return 1;
    return n * factorial(n - 1);	// 不算尾调用优化，因为用到了外层变量 n
}

factorial(5);	// 20
```



如果改写成尾递归，只保留一个调用记录。

``` javascript
function factorial(n, total) {
    if (n === 1)
        return total;
    return factorial(n - 1, n * total);
}

factorial(5, 1)	// 120
```



由此可见，尾调用优化对递归操作意义重大，所以一些函数式编程语言将其写入了语言规格。

ES6 第一次明确规定，所有 ESMAScript 的实现，都必须部署尾调用优化。	==？==



### 递归函数的改写

尾递归的实现往往需要改写递归函数，确保最后一步只调用自身。  
做到这一点的方法，就是将所有用到的内部变量改写成函数的参数。  
这样做的缺点是不太直观，第一眼很难看出来。



俩个方法可以解决这个问题。

+ 在尾递归函数之外，再提供一个正常形式的函数	

    <span style = 'font-size: 12px'>没感觉有什么用</span>==？==

    ``` javascript
    function tailFactorial(n, total) {
        if (n === 1)
            return total;
        return tailFactorial(n - 1, n * total);
    }
    
    function factorial(n) {
        return tailFactorial(n, 1);
    }
    
    factorial(5);	//20
    ```

    函数式编程有一个概念，叫做柯里化（*currying*），意思是将多余参数的函数转换成单参数的形式  
    这里也可以使用柯里化。

    ==?==

    ``` javascript
    function currying(fn, n) {
        return function (m) {	// 匿名函数; m在这里起什么作用？
            return fn.call(this, m, n);
        }
    }
    
    function tailFactorial(n, total) {
        if (n === 1)
            return total;
        return tailFactorial(n - 1, n * total);
    }
    
    const factorial = currying(tailFactorial, 1);
    
    factorial(5);	// 120
    ```



+ 第二种方法简单多了，就是采用 ES6 的函数默认值。

    ``` javascript
    function factorial(n, total = 1) {
        if (n === 1)
            return total;
        return factorial(n - 1, n * total);
    }
    
    factorial(5);	// 120
    ```

    上面代码中，参数`total`有默认值`1`，所以调用时不用提供任何值。



### 严格模式

ES6 的尾调用优化只在严格模式下开启，正常模式是无效的。

这是因为正常模式下，函数内部有两个变量可以跟踪函数的调用栈：

+ `func.arguments`：返回调用时函数的参数
+ `func.caller`：返回调用当前函数的那个函数

尾调用优化发生时，函数的调用栈会改写，因此上面两个变量就会失真。严格模式禁用这两个变量，尾调用模式仅在严格模式下生效。



### 尾递归优化的实现

尾递归优化只在严格模式下生效，那么正常模式下， 或那些不支持该功能的环境中要使用尾递归优化的话，就得自己实现。

自己实现尾递归优化的原理非常简单。只要减少调用栈，就不会导致溢出。就用**循环**换掉**递归**



==。。。==



## 函数参数的尾逗号

ES2017 允许函数的最后一个参数有尾逗号（*trailing comma*）  
此前，函数定义和调用时，都不允许最后一个参数后面出现逗号。



见实例。

``` javascript
function foo(
	param1,
    param2
)	{ /* ... */ }
```

如果像上面这样，将参数写成多行，以后修改代码的时候想添加参数，就势必要在原来最后一个参数后面添加一个`,`。这对于版本管理系统来说，就会显示添加`,`的那一行也发生了改动。这看上去有点冗余。于是新的语法允许定义和调用时，尾部直接有一个`,`



这样的规定也使得，函数参数与数组和**对象的尾逗号规则==？==**保持一致了。

```javascript
function show(a, b, ...args, c){
  alert(args);
}

show(1, 2, 3, 4, 5);  //错误
```



# 数组的扩展

## 扩展运算符

### 含义

扩展运算符（*spread*）是**`...`**。它好比 rest 参数（*剩余参数转为数组*）的逆运算。将一个数组转为用`,`分隔的参数序列

``` javascript
console.log(1, ...[2, 3, 4], 5);	// 1 2 3 4 5

[...document.querySelectorAll('div')]	// [<div>, <div>, <div>]
```



该运算符主要用于函数调用。

``` javascript
function push(array, ...items) {
    array.push(...items);	// 数组.push() 向数组加入元素
}

// 另一个例子

function add(x, y) {
    return x + y;
}
const numbers = [4, 38];
add(...numbers)	// 42
```

上面代码中，`array.push(...items)`和`add(...numbers)`这两行都是函数的调用，都使用了扩展运算符，将一个数组变为参数序列。



### 替代函数的 apply 方法

由于扩展运算符可以展开数组，所以不再需要`apply`方法将数组转为函数的参数了

``` javascript
// ES5
function f(x, y, z) {
    // ...
}
var args = [0, 1, 2];
f.apply(null, args);

// ES6
function f(x, y, z) {
    // ...
}
let args = [0, 1, 2];
f(...args);
```

> apply()和call()。这两个方法的用途都是在特定的执行环境中调用函数，实际上等于设置函数体内this对象的值，换句话说就是为了改变函数内部this的指向。
>
> ==apply()?==
>
> 在 JS 严格模式下，如果`apply()`第一个参数不是对象，则它将成为被调用函数的所有者（对象）。在非严格模式下，如果参数为空，它将成为全局对象



### 扩展运算符的应用

<span style='font-size:20px'>复制数组</span>

数组是复合的数据类型，直接复制的话，只是复制了指向底层数据结构的指针，而不是克隆一个全新的数组。

扩展运算符提供了复制数组的简便写法。

``` javascript
const a1 = [1, 2];

// 写法一
const a2 = [...a1];
// 写法二
const [...a2] = a1;
```



<span style = 'font-size:20px'>合并数组</span>

扩展运算符提供了数组合并的新写法。

``` javascript
const arr1 = ['a', 'b'];
const arr2 = ['c'];
const arr3 = ['d', 'e'];

[...arr1, ...arr2, ...arr3] // [ 'a', 'b', 'c', 'd', 'e' ]
```

使用时要注意，这样是浅拷贝，即如果修改了原数组的成员，会同步反映到新数组。



<span style = 'font-size:20px'>与解构赋值结合</span>

扩展运算符可以与解构赋值结合起来，用于**生成数组**或**数组赋值**。

``` javascript
const [first, ...rest] = [1, 2, 3, 4, 5];
rest	// [2, 3, 4, 5]
```

如果将扩展运算符用于数组赋值，只能放在参数的最后一位，否则报错。



<span style='font-size:20px'>字符串</span>

扩展运算符还可以将字符串转为真正的数组。

``` javascript
[...'hello']	// [ "h", "e", "l", "l", "o" ]
```

上面的写法最重要的好处是，能够正确识别四个字节的 Unicode 字符。	==Unicode 字符？==



<span style='font-size:20px'>实现了 Iterator 接口的对象</span>

任何 Iterator 接口的对象，都可以用扩展运算符转为真正的数组。

``` javascript
let nodeList = document.querySelectorAll('div');	类数组的对象
let array = [...nodeList];
```

对于那些部署 Iterator 接口的类似数组的对象，扩展运算符就无法将其转为真正的数组。



<span style = 'font-size:20px'>Map 和 Set 结构，Generator 函数</span>

扩展运算符内部调用的是数据结构的 Iterator 接口，因此只要具有 Iterator 接口的的对象，都可以使用扩展运算符，比如 Map 结构。

``` javascript
let map = new Map([
    [1, 'one'],
  	[2, 'two'],
  	[3, 'three'],
]);

let arr = [...map.keys()];	// [1, 2, 3]
```

Generator 函数运行后，返回一个遍历器对象，因此也可以使用扩展运算符。

``` javascript
const go = function*() {
    yield 1;
    yield 2;
};

[...go()]	// [1, 2]
```



## Array.from()

`Array.from()`用于将俩类对象转为真正的数组：类似数组的对象（array-like object）和可遍历（iterable）的对象。

==从这开始==

下面是一个类数组对象，`Array.from`将它转为真正的数组。类数组对象，即有`length`属性的对象。

``` javascript
let arrayLike = {
    '0': 'a',
    '1': 'b',
    '2': 'c',
    length: 3
};

let arr = Array.from(arrayLike);	// ['a', 'b', 'c']
```

只要是部署了 Iterator 接口的数据结构，`Array.from`都能将其转为数组。



`Array.from()`还可以接受**第二个参数**，作用类似于数组的`map`方法，即对每个元素进行处理，将处理后的值放入返回的数组。

``` javascript
Array.from(arrayLike, x => x * x);
// 等同于
Array.from(arrayLike).map(x => x * x);


Array.from([1, 2, 3], x => x * x)
// [1, 4, 9]
```



`Array.from()`可以将各种值转为真正的数组，并且还提供`map`功能。这实际上意味着，只要有一个原始的数据结构，你就可以先对它的值进行处理，然后转成规范的数组结构，进而就可以使用数量众多的数组方法

``` javascript
Array.from({ length: 2 }, () => 'jack')
// ['jack', 'jack']
```

上面代码中，`Array.from`的第一个参数指定了第二个参数运行的次数。这种特性可以让该方法的用法变得非常灵活



## Array.of()

`Array.of()`用于将一组值，转换为数组。

``` javascipt
Array.of(3, 11, 8);	// [3, 11, 8]
Array.of(3).length;	// 1
```



这个方法的主要目的，是弥补数组构造函数`Array()`的不足。  
因为参数个数的不同，会导致`Array()`的行为有差异。

``` javascript
Array()	// []
Array(3)	// [, , ,]
Array(3, 11, 8)	// [3, 11, 8]
```

上面代码中，`Array`方法没有参数、一个参数、三个参数时，返回结果都不一样。  
参数个数只有一个时，实际上是指定**数组的长度**。只有当参数个数不少于 2 个时，`Array()`才会返回由参数组成的**新数组**。



`Array.of()`基本上可用来替代`Array()`或`new Array()`==？==。  



## 数组实例的方法

### copyWithin()

数组实例的`copyWithin`方法，在当前数组内部，将指定位置的成员复制到其他位置（会覆盖原有成员），然后返回当前数组。也就是说，使用这个方法，会修改当前数组。

**语法格式**：

```javascript
Array.prototype.copyWithin(target, start = 0, end = this.length)
```

它接受三个参数：

+ target：必须。从该位置开始替换数据。若为负值，表示倒数	==？==
+ start：可选。从该位置开始读取数据，默认为 0 。若为负值，表示倒数
+ end：可选。到该位置前停止读取数据，默认等于数组长度。若为负值，表示倒数

这三个参数都应该是数值，如果不是，会自动转为数值。



实例。

``` javascript
[1, 2, 3, 4, 5].copyWithin(0, 3)
// [4, 5, 3, 4, 5]
```

上面代码表示将 3 号位到数组结束的成员`4, 5`，复制到从 0 号位开始的位置，也就覆盖了原来的`1, 2`。



### find() 和 findIndex()

<span style='font-size:20px'>find() </span>

数组实例的`find()`用于找出第一个符合条件的数组成员。它的**参数是一个回调函数**，所有数组成员依次执行该回调函数，直到找出第一个返回值为`true`的成员后返回该成员。若没有符合条件的成员则返回`undefined`。

``` javascript
[1, 4, -5, -10].find((n) => n <= 0);	// -5
```

> 一个函数被作为参数传递给另一个函数（在这里我们把另一个函数叫做“otherFunction”），回调函数在otherFunction中被调用。

`find()`的回调函数可以接受三个参数，依次为当前的值，当前的位置，原数组	==？==

```javascript
[1, 4, -5, -10].find(function(value, index, arr) {
    return value > 9;
})
```



<span style = 'font-size:20px'>findIndex()</span>

数组实例的`findIndex()`的用法与`find()`非常类似，返回第一个符合条件的数组成员的位置，如所有成员都不符合条件，则返回`-1`。

``` javascript
[1, 5, 10, 15].findIndex(function(value, index, arr) {
    return value > 9;
})	// 2
```



这俩个方法都可以接受第二个参数，用来绑定回调函数的`this`对象。	==?==

``` javascript
function f(v) {
    return v > this.age;
}
let people = {name: 'John', age: 20};
[10, 12, 26, 15].find(f, person);	// 26
```

上面代码中，`find`函数接收了第二个参数`person`对象，回调函数中的`this`对象指向`person`对象。



### fill()

`fill()`使用给定值，填充一个数组。

``` javascript
['a', 'b', 'c'].fill(7)	// [7, 7, 7]

new Array(3).fill(7)	// [7, 7, 7]
```

上面代码表明，`fill()`用于空数组的初始化非常方便。数组中已有的元素，会被全部抹去。



`fill()`接受第二个和第三个参数，用于指定填充的起始位置和结束位置。

``` javascript
['a', 'b', 'c'].fill(7, 1, 2)	// ['a', 7, 'c']
```

上面代码表示，`fill()`从 1 号位开始，向原数组填充 7 ，到 2 号**之前**结束。



注意：如果填充的类型为对象，那么被赋值的是**同一个内存地址的对象**，而不是深拷贝对象。

``` javascript
let arr = new Array(3).fill({name: "Mike"});
arr[0].name = "Ben";	// 改了一个所有都生效
arr // [{name: "Ben"}, {name: "Ben"}, {name: "Ben"}]
```



### entries()，keys() ，values()

ES6 提供三个新的方法——`entries()`，`keys()`和`values()`——用于遍历数组。它们都返回一个遍历器对象，可以用`for...of`循环进行遍历，  
唯一的区别是`keys()`是对键名的遍历、`values()`是对键值的遍历，`entries()`是对键值对的遍历。

``` javascript
for (let index of ['a', 'b'].keys()) {
  console.log(index);
}
// 0 键名
// 1

for (let elem of ['a', 'b'].values()) {
  console.log(elem);
}
// 'a' 键值
// 'b'

for (let [index, elem] of ['a', 'b'].entries()) {
  console.log(index, elem);
}
// 0 "a" 键值对
// 1 "b"
```



### includes()

`Array.prototype.includes()`返回一个布尔值，表示某个数组是否包含给定值，与字符串的`includes()`类似。

``` javascript
[1, 2, 3].includes(2)     // true
[1, 2, 3].includes(4)     // false
[1, 2, NaN].includes(NaN) // true
```



该方法的第二个参数表示搜索的起始位置，默认为 `0`。如果第二个参数为负数，则表示倒数的位置，如果这时它大于数组长度（比如第二个参数为`-4`，但数组长度为`3`），则会重置为从`0`开始。 

``` javascript
[1, 2, 3].includes(1, 0)	// true
[1, 2, 3].includes(1, 1)	// false
```



另外，Map 和  Set 数据结构有一个`has()`，需要注意与`includes()`区分。

- Map 结构的`has`方法，是用来**查找键名**的，比如`Map.prototype.has(key)`、`WeakMap.prototype.has(key)`、`Reflect.has(target, propertyKey)`

- Set 结构的`has`方法，是用来**查找值**的，比如`Set.prototype.has(value)`、`WeakSet.prototype.has(value)`



## 数组的空位

数组的空位指，数组的某一个位置没有任何值。

比如，`Array`构造函数返回的数组都是空位。

```javascript
Array(3) // [, , ,]
```



注意：空位不是`undefined`，一个位置的值等于`undefined`，依然是有值的。空位是没有任何值，`in`运算符可以说明这一点。

> 如果指定的属性在指定的对象或其原型链中，则**`in` 运算符**返回`true`。
>
> 语法
>
> ```
> prop in object
> ```
>
> 参数
>
> - `prop`
>
>     一个字符串类型或者 symbol 类型的属性名或者数组索引（非symbol类型将会强制转为字符串）。
>
> - `objectName`
>
>     检查它（或其原型链）是否包含具有指定名称的属性的对象。

``` javascript
0 in [undefined, undefined, undefined] // true
0 in [, , ,] // false
```



<span style='font-size:20px'>关于空位</span>

ES6 则明确将空位转为`undefined`



`Array.from()`方法会将数组的空位，转为`undefined`，也就是说，这个方法不会忽略空位

扩展运算符`...`也会将空位转为`undefined`

`copyWithin()`会连空位一起拷贝

`fill()`会将空位视为正常的数组位置

`for...of`循环也会遍历空位



由于空位的处理规则非常不统一，所以建议避免出现空位。



# 对象的扩展

## 属性的简洁表示法

ES6 允许直接写入变量和函数，作为对象的属性和方法

这样的书写更为简洁。



对象属性的简写：属性名与变量名相等时。

```javascript
const foo = 'bar';
const baz = {foo};	// 简写	

// 等同于
const baz = {foo: foo};	// 后一个 foo 是变量名
```

上面代码表明，ES6 允许**在对象中直接写变量**

这时，属性名变为变量名，属性值为变量的值

另一个例子

```javascript
function f(x, y) {
    return {x, y};	// 对象属性的简写
}
// 等同于
function f(x, y) {
    return {x: x, y: y}
}


f(1, 2)	// Object {x:1, y:2}
```



除了属性简写，方法也可以简写。

```javascript
const o = {
	method() {	// method 是方法名
        return :"Hello";
    }	
};

// 等同于

const o = {
    method: function() {
        return "Hello";
    }
};
```



这种写法用于**函数的返回值**，将会非常方便。

```javascript
function getPoint() {
	const x = 1;
    const y = 10;
    return {x, y};
}

getPoint()	// {x:1, y:10}
```



CommonJs 模块**?**输出一组变量，就非常适合使用简洁写法。

```javascript
let ms = {};

function getItem(key) {
    return key in ms ? ms[key] : null;
}

function setItem (key, value) {
    ms[key] = value;
}

function clear() {
    ms = {};
}

module.exports = { getItem, setItem, clear };
// 等同于
module.exports = {
    getItem: getItem,
    setItem: setItem,
    clear: clear
};
```



注意：简洁写法的属性名属于**字符串**，这会导致一些看上去比较奇怪的结果。

``` javascript
const obj = {
    class () {}
};

// 等同于

let obj = {
    'class':function() {}
};
```

上面代码中`class`是字符串，所以不会因为它属于关键字，而导致语法解析报错。



如果某个方法的值是一个 Generator 函数，前面需要加上`*`。

``` javascript
const obj = {
  * m() {
    yield 'hello world';
  }
};
```



## 属性名表达式

JS 定义对象的属性，有俩种方法。

``` javascript
// 一
obj.foo = true;

// 二
obj['a' + 'bc'] = 123;	// {abc: 123}
```

上面的方法一是直接用标识符作为变量名；方法二是用表达式作为属性名，这时要将表达式放在**方括号`[]`**之内。



但是，如果使用字面量方式定义对象（使用大括号），在 ES5 中只能使用方法一（标识符）定义属性。

``` javascript
let obj = {
    foo: true,
    abc: 123,
};
```

ES6 允许字面量定义对象时，用方法二（表达式）作为对象的属性名，即把表达式放在方括号内。

``` javascript
let propKey = 'foo';
let obj = {
    [propKey]: true,
    ['a' + 'bc']: 123,
}
```



表达式还可用于**定义方法名**。

``` javascript
let obj = {
    ['h' + 'ello']() {
        return 'hi';
    }
};

obj.hello()	// hi
```



注意：**属性名表达式与简洁表示法**，不能同时使用，会报错

``` javascript
// 报错
const foo = 'bar';
const bar = 'abc';
const baz = { [foo] };

// 正确
const foo = 'bar';
const baz = { [foo]: 'abc'};
```



注意：属性名表达式如果是一个对象，默认情况下会自动将对象转为字符串`[object Object]`

``` javascript
const keyA = {a: 1};
const keyB = {b: 2};

const myObject = {
  [keyA]: 'valueA',
  [keyB]: 'valueB'
};

myObject // Object {[object Object]: "valueB"}
```

上面代码中，`[keyA]`和`[keyB]`得到的都是`[object Object]`，所以`[keyB]`会把`[keyA]`覆盖掉，而`obj`最后只有一个`[object Object]`属性。



## 方法的 name 属性

函数的`name`属性返回函数名。对象方法也是函数，因此也有`name`属性



如果对象的方法使用了取值函数`getter`和存值函数`setter`==？==，则`name`属性不是在该方法上面，而是该方法的属性的描述对象的`get`和`set`属性上面，返回值是方法名前加上`get`和`set`

``` javascript
const obj = {
    get foo() {},
    set foo(x) {},
};

obj.foo.name	// TypeError: Cannot read property 'name' of undefined

const descriptor = Object.getOwnPropertyDesciptor(obj, 'foo');

descriptor.get.name	// "get foo"
descriptor.set.name	// "set foo"
```

==?==



有俩种特殊情况：**`bind()`**创造的函数==?==，`name`属性返回`bound`加上原函数的名字；  
**`Function`**构造函数创造的函数，`name`属性返回`anonymous`



如果对象的方法是一个 **Symbol **值，那么`name`属性返回的是这个 Symbol 值的描述。

``` javascript
const key1 = Symbol('description');	// ?
const key2 = Symbol();
let obj = {
  [key1]() {},
  [key2]() {},
};
obj[key1].name // "[description]"
obj[key2].name // ""
```



## Object.is()

ES5 比较俩个值是否相等，只有俩个运算符：相等运算符`==`和严格相等运算符`===`。  
它们都有缺点，前者会自动转换数据类型，后者的`NaN`不等于自身及`+0`等于`-0`。  
JS 缺乏一种运算，在所有环境中，只要俩个值是一样的，它们就应该相等。



ES6 提出“Same-value equality”（同值相等）算法，用来解决这个问题。`Object.is`就是部署这个算法的新方法。用来比较俩个值是否严格相等。

`Object.is()`与严格比较运算符`===`的行为基本一致。

``` javascript
Object.is('foo', 'foo')
// true
Object.is({}, {})
// false
```



不同之处只有俩个：一是`+0`不等于`-0`，二是`NaN`等于自身。



## Object.assign()

### 基本用法

`Object.assign()`用于**对象的合并**，将源对象（*source*）的所有可枚举属性复制到目标对象（*target*）

``` javascript
const target = {a: 1};
const source1 = {b: 2};
const source2 = {c: 3};

Object.assign(target, source1, source2);
target	// {a: 1, b: 2, c: 3}
```

`Object.assign()`的第一个参数是目标对象，后面所有参数都是源对象。



+ 注意：有同名属性的情况，后面的属性会覆盖前面的属性。



+ 只有一个参数的情况：

如果该参数不是对象，则会先转为对象然后返回。  
由于`undefined`、`null`无法转为对象，所以如果它们作为首参数就会报错。  



+ 非对象出现在源对象的位置（*即非首参数*）：

转为对象，  
如果无法转为对象则跳过，意味着`undefined`和`null`不在首参数，就不会报错。

``` javascript
const v1 = 'abc';
const v2 = true;
const v3 = 10;

const obj = Object.assign({}, v1, v2, v3);
console.log(obj); // { "0": "a", "1": "b", "2": "c" }
```

上面代码中，只有字符串合入目标对象以字符数组的形式，数值和布尔值都被忽略。这是因为这三个包装对象只有字符串的包装对象会产生可枚举属性。

> 包装对象：
>
> 所谓“包装对象”，指的是与数值、字符串、布尔值分别相对应的`Number`、`String`、`Boolean`三个原生对象，可以把原始类型的值包装成对象。
>
> ``` c
> var v1 = new Number(123);	// 包装对象
> var v2 = new String('abc');
> var v3 = new Boolean(true);
> 
> typeof v1 // "object"
> typeof v2 // "object"
> typeof v3 // "object"
> 
> v1 === 123 // false
> v2 === 'abc' // false
> v3 === true // false
> ```
>
> 即，这三个原生对象作为构造函数使用（*new*）时，可以将原始类型的值转为对象；作为普通函数使用时（*不带有`new`*），可以将任意类型的值，转为原始类型的值。



+ `Object.assign`拷贝的属性是有限的，只拷贝源对象的自身属性。  
    不拷贝继承属性，也不拷贝不可枚举的属性。



+ 属性名为 Symbol 值的属性，也会被`Object.assign`拷贝



### 常见用途

==。。。==

1.  <span style='font-size: 20px'>为对象添加属性</span>

``` javascript
class Point {
  constructor(x, y) {	// constructor?
    Object.assign(this, {x, y});
  }
}
```

上面方法通过`Object.assign`方法，将`x`属性和`y`属性添加到`Point`类的对象实例。



2. <span style='font-size:20px'>为对象添加方法</span>

     

3. <span style='font-size:20px'>克隆对象</span>



4. <span style='font-size:20px'>合并多个对象</span>



5. <span style='font-size:20px'>为属性指定默认值</span>



## 属性的可枚举性和遍历

### 可枚举性

描述对象的`enumerable`属性，称为”可枚举性“，如果该属性为`false`，表示某些操作会忽略当前属性。

> 描述对象：
>
> 对象的每个属性都有一个描述对象（Descriptor），用来控制该属性的行为。`Object.getOwnPropertyDescriptor`方法可以获取该属性的描述对象。
>
> ``` javascript
> let obj = { foo: 123 };
> Object.getOwnPropertyDescriptor(obj, 'foo')
> //  {
> //    value: 123,
> //    writable: true,
> //    enumerable: true,
> //    configurable: true
> //  }
> ```

目前有四个操作会忽略`enumerable`为`false`的属性。

- `for...in`循环：只遍历对象自身的和继承的可枚举的属性。

- `Object.keys()`：返回对象自身的所有可枚举的属性的键名。

- `JSON.stringify()`：只串行化对象自身的可枚举的属性。

- `Object.assign()`： 忽略`enumerable`为`false`的属性，只拷贝对象自身的可枚举的属性。



+ 关于继承：

以上四个操作中，只有`for...in`会返回继承的属性，其他三个方法都会忽略继承的属性，只处理对象自身的属性。  
实际上，引入“可枚举”（*`enumerable`*）这个概念的最初目的，就是让某些属性可以规避掉`for...in`操作，不然所有内部属性和方法都会被遍历到。比如，对象原型的`toString`方法，以及数组的`length`属性，就通过“可枚举性”，从而避免被`for...in`遍历到

另外，ES6 规定，所有 Class 的原型的方法都是不可枚举的

总的来说，操作中引入继承的属性会让问题复杂化，大多数时候，我们只关心对象自身的属性。所以，尽量不要用`for...in`循环，而用`Object.keys()`代替。

> **Object.keys()**：
>
> `Object.keys()`会返回一个由一个给定对象的自身可枚举属性组成的数组，数组中属性名的排列顺序和正常循环遍历该对象时返回的顺序一致 。



### 属性的遍历

ES6 一共有 5 种方法可以遍历对象的属性。

1. <span style='font-size: 20px'>for...in</span>
2. <span style='font-size: 20px'>Object.keys()</span>
3. <span style='font-size: 20px'>Object.getOwnPropertyNames()</span>
4. <span style='font-size: 20px'>Object.getOwnPropertySymbols()</span>
5. <span style='font-size: 20px'>Reflect.ownKeys()</span>

以上的 5 种方法遍历对象的键名，都遵守同样的属性遍历的次序规则。

- 首先遍历所有数值键，按照数值升序排列。

- 其次遍历所有字符串键，按照加入时间升序排列。

- 最后遍历所有 Symbol 键，按照加入时间升序排列。



## Object.getOwnPropertyDescriptors()

前面说过，`Object.getOwnPropertyDescriptor`方法会**返回指定对象属性的描述对象**（*descriptor*）。  
ES2017 引入了`Object.getOwnPropertyDescriptors`方法，**返回指定对象所有自身属性（非继承属性）的描述对象**。



==。。。==



## \_\_proto__属性，Object.setPrototypeOf()，Object.getPrototypeOf()

JavaScript 语言的对象继承是通过原型链实现的。ES6 提供了更多原型对象==？==的操作方法。



## \_\_proto__属性

`__proto__`属性（前后各两个下划线），用来读取或设置当前对象的`prototype`对象

==。。。==



# Symbol

## Symbol概述

**ES5 的对象属性名都是字符串**，这容易造成属性名的冲突。比如，你使用了一个他人提供的对象，但又想为这个对象添加新的方法（mixin 模式==？==），新方法的名字就有可能与现有方法产生冲突。如果有一种机制，保证每个属性的名字都是独一无二的就好了，这样就从根本上防止属性名的冲突。这就是 ES6 引入`Symbol`的原因。



ES6引入了一种新的原始**数据类型**`Symbol`，表示**全局唯一的值**。

下例帮助理解全局唯一的概念。以及[「每日一题」JS 中的 *Symbol* 是什么? - 知乎](https://www.baidu.com/link?url=TBQkAWIH5r-ic7n7f3Lw7VutEaVz8exxZf2Z9B6xXuPtc33C4giQWD-4kSNbRQTe&wd=&eqid=e52b28ea0002cf7c00000006606333f2)

``` js
	let s1 = {jack: 1};
    let s2 = {jack: 2};
    console.log(s1, s2);  // {jack: 1} {jack: 2}

    let jack = Symbol();
    let x1 = {[jack]: 1};
    let x2 = {[jack]: 2};
    console.log(x1, x2);  // {Symbol(): 1} {Symbol(): 2}

    console.log(x1[jack] === x2[jack]); // false
```



`Symbol` 是 JS 语言的第七种数据类型。前六种是：`undefined`、`null`、布尔值（Boolean）、字符串（String）、数值（Number）、对象（Object）。



Symbol 值通过**`Symbol`函数**生成。

这就是说，**对象的属性名**现在可以有俩种类型：

- 字符串
- 新增的 Symbol 类型：该属性名独一无二，可保证不会与其他冲突



```javascript
let s = Symbol();

typeof s  // "symbol"
```

上面代码中，变量`s`就是一个独一无二的的值。



注意，**`symbol`函数前不能使用`new`**命令，否则会报错

是因为生成的 Symbol 是一个原始类型的值，不是对象。也就是说。由于 Symbol 值不是对象，所以不能添加属性。基本上，它是一种类似于字符串的数据类型   



```javascript
let s1 = Symbol('foo');
let s2 = Symbol('bar');

s1 // Symbol(foo)
s2 // Symbol(bar)

s1.toString() // "Symbol(foo)"
s2.toString() // "Symbol(bar)"
```

上面代码中，`s1`和`s2`是两个 Symbol 值。如果不加参数，它们在控制台的输出都是`Symbol()`，不利于区分。有了参数以后，就等于为它们加上了描述，输出的时候就能够分清，到底是哪一个值。   



**如果 Symbol 的参数是一个对象**，就会调用该对象的**`toString()`**，**先将其转为字符串**，然后才生成一个 Symbol 值   

```javascript
const obj = {
  toString() {
    return 'abc';
  }
};
const sym = Symbol(obj);
sym // Symbol(abc)
```



注意，**`Symbol`函数的参数只是表示对当前 Symbol 值的描述**，因此相**同参数的`Symbol`函数的返回值是不相等**的

```javascript
// 没有参数的情况
let s1 = Symbol();
let s2 = Symbol();

s1 === s2 // false

// 有参数的情况
let s1 = Symbol('foo');
let s2 = Symbol('foo');

s1 === s2 // false
```



Symbol 值不能与其他类型值运算，会报错



Symbol 值可显式**转为字符串**

```javascript
let sym = Symbol('My symbol');

String(sym) // 'Symbol(My symbol)'
sym.toString() // 'Symbol(My symbol)'
```

> `String()`和`toString()`的异同
>
> 见[String 和 toString 的区别 - 简书](https://www.jianshu.com/p/ef898304ebdf)



Symbol 值也可转为**布尔值**，但不能转为**数值** 

```javascript
let sym = Symbol();
Boolean(sym) // true 
!sym  // false

if (sym) {
  // ...
}

Number(sym) // TypeError
sym + 2 // TypeError
```



## 作为属性名的 Symbol

由于每一个 Symbol 值都是不相等的，意味着 Symbol 值可以作为标识符，用于对象的属性名，就能保证不会出现重名的属性。

这对于一个对象由多个模块构成的情况非常有用，能防止一个键不小心改写或覆盖



```
let mySymbol = Symbol();

// 第一种写法
let a = {}；
a[mySymbol]: 'Hello!'

// 第二种写法
let a = {
  [mySymbol]: 'Hello!'
}

// 第三种
let a = {};
Object.defineProperty(a, mySymbol, { value: 'Hello!'});     

// 以上写法都得到同样结果
a[mySymbol] // "Hello!"
```

上面代码通过方括号结构和`Object.defineProperty`，将对象的属性名指定为一个 Symbol 值。

> Object.defineProperty
>
> 该方法允许精确地添加或修改对象的属性。默认情况下，使用`Object.defineProperty()`添加的属性是不可修改的。



注意，Symbol 值作为对象属性名时，不能用**点运算符`.`**

``` javascript
const mySymbol = Symbol();
const a = {};

a.mySymbol = 'Hello!';
a[mySymbol] // undefined
a['mySymbol'] // "Hello!"
```

因为点运算符后面总是字符串，所以不会读取`mySymbol`作为标识名所指代的那个值，导致`a`的属性名实际上是一个字符串，而不是个 Symbol 值。



同理，在对象的内部，使用 Symbol 值定义属性时，Symbol 值必须放在方括号之中。

``` js
let s = Symbol();
let obj = {
    [s](arg) {...}	// 属性名表达式
};
obj[s](123);
```



Symbol 类型还可以用于定义一组常量，保证这组常量的值都是不相等的。

``` javascript
const log = {};
log.levels = {
    DEBUG: Symbol('debug'),
    INFO: Symbol('info'),
  	WARN: Symbol('warn'),
};
console.log(log.levels.DEBUG, 'debug message');
console.log(log.levels.INFO, 'info message');
```



## Symbol属性名的遍历

Symbol 作为属性名，该属性不会出现在`for...in`、`for...of`循环中，也不会被`Object.keys()`、`Object.getOwnPropertyNames()`？、`JSON.stringify()`？返回。

但是，它也不是私有属性，有一个`Object.getOwnPropertySymbols`方法，可以获取指定对象的所有 Symbol 属性名。



**`Object.getOwnPropertySymbols`**方法返回一个数组，成员是当前对象的所有**用作属性名的 Symbol 值**

```
const obj = {};
let a = Symbol('a');  // a为属性名
let b = Symbol('b');

obj[a] = 'Hello';
obj[b] = 'World';

const objectSymbols = Object.getOwnPropertySymbols(obj);

objectSymbols   // [Symbol(a), Symbol(b)]
```



## Symbol.for()，Symbol.keyFor()

### Symbol.for()

有时，我们需要**使用同一个 Symbol 值**，`Symbol.for`方法可以做到这一点。

`Symbol.for`接受一个**字符串作参数**，然后搜索有没有以该参数作为名称的 Symbol 值

若有，返回这个 Symbol 值；若无，新建并返回一个以该字符串为名称的 Symbol 值

```
let s1 = Symbol.for('foo');
let s2 = Symbol.for('foo');

s1 === s2
```

上面代码中，`s1`和`s2`都是 Symbol 值，但它们都是同参数的`Symbol.for`生成的，所以实际上是同一个值。



**`Symbol.for()`****与****`Symbol()`**这两种写法，都会生成新的 Symbol

它们的区别是，`Symbol.for()`会被登记在全局环境中供搜索，`Symbol()`不会。`Symbol.for()`不会每次调用就返回一个新的 Symbol 类型的值，而是会先检查给定的`key`是否已经存在，如果不存在才会新建一个值。比如，如果你调用`Symbol.for("cat")`30 次，每次都会返回同一个 Symbol 值，但是调用`Symbol("cat")`30 次，会返回 30 个不同的 Symbol 值?。



### Symbol.keyFor()

```
Symbol.keyFor`方法返回一个已登记的 Symbol 类型值的`key
let s1 = Symbol.for("foo");
Symbol.keyFor(s1) // "foo"

let s2 = Symbol("foo");
Symbol.keyFor(s2) // undefined
```

上面代码中，变量`s2`属于未登记的 Symbol 值，所以返回`undefined`。?



## 内置的 Symbol 值

除了定义自己使用的 Symbol 值以外，ES6 还提供了 **11 个内置的 Symbol 值，指向语言内部使用的方法**

- [Symbol.hasInstance](https://www.yuque.com/ostwind/es6/docs-symbol#Symbol.hasInstance)
- [Symbol.isConcatSpreadable](https://www.yuque.com/ostwind/es6/docs-symbol#Symbol.isConcatSpreadable)
- [Symbol.species](https://www.yuque.com/ostwind/es6/docs-symbol#Symbol.species)
- [Symbol.match](https://www.yuque.com/ostwind/es6/docs-symbol#Symbol.match)
- [Symbol.replace](https://www.yuque.com/ostwind/es6/docs-symbol#Symbol.replace)
- [Symbol.search](https://www.yuque.com/ostwind/es6/docs-symbol#Symbol.search)
- [Symbol.split](https://www.yuque.com/ostwind/es6/docs-symbol#Symbol.split)
- [Symbol.iterator](https://www.yuque.com/ostwind/es6/docs-symbol#Symbol.iterator)
- [Symbol.toPrimitive](https://www.yuque.com/ostwind/es6/docs-symbol#Symbol.toPrimitive)
- [Symbol.toStringTag](https://www.yuque.com/ostwind/es6/docs-symbol#Symbol.toStringTag)
- [Symbol.unscopables](https://www.yuque.com/ostwind/es6/docs-symbol#Symbol.unscopables)



# Iterator 接口 和 for...of 循环

## Iterator的概念

Iterator（遍历器）



表示“集合”的数据结构，有`Array`、`Object`、`Map`、`Set`四种数据集合。用户可以组合使用它们，定义自己的数据结构，比如 *数组的成员是**`Map`**，**`Map`**的成员是对象 。*这样就需要一种统一的接口机制，来处理不同的数据结构。

遍历器（lterator）就是这样一种机制



Iterator 是一种**接口**，为各种不同的数据结构提供统一的访问机制，任何数据结构只要部署 Iterator 接口，就可以完成遍历操作

[^遍历操作]: 依次处理该数据结构的所有成员



**Iterator 的作用**有三个：

- 为各种数据结构，提供一个统一、简便的访问接口
- 使得数据结构的成员能按某种次序排列
- ES6 创造了一种新的遍历命令`for...of`循环，iterator 接口主要供`for...of`消费



**Iterator 的遍历过程**是这样的：

1. 创建一个**指针对象**，指向当前数据结构的起始位置。也就是说，遍历器对象本质上就是一个指针对象
2. 第一次调用指针对象的**`next`**方法，可将指针指向数据结构的第一个成员
3. 第二次调用指针对象的`next`方法，指针指向数据结构的第二个成员
4. 不断调用指针对象的`next`方法，直到它指向数据结构的结束位置



**Iterator 的作用机制**

每一次调用`next`，都会返回数据结构的当前成员的信息

具体来说，就是返回一个包含`value`和`done`两个属性的对象。`value`是当前成员的值，`done`是个布尔值，表示遍历是否结束

指针对象的`next`方法，用来移动指针                                                                               



## 默认 Iterator 接口

当使用`for...of`循环遍历某种数据结构时，该循环会自动去寻找 Iterator 接口。



一种数据结构只要部署了 Iterator 接口，我们就称这种数据结构是“可遍历的”（**iterable**）



ES6规定，默认的 Iterator 接口部署在数据结构的`Symbol.iterator`？属性，或者说，一个数据结构只要具有**`Symbol.iterator`**属性，就可以认为是“可遍历的”（iterable）

`Symbol.iterator`属性本身是一个**函数**，就是当前数据结构**默认的遍历器生成函数**？。执行这个函数，就会返回一个遍历器

至于**属性名****`Symbol.iterator`**，它是一个表达式，**返回**`Symbol`**对象的**`iterator`**属性，这是一个预定义好的？、类型为`Symbol`的特殊值，所以要放在方括号内（参见《[Symbol.iterator](https://www.yuque.com/ostwind/es6/docs-symbol#Symbol.iterator)》一章）？



# set 和 map 数据结构

## set 数据结构

set 数据结构类似于数组，但里面的值是唯一的   

```
console.log(new Set([1, 1, 2, 4]));   //{1, 2, 4}
```

> **ArrayFrom**
>
> - 部署了`Iterator`对象 如`Set`  
>
> 
>
> - 将类数组对象 *有**`length`**属性* 转化成数组

```
let obj = {
    0: 'a',
    1: 'b',
    2: 'c',
    length: 3
}
console.log(Array.from(obj));   //["a", "b", "c"]
```

### 将set结构转为数组

- `ArrayFrom`可将`set`结构转为数组结构

```
console.log(new Set([1, 1, 2, 4]));          //{1, 2, 4}
console.log(ArrayFrom(new Set([1, 1, 2, 4])); //[1, 2, 4]
```

这就提供了**数组去重**的一个方法

```
function dedupe(array) {
  return Array.from(new Set(array));
}

dedupe([1, 1, 2, 3]) // [1, 2, 3]
```





- 展开结构`...`   ？

```
console.log(new Set([1, 1, 2, 4]));   
console.log([...new Set([1, 1, 2, 4])]);
```



### set结构的size属性

`set`的`size`属性是去重后的长度

```
let set = new Set([1, 1, 2]);

console.log(set.size);  //2
```



### set结构的方法

#### set结构的操作方法

Set 实例的方法分为两大类：操作方法（用于操作数据）和遍历方法（用于遍历成员）。下面先介绍四个操作方法。



- `add(value)`：添加某个值，返回 Set 结构本身
- `delete(value)`：删除某个值，返回一个布尔值，表示删除是否成功
- `has(value)`：返回一个布尔值，表示该值是否为`Set`的成员
- `clear()`：清除所有成员，没有返回值



```
s.add(1).add(2).add(2);
// 注意2被加入了两次

s.size // 2

s.has(1) // true
s.has(2) // true
s.has(3) // false

s.delete(2);
s.has(2) // false
```



下面是一个对比，看看在**判断是否包括一个键**上面，`Object`结构和`Set`结构的写法不同。

```
// 对象的写法
const properties = {
  'width': 1,
  'height': 1
};

if (properties[someName]) {
  // do something
}

// Set的写法
const properties = new Set();

properties.add('width');
properties.add('height');

if (properties.has(someName)) {
  // do something
}
```



#### set结构实例？的遍历方法

set结构的实例有四个遍历方法，可以用于遍历成员。

- `keys()`：**返回键名**的遍历器
- `values()`：**返回键值**的遍历器
- `entries()`：**返回键值对**的遍历器
- `forEach()`：使用**回调函数遍历每个成员**

**
**

**keys()，values()，****entries()**

`keys`方法、`values`方法、`entries`方法返回的都是遍历器对象（详见《Iterator 对象》一章）。由于 Set 结构没有键名，只有键值（或者说键名和键值是同一个值），

所以`keys`方法和`values`方法的行为完全一致 

```
let set = new Set(['red', 'green', 'blue']);

for (let item of set.keys()) {
  console.log(item);
}
// red
// green
// blue

for (let item of set.values()) {
  console.log(item);
}
// red
// green
// blue

for (let item of set.entries()) {
  console.log(item);
}
// ["red", "red"]
// ["green", "green"]
// ["blue", "blue"]
```

上面代码中，`entries`方法返回的遍历器，同时包括键名和键值，

所以`entries`方法每次输出一个数组，它的两个成员完全相等



### set结构的应用——求交并差集

```
let a = new Set([1, 2, 3]);
let b = new Set([2, 3, 4]);

//求并集：合并为新set
let unique = new Set([...a, ...b]); //展开运算符

//求交集：has、filter
let inter = new Set([...a].filter(x=>b.has(x)));

//求差集:has
let dif = new Set([...a].filter(x=>!b.has(x)));
```



## WeakSet

WeakSet 结构与 Set 类似，也是不重复值的集合。

[---Set 和 Map 数据结构 · 语雀---](https://www.yuque.com/ostwind/es6/docs-set-map)

## map数据结构

更详细的：[---Set 和 Map 数据结构 · 语雀---](https://www.yuque.com/ostwind/es6/docs-set-map#43a7e51d)

### map的基本用法

js的对象本质上是**键值对**的集合（Hash结构 ？），但传统上只能用字符串当作键

给他的使用带来了极大的限制。

```
const data = {};
const element = document.getElementById('myDiv');

data[element] = '元素'
data['[object HTMLDivElement]'];  //"元素"
```

上面代码本意是 将一个 DOM 节点作为对象`data`的键，但由于**对象只接受字符串作为键****名**，所以`element`将自动转为字符串`[object HTMLDivElement]`。



为了解决这个问题，ES6 提供了 Map 数据结构。

Map 类似于对象，也是键值对的集合，但键的范围包括各种类型的值 *包括对象*

也就是说，Object 结构提供了“字符串—值”的对应，Map 结构提供了“值—值”的对应，是一种更完善的 Hash 结构？实现。如果你需要“键值对”的数据结构，Map 比 Object 更合适。



对象可以作为 Map 结构的键

```
const m = new Map();          // 声明一个Map数据结构 m
const o = {p: 'Hello World'}; // 声明一个Object结构 o

m.set(o, 'content')
```

上面代码中使用 Map 结构的`set`方法，将对象`o`当作`m`的一个键



函数也可作为 Map 结构的键

```
const m = new Map();
const hello = function() {console.log('hello');};

m.set(hello, 'Hello ES6!') // 键是函数
m.get(hello)  // Hello ES6!
```



### Map结构实例的属性和操作方法

- **size 属性**



- **set（key, value）**   

设置键名`key`对应的键值为`value`，返回整个 Map 结构

```
const map = new Map();
map.set('foo', true);
map.set('bar', false);

map.size  // 2
```



`set`返回的是当前的 Map 对象，因此可用链式写法

```
let map = new Map()
  .set(1, 'a')
  .set(2, 'b')
  .set(3, 'c');
```



- **get(key)**

```
get`方法读取`key`对应的键值，如果找不到`key`，返回`undefined
const m = new Map();
const hello = function() {console.log('hello');};

m.set(hello, 'Hello ES6!') // 键是函数

m.get(hello)  // Hello ES6!
m.get(hi)     // undefined
```



- **has(key)**   返回布尔值



- **delete(key)**

删除某个键，返回`true`，删除失败？ 返回`false`



- **clear()**   清除所有成员，无返回值     *`map.clear()`**
    *

###  

### Map结构的遍历方法

Map 结构原生？提供三个**遍历器生成函数**和一个**遍历方法**   *方法名同 Set*

- **`keys()`**：**返回键名**的遍历器
- **`values()`**：**返回键值**的遍历器
- **`entries()`**：**返回所有成员**的遍历器
- **`forEach()`**：**遍历 Map** 的所有成员



需特别注意的是， **Map 的遍历顺序就是插入顺序**

**
**

keys() 、 values()、entries() 方法 用法类似

```
const map = new Map([   // ?
  ['F', 'No'],
  ['T', 'Yes'],
]);

// keys()方法
for (let key of map.keys()) {
  console.log(key);
}   
//"F"
//"T"

// entries()方法
for (let item of map.entries() ) {
  console.log(item[0], item[1]);
}
// 或者
for (let [key, value] of map.entries() ) {
  console.log(key, value);
}
// 下例等同于使用map.entries()
for (let [key, value] of map) {
  console.log(key, value);
}
//"F" "No"
//"T" "Yes"
```

上面代码最后的那个例子，表示 Map 结构的默认遍历器接口（`Symbol.iterator`属性）?，就是`entries`方法。

```
map[Symbol.iterator] === map.entries
// true
```



### Map与其他数据结构的互相转换



## WeakMap

# 面向对象   ?

- 关键字class
- 构造器和类分开
- class里直接加方法



## 继承



# Promise

## 学前准备

### 区分实例对象与函数对象



### 两种类型的回调函数

<span style='font-size: 20px'>什么是回调函数</span>

<hr>

回调函数，参数是函数，作为算法传入而不是作为数据。  
概括来说就是：在直接调用函数A()时，把另一个函数B()作为参数，传入函数A()里面，以此来通过函数A()间接调用函数B()。

更多移步[从零起步，真正理解Javascript回调函数 - SegmentFault 思否](https://segmentfault.com/a/1190000021942060)

满足下列条件：

1. 自己定义的。
2. 回调函数一般不亲自调用，但会执行。



<span style = 'font-size:20px'>同步回调函数</span>

``` js
const arr = [1, 3, 5]
arr.forEach(item => {	/* 遍历的回调函数，同步回调 */
    console.log(item);
})
console.log('forEach()之后');

/* 
输出结果： 
	1
	3
	5
	forEach()之后
*/

```

上面代码中，`forEach`内的回调函数都执行完后`forEach`才执行结束，因此是同步回调函数。  
同步回调函数不会放入队列，一上来就执行完。



<span style = "font-size: 20px">异步回调函数	</span>

<hr>

``` js
setTimeout(() => {	// 异步回调函数，会放入队列中来进行
    console.log('timeout callback()');
}, 0);
console.log('setTimeout()之后');

/*
	输出结果:
	setTimeout()之后
	timeout callback()
*/
```

即使`setTimeout`时间设置的是`0`。



### JS 的 error 处理

#### 常见的内置错误

<span style="font-size:18px">ReferenceError</span>：引用变量不存在

+ <span style="font-size:18px">TypeError</span>：数据类型不正确

+ <span style="font-size:20px">RangeError</span>：数据值不在其允许的范围内

    ``` js
    /* 递归调用 */
    function fn() {
        fn();
    }
    fn();	// RangeError: Maximum call stack size exceeded
    ```

+ <span style="font-size:18px">SyntaxError</span>：语法错误



#### 错误处理

<span style="font-size: 20px">捕获错误</span>: try ... catch

``` js
/* 对try{ }里面的错误进行捕获 */
  try {
    let d;
    console.log(d.xxx)
  } catch (err) { // error是对象，名称自定义
    console.log(err.message);
    console.log(err.stack);	// 用的更多，因为更全面
  }
  console.log("出错之后");
/* 
	输出：
	Cannot read property 'xxx' of undefined
	TypeError: Cannot read property 'xxx' of undefined 	at test.html:14
	出错之后
*/
```

因为程序的错误已经被捕获处理，所以后面的代码依然会执行。



<span style="font-size:20px">抛出错误</span>: throw error==?==

 ``` js
  /* 函数功能：在奇数时间执行，偶数时抛错 */
  function foo(bar) {
    if (bar % 2 == 1) {
     console.log("当前数奇数，可以执行");
    } else {
      throw new Error('当前数偶数，无法执行');  // 抛出异常，让下一级处理
    }
  }
  
  try {
    foo(2);
  } catch (error) {
    console.log(error.message);
  }

  foo(1);
  console.log("错误之后");
 ```



## 什么是 Promise

`Promise`是异步编程的一种解决方案，ES6 原生提供了`Promise`对象。

Promise 对象用于一个异步操作的最终完成或失败和其结果值 的表示。简单来说，就是用于处理异步操作，异步处理成功就执行成功的操作；失败就捕获错误或者停止后续操作。

>  同步和异步
>
> **异步：**操作之间没有关系，同时进行多个操作   代码更复杂
>
> **同步：**同时只做一件事                     代码简单



从语法上来说：Promise 是一个构造函数

从功能上来说：Promise 对象用来封装一个异步操作并可以获取其结果

成功的结果数据一般称为 value，失败的结果数据一般称为 reason。



`Promise`对象有以下俩个**特点**：

1. `Promise`对象的**状态不受外界影响**。`Promise`对象代表一个异步操作，有三种状态：`pending`（*进行中*）、`fulfilled`（*已成功*）和`rejected`（*已失败*）。只有异步操作的结果，可以决定当前是哪一种状态，任何其他操作都无法改变这个状态。这也是`Promise`名字的由来，表示其他手段无法改变。

    

2. **状态一旦改变，就不会再变**，任何时候都可以得到这个结果。`Promise`对象的状态改变只有俩种可能：从**`pending`**变为**`fulfilled`**和从**`pending`**变为**`rejected`**。只要这两种情况发生，状态就凝固了，不会再变了，会一直保持这个结果，这时就称为 resolved（*已定型*）。  
    如果改变已经发生了，再对`Promise`对象添加回调函数，也会立即得到这个结果。这与事件（*Event*）不同，事件的特点是，如果你错过了它，再去监听是得不到结果的。



为了行文方便，本章后面的`resolved`==？==统一只指`fulfilled`状态，不包含`rejected`状态。



有了`Promise`对象，就可以将异步操作以同步操作的流程表达出来，避免了层层嵌套的回调函数。此外`Promise`对象提供统一的接口==？==，使得控制异步操作更加容易。

> 简单理解 Promise
>
> Promise 就是把一层一层向内嵌套调用的方法，给拉成一串连续调用的方法。每个嵌套调用的方法都向调用者返回 this，也就是返回自身；然后带着上一次调用的结果进入下一个嵌套调用的环节



`Promise`也有一些缺点。首先，无法取消`Promise`，一旦新建它就会立即执行，无法中途取消。其次，如果不设置**回调函数**，`Promise`内部抛出的错误，不会被`try/catch`捕获。代码一旦开始以异步模式执行，则唯一与之交互的方式就是`Promise`的方法。



如果某些事件不断地反复发生，使用 [Stream](https://nodejs.org/api/stream.html) 模式是比部署`Promise`更好的选择。



<h2>基本用法</h2>

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



`new Promise`的时候里面内容会立即执行。因而为了能实现调用时执行，Promise 一般都是作为函数的返回值

## Promise.resolve()

Promise 并非要一开始就处于 pending 状态，再通过**执行器函数**转换状态。

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

实例化一个 rejected 的 Promise并抛出一个异步错误。类似于`throw()`，因为它们都代表一种程序状态，即需要中断或者特殊处理。    
这个错误不能被 try/catch 捕获，而只能通过**拒绝处理程序**捕获。对应的错误对象会成为拒绝的理由。

reject 的 reason 就是传给 `Promise.reject()`的第一个参数，这个参数也会传给后续的拒绝处理程序。

``` js
let p = Promise.reject(3);
setTimeout(console.log, 0, p);	// Promise {<rejected>: 3}

p.then(undefined, (reason) => setTimeout(console.log, 0, reason) );	// 3
```


与`Promise.resolve()`不同的是，`Promise.reject()`并没有照搬它的幂等逻辑。如果传给`Promise.reject()`一个 Promise 对象，则这个 Promise 对象会成为它返回的 rejected 的 reason



在 Promise 中抛出错误时，因为错误实际上是从消息队列中异步抛出的，所以并不会阻止运行时继续执行同步指令

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
这些方法可以访问异步操作返回的数据，处理 Promise 成功和失败的结果，连续对 Promise 求值，或添加只有 Promise 进入终止状态时才会执行的代码



### Promise.prototype.then()

>  实现 Thenable 接口
>
> 在暴露的异步结构中，任何对象都有一个`then()`方法。这个方法实现了 ==Thenable？== 接口



+ 作用：`Promise.prototype.then()`是为 Promise 实例添加**处理程序**（*Promise 状态改变时的回调函数*）的主要方法



+ 参数：`Promise.prototype.then()`最多接收两个**函数类型**的参数：onResolve 处理程序、onRejected 处理程序。这两个参数都是可选的。会在 Promise 分别进入 resolved、rejected 状态时执行

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



`then()`返回一个新的`Promise`实例，注意不是原来那个`Promise`实例。因此可以采用链式写法，即`then()`后面再调用另一个`then()`。	==？==

``` javascript
getJSON("/posts.json").then(function(json) {
    return json.post;
}).then(function(post) {
    // ...
});
```

==代码的作用在？==

上面的代码使用`then`方法，依次指定了俩个回调函数。第一个回调函数完成以后，会将返回结果作为参数传入第二个回调函数。



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

==。。。==

## Promise 连锁与 Promise 合成

多个 Promise 组合在一起可以构成强大的代码逻辑。可以通过两种方式实现：Promise 连锁与 Promise 合成。前者是一个 Promise 接一个 Promise 地拼接，后者将多个 Promise 组合成一个 Promise。



<span style="font-size:20px">Promise Promise 连锁</span>

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

==。。。==

# generator

## **generatator函数**

 普通函数：一路到底

generaor函数：中间能停



普通函数

```
function show() {
  alert('a');
  
  alert('b');
}
```



**generatator函数**

```
*`不能同时贴着俩边 如`function*show() {
```

`yield `*放弃——暂时不执行*

```
function * show() {
  alert('a');
  yeild;
  alert('b');
}

let genObj = show();

genObj.next();
genObj.next();
```

上面代码中，第九行执行`alert('a');`第十行执行`alert('b');` *踹一步走一步*



最适用场景：代码中间需请求数据时 *`ajax`*




## yeild

### yeild传参

**yeild**也能**传参**

```
function * show() {
  alert('第一部分');
  
  let a = yeild;
  
  alert('第二部分');
  alert(a);
}
let genObj = show();
genObj.next(12);  //第一部分
genObj.next(5);   //第二部分
                  //5
```

也就是说，第一个`next`没法给`yeild`传参

要想给第一部分传参，用普通函数传参的方法。

```
function *show(num1, num2) {
  alert(`${num1},${num2}`);   //模板字符串
  alert('第一部分');
  
  let a = yeild;
  
  alert('第二部分');
  alert(a);
}
let genObj = show(9, 8);
genObj.next(12);  //9,8
                  //第一部分
genObj.next(5);   //第二部分
                  //5
```



### yeild返回中间结果

yeild可以返回中间结果

```
function *show() {
  alert('第一部分');
  
  yeild 12;
  
  alert('第二部分');
  return 5;
}

let gen = show();
let res1 = gen.next();
let res2 = gen.next();

console.log(res1);  //{value：12， done：false}
console.log(res2);  //{value: 5, done：true}
```

上面代码中，`res1`的结果由`yeild`返回，`res2`——最后一步 的结果由`generator`函数的`return`返回。

返回的是个`json`，`done`表示函数是否完成



图解：

![image.png](https://cdn.nlark.com/yuque/0/2021/png/2617721/1612246363915-0365e2dc-5164-42b0-afc7-b7e0ec3fe69f.png)

function*炒菜(菜市场买回来的)

洗菜->洗好的菜

let干净的菜-yield洗好的菜;

干净的菜->切->丝

let切好的菜-yield丝;

切好的菜->炒->熟的菜

熟的菜;

return

![image.png](https://cdn.nlark.com/yuque/0/2021/png/2617721/1612246411952-8e565761-175e-4a0f-bba4-f825e1f0e8d9.png)function炒菜(菜市场买回来的)洗菜->洗好的菜1et干净的菜yield,洗好的菜;干净的菜->切->丝let切好的菜yield丝切好的菜->炒->熟的菜熟的菜;eturn



# async 函数

ES7 引入了 async 函数，使得异步操作变得更加方便。

简单来说，它就是 Generator 函数的语法糖。比较后会发现，async 函数就是将 Generator 函数的` *` 替换成 `async`，将`yield`替换成`await`。



async 函数对 Generator 函数的改进，体现在以下四点：

1. 内置执行器

    Generator 函数的执行必须靠执行器，所以才有了 co 模块==？==，而 async 函数自带执行器。也就是说，async 函数的执行与普通函数一样只需一行

    ``` js
    asyncReadFile();
    ```

    上面的代码调用了`asyncReadFile`函数，然后它就会自动执行，输出最后结果。  
    这完全不像 gnerator 函数，需要调用`next`方法，或者用`co`模块，才能真正执行，得到最后结果。

2. 更好的语义

    `async`和`await`，比起`*`和`yield`，语义要更清楚了。  
    `async`表示函数有异步操作，`await`表示紧跟在后面的表达式需要等待结果。

3. 更广的适用性

    `co`模块约定，`yield`命令后面只能是 Thunk 函数==？==或 Promise 对象，  
    而`async`函数的`await`命令后面，可以是 Promise 对象和原始类型的值（*数值、字符串和布尔值*），但这时等同于同步操作。	==？==

4. 返回值是 Promise 

    async 函数的返回值是 Promise 对象，这比 Generator 函数的返回值是`Iterator`对象方便多了。可以用`then`方法指定下一步的操作。
   
   进一步说，async 函数完全可以看作多个异步操作包装成的一个 Promise 对象，而`await`命令就是内部`then`命令的语法糖，



## 语法

### 返回 Promise 对象

+ `async`函数返回一个 Promise 对象，内部**`return`**返回的值会成为`then`方法回调函数的参数

    ``` js
    async function f() {
        return 'hello';
    }
    
    f().then(v => console.lof(v))	// 'hello'
    ```

    上面代码中`return`的`hello`传给了回调函数内的参数`v`。



+ async 函数内部**抛出错误**会导致返回的 Promise 对象变为`reject`状态，抛出的错误对象会被`catch`方法回调函数接收到



### Promise 对象状态变化

async 函数返回的 Promise 对象，必须等内部所有`await`命令后面的 Promise 对象执行完，才会发生**状态改变**（*则执行 `then` 函数*），除非遇到`return`语句或抛出错误。  
也就是说，只有 async 函数内部的异步操作执行完，才会执行`then`方法指定的回调函数

下面是一个例子。

``` jsx
async function getTitle(url) {
    let response = await fetch(url);
    let html = await response.text();	// ?
    return html.match(/<title>([\s\S]+)<\/title>/i)[1];	// ?
}
getTitle('https://tc39.github.io/ecma262/').then(console.log)
// "ECMAScript 2017 Language Specification"
```

上面代码中，函数`getTitle`内部有三个操作：抓取网页、取出文本、匹配页面标题。只有这三个操作全部完成，才会执行`then`方法里的`console.log`。



## await 命令

+ 正常情况下，`await`命令后面是一个 Promise 对象。若不是，会被转成一个立即`resolve`的 Promise 对象

    ``` js
    async function f() {
        return await 123;
    }
    
    f().then(v => console.log(v))	// 123
    ```



+ `await` 命令后面的 Promise 对象如果变为`reject`状态，则`reject`的参数会被`catch()`的回调函数接收到

    只要一个`await`语句后面的 Promise 变为`reject`，那么整个`async`函数都会中断执行

    有时，我们希望即使前一个异步操作失败也不中断后面的异步操作。这时可以将**第一个`await`放在`try...catch`结构内**。这样不管这个异步操作是否成功，第二个`await`都会执行。

    ``` js
    async function f() {
        try {
            await Promise.reject('出错了');
        } catch(e) {
        }
        return await Promise.resolve('hello world');
        }
    
    f()
    .then(v => console.log(v))	// hello world
    ```

    另一种方法是`await`后面的 Promise 对象再跟一个`catch`方法，处理前面可能出现的错误



## 错误处理

如果`await`后面的异步操作出错，那么等同于`async`函数返回的 Promise 对象被`reject`

==。。。==



## 使用注意点

+ 前面已经说过，`await`拿的是`resolve`的数据，`reject` 的需要靠`try...catch`。`await`命令后面的 Promise 对象，运行结果可能是`rejected`，所以最好把`await`命令放在`try...catch`结构块



+ 多个`await`命令后面的异步操作，如果不存在继发关系，最好让它们同时触发

    ``` js
    let foo = await getFoo();
    let bar = await getBar();
    ```

    上面代码中，`geetFoo`和`getBar`是两个独立的异步操作（*即互不依赖*），被写成继发关系。这样比较耗时，因为只有`getFoo`完成后才会执行`getBar`。完全可以让它们同时触发。

    ``` js
    // 写法一
    let [foo, bar] = await Promise.all([getFoo(), getBar()]);
    
    // 写法二
    let fooPromise = getFoo();
    let barPromise = getBar();
    let foo = await fooPromise;
    let bar = await barPromise;
    ```

    上面两种写法，`getFoo`和`getBar`都是同时触发，这样就会缩短程序的执行时间。	==？==



+ `await`命令只能用在 async 函数之中，如果用在普通函数会报错

    ``` js
    async function dbFuc(db) {
        let docs = [{}, {}, {}];
    	// 报错
        docs.forEach(function (doc) {
            await db.post(doc);
        });
    }
    ```



## 异步 Generator 函数

就像 Generator 函数返回一个同步遍历器对象一样，异步 Generator 函数的作用，是**返回一个异步遍历器对象**。



在语法上，异步 Generator 函数就是`async`函数与 Generator 函数的结合。

``` js
async function* gen() {
    yield 'hello';
}
const genObj = gen();
genObj.next().then(x => console.log(x));	// ?
// { value: 'hello', done: false }
```

上面代码中，`gen`是一个异步 Generator 函数，执行后返回一个异步 Iterator 对象。对该对象调用`next`方法，返回一个 Promise 对象。	==?==



异步遍历器的设计目的之一，就是 Genreator 函数处理同步操作和异步操作时能够使用同一套接口。 ==？==

``` js
// 同步 Generator 函数
function *map(iterable, func) {
    const iter = iterator[Symbol.iterator]();
    while (true) {
        const {value, done} = iter.next();
        if (done)
            break;
        yield func(value);
    }
}

// 异步 Generator 函数
async function *map(iterable, func) 
{
    const iter = iterable[Symbol.asyncIterator]();
    while (true) {
        const {value, done} = await iter.next();
        if (done)
            break;
        yield func(value);
    }
}
```



==写到这了==

# Class 基本用法

JS 语言中，生成实例对象的传统方法是通过构造函数，跟传统的面向对象语言（*如 C++、Java*）差异很大，很容易让新学习这门语言的程序员感到困惑。

ES6 提供了更接近传统语言的写法，引入了 Class 这个概念，作为对象的模板。通过`class`关键字，可以定义类

基本上，ES6 的`class`可以看作只是一个语法糖，它的绝大部分功能，ES5 都可以做到，新的`class`写法只是让对象原型的写法更加清晰、更像面向对象编程的语法而已。



## Class 的定义

实际上，类是特殊的函数，与函数定义相似，定义类也有两种主要方式。

1. <span style="font-size:20px">类声明</span>

    使用带有`class`关键字的类名。

    ``` js
    class Person {/* ... */}
    ```

    

2. <span style="font-size:20px">类表达式</span>

    类表达式可以命名或不命名。**类表达式的名称**只是该类体的局部名称，但可以在类表达式作用域内部通过类（*而不是实例*）的`name`属性取得它

    下面的写法叫做把表达式赋值给变量`Person`.

    ``` js
    const Person = class PersonName {	// 类名是 Person
     identify() {
         console.log(Person.name, PeisonName.name);
     }   
    }
    
    let p = new Person();
    ```

    

    如果类的内部没用到的话，可以省略类表达式的名称，也就是写成下面的形式。

    ``` js
    const Myclass = class {/* ... */};
    ```

    

    采用 Class 表达式，可以写出**立即执行的 Class**。

    ``` js
    let person = new Class {
        constructor(name1) {
            this.name2 = name1;
        }
    
    	sayName() {
        	console.log(this.name2);
    	}
    }('张三')
    
    person.sayName();
    ```

    > **this**
    >
    > `this`永远指向最后调用它的那个对象

    

    

+ 与函数定义不同的是，Class **不存在变量提升**



+ 与函数构造函数一样，多数编程风格都建议类名的首字母大写，以区别于通过它创建的实例

    （*比如通过 `class Foo {}`来创建实例 `foo`*）



+ Clss 受块作用域限制

    ``` js
    {
        function FunctionDeclaration() {}
        class ClassDeclaration {}
    }
    
    console.log(ClassDeclaration);	// ReferenceError: ClassDeclaration is not defined
    ```

    



``` javascript
// 定义类
class Point {
    constructor(x, y) {	
        this.x = x;
        this.y = y;
    }
    toString() {
        return '(' + this.x + this.y + ')';
    }
}
```

上面代码定义了一个“类”，里面有一个[`constructor`方法](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Classes/constructor)，这就是构造方法。而`this`关键字代表实例对象。也就是说，ES5 的构造函数 `Point`对应ES6 的`Point`类的构造方法。



`Point`类除了构造方法外，还定义了一个`toString`方法。注意，定义”类“的方法的时候，前面不需要加`function`关键字，直接把函数定义放进去就可以。另外，方法之间不需要`,`分隔。

ES6 的类，完全可以看作构造函数的另一种写法。

``` javascript
class Point {
    // ...
}

typeof Point // function
Point === Point.prototype.constructor	// true
```

上面代码表明，类的数据类型就是函数，类本身就指向构造函数。



使用时，也是直接对类使用`new`命令，跟构造函数的用法完全一致。

``` javascript
class Foo {
    doStuff() {
        console.log('stuff');
    }
}

let f = new Foo();
f.doStuff()	// stuff
```



构造函数的`prototype`属性，在 ES6 的“类”上面继续存在。事实上，“类”的所有方法都定义在类的`prototype`属性上面。



在类的实例上面调用方法，其实就是调用原型上的方法。

``` javascript
class B {}
let b = new B();

b.constructor === B.prototype.constructor	// true
```

上面代码中，`b`是`B`类的实例，它的`constructor`方法就是`B`类原型的`constructor`方法。



由于类的方法都定义在`prototype`对象上面，所以类的新方法可以添加在`prototype`对象上面。`Object.assign`方法可以很方便地一次向类添加多个方法。

``` javascript
class Point {
    constructor() {
        // ...
    }
}

Object.assign(Point.prototype, {	
    toString() {},
    toValue() {},
});
```

> Object.assign(target, ...source)
>
> 参数：`target`：目标对象；`sources`：源对象
>
> 用于将所有可枚举属性的值从一个或多个源对象（*sources*）分配到目标对象（*target*）
>
> [Object.assign() - JavaScript | MDN](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Object/assign)



`prototype`对象的**`constructor`属性**，直接指向类的本身，这与 ES5 的行为是一致的。

``` javascript
Point.prototype.constructor === Point	// true
```

> constructor 属性
>
> JavaScript中constructor属性指向创建当前对象的构造函数。
>
> [JavaScript中constructor属性 - SegmentFault 思否](https://segmentfault.com/a/1190000013245739)



## 严格模式

类和模块的内部，默认就是严格模式，所以不需要使用`use strict`指定运行模式



考虑到未来所有代码其实都是运行在模块之中，所以 ES6 实际上把整个语言升级到了严格模式



## constructor 方法

`constructor`关键字用于在类定义块内部创建类的构造函数。JS 解释器在使用`new`创建类的新实例会自动调用这个函数。`constructor()`是类的默认方法，如果没有显式定义，相当于将构造函数定义为空函数。



+ `constructor()`会默认返回实例对象（*即 this*），但也完全可以指定返回其他对象。	

``` js
class Foo {
    constructor() {
        return Object.create(null);	// ?
    }
}

new Foo() instanceof Foo	// false
```

> [instanceof](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Operators/instanceof)
>
> **`instanceof`** **运算符**用于检测构造函数的 `prototype` 属性是否出现在某个实例对象的原型链上。
>
> 语法：`object instanceof constructor`。其中`object`：某实例对象；`constructor`某构造函数



+ 类必须用`new`调用，否则报错。这是`constructor`跟普通构造函数的一个主要区别，普通构造函数不用`new`也可以执行。



## 类的实例化

生成类的实例对象的写法也是使用`new`命令。

使用**`new`**调用类的构造函数会执行如下操作。

1. 在内存中创建一个对象。
2. 该对象内部的**`Prototype`**指针被赋值为构造函数的`prototype`属性   ==？==
3. 构造函数内部的**`this`**转为指向这个新对象
4. 执行构造函数内部代码，即给新对象添加属性
5. 如果构造函数有返回非空对象，则返回该对象；否则，返回刚创建的新对象    ==?==

``` js
class Animal {}
let a = new Animal();	// Animal {}


class Person {
    constructor() {
        console.log('person ctor');	// 第四条：执行构造函数内部代码
    }
}
let p = new Person();	// Person {}


class Vegetable {
    constructor() {
        this.color = 'orange';  // 第三条：构造函数内部的this转为指向这个新对象
    }
}
let v = new Vegetable();	// Vegetable {color: "orange"} （第五条：返回刚创建的对象？
console.log(v.color);	// 第四条：给对象添加属性
```

对于第五条的理解：

``` js
class Point {
    constructor(x, y) {
        return {x, y}
    }
}

let point = new Point(2, 3);
console.log(point);	// {x: 2, y: 3}

// 如果第3行没有return一个对象：Point {} 	非空呢？
```



类实例化时传入的参数会用作构造函数的参数。若不需要参数，则实例化时类名后面的括号可省略

``` js
class Point {
    constructor(x, y) {
        this.x = x;
        this.y = y;
    }
}

let point = new Point(2, 3);
```



与 ES5 一样，实例的属性除非显示定义在其本身（*即定义在`this`对象上*），否则都是定义在原型上（*即定义在`class`上*）。

``` js
/* 定义类 */
class Point {
    constructor(x, y) {
        this.x = x;	// 显示定义在本身
        this.y = y;
    }
    
    toString(z) {	// 定义在原型上
        return this.x "+" this.y "+" z;
    }
}

let point = new Point(2, 3);
console.log(point.toString(4))	// 传给参数z

point.toString()	// (2, 3)

point.hasOwnProperty('x')	// true
point.hasOwnProperty('toString')	// false
point.__proto__.hasOwnProperty('toString')	// true
```

> [Object.prototype.toString()](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Object/toString)
>
> 每个对象都有一个`toString()`方法。默认的`toString()`返回`[object type]`，也可以自定义来取代默认方法。不能传入参数但必须返回值。

> [Object.prototype.hasOwnProperty()](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Object/hasOwnProperty)
>
> `hasOwnProperty()` 方法会返回一个布尔值，指示对象自身属性中是否具有指定的属性,也就是，是否有指定的键



与 ES5 一样，类的**所有实例共享一个原型对象**。

``` js
let p1 = new Point(2, 3);
let p2 = new Point(3, 2);

p1.__proto__ === p2.__proto__	// true
```

上面代码中，`p1`和`p2`都是`Point`的实例，它们的原型都是`Point.prototype`，所以`__proto__`属性是相等的。

这也意味着，可以通过实例的`__proto__`属性为类添加方法。

``` js
let p1 = new Point(2, 3);
let p2 = new Point(3, 2);

p1.__proto__.printName = () => 'Oops';

p1.printName()	// Oops
p2.printName()	// Oops
```

上面代码在`p1`的原型上添加了个`printName`方法，由于原型相同，所以`p2`也可以调用这个方法。这意味着，使用实例的`__proto__`属性改写原型，必须相当谨慎，不推荐使用，因为这会改变“类”的原始定义，影响到所有实例。

> **\__proto__** 
>
> `__proto__` 并不是语言本身的特性，这是各大厂商具体实现时添加的私有属性，虽然目前很多现代浏览器的 JS 引擎中都提供了这个私有属性，但依旧不建议在生产中使用该属性，避免对环境产生依赖。生产环境中，我们可以使用 `Object.getPrototypeOf` 方法来获取实例对象的原型，然后再来为原型添加方法/属性。



## 实例、原型、类成员

类可以方便地定义应该存在于**实例**上的成员、应该存在于**原型**上的成员、应该存在于**类本身**的成员



+ <span style="font-size:20px">实例成员</span>

    每次通过`new`标识符都会执行类构造函数，在类构造函数内部可以为新创建的实例`this`添加属性，这些属性也就是实例成员。另外，构造函数执行完毕后，仍然可以给实例添加新成员

    实例成员，只能由实例化的对象来访问

    每个实例对应的是唯一的成员对象，意味着实例成员不会在原型（*`.prototype`*）上共享	==?==



+ <span style="font-size:20px">原型方法</span>

    为了在实例间共享方法，在类语块中定义的方法作为原型方法。

    ```js 
    class Person {
        constructor() {
            // 添加到this的内容会存在于不同实例上
            this.locate = () => console.log('instance');    
        }
        /* 类块中定义的内容会定义在原型上 */
        locate() {
            console.log('prototype');
        }
    }
    
    let p1 = new Person();
    let p2 = new Person();
    
    console.log(p1.locate == p2.locate)  // false
    console.log(Person.prototype.locate() === Person.prototype.locate()) // true
    ```



+ <span style='font-size:20px'>静态类方法</span>

    在类上定义的静态方法通常用于 执行不特定于实例的操作和不要求存在类的实例。
    
    ``` js
    class Person {
        constructor() {
            // 定义在实例上
            this.locate = () => console.log('instance', this);
        }
        // 定义在类的原型对象上
        locate() {
            console.log('prototype', this);
        }
        // 定义在类本身上
        static locate() {
            console.log('class', this);
        }
    }
    
    let p = new Person();
    p.locate()	// instance, Person {}
    Person.prototype.locate();	// prototype, {constructor: ...}
    Person.locate();	// class, class Person {}
    ```



+ <span style="font-size:20px">非函数原型和类成员</span>

    虽然类定义外部并不显示支持在原型或类上**添加成员数据**，但在类定义外部可以手动添加
    
     ``` js
    class Person {
        sayName() {
            console.log(`${Person.greeting} ${this.name}`);
        }
    }
    
    // 在类上定义数据成员
    Person.greeting = 'My name is';
    
    // 在原型上定义数据成员
    Person.prototype.name = 'Jack';
    
    let p = new Person();
    p.sayName();	// My name is Jack
     ```



+ <span style="font-size:20px">迭代器与生成器方法</span>

    ==。。。====？==



# Class 继承

ECMAScript6 新增特性中最出色的一个就是原生支持了类继承机制。虽然使用的是新语法，但背后依旧使用的是**原型链**。



## 简介

Class 可以通过**`extends`**关键字实现继承，这比 ES5 的通过修改原型链实现继承要清晰和方便许多。

使用`extends`关键字就可以继承任何拥有`[Construct]`和原型的对象。很大程度上，这意味着不仅可以继承一个类，也可以继承普通的构造函数（*保持向后兼容==？==*）

``` js
class Vehicle {}
// 继承类 
class Bus extends Vehicle {}

let b = new Bus();
console.log(b instanceof Bus);	// true
console.log(b instanceof Vehicle);	// true


function Person() {}
// 继承普通构造函数
class Engineer extends Person {}

let e = new Engineer();
console.log(e instanceof Engineer);	// true
console.log(e instanceof Person);	// true
```



`extends`关键字也可以在类表达式中使用，例如

``` js
let Bar = class extends Foo {};
```



派生类都会通过原型链访问到**类和原型上定义的方法**。**`this`**值会反映调用相应方法的**实例或者类**。（*与前文例子类似*）



## super 关键字

派生类的方法可以通过`super`关键字引用它们的原型，`super`关键字只能在派生类中使用，且仅限于类构造函数、实例方法、静态方法内部。



`super`既可以当函数使用，也可以当对象使用，用法完全不同。

1. <span style="font-size:20px">super 作为函数调用，代表父类的构造函数</span>

    <hr>

    ES6 要求，子类的构造函数必须执行一次`super`函数，

    ``` js
    class A {}
    
    class B extends A {
        constructor() {
            super();
        }
    }
    ```

    上面代码中，子类`B`的构造函数之中的`super()`，代表调用父类的构造函数。这是必须的，否则 JS 引擎会报错。

    注意，`super`虽然代表了父类`A`的构造函数，但是返回的是子类`B`的实例，即`super`内部的`this`指的是`B`（*因为是 `B`最后调用的 this*），因此`super()`在这里相当于`A.prototype.constructor.call(this)`。

    即是说，调用`super()`会调用父类构造函数，并将返回的实例赋值给`this`

    

    如需要给父类构造函数传参，则需要手动传入。

    

    作为函数时，`super()`只能用在子类的构造函数之中，用在其他地方会报错。

    

    注意，不要在调用`super()`前引用`this`，不然报错。

    

    

2. <span style="font-size:20px">super 作为对象</span>

    <hr>

    1. 普通方法中，指向父类的原型对象。
    2. 静态方法中，指向父类。

    

    ``` js
    class A {
        p() {
            return 2;
        }
    }
    
    class B extends A {
        constructor() {
            super();
            console.log(super.p()); // 2
        }
    }
    
    let b = new B();
    ```

    上面代码中，子类`B`当中`super.p()`，就是将`super`当作一个对象使用。这时，`super`在普通方法中指向`A.prototype`，所以`super.p()`就相当于`A.prototype.p()`。

    这里需注意，由于`super`指向**父类的原型对象**，所以**定义在父类实例上的方法或属性**是无法通过`super`调用的。

    ``` js
    class A {
        construtor() {
            this.p = 2;
        }
    }
    
    class B extends A {
        get m() {
            return super.p;
        }
    }
    
    let b = new B();
    b.m	// undefined
    ```

    上面代码中，`p`是父类`A`实例的属性，`super.p`就引用不到它。但如果属性定义在父类的原型对象上，`super`就可以取到。

    
    
    由于`this`指向子类实例，所以如果通过`super`对某个属性赋值，这时`super`就是`this`，赋值的属性会变成子类实例的属性	==？==
    
    ``` js
    class A {
        constructor() {
            this.x = 1;
        }
    }
    
    class B extends A {
        constructor() {
            super();	// super 作为函数调用，代表父类的构造函数
            this.x = 2;
            super.x = 3;
            console.log(super.x);	// undefined
            console.log(this.x);	// 3
        }
    }
    
    let b = new B();
    ```
    
    上面代码中==？==，`super.x`赋值为`3`，这时等同于对`this.x`赋值为`3`。而当读取`super.x`时，读的是`A.prototype.x`（*因为指向父类的原型对象*），所以返回`undefined`。	==？==
    
    

注意，使用`super`的时候，必须显示指定是作为函数，还是对象使用，否则会报错。也就是不能单独使用`super`关键字，要么用它调用构造函数，要么用它引用静态方法



最后，由于对象总是继承其他对象的，所以可以在任意对象中，使用`super`关键字。



## 类的 prototype 属性和  \__proto__ 属性

\__proto__（隐式原型）和 prototype（显式原型）：

+ **\__proto__**：对象具有属性`__proto__`，对象的**`__proto__`指向构造该对象的构造函数的原型对象**，使实例能获取在构造函数原型中定义的属性和方法
+ **`prototype`**：方法作为特殊的对象，除了有上述`__proto__`属性外，还有自己特有的属性`prototype`。  
    `__proto__`属性指向一个原型对象，原型对象包含所有实例共享的属性和方法。  
    原型对象也有一个`constructor`属性，指向原构造函数

知道这两个基本点后再来分析下面这张图，

<img src="https://pic1.zhimg.com/80/e83bca5f1d1e6bf359d1f75727968c11_720w.jpg?source=1940ef5c" alt="img" style="zoom: 80%;" />

+ 以构造函数 `Foo`为例，

    构造函数的原型属性`prototype`指向了原型对象，在**原型对象**（ `Foo.prototype`）里有共有的方法，所有构造函数声明的实例`f1`、`f2`都可以共享这个方法。所以实例`f1`、`f2`可通过属性`__proto__`来获取原型对象`Foo.prototype`的所有方法。

    而原型对象`Foo.prototype`内又有`constructor`属性指向`function Foo()`。

    构造函数`Function Foo()`除了是方法(*Function*）也是对象。它的`__proto__`属性指向它的构造函数的原型对象`Function.prototype`。

    除了`Foo()`，`Function()`和`Object()`也是一样的道理。



大多数浏览器的 ES5 实现中，每一个对象都有`__proto__`属性，指向对应的构造函数的`prototype`属性。Class 作为构造函数的语法糖，同时有`prototype`属性和`__proto__`属性，因此同时存在两条继承链。 

1. 子类的`__proto__`属性，表示构造函数的继承，总是指向父类
2. 子类`prototype`属性的`__proto__`属性，表示方法的继承，总是指向父类的`prototype`属性

``` js
class A {}
class B extends A {}	

B.__proto__	=== A	// true
B.prototype.__proto__	=== A.prototype	// true
```

上面代码中，子类`B`的`__proto__`属性指向父类`A`，子类`B`的`prototype`属性的`__proto__`属性指向父类`A`的`prototype`属性	==？==



这样的结果是因为，类的继承是按照下面的模式实现的

``` js
class A {
}

class B {
}

// B 的实例继承 A 的实例
Object.setPrototypeOf(B.prototype, A.prototype);
	// 等同于
B.prototype.__proto__ = A.prototype;

// B 继承 A 的静态属性
Object.setPrototypeof(B, A);
	// 等同于
B.__proto__ = A;

const b = new B();
```

以上两个“等同于”是因为**`Object.setPrototypeOf`**方法的实现：

``` js
Object.setPrototypeOf = function (obj, proto) {
    obj.__proto__ = proto;
    return obj;
}
```



这两条继承链，可以这样理解：作为一个对象，子类`B`的原型（*`__proto__`*）是父类`A`；作为一个构造函数，子类的原型对象（*`prototype` 属性*）是父类的原型对象（*`prototype` 属性*）的实例==?==

``` js
Object.create(A.prototype);
// 等同于
B.prototype.__proto__ = A.prototype;
```



## 总结

+ 类中构造器不是必须写的，要对实例进行一些初始化的操作，如添加指定属性时才写
+ 如果 A 类继承了 B 类，且 A 类中有构造器。那么 A 类构造器中必须调用`super()`
+ 类中定义的方法，都是放在了类的原型上，供实例使用



# Module

## Module 语法

### Module 概述

历史上，JavaScript 一直没有模块（module）体系，无法**将一个大程序拆分成互相依赖的小文件，再用简单的方法拼装起来**。

在 ES6 之前，社区制定了一些模块加载方案，最主要的有 CommonJS 和 AMD 两种。前者用于服务器，后者用于浏览器。ES6 在语言标准的层面上，实现了模块功能，而且实现得相当简单，完全可以取代 CommonJS 和 AMD 规范，成为浏览器和服务器通用的模块解决方案。

ES6 **模块的设计思想**是尽量的静态化，使得**编译时就能确定模块的依赖关系**，以及输入和输出的变量。CommonJS 和 AMD 模块，都只能在运行时确定这些东西。*比如，CommonJS 模块就是对象，输入时必须查找对象属性。***？**



**优点**：

- 静态加载带来的好处：由于 ES6 模块是编译时加载，使得静态分析成为可能。有了它，就能进一步拓宽 JS 的语法，比如引入宏（macro）、类型检验（type system）这些只能靠静态分析实现的功能。	*？*
- 不再需要 UMD 模块格式了，将来服务器和浏
- 览器都会支持 ES6 模块格式
- 将来浏览器的新 API 就能用模块格式提供，不再必须做成全局变量或`navigator`对象的属性
- 不再需要对象作为命名空间（*如 Math 对象*），未来这些功能可通过模块提供



### 严格模式

ES6 的模块自动采用严格模式，不管是否有在模块头部加上`"use strict";`



严格模式主要有以下限制:

- **变量**需先声明后使用
- **函数参数**不能有**同名属性**，报错
- 不能用`with`语句
- 不能对**只读属性**赋值，报错
- 不能使用前缀 0 表示**八进制数**，报错

- 不能删除**不可删除属性**，报错
- 不能**删除变量`delete prop`**，报错；只能删除属性``delete global[prop]``
- **`eval`**不会在它的**外层作用域**引入变量
- **`eval`、`arguments`**不能被重新赋值
- **`arguments`**不会自动反映函数参数的变化
- 不能使用**`arguments.callee`**

- 不能使用**`arguments.caller`**
- 禁止**`this`指向全局对象**
- 不能使用**`fn.caller`和`fn.arguments`**获取函数调用的堆栈
- 增加了**保留字**（比如`protected`、`static`和`interface`）

其中尤其需要注意`this`的限制。ES6 模块中，顶层的`this`指向`undefined`，即 不应该在顶层代码中使用`this`



### export 命令

模块功能主要由俩个命令构成：`export`和`import`。`export`命令用于规定模块的对外接口，`import`命令用于输入其他模块提供的功能



一个模块就是一个独立的文件。该文件内部的所有变量，外部无法获取。如果你希望外部能够读取模块内部的某个变量，就必须使用`export`关键字输出该变量

1. `export`直接放在`var`语句前

```javascript
export var firstName = 'Michael';
export var lastName = 'Jackson';
export var year = 1958;
```

上面代码是`profile.js`文件，保存了用户信息。ES6 将其视为一个模块，里面用`export`命令对外输出了三个变量。



2. `export`的写法，除了像上面这样，还有另外一种。在**`export`命令后面使用大括号指定所要输出的一组变量**。

```javascript
var firstName = 'Micheal';
var lastName = 'Jackson';
var year = 1958;

export {firstName, lastName, year};
```

 它与前一种写法是等价的，但应优先考虑这种写法，因为可以在脚本尾部直观看到输出了哪些变量。



`export`命令除了输出变量，还可以输出函数或类（class）。

```javascript
export function multiply(x, y) {
	return x * y;
};
```



**重命名输出变量**：

通常情况，`export`输出的变量就是本来的名字，但可以使用`as`关键字重命名

```javascript
function v1() { ... }

export {
	v1 as streamV1,
    v2 as streamV2
};
```

上面代码使用`as`重命名了函数`v1`的对外接口。重命名后，`v1`可以用不同的名字输出俩次。



需要特别注意的是，`export`命令规定的是对外的接口，必须**与模块内部的变量建立一一对应关系**

```javascript
// 报错
export 1;

// 报错
var m = 1;
export m;
```

上面俩种写法都会报错，因为没有提供对外的接口。

第一种写法直接输出 1，第二种写法通过变量`m`，还是直接输出 1。`1`只是一个值，不是接口。

正确的写法是下面这样：

```javascript
// 写法一
export var m = 1;

//  写法二
var m = 1;
export {m};
```

上面写法都是正确的，规定了对外的接口`m`。其他脚本可以通过这个接口，取到值`1`。它们的实质是，在接口名与模块内部变量之间，建立了一一对应的关系。

同样的，`function`和`class`的输出，也必须遵守这样的写法。



另外，`export`语句输出的接口，与其对应的值是**动态绑定**关系，即 通过该接口，可以取到模块内部实时的值。

这一点与 CommonJS 规范完全不同。CommonJS 模块输出的是值的缓存，不存在动态更新



最后，`export`命令可以出现在模块的任何位置，只要处于模块顶层就可以。

如果处于块级作用域内，就会报错，`import`命令也是如此。这是因为处于条件代码块之中就没法做静态优化了，违背了 ES6 设计的初衷。

```javascript
function foo() {
	export default 'bar'	// SyntaxError	?
}
foo()
```

上面代码，`export`语句放在函数之中，结果报错



### import 命令

`export`定义了模块的对外接口后，其他 JS 文件就可以通过**`import`命令加载这个模块**

```javascript
// main.js
import {firstName, lastName, year} from 'profile.js';

function setName(element) {
    element.textContent = firstName + ' ' + lastName;
}
```

上面代码的`import`用于加载`profile.js`文件，并从中输入变量。`import`命令接受一对大括号`{}`，里面指定要从其他模块导入的变量名，必须与被导入模块`profile.js`对外接口的名称相同



如果想为输入的变量重新取一个名字，`import`命令要使用`as`关键字，将**输入的变量重命名**。

```javascript
import { lastName as surname } from 'profile.js';
```



**`import`输入的变量都是只读的**，因为它的本质是输入接口。也就是说，不允许在加载模块的脚本里面，改写接口

```javascript
import {a} from './xxx.js'

a = {}; // Syntax Error : 'a' is read-only;
```

上面代码中，脚本加载了变量`a`，对其重新赋值就会报错。

但是，若`a`是一个对象，改写`a`的属性是允许的。

```javascript
import {a} from './xxx.js'

a.foo = 'hello';	// 合法操作
```

`a`的属性可以成功改写，并且其他模块也可以读到改写后的值。不过这种写法难以查错，不建议。



`import`后面的**`from`指定模块文件的位置**，可是相对路径 或 绝对路径，`.js`后缀可省略。如果只是模块名，不带有路径，那么必须有配置文件，告诉 JS 引擎该模块的位置

```javascript
import {myMethod} from 'util';
```

上面代码中，`util`是模块文件名，由于不带有路径，必须通过配置，告诉引擎怎么取到这个模块。



注意，`import`命令具有提升效果，会**提升到整个模块的头部**，首先执行

```javascript
foo();

import { foo } from 'myModule';
```

上面代码不会报错。



由于`import`是静态执行，所以**不能使用表达式和变量 这些只有在运行时才能得到结果 的 语法结构**

```javascript
// 报错
import { 'f' + 'oo' } from 'my_module';

// 报错
let module = 'my_module';
import {foo } from module;

// 报错
if(x === 1) {
	import { foo } from 'my_module1';
}
else {
	import { foo } from 'my_module2';
}
```

三种写法都会报错，因为用到了表达式、变量、`if`结构。在静态分析阶段，这些语法都是没法得到值的。



最后，**`import`语句会执行所加载的模块**，因此有下面的写法。

````javascript
import 'lodash'
````

上面代码仅执行`lodash`模块，但不输入任何值。

如果多次重复执行同一句`import`语句，那么**只会执行一次**，不会多次



```javascript
import {foo} from 'my_module';
import {bar} from 'my_module';

// 等同于
import {foo, bar} from 'my_module';
```

上面代码中，虽然`foo`和`bar`在俩个语句中加载，但它们对应的是同一个`my_module`实例。

也就是说，，`import`语句是 **Singleton（*单例）* 模式**



目前阶段，通过 Babel 转码，CommonJS 模块的`require`命令和 ES6 模块的`import`命令，可以写在同一个模块里面，但不建议这样做。因为`import`在静态解析阶段执行，所以它是**一个模块之中最早执行**的

下面的代码可能不会得到预期结果。

```javascript
require('core-js/modules/es6.symbol');
require('core-js/modules/es6.promise');
import React from 'React';
```



### 模块的整体加载

除了指定加载某个输出值，还可以使用整体加载，即**用`*`指定一个对象，所有输出值都加载在这个对象上面**



下面是一个`circle.js`文件，输出俩个方法`area`和`circumference`。

```javascript
export function area(radius) {
	return Math.PI * radius * radius;
}

export function circumference(radius) {
	return 2 * Math.PI * radius;
}
```

现在，加载这个模块。

```javascript
import { area, circumference } from './circle';

console.log('圆面积：' + area(4));
console.log('圆周长：' + circumference(14));
```

上面写法是逐一指定要加载的方法，**整体加载的写法**如下。

```javascript
import * as circle from './circle';

console.log('圆面积：' + circle.area(4));
console.log('圆周长：' + circle.circumference(14));
```

上例中的`circle`就是用`*`指定的对象，所有方法都加载在这个对象里。



注意，模块整体加载所在的那个对象*（上例是`circle`）*,应该是可以静态分析的，所以不允许运行时改变	**?**

下面的写法是不允许的。

```javascript
import * as circle from './circle';

// 下面俩行都是不允许的
circle.foo = 'hello';
circle.area = function () {};
```



### export default 命令

从前面的例子可以看出，使用`import`命令时，用户需要知道所要加载的变量名或函数名，否则无法加载

为了给用户提供方便，快速上手，让用户不用阅读文档了解有哪些属性和方法就能加载模块，就要用到`export default`命令，为模块指定默认输出



``` javascript
// export-default.js
export default function () {	// 匿名函数
    console.log('foo');
}
```

上面代码是一个模块文件`export-default.js`，它的默认输出是一个匿名函数。



其他模块加载该模块时，`import`命令可以为该匿名函数指定任意名字。

``` javascript
// import-default.js
import customName from './export-default';
customName();	// 'foo'
```

上面代码的`import`命令，可用任意名称指向`export-default.js`输出的方法，这时就不需要知道原模块输出的函数名。需要注意的是，这时`import`命令后面，不使用大括号。



`export default`命令用在非匿名函数前，也是可以的

```javascript
// export-default.js
export default function foo() {
	console.log('foo');
}

// 或者写成

function foo () {
	console.log('foo');
}

export default foo;
```

上面代码中，`foo`函数的函数名`foo`在，在模块外部是无效的。加载的时候，看作匿名函数加载。



`import`语句的**大括号**

下面比较一下默认输出 和 正常输出。

``` javascript
// 一、
export default function crc32() {	// 输出
    // ...
}
import crc32 from 'crc32';	// 输入

// 二、
export function crc32() {
    // ...
}
import {crc32} from 'crc32';
```

上面代码的俩组写法，第一组是使用`export default`时，对应的`import`语句不需要使用大括号；第二组是不使用`export default`时，对应的`import`语句需要使用大括号。

`export default`命令**用于指定模块的默认输出**。显然，一个模块只能有一个默认输出，因此`export default`只能使用一次。所以，`import`后面才不用加大括号，因为只可能唯一对应`export default`命令



本质上，`export default`就是**输出一个叫做`default`的变量或方法**，然后系统允许你为它取任意名字

所以，下面的写法是有效的。

``` javascript
// module.js
function add(x, y) {
    return x * y;
}
export {add as default};
// 等同于
// export default add;


// app.js
import {default as foo} from 'modules';
// 等同于
// import foo from 'modules';
```

**?**

正是因为`export default`命令其实只是输出一个叫做`default`的变量，所以它**后面不能跟变量声明语句**

``` javascript
// 正确
export var a = 1;

// 正确
var a = 1;
export default a;

// 错误
export default var a = 1;
```

上面代码中，`export default a`的含义是将变量`a`的值赋给变量`default`。所以，最后一种写法会报错。



同样地，因为`export default`命令的本质是将后面的值，赋给`default`变量，所以可以直接将一个值写在`export default`之后

``` javascript
// 正确
export default 42;

// 报错
export 42;
```

后一句报错是因为没有指定对外的接口，而前一句指定对外接口为`default`。



有了`export default`命令，输入模块时就非常直观了，以输入`lodash`模块为例。

> Lodash就是一个工具库，有很多比较好用的方法可以帮助我们提高开发效率

``` javascript
import _ from 'lodash';
```

==?==



如果想在一条`import`语句中，**同时输入默认方法和其他接口**，可写成下面这样。	==?==

``` javascript
import _, { each, each as forEach } from 'lodash';
```

对应上面代码的`export`语句如下。

``` javascript
export default function (obj) {
    // ...
}

export function each(obj, iterator, context) {
    // ...
}

export { each as forEach };
```

上面代码的最后一行的意思是，暴露出`forEach`接口，默认指向`each`接口，即`forEach`和`each`指向同一个方法。



`export default`也可以用来输出类

``` javascript
// myClass.js
export default class { ... }

// main.js
import myClass from 'myClass';
let o = new myClass();	
```

**?**

### export 与 import 的复合写法	**?**

如果在一个模块之中，先后输入和输出同一个模块，`import`可与`export`写在一起

``` javascript
export { foo, bar } from 'my_module';

// 等同于
import { foo, bar } from 'my_module';
export { foo, bar };
```

上面代码中，`export`和`import`语句可以结合在一起，写成一行。但需要注意的是，写成一行后，`foo` 和`bar`实际上并没有被导入当前模块，只是相当于对外转发了这两个接口，导致当面模块不能直接使用`foo` 和`bar`。	**？**



模块的**接口改名**和**整体输出**，也可采用这种写法

``` javascript
// 接口改名
export { foo as myFoo } from 'my_module';

// 整体输出
export * from 'my_module';
```



默认接口的写法如下。

```javascript
export { default } from 'foo';
```

具名接口**?**改为默认接口的写法如下。

``` javascript
export { es6 as default } from './someone';

// 等同于
import { es6 } from './someModule';
export default es6;
```





### 模块的继承	==?==

模块之间也可以继承



假设有一个`circleplus`模块，继承了`circle`模块

``` javascript
// circleplus.js

export * from 'circle';	// ?
export var e = 2.7;
export default function(X) {
    return Math.exp(X);
}
```

上面代码中的`export *`，表示再输出`circle`模块的所有属性和方法。

注意，`export *`命令会忽略`circle`模块的`default`方法。然后，上面代码又输出了自定义的`e`变量和默认方法。



...

### 跨模块常量

`const`声明的常量只在当前代码块有效，如果想设置**跨模块**（ *跨多个文件*）的常量，或者说一个值要被多个模块共享，可采用下面的写法。



``` javascript
// constants.js 模块
export const A = 1;
export const B = 3;
export const C = 4;

// test1.js 模块
import * as constants from './constants';	// 模块的整体加载
console.log(constants.A); // 1
console.log(constants.B); // 3

// test2.js 模块
import {A, B} from './constants';
console.log(A);	// 1
console.log(B);	// 3
```



如果**要使用的常量非常多**，可以建一个专门的`constants`目录，将各种常量写在不同的文件里面，保存在该目录下	**?**

``` javascript
// constants/db.js
exports const db = {	?
    url: 'http://my.couchdbserver.local:5984',
  	admin_username: 'admin',
  	admin_password: 'admin password'
};

// constants/user.js
export const users = ['root', 'admin', 'staff', 'ceo', 'chief', 'moderator'];
```

然后，将这些文件输出的变量，合并在`index.js`里面。

``` javascript
// constants/index.js
export {db} from './db';
export {users} from './users';
```

使用时，直接加载`index.js`就可以了。

``` javascript
// script.js
import {db, users} from './index';
```

### import()

#### 简介

前面介绍过，`import`命令会被 JS 引擎静态分析，先于模块内其他语句执行

所以，下面的代码会报错。

``` javascript
// 报错
if (x === 2) {
    import myModual from './myModual';
}
```

上面代码中，引擎处理`import`语句是在编译时，这时不会去分析或执行`if`语句，所以会报错误。

也就是说，`import`和`export`命令只能在模块的顶层，不能在代码块之中（*比如，在`if`代码块之中，或在函数之中*）



这样的设计虽然有利于编译器提高效率，但也导致无法在运行时加载模块。在语法上，条件加载就不可能实现。如果`import`命令要取代 Node 的`require`方法，这就形成了一个障碍。因为`require`是运行时加载模块，`import`命令无法取代`require`的动态加载功能

``` javascript
const path = './' + fileName;
const myModual = require(path);
```

上面的语句就是动态加载，`require`到底加载哪一个模块，只有运行时才知道。**？**`import`命令做不到这一点。



因此，有一个提案，建议引入`import()`函数，完成动态加载



``` javascript
import(specifier)
```

上面代码中，`import`函数的参数`specifier`，指定所要加载的模块的位置。

`import`命令能够接受什么参数，**`import()`函数**就能接受什么参数，两者主要区别是 后者为动态加载



`import()`返回一个 

下面是一个例子。	**?**

``` javascript
const main = document.querySelector('main');

import('./section-modules/${someVariable}.js')
 .then(module => {
    module.loadPageInto(main);
  })
  .catch(err => {
    main.textContent = err.message;
  });
```



`import()`函数可用在任何地方，不仅仅是模块，**非模块**的脚本也可以使用



`import()`函数是运行时执行，即 什么时候运行到这一句，就会加载指定的模块



另外，`import()`与所加载的模块没有静态连接关系，这点也是与`import`语句不相同。`import()`类似于 Node 的`require` **?**方法，主要区别是前者是**异步加载**，后者是同步加载



#### 适用场合	==?==

+ 按需加载

`import()`可以在需要的时候，再加载某个模块

...



+ 条件加载



+ ...



#### 补充其它注意点

`import()`加载模块成功以后，这个模块会作为一个对象，当作`then`方法==?==的参数。因此，可以使用对象解构赋值的语法，获取输出接口



## Module 的加载实现

介绍如何在浏览器和 Node 之中加载 ES6 模块，以及实际开发中常遇到的一些问题（*如循环加载*）



### 浏览器加载

#### 加载规则

浏览器加载 ES6 模块，也使用`<script>`标签，但是要加入**`type="module"`**属性



``` javascript
<script type="module" src="./foo.js"></script>
```

上面代码在网页中插入一个模块`foo.js`，因为`type`属性设为`module`，所以浏览器知道这是一个 ES6 模块。



如果网页有**多个**`<script type="module">`，它们会按照在页面出现的顺序依次执行



`<script>`标签的`async`属性也可以打开，这时只要加载完成，渲染引擎就会中断渲染，立即执行，执行完成后，再恢复渲染	==？==

``` javascript
<script type="module" src="./foo.js" async></script>
```

一旦使用了`async`属性，`<script type="module">`就不会按照在页面出现的顺序执行，而是只要该模块加载完成，就执行该模块	==？==



ES6 模块也允许**内嵌在网页中**，语法行为与加载外部脚本完全一致

``` javascript
<script type ="module">
    import utils from "./utiles.js";
	// ...
</script>
```

对于外部的模块脚本（*上例是  `foo.js`*)==？==，有几点需要注意	



# 参考链接

[阮一峰：ECMAScript入门](https://www.yuque.com/ostwind/es6)

[开课吧阶段课程之ES6](https://www.bilibili.com/video/BV1gW411X7M9?p=12)

[变量foo 和bar的由来_wangluqinglxq的专栏-CSDN博客](https://blog.csdn.net/wangluqinglxq/article/details/27711295)

[Array.prototype.map() - JavaScript | MDN](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Array/map)

[箭头函数 - 廖雪峰的官方网站](https://www.liaoxuefeng.com/wiki/1022910821149312/1031549578462080)

[return - JavaScript | MDN](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Statements/return)

[字符串和编码 - 廖雪峰的官方网站](https://www.liaoxuefeng.com/wiki/1016959663602400/1017075323632896)

[二进制位运算符 - JavaScript 教程 - 网道](https://wangdoc.com/javascript/operators/bit.html)

[throw - JavaScript | MDN](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Statements/throw)

[javascript - What is the difference between throw Error and console.error](https://stackoverflow.com/questions/25377115/what-is-the-difference-between-throw-error-and-console-error)

[Error - JavaScript | MDN](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Error)

[Function.prototype.call() - JavaScript | MDN](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Function/call)

[js 中call,  apply, bind 小知识_kdtcsdn的博客](https://blog.csdn.net/kdtcsdn/article/details/103253753)

[JS 回调函数 - 简书](https://www.jianshu.com/p/40e459cfdc6f)

[包装对象 - JavaScript 教程 - 网道](https://wangdoc.com/javascript/stdlib/wrapper.html)

[Object.keys() - JavaScript | MDN](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Object/keys)

[Object.defineProperty() - JavaScript | MDN](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Object/defineProperty)

[从零起步，真正理解Javascript回调函数 - SegmentFault 思否](https://segmentfault.com/a/1190000021942060)

[ES6 Promise讲解_哔哩哔哩](https://www.bilibili.com/video/BV1ya4y1J7P6?from=search&seid=17501235576516228900)

[ES6关于Promise的用法 - SegmentFault 思否](https://segmentfault.com/a/1190000011652907)

[Promise从入门到自定义(promise前端进阶必学)_哔哩哔哩](https://www.bilibili.com/video/BV1WE411P7e6?p=3&spm_id_from=pageDriver)

JavaScript 高级程序设计（第四版）	<a href="#Promise">位置</a>

[undefined与null的区别 - 阮一峰的网络日志](https://www.ruanyifeng.com/blog/2014/03/undefined-vs-null.html)	<a href="#Promise.prototype.then()">位置</a>

[js promise怎么返回值？ - SegmentFault 思否](https://segmentfault.com/q/1010000007889310)	<a href="#Promise.prototype.finally()">位置</a> 

[类 - JavaScript | MDN](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Classes)	<a href="#Class 的定义">位置</a>

[js构造函数的静态成员和实例成员](https://blog.csdn.net/songlf521/article/details/60144182)	<a href="#实例、原型、类成员">位置</a>

[js中Promise与async/await的用法简介_哔哩哔哩](https://www.bilibili.com/video/BV12E411975X?from=search&seid=11866713411434824529)

