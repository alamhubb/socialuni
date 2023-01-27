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
    // 撤回消息需要原始的结构体。
    public originalMsg: OpenImMsgRO
    constructor(content: string, msg: OpenImMsgRO = null) {
        this.originalMsg = msg;
        if (msg) {
            console.log('MessageVO-----constructor',msg.contentType)
            if (msg.contentType === MessageType.TEXTMESSAGE) {
                this.content = msg.content
            } else if (msg.contentType === MessageType.GROUPCREATED) {
                /*const contentObj: { jsonDetail: string } = JsonUtil.toParse(msg.content)
                const jsonObj = JsonUtil.toParse(contentObj.jsonDetail)
                console.log(123)
                console.log(jsonObj)*/
                this.content = '群聊创建成功'
            } else if (msg.contentType === MessageType.FRIENDADDED) {
                /*const contentObj: { jsonDetail: string } = JsonUtil.toParse(msg.content)
                const jsonObj = JsonUtil.toParse(contentObj.jsonDetail)
                console.log(123)
                console.log(jsonObj)*/
                this.content = '好友添加成功'
            } else if (msg.contentType === MessageType.FRIENDDELETED) {
                this.content = '好友已删除'
            } else if (msg.contentType === MessageType.REVOKEMESSAGE) {
                this.content = '消息已被撤回'
            } else if (msg.contentType === MessageType.MEMBERENTER) {
                this.content = 'hello,我是刚入群的新人'
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
