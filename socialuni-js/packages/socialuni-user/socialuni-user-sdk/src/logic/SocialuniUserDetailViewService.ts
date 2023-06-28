import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import SocialuniImUserDetailRO from "socialuni-im-api/src/model/RO/SocialuniImUserDetailRO";
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";
import SocialuniUserAPI from "socialuni-user-api/src/api/SocialuniUserAPI";
import {socialChatModule} from "socialuni-im-sdk/src/store/SocialChatModule";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";
import TalkVO from "socialuni-api-base/src/model/talk/TalkVO";
import SocialuniAddFriendType from "socialuni-im-api/src/constant/SocialuniAddFriendType";
import SocialuniTalkAPI from "socialuni-community-api/src/api/SocialuniTalkAPI";
import {socialuniUserModule} from "../store/SocialuniUserModule";
import SocialuniViewService from "socialuni/src/interface/SocialuniViewService";
import SocialuniFriendAPI from "socialuni-im-api/src/api/SocialuniFriendAPI"
import FriendAddQO from "socialuni-im-api/src/model/QO/firend/FriendAddQO"
import QPopup from "socialuni-ui-uni/src/components/QPopup/QPopup.vue"
import {onLoad} from "uniapp-api/src/UniappPageLifecycleHook";
import {watch, reactive} from "vue";
import {SocialuniViewServiceInterface} from "socialuni/src/interface/SocialuniViewServiceInterface";
import {Vue} from "vue-class-component";
import {ComponentInternalInstance} from "@vue/runtime-core";


export interface SocialuniUserDetailViewRefs {
    moreActionMenu: QPopup
    reportDialog: any;
    schoolEditDialog: QPopup;
    applyUserFriendDialog: QPopup;
    // contactInfoEditDialog: UserContactInfoEditDialog;
}

class SocialuniUserDetailViewService implements SocialuniViewServiceInterface {

    instance: ComponentInternalInstance = null

    initService(instance: ComponentInternalInstance) {
        this.instance = instance
        SocialuniAppUtil.UniUtil.showShareMenu()

        /*onShow(() => {
            this.showMsgInput = true
        })

        onHide(() => {
            this.showMsgInput = false
        })*/

        onLoad((params: { userId: string }) => {
            const userId = params.userId
            console.log('chufale :' + userId)
            // 这里有问题，有时候直接进入页面没有userId
            SocialuniUserAPI.queryUserDetailAPI(userId).then((res: any) => {
                this.user = res.data
                console.log(this.user)
                if (!this.user.isMine) {
                    //1. 是否被拉黑
                    //1. 是否接收陌生人消息，如果接收，则

                    // socialChatModule.checkFriend(this.user)
                    // socialChatModule.setCurChatByUserId(this.user.id)
                }
            })
        })
        watch<CenterUserDetailRO>(this.user, (value, oldValue) => {
            console.log(1232)
            if (!oldValue) {
                this.queryUserTalks()
            }
        })
    }


    user: CenterUserDetailRO = null

    imUserDetail: SocialuniImUserDetailRO = null

    openMoreMenu() {
        this.$refs.moreActionMenu.open()
    }

    get appConfig() {
        return socialuniConfigModule.appConfig
    }

    get isIos() {
        return socialuniSystemModule.isIos
    }

    talks: TalkVO[] = []

    async toMessagePage() {
        //无论什么情况都可以进入消息页面，比如要查看两个人的历史聊天消息呢
        socialChatModule.setChatIdToMessagePage(this.user.id)

        //未登录不能发消息
        //然后判断对方是否接收陌生人消息
        //不接收，判断是否是好友
        //不是好友则提示对方不接收陌生人消息，是否添加好友

        //不需要好友功能了，
        //可以付费消息。 每日可免费领取 1金豆？
        //

        //可以付费发送消息，默认关注对方，对方给你回消息免费。
        //如果你取消关注对方，


        //我有两个发消息的校验逻辑
        //用哪一个？

        //发起付费，则关注对方。
        //发送消息关注吗？

        //对方关注了你，你就能给对方发消息。
        //允许陌生人消息，谁都可以给你发消息。 你可以选择给对方拉黑， 也可以选择不接受陌生人消息
        //然后给你发消息，就需要付费。 付费以后默认关注了你，你给对方发消息就免费？

        //如果你给对方发了消息，如果对方为陌生人，则你必须要开启接收陌生人消息。
        //如果对方是收费的，则代表你关注了对方，付费就关注，然后对方可以免费给你发消息，付费发3条？， 然后呢你不需要打开陌生人消息

        //能不能发消息，

        //应该能进入页面的，
        // if (this.imUserDetail.blackUser){
        //   SocialuniAppUtil.ToastUtil.toast("您已被对方拉黑，无法发送消息")
        // }
        //未关注，但是也可以都是可以进入聊天页面的


        // if (this.user.hasBeFollowed) {
        //   this.applyUserFriendContent = null
        //   this.$refs.applyUserFriendDialog.open()
        // } else {
        // }
        // socialChatModule.setCurChatByUserId(this.user.id)
        //除了是否关注，还有是否已经发起过对话，chatuservo里面要保存还能再发几条
        //判断是否已经支付过了。3条，然后对方每次回复你都可以发三条，然后就需要再次支付，开启了支付
        //mock chat
        // chatModule.userDetailToMessagePage(this.userProp.chat)
        //如果有chat读取，如果没有创建读取
        // chatModule.setChatAction(chat)
    }



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
