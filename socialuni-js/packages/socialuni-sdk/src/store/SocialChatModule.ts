import {Pinia, Store} from "pinia-class-component"
import PageUtil from "socialuni-sdk/src/utils/PageUtil"
import ChatAPI from "socialuni-api/src/api/ChatAPI"
import RouterUtil from "socialuni-sdk/src/utils/RouterUtil"
import PagePath from "socialuni-constant/constant/PagePath"
import PlatformUtils from "socialuni-sdk/src/utils/PlatformUtils"
import ChatType from "socialuni-constant/constant/ChatType"
import CommonUtil from "socialuni-sdk/src/utils/CommonUtil"
import JsonUtil from "socialuni-sdk/src/utils/JsonUtil"
import {socialConfigModule, socialTalkModule, socialUserModule} from "socialuni-sdk/src/store/store"
import {
    CbEvents,
    CreateGroupParams, CustomMsgParams,
    GroupInitInfo, GroupMsgReadParams, ImageMsgParams,
    JoinGroupParams, MarkC2CParams,
    Member,
    OpenIMSDK, PicBaseInfo, PinCveParams, SearchGroupParams,
    SetGroupVerificationParams, setPrvParams, VideoMsgParams
} from "open-im-sdk"
import {InitConfig} from "open-im-sdk/types"
import SocialuniConfig from "socialuni-api/src/config/SocialuniConfig"
import {socialChatModule} from './store'
import OpenImPlatformType from '../plugins/openIm/OpenImPlatformType'
import SocialuniChatRO from "socialuni-api/src/model/SocialuniChatRO";
import {OpenImChatRO} from "socialuni-api/src/model/openIm/OpenImChatRO";
import OpenImSessionType from "../plugins/openIm/constant/OpenImSessionType";
import CenterUserDetailRO from "socialuni-api/src/model/social/CenterUserDetailRO";
import SocialuniImUserTokenUtil from "../utils/SocialuniImUserTokenUtil";
import {OpenImMsgRO} from "socialuni-api/src/model/openIm/OpenImMsgRO";
import SocialuniTalkTabCircleRO from "socialuni-api/src/model/community/circle/SocialuniTalkTabCircleRO";
import SocialuniCircleAPI from "socialuni-api/src/api/socialuni/SocialuniCircleAPI";
import CircleCreateChatQO from "socialuni-api/src/model/community/circle/CircleCreateChatQO";
import UniUtil from "../utils/UniUtil";
import MessageVO from "../model/message/MessageVO";
import {GroupJoinSource} from "../plugins/openIm/OpenImMessageType";
import OpenImFriendApplyRO from "../model/friend/OpenImFriendApplyRO";
import FriendApplyType from "socialuni-constant/constant/FriendApplyType";
import UUIDUtil from "../utils/UUIDUtil";


const openIM = new OpenIMSDK()

@Store
export default class SocialChatModule extends Pinia {
    //请使用getOpenIm
    private _openIm = openIM
    //0.1.2
    //3个状态，加载中，成功，未加载
    openImIsLogin = 0
    private userImToken: string = SocialuniImUserTokenUtil.get() || null
    recvFriendApplicationList: OpenImFriendApplyRO[] = []

    setOpenImLoginSuccess() {
        this.openImIsLogin = 2
    }

    setOpenImLogging() {
        this.openImIsLogin = 1
    }

    setOpenImUnLogin() {
        this.openImIsLogin = 0
    }

    async openIm() {
        await this.initSocialuniChatModule()
        return this._openIm
    }

    async waitLoginSuccess() {
        if (this.openImIsLogin === 1) {
            await CommonUtil.delayTime(10);
            await this.waitLoginSuccess();
        }
    }

    get imToken() {
        return this.userImToken
    }

    // chatId: string = null
    chats: SocialuniChatRO[] = []

    scrollTop: number = 0
    chatsUnreadNumTotal = 0

    async initSocialuniChatModule() {
        if (this.openImIsLogin === 0) {
            this.setOpenImLogging()
            //获取imToken
            const config: InitConfig = {
                userID: socialUserModule.userId,
                token: socialChatModule.imToken,
                url: SocialuniConfig.openImJsImUrl,
                platformID: OpenImPlatformType.web
            }
            try {
                const res = await this._openIm.login(config)
                this.setOpenImLoginSuccess()
                this.refreshChats()
                this.initOpenImListeners()
                this.refreshRecvFriendApplicationList();
                this.computedChatsUnreadNumTotalAction();
            } catch (e) {
                this.setOpenImUnLogin()
                await this.initSocialuniChatModule()
            }
        } else {
            //如果未成功则无限等待，否则，执行下一步
            await this.waitLoginSuccess()
        }
    }

