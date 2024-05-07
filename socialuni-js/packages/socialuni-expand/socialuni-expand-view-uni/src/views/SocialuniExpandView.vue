<template>
  <view class="h100p flex-col bg-white">
    <!--    <q-tabs :tabs="tabs" :value="currentTabIndex" type="full" @input="tabsChange"-->
    <!--            class="flex-none bd-radius flex-1 mr-sm mt-sm">-->
    <!--      <template #default="{tab,index,value}">-->
    <!--        <view class="h30 px-xs row-all-center font-md" :class="{'font-md':value===index}">{{ tab }}</view>-->
    <!--      </template>-->
    <!--    </q-tabs>-->
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
                  <!--                  <div class="row-all-center color-main mt-sm chunk-default pd-xs">-->
                  <!--                    {{ mineUser.openContactInfo ? '下拉刷新将您的排名前置' : '开启联系方式您的信息将在此处展示' }}-->
                  <!--                  </div>-->
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
                        <view class="row-between-center">
                          <div class="row-col-center">
                            <text :class="{'color-red':user.vipFlag}">{{ user.nickname }}</text>
                            <view v-if="user.vipFlag" class="ml-5px cu-tag bg-orange radius sm"
                                  @click.stop="openVip">
                              VIP
                            </view>
                            <social-gender-tag class="ml-xs" :user="user"></social-gender-tag>
                          </div>
                        </view>
                        <view class="row-col-center mt-xss font-12 color-content">
<!--                          {{ formatTime(user.updateTime) }}-->
<!--                          <div class="px-xs row-col-center">|</div>-->
                          <!--        有市区的名称就不显示省的名称-->
                          <text v-if="!user.cityName || !user.districtName">{{ user.provinceName }}</text>
                          <text v-if="user.cityName">
                            <text v-if="!user.districtName">-</text>
                            {{ user.cityName.substring(0, 6) }}
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
<!--                      <view v-if="!isIos" class="col-center flex-none">-->
                        <div v-if="user.openContactInfo" class="use-click row-col-center">
                          <q-button light @click="copyContactInfo(user)">
                            <div class="color-content ml-xs font-12">
                              已获取( 点击复制 )
                            </div>
                          </q-button>
                        </div>
                        <div v-else class="use-click row-col-center">
                          <q-button v-if="user.hasUserLike" @click="toMessagePage(user)" class="mr-sm">
                            <q-icon icon="mdi-chat-outline" size="14"></q-icon>
                            私信
                          </q-button>
                          <q-button v-else text @click="addLikeUser(user)">
                            <q-icon prefix="uni-icons" icon="uniui-heart" size="22"></q-icon>
                          </q-button>
                        </div>

                        <!--                        <div v-else class="use-click row-col-center">-->
                        <!--                          <q-button text @click="getOpenContactInfo(user)" :disabled="showUserContactBtnDisabled">-->
                        <!--                            <q-icon prefix="uni-icons" icon="uniui-heart" size="22"></q-icon>-->
                        <!--                          </q-button>-->
                        <!--                        </div>-->
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
import {Vue, Component, Prop, Emit, toNative, toNative} from 'vue-facing-decorator'
import SocialuniFollowType from "socialuni-constant/constant/user/SocialuniFollowType";
import SocialUserContentRO from "socialuni-api-base/src/model/social/SocialUserContentRO";
import {onLoad, onPullDownRefresh, onReachBottom, onShow} from "@dcloudio/uni-app";
import QTabs from "qing-ui-uni/src/components/QTabs/QTabs.vue";
import QButton from "qing-ui-uni/src/components/QButton/QButton.vue";
import QIcon from "qing-ui-uni/src/components/QIcon/QIcon.vue";
import QPullRefresh from "qing-ui-uni/src/components/QPullRefresh/QPullRefresh.vue";
import SocialuniFollowTag from "socialuni-community-view-uni/src/components/SocialuniFollow/SocialuniFollowTag.vue";
import SocialGenderTag from "socialuni-user-view-uni/src/components/SocialGenderTag/SocialGenderTag.vue";
import LoadMoreType from "socialuni-constant/constant/LoadMoreType";
import SocialuniPageQueryUtil from "socialuni-api-base/src/model/common/SocialuniPageQueryUtil";
import CommonUtil from "qing-util/src/util/CommonUtil";
import SocialuniUserExtendFriendsType from "socialuni-constant/constant/user/SocialuniUserExtendFriendsType";
import SocialuniUserExtendFriendQueryQO from "socialuni-api-base/src/model/user/SocialuniUserExtendFriendQueryQO";
import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import NumUtil from "qing-util/src/util/NumUtil";
import DateUtil from "qing-util/src/util/DateUtil";
import SocialuniUserExtendDetailRO from "socialuni-api-base/src/model/social/SocialuniUserExtendDetailRO";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import UserPageUtil from "socialuni-user-sdk/src/util/UserPageUtil";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import SocialuniImgUtil from "socialuni-user-sdk/src/util/SocialuniImgUtil";
import SocialuniExpandAPI from "socialuni-expand-api/src/api/SocialuniExpandAPI";
import SocialuniUserLikeAPI from "socialuni-expand-api/src/api/SocialuniUserLikeAPI";
import {socialuniChatModule} from "socialuni-im-sdk/src/store/SocialuniChatModule";

