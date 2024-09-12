// 使用更具描述性的名称
const defineProperty = Object.defineProperty;
const getOwnPropertyDescriptor = Object.getOwnPropertyDescriptor;

// `definePropertyIfAbsent` 函数：在对象上定义属性（如果属性不存在）
function definePropertyIfAbsent(object, key, value) {
    console.log(object)
    console.log(key)
    defineProperty(object, key, {enumerable: true, configurable: true, writable: true, value});
}

// 类定义
class MyClass {
    constructor() {
        definePropertyIfAbsent(this, "testA"); // 初始化属性 `testA`
    }

    test() {
        console.log(11111);
    }
}

console.log(MyClass.prototype)
// applyDecorators([decoratorFunction(22)], MyClass.prototype, "testA", 2);

defineProperty(MyClass.prototype, 'testA', {enumerable: true, configurable: true, writable: true, value: '666'});

// 应用装饰器到 `MyClass` 的 `testA` 属性

const mycl = new MyClass()

console.log(mycl['testA'])
