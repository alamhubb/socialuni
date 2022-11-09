import UniUserInfoRO from './UniUserInfoRO'
import {socialSystemModule} from "socialuni-user/src/store/store";
import LoginProvider from "socialuni-constant/constant/LoginProvider";

export default class UniProviderLoginQO {
    provider = ''
    platform = ''

    //小程序三方登录使用
    code = ''

    openId = ''
    unionId = ''

    //微信mp获取unionId使用
    encryptedData = ''
    iv = ''

    //微信有活在梦里
    nickName = ''
    //微信有url
    avatarUrl = ''

    //微信有1,2
    gender: number = 0
    //qq会给生日
    birthday = ''


    //微信有China
    country = ''
    //微信有Beijing
    province = ''
    //微信有Chaoyang
    city = ''

    // appId = ''
    // secret = ''

    constructor(providerCode: string, userInfoRes: any, userInfoRO: UniUserInfoRO, provider?: string) {
        this.platform = socialSystemModule.platform
        //如果为小程序的话不传值，默认为小程序类型
        this.provider = provider || socialSystemModule.mpPlatform

        this.code = providerCode || ''

        if (userInfoRes) {
            this.iv = userInfoRes.iv || ''
            this.encryptedData = userInfoRes.encryptedData || ''
        }

        this.openId = userInfoRO.openId || ''
        this.unionId = userInfoRO.unionId || ''

        this.nickName = userInfoRO.nickName || ''
        this.avatarUrl = userInfoRO.avatarUrl || ''
        this.gender = userInfoRO.gender || 0
        this.country = userInfoRO.country || ''
        this.province = userInfoRO.province || ''
        this.city = userInfoRO.city || ''
        //如果qq可以获取用户年龄
        this.birthday = userInfoRO.year || ''

        if (socialSystemModule.isApp) {
            if (provider === LoginProvider.qq) {
                this.gender = userInfoRO.gender_type || 0
            }
        }
    }
}
