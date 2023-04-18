/**
 * @Author qinkaiyuan
 * @Date 2021-03-13 18:09
 * @Version 1.0
 */
export default class Arrays {
    static has(array, value, prop?: string) {
        return !!this.find(array, value, prop)
    }

    static find(array, value, prop?: string) {
        return array[this.findIndex(array, value, prop)]
    }

    static replace(array: any[], index, row: any) {
        // @ts-ignore
        return array.splice(index, 1, row)
    }

    static deleteIndex(array: any[], index: number) {
        // @ts-ignore
        return array.splice(index, 1)
    }

    static findIndex(array: any[], value, prop?: string) {
        const index = array.findIndex(item => {
            return prop ? (item[prop] === value) : (item === value)
        })
        return index
    }

    // 插叙添加时可选择的数据源,返回删除的数据
    static deleteByPropName(array: any[], value, prop?: string) {
        const delIndex = this.findIndex(array, value, prop)
        const deleteData = array[delIndex]
        if (delIndex > -1) {
            array.splice(delIndex, 1)
            return deleteData
        }
    }

    static delete<T>(arrays: T[], predicate: (value: T, index: number, obj: T[]) => boolean): T {
        const delIndex = arrays.findIndex(predicate)
        const deleteData = arrays[delIndex]
        if (delIndex > -1) {
            arrays.splice(delIndex, 1)
            return deleteData
        }
        return null
    }

    static deleteAry(array, delAry, prop?: string) {
        for (const delAryElement of delAry) {
            this.deleteByPropName(array, delAryElement[prop] || delAryElement, prop)
        }
    }

    static unique<T>(array: T[], prop?: string | ((item) => string)) {
        const res = new Map()
        return array.filter((item) => {
            if (typeof prop === 'string' || !prop) {
                //@ts-ignore
                return !res.has(item[prop] || item) && res.set(item[prop] || item, 1)
            } else {
                return !res.has(prop(item) || item) && res.set(prop(item), 1)
            }
        })
    }

    static hasRepeat(array: any[], prop?: string) {
        const res = new Map()
        for (const item of array) {
            const key = item[prop] || item
            if (res.has(key)) {
                return key
            }
            res.set(key, 1)
        }
        return null
    }
}
