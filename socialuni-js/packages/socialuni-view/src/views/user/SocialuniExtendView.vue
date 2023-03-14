<template>
  <view class="h100p flex-col bg-white">
    <q-tabs :tabs="tabs" :value="currentTabIndex" type="full" @input="tabsChange"
            class="flex-none bd-radius flex-1 mr-sm mt-sm">
      <template #default="{tab,index,value}">
        <view class="h30 px-xs row-all-center font-md" :class="{'font-md':value===index}">{{ tab }}</view>
      </template>
    </q-tabs>
    <div class="flex-1 overflow-hidden">
      <q-pull-refresh ref="pullRefresh" @refresh="manualPulldownRefresh" class="h100p">
        <swiper class="h100p" :current="currentTabIndex" @change="talkSwiperChange">
          <swiper-item class="h100p" v-for="(item, swiperIndex) in tabsPageQueryUtil" :key="swiperIndex">
            <scroll-view class="h100p overflow-hidden"
                         :scroll-y="true" @scrolltolower="autoChooseUseLocationQueryTalks"
                         :lower-threshold="400">
              <div v-if="!item.queryQO.listData.length" class="row-all-center h100 color-content">
                <div>暂无数据</div>
              </div>
              <template v-else>
                <div v-if="mineUser && swiperIndex === 0" class="px-sm">
                  <div class="row-all-center color-main mt-sm chunk-default pd-xs">
                    {{ mineUser.openContactInfo ? '下拉刷新将您的排名前置' : '开启联系方式您的信息将在此处展示' }}
                  </div>
                </div>
                <div class="flex-col px-smm py-sm bb" v-for="user in item.queryQO.listData" :key="user.id"
                     @click="toUserDetailVue(user)">
                  <div class="row-col-center">
                    <image
                        class="card-title-avatar bd flex-none"
                        mode="aspectFill"
                        :src="user.avatar"
                    />
                    <view class="flex-1 row-between-center py-xs">
                      <div class="flex-col flex-1">
                        <view class="row-col-center">
                          <text :class="{'text-red':user.vipFlag}">{{ user.nickname }}</text>
                          <view v-if="user.vipFlag" class="ml-5px cu-tag bg-orange radius sm"
                                @click.stop="openVip">
                            VIP
                          </view>
                          <social-gender-tag class="ml-xs" :user="user"></social-gender-tag>
                        </view>
                        <view class="row-col-center mt-xss font-12 color-content">
                          {{ formatTime(user.lastOnlineTime) }}
                          <div class="px-xs row-col-center">|</div>
                          <!--        有市区的名称就不显示省的名称-->
                          <text v-if="!user.cityName || !user.districtName">{{ user.provinceName }}</text>
                          <text v-if="user.cityName">
                            <text v-if="!user.districtName">-</text>
                            {{ user.cityName }}
                          </text>
                          <text v-if="user.districtName">-{{ user.districtName }}</text>

                          <view class="row-col-center" v-if="user.distance|| user.distance===0">
                            <div class="px-xs row-col-center">|</div>
                            <text v-if="user.distance<0.5">{{ 0.5 }}公里</text>
                            <text v-else-if="user.distance<1">{{ 1 }}公里</text>
                            <text v-else-if="user.distance<5">{{ 5 }}公里</text>
                            <text v-else>{{ numFixed1(user.distance) }}公里</text>
                          </view>
                        </view>
                      </div>

                      <view class="col-center flex-none">
                        <div v-if="user.openContactInfo" class="use-click row-col-center">
                          <q-button light @click="copyContactInfo">
                            <div class="color-content ml-xs font-12">
                              已获取( 点击复制 )
                            </div>
                          </q-button>
                        </div>
                        <div v-else class="use-click row-col-center">
                          <q-button text @click="getOpenContactInfo(user)" :disabled="showUserContactBtnDisabled">
                            <q-icon prefix="uni-icons" icon="uniui-personadd" size="22"></q-icon>
                          </q-button>
                        </div>
                        <!--                    <socialuni-follow-tag :user="user" @change="userFollowChange"></socialuni-follow-tag>-->
                      </view>

                    </view>
                  </div>
                  <view class="ml-60 row-col-center mt-xs">
                    <image v-for="img in imgUrls(user).slice(0,3)" class="size40 bd-radius bd mr-sm"
                           mode="aspectFill"
                           :data-src="img"
                           @click.stop="previewImage(img,user)"
                           :src="img"
                    ></image>
                  </view>
                </div>
                <view class="mt-xs">
                  <uni-load-more :status="item.queryQO.loadMore"
                                 @click="clickOnreachBottom"
                                 :contentText="loadMoreText"></uni-load-more>
                </view>
              </template>
            </scroll-view>
          </swiper-item>
        </swiper>
      </q-pull-refresh>
    </div>
  </view>
