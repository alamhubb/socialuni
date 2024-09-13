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

function Resource(target, {kind, name}) {
    return function (initialValue) {
        console.log(222222);
        console.log(target);
        console.log(kind);
        console.log(name);
        console.log(initialValue);
        console.log(3333);
        return 666;
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
    Resource
], TestSerivce.prototype, "testA", 2);
const newc = new TestSerivce();
newc.test();
