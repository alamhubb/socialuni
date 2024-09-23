import {Resource, Service} from "typeiocjs/src/TypeIocDecorator.ts";
import {TTTTAaaa} from "../../../../Testaa.ts";

@Service
export default class TestSerivce {

    @Resource
    testA: TTTTAaaa

    test() {
        this.testA.eat()
    }
}



