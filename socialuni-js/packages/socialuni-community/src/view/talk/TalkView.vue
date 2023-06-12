<template>
  <view class="flex-col h100p bg-theme-bg">
    <!--    不能使用100%，h5，不包含tabbar，尺寸计算不正确，所以需要使用h100vh-->
    <view class="flex-col h100vh">
      <q-navbar class="flex-none">
        <div class="row-col-center ml-xs mr-sm font-bold bg-click" @click="openCityPicker">
          <q-icon size="14" icon="map-fill"></q-icon>
          {{ location.adName }}
          <q-icon icon="mdi-chevron-right"></q-icon>
        </div>
        <q-search class="flex-1 mr-sm" @click="openTagPicker">
          <q-icon class="mx-5 text-gray" size="16" icon="search"></q-icon>
          <view v-if="selectTagName" class="row-col-center flex-1" @click.stop="">
            <view class="q-tag round bg-green-plain light row-all-center">
              {{ selectTagName }}
            </view>
            <q-icon class="mr-sm text-gray row-all-center" icon="close" @click="deleteTag"></q-icon>
          </view>
          <div v-else class="cursor-text flex-1" @click="openTagPicker">选择话题</div>
        </q-search>
        <!--        <view class="mr-sm" :class="{'text-theme':useFilters}">
                  <q-icon icon="mdi-filter-variant" size="28" @click="showFilterModel"></q-icon>
                </view>-->
        <view v-if="user" class="position-relative mr-sm">
          <q-icon icon="bell-fill" @click="toNotifyVue" size="28"></q-icon>
          <!--          <u-badge :count="unreadNotifiesNum" size="mini"
                             :offset="[0, 0]" @click="toNotifyVue"></u-badge>-->
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


    <social-tag-picker ref="tagPicker" @change="changeTag"></social-tag-picker>
    <q-city-picker ref="cityPicker" :value="location" @input="cityChange"></q-city-picker>
  </view>
</template>

<script lang="ts">
import {Options, Vue, Watch} from 'vue-property-decorator'

import QButton from 'socialuni-ui-uni/src/components/QButton/QButton.vue'
import QNavbar from 'socialuni-ui-uni/src/components/QNavbar/QNavbar.vue'
import QSearch from 'socialuni-ui-uni/src/components/QSearch/QSearch.vue'
import QIcon from 'socialuni-ui-uni/src/components/QIcon/QIcon.vue'
import QPopup from 'socialuni-ui-uni/src/components/QPopup/QPopup.vue'
import QSlider from 'socialuni-ui-uni/src/components/QSlider/QSlider.vue'
import QTabs from "socialuni-ui-uni/src/components/QTabs/QTabs.vue";
import TagSearch from "./TagSearch.vue";
import TabsTalk from "./tabsTalk.vue";
import TalkSwipers from "./talkSwipers.vue";
import UniUtil from "socialuni-app/src/util/UniUtil";
import RouterUtil from "socialuni-util/src/util/RouterUtil";
import PagePath from "socialuni-constant/constant/PagePath";
import PageUtil from "socialuni-util/src/util/PageUtil";
import TagVO from "socialuni-api-base/src/model/community/tag/TagVO";
import DistrictVO from "socialuni-api-base/src/model/DistrictVO";
import {onHide, onShow} from "@dcloudio/uni-app";
import SocialTagPicker from "../../component/SocialTagPicker.vue";
import QCityPicker from "../../component/QCityPicker/QCityPicker.vue";
import CommunityPageUtil from "../../util/CommunityPageUtil";
import {socialChatModule} from "socialuni-im/src/store/SocialChatModule";
import {socialuniTagModule} from "../../store/SocialTagModule";
import {socialuniUserModule} from "socialuni-user/src/store/SocialuniUserModule";
import {socialNotifyModule} from "../../store/SocialNotifyModule";
import {socialAppModule} from "socialuni-app/src/store/SocialAppModule";
import {socialLocationModule} from "../../store/SocialLocationModule";
import {socialuniConfigModule} from "socialuni-app/src/store/SocialuniConfigModule";

// todo 后台可控制是否显示轮播图

@Options({
  components: {
    SocialTagPicker,
    QCityPicker,
    QTabs,
    QSlider,
    QPopup,
    QIcon,
    QSearch,
    QNavbar,
    // MsgInput,
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

  get tags() {
    return socialuniTagModule.tags
  }

  get user() {
    return socialuniUserModule.mineUser
  }

  get unreadNotifies() {
    return socialNotifyModule.unreadNotifies
  }

  // 轮播图
  get homeSwipers() {
    return socialAppModule.homeSwipers
  }

  get selectTagName() {
    return socialuniTagModule.selectTagName
  }

  get location() {
    return socialLocationModule.location
  }

  current = 0
  unreadNotifiesNum = 0

  // 滚动超过轮播图隐藏轮播图，scroll-view开启滚动

  get talkTabsTop() {
    if (socialuniConfigModule.appMoreConfig.showSwipers && this.homeSwipers && this.homeSwipers.length) {
      return socialuniConfigModule.appMoreConfig.swiperHeight + 10
    }
    return 0
  }

  created() {
     console.log(1111)

    onHide(() => {
      this.$refs.tabsTalk.tabsTalkOnHide()
    })
    onShow(()=>{
      socialChatModule.computedChatsUnreadNumTotalAction();
    });
  }

  // life
  mounted() {
    UniUtil.showShareMenu()
    this.initQuery()
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
      //首次打开talk页面，获取用户位置用来查询
      socialLocationModule.appLunchInitDistrict().then(() => {
        this.$refs.tabsTalk.initQuery()
      })
    })
  }

  startPullDown() {
    this.$refs.tabsTalk.startPullDown()
  }

  openTagPicker() {
    this.$refs.tagPicker.open()
  }

  @Watch('selectTagName')
  selectTagNameWatch() {
    //已经watch了，所以修改tag无需重新设置查询
    this.startPullDown()
  }

  // tag
  changeTag(tag: TagVO) {
    socialuniTagModule.setSelectTagName(tag.name)
  }

  deleteTag() {
    socialuniTagModule.setSelectTagName(null)
  }

  toNotifyVue() {
    socialNotifyModule.queryUnreadNotifiesAndUpdateHasReadAction()
    CommunityPageUtil.toNotifyPage()
  }

  // 点击加号去新增talk
  toTalkAdd() {
    CommunityPageUtil.toTalkAddPage()
  }

  openCityPicker() {
    this.$refs.cityPicker.open()
  }

  cityChange(district: DistrictVO) {
    socialLocationModule.setLocation(district)
    this.startPullDown()
  }
}
</script>
