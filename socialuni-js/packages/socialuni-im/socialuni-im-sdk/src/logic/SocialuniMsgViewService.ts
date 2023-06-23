import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";
import UserCheckUtil from "socialuni-user-sdk/src/util/UserCheckUtil";
import MessageVO from "socialuni-im-api/src/model/RO/MessageVO";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import MessageAPI from "socialuni-im-api/src/api/MessageAPI";
import {MessageStatus} from "socialuni-constant/constant/openIm/OpenImMessageType";
import {socialuniChatModule} from "../store/SocialuniChatModule";
import UserPageUtil from "socialuni-user-sdk/src/util/UserPageUtil";
import ChatType from "socialuni-constant/constant/ChatType";
import LoadMoreType from "socialuni-constant/constant/LoadMoreType";
import DateUtil from "socialuni-util/src/util/DateUtil";
import MsgUtil from "socialuni-app-sdk/src/util/MsgUtil";

export default class SocialuniMsgViewService {
    public $refs!: {
        // reportDialog: SocialuniReportDialog;
        messageMoreHandleDialog: any;
        deleteReasonDialog: any;
    }
    inputFocus = false
    upperThreshold = 300
    msgContent = ''
    message: MessageVO = null

    get chatId() {
        return socialuniChatModule.chatId
    }

    get chat() {
        return socialuniChatModule.chat
    }

    get pageTitle() {
        return this.chat.nickname
    }


    get scrollTop() {
        return socialuniChatModule.scrollTop
    }

    get messages() {
        return socialuniChatModule.messages
    }

    openMoreMenu() {
        if (this.chat.type === ChatType.single) {
            UserPageUtil.toUserDetail(this.chat.id);
        } else {
            UserPageUtil.toIMGroupMember(this.groupId); // 权限问题，内容有问题。
        }
    }

    upper() {
        //只有为more才允许加载
        if (this.chat.loadMore === LoadMoreType.more) {
            // 执行正在加载动画
            this.chat.loadMore = LoadMoreType.loading
            this.queryMessages()
        }
    }



    async sendMsgClick() {
        console.log(123456)
        if (!socialuniChatModule.chat) {
            SocialuniAppUtil.ToastUtil.throwError('缺少会话')
        }
        console.log(123456)
        UserCheckUtil.checkUserBindPhoneNum()
        console.log(123)
        console.log(this)
        const msgContent = this.msgContent
        console.log(this.msgContent)
        console.log(msgContent)
        console.log(123456)
        if (msgContent) {
            console.log(123456)
            console.log(123)
            const newMsg = new MessageVO(msgContent, socialuniUserModule.mineUser)
            socialuniChatModule.messages.push(newMsg)
            console.log(123)
            socialuniChatModule.scrollToMessagePageBottom()
            const index: number = socialuniChatModule.messages.length - 1
            // 点击发送后立即push
            console.log(123)
            //启用状态可以直接发送
            this.msgContent = ''
            socialuniChatModule.chat.updateTime = new Date()
            console.log(123)
            try {
                const res = await MessageAPI.sendMsgAPI(socialuniChatModule.chat.id, msgContent)
                socialuniChatModule.chat.updateTime = res.data.createTime
                socialuniChatModule.messages.splice(index, 1, res.data)
            } catch (e) {
                newMsg.readStatus = MessageStatus.Failed
            }
        } else {
            SocialuniAppUtil.ToastUtil.throwError('不能发送空白内容')
        }
        // socialuniChatModule.refreshMessages()

        // PlatformUtils.requestSubscribeChat()
    }

    formatTime(time) {
        return DateUtil.formatTime(time)
    }

    toUserDetailVue(userId: string) {
        UserPageUtil.toUserDetail(userId)
    }

    openMessageMoreHandleDialog(message: MessageVO) {
        this.message = message
        this.$refs.messageMoreHandleDialog.open()
    }

    inputBlur() {
        if (this.inputFocus) {
            this.inputFocus = false
        }
        // #ifndef MP-WEIXIN
        // #endif
        /*
        this.showEmoji = false */
    }

    inputFocusEvent() {
        MsgUtil.cantPopupPromptToast()
    }

}
