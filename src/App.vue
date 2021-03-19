<script lang="ts">
import Vue from 'vue'
import { appModule, systemModule } from '@/plugins/store'
import UniUtil from '@/utils/UniUtil'
import ThreeAuthVO from '@/model/openData/ThreeAuthVO'

export default Vue.extend({
  mpType: 'app',
  //测试已开启的情况下是否会这样
  onLaunch (params) {
    //页面启动，启动函数
    systemModule.appLunchAction()
    // Toast.toastLong('正确版本')
    //如果有跳转信息
    if (params.referrerInfo) {
      const info = params.referrerInfo
      //获取三方的appid
      appModule.threeProviderAppId = info.appId
      //如果有跳转信息
      if (info.extraData) {
        const extraData: ThreeAuthVO = info.extraData
        appModule.threeSecretKey = extraData.appSecretKey
        appModule.threeUserId = extraData.appUserId
        appModule.threeAuthType = extraData.authType
      }
    }
    //页面启动，启动函数
    // systemModule.appInit()
  }
})
</script>
<style lang="scss">
@import "./styles/iconfont/index.css";
@import "./styles/index.scss";
</style>
