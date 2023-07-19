export default class OperateType {
  static modify = 'modify'
  static add = 'add'
  static detail = 'detail'

  static isDetail(val) {
    return val === this.detail
  }

  // static upload = 'upload'
}
