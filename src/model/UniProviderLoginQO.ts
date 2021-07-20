import { systemModule } from '@/store'

export default class UniProviderLoginQO {
  provider = ''
  platform = ''

  //小程序三方登录使用
  code = ''

  openId = ''
  unionId = ''

  //微信mp获取unionId使用
  encryptedData = ''
  iv = ''

  //微信有活在梦里
  nickName = ''
  //微信有url
  avatarUrl = ''

  //微信有1,2
  gender: number = 0
  //qq会给生日
  birthday = ''


  //微信有China
  country = ''
  //微信有Beijing
  province = ''
  //微信有Chaoyang
  city = ''

  constructor () {
    this.platform = systemModule.platform
  }
}
