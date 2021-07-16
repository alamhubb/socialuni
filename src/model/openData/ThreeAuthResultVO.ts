import UserVO from '@/model/user/UserVO'
import SocialLoginRO from '@/model/social/SocialLoginRO'

export default class ThreeAuthResultVO extends SocialLoginRO<UserVO> {
  authType: string
  appUserId: string
  tokenCode: string
  token: string
  phoneNum: string
  user: UserVO
}
