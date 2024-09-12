import {Resource, Service} from "@/plugins/TypeIoc/TypeIocContainer";
import type {TTTTAaaa} from "@/plugins/TypeIoc/Testaa";

@Service
export default class TestSerivce {

    @Resource(22)
    testA: TTTTAaaa = 20

    test() {
        console.log(11111)
        console.log(this.testA)
        // this.testA.eat()
    }
}
