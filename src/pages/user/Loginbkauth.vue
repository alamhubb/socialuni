<template>
  <!--  全部字体加大一号，白色背景，垂直居中，子元素宽度占满-->
  <!--  过来的授权及时手机号授权，也会提示先登录，可以自己选择手机号登录-->
  <div class="h100p bg-white col-all-center">

    <div v-if="goBackCountDown" class="text-bold text-lg">
      授权成功， {{ goBackCountDown }} 秒后返回...
    </div>

    <!--    上padding 7vh，兼容各平台，底部10px，左右20px-->
    <div v-else class="pt-7vh pb-sm px w100p">
      <!--      上半部，无用户，无授权，显示logo+提示绑定手机号-->
      <!--      上半部，无用户，授权，显示三方应用申请信息+提示绑定手机号-->
      <!--      上半部，有用户，授权，无手机号，显示三方应用申请信息+提示绑定手机号-->
      <!--      上半部，有用户，授权-->
      <!--      上半部，手机号登录展示-->
      <view>
        <!--    如果是绑定手机号，或者手机号登录，就展示手机号。-->
        <!--        手机号界面-->
        <!--        35*4 = 140 +30+80 = 250高度-->
        <view v-if="showPhoneView" class="h250px">
          <!--          如果没登录-->
          <view v-if="!hasUser" class="u-type-warning-light-bg row-col-center h80px px-sm">
            <u-icon name="volume-fill" class="u-type-warning" size="50"></u-icon>
            <view class="ml-smm text-bold">
              <text class="u-type-primary">欢迎登录清池，建议使用 微信 或 QQ 登录</text>
              ，无需输入，一键登录更便捷
            </view>
          </view>
          <view v-else class="h80px"></view>

          <view class="mt-md row-col-center">
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
        <view v-else class="h250px flex-col pb-sm">
          <div class="flex-none font-xl font-bold mb row-center">欢迎使用社交联盟授权中心</div>
          <!--          <div class="font-xs">有用户：{{hasUser}}，有手机号：{{hasPhoneNum}}，
                      <br>
                      用户授权：{{isAuthUser}}，手机号授权：{{isAuthPhone}}</div>-->
          <!--          三方授权显示三方申请的信息-->
          <view v-if="threeAuth" class="flex-1">
            <!--            三方授权显示三方的信息-->
            <view class="row-col-center px-sm py-xs">
              <u-avatar class="mr-sm" src="/static/jimei1.png" mode="square" size="50"></u-avatar>
              <text class="mr-sm font-bold">集美丨女生日常分享交友</text>
              申请
            </view>

            <template v-if="threeAuth">
              <!--    如果授权用户信息提示显示获取用户信息-->
              <template v-if="isAuthUser">
                <view class="font-bold row-col-center pa-sm font-md">
                  获取您的昵称、头像、地区、性别及动态
                </view>
                <!-- 如果为三方授权， 不为手机号授权， 如果有用户显示出来用的信息-->
                <view v-if="hasUser" class="flex-row pa-sm u-border-top-bottom">
                  <u-avatar class="mr-sm" src="/static/logo.png" mode="square"></u-avatar>
                  <view class="col-around flex-1">
                    <view>不会魔法</view>
                    <view class="text-grey font-xs">微信个人信息</view>
                  </view>
                  <view class="col-center">
                    <u-icon name="checkmark" color="success" size="40"></u-icon>
                  </view>
                </view>
              </template>
              <template v-else-if="isAuthPhone">
                <!--            为授权手机号显示授权手机号-->
                <view class="font-bold row-col-center pa-sm font-md">
                  获取您清池绑定的手机号
                  <!--                如果有手机号显示-->
                  <!--                <text>：186*****595</text>-->
                  <text v-if="hasPhoneNum">：186*****595</text>
                </view>
              </template>
              <!--  如果没有用户，展示提示请登录-->
              <!--  如果为三方授权，用户授权，没有登录，则显示，上面这俩可以合并-->
              <view v-if="!hasUser" class="pa-sm">
                您未登录，点击登录进行授权
              </view>
              <view v-else-if="isAuthPhone&&!hasPhoneNum" class="px-sm py-xs row-col-center">
                <!--                请点击绑定手机号进行授权-->
                您未在清池绑定手机号，点击绑定手机号授权
              </view>
            </template>


            <!--  如果三方授权，手机号授权，没有用户，没有手机号，提示-->
            <!--  请登录并绑定手机号后进行授权-->
          </view>
          <!--          只有不为三方授权才显示logo-->
          <div v-else class="flex-1 row-center pb-md">
            <!--        登录界面，展示logo-->
            <image class="radius flex-none h100p"
                   mode="aspectFit"
                   src="/static/img/logo.png"
            />
          </div>

          <div v-if="!hasPhoneNum && !isAuthUser" class="row-center">
            <!--            如果为授权手机号，则提示-->
            <view class="u-border-bottom text-gray">
              绑定手机号后可发表动态，详情
            </view>
            <u-icon class="ml-xs text-gray" name="arrow-right"></u-icon>
          </div>
        </view>
      </view>

      <!--        隐私提示-->

      <div class="pt-sm h50px">
        <view v-if="!hasUser" class="row-center row-grid u-type-info">
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

      <view class="h175px">
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
        <view class="col-row-center">
          <!--            手机号登录界面-->
          <template v-if="showPhoneView">
            <!--            如果是输入手机号页面，未登录，提示登录-->
            <button :disabled="loginButtonDisabled"
                    class="h40px cu-btn lg bg-gradual-phone  row-all-center bd-none bg-active round mt w100p"
            >
              <u-icon custom-prefix="mdi" color="white" name="cellphone-android" size="42" class="mr-xs"></u-icon>
              <template v-if="!hasUser">
                手机号登录{{ isAuthPhone ? '并授权' : '' }}
              </template>
              <!--            如果是输入手机号页面，已登录，如果不为三方授权-->
              <template v-else-if="hasUser&&!hasPhoneNum">
                绑定手机号{{ isAuthPhone ? '并授权' : '' }}
              </template>
            </button>
            <!--              其他为错误的逻辑-->
          </template>
          <!--            微信登录界面，非手机号登录界面-->
          <template v-else>
            <!--              没登录提示登录，如果为三方授权且为授权用户信息，追加 并授权三个字-->
            <!-- 只要不为QQ小程序平台都可以使用微信登录-->
            <button :disabled="!platformLoginEnable"
                    :open-type="getBtnOpenType"
                    class="h40px cu-btn lg bg-gradual-wx row-all-center bd-none bg-active round mt w100p"
                    @click="platformLoginClick">
              <u-icon v-if="!hasUser||(isAuthPhone&&!hasPhoneNum)" color="white" name="weixin-fill" size="42"
                      class="mr-xs"></u-icon>
              <template v-if="!hasUser">
                微信登录 {{ isAuthUser ? '并授权' : '' }}
              </template>
              <!--            如果已登录有用户-->
              <template v-else>
                <!--              已登录，三方授权，授权用户信息-->
                <!--                授权那用户信息无需关心手机号绑定状态-->
                <template v-if="isAuthUser">
                  <!-- 只要不为QQ小程序平台都可以使用微信登录-->
                  <!--                    <u-icon color="white" name="weixin-fill" size="42" class="mr-xs"></u-icon>-->
                  授权用户信息
                </template>
                <!--              已登录，三方授权，授权手机号信息，绑定了手机号，提示授权手机号-->
                <template v-else-if="isAuthPhone&&hasPhoneNum">
                  <!--                    <u-icon color="white" name="weixin-fill" size="42" class="mr-xs"></u-icon>-->
                  授权手机号
                </template>
                <!--              已登录，不为三方授权，进来只能是绑定微信手机号-->
                <template v-else-if="!hasPhoneNum">
                  <!--                    <u-icon color="white" name="weixin-fill" size="42" class="mr-xs"></u-icon>-->
                  绑定微信手机号{{ isAuthPhone ? '并授权' : '' }}
                </template>
                <!--                其他为错误的逻辑-->
              </template>
            </button>
          </template>
        </view>


        <view class="row-around-center h40px mt-smm">
          <!--              <view v-if="threeAuth" class="row-col-center">-->
          <view class="row-col-center w40p" @click="threeAuth=!threeAuth">
            <u-icon class="mr-xs text-gray" name="arrow-left"></u-icon>
            <view class="text-gray u-border-bottom">
              <!--            手机号登录界面-->
              <template v-if="showPhoneView">
                <!--            如果是输入手机号页面，未登录，提示手机号登录-->
                <template v-if="!hasUser">
                  {{ isAuthPhone ? '不授权' : '不登录' }}返回
                  <!--                    {{ isAuthPhone ? '集美' : '' }}-->
                </template>
                <!--            如果是输入手机号页面，已登录，如果不为三方授权，提示绑定手机号-->
                <template v-else-if="hasUser&&!hasPhoneNum">
                  {{ isAuthPhone ? '不授权' : '不绑定' }}返回
                  <!--                    {{ isAuthPhone ? '集美' : '' }}-->
                </template>
                <!--              其他为错误的逻辑-->
              </template>
              <!--            微信登录界面，非手机号登录界面-->
              <template v-else>
                <!--              没登录提示登录，如果为三方授权且为授权用户信息，追加 并授权三个字-->
                <template v-if="!hasUser">
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
                  <template v-else-if="!hasPhoneNum">
                    {{ isAuthPhone ? '不授权' : '不绑定' }}返回
                    <!--                      {{ isAuthPhone ? '集美' : '' }}-->
                  </template>
                  <!--                其他为错误的逻辑-->
                </template>
              </template>
            </view>
          </view>
          <view @click="switchShowPhoneNum" class="row-end-center w40p">
            <view class="text-gray">
              <!--            手机号登录界面-->
              <template v-if="showPhoneView">
                <!--            如果是输入手机号页面，未登录，提示手机号登录-->
                <template v-if="!hasUser">
                  其他方式{{ isAuthPhone ? '授权' : '登录' }}
                </template>
                <!--            如果是输入手机号页面，已登录，如果不为三方授权，提示绑定手机号-->
                <template v-else-if="hasUser&&!hasPhoneNum">
                  其他方式{{ isAuthPhone ? '授权' : '绑定' }}
                </template>
                <!--              其他为错误的逻辑-->
              </template>
              <!--            微信登录界面，非手机号登录界面-->
              <template v-else>
                <!--              没登录提示登录，如果为三方授权且为授权用户信息，追加 并授权三个字-->
                <template v-if="!hasUser">
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
            <u-icon v-if="showPhoneView||!hasUser||(!hasPhoneNum&&!isAuthUser)" class="ml-xs text-gray"
                    name="arrow-right"></u-icon>
          </view>
        </view>

      </view>

      <view class="row-between mt-md">
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
import { Vue, Component } from 'vue-property-decorator'
import UserVO from '@/model/user/UserVO'
import { namespace } from 'vuex-class'
import UniUtil from '@/utils/UniUtil'
import UCheckbox from 'uview-ui/components/u-checkbox/u-checkbox.vue'
import UButton from 'uview-ui/components/u-button/u-button.vue'
import { systemModule } from '@/plugins/store'
import ThreeAuthType from '@/const/ThreeAuthType'
import ButtonOpenType from '@/const/ButtonOpenType'
import Alert from '@/utils/Alert'
import ThreeAuthUserInfoResultVO from '@/model/ThreeAuthUserInfoResultVO'

