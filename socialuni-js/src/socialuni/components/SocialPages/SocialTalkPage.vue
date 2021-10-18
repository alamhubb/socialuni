<template>
  <view class="flex-col h100p">
    <view v-show="showTagSearch" class="h100p">
      <tag-search class="h100p" v-model="showTagSearch" @change="changeTag"
      ></tag-search>
    </view>
    <!--    不能使用100%，h5，不包含tabbar，尺寸计算不正确，所以需要使用h100vh-->
    <view v-show="!showTagSearch" class="flex-col h100vh bg-theme">
      <q-navbar class="flex-none" :custom-class="'bg-theme'">
        <q-search class="flex-auto" @click.native="openTagSearchVue">
          <q-icon class="mx-5 text-gray" size="16" icon="search"></q-icon>
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
        <view class="mr-sm" :class="{'text-theme':useFilters}">
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

      <!--  #ifdef APP-PLUS -->
      <!-- <ad class="bg-white mt-10 w100vw" adpid="1890536227"></ad>-->
      <!--  #endif -->

      <talk-swipers class="flex-none" v-if="configShowSwipers"></talk-swipers>


      <div class="flex-1 flex-col bg-theme-light bt-radius-15">
<!--        <div class="flex-row px-smm pt-sm">
          <div class="size100">
            <img class="bd-round size100" :src="tags[0].avatar"/>
            <div>{{tags[0].name}}</div>
          </div>
          <div>
            <div class="h50 row-col-center ml-sm">简介,撒旦法撒旦法撒旦法阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯顿发是的分阿斯蒂芬</div>
            <div class="flex-row">
              <div v-for="(item,index) in tags" v-if="index<4" class="px-sm">
                {{ item.name }}
              </div>
            </div>
            <div class="row-wrap">
              <div v-for="(item,index) in tags" v-if="index<4" class="px-sm">
                <img class="bd-round size40" :src="item.avatar"/>
                <div>{{ item.name }}</div>
              </div>
            </div>
          </div>
        </div>-->
        <tabs-talk class="flex-1" ref="tabsTalk"
                   :scroll-enable="scrollEnable"
                   :selectTagIds="selectTagIds"
        ></tabs-talk>
      </div>

      <q-popup v-model="showFilter" bottom>
        <div class="row-between-center q-box bb-1">
          <view class="text-black text-md font-bold">动态筛选</view>
          <view class="flex-row">
            <view class="text-blue font-bold mx-xs px" @click="hideFilter">取消</view>
            <view class="text-green font-bold ml-lg mr-sm px" @click="filterQuery">确定</view>
          </view>
        </div>
        <view class="mt-sm pb-sm">
          <!--          只有当前应用类型为全部性别才显示性别筛选-->
          <view class="row-center px pt" v-if="appGender === GenderTypeAll">
            <view class="w50 flex-none">性别：</view>
            <radio-group @change="genderChange" class="flex-1">
              <div class="flex-row row-wrap">
                <div v-for="report in visibleGenders" :key="report.value" class="mb-sm">
                  <label>
                    <radio :value="report.value" :checked="report.value===genderTypeValue"></radio>
                    <text class="ml-sm mr">{{ report.label }}</text>
                  </label>
                </div>
              </div>
            </radio-group>
          </view>
          <view class="mt-sm pb-xl pt">
            <view class="row-between px">
              <view>发布人年龄：{{ rangeValue[0] }} - {{ rangeValue[1] }}</view>
            </view>
            <view class="px">
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
    </view>
    <msg-input>
    </msg-input>
  </view>
</template>

<script lang="ts">
import { Component, Vue, Watch } from 'vue-property-decorator'

