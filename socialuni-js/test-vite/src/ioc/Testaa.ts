import {TypeIocService} from "typeiocjs/src/TypeIocDecorator.ts";

export interface TTTTAaaa {
    eat()
}

@TypeIocService
export default class B implements TTTTAaaa {
    static name: haha = 'dada'

    eat() {
        console.log('eat')
    }

}