const SkipUrlConst = {}

const userStore = namespace('user')
const configStore = namespace('config')
const systemStore = namespace('system')
const systemStore = namespace('app')

@Component({
  components: {
    UCheckbox, UButton
  }
})
export default class LoginVue extends Vue {
  @userStore.State('user') user: UserVO
  // @configStore.Getter(ConfigMap.authCodeIntervalKey) authCodeInterval: number
  // @configStore.Getter(ConfigMap.qqServiceKey) qqService: string
  @systemStore.State('isMp') isMp: boolean
  @systemStore.State('isMp') isMp: boolean


  getBtnOpenType () {
    if (!this.hasUser) {
      return ButtonOpenType.getUserInfo
    } else if (this.isAuthPhone && !this.hasPhoneNum) {
      return ButtonOpenType.getPhoneNumber
    } else {
      return ''
    }
  }

  platformLoginClick (loginResult) {
    if (!this.hasUser) {
      this.mpWxLogin(loginResult)
      //登录完成之后，只有为授权用户信息跳转会小程序
    } else if (this.isAuthPhone && !this.hasPhoneNum) {
      this.getPhoneNumberByWx(loginResult)
      //只有为用户授权手机号，跳转回三方，否则停留
    } else if (this.isAuthUser) {
      //处理用户授权
      Alert.confirm('是否确认授权用户信息').then(() => {
        console.log('授权用户信息和token')
        UniUtil.showLoading('授权中')
        // OpenDataAPI.authUserInfoAPI().then(res => {
        UniUtil.hideLoading()
        const result = res.data
        this.goBackCountDown = 2
        const threeResult: ThreeAuthUserInfoResultVO = new ThreeAuthUserInfoResultVO()
        threeResult.appUserId = this.threeUserId
        threeResult.authType = this.threeAuthType
        threeResult.tokenCode = result.tokenCode
        threeResult.user = result.user
        const timer = setInterval(() => {
          if (this.goBackCountDown) {
            this.goBackCountDown--
          } else {
            clearInterval(timer)
            uni.navigateBackMiniProgram({
              extraData: threeResult
            })
          }
        }, 1000)
        // })
      })
    } else if (this.isAuthPhone) {
      Alert.confirm('是否确认授权手机号').then(() => {
        console.log('授权用户手机号')
      })
    } else {
      console.error('错误的登录逻辑分支')
    }
  }

