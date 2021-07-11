/**
 * @Author qingchi
 * @Date 2021-03-05 20:09
 * @Version 1.0
 */
import JsonUtil from '@/plugins/social/utils/JsonUtil'

export default class StorageUtil {
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
    console.log(typeof objStr)
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
