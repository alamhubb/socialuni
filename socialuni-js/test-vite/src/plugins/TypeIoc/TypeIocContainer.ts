import 'reflect-metadata'
import {reactive} from "vue";
import KeyValueObj from "./KeyValueObj";
import {serviceSetHandler} from "./TypeIocServiceHandler";

export function Service(target) {
    serviceSetHandler(target)
}

export function Resource(value) {
    return function(target, propertyKey, descriptor) {
        console.log(value)
        console.log(target)
        console.log(propertyKey)
        console.log(descriptor)
        if (!descriptor) {
            descriptor = {
                enumerable: true,
                configurable: true,
                writable: true,
                value: value
            };
        } else {
            descriptor.value = value;
        }
        return descriptor;
    };
}

export class TypeIocContainer {
    static readonly interfaceResourceKey = 'a9360b695cff4e40aa417121d9b004a7'
    static readonly classResourceKey = '29044b301990480eb6fbf0f50891e0de'
    static readonly propertyResourceKey = '3a7b023a771d4e87a6caa1bef82cefdc'

    private readonly container: Map<string, Object> = new Map()
    private readonly subscribe: Map<string, Set<KeyValueObj<Object>>> = new Map()

    subscribeSet(key: string, value: Object) {
        this.subscribe.set(key, value)
    }

    subscribeGet(key: string) {
        this.subscribe.get(key)
    }

    containerSet(key: string, value: Object) {
        this.container.set(key, value)
    }

    containerGet(key: string) {
        return this.container.get(key)
        // let result = Reflect.getMetadata(TypeIocContainer.propertyResourceKey, prop);
        // const propObj = this.container.get(result)
        // if (propObj === undefined) {
        //     return null
        // }
        // return propObj
    }
}

const typeIocContainer = reactive(new TypeIocContainer())
export default typeIocContainer
