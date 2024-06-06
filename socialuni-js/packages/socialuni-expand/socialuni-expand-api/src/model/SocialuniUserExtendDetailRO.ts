import SocialuniUserRO from "socialuni-api-base/src/model/user/SocialuniUserRO";
import ImgFileVO from "socialuni-api-base/src/model/ImgFileVO";

export default class SocialuniUserExtendDetailRO extends SocialuniUserRO {
    provinceName: string = null
    cityName: string = null
    districtName: string = null
    distance: number = null
    hasUserLike: boolean = null
    updateTime: boolean = null
    imgs: ImgFileVO[] = null
}
