import CenterUserDetailRO from '../social/CenterUserDetailRO'
import CommonStatus from "socialuni-constant/constant/CommonStatus";
import MessageType from "socialuni-constant/constant/MessageType";
import MessageContentType from "socialuni-constant/constant/mesaage/MessageContentType";

export default class MessageVO {
    public id: number
    public user: CenterUserDetailRO
    public readNum: number
    public content: string
    public createTime: number
    public readStatus: string
    public type: string
    public contentType: string
    public isMine: boolean
    public isRead: boolean

    constructor(user: CenterUserDetailRO, content: string) {
        this.id = Math.random()
        this.user = user
        this.readNum = 0
        this.content = content
        this.readStatus = CommonStatus.sending
        this.isMine = true
        this.isRead = true
        this.createTime = new Date().getTime()
        this.type = MessageType.simple
        this.contentType = MessageContentType.text
    }
}
