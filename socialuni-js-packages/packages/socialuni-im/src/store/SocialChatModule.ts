import { Pinia, Store } from "pinia-class-component"
import PageUtil from "socialuni-common/src/utils/PageUtil"
import MessageVO from "socialuni-api/src/model/message/MessageVO"
import ChatAPI from "socialuni-api/src/api/ChatAPI"
import RouterUtil from "socialuni-use/src/utils/RouterUtil"
import PagePath from "socialuni-constant/constant/PagePath"
import PlatformUtils from "socialuni-common/src/utils/PlatformUtils"
import ChatType from "socialuni-constant/constant/ChatType"
import CommonUtil from "socialuni-use/src/utils/CommonUtil"
import JsonUtil from "socialuni-use/src/utils/JsonUtil"
import { OpenImChatRO } from "socialuni-api/src/model/openIm/OpenImChatRO"
import { socialUserModule } from "socialuni-user/src/store/store"
import SocialuniUserRO from "socialuni-user/src/model/SocialuniUserRO"

import { OpenIMSDK } from "open-im-sdk"
import { InitConfig } from "open-im-sdk/types"
import SocialuniConfig from "socialuni-api/src/config/SocialuniConfig"
import { socialChatModule } from './store'
import OpenImPlatformType from '../plugins/openIm/OpenImPlatformType'
import SocialuniChatRO from '../model/SocialuniChatRO'

const openIm = new OpenIMSDK()

@Store
export default class SocialChatModule extends Pinia {
  imToken = ''

  chatId: string = null
  chats: SocialuniChatRO[] = []

  scrollTop: number = 0
  chatsUnreadNumTotal = 0


  async initSocialuniChatModule() {
    await socialUserModule.initSocialuniUserModule()
    //获取imToken
    const config: InitConfig = {
      userID: socialUserModule.userId,
      token: socialUserModule.imToken,
      url: SocialuniConfig.openImJsImUrl,
      platformID: OpenImPlatformType.web
    }
    console.log(config)
    await openIm.login(config)
    const options = {
      offset: 0,
      count: 20
    }
    openIm.getConversationListSplit(options).then(({ data }) => {
      console.log(11111)
      const chats: OpenImChatRO[] = JsonUtil.parse(data)
      this.chats = chats.map(item => new SocialuniChatRO(item))
    })
  }


  async queryChats() {

  }

  //仅负责，排序展示，在chatVue界面实现了
  /*get chats (): SocialuniChatRO[] {
    //a和b比较，返回结果1，则倒序，后者在前面
    return this.queryChats.sort((chat, chatAfter) => {
      //如果置顶优先级比较高，则排前面
      if (chatAfter.topLevel > chat.topLevel) {
        return 1
      } else if (chatAfter.topFlag != chat.topFlag) {
        //是否置顶，如果一个置顶，一个不置顶，则置顶的排前面
        if (chatAfter.topFlag) {
          return 1
        } else {
          return -1
        }
      } else {
        //对比时间
        if (chatAfter.updateTime > chat.updateTime) {
          return 1
        } else {
          return -1
        }
      }
    })
  }*/


  //因为存在排序，所以index并不是更新了update就是第一个，不总是为0，并不总是第一个,
  get chat(): SocialuniChatRO {
    return this.chats[this.chatIndex]
    // return this.chats[0this.chatIndex.]
  }

  get chatIndex(): number {
    return this.chats.findIndex(item => item.id === this.chatId)
  }

  get messages(): MessageVO[] {
    if (this.chat.messages.length) {
      // JsonUtils.log(this.chat)
    }
    return this.chat.messages
  }

  //从列表中进入，从个人详情页进入

  //从详情进入，查看列表中是否有与此人的chat？如何查看，
  //列表中进入，需要调用后台，更新时间。

  //从列表中进入
  setChatIdToMessagePage(chatId: string) {
    this.setChatId(chatId)
    // this.readChatAction(this.chat)
    PageUtil.toMessagePage()
    this.scrollToMessagePageBottom()
  }

  setChatId(chatId: string) {
    this.chatId = chatId
  }


