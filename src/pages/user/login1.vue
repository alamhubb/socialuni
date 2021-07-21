<template>
  <!--  全部字体加大一号，白色背景，垂直居中，子元素宽度占满-->
  <!--  过来的授权及时手机号授权，也会提示先登录，可以自己选择手机号登录-->
  <div class="h100p bg-white col-all-center">
    <!--    上padding 7vh，兼容各平台，底部10px，左右20px-->
    <div class="pt-6vh pb-sm px w100p">
      <!--      上半部，无用户，无授权，显示logo+提示绑定手机号-->
      <!--      上半部，无用户，授权，显示三方应用申请信息+提示绑定手机号-->
      <!--      上半部，有用户，授权，无手机号，显示三方应用申请信息+提示绑定手机号-->
      <!--      上半部，有用户，授权-->
      <!--      上半部，手机号登录展示-->

      <div class="flex-none col-row-center h80px">
        <div class="text-xxl font-bold">欢迎登录集美小世界</div>
        <!--        <div v-if="showPhoneView" class="text-md u-type-warning mt-sm">建议使用微信 或 QQ一键登录</div>-->
      </div>

      <view class="mt">
        <!--    如果是绑定手机号，或者手机号登录，就展示手机号。-->
        <!--        手机号界面-->
        <!--        35*4 = 140 +30+80 = 250高度-->
        <!--        如果有用户且不为微信，则默认展示验证码绑定-->
        <view v-if="showPhoneView" class="h150px">
          <view class="row-col-center">
            <view>手机号</view>
            <div class="flex-row flex-1 ml-smm solid-bottom">
              <!--   自动获取焦点的话app平台会有问题，打开我的页面时会弹出键盘   :focus="true"-->
              <input class="flex-1 h35px" type="number" name="input" :focus="phoneNumFocus" :maxlength="11"
                     v-model.trim="phoneNum"
                     @confirm="authCodeInputFocus"
                     @blur="phoneNumInputBlur" @focus="phoneNumInputFocus"
                     :confirm-hold="true"
                     placeholder="请填写手机号"
              />
              <u-icon v-if="phoneNum" class="text-gray" name="close-circle" size="40"
                      @touchend.native.prevent="phoneNumClear"></u-icon>
            </div>

            <view class="ml-smm cu-capsule radius">
              <view class='cu-tag bg-blue'>
                +86
              </view>
              <view class="cu-tag line-blue">
                中国大陆
              </view>
            </view>
          </view>
          <view class="mt-xs h30px row-col-center px-sm">
            <text v-if="phoneNum && !phoneNumberRight" class="text-red">
              *请输入正确的手机号
            </text>
          </view>

          <view class="row-col-center">
            <view>验证码</view>

            <div class="flex-row ml-smm solid-bottom flex-1">
              <!--   自动获取焦点的话app平台会有问题，打开我的页面时会弹出键盘   :focus="true"-->
              <input class="flex-1 h35px" type="number" name="input" :focus="authCodeFocus" :maxlength="4"
                     v-model.trim="authCode"
                     @focus="authCodeInputFocus"
                     @blur="authCodeInputBlur"
                     placeholder="请填写验证码"
              />
              <u-icon v-if="authCode" class="text-gray" name="close-circle" size="40"
                      @touchend.native.prevent="authCodeClear"></u-icon>

              <!--                <u-icon v-if="phoneNum" class="text-gray mr-lg" name="close-circle" size="40"
                                      @touchend.native.prevent="phoneNumClear"></u-icon>-->
            </div>

            <view @click="sendCodeClick" class="ml-smm">
              <button type="primary" class='cu-btn bg-green' :disabled="sendAuthCodeBtnDisabled">
                {{ countDown ? authCodeInterval + 1 - countDown : '发送验证码' }}
              </button>
            </view>
          </view>
          <view class="mt-xs h30px row-col-center px">
            <text v-if="!authCodeRight&&phoneNumberRight" class="text-red">
              *请输入正确的验证码
            </text>
          </view>
        </view>

        <!--        登录界面，展示logo-->
        <!--      上半部，无用户，无授权，显示logo+提示绑定手机号-->
        <!--      上半部，无用户，授权，显示三方应用申请信息+提示绑定手机号-->
        <!--      上半部，有用户，授权，无手机号，显示三方应用申请信息+提示绑定手机号-->
        <!--      上半部，有用户，授权-->
        <view v-else class="h150px flex-col">
          <!--          只有不为三方授权才显示logo-->
          <div class="flex-1 row-center pb-md">
            <!--        登录界面，展示logo-->
            <image class="radius flex-none h100p"
                   mode="aspectFit"
                   src="/static/img/logo.png"
            />
          </div>
        </view>
      </view>

      <div v-if="!hasPhoneNum" class="row-center pt-sm pb-sm">
        <!--            如果为授权手机号，则提示-->
        <view class="u-border-bottom text-gray">
          绑定手机号后可发表动态，详情
        </view>
        <u-icon class="ml-xs text-gray" name="arrow-right"></u-icon>
      </div>

      <!--        隐私提示-->

      <div class="pt-sm h50px">
        <view v-if="!user" class="row-center row-grid u-type-info">
          <text class="mb-nn">
            登录即代表同意
          </text>
          <navigator :url="userAgreementUrl" class="text-blue">
            《用户协议》
          </navigator>
          <navigator :url="userPrivacyUrl" class="text-blue">
            《隐私政策》
          </navigator>
          <navigator :url="childProtectUrl" class="text-blue">
            《儿童个人信息保护规则及监护人须知》
          </navigator>
        </view>
      </div>

      <div>
        <view class="col-row-center">
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

              <button v-else-if="isWx" :disabled="!openTypeBtnEnable"
                      class="bg-gradual-wx h40px cu-btn lg row-all-center bd-none bg-active round mt w100p"
                      @click="providerLoginClick">
                <u-icon color="white" name="weixin-fill" size="42"
                        class="mr-xs"></u-icon>
                微信登录{{ isAuthUser ? '并授权' : '' }}
              </button>

              <button v-else-if="isQQ" :disabled="!openTypeBtnEnable"
                      open-type="getUserInfo"
                      class="bg-gradual-qq h40px cu-btn lg row-all-center bd-none bg-active round mt w100p"
                      @getuserinfo="providerLoginClick">
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


          <view class="row-between-center w300px h20px mt-md">
            <!--              <view v-if="isThreeAuth" class="row-col-center">-->
            <view class="row-col-center" @click="goBackPage">
              <u-icon class="mr-xs text-gray" name="arrow-left"></u-icon>
              <view class="text-gray u-border-bottom">
                <!--            手机号登录界面-->
                <template v-if="user">
                  不绑定返回
                </template>
                <template v-else>
                  不登录返回
                </template>
              </view>
            </view>
            <view @click="switchShowPhoneNum" class="row-end-center">
              <view class="text-gray">
                <!--            手机号登录界面-->
                <template v-if="showPhoneView">
                  <!--            如果是输入手机号页面，未登录，提示手机号登录-->
                  <template v-if="!user">
                    其他方式登录
                  </template>
                  <!--            如果是输入手机号页面，已登录，如果不为三方授权，提示绑定手机号-->
                  <template v-else>
                    其他方式绑定
                  </template>
                  <!--              其他为错误的逻辑-->
                </template>
                <!--            微信登录界面，非手机号登录界面-->
                <template v-else>
                  <!--              没登录提示登录，如果为三方授权且为授权用户信息，追加 并授权三个字-->
                  <template v-if="!user">
                    <!-- 只要不为QQ小程序平台都可以使用微信登录-->
                    手机号登录
                  </template>
                  <!--            如果已登录有用户-->
                  <template v-else>
                    <!--              已登录，三方授权，授权用户信息-->
                    <!--                授权那用户信息无需关心手机号绑定状态-->
                    <!--              已登录，不为三方授权，进来只能是绑定微信手机号-->
                    验证码方式绑定
                    <!--                其他为错误的逻辑-->
                  </template>
                </template>
              </view>
              <!--              验证码登录、或者没用户、或者没手机号且不为授权用户、-->
              <u-icon class="ml-xs text-gray"
                      name="arrow-right"></u-icon>
            </view>
          </view>
        </view>
      </div>

      <view class="row-between mt-lg">
        <navigator :url="homeUrl" class="u-type-info u-border-bottom">
          <u-icon name="home" size="30" class="mr-xs"></u-icon>
          查看官网
        </navigator>

        <view @click="copyServiceNum" class="u-type-info u-border-bottom">
          客服微信、QQ：{{ qqService }}
          <u-icon name="attach" size="30" class="ml-xs"></u-icon>
        </view>
      </view>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import UserVO from '@/model/user/UserVO'
