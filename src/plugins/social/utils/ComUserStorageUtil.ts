import StorageUtil from '@/plugins/social/utils/StorageUtil'
import UniDevModeType from "@/plugins/uniapp-sdk/constant/UniDevModeType";

export default class ComUserStorageUtil {
  // 开发生产区分user，避免混淆，不区分的话会冲突
  private static readonly userKey: string = 'userKey'
  private static readonly devUserKey: string = 'devUserKey'

  static set (user) {
    if (process.env.NODE_ENV === UniDevModeType.dev) {
      StorageUtil.set(this.devUserKey, user)
    } else {
      StorageUtil.set(this.userKey, user)
    }
  }

  static get () {
    // 开发环境方便测试
    if (process.env.NODE_ENV === UniDevModeType.dev) {
      return StorageUtil.get(this.devUserKey)
    } else {
      return StorageUtil.get(this.userKey)
    }
  }

  static remove () {
    if (process.env.NODE_ENV === UniDevModeType.dev) {
      StorageUtil.remove(this.devUserKey)
    } else {
      StorageUtil.remove(this.userKey)
    }
  }
}
