<template>
  <div
      class="row-between box-shadow px py-mn w100p bb-shadow shadow-b"
  >
    <router-link to="/" class="flex-none row-col-center mr-40 bg-click">
      <img src="@/imgs/logo.jpg" class="h50" alt="logo">
<!--      <div class="font-19 ml-3 mt-1 font-bold color-social">社交软件联盟</div>-->
    </router-link>

    <div class="flex-1 row-end">
      <div class="flex-none row-col-center mr">
        <div v-for="route in constantRouters">
          <router-link v-if="!route.meta.hidden" :to="route.path">
            {{route.meta.title}}
          </router-link>
        </div>
        <el-divider direction="vertical" class="mr"/>
        <a href="https://gitee.com/socialuni/socialuni" target="_blank">
          <div class="row-all-center"><img src="@/imgs/giteelogo.png" class="bg-click size30 mr-sm"/></div>
        </a>
        <a href="https://github.com/social-uni/socialuni" target="_blank">
          <i class="mdi mdi-github font-36 bg-click"/>
        </a>

      </div>
<!--      <div class="row-end-center">
        <div v-if="user" class="row-col-center">
          <el-tag class="mr-10" type="warning" effect="dark">{{ user.phoneNum }}</el-tag>
          <el-dropdown>
            <div>
              <el-avatar shape="square" src="https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png"/>
              <i class="el-icon-caret-bottom el-icon&#45;&#45;right"/>
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

<script setup>
import {constantRouters} from "@/router";

function longinOut() {
  // userModule.userLoginOut()
}

function toDoc() {
  window.open('https://shejiao.qingchiapp.com')
}

function toGitee() {
  window.open('https://gitee.com/socialuni/socialuni')
}

function toHome() {
  this.$router.push('/')
}
</script>
