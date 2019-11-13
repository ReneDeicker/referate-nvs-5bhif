//comment: Strg+K, Strg+C ... revert: Strg+K, Strg+U

/// Funktionen sind Objekte
// function Circle(radius) {
//     this.radius = radius;
//     this.draw = function () {
//         console.log('draw');
//     }
// }

// const circle = new Circle(1);






















/// Clonen eines Objekts
// const circle = {
//     radius: 1,
//     draw() {
//         console.log('draw');
//     }
// };

// const copyCircle = {};

// // alte Lösung
// for (let key in circle)
//     copyCircle[key] = circle[key];
// //  copyCircle['radius] = circle['radius'];

// console.log(copyCircle);

// // bessere Lösung
// const another_c1 = Object.assign({}, circle);

// const another_c2 = { ...circle };

// console.log(another_c1);
// console.log(another_c2);












/// Arrow Functions + Reduce()

// const numbers = [1, -1, 2, 3];
// a = accumulator; c = currentValue

// a = 1, c = -1 ==> a = 0
// a = 0, c =  2 ==> a = 2
// a = 2, c =  3 ==> a = 5
// const sum = numbers.reduce((accumulator, currentValue) =>
//     accumulator + currentValue
// );

// const maxNumber = numbers.reduce((a, b) => (a > b) ? a : b); // ternary operator ´?´

// console.log(sum);
// console.log(maxNumber);













/// Hoisting

// // Function Declaration
// walk(); // works

// function walk() {
//     console.log('walk');
// }

// // Function Expression
// //run(); // error

// console.log(x);
// let x = 1;
// const run = function () {
//     console.log('run');
// }














/// Rest Operator

// function sum(discount, ...prices) {
//     const total = prices.reduce((a, b) => a + b);
//     return total * (1 - discount);
// }

// console.log(sum(0.1, 20, 30, 1));


















/// Erstellen eines Objektes

// // Object Literal Syntax
// const circle1 = {
//     radius: 1,
//     location: {
//         x: 1,
//         y: 0
//     },
//     draw: function () {
//         console.log('draw');
//     }
// };

// // Factory Methode
// function createCircle(radius) {
//     return {
//         radius,
//         location: { // hardcoded
//             x: 1,
//             y: 0
//         },
//         draw: function () {
//             console.log('draw');
//         }
//     };
// }
// const circle2 = createCircle(2);

// // Constructor
// function Circle(radius) {
//     this.radius = radius;
//     this.location = { // hardcoded
//         x: 1,
//         y: 1
//     }
//     this.draw = function () {
//         console.log('draw');
//     }
// }
// const circle3 = new Circle(3);




















/// ES6 Classes

// const _radius = new WeakMap(); // private
// const _move = new WeakMap();   // private
// class Circle {
//     constructor(radius) {
//         _radius.set(this, radius);
//         _move.set(this, function () {
//             console.log('move');
//         })
//     }
//     draw() {
//         _move.get(this)(); // ist eine methode, deshalb -> ()

//         console.log('draw');
//     }
//     get radius() { // getter
//         return _radius.get(this);
//     }
//     set radius(value) { // setter
//         _radius.set(this, value);
//     }
// }

// const c = new Circle(1);





/// Inheritance

class Shape {
    constructor(color) {
        this.color = color;
    }
    move() {
        console.log('move shape');
    }
}

class Circle extends Shape {
    constructor(color, radius) {
        super(color);
        this.radius = radius;
    }
    draw() {
        console.log('draw circle');
    }
}

const c = new Circle('red', 1);
