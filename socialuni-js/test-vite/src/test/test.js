// 使用更具描述性的名称
const defineProperty = Object.defineProperty;

const key = "prop"

// 类定义
class MyClass {
    constructor() {
        defineProperty(this, key, {enumerable: true, configurable: true, writable: true, value: 'value'});
    }
}

const my = new MyClass()
const my1 = new MyClass()

defineProperty(my1, key, {enumerable: true, configurable: true, writable: true, value: 'value1'});


console.log(my[key]) //value
