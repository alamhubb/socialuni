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
import UserType from "socialuni-constant/constant/UserType";
import {onLoad} from "@dcloudio/uni-app";
import MessageViewParams from "socialuni-im-view-uni/src/views/chat/MessageViewParams";
import {nextTick, onMounted} from "vue";
import SocialuniViewService from "socialuni/src/interface/SocialuniViewService";
import Constants from "socialuni-constant/constant/Constant";
import ReportContentType from "socialuni-constant/constant/ReportContentType";
import SelectorQuery = UniNamespace.SelectorQuery;
import NodesRef = UniNamespace.NodesRef;
import NodeInfo = UniNamespace.NodeInfo;
import {Vue} from "vue-class-component";

export default class SocialuniMsgViewService extends SocialuniViewService {
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
    lazyLoadNum = 30

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

    get mineUser() {
        return socialuniUserModule.mineUser
    }

    initService(vueInstance: Vue) {
        this.thisInstance = vueInstance
        console.log(this.thisInstance)
        console.log(this.thisInstance.$refs)
        console.log('chufale initService')
        //TODO 同一会话时，这里要改成onRead，不然需要刷新页面才会触发已读的标志。
        onLoad((params: MessageViewParams) => {
            console.log('chufale onLoad')
            //不这么写refs是空
            // onMounted(() => {
            console.log('chufale onMounted')
            this.init(params)
            // })
        })
    }

    async init(params: MessageViewParams) {
        console.log('执行了')
        //  不存在前台缓存的问题,防止页面数据没有被及时刷新。 缓存之前页面的数据。
        //  issue: I6EZ82
        //
        if (!params.chatId) {
            SocialuniAppUtil.AlertUtil.error('缺少会话信息')
        }
        // if (params.nickname) {
        //   chat.nickname = params.nickname
        // }
        console.log(params.chatId)
        socialuniChatModule.setChatIdAndQueryMsg(params.chatId)

        this.queryTime = new Date()
        await this.queryMessages(true)

        socialuniChatModule.scrollToMessagePageBottom()


        // socialuniChatModule.chatId = params.receiveId
        // socialuniChatModule.setCurChatByUserId(params.userId)
        // this.userId = params.userId;
        /*else if (params.groupId) {
          // socialuniChatModule.setCurChatByGroupId(params.groupId)
          // this.groupId = params.groupId;
        }*/
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
        console.log(this.thisInstance)
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


    queryMessages(initQuery: boolean = false) {
        // console.trace('chaxun')
        return MessageAPI.queryMessagesAPI(this.chat.id, this.queryTime).then((res) => {
            const resMessages: MessageVO[] = res.data
            //获取拼接消息之前，顶部消息的位置
            // const nodeBox: NodesRef = query.select('.scrollView')
            const query: SelectorQuery = uni.createSelectorQuery().in(this.thisInstance)
            //保持当前位置使用
            let preFirstMsgId: string = null
            if (this.messages.length) {
                preFirstMsgId = '#m' + this.messages[0].id
            }
            const nodeBox: NodesRef = query.select(preFirstMsgId)
            nodeBox.boundingClientRect((preNodeRes: NodeInfo) => {
                let preTop = 0
                if (preNodeRes) {
                    preTop = preNodeRes.top
                }
                // this.topId = lastFirstMsgId
                // 如果还有大于等于30个就还可以加载
                if (resMessages && resMessages.length >= this.lazyLoadNum) {
                    this.chat.loadMore = LoadMoreType.more
                } else {
                    // 否则没有了
                    this.chat.loadMore = LoadMoreType.noMore
                }
                if (resMessages.length) {
                    this.queryTime = resMessages[0].createTime
                    if (initQuery) {
                        socialuniChatModule.chat.messages = resMessages
                        socialuniChatModule.readChatAction(socialuniChatModule.chat.messages)
                    } else {
                        socialuniChatModule.chat.messages.unshift(...resMessages)
                        socialuniChatModule.readChatAction(socialuniChatModule.chat.messages)
                        //获取添加后的之前顶部位置，然后滚动到此位置
                        nextTick(() => {
                            const query: SelectorQuery = uni.createSelectorQuery().in(this.thisInstance)
                            // const nodeBox: NodesRef = query.select('.scrollView')
                            const nodeBox: NodesRef = query.select(preFirstMsgId)
                            nodeBox.boundingClientRect((lastNodeRes: NodeInfo) => {
                                if (lastNodeRes) {
                                    socialuniChatModule.scrollTop = lastNodeRes.top - preTop
                                }
                            }).exec()
                        })
                    }
                }
            }).exec()
            /* setTimeout(() => {
               const query: SelectorQuery = uni.createSelectorQuery().in(this)
               // const nodeBox: NodesRef = query.select('.scrollView')
               const nodeBox: NodesRef = query.select(preFirstMsgId)
               nodeBox.boundingClientRect((lastNodeRes) => {
                 if (res) {
                   console.log(lastNodeRes)
                   console.log(preTop)
                   chatModule.scrollTop = lastNodeRes.top - preTop
                   console.log(chatModule.scrollTop)
                 }
               }).exec()
               // this.topId = lastFirstMsgId
               // 如果还有大于等于30个就还可以加载
               if (res.data && res.data.length >= this.lazyLoadNum) {
                 this.chat.loadMore = LoadMoreType.more
               } else {
                 // 否则没有了
                 this.chat.loadMore = LoadMoreType.noMore
               }
             }, 100)*/
        })
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
