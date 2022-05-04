<template>
  <div class="h100p row-center">
    <div class="flex-col h100p w700">
      <view v-show="showTagSearch" class="h100p">
        <tag-search class="h100p" v-model="showTagSearch" @change="changeTag"
        ></tag-search>
      </view>
      <!--    不能使用100%，h5，不包含tabbar，尺寸计算不正确，所以需要使用h100vh-->
      <view v-show="!showTagSearch" class="flex-col h100vh">
        <q-navbar class="flex-none">
          <div class="row-col-center ml-xs mr-sm font-bold bg-click" @click="openCityPicker">
            <q-icon size="14" icon="map-fill"></q-icon>
            {{ location.adName }}
            <q-icon icon="mdi-chevron-right"></q-icon>
          </div>
          <q-search class="flex-1 mr-sm" @click.native="openTagSearchVue">
            <q-icon class="mx-5 text-gray" size="16" icon="search"></q-icon>
            <view v-if="selectTagName" class="row-col-center flex-auto" @click.stop="">
              <view class="q-tag round bg-green-plain light row-all-center">
                {{ selectTagName }}
              </view>
              <q-icon class="mr-sm text-gray row-all-center" icon="close" @click="deleteTag"></q-icon>
            </view>
            <input v-else :adjust-position="false" type="text"
                   placeholder="选择话题" confirm-type="search"/>
          </q-search>
          <!--        <view class="mr-sm" :class="{'text-theme':useFilters}">
                    <q-icon icon="mdi-filter-variant" size="28" @click="showFilterModel"></q-icon>
                  </view>-->
          <view v-if="user" class="position-relative mr-sm">
            <q-icon icon="bell-fill" @click="toNotifyVue" size="28"></q-icon>
            <u-badge :count="unreadNotifiesNum" size="mini"
                     :offset="[0, 0]" @click="toNotifyVue"></u-badge>
          </view>
          <view>
            <q-icon icon="plus-circle" size="28" @click="toTalkAdd"></q-icon>
          </view>
        </q-navbar>

        <!--  #ifdef APP-PLUS -->
        <!-- <ad class="bg-white mt-10 w100vw" adpid="1890536227"></ad>-->
        <!--  #endif -->

        <tabs-talk class="flex-1" ref="tabsTalk"></tabs-talk>
      </view>
      <msg-input>
      </msg-input>

      <q-city-picker ref="cityPicker" :value="location" @input="cityChange"></q-city-picker>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, Watch} from 'vue-property-decorator'

import TalkItem from '../SocialTalk/TalkItem.vue'
import PagePath from '../../constant/PagePath'
import TagVO from '../../model/community/tag/TagVO'
import RouterUtil from '../../utils/RouterUtil'
import PageUtil from '../../utils/PageUtil'
import TalkFilterUtil from '../../utils/TalkFilterUtil'
import UniUtil from '../../utils/UniUtil'
import TalkSwipers from '../SocialTalk/talkSwipers.vue'
import {
  socialAppStore, socialCircleModule,
  socialConfigStore,
  socialLocationModule,
  socialLocationStore,
  socialNotifyModule,
  socialNotifyStore, socialSystemModule,
  socialTagModule,
  socialTagStore,
  socialTalkModule, socialTalkStore,
  socialUserStore
} from '../../store'
import CenterUserDetailRO from '../../model/social/CenterUserDetailRO'
import TagSearch from '../SocialTalk/TagSearch.vue'
import TabsTalkVue from '../SocialTalk/tabsTalk.vue'
import QButton from '../../../qing-ui/components/QButton/QButton.vue'
import GenderType from '../../constant/GenderType'
import SocialuniConfig from '../../config/SocialuniConfig'
import MsgInput from '../MsgInput.vue'
import QNavbar from '../../../qing-ui/components/QNavbar/QNavbar.vue'
import QSearch from '../../../qing-ui/components/QSearch/QSearch.vue'
import QIcon from '../../../qing-ui/components/QIcon/QIcon.vue'
import QPopup from '../../../qing-ui/components/QPopup/QPopup.vue'
import QSlider from '../../../qing-ui/components/QSlider/QSlider.vue'
import ConfigMap from '@/socialuni/constant/ConfigMap'
import HomeSwiperVO from '@/socialuni/model/HomeSwiperVO'
import QTabs from '@/qing-ui/components/QTabs/QTabs.vue'
import DistrictVO from '@/socialuni/model/DistrictVO'
import QCityPicker from '@/socialuni/components/QCityPicker/QCityPicker.vue'
import UnreadNotifyVO from '@/socialuni/model/notify/UnreadNotifyVO'
import LoadMoreType from "@/socialuni/constant/LoadMoreType";
import TalkTabVO from "@/socialuni/model/talk/TalkTabVO";
import CommonUtil from "@/socialuni/utils/CommonUtil";
import TalkAPI from "@/socialuni/api/TalkAPI";
import TalkVO from "@/socialuni/model/talk/TalkVO";
import Constants from "@/socialuni/constant/Constant";
import StorageUtil from "@/socialuni/utils/StorageUtil";
import QPullRefresh from "@/qing-ui/components/QPullRefresh/QPullRefresh.vue";
import SocialTalkFilterDialog from "@/socialuni/components/SocialTalk/SocialTalkFilterDialog.vue";

