import KeyValueObj from "./KeyValueObj";
import typeIocContainer, {TypeIocContainer} from "./TypeIocContainer";

export function serviceSetHandler(target) {
    console.log(target)
    console.log(target.constructor)
    const interfaceResult = Reflect.getMetadata(TypeIocContainer.interfaceResourceKey, target);
    const classResult = Reflect.getMetadata(TypeIocContainer.classResourceKey, target);
    //逻辑校验，不可重复注册，同名
    if (interfaceResult) {
        console.log(interfaceResult)
        const propObj = typeIocContainer.containerGet(interfaceResult)
        if (propObj !== undefined) {
            throw new Error(`${target}:重复注册${target.constructor.name},已存在：${propObj.constructor.name}`)
        }
    } else {
        console.error(`注册失败，不存在：${target.name}`)
    }
    if (classResult) {
        console.log(classResult)
        const propObj = typeIocContainer.containerGet(classResult)
        if (propObj !== undefined) {
            throw new Error(`${target}:重复注册${target.constructor.name},已存在：${propObj.constructor.name}`)
        }
    } else {
        console.error(`注册失败，不存在：${target.name}`)
    }
    try {
        //创建注入对象
        const obj = new target()
        for (const key in obj) {
            //获取当前类，属性上的注解
            const keyRes = Reflect.getMetadata(TypeIocContainer.propertyResourceKey, obj, key);
            //如果属性上存在注解
            if (keyRes) {
                //则判断是否已存在相关订阅，有订阅，把当前对象加入，没有则新建订阅，后续变化通知联动变化
                const keySet = typeIocContainer.subscribeGet(keyRes)
                const keyValueObj = new KeyValueObj(key, obj)
                if (keySet) {
                    keySet.add(keyValueObj)
                } else {
                    const set = new Set<KeyValueObj<Object>>
                    set.add(keyValueObj)
                    typeIocContainer.subscribeSet(keyRes, set)
                }
                const propData = typeIocContainer.containerGet(keyRes)
                console.log("congmaiphuoqu:", keyRes)
                if (propData) {
                    console.log("获取结果存在:")
                    console.log(propData)
                    // obj[key] = propData
                    console.log(obj)
                    console.log(key)
                }
            }
        }
        if (interfaceResult) {
            //找到相关订阅，设置值
            const array = typeIocContainer.subscribeGet(interfaceResult)
            if (array) {
                for (const never of array) {
                    // never.value[never.key] = obj
                }
            }
            typeIocContainer.containerSet(interfaceResult, obj)

            console.log('chenggognzhuce :', interfaceResult)
        }
        if (classResult) {
            //找到相关订阅，设置值
            const array = typeIocContainer.subscribeGet(classResult)
            if (array) {
                for (const never of array) {
                    // never.value[never.key] = obj
                }
            }
            typeIocContainer.containerSet(classResult, obj)
        }
    } catch (e) {
        console.error(e)
    }
}

export function serviceGetHandler(target) {

}
