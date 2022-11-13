import CommonStatus from "socialuni-constant/constant/CommonStatus";
import MessageType from "socialuni-constant/constant/MessageType";
import MessageContentType from "socialuni-constant/constant/mesaage/MessageContentType";
import UUIDUtil from "socialuni-use/src/utils/UUIDUtil";
import {OpenImMsgRO} from "../openIm/OpenImMsgRO";
import {socialUserModule} from "socialuni-user/src/store/store";
import SocialuniUserRO from "socialuni-user/src/model/user/SocialuniUserRO";

export default class MessageVO {
    public id: string
    public user: SocialuniUserRO
    public readNum: number
    public content: string
    public createTime: number
    public readStatus: string
    public type: string
    public contentType: string
    public isMine: boolean
    public isRead: boolean

    constructor(user: SocialuniUserRO, content: string, msg: OpenImMsgRO = null) {
        if (msg) {
            this.id = msg.clientMsgID
            this.user = this.getUserRO(msg)
            this.readNum = 0
            this.content = msg.content
            this.readStatus = CommonStatus.sending
            this.isMine = this.user.isMine
            this.isRead = msg.isRead
            this.createTime = msg.createTime
            this.type = MessageType.simple
            this.contentType = MessageContentType.text
        } else {
            this.id = UUIDUtil.getUUID()
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


    getUserRO(msg: OpenImMsgRO) {
        const user = new SocialuniUserRO()
        user.id = msg.sendID
        user.isMine = msg.sendID === socialUserModule.userId
        user.nickname = msg.senderNickname
        user.avatar = msg.senderFaceUrl
        return user
    }
}
