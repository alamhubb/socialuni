import { MessageBox } from 'element-ui'

export default class AlertUtil {
  static confirm(message: string, okLabel = '确定', cancel = '取消') {
    return MessageBox.confirm(message, {
      type: 'info',
      distinguishCancelAndClose: true,
      confirmButtonText: okLabel,
      cancelButtonText: cancel
    })
  }

  static info(message: string, title?: string) {
    return MessageBox.alert(message, title, {
      type: 'info'
    })
  }

  static warning(message: string, okLabel = '确定', cancel = '取消') {
    return MessageBox.confirm(message, {
      type: 'warning'
    })
  }

  static error(message: string) {
    MessageBox.alert(message, {
      type: 'error'
    })
    throw new Error(message)
  }

  static success(message: string, okLabel = '确定', cancel = '取消') {
    return MessageBox.confirm(message, {
      type: 'success'
    })
  }
}
