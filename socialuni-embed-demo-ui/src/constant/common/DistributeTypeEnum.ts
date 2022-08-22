import EnumStrVO from '@/model/base/EnumStrVO'

export default class DistributeTypeEnum {
    // 数据源节点
    static readonly copy: string = 'N'
    // 转换器节点
    static readonly distribute: string = 'Y'

    private static readonly copyEnum: EnumStrVO = new EnumStrVO(DistributeTypeEnum.copy, '复制')
    private static readonly distributeEnum: EnumStrVO = new EnumStrVO(DistributeTypeEnum.distribute, '分发')

    static readonly enums: EnumStrVO[] = [
      DistributeTypeEnum.copyEnum,
      DistributeTypeEnum.distributeEnum
    ]

    static readonly enum_map: Map<string, string> = new Map<string, string>()

    static getTypeName(value: string) {
      return DistributeTypeEnum.enum_map.get(value)
    }
}
DistributeTypeEnum.enums.forEach((item) => {
  DistributeTypeEnum.enum_map.set(item.value, item.name)
})

