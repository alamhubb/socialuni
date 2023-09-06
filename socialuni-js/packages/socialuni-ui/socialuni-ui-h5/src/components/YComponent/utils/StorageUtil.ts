import ObjectUtil from "@/components/YComponent/utils/ObjectUtil";

export default class StorageUtil {
  // 改成any类型
  static setObj(key: string, value: any) {
    if (ObjectUtil.isEmpty(value)) {
      this.remove(key)
    } else {
      localStorage.setItem(key, ObjectUtil.toJson(value))
    }
  }

  static getObj(key: string): any {
    const objStr: string = localStorage.getItem(key)
    if (objStr) {
      return ObjectUtil.toParse(objStr)
    }
    return null
  }

  static remove(key: string) {
    localStorage.removeItem(key)
  }
}
