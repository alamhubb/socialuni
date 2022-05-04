<template>
  <div class="h100p">
    <social-talk-pc-page v-if="isPc"></social-talk-pc-page>
    <social-talk-mobile-page v-else></social-talk-mobile-page>
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
  socialAppStore,
  socialConfigStore,
  socialLocationModule,
  socialLocationStore,
  socialNotifyModule,
  socialNotifyStore, socialSystemStore,
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
import SocialTalkMobilePage from "@/socialuni/components/SocialPages/SocialTalkMobilePage.vue";
import SocialTalkPcPage from "@/socialuni/components/SocialPages/SocialTalkPcPage.vue";

// todo 后台可控制是否显示轮播图

@Component({
  components: {
    SocialTalkPcPage,
    SocialTalkMobilePage,
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
  @socialSystemStore.State('isPc') isPc: boolean
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
}
</script>
