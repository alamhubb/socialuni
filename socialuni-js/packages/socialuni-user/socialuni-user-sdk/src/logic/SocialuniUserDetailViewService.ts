import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";
import SocialuniUserAPI from "socialuni-user-api/src/api/SocialuniUserAPI";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";
import TalkVO from "socialuni-api-base/src/model/talk/TalkVO";
import {socialuniUserModule} from "../store/SocialuniUserModule";
import QPopup from "socialuni-ui-uni/src/components/QPopup/QPopup.vue"
import {onLoad} from "uniapp-api/src/UniappPageLifecycleHook";
import {watch, reactive, provide} from "vue";
import {SocialuniViewServiceInterface} from "socialuni/src/interface/SocialuniViewServiceInterface";
import {ComponentInternalInstance} from "@vue/runtime-core";
import SocialuniUserProvideKeys from "../constant/SocialuniUserProvideKeys";


export interface SocialuniUserDetailViewRefs {
    moreActionMenu: QPopup
    reportDialog: any;
    schoolEditDialog: QPopup;
    applyUserFriendDialog: QPopup;
    // contactInfoEditDialog: UserContactInfoEditDialog;
}

class SocialuniUserDetailViewService implements SocialuniViewServiceInterface {

    instance: ComponentInternalInstance = null

    user: CenterUserDetailRO = null

    async initService(instance: ComponentInternalInstance) {
        this.instance = instance
        SocialuniAppUtil.UniUtil.showShareMenu()

        this.queryUserInfo()


        /*onShow(() => {
            this.showMsgInput = true
        })

        onHide(() => {
            this.showMsgInput = false
        })*/
    }

    async queryUserInfo(){
        const params = SocialuniAppUtil.RouterUtil.getCurrentPageParams()
        const userId = params.userId
        console.log('chufale :' + userId)
        // 这里有问题，有时候直接进入页面没有userId
        const res = await SocialuniUserAPI.queryUserDetailAPI(userId)
        this.user = res.data
    }



    openMoreMenu() {
        this.$refs.moreActionMenu.open()
    }

    get appConfig() {
        return socialuniConfigModule.appConfig
    }

    get isIosAndMpQQ() {
        return socialuniSystemModule.isIosAndMpQQ
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
            await socialuniUserModule.getOpenContactInfo(this.user)
        } finally {
            this.showUserContactBtnDisabled = false
        }
    }


}

const socialuniUserDetailViewService = reactive(new SocialuniUserDetailViewService())

export default socialuniUserDetailViewService
