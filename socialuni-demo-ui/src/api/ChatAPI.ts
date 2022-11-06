import request from "socialuni-api/src/request/request";
import ChatReadVO from "socialuni-api/src/model/chat/ChatReadVO";
import ChatRemoveVO from "socialuni-api/src/model/chat/ChatRemoveVO";
import SocialuniChatRO from "socialuni-api/src/model/chat/SocialuniChatRO";

export default class ChatAPI {
    /*static getChatAPI (user: UserVO) {
      return http.post<ChatVO>('chat/queryChat', user)
    }*/


    static getChatsAPI() {
        return request.post('chat/queryChats')
    }

    static readChatAPI(chatId: number, messageIds: number[]) {
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

    static openChatAPI(chatId: number, needPayOpen = false, content) {
        return request.post<SocialuniChatRO>('chat/openChat', {id: chatId, needPayOpen, content})
  }
}
