var __defProp111 = Object.defineProperty;

function __defProp(target, key, result) {
    console.log('zhixing target', target)
    console.log('zhixing key', key)
    console.log('zhixing result', result)
    Object.defineProperty(target, key, result)
}

var __getOwnPropDesc = Object.getOwnPropertyDescriptor;
var __decorateClass = (decorators, target, key, kind) => {
    var result;

    if (kind > 1) {
        result = void 0;
    } else if (kind) {
        result = __getOwnPropDesc(target, key);
    } else {
        result = target;
    }
    console.log('result11111', result)

    for (var i = decorators.length - 1, decorator; i >= 0; i--) {
        if (decorator = decorators[i]) {
            if (kind) {
                result = decorator(target, key, result) || result;
            } else {
                result = decorator(result) || result;
            }
        }
    }
    console.log('result2222', result)
    if (kind && result) {
        __defProp(target, key, result);
    }

    console.log('result3333', result)
    return result;
};

import "reflect-metadata";

function Resource(value) {
    return function (target, propertyKey, descriptor) {
        console.log(value)
        console.log(target)
        console.log(propertyKey)
        console.log(descriptor)
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

export default class TestSerivce {
    testA;

    test() {
        console.log(11111);
        console.log(this.testA);
    }
}

__decorateClass([
    Resource(22)
], TestSerivce.prototype, "testA", 2);


const test = new TestSerivce()

/*
__defProp(TestSerivce.prototype, 'testA', {
    enumerable: true,
    configurable: true,
    writable: true,
    value: 33
})
*/

console.log(test.testA)
