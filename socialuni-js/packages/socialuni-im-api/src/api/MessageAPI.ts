import MessageQueryVO from 'socialuni-api/src/model/message/MessageQueryVO'
import MsgDelete from 'socialuni-api/src/model/message/MsgDeleteVO'
import MessageVO from 'socialuni-sdk/src/model/message/MessageVO'
import MessageAddVO from "socialuni-base/src/model/message/MessageAddVO";
import socialuniImRequest from "./socialuniImRequest";

export default class MessageAPI {
    static sendMsgAPI<T>(chatId: string, content: string) {
        const msgAdd: MessageAddVO = new MessageAddVO(chatId, content)
        return socialuniImRequest.post <T>('socialuni/message/sendMsg', msgAdd)
    }

    static queryMessagesAPI(chatId: string, msgIds: string []) {
        return socialuniImRequest.post<MessageVO[]>('socialuni/message/queryMessages', new MessageQueryVO(chatId, msgIds))
    }

    static deleteMsgAPI(msgId: number, deleteReason: string, violation: boolean) {
        return socialuniImRequest.post('message/deleteMsg', new MsgDelete(msgId, deleteReason, violation))
    }
}
