<template>
  <!--  全部字体加大一号，白色背景，垂直居中，子元素宽度占满-->
  <!--  过来的授权及时手机号授权，也会提示先登录，可以自己选择手机号登录-->
  <div class="h100p bg-white px col-between-center">
    <!--    上padding 7vh，兼容各平台，底部10px，左右20px-->
    <div class="w100p pt-1p col-center flex-1">
      <div class="flex-none col-row-center h70px">
        <div class="text-xxl font-bold">欢迎使用社交联盟授权登录</div>
      </div>

      <view class="mt-xs h145px">
        <view class="h120px row-center">
          <!--          头部-->

          <!--          只有不为三方授权才显示logo-->
          <!--        登录界面，展示logo-->
          <image class="radius flex-none h100p"
                 mode="aspectFit"
                 src="/static/img/logo.jpg"
          />
        </view>
      </view>

      <!--      绑定手机号可发布动态提示-->
      <div class="row-center pb">
        <!--            如果为授权手机号，则提示-->
        <view class="u-border-bottom text-gray">
          绑定手机号后可发表动态，详情
        </view>
        <u-icon class="ml-xs text-gray" name="arrow-right"></u-icon>
      </div>

      <!--        隐私提示-->
      <user-privacy-agreement></user-privacy-agreement>

      <!--    返回和登录方式切换-->
      <!--      这个div是为了处理居中问题-->
      <div>
        <view class="h150px col-row-center">
          <view class="col-row-center w300px">
            <!--            微信登录界面，非手机号登录界面-->
            <!--              没登录提示登录，如果为三方授权且为授权用户信息，追加 并授权三个字-->
            <!-- 只要不为QQ小程序平台都可以使用微信登录-->
            <button v-if="isMpQQ" :disabled="!openTypeBtnEnable"
                    open-type="getUserInfo"
                    class="bg-gradual-qq h40px cu-btn lg row-all-center bd-none bg-active round mt w100p"
                    @getuserinfo="providerLogin">
              <u-icon color="white" name="weixin-fill" size="42"
                      class="mr-xs"></u-icon>
              QQ登录
            </button>
            <!--                app和h5也都可以用微信登录-->
            <button v-else :disabled="!openTypeBtnEnable"
                    class="bg-gradual-wx h40px cu-btn lg row-all-center bd-none bg-active round mt w100p"
                    @click="providerLogin">
              <u-icon color="white" name="weixin-fill" size="42"
                      class="mr-xs"></u-icon>
              微信登录
            </button>
            <!--                app和h5也都可以用微信登录-->
            <button :disabled="!openTypeBtnEnable"
                    class="bg-gradual-qq h40px cu-btn lg row-all-center bd-none bg-active round mt w100p"
                    @click="socialuniLogin">
              跳转清池授权登录
            </button>
            <!--              有用户-->
          </view>
        </view>
      </div>

      <view class="row-between-center w100p">
        <view class="row-col-center" @click="goBackPage">
          <u-icon class="mr-xs text-gray" name="arrow-left"></u-icon>
          <view class="text-gray u-border-bottom">
            {{ user ? '不绑定返回' : '不登录返回' }}
          </view>
        </view>
      </view>
    </div>

    <!--      底部客服信息-->
    <login-footer-app-info class="w100p mb-sm"></login-footer-app-info>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { socialAppModule, socialSystemModule, socialSystemStore, socialUserStore } from '../../store'
import Alert from '../../utils/Alert'
import LoginService from '../../service/LoginService'
import PageUtil from '../../utils/PageUtil'
import SystemStoreProp from '../../store/SystemStoreProp'
import LoginFooterAppInfo from '../SocialLogin/LoginFooterAppInfo.vue'
import ThirdApplyAuthInfo from '../SocialLogin/ThirdApplyAuthInfo.vue'
import UserPrivacyAgreement from '../SocialLogin/UserPrivacyAgreement.vue'
import SocialUniAuthVO from '../../model/openData/SocialUniAuthVO'
import UniUtil from '../../utils/UniUtil'
import CenterUserDetailRO from '../../model/social/CenterUserDetailRO'

@Component({
  components: {
    UserPrivacyAgreement,
    ThirdApplyAuthInfo,
    LoginFooterAppInfo
  }
})
export default class SocialLoginPage extends Vue {
  @socialUserStore.State('user') user: CenterUserDetailRO
  @socialUserStore.Getter('hasPhoneNum') hasPhoneNum: boolean

  @socialSystemStore.State('isMp') isMp: boolean
  @socialSystemStore.State(SystemStoreProp.isMpWx) isMpWx: boolean
  @socialSystemStore.State(SystemStoreProp.isMpQQ) isMpQQ: boolean


  //首先需要携带threeAppId和密钥去后台查询，三方信息，如果不对提示错误。然后也无法向后台授权。
  //如果三方信息错误，上面是显示，申请授权方信息错误，不予授权
  openTypeBtnEnable = true

  goBackPage () {
    if (!this.user) {
      PageUtil.goHome()
    } else {
      PageUtil.toMinePage()
    }
  }

  async providerLogin (result) {
    await this.LoginBase(this.providerLoginBase, result)
  }

  async socialuniLogin () {
    await this.LoginBase(this.socialuniLoginBase)
  }

  async LoginBase (loginFun: Function, result?) {
    if (this.openTypeBtnEnable) {
      UniUtil.showLoading('登录中')
      this.openTypeBtnEnable = false
      try {
        console.log('等待执行')
        await loginFun(result)
        console.log('执行完毕')
        this.loginAfterHint('登录成功')
      } finally {
        this.openTypeBtnEnable = true
        UniUtil.hideLoading()
      }
    }
  }

  //平台登录
  //登录，授权，绑定手机号各大平台登录结果，后者授权手机号结果
  async providerLoginBase (result) {
    //一行代码就可以获取登录所需要的信息, 还可以配合后台使用，一键登录，记住用户
    await LoginService.providerLogin(socialSystemModule.mpPlatform, result)
  }

  async socialuniLoginBase () {
    //开发模式模拟授权
    if (socialAppModule.isDevMode) {
      await LoginService.socialuniMockLogin()
    } else {
      const authVO: SocialUniAuthVO = new SocialUniAuthVO('user')
      PageUtil.toSocialUniAuth(authVO)
    }
  }

  loginAfterHint (msg: string) {
    if (!this.user.phoneNum) {
      msg += '，绑定手机号后才可发布内容'
    }
    //qq小程序下ios系统存在输入框冲突问题，使用了一个输入框，另一个就无法出现
    if (socialSystemModule.isIosAndMpQQ) {
      msg += '，如遇无法弹出输入框，请重启应用'
    }
    Alert.hint(msg).finally(() => {
      this.goBackPage()
    })
  }
}
</script>
