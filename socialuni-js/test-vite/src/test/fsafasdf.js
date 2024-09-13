// 使用更具描述性的名称
const defineProperty = Object.defineProperty;

// 类定义
class MyClass {
    constructor() {
        const key = 'test'
        const object = this
        const value = '444'

        defineProperty(this, 'test', {enumerable: true, configurable: true, writable: true, value: '555'});
    }

    test() {
        console.log(11111);
    }
}

console.log(MyClass.prototype)
// applyDecorators([decoratorFunction(22)], MyClass.prototype, "testA", 2);

const mycl = new MyClass()

// delete mycl['test']

defineProperty(MyClass.prototype, 'test', {enumerable: true, configurable: true, writable: true, value: '666'});

// 应用装饰器到 `MyClass` 的 `testA` 属性

console.log(mycl['test'])
