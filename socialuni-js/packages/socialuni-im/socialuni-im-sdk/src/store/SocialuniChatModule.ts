import {nextTick, reactive} from "vue";
import ChatAPI from "socialuni-im-api/src/api/ChatAPI";
import SocialuniChatRO from "socialuni-api-base/src/model/SocialuniChatRO";
import LoadMoreType from "socialuni-constant/constant/LoadMoreType";
import ChatQueryQO from "socialuni-im-api/src/model/QO/chat/ChatQueryQO";
import MessageVO from "socialuni-im-api/src/model/RO/MessageVO";
import MessageAPI from "socialuni-im-api/src/api/MessageAPI";
import CommonUtil from "qing-util/src/util/CommonUtil";
import Arrays from "qing-util/src/util/Arrays";
import ImPageUtil from "../util/ImPageUtil";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import UserCheckUtil from "socialuni-user-sdk/src/util/UserCheckUtil";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import {MessageStatus} from "socialuni-constant/constant/openIm/OpenImMessageType";
import SocialuniImMineUserDetailRO from "socialuni-im-api/src/model/RO/SocialuniImMineUserDetailRO";
import SocialuniImUserAPI from "socialuni-im-api/src/api/SocialuniImUserAPI";
import RouterUtil from "qingjs-h5/src/util/RouterUtil";
import ImPagePath from "../constant/ImPagePath";
import SocialuniUserLikeAPI from "socialuni-expand-api/src/api/SocialuniUserLikeAPI";
import ChatType from "socialuni-constant/constant/ChatType";
import MessageViewParams from "../model/MessageViewParams";
import CommonEventUtil from "qingjs/src/util/CommonEventUtil";
import SocialuniImEventKey from "socialuni-im-api/src/constant/SocialuniMusicEventConst";
import SocialuniPageQueryQO from "socialuni-api-base/src/model/common/SocialuniPageQueryQO";
import MessageQueryVO from "socialuni-im-api/src/model/QO/message/MessageQueryVO";

class SocialuniChatModule {
    readonly chatPageIndex = 1


    queryTime: Date = null
    lazyLoadNum = 30

    private _chatId = ''
    get chatId(): string {
        return this._chatId;
    }

    setChatId(value: string) {
        this._chatId = value;
    }

    chats: SocialuniChatRO[] = []
    scrollTop: number = 0

    imMineUserInfo: SocialuniImMineUserDetailRO = null

    //为什么放到store，因为推送消息时使用，不放store获取不到
    //因为存在排序，所以index并不是更新了update就是第一个，不总是为0，并不总是第一个,
    get chat(): SocialuniChatRO {
        //不再使用index，存在陌生人情况
        return this.chats[this.chatIndex]
    }

    get chatIndex(): number {
        return this.chats.findIndex(item => item.id === this._chatId)
    }

    get messages(): MessageVO[] {
        if (this.chat) {
            return this.chat.messages
        }
        return []
    }

    async init(params: MessageViewParams) {
        if (params && params.chatId) {
            const chatId = params.chatId
            await this.setChatIdAndQueryMsg(chatId)
            CommonEventUtil.emit(SocialuniImEventKey.socialuniImPageInit, params)
        }
    }

    async setChatIdAndQueryMsg(chatId: string) {
        let chat = socialuniChatModule.chats.find(item => item.id === chatId)
        if (!chat) {
            chat = new SocialuniChatRO()
            chat.id = chatId
            chat.loadMore = LoadMoreType.more
            this.chats.unshift(chat)

            const res = await SocialuniUserLikeAPI.queryChatAPI(new ChatQueryQO(chatId))
            this.pushMsgReplaceChatByChat(res.data)
        }
        this.setChatId(chatId)
        this.readChat(this.messages)
        socialuniChatModule.scrollToMessagePageBottom()
    }


    async getChatsAction() {
        const res = await SocialuniUserLikeAPI.queryChatListAPI()
        this.setChats(res.data)
    }


    setChats(chats: SocialuniChatRO[]) {
        const newChats = []
        for (const chat1 of chats) {
            const newChat = new SocialuniChatRO(chat1)
            newChats.push(newChat)
            console.log(newChat.messages)
            this.queryChatMessages(newChat)
        }
        // this.chats = chats
        this.chats = newChats
        this.computedChatsUnreadNumTotalAction()
    }

    async queryChatMessages(chat: SocialuniChatRO) {
        const pageQueryQO = new SocialuniPageQueryQO<MessageQueryVO>()
        const queryQO = new MessageQueryVO(chat.id, new Date())
        pageQueryQO.queryData = queryQO
        pageQueryQO.pageSize = chat.pageSize
        pageQueryQO.pageNum = chat.pageNum
        pageQueryQO.queryTime = queryQO.queryTime

        MessageAPI.queryNewMessagesAPI(pageQueryQO).then(res => {
            console.log(chat)
            console.log(res.data)
            chat.messages = res.data
            this.computedChatsUnreadNumTotalAction()
        })
    }


