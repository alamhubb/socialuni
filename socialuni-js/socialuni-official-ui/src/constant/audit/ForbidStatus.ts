import EnumNumVO from '../../model/base/EnumNumVO'

export default class ForbidStatus {
  // 数据源节点
  static readonly unForbid: EnumNumVO = new EnumNumVO(0, '未冻结')
  static readonly forbid: EnumNumVO = new EnumNumVO(1, '已被冻结')

  static readonly enums: EnumNumVO[] = []

  static readonly enumMap: Map<number, string> = new Map<number, string>()

  static getTypeName(value: number) {
    return this.enumMap.get(value)
  }
}

for (const key in ForbidStatus) {
  if (ForbidStatus[key] instanceof EnumNumVO) {
    ForbidStatus.enumMap.set(ForbidStatus[key].value, ForbidStatus[key].name)
  }
}

