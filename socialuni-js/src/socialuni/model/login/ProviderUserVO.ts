//三方平台登录返回的用户信息结构
import ProviderType, { Provider } from '../../const/ProviderType'

export default class ProviderUserVO {
  provider: Provider = ProviderType.qq
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
  /*在回调中调用 wx.login 登录，可能会刷新登录态。此时服务器使用 code 换取的 sessionKey
     不是加密时使用的 sessionKey，导致解密失败。建议开发者提前进行 login；
     或者在回调中先使用 checkSession 进行登录态检查，避免 login 刷新登录态。*/
  //先checksession，如果已过期，则调用登录，
  // cloudID: "43_dxlIzWzxNIvupKp3fdPzt_0"
  // encryptedData: "CHQwL7tl8LXGJi27v6feKs69Yd+A0t2E+Sw=="
  // errMsg: "getPhoneNumber:ok"
  // iv: "a1U5blDAk"
  encryptedData = ''
  iv = ''
  //微信绑定手机号使用
  sessionEnable = false

  //暂未使用
  inviteCode = ''

  // 微信结果
  /*
  accessToken: ""
  authCode: ""
  avatarUrl: "https://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83erFFcuGCzsjBx5SqhiaYX1sdV6vErEBb8xZC9DAObtETtMtTECM9RCHtqmSgVlqCFMoCvQOzGvy7pw/132"
  birthday: ""
  city: "Chaoyang"
  clientid: ""
  code: "051Ssb000qOykL1UPx10"
  country: "China"
  encryptedData: "sadfasdfdsadfsadf="
  gender: 1
  inviteCode: ""
  iv: "/GKiU+wsZHqe"
  language: "zh_CN"
  nickName: "不会魔法"
  openId: ""
  phoneNum: ""
  platform: "mp"
  provider: "weixin"
  province: "Beijing"
  sessionEnable: false
  unionId: ""
  */
}
