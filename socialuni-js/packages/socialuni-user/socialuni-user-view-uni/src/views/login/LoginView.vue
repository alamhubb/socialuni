<template>
  <!--  全部字体加大一号，白色背景，垂直居中，子元素宽度占满-->
  <!--  过来的授权及时手机号授权，也会提示先登录，可以自己选择手机号登录-->
  <div class="h100p bg-white px col-between-center">
    <!--    上padding 7vh，兼容各平台，底部10px，左右20px-->
    <div class="h100p w100p pt-1p col-center flex-1">
      <div class="flex-none col-row-center h70">
        <div class="text-xxl font-bold">欢迎登录</div>
        <!--        <div class="text-xxl font-bold">欢迎使用社交联盟授权登录</div>-->
        <div v-if="showPhoneView && showProviderLogin" class="text-md color-warn mt-xs">
          建议使用{{
            isMpQQ ? 'QQ' : '微信'
          }}一键登录，无需等待
        </div>
      </div>

      <div class="mt-xs h165">
        <phone-login-form :view-service="viewService" ref="loginForm" v-if="showPhoneLogin"
                          :show="showPhoneLogin"></phone-login-form>

        <div class="h120 row-center" v-else>
          <!--          头部-->

          <!--          只有不为三方授权才显示logo-->
          <!--        登录界面，展示logo-->
          <image class="radius flex-none h100p"
                 mode="aspectFit"
                 src="/static/img/logo.jpg"
          />
        </div>
      </div>

      <!--      绑定手机号可发布动态提示-->
      <div class="row-center">
        <!--            如果为授权手机号，则提示-->
        <div class="u-border-bottom text-gray">
          绑定手机号后可发表动态
          <!--          ，详情-->
        </div>
        <!--        <q-icon class="ml-xs text-gray" icon="arrow-right"></q-icon>-->
      </div>

      <!--        隐私提示-->
      <user-privacy-agreement :view-service="viewService"></user-privacy-agreement>

      <!--    返回和登录方式切换-->
      <!--      这个div是为了处理居中问题-->
      <div>
        <div class="h150 col-row-center">
          <div class="col-row-center w300">
            <!--            微信登录界面，非手机号登录界面-->
            <!--              没登录提示登录，如果为三方授权且为授权用户信息，追加 并授权三个字-->
            <!-- 只要不为QQ小程序平台都可以使用微信登录-->
            <template v-if="!user">
              <q-button v-if="showPhoneLogin"
                        :disabled="viewService.bindBtnDisabled" @click="viewService.handleLogin()"
                        add-class="bg-gradual-phone" md class="w90p mt"
                        :class="loginButtonDisabled?'':'bg-click'"
              >
                <q-icon color="white" icon="mdi-cellphone-android" size="21" class="mr-xs"></q-icon>
                {{ viewService.bindBtnDisabled ? '登陆中' : viewService.loginBtnText }}
              </q-button>
              <q-button v-else-if="isMpQQ" :disabled="allButtonDisabled"
                        add-class="bg-gradual-qq" md class="w90p mt"
                        open-type="getUserInfo"
                        @getuserinfo="providerLogin">
                <q-icon color="white" icon="qq-fill" size="19"
                        class="mr-xs"></q-icon>
                QQ登录
              </q-button>
              <!--                app和h5也都可以用微信登录-->

              <q-button v-else :disabled="allButtonDisabled"
                        add-class="bg-gradual-wx" md class="w90p mt"
                        @click="providerLogin">
                <!--                                <q-icon color="white" icon="weixin-fill" size="21"
                                                        class="mr-xs"></q-icon>-->
                登录
              </q-button>
            </template>
            <!--              有用户-->
            <template v-else>
              <q-button v-if="showPhoneView" :disabled="loginButtonDisabled" @click="viewService.handleLogin()"
                        add-class="bg-gradual-phone"
              >
                <q-icon prefix="mdi" color="white" icon="cellphone-android" size="21"
                        class="mr-xs"></q-icon>
                绑定手机号
              </q-button>
              <!--                <button v-else-if="isMpWx" :disabled="!openTypeBtnEnable"
                                      open-type="getPhoneNumber"
                                      class="h40 cu-btn lg bg-gradual-wx row-all-center bd-none bg-click round mt w100p"
                                      @getphonenumber="bindWxPhoneNum">
                                <q-icon color="white" icon="weixin-fill" size="21"
                                        class="mr-xs"></q-icon>
                                绑定微信手机号
                              </button>-->
            </template>
          </div>
        </div>
      </div>

      <div class="row-between-center w100p">
        <div class="row-col-center" @click="goBackPage">
          <q-icon class="mr-xs text-gray" icon="arrow-left"></q-icon>
          <div class="text-gray u-border-bottom">
            {{ user ? '不绑定返回' : '不登录返回' }}
          </div>
        </div>

        <div v-if="showProviderLogin" @click="switchShowPhoneNum" class="row-end-center">
          <div class="text-gray">
            {{ showPhoneView ? '其他方式登录' : '手机号登录' }}
          </div>
          <!--              验证码登录、或者没用户、或者没手机号且不为授权用户、-->
          <q-icon class="ml-xs text-gray"
                  icon="arrow-right"></q-icon>
        </div>
      </div>
    </div>

    <!--      底部客服信息-->
    <!--    <login-footer-app-info class="w100p mb-sm"></login-footer-app-info>-->
  </div>
</template>


