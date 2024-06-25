<template>
  <div>
    <!--      手机号登录form-->
    <div class="row-col-center">
      <div>手机号</div>
      <div class="flex-row flex-1 ml-smm solid-bottom">
        <!--   自动获取焦点的话app平台会有问题，打开我的页面时会弹出键盘   :focus="true"-->
        <input class="flex-1 h35px" type="number" name="input" :focus="phoneNumFocus"
               :maxlength="11"
               v-model.trim="viewService.loginData.phoneNum"
               @confirm="passwordInputFocus()"
               @input="phoneNumberOnInput"
               :confirm-hold="true"
               placeholder="请填写手机号"
        />
        <q-icon v-if="viewService.loginData.phoneNum" class="text-gray" icon="close-circle" size="20"
                @click="phoneNumClear"></q-icon>
      </div>

      <div class="ml-smm cu-capsule radius">
        <div class='cu-tag bg-blue'>
          +86
        </div>
        <div class="cu-tag line-blue">
          中国大陆
        </div>
      </div>
    </div>
    <div class="h30 row-col-center">
      <span v-if="viewService.loginData.phoneNumHasError" class="color-red">
        *请输入正确的手机号
      </span>
    </div>

    <div class="row-col-center">
      <div>密码</div>
      <div class="flex-row ml-smm solid-bottom flex-1 pl-smm">
        <!--   自动获取焦点的话app平台会有问题，打开我的页面时会弹出键盘   :focus="true"-->
        <input class="flex-1 h35px" type="password" name="input" :focus="passwordFocus"
               :maxlength="20"
               v-model.trim="viewService.loginData.password"
               @blur="passwordInputBlur()"
               placeholder="请填写密码"
        />
        <q-icon v-if="viewService.loginData.password" class="text-gray" icon="close-circle" size="20"
                @click="passwordClear"></q-icon>
      </div>
    </div>
    <div class="h30 row-col-center">
      <span v-if="!viewService.loginData.phoneNumHasError && viewService.loginData.passwordHasError" class="color-red">
        *{{ viewService.loginData.passwordHasError }}
      </span>
    </div>

    <div class="row-col-center" v-if="!viewService.loginData.phoneNumRegistered">
      <div>验证码</div>

      <div class="flex-row ml-smm solid-bottom flex-1">
        <!--   自动获取焦点的话app平台会有问题，打开我的页面时会弹出键盘   :focus="true"-->
        <input class="flex-1 h35px" type="number" name="input" :focus="authCodeFocus"
               :maxlength="4"
               v-model.trim="viewService.loginData.authCode"
               @blur="authCodeInputBlur()"
               placeholder="请填写验证码"
        />
        <q-icon v-if="viewService.loginData.authCode" class="text-gray" icon="close-circle" size="20"
                @click="authCodeClear"></q-icon>
        <!--                <q-icon v-if="phoneNum" class="text-gray mr-lg" name="close-circle" size="40"
                                @touchend.native.prevent="phoneNumClear"></q-icon>-->
      </div>

      <div @click="sendCodeClick" class="ml-smm">
        <button type="primary" class='cu-btn bg-green' :disabled="viewService.loginData.sendAuthCodeBtnDisabled">
          {{ viewService.loginData.countDownInner ? viewService.loginData.countDown : '发送验证码' }}
        </button>
      </div>
    </div>
    <div class="h30 row-col-center" v-if="!viewService.loginData.phoneNumRegistered">
      <span v-if="viewService.loginData.authCodeHasError" class="color-red">
        *请输入正确的验证码
      </span>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Emit, Model, Prop, Vue, Watch, toNative} from 'vue-facing-decorator'
import PhoneNumFormData from "./PhoneNumFormData";
import QIcon from "qingjs-ui/src/components/QIcon.vue";
import SocialuniLoginFormService from "socialuni-user-sdk/src/logic/SocialuniLoginFormService";
import CommonUtil from "qing-util/src/util/CommonUtil";
import {nextTick} from "vue";
import PhoneAPI from "socialuni-user-api/src/api/PhoneAPI";

@toNative
@Component({
  components: {QIcon}
})
export default class PhoneLoginForm extends Vue {
  @Prop() show: boolean
  // @Model('modelValue') readonly value!: PhoneNumFormData

  @Prop() viewService: SocialuniLoginFormService

  // @Emit('update:modelValue')
  // input() {
  //   return this.viewService.loginData
  // }


  async phoneNumberOnInput() {
    await this.viewService.phoneNumberOnInput()
    console.log(1111)
    if (!this.viewService.loginData.phoneNumHasError) {
      console.log(12222)
      nextTick(() => {
        // this.$refs.loginForm.clearValidate()
        setTimeout(() => {
          this.passwordInputFocus()
        }, 50)
      })
    }
  }

  @Watch('show')
  showPhoneViewWatch() {
    if (this.show) {
      this.phoneNumInputFocus()
    } else {
      this.phoneNumInputBlur()
      this.authCodeInputBlur()
    }
  }

  phoneNumInputBlur() {
    this.phoneNumFocus = false
  }


  authCodeInputBlur() {
    this.authCodeFocus = false
  }

  passwordInputBlur() {
    this.passwordFocus = false
  }

  passwordFocus = false
  authCodeFocus = false
  phoneNumFocus = false


  phoneNumClear() {
    this.viewService.loginData.phoneNumClear()
    this.phoneNumInputFocus()
  }

  passwordClear() {
    this.viewService.loginData.passwordClear()
    this.passwordInputFocus()
  }

  authCodeClear() {
    this.viewService.loginData.authCodeClear()
    this.authCodeInputFocus()
  }


  sendCodeClick() {
    this.viewService.sendCodeClick()
    this.authCodeClear()
  }

  phoneNumInputFocus() {
    this.phoneNumFocus = false
    CommonUtil.delayTime(0).then(() => {
      this.phoneNumFocus = true
      CommonUtil.delayTime(100).then(() => {
        this.phoneNumFocus = true
      })
    })
  }

  authCodeInputFocus() {
    this.authCodeFocus = true
    CommonUtil.delayTime(100).then(() => {
      this.authCodeFocus = true
    })
  }

  passwordInputFocus() {
    console.log(33333)
    this.passwordFocus = false
    CommonUtil.delayTime(0).then(() => {
      this.passwordFocus = true
      CommonUtil.delayTime(100).then(() => {
        this.passwordFocus = true
      })
    })
  }
}
</script>
