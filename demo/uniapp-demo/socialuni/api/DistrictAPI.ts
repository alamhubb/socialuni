import socialHttp from '/socialuni/plugins/http/socialHttp'

export default class DistrictAPI {
  //初始化时获取，选择全部街道时获取
  static queryHotDistrictsAPI () {
    return socialHttp.post('district/queryHotDistricts')
  }

  static queryDistrictsAPI () {
    return socialHttp.post('district/queryDistricts')
  }

  static queryUserRecentlyDistrictsAPI () {
    return socialHttp.post('district/queryUserRecentlyDistricts')
  }
}
