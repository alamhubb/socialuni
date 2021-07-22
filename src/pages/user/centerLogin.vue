<template>
  <!--  全部字体加大一号，白色背景，垂直居中，子元素宽度占满-->
  <!--  过来的授权及时手机号授权，也会提示先登录，可以自己选择手机号登录-->
  <div class="h100p bg-white">

    <div v-if="goBackCountDown" class="font-bold text-lg">
      {{ authSuccess ? '授权成功' : '不授权' }}，

      {{ goBackCountDown }} 秒后返回...
    </div>

    <!--    上padding 7vh，兼容各平台，底部10px，左右20px-->
    <div v-else class="pt-6vh pb-sm px w100p">

      <div class="flex-none col-row-center h80px">
        <div class="text-xxl font-bold">欢迎使用社交联盟授权登录</div>
        <div v-if="showPhoneView" class="text-md u-type-warning mt-sm">建议使用微信 或 QQ一键登录</div>
      </div>

      <view class="mt">
        <!--    如果是绑定手机号，或者手机号登录，就展示手机号。-->
        <!--        手机号界面-->
        <!--        35*4 = 140 +30+80 = 250高度-->
        <!--        如果有用户且不为微信，则默认展示验证码绑定-->
        <view v-if="showPhoneView" class="h150px">
          <!--          如果没登录-->
          <!--          <view v-if="!user" class="u-type-warning-light-bg row-col-center h80px px-sm">
                      <u-icon name="volume-fill" class="u-type-warning" size="50"></u-icon>
                      <view class="ml-smm font-bold">
                        欢迎使用社交联盟授权，建议使用微信或QQ登录
                      </view>
                    </view>-->
          <!--          <view class="h80px font-lg font-bold mb">
                      欢迎使用社交联盟授权<span v-if="!user">，建议使用微信或QQ登录</span>
                    </view>-->


        </view>

        <!--        登录界面，展示logo-->
        <!--      上半部，无用户，无授权，显示logo+提示绑定手机号-->
        <!--      上半部，无用户，授权，显示三方应用申请信息+提示绑定手机号-->
        <!--      上半部，有用户，授权，无手机号，显示三方应用申请信息+提示绑定手机号-->
        <!--      上半部，有用户，授权-->
        <view v-else class="h150px flex-col">
          <third-apply-auth-info v-if="isThreeAuth" ></third-apply-auth-info>
          <!--          头部-->

          <!--          只有不为三方授权才显示logo-->
          <div v-else class="flex-1 row-center pb-md">
            <!--        登录界面，展示logo-->
            <image class="radius flex-none h100p"
                   mode="aspectFit"
                   src="/static/img/logo.jpg"
            />
          </div>
        </view>
      </view>

      <!--      绑定手机号可发布动态提示-->
      <div v-if="!hasPhoneNum && !isAuthUser" class="row-center pt-sm pb-sm">
        <!--            如果为授权手机号，则提示-->
        <view class="u-border-bottom text-gray">
          绑定手机号后可发表动态，详情
        </view>
        <u-icon class="ml-xs text-gray" name="arrow-right"></u-icon>
      </div>

      <!--        隐私提示-->

      <!--    底部按钮部分-->
      <!--      这个div是为了处理居中问题-->
      <div>
        <view class="h150px col-row-center">
          <!--          已登录用户绑定手机号，
                    使用手机号登录，
                    直接登录，
                    授权登录
                    授权绑定手机号，
                    跳转过来用户没绑定手机号，如果未登录，提示登录，如果已登录，提示授权，未绑定手机号提示绑定手机号，已绑定提示授权。
                    提供返回按钮，无论怎样，如果是第三方跳转过来的始终显示一个返回应用-->

          <!--          未登录，使用手机号登录-->
          <!--            如果有手机号，则直接授权，下面是不授权返回、-->
          <!--            没手机号，则是微信的话显示 绑定微信手机号，下面是手机号授权。就不是手机号登录，就是手机号绑定-->

          <!--            如果为第三方授权，如果为授权手机号-->
          <view class="col-row-center w300px h120px">
            <template v-if="!user">
              <button v-if="showPhoneView" :disabled="loginButtonDisabled" @click="loginByPhoneNumAndBindPhoneNum"
                      class="h40px cu-btn lg bg-gradual-phone  row-all-center bd-none bg-active round mt w100p"
              >
                <u-icon custom-prefix="mdi" color="white" name="cellphone-android" size="42" class="mr-xs"></u-icon>
                <template v-if="!user">
                  手机号登录{{ isAuthUser ? '并授权' : '' }}
                </template>
              </button>

              <button v-else-if="isMpWX" :disabled="!openTypeBtnEnable"
                      class="bg-gradual-wx h40px cu-btn lg row-all-center bd-none bg-active round mt w100p"
                      @click="providerLogin">
                <u-icon color="white" name="weixin-fill" size="42"
                        class="mr-xs"></u-icon>
                微信登录{{ isAuthUser ? '并授权' : '' }}
              </button>

              <button v-else-if="isMpQQ" :disabled="!openTypeBtnEnable"
                      open-type="getUserInfo"
                      class="bg-gradual-qq h40px cu-btn lg row-all-center bd-none bg-active round mt w100p"
                      @getuserinfo="providerLogin">
                <u-icon color="white" name="weixin-fill" size="42"
                        class="mr-xs"></u-icon>
                QQ登录{{ isAuthUser ? '并授权' : '' }}
              </button>
            </template>
            <template v-else>
              <button :disabled="loginButtonDisabled" @click="loginByPhoneNumAndBindPhoneNum"
                      class="h40px cu-btn lg bg-gradual-phone  row-all-center bd-none bg-active round mt w100p"
              >
                <u-icon custom-prefix="mdi" color="white" name="cellphone-android" size="42" class="mr-xs"></u-icon>
                <!--            如果是输入手机号页面，已登录，如果不为三方授权-->
                绑定手机号
              </button>
            </template>
          </view>


          <view class="row-between-center w300px h40px mt-lg">
            <!--              <view v-if="isThreeAuth" class="row-col-center">-->
            <view class="row-col-center" @click="goBackPage">
              <u-icon class="mr-xs text-gray" name="arrow-left"></u-icon>
              <view class="text-gray u-border-bottom">


                <!--            手机号登录界面-->
                <template v-if="showPhoneView">
                  <!--            如果是输入手机号页面，未登录，提示手机号登录-->
                  <template v-if="!user">
                    {{ isAuthUser ? '不授权' : '不登录' }}返回
                    <!--                    {{ isAuthPhone ? '集美' : '' }}-->
                  </template>
                  <!--            如果是输入手机号页面，已登录，如果不为三方授权，提示绑定手机号-->
                  <template v-else>
                    {{ isAuthPhone ? '不授权' : '不绑定' }}返回
                    <!--                    {{ isAuthPhone ? '集美' : '' }}-->
                  </template>
                  <!--              其他为错误的逻辑-->
                </template>
                <!--            微信登录界面，非手机号登录界面-->
                <template v-else>
                  <!--              没登录提示登录，如果为三方授权且为授权用户信息，追加 并授权三个字-->
                  <template v-if="!user">
                    <!-- 只要不为QQ小程序平台都可以使用微信登录-->
                    {{ isAuthUser ? '不授权' : '不登录' }}返回
                    <!--                    {{ isAuthPhone ? '集美' : '' }}-->
                  </template>
                  <!--            如果已登录有用户-->
                  <template v-else>
                    <!--              已登录，三方授权，授权用户信息-->
                    <!--                授权那用户信息无需关心手机号绑定状态-->
                    <template v-if="isAuthUser">
                      不授权返回
                      <!--                      集美-->
                    </template>
                    <!--              已登录，三方授权，授权手机号信息，绑定了手机号，提示授权手机号-->
                    <template v-else-if="isAuthPhone&&hasPhoneNum">
                      不授权返回
                      <!--                      集美-->
                    </template>
                    <!--              已登录，不为三方授权，进来只能是绑定微信手机号-->
                    <template v-else>
                      {{ isAuthPhone ? '不授权' : '不绑定' }}返回
                      <!--                      {{ isAuthPhone ? '集美' : '' }}-->
                    </template>
                    <!--                其他为错误的逻辑-->
                  </template>
                </template>
              </view>
            </view>
            <view @click="switchShowPhoneNum" class="row-end-center">
              <view class="text-gray">
                <!--            手机号登录界面-->
                <template v-if="(user&&!isMpWX)||showPhoneView">
                  <!--            如果是输入手机号页面，未登录，提示手机号登录-->
                  <template v-if="!user">
                    其他方式{{ isAuthPhone ? '授权' : '登录' }}
                  </template>
                  <!--            如果是输入手机号页面，已登录，如果不为三方授权，提示绑定手机号-->
                  <template v-else-if="user&&!hasPhoneNum&&isMpWX">
                    其他方式{{ isAuthPhone ? '授权' : '绑定' }}
                  </template>
                  <!--              其他为错误的逻辑-->
                </template>
                <!--            微信登录界面，非手机号登录界面-->
                <template v-else>
                  <!--              没登录提示登录，如果为三方授权且为授权用户信息，追加 并授权三个字-->
                  <template v-if="!user">
                    <!-- 只要不为QQ小程序平台都可以使用微信登录-->
                    手机号{{ isAuthUser ? '授权' : '登录' }}
                  </template>
                  <!--            如果已登录有用户-->
                  <template v-else-if="!hasPhoneNum&&!isAuthUser">
                    <!--              已登录，三方授权，授权用户信息-->
                    <!--                授权那用户信息无需关心手机号绑定状态-->
                    <!--              已登录，不为三方授权，进来只能是绑定微信手机号-->
                    验证码方式{{ isAuthPhone ? '授权' : '绑定' }}
                    <!--                其他为错误的逻辑-->
                  </template>
                </template>
              </view>
              <!--              验证码登录、或者没用户、或者没手机号且不为授权用户、-->
              <u-icon v-if="(!user||isMpWX) && (showPhoneView||!user||(!hasPhoneNum&&!isAuthUser))"
                      class="ml-xs text-gray"
                      name="arrow-right"></u-icon>
            </view>
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
import UserVO from '@/model/user/UserVO'
import { namespace } from 'vuex-class'
import UniUtil from '@/utils/UniUtil'
import CommonUtil from '@/utils/CommonUtil'
import UserAPI from '@/api/UserAPI'
import ConfigMap from '@/const/ConfigMap'
import ProviderType from '@/const/ProviderType'
import ProviderUserVO from '@/model/login/ProviderUserVO'
import { systemModule, userModule } from '@/store'
import Alert from '@/utils/Alert'
import OpenDataAPI from '@/api/OpenDataAPI'
import Constants from '@/const/Constant'
import Toast from '@/utils/Toast'
import LoginService from '@/service/LoginService'
import ResultVO from '@/model/ResultVO'
import ErrorCode from '@/const/ErrorCode'
import PageUtil from '@/utils/PageUtil'
import AppUtilAPI from '@/api/AppUtilAPI'
import UserService from '@/service/UserService'
import DevUserVO from '@/model/dev/DevUserVO'
import SocialUniAuthVO from '@/model/openData/SocialUniAuthVO'
import SocialConfig from '@/config/SocialConfig'
import SystemStoreProp from '@/store/SystemStoreProp'
import LoginFooterAppInfo from '@/pages/user/LoginFooterAppInfo.vue'
import ThirdApplyAuthInfo from '@/pages/user/ThirdApplyAuthInfo.vue'

