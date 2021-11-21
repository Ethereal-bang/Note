function Animal(name) {
    this.name = name;
    this.arr = [0, 1];  // 引用类型
}
Animal.prototype.sayName = function () { console.log("it`s", this.name) };

function Dog(name, age) {
    // 继承属性
    Animal.call(this, name);

    this.age = age;
}
// 继承方法
Dog.prototype = new Animal("dog");
Dog.prototype.constructor = Dog;    // 没有这行：[Function: Animal]

Dog.prototype.sayAge = function () { console.log("age:", this.age) };

const d1 = new Dog("Jim", 2);
const d2 = new Dog("Harry", 1);
d1.sayName();  // Jim
d1.sayAge();   // 2
// 每个实例有自己的属性：
d2.arr.push(3);
console.log(d1.arr, d2.arr);    // [ 0, 1 ] [ 0, 1, 3 ]