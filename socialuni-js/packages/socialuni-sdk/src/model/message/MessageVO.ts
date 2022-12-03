import CommonStatus from "socialuni-constant/constant/CommonStatus";
import MessageContentType from "socialuni-constant/constant/mesaage/MessageContentType";
import {OpenImMsgRO} from "socialuni-api/src/model/openIm/OpenImMsgRO";
import JsonUtil from "../../utils/JsonUtil";
import {socialUserModule} from "../../store/store";
import UUIDUtil from "../../utils/UUIDUtil";
import SocialuniUserRO from "socialuni-api/src/model/user/SocialuniUserRO";
import {MessageType} from "../../plugins/openIm/OpenImMessageType";
import SocialuniMessageType from "socialuni-constant/constant/mesaage/SocialuniMessageType";

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

    constructor(content: string, msg: OpenImMsgRO = null) {
        if (msg) {
            if (msg.contentType === MessageType.TEXTMESSAGE) {
                console.log(7777)
                console.log(msg.content)
                this.content = msg.content
            } else if (msg.contentType === MessageType.GROUPCREATED) {
                const contentObj = JsonUtil.toParse(msg.content)
                console.log(contentObj)
            }

            if (socialUserModule.mineUser && msg.sendID === socialUserModule.mineUser.id) {
                this.user = socialUserModule.mineUser
            } else {
                const user = new SocialuniUserRO()
                user.id = msg.sendID
                user.avatar = msg.senderFaceUrl
                user.nickname = msg.senderNickname
                user.isMine = false
                this.user = user
            }
            this.isMine = this.user.isMine
            this.id = msg.serverMsgID
            this.readNum = 0
            this.readStatus = CommonStatus.enable
            this.isRead = msg.isRead
            this.createTime = msg.createTime
            this.type = SocialuniMessageType.simple
            this.contentType = MessageContentType.text
        } else {
            this.id = UUIDUtil.getUUID()
            this.user = socialUserModule.mineUser
            this.readNum = 0
            this.content = content
            this.readStatus = CommonStatus.sending
            this.isMine = true
            this.isRead = true
            this.createTime = new Date().getTime()
            this.type = SocialuniMessageType.simple
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
