<template>
    <div class="h100p row-all-center">
        <div class="pb-5p">
            <div class="row-center">
                <!-- <div class="system_title_2">数据创建像搭积木一样简单</div> -->
                <!--        <h6 class="system_desc">缩短了您与数据的距离,真正实现了数据的触手可及。同时也提供了命令行工具，方便您将算法嵌入到自己的工程中。</h6>-->
                <div class="font-20 font-bold color-social">欢迎加入 - 登录系统</div>
            </div>
            <div class="mt w300">
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
    </div>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import SocialuniLoginViewService from "socialuni-user-sdk/src/logic/SocialuniLoginViewService";

@Options({})
export default class LoginPage extends Vue {

    viewService: SocialuniLoginViewService = SocialuniLoginViewService()


}
</script>
