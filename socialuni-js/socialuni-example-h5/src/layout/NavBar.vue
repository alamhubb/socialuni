<template>
    <div class="row-between-center shadow-bottom index-sm px-smm h50 bg-white flex-none">
        <div class="flex-none row-col-center mr-40 bg-click" @click="toHome">
            <!--      <img src="@/assets/img/logo.jpg" class="h40" alt="logo">-->
            <div class="font-19 font-bold">演示系统</div>
        </div>

        <div class="row-end-center">
            <!--            <div class="flex-none row-col-center mr">
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
                        </div>-->
            <div class="row-col-center">
                <!--                <input id="fileSelector" type="file" @change="uploadData"/>-->
<!--                <el-button @click="uploadUserAvatarImg">test</el-button>-->
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
            <socialuni-login-view-h5 @login-success="loginSuccess"></socialuni-login-view-h5>
        </s-dialog>


        <socialuni-user-edit-dialog ref="userEditDialog"></socialuni-user-edit-dialog>
    </div>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import SDialog from "@/components/socialuni/SDialog.vue";
import LoginView from "@/components/view/loginView.vue";
import SocialuniUserEditDialog from "@/views/user/SocialuniUserEditDialog.vue";
import CommonEventUtil from "../../../packages/uniapp-api/src/util/CommonEventUtil";
import SocialuniUserEventConst from "socialuni-user-sdk/src/constant/SocialuniUserEventConst";
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";
import CosService from "socialuni-app-sdk/src/util/CosService";
import type DomFile from "socialuni-app-sdk/src/model/DomFile";
import TencentCosAPI from "socialuni-app-api/src/api/TencentCosAPI";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import SocialuniMineUserAPI from "socialuni-user-api/src/api/SocialuniMineUserAPI";
import ImgAddQO from "socialuni-api-base/src/model/user/ImgAddQO";
import UUIDUtil from "socialuni-util/src/util/UUIDUtil";
import ImgUtil from "socialuni-util/src/util/ImgUtil";
import WebsocketUtil from "socialuni-api-base/src/websocket/WebsocketUtil";
import {ArrowDown, Tools} from "@element-plus/icons-vue";
import SocialuniLoginView from "socialuni-user-view-h5/src/views/SocialuniLoginView.vue";

@Options({
    components: {SocialuniUserEditDialog, Tools, SocialuniLoginView, SDialog, ArrowDown}
})
export default class NavBar extends Vue {
    $refs: {
        loginDialog: SDialog
        userEditDialog: SocialuniUserEditDialog
    }

    created() {
        CommonEventUtil.on(SocialuniUserEventConst.toLogin, () => {
            console.log('接收到了')
            this.toLogin()
        })
        console.log(3232323)
    }

    mounted() {

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
        SocialuniAppUtil.ToastUtil.success('登录成功')
        this.$refs.loginDialog.close()
    }

    editUserInfo() {
        this.$refs.userEditDialog.open()
    }

    async uploadUserAvatarImg() {
        console.log(123123)
        try {
            const cosAuthRO = await CosService.getCosAuthRO()

            console.log(123123)
            console.log(cosAuthRO.uploadImgPath)
            console.log(cosAuthRO)
            console.log(456465)
            console.log(cosAuthRO)
            const imgFiles: DomFile[] = await SocialuniAppUtil.UniUtil.chooseImage(1)
            SocialuniAppUtil.UniUtil.showLoading('上传中')
            const imgFile: DomFile = imgFiles[0]
            imgFile.src = cosAuthRO.uploadImgPath + 'img/' + imgFile.src
            const res = await Promise.all([TencentCosAPI.uploadFileAPI(imgFile, cosAuthRO), SocialuniMineUserAPI.addUserAvatarImgAPI(new ImgAddQO(imgFile))])
            socialuniUserModule.setUser(res[1].data)
        } catch (e) {
            console.error(e)
        } finally {
            SocialuniAppUtil.UniUtil.hideLoading()
        }
    }

    async uploadData(e: any) {

        const file = e.target.files[0]
        console.log(e)
        console.log(e.target)
        console.log(e.target.files[0])
        try {
            const cosAuthRO = await CosService.getCosAuthRO()
            console.log(cosAuthRO)
            const imgKey = UUIDUtil.getUUID() + ImgUtil.getFileSuffixName(file.name)
            const res = await Promise.all([TencentCosAPI.uploadFileAPI(imgKey, file, cosAuthRO), SocialuniMineUserAPI.addUserAvatarImgAPI(new ImgAddQO(file))])
            socialuniUserModule.setUser(res[1].data)
        } catch (e) {
            console.error(e)
        } finally {
            SocialuniAppUtil.UniUtil.hideLoading()
        }
    }


    loginOut() {
        SocialuniAppUtil.ToastUtil.success('退出登录成功')
        socialuniUserModule.removeUserAndToken()
        WebsocketUtil.websocketConnect(false)
    }

    get mineUser() {
        console.log(socialuniUserModule.mineUser)
        return socialuniUserModule.mineUser
    }
}
</script>
