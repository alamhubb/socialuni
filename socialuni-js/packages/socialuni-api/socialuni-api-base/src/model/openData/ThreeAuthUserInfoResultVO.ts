import CenterUserDetailRO from '../social/CenterUserDetailRO'

export default class ThreeAuthUserInfoResultVO {
  authType: string
  appUserId: string
  tokenCode: string
  user: CenterUserDetailRO
}
