import socialHttp from '@/plugins/social/http'
import SocialServerConfig from '@/plugins/social/api/center/SocialServerConfig'

export default class SocialDistrictAPI {
  //初始化时获取，选择全部街道时获取
  static queryHotDistrictsAPI () {
    return socialHttp.post(SocialServerConfig.SOCIAL_UNI_SERVER_URL + 'district/queryHotDistricts')
  }

  static queryDistrictsAPI () {
    return socialHttp.post(SocialServerConfig.SOCIAL_UNI_SERVER_URL + 'district/queryDistricts')
  }

  static queryUserRecentlyDistrictsAPI () {
    return socialHttp.post(SocialServerConfig.SOCIAL_UNI_SERVER_URL + 'district/queryUserRecentlyDistricts')
  }
}
