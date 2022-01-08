<template>
  <!--  全部字体加大一号，白色背景，垂直居中，子元素宽度占满-->
  <!--  过来的授权及时手机号授权，也会提示先登录，可以自己选择手机号登录-->
  <div class="h100p bg-white col-all-center">
    <div v-if="goBackCountDown" class="font-bold text-lg">
      {{ authSuccess ? '授权成功' : '不授权' }}，{{ goBackCountDown }} 秒后返回...
    </div>

    <!--    上padding 7vh，兼容各平台，底部10px，左右20px-->
    <div v-else class="pt-6vh pb-sm px w100p">
      <div class="flex-none col-row-center h80">
        <div class="text-xxl font-bold">欢迎使用社交联盟授权登录</div>
      </div>

      <view class="mt">
        <view class="h150 flex-col">
          <!--      底部客服信息-->
          <view class="flex-1">
            <!--            三方授权显示三方的信息-->
            <view class="row-col-center px-sm py-xs" v-if="threeDevAccount">
              <u-avatar class="mr-sm" src="/static/img/logo.jpg" mode="square" size="50"></u-avatar>
              <!--      <u-avatar class="mr-sm" :src="threeDevUser.avatarUrl" mode="square" size="50"></u-avatar>-->
              <text class="mr-sm font-bold">{{ threeDevAccount.appName }}</text>
              申请
            </view>
            <!--    如果授权用户信息提示显示获取用户信息-->
            <view class="font-bold row-col-center pd-sm">
              获取您的手机号、昵称、头像、地区、性别及动态
            </view>

            <!--  如果没有用户，展示提示请登录-->
            <!--  如果为三方授权，用户授权，没有登录，则显示，上面这俩可以合并-->
            <view v-if="!user" class="pd-sm">
              您未登录，请登录并绑定手机号后再进行授权
            </view>
            <view v-else-if="!hasPhoneNum" class="px-sm py-xs row-col-center">
              <!--                请点击绑定手机号进行授权-->
              您未绑定手机号，请绑定手机号后再进行授权
            </view>
            <template v-else>

              <!-- 如果为三方授权， 不为手机号授权， 如果有用户显示出来用的信息-->
              <view v-if="user" class="flex-row pd-sm u-border-top-bottom h65px">
                <u-avatar class="mr-sm" :src="user.avatar" mode="square"></u-avatar>
                <view class="col-around flex-1">
                  <view>{{ user.nickname }}</view>
                  <view class="text-grey text-sm">微信个人信息</view>
                </view>
                <view class="col-center">
                  <q-icon icon="checkmark" class="color-success" size="20"></q-icon>
                </view>
              </view>
              <!--            为授权手机号显示授权手机号-->
              <view class="font-bold row-col-center pd-sm">
                获取您清池绑定的手机号
                <!--                如果有手机号显示-->
                <!--                <text>：186*****595</text>-->
                <text v-if="hasPhoneNum">：{{ user.phoneNum }}</text>
              </view>
              <!--  如果三方授权，手机号授权，没有用户，没有手机号，提示-->
              <!--  请登录并绑定手机号后进行授权-->
            </template>
          </view>
          <!--          头部-->

        </view>
      </view>

      <!--        隐私提示-->
      <div class="pt-sm h50">
      </div>


      <!--    返回和登录方式切换-->
      <!--      这个div是为了处理居中问题-->
      <div>
        <view class="h150 col-row-center">
          <view class="col-row-center w300">
            <!--            微信登录界面，非手机号登录界面-->
            <!--              没登录提示登录，如果为三方授权且为授权用户信息，追加 并授权三个字-->
            <!-- 只要不为QQ小程序平台都可以使用微信登录-->
            <button v-if="!user" :disabled="!openTypeBtnEnable"
                    class="h40 cu-btn lg bg-gradual-qq row-all-center bd-none bg-active round mt w100p"
                    @click="toLoginPage">
              前往登陆
            </button>
            <button v-else-if="!hasPhoneNum" :disabled="!openTypeBtnEnable"
                    class="h40 cu-btn lg bg-gradual-qq row-all-center bd-none bg-active round mt w100p"
                    @click="toBindPhone">
              前往绑定手机号
            </button>
            <button v-else :disabled="!openTypeBtnEnable"
                    class="h40 cu-btn lg bg-gradual-qq row-all-center bd-none bg-active round mt w100p"
                    @click="oAuthUserInfoAndPhoneNum">
              <template>
                授权手机号和用户信息
              </template>
            </button>
          </view>

          <view class="row-between-center w300 h40 mt-lg">
            <template>
              <view class="row-col-center" @click="goBackPage">
                <q-icon class="mr-xs color-info" icon="arrow-left" size="12"></q-icon>
                <view class="text-gray u-border-bottom">
                  不授权返回
                </view>
              </view>
            </template>
          </view>
        </view>
      </div>

      <!--      底部客服信息-->
      <login-footer-app-info></login-footer-app-info>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import SystemStoreProp from '@/socialuni/store/SystemStoreProp'
