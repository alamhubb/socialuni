import request from '../request/request';
import ChatReadVO from '../model/chat/ChatReadVO'
import ChatRemoveVO from '../model/chat/ChatRemoveVO'
import SocialuniChatRO from 'socialuni-base-api/src/model/SocialuniChatRO'

export default class ChatAPI {
    /*static getChatAPI (user: UserVO) {
      return http.post<ChatVO>('chat/queryChat', user)
    }*/


    static queryChatListAPI() {
        return request.get<SocialuniChatRO[]>('socialuni/chat/queryChatList')
    }

    static readChatAPI(chatId: string, messageIds: string[]) {
        const chatRead: ChatReadVO = new ChatReadVO(chatId, messageIds)
        return request.post('chat/readChat', chatRead)
    }

    static frontDeleteChatAPI(chatId: number) {
        const chat: ChatRemoveVO = new ChatRemoveVO(chatId)
        return request.post('chat/frontDeleteChat', chat)
    }

    static closeChatAPI(chatId: number) {
        const chat: ChatRemoveVO = new ChatRemoveVO(chatId)
        return request.post('chat/closeChat', chat)
    }

    static openChatAPI(chatId: string, needPayOpen = false, content) {
        return request.post<SocialuniChatRO>('chat/openChat', {id: chatId, needPayOpen, content})
    }
}
