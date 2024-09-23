import typeIocContainer from "./TypeIocContainer";
import UUIDUtil from "qing-util/src/util/UUIDUtil";

const fieldResourceNameMetadataKey = '68f3275e05804ff4b8baec697b0ad1b5';
const fieldBeanNameMetadataKey = '91e2f3b6241948279fe29f8dd376b585';
export const spliceSymbol = '$$';

// 定义命名空间
export function TypeIocService(serviceNames: string | string[]) {
    function registryService(serviceName: string, target: any, obj: any, ctx: any) {
        //逻辑校验，不可重复注册，同名
        if (!serviceName) {
            throw Error(`注册失败，不存在：${target.name}`);
        }
        console.log(ctx)
        // const propObj = typeIocContainer.containerGet(serviceName)
        // if (propObj) {
        //     throw new Error(`${target}:重复注册${target.constructor.name},已存在：${propObj.constructor.name}`)
        // }
        //给订阅的全部赋值
        typeIocContainer.publish(serviceName, obj);
        for (const fieldName in obj) {
            //遍历所有属性，查看是否有需要注入的属性
            const resourceName = ctx.metadata[fieldResourceNameMetadataKey + spliceSymbol + fieldName];
            //如果存在注入名称
            if (resourceName) {
                //注入的不为自己
                if (resourceName !== serviceName) {
                    // obj[fieldName] = obj
                    const iocService = typeIocContainer.subAndGet(resourceName, fieldName, obj);
                    if (iocService) {
                        obj[fieldName] = iocService;
                    }
                }
            }

            const beanName = ctx.metadata[fieldBeanNameMetadataKey + spliceSymbol + fieldName];
            if (beanName) {
                let beanData = null;
                if (typeof obj[fieldName] === 'function') {
                    beanData = obj[fieldName]()
                } else {
                    beanData = obj[fieldName]
                }
                typeIocContainer.publish(beanName, beanData);
            }
        }
    }

    return (target: any, ctx: any) => {
        try {
            //创建注入对象
            const obj = new target();
            if (Array.isArray(serviceNames)) {
                for (const serviceName of serviceNames) {
                    registryService(serviceName, target, obj, ctx);
                }
            } else {
                registryService(serviceNames, target, obj, ctx);
            }
        } catch (e) {
            console.error(e);
            console.error('注入失败：', target.name);
        }
    };
}

export function TypeIocResource(serviceName: string) {
    return (_target: any, ctx: any) => {
        ctx.metadata[fieldResourceNameMetadataKey + spliceSymbol + ctx.name] = serviceName;
    };
}

//作用，添加bean名称，service注册时，遇到bean，则添加名称，然后执行service时，获取这个属性的matedata,根据metadata，执行function，或者 属性，获取结果，放入map，注册bean
export function TypeIocBean(serviceName: string) {
    return (_target: any, ctx: any) => {
        ctx.metadata[fieldBeanNameMetadataKey + spliceSymbol + ctx.name] = serviceName;
    };
}


//给装饰器添加唯一标识
// Service['uuid'] = '04978640a2c0478ab854ddea66822844'
// Resource['uuid'] = '91e2f3b6241948279fe29f8dd376b585'
// Bean['uuid'] = '0d06e8f4990d430686471af814ccb9bb'
