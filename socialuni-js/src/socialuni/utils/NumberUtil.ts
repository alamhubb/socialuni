/**
 * @Author qingchi
 * @Date 2021-03-13 18:09
 * @Version 1.0
 */
export default class NumberUtil {
  static isNumber (obj) {
    // 等于number，或者有值且不为NaN
    return typeof obj === 'number' || (!!obj && !isNaN(obj))
  }

  static isAllNumber (val: string): boolean {
    return /^\d+$/.test(val)
  }

  static containNoNumber (val: string): boolean {
    return !NumberUtil.isAllNumber(val)
  }
}
