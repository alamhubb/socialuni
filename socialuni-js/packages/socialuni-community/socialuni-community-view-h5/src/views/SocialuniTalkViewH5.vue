<template>
  <div class="h100p overflow-auto" v-infinite-scroll="scrollToLower" :infinite-scroll-immediate="false"
       :infinite-scroll-distance="200"
       :infinite-scroll-delay="200"
  >
    <div class="mg-x-auto w1200">
      <div class="row-center">
        <!--      <q-nav-menu/>-->
        <el-menu class="w200 flex-none br position-sticky top-0 h100p socialuni-community-view-left-menu mr-sm">
          <q-enum-link v-for="tab in talkTabs" :to="'/community?tab='+tab.name">
            <el-menu-item :index="tab.name">
              <q-icon icon="mdi-send" class="color-content mr-xs mdi-rotate-315" size="12"></q-icon>
              {{ tab.name }}
            </el-menu-item>
          </q-enum-link>
          <q-enum-link v-for="circle in mineCirclesTop10" :to="'/community?circle='+circle">
            <el-menu-item :index="circle">
              <q-icon icon="mdi-send" class="color-content mr-xs mdi-rotate-315" size="12"></q-icon>
              {{ circle }}
            </el-menu-item>
          </q-enum-link>
        </el-menu>
        <div class="w600">
          {{ pageQueryUtil.queryQO }}
          {{ pageQueryUtil.num }}
          {{ talksNew.length }}
          <el-button @click="pageQueryUtil.num++">fasdfasd</el-button>
          <!--          不放上面是因为，头部距离问题，这样会无缝隙，那样padding会在上面，始终空白-->
          <div v-for="(talk,index) in talksNew" :key="talk.id">
            <talk-item :talk="talk"
                       :talk-tab-type="tabName"
                       @delete-talk="deleteTalk"
            />
          </div>

          <msg-input class="w600"></msg-input>
        </div>
        <talk-operate @deleteTalk="deleteTalk"></talk-operate>

        <social-talk-filter-dialog ref="talkFilterDialog"
                                   @confirm="startPullDown"></social-talk-filter-dialog>
        <!--      <socialuni-comment-input-dialog ref="commentDialog"></socialuni-comment-input-dialog>-->
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, Watch, toNative} from 'vue-facing-decorator'
import QTabs from 'qing-ui/src/components/QTabs/QTabs.vue'
import QIcon from 'qing-ui/src/components/QIcon.vue'
import TalkTabVO from "socialuni-api-base/src/model/talk/SocialuniTalkTabRO";
import LoadMoreType from "socialuni-constant/constant/LoadMoreType";
import CommonUtil from "qing-util/src/util/CommonUtil";
import TalkVO from "socialuni-api-base/src/model/talk/TalkVO";
import Constants from "socialuni-constant/constant/Constant";
import SocialuniTalkTabCircleRO from "socialuni-api-base/src/model/community/circle/SocialuniTalkTabCircleRO";
import SocialuniTalkAPI from "socialuni-community-api/src/api/SocialuniTalkAPI";
import {socialTalkModule} from "socialuni-community-sdk/src/store/SocialTalkModule";
import {socialuniTagModule} from "socialuni-community-sdk/src/store/SocialTagModule";
import TalkQOFactory from "socialuni-community-sdk/src/factory/TalkQOFactory";
import {socialLocationModule} from "socialuni-community-sdk/src/store/SocialLocationModule";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import UserMsgUtil from "socialuni-user-sdk/src/util/UserMsgUtil";
import UserPageUtil from "socialuni-user-sdk/src/util/UserPageUtil";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import TalkItem from "socialuni-community-ui/src/components/talkItem/TalkItem.vue";
import SocialuniPageQueryUtil from "socialuni-api-base/src/model/common/SocialuniPageQueryUtil";
import SocialuniUserExtendDetailRO from "socialuni-expand-api/src/model/SocialuniUserExtendDetailRO";
import SocialuniUserExtendFriendQueryQO from "socialuni-api-base/src/model/user/SocialuniUserExtendFriendQueryQO";
import SocialuniExpandAPI from "socialuni-expand-api/src/api/SocialuniExpandAPI";
import TalkQueryVO from "socialuni-api-base/src/model/talk/TalkQueryVO";
import {socialCircleModule} from "socialuni-community-sdk/src/store/SocialCircleModule";
import TalkTabType from "socialuni-constant/constant/TalkTabType";
import QEnumLink from 'qing-ui-h5/src/components/QEnumLink.vue'
import CommonEventUtil from "qingjs/src/util/CommonEventUtil";
import SocialuniImEventKey from "socialuni-im-api/src/constant/SocialuniMusicEventConst";
import JsonUtil from "qing-util/src/util/JsonUtil";
import QNavMenu from "qing-ui-h5/src/components/QNavMenu.vue";
import CommunityEventConst from "socialuni-community-sdk/src/constant/CommunityEventConst";
import SocialuniCommentInputDialog from "./SocialuniCommentInputDialog.vue";
import MsgInput from "socialuni-ui/src/components/MsgInput.vue";
// todo 后台可控制是否显示轮播图
@toNative
@Component({
  components: {
    SocialuniCommentInputDialog,
    QNavMenu,
    QIcon,
    MsgInput,
    QTabs,
    QEnumLink,
    TalkItem
  }
})
export default class SocialuniTalkViewH5 extends Vue {
  $refs: {
    commentDialog: SocialuniCommentInputDialog
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
    if (this.curTalkTabObj && !this.curTalkTabObj.firstLoad) {
      //如果当前为关注，则重新查询,否则的话将关注列设置为首次查询
      this.startPullDown()
      //把非当前的设置为初始
      this.talkTabs.filter(item => item.type !== this.curTalkTabObj.type).forEach(item => (item.firstLoad = true))
    }
  }