// todo 后台可控制是否显示轮播图

@Component({
  components: {
    QCityPicker,
    QTabs,
    QSlider,
    QPopup,
    QIcon,
    QSearch,
    QNavbar,
    MsgInput,
    QButton,
    TagSearch,
    TabsTalk: TabsTalkVue,
    TalkSwipers,
    TalkItem
  }
})
export default class SocialTalkPcPage extends Vue {
  public $refs!: {
    tabsTalk: TabsTalkVue;
    pullRefresh: QPullRefresh
    talkFilterDialog: SocialTalkFilterDialog
    cityPicker: QCityPicker
  }
  @socialTagStore.State('tags') readonly tags: TagVO []
  @socialUserStore.State('user') user: CenterUserDetailRO
  // 轮播图
  // 点击通知去通知页
  @socialNotifyStore.Getter('unreadNotifies') unreadNotifies: UnreadNotifyVO[]
  // 轮播图
  @socialConfigStore.State('showSwipers') configShowSwipers: boolean
  @socialAppStore.State('homeSwipers') homeSwipers: HomeSwiperVO[]
  @socialConfigStore.Getter(ConfigMap.swiperHeightKey) swiperHeight: number
  @socialTagStore.State('selectTagName') selectTagName: string
  @socialLocationStore.Getter('location') location: DistrictVO

  current = 0
  // tag 相关
  showTagSearch = false
  // 筛选相关
  rangeMin: number = TalkFilterUtil.minAgeFilterDefault
  rangMax: number = TalkFilterUtil.maxAgeFilterDefault
  // 组件内的值
  genderTypeValue: string = socialTalkModule.userGender
  appGender: string = SocialuniConfig.appGenderType
  GenderTypeAll = GenderType.all
  rangeValue: number[] = [socialTalkModule.userMinAge, socialTalkModule.userMaxAge]
  unreadNotifiesNum = 0
  // filter内容
  showFilter = false
  // 滚动超过轮播图隐藏轮播图，scroll-view开启滚动
  scrollEnable = false

  switchCircleTabValue(event: { detail: any }) {
    const detail: {
      current: number,
      source: number
    } = event.detail
    this.current = detail.current
  }

  get talkTabsTop() {
    if (this.configShowSwipers && this.homeSwipers && this.homeSwipers.length) {
      return this.swiperHeight + 10
    }
    return 0
  }

  get visibleGenders() {
    if (this.user) {
      if (this.user.gender === GenderType.girl) {
        return GenderType.talkQueryGirlEnums
      } else {
        return GenderType.talkQueryBoyEnums
      }
    }
    return GenderType.talkQueryEnums
  }

  // life
  mounted() {
    this.pageMounted()
  }

  pageMounted() {
    UniUtil.showShareMenu()
    // 这里是不是有问题应该选择异性
    // 指的是用户选择的筛选性别
    this.initQuery()
    this.socialTalkScroll({scrollTop: 0})
  }

  tabsTalkOnHide() {
    this.$refs.tabsTalk.tabsTalkOnHide()
  }

