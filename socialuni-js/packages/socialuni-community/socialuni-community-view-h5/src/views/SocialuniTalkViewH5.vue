<template>
  <div class="h100p overflow-auto pt-sm" v-infinite-scroll="scrollToLower" :infinite-scroll-immediate="false"
       :infinite-scroll-distance="200"
       :infinite-scroll-delay="200"
  >
    <div class="mg-x-auto">
      <div class="row-center-start">
        <!--      <q-nav-menu/>-->
        <el-menu
            class="w200 bd-radius flex-none br position-sticky top-0 h-auto socialuni-community-view-left-menu mr-sm">
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

          <!--          <q-load-more></q-load-more>-->
          <!--          <q-icon icon="mdi-loading mdi-spin"></q-icon>-->
          <!--          不放上面是因为，头部距离问题，这样会无缝隙，那样padding会在上面，始终空白-->
          <div v-for="(talk,index) in talksNew" :key="talk.id">
            <talk-item :talk="talk"
                       :talk-tab-type="tabName"
                       @delete-talk="deleteTalk"
            />
          </div>

          <q-load-more class="my-sm" :status="pageQueryUtil.loadMore"></q-load-more>

          <msg-input class="w600"></msg-input>
        </div>
        <div class="w300 ml-sm bg-white bd-radius pd position-sticky top-0">
          <div>
            <div v-for="tag in hotTagsTop10">
              <div class="mt-sm color-blue_dark mr-sm font-18">
                <span class="color-blue mr-nn">#</span>
                {{ tag.name }}
              </div>
            </div>
          </div>
        </div>

        <q-dialog ref="talkAddDialog" confirm-text="发布" @confirm="addTalk">
          <talk-add-view ref="talkAddView"></talk-add-view>
        </q-dialog>

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
import QLoading from 'qing-ui/src/components/QLoading.vue'
import QLoadMore from 'qing-ui/src/components/QLoadMore.vue'
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
import QDialog from "qing-ui-h5/src/components/QDialog.vue";
import TalkAddView from "./TalkAddView.vue";
import SocialuniTalkListViewService from "socialuni-community-sdk/src/logic/service/SocialuniTalkListViewService";
import {getCurrentInstance, watch} from "vue";


// todo 后台可控制是否显示轮播图
@toNative
@Component({
  components: {
    TalkAddView,
    QDialog,
    SocialuniCommentInputDialog,
    QNavMenu,
    QLoading,
    QLoadMore,
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
    talkAddDialog: QDialog
    talkAddView: TalkAddView
  }


  talkViewService = new SocialuniTalkListViewService()

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

  get hotTagsTop10() {
    return socialuniTagModule.hotTags
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

  setTabName(){
    socialTalkModule.curTabName = this.$route.query.tab as string
    if (this.$route.query.circle) {
      socialCircleModule.setCircleName(this.$route.query.circle as string)
    }
    console.log('111111')
    if (!socialTalkModule.curTabName) {
      if (!socialCircleModule.circleName) {
        console.log('chufale tiaozhuan')
        // window.open('/community?tab=首页', '_self')
        this.$router.push('/community?tab=' + '首页')
        return
      }
      socialTalkModule.curTabName = '首页'
    }
  }

  mounted() {
    this.talkViewService = new SocialuniTalkListViewService()

    console.log(9999)
    console.log(this)
    console.log(this.$route)
    console.log(this.$route.query.tab)
    console.log(this.$route.params.tab)
    console.log(this.$router)
    console.log(888)

    this.talkViewService.initService(this)

    watch(() => this.$route.query, (newVal, oldVal) => {
      if (JsonUtil.toJson(newVal) !== JsonUtil.toJson(oldVal)) {
        console.log('chufale query')
        console.log(this)
        console.log(this.$route)
        console.log(this.$route.query.tab)
        console.log(this.$router)
        this.setTabName()
        this.talkViewService.initQuery()
      }
    })

    CommonEventUtil.on(CommunityEventConst.socialuniTalkAddEvent, () => {
      console.log('chufale jianting')
      this.$refs.talkAddDialog.open()
    })
  }

  async addTalk() {
    await this.$refs.talkAddView.addTalk()
    this.$refs.talkAddDialog.close()
    this.initLogic()
  }


  initLogic() {
    // this.pageQueryUtil = new SocialuniPageQueryUtil(SocialuniTalkAPI.queryTalksAPI)
    // console.log('chufa11111')
    // this.tabName = this.$route.query.tab as string
    // if (this.$route.query.circle) {
    //   socialCircleModule.setCircleName(this.$route.query.circle as string)
    // }
    // if (!this.tabName) {
    //   if (!socialCircleModule.circleName) {
    //     this.$router.push('/community?tab=' + '首页')
    //     return
    //   }
    //   this.tabName = '首页'
    // }
    // // 获取元素高度，用来计算scroll-view高度
    // // this.$refs.tabsTalk.initQuery()
    // this.$nextTick(() => {
    //   console.log(12312)
    //   //首次打开talk页面，获取用户位置用来查询
    //   // locationModule.appLunchInitDistrict().then(() => {
    //   //首次打开talk页面，获取用户位置用来查询
    //   socialLocationModule.appLunchInitDistrict().then(async () => {
    //     // this.initQuery()
    //     const talkQO = TalkQOFactory.getTalkQueryQO(this.tabName, socialTalkModule.userGender, socialTalkModule.userMinAge, socialTalkModule.userMaxAge, socialuniTagModule.selectTagNames, socialCircleModule.circleName)
    //     await this.pageQueryUtil.initQuery(talkQO)
    //   })
    // })
    // this.updateShowAd()
    // socialCircleModule.getHotCirclesAction()
    // socialuniTagModule.getHotTagsAction()
  }

  scrollToLower() {
    // console.log('chufale')
    const talkQO = TalkQOFactory.getTalkQueryQO(this.tabName, socialTalkModule.userGender, socialTalkModule.userMinAge, socialTalkModule.userMaxAge, socialuniTagModule.selectTagNames, socialCircleModule.circleName)
    this.pageQueryUtil.loadNextPage(talkQO)
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

  tabScrollToTop() {
    this.curTalkTabObj.pageScrollTop = 0
    this.$nextTick(() => {
      this.curTalkTabObj.pageScrollTop = -1
    })
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
  font-size: 18px;
}
</style>