const userStore = namespace('user')
const configStore = namespace('config')
const systemStore = namespace('system')
const appStore = namespace('app')
@Component({
  components: { ThirdApplyAuthInfo, LoginFooterAppInfo }
})
export default class Login extends Vue {
  @userStore.State('user') user: UserVO
  @userStore.Getter('hasPhoneNum') hasPhoneNum: boolean

  @configStore.Getter(ConfigMap.authCodeIntervalKey) authCodeInterval: number
  @configStore.Getter(ConfigMap.qqServiceKey) qqService: string
  @systemStore.State('isMp') isMp: boolean
  @systemStore.State(SystemStoreProp.isMpWx) isMpWX: boolean
  @systemStore.State(SystemStoreProp.isMpQQ) isMpQQ: boolean

  //三方授权相关
  @appStore.State('threeUserId') threeUserId: string
  @appStore.State('threeAuthType') threeAuthType: string
  @appStore.State('threeAppId') threeAppId: string
  @appStore.State('threeDevUser') threeDevUser: DevUserVO
  //getter
  @appStore.Getter('isThreeAuth') isThreeAuth: boolean
  @appStore.Getter('isAuthUser') isAuthUser: boolean
  @appStore.Getter('isAuthPhone') isAuthPhone: boolean

  authApp = !SocialConfig.authApp

