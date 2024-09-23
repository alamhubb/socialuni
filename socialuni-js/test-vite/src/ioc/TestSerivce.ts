import {TypeIocResource, TypeIocService} from "typeiocjs/src/TypeIocDecorator.ts";
import {TTTTAaaa} from "./Testaa.ts";

@TypeIocService
export default class TestSerivce {

    @TypeIocResource
    testA: TTTTAaaa

    test() {
        this.testA.eat()
    }
}



