<template>
    <view class="bg-white">
        <scroll-view scroll-x class="bg-white nav text-center">
            <view class="cu-item" :class="follow===followPageType?'text-blue cur':''"
                  @tap="switchHomeType" :data-id="follow">
                关注
            </view>
            <view class="cu-item" :class="fans===followPageType?'text-blue cur':''"
                  @tap="switchHomeType" :data-id="fans">
                粉丝
            </view>
        </scroll-view>
        <view class="mt-10px">
            <follow-item v-for="user in showUsers" :user="user" :key="user.id"/>
        </view>
    </view>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import CenterUserDetailRO from '../../model/social/CenterUserDetailRO'
import HomeType from '@/const/HomeType'
import FollowItem from '@/pagesLazy/user/FollowItem.vue'
import FollowAPI from '/socialuni/api/FollowAPI'

@Component({
  components: { FollowItem }
})
export default class FollowPage extends Vue {
    followPageType: string = HomeType.follow
    follow: string = HomeType.follow
    fans: string = HomeType.fans
    followUsers: CenterUserDetailRO[] = []
    fansUsers: CenterUserDetailRO[] = []

    onLoad () {
      uni.startPullDownRefresh({})
    }

    queryUserFollows () {
      FollowAPI.queryUserFollowsAPI().then((res: any) => {
        this.followUsers = res.data.follows
        this.fansUsers = res.data.fans
      }).finally(() => {
        uni.stopPullDownRefresh()
      })
    }

    get showUsers (): CenterUserDetailRO[] {
      if (this.followPageType === this.fans) {
        return this.fansUsers
      } else {
        return this.followUsers
      }
    }

    onPullDownRefresh () {
      this.queryUserFollows()
    }

    switchHomeType (e) {
      this.followPageType = e.currentTarget.dataset.id
      this.queryUserFollows()
    }
}
</script>
