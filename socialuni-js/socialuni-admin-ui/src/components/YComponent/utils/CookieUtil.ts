import Cookies from 'js-cookie'
import ObjectUtil from "@/components/YComponent/utils/ObjectUtil";

export default class CookieUtil {
  // 改成any类型
  static setObj(key: string, value: any) {
    if (ObjectUtil.isEmpty(value)) {
      this.remove(key)
    } else {
      Cookies.set(key, value)
    }
  }

  static getObj(key: string): any {
    return Cookies.get(key)
  }

  static remove(key: string) {
    Cookies.remove(key)
  }
}
