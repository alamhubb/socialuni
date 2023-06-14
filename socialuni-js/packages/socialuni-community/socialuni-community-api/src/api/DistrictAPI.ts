import socialuniCommunityRequest from "../request/socialuniCommunityRequest";

export default class DistrictAPI {
  //初始化时获取，选择全部街道时获取
  static queryHotDistrictsAPI() {
    return socialuniCommunityRequest.get('socialuni/district/queryHotDistricts')
  }

  static queryDistrictsAPI() {
    return socialuniCommunityRequest.get('socialuni/district/queryDistricts')
  }

  static queryUserRecentlyDistrictsAPI() {
    return socialuniCommunityRequest.get('socialuni/district/queryUserRecentlyDistricts')
  }
}
