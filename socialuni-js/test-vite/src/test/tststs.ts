function Resource(target: any, propertyKey: string) {
    // 直接修改属性的初始值为1
    let value = 1; // 要设置的默认值

    Object.defineProperty(target, propertyKey, {
        get() {
            return value;
        },
        set(newValue) {
            value = newValue;
        },
        enumerable: true,
        configurable: true,
    });
    return target
}

export class TestService1111 {
    @Resource
    testA: any;  // 类型可以是 any，稍后将通过装饰器赋值

    printValue() {
        console.log(this.testA); // 打印 1
    }
}