  //首先需要携带threeAppId和密钥去后台查询，三方信息，如果不对提示错误。然后也无法向后台授权。
  //如果三方信息错误，上面是显示，申请授权方信息错误，不予授权

  phoneNum = ''
  authCode = ''
  countDown = 0
  openTypeBtnEnable = true

  showPhoneView = false
  goBackCountDown = 0

  //是否授权成功
  authSuccess = false


  //同意协议
  // contractChecked = true



  get loginButtonDisabled () {
    // !this.contractChecked ||
    return !this.phoneNumberRight || !this.authCodeRight || !this.openTypeBtnEnable
  }


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

  switchShowPhoneNum () {
    if (this.showPhoneView) {
      this.phoneNumInputBlur()
      this.authCodeInputBlur()
      this.showPhoneView = false
    } else {
      this.showPhoneView = true
      this.phoneNumInputFocus()
    }
  }



  //手机号登录和手机号绑定
  async loginByPhoneNumAndBindPhoneNum () {
    //再次校验
    if (!this.phoneNumberRight) {
      return Toast.toast('请输入正确的手机号')
    }
    if (!this.authCodeRight) {
      return Toast.toast('请输入正确的验证码')
    }
    /*
    默认选中
    if (!this.contractChecked) {
      return Alert.hint('请仔细阅读用户协议、隐私政策等内容后勾选同意')
    }*/
    if (this.openTypeBtnEnable) {
      this.openTypeBtnEnable = false
      if (this.user) {
        if (!this.user.phoneNum) {
          //手机号绑定
          const user = await UserAPI.bindPhoneNumAPI(this.phoneNum, this.authCode)
          userModule.setUser(user)
          let msg = '绑定成功'
          //qq小程序下ios系统存在输入框冲突问题，使用了一个输入框，另一个就无法出现
          if (systemModule.isIosAndMpQQ) {
            msg += '，如遇无法弹出输入框，请重启应用'
          }
          // 提示验证码发送成功
          Alert.hint(msg)
        } else {
          Alert.hint('您已绑定手机号')
        }
      } else {
        const loginData = new ProviderUserVO()
        loginData.phoneNum = this.phoneNum
        loginData.authCode = this.authCode
        loginData.provider = ProviderType.phone
        loginData.platform = systemModule.platform
        await LoginService.providerLogin(ProviderType.phone, loginData)
      }
      //只有为三方授权才调用
      if (this.isThreeAuth) {
        //如果点了按钮就会并授权，要不然也不会到这里
        await this.authUserOrPhoneNum()
      }
      this.goBackPage()
      this.openTypeBtnEnable = true
    }
  }


