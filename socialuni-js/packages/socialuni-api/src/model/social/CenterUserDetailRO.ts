import ImgFileVO from '../ImgFileVO'
import SocialUserFollowDetailRO from "./SocialUserFollowDetailRO";

export default class CenterUserDetailRO extends SocialUserFollowDetailRO {
    imgs: ImgFileVO [] = null
    schoolName: string = null
    //联系方式
    contactInfo: string = null
    openContactInfo: boolean = null
    lastOnlineTime: Date = null
    hugNum: number = null
    //前端字段
    hasFriend = false
}
