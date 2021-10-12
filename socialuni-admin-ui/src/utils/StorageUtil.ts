import JsonUtil from '@/utils/JsonUtil'

export default class StorageUtil {
  // 改成any类型
  static setObj(key: string, value: any) {
    localStorage.setItem(key, JsonUtil.toJson(value || null))
  }

  static getObj(key: string): any {
    const objStr: string = localStorage.getItem(key)
    if (objStr) {
      return JsonUtil.jsonParse(objStr)
    }
    return null
  }

  static remove(key: string) {
    localStorage.removeItem(key)
  }
}
