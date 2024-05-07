<template>
  <div class="bg-default h100p">
    <q-navbar title="消息">
      <div class="row-end-center flex-1 pl">
        <q-search class="w100p mx-sm bg-white">
          <q-icon class="mx-xs text-gray" icon="search" size="16"></q-icon>
          <input class="flex-1" v-model="viewService.searchContent" :adjust-position="false" type="text"/>
          <q-icon v-if="viewService.searchContent" class="mr text-gray row-all-center" icon="close" size="16"
                  @click="viewService.clearSearchContent"
          ></q-icon>
        </q-search>
        <view @click="viewService.toAddFriend()">
          <q-icon icon="list-dot" size="20" @click="viewService.openUserChatSetting()"></q-icon>
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
        <uni-list-item title="好友申请" link @click="viewService.toUserApplyPage()"
                       :show-badge="viewService.recvFriendApplication != 0"
                       :badge-text="viewService.recvFriendApplication"></uni-list-item>
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
      <view v-for="chat in viewService.chatList" :key="chat.id" class="cu-item" @click="viewService.toMessagePage(chat)"
            @longpress="viewService.showBottomMenuClick(chat.id)">
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
                  <div v-if="chat.messages[chat.messages.length - 1].contentType === '图片'">
                    [图片]
                  </div>
                  <div v-else>
                    {{ chat.messages[chat.messages.length - 1].content }}
                  </div>
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
          <view class="text-grey text-xs">{{ viewService.formatTime(chat.updateTime) }}</view>
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
import {Component, Vue, toNative} from 'vue-facing-decorator'
import SocialUserContentRO from "socialuni-api-base/src/model/social/SocialUserContentRO"
import QNavbar from "qing-ui-uni/src/components/QNavbar/QNavbar.vue"
import QIcon from "qing-ui-uni/src/components/QIcon/QIcon.vue"
import QSearch from "qing-ui-uni/src/components/QSearch/QSearch.vue"
import QInput from "qing-ui-uni/src/components/QInput/QInput.vue"
import {
  socialuniChatViewService
} from "socialuni-im-sdk/src/logic/SocialuniChatViewService";
import {getCurrentInstance} from "vue";

@toNative
@Component({
  components: {QSearch, QInput, QIcon, QNavbar}
})
export default class SocialuniChatView extends Vue {

  viewService = socialuniChatViewService

  created() {
    this.viewService.initService(getCurrentInstance())
  }
}
</script>
