<template>
  <div v-if="talkTabs.length" class="flex-col h100p">
    <!--  <div v-if="talkTabs.length" class="flex-col h100p bg-primary">-->
    <!--    <q-tabs :tabs="talkTabs" v-model="current" type="bar" @input="tabsChange"-->
    <div class="flex-row px-sm mb-xss flex-none">
      <q-tabs :tabs="talkTabs" v-model="currentTabIndex" type="line" @change="tabsChange"
              class="bd-radius flex-1 mr-sm">
        <template #default="{tab,index,value}">
          <div class="h30 px-xs row-all-center font-md" :class="{'font-md':value===index}">{{
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
      <div>
        <div :current="currentTabIndex" class="h100p"
                @change="talkSwiperChange">
          <div class="h100p" v-for="(item, swiperIndex) in talkTabs" :key="swiperIndex">
            <!--
            使用view实现的问题，没有scroll事件小程序上
            <div class="h100p bg-default" :class="[scrollEnable?'overflow-scroll':'overflow-hidden']" :scroll-y="scrollEnable" @scrolltolower="onreachBottom"
                  :lower-threshold="800"
                  @scroll.native="talksScrollEvent"
                  @scroll="talksScrollEvent"
            >-->

            <!--          首页展示区分不同类型，
                      圈子类型、关注类型、首页类型、同城类型-->
            <div class="h100p bd-radius-10 mx-sm overflow-hidden" style="width: calc(100% - 20px)"
                         :scroll-y="true" @scrolltolower="autoChooseUseLocationQueryTalks"
                         :scroll-top="talkTabs[swiperIndex].pageScrollTop"
                         :lower-threshold="800"
                         @scroll="talksScrollEvent">
              <!--          不放上面是因为，头部距离问题，这样会无缝隙，那样padding会在上面，始终空白-->
              <div class="pb-60"
                   v-if="talkTabs[swiperIndex].talks.length || talkTabs[swiperIndex].name !== followTabName">
<!--                <talk-swipers class="px-mn pb-sm"-->
<!--                              v-if="talkTabs[swiperIndex].name === homeTabName && configShowSwipers"></talk-swipers>-->


                <div class="card mb-sm elevation-4 px-sm" v-if="talkTabs[swiperIndex].circle">
                  <!--                创建自己的圈子-->
                  <div class="row-between-center">
                    <div>{{ talkTabs[swiperIndex].name }}圈</div>
                    <!--                    圈主：客服-->
                    <div class="q-tag use-click"
                         @click="joinCircleGroupChat(talkTabs[swiperIndex].circle)">进入群聊
                    </div>
                    <!--                  显示圈主，如果没有圈主，则显示，此圈没有圈主，申请成为圈主-->
                  </div>
                  <!--                <div>处对象圈子介绍</div>-->
                </div>

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


                <div v-for="(talk,index) in talkTabs[swiperIndex].talks" :key="talk.id">
                  <talk-item :talk="talk"
                             :talk-tab-type="curTalkTabObj.type"
                             @delete-talk="deleteTalk"
                  />
                  <!-- app端广告有问题-->
                  <!--  #ifdef APP-PLUS -->
                  <!--<div v-if="showAd&&showAdIndexList.includes(index)" class="mb-5">
                    <ad class="bg-white" adpid="1890536227"></ad>
                  </div>-->
                  <!--  #endif -->
                  <!--wx平台显示的广告-->
                  <!--  #ifdef MP-WEIXIN -->
                  <ad v-if="showAd&&showAdIndexList.includes(index)"
                      class="bg-white mb-5" unit-id="adunit-65c8911d279d228f" ad-type="video"
                      ad-theme="white"></ad>
                  <!--  #endif -->

                  <!--qq平台显示的广告-->
                  <!--  #ifdef MP-QQ -->
                  <ad v-if="showAd&&showAdIndexList.includes(index)"
                      class="bg-white mb-5" unit-id="bcc21923107071ac3f8aa076c7e00229"
                      type="card"></ad>
                  <!--  #endif -->

                  <!--头条平台显示的广告-->
                  <!--  #ifdef MP-TOUTIAO -->
                  <ad v-if="showAd&&showAdIndexList.includes(index)"
                      class="bg-white mb-5" type="banner video large"
                      unit-id="3snract0gqnc3fn16d"></ad>
                  <!--  #endif -->
                </div>

                <!-- 下拉刷新组件 -->
                <div class="mt-xs">
                  <uni-load-more :status="talkTabs[swiperIndex].loadMore"
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
            </div>
          </div>
        </div>
      </div>
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

// todo 后台可控制是否显示轮播图
@toNative
@Component({
  components: {
    QIcon,
    QTabs,
    TalkItem
  }
})
export default class SocialuniTalkViewH5 extends Vue {
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

  // talkTabs: TalkTabVO [] = []
  // 页面初始化模块
  // homeTypeObjs: HomeTypeTalkVO [] = []

  readonly talkCacheNum: number = 30


  // 生命周期
  created() {
    // this.talkTabs = TalkAPI.queryHomeTalkTabsAPI()
    // LocationUtil.getCityByIpWebAPI()
    // 更新广告状态
    // 更新广告刷新时间
    this.updateShowAd()
    // 根据本地存储获取之前的 homeName
    // 有了位置才进行查询,因为查询同城需要位置信息
    // 获取位置，查询同城talks使用
  }

  mounted() {
    // 获取元素高度，用来计算scroll-view高度
    // this.$refs.tabsTalk.initQuery()
    this.$nextTick(() => {
      //首次打开talk页面，获取用户位置用来查询
      // locationModule.appLunchInitDistrict().then(() => {
      //首次打开talk页面，获取用户位置用来查询
      socialLocationModule.appLunchInitDistrict().then(() => {
        this.initQuery()
      })
    })
  }

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
      // }
      //首次时加载地理位置就好了，之后就是点击定位的时候加载
      /*if (locationModule.openLocation) {
        this.requestUseLocationQueryTalks(firstLoad)
      } else {
        this.queryTalks(firstLoad)
      }*/
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
