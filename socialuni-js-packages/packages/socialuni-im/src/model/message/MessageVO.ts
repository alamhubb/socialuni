import CenterUserDetailRO from 'socialuni-api/src/model/social/CenterUserDetailRO'
import CommonStatus from "socialuni-constant/constant/CommonStatus";
import MessageType from "socialuni-constant/constant/MessageType";
import MessageContentType from "socialuni-constant/constant/mesaage/MessageContentType";
import {OpenImMsgRO} from "socialuni-api/src/model/openIm/OpenImMsgRO";
import SocialuniUserAPI from "socialuni-api/src/api/socialuni/SocialuniUserAPI";
import SocialuniContentUserRO from "socialuni-api/src/model/social/SocialuniContentUserRO";
import UUIDUtil from "packages/socialuni-use/src/utils/UUIDUtil";

export default class MessageVO {
    public id: string
    public user: SocialuniContentUserRO
    public readNum: number
    public content: string
    public createTime: number
    public readStatus: string
    public type: string
    public contentType: string
    public isMine: boolean
    public isRead: boolean

    constructor(user: CenterUserDetailRO, content: string, msg: OpenImMsgRO = null) {
        if (msg) {
            this.id = msg.clientMsgID
            this.user = new SocialuniContentUserRO(msg)
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
}