  // 微信点击按钮，获取手机号用来绑定
  getPhoneNumberByWx (obj: any) {
    if (obj.detail.errMsg === 'getPhoneNumber:ok') {
      this.phoneBtnDisabled = true
      // 默认未过期
      LoginUtil.checkSession().then(() => {
        const loginData: LoginDataVO = new LoginDataVO()
        Object.assign(loginData, obj.detail)
        loginData.sessionEnable = true
        this.getPhoneNumberAfterHandler(loginData).then(() => {
          this.phoneBtnDisabled = false
        }).catch((error) => {
          if (error.errorCode === ErrorConst.custom) {
            this.getPhoneNumberByLogin(obj)
          } else {
            this.phoneBtnDisabled = false
          }
        })
      }).catch(() => {
        this.getPhoneNumberByLogin(obj)
      })
    } else {
      ToastUtil.toast('您选择了不绑定')
    }
  }

  mpWxLogin (loginResult) {
    // #ifdef MP
    // #ifndef MP-TOUTIAO
    if (loginResult.detail.errMsg !== Constants.loginSuccess) {
      Toast.toast('您取消了登录')
      return
    }
    // #endif
    this.disabledLoginBtn = true
    LoginService.platformLogin(systemModule.provider).finally(() => {
      this.disabledLoginBtn = false
    })
    // #endif
  }

