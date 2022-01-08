<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { socialAppModule, socialOAuthModule, socialSystemModule } from '@/socialuni/store'
import UserService from '@/socialuni/service/UserService'
import UniUtil from '@/socialuni/utils/UniUtil'
import OAuthService from '@/socialuni/service/OAuthService'
import SocialuniConfig from '@/socialuni/config/SocialuniConfig'

@Component
export default class SocialMinxinVue extends Vue {
  onLaunch (params) {
    //@ts-ignore
    this.isAppPage = true
    //无论如何都要获取当前用户信息
    UserService.getMineUserInitDataAction()
    // 执行获取系统信息的函数,始终保持第一，因为别的都依赖于他
    // 获取用户需要使用需要限制性
    socialSystemModule.getSystemInfo()
    //为三方只授权不需要查询信息
    //页面启动，启动函数
    //如果有跳转信息
    if (SocialuniConfig.authApp) {
      //如果有跳转信息
      socialOAuthModule.setThreeAuthInfo(params)
    } else {
      socialAppModule.appLunchAction()
    }
    UniUtil.showShareMenu()
  }

  onShow (params) {
    //@ts-ignore
    if (this.isAppPage) {
      //避免已打开情况，进入不为lunchan而是show
      this.oAuthUserInfo(params)
    }
  }

  async oAuthUserInfo (params) {
    if (SocialuniConfig.authApp) {
      //避免已打开情况，进入不为lunchan而是show
      socialOAuthModule.setThreeAuthInfo(params)
    } else {
      await OAuthService.oAuthUserInfo(params)
    }
  }
}
</script>