    readChat(messageROs: MessageVO[]) {
        const messages: MessageVO[] = messageROs.filter(item => !item.isMine && !item.isRead)
        for (const message of messages) {
            message.isRead = true
        }
        this.chat.unreadNum = 0
        this.computedChatsUnreadNumTotalAction()
        if (socialuniUserModule.hasUser && this.chat.type === ChatType.single) {
            ChatAPI.readChatAPI(this.chatId)
        }
    }

    chatsUnreadNumTotal = 0

    computedChatsUnreadNumTotalAction() {
        this.chatsUnreadNumTotal = this.chats.reduce((total, chat) => {
            total = total + chat.unreadNum
            return total
        }, 0)
    }

    showTabBarRedDot() {
        uni.showTabBarRedDot({
            index: this.chatPageIndex
        })
    }

    hideTabBarRedDot() {
        uni.hideTabBarRedDot({
            index: this.chatPageIndex
        })
    }


    //替换chat消息，如果不存在则添加
    pushMsgReplaceChatByChat(chat: SocialuniChatRO) {
        if (chat) {
            let chatIndex = socialuniChatModule.chats.findIndex(item => item.id === chat.id)

            console.log(chatIndex)
            if (chatIndex > -1) {
                this.pushMsgReplaceChat(chatIndex, chat)
            } else {
                this.chats.unshift(chat)
            }
        }
    }


    //来消息后，替换已有chat
    pushMsgReplaceChat(chatIndex: number, chat: SocialuniChatRO) {
        const oldChat = this.chats[chatIndex]
        console.log(this.chats)
        console.log(chatIndex)
        console.log(oldChat)
        let messages: MessageVO[] = oldChat.messages
        //将新消息放到当前msg中
        messages.push(...chat.messages)

        messages = Arrays.unique(messages, 'id')

        chat.messages = messages
        chat.loadMore = oldChat.loadMore
        //不需要替换，修改了
        this.replaceChat(chatIndex, chat)
    }

    replaceChat(chatIndex: number, chat: SocialuniChatRO) {
        this.chats.splice(chatIndex, 1, chat)
    }

    scrollToMessagePageBottom() {
        CommonUtil.delayTime(100).then(() => {
            console.log('滚动到底部')
            this.scrollTop = this.messages.length * 500
        })
    }


    pushChatAndMessagesAction(newChat: SocialuniChatRO) {
        // console.log('出发了pushchat')
        // 如果正在这个chat聊天
        console.log(789789798)
        console.log(ImPagePath)
        console.log(ImPagePath.imPagePath)

        console.log(ImPagePath.imPagePath.message)
        console.log(QingAppUtil.RouterUtil.getCurrentPageURI())
        if (QingAppUtil.RouterUtil.getCurrentPageURI() === ImPagePath.imPagePath.message && this._chatId === newChat.id) {
            // 则直接往msg增加消息
            //将新消息放到当前msg中并替换
            this.pushMsgReplaceChat(this.chatIndex, newChat)
            // 前台将消息改为已读,修改时间使用后台的就行
            this.readChat(newChat.messages)
            this.scrollToMessagePageBottom()
            // 后台改为已读
            // 向后台发送消息，将收到的消息改为已读
            // 如果当前就是这个聊天
        } else {
            //将新消息放到当前msg中并替换
            this.pushMsgReplaceChatByChat(newChat)
            //不需要吧，后台chat应该计算好当前未读数量
            /*!// 如果已登录
            if (UserStore.hasUser() && chat.type !== ChatType.system_group) {
              chat.unreadNum = newChat.unreadNum
            } else {
              chat.unreadNum = chat.unreadNum + 1
            }*/

            // 不是正在这个chat聊天，但是chats列表中包含这个chat
            // 如果列表中已经包含次chat
            // 则找到chat，赋值,加入新message
            // 在列表中将这个chat放到最前面
            // 找到需要添加内容的chat
        }
        //计算未读数量
        this.computedChatsUnreadNumTotalAction()
    }

    setChatIdToMessagePage(receiveId: string) {
        console.log(receiveId)
        // this.setChatId(chatId)
        // this.readChatAction(this.chat)
        if (!receiveId) {
            QingAppUtil.AlertUtil.error('缺少会话信息')
        }
        /*const chat = new SocialuniChatRO()
        chat.receiveId = receiveId
        chat.nickname = chatName
        this.setChat(chat)*/

        ImPageUtil.toMessagePageByChatId(receiveId)
        return receiveId
    }

    async queryMineImUserInfo() {
        const res = await SocialuniImUserAPI.getImMineUser()
        this.imMineUserInfo = res.data
    }

}

export const socialuniChatModule: SocialuniChatModule = reactive(new SocialuniChatModule())
