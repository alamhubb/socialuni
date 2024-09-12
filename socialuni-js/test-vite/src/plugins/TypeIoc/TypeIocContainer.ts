import 'reflect-metadata'
import {reactive} from "vue";
import KeyValueObj from "./KeyValueObj";

export function Service(target) {
    console.log(9999)
    console.log(target)
    typeIocContainer.set(target)
}

export function Resource(target) {
    console.log(44444)
    for (const key in target) {
        console.log(key)
    }
    console.log(typeof target)
    console.log(target)
    console.log(target.b)
    console.log(target.$data)
    console.log(target.obj)
    console.log(target.typeIocContainer)
    console.log(5555)
}

export class TypeIocContainer {
    static readonly classInterfaceKey = 'a9360b695cff4e40aa417121d9b004a7'
    static readonly propertyInterfaceKey = '3a7b023a771d4e87a6caa1bef82cefdc'

    private readonly container = new Map()
    private readonly subscribe: Map<string, KeyValueObj<any>[]> = new Map()


    set(clas) {
        console.log(clas)
        console.log(clas.constructor)
        let result = Reflect.getMetadata(TypeIocContainer.classInterfaceKey, clas);
        console.log(result)
        if (result) {
            console.log(result)
            const propObj = this.container.get(result)
            if (propObj !== undefined) {
                throw new Error(`${clas}:重复注册${clas.constructor.name},已存在：${propObj.constructor.name}`)
            }
        } else {
            console.error(`注册失败，不存在：${clas.name}`)
        }
        try {
            const obj = new clas()
            for (const key in obj) {
                const keyRes = Reflect.getMetadata(TypeIocContainer.propertyInterfaceKey, obj, key);
                if (keyRes) {
                    const keyArray = this.subscribe.get(keyRes)
                    const keyValueObj = new KeyValueObj(key, obj)
                    if (keyArray) {
                        keyArray.push(keyValueObj)
                    } else {
                        this.subscribe.set(keyRes, [keyValueObj])
                    }
                    const propData = this.container.get(keyRes)
                    if (propData) {
                        obj[key] = propData
                    }
                }
            }
            if (result) {
                const array = this.subscribe.get(result)
                if (array) {
                    for (const never of array) {
                        never.value[never.key] = obj
                    }
                }
                this.container.set(result, obj)

                console.log('chenggognzhuce :', result)
            }
        } catch (e) {
            console.error(e)
        }
    }

    get(prop) {
        let result = Reflect.getMetadata(TypeIocContainer.propertyInterfaceKey, prop);
        const propObj = this.container.get(result)
        if (propObj === undefined) {
            return null
        }
        return propObj
    }
}

const typeIocContainer = reactive(new TypeIocContainer())
export default typeIocContainer
