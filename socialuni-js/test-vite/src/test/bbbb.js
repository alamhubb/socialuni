// 使用更具描述性的名称
const defineProperty = Object.defineProperty;
const getOwnPropertyDescriptor = Object.getOwnPropertyDescriptor;

// `applyDecorators` 函数：应用装饰器到对象属性
function applyDecorators(decorators, target, propertyKey, condition) {
    console.log(decorators)
    let descriptor = condition > 1 ? undefined : (condition ? getOwnPropertyDescriptor(target, propertyKey) : undefined);

    console.log(descriptor)
    for (let i = decorators.length - 1; i >= 0; i--) {
        const decorator = decorators[i];
        console.log(decorators)
        console.log(3333)
        console.log(decorator)
        if (decorator) {
            console.log(444)
            console.log(decorator)
            const res = decorator(target, propertyKey, descriptor)
            console.log(res)
            descriptor = res || descriptor;
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
        defineProperty(object, key, {enumerable: true, configurable: true, writable: true, value});
    }
}

// `definePropertyForKey` 函数：确保对象的属性值
function definePropertyForKey(object, key, value) {
    definePropertyIfAbsent(object, typeof key !== "symbol" ? key + "" : key, value);
}

// 类定义
class MyClass {
    constructor() {
        definePropertyForKey(this, "testA"); // 初始化属性 `testA`
    }

    test() {
        console.log(11111);
    }
}

// 修改后的装饰器函数：返回一个属性描述符
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
            descriptor.value = value; // 设置属性值
        }
        return descriptor;
    };
}

// 应用装饰器到 `MyClass` 的 `testA` 属性
applyDecorators([decoratorFunction(22)], MyClass.prototype, "testA", 2);

const myInstance = new MyClass();

console.log(myInstance['testA']); // 输出 22