import { namespace } from 'vuex-class'
import CommonUtil from '@/utils/CommonUtil'
import NumberUtil from '@/utils/NumberUtil'
import ConfigMap from '@/const/ConfigMap'
import SkipUrlConst from '@/const/SkipUrlConst'
import { appModule, userModule } from '@/store'
import Alert from '@/utils/Alert'
import Constants from '@/const/Constant'
import Toast from '@/utils/Toast'
import PageUtil from '@/utils/PageUtil'
import SocialUniAuthVO from '@/model/openData/SocialUniAuthVO'
import SystemStoreProp from '@/store/SystemStoreProp'
import UniProviderLoginQO from '@/model/UniProviderLoginQO'
import UserAPI from '@/api/UserAPI'
import LoginAPI from '@/api/LoginAPI'
import UniUtil from '@/utils/UniUtil'
import UserService from '@/service/UserService'
import LoginService from '@/service/LoginService'

const userStore = namespace('user')
const configStore = namespace('config')
const systemStore = namespace('system')
const appStore = namespace('app')

@Component
export default class LoginPage extends Vue {
  @uniSystemStore.State(SystemStoreProp.isMpWx) isWx: string
  @uniSystemStore.State(SystemStoreProp.isMpQQ) isQQ: string

  @userStore.State('user') user: UserVO
  @userStore.Getter('hasPhoneNum') hasPhoneNum: boolean

