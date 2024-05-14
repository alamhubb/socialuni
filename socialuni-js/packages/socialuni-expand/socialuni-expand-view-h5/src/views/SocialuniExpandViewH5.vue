<template>
  <div class="flex-col">
    <q-tabs
        v-model="currentTabIndex"
        active-class="bg-white bb-blue-3"
        :tabs="tabs"
        @change="tabsChange"
        class="flex-none bg-white position-sticky top-0 index-xs"
    />
    <div @click="$refs.cityPicker.open()">dakai</div>
    <q-city-picker ref="cityPicker" :bottom="false"></q-city-picker>

    <div class="flex-1 overflow-hidden">
      <div class="h100p" v-for="(item, swiperIndex) in tabsPageQueryUtil" :key="swiperIndex">
        <div>
          <div v-if="!item.listData.length" class="row-all-center h100 color-content">
            <div>暂无数据</div>
          </div>
          <template v-else>
            <div v-if="mineUser && swiperIndex === 0" class="px-sm">
              <!--                  <div class="row-all-center color-main mt-sm chunk-default pd-xs">-->
              <!--                    {{ mineUser.openContactInfo ? '下拉刷新将您的排名前置' : '开启联系方式您的信息将在此处展示' }}-->
              <!--                  </div>-->
            </div>
            <div class="flex-col px-smm py-sm bb" v-for="user in item.listData" :key="user.id"
                 @click="toUserDetailVue(user)">
              <div class="row-col-center">
                <img
                    class="size50 bd-radius-xs mr-10 flex-none"
                    mode="aspectFill"
                    :src="user.avatar"
                />
                <div class="flex-1 row-between-center py-xs">
                  <div class="flex-col flex-1">
                    <div class="row-between-center">
                      <div class="row-col-center">
                        <span :class="{'color-red':user.vipFlag}">{{ user.nickname }}</span>
                        <div v-if="user.vipFlag" class="ml-5px cu-tag bg-orange radius sm"
                              @click.stop="openVip">
                          VIP
                        </div>
                        <s-user-gender-tag class="ml-xs" :user="user"></s-user-gender-tag>
                      </div>
                    </div>
                    <div class="row-col-center mt-xss font-12 color-content">
                      <!--                          {{ formatTime(user.updateTime) }}-->
                      <!--                          <div class="px-xs row-col-center">|</div>-->
                      <!--        有市区的名称就不显示省的名称-->
                      <span v-if="!user.cityName || !user.districtName">{{ user.provinceName }}</span>
                      <span v-if="user.cityName">
                        <span v-if="!user.districtName">-</span>
                        {{ user.cityName.substring(0, 6) }}
                      </span>
                      <span v-if="user.districtName">-{{ user.districtName }}</span>

                      <div class="row-col-center" v-if="user.distance|| user.distance===0">
                        <div class="px-xs row-col-center">|</div>
                        <span v-if="user.distance<0.5">{{ 0.5 }}公里</span>
                        <span v-else-if="user.distance<1">{{ 1 }}公里</span>
                        <span v-else-if="user.distance<5">{{ 5 }}公里</span>
                        <span v-else>{{ numFixed1(user.distance) }}公里</span>
                      </div>
                    </div>
                  </div>
                  <div class="col-center flex-none">
                    <!--                      <div v-if="!isIos" class="col-center flex-none">-->
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
                        <q-icon icon="mdi-heart-outline" size="22"></q-icon>
                      </q-button>
                    </div>

                    <!--                        <div v-else class="use-click row-col-center">-->
                    <!--                          <q-button text @click="getOpenContactInfo(user)" :disabled="showUserContactBtnDisabled">-->
                    <!--                            <q-icon prefix="uni-icons" icon="uniui-heart" size="22"></q-icon>-->
                    <!--                          </q-button>-->
                    <!--                        </div>-->
                    <!--                    <socialuni-follow-tag :user="user" @change="userFollowChange"></socialuni-follow-tag>-->
                  </div>
                </div>
              </div>
              <div class="ml-60 row-col-center mt-xs">
                <img v-for="img in imgUrls(user).slice(0,3)" class="size40 bd-radius bd mr-sm"
                     mode="aspectFill"
                     :data-src="img"
                     @click.stop="previewImage(img,user)"
                     :src="img"
                />
              </div>
            </div>
            <div class="mt-xs">
              <uni-load-more :status="item.queryQO.loadMore"
                             @click="clickOnreachBottom"
                             :contentText="loadMoreText"></uni-load-more>
            </div>
          </template>
        </div>
        <!--        <q-scrollbar class="h100p" v-infinite-scroll="autoChooseUseLocationQueryTalks">-->
        <!--        -->
        <!--        </q-scrollbar>-->
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {Vue, Component, Prop, Emit, toNative} from 'vue-facing-decorator'
import SocialuniFollowType from "socialuni-constant/constant/user/SocialuniFollowType";
import SocialUserContentRO from "socialuni-api-base/src/model/social/SocialUserContentRO";
import QTabs from "qing-ui/src/components/QTabs/QTabs.vue";
import QButton from "qing-ui/src/components/QButton.vue";
import LoadMoreType from "socialuni-constant/constant/LoadMoreType";
import SocialuniPageQueryUtil from "socialuni-api-base/src/model/common/SocialuniPageQueryUtil";
import CommonUtil from "qing-util/src/util/CommonUtil";
import SocialuniUserExtendFriendsType from "socialuni-constant/constant/user/SocialuniUserExtendFriendsType";
import SocialuniUserExtendFriendQueryQO from "socialuni-api-base/src/model/user/SocialuniUserExtendFriendQueryQO";
import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import NumUtil from "qing-util/src/util/NumUtil";
import DateUtil from "qing-util/src/util/DateUtil";
import SocialuniUserExtendDetailRO from "socialuni-expand-api/src/model/SocialuniUserExtendDetailRO";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import UserPageUtil from "socialuni-user-sdk/src/util/UserPageUtil";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import SocialuniImgUtil from "socialuni-user-sdk/src/util/SocialuniImgUtil";
import SocialuniExpandAPI from "socialuni-expand-api/src/api/SocialuniExpandAPI";
import SocialuniUserLikeAPI from "socialuni-expand-api/src/api/SocialuniUserLikeAPI";
import {socialuniChatModule} from "socialuni-im-sdk/src/store/SocialuniChatModule";
import SocialuniUserExpandService from "socialuni-user-sdk/src/logic/SocialuniUserExpandService";
import QScrollbar from "qing-ui-h5/src/components/QScrollbar.vue";
import SUserGenderTag from "socialuni-user-ui/src/components/SUserGenderTag.vue";
import QIcon from "qing-ui/src/components/QIcon.vue";
import QScroll from "qing-ui/src/components/QScroll.vue";
import QPicker from "qing-ui/src/components/QPicker.vue";
import QCityPicker from "socialuni-ui/src/components/QCityPicker.vue";
import CommonEventUtil from "qingjs/src/util/CommonEventUtil";
import {socialLocationModule} from "socialuni-community-sdk/src/store/SocialLocationModule";

