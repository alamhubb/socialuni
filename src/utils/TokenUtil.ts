import StorageUtil from "@/utils/StorageUtil";

export default class TokenUtil {
  // 开发生产区分token，避免混淆，不区分的话会冲突
  private static readonly token: string = 'token'
  private static readonly dev_token: string = 'dev_token'

  static set (token: string) {
    if (process.env.NODE_ENV === 'development') {
      StorageUtil.set(this.dev_token, token)
    } else {
      StorageUtil.set(this.token, token)
    }
  }

  static get (): string {
    // 开发环境方便测试
    if (process.env.NODE_ENV === 'development') {
      // return StorageUtil.get(this.dev_token)
      return 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxN18xMjEyMTIxMjIwXzliNzExYWY3NTM5OTRjMTI5MTRlYWE5YzdhNzUyMTUxIn0.qNZQdtX_4kIHqCB4fHgAyE4hT9dIV9Jw8f92HzXIm4k'
    } else {
      return StorageUtil.get(this.token)
    }
  }

  static hasToken (): boolean {
    return !!TokenUtil.get()
  }

  static remove () {
    if (process.env.NODE_ENV === 'development') {
      StorageUtil.remove(this.dev_token)
    } else {
      StorageUtil.remove(this.token)
    }
  }
}
