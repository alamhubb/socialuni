import EnumStrVO from '@/model/base/EnumStrVO'

export default class MergeJoinTypeEnum {
  // 数据源节点
  static readonly inner: string = 'INNER'
  static readonly leftOuter: string = 'LEFT OUTER'
  static readonly rightOuter: string = 'RIGHT OUTER'
  static readonly fullOuter: string = 'FULL OUTER'

  private static readonly innerEnum: EnumStrVO = new EnumStrVO(MergeJoinTypeEnum.inner)
  private static readonly leftOuterEnum: EnumStrVO = new EnumStrVO(MergeJoinTypeEnum.leftOuter)
  private static readonly rightOuterEnum: EnumStrVO = new EnumStrVO(MergeJoinTypeEnum.rightOuter)
  private static readonly fullOuterEnum: EnumStrVO = new EnumStrVO(MergeJoinTypeEnum.fullOuter)

  static readonly enum_map: Map<number, string> = new Map<number, string>()
  static readonly enums: EnumStrVO[] = []

  static getTypeName(value: number) {
    return MergeJoinTypeEnum.enum_map.get(value)
  }
}

for (const key in MergeJoinTypeEnum) {
  if (MergeJoinTypeEnum[key] instanceof EnumStrVO) {
    MergeJoinTypeEnum.enums.push(MergeJoinTypeEnum[key])
    MergeJoinTypeEnum.enum_map.set(MergeJoinTypeEnum[key].value, MergeJoinTypeEnum[key].name)
  }
}
