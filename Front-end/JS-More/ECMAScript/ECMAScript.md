# 初步自检

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

## 特点

+ **不允许相同作用域重复声明**

+ **块级作用域**

+ **暂时性死区**[^ 1]——本质就是，只要一进入当前作用域，所要使用的变量就已经存在了，但是不可获取

    ```js
    let tmp = "";
    if (true) {
      // TDZ开始
      tmp = 'abc'; // ReferenceError
      console.log(tmp); // ReferenceError
    
      let tmp; // TDZ结束
      console.log(tmp); // undefined
    }	// let const 同理
    ```

    只要块级作用域内存在`let`命令，它所声明的变量就“**绑定**”（binding）这个区域，不再受外部的影响



## let 命令

+ <span style="font-size:22px">Eg：for 循环</span>

    let 声明的变量 `i `，当前的` i `只在本轮循环有效，所以每一次循环的 i 其实都是一个新的变量。

    循环含俩个作用域，设置循环变量的部分是一个父作用域，循环体内部是一个单独的子作用域。

    ```js
    for (let i = 0; i < 3; i++) {
      let i = 'abc';
      console.log(i);
    }
    // abc
    // abc
    // abc
    ```

    > 代码正常运行，表明函数内部变量 `i` 与循环变量 `i` 不在同一个作用域，有各自单独的作用域——如果同一作用域重复声明会报错



## 块级作用域与函数声明

函数能不能在块级作用域之中声明？

+ **ES5** 规定，函数只能在**顶层作用域**和**函数作用域**之中声明，不能在块级作用域声明。

    但浏览器没有遵守这个规定，为了兼容以前的旧代码，还是支持在块级作用域之中声明函数，因此不会报错

+ **ES6** 引入了块级作用域，明确允许在**块级作用域之中声明函数**

    ES6 规定，块级作用域之中，函数声明语句的行为类似于`let`，在块级作用域之外不可引用

