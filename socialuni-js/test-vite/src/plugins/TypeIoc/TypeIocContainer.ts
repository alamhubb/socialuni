import 'reflect-metadata'


export default class TypeIocContainer {
    static readonly classInterfaceKey = 'a9360b695cff4e40aa417121d9b004a7'
    static readonly propertyInterfaceKey = '3a7b023a771d4e87a6caa1bef82cefdc'

    private readonly static container = new Map()

    static set(clas) {
        let result = Reflect.getMetadata(TypeIocContainer.classInterfaceKey, clas);
        const propObj = this.container.get(result)
        if (propObj !== undefined) {
            throw new Error(`${clas}:重复注册${result},已存在：${propObj}`)
        }
        this.container.set(result, clas)
    }

    static get(prop) {
        let result = Reflect.getMetadata(TypeIocContainer.propertyInterfaceKey, prop);
        const propObj = this.container.get(result)
        if (propObj === undefined) {
            return null
        }
        return propObj
    }
}