import UserPrivacyAgreement from '@/socialuni/components/SocialLogin/UserPrivacyAgreement.vue'
import LoginFooterAppInfo from '@/socialuni/components/SocialLogin/LoginFooterAppInfo.vue'
import CenterUserDetailRO from '@/socialuni/model/social/CenterUserDetailRO'
import ErrorCode from '@/socialuni/const/ErrorCode'
import UniUtil from '@/socialuni/utils/UniUtil'
import PageUtil from '@/socialuni/utils/PageUtil'
import AppUtilAPI from '@/socialuni/api/AppUtilAPI'
import DevAccountRO from '@/socialuni/model/dev/DevAccountRO'
import { socialOAuthModule, socialOAuthStore, socialSystemStore, socialUserStore } from '@/socialuni/store'
import JsonUtil from '@/socialuni/utils/JsonUtil'
import ResultRO from '@/socialuni/model/social/ResultRO'
import SocialLoginRO from '@/socialuni/model/social/SocialLoginRO'
import UniUserInfoRO from '@/socialuni/model/UniUserInfoRO'
import LoginProvider from '@/socialuni/const/LoginProvider'
import ToastUtil from '@/socialuni/utils/ToastUtil'
import AlertUtil from '@/socialuni/utils/AlertUtil'
import OAuthAPI from '@/api/OAuthAPI'
import QIcon from '@/qing-ui/components/QIcon/QIcon.vue'

@Component({
  components: {
    QIcon,
    UserPrivacyAgreement,
    LoginFooterAppInfo
  }
})
export default class OAuth extends Vue {
  @socialUserStore.State('user') user: CenterUserDetailRO
  @socialUserStore.Getter('hasPhoneNum') hasPhoneNum: boolean

  @socialSystemStore.State('isMp') isMp: boolean
  @socialSystemStore.State(SystemStoreProp.isMpWx) isMpWx: boolean
  @socialSystemStore.State(SystemStoreProp.isMpQQ) isMpQQ: boolean

  //三方授权相关
  @socialOAuthStore.State('threeUserId') threeUserId: string
  @socialOAuthStore.State('threeAuthType') threeAuthType: string
  @socialOAuthStore.State('threeAppId') threeAppId: string
  @socialOAuthStore.State('threeDevAccount') threeDevAccount: DevAccountRO
  //getter
  @socialOAuthStore.Getter('isAuthUser') isAuthUser: boolean
  @socialOAuthStore.Getter('isAuthPhone') isAuthPhone: boolean


  phoneNum = ''
  authCode = ''
  countDown = 0
  openTypeBtnEnable = true

  showPhoneView = false
  goBackCountDown = 0

  //是否授权成功
  authSuccess = false

  onLoad () {
    JsonUtil.log(this.user)
    console.log(this.user)
  }

  toLoginPage () {
    PageUtil.toMinePage()
  }

  toBindPhone () {
    PageUtil.toPhonePage()
  }


  goBackPage () {
    if (socialOAuthModule.isThreeAuth) {
      const result: ResultRO<any> = new ResultRO<any>()
      result.errorCode = ErrorCode.business
      result.errorMsg = '用户未授权'
      result.success = false
      UniUtil.showLoading('不授权，返回中...')
      uni.navigateBackMiniProgram({ extraData: result })
    }
  }

  async oAuthUserInfoAndPhoneNum () {
    console.log(123)
    //有用户信息，并且伪授权用户信息
    if (this.hasPhoneNum) {
      try {
        //处理用户授权
        await AlertUtil.confirm('是否确认授权您的手机号码')
        UniUtil.showLoading('授权中')
        const threeAuthResultVO = await OAuthAPI.oAuthUserInfoAndPhoneNumAPI(socialOAuthModule.threeAppId, LoginProvider.wx)
        UniUtil.hideLoading()
        this.authSuccessGoBackThreeMp(threeAuthResultVO)
      } catch (e) {
        ToastUtil.toast('您取消了授权')
      }
    } else {
      //提示未绑定手机号，是否前往绑定
      AppUtilAPI.sendErrorLogAPI(null, '错误的用户授权类型')
    }
  }

  //授权成功返回第三方小程序
  authSuccessGoBackThreeMp (extraData: ResultRO<SocialLoginRO<UniUserInfoRO>>) {
    this.goBackCountDown = 1
    let hintText = ''
    if (extraData.success) {
      this.authSuccess = true
      hintText = '授权成功，返回中...'
    } else {
      hintText = '不授权，返回中...'
    }
    UniUtil.showLoading(hintText)
    const timer = setInterval(() => {
      if (this.goBackCountDown) {
        this.goBackCountDown--
      } else {
        UniUtil.hideLoading()
        clearInterval(timer)
        uni.navigateBackMiniProgram({
          extraData: extraData
        })
      }
    }, 1000)
  }
}
</script>
