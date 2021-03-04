<script lang="ts">
import Vue from 'vue'
import { appModule, systemModule } from '@/plugins/store'
import UniUtil from '@/utils/UniUtil'

export default Vue.extend({
  mpType: 'app',
  onLaunch (params) {
    UniUtil.toastLong('正确版本')
    if (params.referrerInfo) {
      const info = params.referrerInfo
      appModule.threeProviderAppId = info.appId
      if (info.extraData) {
        const extraData = info.extraData
        appModule.threeSecretKey = extraData.secretKey
        appModule.threeUserId = extraData.userId
      }
    }
    //页面启动，启动函数
    systemModule.appInit()
  }
})
</script>
<style lang="scss">
@import "./styles/iconfont/index.css";
@import "./styles/index.scss";
</style>
