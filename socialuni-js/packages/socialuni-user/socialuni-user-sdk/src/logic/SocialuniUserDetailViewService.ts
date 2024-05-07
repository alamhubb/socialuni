import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import SocialuniUserAPI from "socialuni-user-api/src/api/SocialuniUserAPI";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import TalkVO from "socialuni-api-base/src/model/talk/TalkVO";
import {socialuniUserModule} from "../store/SocialuniUserModule";
import {watch, reactive, provide, ComponentInternalInstance, getCurrentInstance} from "vue";
import SocialuniUserExpandService from "./SocialuniUserExpandService";
import SocialuniViewService from "socialuni/src/interface/SocialuniViewService";
import ImgFileVO from "socialuni-api-base/src/model/ImgFileVO";
import SocialuniAppUserAPI from "socialuni-app-api/src/api/SocialuniAppUserAPI";

export default class SocialuniUserDetailViewService extends SocialuniViewService<any> {

    user: CenterUserDetailRO = null

    async initService(instance: ComponentInternalInstance, params: any) {
        this.instance = instance
        console.log('chufale inser')
        QingAppUtil.NativeUtil.showShareMenu()
        console.log(44444)
        console.log('chufale onload')
        console.log(55555)
        console.log(params.userId)
        this.queryUserInfo(params.userId)

        /*onShow(() => {
            this.showMsgInput = true
        })

        onHide(() => {
            this.showMsgInput = false
        })*/
    }

    async queryUserInfo(userId) {
        // 这里有问题，有时候直接进入页面没有userId
        const res = await SocialuniAppUserAPI.queryUserDetailAPI(userId)
        this.user = res.data
        // await Promise.all([
        //     SocialuniUserAPI.getUserImgListAPI(userId).then(res => {
        //         this.user.imgs = res.data
        //     }),
        //     SocialuniUserAPI.getUserImgListAPI(userId).then(res => {
        //         this.user.imgs = res.data
        //     })
        // ])
    }

    get appConfig() {
        return socialuniConfigModule.appConfig
    }

    get isIosAndMpQQ() {
        return socialuniSystemModule.isIosOrMpQQ
    }

    talks: TalkVO[] = []


    copyText(textCopy: string) {
        QingAppUtil.NativeUtil.textCopy(textCopy)
    }


    seeAvatarDetail() {
        uni.previewImage({
            current: 0,
            urls: [this.user.avatar]
        })
    }


    get mineUser() {
        return socialuniUserModule.mineUser
    }


    copyContactInfo() {
        QingAppUtil.NativeUtil.textCopy(this.user.contactInfo)
    }

    showUserContactBtnDisabled: boolean = false

    async getOpenContactInfo() {
        //打开获取对方联系方式功能，支付贝壳
        this.showUserContactBtnDisabled = true
        try {
            await SocialuniUserExpandService.getOpenContactInfo(this.user)
        } finally {
            this.showUserContactBtnDisabled = false
        }
    }
}

export const socialuniUserDetailViewService = reactive(new SocialuniUserDetailViewService())