  @Watch('homeSwipers')
  homeSwipersWatch() {
    this.socialTalkScroll({scrollTop: 0})
  }

  socialTalkScroll(e) {
    // 只有开启了轮播图，才需要控制下方滚动
    if (this.configShowSwipers && this.homeSwipers && this.homeSwipers.length) {
      // +5点余量以防万一
      const scrollTop = e.scrollTop + 3
      // 只有不可滚动时，且大于选项卡高度，才改为可用
      if ((!this.scrollEnable) && scrollTop >= this.talkTabsTop) {
        this.scrollEnable = true
      } else if (this.scrollEnable && scrollTop < this.talkTabsTop) {
        this.scrollEnable = false
      }
    } else {
      // 如果不显示轮播图，则下方talks一定可以滚动
      this.scrollEnable = true
    }
  }

  // 必须这么写否则不生效
  @Watch('unreadNotifies')
  unreadNotifiesWatch() {
    this.unreadNotifiesNum = this.unreadNotifies.length
  }

  // 去除页面初始化的，初始化查询
  initQuery() {
    // this.$refs.tabsTalk.initQuery()
    this.$nextTick(() => {
      //首次打开talk页面，获取用户位置用来查询
      // locationModule.appLunchInitDistrict().then(() => {
      this.$refs.tabsTalk.initQuery()
      // })
    })
  }

  openTagSearchVue() {
    socialTagModule.getTagTypesAction()
    this.showTagSearch = true
  }

  @Watch('selectTagName')
  selectTagNameWatch() {
    this.initQuery()
  }

  // tag
  changeTag(tag: TagVO) {
    socialTagModule.setSelectTagName(tag.name)
    this.initQuery()
  }

  deleteTag() {
    socialTagModule.setSelectTagName(null)
    this.initQuery()
  }

  toNotifyVue() {
    socialNotifyModule.queryUnreadNotifiesAndUpdateHasReadAction()
    RouterUtil.navigateTo(PagePath.notify)
  }

  // 点击加号去新增talk
  toTalkAdd() {
    PageUtil.toTalkAddPage()
  }

  openCityPicker() {
    this.$refs.cityPicker.open()
  }

  cityChange(district: DistrictVO) {
    socialLocationModule.setLocation(district)
    this.$refs.tabsTalk.autoChooseUseLocationQueryTalks(true)
  }

  @socialTalkStore.State('inputContentFocus') inputContentFocus: boolean
  @socialLocationStore.Getter('location') location: DistrictVO
  // 轮播图
  @socialConfigStore.State('showSwipers') configShowSwipers: boolean

  readonly loading: string = LoadMoreType.loading
  loadMoreText = {
    contentdown: '点击显示更多',
    contentrefresh: '正在加载...',
    contentnomore: '没有更多数据了,点击刷新'
  }

