import JsonUtil from 'socialuni-sdk/src/utils/JsonUtil'
import {OpenImMsgRO} from "./openIm/OpenImMsgRO";
import MessageVO from "./message/MessageVO";
import {OpenImChatRO} from "./openIm/OpenImChatRO";
import {socialChatModule} from "socialuni-sdk/src/store/store";
import SocialuniUserAPI from "../api/socialuni/SocialuniUserAPI";
import SocialuniUserRO from "./user/SocialuniUserRO";

export default class SocialuniChatRO {
    public id: string = null
    public nickname: string = null
    public type: number = null
    public status: string = null
    public messages: MessageVO[] = []
    public avatar: string = null
    public unreadNum: number = null
    public updateTime: number = null
    public lastMsg: OpenImMsgRO = null
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
            this.type = openImChat.conversationType
            // this.messages = chat.messages
            // this.topLevel = chat.topLevel
            this.topFlag = openImChat.isPinned
            this.unreadNum = openImChat.unreadCount
            this.updateTime = openImChat.latestMsgSendTime
            // this.lastContent = openImChat.lastContent
            // this.vipFlag = chat.vipFlag
            this.receiveUserId = openImChat.userID
            if (openImChat.latestMsg) {
                this.lastMsg = JsonUtil.toParse(openImChat.latestMsg)
                this.lastContent = this.lastMsg.content
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
        chat.status = CommonStatus.waitOpen
        // chat.needPayOpen = user.showBuyMsg
        return chat
      }*/
}
