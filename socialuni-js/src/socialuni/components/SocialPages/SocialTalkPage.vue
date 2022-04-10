<template>
  <view class="flex-col h100p">
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
          <view v-if="selectTagName" class="flex-row flex-auto">
            <view class="q-tag round bg-green-plain light row-all-center">
              {{ selectTagName }}
            </view>
          </view>
          <input v-else :adjust-position="false" type="text"
                 placeholder="选择话题" confirm-type="search"/>
          <q-icon v-if="selectTagName" class="mr-sm text-gray row-all-center" icon="close"
                  @click.native.stop="deleteTag"
          ></q-icon>
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
  </view>
</template>

<script lang="ts">
import { Component, Vue, Watch } from 'vue-property-decorator'

import TalkItem from '../SocialTalk/TalkItem.vue'
import PagePath from '../../constant/PagePath'
import TagVO from '../../model/community/tag/TagVO'
import RouterUtil from '../../utils/RouterUtil'
import PageUtil from '../../utils/PageUtil'
import TalkFilterUtil from '../../utils/TalkFilterUtil'
import UniUtil from '../../utils/UniUtil'
import TalkSwipers from '../SocialTalk/talkSwipers.vue'
import {
  socialAppStore,
  socialConfigStore,
  socialLocationModule,
  socialLocationStore,
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
export default class SocialTalkPage extends Vue {
  public $refs!: {
    tabsTalk: TabsTalkVue;
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

  switchCircleTabValue (event: { detail: any }) {
    const detail: {
      current: number,
      source: number
    } = event.detail
    this.current = detail.current
  }

  get talkTabsTop () {
    if (this.configShowSwipers && this.homeSwipers && this.homeSwipers.length) {
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
    this.initQuery()
    this.socialTalkScroll({ scrollTop: 0 })
  }

  tabsTalkOnHide () {
    this.$refs.tabsTalk.tabsTalkOnHide()
  }

  @Watch('homeSwipers')
  homeSwipersWatch () {
    this.socialTalkScroll({ scrollTop: 0 })
  }

  socialTalkScroll (e) {
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

  @Watch('selectTagName')
  selectTagNameWatch () {
    this.initQuery()
  }

  // tag
  changeTag (tag: TagVO) {
    socialTagModule.setSelectTagName(tag.name)
    this.initQuery()
  }

  deleteTag () {
    socialTagModule.setSelectTagName(null)
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

  openCityPicker () {
    this.$refs.cityPicker.open()
  }

  cityChange (district: DistrictVO) {
    socialLocationModule.setLocation(district)
    this.$refs.tabsTalk.autoChooseUseLocationQueryTalks(true)
  }
}
</script>
