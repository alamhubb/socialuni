/**
 * @Author qingchi
 * @Date 2021-03-05 21:36
 * @Version 1.0
 */
//各平台通用工具类
export default class CommonUtil {
  //节流,有立即执行和延迟执行
  static throttleDelay (func, wait) {
    let timer
    return (...args) => {
      //如果没有正在执行的
      if (!timer) {
        //则延迟后执行
        timer = setTimeout(() => {
          func(...args)
          timer = null
        }, wait)
      }
    }
  }

  static throttle (func, wait) {
    let timer
    return (...args) => {
      //没有在执行的，立即执行
      if (!timer) {
        func(...args)
        timer = setTimeout(() => {
          timer = null
        }, wait)
      }
    }
  }

  //防抖
  static debounceDelay (func, wait) {
    let timer
    return (...args) => {
      //清空上一个，只执行最后一次
      clearTimeout(timer)
      timer = setTimeout(() => {
        func(...args)
      }, wait)
    }
  }

  //防抖立即执行
  static debounce (func, wait) {
    let timer = null
    return async (...args: any[]) => {
      if (!timer) {
        func(...args)
      }
      if (timer) {
        clearTimeout(timer.time)
      }
      timer = CommonUtil.setTimeout(wait)
      timer.then(() => {
        timer = null
      })
    }
  }

  static setTimeout<T> (delay: number = 1000, callback: (...args: any[]) => T = null) {
    let time
    const p1 = new Promise<T>(resolve => {
      time = setTimeout(async () => {
        if (callback) {
          const res = await callback()
          resolve(res)
        } else {
          resolve(null)
        }
      }, delay)
    })
    let cancel
    const p2 = new Promise<T>((resolve, reject) => (cancel = reject))
    const p: { cancel, time } & Promise<T> = Promise.race([p1, p2]) as any
    p.cancel = cancel
    p.time = time
    return p
  }

  public static delayTime (millisecond: number): Promise<any> {
    return new Promise<any>(resolve =>
      setTimeout(() => {
        resolve(null)
      }, millisecond))
  }
}
