import {TTTTAaaa} from "./TypeIoc/Testaa.ts";
import {Service, Resource} from "./TypeIoc/TypeIocDecorator.ts";

@Service
export default class TestSerivce {

    @Resource
    testA: TTTTAaaa

    test() {
        this.testA.eat()
    }
}



