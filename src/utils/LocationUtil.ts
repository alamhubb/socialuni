import DistrictVO from '@/model/DistrictVO'
import StorageUtil from '@/utils/StorageUtil'
import MapUtil from '@/utils/MapUtil'
import { locationModule, systemModule } from '@/plugins/store'
import AppAuthUtil from '@/utils/AppAuthUtil'

const chinaAdCode = '100000'
const initAdCode = '100001'

const chinaDistrict = new DistrictVO()
chinaDistrict.id = 1
chinaDistrict.adName = '中国'
chinaDistrict.provinceName = '中国'
chinaDistrict.adCode = chinaAdCode

const initDistrict = new DistrictVO()
//只在第一次进入系统查询时，设置初始值使用，查询之后就会把默认值替换了
initDistrict.adCode = initAdCode

export default class LocationUtil {
  static readonly chinaAdCode = chinaAdCode

  static readonly initAdCode = initAdCode

  static readonly nationwide = '全国'

  static readonly locationKey = 'location'
  static readonly openLocationKey = 'openLocation'

  static readonly initDistrict: DistrictVO = initDistrict
  static readonly chinaDistrict: DistrictVO = chinaDistrict

  static openLocation () {
    StorageUtil.setObj(LocationUtil.openLocationKey, '1')
  }

  static getOpenLocation () {
    return !!StorageUtil.get(LocationUtil.openLocationKey)
  }

  static getLocation (): DistrictVO {
    return StorageUtil.getObj(LocationUtil.locationKey) || LocationUtil.initDistrict
  }

  static setLocation (district: DistrictVO) {
    StorageUtil.setObj(LocationUtil.locationKey, district)
  }


  //查询地理位置的功能，
  //首先获取，用户是否授权了，获取地理位置的权限，如果用户授权了，则使用sdk，获取用户地理位置，更新经纬度，
  //为什么，因为查询动态的时候要显示位置距离

  //如果授权了的话，每次都需要获取最新的地理位置吗？，不需要的


  //每次启动时获取一次，更新位置，其他也有可能点击定位什么的时候再更新这个值。启动的时候只更新经纬度
  //点击获取地理位置时，确定时更新 定位和经纬度
  //talk的时候，不更新位置，只获取当前位置，显示当前发表的当前位置，可以选择位置为中国，默认选中当前位置，默认

  //你需要显示什么位置，默认，如果用户开启了授权位置功能，则显示用户当前地理位置，如果用户未开放地理位置功能，则显示webapi定位位置。
  //用户可以自己切换为，中国，仅在当前页面有效，不影响用户的定位 adcode，和经纬度？，用户点击了定位，也不影响外部定位。

  //页面加载，只获取经纬度

  //同城定位切换dis确定后

  //新增动态界面，不修改，仅影响当前页面


  //可以封装的组件，登录组件，广告组件


  //就用位置信息举例，首次登录
  //区分平台，
  //如果mp
  //获取用户授权信息

  //没授权就用web获取地理位置，授权了就用sdk，基础util 就返回位置信息，具体存哪里store决定

  //无需授权，根据组合方式获取地理位置，无论如何都会返回地理位置
  static async getLocationNotAuth () {
    let hasAuth
    //如果小程序，
    if (systemModule.isMp) {
      //获取用户是否授权过
      hasAuth = await AppAuthUtil.getUserAuthLocation()
    } else {
      hasAuth = locationModule.openLocation
    }
    //如果非小程序，获取用户是否授权过，授权过使用组合方式
    //如果用户已经授权过地理位置了
    if (hasAuth) {
      return LocationUtil.getCurLocationCom()
    }
    //没授权过使用web
    return LocationUtil.getCityByIpWebAPI()
  }

  // 组合sdk和webapi
  static getCurLocationCom () {
    return LocationUtil.getCurLocationBySDK().then((res: DistrictVO) => {
      return res
    }).catch(() => {
      return LocationUtil.getCityByIpWebAPI().then((res: DistrictVO) => {
        return res
      })
    })
  }

  // 使用sdk精准定位
  static getCurLocationBySDK (): Promise<DistrictVO> {
    return MapUtil.getLocationBySdk().then((res: any) => {
      const lonAndLat: number[] = res.streetNumber.location.split(',')
      const district: DistrictVO = new DistrictVO()
      district.adCode = res.adcode
      district.provinceName = res.province
      district.districtName = res.district
      if (res.province !== res.city) {
        if (res.city) {
          if (Array.isArray(res.city)) {
            if (res.city.length) {
              district.cityName = res.city[0]
            }
          } else {
            district.cityName = res.city
          }
        }
      }
      district.adName = res.district
      district.lon = lonAndLat[0]
      district.lat = lonAndLat[1]
      district.isLocation = true
      //更新用户经纬度
      locationModule.updateLocationLonAndLat(district.lon, district.lat)
      //如果未开启定位的话开启定位
      if (!locationModule.openLocation) {
        locationModule.openLocationAction()
      }
      return district
    })
  }

  // 使用webapi粗略定位
  static async getCityByIpWebAPI () {
    return MapUtil.getLocationByWeb().then((res: any) => {
      if (res.info === 'OK') {
        const rectangle: string = res.rectangle
        const rectangleAry: string[] = rectangle.split(';')
        const lonAndLatAry: string[] = rectangleAry[0].split(',')
        const district: DistrictVO = new DistrictVO()
        district.adCode = res.adcode
        district.provinceName = res.province
        if (res.province !== res.city) {
          district.cityName = res.city
        }
        district.adName = res.city
        district.lon = Number(lonAndLatAry[0])
        district.lat = Number(lonAndLatAry[1])
        district.isLocation = true
        return district
      } else {
        return null
      }
    })
  }

  /* static getCityByLatLonAPI (rectangle: string) {
    WebAPI.get(' https://restapi.amap.com/v3/geocode/regeo?key=a64bc4ccb330776939d57e229ca1e63b&location=' + rectangle).then(() => {
    })
  } */
}
