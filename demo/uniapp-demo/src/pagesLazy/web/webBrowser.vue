<template>
  <view>
    <!--  #ifndef MP -->
    <u-navbar>
      <view class="row-between-center w100p flex-auto">
        <view class="ml-xl font-bold text-md">
          {{ title }}
        </view>
        <view class="pr pl-xl" @click="showShare">
          <q-icon icon="more-dot-fill" size="25"></q-icon>
        </view>
      </view>
    </u-navbar>
    <!--  #endif -->

    <web-view :src="webUrl"></web-view>
  </view>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import PagePath from 'socialuni/const/PagePath'
import { namespace } from 'vuex-class'
import { socialSystemModule } from 'socialuni/store'
import RouterUtil from 'socialuni/utils/RouterUtil'

const systemStore = namespace('system')

@Component
export default class WebBrowserPage extends Vue {
  @systemStore.State('titleHeight') titleHeight

  webUrl = ''
  title = '网页'

  public $scope!: {
    $getAppWebview: any;
  }

  onLoad (params) {
    // this.webUrl = 'https://mp.qingchiapp.com'
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
