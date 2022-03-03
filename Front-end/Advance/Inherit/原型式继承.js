function object(proto) {
    function F() {}
    F.prototype = proto;
    return new F();
}
let Jack = {
    "school": "cqu",
    "friends": ["a",],
}

let Jim = Object.create(Jack, {
    "name": {
        value: "Jim",
    }
});
Jim.friends.push("b");  // 引用类型共享
console.log(Jim.school, Jim.name, Jack.friends);    // cqu Jim ['a', 'b']