import ImgFileVO from '../ImgFileVO'
import SocialuniContentUserRO from "./SocialuniContentUserRO";

export default class CenterUserDetailRO extends SocialuniContentUserRO {
    id: string = null
    nickname: string = ''
    avatar: string = ''
    gender: string = null
    schoolName: string = null
    //im模块的token
    imToken: string = null
    socialCoin: number = null
    //联系方式
    contactInfo: string = null
    openContactInfo: boolean = null
    age: number = null
    identityAuth: boolean = false
    city = ''
    birthday: string = null
    phoneNum: string = null
    bindedSocialuni: boolean = null
    socialuniNickname: string = null
    hasFollowed: boolean = null
    hasBeFollowed: boolean = null
    isMine: boolean = null
    fansNum: number = null
    followNum: number = null
    imgs: ImgFileVO [] = null
}
