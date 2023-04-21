import SocialuniCommonStatus from "socialuni-constant/constant/status/SocialuniCommonStatus";
import {OpenImMsgRO} from "socialuni/src/model/openIm/OpenImMsgRO";
import UUIDUtil from "socialuni-util/src/util/UUIDUtil";
import SocialuniUserRO from "socialuni/src/model/user/SocialuniUserRO";
import SocialuniMessageType from "socialuni-constant/constant/mesaage/SocialuniMessageType";
import {MessageType} from "socialuni-constant/constant/openIm/OpenImMessageType";
import {socialChatModule} from "socialuni-im/src/store/SocialChatModule";
import {socialuniUserModule} from "socialuni/src/store/SocialuniUserModule";

export default class MessageVO {
    public id: string
    public user: SocialuniUserRO
    public readNum: number
    public contentData: any  // 原始的数据内容
    public content: string // 用于展示
    public action: string  // 触发创建的api
    public createTime: Date
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
        this.type = SocialuniMessageType.simple
        if (msg) {
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
                this.type = SocialuniMessageType.system;
            } else if (msg.contentType === MessageType.FRIENDDELETED) {
                this.content = '好友已删除';
                this.type = SocialuniMessageType.system;
            } else if (msg.contentType === MessageType.REVOKEMESSAGE) {
                this.content = '消息已被撤回';
                this.type = SocialuniMessageType.system;
            } else if (msg.contentType === MessageType.MEMBERENTER) {
                this.content = '有新人入群'
                this.type = SocialuniMessageType.system;
            } else if (msg.contentType === MessageType.CUSTOMMESSAGE) {

                let customMessage = JSON.parse(msg.content);
                // 描述用于展示给用户。
                this.content = customMessage.description;

                // 删除。
                socialChatModule.openIm().then(openIm=>{
                    openIm.clearC2CHistoryMessageFromLocalAndSvr(customMessage.data).then(({ data })=>{
                        console.log(  '  clearC2CHistoryMessageFromLocalAndSvr  ');
                    }).catch(err=>{
                    })
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

            if ( msg.sendID === socialuniUserModule?.mineUser?.id) {
                this.user = socialuniUserModule.mineUser
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
            this.readStatus = SocialuniCommonStatus.enable
            this.status   = msg.status;
            this.isRead = msg.isRead
            this.createTime = msg.createTime
            this.contentType = msg.contentType//MessageContentType.text
        } else {
            this.id = UUIDUtil.getUUID()
            this.user = socialuniUserModule.mineUser
            this.readNum = 0
            this.content = content
            this.contentData = content;
            this.readStatus = SocialuniCommonStatus.sending
            this.status = 1;
            this.isMine = true
            this.isRead = true
            this.createTime = new Date().getTime()
            this.contentType = MessageType.TEXTMESSAGE //MessageContentType.text
            this.action = 'createTextMessage'
        }
    }


    getUserRO(msg: OpenImMsgRO) {
        const user = new SocialuniUserRO()
        user.id = msg.sendID
        user.isMine = msg.sendID === socialuniUserModule.userId
        user.nickname = msg.senderNickname
        user.avatar = msg.senderFaceUrl
        return user
    }
}
