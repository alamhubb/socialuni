<template>
  <!--      底部客服信息-->
  <div class="row-between">
    <navigator :url="homeUrl" class="u-type-info u-border-bottom">
      <u-icon name="home" size="30" class="mr-xs"></u-icon>
      查看官网
    </navigator>

    <view @click="copyServiceNum" class="u-type-info u-border-bottom">
      客服微信、QQ：{{ qqService }}
      <u-icon name="attach" size="30" class="ml-xs"></u-icon>
    </view>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import UniUtil from 'socialuni/utils/UniUtil'
import ConfigMap from 'socialuni/const/ConfigMap'
import SkipUrlConst from 'socialuni/const/SkipUrlConst'
import { socialConfigStore } from 'socialuni/store'

@Component
export default class LoginFooterAppInfo extends Vue {
  @socialConfigStore.Getter(ConfigMap.qqServiceKey) qqService: string

  get homeUrl (): string {
    return SkipUrlConst.homeUrl()
  }

  copyServiceNum () {
    UniUtil.textCopy('491369310', '客服联系方式已复制')
  }
}
</script>
