import CenterUserDetailRO from "@socialuni/socialuni-api-base/src/model/social/CenterUserDetailRO";
import SocialuniAppUtil from "@socialuni/socialuni-native-util/src/util/SocialuniAppUtil";
import SocialuniUserAPI from "@socialuni/socialuni-user-api/src/api/SocialuniUserAPI";
import {socialuniConfigModule} from "@socialuni/socialuni-app-sdk/src/store/SocialuniConfigModule";
import {socialuniSystemModule} from "@socialuni/socialuni-util/src/store/SocialuniSystemModule";
import TalkVO from "@socialuni/socialuni-api-base/src/model/talk/TalkVO";
import {socialuniUserModule} from "../store/SocialuniUserModule";
import {watch, reactive, provide} from "vue";
import {SocialuniViewServiceInterface} from "@socialuni/socialuni/src/interface/SocialuniViewServiceInterface";
import {ComponentInternalInstance} from "@vue/runtime-core";
import {onLoad} from "uniapp-api/src/UniappPageLifecycleHook";
import SocialuniUserExpandService from "./SocialuniUserExpandService";
import SocialuniViewService from "@socialuni/socialuni/src/interface/SocialuniViewService";
import {Vue} from "vue-class-component";

export default class SocialuniUserDetailViewService extends SocialuniViewService<any> {

    user: CenterUserDetailRO = null

    async initService(instance: Vue) {
        this.instance = instance
        console.log('chufale inser')
        SocialuniAppUtil.UniUtil.showShareMenu()
        console.log(44444)
        onLoad((params) => {
            console.log('chufale onload')
            console.log(55555)
            console.log(params.userId)
            this.queryUserInfo(params.userId)
        })

        /*onShow(() => {
            this.showMsgInput = true
        })

        onHide(() => {
            this.showMsgInput = false
        })*/
    }

    async queryUserInfo(userId) {
        // 这里有问题，有时候直接进入页面没有userId
        const res = await SocialuniUserAPI.queryUserDetailAPI(userId)
        this.user = res.data
    }

    get appConfig() {
        return socialuniConfigModule.appConfig
    }

    get isIosAndMpQQ() {
        return socialuniSystemModule.isIosOrMpQQ
    }

    talks: TalkVO[] = []


    copyText(textCopy: string) {
        SocialuniAppUtil.UniUtil.textCopy(textCopy)
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
        SocialuniAppUtil.UniUtil.textCopy(this.user.contactInfo)
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
