/**
 * @Author qingchi
 * @Date 2021-03-05 21:36
 * @Version 1.0
 */
import { v4 as uuidv4 } from 'uuid'

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
  static debounce (func, wait) {
    let timer
    return (...args) => {
      //清空上一个，只执行最后一次
      clearTimeout(timer)
      timer = setTimeout(() => {
        func(...args)
      }, wait)
    }
  }

  public static getUUID (): string {
    const randoms: number[] = []
    for (let i = 0; i < 16; i++) {
      randoms.push(Math.round(Math.random() * 255))
    }
    return uuidv4({
      random: randoms
    }).replace(/-/g, '')
  }

  public static getClassUUID (): string {
    const randoms: number[] = []
    for (let i = 0; i < 16; i++) {
      randoms.push(Math.round(Math.random() * 255))
    }
    return 'uuid' + uuidv4({
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
