<template>
  <view class="bg-white">
    <scroll-view scroll-x class="bg-white nav text-center">
      <view class="cu-item" :class="SocialuniFollowType.follow===followPageType?'text-blue cur':''"
            @click="switchHomeType(SocialuniFollowType.follow)">
        关注
      </view>
      <view class="cu-item" :class="SocialuniFollowType.fans===followPageType?'text-blue cur':''"
            @tap="switchHomeType(SocialuniFollowType.fans)">
        粉丝
      </view>
    </scroll-view>
    <view class="mt-10px">
      <socialuni-follow-item  v-for="user in showUsers" :user="user" :key="user.id"></socialuni-follow-item>
    </view>
  </view>
</template>

<script lang="ts">
import {Vue, Options} from 'vue-property-decorator'
import SocialuniFollowItem from './SocialuniFollowItem'
import SocialuniFollowType from "socialuni-constant/constant/user/SocialuniFollowType";
import SocialUserContentRO from "socialuni-api/src/model/social/SocialUserContentRO";
import FollowAPI from "socialuni-api/src/api/socialuni/FollowAPI";


@Options({
  components: {SocialuniFollowItem}
})
export default class FollowView extends Vue {
  followPageType: string = SocialuniFollowType.follow
  SocialuniFollowType = SocialuniFollowType
  followUsers: SocialUserContentRO[] = []
  fansUsers: SocialUserContentRO[] = []

  onLoad() {
    uni.startPullDownRefresh()
  }

  queryUserFollows() {
    FollowAPI.queryUserFollowsAPI().then((res: any) => {
      this.followUsers = res.data.follows
      this.fansUsers = res.data.fans
    }).finally(() => {
      uni.stopPullDownRefresh()
    })
  }

  get showUsers(): SocialUserContentRO[] {
    if (this.followPageType === SocialuniFollowType.fans) {
      return this.fansUsers
    } else {
      return this.followUsers
    }
  }

  onPullDownRefresh() {
    this.queryUserFollows()
  }

  switchHomeType(e) {
    this.followPageType = e.currentTarget.dataset.id
    this.queryUserFollows()
  }
}
</script>