  @configStore.Getter(ConfigMap.authCodeIntervalKey) authCodeInterval: number
  @configStore.Getter(ConfigMap.qqServiceKey) qqService: string

  //首先需要携带threeAppId和密钥去后台查询，三方信息，如果不对提示错误。然后也无法向后台授权。
  //如果三方信息错误，上面是显示，申请授权方信息错误，不予授权

  phoneNum = ''
  authCode = ''
  countDown = 0
  openTypeBtnEnable = true

  showPhoneView = false

  //是否授权成功
  authSuccess = false

  get homeUrl (): string {
    return SkipUrlConst.homeUrl()
  }

  get userAgreementUrl (): string {
    return SkipUrlConst.userAgreementUrl()
  }

  get userPrivacyUrl (): string {
    return SkipUrlConst.userPrivacyUrl()
  }

  get childProtectUrl (): string {
    return SkipUrlConst.childProtectUrl()
  }

  //同意协议
  // contractChecked = true

  phoneNumClear () {
    this.phoneNum = ''
    CommonUtil.delayTime(100).then(() => {
      this.phoneNumInputFocus()
    })
  }

  authCodeClear () {
    this.authCode = ''
    CommonUtil.delayTime(100).then(() => {
      this.authCodeInputFocus()
    })
  }

  get loginButtonDisabled () {
    // !this.contractChecked ||
    return !this.phoneNumberRight || !this.authCodeRight || !this.openTypeBtnEnable
  }

  copyServiceNum () {
    UniUtil.textCopy('491369310', '客服联系方式已复制')
  }

  sendCodeClick () {
    if (!this.phoneNumberRight) {
      return Toast.toast('请输入正确的手机号')
    }
    if (this.countDown) {
      return Toast.toast('验证码发送频繁，请等待')
    }

    this.authCodeClear()

    this.countDown++
    const Timer = setInterval(() => {
      if (this.countDown === this.authCodeInterval) {
        clearInterval(Timer)
        this.countDown = 0
        return
      }
      this.countDown++
    }, 1000)
    // 如果怕太频繁，就显示相同手机号每天只能发送几次，一小时内只能5次
    UserAPI.sendAuthCodeAPI(this.phoneNum).then(() => {
      // 提示验证码发送成功
      Toast.toast('验证码发送成功')
    })
  }