<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import PhoneNumFormData from "./PhoneNumFormData";
import UserPrivacyAgreement from "./UserPrivacyAgreement.vue";
import PhoneLoginForm from "./PhoneLoginForm.vue";
import LoginFooterAppInfo from "./LoginFooterAppInfo.vue";
import QButton from "qing-ui-uni/src/components/QButton/QButton.vue";
import QIcon from "qing-ui/src/components/QIcon.vue";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import LoginService from "socialuni-user-sdk/src/logic/LoginService";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import UserPageUtil from "socialuni-user-sdk/src/util/UserPageUtil";
import PhoneService from "socialuni-user-sdk/src/logic/PhoneService";
import SocialuniUserPrivacyAgreeService from "socialuni-user-sdk/src/logic/SocialuniUserPrivacyAgreeService";
import SocialuniLoginViewService from "socialuni-user-sdk/src/logic/SocialuniLoginViewService";
import {getCurrentInstance} from "vue";
import {onHide, onLoad, onShow} from "@dcloudio/uni-app";
import {socialuniAppUserModule} from "socialuni-user-sdk/src/store/SocialuniAppUserModule";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import PasswordUtil from "socialuni-user-sdk/src/util/PasswordUtil";
import SocialuniLoginService from "socialuni-user-sdk/src/logic/SocialuniLoginService";

@toNative
@Component({
  components: {
    QIcon,
    QButton,
    UserPrivacyAgreement,
    PhoneLoginForm,
    LoginFooterAppInfo
  }
})
export default class LoginView extends Vue {
  $refs: {
    loginForm: PhoneLoginForm
  }

  viewService = new SocialuniLoginViewService()

  get showProviderLogin() {
    return !this.user && socialuniSystemModule.isMp
  }

  get showPhoneLogin() {
    return this.showPhoneView || !this.showProviderLogin
  }

  get user() {
    return socialuniUserModule.mineUser
  }

  get hasPhoneNum() {
    return socialuniAppUserModule.hasPhoneNum
  }

  get isMp() {
    return socialuniSystemModule.isMp
  }

  get isMpQQ() {
    return socialuniSystemModule.isMpQQ
  }

  //首先需要携带threeAppId和密钥去后台查询，三方信息，如果不对提示错误。然后也无法向后台授权。
  //如果三方信息错误，上面是显示，申请授权方信息错误，不予授权

  openTypeBtnEnable = true

  get allButtonDisabled() {
    return (!this.user && !this.viewService.contractChecked) || !this.openTypeBtnEnable
  }

  showPhoneView = false

  //同意协议
  // contractChecked = true

  created() {
    this.initData()
    onLoad((params) => {
      console.log(this)
      console.log(getCurrentInstance())
      this.viewService.initService(getCurrentInstance(), params)
    })
  }

  initData() {
    //不为微信则默认为验证码方式绑定
    if (this.user) {
      this.showPhoneView = true
    }
  }

  get loginButtonDisabled() {
    return this.viewService.loginData.loginDataHasError || this.allButtonDisabled
  }

  goBackPage() {
    /* if (socialOAuthModule.isThreeAuth) {
       PageUtil.toOAuthPage()
     } else {
       RouterUtil.goBackOrHome()
     }*/
    QingAppUtil.RouterUtil.goBackOrHome()
  }

  switchShowPhoneNum() {
    if (this.showPhoneView) {
      this.showPhoneView = false
    } else {
      this.showPhoneView = true
    }
  }

  //平台登录
  //登录，授权，绑定手机号各大平台登录结果，后者授权手机号结果
  async providerLogin(result) {
    if (!this.allButtonDisabled) {
      try {
        this.openTypeBtnEnable = false
        //一行代码就可以获取登录所需要的信息, 还可以配合后台使用，一键登录，记住用户
        await LoginService.providerLogin(socialuniSystemModule.mpPlatform, result)
        this.loginAfterHint('登录成功')
      } finally {
        this.goToOAuthPage()
        this.openTypeBtnEnable = true
      }
    }
  }

  async phoneLogin() {
    await this.viewService.handleLogin()
  }

  resetAuthCodeCountDown() {
    // this.$refs.loginForm?.resetAuthCodeCountDown()
  }

  goToOAuthPage() {
    /*if (socialOAuthModule.isThreeAuth) {
      if (this.hasPhoneNum) {
        PageUtil.toOAuthPage()
      } else {
        PageUtil.toPhonePage()
      }
    }*/
  }

  //手机号登录和手机号绑定
  async bindPhoneNum() {
    if (this.openTypeBtnEnable) {
      try {
        this.openTypeBtnEnable = false
        await PhoneService.bindPhoneNum(this.viewService.loginData.phoneNum, this.viewService.loginData.authCode)
        this.loginAfterHint('绑定成功')
      } catch (e) {
        this.goToOAuthPage()
      } finally {
        this.resetAuthCodeCountDown()
        this.openTypeBtnEnable = true
      }
    }
  }

  // 微信点击按钮，获取手机号用来绑定
  async bindWxPhoneNum(obj: any) {
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

  loginAfterHint(msg: string) {
    if (!this.user.phoneNum) {
      msg += '，绑定手机号后才可发布内容'
    }
    //qq小程序下ios系统存在输入框冲突问题，使用了一个输入框，另一个就无法出现
    if (socialuniSystemModule.isIosOrMpQQ) {
      msg += '，如遇无法弹出输入框，请重启应用'
    }
    QingAppUtil.AlertUtil.hint(msg).finally(() => {
      //有手机号才直接返回，没手机号继续提示绑定手机号
      if (this.hasPhoneNum) {
        UserPageUtil.goBackOrMine()
      }
      /*if (socialOAuthModule.isThreeAuth) {
        if (this.hasPhoneNum) {
          PageUtil.toOAuthPage()
        } else {
          PageUtil.toPhonePage()
        }
      } else {

      }*/
    })
  }


  /*
    默认选中
    if (!this.contractChecked) {
      return Alert.hint('请仔细阅读用户协议、隐私政策等内容后勾选同意')
    }*/
}
</script>
