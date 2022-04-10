<template>
  <view v-if="talkTabs.length" class="flex-col h100p">
    <!--  <view v-if="talkTabs.length" class="flex-col h100p bg-primary">-->
    <!--    <q-tabs :tabs="talkTabs" v-model="current" type="bar" @input="tabsChange"-->
    <div class="flex-row px-sm mb-xss">
      <q-tabs :tabs="talkTabs" :value="currentTabIndex" type="line" @input="tabsChange"
              class="bd-radius flex-1 mr-sm">
        <template #default="{tab,index,value}">
          <view class="h30 px-xs row-all-center font-md" :class="{'font-md':value===index}">{{ tab.name }}</view>
        </template>
      </q-tabs>
      <div class="flex-none row-col-center">
        <q-icon icon="list-dot" size="20" @click="openTalkFilterDialog"></q-icon>
      </div>
    </div>

    <q-pull-refresh ref="pullRefresh" @refresh="queryEnd">
      <swiper :current="currentTabIndex"
              :style="{
              'height':'calc(100vh - '+talksListHeightSub+'px)',
            }"
              @change="talkSwiperChange">
        <swiper-item v-for="(item, swiperIndex) in talkTabs" :key="swiperIndex">
          <!--
          使用view实现的问题，没有scroll事件小程序上
          <view class="h100p bg-default" :class="[scrollEnable?'overflow-scroll':'overflow-hidden']" :scroll-y="scrollEnable" @scrolltolower="onreachBottom"
                :lower-threshold="800"
                @scroll.native="talksScrollEvent"
                @scroll="talksScrollEvent"
          >-->

          <!--          首页展示区分不同类型，
                    圈子类型、关注类型、首页类型、同城类型-->

          <scroll-view class="h100p" :scroll-y="scrollEnable" @scrolltolower="onreachBottom"
                       :lower-threshold="800"
                       @scroll="talksScrollEvent">
            <!--          不放上面是因为，头部距离问题，这样会无缝隙，那样padding会在上面，始终空白-->
            <div class="px-sm pb-60 bg-theme3"
                 v-if="talkTabs[swiperIndex].talks.length || talkTabs[swiperIndex].type !== 'follow'">
              <talk-swipers v-if="talkTabs[swiperIndex].type === 'home' && configShowSwipers"></talk-swipers>

<!--              <div v-else-if="talkTabs[swiperIndex].type === 'circle'" class="card mb-sm elevation-4 px">
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


              <view v-for="(talk,index) in talkTabs[swiperIndex].talks" :key="talk.id">
                <talk-item :talk="talk"
                           :talk-tab-type="talkTabObj.type"
                           @deleteTalk="deleteTalk"
                />
                <!-- app端广告有问题-->
                <!--  #ifdef APP-PLUS -->
                <!--<view v-if="showAd&&showAdIndexList.includes(index)" class="mb-5">
                  <ad class="bg-white" adpid="1890536227"></ad>
                </view>-->
                <!--  #endif -->
                <!--wx平台显示的广告-->
                <!--  #ifdef MP-WEIXIN -->
                <ad v-if="showAd&&showAdIndexList.includes(index)"
                    class="bg-white mb-5" unit-id="adunit-65c8911d279d228f" ad-type="video" ad-theme="white"></ad>
                <!--  #endif -->

                <!--qq平台显示的广告-->
                <!--  #ifdef MP-QQ -->
                <ad v-if="showAd&&showAdIndexList.includes(index)"
                    class="bg-white mb-5" unit-id="bcc21923107071ac3f8aa076c7e00229" type="card"></ad>
                <!--  #endif -->

                <!--头条平台显示的广告-->
                <!--  #ifdef MP-TOUTIAO -->
                <ad v-if="showAd&&showAdIndexList.includes(index)"
                    class="bg-white mb-5" type="banner video large" unit-id="3snract0gqnc3fn16d"></ad>
                <!--  #endif -->
              </view>

              <!-- 下拉刷新组件 -->
              <view class="mt-xs">
                <uni-load-more :status="talkTabs[swiperIndex].loadMore" @click.native="queryEnd"
                               :contentText="loadMoreText"></uni-load-more>
              </view>
            </div>
            <template v-else>
              <view v-if="user" class="row-center h500 pt-100 font-bold text-gray text-md">
                您还没有关注其他人
              </view>
              <view v-else class="row-center h500 pt-100 font-bold text-gray text-md" @click="toLoginVue">
                您还没有登录，点击登录
              </view>
            </template>
          </scroll-view>
        </swiper-item>
      </swiper>
    </q-pull-refresh>
    <!--            从附近哪里选择城市-->
    <!--            搜索栏左边加个筛选按钮可以筛选性别年龄-->
    <!--            除去搜索栏和导航栏的高度就是剩余高度-->

    <!--        默认附近，可以切换城市，城市-->

    <q-city-picker ref="cityPicker" :value="location" @input="cityChange"></q-city-picker>

    <talk-operate @deleteTalk="deleteTalk"></talk-operate>

    <social-talk-filter-dialog ref="talkFilterDialog"
                               @confirm="autoChooseUseLocationQueryTalks(true)"></social-talk-filter-dialog>
  </view>