  goBackPage () {
    if (!this.user) {
      PageUtil.goHome()
    } else {
      PageUtil.toMinePage()
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


  authCodeFocus = false
  phoneNumFocus = false

  phoneNumInputFocus () {
    this.phoneNumFocus = true
  }

  phoneNumInputBlur () {
    this.phoneNumFocus = false
  }

  authCodeInputFocus () {
    this.authCodeFocus = true
  }

  authCodeInputBlur () {
    this.authCodeFocus = false
  }

  get sendAuthCodeBtnDisabled () {
    return !this.phoneNumberRight || Boolean(this.countDown)
  }

  get authCodeRight () {
    return this.authCode && this.authCode.length === 4 && NumberUtil.isAllNumber(this.authCode)
  }

  get phoneNumberRight () {
    return this.phoneNum && this.phoneNum.length === 11 && NumberUtil.isAllNumber(this.phoneNum)
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
          if (uniSystemModule.isIos && uniSystemModule.isQQ) {
            msg += '，如遇无法弹出输入框，请重启应用'
          }
          // 提示验证码发送成功
          Alert.hint(msg)
        } else {
          Alert.hint('您已绑定手机号')
        }
      } else {
        await LoginService.phoneLogin(this.phoneNum, this.authCode)
      }
      this.goBackPage()
      this.openTypeBtnEnable = true
    }
  }


  //平台登录
  async providerLogin (result, provider: UniProviderLoginQO) {
    // #ifdef MP
    // #ifndef MP-TOUTIAO
    if (result.detail.errMsg !== Constants.loginSuccess) {
      return Toast.toast('您取消了登录')
    }
    // #endif
    // await SocialLoginService.providerLogin(provider)
    // #endif
  }


  //登录，授权，绑定手机号各大平台登录结果，后者授权手机号结果
  async providerLoginClick (provide: string) {
    //一行代码就可以获取登录所需要的信息, 还可以配合后台使用，一键登录，记住用户
    LoginService.providerLogin(provide)
  }


  //登录，授权，绑定手机号各大平台登录结果，后者授权手机号结果
  async openTypeBtnClick (providerResult) {
    /*  const res = await OAuthAPI.mockApplySocialOAuthAPI()
      const SocialLoginRO = res.data
      const loginQO = UniProviderLoginQOUtil.getQOBySocialLoginRO(SocialLoginRO)*/
    // const loginQO = await UniUserUtil.getUniProviderLoginQO(uniSystemModule.loginProviderType)
    // LoginAPI.providerLoginAPI(loginQO)

    /*if (this.openTypeBtnEnable) {
      this.openTypeBtnEnable = false
      const loginData = await UniUserUtil.getProviderUserInfoRO(systemModule.loginProviderType)
      /!*if (systemModule.isApp) {
        loginData.clientid = systemModule.clientid
      }*!/
      return LoginAPI.providerLoginAPI(loginData).then((res: any) => {
        const user = UserService.getMineUserInitDataActionByToken(res)
        let hintText = '登录成功'
        if (!user.phoneNum) {
          hintText += '，绑定手机号后才可发布内容'
        }
        if (systemModule.isIosAndMpQQ) {
          hintText += '，如遇无法弹出输入框，请重启应用'
        }
        Alert.hint(hintText)
        return user
      })*/

    /*try {
        //没用户，天王老子来了也只能先登录
        await this.providerLogin(providerResult, systemModule.loginProviderType)
        //登录完成之后，只有为授权用户信息跳转会小程序
        //如果不为三方授权、且没有手机号，则只可能是来绑定手机号的
      } finally {
        this.openTypeBtnEnable = true
      }*/
    // }
  }

  socialUniAuthLogin () {
    //开发模式模拟授权
    if (appModule.isDevMode) {
      LoginAPI.mockLogin().then(res => {
        UserService.getMineUserInitDataActionByToken(res as any)
        PageUtil.toTalkPage()
      })
    } else {
      const authVO: SocialUniAuthVO = new SocialUniAuthVO('user')
      PageUtil.toSocialUniAuth(authVO)
    }
  }

  socialUniAuthPhoneNum () {
    //开发模式模拟授权
    if (appModule.isDevMode) {
      LoginAPI.mockBindPhone().then(res => {
        userModule.setUser(res.data)
      })
    } else {
      const authVO: SocialUniAuthVO = new SocialUniAuthVO('phone')
      PageUtil.toSocialUniAuth(authVO)
    }
  }
}
</script>
