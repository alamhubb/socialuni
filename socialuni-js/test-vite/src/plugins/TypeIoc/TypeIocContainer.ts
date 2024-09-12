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
    static readonly interfaceResourceKey = 'a9360b695cff4e40aa417121d9b004a7'
    static readonly classResourceKey = '29044b301990480eb6fbf0f50891e0de'
    static readonly propertyResourceKey = '3a7b023a771d4e87a6caa1bef82cefdc'

    private readonly container = new Map()
    private readonly subscribe: Map<string, Set<KeyValueObj<any>>> = new Map()


    set(clas) {
        console.log(clas)
        console.log(clas.constructor)
        const interfaceResult = Reflect.getMetadata(TypeIocContainer.interfaceResourceKey, clas);
        const classResult = Reflect.getMetadata(TypeIocContainer.classResourceKey, clas);
        //逻辑校验，不可重复注册，同名
        if (interfaceResult) {
            console.log(interfaceResult)
            const propObj = this.container.get(interfaceResult)
            if (propObj !== undefined) {
                throw new Error(`${clas}:重复注册${clas.constructor.name},已存在：${propObj.constructor.name}`)
            }
        } else {
            console.error(`注册失败，不存在：${clas.name}`)
        }
        if (classResult) {
            console.log(classResult)
            const propObj = this.container.get(classResult)
            if (propObj !== undefined) {
                throw new Error(`${clas}:重复注册${clas.constructor.name},已存在：${propObj.constructor.name}`)
            }
        } else {
            console.error(`注册失败，不存在：${clas.name}`)
        }
        try {
            //创建注入对象
            const obj = new clas()
            for (const key in obj) {
                //获取当前类，属性上的注解
                const keyRes = Reflect.getMetadata(TypeIocContainer.propertyResourceKey, obj, key);
                //如果属性上存在注解
                if (keyRes) {
                    //则判断是否已存在相关订阅，有订阅，把当前对象加入，没有则新建订阅，后续变化通知联动变化
                    const keySet = this.subscribe.get(keyRes)
                    const keyValueObj = new KeyValueObj(key, obj)
                    if (keySet) {
                        keySet.add(keyValueObj)
                    } else {
                        const set = new Set<KeyValueObj<Object>>
                        set.add(keyValueObj)
                        this.subscribe.set(keyRes, set)
                    }
                    const propData = this.container.get(keyRes)
                    console.log("congmaiphuoqu:", keyRes)
                    if (propData) {
                        console.log("获取结果存在:")
                        console.log(propData)
                        obj[key] = propData
                        console.log(obj)
                        console.log(key)
                    }
                }
            }
            if (interfaceResult) {
                //找到相关订阅，设置值
                const array = this.subscribe.get(interfaceResult)
                if (array) {
                    for (const never of array) {
                        never.value[never.key] = obj
                    }
                }
                this.container.set(interfaceResult, obj)

                console.log('chenggognzhuce :', interfaceResult)
            }
            if (classResult) {
                //找到相关订阅，设置值
                const array = this.subscribe.get(classResult)
                if (array) {
                    for (const never of array) {
                        never.value[never.key] = obj
                    }
                }
                this.container.set(classResult, obj)
            }
        } catch (e) {
            console.error(e)
        }
    }

    get(prop) {
        console.log(prop)
        console.log(888888)
        return this.container.get(prop)
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
