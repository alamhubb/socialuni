//三方平台登录返回的用户信息结构
export default class UniUserInfoRO {
  openId = ''
  unionId = ''

  //微信有活在梦里
  nickName = ''
  //微信有url
  avatarUrl = ''
  //微信有1,2
  gender: number = null
  //app下qq为gender_type
  gender_type: number = null
  //app下qq为birthday为year
  year: string = null
  //微信有China
  country = ''
  //微信有Beijing
  province = ''
  //微信有Chaoyang
  city = ''
  phoneNum: string = null
}
