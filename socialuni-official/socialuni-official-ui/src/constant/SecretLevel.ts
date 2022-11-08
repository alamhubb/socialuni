import EnumNumVO from '../model/base/EnumNumVO'

export default class SecretLevel {
  // 数据源节点
  static readonly JM: EnumNumVO = new EnumNumVO(1, 'JM')
  static readonly MM: EnumNumVO = new EnumNumVO(2, 'MM')
  static readonly INWARD: EnumNumVO = new EnumNumVO(3, '内部')
  static readonly OPEN: EnumNumVO = new EnumNumVO(4, '公开')

  static readonly SECRET_LEVELS: EnumNumVO[] = [SecretLevel.JM, SecretLevel.MM, SecretLevel.INWARD, SecretLevel.OPEN]

  static readonly SECRET_LEVEL_MAP: Map<number, string> = new Map<number, string>()

  static getSecretLevelName(value: number) {
    return SecretLevel.SECRET_LEVEL_MAP.get(value)
  }
}

SecretLevel.SECRET_LEVELS.forEach((item) => {
  SecretLevel.SECRET_LEVEL_MAP.set(item.value, item.name)
})

