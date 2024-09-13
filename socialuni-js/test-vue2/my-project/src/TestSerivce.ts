export interface TTTTAaaa {
    eat()
}

function Resource(value: number): any {
    console.log(`Decorated property: ${String(value)}`);
    return (target, key) => {
        return {
            enumerable: true,
            configurable: true,
            writable: true,
            value: value
        }
    }
}


export default class TestSerivce {

    @Resource(22)
    testA: TTTTAaaa

    test() {
        console.log(11111)
        console.log(this.testA)
        // this.testA.eat()
    }
}
