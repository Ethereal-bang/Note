function Animal(species) {
    this.speacies = species;
}
function Dog(species, name) {
    Animal.call(this, species);
    this.name = name;
    this.say = () => { console.log(this.speacies, this.name) };
}
let d1 = new Dog("dog", "first");
let d2 = new Dog("dog", "second");
d1.say();   // dog first
d2.say();   // dog second
console.log(d1.__proto__)