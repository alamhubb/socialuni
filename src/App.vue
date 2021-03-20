<script lang="ts">
import Vue from 'vue'
import { appModule, systemModule } from '@/plugins/store'
import SocialUniAuthVO from '@/model/openData/SocialUniAuthVO'
import SocialAuthType from '@/const/SocialAuthType'
import UniUtil from '@/utils/UniUtil'
import ResultVO from '@/model/ResultVO'
import ErrorCode from '@/const/ErrorCode'

export default Vue.extend({
  mpType: 'app',
  //测试已开启的情况下是否会这样
  onLaunch (params) {
    //页面启动，启动函数
    systemModule.appLunchAction()
    /*appModule.threeSecretKey = '075b7c28ea7246eeb91c19c304cc5eef'
    appModule.threeUserId = '10081'
    appModule.threeAuthType = SocialAuthType.user*/
    // Toast.toastLong('正确版本')
    //如果有跳转信息
    if (params.referrerInfo) {
      const info = params.referrerInfo
      //获取三方的appid
      appModule.threeAppId = info.appId
      //如果有跳转信息
      if (info.extraData) {
        const extraData: SocialUniAuthVO = info.extraData
        appModule.threeSecretKey = extraData.appSecretKey
        appModule.threeUserId = extraData.appUserId
        appModule.threeAuthType = extraData.authType
        if (!appModule.threeSecretKey) {
          const result: ResultVO<any> = new ResultVO<any>()
          result.errorCode = ErrorCode.business
          result.errorMsg = '社交联盟密钥错误'
          result.success = false
          UniUtil.showLoading('密钥错误，返回中...')
          uni.navigateBackMiniProgram({ extraData: result })
        }
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
