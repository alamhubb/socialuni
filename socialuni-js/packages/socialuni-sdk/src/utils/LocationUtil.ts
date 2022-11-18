import StorageUtil from 'socialuni-sdk/src/utils/StorageUtil'
import MapUtil from './MapUtil'
import AppAuthUtil from 'socialuni-sdk/src/utils/AppAuthUtil'
import DistrictVO from "socialuni-api/src/model/DistrictVO";
import {socialLocationModule} from "socialuni-sdk/src/store/store";
import {socialSystemModule} from "socialuni-sdk/src/store/store";
import {QQMapResult} from "socialuni-api/src/model/location/QQMapResult";

const chinaAdCode = '100000'

const chinaDistrict = new DistrictVO()
chinaDistrict.id = 1
chinaDistrict.adName = '中国'
chinaDistrict.provinceName = '中国'
chinaDistrict.adCode = chinaAdCode

//只在第一次进入系统查询时，设置初始值使用，查询之后就会把默认值替换了

export default class LocationUtil {
    static readonly chinaAdCode = chinaAdCode

    static readonly nationwide = '全国'

    static readonly locationKey = 'location'
    static readonly circleLocationKey = 'circleLocationKey'
    static readonly openLocationKey = 'openLocation'

    static readonly filterLocationKey = 'filterLocation'


    static readonly chinaDistrict: DistrictVO = chinaDistrict

    static openLocation() {
        StorageUtil.setObj(LocationUtil.openLocationKey, '1')
    }

    static getOpenLocation() {
        return !!StorageUtil.get(LocationUtil.openLocationKey)
    }

    static getLocation(): DistrictVO {
        return StorageUtil.getObj(LocationUtil.locationKey) || LocationUtil.chinaDistrict
    }

    static getCircleLocation(): DistrictVO {
        return StorageUtil.getObj(LocationUtil.circleLocationKey) || LocationUtil.chinaDistrict
    }

    static setLocation(district: DistrictVO) {
        StorageUtil.setObj(LocationUtil.locationKey, district)
    }

    static setCircleLocation(district: DistrictVO) {
        StorageUtil.setObj(LocationUtil.circleLocationKey, district)
    }


    static setFilterLocation(district: DistrictVO) {
        StorageUtil.setObj(LocationUtil.filterLocationKey, district)
    }

    static getFilterLocation(): DistrictVO {
        return StorageUtil.getObj(LocationUtil.filterLocationKey) || LocationUtil.chinaDistrict
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
    static async getLocationNotAuth() {
        let hasAuth
        //如果小程序，
        if (socialSystemModule.isMp) {
            //获取用户是否授权过
            try {
                hasAuth = await AppAuthUtil.getUserAuthLocation()
            } catch (error) {
                hasAuth = false
            }
        } else {
            hasAuth = socialLocationModule.openLocation
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
    static getCurLocationCom() {
        return LocationUtil.getCurLocationBySDK().then((res: DistrictVO) => {
            return res
        }).catch(() => {
            return LocationUtil.getCityByIpWebAPI().then((res: DistrictVO) => {
                return res
            })
        })
    }

    // 使用sdk精准定位
    static getCurLocationBySDK(): Promise<DistrictVO> {
        return MapUtil.getLocationBySdk().then((res: QQMapResult) => {
            const district: DistrictVO = new DistrictVO()
            district.adCode = res.adcode
            district.provinceName = res.province
            district.districtName = res.district
            if (res.province !== res.city) {
                if (res.city) {
                    district.cityName = res.city
                }
            }
            district.adName = res.district
            district.lon = res.location.lng
            district.lat = res.location.lat
            district.isLocation = true
            //更新用户经纬度
            socialLocationModule.updateLocationLonAndLat(district.lon, district.lat)
            //如果未开启定位的话开启定位
            if (!socialLocationModule.openLocation) {
                socialLocationModule.openLocationAction()
            }
            return district
        })
    }

    // 使用webapi粗略定位
    static async getCityByIpWebAPI() {
        return MapUtil.getLocationByWeb().then((res: QQMapResult) => {
            if (!res) {
                return null
            }
            const district: DistrictVO = new DistrictVO()
            district.adCode = res.adcode
            district.provinceName = res.province
            if (res.province !== res.city) {
                district.cityName = res.city
            }
            district.adName = res.city
            district.lon = res.location.lng
            district.lat = res.location.lat
            district.isLocation = true
            return district
        })
    }
}
