<template>
  <!--      底部客服信息-->
  <div class="row-between">
    <navigator v-if="homeUrl" :url="homeUrl" class="u-type-info u-border-bottom">
      <q-icon icon="home" size="15" class="mr-xs"></q-icon>
      查看官网
    </navigator>

    <view v-if="serviceWeChat" @click="copyServiceNum" class="flex-row u-type-info u-border-bottom">
      客服微信：{{ serviceWeChat }}
      <q-icon icon="attach" size="15" class="ml-xs"></q-icon>
    </view>
  </div>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator'
import UniUtil from '../../socialuni/utils/UniUtil'
import SkipUrlConst from '../../socialuni/constant/SkipUrlConst'
import {socialConfigModule, socialSystemStore} from '../../socialuni/store'
import QIcon from '@/qing-ui/components/QIcon/QIcon.vue'
import SystemStoreProp from '@/socialuni/store/SystemStoreProp'

@Component({
  components: {QIcon}
})
export default class LoginFooterAppInfo extends Vue {
  @socialSystemStore.State(SystemStoreProp.isMpQQ) isMpQQ: boolean

  get serviceWeChat() {
    return socialConfigModule.appMoreConfig.serviceWeChat
  }

  get homeUrl(): string {
    return SkipUrlConst.homeUrl()
  }

  copyServiceNum() {
    UniUtil.textCopy(this.serviceWeChat, '客服联系方式已复制')
  }
}
</script>
