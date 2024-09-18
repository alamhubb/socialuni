<template>
  <div class="h100p flex-col bg-white">
    <!--    <q-tabs :tabs="tabs" :value="currentTabIndex" type="full" @input="tabsChange"-->
    <!--            class="flex-none bd-radius flex-1 mr-sm mt-sm">-->
    <!--      <template #default="{tab,index,value}">-->
    <!--        <div class="h30 px-xs row-all-center font-md" :class="{'font-md':value===index}">{{ tab }}</div>-->
    <!--      </template>-->
    <!--    </q-tabs>-->
    <div class="flex-1 overflow-hidden">
      <q-pull-refresh ref="pullRefresh" @refresh="manualPulldownRefresh" class="h100p">
        <swiper class="h100p" :current="currentTabIndex" @change="talkSwiperChange">
          <swiper-item class="h100p" v-for="(item, swiperIndex) in tabsPageQueryUtil" :key="swiperIndex">
            <scroll-view class="h100p overflow-hidden"
                         :scroll-y="true" @scrolltolower="autoChooseUseLocationQueryTalksHandler"
                         :lower-threshold="400">
              <div v-if="!item.listData.length" class="row-all-center h100 color-content">
                <div>暂无数据</div>
              </div>
              <template v-else>
                <div v-if="mineUser && swiperIndex === 0" class="px-sm">
                  <!--                  <div class="row-all-center color-main mt-sm chunk-default pd-xs">-->
                  <!--                    {{ mineUser.openContactInfo ? '下拉刷新将您的排名前置' : '开启联系方式您的信息将在此处展示' }}-->
                  <!--                  </div>-->
                </div>
                <SocialuniExpandUserInfo v-for="user in item.listData" :user="user"
                                         :key="user.id"></SocialuniExpandUserInfo>
                <div class="mt-xs">
                  <uni-load-more :status="item.loadMore"
                                 @click="clickOnreachBottom"
                                 :contentText="loadMoreText"></uni-load-more>
                </div>
              </template>
            </scroll-view>
          </swiper-item>
        </swiper>
      </q-pull-refresh>
    </div>
  </div>
</template>

<script lang="ts">
import {Vue, Component, Prop, Emit, toNative} from 'vue-facing-decorator'
import SocialuniFollowType from "socialuni-constant/constant/user/SocialuniFollowType";
import SocialUserContentRO from "socialuni-api-base/src/model/social/SocialUserContentRO";
import {onLoad, onPullDownRefresh, onReachBottom, onShow} from "@dcloudio/uni-app";
import QTabs from "qingjs-ui/src/components/QTabs/QTabs.vue";
import QButton from "qingjs-ui/src/components/QButton.vue";
import QIcon from "qingjs-ui/src/components/QIcon.vue";
import QPullRefresh from "qingjs-ui-uni/src/components/QPullRefresh/QPullRefresh.vue";
import SocialuniFollowTag from "socialuni-community-view-uni/src/components/SocialuniFollow/SocialuniFollowTag.vue";
import SocialGenderTag from "socialuni-user-ui/src/components/SUserGenderTag.vue";
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
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";
import SocialuniImgUtil from "socialuni-user-sdk/src/util/SocialuniImgUtil";
import SocialuniExpandAPI from "socialuni-expand-api/src/api/SocialuniExpandAPI";
import SocialuniUserLikeAPI from "socialuni-expand-api/src/api/SocialuniUserLikeAPI";
import {socialuniChatModule} from "socialuni-im-sdk/src/store/SocialuniChatModule";
import SocialuniUserExpandService from "socialuni-user-sdk/src/logic/SocialuniUserExpandService";
import SocialuniExpandUserInfo from "socialuni-expand-ui/src/components/SocialuniExpandUserInfo.vue";


@Component({
  components: {QPullRefresh, QButton, QIcon, SocialGenderTag, QTabs, SocialuniExpandUserInfo}
})
class SocialuniExpandView extends Vue {
  $refs: {
    pullRefresh: QPullRefresh
  }

  tabs = SocialuniUserExtendFriendsType.allTypes
  tabsPageQueryUtil: SocialuniPageQueryUtil<SocialuniUserExtendDetailRO, SocialuniUserExtendFriendQueryQO>[] = [new SocialuniPageQueryUtil()]
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
    this.tabsPageQueryUtil = this.tabs.map(item => new SocialuniPageQueryUtil(SocialuniExpandAPI.queryExtendFriendUsersAPI))

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
    // for (const listDatum of this.tabsPageQueryUtil[this.currentTabIndex].listData) {
    //   listDatum.getUserContactBtnDisabled = false
    // }
    this.endPulldownRefresh()
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
    await this.tabsPageQueryUtil[this.currentTabIndex].loadNextPage()
    // for (const listDatum of this.tabsPageQueryUtil[this.currentTabIndex].listData) {
    //   listDatum.getUserContactBtnDisabled = false
    // }
  }

  get curTalkTabObj() {
    return this.tabsPageQueryUtil[this.currentTabIndex]
  }

  //点击不需要更新查询时间，查不出来就查不出来，万一是自己手动暂停了查询呢，而且如果重设时间会导致数据重复问题
  async clickOnreachBottom() {
    //停止查询方法
    const talkTab = this.curTalkTabObj
    if (talkTab) {
      await this.autoChooseUseLocationQueryTalksHandler()
    }
  }


  get mineUser() {
    return socialuniUserModule.mineUser
  }


}
export default toNative(SocialuniExpandView)
</script>
