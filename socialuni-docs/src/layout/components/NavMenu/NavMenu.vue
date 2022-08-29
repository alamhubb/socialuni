<template>
  <div>
    <div v-for="menu in menus" :id="menu.meta.title">
      {{ menu.meta.title }}
    </div>
  </div>
</template>

<script setup lang="ts">
import type {Ref} from 'vue'
import {computed, ref} from "vue";
import {useRoute} from "vue-router";
import {constantRouters} from "@/router";
import type {RouteRecordRaw} from "vue-router";

const route = useRoute()

//获取父级名称
const curRouteParentName = route.path.split('/').filter(item => !!item)[0]
console.log(curRouteParentName)
const parentRoute: RouteRecordRaw = constantRouters.find(item => item.name === curRouteParentName)

//获取当前菜单
const menus: Ref<RouteRecordRaw[]> = ref([])
if (parentRoute && parentRoute.children) {
  menus.value = parentRoute.children
}

const key = computed(() => {
  return route.path
})
const activeMenu = computed(() => {
  const {meta, path} = route
  // if set path, the sidebar will highlight the path you set
  if (meta.activeMenu) {
    return meta.activeMenu
  }
  return path
})

function longinOut() {
  // userModule.userLoginOut()
}
</script>
