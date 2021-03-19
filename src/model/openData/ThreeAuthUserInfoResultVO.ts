import UserVO from '@/model/user/UserVO'

export default class ThreeAuthUserInfoResultVO {
  authType: string
  appUserId: string
  tokenCode: string
  user: UserVO
}
