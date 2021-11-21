function createAnother (original) {
    let clone = Object.create(original);
    clone.sayHi = () => { console.log("hi") };
    return clone;
}
let parent = {
    nation: "China",
}

let child = createAnother(parent);  // child具有所有parent的属性方法及新方法
console.log(child.nation)