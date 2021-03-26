<script lang="ts">
import Vue from 'vue'
import { appModule, systemModule } from '@/plugins/store'
import UserService from '@/service/UserService'

export default Vue.extend({
  mpType: 'app',
  //测试已开启的情况下是否会这样
  onLaunch (params) {
    // appModule.threeSecretKey = '075b7c28ea7246eeb91c19c304cc5eef'
    // appModule.threeUserId = '10081'
    // appModule.threeAuthType = SocialAuthType.phone
    // Toast.toastLong('正确版本')
    // promise方式
    /*uniCloud.callFunction({
      name: 'getUser',
      data: { a: 1 }
    })*/

    //如果有跳转信息
    appModule.setThreeAuthInfo(params)
    //无论如何都要获取当前用户信息
    UserService.getMineUserInitDataAction()
    // 执行获取系统信息的函数,始终保持第一，因为别的都依赖于他
    // 获取用户需要使用需要限制性
    systemModule.getSystemInfo()
    //为三方只授权不需要查询信息
    if (appModule.threeSecretKey) {
      appModule.getThreeDevUserAction()
    } else {
      //页面启动，启动函数
      systemModule.appLunchAction()
    }
    //页面启动，启动函数
    // systemModule.appInit()
  },
  //@ts-ignore
  onShow (params) {
    appModule.setThreeAuthInfo(params)
  }
})
</script>
<style lang="scss">
@import "./styles/color/index.css";
@import "./styles/iconfont/index.css";
@import "./styles/index.scss";
</style>
