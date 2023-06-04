import { Message } from 'element-'

export default class ToastUtil {
  static info(message: string) {
    return Message.info({
      message, duration: 1500,
      showClose: true
    })
  }

  static warning(message: string) {
    return Message.warning({
      message, duration: 1500,
      showClose: true
    })
  }

  static throwError(message: string, duration = 1500) {
    Message.error({
      message, duration,
      showClose: true
    })
    throw new Error(message)
  }

  static error(message: string, duration = 1500) {
    Message.error({
      message,
      duration,
      showClose: true
    })
  }

  static success(message: string) {
    return Message.success({
      message, duration: 1500,
      showClose: true
    })
  }
}
