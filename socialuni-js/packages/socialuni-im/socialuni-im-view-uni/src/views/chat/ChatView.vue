<template>
    <div class="bg-default h100p">
        <q-navbar title="消息">
            <div class="row-end-center flex-1 pl">
                <q-search class="w100p mx-sm bg-white">
                    <q-icon class="mx-xs text-gray" icon="search" size="16"></q-icon>
                    <input class="flex-1" v-model="searchContent" :adjust-position="false" type="text"/>
                    <q-icon v-if="searchContent" class="mr text-gray row-all-center" icon="close" size="16"
                            @click="clearSearchContent"
                    ></q-icon>
                </q-search>
                <view @click="toAddFriend">
                    <q-icon icon="list-dot" size="20" @click="openUserChatSetting"></q-icon>
                </view>
            </div>
        </q-navbar>
        <!--    <view v-if="showChatHint&& showChats && showChats.length" class="row-col-center bg-orange">
              <view class="flex-1 card-text-row">
                长按聊天框可解除匹配
              </view>
              <view class="flex-none mr-10px">
                <q-icon icon="close-circle-fill" size="18" @click="closeUploadImgHint"></q-icon>
              </view>
            </view>-->

        <div>
            <uni-list>
                <uni-list-item title="好友申请" link @click="toUserApplyPage" :show-badge="recvFriendApplication != 0"
                               :badge-text="recvFriendApplication"></uni-list-item>
                <!--        <uni-list-item title="发出的好友" link to="/pages/chat/friend?type=sendFriendApplication"></uni-list-item>
                        <uni-list-item title="新朋友" link to="/pages/chat/friend?type=recvFriendApplication"></uni-list-item>
                        <uni-list-item title="黑名单" link to="/pages/chat/friend?type=black"></uni-list-item>
                        <uni-list-item title="通讯录" link to="/pages/chat/friend?type=friend"></uni-list-item>
                        <uni-list-item title="群聊" link to="/pages/chat/group?type=friend"></uni-list-item>-->
            </uni-list>
        </div>

        <!--    <div>
              <div v-for="item in users">
                <div class="flex-row px mb bd-radius bg-white">
                  <div>
                    <img class="size50" :src="item.avatar">
                  </div>
                  <div>
                    {{ item.nickname }}
                  </div>
                </div>
              </div>
            </div>-->
        <!--    <view v-show="false">
              {{ chatsUnreadNumTotal }}
            </view>-->
        <view class="cu-list menu-avatar pb-50px">
            <view v-for="chat in chatList" :key="chat.id" class="cu-item" @click="toMessagePage(chat)"
                  @longpress="showBottomMenuClick(chat.id)">
                <image class="cu-avatar radius lg" :src="chat.avatar"/>
                <view class="content h45 col-between">
                    <view>
                        <view class="text-cut text-df text-black" :class="{'color-red':chat.vipFlag}">{{
                            chat.nickname
                            }}
                        </view>
                        <!--            <view v-if="systemChats.indexOf(chat.type)>-1"
                                          class="cu-tag round bg-blue sm text-sm col-center text-bold">官方
                                    </view>
                                    <view v-else-if="chat.vipFlag" class="cu-tag round bg-red sm text-sm col-center text-bold">VIP
                                    </view>-->
                    </view>
                    <view>
                        <view class="color-content text-sm flex">
                            <view class="text-cut text-sm">
                                <template v-if="chat.messages.length">
                                    {{ chat.messages[chat.messages.length - 1].content }}
                                </template>
                                <template v-else>
                                    {{ chat.lastContent }}
                                </template>
                                <!--                <template v-if="chat.status === waitOpenStatus">
                                                  会话待开启
                                                </template>
                                                <template v-else-if="chat.status === closeStatus">
                                                  会话已关闭
                                                </template>
                                                <template v-else-if="chat.messages.length">
                                                  {{ chat.messages[chat.messages.length - 1].content }}
                                                </template>
                                                <template v-else>
                                                  会话已开启
                                                </template>-->

                            </view>
                        </view>
                    </view>
                </view>
                <view class="action h45px col-between">
                    <view class="text-grey text-xs">{{ formatTime(chat.updateTime) }}</view>
                    <view>
                        <view v-show="chat.unreadNum>0" class="cu-tag round bg-red sm">
                            {{ chat.unreadNum }}
                        </view>
                    </view>
                </view>
            </view>
        </view>
    </div>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import SocialUserContentRO from "socialuni-api-base/src/model/social/SocialUserContentRO"
