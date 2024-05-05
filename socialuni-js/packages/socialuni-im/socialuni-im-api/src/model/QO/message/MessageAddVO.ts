export default class MessageAddVO {
    public receiveId: string
    public content: string
    public type: string

    constructor(receiveId: string, content: string, type: string = null) {
        this.receiveId = receiveId
        this.content = content
        this.type = type
    }
}
