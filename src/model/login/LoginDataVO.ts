import ProviderType from '@/const/ProviderType'

export default class LoginDataVO {
  provider: string = ProviderType.qq
  platform = ''

  //设备id
  clientid = ''

  openId = ''
  unionId = ''
  //app三方登录使用
  accessToken = ''
  //小程序三方登录使用
  code = ''

  nickName = ''
  avatarUrl = ''
  gender: number = null
  birthday = ''
  city = ''

  phoneNum = ''
  authCode = ''

  //百度小程序使用
  //微信绑定手机号使用
  // cloudID: "43_dxlIzWzxNIvupKp3fdPzt_0"
  // encryptedData: "CHQwL7tl8LXGJi27v6feKs69Yd+A0t2E+Sw=="
  // errMsg: "getPhoneNumber:ok"
  // iv: "a1U5blDAk"
  encryptedData = ''
  iv = ''
  sessionEnable = false

  //暂未使用
  inviteCode = ''
}
