export default class ToastUtil {
  static info(message: string) {
    return ElMessage.info({
      message, duration: 1500,
      showClose: true
    })
  }

  static warning(message: string) {
    return ElMessage.warning({
      message, duration: 1500,
      showClose: true
    })
  }

  static throwError(message: string, duration = 1500) {
    ElMessage.error({
      message, duration,
      showClose: true
    })
    throw new Error(message)
  }

  static error(message: string, duration = 1500) {
    ElMessage.error({
      message,
      duration,
      showClose: true
    })
  }

  static success(message: string) {
    return ElMessage.success({
      message, duration: 1500,
      showClose: true
    })
  }
}
