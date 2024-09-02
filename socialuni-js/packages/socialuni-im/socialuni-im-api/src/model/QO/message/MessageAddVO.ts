export default class MessageAddVO {
    public chatId: string
    public content: string
    public type: string

    constructor(receiveId: string, content: string, type: string = null) {
        this.chatId = receiveId
        this.content = content
        this.type = type
    }
}
