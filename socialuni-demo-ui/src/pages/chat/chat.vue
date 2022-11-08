<template>
  <view class="bg-default h100p">
    <!--<q-bar>
        <view>
            <q-icon icon="mdi-volume-high"></q-icon>
            系统通知
        </view>
        <view>
            <q-icon icon="arrow-right"></q-icon>
        </view>
    </q-bar>-->
    <view v-if="showChatHint&& showChats && showChats.length" class="row-col-center bg-orange">
      <view class="flex-auto card-text-row">
        长按聊天框可解除匹配
      </view>
      <view class="flex-none mr-10px">
        <q-icon icon="close-circle-fill" size="18" @click="closeUploadImgHint"></q-icon>
      </view>
    </view>
    <!--    <view v-show="false">
          {{ chatsUnreadNumTotal }}
        </view>-->
    <view class="cu-list menu-avatar pb-50px">
      <view v-for="chat in showChats" :key="chat.id" class="cu-item" @click="toMessagePage(chat)"
            @longpress="showBottomMenuClick(chat.id)">
        <image class="cu-avatar radius lg" :src="chat.avatar" />
        <view class="content h45px col-between">
          <view>
            <view class="text-cut text-df text-black" :class="{'color-red':chat.vipFlag}">{{
                chat.nickname
              }}
            </view>
            <view v-if="systemChats.indexOf(chat.type)>-1"
                  class="cu-tag round bg-blue sm text-sm col-center text-bold">官方
            </view>
            <view v-else-if="chat.vipFlag" class="cu-tag round bg-red sm text-sm col-center text-bold">VIP
            </view>
          </view>
          <view>
            <view class="text-gray text-sm flex">
              <view class="text-cut text-sm">
                <template v-if="chat.status === waitOpenStatus">
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
                </template>
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
  </view>
</template>

<script lang="ts">
import ChatType from 'socialuni-constant/constant/ChatType'
import CommonStatus from 'socialuni-constant/constant/CommonStatus'
import { Options, Vue } from 'vue-property-decorator'
import Constants from 'socialuni-constant/constant/Constant'
import ChatAPI from 'socialuni-api/src/api/ChatAPI'
import { socialChatModule } from "socialuni-im/src/store/store"
import DateUtil from "socialuni-use/src/utils/DateUtil";
import UniUtil from "socialuni-common/src/utils/UniUtil";
import AlertUtil from "socialuni-use/src/utils/AlertUtil";
import ToastUtil from "socialuni-use/src/utils/ToastUtil";
import {onLoad} from "@dcloudio/uni-app";

@Options({})
export default class ChatView extends Vue {

  get chats() {
    return socialChatModule.chats
  }

  // @chatStore.Getter('chatsUnreadNumTotal') readonly chatsUnreadNumTotal: number

  readonly systemChats: string[] = ChatType.systemChats
  chatId = 0
  readonly waitOpenStatus: string = CommonStatus.waitOpen
  readonly closeStatus: string = CommonStatus.close
  showChatHint: boolean = uni.getStorageSync(Constants.showChatHintKey) !== 'false'

  closeUploadImgHint() {
    this.showChatHint = false
    uni.setStorageSync(Constants.showChatHintKey, 'false')
  }

  formatTime(dateStr) {
    return DateUtil.formatTime(dateStr)
  }

  created(){
    onLoad((params) => {
      console.log(123)
      UniUtil.showShareMenu()
    })
  }

  onPullDownRefresh() {
    this.initQuery()
  }

  // 初始查询，会清空已有talk
  initQuery() {
    /*socialChatModule.getChatsAction().finally(() => {
      this.stopPullDownRefresh()
    })*/
  }

  stopPullDownRefresh() {
    uni.stopPullDownRefresh()
  }

  showBottomMenuClick(chatId: number) {
    this.chatId = chatId
    UniUtil.actionSheet(['关闭会话', '列表中删除']).then((index: number) => {
      if (index === 0) {
        this.closeChat()
      } else if (index === 1) {
        this.frontDeleteChat()
      }
    }).catch(() => {
      this.chatId = null
    })
  }

  closeChat() {
    AlertUtil.confirm('是否确定关闭会话，对方将无法再给您发送消息').then(() => {
      ChatAPI.closeChatAPI(this.chatId).then(() => {
        socialChatModule.deleteChatAction(this.chatId)
        ToastUtil.toast('已关闭')
      }).finally(() => {
        this.chatId = null
      })
    })
  }

  frontDeleteChat() {
    AlertUtil.confirm('是否确定从列表中删除会话，可从私信处再次找回').then(() => {
      ChatAPI.frontDeleteChatAPI(this.chatId).then(() => {
        socialChatModule.deleteChatAction(this.chatId)
      }).finally(() => {
        this.chatId = null
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
    socialChatModule.setChatIdToMessagePage(chat.id)
  }
}
</script>
