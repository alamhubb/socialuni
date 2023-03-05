<template>
  <view class="h100p flex-col">
    <q-tabs :tabs="tabs" :value="currentTabIndex" type="line" @input="tabsChange"
            class="flex-none bd-radius flex-1 mr-sm">
      <template #default="{tab,index,value}">
        <view class="h30 px-xs row-all-center font-md" :class="{'font-md':value===index}">{{ tab }}</view>
      </template>
    </q-tabs>
    <div class="flex-1 overflow-hidden">
      <swiper class="h100p" :current="currentTabIndex" @change="talkSwiperChange">
        <swiper-item v-for="(item, swiperIndex) in tabsFollowData" :key="swiperIndex">
          <div v-if="!tabsFollowData[swiperIndex].length" class="row-all-center h100 color-content">
            <div v-if="swiperIndex===0">您还没有关注任何人</div>
            <div v-else>您还没有粉丝</div>
          </div>
          <template v-else>
            <div class="flex-row px-smm py-sm bb" v-for="user in tabsFollowData[swiperIndex]" :key="user.id"
                 @click="toUserDetailVue">
              <image
                  class="card-title-avatar"
                  mode="aspectFill"
                  :src="user.avatar"
              />
              <view class="flex-1 row-between">
                <view class="col-between py-xs">
                  <view class="row-col-center">
                    <text :class="{'text-red':user.vipFlag}">{{ user.nickname }}</text>
                    <view v-if="user.vipFlag" class="ml-5px cu-tag bg-orange radius sm"
                          @click.stop="openVip">
                      VIP
                    </view>
                    <social-gender-tag class="ml-xs" :user="user"></social-gender-tag>
                  </view>
                  <view class="color-content mt-xs">
                    粉丝：{{ user.fansNum }}
                  </view>
                </view>
                <view class="col-center">
                  <socialuni-follow-tag :user="user"></socialuni-follow-tag>
                </view>
              </view>
            </div>
          </template>
        </swiper-item>
      </swiper>
    </div>
  </view>
</template>

<script lang="ts">
import {Vue, Options, Prop, Emit} from 'vue-property-decorator'
import SocialuniFollowType from "socialuni-constant/constant/user/SocialuniFollowType";
import SocialUserContentRO from "socialuni-api/src/model/social/SocialUserContentRO";
import FollowAPI from "socialuni-api/src/api/socialuni/FollowAPI";
import SocialGenderTag from "../../../components/SocialGenderTag/SocialGenderTag.vue";
import PageUtil from "socialuni-sdk/src/utils/PageUtil";
import PagePath from "socialuni-constant/constant/PagePath";
import {onPullDownRefresh} from "@dcloudio/uni-app";
import {socialTalkModule} from "socialuni-sdk/src/store/store";
import QTabs from "socialuni-view/src/components/QTabs/QTabs.vue";
import LoadMoreType from "socialuni-constant/constant/LoadMoreType";
import SocialuniFollowTag from "../../../components/SocialuniFollow/SocialuniFollowTag.vue";

@Options({
  components: {SocialuniFollowTag, SocialGenderTag, QTabs}
})
export default class FollowView extends Vue {
  followPageType: string = SocialuniFollowType.follow
  SocialuniFollowType = SocialuniFollowType
  followUsers: SocialUserContentRO[] = []
  fansUsers: SocialUserContentRO[] = []

  tabs = SocialuniFollowType.allFollowTypes

  tabsFollowData = [[], []]

  currentTabIndex = 1

  created() {
    onPullDownRefresh(() => {
      console.log('查询')
      this.queryUserFollows()
    })
    uni.startPullDownRefresh()
  }

  queryUserFollows() {
    this.tabsFollowData = [[], []]
    FollowAPI.queryUserFollowsAPI().then((res: any) => {
      this.followUsers = res.data.follows
      this.fansUsers = res.data.fans
      this.tabsFollowData = [this.followUsers, this.fansUsers]
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

  @Emit()
  change(user: SocialUserContentRO) {
    return user
  }

  openVip() {
    PageUtil.toVipPage()
  }

  toUserDetailVue() {
    if (PageUtil.getCurrentPageURI() !== PagePath.userDetail) {
      PageUtil.navigateTo(PagePath.userDetail + '?userId=' + this.user.id)
    }
  }

  // tabs通知swiper切换
  async tabsChange(index) {
    this.currentTabIndex = index
  }

  // talkSwipe
  async talkSwiperChange(e) {
    const currentTabIndex = e.detail.current
  }
}
</script>
