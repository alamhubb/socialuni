<template>
  <div class="h100p row-all-center">
    <div class="pb-5p">
      <div class="row-center">
        <!-- <div class="system_title_2">数据创建像搭积木一样简单</div> -->
        <!--        <h6 class="system_desc">缩短了您与数据的距离,真正实现了数据的触手可及。同时也提供了命令行工具，方便您将算法嵌入到自己的工程中。</h6>-->
        <div class="font-20 font-bold color-social">欢迎加入 - {{ appName }}</div>
      </div>
      <div class="mt w300">
        <el-form
            ref="loginForm"
            :model="loginUser"
            :rules="loginRules"
            auto-complete="on"
            label-position="top"
        >

          <el-form-item label="秘钥登录" label-width="70px">
            <el-input
                v-model="secretKey"
                placeholder="请输入秘钥登录"
                type="text"
                clearable
            />
          </el-form-item>
          <el-form-item>
            <el-button
                class="flex-none w100p"
                type="primary"
                :disabled="bindBtnDisabled"
                @click="secretKeyLogin"
            >
              秘钥登录
            </el-button>
          </el-form-item>
        </el-form>
        <socialuni-login-view @login-success="loginSuccess"></socialuni-login-view>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import ToastUtil from '@/utils/ToastUtil'
import NumberUtil from '@/utils/NumberUtil'
import SocialPhoneNumQO from "socialuni-admin-api/src/model/base/SocialPhoneNumQO";
import PhoneAPI from "socialuni-user-api/src/api/PhoneAPI";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import SocialuniLoginService from "socialuni-user-sdk/src/logic/SocialuniLoginService";
import SocialuniLoginView from "socialuni-user-view-h5/src/views/SocialuniLoginView.vue";
import LoginAPI from "socialuni-admin-api/src/api/LoginAPI";
import SocialPhoneNumLoginQO from "socialuni-api-base/src/model/phone/SocialPhoneNumLoginQO";
import SocialuniUserService from "socialuni-user-sdk/src/logic/SocialuniUserService";


@Component({components: {SocialuniLoginView}})
class AdminLoginPage extends Vue {
  $refs: {
    authCode: ElInput;
  }

  created() {
    console.log(123)
  }

  appName = socialuniConfigModule.appName

  secretKey = ''

  loginUser: SocialPhoneNumQO = new SocialPhoneNumQO()

  authCodeInterval = 30

  // 同意协议
  contractChecked = true

  loginRules = {
    phoneNum: [{required: true, trigger: 'blur', message: '请输入正确的手机号', min: 11, max: 11}],
    authCode: [{required: true, trigger: 'blur', message: '请输入正确的验证码', min: 4, max: 4}]
  }

  countDown = 0

  bindBtnDisabled = false

  secretKeyLogin() {
    if (this.bindBtnDisabled) {
      return ToastUtil.warning('正在登陆中，请勿重复点击')
    }
    this.bindBtnDisabled = true
    this.userSecretKeyLogin(this.secretKey).then(() => {
      this.$router.push({path: '/'})
    }).finally(() => {
      this.bindBtnDisabled = false
    })
  }

  async userSecretKeyLogin(secretKey: string) {
    const {data} = await LoginAPI.secretKeyLoginAPI(secretKey)
    SocialuniUserService.loginSuccess(data)
  }

  loginSuccess() {
    console.log('触发了跳远')
    this.$router.push({path: '/'})
  }
}
export default toNative(AdminLoginPage)
</script>
