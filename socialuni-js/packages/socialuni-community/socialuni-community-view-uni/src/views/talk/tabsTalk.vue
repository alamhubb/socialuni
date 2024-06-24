<template>
  <div v-if="tabQueryUtils.length" class="flex-col h100p">
    <!--  <div v-if="talkTabs.length" class="flex-col h100p bg-primary">-->
    <!--    <q-tabs :tabs="talkTabs" v-model="current" type="bar" @input="tabsChange"-->
    <div class="flex-row px-sm mb-xss flex-none">
      <q-tabs :tabs="talkTabs" :value="currentTabIndex" type="line" @change="tabsChange"
              class="bd-radius flex-1 mr-sm">
        <template #default="{tab,index,value}">
          <div class="h30 px-xs row-all-center font-md"
               :class="{'font-md font-bold color-blue1':currentTabIndex===index}">{{
              tab.name
            }}
          </div>
        </template>
      </q-tabs>
      <div class="flex-none row-col-center">
        <q-icon icon="list-dot" size="20" @click="openTalkFilterDialog"></q-icon>
      </div>
    </div>

    <div class="flex-1 overflow-hidden">
      <q-pull-refresh ref="pullRefresh" @refresh="manualPulldownRefresh" class="h100p">
        <swiper :current="currentTabIndex" class="h100p"
                @change="talkSwiperChange">
          <swiper-item class="h100p" v-for="(item, swiperIndex) in talkTabs" :key="swiperIndex">
            <!--
            使用view实现的问题，没有scroll事件小程序上
            <div class="h100p bg-default" :class="[scrollEnable?'overflow-scroll':'overflow-hidden']" :scroll-y="scrollEnable" @scrolltolower="onreachBottom"
                  :lower-threshold="800"
                  @scroll.native="talksScrollEvent"
                  @scroll="talksScrollEvent"
            >-->

            <!--          首页展示区分不同类型，
                      圈子类型、关注类型、首页类型、同城类型-->
            <scroll-view class="h100p bd-radius-10 mx-sm overflow-hidden" style="width: calc(100% - 20px)"
                         :scroll-y="true" @scrolltolower="autoChooseUseLocationQueryTalks"
                         :scroll-top="talkTabs[swiperIndex].pageScrollTop"
                         :lower-threshold="800"
                         @scroll="talksScrollEvent">
              <!--          不放上面是因为，头部距离问题，这样会无缝隙，那样padding会在上面，始终空白-->
              <div class="pb-60"
                   v-if="tabQueryUtils[swiperIndex].listData.length || tabQueryUtils[swiperIndex].viewData.name!== followTabName">
                <talk-swipers class="px-mn pb-sm"
                              v-if="tabQueryUtils[swiperIndex].viewData.name === homeTabName && configShowSwipers"></talk-swipers>


                <div class="card mb-sm elevation-4 px-sm" v-if="tabQueryUtils[swiperIndex].viewData.circle">
                  <!--                创建自己的圈子-->
                  <div class="row-between-center">
                    <div>{{ tabQueryUtils[swiperIndex].viewData.name }}圈</div>
                    <!--                    圈主：客服-->
                    <div class="q-tag use-click"
                         @click="joinCircleGroupChat(tabQueryUtils[swiperIndex].viewData.circle)">进入群聊
                    </div>
                    <!--                  显示圈主，如果没有圈主，则显示，此圈没有圈主，申请成为圈主-->
                  </div>
                  <!--                <div>处对象圈子介绍</div>-->
                </div>

                <!--              <div v-else-if="tabQueryUtils[swiperIndex].viewData.type === 'circle'" class="card mb-sm elevation-4 px">
                                <div class="row-between-center mb-sm">
                                  <div>
                                    圈主：xxxx
                                  </div>
                                  <div class="row-col-center">
                                    <div class="color-sub">竞选详情</div>
                                    <div class="color-sub ml-md">圈子管理</div>
                                  </div>
                                </div>
                                <div class="row-col-center">
                                  小圈主：胺分散法，撒飞洒地方，阿斯蒂芬阿萨德，士大夫撒地方，
                                </div>
                              </div>-->


                <div v-for="(talk,index) in tabQueryUtils[swiperIndex].listData" :key="talk.id">
                  <talk-item :talk="talk"
                             :talk-tab-type="tabQueryUtils[swiperIndex].viewData.type"
                             @delete-talk="deleteTalk"
                  />
                </div>

                <!-- 下拉刷新组件 -->
                <div class="mt-xs">
                  <uni-load-more :status="tabQueryUtils[swiperIndex].loadMore"
                                 @click="clickOnreachBottom"
                                 :contentText="loadMoreText"></uni-load-more>
                </div>
              </div>
              <template v-else>
                <div v-if="user" class="row-center h500 pt-100 font-bold text-gray text-md">
                  您还没有关注其他人
                </div>
                <div v-else class="row-center h500 pt-100 font-bold text-gray text-md"
                     @click="toLoginVue">
                  您还没有登录，点击登录
                </div>
              </template>
            </scroll-view>
          </swiper-item>
        </swiper>
      </q-pull-refresh>
    </div>
    <!--            从附近哪里选择城市-->
    <!--            搜索栏左边加个筛选按钮可以筛选性别年龄-->
    <!--            除去搜索栏和导航栏的高度就是剩余高度-->

    <!--        默认附近，可以切换城市，城市-->
    <talk-operate @deleteTalk="deleteTalk"></talk-operate>

    <social-talk-filter-dialog ref="talkFilterDialog"
                               @confirm="startPullDown"></social-talk-filter-dialog>
  </div>
