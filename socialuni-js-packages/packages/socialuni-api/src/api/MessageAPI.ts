import request from 'socialuni-common/src/request'
import MessageAddVO from '../model/message/MessageAddVO'
import MessageQueryVO from '../model/message/MessageQueryVO'
import MsgDelete from '../model/message/MsgDeleteVO'
import MessageVO from '../model/message/MessageVO'

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
