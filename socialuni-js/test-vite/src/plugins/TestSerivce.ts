import type {TTTTAaaa} from "@/plugins/TypeIoc/Testaa";
import {serviceSetHandler} from "@/plugins/TypeIoc/TypeIocServiceHandler";

function Resource(value) {
    return function(target, propertyKey, descriptor) {
        console.log(value)
        console.log(target)
        console.log(propertyKey)
        console.log(descriptor)
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

export function Service(target) {
}

@Service
export default class TestSerivce {

    @Resource(22)
    testA: TTTTAaaa

    test() {
        console.log(11111)
        console.log(this.testA)
        // this.testA.eat()
    }
}
