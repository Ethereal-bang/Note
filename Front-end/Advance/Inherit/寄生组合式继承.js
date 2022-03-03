function Parent(name) {
    this.name = name;
    this.arr = [1];
}
function Child(name, age) {
    Parent.call(this, name);
    this.age = age;
}

function inherentPrototype(child, parent) {
    // child.prototype = parent.prototype;  // 修改Child原型时会影响Parent原型
    child.prototype = Object.create(parent.prototype);  // 不会修改Parent原型属性
    child.prototype.constructor = child;
}
inherentPrototype(Child, Parent)
Child.prototype.sayHi = function () { console.log(this.name) }

console.log(Child.prototype, Parent.prototype)
/*Parent {
    constructor: [Function: Child],
    sayHi: [Function (anonymous)]
} {}*/
let child = new Child("c", 19)
let parent = new Parent("p")
console.log(child instanceof Parent) // true
child.arr.push(2);
console.log(child, parent)
// Child { name: 'c', arr: [ 1, 2 ], age: 19 } Parent { name: 'p', arr: [ 1 ] }