+ Eg：

    ```js
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

    ```js
    // ES5 环境
    function f() { console.log('I am outside!'); }
    
    (function () {
      function f() { console.log('I am inside!'); }
      if (false) {
      }
      f();
    }());
    ```

    <hr>

    **ES6** 就完全不一样了，理论上会得到“I am outside!”。因为块级作用域内声明的函数类似于`let`，对作用域之外没有影响。但是，如果你真的在 ES6 浏览器中运行上面的代码，是会报错的，这是为什么呢？

如果改变了块级作用域内声明的函数的处理规则，显然会对老代码产生很大影响。为了**减轻**因此产生的**不兼容问题**，ES6 规定，浏览器的实现可以不遵守上面的规定，有自己的[行为方式](http://stackoverflow.com/questions/31419897/what-are-the-precise-semantics-of-block-level-functions-in-es6)。
+ 允许在**块级作用域内声明函数**
+ 函数声明类似于`var`，即会**提升**到**全局作用域**或**函数作用域的头部**。
+ 同时，函数声明还会**提升**到所在的**块级作用域的头部**。

> Note: 上面三条规则只对 ES6 的浏览器实现有效，其他环境的实现不用遵守，还是将块级作用域的函数声明当作`let`处理。

根据这三条规则，在**浏览器**的 ES6 环境中，**块级作用域内声明的函数**，行为类似于`var`声明的变量。

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

上面的代码在符合 ES6 的浏览器中，都会报错，因为实际运行的是下面的代码：

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

考虑到环境导致的行为差异太大，应该**避免在块级作用域内声明函数**。如果确实需要，也应该写成**函数表达式**`let f = function() {}`，而不是函数声明语句



## const命令

+ <span style="font-size:22px">内存地址不得改动:</span>

    `const`实际上保证的，并不是变量的值不得改动，而是变量指向的那个内存地址不得改动

    如果真的想将**对象冻结**，应该使用**`Object.freeze`**方法

    ```js
    const foo = Object.freeze({});	// 这什么写法?
    
    // 常规模式时，下面一行不起作用；
    // 严格模式时，该行会报错
    foo.prop = 123;
    ```

    上面代码中，常量`foo`指向一个冻结的对象，所以添加新属性不起作用，严格模式时还会报错。

    除了将对象本身冻结，对象的属性也应该冻结。下面是一个将**对象彻底冻结**的函数：

    ```js
    var constantize = (obj) => {	// 函数表达式
      Object.freeze(obj);
      Object.keys(obj).forEach( (key, i) => {
        if ( typeof obj[key] === 'object' ) {	// 对象的属性是对象
          constantize( obj[key] );
        }
      });
    };
    ```



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



顶层对象的属性与全局变量挂钩，被认为是 JS 最大的设计败笔之一。这样的设计带来了几个很大的问题，

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



## global 对象 



ES5 的顶层对象，本身也是一个问题，因为它在各种实现里面是不统一的

- 浏览器里面，顶层对象是`window`，但 Node 和 Web Worker[^ 2] 没有`window`。
- 浏览器和 Web Worker 里面，`self`也指向顶层对象，但是 Node 没有`self`。
- Node 里面，顶层对象是`global`，但其他环境都不支持。



同一段代码为了能够在各种环境，都能取到顶层对象，现在一般是使用**`this`**变量，但是有局限性

- **全局环境**中，`this`会返回顶层对象。但是，**Node 模块**和 **ES6 模块**中，`this`返回的是当前模块。
- **函数**里面的`this`，如果函数不是作为对象的方法运行，而是单纯作为函数运行，`this`会指向顶层对象。但是，严格模式下，这时`this`会返回`undefined`。
- 不管是严格模式，还是普通模式，`new Function('return this')()`，总是会返回全局对象。但是，如果浏览器用了 CSP（Content Security Policy，内容安全政策），那么`eval`、`new Function`这些方法都可能无法使用。



综上所述，很难找到一种方法，可以在所有情况下，都**取到顶层对象**



# 变量的解构赋值

ES6 允许按照一定模式，从数组和对象中提取值，对变量进行赋值，这被称为解构（Destructuring）



## 数组的解构赋值

+ <span style="font-size:22px">模式匹配：</span>——只要等号两边的模式相同，左边的变量就会被赋予对应的值

    ```js
    let [a, b, c] = [1, 2, 3];
    ```

    如果解构不成功，变量的值就等于`undefined`

+ <span style="font-size:22px">不完全解构</span>——右边参数数量少于左边，依然可以成功

+ 右边为**可遍历的结构**，否则报错

+ <span style="font-size:22px">默认值:</span>

    解构赋值允许指定默认值，只有当一个数组成员严格等于`undefined`，默认值才会生效

    ```js
    let [foo = true] = [];
    foo // true
    let [x, y = 'b'] = ['a']; // x='a', y='b'
    let [x, y = 'b'] = ['a', undefined]; // x='a', y='b'
    ```

    如果默认值是一个表达式,那么这个表达式是惰性求值的，即只有在用到的时候，才会求值。



## 对象的解构赋值

+ 对象的属性没有次序，**变量必须与属性同名**，才能取到正确的值。

    这实际上说明，对象的解构赋值是下面形式的简写*（参见《对象的扩展》）。*

    ```js
    let { foo, bar } = { foo: "aaa", bar: "bbb" };
    // 简写:
    let { foo: foo, bar: bar } = { foo: "aaa", bar: "bbb" };
    ```

+ **内部机制**——**先找到同名属性，然后再赋给对应的变量**。被赋值的是变量不是模式

    ```js
    let { foo: baz } = { foo: "aaa", bar: "bbb" };
    baz // "aaa"
    foo // error: foo is not defined
    ```

    > 上面代码中，`foo`是匹配的模式，`baz`才是变量

+ **嵌套解构的对象**

    注意：模式与变量的辨别

    ```js
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

+ <span style="font-size:22px">默认值:</span>

    ```js
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

    上面代码中，等号左边对象的`foo`属性，对应一个**子对象**。该子对象的`bar`属性，解构时会报错。是因为`foo`这时等于`undefined`，再取子属性就会报错。



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



## 数值和布尔值的解构赋值

解构赋值的规则是，只要等号右边的值不是对象或数组，就先**将其转为对象**

由于**`undefined`**和**`null`**无法转为对象，所以对它们进行解构赋值，都会报错 TypeError



## 函数参数的解构赋值

```javascript
function add([x, y]){
  return x + y;
}

add([1, 2]); // 3
```

上面代码中，函数`add`的参数表面上是一个数组，但在传入参数的那一刻，数组参数就被解构成变量`x`和`y`。对于函数内部的代码来说，它们能感受到的参数就是`x`和`y`。



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
```

上面代码中，函数`move`参数是一个对象，通过对这个对象进行解构，得到变量`x`和`y`的值。如果解构失败，`x`和`y`等于默认值。

注意，下面的写法会得到不一样的结果。

```javascript
function move({x, y} = { x: 0, y: 0 }) {
  return [x, y];
}

move({x: 3, y: 8}); // [3, 8]
move({x: 3}); // [3, undefined]
```

上面代码是为函数`move`的参数指定默认值，而不是为变量`x`和`y`指定默认值，所以会得到与前一种写法不同的结果。



## 用途

变量的解构赋值用途很多



+ <span style='font-size:18px'>交换变量的值</span>



