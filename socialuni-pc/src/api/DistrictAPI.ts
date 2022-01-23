import request from '../plugins/http/request'

export default class DistrictAPI {
  //初始化时获取，选择全部街道时获取
  static queryHotDistrictsAPI () {
    return request.post('district/queryHotDistricts')
  }

  static queryDistrictsAPI () {
    return request.post('district/queryDistricts')
  }

  static queryUserRecentlyDistrictsAPI () {
    return request.post('district/queryUserRecentlyDistricts')
  }
}
