export default class ColorUtil {
  // 深蓝
  static blueDeep = 'blueDeep'
  // 亮绿
  static greenLight = 'greenLight'
  // 深灰
  static greyDeep = 'greyDeep'
  // 黄色
  static yellow = 'yellow'
  // 红色
  static red = 'red'
  // 浅蓝
  static blueLight = 'blueLight'
  // 深紫
  static purpleDeep = 'purpleDeep'
  // 橙色
  static orange = 'orange'
  // 深绿
  static greenDeep = 'greenDeep'
  // 紫色
  static purple = 'purple'

  static echarts_colors: string [] = [
    '#5b8ff9',
    '#38d193',
    '#626c91',
    '#f6bd15',
    '#e86552',
    '#30b1e0',
    '#6a23cc',
    '#ff9844',
    '#1d9493',
    '#ba40d9'
  ]

  static echarts_colors_rotate: string [] = [
    '#3eb753',
    '#1f63db',
    '#f5a339'
  ]

  static colors: string [] = [
    ColorUtil.blueDeep,
    ColorUtil.greenLight,
    ColorUtil.greyDeep,
    ColorUtil.yellow,
    ColorUtil.red,
    ColorUtil.blueLight,
    ColorUtil.purpleDeep,
    ColorUtil.orange,
    ColorUtil.greenDeep,
    ColorUtil.purple
  ]

  static getColorByIndex(index): string {
    return ColorUtil.echarts_colors[(index + 1) % ColorUtil.echarts_colors.length]
  }

  // 获取行checkbox 颜色
  static getCheckedBoxClass(row, index): string {
    if (index < 5) {
      return row.checked
        ? 'checkbox-bg-' + ColorUtil.colors[index]
        : 'checkbox-border-' + ColorUtil.colors[index]
    }
    return
  }
}
