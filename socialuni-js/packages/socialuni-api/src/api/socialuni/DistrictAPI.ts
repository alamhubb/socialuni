import request from "socialuni-api/src/request/request";

export default class DistrictAPI {
  //初始化时获取，选择全部街道时获取
  static queryHotDistrictsAPI() {
    return request.get('socialuni/district/queryHotDistricts')
  }

  static queryDistrictsAPI() {
    return request.get('socialuni/district/queryDistricts')
  }

  static queryUserRecentlyDistrictsAPI() {
    return request.get('socialuni/district/queryUserRecentlyDistricts')
  }
}