@toNative
@Component({
  components: {QPullRefresh, QButton, QIcon, SocialGenderTag, QTabs}
})
export default  class SocialuniExpandView extends Vue {
  $refs: {
    pullRefresh: QPullRefresh
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

  get isIos() {
    return socialuniSystemModule.isIos
  }


  async manualPulldownRefresh() {
    await this.initQuery()
  }

  created() {
    this.tabsPageQueryUtil = [new SocialuniPageQueryUtil(SocialuniExpandAPI.queryExtendFriendUsersAPI), new SocialuniPageQueryUtil(SocialuniExpandAPI.queryExtendFriendUsersAPI)]

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

  startPulldownRefresh() {
    this.$refs.pullRefresh.startPulldownRefresh()
  }

  endPulldownRefresh() {
    this.$refs.pullRefresh.endPulldownRefresh()
  }


  async initQuery() {
    const queryData = new SocialuniUserExtendFriendQueryQO(this.tabs[this.currentTabIndex]);
    await this.tabsPageQueryUtil[this.currentTabIndex].initQuery(queryData)
    for (const listDatum of this.tabsPageQueryUtil[this.currentTabIndex].queryQO.listData) {
      listDatum.getUserContactBtnDisabled = false
    }
    this.endPulldownRefresh()
  }

  /*openVip() {
    PageUtil.toVipPage()
  }*/

  toUserDetailVue(user: SocialUserContentRO) {
    UserPageUtil.toUserDetail(user.id)
  }

  // tabs通知swiper切换
  tabsChange(index) {
    if (index === this.currentTabIndex) {
      this.startPulldownRefresh()
    } else {
      this.currentTabIndex = index
      if (this.tabsPageQueryUtil[this.currentTabIndex].queryQO.firstLoad) {
        this.startPulldownRefresh()
      }
    }
  }

  // talkSwipe
  talkSwiperChange(e) {
    this.tabsChange(e.detail.current)
  }

  async autoChooseUseLocationQueryTalksHandler() {
    await this.tabsPageQueryUtil[this.currentTabIndex].nextPageQuery()
    for (const listDatum of this.tabsPageQueryUtil[this.currentTabIndex].queryQO.listData) {
      listDatum.getUserContactBtnDisabled = false
    }
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
    user.getUserContactBtnDisabled = true
    try {
      await SocialuniUserExpandService.getOpenContactInfo(user)
    } finally {
      user.getUserContactBtnDisabled = false
    }
  }

  copyContactInfo(user: CenterUserDetailRO) {
    QingAppUtil.NativeUtil.textCopy(user.contactInfo)
  }

  get mineUser() {
    return socialuniUserModule.mineUser
  }

  numFixed1(num) {
    return NumUtil.numFixed1(num)
  }

  formatTime(dateStr) {
    return DateUtil.formatTime(dateStr)
  }

  imgUrls(user: CenterUserDetailRO) {
    if (user && user.imgs) {
      return user.imgs.map(item => SocialuniImgUtil.getUserLargeImgUrl(item.src))
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

  addLikeUser(user: CenterUserDetailRO) {
    SocialuniUserLikeAPI.addUserLikeAPI(user)
  }

  async toMessagePage(user) {
    socialuniChatModule.setChatIdToMessagePage(user.id)
  }
}
</script>
