import request from '../../plugins/http/request'

export default class DistrictAPI {
  //初始化时获取，选择全部街道时获取
  static queryHotDistrictsAPI() {
    return request.post('socialuni/district/queryHotDistricts')
  }

  static queryDistrictsAPI() {
    return request.post('socialuni/district/queryDistricts')
  }

  static queryUserRecentlyDistrictsAPI() {
    return request.post('socialuni/district/queryUserRecentlyDistricts')
  }
}