</template>

<script lang="ts">
import { Component, Prop, Vue, Watch } from 'vue-property-decorator'
import TalkVO from '../../model/talk/TalkVO'
import TalkAPI from '../../api/TalkAPI'
import CenterUserDetailRO from '../../model/social/CenterUserDetailRO'

import TalkItem from './TalkItem.vue'
import LoadMoreType from '../../constant/LoadMoreType'
import DistrictVO from '../../model/DistrictVO'
import Constants from '../../constant/Constant'
import StorageUtil from '../../utils/StorageUtil'
import TalkTabVO from '../../model/talk/TalkTabVO'
import CommonUtil from '../../utils/CommonUtil'
import TalkSwipers from './talkSwipers.vue'
import {
  socialCircleModule,
  socialConfigStore,
  socialLocationModule,
  socialLocationStore,
  socialSystemModule,
  socialTagModule,
  socialTagStore,
  socialTalkModule,
  socialTalkStore,
  socialUserStore
} from '../../store'
import TalkOperate from './talkOperate.vue'
import QTab from '../../../qing-ui/components/QTab/QTab.vue'
import QTabs from '../../../qing-ui/components/QTabs/QTabs.vue'
import QIcon from '../../../qing-ui/components/QIcon/QIcon.vue'
import PageUtil from '../../utils/PageUtil'
import QPullRefresh from '@/qing-ui/components/QPullRefresh/QPullRefresh.vue'
import TagVO from '@/socialuni/model/community/tag/TagVO'
import SocialTalkFilterDialog from '@/socialuni/components/SocialTalk/SocialTalkFilterDialog.vue'
import QCityPicker from '@/socialuni/components/QCityPicker/QCityPicker.vue'


// todo 后台可控制是否显示轮播图

