import { VuexModule, Module, Action } from 'vuex-class-modules'
import DistrictVO from '/socialuni/model/DistrictVO'
import DistrictAPI from '/socialuni/api/DistrictAPI'
import LocationUtil from '/socialuni/utils/LocationUtil'

@Module({ generateMutationSetters: true })
export default class DistrictModule extends VuexModule {
  // 系统加载时通过getDistrictAction赋值
  //有记录的花记录上一次的，没有记录的话初始全国的
  //记录用户是否授权过定位
  openPosition: boolean = LocationUtil.getOpenLocation()
  district: DistrictVO = LocationUtil.getLocation()
  districts: DistrictVO [] = []

  @Action
  async appLunchInitDistrict () {
    //首次打开无论如何获取用户地理位置
    const district: DistrictVO = await LocationUtil.getLocationNotAuth()
    if (district) {
      this.district.lat = district.lat
      this.district.lat = district.lat
    }
    //初始第一次查询才赋值，设置用户默认的位置信息
    if (this.district.adCode === LocationUtil.initAdCode) {
      this.setDistrict(district)
    } else {
      //只有未开启定位时，才使用后台返回的经纬度
      //为什么需要后台返回的定位信息？
      //只要获取了，就更新用户的地理位置
      if (district && district.lon && district.lat) {
        this.updateLonAndLat(district.lon, district.lat)
      }
    }
  }

  @Action
  openPositionAction () {
    this.openPosition = true
    LocationUtil.openLocation()
  }

  updateLonAndLat (lon, lat) {
    this.district.lon = lon
    this.district.lat = lat
    this.setDistrict(this.district)
  }

  setDistrict (district: DistrictVO) {
    //只要开启过定位就不再关闭
    //如果空值则默认中国，后台定位不到有时候会返回空，11.21此逻辑已修改，无论如何后台不会返回空
    if (!district || !district.adCode) {
      // 远程获取，获取不到返回中国
      // storeAge存储
      district = LocationUtil.chinaDistrict
    }
    LocationUtil.setLocation(district)
    this.district = district
  }

  @Action
  getDistrictsAction () {
    // 查询所有城市
    DistrictAPI.queryDistrictsAPI().then((res: any) => {
      this.districts = res.data
    })
  }

  @Action
  getHotDistrictsAction () {
    // 查询所有城市
    DistrictAPI.queryHotDistrictsAPI().then((res: any) => {
      this.districts = res.data
    })
  }
}
