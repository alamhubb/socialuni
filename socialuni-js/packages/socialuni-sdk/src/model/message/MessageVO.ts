import CommonStatus from "socialuni-constant/constant/CommonStatus";
import MessageContentType from "socialuni-constant/constant/mesaage/MessageContentType";
import {OpenImMsgRO} from "socialuni-api/src/model/openIm/OpenImMsgRO";
import JsonUtil from "../../utils/JsonUtil";
import {socialChatModule, socialUserModule} from "../../store/store";
import UUIDUtil from "../../utils/UUIDUtil";
import SocialuniUserRO from "socialuni-api/src/model/user/SocialuniUserRO";
import {MessageType} from "../../plugins/openIm/OpenImMessageType";
import SocialuniMessageType from "socialuni-constant/constant/mesaage/SocialuniMessageType";

export default class MessageVO {
    public id: string
    public user: SocialuniUserRO
    public readNum: number
    public contentData: any  // 原始的数据内容
    public content: string // 用于展示
    public action: string  // 触发创建的api
    public createTime: number
    public readStatus: string
    public status: number // 发送的状态。
    public type: string
    public contentType: number
    public isMine: boolean
    public isRead: boolean
    // 撤回消息需要原始的结构体。
    public originalMsg: OpenImMsgRO

    /**
     * 创建任意内容的。
     * @param action
     * @param content
     */
    static  create(action: string , contentData: any) : MessageVO{
        let messageVO = new MessageVO(null,null);
        messageVO.action = action;
        messageVO.contentData = contentData;
        return messageVO;
    }
    constructor(content: string, msg: OpenImMsgRO = null) {
        this.originalMsg = msg;
        if (msg) {
            console.log(msg,'MessageVO-----constructor',msg.contentType)
            this.contentData = msg.content;
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
            } else if (msg.contentType === MessageType.CUSTOMMESSAGE) {

                let customMessage = JSON.parse(msg.content);
                // 描述用于展示给用户。
                this.content = customMessage.description;

                // 删除。
                socialChatModule.openIm.clearC2CHistoryMessageFromLocalAndSvr(customMessage.data).then(({ data })=>{
                    console.log(  '  clearC2CHistoryMessageFromLocalAndSvr  ');
                }).catch(err=>{
                })
            } else if (msg.contentType === MessageType.PICTUREMESSAGE) {
                // 描述用于展示给用户。
                this.content = '收到一张图片';
                this.contentData = JSON.parse(msg.content);
            } else if (msg.contentType === MessageType.VIDEOMESSAGE) {
                // 描述用于展示给用户。
                this.content = '收到一个视频';
                this.contentData = JSON.parse(msg.content);
            }

            if ( msg.sendID === socialUserModule?.mineUser?.id) {
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
            this.status   = msg.status;
            this.isRead = msg.isRead
            this.createTime = msg.createTime
            this.type = SocialuniMessageType.simple
            this.contentType = msg.contentType//MessageContentType.text
        } else {
            this.id = UUIDUtil.getUUID()
            this.user = socialUserModule.mineUser
            this.readNum = 0
            this.content = content
            this.contentData = content;
            this.readStatus = CommonStatus.sending
            this.status = 1;
            this.isMine = true
            this.isRead = true
            this.createTime = new Date().getTime()
            this.type = SocialuniMessageType.simple
            this.contentType = MessageType.TEXTMESSAGE //MessageContentType.text
            this.action = 'createTextMessage'
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
