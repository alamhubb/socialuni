<template>
  <view class="bg-default">
    <user-info :user.sync="user"></user-info>
    <msg-input v-if="showMsgInput">
    </msg-input>
  </view>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator'
import CenterUserDetailRO from '@/socialuni/model/social/CenterUserDetailRO'
import SocialuniUserAPI from '@/socialuni/api/socialuni/SocialuniUserAPI'
import MsgInput from '@/components/MsgInput.vue'
import UserInfo from '@/pages/user/UserInfo.vue'

@Component({
  components: {MsgInput, UserInfo}
})
export default class UserDetail extends Vue {
  user: CenterUserDetailRO = null
  showMsgInput = false

  onShow() {
    this.showMsgInput = true
  }

  onHide() {
    this.showMsgInput = false
  }

  onLoad(params) {
    const userId = params.userId
    // 这里有问题，有时候直接进入页面没有userId
    SocialuniUserAPI.queryUserDetailAPI(userId).then((res: any) => {
      this.user = res.data
    })
  }
}
</script>
