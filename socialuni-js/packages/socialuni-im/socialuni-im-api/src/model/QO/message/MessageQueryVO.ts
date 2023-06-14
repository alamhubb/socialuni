export default class MessageQueryVO {
  public chatId: string
  public queryTime: Date

  constructor (chatId: string, queryTime: Date) {
    this.chatId = chatId
    this.queryTime = queryTime
  }
}
