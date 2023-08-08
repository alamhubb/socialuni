import {ComponentInternalInstance, getCurrentInstance, onMounted, onUnmounted} from "vue";
import SocialuniViewService from "@socialuni/socialuni/src/interface/SocialuniViewService";
import ReportContentType from "@socialuni/socialuni-constant/constant/ReportContentType";
import Constants from "@socialuni/socialuni-constant/constant/Constant";
import MessageViewParams from "../model/MessageViewParams";
import {socialuniMsgModule} from "../store/SocialMessageModule";
import SocialuniAppUtil from "@socialuni/socialuni-native-util/src/util/SocialuniAppUtil";
import UserCheckUtil from "@socialuni/socialuni-user-sdk/src/util/UserCheckUtil";
import MessageVO from "@socialuni/socialuni-im-api/src/model/RO/MessageVO";
import {socialuniUserModule} from "@socialuni/socialuni-user-sdk/src/store/SocialuniUserModule";
import MessageAPI from "@socialuni/socialuni-im-api/src/api/MessageAPI";
import {MessageStatus} from "@socialuni/socialuni-constant/constant/openIm/OpenImMessageType";
import {socialuniChatModule} from "../store/SocialuniChatModule";
import UserPageUtil from "@socialuni/socialuni-user-sdk/src/util/UserPageUtil";
import ChatType from "@socialuni/socialuni-constant/constant/ChatType";
import LoadMoreType from "@socialuni/socialuni-constant/constant/LoadMoreType";
import DateUtil from "@socialuni/socialuni-util/src/util/DateUtil";
import MsgUtil from "@socialuni/socialuni-app-sdk/src/util/MsgUtil";
import UserType from "@socialuni/socialuni-constant/constant/UserType";

export default class SocialuniMsgViewService extends SocialuniViewService<any> {
    public $refs!: {
        // reportDialog: SocialuniReportDialog;
        messageMoreHandleDialog: any;
        deleteReasonDialog: any;
    }
    inputFocus = false
    upperThreshold = 300
    msgContent = ''
    message: MessageVO = null
    deleteReason: string = null
    queryTime: Date = null
    reportContentType: string = ReportContentType.message

    get chatId() {
        return socialuniChatModule.chatId
    }

    get chat() {
        return socialuniChatModule.chat
    }

    get pageTitle() {
        return this.chat?.nickname
    }

    get scrollTop() {
        return socialuniChatModule.scrollTop
    }

    scrollToMessagePageBottom() {
        socialuniChatModule.scrollTop = 0
        socialuniChatModule.scrollToMessagePageBottom()
    }


    get messages() {
        return socialuniChatModule.messages
    }

    get mineUser() {
        return socialuniUserModule.mineUser
    }

    initService(instance: ComponentInternalInstance, params: MessageViewParams) {
        super.initService(instance, params)
        onMounted(() => {
            socialuniChatModule.scrollTop = 0
        })
        onUnmounted(() => {
            socialuniChatModule.scrollTop = 0
        })
        //TODO 同一会话时，这里要改成onRead，不然需要刷新页面才会触发已读的标志。
        console.log('chufale onload')
        socialuniMsgModule.init(params)
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
            // this.queryMessages()
        }
    }


    async sendMsgClick() {
        console.log(123456)
        console.log(socialuniChatModule.chats)
        console.log(socialuniChatModule.chatIndex)
        console.log(socialuniChatModule.chatId)
        if (!socialuniChatModule.chat) {
            SocialuniAppUtil.ToastUtil.throwError('缺少会话')
        }
        console.log(123456)
        UserCheckUtil.checkUserBindPhoneNum()
        console.log(123)
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
        console.log('chufale shiqu jiaodian ')
        console.log(this)
        if (this.inputFocus) {
            this.inputFocus = false
        }
        console.log('chufale shiqu jiaodian ')
        // #ifndef MP-WEIXIN
        // #endif
        /*
        this.showEmoji = false */
    }

    inputFocusEvent() {
        console.log('获取了焦点')
        MsgUtil.cantPopupPromptToast()
        console.log('获取了焦点')
    }


    openPhoto() {
        const that = this;
        const itemList: string[] = ['图片', '视频', '录音'];
        if (this.userId) {
            itemList.push('删除对方聊天记录');
        }

        //调用相册api，可选择拍照和引用相册
        SocialuniAppUtil.UniUtil.actionSheet(itemList).then((index: number) => {
            switch (itemList[index]) {
                case '图片':
                    that.chooseImage();
                    break;
                case '视频':
                    that.chooseVideo();
                    break;
                case '录音':
                    SocialuniAppUtil.AlertUtil.error('暂不支持,开发中');
                    break;
                case '删除对方聊天记录':
                    socialuniChatModule.pushCustomMessage(socialuniUserModule.userId, "{}", "发送删除对方聊天记录");
                    break;
                default :
                    break;
            }
        }).catch(() => {

        })
    }

    closeDeleteDialog() {
        this.$refs.deleteReasonDialog.close()
        this.initData()
    }

    initData() {
        this.curMsg = null
        this.deleteReason = ''
    }

    confirmDeleteTalk(msg: MessageVO) {
        if (this.mineUser.type === UserType.systemUser) {
            this.deleteMsgAction(this.curMsg)
        } else {
            SocialuniAppUtil.AlertUtil.confirm('是否确定删除此条消息，此操作无法恢复').then(() => {
                this.deleteMsgAction(msg)
            })
        }
        this.closeDeleteDialog()
    }

    copyText() {
        SocialuniAppUtil.UniUtil.textCopy(this.message.content)
        this.closeMessageMoreDialog()
        this.initChooseCommentData()
    }

    closeMessageMoreDialog() {
        this.$refs.messageMoreHandleDialog.close()
    }

    initChooseCommentData() {
        this.message = null
    }


    closeShowMsgHint() {
        this.showMsgHint = false
        uni.setStorageSync(Constants.showMsgHintKey, 'false')
    }


    get showLoadMore() {
        /**
         * 这里有坑，如果使用加载更多，则加载更多msg后滚动会出现问题，滚动不到之前的那条，待修复的问题
         */
        return this.chat.loadMore !== LoadMoreType.more
    }


    openReportDialog() {

        this.closeMessageMoreDialog()
        SocialuniAppUtil.AlertUtil.error('暂不支持,开发中');
        console.log('-----举报--openReportDialog-------')
        // this.$refs.reportDialog.openReport()
    }

    banChange({detail}) {
        this.violation = detail.value
    }

    /**
     * 撤回某条消息
     */
    async revokeMessage() {
        (await socialuniChatModule.openIm()).revokeMessage(JSON.stringify(this.message.originalMsg)).then(({data}) => {
            console.log('revokeMessage', data);
        }).catch(err => {
            console.log('revokeMessage---err', err);
        })
        this.closeMessageMoreDialog()
        this.initChooseCommentData()
    }
}

export const socialuniMsgViewService = new SocialuniMsgViewService()