  userDetailToMessagePage(chat: SocialuniChatRO) {
    //第一步，先看列表中是否已有与此用户的聊天列表
    const findChat = this.chats.find(item => item.id === chat.id)
    //如果已有,则直接跳转过去
    if (!findChat) {
      //首先查询是否有名字相同的chat
      //这样则需要chat名称不能相同，
      //创建mock chat
      // chat = ChatVO.creatChat(user)
      //如果本地没有则更新时间为最新，同步后台逻辑
      chat.updateTime = new Date().getTime()
      this.chats.unshift(chat)
      /*//修改当前chat的id
      this.setChatId(chat.id)
      //后台创建真实chat
      ChatAPI.getChatAPI(user).then(res => {
        constant resultChat: SocialuniChatRO = res.data
        //修改mock chatId
        chat.id = resultChat.id
        //修改当前chat的id
        this.setChatId(resultChat.id)
        //替换当前chat
        this.replaceChat(resultChat)
      })*/
    }
    this.setChatIdToMessagePage(chat.id)
  }

  openChatAction(content) {
    const needPayOpen = this.chat.needPayOpen
    return ChatAPI.openChatAPI(this.chat.id, this.chat.needPayOpen, content).then((res) => {
      socialChatModule.replaceChat(this.chatIndex, res.data)
      socialChatModule.scrollToMessagePageBottom()
      if (needPayOpen) {
        // userModule.user.shell -= 10
      }
    })
  }

  //来消息后，替换已有chat
  pushMsgReplaceChat(chatIndex, chat: SocialuniChatRO) {
    const oldChat = this.chats[chatIndex]
    const messages: MessageVO[] = oldChat.messages
    //将新消息放到当前msg中
    messages.push(...chat.messages)
    chat.messages = messages
    chat.loadMore = oldChat.loadMore
    //不需要替换，修改了
    this.replaceChat(chatIndex, chat)
  }


  replaceChat(chatIndex, chat: SocialuniChatRO) {
    this.chats.splice(chatIndex, 1, chat)
  }


  /*get chatsUnreadNumTotal () {
    // 应该在这里计算是否显示红点
    constant chatUnreadNum = this.queryChats.reduce((total, chat) => {
      total = total + chat.unreadNum
      return total
    }, 0)
    // 如果未读数量为0了，则隐藏红点
    if (chatUnreadNum) {
      uni.showTabBarRedDot({
        index: 2
      })
    } else {
      uni.hideTabBarRedDot({
        index: 2
      })
    }
    return chatUnreadNum
  }*/

  // 四个地方使用，初始查询，推送消息，阅读清空消息，删除消息
  //为什么不使用get呢,get不行微信小程序有兼容问题

  computedChatsUnreadNumTotalAction() {
    // 应该在这里计算是否显示红点
    /*this.chatsUnreadNumTotal = this.chats.reduce((total, chat) => {
      total = total + chat.unreadNum
      return total
    }, 0)

    constant chatUnreadNum: number = this.chatsUnreadNumTotal
    // 如果未读数量为0了，则隐藏红点
    if (chatUnreadNum) {
      uni.showTabBarRedDot({
        index: 2
      })
    } else {
      uni.hideTabBarRedDot({
        index: 2
      })
    }*/
  }


  scrollToMessagePageBottom() {
    CommonUtil.delayTime(100).then(() => {
      this.scrollTop = this.messages.length * 500
      // this.scrollTop = -1000
    })
    // ScrollUtil.scrollTo(this.messages.length * 500)
  }


  //后台推送了一条消息，你不知道前台是否有这条消息，后台更不可能知道前台是否删除了这条消息
  //就直接push进去就行了。然后方法内部判断，是否有，有的话替换，msg push，没有的话直接unshift
  //有三种可能
  //一，在展示当前chat，2没在，但是列表中有，3列表中没有
  //如果是正在聊的，需要改为，已读，先不做已读未读