import TalkItem from '../SocialTalk/TalkItem.vue'
import PagePath from '../../const/PagePath'
import UnreadNotifyVO from '../../model/UnreadNotifyVO'
import TagVO from '../../model/tag/TagVO'
import RouterUtil from '../../utils/RouterUtil'
import PageUtil from '../../utils/PageUtil'
import TalkFilterUtil from '../../utils/TalkFilterUtil'
import UniUtil from '../../utils/UniUtil'
import TalkSwipers from '../SocialTalk/talkSwipers.vue'
import {
  socialConfigStore,
  socialNotifyModule,
  socialNotifyStore,
  socialTagModule,
  socialTagStore,
  socialTalkModule,
  socialUserStore
} from '../../store'
import CenterUserDetailRO from '../../model/social/CenterUserDetailRO'
import TagSearch from '../SocialTalk/TagSearch.vue'
import TabsTalkVue from '../SocialTalk/tabsTalk.vue'
import QButton from '../q-button/QButton.vue'
import GenderType from '../../const/GenderType'
import SocialuniConfig from '../../model/SocialuniConfig'
import MsgInput from '../MsgInput.vue'
import QNavbar from '../q-navbar/q-navbar.vue'
import QSearch from '../q-search/q-search.vue'
import QIcon from '../q-icon/q-icon.vue'
import QPopup from '../q-popup/q-popup.vue'
import QSlider from '../q-slider/q-slider.vue'
import ConfigMap from '@/socialuni/const/ConfigMap'

// todo 后台可控制是否显示轮播图

@Component({
  components: {
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
export default class SocialTalkPage extends Vue {
  public $refs!: {
    tabsTalk: TabsTalkVue;
  }
  @socialTagStore.State('tags') readonly tags: TagVO []
  @socialUserStore.State('user') user: CenterUserDetailRO
  // 轮播图
  // 点击通知去通知页
  @socialNotifyStore.Getter('unreadNotifies') unreadNotifies: UnreadNotifyVO[]
  // 轮播图
  @socialConfigStore.State('showSwipers') configShowSwipers: boolean
  @socialConfigStore.Getter(ConfigMap.swiperHeightKey) swiperHeight: number

  // tag 相关
  showTagSearch = false
  selectTag: TagVO = null
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

  get talkTabsTop () {
    if (this.configShowSwipers) {
      return this.swiperHeight + 10
    }
    return 0
  }

  get visibleGenders () {
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
  mounted () {
    this.pageMounted()
  }

  pageMounted () {
    UniUtil.showShareMenu()
    // 这里是不是有问题应该选择异性
    // 指的是用户选择的筛选性别
    this.initFilterValue()
    this.initQuery()
  }

  tabsTalkOnHide () {
    this.$refs.tabsTalk.tabsTalkOnHide()
  }

  socialTalkScroll (e) {
    // 只有开启了轮播图，才需要控制下方滚动
    if (this.configShowSwipers) {
      // +5点余量以防万一
      const scrollTop = e.scrollTop + 1
      console.log(scrollTop)
      console.log(this.talkTabsTop)
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
    console.log(this.scrollEnable)
  }

  // 必须这么写否则不生效
  @Watch('unreadNotifies')
  unreadNotifiesWatch () {
    this.unreadNotifiesNum = this.unreadNotifies.length
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

  openTagSearchVue () {
    socialTagModule.getTagTypesAction()
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

  //隐藏和展示都使用user中的默认值
  showFilterModel () {
    this.showFilter = true
    //修复打开filter时，当前值不对的问题
  }

  hideFilter () {
    this.showFilter = false
    this.initFilterValue()
  }

  initFilterValue () {
    this.genderTypeValue = socialTalkModule.userGender
    this.rangeValue = [socialTalkModule.userMinAge, socialTalkModule.userMaxAge]
  }

  get useFilters (): boolean {
    return socialTalkModule.userGender !== GenderType.talkQueryFilterMap.get(SocialuniConfig.appGenderType) ||
      socialTalkModule.userMinAge !== TalkFilterUtil.minAgeFilterDefault ||
      socialTalkModule.userMaxAge !== TalkFilterUtil.maxAgeFilterDefault
  }

  format () {
    return ''
  }

  handleRangeChange (e) {
    this.rangeValue = e
  }

  genderChange ({ target }) {
    this.genderTypeValue = target.value
  }

  filterQuery () {
    socialTalkModule.setFilterData(this.genderTypeValue, this.rangeValue[0], this.rangeValue[1])
    this.hideFilter()
    this.initQuery()
  }

  toNotifyVue () {
    socialNotifyModule.queryUnreadNotifiesAndUpdateHasReadAction()
    RouterUtil.navigateTo(PagePath.notify)
  }

  // 点击加号去新增talk
  toTalkAdd () {
    PageUtil.toTalkAddPage()
  }
}
</script>
