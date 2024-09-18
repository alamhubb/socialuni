import {TTTTAaaa} from "./plugins/TypeIoc/Testaa.ts";
import {Service, Resource} from "./plugins/TypeIoc/TypeIocDecorator.ts";

@Service
export default class TestSerivce {

    @Resource
    testA: TTTTAaaa

    test() {
        this.testA.eat()
    }
}



