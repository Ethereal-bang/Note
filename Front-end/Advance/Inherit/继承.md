![Image](https://segmentfault.com/img/bVbd9t9?w=2162&h=990/view)

# 概念：constructor、原型对象、实例

constructor 通过`.prototype`引用与之关联的原型对象，原型对象也有一属性`constructor`指回 constructor，实例有一指针`__proto__`指向原型

+ constructor 用来创建实例对象，原型对象上的属性和方法是所有实例共享的

+ 实例与构造函数没有直接联系，与原型对象有直接联系：

    ```js
    person.__proto__ === Person.prototype;	// true
    person.__proto__.constructor === Person;	// true
    ```

![image-20211127221600256](https://gitee.com/ethereal-bang/images/raw/master/20211127221607.png)

+ <span style="font-size:22px">例子：</span>

    ```js
    function Obj(value) {
        this.value = value;
        this.next = null;   
    }
    Obj.prototype[Symbol.iterator] = function() {   // 在构造函数Obj原型上设置属性
        // ...
    }
    
    // 实例对象instance
    let one = new Obj(1)
    let two = new Obj(2)
    let three = new Obj(3)
    
    
    console.log(Obj.prototype) // { [Symbol(Symbol.iterator)]: [Function (anonymous)] }
    console.log(one.constructor)    // [Function: Obj]
    console.log(one.__proto__ === Obj.prototype)    // true
    console.log(Function.__proto__ === Function.prototype)  // true
    ```



# 原型链继承

```js
function Parent() {};
function Child() {};

# Child.prototype = new Parent();	// Child的原型指向Parent实例
```

+ <span style="color:green">简单、易实现</span>
+ <span style="color:red">无法实现多继承</span>[^ 1 ]
+ <span style="color:red">原型属性若是引用类型，所有原型链上的实例都会共享(*访问和修改* )引用</span>
+ <span style="color:red">子类型构造时不能向父类型传参</span>（——无法在不影响所有实例的情况向父类的构造函数传参）

引用值以及传参的问题导致原型链基本不会被单独使用



+ <span style="font-size:20px">`instanceof`：</span>

    `instanceof`用来检查实例的原型链中是否包含指定构造函数的原型：

    ```js
    person instanceof Person;	// true
    person instanceof Object; // true
    Person.prototype instanceof Object; // true	
    ```



# Constructor stealing[^ 2] 继承（经典继承）

子类构造函数中调用父类构造函数，通过`apply`等方法改变上下文。

```js
function Parent(nation) {
  this.nation = nation;
  this.getNation = function() {
    console.log(this.nation);
  };
}
function Child(nation, name) {
#  Parent.call(this, nation);	// 调用父类构造函数的同时将this绑定到Child的实例
  this.name = name;	// Child.prototype的属性
}
```

相当于在`Child`实例对象上运行了`Parent()`函数所有初始化代码

+ 实例的`__proto__`直接指向 Object
+ <span style="color:green">可以在子类 constructor 中向父类 constructor 传参</span>：相对于原型链继承，盗用构造函数继承的一个优点
+ <span style="color:red">不能继承原型属性/方法</span>——子类不能访问父类定义的方法（方法只能在构造函数定义）

+ <span style="color:red">函数不能复用</span>：主要缺点，也是使用构造函数模式自定义类型的问题——必须在构造函数中定义方法



# 组合继承（最常用）

也叫“伪经典继承”。结合了原型链和盗用构造函数的优点。

基本思路：原型链继承原型上属性、方法，通过盗用构造函数继承实例属性

```js
function Animal() {}
function Dog(name) {
  Animal.call(this);	// 盗用构造函数继承实例属性
  this.name = name;
}

Dog.prototype = new Animal();	// 原型链继承原型属性、方法
Dog.prototype.constructor = Dog;
```

+ 子类型`prototype`指向父类型实例，但原型的`constructor`指向自身
+ <span style="color:green">可重用</span>——方法定义在原型上
+ <span style="color:green">每个实例有自己的属性</span>
+ <span style="color:red">存效率问题——父类构造函数会被调用两次</span>：第一次是创建子类原型（*行 7*），第二次是子类构造函数中（*行 3*）



# 原型式继承

出发点是即使不自定义类型也可以**通过原型实现对象之间的信息共享**



+ <span style="font-size:20px">`object`函数：</span>

    这个`object`函数创建一个**临时构造函数**，传入对象（这里的`o`相当于`proto`）赋值给构造函数的原型，然后返回这个临时类型的实例。

    ```js
    function object(o) {
      funnction F() {};	// 新建一个空的构造函数
      F.prototype = o;
      return new F();
    }
    ```

    > 文章《JavaScript 中的原型式继承》介绍了一种不涉及严格意义上的构造函数的继承方法。他的出发点是即使不自定义类型也可以通过原型实现对象之间的信息共享，所以给出了以上函数。

    

+ <span style="font-size:20px">Object.create()：</span>

    ES5 通过新增的[`Object.create()`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Object/create)方法规范了原型式继承，只使用第一个参数使它与`object()`函数效果相同。

    > **Object.create()：**
    >
    > 创建一个对象，使用现有对象提供新创建对象的`__proto__`，返回这个带着指定的原型对象和属性的新对象



+ <span style="color:red">引用类型属性被所有对象实例共享</span>
+ <span style="color:red">无法向父类构造函数传值</span>
+ <span style="color:green">非常适合不需单独创建构造函数，但仍需在对象间共享信息的场合</span>——想在已有对象的基础上创建一个新对象。已有对象上定义着另一个对象也应该共享的信息



# 寄生式继承

原型式继承的基础上加入了工厂模式。

创建一个实现继承的函数，以某种方式增强对象后返回该对象：

```js
function createAnother (original) {
  // 1. 通过调用原型式继承的函数创建一个新对象
  let clone = Object.create(original);	
  // 2. 增强这个对象
  clone.sayHi = function() { console.log("hi") };
  // 3. 返回这个对象
  return clone;
}
```

+ <span style="color:red">函数难以重用</span>，与构造函数模式类似
+ <span style="color:green">适合主要关注对象，不在乎类型和构造函数的场景</span>



# 寄生组合式继承（最理想）

组合式继承也存效率问题，即父类构造函数会被调用两次。

寄生式组合继承通过盗用构造函数继承属性，但使用混合式原型链继承方法。基本思路是不通过父类构造函数给子类原型赋值，而是取得父类原型的一个副本，即是使用寄生式继承来继承父类原型，将返回的新对象赋值给子类型原型。基本模式如下：

```js
function inheritPrototype(subType, superType) {
  subType.prototype = Object.create(superType.prototype);	
  subType.prototype.constructor = subType;	// 不然constructor会是superType
}
```

+ 行 2 使用`Object.create`与直接赋值`superType.prototype`的区别：

    直接赋值相当于父类、子类共用同一原型，`Object.create`是创建了父类原型的一个副本。



`inheritPrototype`接收子类构造函数和父类构造函数。

+ <span style="color:green">减少了调用父构造函数次数</span>——不通过父构造函数给子类原型赋值，而是取得父类原型的一个副本（深拷贝）
+ <span style="color:green">不会在父类型 prototype 上创建多余属性</span>——子类原型修改时
+ <span style="color:green">原型链仍保持不变</span>（`instanceof`、`isPrototypeOf()`依旧有效）
+ 寄生式组合继承可以算是引用类型继承的最佳模式



# 类 Extends 继承

虽然类继承使用的是新语法，但背后依旧使用的是原型链

+ <span style="font-size:20px">继承基础：</span>

    使用`extend`能继承任何拥有`constructor`和原型的对象，意味着不仅可以继承一个类，还可以继承普通的构造函数：

    ```js
    // 继承类：
    class Vehicle {}
    class Bus extends Vehicle {}
    
    // 继承构造函数
    function Person() {}
    class Engineer extends Person {}
    
    // 类表达式中也可以使用 extends
    let Doctor = class extends Person {}
    ```



+ <span style="font-size:20px">子类构造函数与`super()`：</span>

    `super`方法必须在第一行，只能在派生类构造函数和静态方法上使用`super`：

    ```js
    class Vehicle {
        static identify() { console.log('vehicle') };
        constructor() {
            this.run = true;
        }
    }
    class Bus extends Vehicle {
        static identify() {
            super.identify();   // 通过super调用父类上定义的静态方法
        }
        constructor() {
            super();
        }
    }
    ```





# 参考

+ 总：

    《[JavaScript高级程序设计]()》

    [review/继承.md at main · Martinyua/review](https://github.com/Martinyua/review/blob/main/1.JS-Basic/%E7%BB%A7%E6%89%BF.md)



[^ 1 ]:继承多个
[^ 2]:盗用构造函数
