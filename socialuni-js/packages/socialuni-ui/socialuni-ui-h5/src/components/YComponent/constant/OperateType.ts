export default class OperateType {
  static edit = 'edit'
  static add = 'add'
  static detail = 'detail'

  static isDetail(val) {
    return val === this.detail
  }

  static isAdd(val) {
    return val === this.add
  }

  // static upload = 'upload'
}
