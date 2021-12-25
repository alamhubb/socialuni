<template>
  <!--      底部客服信息-->
  <div class="row-between">
    <navigator :url="homeUrl" class="u-type-info u-border-bottom">
      <q-icon icon="home" size="15" class="mr-xs"></q-icon>
      查看官网
    </navigator>

    <view @click="copyServiceNum" class="flex-row u-type-info u-border-bottom">
      客服微信、QQ：{{ qqService }}
      <q-icon icon="attach" size="15" class="ml-xs"></q-icon>
    </view>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import UniUtil from '../../utils/UniUtil'
import ConfigMap from '../../const/ConfigMap'
import SkipUrlConst from '../../const/SkipUrlConst'
import { socialConfigStore } from '../../store'
import QIcon from '@/socialuni/components/QIcon/QIcon.vue'

@Component({
  components: { QIcon }
})
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
