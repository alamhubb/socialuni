<template>
  <!--  全部字体加大一号，白色背景，垂直居中，子元素宽度占满-->
  <!--  过来的授权及时手机号授权，也会提示先登录，可以自己选择手机号登录-->
  <div class="h100p bg-white px col-between-center">
    <!--    上padding 7vh，兼容各平台，底部10px，左右20px-->
    <div class="w100p pt-1p col-center flex-1">
      <div class="flex-none col-row-center h70">
        <div class="text-xxl font-bold">欢迎使用社交联盟授权登录</div>
      </div>

      <view class="mt-xs h145">
        <view class="h120 row-center">
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
        <q-icon class="ml-xs color-info" name="arrow-right" size="12"></q-icon>
      </div>

      <!--        隐私提示-->
      <user-privacy-agreement></user-privacy-agreement>

      <!--    返回和登录方式切换-->
      <!--      这个div是为了处理居中问题-->
      <div>
        <view class="h150 col-row-center">
          <view class="col-row-center w300">
            <!--                app和h5也都可以用微信登录-->
            <button :disabled="!openTypeBtnEnable"
                    class="bg-gradual-qq h40 cu-btn lg row-all-center bd-none bg-click round mt w100p"
                    @click="bindSocialuniPhone">
              前往清池授权手机号
            </button>
          </view>
        </view>
      </div>

      <view class="row-between-center w100p">
        <view class="row-col-center" @click="goBackPage">
          <q-icon class="mr-xs color-info" name="arrow-left" size="12"></q-icon>
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
import SocialAuthType from '@/socialuni/const/SocialAuthType'
import UniUtil from '@/socialuni/utils/UniUtil'
import PageUtil from '@/socialuni/utils/PageUtil'
import { socialSystemStore, socialUserStore } from '@/socialuni/store'
import CenterUserDetailRO from '@/socialuni/model/social/CenterUserDetailRO'
import SystemStoreProp from '@/socialuni/store/SystemStoreProp'
import UserPrivacyAgreement from '@/socialuni/components/SocialLogin/UserPrivacyAgreement.vue'
import LoginFooterAppInfo from '@/socialuni/components/SocialLogin/LoginFooterAppInfo.vue'
import SocialuniAuthQO from '@/socialuni/model/openData/SocialuniAuthQO'
import QIcon from '@/qing-ui/components/QIcon/QIcon.vue'

@Component({
  components: {
    QIcon,
    UserPrivacyAgreement,
    LoginFooterAppInfo
  }
})
export default class SocialPhonePage extends Vue {
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

  async bindSocialuniPhone () {
    if (this.openTypeBtnEnable) {
      UniUtil.showLoading('跳转中')
      this.openTypeBtnEnable = false
      try {
        console.log('等待执行')
        //开发模式模拟授权
        /*if (socialAppModule.isDevMode) {
          await MockService.mockBindSocialuniPhone()
        } else {
          const authVO: SocialuniAuthQO = new SocialuniAuthQO(SocialAuthType.phone)
          PageUtil.toSocialUniAuth(authVO)
        }*/
        console.log('chufa')
        const authVO: SocialuniAuthQO = new SocialuniAuthQO(SocialAuthType.phone)
        PageUtil.toSocialUniAuth(authVO)
        console.log('执行完毕')
      } finally {
        this.openTypeBtnEnable = true
        UniUtil.hideLoading()
      }
    }
  }
}
</script>