</template>

<script lang="ts">
import {Component, Vue, Watch, toNative} from 'vue-facing-decorator'
import TalkSwipers from './talkSwipers.vue'

import TalkOperate from './talkOperate.vue'
import QTab from 'qing-ui-uni/src/components/QTab/QTab.vue'
import QTabs from 'qing-ui/src/components/QTabs/QTabs.vue'
import QIcon from 'qing-ui/src/components/QIcon.vue'
import SocialTalkFilterDialog from "./SocialTalkFilterDialog.vue";
import QPullRefresh from "qing-ui-uni/src/components/QPullRefresh/QPullRefresh.vue";
import TalkTabVO from "socialuni-api-base/src/model/talk/SocialuniTalkTabRO";
import LoadMoreType from "socialuni-constant/constant/LoadMoreType";
import CommonUtil from "qing-util/src/util/CommonUtil";
import TalkVO from "socialuni-api-base/src/model/talk/TalkVO";
import Constants from "socialuni-constant/constant/Constant";
import SocialuniTalkTabCircleRO from "socialuni-api-base/src/model/community/circle/SocialuniTalkTabCircleRO";
import MsgUtil from "socialuni-app-sdk/src/util/MsgUtil";
import SocialuniTalkAPI from "socialuni-community-api/src/api/SocialuniTalkAPI";
import {socialTalkModule} from "socialuni-community-sdk/src/store/SocialTalkModule";
import {socialuniTagModule} from "socialuni-community-sdk/src/store/SocialTagModule";
import TalkQOFactory from "socialuni-community-sdk/src/factory/TalkQOFactory";
import {socialLocationModule} from "socialuni-community-sdk/src/store/SocialLocationModule";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import TalkItem from "socialuni-community-ui/src/components/talkItem/TalkItem.vue";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import UserMsgUtil from "socialuni-user-sdk/src/util/UserMsgUtil";
import UserPageUtil from "socialuni-user-sdk/src/util/UserPageUtil";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import SocialuniTalkListViewService from "socialuni-community-sdk/src/logic/service/SocialuniTalkListViewService";
import TalkFilterUtil from "socialuni-community-sdk/src/util/TalkFilterUtil";
import {socialCircleModule} from "socialuni-community-sdk/src/store/SocialCircleModule";
import SocialuniPageQueryUtil from "socialuni-api-base/src/model/common/SocialuniPageQueryUtil";

// todo 后台可控制是否显示轮播图
@toNative
@Component({
  components: {
    SocialTalkFilterDialog,
    QPullRefresh,
    QIcon,
    QTabs,
    QTab,
    TalkOperate,
    TalkSwipers,
    TalkItem
  }
})
export default class TabsTalk extends Vue {
  $refs: {
    pullRefresh: QPullRefresh
    talkFilterDialog: SocialTalkFilterDialog
  }

  get inputContentFocus() {
    return socialTalkModule.inputContentFocus
  }

  get location() {
    return socialLocationModule.location
  }

  // 轮播图
  get configShowSwipers() {
    return socialuniConfigModule.appMoreConfig.showSwipers
  }

  loadMoreText = {
    contentdown: '点击显示更多',
    contentrefresh: '正在加载...',
    contentnomore: '没有更多数据了,点击刷新'
  }

  get followTabName() {
    return socialuniConfigModule.appConfig.followTabName
  }

  get homeTabName() {
    return socialuniConfigModule.appConfig.homeTabName
  }

  get cityTabName() {
    return socialuniConfigModule.appConfig.cityTabName
  }


  // 用户登录后重新查询
  @Watch('user')
  watchUserChange() {
    //系统不为首次加载
    //必须有this.talkTabObj 且 不为首次加载才行
    if (this.curTalkTabObj && !this.curTabQueryUtil.queryQO.firstLoad) {
      //如果当前为关注，则重新查询,否则的话将关注列设置为首次查询
      this.startPullDown()
      //把非当前的设置为初始
      this.tabQueryUtils.filter(item => item.viewData.type !== this.curTalkTabObj.type).forEach(item => (item.queryQO.firstLoad = true))
    }
  }

