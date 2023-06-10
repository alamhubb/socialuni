import ImgFileVO from '../ImgFileVO'
import SocialUserFollowDetailRO from "./SocialUserFollowDetailRO";
import CenterUserDetailRO from "./CenterUserDetailRO";

export default class SocialuniUserExtendDetailRO extends CenterUserDetailRO {
    provinceName: string = null
    cityName: string = null
    districtName: string = null
    distance: number = null
}