</template>

<script lang="ts">
import {Vue, Options, Prop, Emit} from 'vue-property-decorator'
import SocialuniFollowType from "socialuni-constant/constant/user/SocialuniFollowType";
import SocialUserContentRO from "socialuni-api/src/model/social/SocialUserContentRO";
import FollowAPI from "socialuni-api/src/api/socialuni/FollowAPI";
import SocialGenderTag from "socialuni-view/src/components/SocialGenderTag/SocialGenderTag.vue";
import SocialuniFollowTag from "socialuni-view/src/components/SocialuniFollow/SocialuniFollowTag.vue";
import PageUtil from "socialuni-sdk/src/utils/PageUtil";
import PagePath from "socialuni-constant/constant/PagePath";
import {onLoad, onPullDownRefresh, onReachBottom, onShow} from "@dcloudio/uni-app";
import {socialTalkModule, socialUserModule} from "socialuni-sdk/src/store/store";
import QTabs from "socialuni-view/src/components/QTabs/QTabs.vue";
import LoadMoreType from "socialuni-constant/constant/LoadMoreType";
import SocialuniPageQueryQO from "socialuni-api/src/model/common/SocialuniPageQueryQO";
import SocialuniPageQueryUtil from "socialuni-api/src/model/common/SocialuniPageQueryUtil";
import SocialuniUserRO from "socialuni-api/src/model/user/SocialuniUserRO";
import CommonUtil from "socialuni-sdk/src/utils/CommonUtil";
import SocialUserFollowDetailRO from "socialuni-api/src/model/social/SocialUserFollowDetailRO";
import SocialuniUserExtendFriendsType from "socialuni-constant/constant/user/SocialuniUserExtendFriendsType";
import SocialuniUserAPI from "socialuni-api/src/api/socialuni/SocialuniUserAPI";
import SocialuniUserExtendFriendQueryQO from "socialuni-api/src/model/user/SocialuniUserExtendFriendQueryQO";
import SocialuniUserExpandAPI from "socialuni-api/src/api/socialuni/SocialuniUserExpandAPI";
import request from "socialuni-api/src/request/request";
import CenterUserDetailRO from "socialuni-api/src/model/social/CenterUserDetailRO";
import QIcon from "../../components/QIcon/QIcon.vue";
import QButton from "../../components/QButton/QButton.vue";
import UniUtil from "socialuni-sdk/src/utils/UniUtil";
import NumUtil from "socialuni-sdk/src/utils/NumUtil";
import DateUtil from "socialuni-sdk/src/utils/DateUtil";
import SocialuniUserExtendDetailRO from "socialuni-api/src/model/social/SocialuniUserExtendDetailRO";
import ImgUtil from "socialuni-sdk/src/utils/ImgUtil";
import QPullRefresh from "../../components/QPullRefresh/QPullRefresh.vue";

@Options({
  components: {QPullRefresh, QButton, QIcon, SocialuniFollowTag, SocialGenderTag, QTabs}
})
export default class SocialuniExtendView extends Vue {
  $refs:{
    pullRefresh:QPullRefresh
  }

  tabs = SocialuniUserExtendFriendsType.allTypes
  tabsPageQueryUtil: SocialuniPageQueryUtil<SocialuniUserExtendDetailRO, SocialuniUserExtendFriendQueryQO>[] = [new SocialuniPageQueryUtil(), new SocialuniPageQueryUtil()]
  currentTabIndex = 0

  loadMoreText = {
    contentdown: '点击显示更多',
    contentrefresh: '正在加载...',
    contentnomore: '没有更多数据了,点击刷新'
  }
  showUserContactBtnDisabled: boolean = false


