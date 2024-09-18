<template>
  <el-menu>
    <el-menu-item v-for="menu in menus" :id="menu.meta.title">
      {{ menu.meta.title }}
    </el-menu-item>
  </el-menu>
</template>

<script lang="ts">
import { Component, Vue , toNative} from 'vue-facing-decorator'
import { menuRoutes } from '@/router'


@Component({})
class NavMenu extends Vue {
  // 是x(横向,水平)轴布局
  isXAxisLayout = false

  get menus(){
    return menuRoutes
  }

}
export default toNative(NavMenu)
</script>

