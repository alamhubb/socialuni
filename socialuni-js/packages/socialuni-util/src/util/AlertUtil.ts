import { ElMessageBox } from 'element-plus'

export default class AlertUtil {
  static confirm(message: string, okLabel = '确定', cancel = '取消') {
    return ElMessageBox.confirm(message, {
      type: 'info',
      distinguishCancelAndClose: true,
      confirmButtonText: okLabel,
      cancelButtonText: cancel
    })
  }

  static info(message: string, title?: string) {
    return ElMessageBox.alert(message, title, {
      type: 'info'
    })
  }

  static warning(message: string, okLabel = '确定', cancel = '取消') {
    return ElMessageBox.confirm(message, {
      type: 'warning'
    })
  }

  static error(message: string) {
    ElMessageBox.alert(message, {
      type: 'error'
    })
    throw new Error(message)
  }

  static success(message: string, okLabel = '确定', cancel = '取消') {
    return ElMessageBox.confirm(message, {
      type: 'success'
    })
  }
}
