import MsgDelete from 'socialuni-base-api/src/model/message/MsgDeleteVO'
import socialuniImRequest from "./socialuniImRequest";
import MessageQueryVO from "../model/QO/message/MessageQueryVO";
import MessageAddVO from "../model/QO/message/MessageAddVO";
import MessageVO from "../model/RO/MessageVO";

export default class MessageAPI {
    static sendMsgAPI<T>(chatId: string, content: string) {
        const msgAdd: MessageAddVO = new MessageAddVO(chatId, content)
        return socialuniImRequest.post <T>('socialuni/message/sendMsg', msgAdd)
    }

    static queryMessagesAPI(chatId: string, queryTime: Date) {
        return socialuniImRequest.post<MessageVO[]>('socialuni/message/queryMessages', new MessageQueryVO(chatId, queryTime))
    }

    static deleteMsgAPI(msgId: number, deleteReason: string, violation: boolean) {
        return socialuniImRequest.post('message/deleteMsg', new MsgDelete(msgId, deleteReason, violation))
    }
}
