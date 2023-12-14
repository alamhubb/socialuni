/**
 * @Author qingchi
 * @Date 2021-03-05 20:09
 * @Version 1.0
 */
import JsonUtil from "./JsonUtil";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";

export default class UniStorageUtil {
    private static readonly devPre: string = 'dev_'


    static setObj(key: string, value: any) {
        if (socialuniSystemModule.isDev) {
            key = UniStorageUtil.devPre + key
        }
        if (value) {
            uni.setStorageSync(key, JsonUtil.toJson(value))
        } else {
            UniStorageUtil.remove(key)
        }
    }

    static getObj(key: string): any {
        if (socialuniSystemModule.isDev) {
            key = UniStorageUtil.devPre + key
        }
        const objStr: string = uni.getStorageSync(key)
        if (objStr) {
            return JsonUtil.toParse(objStr)
        }
        return null
    }

    static set(key: string, value: any): any {
        if (socialuniSystemModule.isDev) {
            key = UniStorageUtil.devPre + key
        }
        if (value || value === 0 || value === '') {
            //string直接转换的话会加上""
            if (value instanceof Object) {
                uni.setStorageSync(key, JsonUtil.toJson(value))
            } else {
                uni.setStorageSync(key, value)
            }
        } else {
            UniStorageUtil.remove(key)
        }
    }

    static get(key: string): any {
        if (socialuniSystemModule.isDev) {
            key = UniStorageUtil.devPre + key
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
            key = UniStorageUtil.devPre + key
        }
        uni.removeStorageSync(key)
    }
}
