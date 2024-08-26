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
        <div @click="viewService.toAddFriend()">
          <q-icon icon="list-dot" size="20" @click="viewService.openUserChatSetting()"></q-icon>
        </div>
      </div>
    </q-navbar>
    <!--    <div v-if="showChatHint&& showChats && showChats.length" class="row-col-center bg-orange">
          <div class="flex-1 card-text-row">
            长按聊天框可解除匹配
          </div>
          <div class="flex-none mr-10px">
            <q-icon icon="close-circle-fill" size="18" @click="closeUploadImgHint"></q-icon>
          </div>
        </div>-->

    <!--    <div>
          <uni-list>
            <uni-list-item title="好友申请" link @click="viewService.toUserApplyPage()"
                           :show-badge="viewService.recvFriendApplication != 0"
                           :badge-text="viewService.recvFriendApplication"></uni-list-item>
            &lt;!&ndash;        <uni-list-item title="发出的好友" link to="/pages/chat/friend?type=sendFriendApplication"></uni-list-item>
                    <uni-list-item title="新朋友" link to="/pages/chat/friend?type=recvFriendApplication"></uni-list-item>
                    <uni-list-item title="黑名单" link to="/pages/chat/friend?type=black"></uni-list-item>
                    <uni-list-item title="通讯录" link to="/pages/chat/friend?type=friend"></uni-list-item>
                    <uni-list-item title="群聊" link to="/pages/chat/group?type=friend"></uni-list-item>&ndash;&gt;
          </uni-list>
        </div>-->

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
    <!--    <div v-show="false">
          {{ chatsUnreadNumTotal }}
        </div>-->
    <div class="cu-list menu-avatar pb-50px bt">
      <q-row-item class="bg-white bb">
          通知
        <div v-if="viewService.unreadNotifyNum">
          （{{ viewService.unreadNotifyNum }}）{{ viewService.lastUnreadNotify.content}}
        </div>
<!--        <div v-show="true" class="ml-xs bd-round bg-red3 size10 row-all-center color-white font-12">
          {{ viewService.unreadNotifyNum }}
        </div>-->
      </q-row-item>
      <div v-for="chat in viewService.chatList" :key="chat.id" class="cu-item" @click="viewService.toMessagePage(chat)"
           @longpress="viewService.showBottomMenuClick(chat.id)">
        <img class="cu-avatar radius lg" :src="chat.avatar"/>
        <div class="content h45 col-between">
          <div>
            <div class="text-cut text-df text-black" :class="{'color-red':chat.vipFlag}">{{
                chat.nickname
              }}
            </div>
            <!--            <div v-if="systemChats.indexOf(chat.type)>-1"
                              class="cu-tag round bg-blue sm text-sm col-center text-bold">官方
                        </div>
                        <div v-else-if="chat.vipFlag" class="cu-tag round bg-red sm text-sm col-center text-bold">VIP
                        </div>-->
          </div>
          <div>
            <div class="color-content text-sm flex">
              <div class="text-cut text-sm">
                <div v-if="chat.messages.length">
                  <div v-if="chat.messages[chat.messages.length - 1].contentType === '图片'">
                    [图片]
                  </div>
                  <div v-else>
                    {{ chat.messages[chat.messages.length - 1].content }}
                  </div>
                </div>
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

              </div>
            </div>
          </div>
        </div>
        <div class="action h45 col-between-center">
          <div class="color-content font-12">{{ viewService.formatTime(chat.updateTime) }}</div>
          <div v-show="chat.unreadNum>0" class="bd-round bg-red4 size16 row-all-center color-white font-12">
            {{ chat.unreadNum }}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import SocialUserContentRO from "socialuni-api-base/src/model/social/SocialUserContentRO"
import QNavbar from "qingjs-ui-uni/src/components/QNavbar/QNavbar.vue"
import QIcon from "qingjs-ui/src/components/QIcon.vue"
import QSearch from "qingjs-ui-uni/src/components/QSearch/QSearch.vue"
import QInput from "qingjs-ui/src/components/QInput.vue"
import QRowItem from "qingjs-ui/src/components/QRowItem.vue"
import {
  socialuniChatViewService
} from "socialuni-im-sdk/src/logic/SocialuniChatViewService";
import {getCurrentInstance} from "vue";

@toNative
@Component({
  components: {QSearch, QInput, QIcon, QNavbar, QRowItem}
})
export default class SocialuniChatView extends Vue {

  viewService = socialuniChatViewService

  created() {
    this.viewService.initService(this)
  }
}
</script>
