<template>
  <view class="flex-col h100p">
    <!--    不能使用100%，h5，不包含tabbar，尺寸计算不正确，所以需要使用h100vh-->
    <view class="flex-col h100vh">
      <q-navbar class="flex-none">
        <div class="row-col-center ml-xs mr-sm font-bold bg-click" @click="openCityPicker">
          <q-icon size="14" icon="map-fill"></q-icon>
          {{ location.adName }}
          <q-icon icon="mdi-chevron-right"></q-icon>
        </div>
        <q-search class="flex-1 mr-sm" @click.native="openTagPicker">
          <q-icon class="mx-5 text-gray" size="16" icon="search"></q-icon>
          <view v-if="selectTagName" class="row-col-center flex-1" @click.stop="">
            <view class="q-tag round bg-green-plain light row-all-center">
              {{ selectTagName }}
            </view>
            <q-icon class="mr-sm text-gray row-all-center" icon="close" @click="deleteTag"></q-icon>
          </view>
          <div v-else class="cursor-text flex-1">选择话题</div>
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


    <socia-tag-picker ref="tagPicker" @change="changeTag"></socia-tag-picker>
    <q-city-picker ref="cityPicker" :value="location" @input="cityChange"></q-city-picker>
  </view>
</template>

<script lang="ts">
import {Component, Vue, Watch} from 'vue-property-decorator'

import PagePath from '../../socialuni/constant/PagePath'
import TagVO from '../../socialuni/model/community/tag/TagVO'
import RouterUtil from '../../socialuni/utils/RouterUtil'
import PageUtil from '../../socialuni/utils/PageUtil'
import UniUtil from '../../socialuni/utils/UniUtil'
import {
  socialAppStore,
  socialConfigModule,
  socialLocationModule,
  socialLocationStore,
  socialNotifyModule,
  socialNotifyStore,
  socialTagModule,
  socialTagStore,
  socialUserStore
} from '../../socialuni/store'
import CenterUserDetailRO from '../../socialuni/model/social/CenterUserDetailRO'
import QButton from '../../qing-ui/components/QButton/QButton.vue'
import QNavbar from '../../qing-ui/components/QNavbar/QNavbar.vue'
import QSearch from '../../qing-ui/components/QSearch/QSearch.vue'
import QIcon from '../../qing-ui/components/QIcon/QIcon.vue'
import QPopup from '../../qing-ui/components/QPopup/QPopup.vue'
import QSlider from '../../qing-ui/components/QSlider/QSlider.vue'
import HomeSwiperVO from '@/socialuni/model/HomeSwiperVO'
import QTabs from '@/qing-ui/components/QTabs/QTabs.vue'
import DistrictVO from '@/socialuni/model/DistrictVO'
import QCityPicker from '@/components/QCityPicker/QCityPicker.vue'
import UnreadNotifyVO from '@/socialuni/model/notify/UnreadNotifyVO'
import MsgInput from '@/components/MsgInput.vue'
import TabsTalk from '@/pages/talk/tabsTalk.vue'
import TalkSwipers from '@/pages/talk/talkSwipers.vue'
import TagSearch from '@/pages/talk/TagSearch.vue'
import SociaTagPicker from '@/components/SocialTagPicker.vue'
import SocialTagPicker from '@/components/SocialTagPicker.vue'

// todo 后台可控制是否显示轮播图

@Component({
  components: {
    SociaTagPicker,
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
    TabsTalk,
    TalkSwipers
  }
})
export default class TalkView extends Vue {
  public $refs!: {
    tabsTalk: TabsTalk;
    cityPicker: QCityPicker
    tagPicker: SocialTagPicker
  }
  @socialTagStore.State('tags') readonly tags: TagVO []
  @socialUserStore.State('user') user: CenterUserDetailRO
  // 轮播图
  // 点击通知去通知页
  @socialNotifyStore.Getter('unreadNotifies') unreadNotifies: UnreadNotifyVO[]
  // 轮播图
  @socialAppStore.State('homeSwipers') homeSwipers: HomeSwiperVO[]
  @socialTagStore.State('selectTagName') selectTagName: string
  @socialLocationStore.Getter('location') location: DistrictVO

  current = 0
  unreadNotifiesNum = 0
  // 滚动超过轮播图隐藏轮播图，scroll-view开启滚动
  scrollEnable = false

  get talkTabsTop() {
    if (socialConfigModule.appMoreConfig.showSwipers && this.homeSwipers && this.homeSwipers.length) {
      return socialConfigModule.appMoreConfig.swiperHeight + 10
    }
    return 0
  }

  // life
  mounted() {
    this.pageMounted()
  }

  onHide() {
    this.$refs.tabsTalk.tabsTalkOnHide()
  }

  pageMounted() {
    UniUtil.showShareMenu()
    // 这里是不是有问题应该选择异性
    // 指的是用户选择的筛选性别
    this.initQuery()
    this.socialTalkScroll({scrollTop: 0})
  }

  onPageScroll(e) {
    this.socialTalkScroll(e)
  }

  socialTalkScroll(e) {
    // 只有开启了轮播图，才需要控制下方滚动
    if (socialConfigModule.appMoreConfig.showSwipers && this.homeSwipers && this.homeSwipers.length) {
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

  @Watch('homeSwipers')
  homeSwipersWatch() {
    this.socialTalkScroll({scrollTop: 0})
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

  openTagPicker() {
    this.$refs.tagPicker.open()
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
}
</script>
