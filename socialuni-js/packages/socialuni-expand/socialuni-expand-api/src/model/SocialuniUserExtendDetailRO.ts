import SocialuniUserRO from "socialuni-api-base/src/model/user/SocialuniUserRO";

export default class SocialuniUserExtendDetailRO extends SocialuniUserRO {
    provinceName: string = null
    cityName: string = null
    districtName: string = null
    distance: number = null
    hasUserLike: boolean = null
    updateTime: boolean = null
}
