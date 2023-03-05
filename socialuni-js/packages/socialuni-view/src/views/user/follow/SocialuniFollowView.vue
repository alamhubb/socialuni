<template>
  <view class="h100p flex-col">
    <q-tabs :tabs="tabs" :value="currentTabIndex" type="full" @input="tabsChange"
            class="flex-none bd-radius flex-1 mr-sm">
      <template #default="{tab,index,value}">
        <view class="h30 px-xs row-all-center font-md" :class="{'font-md':value===index}">{{ tab }}</view>
      </template>
    </q-tabs>
    <div class="flex-1 overflow-hidden">
      <swiper class="h100p" :current="currentTabIndex" @change="talkSwiperChange">
        <swiper-item v-for="(item, swiperIndex) in tabsPageQueryUtil" :key="swiperIndex">
          <div v-if="!item.queryQO.listData.length" class="row-all-center h100 color-content">
            <div v-if="swiperIndex===0">您还没有关注任何人</div>
            <div v-else>您还没有粉丝</div>
          </div>
          <template v-else>
            <div class="flex-row px-smm py-sm bb" v-for="user in item.queryQO.listData" :key="user.id"
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
import {onLoad, onPullDownRefresh, onReachBottom, onShow} from "@dcloudio/uni-app";
import {socialTalkModule} from "socialuni-sdk/src/store/store";
import QTabs from "socialuni-view/src/components/QTabs/QTabs.vue";
import LoadMoreType from "socialuni-constant/constant/LoadMoreType";
import SocialuniFollowTag from "../../../components/SocialuniFollow/SocialuniFollowTag.vue";
import SocialuniPageQueryQO from "socialuni-api/src/model/common/SocialuniPageQueryQO";
import SocialuniPageQueryUtil from "socialuni-api/src/model/common/SocialuniPageQueryUtil";

@Options({
  components: {SocialuniFollowTag, SocialGenderTag, QTabs}
})
export default class FollowView extends Vue {
  tabs = SocialuniFollowType.allFollowTypes
  tabsPageQueryUtil: SocialuniPageQueryUtil[] = [new SocialuniPageQueryUtil(), new SocialuniPageQueryUtil()]
  currentTabIndex = 0

  created() {
    this.tabsPageQueryUtil = [new SocialuniPageQueryUtil(FollowAPI.queryUserFollowsAPI), new SocialuniPageQueryUtil(FollowAPI.queryUserFollowsAPI)]

    onPullDownRefresh(async () => {
      console.log(123)
      await this.tabsPageQueryUtil[this.currentTabIndex].initQuery(this.tabs[this.currentTabIndex])
      uni.stopPullDownRefresh()
    })
    onReachBottom(() => {
      console.log(456)
      this.tabsPageQueryUtil[this.currentTabIndex].nextPageQuery(this.tabs[this.currentTabIndex])
    })

    onLoad((params: { followType: string }) => {
      if (params) {
        if (params.followType === SocialuniFollowType.fans) {
          this.currentTabIndex = 1
        } else {
          this.currentTabIndex = 0
        }
      }

      this.$nextTick(() => {
        uni.startPullDownRefresh()
      })
    })
  }

  openVip() {
    PageUtil.toVipPage()
  }

  toUserDetailVue(user: SocialUserContentRO) {
    if (PageUtil.getCurrentPageURI() !== PagePath.userDetail) {
      PageUtil.navigateTo(PagePath.userDetail + '?userId=' + user.id)
    }
  }

  // tabs通知swiper切换
  async tabsChange(index) {
    this.currentTabIndex = index
  }

  // talkSwipe
  async talkSwiperChange(e) {
    this.currentTabIndex = e.detail.current
  }
}
</script>
