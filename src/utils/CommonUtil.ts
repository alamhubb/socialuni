/**
 * @Author qingchi
 * @Date 2021-03-05 21:36
 * @Version 1.0
 */
import {v4 as uuidv4} from 'uuid'

//各平台通用工具类
export default class CommonUtil {
  //节流
  static throttle (f, wait) {
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

  //防抖
  static debounce (f, wait) {
    let timer
    return (...args) => {
      clearTimeout(timer)
      timer = setTimeout(() => {
        f(...args)
      }, wait)
    }
  }

  public static getUUID(): string {
    const randoms: number[] = []
    for (let i = 0; i < 16; i++) {
      randoms.push(Math.round(Math.random() * 255))
    }
    return uuidv4({
      random: randoms
    }).replace(/-/g, '')
  }

  public static delayTime (millisecond: number): Promise<any> {
    return new Promise<any>(resolve =>
      setTimeout(() => {
        resolve(null)
      }, millisecond))
  }
}
