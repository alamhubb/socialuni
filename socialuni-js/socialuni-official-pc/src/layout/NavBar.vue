<template>
    <div class="row-between-center shadow-bottom index-sm px-smm h50 bg-white flex-none">
        <div class="flex-none row-col-center mr-40 bg-click" @click="toHome">
            <!--      <img src="@/assets/img/logo.jpg" class="h40" alt="logo">-->
            <div class="font-19 font-bold">演示系统</div>
        </div>

        <div class="row-end-center">
            <div class="flex-none row-col-center mr">
                <a href="https://socialuni.cn" target="_blank" class="mr-sm md:mr bg-click">
                    <div class="row-all-center">官网文档</div>
                </a>
                <el-divider direction="vertical" class="mr"/>
                <a href="https://socialuni.cn/demo" target="_blank" class="mr-sm md:mr bg-click">
                    <div class="row-all-center">demo演示</div>
                </a>
                <el-divider direction="vertical" class="mr"/>
                <div class="row-col-center">
                    <a href="https://gitee.com/socialuni/socialuni" target="_blank" class="mr-sm md:mr">
                        <div class="row-all-center"><img src="@/assets/imgs/giteelogo.png" class="use-click size31"/>
                        </div>
                    </a>
                    <a href="https://github.com/social-uni/socialuni" target="_blank" class="md:mr-sm">
                        <div class="row-all-center"><i class="mdi mdi-github font-36 use-click color-black"/></div>
                    </a>
                </div>
            </div>
            <div class="row-col-center">
                <el-avatar v-if="!mineUser" class="use-click" @click="toLogin">登录</el-avatar>
                <div v-else class="row-col-center">
                    <el-tag class="mr-10" type="warning" effect="dark">{{ mineUser.nickname }}
                    </el-tag>
                    <el-avatar shape="square" :src="mineUser.avatar"/>

                </div>
            </div>
        </div>

        <el-dropdown v-if="mineUser" trigger="click">
            <el-icon :size="20" class="ml">
                <Tools/>
            </el-icon>
            <template #dropdown>
                <el-dropdown-menu>
                    <el-dropdown-item @click.native="editUserInfo">编辑信息</el-dropdown-item>
                    <el-dropdown-item divided @click.native="loginOut">退出登陆</el-dropdown-item>
                </el-dropdown-menu>
            </template>
        </el-dropdown>

        <s-dialog ref="loginDialog" title="登录" width="400px" no-show-footer>
            <login-view @login-success="loginSuccess"></login-view>
        </s-dialog>


        <socialuni-user-edit-dialog ref="userEditDialog"></socialuni-user-edit-dialog>
    </div>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import {socialuniUserModule} from "socialuni-user/src/store/SocialuniUserModule";
import {ArrowDown, Tools} from "@element-plus/icons-vue";
import WebsocketUtil from "socialuni-api-base/src/websocket/WebsocketUtil";
import ToastUtil from "socialuni-util/src/util/ToastUtil";
import SDialog from "@/components/socialuni/SDialog.vue";
import LoginView from "@/components/view/loginView.vue";
import SocialuniUserEventConst from "socialuni-user/src/constant/SocialuniUserEventConst";
import SocialuniEventUtil from "socialuni/src/util/SocialuniEventUtil";
import UserEditView from "@/views/user/UserEditView.vue";
import UniUtil from "socialuni-util/src/util/UniUtil";
import SocialuniMineUserAPI from "socialuni-user-api/src/api/SocialuniMineUserAPI";
import SocialuniUserEditDialog from "@/views/user/SocialuniUserEditDialog.vue";

@Options({
    components: {SocialuniUserEditDialog, UserEditView, Tools, LoginView, SDialog, ArrowDown}
})
export default class NavBar extends Vue {
    $refs: {
        loginDialog: SDialog
        userEditDialog: SocialuniUserEditDialog
    }

    created() {
        SocialuniEventUtil.on(SocialuniUserEventConst.toLogin, () => {
            console.log('接收到了')
            this.toLogin()
        })
    }

    toHome() {
        this.$router.push('/')
    }

    toLogin() {
        console.log(this.$refs)
        console.log(this.$refs.loginDialog)
        this.$refs.loginDialog.open()
        // this.$router.push('/login')
    }

    loginSuccess() {
        ToastUtil.success('登录成功')
        this.$refs.loginDialog.close()
    }

    editUserInfo() {
        this.$refs.userEditDialog.open()
    }


    loginOut() {
        ToastUtil.success('退出登录成功')
        socialuniUserModule.removeUserAndToken()
        WebsocketUtil.websocketConnect(false)
    }

    get mineUser() {
        console.log(socialuniUserModule.mineUser)
        return socialuniUserModule.mineUser
    }
}
</script>
