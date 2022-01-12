<template>
  <view>
    <!--  #ifdef MP -->
    <q-navbar show-back>
      <view class="row-between-center w100p flex-auto">
        <view class="ml-xl font-bold text-md">
          {{ title }}
        </view>
      </view>
    </q-navbar>
    <!--  #endif -->
    <!--  #ifndef MP -->
    <q-navbar show-back>
      <view class="row-between-center w100p flex-auto">
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
import { Component, Vue } from 'vue-property-decorator'
//@ts-ignore
import PagePath from 'socialuni/const/PagePath'
import { socialSystemModule, socialSystemStore } from '@/socialuni/store'
import RouterUtil from '@/socialuni/utils/RouterUtil'
import QNavbar from '@/qing-ui/components/QNavbar/QNavbar.vue'

@Component({
  components: { QNavbar }
})
export default class WebBrowserPage extends Vue {
  @socialSystemStore.State('titleHeight') titleHeight

  webUrl = ''
  title = '网页'

  public $scope!: {
    $getAppWebview: any;
  }

  onLoad (params) {
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
  }

  onReady () {
    /*if (systemModule.isMp) {
      UniUtils.copyLink(this.webUrl)
    }*/
    // #ifdef APP-PLUS
    //ios情况下，赋值比获取快，所以需要在mounted里面赋值
    const currentWebview = this.$scope.$getAppWebview()
    const wv = currentWebview.children()[0]
    const height = socialSystemModule.contentHeight
    wv.setStyle({ top: this.titleHeight, height: height })
    // #endif
  }

  showShare () {
    RouterUtil.navigateTo(PagePath.share, { url: this.webUrl })
  }
}
</script>
