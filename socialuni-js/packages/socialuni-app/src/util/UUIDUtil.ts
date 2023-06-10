/**
 * @Author qingchi
 * @Date 2021-03-05 21:36
 * @Version 1.0
 */
import { v4 as uuidv4 } from 'uuid'

//各平台通用工具类
export default class UUIDUtil {
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
}
