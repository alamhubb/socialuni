<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { socialSystemModule, socialUserModule } from '@/socialuni/store'
import UserService from '@/socialuni/service/UserService'
import UniUtil from '@/socialuni/utils/UniUtil'
import SocialLoginRO from '@/socialuni/model/social/SocialLoginRO'
import UniUserInfoRO from '@/socialuni/model/UniUserInfoRO'
import OAuthService from '@/socialuni/service/OAuthService'
import ToastUtil from '@/socialuni/utils/ToastUtil'
import PageUtil from '@/socialuni/utils/PageUtil'
import ResultRO from '@/socialuni/model/social/ResultRO'

@Component
export default class SocialMinxinVue extends Vue {
  onLaunch () {
    //@ts-ignore
    this.isAppPage = true
    //无论如何都要获取当前用户信息
    UserService.getMineUserInitDataAction()
    // 执行获取系统信息的函数,始终保持第一，因为别的都依赖于他
    // 获取用户需要使用需要限制性
    socialSystemModule.getSystemInfo()
    //为三方只授权不需要查询信息
    //页面启动，启动函数
    socialSystemModule.appLunchAction()
    //页面启动，启动函数
    // systemModule.appInit()
    UniUtil.showShareMenu()
  }

  onShow (params) {
    //@ts-ignore
    if (this.isAppPage) {
      //避免已打开情况，进入不为lunchan而是show
      this.oAuthUserInfo(params)
    }
    // this.socialPageProvide.msgInputShow = true
  }

  async oAuthUserInfo (params) {
    if (params && params.referrerInfo) {
      const info = params.referrerInfo
      //这里可以返回回来的appId
      // appModule.threeProviderAppId = info.appId
      const extraData: ResultRO<SocialLoginRO<UniUserInfoRO>> = info.extraData
      if (extraData) {
        if (extraData.success) {
          const authData: SocialLoginRO<UniUserInfoRO> = extraData.data

          if (socialUserModule.hasUser) {
            await OAuthService.oAuthBindSocialuniPhone(authData)
          } else {
            await OAuthService.oAuthUserPhoneNumLogin(authData)
          }
          ToastUtil.toastLong('授权成功')
          PageUtil.toMinePage()
        }
      }
    }
  }


  onHide () {
    // console.log(this.socialPageProvide.msgInputShow)
    // this.socialPageProvide.msgInputShow = false
  }
}
</script>
