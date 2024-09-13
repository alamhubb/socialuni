var __defProp = Object.defineProperty;
var __getOwnPropDesc = Object.getOwnPropertyDescriptor;

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

var __decorateClass = (decorators, target, key, kind) => {
    var result;
    if (kind > 1) {
        result = void 0;
    } else if (kind) {
        result = __getOwnPropDesc(target, key);
    } else {
        result = target;
    }

    for (var i = decorators.length - 1; i >= 0; i--) {
        var decorator = decorators[i];
        if (decorator) {
            result = kind ? decorator(target, key, result) : decorator(result) || result;
        }
    }

    if (kind && result) {
        __defProp(target, key, result);
    }

    return result;
};

let TestSerivce = class {
    testA;

    test() {
        console.log(11111);
        console.log(this.testA);
    }
};

__decorateClass([
    Resource(22)
], TestSerivce.prototype, "testA", 2);

/*TestSerivce = __decorateClass([
    Service,
    Reflect.metadata("29044b301990480eb6fbf0f50891e0de", "TestSerivce")
], TestSerivce);*/

export {
    TestSerivce as default
};

const test = new TestSerivce()
test.test()
