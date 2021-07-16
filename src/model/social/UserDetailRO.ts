import SocialUserBaseRO from '@/model/social/SocialUserBaseRO'

export default class UserOAuthRO extends SocialUserBaseRO {
  phoneNum: string = null
  birthday: string = null
}
