<template>
  <div class="h100p flex-col bg-white">
    <q-tabs :tabs="tabs" :value="currentTabIndex" type="full" @input="tabsChange"
            class="flex-none bd-radius flex-1 mr-sm mt-sm">
      <template #default="{tab,index,value}">
        <div class="h30 px-xs row-all-center font-md" :class="{'font-md':value===index}">{{ tab }}</div>
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
                <div v-if="swiperIndex===0">您还没有关注任何人</div>
                <div v-else>您还没有粉丝</div>
              </div>
              <template v-else>
                <div class="flex-row px-smm py-sm bb" v-for="user in item.queryQO.listData" :key="user.id"
                     @click="toUserDetailVue(user)">
                  <image
                      class="card-title-avatar bd"
                      mode="aspectFill"
                      :src="user.avatar"
                  />
                  <div class="flex-1 row-between">
                    <div class="col-between py-xs">
                      <div class="row-col-center">
                        <span :class="{'color-red':user.vipFlag}">{{ user.nickname }}</span>
                        <div v-if="user.vipFlag" class="ml-5px cu-tag bg-orange radius sm"
                              @click.stop="openVip">
                          VIP
                        </div>
                        <social-gender-tag class="ml-xs" :user="user"></social-gender-tag>
                      </div>
                      <div class="color-content mt-xs">
                        粉丝：{{ user.fansNum }}
                      </div>
                    </div>
                    <div class="col-center">
                      <socialuni-follow-tag :user="user" @change="userFollowChange"></socialuni-follow-tag>
                    </div>
                  </div>
                </div>
                <div class="mt-xs">
                  <uni-load-more :status="item.queryQO.loadMore"
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
import QTabs from "qing-ui-uni/src/components/QTabs/QTabs.vue";
import LoadMoreType from "socialuni-constant/constant/LoadMoreType";
import SocialuniPageQueryUtil from "socialuni-api-base/src/model/common/SocialuniPageQueryUtil";
import CommonUtil from "qing-util/src/util/CommonUtil";
import SocialUserFollowDetailRO from "socialuni-api-base/src/model/social/SocialUserFollowDetailRO";
import QPullRefresh from "qing-ui-uni/src/components/QPullRefresh/QPullRefresh.vue";
import SocialGenderTag from "socialuni-user-ui/src/components/SUserGenderTag.vue";
import FollowAPI from "socialuni-community-api/src/api/FollowAPI";
import UserPageUtil from "socialuni-user-sdk/src/util/UserPageUtil";

@toNative
@Component({
  components: {QPullRefresh, SocialGenderTag, QTabs}
})
export default class SocialuniFollowView extends Vue {
  $refs: {
    pullRefresh: QPullRefresh
  }
  tabs = SocialuniFollowType.allFollowTypes
  tabsPageQueryUtil: SocialuniPageQueryUtil<SocialUserFollowDetailRO>[] = [new SocialuniPageQueryUtil(), new SocialuniPageQueryUtil()]
  currentTabIndex = 0

  loadMoreText = {
    contentdown: '点击显示更多',
    contentrefresh: '正在加载...',
    contentnomore: '没有更多数据了,点击刷新'
  }

  async manualPulldownRefresh() {
    await this.initQuery()
  }

  startPulldownRefresh() {
    this.$refs.pullRefresh.startPulldownRefresh()
  }

  endPulldownRefresh() {
    this.$refs.pullRefresh.endPulldownRefresh()
  }

  created() {
    this.tabsPageQueryUtil = [new SocialuniPageQueryUtil(FollowAPI.queryUserFollowsAPI), new SocialuniPageQueryUtil(FollowAPI.queryUserFollowsAPI)]

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

  async initQuery() {
    await this.tabsPageQueryUtil[this.currentTabIndex].initQuery(this.tabs[this.currentTabIndex])
    this.endPulldownRefresh()
  }

  openVip() {
    PageUtil.toVipPage()
  }

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

  autoChooseUseLocationQueryTalksHandler() {
    this.tabsPageQueryUtil[this.currentTabIndex].nextPageQuery(this.tabs[this.currentTabIndex])
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

}
</script>
