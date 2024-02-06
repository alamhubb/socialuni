<template>
  <div v-if="value">
    <!--      手机号登录form-->
    <view class="row-col-center">
      <view>手机号</view>
      <div class="flex-row flex-1 ml-smm solid-bottom">
        <!--   自动获取焦点的话app平台会有问题，打开我的页面时会弹出键盘   :focus="true"-->
        <input class="flex-1 h35px" type="number" name="input" :focus="loginData.phoneNumFocus" :maxlength="11"
               v-model.trim="loginData.phoneNum"
               @confirm="loginData.authCodeInputFocus()"
               @blur="loginData.phoneNumInputBlur()" @focus="loginData.phoneNumInputFocus()"
               @input="input"
               :confirm-hold="true"
               placeholder="请填写手机号"
        />
        <q-icon v-if="loginData.phoneNum" class="text-gray" icon="close-circle" size="20"
                @click="phoneNumClear"></q-icon>
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
    <view class="mt-xs h30 row-col-center">
      <text v-if="loginData.phoneNumError" class="color-red">
        *请输入正确的手机号
      </text>
    </view>

    <view class="row-col-center">
      <view>验证码</view>

      <div class="flex-row ml-smm solid-bottom flex-1">
        <!--   自动获取焦点的话app平台会有问题，打开我的页面时会弹出键盘   :focus="true"-->
        <input class="flex-1 h35px" type="number" name="input" :focus="loginData.authCodeFocus" :maxlength="4"
               v-model.trim="loginData.authCode"
               @focus="loginData.authCodeInputFocus()"
               @blur="loginData.authCodeInputBlur()"
               @input="input"
               placeholder="请填写验证码"
        />
        <q-icon v-if="loginData.authCode" class="text-gray" icon="close-circle" size="20"
                @click="authCodeClear"></q-icon>
        <!--                <q-icon v-if="phoneNum" class="text-gray mr-lg" name="close-circle" size="40"
                                @touchend.native.prevent="phoneNumClear"></q-icon>-->
      </div>

      <view @click="sendCodeClick" class="ml-smm">
        <button type="primary" class='cu-btn bg-green' :disabled="loginData.sendAuthCodeBtnDisabled">
          {{ !loginData.countDown ? loginData.countDown : '发送验证码' }}
        </button>
      </view>
    </view>
    <view class="mt-xs h30 row-col-center">
      <text v-if="loginData.authCodeError" class="color-red">
        *请输入正确的验证码
      </text>
    </view>
  </div>
</template>

<script lang="ts">
import {Component, Emit, Model, Prop, Vue, Watch} from 'vue-facing-decorator'
import PhoneNumFormData from "./PhoneNumFormData";
import QIcon from "qing-ui-uni/src/components/QIcon/QIcon.vue";
import CommonUtil from "qing-util/src/util/CommonUtil";
import PhoneAPI from "socialuni-user-api/src/api/PhoneAPI";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import SocialuniLoginDataVO from "socialuni-user-sdk/src/model/SocialuniLoginDataVO";

@Component({
  components: {QIcon}
})
export default class PhoneLoginForm extends Vue {
  @Prop() show: boolean
  @Model('modelValue') readonly value!: PhoneNumFormData

  loginData = new SocialuniLoginDataVO()

  @Emit('update:modelValue')
  input() {
    return this.loginData
  }

  created() {
    this.loginData = new SocialuniLoginDataVO(this.value.phoneNum)
  }

  @Watch('show')
  showPhoneViewWatch() {
    if (this.show) {
      this.loginData.phoneNumInputFocus()
    } else {
      this.loginData.phoneNumInputBlur()
      this.loginData.authCodeInputBlur()
    }
  }

  phoneNumClear() {
    this.loginData.phoneNumClear()
    this.input()
  }

  authCodeClear() {
    this.loginData.authCodeClear()
    this.input()
  }

  sendCodeClick() {
    this.loginData.sendAuthCodeCheck()

    this.loginData.authCodeClear()

    this.loginData.computedCountDown()
    // 如果怕太频繁，就显示相同手机号每天只能发送几次，一小时内只能5次
    PhoneAPI.sendAuthCodeAPI(this.loginData.phoneNum).then(() => {
      // 提示验证码发送成功
      QingAppUtil.ToastUtil.success('验证码发送成功')
    })
  }
}
</script>
