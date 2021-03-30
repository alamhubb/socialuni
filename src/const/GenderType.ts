export default class GenderType {
  //现在没办法，只能是中文，以后可以连带后台一起改，因为目前就是用中文做的判断。
  static readonly all: string = '全部'

  // 男生
  static readonly boy: string = '男'
  static readonly boyIcon: string = 'mdi-gender-male'
  static readonly boyCuIcon: string = 'male'
  static readonly boyColor: string = 'font-blue'
  static readonly boyBgColor: string = 'bg-blue'
  // 女生
  static readonly girl: string = '女'
  static readonly girlIcon: string = 'mdi-gender-female'
  static readonly girlCuIcon: string = 'female'
  static readonly girlColor: string = 'font-pink'
  static readonly girlBgColor: string = 'bg-pink'
}
