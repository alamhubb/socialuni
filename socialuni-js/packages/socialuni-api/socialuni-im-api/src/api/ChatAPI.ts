import SocialuniChatRO from 'socialuni-api-base/src/model/SocialuniChatRO'
import socialuniImRequest from "./socialuniImRequest";
import ChatReadVO from "socialuni-api-base/src/model/chat/ChatReadVO";
import ChatRemoveVO from "socialuni-api-base/src/model/chat/ChatRemoveVO";
import ChatQueryQO from "../model/QO/chat/ChatQueryQO";

export default class ChatAPI {
    /*static getChatAPI (user: UserVO) {
      return http.post<ChatVO>('chat/queryChat', user)
    }*/


    static queryChatListAPI() {
        return socialuniImRequest.get<SocialuniChatRO[]>('socialuni/chat/queryChatList')
    }

    static queryChatAPI(queryQO: ChatQueryQO) {
        return socialuniImRequest.post<SocialuniChatRO>('socialuni/chat/queryChat', queryQO)
    }

    static readChatAPI(chatId: string, messageIds: string[] = []) {
        const chatRead: ChatReadVO = new ChatReadVO(chatId, messageIds)
        return socialuniImRequest.post('socialuni/chat/readChat', chatRead)
    }

    static frontDeleteChatAPI(chatId: number) {
        const chat: ChatRemoveVO = new ChatRemoveVO(chatId)
        return socialuniImRequest.post('chat/frontDeleteChat', chat)
    }

    static closeChatAPI(chatId: number) {
        const chat: ChatRemoveVO = new ChatRemoveVO(chatId)
        return socialuniImRequest.post('chat/closeChat', chat)
    }

    static openChatAPI(chatId: string, needPayOpen = false, content) {
        return socialuniImRequest.post<SocialuniChatRO>('chat/openChat', {id: chatId, needPayOpen, content})
    }
}
