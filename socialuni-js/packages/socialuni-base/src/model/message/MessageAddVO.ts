export default class MessageAddVO {
  public receiveId: string
  public content: string

  constructor (receiveId: string, content: string) {
    this.receiveId = receiveId
    this.content = content
  }
}
