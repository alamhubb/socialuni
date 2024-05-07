<template>
  <el-menu>
    <el-menu-item v-for="menu in menus" :id="menu.meta.title">
      {{ menu.meta.title }}
    </el-menu-item>
  </el-menu>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-facing-decorator'
import { menuRoutes } from '@/router'

@toNative
@Component({})
export default class NavMenu extends Vue {
  // 是x(横向,水平)轴布局
  isXAxisLayout = false

  get menus(){
    return menuRoutes
  }

}
</script>

