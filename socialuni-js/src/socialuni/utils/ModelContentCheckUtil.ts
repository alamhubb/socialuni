import AlertUtil from '@/socialuni/utils/AlertUtil'
import EnumNumVO from '@/socialuni/constant/EnumNumVO'

export default class ModelContentCheckUtil {
  static numberMap: Map<string, string> = new Map<string, string>()

  static getNumberByHanzi (hanzi) {
    const number = ModelContentCheckUtil.numberMap.get(hanzi)
    if (number) {
      return number
    }
    return hanzi
  }

  static hasUn18Content (content) {
    //替换汉字数字
    content = content.replace(/\S/g, (match) => {
      return ModelContentCheckUtil.getNumberByHanzi(match)
    })
    //删除非数字，字母
    content = content.replace(/[^\u4E00-\u9FA5\w]*/g, '')
    //禁止发布包含小于18年龄的岁数
    //这种不匹配
    const reg = /\d+/g
    content.replace(reg, (match) => {
      const num = Number(match)
      if (match.length === 2 && num < 18) {
        AlertUtil.error('根据平台规则，禁止发布包含小于18岁未成年的内容，规避此规则内容会按违反社区规则进行封号处罚')
        return
      }
      return match
    })
  }
}

[
  new EnumNumVO(0, '零'),
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
  new EnumNumVO(1, '拾'),
  new EnumNumVO(1, '①'),
  new EnumNumVO(2, '②'),
  new EnumNumVO(3, '③'),
  new EnumNumVO(4, '④'),
  new EnumNumVO(5, '⑤'),
  new EnumNumVO(6, '⑥'),
  new EnumNumVO(7, '⑦'),
  new EnumNumVO(8, '⑧'),
  new EnumNumVO(9, '⑨')
].forEach(item => {
  ModelContentCheckUtil.numberMap.set(item.name, String(item.value))
})
