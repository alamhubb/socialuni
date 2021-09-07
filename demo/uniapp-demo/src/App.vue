<script lang="ts">
import Vue from 'vue'
import { appModule, socialSystemModule, userModule } from '/socialuni/store'
import UserService from '/socialuni/service/UserService'
import ThreeAuthType from '/socialuni/const/ThreeAuthType'
import Toast from '/socialuni/utils/Toast'
import ResultVO from '/socialuni/model/ResultVO'
import ThreeAuthResultVO from '/socialuni/model/openData/ThreeAuthResultVO'
import PageUtil from '/socialuni/utils/PageUtil'
import SocialLoginRO from '/socialuni/model/social/SocialLoginRO'
import CenterUserDetailRO from '/socialuni/model/social/CenterUserDetailRO'
import SocialConfig from '/socialuni/model/SocialConfig'

export default Vue.extend({
  mpType: 'app',
  //测试已开启的情况下是否会这样
  onLaunch (params) {
    console.log(123)
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
    if (SocialConfig.authApp) {
      appModule.setThreeAuthInfo(params)
    }
    //无论如何都要获取当前用户信息
    UserService.getMineUserInitDataAction()
    // 执行获取系统信息的函数,始终保持第一，因为别的都依赖于他
    // 获取用户需要使用需要限制性
    socialSystemModule.getSystemInfo()
    //为三方只授权不需要查询信息
    if (appModule.threeSecretKey) {
      // appModule.getThreeDevUserAction()
    } else {
      //页面启动，启动函数
      socialSystemModule.appLunchAction()
    }
    //页面启动，启动函数
    // systemModule.appInit()
  },
  //@ts-ignore
  onShow (params) {
    //避免已打开情况，进入不为lunchan而是show
    if (params && params.referrerInfo) {
      if (SocialConfig.authApp) {
        appModule.setThreeAuthInfo(params)
      } else {
        const info = params.referrerInfo
        //这里可以返回回来的appId
        // appModule.threeProviderAppId = info.appId
        const extraData: ResultVO<ThreeAuthResultVO> = info.extraData
        if (extraData) {
          if (extraData.success) {
            const authData: ThreeAuthResultVO = extraData.data
            const socialLoginRO: SocialLoginRO<CenterUserDetailRO> = new SocialLoginRO(authData.token, authData.user)
            if (authData.authType === ThreeAuthType.user) {
              Toast.toastLong('授权登录成功')
              UserService.getMineUserInitDataActionByToken(socialLoginRO)
            } else {
              Toast.toastLong('手机号绑定成功')
              userModule.user.phoneNum = authData.phoneNum
              userModule.setUser(userModule.user)
            }
            PageUtil.toMinePage()
          }
        }
      }
    }
  }
})
</script>
<style lang="scss">
@import "/socialuni/styles/color/index.css";
@import "/socialuni/styles/iconfont/index.css";
@import "/socialuni/styles/index.scss";
</style>
