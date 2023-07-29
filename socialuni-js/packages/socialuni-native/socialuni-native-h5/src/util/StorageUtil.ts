/**
 * @Author qingchi
 * @Date 2021-03-05 20:09
 * @Version 1.0
 */
import {socialuniSystemModule} from "@socialuni/socialuni-util/src/store/SocialuniSystemModule";
import JsonUtil from "@socialuni/socialuni-util/src/util/JsonUtil";

export default class StorageUtil {
    private static readonly devPre: string = 'dev_'

    static setObj(key: string, value: any) {
        if (socialuniSystemModule.isDev) {
            key = StorageUtil.devPre + key
        }
        if (value) {
            localStorage.setItem(key, JsonUtil.toJson(value))
        } else {
            StorageUtil.remove(key)
        }
    }

    static getObj(key: string): any {
        if (socialuniSystemModule.isDev) {
            key = StorageUtil.devPre + key
        }
        const objStr: string = localStorage.getItem(key)
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
                localStorage.setItem(key, JsonUtil.toJson(value))
            } else {
                localStorage.setItem(key, value)
            }
        } else {
            StorageUtil.remove(key)
        }
    }

    static get(key: string): any {
        if (socialuniSystemModule.isDev) {
            key = StorageUtil.devPre + key
        }
        const objStr: string = localStorage.getItem(key)
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
        localStorage.removeItem(key)
    }
}
