<template>
  <!--  全部字体加大一号，白色背景，垂直居中，子元素宽度占满-->
  <!--  过来的授权及时手机号授权，也会提示先登录，可以自己选择手机号登录-->
  <div class="h100p bg-white col-all-center">
    <div v-if="goBackCountDown" class="font-bold text-lg">
      {{ authSuccess ? '授权成功' : '不授权' }}，{{ goBackCountDown }} 秒后返回...
    </div>

    <!--    上padding 7vh，兼容各平台，底部10px，左右20px-->
    <div v-else class="pt-6vh pb-sm px w100p">
      <div class="flex-none col-row-center h80px">
        <div class="text-xxl font-bold">欢迎使用社交联盟授权登录</div>
      </div>

      <view class="mt">
        <view class="h150px flex-col">
          <third-apply-auth-info v-if="isThreeAuth"></third-apply-auth-info>
          <!--          头部-->

        </view>
      </view>

      <!--        隐私提示-->
      <div class="pt-sm h50px">
      </div>


      <!--    返回和登录方式切换-->
      <!--      这个div是为了处理居中问题-->
      <div>
        <view class="h150px col-row-center">
          <view class="col-row-center w300px">
            <!--            微信登录界面，非手机号登录界面-->
            <!--              没登录提示登录，如果为三方授权且为授权用户信息，追加 并授权三个字-->
            <!-- 只要不为QQ小程序平台都可以使用微信登录-->
            <button v-if="isAuthUser" :disabled="!openTypeBtnEnable"
                    class="h40px cu-btn lg bg-gradual-qq row-all-center bd-none bg-active round mt w100p"
                    @click="authUserInfo">
              授权用户信息
            </button>
            <button v-else :disabled="!openTypeBtnEnable"
                    class="h40px cu-btn lg bg-gradual-qq row-all-center bd-none bg-active round mt w100p"
                    @click="authPhoneNum">
              <template>
                授权手机号
              </template>
            </button>
          </view>

          <view class="row-between-center w300px h40px mt-lg">
            <template>
              <view class="row-col-center" @click="goBackPage">
                <u-icon class="mr-xs text-gray" name="arrow-left"></u-icon>
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
import CenterUserDetailRO from 'socialuni/model/social/CenterUserDetailRO'
import UniUtil from 'socialuni/utils/UniUtil'
import Alert from 'socialuni/utils/Alert'
import OpenDataAPI from 'socialuni/api/OpenDataAPI'
import ResultVO from 'socialuni/model/ResultVO'
import ErrorCode from 'socialuni/const/ErrorCode'
import PageUtil from 'socialuni/utils/PageUtil'
import AppUtilAPI from 'socialuni/api/AppUtilAPI'
import DevUserVO from 'socialuni/model/dev/DevUserVO'
import SystemStoreProp from 'socialuni/store/SystemStoreProp'
import LoginFooterAppInfo from './LoginFooterAppInfo.vue'
import ThirdApplyAuthInfo from './ThirdApplyAuthInfo.vue'
import UserPrivacyAgreement from './UserPrivacyAgreement.vue'
import { socialAppStore, socialSystemStore, socialUserStore } from 'socialuni/store'

@Component({
  components: {
    UserPrivacyAgreement,
    ThirdApplyAuthInfo,
    LoginFooterAppInfo
  }
})
export default class Login extends Vue {
  @socialUserStore.State('user') user: CenterUserDetailRO
  @socialUserStore.Getter('hasPhoneNum') hasPhoneNum: boolean

  @socialSystemStore.State('isMp') isMp: boolean
  @socialSystemStore.State(SystemStoreProp.isMpWx) isMpWx: boolean
  @socialSystemStore.State(SystemStoreProp.isMpQQ) isMpQQ: boolean

  //三方授权相关
  @socialAppStore.State('threeUserId') threeUserId: string
  @socialAppStore.State('threeAuthType') threeAuthType: string
  @socialAppStore.State('threeAppId') threeAppId: string
  @socialAppStore.State('threeDevUser') threeDevUser: DevUserVO
  //getter
  @socialAppStore.Getter('isThreeAuth') isThreeAuth: boolean
  @socialAppStore.Getter('isAuthUser') isAuthUser: boolean
  @socialAppStore.Getter('isAuthPhone') isAuthPhone: boolean


  phoneNum = ''
  authCode = ''
  countDown = 0
  openTypeBtnEnable = true

  showPhoneView = false
  goBackCountDown = 0

  //是否授权成功
  authSuccess = false

  goBackPage () {
    if (this.isThreeAuth) {
      const result: ResultVO<any> = new ResultVO<any>()
      result.errorCode = ErrorCode.business
      result.errorMsg = '用户未授权'
      result.success = false
      UniUtil.showLoading('不授权，返回中...')
      uni.navigateBackMiniProgram({ extraData: result })
    } else {
      if (!this.user) {
        PageUtil.goHome()
      } else {
        PageUtil.toMinePage()
      }
    }
  }

  async authUserInfo () {
    //有用户信息，并且伪授权用户信息
    if (this.user) {
      //处理用户授权
      await Alert.confirm('是否确认授权您的用户信息')
      UniUtil.showLoading('授权中')
      const threeAuthResultVO = await OpenDataAPI.authUserInfoAPI()
      UniUtil.hideLoading()
      threeAuthResultVO.data.appUserId = this.threeUserId
      threeAuthResultVO.data.authType = this.threeAuthType
      this.authSuccessGoBackThreeMp(threeAuthResultVO)
      //为授权手机号，并且有手机号
    } else {
      //提示未登录是否前往登录
      AppUtilAPI.sendErrorLogAPI(null, '错误的用户授权类型')
    }
  }

  async authPhoneNum () {
    //有用户信息，并且伪授权用户信息
    if (this.hasPhoneNum) {
      //处理用户授权
      await Alert.confirm('是否确认授权您的手机号码')
      UniUtil.showLoading('授权中')
      const threeAuthResultVO = await OpenDataAPI.authUserPhoneNumAPI()
      UniUtil.hideLoading()
      threeAuthResultVO.data.appUserId = this.threeUserId
      threeAuthResultVO.data.authType = this.threeAuthType
      this.authSuccessGoBackThreeMp(threeAuthResultVO)
    } else {
      //提示未绑定手机号，是否前往绑定
      AppUtilAPI.sendErrorLogAPI(null, '错误的用户授权类型')
    }
  }

  //授权成功返回第三方小程序
  authSuccessGoBackThreeMp (extraData: ResultVO<any>) {
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
