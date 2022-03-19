import SocialLoginRO from '../social/SocialLoginRO'
import CenterUserDetailRO from '../social/CenterUserDetailRO'

export default class ThreeAuthResultVO extends SocialLoginRO<CenterUserDetailRO> {
  authType: string
  appUserId: string
  tokenCode: string
  token: string
  phoneNum: string
  user: CenterUserDetailRO
}
