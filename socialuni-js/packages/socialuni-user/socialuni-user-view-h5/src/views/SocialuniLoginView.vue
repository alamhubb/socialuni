<template>
    <div class="row-all-center pd">
        <div class="w300">
            <el-form
                    ref="loginForm"
                    :model="viewService.loginUser"
                    :rules="viewService.loginRules"
                    auto-complete="on"
                    label-position="top"
            >
                <el-form-item label="手机号" prop="phoneNum" label-width="70px">
                    <div class="flex-row w100p">
                        <el-input
                                class="flex-1"
                                v-model="viewService.loginUser.phoneNum"
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
                                v-model="viewService.loginUser.authCode"
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
                                :disabled="viewService.sendAuthCodeBtnDisabled"
                                @click="viewService.sendCodeClick()"
                        >
                            {{
                            viewService.countDown ? (viewService.authCodeInterval + 1 - viewService.countDown) + '秒后可重试' : '发送验证码'
                            }}
                        </el-button>
                    </div>
                </el-form-item>

                <div class="mt-30">
                    <el-checkbox v-model="viewService.contractChecked" class="mb-5">
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
                >{{ viewService.bindBtnDisabled ? '登陆中' : '注 册 并 登 录' }}
                </el-button>
            </el-form>
        </div>
    </div>
</template>

<script lang="ts">
import {Emit, Options, Vue} from 'vue-property-decorator'
import SocialuniLoginViewService from "socialuni-user-sdk/src/logic/SocialuniLoginViewService";

@Options({})
export default class SocialuniLoginView extends Vue {
    viewService: SocialuniLoginViewService = new SocialuniLoginViewService()
}
</script>
