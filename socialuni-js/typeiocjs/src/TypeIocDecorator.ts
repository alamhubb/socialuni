import typeIocContainer from "./TypeIocContainer";
const fieldNameMetadataKey = '68f3275e05804ff4b8baec697b0ad1b5';
export function Service(serviceNames: string | string[]) {
    function registryService(serviceName: string, target: any, obj: any, ctx: any) {
        //逻辑校验，不可重复注册，同名
        if (!serviceName) {
            throw Error(`注册失败，不存在：${target.name}`);
        }
        // const propObj = typeIocContainer.containerGet(serviceName)
        // if (propObj) {
        //     throw new Error(`${target}:重复注册${target.constructor.name},已存在：${propObj.constructor.name}`)
        // }
        //给订阅的全部赋值
        typeIocContainer.publish(serviceName, obj);
        for (const fieldName in obj) {
            const iocServiceName = ctx.metadata[fieldNameMetadataKey];
            //如果存在注入名称
            if (iocServiceName) {
                if (iocServiceName === serviceName) {
                    // obj[fieldName] = obj
                }
                else {
                    const iocService = typeIocContainer.subAndGet(iocServiceName, fieldName, obj);
                    if (iocService) {
                        obj[fieldName] = iocService;
                    }
                }
            }
            else {
                console.error('没有serviename:', fieldName);
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
            }
            else {
                registryService(serviceNames, target, obj, ctx);
            }
        }
        catch (e) {
            console.error(e);
            console.error('注入失败：', target.name);
        }
    };
}
export function Resource(serviceName: string) {
    return (_target: any, ctx: any) => {
        ctx.metadata[fieldNameMetadataKey] = serviceName;
    };
}