import StorageUtil from '@/plugins/social/utils/StorageUtil'
import UniDevModeType from "@/plugins/uniapp-sdk/constant/UniDevModeType";

export default class TokenUtil {
  // 开发生产区分token，避免混淆，不区分的话会冲突
  private static readonly token: string = 'token'
  private static readonly dev_token: string = 'dev_token'

  static set (token: string) {
    if (process.env.NODE_ENV === UniDevModeType.dev) {
      StorageUtil.set(this.dev_token, token)
    } else {
      StorageUtil.set(this.token, token)
    }
  }

  static get (): string {
    // 开发环境方便测试
    if (process.env.NODE_ENV === UniDevModeType.dev) {
      return StorageUtil.get(this.dev_token)
    } else {
      return StorageUtil.get(this.token)
    }
  }

  static hasToken (): boolean {
    return !!TokenUtil.get()
  }

  static remove () {
    if (process.env.NODE_ENV === UniDevModeType.dev) {
      StorageUtil.remove(this.dev_token)
    } else {
      StorageUtil.remove(this.token)
    }
  }
}