@Component({
  components: {
    QCityPicker,
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
export default class TabsTalkPage extends Vue {
  $refs: {
    pullRefresh: QPullRefresh
    talkFilterDialog: SocialTalkFilterDialog
    cityPicker: QCityPicker
  }
  @socialTalkStore.State('inputContentFocus') inputContentFocus: boolean
  @socialLocationStore.Getter('location') location: DistrictVO
  // 轮播图
  @socialConfigStore.State('showSwipers') configShowSwipers: boolean

  @Prop() readonly scrollEnable: boolean
  readonly loading: string = LoadMoreType.loading
  loadMoreText = {
    contentdown: '点击显示更多',
    contentrefresh: '正在加载...',
    contentnomore: '没有更多数据了,点击刷新'
  }

  // 用户登录后重新查询
  @Watch('user')
  watchUserChange () {
    //系统不为首次加载
    //必须有this.talkTabObj 且 不为首次加载才行
    if (this.talkTabObj && !this.talkTabObj.firstLoad) {
      //如果当前为关注，则重新查询,否则的话将关注列设置为首次查询
      this.startPullDown()
      //把非当前的设置为初始
      this.talkTabs.filter(item => item.type !== this.talkTabObj.type).forEach(item => (item.firstLoad = true))
    }
  }

  @socialTalkStore.State('talkTabs') readonly talkTabs: TalkTabVO []
  // talkTabs: TalkTabVO [] = []
  // 页面初始化模块
  // homeTypeObjs: HomeTypeTalkVO [] = []

  @socialConfigStore.Getter('talkCacheNum') readonly talkCacheNum: number

  queryTime = new Date()


  // 生命周期
  created () {
    // this.talkTabs = TalkAPI.queryHomeTalkTabsAPI()
    // LocationUtil.getCityByIpWebAPI()
    // 更新广告状态
    // 更新广告刷新时间
    this.updateShowAd()
    this.queryTime = new Date()
    // 根据本地存储获取之前的 homeName
    // 有了位置才进行查询,因为查询同城需要位置信息
    // 获取位置，查询同城talks使用
  }

  mounted () {
    // 获取元素高度，用来计算scroll-view高度
    this.getTabBarTop()
  }

  // 供父组件调用，每次隐藏把数据缓存进storage
  tabsTalkOnHide () {
    // 存入store
    const storeTalkTabs: TalkTabVO[] = []
    this.talkTabs.forEach(item => {
      const storeTalkTab: TalkTabVO = new TalkTabVO(item.name, item.type)
      storeTalkTab.talks = item.talks.slice(0, this.talkCacheNum)
      storeTalkTabs.push(storeTalkTab)
    })
    //缓存记录本次推出时的默认值
    socialTalkModule.saveLastTalkTabs(storeTalkTabs, this.currentTabIndex, this.talkTabObj.type)
  }


  tabsHeight = 0
  // 去除的高度,单位px
  talksListHeightSub = 0

  getTabBarTop () {
    this.tabsHeight = 40
    // h5有头顶和下边导航栏都算了高度
    // #ifdef H5
    //tab的高度加上导航栏的高度,h5+ 50 底部
    this.talksListHeightSub = socialSystemModule.navBarHeight + this.tabsHeight
    // #endif
    // #ifndef H5
    this.talksListHeightSub = socialSystemModule.statusBarHeight + socialSystemModule.navBarHeight + this.tabsHeight
    // #endif
  }


  @socialUserStore.State('user') user: CenterUserDetailRO
  @socialTagStore.State('tags') tags: TagVO[]

  // 页面是否为首次查询

  get HotTags () {
    return this.tags.slice(0, 10)
  }

  //供父组件使用，不可删除
  initQuery () {
    //首次打开talk页面，获取用户位置用来查询
    socialLocationModule.appLunchInitDistrict().then(() => {
      this.startPullDown()
    })
  }

  openTalkFilterDialog () {
    this.$refs.talkFilterDialog.open()
  }

  // scroll-view到底部加载更多
  onreachBottom (e) {
    // 只要不是没有了就还可以加载
    if (this.talkTabObj.loadMore === LoadMoreType.more) {
      this.autoChooseUseLocationQueryTalks(false)
    }
  }

  //如果用户开了定位，就获取经纬度去查询，如果用户没开启定位，就不使用经纬度，没必要每次都获取经纬度。
  async autoChooseUseLocationQueryTalks (firstLoad?: boolean) {
    //只有不为加载中才可以加载
    //手动刷新可以刷新，或者为
    if (this.talkTabObj.loadMore === LoadMoreType.more || firstLoad) {
      // 执行正在加载动画
      this.talkTabObj.loadMore = LoadMoreType.loading
      // if (firstLoad) {
      await this.queryTalks(firstLoad)
      // }
      //首次时加载地理位置就好了，之后就是点击定位的时候加载
      /*if (locationModule.openLocation) {
        this.requestUseLocationQueryTalks(firstLoad)
      } else {
        this.queryTalks(firstLoad)
      }*/
    }
  }

  // 默认地理位置是北京，以后可以根据ip获取当前城市
  // 话题的话显示最热门的话题，且只查询包含话题的动态
  async queryTalks (firstLoad?: boolean) {
    if (firstLoad) {
      this.refreshQueryDate()
    }
    //只有在传false时校验后面的
    const fistLoad = firstLoad || this.talkTabObj.firstLoad
    // query condition
    const talkIds: number[] = (fistLoad ? [] : this.talkIds)
    CommonUtil.delayTime(0).then(() => {
      this.talkTabObj.firstLoad = false
    })
    return TalkAPI.queryTalksAPI(talkIds, socialTagModule.selectTagIds, this.talkTabObj.type, socialTalkModule.userGender, socialTalkModule.userMinAge, socialTalkModule.userMaxAge, this.queryTime, socialCircleModule.circleName, socialTagModule.selectTagNames).then((res: any) => {
      // 如果不是上拉加载，则是下拉刷新，则停止下拉刷新动画
      if (this.talkTabObj.loadMore === LoadMoreType.loading) {
        if (res.data && res.data.length) {
          if (fistLoad) {
            this.talkTabObj.talks = res.data
          } else {
            this.talkTabObj.talks.push(...res.data)
          }
        }
        // 如果还有大于等于10个就还可以加载
        //scroll-view的坑，如果不这么写，同步修改的话，会立马触发下次滚动到底部事件
        CommonUtil.delayTime(100).then(() => {
          // 如果还有大于等于10个就还可以加载
          if (res.data && res.data.length >= this.lazyLoadNum) {
            this.talkTabObj.loadMore = LoadMoreType.more
          } else {
            // 否则没有了
            this.talkTabObj.loadMore = LoadMoreType.noMore
          }
        })
      }
    }).catch(() => {
      this.talkTabObj.loadMore = LoadMoreType.more
    })
  }

  async queryEnd () {
    //停止查询方法
    const talkTab = this.talkTabObj
    if (talkTab) {
      //如果正在查询，则更改状态为加载更多
      const loadMoreState = talkTab.loadMore
      if (loadMoreState === LoadMoreType.loading) {
        talkTab.loadMore = LoadMoreType.more
      } else {
        await this.autoChooseUseLocationQueryTalks(true)
        this.$refs.pullRefresh.endPulldownRefresh()
      }
    }
  }

  startPullDown () {
    this.$refs.pullRefresh.startPulldownRefresh()
  }

  refreshQueryDate () {
    this.queryTime = new Date()
  }

  // 展示的talks
  get talkIds () {
    if (this.talks.length) {
      return this.talks.map(item => item.id)
    }
    return []
  }

  get talks (): TalkVO[] {
    if (this.talkTabs && this.talkTabs.length) {
      return this.talkTabObj.talks
    }
    return []
  }

  // 被举报后前台删除talk
  deleteTalk (talkId: number) {
    this.talks.splice(this.talks.findIndex(talk => talk.id === talkId), 1)
  }

  get talkTabObj () {
    if (this.talkTabs && this.talkTabs.length) {
      return this.talkTabs[this.currentTabIndex]
    } else {
      return null
    }
  }

  // 因为内部的滑动机制限制，请将tabs组件和swiper组件的current用不同变量赋值
  @socialTalkStore.State('currentTabIndex') currentTabIndex: number
  // tabs组件的current值，表示当前活动的tab选项
  // swiper组件的current值，表示当前那个swiper-item是活动的

  // tabs通知swiper切换
  tabsChange (index) {
    if (index === this.currentTabIndex) {
      this.startPullDown()
      return
    }
    socialTalkModule.setCurTabIndexUpdateCircle(index)
  }

  // talkSwipe
  async talkSwiperChange (e) {
    const current = e.detail.current
    const curTab = socialTalkModule.setCurTabIndexUpdateCircle(current)
    // 存入store
    // 切换时截取其他的只保留后20条
    this.talkTabs.forEach((item, index) => {
      if (index !== current) {
        //截取20
        item.talks = item.talks.slice(-20)
        item.loadMore = LoadMoreType.more
      }
    })
    //如果首次加载，则需要查询
    if (curTab.firstLoad) {
      await this.autoChooseUseLocationQueryTalks(true)
      this.tabsTalkOnHide()
    }
  }

  openCityPicker () {
    this.$refs.cityPicker.open()
  }

  cityChange (district: DistrictVO) {
    socialLocationModule.setLocation(district)
    this.startPullDown()
  }

  @socialConfigStore.State('appConfig') readonly appConfig: object

  // 每次查询几条
  get lazyLoadNum (): number {
    return this.appConfig[Constants.everyLoadNum] || Constants.everyLoadNum_number
  }

  showAd = false

  // 广告相关
  updateShowAd () {
    // 如果展示广告
    // 获取上次展示时间
    const lastDateStr: string = StorageUtil.get(Constants.lastShowAdTime)
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
  get showAdMinutes (): number {
    return this.appConfig[Constants.talkShowAdTimeInterval] || Constants.talkShowAdTimeIntervalDefault
  }

  get showAdIndexList (): number[] {
    return this.appConfig[Constants.talkShowAdIndexListKey] || Constants.talkShowAdIndexAryDefault
  }

  @Watch('talks')
  watchTalks () {
    // 如果已有talk数量大于系统阀值，加载出来第2页后，就重置下次看广告时间
    if (this.showAd && this.talks.length >= 50) {
      StorageUtil.set(Constants.lastShowAdTime, String(new Date()))
    }
  }

  lastScrollTop = 0
  curScrollTop = 0
  timeout = null

  // app端兼容问题，滚动页面评论input不会失去焦点，需要手动控制
  talksScrollEvent ({ detail }) {
    // 如果处于获取焦点状态，则失去焦点
    if (socialTalkModule.inputContentFocus) {
      socialTalkModule.inputContentBlur()
    }
    //只有app端处理滚动隐藏显示tabbar逻辑，小程序平台一卡一卡的
    if (!socialSystemModule.isMp) {
      //记录当前位置
      this.curScrollTop = detail.scrollTop
      this.throttleScroll()
    }
  }

  throttleScroll = CommonUtil.throttle(this.scrollHandler, 300)

  scrollHandler () {
    //如果向下滚动超过50隐藏
    if (this.curScrollTop > (this.lastScrollTop + 500)) {
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
  }

  toLoginVue () {
    PageUtil.toMinePage()
  }
}
</script>
