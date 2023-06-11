import {reactive} from "vue";
import ChatAPI from "socialuni-im-api/src/api/ChatAPI";
import SocialuniChatRO from "socialuni-api-base/src/model/SocialuniChatRO";
import LoadMoreType from "socialuni-constant/constant/LoadMoreType";
import ChatQueryQO from "socialuni-im-api/src/model/QO/chat/ChatQueryQO";
import {socialuniChatModule} from "./socialuniChatModule";
import MessageVO from "socialuni-im-api/src/model/RO/MessageVO";
import MessageAPI from "socialuni-im-api/src/api/MessageAPI";
import CommonUtil from "socialuni-util/src/util/CommonUtil";
import Arrays from "socialuni-util/src/util/Arrays";
import ImPageUtil from "../util/ImPageUtil";
import SocialuniAppUtil from "socialuni-app/src/util/SocialuniAppUtil";

class SocialuniChatModule {
    chatId = ''
    chats: SocialuniChatRO[] = []
    scrollTop: number = 0

    //为什么放到store，因为推送消息时使用，不放store获取不到
    //因为存在排序，所以index并不是更新了update就是第一个，不总是为0，并不总是第一个,
    get chat(): SocialuniChatRO {
        //不再使用index，存在陌生人情况
        return this.chats[this.chatIndex]
    }

    get chatIndex(): number {
        return this.chats.findIndex(item => item.id === this.chatId)
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
        // this.computedChatsUnreadNumTotalAction()
    }

    async getChatsAction() {
        const res = await ChatAPI.queryChatListAPI()
        this.setChats(res.data)
        console.log(this.chats.length)
        if (this.chats.length) {
            console.log(this.chats.length)
            this.setChatId(this.chats[0].id)
        }
    }

    setChatId(chatId: string) {
        console.log(chatId)
        let chat = socialuniChatModule.chats.find(item => item.id === chatId)
        console.log(chat)
        if (!chat) {
            chat = new SocialuniChatRO()
            // chat.receiveId = params.chatId
            chat.id = chatId
            chat.loadMore = LoadMoreType.more
            this.chats.unshift(chat)

            console.log('chaxunchat messag')

            ChatAPI.queryChatAPI(new ChatQueryQO(chatId)).then(res => {
                this.readChatAction(res.data.messages)
                this.pushMsgReplaceChatByChat(res.data)
            })
        }
        this.chatId = chatId
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
            console.log('xiugai top zhi')
            this.scrollTop = this.messages.length * 500
            // this.scrollTop = -1000
        })
        // ScrollUtil.scrollTo(this.messages.length * 500)
    }


    async pushMessageAction(msg: MessageVO) {
        // this.messages.push(msg)
        // JsonUtils.log(this.messages)
        // console.log(JSON.stringify(msg))
        this.scrollToMessagePageBottom()
        const index: number = this.messages.length - 1
        // console.log(index)
        this.chat.updateTime = new Date().getTime()
        // this.chat.lastContent = msg.content
        // 滚屏到最后面
        // 不能监控变化滚动，有时候是往前面插入
        /*const {data} = await (await socialuniChatModule.openIm())[msg.action](msg.contentData);
        const params = {
            recvID: this.chat.receiveUserId,
            groupID: this.chat.groupId,
            message: data,
        };*/
        // console.log('-------params-------', params);

        console.log(msg)
        return MessageAPI.sendMsgAPI(this.chat.id, msg.content)
        //
        /*let actionMethod: Function = null;
        switch (msg.action) {
            case 'createImageMessage':
            case 'createSoundMessage':
            case 'createVideoMessage':
            case 'createFileMessage':
                actionMethod = (await socialuniChatModule.openIm()).sendMessageNotOss;
                break;
            default:
                actionMethod = (await socialuniChatModule.openIm()).sendMessage;
                break;
        }
        // 执行方法。
        actionMethod(params).then((res) => {
            // 后台返回后再替换
            this.chat.updateTime = res.data.createTime
            this.messages.splice(index, 1, new MessageVO(null, JsonUtil.toParse(res.data)))
        }).catch((e) => {
            console.log(e)
            // 这里应该变为发送失败
            this.messages.splice(index, 1)
        }).finally(() => {
            console.log(666)
        })*/
        // socialuniChatModule.refreshMessages()

        // PlatformUtils.requestSubscribeChat()
    }


    pushChatAndMessagesAction(newChat: SocialuniChatRO) {

        // 则直接往msg增加消息
        // 前台将消息改为已读,修改时间使用后台的就行
        this.readChatAction(newChat.messages)
        //将新消息放到当前msg中并替换
        this.pushMsgReplaceChat(this.chatIndex, newChat)
        this.scrollToMessagePageBottom()
        // 后台改为已读
        // 向后台发送消息，将收到的消息改为已读
        // 如果当前就是这个聊天
    }

    setChatIdToMessagePage(receiveId: string) {
        // this.setChatId(chatId)
        // this.readChatAction(this.chat)
        if (!receiveId) {
            SocialuniAppUtil.AlertUtil.error('缺少会话信息')
        }
        /*const chat = new SocialuniChatRO()
        chat.receiveId = receiveId
        chat.nickname = chatName
        this.setChat(chat)*/

        ImPageUtil.toMessagePageByChatId(receiveId)
    }

}

export const socialuniChatModule: SocialuniChatModule = reactive(new SocialuniChatModule())
