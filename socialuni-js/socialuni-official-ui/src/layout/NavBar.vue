<template>
  <div
      class="row-between box-shadow px-20 md:px-30 py-3 w100% shadow-b"
  >
    <router-link to="/" class="flex-none row-col-center use-click">
      <div class="row-col-center" v-if="MediaUtil.gtMd">
        <img src="../imgs/logo.jpg" class="h50" alt="logo">
        <div class="font-22 ml-3 mb-2 font-bold color-main">社交联盟</div>
      </div>
      <div v-else class="row-col-center">
        <img src="../imgs/socialunilogo.jpg" class="h40" alt="logo">
      </div>
    </router-link>

    <div class="flex-1 row-end">
      <div class="flex-none row-col-center">
        <div v-for="route in constantRouters">
          <router-link v-if="!route.meta.hidden" :to="route.path" active-class="color-theme"
                       class="hover-color-theme mx-xs md:mx-smm font-bold color-content">
            {{ route.meta.title }}
          </router-link>
        </div>
        <el-divider direction="vertical" class="mr-smm md:mr"/>
        <div class="row-col-center">
          <a href="https://console-docs.apipost.cn/preview/940ead4467df9d6d/0e3f3c24b231f818?target_id=b78d2016-0442-4a2f-9588-953a364bd21c" target="_blank" class="mr-sm md:mr">
            <div class="row-all-center">api接口文档</div>
          </a>
          <a href="https://admin.socialuni.cn" target="_blank" class="mr-sm md:mr">
            <div class="row-all-center">后台管理</div>
          </a>
          <a href="https://gitee.com/socialuni/socialuni" target="_blank" class="mr-sm md:mr">
            <div class="row-all-center"><img src="../imgs/giteelogo.png" class="use-click size31"/></div>
          </a>
          <a href="https://github.com/social-uni/socialuni" target="_blank" class="md:mr-sm">
            <div class="row-all-center"><i class="mdi mdi-github font-36 use-click color-black"/></div>
          </a>
        </div>
        <div v-if="user" class="row-col-center">
          <el-divider direction="vertical" class="mr-smm md:mr"/>
          <el-dropdown v-if="user" trigger="click">
            <div class="row-col-center">
              <el-avatar :size="34">{{ user.nickname }}</el-avatar>
              <el-icon class="el-icon--right">
                <CaretBottom/>
              </el-icon>
            </div>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click.native="logout">退出登陆</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </div>

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
import {
  ArrowDown,
  CaretBottom,
  Check,
  Delete,
  Edit,
  Message,
  Search,
  Star,
  StarFilled
} from '@element-plus/icons-vue'
import {constantRouters} from "@/router/index";
import UserStore from "@/store/UserStore";
import MediaUtil from "@/styles/jsStyle/MediaUtil";
import {computed} from "vue";

const user = computed(() => {
  return UserStore.user
})

async function logout() {
  await QingAppUtil.AlertUtil.confirm('是否确认退出')
  UserStore.clearUser()
  TokenUtil.remove()
  QingAppUtil.ToastUtil.success('退出成功')
}
</script>
