import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import SocialuniImUserDetailRO from "socialuni-im-api/src/model/RO/SocialuniImUserDetailRO";
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";
import SocialuniUserAPI from "socialuni-user-api/src/api/SocialuniUserAPI";
import {socialChatModule} from "socialuni-im-sdk/src/store/SocialChatModule";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";
import TalkVO from "socialuni-api-base/src/model/talk/TalkVO";
import SocialuniFriendAPI from "socialuni-im-api/src/api/SocialuniFriendAPI";
import FriendAddQO from "socialuni-im-api/src/model/QO/firend/FriendAddQO";
import SocialuniAddFriendType from "socialuni-im-api/src/constant/SocialuniAddFriendType";
import {Watch} from "vue-property-decorator";
import SocialuniTalkAPI from "socialuni-community-api/src/api/SocialuniTalkAPI";
import {socialuniUserModule} from "../store/SocialuniUserModule";
import SocialuniViewService from "socialuni/src/interface/SocialuniViewService";

import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO"
import UserSchoolEditDialog from "./UserSchoolEditDialog.vue"
import TalkVO from "socialuni-api-base/src/model/talk/TalkVO"
import FollowStatus from "socialuni-constant/constant/FollowStatus"
import SocialuniUserInfoImg from "./SocialuniUserInfoImg.vue"
import SocialuniFriendAPI from "socialuni-im-api/src/api/SocialuniFriendAPI"
import FriendAddQO from "socialuni-im-api/src/model/QO/firend/FriendAddQO"
import QPopup from "socialuni-ui-uni/src/components/QPopup/QPopup.vue"
import QIcon from "socialuni-ui-uni/src/components/QIcon/QIcon.vue"
import QInput from "socialuni-ui-uni/src/components/QInput/QInput.vue"
import QButton from "socialuni-ui-uni/src/components/QButton/QButton.vue"
import QSearch from "socialuni-ui-uni/src/components/QSearch/QSearch.vue"
import QNavbar from "socialuni-ui-uni/src/components/QNavbar/QNavbar.vue"
import SocialuniFollowTag from "socialuni-community-view-uni/src/components/SocialuniFollow/SocialuniFollowTag.vue"
import MsgInput from "socialuni-community-view-uni/src/components/MsgInput.vue"
import SocialGenderTag from "../../components/SocialGenderTag/SocialGenderTag.vue"
import QRowItem from "socialuni-ui-uni/src/components/QRowItem/QRowItem.vue"
import {onLoad} from "uniapp-api/src/UniappPageLifecycleHook";

export interface SocialuniUserDetailViewRefs {
    moreActionMenu: QPopup
    reportDialog: any;
    schoolEditDialog: UserSchoolEditDialog;
    applyUserFriendDialog: QPopup;
    // contactInfoEditDialog: UserContactInfoEditDialog;
}

export default class SocialuniUserDetailViewService extends SocialuniViewService<SocialuniUserDetailViewRefs> {
    user: CenterUserDetailRO = null
    showUserContactBtnDisabled: boolean = false
    imUserDetail: SocialuniImUserDetailRO = null

    openMoreMenu() {
        this.$refs.moreActionMenu.open()
    }

    created() {
        console.log(11111111)
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
                if (!this.user.isMine) {
                    //1. 是否被拉黑
                    //1. 是否接收陌生人消息，如果接收，则

                    // socialChatModule.checkFriend(this.user)
                    // socialChatModule.setCurChatByUserId(this.user.id)
                }
            })
        })
    }


    /**
     * 从好友列表中删除用户。
     */
    async deleteFriend() {
        SocialuniAppUtil.AlertUtil.confirm('是否确认解除好友关系').then(async () => {
            (await socialChatModule.openIm()).deleteFriend(this.user.id).then(({data}) => {
                console.log('deleteFriend', data)
                SocialuniAppUtil.ToastUtil.toast('成功解除好友关系')
                socialChatModule.checkFriend(this.user)
            }).catch(err => {
            })
        })
    }

    /**
     * 将用户添加到黑名单。
     */
    async addBlack() {
        SocialuniAppUtil.AlertUtil.confirm('是否确认添加到黑名单').then(async () => {
            (await socialChatModule.openIm()).addBlack(this.user.id).then(({data}) => {
                socialChatModule.checkFriend(this.user)
            }).catch(err => {
            })
        })

    }

    /**
     * 将用户从黑名单移除。
     */
    async removeBlack() {
        SocialuniAppUtil.AlertUtil.confirm('是否确认从黑名单移除').then(async () => {
            (await socialChatModule.openIm()).removeBlack(this.user.id).then(({data}) => {
                socialChatModule.checkFriend(this.user)
            }).catch(err => {
            })
        })

    }

    applyUserFriendContent: string = null


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


    /**
     * 添加好友申请。
     */
    async addFriend() {
        // socialChatFriendModule.addFriend(this.user.id, "请求加好友");
        /*const options: AddFriendParams = {
          toUserID: this.user.id,
          reqMsg: this.applyUserFriendContent
        };
    */
        console.log('123123')
        await SocialuniFriendAPI.addFriend(new FriendAddQO(this.user.id, this.applyUserFriendContent, SocialuniAddFriendType.apply))
        // await (await socialChatModule.openIm()).addFriend(options)
        SocialuniAppUtil.ToastUtil.toastLong('添加好友申请发送成功，请耐心等待对方回复')
    }

    get talkIds() {
        if (this.talks.length) {
            return this.talks.map(item => item.id)
        }
        return []
    }

    copyText(textCopy: string) {
        SocialuniAppUtil.UniUtil.textCopy(textCopy)
    }

    deleteTalk(talkId: string) {
        this.talks.splice(this.talks.findIndex(talk => talk.id === talkId), 1)
    }

    @Watch('user', {
        deep: true,
        immediate: true
    })
    watchUserChange(newUser: CenterUserDetailRO, oldUser: CenterUserDetailRO) {
        // 如果以前是null才查询
        if (!oldUser) {
            this.queryUserTalks()
        }
    }

    queryUserTalks() {
        if (this.user) {
            SocialuniTalkAPI.queryUserTalksAPI(this.user.id, this.talkIds).then((res: any) => {
                this.talks = res.data
            })
        }
    }

    seeAvatarDetail() {
        uni.previewImage({
            current: 0,
            urls: [this.user.avatar]
        })
    }

    copyContactInfo() {
        SocialuniAppUtil.UniUtil.textCopy(this.user.contactInfo)
    }

    get mineUser() {
        return socialuniUserModule.mineUser
    }

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
