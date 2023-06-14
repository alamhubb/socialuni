import EnumStrVO from '@/components/YComponent/model/EnumStrVO'

export default class DataTypeStrEnum {
  static readonly Number = 'Number'
  static readonly String = 'String'
  static readonly Date = 'Date'
  static readonly Boolean = 'Boolean'
  static readonly Integer = 'Integer'
  static readonly BigNumber = 'BigNumber'
  static readonly Serializable = 'Serializable'
  static readonly Binary = 'Binary'
  static readonly Timestamp = 'Timestamp'
  static readonly InternetAddress = 'InternetAddress'

  static readonly enums = [
    new EnumStrVO(DataTypeStrEnum.Number),
    new EnumStrVO(DataTypeStrEnum.String),
    new EnumStrVO(DataTypeStrEnum.Date),
    new EnumStrVO(DataTypeStrEnum.Boolean),
    new EnumStrVO(DataTypeStrEnum.Integer),
    new EnumStrVO(DataTypeStrEnum.BigNumber),
    new EnumStrVO(DataTypeStrEnum.Serializable),
    new EnumStrVO(DataTypeStrEnum.Binary),
    new EnumStrVO(DataTypeStrEnum.Timestamp),
    new EnumStrVO(DataTypeStrEnum.InternetAddress)
  ]

  static readonly enumMap: Map<string, string> = new Map<string, string>()

  static getEnumName(value: string) {
    return DataTypeStrEnum.enumMap.get(value)
  }
}

DataTypeStrEnum.enums.forEach((item) => {
  DataTypeStrEnum.enumMap.set(item.value, item.name)
})
