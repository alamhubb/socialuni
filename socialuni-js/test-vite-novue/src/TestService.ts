function Resource(target, {kind, name}) {
    return function (initialValue) {
        console.log(target)   //undefined
        console.log(kind)   //field
        console.log(name)   //x
        console.log(initialValue) //1
        return 666;
    };
}

class TestSerivce {
    @Resource
    testA;

    test() {
        console.log(11111);
        console.log(this.testA);
    }
}

const newc = new TestSerivce()
newc.test()
