import SocialUserBaseRO from '@/plugins/social/model/SocialUserBaseRO'

export default class UserOAuthRO extends SocialUserBaseRO {
  phoneNum: string = null
  birthday: string = null
}
