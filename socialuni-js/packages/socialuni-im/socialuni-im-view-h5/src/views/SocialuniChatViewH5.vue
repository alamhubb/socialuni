<template>
  <div class="h100p">
    <s-scrollbar class="h100p">

      <div>
        <el-input></el-input>
        <el-icon>
          <CirclePlus />
        </el-icon>
      </div>
<!--      <div>
        <div></div>
        <div>
          <el-button @click="">创建</el-button>
        </div>
      </div>-->
      <div v-for="chat in viewService.chatList" :key="chat.id" class="flex-row pd-sm bb use-click" :class="{'bg-white': chat.id === viewService?.chat?.id}"
           @click="viewService.toMessagePage(chat)"
           @contextmenu.prevent="viewService.showBottomMenuClick(chat.id)"
           @longpress="viewService.showBottomMenuClick(chat.id)"
      >
        <el-avatar shape="square" :src="chat.avatar"></el-avatar>
        <div class="ml-sm h45 col-between flex-1">
          <div>
            <div class="text-cut text-df color-black" :class="{'color-red':chat.vipFlag}">{{
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

              </div>
            </div>
          </div>
        </div>
        <div class="h45 col-between">
          <template v-if="chat.messages.length">

            <div class="text-grey text-xs">    {{ viewService.formatTime(chat.messages[chat.messages.length - 1].createTime) }}</div>
          </template>
          <template v-else>
            <div class="text-grey text-xs">{{ viewService.formatTime(chat.updateTime) }}</div>
          </template>

          <div class="row-col-center">
            <div v-show="chat.unreadNum>0" class="bg-red4 size17 row-all-center font-12 bd-round mb-5 color-white">
              {{ chat.unreadNum }}
            </div>
          </div>
        </div>
      </div>
    </s-scrollbar>
    <!--        <q-navbar title="消息">
                <div class="row-end-center flex-1 pl">
                    <q-search class="w100p mx-sm bg-white">
                        <q-icon class="mx-xs text-gray" icon="search" size="16"></q-icon>
                        <input class="flex-1" v-model="searchContent" :adjust-position="false" type="text"/>
                        <q-icon v-if="searchContent" class="mr text-gray row-all-center" icon="close" size="16"
                                @click="clearSearchContent"
                        ></q-icon>
                    </q-search>
                    <div @click="toAddFriend">
                        <q-icon icon="list-dot" size="20" @click="openUserChatSetting"></q-icon>
                    </div>
                </div>
            </q-navbar>-->
    <!--    <div v-if="showChatHint&& showChats && showChats.length" class="row-col-center bg-orange">
          <div class="flex-1 card-text-row">
            长按聊天框可解除匹配
          </div>
          <div class="flex-none mr-10px">
            <q-icon icon="close-circle-fill" size="18" @click="closeUploadImgHint"></q-icon>
          </div>
        </div>-->

    <!--        <div>
                <div>
                    <div title="好友申请" link @click="toUserApplyPage" :show-badge="recvFriendApplication != 0"
                                   :badge-text="recvFriendApplication"></div>
                    &lt;!&ndash;        <uni-list-item title="发出的好友" link to="/pages/chat/friend?type=sendFriendApplication"></uni-list-item>
                            <uni-list-item title="新朋友" link to="/pages/chat/friend?type=recvFriendApplication"></uni-list-item>
                            <uni-list-item title="黑名单" link to="/pages/chat/friend?type=black"></uni-list-item>
                            <uni-list-item title="通讯录" link to="/pages/chat/friend?type=friend"></uni-list-item>
                            <uni-list-item title="群聊" link to="/pages/chat/group?type=friend"></uni-list-item>&ndash;&gt;
                </div>
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
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import SScrollbar from "qingjs-ui-h5/src/components/QScrollbar.vue";
import SocialuniChatViewServiceH5 from "socialuni-im-h5-sdk/src/logic/SocialuniChatViewServiceH5";

@toNative
@Component({components: {SScrollbar}})
export default class SocialuniChatViewH5 extends Vue {

  viewService: SocialuniChatViewServiceH5 = new SocialuniChatViewServiceH5()

}
</script>
