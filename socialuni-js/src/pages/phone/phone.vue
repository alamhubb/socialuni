<template>
  <!--  全部字体加大一号，白色背景，垂直居中，子元素宽度占满-->
  <!--  过来的授权及时手机号授权，也会提示先登录，可以自己选择手机号登录-->
  <div class="h100p bg-white px col-between-center">
    <!--    上padding 7vh，兼容各平台，底部10px，左右20px-->
    <div class="w100p pt-1p col-center flex-1">
      <div class="flex-none col-row-center h70">
        <div class="text-xxl font-bold">欢迎使用社交联盟授权登录</div>
      </div>

      <view class="mt-xs h145px">
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
        <u-icon class="ml-xs text-gray" name="arrow-right"></u-icon>
      </div>

      <!--        隐私提示-->
      <user-privacy-agreement></user-privacy-agreement>

      <!--    返回和登录方式切换-->
      <!--      这个div是为了处理居中问题-->
      <div>
        <view class="h150 col-row-center">
          <view class="col-row-center w300">
            <!--            微信登录界面，非手机号登录界面-->
            <template>
              <!--              没登录提示登录，如果为三方授权且为授权用户信息，追加 并授权三个字-->
              <!-- 只要不为QQ小程序平台都可以使用微信登录-->
              <template v-if="!user">
                <button v-if="showPhoneView" :disabled="loginButtonDisabled" @click="phoneLogin"
                        class="h40 cu-btn lg bg-gradual-phone  row-all-center bd-none bg-active round mt w100p"
                >
                  <u-icon custom-prefix="mdi" color="white" name="cellphone-android" size="42" class="mr-xs"></u-icon>
                  手机号登录
                </button>
                <button v-else-if="isMpQQ" :disabled="!openTypeBtnEnable"
                        open-type="getUserInfo"
                        class="bg-gradual-qq h40 cu-btn lg row-all-center bd-none bg-active round mt w100p"
                        @getuserinfo="providerLogin">
                  <u-icon color="white" name="weixin-fill" size="42"
                          class="mr-xs"></u-icon>
                  QQ登录
                </button>
                <!--                app和h5也都可以用微信登录-->
                <button v-else :disabled="!openTypeBtnEnable"
                        class="bg-gradual-wx h40 cu-btn lg row-all-center bd-none bg-active round mt w100p"
                        @click="providerLogin">
                  <u-icon color="white" name="weixin-fill" size="42"
                          class="mr-xs"></u-icon>
                  微信登录
                </button>
              </template>
              <!--              有用户-->
              <template v-else>
                <button v-if="showPhoneView" :disabled="loginButtonDisabled" @click="bindPhoneNum"
                        class="h40 cu-btn lg bg-gradual-phone  row-all-center bd-none bg-active round mt w100p"
                >
                  <u-icon custom-prefix="mdi" color="white" name="cellphone-android" size="42" class="mr-xs"></u-icon>
                  绑定手机号
                </button>

                <button v-else-if="isMpWx" :disabled="!openTypeBtnEnable"
                        open-type="getPhoneNumber"
                        class="h40 cu-btn lg bg-gradual-wx row-all-center bd-none bg-active round mt w100p"
                        @getphonenumber="bindWxPhoneNum">
                  <u-icon color="white" name="weixin-fill" size="42"
                          class="mr-xs"></u-icon>
                  绑定微信手机号
                </button>
              </template>
            </template>
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

        <view @click="switchShowPhoneNum" class="row-end-center">
          <view class="text-gray">
            <template v-if="!user">
              {{ showPhoneView ? '其他方式登录' : '手机号登录' }}
            </template>
            <template v-else-if="user && isMpWx">
              {{ showPhoneView ? '其他方式绑定' : '验证码方式绑定' }}
            </template>
          </view>
          <!--              验证码登录、或者没用户、或者没手机号且不为授权用户、-->
          <u-icon class="ml-xs text-gray"
                  name="arrow-right"></u-icon>
        </view>
      </view>
    </div>

    <!--      底部客服信息-->
    <login-footer-app-info class="w100p mb-sm"></login-footer-app-info>
  </div>
</template>


