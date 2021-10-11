import { Message } from 'element-ui'

export default class ToastUtil {
  public static info(message: string) {
    return Message.info({
      message, duration: 1500
    })
  }

  public static warning(message: string) {
    return Message.warning({
      message, duration: 1500
    })
  }

  public static error(message: string) {
    return Message.error({
      message, duration: 1500
    })
  }

  public static success(message: string) {
    return Message.success({
      message, duration: 1500
    })
  }

  /*

    public static action (msg: string, okLabel = '确定', cancel = '取消') {
        return new Promise((resolve, reject) => {
            uni.showModal({
                content: msg,
                confirmText: okLabel,
                cancelText: cancel,
                success (res) {
                    if (res.confirm) {
                        resolve()
                    } else if (res.cancel) {
                        reject(new Error('点击了取消'))
                    }
                }
            })
        })
    }

    public static info (msg: string, okLabel?: string) {
        return new Promise((resolve, reject) => {
            uni.showModal({
                title: '提示',
                content: msg,
                confirmText: okLabel || '确定',
                success (res) {
                    if (res.confirm) {
                        resolve()
                    } else if (res.cancel) {
                        reject(new Error('点击了取消'))
                    }
                }
            })
        })
    }

    public static hint (msg: string, okLabel?: string) {
        return new Promise((resolve, reject) => {
            uni.showModal({
                content: msg,
                showCancel: false,
                confirmText: okLabel || '确定',
                success (res) {
                    if (res.confirm) {
                        resolve()
                    } else if (res.cancel) {
                        reject(new Error('点击了取消'))
                    }
                }
            })
        })
    }

    public static error (msg: string, title?: string) {
        return new Promise((resolve, reject) => {
            uni.showModal({
                title: title || '错误',
                content: msg,
                showCancel: false,
                success (res) {
                    if (res.confirm) {
                        resolve()
                    } else if (res.cancel) {
                        reject()
                    }
                }
            })
        })
    }
*/
}
