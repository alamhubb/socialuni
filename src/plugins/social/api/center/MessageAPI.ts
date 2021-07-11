import socialHttp from '@/plugins/social/http'
import MessageAddVO from '@/model/message/MessageAddVO'
import MessageQueryVO from '@/model/message/MessageQueryVO'
import MsgDelete from '@/model/message/MsgDeleteVO'
import MessageVO from '@/model/message/MessageVO'

export default class MessageAPI {
  static sendMsgAPI<T> (chatId: number, content: string) {
    const msgAdd: MessageAddVO = new MessageAddVO(chatId, content)
    return socialHttp.post <T>('message/sendMsg', msgAdd)
  }

  static queryMessagesAPI (chatId: number, msgIds: number []) {
    return socialHttp.post<MessageVO[]>('message/queryMessages', new MessageQueryVO(chatId, msgIds))
  }

  static deleteMsgAPI (msgId: number, deleteReason: string, violation: boolean) {
    return socialHttp.post('message/deleteMsg', new MsgDelete(msgId, deleteReason, violation))
  }
}
