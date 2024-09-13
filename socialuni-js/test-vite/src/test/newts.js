var __esDecorate = (this && this.__esDecorate) || function (ctor, descriptorIn, decorators, contextIn, initializers, extraInitializers) {
    function accept(f) {
        if (f !== void 0 && typeof f !== "function") throw new TypeError("Function expected");
        return f;
    }

    var kind = contextIn.kind;
    var key;
    if (kind === "getter") {
        key = "get";
    } else if (kind === "setter") {
        key = "set";
    } else {
        key = "value";
    }

    var target = !descriptorIn && ctor ? contextIn["static"] ? ctor : ctor.prototype : null;
    var descriptor = descriptorIn || (target ? Object.getOwnPropertyDescriptor(target, contextIn.name) : {});
    var _, done = false;

    for (var i = decorators.length - 1; i >= 0; i--) {
        var context = {};
        for (var p in contextIn) {
            context[p] = p === "access" ? {} : contextIn[p];
        }
        for (var p in contextIn.access) {
            context.access[p] = contextIn.access[p];
        }

        context.addInitializer = function (f) {
            if (done) throw new TypeError("Cannot add initializers after decoration has completed");
            extraInitializers.push(accept(f || null));
        };

        var result = (0, decorators[i])(kind === "accessor" ? {
            get: descriptor.get,
            set: descriptor.set
        } : descriptor[key], context);

        if (kind === "accessor") {
            if (result !== void 0) {
                if (result === null || typeof result !== "object") throw new TypeError("Object expected");
                if (_ = accept(result.get)) descriptor.get = _;
                if (_ = accept(result.set)) descriptor.set = _;
                if (_ = accept(result.init)) initializers.unshift(_);
            }
        } else {
            if (_ = accept(result)) {
                if (kind === "field") {
                    initializers.unshift(_);
                } else {
                    descriptor[key] = _;
                }
            }
        }
    }

    if (target) Object.defineProperty(target, contextIn.name, descriptor);
    done = true;
};

var __runInitializers = (this && this.__runInitializers) || function (thisArg, initializers, value) {
    var useValue = arguments.length > 2;
    for (var i = 0; i < initializers.length; i++) {
        value = useValue ? initializers[i].call(thisArg, value) : initializers[i].call(thisArg);
    }
    return useValue ? value : void 0;
};

function Resource(target, _a) {
    var kind = _a.kind;
    var name = _a.name;

    return function (initialValue) {
        console.log(target); // undefined
        console.log(kind); // field
        console.log(name); // x
        console.log(initialValue); // 1
        return 666;
    };
}

var TestSerivce = function () {
    var _a;
    var _testA_decorators;
    var _testA_initializers = [];
    var _testA_extraInitializers = [];

    return _a = /** @class */ (function () {
        function TestSerivce() {
            this.testA = __runInitializers(this, _testA_initializers, void 0);
            __runInitializers(this, _testA_extraInitializers);
        }

        TestSerivce.prototype.test = function () {
            console.log(11111);
            console.log(this.testA);
        };

        return TestSerivce;
    }()),

        (function () {
            var _metadata = typeof Symbol === "function" && Symbol.metadata ? Object.create(null) : void 0;
            _testA_decorators = [Resource];
            __esDecorate(null, null, _testA_decorators, {
                kind: "field",
                name: "testA",
                static: false,
                private: false,
                access: {
                    has: function (obj) {
                        return "testA" in obj;
                    }, get: function (obj) {
                        return obj.testA;
                    }, set: function (obj, value) {
                        obj.testA = value;
                    }
                },
                metadata: _metadata
            }, _testA_initializers, _testA_extraInitializers);
            if (_metadata) Object.defineProperty(_a, Symbol.metadata, {
                enumerable: true,
                configurable: true,
                writable: true,
                value: _metadata
            });
        })(),

        _a;
}();

var newc = new TestSerivce();
newc.test();
