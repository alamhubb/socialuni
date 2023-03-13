import MessageAddVO from '../../../socialuni-api/src/model/message/MessageAddVO'
import MessageQueryVO from '../../../socialuni-api/src/model/message/MessageQueryVO'
import MsgDelete from '../../../socialuni-api/src/model/message/MsgDeleteVO'
import MessageVO from 'socialuni-sdk/src/model/message/MessageVO'
import request from "../../socialuni-common-api/src/request/request";

export default class MessageAPI {
    static sendMsgAPI<T>(chatId: string, content: string) {
        const msgAdd: MessageAddVO = new MessageAddVO(chatId, content)
        return request.post <T>('message/sendMsg', msgAdd)
    }

    static queryMessagesAPI(chatId: string, msgIds: number []) {
        return request.post<MessageVO[]>('message/queryMessages', new MessageQueryVO(chatId, msgIds))
    }

    static deleteMsgAPI(msgId: number, deleteReason: string, violation: boolean) {
        return request.post('message/deleteMsg', new MsgDelete(msgId, deleteReason, violation))
    }
}
