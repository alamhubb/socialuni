import JsonUtil from 'socialuni-sdk/src/utils/JsonUtil'
import {OpenImMsgRO} from "./openIm/OpenImMsgRO";
import MessageVO from "./message/MessageVO";
import {OpenImChatRO} from "./openIm/OpenImChatRO";
import {socialChatModule} from "../store/store";

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
    console.log(openImChat)
    if (openImChat) {
      this.id = openImChat.conversationID
      this.nickname = openImChat.showName
      this.type = openImChat.conversationType
      // this.messages = chat.messages
      this.avatar = openImChat.faceURL
      // this.topLevel = chat.topLevel
      this.topFlag = openImChat.isPinned
      this.unreadNum = openImChat.unreadCount
      this.updateTime = openImChat.latestMsgSendTime
      // this.lastContent = openImChat.lastContent
      // this.vipFlag = chat.vipFlag
      this.receiveUserId = openImChat.userID
      this.lastMsg = JsonUtil.parse(openImChat.latestMsg)
      this.lastContent = this.lastMsg.content


      const options = {
        conversationID: this.id,
        startClientMsgID: "",
        count: 10,
        groupID: "",
        userID: "",
      }
      console.log(options)
      socialChatModule.openIm.getHistoryMessageList(options).then(({data}) => {
        const msgs: OpenImMsgRO[] = JsonUtil.parse(data)
        this.messages = msgs.map(item => new MessageVO(null, null, item))
      }).catch(err => {
      }).finally(() => {
        console.log(456)
      })

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
