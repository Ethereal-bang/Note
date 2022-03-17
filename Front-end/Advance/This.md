# This 原理

JS 之所以有`this`的设计取决于内存的数据结构



1. <span style="font-size:22px">对象</span>

    ```js
    let obj = {foo: 5}
    ```

    上文是将一个对象赋值给`obj`。JS 引擎会先生成对象，再**将对象内存地址赋值**给`obj`。即实际是以这样的形式保存：

    <img src="https://gitee.com/ethereal-bang/images/raw/master/20211002202759.png" alt="image-20211002202752118" style="zoom:53%;" />

    `foo`属性值保存在`value`属性。

2. <span style="font-size:22px">函数</span>

    问题在于属性值是函数时：

    ```js
    let obj = { foo: function () {} }
    ```

    这时，JS 会将函数单独保存在内存，再将函数地址赋值给`foo`的`value`属性。

    <img src="https://gitee.com/ethereal-bang/images/raw/master/20211002203318.png" alt="image-20211002203318284" style="zoom:53%;" />

    由于**函数可以在不同环境上下文执行**：

    ```js
    let f = function () {};
    let obj = { f: f };
    // 单独执行
    f();
    // obj 环境执行
    obj.f();
    ```

3. <span style="font-size:20px">环境变量</span>：

    JS 允许函数体内部引用当前环境其他变量：

    ```js
    let f = function () {
      console.log(x);	// x 由运行环境提供
    }
    ```

    问题来了，由于函数可在不同环境执行。所以需要一种机制：在函数体内获得当前运行环境（*context*）。因此，This 出现了，其设计目的就是**在函数内部指代函数当前运行环境**。

    ```js
    let f = function () {
      console.log(this.x);	// 指当前运行环境的`x`
    }
    ```

    再有：

    ```js
    let f = function () {
      console.log(this.x);
    }
    
    let x = 1;
    let obj = {
      f: f,
      x: 2,
    }
    // 单独执行
    f()	// 1
    // obj 环境
    obj.f()	// 2
    ```

    上面代码中：函数`f`全局环境执行，`this.x`指向全局环境`x`：

    <img src="https://gitee.com/ethereal-bang/images/raw/master/20211002204825.png" alt="image-20211002204825652" style="zoom:40%;" />

    `obj`环境执行，指向`obj.x`：

    <img src="https://gitee.com/ethereal-bang/images/raw/master/20211002204904.png" alt="image-20211002204904330" style="zoom:50%;" />

    所以为什么`let foo = obj.foo`，`foo`就变成在全局环境执行：

    ```js
    let obj = {
      foo: function () { console.log(this.bar) },
      bar: 1
    }
    
    let foo = obj.foo;
    let bar = 2;
    
    obj.foo()	// 1
    foo()	// 2
    ```

    因为`obj.foo()`是通过`obj`找到`foo`，所以在`obj`环境执行；一旦`let foo = obj.foo`，变量`foo`直接指向函数本身，`foo()`变成在全局环境执行。



# this 与 function

函数执行时，[`this` 关键字](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Operators/this)并不会指向正在运行的函数本身，而是指向调用该函数的对象。所以，如果你想在函数内部获取函数自身的引用，只能使用函数名或者使用[arguments.callee](https://developer.mozilla.org/zh-cn/docs/JavaScript/Reference/Functions_and_function_scope/arguments/callee)属性([严格模式](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Strict_mode)下不可用)，如果该函数是一个匿名函数,则你只能使用后者。



## 构造函数中的 this

指向其原型对象，因为是原型对象调用该构造函数



## this 与箭头函数

如定义为箭头函数，this 值与定义时环境无关。



# this 与 Class

## 原型和静态方法（*static*）的 this

调用原型或静态方法时没指定 this 的值时，方法内的 this 将为`undefined`：

```js
class Animal {
  speak() { return this }
	static eat() { return this }
}

let obj = new Animal();
// new-会绑定this
obj.speak();	// Animal{}

Animal.eat()	// class Animal
let eat = Animal.eat;
eat();	// undefined
```

如果静态方法包含`this`关键字，这个`this`指的是类，而不是实例。



## new

`new`关键字进行如下操作：

1. 创建新对象`{}`
2. 为`{}`添加`__proto__`属性指向构造函数的原型对象（*`.prototype`*）
3. 将该对象的 **this 指向构造函数**并传参调用构造函数
4. 该函数没有返回对象就返回`this`



## 实例属性

实例属性需定义在在类的方法里：

```js
class Rectangle {
  constructor(height, width) {
    this.height = height;
    this.width = width;
  }
}
```

静态或原型的属性需定义在类定义外面



# 案例

+ 闭包与 for 循环

    ```javascript
    for (var i = 0; i < 5; i++) {
        (function fn(i) {
            setTimeout(function() {
                console.log(i)
            }, 0)
        })(i)
    }	// 0 1 2 3 4
    for (var i = 0; i < 5; i++) {
        setTimeout(function() {
            console.log(i)
        }, 0)
    }	// 5 5 5 5 5
    ```

    

# REF

[JavaScript 的 this 原理 - 阮一峰的网络日志](http://www.ruanyifeng.com/blog/2018/06/javascript-this.html)

