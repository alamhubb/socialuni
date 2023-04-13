export default class MessageQueryVO {
  public chatId: string
  public msgIds: string []

  constructor (chatId: string, msgIds?: string[]) {
    this.chatId = chatId
    this.msgIds = msgIds
  }
}
