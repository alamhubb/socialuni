import ImgFileVO from '../ImgFileVO'
import SocialUserContentRO from "./SocialUserContentRO";

export default class CenterUserDetailRO extends SocialUserContentRO {
    schoolName: string = null
    //im模块的token
    imToken: string = null
    socialCoin: number = null
    //联系方式
    contactInfo: string = null
    openContactInfo: boolean = null
    birthday: string = null
    phoneNum: string = null
    bindedSocialuni: boolean = null
    socialuniNickname: string = null
    fansNum: number = null
    followNum: number = null
    imgs: ImgFileVO [] = null
    //前端字段
    hasFriend = false
}
