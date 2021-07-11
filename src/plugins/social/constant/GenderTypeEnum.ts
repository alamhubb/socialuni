import EnumNumVO from '@/const/EnumNumVO'

export default class GenderTypeEnum {
  // 系统
  static readonly unknown: number = 0
  static readonly male: number = 1
  static readonly female: number = 2

  static readonly unknownEnum: EnumNumVO = new EnumNumVO(GenderTypeEnum.unknown, '')
  static readonly maleEnum: EnumNumVO = new EnumNumVO(GenderTypeEnum.male, 'boy')
  static readonly femaleEnum: EnumNumVO = new EnumNumVO(GenderTypeEnum.female, 'girl')

  static readonly enums: EnumNumVO[] = [
    GenderTypeEnum.unknownEnum,
    GenderTypeEnum.maleEnum,
    GenderTypeEnum.femaleEnum
  ]

  static readonly enum_map: Map<number, string> = new Map<number, string>()
  static readonly enum_map_label: Map<string, number> = new Map<string, number>()

  static getEnumName (value: number) {
    return GenderTypeEnum.enum_map.get(value)
  }

  static getEnumValue (label: string) {
    return GenderTypeEnum.enum_map_label.get(label)
  }
}

GenderTypeEnum.enums.forEach((item) => {
  GenderTypeEnum.enum_map.set(item.value, item.label)
  GenderTypeEnum.enum_map_label.set(item.label, item.value)
})







