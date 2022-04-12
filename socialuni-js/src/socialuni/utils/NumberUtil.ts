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
  new EnumNumVO(1, '十'),
  new EnumNumVO(1, '壹'),
  new EnumNumVO(2, '贰'),
  new EnumNumVO(3, '叁'),
  new EnumNumVO(4, '肆'),
  new EnumNumVO(5, '伍'),
  new EnumNumVO(6, '陆'),
  new EnumNumVO(7, '柒'),
  new EnumNumVO(8, '捌'),
  new EnumNumVO(9, '玖'),
  new EnumNumVO(1, '拾')
].forEach(item => {
  NumberUtil.numberMap.set(item.name, String(item.value))
})
