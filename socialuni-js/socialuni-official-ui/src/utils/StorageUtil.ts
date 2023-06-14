import ObjectUtil from './ObjectUtil'

export default class StorageUtil {
    // 改成any类型
    static setObj(key: string, value: any) {
        localStorage.setItem(key, ObjectUtil.toJson(value))
    }

    static getObj(key: string): any {
        const objStr: string = localStorage.getItem(key)
        if (objStr) {
            if (objStr === 'undefined' || objStr === 'null') {
                return null
            }
            return ObjectUtil.toParse(objStr)
        }
        return null
    }

    static remove(key: string) {
        localStorage.removeItem(key)
    }
}
