<template>
  <view class="bg-default">
    <user-info :user.sync="user"></user-info>
    <msg-input>
    </msg-input>
  </view>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import TalkItem from '../SocialTalk/TalkItem.vue'
import CenterUserDetailRO from '../../model/social/CenterUserDetailRO'
import UserAPI from '../../api/UserAPI'
import TalkItemContent from '../SocialTalk/TalkItemContent.vue'
import MsgInput from '../MsgInput.vue'
import UserInfo from '../SocialUser/UserInfo.vue'

@Component({
  components: { MsgInput, UserInfo, TalkItem, TalkItemContent }
})
export default class UserDetail extends Vue {
  user: CenterUserDetailRO = null

  onLoad (params: any) {
    // 这里有问题，有时候直接进入页面没有userId
    UserAPI.queryUserDetailAPI(params.userId).then((res: any) => {
      this.user = res.data
    })
  }
}
</script>
