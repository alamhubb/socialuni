var __defProp = Object.defineProperty;
var __getOwnPropDesc = Object.getOwnPropertyDescriptor;
var __decorateClass = (decorators, target, key, kind) => {
    var result = kind > 1 ? void 0 : kind ? __getOwnPropDesc(target, key) : target;
    for (var i = decorators.length - 1, decorator; i >= 0; i--)
        if (decorator = decorators[i])
            result = (kind ? decorator(target, key, result) : decorator(result)) || result;
    if (kind && result) __defProp(target, key, result);
    return result;
};

function Resource(value) {
    return function(target, propertyKey, descriptor) {
        console.log(value);
        console.log(target);
        console.log(propertyKey);
        console.log(descriptor);
        if (!descriptor) {
            descriptor = {
                enumerable: true,
                configurable: true,
                writable: true,
                value
            };
        } else {
            descriptor.value = value;
        }
        return descriptor;
    };
}
class TestSerivce {
    testA;
    test() {
        console.log(11111);
        console.log(this.testA);
    }
}
__decorateClass([
    Resource(22)
], TestSerivce.prototype, "testA", 2);

var newc = new TestSerivce();
newc.test();
