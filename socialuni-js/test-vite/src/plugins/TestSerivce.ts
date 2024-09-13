import {Resource} from "@/plugins/TypeIoc/TypeIocContainer";
import type {TTTTAaaa} from "@/plugins/TypeIoc/Testaa";

export default class TestSerivce {

    @Resource(22)
    testA: TTTTAaaa

    test() {
        console.log(11111)
        console.log(this.testA)
        // this.testA.eat()
    }
}