  get tabQueryUtils() {
    return socialTalkModule.talkTabQueryUtils
  }

  get talkTabs() {
    return socialTalkModule.talkTabs
  }

  // talkTabs: TalkTabVO [] = []
  // 页面初始化模块
  // homeTypeObjs: HomeTypeTalkVO [] = []

  readonly talkCacheNum: number = 30


  // 生命周期
  created() {
    // this.tabQueryUtils = TalkAPI.queryHomeTalkTabsAPI()
    // LocationUtil.getCityByIpWebAPI()
    // 更新广告状态
    // 更新广告刷新时间
    this.updateShowAd()
    // 根据本地存储获取之前的 homeName
    // 有了位置才进行查询,因为查询同城需要位置信息
    // 获取位置，查询同城talks使用
  }

  mounted() {
  }

  // 供父组件调用，每次隐藏把数据缓存进storage
  tabsTalkOnHide() {
    // 存入store
    const storeTalkTabs: TalkTabVO[] = []
    this.tabQueryUtils.forEach(queryUtils => {
      const item = queryUtils.viewData
      const storeTalkTab: TalkTabVO = new TalkTabVO(item.name, item.type)
      storeTalkTab.firstLoad = queryUtils.queryQO.firstLoad
      storeTalkTab.scrollTop = item.scrollTop
      storeTalkTab.pageScrollTop = item.scrollTop
      storeTalkTab.talks = queryUtils.listData.slice(-this.talkCacheNum)
      storeTalkTabs.push(storeTalkTab)
    })
    //缓存记录本次推出时的默认值
    socialTalkModule.saveLastTalkTabs(storeTalkTabs, this.currentTabIndex, this.curTalkTabObj.type)
  }


  get user() {
    return socialuniUserModule.mineUser
  }

  openTalkFilterDialog() {
    this.$refs.talkFilterDialog.open()
  }

  //供父组件使用，不可删除
  initQuery() {
    //首次打开talk页面，获取用户位置用来查询
    // if (this.curTalkTabObj.firstLoad) {
    this.startPullDown()
    // }
  }

  //js触发下拉刷新效果
  startPullDown() {
    this.tabScrollToTop()
    this.$refs.pullRefresh.startPulldownRefresh()
  }

  tabScrollToTop() {
    this.curTalkTabObj.pageScrollTop = 0
    this.$nextTick(() => {
      this.curTalkTabObj.pageScrollTop = -1
    })
  }

  //用户手动执行下拉刷星
  private manualPulldownRefresh() {
    console.log('chufale手动更新')
    this.tabScrollToTop()
    this.clickOnreachBottom()
  }


  //点击不需要更新查询时间，查不出来就查不出来，万一是自己手动暂停了查询呢，而且如果重设时间会导致数据重复问题
  async clickOnreachBottom() {
    //停止查询方法
    if (this.curTabQueryUtil) {
      const talkQO = TalkQOFactory.getTalkQueryQO(this.curTalkTabObj.name, socialTalkModule.userGender, socialTalkModule.userMinAge, socialTalkModule.userMaxAge, socialuniTagModule.selectTagNames, socialCircleModule.circleName)
      await this.curTabQueryUtil.initQuery(talkQO)
      this.$refs.pullRefresh.endPulldownRefresh()
    }
  }

  // scroll-view到底部加载更多
  //如果用户开了定位，就获取经纬度去查询，如果用户没开启定位，就不使用经纬度，没必要每次都获取经纬度。
  async autoChooseUseLocationQueryTalks() {
    const curTabQueryUtil = this.curTabQueryUtil
    const talkQO = TalkQOFactory.getTalkQueryQO(this.curTalkTabObj.name, socialTalkModule.userGender, socialTalkModule.userMinAge, socialTalkModule.userMaxAge, socialuniTagModule.selectTagNames, socialCircleModule.circleName)
    if (curTabQueryUtil) {
      await curTabQueryUtil.loadNextPage(talkQO)
    }
  }

  get talks(): TalkVO[] {
    if (this.tabQueryUtils && this.tabQueryUtils.length) {
      return this.curTabQueryUtil.listData
    }
    return []
  }

  // 被举报后前台删除talk
  deleteTalk(talkId: string) {
    this.talks.splice(this.talks.findIndex(talk => talk.id === talkId), 1)
  }

  get curTabQueryUtil() {
    if (this.tabQueryUtils && this.tabQueryUtils.length) {
      return this.tabQueryUtils[this.currentTabIndex]
    } else {
      return null
    }
  }

  get curTalkTabObj() {
    if (this.tabQueryUtils && this.tabQueryUtils.length) {
      return this.tabQueryUtils[this.currentTabIndex].viewData
    } else {
      return null
    }
  }


