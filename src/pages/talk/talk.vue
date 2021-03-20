<template>
  <view class="flex-col h100p">
    <view v-show="showTagSearch" class="h100p">
      <tag-search class="h100p" v-model="showTagSearch" @change="changeTag"
      ></tag-search>
    </view>
    <view v-show="!showTagSearch" class="flex-col h100p">
      <q-navbar class="flex-none">
        <q-search class="flex-auto" @click.native="openTagSearchVue">
          <q-icon class="mx-5px text-gray" size="16" icon="search"></q-icon>
          <view v-if="selectTag" class="flex-row flex-auto">
            <view class="cu-tag round bg-green-plain light row-all-center">
              {{ selectTag.name }}
            </view>
          </view>
          <input v-else :adjust-position="false" type="text"
                 placeholder="选择话题" confirm-type="search"/>
          <q-icon v-if="selectTag" class="mr-sm text-gray row-all-center" icon="close"
                  @click.native.stop="deleteTag(selectTag)"
          ></q-icon>
        </q-search>
        <view class="mr-sm" :class="{'text-blue':useFilters}">
          <q-icon icon="mdi-filter-variant" size="28" @click="showFilterModel"></q-icon>
        </view>
        <view v-if="user" class="position-relative mr-sm">
          <q-icon icon="bell-fill" @click="toNotifyVue" size="28"></q-icon>
          <u-badge :count="unreadNotifiesNum" size="mini"
                   :offset="[0, 0]" @click="toNotifyVue"></u-badge>
        </view>
        <view class="mr-sm">
          <q-icon icon="plus-circle" size="28" @click="toTalkAdd"></q-icon>
        </view>
      </q-navbar>

      <q-popup v-model="showFilter" bottom>
        <q-bar round class="solid-bottom">
          <view class="text-black text-lgg text-bold">筛选</view>
          <view class="flex-row">
            <view class="text-blue text-bold mx-xs px" @click="hideFilter">取消</view>
            <view class="text-green text-bold ml-lg mr-sm px" @click="filterQuery">确定</view>
          </view>
        </q-bar>
        <view class="mt pb-sm">
          <view class="row-center px-lg pt" v-if="genderDefault === '全部'">
            <view class="w70px row-start">性别：</view>
            <view>
              <radio-group @change="genderChange" class="flex-1">
                <label v-for="report in genders" :key="report">
                  <radio :value="report" :checked="report===genderValue"></radio>
                  <text class="ml-sm mr">{{ report }}</text>
                </label>
              </radio-group>
            </view>
          </view>
          <view class="mt-20px pb-xl pt">
            <view class="row-between px-lg">
              <view>年龄：{{ rangeValue[0] }} - {{ rangeValue[1] }}</view>
            </view>
            <view class="px-lg">
              <q-slider
                :value="rangeValue"
                :min="rangeMin"
                :max="rangMax"
                :bar-height="3"
                active-color="#FF6B00"
                :format="format"
                @change="handleRangeChange"
              ></q-slider>
            </view>
          </view>
        </view>
      </q-popup>

      <!--  #ifdef APP-PLUS -->
      <!-- <ad class="bg-white mt-10px w100vw" adpid="1890536227"></ad>-->
      <!--  #endif -->

      <talk-swipers v-if="configShowSwipers"></talk-swipers>

      <tabs-talk class="flex-1" ref="tabsTalk"
                 :scroll-enable="scrollEnable"
                 :selectTagIds="selectTagIds"
                 :userGender="userGender"
                 :userMinAge="userMinAge"
                 :userMaxAge="userMaxAge"
                 :tabs-id="tabsId"
      ></tabs-talk>
    </view>

    <!--            评论输入框按钮-->
    <!--    <msg-input v-if="showMsgInput">
        </msg-input>-->

    <msg-input v-if="showMsgInput">
    </msg-input>
  </view>
</template>

<script lang="ts">
import {
  Vue,
  Component,
  Watch
} from 'vue-property-decorator'

import TalkItem from '@/pages/talk/TalkItem.vue'
import { namespace } from 'vuex-class'
import PagePath from '@/const/PagePath'
import UnreadNotifyVO from '@/model/UnreadNotifyVO'
import TagVO from '@/model/tag/TagVO'
import RouterUtil from '@/utils/RouterUtil'
import PageUtil from '@/utils/PageUtil'
import TalkFilterUtil from '@/utils/TalkFilterUtil'
import UniUtil from '@/utils/UniUtil'
import CommonUtil from '@/utils/CommonUtil'
import TalkSwipers from '@/pages/talk/talkSwipers.vue'
import { appModule, locationModule, notifyModule, systemModule, tagModule, talkModule } from '@/plugins/store'
import UserVO from '@/model/user/UserVO'
import TagSearch from '@/pages/talk/TagSearch.vue'
import NodesRef = UniApp.NodesRef
import SelectorQuery = UniApp.SelectorQuery
import TabsTalkVue from '@/pages/talk/tabsTalk.vue'

const talkStore = namespace('talk')
const userStore = namespace('user')
const notifyStore = namespace('notify')
const configStore = namespace('config')

// todo 后台可控制是否显示轮播图

@Component({
  components: {
    TagSearch,
    TabsTalk: TabsTalkVue,
    TalkSwipers,
    TalkItem
  }
})
export default class TalkVue extends Vue {
  public $refs!: {
    tabsTalk: TabsTalkVue;
  }
  @userStore.State('user') user: UserVO
  // 轮播图
  @talkStore.State('userMinAge') userMinAge: number
  @talkStore.State('userMaxAge') userMaxAge: number
  @talkStore.State('userGender') userGender: string
  // 点击通知去通知页
  @notifyStore.Getter('unreadNotifies') unreadNotifies: UnreadNotifyVO[]
  // 轮播图
  @configStore.State('showSwipers') configShowSwipers: boolean

