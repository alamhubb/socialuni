/**
 * @Author qingchi
 * @Date 2021-03-13 18:09
 * @Version 1.0
 */
export default class NumberUtil {
  static isNumber(val: string): boolean {
    return /^\d+$/.test(val)
  }

  static containNoNumber(val: string): boolean {
    return !NumberUtil.isNumber(val)
  }
}