  // 因为内部的滑动机制限制，请将tabs组件和swiper组件的current用不同变量赋值
  // tabs组件的current值，表示当前活动的tab选项
  // swiper组件的current值，表示当前那个swiper-item是活动的
  get currentTabIndex() {
    return socialTalkModule.currentTabIndex
  }

  // tabs通知swiper切换
  async tabsChange(index) {
    if (index === this.currentTabIndex) {
      this.tabScrollToTop()
      return
    }
    socialTalkModule.setCurTabIndexUpdateCircle(index)
  }

  // talkSwipe
  async talkSwiperChange(e) {
    const current = e.detail.current
    //首次加载可能为空
    if (current || current === 0) {
      const curTab = socialTalkModule.setCurTabIndexUpdateCircle(current)
      // 存入store
      // 切换时截取其他的只保留后20条
      this.tabQueryUtils.forEach((item, index) => {
        if (index !== current) {
          //截取20
          item.listData = item.listData.slice(-20)
          item.loadMore = LoadMoreType.more
        }
      })
      //如果首次加载，则需要查询
      if (curTab.firstLoad) {
        this.startPullDown()
        //不理解为什么首次加载要缓存
        // this.tabsTalkOnHide()
      }
    }
  }

  // 每次查询几条
  get lazyLoadNum(): number {
    return socialuniConfigModule.appMoreConfig[Constants.everyLoadNum] || Constants.everyLoadNum_number
  }

  showAd = false

  // 广告相关
  updateShowAd() {
    // 如果展示广告
    // 获取上次展示时间
    const lastDateStr: string = QingAppUtil.StorageUtil.get(Constants.lastShowAdTime)
    // 如果有上次展示时间
    if (lastDateStr) {
      const lastDateTime: number = new Date(lastDateStr).getTime()
      const curTime: number = new Date().getTime()
      // 如果已经过了上次展示时间间隔，则继续展示广告
      this.showAd = curTime - lastDateTime > this.showAdMinutes * Constants.minute
    } else {
      this.showAd = true
    }
  }

  // 默认30分钟展示1次
  get showAdMinutes(): number {
    return socialuniConfigModule.appMoreConfig[Constants.talkShowAdTimeInterval] || Constants.talkShowAdTimeIntervalDefault
  }

  get showAdIndexList(): number[] {
    return socialuniConfigModule.appMoreConfig[Constants.talkShowAdIndexListKey] || Constants.talkShowAdIndexAryDefault
  }

  @Watch('talks')
  watchTalks() {
    // 如果已有talk数量大于系统阀值，加载出来第2页后，就重置下次看广告时间
    if (this.showAd && this.talks.length >= 50) {
      QingAppUtil.StorageUtil.set(Constants.lastShowAdTime, String(new Date()))
    }
  }

  lastScrollTop = 0
  scrollTop = 0
  curScrollTop = 0
  timeout = null

  // app端兼容问题，滚动页面评论input不会失去焦点，需要手动控制
  talksScrollEvent({detail}) {
    // 如果处于获取焦点状态，则失去焦点
    if (socialTalkModule.inputContentFocus) {
      socialTalkModule.inputContentBlur()
    }
    // this.talkTabScroll(detail.scrollTop)
    //只有app端处理滚动隐藏显示tabbar逻辑，小程序平台一卡一卡的
    if (!socialuniSystemModule.isMp) {
      //记录当前位置
      this.curScrollTop = detail.scrollTop
      // this.throttleScroll()
    }
  }

  //为了每次保存后重新加载可以记录上次的位置
  /*talkTabScroll = CommonUtil.debounce((scrollTop) => {
      console.log('触发了滚动')
    this.curTalkTabObj.scrollTop = scrollTop
  }, 100)*/

  /*throttleScroll = CommonUtil.throttle(() => {
    //如果向下滚动超过50隐藏
    if (this.curScrollTop > (this.lastScrollTop + 500)) {
        console.log('触发了hide')
      uni.hideTabBar()
      //如果向上滚动超过20显示
    } else if (this.curScrollTop < (this.lastScrollTop - 500)) {
      uni.showTabBar()
      //如果小于100或等于0，显示
    } else if (this.curScrollTop < 100 || this.curScrollTop === 0) {
      uni.showTabBar()
    }
    //修改上次位置为当前位置
    this.lastScrollTop = this.curScrollTop
  }, 300)*/

  scrollHandler() {

  }

  toLoginVue() {
    UserPageUtil.toMinePage()
  }

  joinCircleGroupChat(circle: SocialuniTalkTabCircleRO) {
    // 登录才可以点赞
    if (this.user) {
      // socialChatModule.joinCircleGroupChat(circle)
    } else {
      UserMsgUtil.unLoginMessage()
    }
  }
}
</script>