    async initOpenImListeners() {
        ;(await this.openIm()).on(CbEvents.ONNEWCONVERSATION, (data) => {
            console.log('ONNEWCONVERSATION', data)
        })
        ;(await this.openIm()).on(CbEvents.ONCONVERSATIONCHANGED, (data) => {
            console.log('ONCONVERSATIONCHANGED', data)
            // 撤回消息回显。
            this.refreshChats();
            // this.showTabBarRedDot();
        })
        ;(await this.openIm()).on(CbEvents.ONTOTALUNREADMESSAGECOUNTCHANGED, (data) => {
            console.log('ONTOTALUNREADMESSAGECOUNTCHANGED', data)
        })
        ;(await this.openIm()).on(CbEvents.ONRECVNEWMESSAGE, (data) => {
            console.log('ONRECVNEWMESSAGE', data)
            this.refreshChats();
            this.showTabBarRedDot();
        })
        ;(await this.openIm()).on(CbEvents.ONRECVNEWMESSAGES, (data) => {
            console.log('ONRECVNEWMESSAGES', data)
        })
        // 收到新邀请时
        ;(await this.openIm()).on(CbEvents.ONFRIENDAPPLICATIONADDED, (data) => {
            console.log('收到新邀请时ONFRIENDAPPLICATIONADDED', data)
            this.refreshRecvFriendApplicationList();
            // this.showTabBarRedDot();
        })
    }

    /**
     * 设置 底部导航栏红点
     */
    showTabBarRedDot() {
        uni.showTabBarRedDot({
            index: 2
        })
    }

    setImToken(token: string) {
        this.userImToken = token
        SocialuniImUserTokenUtil.set(token)
    }

    async removeImToken() {
        this.setImToken(null)
        ;(await this.openIm()).logout().then(_ => {
            this.setOpenImUnLogin()
        })
    }


    loginOpenImg() {

    }


    async refreshChats() {
        const options = {
            offset: 0,
            count: 20
        };
        ;(await this.openIm()).getConversationListSplit(options).then(({data}) => {
            const chats: OpenImChatRO[] = JsonUtil.toParse(data)
            const newChats = chats.map(item => {
                const chat = new SocialuniChatRO(item)
                if (this.chat && chat.id === this.chat.id) {
                    this.setChat(chat)
                }
                return chat
            })
            this.setChats(newChats)
        })
    }

    async checkFriend(user: CenterUserDetailRO) {
        if (!user.isMine) {
            // console.log('=========checkFriend==========')
            (await socialChatModule.openIm()).checkFriend([user.id]).then(({data}) => {
                // console.log('checkFriend',data,this.hasFriend,typeof data);
                // 他是string需要手动转化一下。
                data = JSON.parse(data);
                console.log(data)
                // console.log('checkFriend222222222222222222',data,this.hasFriend,typeof data);
                for (let i = 0; i < data.length; i++) {
                    let datum = data[i];
                    // console.log('==============datum===============',datum,user.id,datum.userID );
                    if (datum.userID == user.id) {
                        user.hasFriend = datum.result !== 0;
                        // console.log('checkFriend',data,this.hasFriend);
                    }
                }
            }).catch(err => {
                console.log('checkFriend--err', err);
            })
        }
    }


    async queryChats() {

    }

    async pinConversation(conversationID: string, isPinned: boolean = true) {
        const options: PinCveParams = {
                conversationID,
                isPinned
            }
        ;(await this.openIm()).pinConversation(options).then(({data}) => {
        }).catch(err => {
        })
    }

    async setOneConversationPrivateChat(conversationID: string, isPrivate: boolean = true) {
        const options: setPrvParams = {
                conversationID,
                isPrivate
            }
        ;(await this.openIm()).setOneConversationPrivateChat(options).then(({data}) => {
        }).catch(err => {
        })
    }

