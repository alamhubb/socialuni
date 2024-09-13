
function Resource(value) {
    console.log("Decorated property: ".concat(String(value)));
    return function (target, key) {
        return {
            enumerable: true,
            configurable: true,
            writable: true,
            value: value
        };
    };
}
var TestSerivce = /** @class */ (function () {
    function TestSerivce() {
        Object.defineProperty(this, "testA", {
            enumerable: true,
            configurable: true,
            writable: true,
            value: void 0
        });
    }
    Object.defineProperty(TestSerivce.prototype, "test", {
        enumerable: false,
        configurable: true,
        writable: true,
        value: function () {
            console.log(11111);
            console.log(this.testA);
            // this.testA.eat()
        }
    });
    (0,tslib__WEBPACK_IMPORTED_MODULE_0__.__decorate)([
        Resource(22),
        (0,tslib__WEBPACK_IMPORTED_MODULE_0__.__metadata)("design:type", Object)
    ], TestSerivce.prototype, "testA", void 0);
    return TestSerivce;
}());
/* harmony default export */ __webpack_exports__["default"] = (TestSerivce);

