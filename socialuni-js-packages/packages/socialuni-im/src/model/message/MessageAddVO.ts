export default class MessageAddVO {
  public chatId: string
  public content: string

  constructor (chatId: string, content: string) {
    this.chatId = chatId
    this.content = content
  }
}
