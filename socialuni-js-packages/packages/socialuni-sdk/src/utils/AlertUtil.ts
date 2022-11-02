export default class AlertUtil {
  public static confirm (msg: string, okLabel = '确定', cancel = '取消') {
    return new Promise((resolve, reject) => {
      uni.showModal({
        content: msg,
        confirmText: okLabel,
        cancelText: cancel,
        success (res) {
          if (res.confirm) {
            resolve(true)
          } else if (res.cancel) {
            reject(false)
          }
        }
      })
    })
  }


  //没有title
  public static hint (msg: string, okLabel?: string) {
    return new Promise((resolve, reject) => {
      uni.showModal({
        content: msg,
        showCancel: false,
        confirmText: okLabel || '确定',
        success (res) {
          if (res.confirm) {
            resolve(null)
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
            resolve(null)
          } else if (res.cancel) {
            reject(new Error('点击了取消'))
          }
        }
      })
    })
  }

  public static warning (msg: string, okLabel?: string) {
    return new Promise((resolve, reject) => {
      uni.showModal({
        title: '警告',
        content: msg,
        confirmText: okLabel || '确定',
        success (res) {
          if (res.confirm) {
            resolve(null)
          } else if (res.cancel) {
            reject(new Error('点击了取消'))
          }
        }
      })
    })
  }

  public static success (msg: string, okLabel?: string) {
    return new Promise((resolve, reject) => {
      uni.showModal({
        title: '成功',
        content: msg,
        showCancel: false,
        confirmText: okLabel || '确定',
        success (res) {
          if (res.confirm) {
            resolve(null)
          } else if (res.cancel) {
            reject()
          }
        }
      })
    })
  }

  public static error (msg: string, title?: string) {
    uni.showModal({
      content: msg,
      showCancel: false
    })
    throw new Error(msg)
  }
}
