import socialuniImRequest from "./socialuniImRequest";
import MessageQueryVO from "../model/QO/message/MessageQueryVO";
import MessageAddVO from "../model/QO/message/MessageAddVO";
import MessageVO from "../model/RO/MessageVO";
import MsgDeleteVO from "../model/QO/message/MsgDeleteVO";

export default class MessageAPI {
    static sendMsgAPI(chatId: string, content: string) {
        const msgAdd: MessageAddVO = new MessageAddVO(chatId, content)
        return socialuniImRequest.post <MessageVO>('socialuni/message/sendMsg', msgAdd)
    }

    static queryMessagesAPI(chatId: string, queryTime: Date) {
        return socialuniImRequest.post<MessageVO[]>('socialuni/message/queryMessages', new MessageQueryVO(chatId, queryTime))
    }

    static deleteMsgAPI(msgId: number, deleteReason: string, violation: boolean) {
        return socialuniImRequest.post('message/deleteMsg', new MsgDeleteVO(msgId, deleteReason, violation))
    }
}
