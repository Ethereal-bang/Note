function Animal(name) {
    this.name = name;
    this.obj = {    // Animal.prototype的所有实例都将共享这一属性
        a: 1,
    }
    this.say = () => { console.log("I`m animal named", this.name) };
    this.showObj = () => { console.log(this.obj.a) };
}

function Dog() {
    this.say = () => { console.log("汪！") };
    this.obj.a = 2;
}
// 原型链继承：
Dog.prototype = new Animal("dog");  // 无法在不影响所有实例的情况向父类构造函数传参
const dog = new Dog();
dog.showObj();  // 2
dog.say();
// 行10显示汪！，注掉显示animal   因为会顺着原型链向上查找
console.log(dog instanceof Animal)  // true