  async manualPulldownRefresh() {
    await this.initQuery()
  }

  created() {
    this.tabsPageQueryUtil = [new SocialuniPageQueryUtil(SocialuniUserExpandAPI.queryExtendFriendUsersAPI), new SocialuniPageQueryUtil(SocialuniUserExpandAPI.queryExtendFriendUsersAPI)]

    onLoad((params: { followType: string }) => {
      if (params) {
        if (params.followType === SocialuniFollowType.fans) {
          this.currentTabIndex = 1
        } else {
          this.currentTabIndex = 0
        }
      }
      this.$nextTick(() => {
        this.startPulldownRefresh()
      })
    })
  }

  startPulldownRefresh(){
    this.$refs.pullRefresh.startPulldownRefresh()
  }
  endPulldownRefresh(){
    this.$refs.pullRefresh.endPulldownRefresh()
  }


  async initQuery() {
    const queryData = new SocialuniUserExtendFriendQueryQO(this.tabs[this.currentTabIndex]);
    console.log(queryData)
    await this.tabsPageQueryUtil[this.currentTabIndex].initQuery(queryData)
    this.endPulldownRefresh()
  }

  openVip() {
    PageUtil.toVipPage()
  }

  toUserDetailVue(user: SocialUserContentRO) {
    PageUtil.toUserDetail(user.id)
  }

  // tabs通知swiper切换
  tabsChange(index) {
    this.currentTabIndex = index
    if (this.tabsPageQueryUtil[this.currentTabIndex].queryQO.firstLoad) {
      this.startPulldownRefresh()
    }
  }

  // talkSwipe
  talkSwiperChange(e) {
    this.tabsChange(e.detail.current)
  }

  autoChooseUseLocationQueryTalksHandler() {
    this.tabsPageQueryUtil[this.currentTabIndex].nextPageQuery()
  }

  autoChooseUseLocationQueryTalks = CommonUtil.debounce(() => {
    this.autoChooseUseLocationQueryTalksHandler()
  }, 500)


  get curTalkTabObj() {
    return this.tabsPageQueryUtil[this.currentTabIndex].queryQO
  }

  //同步更新粉丝和关注列表状态
  userFollowChange(user: SocialUserContentRO) {
    for (const socialuniPageQueryUtil of this.tabsPageQueryUtil) {
      for (const listDatum of socialuniPageQueryUtil.queryQO.listData) {
        if (listDatum.id === user.id) {
          listDatum.hasFollowed = user.hasFollowed
        }
      }
    }
  }

  //点击不需要更新查询时间，查不出来就查不出来，万一是自己手动暂停了查询呢，而且如果重设时间会导致数据重复问题
  async clickOnreachBottom() {
    //停止查询方法
    const talkTab = this.curTalkTabObj
    if (talkTab) {
      //如果正在查询，则停止查询，没办法省略因为修改和使用的是一个变量
      if (talkTab.loadMore === LoadMoreType.loading) {
        talkTab.loadMore = LoadMoreType.more
      } else {
        talkTab.loadMore = LoadMoreType.more
        //如果正在查询，则更改状态为加载更多,点击暂停加载。
        await this.autoChooseUseLocationQueryTalks()
      }
    }
  }

  async getOpenContactInfo(user: CenterUserDetailRO) {
    //打开获取对方联系方式功能，支付贝壳
    this.showUserContactBtnDisabled = true
    try {
      await socialUserModule.getOpenContactInfo(user)
    } finally {
      this.showUserContactBtnDisabled = false
    }
  }

  copyContactInfo(user: CenterUserDetailRO) {
    UniUtil.textCopy(user.contactInfo)
  }

  get mineUser() {
    return socialUserModule.mineUser
  }

  numFixed1(num) {
    return NumUtil.numFixed1(num)
  }

  formatTime(dateStr) {
    return DateUtil.formatTime(dateStr)
  }

  imgUrls(user: CenterUserDetailRO) {
    if (user && user.imgs) {
      return user.imgs.map(item => ImgUtil.getUserLargeImgUrl(item.src))
    } else {
      return []
    }
  }

  previewImage(current, user: CenterUserDetailRO) {
    uni.previewImage({
      current: current,
      urls: this.imgUrls(user)
    })
  }


}
</script>