  //平台登录
  //登录，授权，绑定手机号各大平台登录结果，后者授权手机号结果
  async providerLogin (result) {
    if (systemModule.isMp) {
      if (systemModule.isMpQQ) {
        if (result.detail.errMsg !== Constants.loginSuccess) {
          return Toast.toast('您取消了登录')
        }
      }
      //一行代码就可以获取登录所需要的信息, 还可以配合后台使用，一键登录，记住用户
      LoginService.providerLogin(systemModule.mpPlatform)
    }
  }


  //登录，授权，绑定手机号各大平台登录结果，后者授权手机号结果
  async openTypeBtnClick (providerResult) {
    console.log(providerResult)
    if (this.openTypeBtnEnable) {
      this.openTypeBtnEnable = false
      try {
        //没用户，天王老子来了也只能先登录
        if (!this.user) {
          await this.providerLogin(providerResult, systemModule.mpPlatform)
          //登录完成之后，只有为授权用户信息跳转会小程序
          //如果不为三方授权、且没有手机号，则只可能是来绑定手机号的
        } else if (!this.isAuthUser && !this.hasPhoneNum) {
          //如果微信绑定手机号
          await this.getPhoneNumberByWx(providerResult)
        }
        //只有为三方授权才调用
        //如果授权用信息
        //如果授权手机号
        if (this.isThreeAuth) {
          //如果点了按钮就会并授权，要不然也不会到这里
          await this.authUserOrPhoneNum()
        }
      } finally {
        this.openTypeBtnEnable = true
      }
    }
  }

  socialUniAuthLogin () {
    const authVO: SocialUniAuthVO = new SocialUniAuthVO('user', CommonUtil.getUUID())
    uni.navigateToMiniProgram({
      appId: 'wx0bf5fe9ceeab514c',
      // appId: '1109985787',
      path: 'pages/user/login',
      extraData: authVO
    })
  }

  socialUniAuthPhoneNum () {
    const authVO: SocialUniAuthVO = new SocialUniAuthVO('phone', this.user.id)
    uni.navigateToMiniProgram({
      appId: 'wx0bf5fe9ceeab514c',
      // appId: '1109985787',
      path: 'pages/user/login',
      extraData: authVO
    })
  }

  async authUserOrPhoneNum () {
    //有用户信息，并且伪授权用户信息
    if (this.isAuthUser && this.user) {
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
      AppUtilAPI.sendErrorLogAPI(null, '错误的用户授权类型')
    }
  }

  async authPhoneNum () {
    //有用户信息，并且伪授权用户信息
    if (this.isAuthPhone && this.hasPhoneNum) {
      //处理用户授权
      await Alert.confirm('是否确认授权您的手机号码')
      UniUtil.showLoading('授权中')
      const threeAuthResultVO = await OpenDataAPI.authUserPhoneNumAPI()
      UniUtil.hideLoading()
      threeAuthResultVO.data.appUserId = this.threeUserId
      threeAuthResultVO.data.authType = this.threeAuthType
      this.authSuccessGoBackThreeMp(threeAuthResultVO)
    } else {
      AppUtilAPI.sendErrorLogAPI(null, '错误的用户授权类型')
    }
  }


  // 微信点击按钮，获取手机号用来绑定
  async getPhoneNumberByWx (obj: any) {
    await UserService.getPhoneNumberByWx(obj)
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
