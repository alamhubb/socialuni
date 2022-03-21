<template>
  <!--      底部客服信息-->
  <div class="row-between">
    <navigator :url="homeUrl" class="u-type-info u-border-bottom">
      <q-icon icon="home" size="15" class="mr-xs"></q-icon>
      查看官网
    </navigator>

    <view @click="copyServiceNum" class="flex-row u-type-info u-border-bottom">
      客服{{ isMpQQ ? 'QQ' : '微信' }}：{{ qqService }}
      <q-icon icon="attach" size="15" class="ml-xs"></q-icon>
    </view>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import UniUtil from '../../utils/UniUtil'
import ConfigMap from '../../constant/ConfigMap'
import SkipUrlConst from '../../constant/SkipUrlConst'
import { socialConfigStore, socialSystemStore } from '../../store'
import QIcon from '@/qing-ui/components/QIcon/QIcon.vue'
import SystemStoreProp from '@/socialuni/store/SystemStoreProp'

@Component({
  components: { QIcon }
})
export default class LoginFooterAppInfo extends Vue {
  @socialConfigStore.Getter(ConfigMap.qqServiceKey) qqService: string
  @socialSystemStore.State(SystemStoreProp.isMpQQ) isMpQQ: boolean

  get homeUrl (): string {
    return SkipUrlConst.homeUrl()
  }

  copyServiceNum () {
    UniUtil.textCopy('491369310', '客服联系方式已复制')
  }
}
</script>
