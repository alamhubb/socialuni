<template>
  <view>
    <!--  #ifdef MP -->
    <q-navbar show-back>
      <view class="row-between-center w100p flex-1">
        <view class="ml-xl font-bold text-md">
          {{ title }}
        </view>
      </view>
    </q-navbar>
    <!--  #endif -->
    <!--  #ifndef MP -->
    <q-navbar show-back>
      <view class="row-between-center w100p flex-1">
        <view class="ml-xl font-bold text-md">
          {{ title }}
        </view>
        <view class="pr pl-xl" @click="showShare">
          <q-icon icon="more-dot-fill" size="25"></q-icon>
        </view>
      </view>
    </q-navbar>
    <!--  #endif -->

    <web-view :src="webUrl"></web-view>
  </view>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-facing-decorator'
import {onLoad, onReady} from "@dcloudio/uni-app";
import QNavbar from 'socialuni-ui-uni/src/components/QNavbar/QNavbar.vue'
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import PagePath from "socialuni-constant/constant/PagePath";
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";

@Component({
  components: {QNavbar}
})
export default class WebBrowserPage extends Vue {
  get titleHeight() {
    return socialuniSystemModule.titleHeight
  }

  webUrl = ''
  title = '网页'

  public $scope!: {
    $getAppWebview: any;
  }

  created() {
    onLoad((params) => {
      console.log(params)
      // this.webUrl = 'https://mp.socialuni.cn'
      this.webUrl = decodeURIComponent(params.url)
      const title: string = params.title
      if (title) {
        this.title = title
        uni.setNavigationBarTitle({
          title: title
        })
      }
    })

    onReady(() => {
      /*if (systemModule.isMp) {
        UniUtils.copyLink(this.webUrl)
      }*/
      // #ifdef APP-PLUS
      //ios情况下，赋值比获取快，所以需要在mounted里面赋值
      const currentWebview = this.$scope.$getAppWebview()
      const wv = currentWebview.children()[0]
      const height = socialuniSystemModule.contentHeight
      wv.setStyle({top: this.titleHeight, height: height})
      // #endif
    })
  }


  showShare() {
    SocialuniAppUtil.RouterUtil.navigateTo(PagePath.share, {url: this.webUrl})
  }
}
</script>
