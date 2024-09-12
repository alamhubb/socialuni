import {Service} from "@/plugins/TypeIoc/TypeIocContainer";
import 'reflect-metadata'

import {TypeIocContainer} from "@/plugins/TypeIoc/TypeIocContainer";

console.log(123)

export interface TTTTAaaa {
    eat()
}


console.log('zhxingle23')

@Service
export default class B implements TTTTAaaa {
    static name: haha = 'dada'

    eat() {
        console.log('eat123')
    }

}