@toNative
@Component({
  components: {QTabs, QScrollbar, SUserGenderTag, QButton, QIcon, QScroll, QPicker, QCityPicker}
})
export default class SocialuniExpandViewH5 extends Vue {
  $refs: {}

  tabs = SocialuniUserExtendFriendsType.allTypes
  tabsPageQueryUtil: SocialuniPageQueryUtil<SocialuniUserExtendDetailRO, SocialuniUserExtendFriendQueryQO>[] = []
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

  get districts() {
    return socialLocationModule.districts
  }

  created() {
    socialLocationModule.getDistrictsAction()
    this.tabsPageQueryUtil = this.tabs.map(() => new SocialuniPageQueryUtil(SocialuniExpandAPI.queryExtendFriendUsersAPI))
    this.initQuery()
    // onLoad((params: { followType: string }) => {
    //   if (params) {
    //     if (params.followType === SocialuniFollowType.fans) {
    //       this.currentTabIndex = 1
    //     } else {
    //       this.currentTabIndex = 0
    //     }
    //   }
    //   this.$nextTick(() => {
    //     this.startPulldownRefresh()
    //   })
    // })
    CommonEventUtil.off('appScrollToLower');
    this.$nextTick(() => {
      CommonEventUtil.on('appScrollToLower', () => {
        console.log('触发了')
        this.autoChooseUseLocationQueryTalks()
      })
    })
  }

  startPulldownRefresh() {
    this.initQuery()
  }

  endPulldownRefresh() {
    // this.$refs.pullRefresh.endPulldownRefresh()
  }


  async initQuery() {
    const queryData = new SocialuniUserExtendFriendQueryQO(this.tabs[this.currentTabIndex]);
    await this.tabsPageQueryUtil[this.currentTabIndex].initQuery(queryData)
    for (const listDatum of this.tabsPageQueryUtil[this.currentTabIndex].listData) {
      // listDatum.getUserContactBtnDisabled = false
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
    const queryData = new SocialuniUserExtendFriendQueryQO(this.tabs[this.currentTabIndex]);
    await this.tabsPageQueryUtil[this.currentTabIndex].loadNextPage(queryData)
    for (const listDatum of this.tabsPageQueryUtil[this.currentTabIndex].listData) {
      // listDatum.getUserContactBtnDisabled = false
    }
  }

  autoChooseUseLocationQueryTalks = CommonUtil.debounce(() => {
    this.autoChooseUseLocationQueryTalksHandler()
  }, 500)


  get curTalkTabObj() {
    return this.tabsPageQueryUtil[this.currentTabIndex]?.queryQO
  }

  //同步更新粉丝和关注列表状态
  userFollowChange(user: SocialUserContentRO) {
    for (const socialuniPageQueryUtil of this.tabsPageQueryUtil) {
      for (const listDatum of socialuniPageQueryUtil.listData) {
        if (listDatum.id === user.id) {
          // listDatum.hasFollowed = user.hasFollowed
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

  addLikeUser(user: SocialuniUserExtendDetailRO) {
    SocialuniUserLikeAPI.addUserLikeAPI(user)
  }

  async toMessagePage(user) {
    socialuniChatModule.setChatIdToMessagePage(user.id)
  }
}
</script>
