import EnumStrVO from '@/model/base/EnumStrVO'

export default class DataTableColumnType {
  // 数据源节点
  static readonly Integer: EnumStrVO = new EnumStrVO('Integer')
  static readonly String: EnumStrVO = new EnumStrVO('String')

  static readonly ENUMS: EnumStrVO[] = [DataTableColumnType.Integer, DataTableColumnType.String]

  static readonly ENUMS_MAP: Map<string, string> = new Map<string, string>()

  static getNameByValue(value: string) {
    return DataTableColumnType.ENUMS_MAP.get(value)
  }
}

DataTableColumnType.ENUMS.forEach((item) => {
  DataTableColumnType.ENUMS_MAP.set(item.value, item.name)
})
