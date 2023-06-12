<template>
  <view class="px-mn pb-sm" v-if="showSwipers">
    <!--  <view class="bg-primary px-smm pt-xs">-->
    <swiper class="w100p" :indicator-dots="true" :autoplay="true" circular :interval="2500"
            :style="{'height':swiperHeight+'px'}">
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
import {Options, Vue} from 'vue-property-decorator'
import SkipType from "socialuni-constant/constant/SkipType";
import RouterUtil from "socialuni-util/src/util/RouterUtil";
import PageUtil from "socialuni-util/src/util/PageUtil";


// todo 后台可控制是否显示轮播图
@Options({})
export default class TalkSwipers extends Vue {
  get homeSwipers() {
    return socialAppModule.homeSwipers
  }

  get swiperHeight() {
    return socialuniConfigModule.appMoreConfig.swiperHeight
  }

  get showSwipers() {
    return this.homeSwipers && this.homeSwipers.length
  }

  skipWebView(current: number) {
    //这里要考虑，跳转其他小程序，跳转其他app，跳转本应用页面
    //区分跳转类型，跳转web，local，小程序，app
    //特殊处理小程序无法跳转app
    const homeSwiper = this.homeSwipers[current]
    //需要跳转
    if (homeSwiper.skip) {
      if (homeSwiper.skipType === SkipType.app) {
        if (socialuniSystemModule.isMp) {
          PageUtil.navigateToAll(homeSwiper.standType, homeSwiper.standUrl, homeSwiper.name)
        } else {
          PageUtil.navigateToWeb(homeSwiper.skipUrl, homeSwiper.name)
        }
      } else if (homeSwiper.skipType === SkipType.mp) {
        PageUtil.navigateToMp(homeSwiper.skipUrl, homeSwiper.name)
      } else if (homeSwiper.skipType === SkipType.web) {
        PageUtil.navigateToWeb(homeSwiper.skipUrl, homeSwiper.name)
      } else if (homeSwiper.skipType === SkipType.local) {
        RouterUtil.navigateTo(homeSwiper.skipUrl)
      } else {
        PageUtil.toWebHome()
      }
    }
  }
}
</script>