  get talkTabs() {
    return socialTalkModule.talkTabs
  }

  pageQueryUtil: SocialuniPageQueryUtil<TalkVO, TalkQueryVO> = new SocialuniPageQueryUtil(SocialuniTalkAPI.queryTalksAPI)

  get mineCirclesTop10() {
    console.log(socialCircleModule.mineCirclesTop10)
    return socialCircleModule.mineCirclesTop10
  }

  get talksNew() {
    return this.pageQueryUtil.listData
  }

  @Watch('$route.query')
  routerQueryWatch(newVal, oldVal) {
    if (JsonUtil.toJson(newVal) !== JsonUtil.toJson(oldVal)) {
      this.initLogic()
    }
  }


  mounted() {
    this.initLogic()
  }


  tabName = '首页'

  initLogic() {
    this.pageQueryUtil = new SocialuniPageQueryUtil(SocialuniTalkAPI.queryTalksAPI)
    console.log('chufa11111')
    this.tabName = this.$route.query.tab as string
    if (this.$route.query.circle) {
      socialCircleModule.setCircleName(this.$route.query.circle as string)
    }
    if (!this.tabName) {
      if (!socialCircleModule.circleName) {
        this.$router.push('/community?tab=' + '首页')
        return
      }
      this.tabName = '首页'
    }
    // 获取元素高度，用来计算scroll-view高度
    // this.$refs.tabsTalk.initQuery()
    this.$nextTick(() => {
      console.log(12312)
      //首次打开talk页面，获取用户位置用来查询
      // locationModule.appLunchInitDistrict().then(() => {
      //首次打开talk页面，获取用户位置用来查询
      socialLocationModule.appLunchInitDistrict().then(async () => {
        // this.initQuery()
        const talkQO = TalkQOFactory.getTalkQueryQO(this.tabName, socialTalkModule.userGender, socialTalkModule.userMinAge, socialTalkModule.userMaxAge, socialuniTagModule.selectTagNames, socialCircleModule.circleName)
        await this.pageQueryUtil.initQuery(talkQO)
      })
    })
    this.updateShowAd()
    socialCircleModule.getHotCirclesAction()
  }

  nextPageQueryDebounce = CommonUtil.debounce(() => {
    this.nextPageQuery()
  }, 500)

  nextPageQuery() {
    const talkQO = TalkQOFactory.getTalkQueryQO(this.tabName, socialTalkModule.userGender, socialTalkModule.userMinAge, socialTalkModule.userMaxAge, socialuniTagModule.selectTagNames, socialCircleModule.circleName)
    this.pageQueryUtil.nextPageQuery(talkQO)
  }


  scrollToLower() {
    // console.log('chufale')
    this.nextPageQueryDebounce()
    // const talkQO = TalkQOFactory.getTalkQueryQO(this.tabName, socialTalkModule.userGender, socialTalkModule.userMinAge, socialTalkModule.userMaxAge, socialuniTagModule.selectTagNames, socialCircleModule.circleName)
    // this.pageQueryUtil.nextPageQuery(talkQO)
  }


  // talkTabs: TalkTabVO [] = []
  // 页面初始化模块
  // homeTypeObjs: HomeTypeTalkVO [] = []

  readonly talkCacheNum: number = 30


