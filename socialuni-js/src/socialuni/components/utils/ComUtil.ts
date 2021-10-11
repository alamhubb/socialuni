/**
 * @Author qingchi
 * @Date 2021-03-05 21:36
 * @Version 1.0
 */
//各平台通用工具类
export default class ComUtil {
  //节流
  static throttle (func, wait = 500) {
    let timer
    return (...args) => {
      if (!timer) {
        func(...args)
        timer = setTimeout(() => {
          timer = null
        }, wait)
      }
    }
  }

  //防抖
  static debounce (func, wait = 500) {
    let timer
    return (...args) => {
      clearTimeout(timer)
      timer = setTimeout(() => {
        func(...args)
      }, wait)
    }
  }
}
