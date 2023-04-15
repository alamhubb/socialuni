import DistrictVO from "socialuni-base-api/src/model/DistrictVO";
import {reactive} from "vue";
import {socialTalkModule} from "./SocialTalkModule";
import DistrictAPI from "socialuni-community-api/src/api/DistrictAPI";
import LocationUtil from "../util/LocationUtil";

class SocialLocationModule {
    // 系统加载时通过getDistrictAction赋值
    //有记录的花记录上一次的，没有记录的话初始全国的
    //记录用户是否授权过定位
    openLocation: boolean = LocationUtil.getOpenLocation()
    cityLocation: DistrictVO = LocationUtil.getLocation()
    circleLocation: DistrictVO = LocationUtil.getCircleLocation()
    districts: DistrictVO [] = []

    get location() {
        // if (socialTalkModule.curTabIsCircle) {
        //     return this.circleLocation
        // }
        return this.cityLocation
    }


    async appLunchInitDistrict() {
        //首次打开无论如何获取用户地理位置
        const district: DistrictVO = await LocationUtil.getLocationNotAuth()
        if (district) {
            //只有未开启定位时，才使用后台返回的经纬度
            //为什么需要后台返回的定位信息？
            //只要获取了，就更新用户的地理位置
            if (district && district.lon && district.lat) {
                this.updateLocationLonAndLat(district.lon, district.lat)
            }
        }
    }


    openLocationAction() {
        this.openLocation = true
        LocationUtil.openLocation()
    }

    updateLocationLonAndLat(lon, lat) {
        this.cityLocation.lon = lon
        this.cityLocation.lat = lat
        this.setCityLocation(this.cityLocation)
    }

    setCityLocation(district: DistrictVO) {
        //只要开启过定位就不再关闭
        //如果空值则默认中国，后台定位不到有时候会返回空，11.21此逻辑已修改，无论如何后台不会返回空
        if (!district || !district.adCode) {
            // 远程获取，获取不到返回中国
            // storeAge存储
            district = LocationUtil.chinaDistrict
        }
        LocationUtil.setLocation(district)
        this.cityLocation = district
    }

    setLocation(district: DistrictVO) {
        //只要开启过定位就不再关闭
        //如果空值则默认中国，后台定位不到有时候会返回空，11.21此逻辑已修改，无论如何后台不会返回空
        if (!district || !district.adCode) {
            // 远程获取，获取不到返回中国
            // storeAge存储
            district = LocationUtil.chinaDistrict
        }
        if (socialTalkModule.curTabIsCircle) {
            LocationUtil.setCircleLocation(district)
            this.circleLocation = district
        } else {
            LocationUtil.setLocation(district)
            this.cityLocation = district
        }
    }


    getDistrictsAction() {
        // 查询所有城市
        DistrictAPI.queryDistrictsAPI().then((res: any) => {
            this.districts = res.data
            console.log(res.data)
            console.log(this.districts)
        })
    }


    getHotDistrictsAction() {
        // 查询所有城市
        DistrictAPI.queryHotDistrictsAPI().then((res: any) => {
            this.districts = res.data
        })
    }
}

export const socialLocationModule: SocialLocationModule = reactive(new SocialLocationModule())
