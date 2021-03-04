import UserVO from '@/model/user/UserVO'

export interface AuthUserInfoResultVO {
  tokenCode: string
  user: UserVO
}
