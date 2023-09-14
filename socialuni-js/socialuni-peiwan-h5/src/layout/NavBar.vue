<template>
  <div
      class="row-between shadow-bottom index-sm px color-white h50" style="background-color: #2b2f3a"
  >
    <div class="flex-none row-col-center mr-40 bg-click" @click="toHome">
      <!--      <img src="@/assets/img/logo.jpg" class="h40" alt="logo">-->
      <div class="font-19 ml-3 mt-1 font-bold color">北京同城陪玩</div>
<!--      <div class="font-19 ml-3 mt-1 font-bold color">清绿北京同城陪玩 - 纯绿色陪伴</div>-->
    </div>

    <div class="flex-1 row-end">
      <div class="flex-none row-col-center mr use-click" @click="openKefu">
        <i class="mdi mdi-qrcode mr-sm font-16"></i>
        客服微信：491369310

        <!--        <a href="https://socialuni.cn" target="_blank" class="mr-sm md:mr bg-click">
                  <div class="row-all-center color-white">官网文档</div>
                </a>
                <el-divider direction="vertical" class="mr" />
                <a href="https://socialuni.cn/demo" target="_blank" class="mr-sm md:mr bg-click">
                  <div class="row-all-center color-white">demo演示</div>
                </a>-->
        <!--        <el-divider direction="vertical" class="mr" />-->
        <!--        <div class="row-col-center">
                  <a href="https://gitee.com/socialuni/socialuni" target="_blank" class="mr-sm md:mr">
                    <div class="row-all-center"><img src="@/assets/imgs/giteelogo.png" class="use-click size31"/></div>
                  </a>
                  <a href="https://github.com/social-uni/socialuni" target="_blank" class="md:mr-sm">
                    <div class="row-all-center"><i class="mdi mdi-github font-36 use-click color-black"/></div>
                  </a>
                </div>-->
      </div>
      <!--      <div class="flex-1 row-end-center">
              <div v-if="user" class="row-col-center">
                <el-tag class="mr-10" type="warning" effect="dark">{{ user.phoneNum }}</el-tag>
                <el-dropdown>
                  <div>
                    <el-avatar shape="square" src="https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png" />
                    <i class="el-icon-caret-bottom el-icon&#45;&#45;right" />
                  </div>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item>{{
                      user ? user.nickname : '未登录'
                    }}
                    </el-dropdown-item>
                    <el-dropdown-item divided @click.native="longinOut">退出登陆</el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
              </div>
            </div>-->

      <!--        <el-dropdown trigger="click" class="flex-none">
        <div class="el-dropdown-link color-white pointer row-end-center h100p pr-md">
          {{ user ? user.username : '18600' }}
          <i class="el-icon-arrow-down el-icon&#45;&#45;right" />
        </div>
        <el-dropdown-menu slot="dropdown">

        </el-dropdown-menu>
      </el-dropdown>-->
    </div>
  </div>
  <!--    <nav-breadcrumb />-->
</template>

<script lang="ts">
import NavMenu from '@/layout/NavMenu.vue'
import NavBreadcrumb from '@/layout/NavBreadcrumb.vue'
import {Component, Vue} from 'vue-facing-decorator'
import {socialuniUserModule} from "@socialuni/socialuni-user-sdk/src/store/SocialuniUserModule";
import CommonEventUtil from "@socialuni/socialuni-native-util/src/util/CommonEventUtil";
import SocialuniUserEventConst from "@socialuni/socialuni-user-sdk/src/constant/SocialuniUserEventConst";
import {ElMessageBox} from "element-plus";


@Component({
  components: {NavBreadcrumb, NavMenu}
})
export default class NavBar extends Vue {

  created() {
    CommonEventUtil.on(SocialuniUserEventConst.toLogin, () => {
      console.log('接收到了')
      this.toLogin()
    })
  }

  mounted() {

  }

  toLogin() {
    this.$router.push('/login')
  }


  get user() {
    return socialuniUserModule.mineUser
  }

  longinOut() {
    userModule.userLoginOut()
  }

  toDoc() {
    window.open('https://socialuni.cn')
  }

  toGitee() {
    window.open('https://gitee.com/socialuni/socialuni')
  }

  toHome() {
    this.$router.push('/')
  }

  openKefu() {
    const date = new Date().toLocaleDateString()
    ElMessageBox.alert(
        `
          <div>

<div class="font-bold font-16 color-main">联系客服下单，客服微信：491369310</div>
          <div class="row-all-center">
          <img class="size300" src="https://cdxapp-1257733245.cos.ap-beijing.myqcloud.com/peiwan/wanwei/bjwanwei.jpg"/>
</div>
          </div>
          `,
        `${date}日更新`,
        {
          dangerouslyUseHTMLString: true,
        }
    )
  }

}
</script>
