class MyClass {
    constructor() {
        // Constructor logic here
    }
    // 其他方法或属性
}

// 使用 `Object.defineProperty` 来定义或修改 `testA` 属性
Object.defineProperty(MyClass.prototype, 'testA', {
    enumerable: true,
    configurable: true,
    writable: true,
    value: '666'
});

// 创建一个 MyClass 实例
const myInstance = new MyClass();

// 输出 testA 属性的值
console.log(myInstance['testA']); // 输出: 666
