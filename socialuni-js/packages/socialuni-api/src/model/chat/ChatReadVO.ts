export default class ChatReadVO {
    public chatId: string
    public messageIds: string[]

    constructor (chatId: string, messageIds: string[]) {
      this.chatId = chatId
      this.messageIds = messageIds
    }
}