  phoneNum = ''
  authCode = ''
  countDown = 0
  bindBtnDisabled = false
  platformLoginEnable = true

  // showPhoneView = true
  showPhoneView = false

  //同意协议
  contractChecked = true

  authCodeFocus = false
  phoneNumFocus = false

  threeAuthType = ThreeAuthType.user
  // threeAuthType = ThreeAuthType.phone

  hasUser = true
  hasPhoneNum = false

  threeAuth = true
  threeUserId = ''

  goBackCountDown = 0

  //是什么授权什么后面显示个并授权
  //绑定微信手机号并授权
  //绑定并授权
  //绑定

  //过来申请手机号授权，用户未注册，未登录，未绑定怎么显示。
  // 如果三方来的不显示绑定手机号发表动态，仅显示申请手机号信息和用户信息
  // 显示 绑定微信手机号并授权

  //绑定微信手机号
  //微信登录

  //如果有用户，
  //仅授权用户信息，不要提示授权并绑定手机号，可以有个绑定手机号按钮，因为提示了绑定手机号，才能发表动态
  //如果来授权用户信息，就不要展示绑定手机号才可发布动态？这个交给三方决定，你提示用户可以直接绑定手机号授权
  //同时授权手机号 ，不存在这种情况。根据三方请求决定的，用户无法决定授权什么

  //返回手机号的同时返回用户信息，还是仅返回手机号，同时返回没什么问题，只要调用授权手机号接口就行。

