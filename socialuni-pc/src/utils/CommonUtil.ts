export default class CommonUtil {
  // 节流
  static throttle(f, wait) {
    let timer
    return (...args) => {
      if (timer) {
        return
      }
      timer = setTimeout(() => {
        f(...args)
        timer = null
      }, wait)
    }
  }

  // 防抖
  static debounce(f, wait) {
    let timer
    return (...args) => {
      clearTimeout(timer)
      timer = setTimeout(() => {
        f(...args)
      }, wait)
    }
  }

  static delayTime(millisecond: number): Promise<any> {
    return new Promise<any>(resolve =>
      setTimeout(() => {
        resolve(null)
      }, millisecond))
  }
}
