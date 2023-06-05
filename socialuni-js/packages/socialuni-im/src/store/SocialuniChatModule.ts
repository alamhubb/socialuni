import {reactive} from "vue";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";
import ChatAPI from "socialuni-im-api/src/api/ChatAPI";
import SocialuniChatRO from "socialuni-api-base/src/model/SocialuniChatRO";
import LoadMoreType from "socialuni-constant/constant/LoadMoreType";
import ChatQueryQO from "socialuni-im-api/src/model/QO/chat/ChatQueryQO";
import {socialChatModule} from "./SocialChatModule";
import MessageVO from "socialuni-im-api/src/model/RO/MessageVO";
import Arrays from "socialuni-util/src/util/Arrays";

class SocialuniChatModule {
    chatId = ''
    chats: SocialuniChatRO[] = []

    //因为存在排序，所以index并不是更新了update就是第一个，不总是为0，并不总是第一个,
    get chat(): SocialuniChatRO {
        //不再使用index，存在陌生人情况
        return this.chats[this.chatIndex]
    }

    get chatIndex(): number {
        return this.chats.findIndex(item => item.id === this.chatId)
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
        console.log(socialuniPluginsModule.plugins)
        for (const plugin of socialuniPluginsModule.plugins) {
            console.log(plugin.name)
        }
        console.log(socialuniPluginsModule.plugins.length)
        const res = await ChatAPI.queryChatListAPI()
        console.log(res)
        this.setChats(res.data)
    }

    setChatId(chatId: string) {
        let chat = socialChatModule.chats.find(item => item.id === chatId)
        if (!chat) {
            chat = new SocialuniChatRO()
            // chat.receiveId = params.chatId
            chat.id = chatId
            chat.loadMore = LoadMoreType.more
            socialChatModule.chats.unshift(chat)

            ChatAPI.queryChatAPI(new ChatQueryQO(chatId)).then(res => {
                this.readChatAction(res.data.messages)
                this.pushMsgReplaceChatByChat(res.data)
            })
        }
        this.chatId = chatId
        socialChatModule.scrollToMessagePageBottom()
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
            let chatIndex = socialChatModule.chats.findIndex(item => item.id === chat.id)

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
}

export const socialuniChatModule: SocialuniChatModule = reactive(new SocialuniChatModule())
