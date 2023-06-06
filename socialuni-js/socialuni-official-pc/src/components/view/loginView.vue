<template>
    <div class="row-all-center pd">
        <div class="w300">
            <el-form
                    ref="loginForm"
                    :model="loginUser"
                    :rules="loginRules"
                    auto-complete="on"
                    label-position="top"
            >
                <el-form-item label="手机号" prop="phoneNum" label-width="70px">
                    <div class="flex-row w100p">
                        <el-input
                                class="flex-1"
                                v-model="loginUser.phoneNum"
                                placeholder="请输入手机号"
                                type="text"
                                :maxlength="11"
                                clearable
                        >
                            <template #prepend>+86</template>
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
                <el-form-item label="验证码" prop="authCode" label-width="70px">
                    <div class="flex-row w100p">
                        <el-input
                                ref="authCode"
                                v-model="loginUser.authCode"
                                class="flex-1"
                                type="text"
                                placeholder="请输入验证码"
                                :maxlength="4"
                                clearable
                                @keyup.enter.native="handleLogin"
                        />
                        <el-button
                                class="flex-none ml w130"
                                plain
                                type="primary"
                                :disabled="sendAuthCodeBtnDisabled"
                                @click="sendCodeClick"
                        >
                            {{ countDown ? (authCodeInterval + 1 - countDown) + '秒后可重试' : '发送验证码' }}
                        </el-button>
                    </div>
                </el-form-item>

                <div class="mt-30">
                    <el-checkbox v-model="contractChecked" class="mb-5">
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
                        :disabled="bindBtnDisabled"
                        type="primary"
                        class="w100p mt"
                        @click.native.prevent="handleLogin"
                >{{ bindBtnDisabled ? '登陆中' : '注 册 并 登 录' }}
                </el-button>
            </el-form>
        </div>
    </div>
</template>

<script lang="ts">
import {Emit, Options, Vue} from 'vue-property-decorator'
import ElInput from "element-plus/es/components/input";
import NumberUtil from "socialuni-util/src/util/NumberUtil";
import ToastUtil from "socialuni-util/src/util/ToastUtil";
import PhoneAPI from "socialuni-user-api/src/api/PhoneAPI";
import SocialuniLoginService from "@/logic/service/SocialuniLoginService";
import SocialPhoneNumLoginQO from "socialuni-api-base/src/model/phone/SocialPhoneNumLoginQO";

@Options({})
export default class LoginView extends Vue {
    $refs: {
        authCode: ElInput;
    }

    secretKey = ''

    loginUser: SocialPhoneNumLoginQO = new SocialPhoneNumLoginQO()

    authCodeInterval = 30

    // 同意协议
    contractChecked = true

    loginRules = {
        phoneNum: [{required: true, trigger: 'blur', message: '请输入正确的手机号', min: 11, max: 11}],
        authCode: [{required: true, trigger: 'blur', message: '请输入正确的验证码', min: 4, max: 4}]
    }

    countDown = 0

    bindBtnDisabled = false

    get sendAuthCodeBtnDisabled() {
        return !this.phoneNumberRight || Boolean(this.countDown)
    }

    get authCodeRight() {
        return this.loginUser.authCode && this.loginUser.authCode.length === 4 && NumberUtil.isNumber(this.loginUser.authCode)
    }

    get phoneNumberRight() {
        return this.loginUser.phoneNum && this.loginUser.phoneNum.length === 11 && NumberUtil.isNumber(this.loginUser.phoneNum)
    }

    // 手机号登陆和手机号绑定
    handleLogin() {
        if (this.bindBtnDisabled) {
            return ToastUtil.warning('正在登陆中，请勿重复点击')
        }
        // 再次校验
        if (!this.phoneNumberRight) {
            return ToastUtil.error('请输入正确的手机号')
        }
        if (!this.authCodeRight) {
            return ToastUtil.error('请输入正确的验证码')
        }
        if (!this.contractChecked) {
            return ToastUtil.warning('请仔细阅读用户协议、隐私政策等内容后勾选同意')
        }
        this.bindBtnDisabled = true
        SocialuniLoginService.phoneLogin(this.loginUser.phoneNum, this.loginUser.authCode).then(() => {
            this.loginSuccess()
        }).finally(() => {
            this.bindBtnDisabled = false
        })
    }

    @Emit()
    loginSuccess() {
        return
    }


    sendCodeClick() {
        if (!this.loginUser.phoneNum) {
            return ToastUtil.error('请输入正确的手机号')
        }
        if (this.countDown) {
            return ToastUtil.error('验证码发送频繁，请等待')
        }

        this.loginUser.authCode = ''
        this.$refs.authCode.focus()

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
        PhoneAPI.sendAuthCodeAPI(this.loginUser.phoneNum).then(() => {
            // 提示验证码发送成功
            ToastUtil.success('验证码发送成功')
        })
    }
}
</script>