  // 用户登录后重新查询
  @Watch('user')
  watchUserChange() {
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
  created() {
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

  mounted() {
    // 获取元素高度，用来计算scroll-view高度
    this.getTabBarTop()
  }

  // 供父组件调用，每次隐藏把数据缓存进storage
  tabsTalkOnHide() {
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

  getTabBarTop() {
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

  get HotTags() {
    return this.tags.slice(0, 10)
  }

  //供父组件使用，不可删除
  initQuery() {
    //首次打开talk页面，获取用户位置用来查询
    socialLocationModule.appLunchInitDistrict().then(() => {
      this.startPullDown()
    })
  }

  openTalkFilterDialog() {
    this.$refs.talkFilterDialog.open()
  }

  // scroll-view到底部加载更多
  onreachBottom(e) {
    // 只要不是没有了就还可以加载
    if (this.talkTabObj.loadMore === LoadMoreType.more) {
      this.autoChooseUseLocationQueryTalks(false)
    }
  }

  //如果用户开了定位，就获取经纬度去查询，如果用户没开启定位，就不使用经纬度，没必要每次都获取经纬度。
  async autoChooseUseLocationQueryTalks(firstLoad?: boolean) {
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
  async queryTalks(firstLoad?: boolean) {
    if (firstLoad) {
      this.refreshQueryDate()
    }
    const talkTabObj = this.talkTabObj
    //只有在传false时校验后面的
    const fistLoad = firstLoad || talkTabObj.firstLoad
    // query condition
    const talkIds: number[] = (fistLoad ? [] : this.talkIds)
    CommonUtil.delayTime(0).then(() => {
      talkTabObj.firstLoad = false
    })
    return TalkAPI.queryTalksAPI(talkIds, socialTagModule.selectTagIds, talkTabObj.type, socialTalkModule.userGender, socialTalkModule.userMinAge, socialTalkModule.userMaxAge, this.queryTime, socialCircleModule.circleName, socialTagModule.selectTagNames).then((res: any) => {
      // 如果不是上拉加载，则是下拉刷新，则停止下拉刷新动画
      if (talkTabObj.loadMore === LoadMoreType.loading) {
        if (res.data && res.data.length) {
          if (fistLoad) {
            talkTabObj.talks = res.data
          } else {
            talkTabObj.talks.push(...res.data)
          }
        }
        // 如果还有大于等于10个就还可以加载
        //scroll-view的坑，如果不这么写，同步修改的话，会立马触发下次滚动到底部事件
        CommonUtil.delayTime(100).then(() => {
          // 如果还有大于等于10个就还可以加载
          if (res.data && res.data.length >= this.lazyLoadNum) {
            talkTabObj.loadMore = LoadMoreType.more
          } else {
            // 否则没有了
            talkTabObj.loadMore = LoadMoreType.noMore
          }
        })
      }
    }).catch(() => {
      talkTabObj.loadMore = LoadMoreType.more
    })
  }

  async queryEnd() {
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

  startPullDown() {
    this.$refs.pullRefresh.startPulldownRefresh()
  }

  refreshQueryDate() {
    this.queryTime = new Date()
  }

  // 展示的talks
  get talkIds() {
    if (this.talks.length) {
      return this.talks.map(item => item.id)
    }
    return []
  }

  get talks(): TalkVO[] {
    if (this.talkTabs && this.talkTabs.length) {
      return this.talkTabObj.talks
    }
    return []
  }

  // 被举报后前台删除talk
  deleteTalk(talkId: number) {
    this.talks.splice(this.talks.findIndex(talk => talk.id === talkId), 1)
  }

  get talkTabObj() {
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
  tabsChange(index) {
    if (index === this.currentTabIndex) {
      this.scrollTop = 0
      this.startPullDown()
      return
    }
    socialTalkModule.setCurTabIndexUpdateCircle(index)
  }

  // talkSwipe
  async talkSwiperChange(e) {
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

  openCityPicker() {
    this.$refs.cityPicker.open()
  }

  cityChange(district: DistrictVO) {
    socialLocationModule.setLocation(district)
    this.startPullDown()
  }

  @socialConfigStore.State('appConfig') readonly appConfig: object

  // 每次查询几条
  get lazyLoadNum(): number {
    return this.appConfig[Constants.everyLoadNum] || Constants.everyLoadNum_number
  }

  showAd = false

  // 广告相关
  updateShowAd() {
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
  get showAdMinutes(): number {
    return this.appConfig[Constants.talkShowAdTimeInterval] || Constants.talkShowAdTimeIntervalDefault
  }

  get showAdIndexList(): number[] {
    return this.appConfig[Constants.talkShowAdIndexListKey] || Constants.talkShowAdIndexAryDefault
  }

  @Watch('talks')
  watchTalks() {
    // 如果已有talk数量大于系统阀值，加载出来第2页后，就重置下次看广告时间
    if (this.showAd && this.talks.length >= 50) {
      StorageUtil.set(Constants.lastShowAdTime, String(new Date()))
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
    //只有app端处理滚动隐藏显示tabbar逻辑，小程序平台一卡一卡的
    if (!socialSystemModule.isMp) {
      //记录当前位置
      this.curScrollTop = detail.scrollTop
      this.throttleScroll()
    }
  }

  throttleScroll = CommonUtil.throttle(this.scrollHandler, 300)

  scrollHandler() {
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

  toLoginVue() {
    PageUtil.toMinePage()
  }
}
</script>
