import UserVO from '@/model/user/UserVO'

export default class ThreeAuthResultVO {
  authType: string
  appUserId: string
  tokenCode: string
  phoneNum: string
  user: UserVO
}
