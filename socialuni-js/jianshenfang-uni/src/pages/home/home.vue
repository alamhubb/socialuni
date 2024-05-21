<template>
  <div class="pb-smm bg-home1 px-sm h100p pt-100">

    <div>
      <swiper class="w100p" :indicator-dots="true" :autoplay="true" circular :interval="2500"
              :style="{'height':150+'px'}">
        <swiper-item class="bd-radius" v-for="(item,index) in homeSwipers" @click="skipWebView(index)">
          <!--        class="radius flex-none h100p"-->
          <img
              class="h100p bd-radius-10 w100p"
              mode="aspectFill"
              :src="item.imgUrl"
          />
        </swiper-item>
      </swiper>
    </div>

    <div class="mt">
      <div class="flex-row">
        <!--          bg-orange3-->
        <div class="flex-1 row-all-center py-10 bd-radius-10 mb-sm elevation-4 bg-white">
          <div class="flex-1 col-all-center py-10 mr-sm">
            <!--          <div class="flex-1 row-all-center py-10 bd-radius-10 mb-sm elevation-4 mr-sm color-white" style="background-color: #ff8b36">-->
            <div>
              <date-icon class="size40"></date-icon>
            </div>
            <div class="font-12 mt-xs">
              团课预约
            </div>
          </div>

          <div class="flex-1 col-all-center py-10 mr-sm">
            <!--          <div class="flex-1 row-all-center py-10 bd-radius-10 mb-sm elevation-4 mr-sm color-white" style="background-color: #ff8b36">-->
            <div>
              <trumpet class="size40"></trumpet>
            </div>
            <div class="font-12 mt-xs">
              活动订单
            </div>
          </div>

          <div class="flex-1 col-all-center py-10 mr-sm">
            <!--          <div class="flex-1 row-all-center py-10 bd-radius-10 mb-sm elevation-4 mr-sm color-white" style="background-color: #ff8b36">-->
            <div>
              <shopping  class="size40"></shopping>
            </div>
            <div class="font-12 mt-xs">
              会员商城
            </div>
          </div>

          <div class="flex-1 col-all-center py-10 mr-sm">
            <!--          <div class="flex-1 row-all-center py-10 bd-radius-10 mb-sm elevation-4 mr-sm color-white" style="background-color: #ff8b36">-->
            <div>
              <!--              <video-icon class="size40"></video-icon>-->
              <course-icon  class="size40"></course-icon>
            </div>
            <div class="font-12 mt-xs">
              线上课程
            </div>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, Watch, toNative} from 'vue-facing-decorator'
import TalkView from "socialuni-community-view-uni/src/views/talk/TalkView.vue"
import SocialGenderTag from "socialuni-user-ui/src/components/SUserGenderTag.vue";
import QRowItem from "qing-ui/src/components/QRowItem.vue";
import QIcon from "qing-ui/src/components/QIcon.vue";
import SkipType from "socialuni-constant/constant/SkipType";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import SocialuniAppPageUtil from "socialuni-app-sdk/src/util/SocialuniAppPageUtil";
import dateIcon from '@/static/svg/dateIcon.svg'
import trumpet from '@/static/svg/trumpet.svg'
import shopping from '@/static/svg/shopping.svg'
import courseIcon from '@/static/svg/courseIcon.svg'


@toNative
@Component({
  components: {
    SocialGenderTag,
    QIcon,
    QRowItem,
    dateIcon,
    trumpet,
    shopping,
    courseIcon,
    TalkView
  }
})
export default class TalkPage extends Vue {


  get homeSwipers() {
    return [
      {imgUrl: 'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/lunbo/qingchirules.png'},
      {imgUrl: 'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/lunbo/contact.png'},
    ]
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
          SocialuniAppPageUtil.navigateToAll(homeSwiper.standType, homeSwiper.standUrl, homeSwiper.name)
        } else {
          SocialuniAppPageUtil.navigateToWeb(homeSwiper.skipUrl, homeSwiper.name)
        }
      } else {
        SocialuniAppPageUtil.navigateToAll(homeSwiper.skipType, homeSwiper.standUrl, homeSwiper.name)
      }
    }
  }
}
</script>
