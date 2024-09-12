import {Resource, Service} from "@/plugins/TypeIoc/TypeIocContainer";
import type {TTTTAaaa} from "@/plugins/TypeIoc/Testaa";

@Service
export default class TestSerivce {

    @Resource
    testA: TTTTAaaa

    test() {
        console.log(this.testA)
        this.testA.eat()
    }
}
