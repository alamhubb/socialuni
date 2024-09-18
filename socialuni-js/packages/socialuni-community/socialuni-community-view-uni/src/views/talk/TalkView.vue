<template>
  <div class="flex-col h100p bg-theme-bg">
    <!--    不能使用100%，h5，不包含tabbar，尺寸计算不正确，所以需要使用h100vh-->
    <div class="flex-col h100vh">
      <q-navbar class="flex-none">
        <div class="row-col-center ml-xs mr-sm font-bold bg-click" @click="openCityPicker">
          <q-icon size="14" icon="map-fill"></q-icon>
          {{ location.adName }}
          <q-icon icon="mdi-chevron-right"></q-icon>
        </div>
        <q-search class="flex-1 mr-sm" @click="openTagPicker">
          <q-icon class="mx-5 text-gray" size="16" icon="search"></q-icon>
          <div v-if="selectTagName" class="row-col-center flex-1" @click.stop="">
            <div class="q-tag round bg-green-plain light row-all-center">
              {{ selectTagName }}
            </div>
            <q-icon class="mr-sm text-gray row-all-center" icon="close" @click="deleteTag"></q-icon>
          </div>
          <div v-else class="cursor-text flex-1" @click="openTagPicker">选择话题</div>
        </q-search>
        <!--        <div class="mr-sm" :class="{'text-theme':useFilters}">
                  <q-icon icon="mdi-filter-variant" size="28" @click="showFilterModel"></q-icon>
                </div>-->
        <!--                <div v-if="user" class="position-relative mr-sm">
                            <q-icon icon="bell-fill" @click="toNotifyVue" size="28"></q-icon>
                            &lt;!&ndash;          <u-badge :count="unreadNotifiesNum" size="mini"
                                               :offset="[0, 0]" @click="toNotifyVue"></u-badge>&ndash;&gt;
                        </div>-->
        <div>
          <q-icon icon="plus-circle" size="28" @click="toTalkAdd"></q-icon>
        </div>
      </q-navbar>


      <tabs-talk class="flex-1" ref="tabsTalk"></tabs-talk>
    </div>
    <msg-input class="w100p">
    </msg-input>


    <social-tag-picker ref="tagPicker" @change="changeTag"></social-tag-picker>
    <q-city-picker ref="cityPicker" :value="location" @input="cityChange"></q-city-picker>
  </div>
</template>

<script lang="ts">
import {Component, Vue, Watch, toNative} from 'vue-facing-decorator'

import QButton from 'qingjs-ui/src/components/QButton.vue'
import QNavbar from 'qingjs-ui-uni/src/components/QNavbar/QNavbar.vue'
import QSearch from 'qingjs-ui-uni/src/components/QSearch/QSearch.vue'
import QIcon from 'qingjs-ui/src/components/QIcon.vue'
import QPopup from 'qingjs-ui/src/components/QPopup.vue'
import QSlider from 'qingjs-ui-uni/src/components/QSlider/QSlider.vue'
import QTabs from "qingjs-ui/src/components/QTabs/QTabs.vue";
import TagSearch from "./TagSearch.vue";
import TabsTalk from "./tabsTalk.vue";
import TalkSwipers from "./talkSwipers.vue";
import TagVO from "socialuni-api-base/src/model/community/tag/TagVO";
import DistrictVO from "socialuni-api-base/src/model/DistrictVO";
import {onHide, onLoad, onShow} from "@dcloudio/uni-app";
import SocialTagPicker from "socialuni-community-view-uni/src/components/SocialTagPicker.vue";
import QCityPicker from "socialuni-community-view-uni/src/components/QCityPicker/QCityPicker.vue";
import CommunityPageUtil from "socialuni-community-sdk/src/util/CommunityPageUtil";
import {socialuniTagModule} from "socialuni-community-sdk/src/store/SocialTagModule";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import {socialNotifyModule} from "socialuni-community-sdk/src/store/SocialNotifyModule";
import {socialAppModule} from "socialuni-app-sdk/src/store/SocialAppModule";
import {socialLocationModule} from "socialuni-community-sdk/src/store/SocialLocationModule";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";
import MsgInput from "socialuni-ui/src/components/MsgInput.vue";
import {getCurrentInstance} from "vue";

// todo 后台可控制是否显示轮播图


@Component({
  components: {
    SocialTagPicker,
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
class TalkView extends Vue {
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
    onLoad((params) => {
      if (params.load && params.load === 'true') {
        this.initQuery()
      }
    })
    onHide(() => {
      this.$refs.tabsTalk.tabsTalkOnHide()
    })
    onShow(() => {
      // socialChatModule.computedChatsUnreadNumTotalAction();
    });
  }

  // life
  mounted() {
    QingAppUtil.NativeUtil.showShareMenu()
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
export default toNative(TalkView)
</script>
