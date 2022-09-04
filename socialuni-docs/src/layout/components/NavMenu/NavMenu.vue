<template>
  <div v-if="menus.length">
    <div v-for="menu in menus" :id="menu.meta.title">
      {{ menu.meta.title }}
    </div>
  </div>
</template>

<script setup lang="ts">
import type {Ref} from 'vue'
import {computed, ref} from "vue";
import type {RouteRecordRaw} from "vue-router";
import {useRoute} from "vue-router";
import {constantRouters} from "@/router";

const route = useRoute()
console.log(route.path)
console.log(route.name)

const curRouteParentName = route.path.split('/').filter(item => !!item)[0]

console.log(curRouteParentName)
const menus: Ref<RouteRecordRaw[]> = ref([])

const parentRoute: RouteRecordRaw = constantRouters.find(item => item.name === curRouteParentName)

if (parentRoute && parentRoute.children) {
  menus.value = parentRoute.children
}

const key = computed(() => {
  return route.path
})

function longinOut() {
  // userModule.userLoginOut()
}
</script>
