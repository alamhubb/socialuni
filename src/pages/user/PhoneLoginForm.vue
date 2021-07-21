<template>
  <div>
    <!--      手机号登录form-->
    <view class="row-col-center">
      <view>手机号</view>
      <div class="flex-row flex-1 ml-smm solid-bottom">
        <!--   自动获取焦点的话app平台会有问题，打开我的页面时会弹出键盘   :focus="true"-->
        <input class="flex-1 h35px" type="number" name="input" :focus="phoneNumFocus" :maxlength="11"
               v-model.trim="phoneFormData.phoneNum"
               @confirm="authCodeInputFocus"
               @blur="phoneNumInputBlur" @focus="phoneNumInputFocus"
               :confirm-hold="true"
               placeholder="请填写手机号"
        />
        <u-icon v-if="phoneFormData.phoneNum" class="text-gray" name="close-circle" size="40"
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
      <text v-if="phoneFormData.phoneNum && !phoneFormData.phoneNumberRight" class="text-red">
        *请输入正确的手机号
      </text>
    </view>

    <view class="row-col-center">
      <view>验证码</view>

      <div class="flex-row ml-smm solid-bottom flex-1">
        <!--   自动获取焦点的话app平台会有问题，打开我的页面时会弹出键盘   :focus="true"-->
        <input class="flex-1 h35px" type="number" name="input" :focus="authCodeFocus" :maxlength="4"
               v-model.trim="phoneFormData.authCode"
               @focus="authCodeInputFocus"
               @blur="authCodeInputBlur"
               placeholder="请填写验证码"
        />
        <u-icon v-if="phoneFormData.authCode" class="text-gray" name="close-circle" size="40"
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
      <text v-if="!phoneFormData.authCodeRight&&phoneFormData.phoneNumberRight" class="text-red">
        *请输入正确的验证码
      </text>
    </view>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue, Watch } from 'vue-property-decorator'
import PhoneFormData from '@/model/login/PhoneFormData'
import CommonUtil from '@/utils/CommonUtil'
import Toast from '@/utils/Toast'
import UserAPI from '@/api/UserAPI'
import ConfigMap from '@/const/ConfigMap'
import { configStore } from '@/store'

@Component
export default class PhoneLoginForm extends Vue {
  @configStore.Getter(ConfigMap.authCodeIntervalKey) authCodeInterval: number
  @Prop() showPhoneView: boolean

  @Watch('showPhoneView')
  showPhoneViewWatch () {
    if (this.showPhoneView) {
      this.phoneNumInputFocus()
    } else {
      this.phoneNumInputBlur()
      this.authCodeInputBlur()
    }
  }

  phoneFormData = new PhoneFormData()
  countDown = 0

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
    return !this.phoneFormData.phoneNumberRight || Boolean(this.countDown)
  }

  phoneNumClear () {
    this.phoneFormData.phoneNum = ''
    CommonUtil.delayTime(100).then(() => {
      this.phoneNumInputFocus()
    })
  }

  authCodeClear () {
    this.phoneFormData.authCode = ''
    CommonUtil.delayTime(100).then(() => {
      this.authCodeInputFocus()
    })
  }

  sendCodeClick () {
    if (!this.phoneFormData.phoneNumberRight) {
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
    UserAPI.sendAuthCodeAPI(this.phoneFormData.phoneNum).then(() => {
      // 提示验证码发送成功
      Toast.toast('验证码发送成功')
    })
  }
}
</script>
