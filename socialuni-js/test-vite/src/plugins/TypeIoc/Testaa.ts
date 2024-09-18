import 'reflect-metadata'
import {Service} from "./TypeIocDecorator.ts";

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

