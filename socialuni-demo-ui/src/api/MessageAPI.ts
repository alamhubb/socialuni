import request from "socialuni-common/src/request/request";
import MessageAddVO from "socialuni-api/src/model/message/MessageAddVO";
import MessageQueryVO from "socialuni-api/src/model/message/MessageQueryVO";
import MessageVO from "socialuni-api/src/model/message/MessageVO";

export default class MessageAPI {
    static sendMsgAPI<T>(chatId: string, content: string) {
        const msgAdd: MessageAddVO = new MessageAddVO(chatId, content)
        return request.post <T>('message/sendMsg', msgAdd)
    }

    static queryMessagesAPI(chatId: string, msgIds: string []) {
        return request.post<MessageVO[]>('message/queryMessages', new MessageQueryVO(chatId, msgIds))
    }

    static deleteMsgAPI(msgId: string, deleteReason: string, violation: boolean) {
        // return request.post('message/deleteMsg', new MsgDelete(msgId, deleteReason, violation))
  }
}
