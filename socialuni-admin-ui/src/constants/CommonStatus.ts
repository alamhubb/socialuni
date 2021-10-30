export default class CommonStatus {
  // 初始
  public static readonly init: string = 'init'
  // 审核中
  public static readonly audit: string = 'audit'
  // 已认证
  public static readonly certified: string = 'certified'
  // 未通过认证
  public static readonly notCertified: string = 'notCertified'
  // 违规
  public static readonly violation: string = 'violation'
  // 启用
  public static readonly enable: string = 'enable'
  // 禁用
  public static readonly disable: string = 'disable'
  // 删除
  public static readonly delete: string = 'delete'
}
