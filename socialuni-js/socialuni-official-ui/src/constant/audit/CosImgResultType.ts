import EnumNumVO from '../../model/base/EnumNumVO'

export default class CosImgResultType {
  // 数据源节点
  static readonly normal: EnumNumVO = new EnumNumVO(0, '审核正常')
  static readonly sensitive: EnumNumVO = new EnumNumVO(1, '敏感文件')
  static readonly maySensitive: EnumNumVO = new EnumNumVO(2, '疑似敏感')

  static readonly enums: EnumNumVO[] = []

  static readonly enumMap: Map<number, string> = new Map<number, string>()

  static getTypeName(value: number) {
    return this.enumMap.get(value)
  }
}

for (const key in CosImgResultType) {
  if (CosImgResultType[key] instanceof EnumNumVO) {
    CosImgResultType.enumMap.set(CosImgResultType[key].value, CosImgResultType[key].name)
  }
}

