<template>
  <view class="bg-default">
    <user-info :user.sync="user">
      <template v-slot:list>
        <view class="row-between-center py-xs pr-xs" >
          <view class="flex-row flex-1" :class="{'row-around':isMine}">
            <view v-if="user.hasFriend" class="px-lg line-height-1" @click.stop="deleteFriend">
              <text class="text-sm text-gray">解除好友</text>
            </view>
            <view v-else class="px-lg line-height-1" @click.stop="addFriend">
              <text class="text-sm text-gray">添加好友</text>
            </view>
            <view class="px-lg line-height-1" @click.stop="addBlack">
              <text class="text-sm text-gray">添加黑名单</text>
            </view>
          </view>
        </view>
      </template>
    </user-info>
    <msg-input v-if="showMsgInput">
    </msg-input>
  </view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import CenterUserDetailRO from "socialuni-api/src/model/social/CenterUserDetailRO";
import UniUtil from "socialuni-sdk/src/utils/UniUtil";
import MsgInput from "socialuni-view/src/components/MsgInput.vue";
import {onHide, onLoad, onShow} from "@dcloudio/uni-app";
import SocialuniUserAPI from "socialuni-api/src/api/socialuni/SocialuniUserAPI";
import UserInfo from "socialuni-sdk/src/views/user/UserInfo.vue";
import {socialChatModule} from "socialuni-sdk/src/store/store";

@Options({
  components: {MsgInput, UserInfo}
})
export default class UserDetail extends Vue {
  user: CenterUserDetailRO = null
  showMsgInput = false

  created() {
    UniUtil.showShareMenu()

    onShow(() => {
      this.showMsgInput = true
    })

    onHide(() => {
      this.showMsgInput = false
    })


    onLoad((params) => {
      const userId = params.userId
      // 这里有问题，有时候直接进入页面没有userId
      SocialuniUserAPI.queryUserDetailAPI(userId).then((res: any) => {
        this.user = res.data
        if (!this.user.isMine){
          socialChatModule.checkFriend(this.user);
          socialChatModule.setCurChatByUserId(this.user.id)
        }
      })
    })
  }
}
</script>
