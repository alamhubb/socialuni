<template>
  <el-menu :default-active="activeMenu">
    <!--      mode="horizontal"-->
    <!--    如果router没有子节点，或者仅仅有一个展示的子节点，或者总是展示-->
    <nav-menu-item
        v-for="route in menuRoutes"
        :key="route.path"
        :route="route"
        :base-path="route.path"
    />
  </el-menu>
</template>

<script setup>
import {computed} from "vue";
import {useRoute} from "vue-router";
const route = useRoute()
console.log(this)
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
