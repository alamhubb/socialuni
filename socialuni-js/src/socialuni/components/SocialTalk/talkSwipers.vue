<template>
  <view class="px-sm pb-sm" v-if="showSwipers">
    <!--  <view class="bg-primary px-smm pt-xs">-->
    <swiper :indicator-dots="true" :autoplay="true" circular :interval="2500" :style="{'height':swiperHeight+'px'}">
      <swiper-item class="bd-radius" v-for="(item,index) in homeSwipers" @click="skipWebView(index)">
        <!--        class="radius flex-none h100p"-->
        <image
          class="h100p bd-radius w100p"
          mode="aspectFill"
          :src="item.imgUrl"
        />
      </swiper-item>
    </swiper>
  </view>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'

import HomeSwiperVO from '../../model/HomeSwiperVO'
import ConfigMap from '../../constant/ConfigMap'
import SkipType from '../../constant/SkipType'
import { socialAppStore, socialConfigStore, socialSystemModule } from '../../store'
import RouterUtil from '../../utils/RouterUtil'

// todo 后台可控制是否显示轮播图
@Component
export default class TalkSwipersPage extends Vue {
  @socialAppStore.State('homeSwipers') readonly homeSwipers: HomeSwiperVO[]
  @socialConfigStore.Getter(ConfigMap.homeUrlKey) homeUrl: string
  @socialConfigStore.Getter(ConfigMap.swiperHeightKey) swiperHeight: number

  get showSwipers () {
    return this.homeSwipers && this.homeSwipers.length
  }

  skipWebView (current: number) {
    //这里要考虑，跳转其他小程序，跳转其他app，跳转本应用页面
    //区分跳转类型，跳转web，local，小程序，app
    //特殊处理小程序无法跳转app
    const homeSwiper = this.homeSwipers[current]
    console.log(current)
    console.log(this.homeSwipers)
    console.log(homeSwiper)
    //需要跳转
    if (homeSwiper.skip) {
      if (homeSwiper.skipType === SkipType.app) {
        if (socialSystemModule.isMp) {
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
