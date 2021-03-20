import http from '@/plugins/http'

export default class DistrictAPI {
  //初始化时获取，选择全部街道时获取
  static queryHotDistrictsAPI () {
    return http.post('district/queryHotDistricts')
  }

  static queryDistrictsAPI () {
    return http.post('district/queryDistricts')
  }

  static queryUserRecentlyDistrictsAPI () {
    return http.post('district/queryUserRecentlyDistricts')
  }
}
