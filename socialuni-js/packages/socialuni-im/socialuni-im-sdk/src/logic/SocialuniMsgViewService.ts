import {ComponentInternalInstance, getCurrentInstance, onMounted, onUnmounted} from "vue";
import SocialuniViewService from "socialuni/src/interface/SocialuniViewService";
import ReportContentType from "socialuni-constant/constant/ReportContentType";
import Constants from "socialuni-constant/constant/Constant";
import MessageViewParams from "../model/MessageViewParams";
import {socialuniMsgModule} from "../store/SocialMessageModule";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import UserCheckUtil from "socialuni-user-sdk/src/util/UserCheckUtil";
import MessageVO from "socialuni-im-api/src/model/RO/MessageVO";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import MessageAPI from "socialuni-im-api/src/api/MessageAPI";
import {MessageStatus} from "socialuni-constant/constant/openIm/OpenImMessageType";
import {socialuniChatModule} from "../store/SocialuniChatModule";
import UserPageUtil from "socialuni-user-sdk/src/util/UserPageUtil";
import ChatType from "socialuni-constant/constant/ChatType";
import LoadMoreType from "socialuni-constant/constant/LoadMoreType";
import DateUtil from "qing-util/src/util/DateUtil";
import MsgUtil from "socialuni-app-sdk/src/util/MsgUtil";
import UserType from "socialuni-constant/constant/UserType";
import CosAuthRO from "socialuni-api-base/src/model/cos/CosAuthRO";
import CosService from "socialuni-app-sdk/src/util/CosService";
import {socialAppModule} from "socialuni-app-sdk/src/store/SocialAppModule";
import SocialuniAppAPI from "socialuni-app-api/src/api/SocialuniAppAPI";
import AppMsg from "socialuni-constant/constant/AppMsg";
import DomFile from "qingjs/src/model/DomFile";
import TencentCosAPI from "socialuni-app-api/src/api/TencentCosAPI";
import SocialuniMineUserAPI from "socialuni-user-api/src/api/SocialuniMineUserAPI";
import ImgAddQO from "socialuni-api-base/src/model/user/ImgAddQO";
import SocialuniUserExpandService from "socialuni-user-sdk/src/logic/SocialuniUserExpandService";
import {socialuniAppUserModule} from "socialuni-user-sdk/src/store/SocialuniAppUserModule";
import SocialuniUserLikeAPI from "socialuni-expand-api/src/api/SocialuniUserLikeAPI";
import SocialuniLikeService from "socialuni-expand-sdk/src/service/SocialuniLikeService";

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

    //为什么使用instance而不使用this？
    initService(instance: Vue, params: MessageViewParams) {
        console.log('zhixingle initService')
        super.initService(instance, params)
        onMounted(() => {
            socialuniChatModule.scrollTop = 0
        })
        onUnmounted(() => {
            socialuniChatModule.scrollTop = 0
        })
        console.log('chufale onload')
        socialuniChatModule.init(params)
    }

    openMoreMenu() {
        if (this.chat.type === ChatType.single) {
            UserPageUtil.toUserDetail(this.chat.id);
        } else {
            // UserPageUtil.toIMGroupMember(this.groupId); // 权限问题，内容有问题。
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

    msgType = null


    async sendMsgClick() {
        if (!socialuniChatModule.chat) {
            QingAppUtil.ToastUtil.throwError('缺少会话')
        }
        UserCheckUtil.checkUserBindPhoneNum()
        const msgContent = this.msgContent
        const msgType = this.msgType
        if (msgContent) {
            if (socialuniChatModule.chat.needPayOpen || socialuniChatModule.chat.sendMsgNeedCoin) {
                await SocialuniLikeService.checkUserCoinAndPay(socialuniChatModule.chat.sendMsgNeedCoin)
            }
            const newMsg = new MessageVO(msgContent, socialuniUserModule.mineUser)
            const newLength = socialuniChatModule.messages.push(newMsg)
            socialuniChatModule.scrollToMessagePageBottom()
            const index: number = newLength - 1
            const content = this.msgContent
            this.msgContent = ''
            this.msgType = null
            try {
                console.log('fasfdas')
                const res = await SocialuniUserLikeAPI.sendMsgAPI(socialuniChatModule.chat.id, msgContent, msgType)
                console.log('fasdf6565656')
                // 点击发送后立即push
                //启用状态可以直接发送
                socialuniChatModule.chat.updateTime = res.data.createTime
                socialuniChatModule.messages.splice(index, 1, res.data)
                socialuniAppUserModule.userCoinNum = socialuniAppUserModule.userCoinNum - 10
            } catch (e) {
                newMsg.readStatus = MessageStatus.Failed
            }
        } else {
            QingAppUtil.ToastUtil.throwError('不能发送空白内容')
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
        const itemList: string[] = ['图片'];
        // const itemList: string[] = ['图片', '视频', '录音'];
        //调用相册api，可选择拍照和引用相册
        QingAppUtil.NativeUtil.actionSheet(itemList).then((index: number) => {
            switch (itemList[index]) {
                case '图片':
                    this.chooseImage();
                    break;
                case '视频':
                    this.chooseVideo();
                    break;
                case '录音':
                    QingAppUtil.AlertUtil.error('暂不支持,开发中');
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

    /**
     * 图片前台压缩，往后台传一个压缩后的可看清的图，然后后台弄出来一个压缩图，
     */
    async chooseImage() {
        //获取cos认证信息
        let cosAuthRO
        CosService.getCosAuthRO().then(res => {
            cosAuthRO = res
        })
        const imgFiles: DomFile[] = await QingAppUtil.NativeUtil.chooseImage(1)
        const imgFile: DomFile = imgFiles[0]
        imgFile.src = cosAuthRO.uploadImgPath + 'im/img/' + imgFile.src
        const res = await Promise.all([TencentCosAPI.uploadFileAPI(imgFile, cosAuthRO)])
        this.msgType = '图片'
        this.msgContent = imgFile.src
        this.sendMsgClick()
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
            QingAppUtil.AlertUtil.confirm('是否确定删除此条消息，此操作无法恢复').then(() => {
                this.deleteMsgAction(msg)
            })
        }
        this.closeDeleteDialog()
    }

    copyText() {
        QingAppUtil.NativeUtil.textCopy(this.message.content)
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
        QingAppUtil.AlertUtil.error('暂不支持,开发中');
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
