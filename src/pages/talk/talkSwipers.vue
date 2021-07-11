<template>
  <view class="px-smm pt-xs" v-if="showSwipers">
    <!--  <view class="bg-primary px-smm pt-xs">-->
    <u-swiper :height="swiperHeight" :list="homeSwipers" name="imgUrl" @click="skipWebView"
              :interval="2500"
    ></u-swiper>
  </view>
</template>

<script lang="ts">
import {
  Vue,
  Component
} from 'vue-property-decorator'

import { namespace } from 'vuex-class'

import HomeSwiperVO from '@/model/HomeSwiperVO'
import ConfigMap from '@/const/ConfigMap'
import SkipType from '@/const/SkipType'
import { systemModule } from '@/store'
import RouterUtil from '@/utils/RouterUtil'

const appStore = namespace('app')
const configStore = namespace('config')
// todo 后台可控制是否显示轮播图
@Component
export default class TalkSwipersPage extends Vue {
  @appStore.State('homeSwipers') readonly homeSwipers: HomeSwiperVO[]
  @configStore.Getter(ConfigMap.homeUrlKey) homeUrl: string
  swiperHeight: number = ConfigMap.swiperHeightDefault

  get showSwipers () {
    return this.homeSwipers.length
  }

  skipWebView (current: number) {
    //这里要考虑，跳转其他小程序，跳转其他app，跳转本应用页面
    //区分跳转类型，跳转web，local，小程序，app
    //特殊处理小程序无法跳转app
    const homeSwiper = this.homeSwipers[current]
    //需要跳转
    if (homeSwiper.skip) {
      if (homeSwiper.skipType === SkipType.app) {
        if (systemModule.isMp) {
          RouterUtil.navigateToAll(homeSwiper.standType, homeSwiper.standUrl, homeSwiper.name)
        } else {
          RouterUtil.navigateToWeb(homeSwiper.skipUrl, homeSwiper.name)
        }
      } else if (homeSwiper.skipType === SkipType.mp) {
        RouterUtil.navigateToMp(homeSwiper.skipUrl, homeSwiper.name)
      } else if (homeSwiper.skipType === SkipType.web) {
        RouterUtil.navigateToWeb(homeSwiper.skipUrl, homeSwiper.name)
      } else if (homeSwiper.skipType === SkipType.local) {
        RouterUtil.navigateTo(homeSwiper.skipUrl)
      } else {
        RouterUtil.toWebHome()
      }
    }
  }
}
</script>
