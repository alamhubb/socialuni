import StorageUtil from '@/utils/StorageUtil'
import UserVO from '@/model/user/UserVO'

export default class UserStorageUtil {
  // 开发生产区分user，避免混淆，不区分的话会冲突
  private static readonly user_key: string = 'user_key'
  private static readonly dev_user_key: string = 'dev_user_key'

  static set (user: UserVO) {
    if (process.env.NODE_ENV === 'development') {
      StorageUtil.setObj(this.dev_user_key, user)
    } else {
      StorageUtil.setObj(this.user_key, user)
    }
  }

  static get (): UserVO {
    // 开发环境方便测试
    if (process.env.NODE_ENV === 'development') {
      return StorageUtil.getObj(this.dev_user_key)
      // return 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxNl81YWVkZTMwZDA3NDc0NTgwOThjZTAzYTVjMWY3MTc4OCJ9.XlQXd_4afra45_b_P6wsIgMxdi779xhfwM03oci8vOI'
    } else {
      return StorageUtil.getObj(this.user_key)
    }
  }

  static remove () {
    console.log('出发而')
    if (process.env.NODE_ENV === 'development') {
      console.log('清除了')
      StorageUtil.remove(this.dev_user_key)
    } else {
      console.log('清除了')
      StorageUtil.remove(this.user_key)
    }
  }
}
