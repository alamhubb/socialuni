/**
 * @Author qingchi
 * @Date 2021-03-13 18:09
 * @Version 1.0
 */
import EnumNumVO from '@/socialuni/constant/EnumNumVO'

export default class NumberUtil {
  static numberMap: Map<string, string> = new Map<string, string>()

  static getNumberByHanzi (hanzi) {
    const number = NumberUtil.numberMap.get(hanzi)
    if (number) {
      return number
    }
    return hanzi
  }

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

[
  new EnumNumVO(1, '一'),
  new EnumNumVO(2, '二'),
  new EnumNumVO(3, '三'),
  new EnumNumVO(4, '四'),
  new EnumNumVO(5, '五'),
  new EnumNumVO(6, '六'),
  new EnumNumVO(7, '七'),
  new EnumNumVO(8, '八'),
  new EnumNumVO(9, '九'),
  new EnumNumVO(1, '十')
].forEach(item => {
  NumberUtil.numberMap.set(item.name, String(item.value))
})
