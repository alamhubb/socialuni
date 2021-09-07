/**
 * @Author qingchi
 * @Date 2021-03-05 20:09
 * @Version 1.0
 */
import JsonUtil from '@/utils/JsonUtil'

export default class StorageUtil {
  static setObj (key: string, value: any) {
    if (value) {
      uni.setStorageSync(key, JsonUtil.toJson(value))
    } else {
      StorageUtil.remove(key)
    }
  }

  static getObj (key: string): any {
    const objStr: string = uni.getStorageSync(key)
    if (objStr) {
      return JsonUtil.jsonParse(objStr)
    }
    return null
  }

  static set (key: string, value: any): any {
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

  static get (key: string): any {
    const objStr: string = uni.getStorageSync(key)
    if (objStr) {
      try {
        return JsonUtil.jsonParse(objStr)
      } catch {
        return objStr
      }
    }
    return null
  }

  static remove (key: string) {
    uni.removeStorageSync(key)
  }
}