- <span style='font-size:18px'>从函数返回多个值</span>

    函数只能返回一个值，如果要返回多个值，只能将它们放在数组或对象里返回。有了解构赋值，取出这些值就非常方便。

    ```js
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



- <span style='font-size:18px'>定义函数参数</span>——解构赋值可以方便地将一组参数与变量名对应起来。   

    ```js
    // 参数是一组有次序的值
    function f([x, y, z]) { ... }
    f([1, 2, 3]);
    
    // 参数是一组无次序的值
    function f({x, y, z}) { ... }
    f({z: 3, y: 2, x: 1});
    ```



- <span style='font-size: 18px'>提取 JSON对象中数据</span>——解构赋值对提取 JSON 对象中的数据，尤其有用。

    ```js
    let jsonData = {
      id: 42,
      status: "OK",
      data: [867, 5309]
    };
    
    let { id, status, data: number } = jsonData;	// 此时data只是模式，不会被赋值
    
    console.log(id, status, number);
    ```



- <span style='font-size: 18px'>函数参数的默认值</span>

    ```js
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
    
    参数的默认值，就避免了在函数体内部再写`var foo = config.foo || 'default foo';`这样的语句。



+ <span style='font-size: 18px'>遍历 Map 结构</span>

    > Map 类似于对象，也是键值对的集合，但键的范围包括各种类型的值 *包括对象*

    如果只想获取键名，或者只想获取键值，可以写成下面这样：

    ```js
    // 获取键名
    for (let [key] of map) {
      // ...
    }
    
    // 获取键值
    for (let [,value] of map) {
      // ...
    }
    ```



+ <span style='font-size: 18px'>输入模块的指定方法</span>——加载模块时，往往需要**指定输入哪些方法**，解构赋值使得输入语句非常清晰。

    ```js
    const { SourceMapConsumer, SourceNode } = require("source--map");
    ```



# 字符串的扩展

ES6 加强了对 Unicode 的支持，并且扩展了字符串对象。

