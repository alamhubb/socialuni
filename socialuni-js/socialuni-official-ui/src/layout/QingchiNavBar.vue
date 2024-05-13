<template>
  <div
      class="row-between box-shadow px-20 md:px-30 py-3 w100% shadow-b h55"
  >
    <router-link to="/qingchi" class="flex-none row-col-center use-click">
      <div class="row-col-center">
        <img src="https://cdxapp-1257733245.file.myqcloud.com/qingchi/home/full.jpg!thumbnail" class="h40" alt="logo">
        <div class="font-22 ml-5 mb-2 font-bold color-main">清池app</div>
      </div>
    </router-link>

    <div class="flex-1 row-end">
      <div class="flex-none row-col-center">
        <router-link to="/" active-class="color-theme"
                     class="hover-color-theme mx-xs md:mx-smm font-bold color-content">
          社交联盟
        </router-link>
      </div>
<!--      <div class="flex-none row-col-center">
        <div v-for="route in qingchiRouters">
          <router-link v-if="!route.meta.hidden" :to="route.path" active-class="color-theme"
                       class="hover-color-theme mx-xs md:mx-smm font-bold color-content">
            {{ route.meta.title }}
          </router-link>
        </div>
      </div>-->
    </div>
  </div>
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
import UserStore from "@/store/UserStore.js";
import AlertUtil from "@/utils/AlertUtil.js";
import TokenUtil from "@/utils/TokenUtil.js";
import ToastUtil from "@/utils/ToastUtil.js";
import {computed} from "vue";
import {qingchiRouters} from "@/router/index.ts";

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
