<template>
  <view class="card-title pb-10px" @click="toUserDetailVue">
    <image
      class="card-title-avatar"
      mode="aspectFill"
      :src="user.avatar"
    />
    <view class="flex-auto row-between">
      <view class="card-title-content">
        <view class="row-col-center">
          <view>
            <text :class="{'color-red':user.vipFlag}">{{ user.nickname }}</text>
            <view v-if="user.vipFlag" class="ml-5px cu-tag bg-orange radius sm"
                  @click.stop="openVip">
              VIP
            </view>
            <q-icon
              size="24"
              class="ml-5px"
              :icon="getGenderIcon(user)"
              :class="[getGenderColor(user)]"
            />
          </view>
        </view>
        <view class="text-gray">
          粉丝：{{ user.fansNum }}
        </view>
      </view>
      <view class="col-center">
        <button class="cu-btn round px-15px bg-white"
                :class="'bd-'+getFollowStatusColor(followStatus)"
                @click.stop="followHandler">
          <text>{{ followStatus }}</text>
        </button>
      </view>
    </view>
  </view>
</template>

<script lang="ts">
import { Component, Emit, Prop, Vue } from 'vue-property-decorator'
import CenterUserDetailRO from '../../model/social/CenterUserDetailRO'
import FollowAPI from '/socialuni/api/FollowAPI'
import UserUtil from '@/utils/UserUtil'
import FollowStatus from '@/const/FollowStatus'
import FollowAddVO from '@/model/FollowAddVO'
import PagePath from '@/const/PagePath'
import PageUtil from '@/utils/PageUtil'
import RouterUtil from '@/utils/RouterUtil'

@Component
export default class FollowItem extends Vue {
  @Prop() user: CenterUserDetailRO
  followBtnDisabled = false
  followStatus = ''

  created () {
    this.followStatus = FollowStatus.getFollowStatus(this.user)
  }

  @Emit()
  change (user: CenterUserDetailRO) {
    return user
  }

  getGenderIcon (user: CenterUserDetailRO) {
    return UserUtil.getGenderIcon(user)
  }

  getGenderColor (user: CenterUserDetailRO) {
    return UserUtil.getGenderColor(user)
  }

  followHandler () {
    if (!this.followBtnDisabled) {
      this.followBtnDisabled = true
      const followAdd: FollowAddVO = new FollowAddVO(this.user.id)
      if (this.followStatus === FollowStatus.follow) {
        // 如果对方关注了自己
        if (this.user.hasBeFollowed) {
          this.followStatus = FollowStatus.eachFollow
        } else {
          this.followStatus = FollowStatus.followed
        }
        FollowAPI.addFollowAPI(followAdd).finally(() => {
          this.followBtnDisabled = false
        })
      } else {
        this.followStatus = FollowStatus.follow
        // 进行取消关注操作
        FollowAPI.cancelFollowAPI(followAdd).finally(() => {
          this.followBtnDisabled = false
        })
      }
    }
  }

  getFollowStatusColor (followStatus: string) {
    return UserUtil.getFollowStatusColor(followStatus)
  }

  openVip () {
    PageUtil.toVipPage()
  }

  toUserDetailVue () {
    if (RouterUtil.getCurrentPageURI() !== PagePath.userDetail) {
      RouterUtil.navigateTo(PagePath.userDetail + '?userId=' + this.user.id)
    }
  }
}
</script>
