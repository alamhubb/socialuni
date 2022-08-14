import StorageUtil from '@/utils/StorageUtil'

export default class TokenUtil {
  // 开发生产区分token，避免混淆，不区分的话会冲突
  private static readonly token: string = 'token'
  private static readonly dev_token: string = 'dev_token'

  static set(token: string) {
    if (process.env.NODE_ENV === 'development') {
      StorageUtil.setObj(this.dev_token, token)
    } else {
      StorageUtil.setObj(this.token, token)
    }
  }

  static get(): string {
    // 开发环境方便测试
    if (process.env.NODE_ENV === 'development') {
      return StorageUtil.getObj(this.dev_token)
      // return 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxNl81YWVkZTMwZDA3NDc0NTgwOThjZTAzYTVjMWY3MTc4OCJ9.XlQXd_4afra45_b_P6wsIgMxdi779xhfwM03oci8vOI'
    } else {
      return StorageUtil.getObj(this.token)
    }
  }

  static hasToken(): boolean {
    return !!TokenUtil.get()
  }

  static remove() {
    if (process.env.NODE_ENV === 'development') {
      StorageUtil.remove(this.dev_token)
    } else {
      StorageUtil.remove(this.token)
    }
  }
}