  //如果没user且为phone

  //手机号登录 {{并授权}} 这种只返回用户信息，因为请求时用户授权的请求
  //手机号绑定 {{并授权}}

  //如果三方登录，且不为授权手机号，这种情况先可以不考虑
  //仅授权用户信息返回
  //同时授权手机号返回


  get phoneBtnNum () {

  }

  get isAuthUser () {
    return this.threeAuth && this.threeAuthType === ThreeAuthType.user
  }

  get isAuthPhone () {
    return this.threeAuth && this.threeAuthType === ThreeAuthType.phone
  }

  get homeUrl (): string {
    // return SkipUrlConst.homeUrl()
  }

  get userAgreementUrl (): string {
    // return SkipUrlConst.userAgreementUrl()
  }

  get userPrivacyUrl (): string {
    // return SkipUrlConst.userPrivacyUrl()
  }

  get childProtectUrl (): string {
    // return SkipUrlConst.childProtectUrl()
  }

  get loginButtonDisabled () {
    // return !this.contractChecked || !this.phoneNumberRight || !this.authCodeRight || this.bindBtnDisabled
    return !this.phoneNumberRight || !this.authCodeRight || this.bindBtnDisabled
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

  created () {
    if (this.user) {
      this.showPhoneView = true
    }
  }

  qqLogin () {
    this.providerLogin(ProviderType.qq)
  }

  wxLogin () {
    this.providerLogin(ProviderType.wx)
  }

  providerLogin (providerType: Provider) {
    if (this.platformLoginEnable) {
      this.platformLoginEnable = false
      LoginService.platformLogin(providerType).finally(() => {
        this.platformLoginEnable = true
      })
    }
  }

  /* //不显示登录
   get showMpLoginBtn() {
     //不为手机登录，或者都不为焦点
     return !this.showPhoneView || (!this.phoneNumFocus && !this.authCodeFocus)
   }
 */
  phoneNumClear () {
    this.phoneNum = ''
    UniUtil.delayTime(100).then(() => {
      this.phoneNumInputFocus()
    })
  }

  authCodeClear () {
    this.authCode = ''
    UniUtil.delayTime(100).then(() => {
      this.authCodeInputFocus()
    })
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


  cancelBind () {
    // 回上一页
    PageUtil.goBack()
  }

  //手机号登录和手机号绑定
  loginByPhoneNumAndBindPhoneNum () {
    //再次校验
    if (!this.phoneNumberRight) {
      return Toast.toast('请输入正确的手机号')
    }
    if (!this.authCodeRight) {
      return Toast.toast('请输入正确的验证码')
    }
    if (!this.contractChecked) {
      return UniUtil.hint('请仔细阅读用户协议、隐私政策等内容后勾选同意')
    }
    if (!this.bindBtnDisabled) {
      this.bindBtnDisabled = true
      if (this.user) {
        //手机号绑定
        UserAPI.bindPhoneNumAPI(this.phoneNum, this.authCode).then((res: any) => {
          UserStore.setMineUser(res.data)
          let msg = '绑定成功'
          //qq小程序下ios系统存在输入框冲突问题，使用了一个输入框，另一个就无法出现
          if (systemModule.isIosAndMpQQ) {
            msg += '，如遇无法弹出输入框，请重启应用'
          }
          // 提示验证码发送成功
          UniUtil.hint(msg).finally(() => {
            PageUtil.toMinePage()
          })
        }).finally(() => {
          this.bindBtnDisabled = false
        })
      } else {
        const loginData = new LoginDataVO()
        loginData.phoneNum = this.phoneNum
        loginData.authCode = this.authCode
        loginData.provider = ProviderType.phone
        loginData.platform = systemModule.platform
        LoginAPI.platformLoginAPI(loginData).then(() => {
          // 提示验证码发送成功
          let msg = '登录成功'
          //qq小程序下ios系统存在输入框冲突问题，使用了一个输入框，另一个就无法出现
          if (systemModule.isIosAndMpQQ) {
            msg += '，如遇无法弹出输入框，请重启应用'
          }
          UniUtil.hint(msg)
        }).finally(() => {
          this.bindBtnDisabled = false
        })
      }
    }
  }
}
</script>
