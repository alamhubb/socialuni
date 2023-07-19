import EnumStrVO from '@/components/YComponent/model/EnumStrVO'

export default class SkipType {
  static readonly web: string = 'web'
  static readonly app: string = 'app'
  static readonly mp: string = 'mp'
  static readonly local: string = 'local'

  private static readonly webEnum: EnumStrVO = new EnumStrVO(SkipType.web, '网页')
  private static readonly appEnum: EnumStrVO = new EnumStrVO(SkipType.app, 'App')
  private static readonly mpEnum: EnumStrVO = new EnumStrVO(SkipType.mp, '小程序')
  private static readonly localEnum: EnumStrVO = new EnumStrVO(SkipType.local, '应用内')

  static readonly enum_map: Map<number, string> = new Map<number, string>()
  static readonly enums: EnumStrVO[] = []

  static getTypeName(value: number) {
    return SkipType.enum_map.get(value)
  }
}

for (const key in SkipType) {
  if (SkipType[key] instanceof EnumStrVO) {
    SkipType.enums.push(SkipType[key])
    SkipType.enum_map.set(SkipType[key].value, SkipType[key].name)
  }
}