  // 供父组件调用，每次隐藏把数据缓存进storage
  tabsTalkOnHide() {
    // 存入store
    const storeTalkTabs: TalkTabVO[] = []
    this.talkTabs.forEach(item => {
      const storeTalkTab: TalkTabVO = new TalkTabVO(item.name, item.type)
      storeTalkTab.firstLoad = item.firstLoad
      storeTalkTab.scrollTop = item.scrollTop
      storeTalkTab.pageScrollTop = item.scrollTop
      storeTalkTab.talks = item.talks.slice(-this.talkCacheNum)
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
    this.manualPulldownRefresh()
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
    this.curTalkTabObj.queryTime = new Date()
    this.curTalkTabObj.firstLoad = true
    this.clickOnreachBottom()
  }


  //点击不需要更新查询时间，查不出来就查不出来，万一是自己手动暂停了查询呢，而且如果重设时间会导致数据重复问题
  async clickOnreachBottom() {
    //停止查询方法
    const talkTab = this.curTalkTabObj
    if (talkTab) {
      if (!talkTab.talks.length) {
        this.curTalkTabObj.queryTime = new Date()
        this.curTalkTabObj.firstLoad = true
      }
      //如果正在查询，则停止查询，没办法省略因为修改和使用的是一个变量
      if (talkTab.loadMore === LoadMoreType.loading) {
        talkTab.loadMore = LoadMoreType.more
      } else {
        talkTab.loadMore = LoadMoreType.more
        //如果正在查询，则更改状态为加载更多,点击暂停加载。
        await this.autoChooseUseLocationQueryTalks()
      }
      // this.$refs.pullRefresh.endPulldownRefresh()
    }
  }

  // scroll-view到底部加载更多
  //如果用户开了定位，就获取经纬度去查询，如果用户没开启定位，就不使用经纬度，没必要每次都获取经纬度。
  async autoChooseUseLocationQueryTalks() {
    const talkTabObj = this.curTalkTabObj
    //只有在传false时校验后面的
    const firstLoad = talkTabObj.firstLoad
    //只有不为加载中才可以加载
    //手动刷新可以刷新，或者为
    if (this.curTalkTabObj.loadMore === LoadMoreType.more || firstLoad) {
      // 执行正在加载动画
      this.curTalkTabObj.loadMore = LoadMoreType.loading


      // if (firstLoad) {
      // 默认地理位置是北京，以后可以根据ip获取当前城市
      // 话题的话显示最热门的话题，且只查询包含话题的动态

      //如果首页刷新时，查询置顶动态
      /*if (talkTabObj.name === socialuniConfigModule.appConfig.homeTabName) {
        //查询置顶冬天
        SocialuniTalkAPI.queryStickTalksAPI().then(res => {
          const stickTalks = res.data
          if (stickTalks.length) {
            resDataTalks.unshift(...stickTalks)
          }
        })
      }*/
      if (firstLoad) {
        talkTabObj.firstLoad = false
      }

      const talkQO = TalkQOFactory.getTalkQueryQO(talkTabObj.name, socialTalkModule.userGender, socialTalkModule.userMinAge, socialTalkModule.userMaxAge, talkTabObj.queryTime, socialuniTagModule.selectTagNames, firstLoad)

      return SocialuniTalkAPI.queryTalksAPI(talkQO).then((res: any) => {
        // 如果不是上拉加载，则是下拉刷新，则停止下拉刷新动画
        if (talkTabObj.loadMore === LoadMoreType.loading) {
          if (res.data) {
            console.log(res.data)
            if (firstLoad) {
              //必须这么写，要不然存在置顶后返回的情况就有问题了，也不能直接使用talkTabObj.talks.push。那样会存在闪烁的情况那样等于分了两次push
              //首次加载，则重新赋值重置内容
              talkTabObj.talks = res.data
            } else {
              //追加新内容
              talkTabObj.talks.push(...res.data)
            }
            if (res.data.length) {
              talkTabObj.queryTime = res.data[res.data.length - 1].updateTime
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
  }

  get talks(): TalkVO[] {
    if (this.talkTabs && this.talkTabs.length) {
      return this.curTalkTabObj.talks
    }
    return []
  }

  // 被举报后前台删除talk
  deleteTalk(talkId: string) {
    this.talks.splice(this.talks.findIndex(talk => talk.id === talkId), 1)
  }

  get curTalkTabObj() {
    if (this.talkTabs && this.talkTabs.length) {
      return this.talkTabs[this.currentTabIndex]
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
      this.talkTabs.forEach((item, index) => {
        if (index !== current) {
          //截取20
          item.talks = item.talks.slice(-20)
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
<style>
.socialuni-community-view-left-menu .el-menu-item {
  font-size: 20px;
}
</style>
