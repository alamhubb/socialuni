import GenderType from '@/const/GenderType'

export default class SocialConfig {
  static readonly socialSecretKey = process.env.VUE_APP_SOCIALUNI_SECRET_KEY
  //app的性别类型，全部性别，还是仅女生可用
  static readonly appGenderType = GenderType.all
  static readonly authApp = true
}