  pushChatAndMessagesAction(newChat: SocialuniChatRO) {
    // console.log('出发了pushchat')
    // 如果正在这个chat聊天
    if (RouterUtil.getCurrentPageURI() === PagePath.message && this.chatId === newChat.id) {
      // if (this.chatId === newChat.id) {
      // 则直接往msg增加消息
      // 前台将消息改为已读,修改时间使用后台的就行
      this.readChatAction(newChat)
      //将新消息放到当前msg中并替换
      this.pushMsgReplaceChat(this.chatIndex, newChat)
      this.scrollToMessagePageBottom()
      // 后台改为已读
      // 向后台发送消息，将收到的消息改为已读
      // 如果当前就是这个聊天
    } else {
      const newChatIndex = this.chats.findIndex(item => item.id === newChat.id)
      if (newChatIndex > -1) {
        //将新消息放到当前msg中并替换
        this.pushMsgReplaceChat(newChatIndex, newChat)
      } else {
        this.chats.unshift(newChat)
      }
      //不需要吧，后台chat应该计算好当前未读数量
      /*// 如果已登录
      if (UserStore.hasUser() && chat.type !== ChatType.system_group) {
        chat.unreadNum = newChat.unreadNum
      } else {
        chat.unreadNum = chat.unreadNum + 1
      }*/

      // 不是正在这个chat聊天，但是chats列表中包含这个chat
      // 如果列表中已经包含次chat
      // 则找到chat，赋值,加入新message
      // 在列表中将这个chat放到最前面
      // 找到需要添加内容的chat
    }
    //计算未读数量
    this.computedChatsUnreadNumTotalAction()
  }

  //在聊天界面的时候，自己发送msg 本地添加msg

  pushMessageAction(msg: MessageVO) {
    this.messages.push(msg)
    // JsonUtils.log(this.messages)
    // console.log(JSON.stringify(msg))
    this.scrollToMessagePageBottom()
    const index: number = this.messages.length - 1
    // console.log(index)
    this.chat.updateTime = new Date().getTime()
    // this.chat.lastContent = msg.content
    // 滚屏到最后面
    // 不能监控变化滚动，有时候是往前面插入


    SocialuniMsg.sendMsgAPI<MessageVO>(this.chat.id, msg.content).then((res) => {
      // 后台返回后再替换
      this.chat.updateTime = res.data.createTime
      this.messages.splice(index, 1, res.data)
    }).catch(() => {
      // 这里应该变为发送失败
      this.messages.splice(index, 1)
    })
    PlatformUtils.requestSubscribeChat()
  }


  // 前台和后台都将chat和msg改为已读,更新chat的时间

  readChatAction(chat: SocialuniChatRO) {
    //目前不根据点击时间更新，只根据消息时间更新
    // chat.updateTime = new Date().getTime()
    // 不为自己的 且未读的
    const messages: MessageVO[] = chat.messages.filter(item => !item.isMine && !item.isRead)
    const msgIds: number[] = messages.map(msg => msg.id)
    // if (messages.length > 0) {
    // msgIds =
    //如果登录了，才调用后台
    // 如果登录了
    //目前 官方群聊没记录已读状态，读取也不管用
    if (socialUserModule.hasUser && chat.type !== ChatType.system_group) {
      ChatAPI.readChatAPI(chat.id, msgIds)
    }
    for (const message of messages) {
      message.isRead = true
    }
    chat.unreadNum = 0
    this.computedChatsUnreadNumTotalAction()
    // }
  }


  //获取chats

  getChatsAction() {
    /*return ChatAPI.getChatsAPI().then((res: ResultRO<ChatVO[]>) => {
      this.setChats(res.data)
    })*/
  }

  setChats(chats: SocialuniChatRO[]) {
    this.chats = chats
    this.computedChatsUnreadNumTotalAction()
  }


  //前台删除，需要增加后台删除逻辑

  deleteMsgAction(msgId: number) {
    this.messages.splice(this.messages.findIndex(msg => msg.id === msgId), 1)
  }


  deleteChatAction(chatId: number) {
    this.chats.splice(this.chats.findIndex(chat => chat.id === chatId), 1)
  }
}

/*
  pushMessagesAction (msgs: MessageVO[]) {

  }*/
/*

  pushChatAction (newChat: SocialuniChatRO) {
    this.chats.unshift(newChat)
  }*/

