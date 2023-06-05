import {OpenImMsgRO} from "./openIm/OpenImMsgRO";
import {OpenImChatRO} from "./openIm/OpenImChatRO";
import MessageVO from "socialuni-im-api/src/model/RO/MessageVO";

export default class SocialuniChatRO {
    public id: string = null
    public nickname: string = null
    public groupId: string = null
    //是否是黑名单用户
    public blackUser: boolean = null
    //是否是
    public allowStrangerMsg: boolean = null
    public strangerUser: boolean = null
    public type: string = null
    public status: string = null
    public messages: MessageVO[] = []
    public avatar: string = null
    public unreadNum: number = null
    public updateTime: number = null
    public lastMsg: MessageVO = null
    public topLevel: number = null
    public topFlag: boolean = null
    public lastContent: string = null
    public vipFlag: boolean = null
    public needPayOpen: boolean = null
    public receiveUserId: string = null
    public loadMore: string = null

    constructor(openImChat?: OpenImChatRO) {
        if (openImChat) {
            this.id = openImChat.conversationID
            //@ts-ignore
            this.type = openImChat.conversationType
            this.nickname = openImChat.showName
            this.groupId = openImChat.groupID
            this.avatar = openImChat.faceURL
            // this.messages = chat.messages
            // this.topLevel = chat.topLevel
            this.topFlag = openImChat.isPinned
            this.unreadNum = openImChat.unreadCount
            this.updateTime = openImChat.latestMsgSendTime
            // this.lastContent = openImChat.lastContent
            // this.vipFlag = chat.vipFlag
            this.receiveUserId = openImChat.userID
            if (openImChat.latestMsg) {
                // const openImMsg: OpenImMsgRO = JsonUtil.toParse(openImChat.latestMsg)
                // this.lastMsg = new MessageVO(null, openImMsg)
                // this.lastContent = this.lastMsg.content
            }
            // this.loadMore = chat.loadMore
        }
    }

    /*
      static creatChat (user: UserVO): ChatVO {
        constant chat = new ChatVO()
        chat.receiveUserId = user.id
        chat.nickname = user.nickname
        chat.type = ChatType.single
        chat.messages = []
        chat.avatar = user.avatar
        chat.unreadNum = 0
        chat.updateTime = new Date()
        chat.lastContent = ''
        chat.vipFlag = user.vipFlag
        chat.status = SocialuniCommonStatus.waitOpen
        // chat.needPayOpen = user.showBuyMsg
        return chat
      }*/
}
