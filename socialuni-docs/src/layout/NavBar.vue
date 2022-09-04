<template>
  <div
      class="row-between box-shadow px-30 py-3 w100% shadow-b"
  >
    <router-link to="/" class="flex-none row-col-center mr-40 use-click">
      <img src="@/imgs/logo.jpg" class="h50" alt="logo">
      <div class="font-22 ml-3 mb-2 font-bold color-main">社交联盟</div>
    </router-link>

    <div class="flex-1 row-end">
      <div class="flex-none row-col-center mr">
        <div v-for="route in constantRouters">
          <router-link v-if="!route.meta.hidden" :to="route.path" active-class="color-theme" class="hover-color-theme px font-bold color-content">
            {{route.meta.title}}
          </router-link>
        </div>
        <el-divider direction="vertical" class="mr"/>
        <a href="https://gitee.com/socialuni/socialuni" target="_blank">
          <div class="row-all-center"><img src="@/imgs/giteelogo.png" class="use-click size30 mr"/></div>
        </a>
        <a href="https://github.com/social-uni/socialuni" target="_blank">
          <i class="mdi mdi-github font-36 use-click color-black"/>
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
        <div class="el-dropdown-link color-white pointer row-end-center h100% pr-md">
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
</script>
