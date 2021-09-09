export default class UniDevModeType {
  //区分开发和生产模式，使用不同userkey否则tokek，user信息会冲突
  static readonly dev: string = 'development'
  static readonly prod: string = 'production'
}
