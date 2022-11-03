import EnumStrVO from './EnumStrVO'

export default class GenderType {
  //现在没办法，只能是中文，以后可以连带后台一起改，因为目前就是用中文做的判断。
  static readonly all: string = 'all'
  static readonly allOld: string = '全部'

  // 女生
  static readonly girl: string = 'girl'
  static readonly girlOld: string = '女'
  static readonly userGirlStr: string = '女'
  static readonly girlIcon: string = 'mdi-gender-female'
  static readonly girlCuIcon: string = 'female'
  static readonly girlColor: string = 'color-red6'
  static readonly girlBgColor: string = 'bg-red6'

  static readonly onlyGirl: string = 'onlyGirl'
  // 男生
  static readonly boy: string = 'boy'
  static readonly boyOld: string = '男'
  static readonly userBoyStr: string = '男'
  static readonly boyIcon: string = 'mdi-gender-male'
  static readonly boyCuIcon: string = 'male'
  static readonly boyColor: string = 'color-blue'
  static readonly boyBgColor: string = 'bg-blue'

  static readonly onlyBoy: string = 'onlyBoy'

  static readonly allEnum = new EnumStrVO(GenderType.all, '不限')
  static readonly girlEnum = new EnumStrVO(GenderType.girl, '女生')
  static readonly boyEnum = new EnumStrVO(GenderType.boy, '男生')
  static readonly onlyGirlEnum = new EnumStrVO(GenderType.onlyGirl, '仅女生可见')
  static readonly onlyBoyEnum = new EnumStrVO(GenderType.onlyBoy, '仅男生可见')

  static readonly girlTalkAddEnum = new EnumStrVO(GenderType.girl, '仅女生')
  static readonly boyTalkAddEnum = new EnumStrVO(GenderType.boy, '仅男生')

  //用来做兼容处理
  static readonly talkQueryFilterMap: Map<string, string> = new Map<string, string>([
    [GenderType.all, GenderType.all],
    [GenderType.girl, GenderType.onlyGirl],
    [GenderType.boy, GenderType.onlyBoy]
  ])

  static readonly talkQueryEnums: EnumStrVO[] = [
    GenderType.allEnum,
    GenderType.girlEnum,
    GenderType.boyEnum
    // GenderType.onlyGirlEnum,
    // GenderType.onlyBoyEnum
  ]
  ''
  static readonly talkQueryGirlEnums: EnumStrVO[] = [
    GenderType.allEnum,
    GenderType.girlEnum,
    GenderType.boyEnum,
    GenderType.onlyGirlEnum
  ]

  static readonly talkQueryBoyEnums: EnumStrVO[] = [
    GenderType.allEnum,
    GenderType.girlEnum,
    GenderType.boyEnum,
    GenderType.onlyBoyEnum
  ]

  static readonly talkAddGirlEnums: EnumStrVO[] = [
    GenderType.allEnum,
    GenderType.girlTalkAddEnum
  ]

  static readonly talkAddBoyEnums: EnumStrVO[] = [
    GenderType.allEnum,
    GenderType.boyTalkAddEnum
  ]

  static readonly userEditEnums: EnumStrVO[] = [
    new EnumStrVO(GenderType.girl, GenderType.userGirlStr),
    new EnumStrVO(GenderType.boy, GenderType.userBoyStr)
  ]

  // static readonly values: string[] = GenderType.talkQueryEnums.map(item => item.value)
  //用来做兼容处理
  static readonly mapOld: Map<string, string> = new Map<string, string>([
    [GenderType.all, GenderType.all],
    [GenderType.allOld, GenderType.all],
    [GenderType.girl, GenderType.girl],
    [GenderType.girlOld, GenderType.girl],
    [GenderType.boy, GenderType.boy],
    [GenderType.boyOld, GenderType.boy],
    [GenderType.onlyGirl, GenderType.onlyGirl],
    [GenderType.onlyBoy, GenderType.onlyBoy]
  ])
}
