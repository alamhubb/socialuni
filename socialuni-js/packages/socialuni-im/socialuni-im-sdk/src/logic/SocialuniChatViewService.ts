import {socialuniChatModule} from "socialuni-im-sdk/src/store/SocialuniChatModule";
import SocialuniChatRO from "socialuni-api-base/src/model/SocialuniChatRO";
import DateUtil from "qing-util/src/util/DateUtil";
import {watch} from "vue";
import {Vue} from "vue-class-component";
import SocialUserContentRO from "socialuni-api-base/src/model/social/SocialUserContentRO";
import ChatType from "socialuni-constant/constant/ChatType";
import Constants from "socialuni-constant/constant/Constant";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import ImPageUtil from "../util/ImPageUtil";
import SocialuniImUserAPI from "socialuni-im-api/src/api/SocialuniImUserAPI";
import SocialuniViewService from "socialuni/src/interface/SocialuniViewService";
import {Component, toNativeInternalInstance} from "@vue/runtime-core";

interface SocialuniChatViewServiceRefs {
    messageBox: HTMLDivElement;
}


export default class SocialuniChatViewService extends SocialuniViewService<SocialuniChatViewServiceRefs> {
    created() {
        /* onLoad((params) => {
             SocialuniAppUtil.UniUtil.showShareMenu()
         })
         onShow(() => {
             // socialuniChatModule.computedChatsUnreadNumTotalAction()
         })*/
        /*setInterval(()=>{
          this.$forceUpdate()
          console.log(123)
        },100)*/
        /*if (socialuniUserModule.mineUser) {
          SocialuniUserAPI.queryRecentlyUsersAPI().then(res => {
            this.users = res.data
          })
        }*/
    }

    users: SocialUserContentRO[] = []

    searchContent = null


    initService(instance: ComponentInternalInstance) {
        super.initService(instance)
        watch(() => socialuniChatModule.scrollTop, () => {
            if (this.$refs.messageBox) {
                this.$refs.messageBox.scrollTop = socialuniChatModule.scrollTop
            }
            // this.scrollTop = -1000
        })
    }

    scrollTop: number = 0

    formatTime(time: any) {
        return DateUtil.formatTime(time)
    }


    get chat(): SocialuniChatRO {
        if (socialuniChatModule.chat) {
            console.log(socialuniChatModule.chat.messages)
            console.log(socialuniChatModule.chat.messages.length)

            return socialuniChatModule.chat
        }
        return null
    }


    get chatList() {
        const chats = socialuniChatModule.chats
        return chats.filter(item => !this.searchContent || item.nickname.includes(this.searchContent))
    }


    get chats() {
        const chats = socialuniChatModule.chats
        return chats.filter(item => !this.searchContent || item.nickname.includes(this.searchContent))
    }


    get recvFriendApplication() {
        // return socialuniChatModule.getRecvFriendApplicationList(0).length
        return 0
    }

    // @chatStore.Getter('chatsUnreadNumTotal') readonly chatsUnreadNumTotal: number

    readonly systemChats: string[] = ChatType.systemChats
    chatId = null
    // readonly waitOpenStatus: string = SocialuniCommonStatus.waitOpen
    // readonly closeStatus: string = SocialuniCommonStatus.close
    // showChatHint: boolean = uni.getStorageSync(Constants.showChatHintKey) !== 'false'

    closeUploadImgHint() {
        this.showChatHint = false
        uni.setStorageSync(Constants.showChatHintKey, 'false')
    }



    onPullDownRefresh() {
        this.initQuery()
    }

    // 初始查询，会清空已有talk
    initQuery() {
        /*socialuniChatModule.getChatsAction().finally(() => {
          this.stopPullDownRefresh()
        })*/
    }

    stopPullDownRefresh() {
        uni.stopPullDownRefresh()
    }

    showBottomMenuClick(chatId: number) {
        this.chatId = chatId
        console.log('chatId===', chatId)
        QingAppUtil.NativeUtil.actionSheet(['置顶', '删除']).then((index: number) => {
            if (index === 0) {
                this.pinConversation()
            } else if (index === 1) {
                this.frontDeleteChat()
            } else if (index === 2) {  // '开启阅后即焚'
                this.setOneConversationPrivateChat()
            }
        }).catch(() => {
            this.chatId = null
        })
    }

