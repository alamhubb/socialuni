/**
 * @Author qingchi
 * @Date 2021-03-05 20:09
 * @Version 1.0
 */
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import JsonUtil from "qing-util/src/util/JsonUtil";

export default class StorageUtil {
    private static readonly devPre: string = 'dev_'


    static setObj(key: string, value: any) {
        if (socialuniSystemModule.isDev) {
            key = StorageUtil.devPre + key
        }
        if (value) {
            uni.setStorageSync(key, JsonUtil.toJson(value))
        } else {
            StorageUtil.remove(key)
        }
    }

    static getObj(key: string): any {
        if (socialuniSystemModule.isDev) {
            key = StorageUtil.devPre + key
        }
        const objStr: string = uni.getStorageSync(key)
        if (objStr) {
            return JsonUtil.toParse(objStr)
        }
        return null
    }

    static set(key: string, value: any): any {
        if (socialuniSystemModule.isDev) {
            key = StorageUtil.devPre + key
        }
        if (value || value === 0 || value === '') {
            //string直接转换的话会加上""
            if (value instanceof Object) {
                uni.setStorageSync(key, JsonUtil.toJson(value))
            } else {
                uni.setStorageSync(key, value)
            }
        } else {
            StorageUtil.remove(key)
        }
    }

    static get(key: string): any {
        if (socialuniSystemModule.isDev) {
            key = StorageUtil.devPre + key
        }
        const objStr: string = uni.getStorageSync(key)
        if (objStr) {
            try {
                return JsonUtil.toParse(objStr)
            } catch {
                return objStr
            }
        }
        return null
    }

    static remove(key: string) {
        if (socialuniSystemModule.isDev) {
            key = StorageUtil.devPre + key
        }
        uni.removeStorageSync(key)
    }
}
