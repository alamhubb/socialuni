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

class SocialuniChatModule {
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

    setChats(chats: SocialuniChatRO[]) {
        /*console.log(3333)
        console.log(this.chats.length)
        if (this.chats.length) {
            for (const chat1 of chats) {
                this.pushMsgReplaceChatByChat(chat1)
            }
        } else {
            this.chats = chats
        }*/
        this.chats = chats
        if (this.chats.length && !this.chatId) {
            socialuniChatModule.setChatIdToMessagePage(this.chats[0].id)
            // this.setChatId(this.chats[0].id)
        }
        // this.computedChatsUnreadNumTotalAction()
    }

    async getChatsAction() {
        const res = await ChatAPI.queryChatListAPI()
        this.setChats(res.data)
    }

    setChatIdAndQueryMsg(chatId: string) {
        let chat = socialuniChatModule.chats.find(item => item.id === chatId)
        if (!chat) {
            chat = new SocialuniChatRO()
            // chat.receiveId = params.chatId
            chat.id = chatId
            chat.loadMore = LoadMoreType.more
            this.chats.unshift(chat)

            ChatAPI.queryChatAPI(new ChatQueryQO(chatId)).then(res => {
                this.readChatAction(res.data.messages)
                this.pushMsgReplaceChatByChat(res.data)
            })
        }
        console.log('chatid')
        console.log(chatId)
        this.setChatId(chatId)
        socialuniChatModule.scrollToMessagePageBottom()
    }


    readChatAction(messagesROs: MessageVO[]) {
        //目前不根据点击时间更新，只根据消息时间更新
        // chat.updateTime = new Date().getTime()
        // 不为自己的 且未读的
        const messages: MessageVO[] = messagesROs.filter(item => !item.isMine && !item.isRead)
        const msgIds: string[] = messages.map(msg => msg.id)
        /*if (msgIds.length) {
            // msgIds =
            //如果登录了，才调用后台
            // 如果登录了
            //目前 官方群聊没记录已读状态，读取也不管用
            if (socialuniUserModule.hasUser && this.chat.type === ChatType.single) {
                ChatAPI.readChatAPI(this.chatId)
            }
            for (const message of messages) {
                message.isRead = true
            }
            this.chat.unreadNum = 0
            this.computedChatsUnreadNumTotalAction()
        }*/
    }

    //替换chat消息，如果不存在则添加
    pushMsgReplaceChatByChat(chat: SocialuniChatRO) {
        if (chat) {
            let chatIndex = socialuniChatModule.chats.findIndex(item => item.id === chat.id)

            if (chatIndex > -1) {
                this.pushMsgReplaceChat(chatIndex, chat)
            } else {
                this.chats.unshift(chat)
            }
        }
    }


    //来消息后，替换已有chat
    pushMsgReplaceChat(chatIndex, chat: SocialuniChatRO) {
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

    replaceChat(chatIndex, chat: SocialuniChatRO) {
        this.chats.splice(chatIndex, 1, chat)
    }

    scrollToMessagePageBottom() {
        CommonUtil.delayTime(100).then(() => {
            console.log('滚动到底部')
            this.scrollTop = this.messages.length * 500


            // this.scrollTop = -1000
        })
        // ScrollUtil.scrollTo(this.messages.length * 500)
    }


    /*async pushMessageAction(msgContent) {
        if (!socialuniChatModule.chat) {
            SocialuniAppUtil.ToastUtil.throwError('缺少会话')
        }
        UserCheckUtil.checkUserBindPhoneNum()

        if (msgContent) {
            const newMsg = new MessageVO(msgContent, socialuniUserModule.mineUser)
            socialuniChatModule.messages.push(newMsg)
            socialuniChatModule.scrollToMessagePageBottom()
            const index: number = socialuniChatModule.messages.length - 1
            // 点击发送后立即push
            //启用状态可以直接发送
            this.msgContent = ''
            this.chat.updateTime = new Date().getTime()
            try {
                const res = await MessageAPI.sendMsgAPI(this.chat.id, msgContent)
                this.chat.updateTime = res.data.createTime
                socialuniChatModule.messages.splice(index, 1, res.data)
            } catch (e) {
                newMsg.readStatus = MessageStatus.Failed
            }
        } else {
            SocialuniAppUtil.ToastUtil.throwError('不能发送空白内容')
        }
        // socialuniChatModule.refreshMessages()

        // PlatformUtils.requestSubscribeChat()
    }*/


    pushChatAndMessagesAction(newChat: SocialuniChatRO) {
        // console.log('出发了pushchat')
        // 如果正在这个chat聊天
        console.log(789789798)
        console.log(ImPagePath)
        console.log(ImPagePath.imPagePath)
        if (QingAppUtil.RouterUtil.getCurrentPageURI() === ImPagePath.imPagePath.message && this._chatId === newChat.id) {
            // 则直接往msg增加消息
            // 前台将消息改为已读,修改时间使用后台的就行
            this.readChatAction(newChat.messages)
            //将新消息放到当前msg中并替换
            this.pushMsgReplaceChat(this.chatIndex, newChat)
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
        // this.computedChatsUnreadNumTotalAction()
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

        // ImPageUtil.toMessagePageByChatId(receiveId)
        return receiveId
    }

    async queryMineImUserInfo() {
        const res = await SocialuniImUserAPI.getImMineUser()
        this.imMineUserInfo = res.data
    }

}

export const socialuniChatModule: SocialuniChatModule = reactive(new SocialuniChatModule())
