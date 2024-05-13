<template>
  <div class="row-all-center pd">
    <div class="w300">
      <el-form
          ref="loginForm"
          :model="viewService.loginData"
          :rules="viewService.loginRules"
          auto-complete="on"
          label-position="top"
      >
        <el-form-item label="手机号" prop="phoneNum" label-width="70px">
          <div class="flex-row w100p">
            <el-input
                class="flex-1"
                v-model="viewService.loginData.phoneNum"
                placeholder="请输入手机号"
                type="text"
                :maxlength="11"
                clearable
                @input="phoneNumberOnInput()"
                @blur="phoneNumberOnInput()"
            >
              <template #prepend>+86</template>
            </el-input>
          </div>
        </el-form-item>

        <el-form-item label="密码" prop="password" label-width="70px">
          <div class="flex-row w100p">
            <el-input
                ref="password"
                class="flex-1"
                show-password
                v-model="viewService.loginData.password"
                placeholder="请输入密码"
                type="password"
                :maxlength="16"
                @input="passwordInput"
                clearable
                @keyup.enter.native="passwordEntry"
            >
            </el-input>
          </div>
        </el-form-item>
        <!--          <el-form-item label="密码" prop="phoneNum" label-width="70px">
          <div class="flex-row">
            <el-input
              v-model="loginUser.phoneNum"
              placeholder="请输入密码"
              type="text"
              :maxlength="11"
              clearable
            />
          </div>
        </el-form-item>-->
        <el-form-item v-if="!viewService.loginData.phoneNumRegistered" label="验证码" prop="authCode"
                      label-width="70px">
          <div class="flex-row w100p">
            <el-input
                ref="authCode"
                v-model="viewService.loginData.authCode"
                class="flex-1"
                type="text"
                placeholder="请输入验证码"
                :maxlength="4"
                clearable
                @keyup.enter.native="viewService.handleLogin()"
            />
            <el-button
                class="flex-none ml w130"
                plain
                type="primary"
                :disabled="viewService.loginData.sendAuthCodeBtnDisabled"
                @click="viewService.sendCodeClick()"
            >
              {{
                viewService.loginData.countDownInner ? (viewService.loginData.countDown + '秒后可重试') : '发送验证码'
              }}
            </el-button>
          </div>
        </el-form-item>

        <div class="mt-30 row-wrap row-col-center">
          <el-checkbox v-model="viewService.contractChecked">
            <span class="color-black">我已阅读并同意</span>
          </el-checkbox>
          <a href="https://www.qingchiapp.com/agreement" class="color-blue">
            《用户协议》
          </a>
          <a href="https://www.qingchiapp.com/privacy" class="color-blue">
            《隐私政策》
          </a>
          <a href="https://www.qingchiapp.com/childProtect" class="color-blue">
            《儿童个人信息保护规则及监护人须知》
          </a>
        </div>

        <el-button
            :disabled="viewService.bindBtnDisabled"
            type="primary"
            class="w100p mt"
            @click.native.prevent="viewService.handleLogin()"
        >{{ viewService.bindBtnDisabled ? '登陆中' : viewService.loginBtnText }}
        </el-button>
      </el-form>
    </div>
  </div>
</template>

<script lang="ts">
import {Emit, Component, Vue, toNative} from 'vue-facing-decorator'
import SocialuniLoginViewService from "socialuni-user-sdk/src/logic/SocialuniLoginViewService";
import {getCurrentInstance, nextTick} from "vue";

@toNative
@Component({})
export default class SocialuniLoginView extends Vue {
  $refs: {
    loginForm: any
    password: any
    authCode: any
  }

  viewService: SocialuniLoginViewService = new SocialuniLoginViewService()

  created() {
    this.viewService.initService(this)
  }

  passwordEntry() {
    if (this.viewService.loginData.phoneNumRegistered) {
      this.viewService.handleLogin()
    } else {
      this.$refs.authCode.focus()
    }
  }

  passwordInput() {
    this.$nextTick(() => {
      this.$refs.loginForm.clearValidate()
    })
  }

  async phoneNumberOnInput() {
    this.viewService.phoneNumberOnInput()
    if (!this.viewService.loginData.phoneNumHasError) {
      nextTick(() => {
        // this.$refs.loginForm.clearValidate()
        setTimeout(() => {
          this.$refs.password.focus()
        }, 100)
      })
    }

  }
}
</script>
