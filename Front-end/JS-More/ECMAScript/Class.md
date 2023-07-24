# Class 基本用法

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

    采用 Class 表达式，可以写出**立即实例化的 Class**。

    ``` js
    let person = new Class {
        constructor(name) {
            this.name = name;
        }
    }('张三')
    ```



## 类、`extends`实质

ECMAScript 没有正式的类这个类型，类就是一种特殊函数

+ **`typeof`**检测类标识符表明它是一个函数：

    ```js
    class Person {/* ... */}
    
    console.log(Person)	// [class Person]
    console.log(typeof Person)	// function
    ```

+ 类有**`prototype`**属性，而这个原型也有一个**`constructor`**属性指向类自身：

    ```js
    console.log(Person.prototype)   // {}
    console.log(Person.prototype.constructor === Person)	// true
    ```

    实际上，类所有方法都定义在类的`prototype`属性上，所以也可以通过`prototype`属性对类添加方法

+ **`instanceof`**也可以检查：类的构造函数原型存在于实例的原型链中：

    ```js
    console.log(p instanceof Person)	// true
    ```

+ **`new`**调用时类本身会被当做构造函数，重点在于，类中定义的`constructor`方法不会被当成构造函数

    ```js
    console.log(p instanceof Person)	// true
    console.log(p instanceof Person.constructor)	// false
    ```

+ 类也可以像立即调用函数表达式一样**立即实例化**：

    ```js
    let p = new Class Person {
      constructor(name) { this.name = name };
    }('Jim');
    console.log(p)	// Person {}
    ```

+ 函数声明可以提升，但 Class 定义**不存在变量提升**

+ 函数受函数作用域限制，Class 受**块作用域**限制

    ``` js
    {
        function FunctionDeclaration() {}
        class ClassDeclaration {}
    }
    
    console.log(ClassDeclaration);	// ReferenceError: ClassDeclaration is not defined
    ```

    > **函数作用域：**
    >
    > 内部函数可以访问到外部函数变量



## 实例、原型、类成员

类的语法可以方便地定义存于实例上的成员、存于原型上的成员、存于类本身的成员

+ **实例成员**：只能由实例化对象访问，即 this 上的属性

+ **原型成员数据**：只能在外部添加：

    ```js
    class Test {
      // name: 'test', 报错
    }
    Test.name = 'test'
    console.log(Test.name)
    ```

+ **原型方法**：类中除 this 上定义的内容都会定义在类的原型上

    除此外，还可以直接在 prototype 中定义，利用`Object.assign`可以很方便地一次向类中添加多个方法：

    ```js
    Object.assign(Point.prototype, {	
        toString() {},
        toValue() {},
    });
    ```

+ **静态类成员**：`this`引用类本身，其余所有约定与原型成员同。静态类方法适用于 执行不特定于实例的操作和不要求存在类的实例 时。静态类方法非常适合作为实例工厂。

```js
class Location {
    constructor() {
        // 定义在实例上
        this.locate = () => console.log('instance', this);	
      // Location { }
    }
    // 定义在类的原型对象上
    locate() {
        console.log('prototype', this); // {}
    }
    // 定义在类本身上
    static locate() {
        console.log('class', this); // class [class Location]
    }
}
```



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



## 私有字段

私有字段不能在类外部访问，以`#`开头变量为标识
