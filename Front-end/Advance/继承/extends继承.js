// 类：
class Person {
    say() { console.log("hi") }
}
console.log(Person)	// [class Person]
console.log(typeof Person)	// function
console.log(Person.prototype)   // {}
console.log(Person.prototype.constructor)   // [class Person]
let p = new Person();
console.log(p instanceof Person.prototype.constructor)  // true
console.log(Person.constructor)

class Test {
    // name: 'test'    报错
    constructor() {
        this.position = 'instance'
        this.locate = () => console.log("instance");
    }
    locate() { console.log('prototype') };

    // static locate() {
    //
    // }
}
Test.nickName = 'test'
console.log(Test.nickName)
Test.prototype.locate()
console.log(new Test().position)
class Location {
    constructor() {
        // 定义在实例上
        this.locate = () => console.log('instance', this);	// Location { }
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
let l = new Location();
l.locate()	// instance, Person {}
Location.prototype.locate();	// prototype, {constructor: ...}
Location.locate();	// class, class Person {}

// extends:
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
Bus.identify()