import QNavbar from "socialuni-ui-uni/src/components/QNavbar/QNavbar.vue"
import QIcon from "socialuni-ui-uni/src/components/QIcon/QIcon.vue"
import QSearch from "socialuni-ui-uni/src/components/QSearch/QSearch.vue"
import QInput from "socialuni-ui-uni/src/components/QInput/QInput.vue"
import SocialuniImMineUserDetailRO from "socialuni-im-api/src/model/RO/SocialuniImMineUserDetailRO";
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";
import SocialuniImUserAPI from "socialuni-im-api/src/api/SocialuniImUserAPI";
import SocialuniChatRO from "socialuni-api-base/src/model/SocialuniChatRO";
import {socialuniChatModule} from "socialuni-im-sdk/src/store/SocialuniChatModule";
import ChatType from "socialuni-constant/constant/ChatType";
import SocialuniCommonStatus from "socialuni-constant/constant/status/SocialuniCommonStatus";
import Constants from "socialuni-constant/constant/Constant";
import DateUtil from "socialuni-util/src/util/DateUtil";
import {onLoad, onShow} from "@dcloudio/uni-app";
import ImPageUtil from "socialuni-im-sdk/src/util/ImPageUtil";

@Options({
    components: {QSearch, QInput, QIcon, QNavbar}
})
export default class ChatView extends Vue {
    users: SocialUserContentRO[] = []

    searchContent = null

    get chats() {
        const chats = socialuniChatModule.chats
        return chats.filter(item => !this.searchContent || item.nickname.includes(this.searchContent))
    }

    get chatList() {
        console.log(socialuniChatModule.chats)
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
    readonly waitOpenStatus: string = SocialuniCommonStatus.waitOpen
    readonly closeStatus: string = SocialuniCommonStatus.close
    showChatHint: boolean = uni.getStorageSync(Constants.showChatHintKey) !== 'false'

    closeUploadImgHint() {
        this.showChatHint = false
        uni.setStorageSync(Constants.showChatHintKey, 'false')
    }

    formatTime(dateStr) {
        return DateUtil.formatTime(dateStr)
    }

    created() {
        onLoad((params) => {
            SocialuniAppUtil.UniUtil.showShareMenu()
        })
        onShow(() => {
            // socialuniChatModule.computedChatsUnreadNumTotalAction()
        })
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
        SocialuniAppUtil.UniUtil.actionSheet(['置顶', '删除']).then((index: number) => {
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
        SocialuniAppUtil.AlertUtil.confirm('是否确定从列表中删除会话，可从私信处再次找回').then(async () => {
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
            socialuniChatModule.setChatIdToMessagePage(chat.receiveUserId)
        } else {
            socialuniChatModule.setChatIdToMessagePage(chat.id)
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
        SocialuniAppUtil.UniUtil.actionSheet(msg).then(res => {
            if (res === 0) {
                if (socialuniChatModule.imMineUserInfo.allowStrangerMsg) {
                    socialuniChatModule.imMineUserInfo.allowStrangerMsg = false
                    SocialuniAppUtil.ToastUtil.toast('关闭陌生人免费消息成功')
                    SocialuniImUserAPI.closeStrangerMsg().then(res => {
                        socialuniChatModule.imMineUserInfo = res.data
                    })
                } else {
                    socialuniChatModule.imMineUserInfo.allowStrangerMsg = true
                    SocialuniAppUtil.ToastUtil.toast('打开陌生人免费消息成功')
                    SocialuniImUserAPI.openStrangerMsg().then(res => {
                        socialuniChatModule.imMineUserInfo = res.data
                    })
                }
            }
            console.log(res)
        })
    }
}
</script>