    pinConversation() {
        // socialuniChatModule.pinConversation(this.chatId)
    }

    /**
     * '开启阅后即焚'
     */
    async setOneConversationPrivateChat() {
        let toUserId = '5c8d2cb04a774a7f8a4817996e380f29'
        let fromUserID = '768091f75a8c46688baa3c1137161c5f';

        (await socialuniChatModule.openIm()).clearC2CHistoryMessageFromLocalAndSvr(fromUserID).then(({data}) => {
            console.log('  clearC2CHistoryMessageFromLocalAndSvr  ', toUserId, data)
        }).catch(err => {
        })
    }

    async frontDeleteChat() {
        QingAppUtil.AlertUtil.confirm('是否确定从列表中删除会话，可从私信处再次找回').then(async () => {
            (await socialuniChatModule.openIm()).deleteConversationFromLocalAndSvr(this.chatId).then(({data}) => {
                socialuniChatModule.deleteChatAction(this.chatId)
            }).catch(err => {
            })
        })
    }

    get showChats(): SocialuniChatRO[] {
        if (this.chats && this.chats.length) {
            //a和b比较，返回结果1，则倒序，后者在前面
            return this.chats.sort((chat, chatAfter) => {
                //如果置顶优先级比较高，则排前面
                if (chatAfter.topLevel > chat.topLevel) {
                    return 1
                } else if (chatAfter.topFlag !== chat.topFlag) {
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
        } else {
            return []
        }
    }

    /*
    get showChats (): ChatVO[] {
      if (this.chats && this.chats.length) {
        this.chats.sort((x: ChatVO, y: ChatVO) => {
          // 如果都为置顶
          if (x.topFlag && y.topFlag) {
            // 比类型
            if (x.topLevel === y.topLevel) {
              // 比更新时间
              return new Date(y.updateTime).getTime() - new Date(x.updateTime).getTime()
            } else {
              return y.topLevel - x.topLevel
            }
          } else {
            if (x.topFlag) {
              return -1
            } else if (y.topFlag) {
              return 1
            } else {
              if (x.topLevel === y.topLevel) {
                return new Date(y.updateTime).getTime() - new Date(x.updateTime).getTime()
              } else {
                return y.topLevel - x.topLevel
              }
            }
          }
        })
        return this.chats
      } else {
        return []
      }
    }*/

    toMessagePage(chat: SocialuniChatRO) {
        //需要先清除，再跳转页面
        //进入页面需要查询，不查询则不显示
        if (chat.receiveUserId) {
            return socialuniChatModule.setChatIdToMessagePage(chat.receiveUserId)
        } else {
            return socialuniChatModule.setChatIdToMessagePage(chat.id)
        }
    }

    toAddFriend() {
        ImPageUtil.toChatFriend()
    }

    clearSearchContent() {
        this.searchContent = ''
    }

    toUserApplyPage() {
        ImPageUtil.toFriendApply()
    }

    openUserChatSetting() {
        let msg = []
        if (socialuniChatModule.imMineUserInfo.allowStrangerMsg) {
            msg = ['关闭陌生人免费消息']
        } else {
            msg = ['打开陌生人免费消息']
        }
        QingAppUtil.NativeUtil.actionSheet(msg).then(res => {
            if (res === 0) {
                if (socialuniChatModule.imMineUserInfo.allowStrangerMsg) {
                    socialuniChatModule.imMineUserInfo.allowStrangerMsg = false
                    QingAppUtil.ToastUtil.toast('关闭陌生人免费消息成功')
                    SocialuniImUserAPI.closeStrangerMsg().then(res => {
                        socialuniChatModule.imMineUserInfo = res.data
                    })
                } else {
                    socialuniChatModule.imMineUserInfo.allowStrangerMsg = true
                    QingAppUtil.ToastUtil.toast('打开陌生人免费消息成功')
                    SocialuniImUserAPI.openStrangerMsg().then(res => {
                        socialuniChatModule.imMineUserInfo = res.data
                    })
                }
            }
            console.log(res)
        })
    }
}

export const socialuniChatViewService = new SocialuniChatViewService()