<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import UserPrivacyAgreement from '@/socialuni/components/SocialLogin/UserPrivacyAgreement.vue'
import LoginFooterAppInfo from '@/socialuni/components/SocialLogin/LoginFooterAppInfo.vue'
import { socialSystemModule, socialSystemStore, socialUserStore } from '@/socialuni/store'
import SystemStoreProp from '@/socialuni/store/SystemStoreProp'
import PhoneNumFormData from '@/socialuni/model/phone/PhoneNumFormData'
import LoginService from '@/socialuni/service/LoginService'
import PhoneService from '@/socialuni/service/PhoneService'
import Alert from '@/socialuni/utils/Alert'
import RouterUtil from '@/socialuni/utils/RouterUtil'
import PageUtil from '@/socialuni/utils/PageUtil'
import CenterUserDetailRO from '@/socialuni/model/social/CenterUserDetailRO'

@Component({
  components: {
    UserPrivacyAgreement,
    LoginFooterAppInfo
  }
})
export default class QcLogin extends Vue {
  @socialUserStore.State('user') user: CenterUserDetailRO
  @socialUserStore.Getter('hasPhoneNum') hasPhoneNum: boolean

  @socialSystemStore.State('isMp') isMp: boolean
  @socialSystemStore.State(SystemStoreProp.isMpWx) isMpWx: boolean
  @socialSystemStore.State(SystemStoreProp.isMpQQ) isMpQQ: boolean


  //首先需要携带threeAppId和密钥去后台查询，三方信息，如果不对提示错误。然后也无法向后台授权。
  //如果三方信息错误，上面是显示，申请授权方信息错误，不予授权

  phoneFormData = new PhoneNumFormData()

  openTypeBtnEnable = true

  showPhoneView = false

  //同意协议
  // contractChecked = true

  created () {
    this.initData()
  }

  onLoad () {
    this.initData()
  }

  initData () {
    this.phoneFormData = new PhoneNumFormData()
    //不为微信则默认为验证码方式绑定
    if (this.user && !this.isMpWx) {
      this.showPhoneView = true
    }
  }

  get loginButtonDisabled () {
    return this.phoneFormData && (PhoneNumFormData.phoneNumberError(this.phoneFormData.phoneNum) || PhoneNumFormData.authCodeError(this.phoneFormData.authCode) || !this.openTypeBtnEnable)
  }

  goBackPage () {
    if (oAuthModule.isThreeAuth) {
      PageUtil.toOAuthPage()
    } else {
      RouterUtil.goBack()
    }
  }

  switchShowPhoneNum () {
    if (this.showPhoneView) {
      this.showPhoneView = false
    } else {
      this.showPhoneView = true
    }
  }

  //平台登录
  //登录，授权，绑定手机号各大平台登录结果，后者授权手机号结果
  async providerLogin (result) {
    if (this.openTypeBtnEnable) {
      try {
        this.openTypeBtnEnable = false
        //一行代码就可以获取登录所需要的信息, 还可以配合后台使用，一键登录，记住用户
        await LoginService.providerLogin(socialSystemModule.mpPlatform, result)
        this.loginAfterHint('登录成功')
      } finally {
        this.goToOAuthPage()
        this.openTypeBtnEnable = true
      }
    }
  }

  async phoneLogin () {
    if (this.openTypeBtnEnable) {
      try {
        this.openTypeBtnEnable = false
        await LoginService.phoneLogin(this.phoneFormData.phoneNum, this.phoneFormData.authCode)
        this.loginAfterHint('登录成功')
      } finally {
        this.goToOAuthPage()
        this.openTypeBtnEnable = true
      }
    }
  }

  goToOAuthPage () {
    if (oAuthModule.isThreeAuth) {
      if (this.hasPhoneNum) {
        PageUtil.toOAuthPage()
      }
    }
  }

  //手机号登录和手机号绑定
  async bindPhoneNum () {
    if (this.openTypeBtnEnable) {
      try {
        this.openTypeBtnEnable = false
        await PhoneService.bindPhoneNum(this.phoneFormData.phoneNum, this.phoneFormData.authCode)
        this.loginAfterHint('绑定成功')
      } finally {
        this.goToOAuthPage()
        this.openTypeBtnEnable = true
      }
    }
  }

  // 微信点击按钮，获取手机号用来绑定
  async bindWxPhoneNum (obj: any) {
    if (this.openTypeBtnEnable) {
      try {
        this.openTypeBtnEnable = false
        await PhoneService.bindWxPhoneNum(obj)
        this.loginAfterHint('绑定成功')
      } finally {
        this.goToOAuthPage()
        this.openTypeBtnEnable = true
      }
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
      //有手机号才直接返回，没手机号继续提示绑定手机号
      if (this.hasPhoneNum) {
        this.goBackPage()
      }
    })
  }


  /*
    默认选中
    if (!this.contractChecked) {
      return Alert.hint('请仔细阅读用户协议、隐私政策等内容后勾选同意')
    }*/
}
</script>
