import User from '@/model/user/User'

export default class UserIdentityAuditRO {
  id: number
  preCheckScore: number
  age: number
  authNum: number
  checked: boolean
  success: boolean
  user: User

  userIdImgSrc: string
  userSelfieImgSrc: string
  birth: string
  name: string
  nation: string
  sex: string
}