[---字符串的扩展·语雀---](https://cn.bing.com/?FORM=Z9FD1)

## 字符的 Unicode 表示法

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



JavaScript 允许采用`\uxxxx`形式表示一个字符，其中`xxxx`表示字符的 **Unicode 码点**==？==

```javascript
"\u0061"
// "a"
```



## 字符串的遍历器接口   

ES6 为字符串添加了**遍历器接口**（详见《Iterator》一章），使得字符串可以被`for...of`循环遍历

```javascript
for (let codePoint of 'foo') {
  console.log(codePoint)
}
// "f"
// "o"
// "o"
```

除了遍历字符串，这个遍历器最大的优点是可以识别大于`0xFFFF`的码点，传统的`for`循环无法识别这样的码点。



## 模板字符串



# 数值的扩展

+ <span style="font-size:22px">指数运算符:</span>

    ES6 新增了一个指数运算符`**`。（*类似Python*）`x ** y`表示`x`的`y`次幂。

    > **Math.pow()：**
    >
    > 在 V8 引擎中，指数运算符与`Math.pow`的实现不相同，对特别大的运算结果，俩者会有细微的差异



# 函数的扩展

## 函数参数的默认值

ES6 允许为函数的参数设置默认值，即直接写在参数定义的后面

``` javascript
function log(x, y = 'world') {
    console.log(x, y);
}

log('Hello');	// Hello world
```

+ <span style='font-size: 22px'>解构赋值的默认值</span>

    参数默认值能够与解构赋值默认值结合使用

    1. 只使用了解构赋值默认值的情况——如果函数调用时没提供参数，变量不会生成，从而报错。通过提供函数参数默认值可以避免这种情况。

    2. 解构赋值与函数参数默认值结合使用：

        ```js
        function foo({ x, y = 1 } = {}) {
            console.log(x, y);
        }
        
        foo();	// undefined 1
        ```

        指定，如果没提供参数，`foo`的参数默认为一个空对象

        > 上例详解：`= {}`是设置函数参数默认值，而`{ x, y = 1 }`是设置解构赋值默认值。因为没提供参数，因此使用参数默认值，也就是空对象。但`y`有解构赋值默认值`1`，所以`y`有值。

        ```js
        function fetch(url, { body = '', method = 'get', headers = {} } = {}) {
        	console.log(method);
        }
        
        fetch('baidu.com');	// get
        ```

        > 上面代码中，函数`fetch`没有第二个参数时，**函数参数的默认值就会生效，然后才是解构赋值的默认值生效**（*所以函参默认值生效会引起解构赋值默认值生效*），变量`method`才会取到默认值`get`。

+ 默认值位置——如果非尾部的参数设置默认值，实际上这个参数是没法省略的



### 函数的 length 属性

这是因为`length`属性的含义是，该函数预期传入的参数个数。某个参数指定默认值以后，预期传入的参数个数就不包括这个参数了。 同理，` rest`参数也不会计入`length`属性。

因此指定了默认值以后，函数的`length`属性将返回没有指定默认值的参数个数。也就是说，指定了默认值以后，`length`属性将失真。



### 作用域

一旦设置了参数的默认值，函数进行声明初始化时，参数会形成一个单独的作用域（*context*）。等到初始化结束，这个作用域就会消失。  这种行为，在不设置参数默认值时是不会出现的。



再看下面的例子。

``` javascript
let x = 1;

function f(y = x) {
    let x = 2;
    console.log(y);
}

f();	// 1
```

> 上面代码中，函数`f`被调用时，参数`y = x`形成一个单独的作用域。这个作用域里面，变量`x`本身没有定义，所以指向外层的全局变量`x`。  函数调用时，函数体内的局部变量`x`影响不到默认值变量`x`。如果此时，全局变量`x`不存在，就会报错。`ReferenceError: x is not defined`。



### 应用

利用参数默认值，可以指定某一个参数不得省略，如果省略就抛出一个错误。

实例。

``` javascript
function throwIfMissing() {
    throw new Error('Missing parameter');	// 抛出创建的 Error 实例化对象
}

function foo(mustBeProvided = throwIfMissing()) {	// 直接=throw的话报错Unexpected token 'throw'
    return mustBeProvided;
}

foo();	// Error: Missing parameter
```

上面代码实现了当没有传参时显示错误`Missing parameter`。

> `throw`用来抛出一个用户自定义的异常在控制台，当前函数的执行将被停止（*`throw`之后的语句将不会执行* ）
>
> 而`console.error()`只会在控制台输出红色信息
>
> `error` 详细介绍查阅[Error - JavaScript | MDN](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Error)



## rest 参数

用于**获取函数的多余参数**，这样就不需使用`arguments`对象（*伪数组*）了。rest 参数搭配的变量是一个**数组**，该变量**将多余的参数放入数组**中。

+ Eg：求和

    ```js
    function add(...values) {
        let sum = 0;
        for (let val of values) {	// 数组遍历
            sum += val;
        }
        return sum;
    }
    
    add(2, 5, 3);	// 10
    ```

    

+ **arguments 对象: **

    `arguments`对象不是数组，是<span style="color:red">类数组对象</span>

    所以使用数组的方法，必须使用`Array.prototype.slice.call`先将其转为数组。rest 参数就不存在这个问题，它是一个真正的数组，数组特有的方法都可以使用。



## 严格模式

从 ES5 开始，函数内部可以设定为严格模式。

``` javascript
function foo(a, b) {
    'use strict';
    // ...
}
```



## name 属性

函数的`name`属性，返回该函数的函数名。

``` javascript
function foo() {}
foo.name;	// foo
```



## 箭头函数

Arrow Function

+ <span style="font-size:22px">注意:</span>
    + 函数体内的`this`对象，就是定义时所在的对象，而不是使用时所在的对象	
    + 不可以作构造函数，也就是说，不可以使用`new`命令，否则会抛出一个错误
    + 不可以使用`arguments`对象，该对象在函数体内不存在，要用可用 rest 参数代替
    + 不可使用`yield`命令，因此箭头函数不能用作 Generator 函数

    上面四点中，尤其第一点值得注意.因为`this`对象的指向是可变的，但是在箭头函数中，它是固定的。



## 尾调用优化

### 什么是尾调用

尾调用（Tail Call）是函数式编程的一个重要概念，就是指某个函数的最后一步是调用另一个函数。

``` javascript
function f(x) {
    return g(x);
}
```



### 尾调用优化

尾调用之所以与其他调用不同，就在于它的特殊的调用位置。

尾调用由于是函数的最后一步操作，所以不需要保留外层函数的调用帧。因为调用位置、内部信息等都不会再用到了，只要直接用内层函数的调用帧，取代外层函数的调用帧就可以了。



+ <span style='font-size: 19px'>意义</span>

    尾调用优化即只保留内层函数的调用帧。如果所有函数都是尾调用，那么完全可以做到每次执行时调用帧只有一项，这将大大**节省内存**。这就是尾调用优化的意义。



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

ES6 第一次明确规定，所有 ESMAScript 的实现，都必须部署尾调用优化。



### 递归函数的改写

尾递归的实现往往需要改写递归函数，确保最后一步只调用自身。  
做到这一点的方法，就是将所有用到的内部变量改写成函数的参数。  
这样做的缺点是不太直观，第一眼很难看出来。



+ 解决方法——函数默认值：

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



## 函数参数的尾逗号

ES2017 允许函数的最后一个参数有尾逗号（*trailing comma*）  :

``` javascript
function foo(
	param1,
  param2,
)	{ /* ... */ }
```



# 数组的扩展

## 扩展运算符

扩展运算符（*spread*）是**`...`**。它好比 rest 参数（*剩余参数转为数组*）的逆运算。**将一数组转为参数序列**

该运算符主要用于函数调用。



+ <span style='font-size:22px'>扩展运算符的应用：</span>

    <span style='font-size:18px'>深拷贝[^ 3]数组</span>

    ```js
    const a1 = [1, 2];
    const a2 = [...a1];
    
    a2.push(3)
    console.log(a1) // 1,2
    ```

    <span style = 'font-size:18px'>合并数组</span>（*浅拷贝*）

    <span style = 'font-size:18px'>与解构赋值结合</span>

    <span style='font-size:18px'>字符串转为数组</span>

    <span style='font-size:18px'>Iterator 接口转为数组</span>

    <span style = 'font-size:18px'>Map 和 Set 结构，Generator 函数</span>

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

    ```js
    const go = function*() {
        yield 1;
        yield 2;
    };
    
    [...go()]	// [1, 2]
    ```



## Array.from()

`Array.from()`用于将俩类对象转为真正的数组：类似数组的对象（array-like object）和可遍历（iterable）的对象。



## Array.of()

`Array.of()`用于将一组值，转换为数组。

``` javascipt
Array.of(3, 11, 8);	// [3, 11, 8]
Array.of(3).length;	// 1
```



这个方法的主要目的，是弥补数组构造函数`Array()`的不足。因为参数个数的不同，会导致`Array()`的行为有差异。

`Array.of()`基本上可用来替代`Array()`或`new Array()`  



## 数组实例的方法

### copyWithin()

数组实例的`copyWithin`方法，在当前数组内部，将指定位置的成员复制到其他位置（会覆盖原有成员），然后返回当前数组。也就是说，使用这个方法，会修改当前数组。

**语法格式**：

```javascript
Array.prototype.copyWithin(target, start = 0, end = this.length)
```



### find() 和 findIndex()

<span style='font-size:20px'>find() </span>

数组实例的`find()`用于找出第一个符合条件的数组成员。它的**参数是一个回调函数**，所有数组成员依次执行该回调函数，直到找出第一个返回值为`true`的成员后返回该成员。若没有符合条件的成员则返回`undefined`。

``` javascript
[1, 4, -5, -10].find((n) => n <= 0);	// -5
```



<span style = 'font-size:20px'>findIndex()</span>

数组实例的`findIndex()`的用法与`find()`非常类似，返回第一个符合条件的数组成员的位置，如所有成员都不符合条件，则返回`-1`。

``` javascript
[1, 5, 10, 15].findIndex(function(value, index, arr) {
    return value > 9;
})	// 2
```



### fill()

`fill()`使用给定值，填充一个数组。

``` javascript
['a', 'b', 'c'].fill(7)	// [7, 7, 7]

new Array(3).fill(7)	// [7, 7, 7]
```

上面代码表明，`fill()`用于空数组的初始化非常方便。数组中已有的元素，会被全部抹去。



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



> 另外，Map 和  Set 数据结构有一个`has()`，需要注意与`includes()`区分。
>
> + Map 结构的`has`方法，是用来**查找键名**的，比如`Map.prototype.has(key)`、`WeakMap.prototype.has(key)`、`Reflect.has(target, propertyKey)`
> + Set 结构的`has`方法，是用来**查找值**的，比如`Set.prototype.has(value)`、`WeakSet.prototype.has(value)`



# 对象的扩展

## 属性、方法的简洁表示法

```javascript
const foo = 'bar';
const baz = {foo};	// 简写	

// 等同于
const baz = {foo: foo};	// 后一个 foo 是变量名
```

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



CommonJs 模块输出一组变量，就非常适合使用简洁写法。

```javascript
function getItem(key) {}
function setItem (key, value) {}
function clear() {}

module.exports = { getItem, setItem, clear };
// 等同于
module.exports = {
    getItem: getItem,
    setItem: setItem,
    clear: clear
};
```



## 方法的 name 属性

函数的`name`属性返回函数名。对象方法也是函数，因此也有`name`属性



如果对象的方法使用了取值函数`getter`和存值函数`setter`，则`name`属性不是在该方法上面，而是该方法的属性的描述对象的`get`和`set`属性上面，返回值是方法名前加上`get`和`set`



## Object.is()

ES5 比较俩个值是否相等，只有俩个运算符：相等运算符`==`和严格相等运算符`===`。 它们都有缺点，前者会自动转换数据类型，后者的`NaN`不等于自身及`+0`等于`-0`。  

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
`Object.assign()`用于**对象的合并**，将源对象（*source*）的所有可枚举属性复制到目标对象（*target*）




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



# Symbol

## Symbol概述

> **引入`Symbol`的原因:**
>
> ES5 的对象属性名都是字符串，这容易造成属性名的冲突。比如，你使用了一个他人提供的对象，但又想为这个对象添加新的方法，新方法的名字就有可能与现有方法产生冲突。如果有一种机制，保证每个属性的名字都是独一无二的就好了，这样就从根本上防止属性名的冲突。
>

Symbol 值通过 **Symbol()** 生成

+ <span style="font-size:20px">**`Symbol()`的参数**</span>

    **作用**：

    ```js
    let s1 = Symbol('foo');
    let s2 = Symbol('bar');
    
    s1 // Symbol(foo)
    s2 // Symbol(bar)
    s1.toString() // "Symbol(foo)"
    s2.toString() // "Symbol(bar)"
    ```

    上面代码中，`s1`和`s2`是两个 Symbol 值。如果不加参数，它们在控制台的输出都是`Symbol()`，不利于区分。有了参数以后，就等于为它们加上了描述，输出的时候就能够分清，到底是哪一个值。   




## 作为属性名的 Symbol

由于每一个 Symbol 值都是不相等的，意味着 Symbol 值可以作为标识符，用于对象的属性名，就能保证不会出现重名的属性。

这对于一个对象由多个模块构成的情况非常有用，能防止一个键不小心改写或覆盖。说明 **Symbol 适用于对象自身单独的属性**（能防止该属性被意外访问到

```jsx
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

> **Object.defineProperty**：
>
> 该方法允许精确地添加或修改对象的属性。默认情况下，使用`Object.defineProperty()`添加的属性是不可修改的。



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



## Symbol 属性名的遍历

Symbol 作为属性名，该属性不会出现在`for...in`、`for...of`循环中，也不会被`Object.keys()`、[`Object.getOwnPropertyNames()`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Object/getOwnPropertyNames)、`JSON.stringify()` 返回。

但是，它也不是私有属性，有一个`Object.getOwnPropertySymbols`方法，可以获取指定对象的所有 Symbol 属性名。

**`Object.getOwnPropertySymbols`**方法返回一个数组，成员是当前对象的所有**用作属性名的 Symbol 值**



## Symbol.for()，Symbol.keyFor()

`Symbol.for()`靠`key`搜索返回`symbol`值；`Symbol.keyFor()`靠`symbol`值搜索返回`key`

**全局 Symbol 注册表**：

<table>
  <thead>
  	<tr>
      <th>字段名</th>
      <th>字段值</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>[[key]]</th>
      <th>字符串，用来标识每个 symbol</th>
    </tr>
    <tr>
      <th>[[symbol]]</th>
      <th>存储的 symbol 值</th>
    </tr>
  </tbody>
</table>



### Symbol.for()

有时，我们需要**使用同一个 Symbol 值**，`Symbol.for`方法可以做到这一点。

`Symbol.for`接受一个**字符串作参数**，然后**搜索**有没有以**该参数作为名称的 Symbol 值**

若有，返回这个 Symbol 值；若无，新建并返回一个以该字符串为名称的 Symbol 值

```js
let s1 = Symbol.for('foo');
let s2 = Symbol.for('foo');

s1 === s2
```

上面代码中，`s1`和`s2`都是 Symbol 值，但它们都是同参数的`Symbol.for`生成的，所以实际上是同一个值。

**`Symbol.for()`**与**`Symbol()`**这两种写法，都会**生成新的 Symbol**

它们的区别是，`Symbol.for()`会被**登记在全局环境中**供搜索，`Symbol()`不会。`Symbol.for()`不会每次调用就返回一个新的 Symbol 类型的值，而是会先检查给定的`key`是否已经存在，如果不存在才会新建一个值



### Symbol.keyFor()

`Symbol.keyFor`方法**返回一个已登记的 Symbol 类型值的 key**

```
let s1 = Symbol.for("foo");
Symbol.keyFor(s1) // "foo"

let s2 = Symbol("foo");
Symbol.keyFor(s2) // undefined
```

上面代码中，变量`s2`属于未登记的 Symbol 值，所以返回`undefined`。



# Iterator 接口 和 for...of 循环

## Iterator的概念

Iterator（遍历器）

表示“集合”的数据结构，有`Array`、`Object`、`Map`、`Set`四种数据集合。用户可以组合使用它们，定义自己的数据结构，比如 *数组的成员是**`Map`**，**`Map`**的成员是对象 。*这样就需要一种统一的接口机制，来处理不同的数据结构。

遍历器（lterator）就是这样一种机制

Iterator 是一种**接口**，为各种不同的数据结构提供统一的访问机制，任何数据结构只要部署 Iterator 接口，就可以完成遍历操作



**Iterator 的作用**有三个：

- 为各种数据结构，提供一个统一、简便的访问接口
- 使得数据结构的成员能按某种次序排列
- ES6 创造了一种新的遍历命令`for...of`循环，iterator 接口主要供`for...of`消费



**Iterator 的遍历过程**是这样的：

1. 创建一个**指针对象**，指向当前数据结构的起始位置。也就是说，<span style="color:red">遍历器对象本质上就是指针对象</span>
2. 第一次调用指针对象的**`next`**方法，可将指针指向数据结构的第一个成员
3. 第二次调用指针对象的`next`方法，指针指向数据结构的第二个成员
4. 不断调用指针对象的`next`方法，直到它指向数据结构的结束位置



## Iterator 的作用机制

<span style="color:red">每一次调用`next`，都会返回数据结构的当前成员的信息</span>——返回一个包含`value`和`done`两个属性的对象

指针对象的`next`方法，用来移动指针                        



+ 指针结构实现 Iterator 接口：

    ```js
    function Node (val) {
        this.value = val;
        this.next = null;  // 区分iterator的next方法
    }
    Node.prototype[Symbol.iterator] = function () {
        let cur = this; // this指向原型对象
    
        return {
            next() {
                if (cur) {
                    const value = cur.value;
                    cur = cur.next; // 移动指针
                    return {value, done: false}
                } else
                    return {value: undefined, done: true}
            }
        };
    }
    ```

    > Test:
    >
    > ```js
    > const one = new Node(1)
    > const two = new Node(2)
    > const three = new Node(3)
    > one.next = two;
    > two.next = three;
    > for (const n of two) {  // 2, 3
    >     console.log(n);
    > }
    > ```

+ Symbol.iterator 的最简单实现还是使用 Generator 函数



## 默认 Iterator 接口

当使用`for...of`循环遍历某种数据结构时，该循环会自动去寻找 Iterator 接口。

一种数据结构只要部署了 Iterator 接口（也可以说 可以应用`for...of`的对象被称为 iterable，我们就称这种数据结构是“可遍历的”（**iterable**）

ES6规定，默认的 Iterator 接口部署在数据结构的`Symbol.iterator`属性，或者说，一个数据结构只要具有**`Symbol.iterator`**属性，就可以认为是“可遍历的”（iterable）

**`Symbol.iterator`**属性对应一个**函数**，执行**返回一个遍历器对象**：

```js
let arr = [1, 2, 3];
let iter = arr[Symbol.iterator]()   // 调用该属性得到遍历器对象

iter.next() // { value: 1, done: false }
iter.next() // { value: 2, done: false }
iter.next() // { value: 3, done: false }

iter.next() // { value: undefined, done: true }
iter.next() // { value: undefined, done: true }
```



## 调用 Iterator 接口的场合

除了下文`for...of`循环还有一些场合会默认调用 Iterator 接口：

+ 解构赋值

+ 扩展运算符`...`

+ yield*：

    `yield*`后面跟的是可遍历结构，它会调用该结构的遍历器接口

+ 任何接收数组作参数的场合

    因为数组的遍历会调用遍历器接口



## for...of 循环

ES6 引入`for...of`循环**作为遍历所有数据结构的统一方法**。

条件：部署了`Symbol.iterator`属性，即具有 Iterator 接口

```javascript
let arr = [1, 2, 3];
for (let item of arr) {
    console.log(item)	// 1 2 3
}	

let arr = [1, 2, 3];
arr[Symbol.iterator] = undefined;
for (let item of arr) {	// TypeError: arr is not iterable
    console.log(item)
}
```

`for...of`可以使用的范围包括：数组、Set、Map、部分类数组对象（*arguments 对象、字符串、DOM NodeList 对象*）、Generator 对象



# Set 和 Map 数据结构

## Set 数据结构

Set 数据结构类似于数组，但里面的值是唯一的   

set**.size** ——去重后长度

**初始化：** `let set = new Set(['red', 'green', 'blue'])`

**添加多个：**`set = new Set([...set, ...arr])`

**遍历：**

```js
for (let item of set.keys()) {}

for (let item of set.values()) {}

for (let item of set.entries()) {
  console.log(item);
}
// ["red", "red"]
// ["green", "green"]
// ["blue", "blue"]
```


上面代码中，`entries`方法返回的遍历器，同时包括键名和键值，

所以`entries`方法每次输出一个数组，它的两个成员完全相等



## Map数据结构

**Map** 类似于对象，也**是键值对的集合**，但**键的范围包括各种类型的值** *包括对象*



### Map结构实例的属性和操作方法

- **size 属性**

- **set（key, value）**   

    设置键名`key`对应的键值为`value`，返回整个 Map 结构

    ```js
    const map = new Map([["foo", true],]);
    map.set('bar', false);
    
    map.size  // 2
    ```
    

`set`返回的是当前的 Map 对象，因此可用链式写法
    
```js
    let map = new Map()
      .set(1, 'a')
      .set(2, 'b')
      .set(3, 'c');
```

- **get(key)**——找不到 `key` 返回 undefined

- **has(key)**   返回布尔值

- **delete(key)**

    删除某个键，返回`true`，删除失败 返回`false`

- **clear()**   清除所有成员，无返回值  



### Map结构的遍历方法

Map 结构原生提供三个**遍历器生成函数**和一个**遍历方法**   *方法名同 Set*

- **`keys()`**：**返回键名**的遍历器
- **`values()`**：**返回键值**的遍历器
- **`entries()`**：**返回所有成员**的遍历器
- **`forEach()`**：**遍历 Map** 的所有成员



需特别注意的是， **Map 的遍历顺序就是插入顺序**



keys() 、 values()、entries() 方法 用法类似

```js
const map = new Map([   // 
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

上面代码最后的那个例子，表示 Map 结构的默认遍历器接口（`Symbol.iterator`属性），就是`entries`方法。

```js
map[Symbol.iterator] === map.entries
// true
```



# async 函数

ES7 引入了 async 函数，使得异步操作变得更加方便。

简单来说，它就是 Generator 函数的语法糖。比较后会发现，async 函数就是将 Generator 函数的` *` 替换成 `async`，将`yield`替换成`await`。



async 函数对 Generator 函数的改进，体现在以下四点：

1. 内置执行器

    Generator 函数的执行必须靠执行器（*调 next 方法*），所以才有了 co 模块==？==，而 async 函数自带执行器。也就是说，async 函数的执行与普通函数一样只需一行

    ``` js
    asyncReadFile();
    ```

    > 上面的代码调用了`asyncReadFile`函数，然后它就会自动执行，输出最后结果。
    
2. 更好的语义

3. 返回值是 Promise 

    async 函数的返回值是 Promise 对象，这比 Generator 函数的返回值是 Iterator 对象方便，用 then 方法指定下一步的操作。

    进一步说，async 函数完全可以看作多个异步操作包装成的一个 Promise 对象，而 await 命令就是内部 then 命令的语法糖，




## 返回 Promise 对象

+ async 函数返回一个 Promise 对象，内部 return 的值会成为 then 方法回调函数的参数

    ``` js
    async function f() {
        return 'hello';
    }
    
    f().then(v => console.lof(v))	// 'hello'
    ```

    上面代码中`return`的`hello`传给了回调函数内的参数`v`。

+ async 函数返回的 Promise 对象，只有 async 函数内部的异步操作执行完，才会执行 then 方法指定的回调函数，除非遇到 return 语句或抛出错误。



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

上面代码中，`gen`是一个异步 Generator 函数，执行后返回一个异步 Iterator 对象。对该对象调用`next`方法，返回一个 Promise 对象.



异步遍历器的设计目的之一，就是 Genreator 函数处理同步操作和异步操作时能够使用同一套接口。

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



# Module

## Module 的加载实现

介绍如何在浏览器和 Node 之中加载 ES6 模块，以及实际开发中常遇到的一些问题（*如循环加载*）



### 浏览器加载

浏览器加载 ES6 模块，也使用`<script>`标签，但是要加入**`type="module"`**属性

+ Demo：

    ```js
    <script type="module" src="./foo.js"></script>
    ```

    > 上面代码在网页中插入一个模块`foo.js`，因为`type`属性设为`module`，所以浏览器知道这是一个 ES6 模块。

+ 如网页有**多个**`<script type="module">`，它们会按照在页面出现的顺序依次执行

+ **async 属性**也可以打开，这时只要加载完成，渲染引擎就会中断渲染，立即执行，执行完成后，再恢复渲染

    一旦使用了`async`属性，`<script type="module">`就不会按照在页面出现的顺序执行，而是只要该模块加载完成，就执行该模块

+ ES6 模块也允许**内嵌在网页中**，语法行为与加载外部脚本完全一致



# REF

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

[js构造函数的静态成员和实例成员](https://blog.csdn.net/songlf521/article/details/60144182)	<a href="#实例、原型、类成员">位置</a>



[^ 1]: temporal dead zone，简称 TDZ
[^ 2]: 允许主线程创建 Worker 线程，将一些任务分配给后者运行
[^ 3]: 源对象与拷贝对象互相独立



