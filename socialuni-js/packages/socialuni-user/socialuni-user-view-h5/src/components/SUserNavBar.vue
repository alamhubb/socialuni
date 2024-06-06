<template>
  <div class="row-between-center shadow-bottom index-sm px-smm h50 bg-white flex-none position-relative">
    <slot name="title">
      <div class="flex-none row-end-center mr-40 use-click" @click="toHome">
        <img v-if="logo" :src="logo" class="h44 mr-sm" alt="logo">
        <div class="font-22 font-bold">{{ title }}</div>
      </div>
    </slot>

    <slot>
      <div class="flex-1 overflow-hidden h100p">
      </div>
    </slot>

    <div class="flex-1 row-end-center">

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
        <el-avatar size="default" v-if="!mineUser" class="use-click" @click="toLogin">登录</el-avatar>
        <div v-else class="row-col-center">
          <el-tag class="mr-10" type="warning" effect="dark">{{ mineUser.nickname }}
          </el-tag>
          <el-avatar shape="square" :src="mineUser.avatar"/>
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
    </div>


  </div>

  <q-dialog ref="loginDialog" title="登录" width="350px" no-show-footer>
    <socialuni-login-view class="pd" @login-success="loginSuccess"></socialuni-login-view>
  </q-dialog>


  <socialuni-user-edit-dialog ref="userEditDialog"></socialuni-user-edit-dialog>
</template>

<script lang="ts">
import {Component, Prop, Vue, toNative} from 'vue-facing-decorator'
import SocialuniUserEventConst from "socialuni-user-sdk/src/constant/SocialuniUserEventConst";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import CosService from "socialuni-app-sdk/src/util/CosService";
import TencentCosAPI from "socialuni-app-api/src/api/TencentCosAPI";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import SocialuniMineUserAPI from "socialuni-user-api/src/api/SocialuniMineUserAPI";
import ImgAddQO from "socialuni-api-base/src/model/user/ImgAddQO";
import UUIDUtil from "qing-util/src/util/UUIDUtil";
import ImgUtil from "qing-util/src/util/ImgUtil";
import {ArrowDown, Tools} from "@element-plus/icons-vue";
import CommonEventUtil from "qingjs/src/util/CommonEventUtil";
import QDialog from "qing-ui-h5/src/components/QDialog.vue";
import SocialuniLoginView from "../views/SocialuniLoginView.vue";
import SocialuniUserEditDialog from "../views/SocialuniUserEditDialog.vue";
import SocialuniUserService from "socialuni-user-sdk/src/logic/SocialuniUserService";
import SocialuniUserEventOn from "socialuni-user-sdk/src/event/SocialuniUserEventOn";
import WebsocketUtil from "socialuni-api-base/src/websocket/WebsocketUtil";
import SocialuniUserEventEmit from "socialuni-user-sdk/src/event/SocialuniUserEventEmit";

@toNative
@Component({
  components: {SocialuniUserEditDialog, Tools, SocialuniLoginView, QDialog, ArrowDown}
})
export default class SUserNavBar extends Vue {
  $refs: {
    loginDialog: QDialog
    userEditDialog: SocialuniUserEditDialog
  }

  @Prop() title: string
  @Prop() logo: string


  dialogVisible = false

  toHome() {
    this.$router.push('/')
  }

  toLogin() {
    SocialuniUserEventEmit.toLogin()
  }

  loginSuccess() {
    QingAppUtil.ToastUtil.success('登录成功')
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
      const imgFiles: DomFile[] = await QingAppUtil.NativeUtil.chooseImage(1)
      QingAppUtil.NativeUtil.showLoading('上传中')
      const imgFile: DomFile = imgFiles[0]
      imgFile.src = cosAuthRO.uploadImgPath + 'img/' + imgFile.src
      const res = await Promise.all([TencentCosAPI.uploadFileAPI(imgFile, cosAuthRO), SocialuniMineUserAPI.addUserAvatarImgAPI(new ImgAddQO(imgFile))])
      socialuniUserModule.setUser(res[1].data)
    } catch (e) {
      console.error(e)
    } finally {
      QingAppUtil.NativeUtil.hideLoading()
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
      QingAppUtil.NativeUtil.hideLoading()
    }
  }


  loginOut() {
    SocialuniUserService.loginOut()
  }

  get mineUser() {
    return socialuniUserModule.mineUser
  }
}
</script>