    /**
     * 获取收到的好友请求列表
     * @param handleResult -1 拒绝   0 未处理   1 同意
     */
    getRecvFriendApplicationList(handleResult: number) {
        return this.recvFriendApplicationList.filter(item => item.handleResult == handleResult);
    }

    /**
     * 获取收到的好友请求列表
     */
    async refreshRecvFriendApplicationList() {
        await (await this.openIm()).getRecvFriendApplicationList().then(({data}) => {
            const list = JSON.parse(data);
            const newList = []
            for (const datum of list) {
                const item = new OpenImFriendApplyRO(datum)
                item.type = FriendApplyType.recvFriendApply
                newList.push(item)
            }
            this.recvFriendApplicationList = newList;
            // 计算小红点
            this.computedChatsUnreadNumTotalAction();
        })
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
    /*get chat(): SocialuniChatRO {
        //不再使用index，存在陌生人情况
        return this.chats[this.chatIndex]

        // return this.chats[0this.chatIndex.]
    }*/

    chat: SocialuniChatRO = null

    //为避免异步加载性能问题，进入用户详情页面就设置chat信息
    async setCurChatByUserId(userId: string) {
        await this.initSocialuniChatModule()
        ;(await this.openIm()).getOneConversation({
            sessionType: OpenImSessionType.Single,
            sourceID: userId
        }).then(res => {
            const data = res.data
            const openImChatRO: OpenImChatRO = JsonUtil.toParse(data)
            const chat = new SocialuniChatRO(openImChatRO)
            /*SocialuniUserAPI.queryUserDetailAPI(openImChatRO.userID).then(res => {
                const userRO: SocialuniUserRO = res.data
                chat.nickname = userRO.nickname
                chat.avatar = userRO.avatar
            })*/
            this.setChat(chat)
        })
        // 设置实时消息已读回执
        const options: MarkC2CParams = {
                userID: userId,
                msgIDList: []
            }
        ;(await socialChatModule.openIm()).markC2CMessageAsRead(options).then(({data}) => {
            console.log('markC2CMessageAsRead', data);
            this.computedChatsUnreadNumTotalAction()

        }).catch(err => {
        })
    }

    //为避免异步加载性能问题，进入用户详情页面就设置chat信息
    async setCurChatByGroupId(groupId: string) {
        await this.initSocialuniChatModule()
        ;(await this.openIm()).getOneConversation({
            sessionType: OpenImSessionType.Group,
            sourceID: groupId
        }).then(res => {
            const data = res.data
            const openImChatRO: OpenImChatRO = JsonUtil.toParse(data)
            const chat = new SocialuniChatRO(openImChatRO)
            /*SocialuniUserAPI.queryUserDetailAPI(openImChatRO.userID).then(res => {
                const userRO: SocialuniUserRO = res.data
                chat.nickname = userRO.nickname
                chat.avatar = userRO.avatar
            })*/
            this.setChat(chat)
        })
        // 设置实时消息已读回执
        const options: GroupMsgReadParams = {
            groupID: groupId,
            msgIDList: []
        }
        openIM.markGroupMessageAsRead(options).then(({data}) => {
            console.log('markGroupMessageAsRead', data);
            this.computedChatsUnreadNumTotalAction()
        }).catch(err => {
        })
    }


    async setChat(openImChat: SocialuniChatRO) {
        const options = {
                conversationID: openImChat.id,
                startClientMsgID: "",
                count: 100,
                groupID: "",
                userID: "",
            }
            // const user = new SocialuniUserRO()
            // user.id = openImChat.receiveUserId
            // user.avatar = openImChat.avatar
            // user.nickname = openImChat.nickname
            // user.isMine = false
        ;(await socialChatModule.openIm()).getHistoryMessageList(options).then(({data}) => {
            const msgs: OpenImMsgRO[] = JsonUtil.toParse(data)
            console.log(msgs)
            openImChat.messages = msgs.map(item => new MessageVO(null, item))
            this.chat = openImChat
            socialChatModule.scrollToMessagePageBottom()
        })
    }

    toMessagePageFromUserDetail(userId: string) {
        PageUtil.toMessagePageByUserId(userId)
        socialChatModule.scrollToMessagePageBottom()
    }

    toMessagePageFromGroupChat(groupId: string) {
        PageUtil.toMessagePageByGroupId(groupId)
        socialChatModule.scrollToMessagePageBottom()
    }


    /*get chatIndex(): number {
        return this.chats.findIndex(item => item.id === this.chatId)
    }*/

    get messages(): MessageVO[] {
        if (this.chat) {
            return this.chat.messages
        }
        return []
    }

    //从列表中进入，从个人详情页进入

    //从详情进入，查看列表中是否有与此人的chat？如何查看，
    //列表中进入，需要调用后台，更新时间。

    //从列表中进入
    setChatIdToMessagePage(userId: string) {
        // this.setChatId(chatId)
        // this.readChatAction(this.chat)
        PageUtil.toMessagePageByUserId(userId)
        this.scrollToMessagePageBottom()
    }

    /*setChatId(chatId: string) {
        this.chatId = chatId
    }*/


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
            // socialChatModule.replaceChat(this.chatIndex, res.data)
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
    /**
     * 初始化消息总未读数。
     * 应该在这里计算是否显示红点
     */
    async computedChatsUnreadNumTotalAction() {
        // 获取消息总未读。
        ;(await this.openIm()).getTotalUnreadMsgCount().then(({data}) => {
            // 获得好友申请
            let recvFriendApplicationLength = this.getRecvFriendApplicationList(0).length;
            console.log('---recvFriendApplicationLength------', recvFriendApplicationLength);
            if (recvFriendApplicationLength + data > 0) {
                this.showTabBarRedDot();
            } else {
                uni.hideTabBarRedDot({
                    index: 2
                })
            }
        }).catch(err => {
        })
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
            this.scrollTop = this.messages.length * 5000
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
            // this.pushMsgReplaceChat(this.chatIndex, newChat)
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

    /**
     * 发送视频消息。
     * @param data
     * @param extension
     * @param description
     */
    async pushVideoMessage(url: string) {
        const options: VideoMsgParams = {
            videoPath: "",
            duration: 60,
            videoType: "mp4",
            snapshotPath: "",
            videoUUID: UUIDUtil.getUUID(),
            videoUrl: url,
            videoSize: 1234,
            snapshotUUID: UUIDUtil.getUUID(),
            snapshotSize: 123,
            snapshotUrl: "",
            snapshotWidth: 124,
            snapshotHeight: 125
        };
        const msg: MessageVO = MessageVO.create("createVideoMessage", options);
        msg.content = '视频';
        await socialChatModule.pushMessageAction(msg)
    }

    /**
     * 发送图片消息。
     * @param data
     * @param extension
     * @param description
     */
    async pushImageMessage(url: string) {
        const baseInfo: PicBaseInfo = {
            uuid: UUIDUtil.getUUID(),
            type: "png",
            size: 12465,
            width: 1080,
            height: 720,
            url: url
        }
        const options: ImageMsgParams = {
            sourcePicture: baseInfo,
            bigPicture: baseInfo,
            snapshotPicture: baseInfo,
        }
        const msg: MessageVO = MessageVO.create("createImageMessage", options);
        msg.content = '图片';
        await socialChatModule.pushMessageAction(msg)
    }

    /**
     * 发送自定义消息。
     * @param data
     * @param extension
     * @param description
     */
    async pushCustomMessage(data: string, extension: string, description: string) {
        const options: CustomMsgParams = {
            data,
            extension,
            description
        }
        const msg: MessageVO = MessageVO.create("createCustomMessage", options);
        msg.content = description;
        await socialChatModule.pushMessageAction(msg)
    }

    //在聊天界面的时候，自己发送msg 本地添加msg

    async pushMessageAction(msg: MessageVO) {
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
        const {data} = await (await socialChatModule.openIm())[msg.action](msg.contentData);
        const params = {
            recvID: this.chat.receiveUserId,
            groupID: this.chat.groupId,
            message: data,
        };
        console.log('-------params-------', params);

        // const msgAdd: MessageAddVO = new MessageAddVO(chatId, content)
        // return request.post <T>('message/sendMsg', msgAdd)
        //
        let actionMethod: Function = null;
        switch (msg.action) {
            case 'createImageMessage':
            case 'createSoundMessage':
            case 'createVideoMessage':
            case 'createFileMessage':
                actionMethod = (await socialChatModule.openIm()).sendMessageNotOss;
                break;
            default:
                actionMethod = (await socialChatModule.openIm()).sendMessage;
                break;
        }
        // 执行方法。
        actionMethod(params).then((res) => {
            // 后台返回后再替换
            this.chat.updateTime = res.data.createTime
            this.messages.splice(index, 1, new MessageVO(null, JsonUtil.toParse(res.data)))
        }).catch((e) => {
            console.log(e)
            // 这里应该变为发送失败
            this.messages.splice(index, 1)
        }).finally(() => {
            console.log(666)
        })
        // socialChatModule.refreshMessages()

        PlatformUtils.requestSubscribeChat()
    }


    // 前台和后台都将chat和msg改为已读,更新chat的时间

    readChatAction(chat: SocialuniChatRO) {
        //目前不根据点击时间更新，只根据消息时间更新
        // chat.updateTime = new Date().getTime()
        // 不为自己的 且未读的
        const messages: MessageVO[] = chat.messages.filter(item => !item.isMine && !item.isRead)
        const msgIds: string[] = messages.map(msg => msg.id)
        // if (messages.length > 0) {
        // msgIds =
        //如果登录了，才调用后台
        // 如果登录了
        //目前 官方群聊没记录已读状态，读取也不管用
        // if (socialUserModule.hasUser && chat.type !== ChatType.system_group) {
        //     ChatAPI.readChatAPI(chat.id, msgIds)
        // }
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
        // this.computedChatsUnreadNumTotalAction()
    }


    //前台删除，需要增加后台删除逻辑
    deleteMsgAction(msgId: string) {
        this.messages.splice(this.messages.findIndex(msg => msg.id === msgId), 1)
    }


    deleteChatAction(chatId: string) {
        this.chats.splice(this.chats.findIndex(chat => chat.id === chatId), 1)
    }

    async joinCircleGroupChat(circle: SocialuniTalkTabCircleRO) {
        //如果已经创建群聊
        if (circle.groupChatId) {
            UniUtil.showLoading('加载中')
            const optionsSearch: SearchGroupParams = {
                keywordList: [circle.groupChatId],
                isSearchGroupID: true,
                isSearchGroupName: false
            }
            try {
                const res = await openIM.searchGroups(optionsSearch)
                const resData: any[] = JsonUtil.toParse(res.data)
                if (!resData.length) {
                    const options: JoinGroupParams = {
                        groupID: circle.groupChatId,
                        reqMsg: "",
                        joinSource: GroupJoinSource.Search
                    }
                    await (await this.openIm()).joinGroup(options)
                }
            } finally {
                UniUtil.hideLoading()
            }
            socialChatModule.toMessagePageFromGroupChat(circle.groupChatId)
        } else {
            UniUtil.showLoading('加载中')
            console.log(circle)
            //未创建则创建，并且更新话题的群聊id
            const groupBaseInfo: GroupInitInfo = {
                groupType: 0,
                groupName: circle.name, // 群名称
                introduction: "", // 群介绍
                notification: "",  // 群公告
                faceURL: circle.avatar,  //
                ex: ""   // 扩展字段
            }
            /**
             * 经过测试: 会自动添加群主。 roleLevel: 默认为2 。
             * 还有下面这个是邀请成员的 level为1 。  类似于微信的拉群聊。不需要同意就直接能拉进群聊。
             */
            const memberList: Member[] = [
                {
                    userID: socialConfigModule.appConfig.systemUserId,
                    roleLevel: 1
                }
            ];
            const options: CreateGroupParams = {
                groupBaseInfo,
                memberList
            }
            console.log(options)
            try {
                const res = await (await socialChatModule.openIm()).createGroup(options)
                const resData: { groupID: string } = JsonUtil.toParse(res.data)
                const cRes = await SocialuniCircleAPI.createCircleChatAPI(new CircleCreateChatQO(circle.name, resData.groupID))
                socialTalkModule.curTab.circle.groupChatId = cRes.data
                const optionsVerification: SetGroupVerificationParams = {
                        groupID: circle.groupChatId,
                        verification: 2,
                    }
                ;(await this.openIm()).setGroupVerification(optionsVerification)
                socialChatModule.toMessagePageFromGroupChat(cRes.data)
            } finally {
                UniUtil.hideLoading()
            }
        }
    }
}

/*
  pushMessagesAction (msgs: MessageVO[]) {

  }*/
/*

  pushChatAction (newChat: SocialuniChatRO) {
    this.chats.unshift(newChat)
  }*/