  // tag 相关
  showTagSearch = false
  selectTag: TagVO = null
  // 筛选相关
  rangeMin: number = TalkFilterUtil.minAgeFilterDefault
  rangMax: number = TalkFilterUtil.maxAgeFilterDefault
  genders: string [] = ['全部', '男', '女']
  // 组件内的值
  genderValue: string = appModule.appQueryGender
  genderDefault: string = appModule.appQueryGender
  rangeValue: number[] = [TalkFilterUtil.minAgeFilterDefault, TalkFilterUtil.maxAgeFilterDefault]
  unreadNotifiesNum = 0
  // 评论输入框
  showMsgInput = false
  // filter内容
  showFilter = false
  // 传入子组件，控制组件高度
  talkTabsTop = 0
  // 滚动超过轮播图隐藏轮播图，scroll-view开启滚动
  scrollEnable = false

  // 唯一id值
  readonly uuid: string = 'u' + CommonUtil.getUUID()
  readonly tabsId: string = this.uuid + '_tabs'

  onLoad (params: any) {
    /*if (params.load) {
      this.initQuery()
    }*/
    this.initQuery()
    UniUtil.showShareMenu()
  }


  // life
  onReady () {
    // 不这么写百度读不出来
    CommonUtil.delayTime(100).then(() => {
      this.getTabBarTop()
    })
    // 这里是不是有问题应该选择异性
    // 指的是用户选择的筛选性别
    this.genderValue = this.userGender
    this.rangeValue = [this.userMinAge, this.userMaxAge]
  }

  // todo 有个1
  onShow () {
    this.showMsgInput = true
  }

  onHide () {
    this.showMsgInput = false
    this.$refs.tabsTalk.tabsTalkOnHide()
  }

  onPageScroll (e) {
    // 只有开启了轮播图，才需要控制下方滚动
    if (this.configShowSwipers) {
      // +5点余量以防万一
      const scrollTop = e.scrollTop + 1
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
  unreadNotifiesWatch () {
    this.unreadNotifiesNum = this.unreadNotifies.length
  }

  @Watch('configShowSwipers')
  configShowSwipersWatch () {
    CommonUtil.delayTime(100).then(() => {
      this.getTabBarTop()
    })
  }

  // 去除页面初始化的，初始化查询
  initQuery () {
    // this.$refs.tabsTalk.initQuery()
    this.$nextTick(() => {
      //首次打开talk页面，获取用户位置用来查询
      // locationModule.appLunchInitDistrict().then(() => {
      this.$refs.tabsTalk.initQuery()
      // })
    })
  }

  getTabBarTop () {
    let query: SelectorQuery
    // #ifdef H5
    query = uni.createSelectorQuery().in(this)
    // #endif
    // #ifndef H5
    query = uni.createSelectorQuery().in(this.$refs.tabsTalk)
    // #endif
    // 获取tabs到top的点
    const nodeLeft: NodesRef = query.select('.' + this.tabsId)
    nodeLeft.boundingClientRect((res: any) => {
      if (res) {
        // 44 为导航栏的高度
        // #ifdef H5
        this.talkTabsTop = res.top - 44
        // #endif
        // #ifndef H5
        this.talkTabsTop = res.top - 44 - systemModule.statusBarHeight
        // #endif
        // app平台0.000000x小数，所以写1余量
        if (this.talkTabsTop <= 1) {
          this.scrollEnable = true
        }
      } else {
        CommonUtil.delayTime(100).then(() => {
          this.getTabBarTop()
        })
      }
    }).exec()
  }

  openTagSearchVue () {
    tagModule.getTagTypesAction()
    this.showTagSearch = true
  }

  // tag
  changeTag (tag: TagVO) {
    this.selectTag = tag
    this.initQuery()
  }

  deleteTag () {
    this.selectTag = null
    this.initQuery()
  }

  get selectTagIds (): number[] {
    if (this.selectTag) {
      return [this.selectTag.id]
    } else {
      return []
    }
  }

  showFilterModel () {
    this.showFilter = true
    //修复打开filter时，当前值不对的问题
    this.genderValue = this.userGender
    this.rangeValue = [this.userMinAge, this.userMaxAge]
  }

  hideFilter () {
    this.showFilter = false
    this.genderValue = this.userGender
    this.rangeValue = [this.userMinAge, this.userMaxAge]
  }

  get useFilters (): boolean {
    //todo 集美这里又区别，默认的筛选
    return (appModule.appQueryGender === this.genders[0] && this.userGender !== appModule.appQueryGender) ||
      this.userMinAge !== TalkFilterUtil.minAgeFilterDefault ||
      this.userMaxAge !== TalkFilterUtil.maxAgeFilterDefault
  }

  format () {
    return ''
  }

  handleRangeChange (e) {
    this.rangeValue = e
  }

  genderChange ({ target }) {
    this.genderValue = target.value
  }

  filterQuery () {
    talkModule.userGender = this.genderValue
    talkModule.userMinAge = this.rangeValue[0]
    talkModule.userMaxAge = this.rangeValue[1]
    TalkFilterUtil.setFilterData(talkModule.userGender, talkModule.userMinAge, talkModule.userMaxAge)
    this.hideFilter()
    this.initQuery()
  }

  toNotifyVue () {
    notifyModule.queryUnreadNotifiesAndUpdateHasReadAction()
    RouterUtil.navigateTo(PagePath.notify)
  }

  // 点击加号去新增talk
  toTalkAdd () {
    PageUtil.toTalkAddPage()
  }
}
</script>
