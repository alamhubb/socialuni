// 使用更具描述性的名称
const defineProperty = Object.defineProperty;
const getOwnPropertyDescriptor = Object.getOwnPropertyDescriptor;

// `applyDecorators` 函数：应用装饰器到对象属性
function applyDecorators(decorators, target, propertyKey, condition) {
    let descriptor = null
    if (condition > 1) {
        descriptor = undefined
    } else {
        console.log(1111)
        console.log(getOwnPropertyDescriptor(target, propertyKey))
        descriptor = (condition ? getOwnPropertyDescriptor(target, propertyKey) : target)
    }
    for (let i = decorators.length - 1; i >= 0; i--) {
        const decorator = decorators[i];
        if (decorator) {
            descriptor = (condition ? decorator(target, propertyKey, descriptor) : decorator(descriptor)) || descriptor;
        }
    }

    if (condition && descriptor) {
        defineProperty(target, propertyKey, descriptor);
    }

    return descriptor;
}

// `definePropertyIfAbsent` 函数：在对象上定义属性（如果属性不存在）
function definePropertyIfAbsent(object, key, value) {
    if (!(key in object)) {
        object[key] = value;
    } else {
        console.log(object)
        console.log(key)
        defineProperty(object, key, {enumerable: true, configurable: true, writable: true, value});
    }
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

// 装饰器函数：简单打印和设置值
function decoratorFunction(value) {
    return function (target, propertyKey, descriptor) {
        if (!descriptor) {
            descriptor = {
                enumerable: true,
                configurable: true,
                writable: true,
                value: value
            };
        } else {
            descriptor.value = value;
        }
        return descriptor;
    };
}

console.log(MyClass.prototype)
// applyDecorators([decoratorFunction(22)], MyClass.prototype, "testA", 2);

defineProperty(MyClass.prototype, 'testA', {enumerable: true, configurable: true, writable: true, value: '666'});

// 应用装饰器到 `MyClass` 的 `testA` 属性

const mycl = new MyClass()

console.log(mycl['testA'])
