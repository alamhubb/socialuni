<template>
  <div
    class="row-between shadow-bottom index-sm px"
  >
    <div class="flex-none row-col-center mr-40 bg-click" @click="toHome">
<!--      <img src="@/assets/img/logo.jpg" class="h40" alt="logo">-->
<!--      <div class="font-19 ml-3 mt-1 font-bold color-social">社交软件联盟</div>-->
    </div>

    <div class="flex-1 row-end">
      <div class="flex-none row-col-center mr">
        <a href="https://socialuni.cn" target="_blank" class="mr-sm md:mr bg-click">
          <div class="row-all-center">官网文档</div>
        </a>
        <el-divider direction="vertical" class="mr" />
        <a href="https://socialuni.cn/demo" target="_blank" class="mr-sm md:mr bg-click">
          <div class="row-all-center">demo演示</div>
        </a>
        <el-divider direction="vertical" class="mr" />
        <div class="row-col-center">
          <a href="https://gitee.com/socialuni/socialuni" target="_blank" class="mr-sm md:mr">
            <div class="row-all-center"><img src="@/assets/imgs/giteelogo.png" class="use-click size31"/></div>
          </a>
          <a href="https://github.com/social-uni/socialuni" target="_blank" class="md:mr-sm">
            <div class="row-all-center"><i class="mdi mdi-github font-36 use-click color-black"/></div>
          </a>
        </div>
      </div>
      <div class="flex-1 row-end-center">
        <div v-if="user" class="row-col-center">
          <el-tag class="mr-10" type="warning" effect="dark">{{ user.phoneNum }}</el-tag>
          <el-dropdown>
            <div>
              <el-avatar shape="square" src="https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png" />
              <i class="el-icon-caret-bottom el-icon--right" />
            </div>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>{{
                user ? user.username : '未登录'
              }}
              </el-dropdown-item>
              <el-dropdown-item divided @click.native="longinOut">退出登陆</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>

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
import DevAccountRO from '@/model/base/DevAccountRO'
import { Component, Vue } from 'vue-facing-decorator'
import {socialuniUserModule} from "@socialuni/socialuni-user-sdk/src/store/SocialuniUserModule";


@Component({
  components: { NavBreadcrumb, NavMenu }
})
export default class NavBar extends Vue {

  get user(){
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
}
</script>
