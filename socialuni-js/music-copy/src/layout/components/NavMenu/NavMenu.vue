<template>
  <el-menu :default-active="activeMenu">
    <!--      mode="horizontal"-->
    <!--    如果router没有子节点，或者仅仅有一个展示的子节点，或者总是展示-->
    <nav-menu-item
      v-for="route in routes"
      v-show="route.meta.isOpen || (user&&user.devNum === 1212121212)"
      :key="route.path"
      :route="route"
      :base-path="route.path"
    />
  </el-menu>
</template>

<script lang="ts">
import { Component, Vue , toNative} from 'vue-facing-decorator'
import NavMenuItem from '@/layout/components/NavMenu/NavMenuItem.vue'
import { menuRoutes } from '@/router/router'
import DevAccountRO from '@/model/base/DevAccountRO'

@toNative
@Component({
  components: { NavMenuItem }
})
export default class NavMenu extends Vue {

  get user(){

  }

  /*  @userStore.State('user')
    user: UserVO

    */

  created() {
    // 初始系统中公用的数据
    // searchModule.initQueryStaffs()
  }

  get key() {
    return this.$route.path
  }

  get routes() {
    return menuRoutes
  }

  get activeMenu() {
    const route = this.$route
    const { meta, path } = route
    // if set path, the sidebar will highlight the path you set
    if (meta.activeMenu) {
      return meta.activeMenu
    }
    return path
  }

  get isCollapse() {
    return false
  }

  get showHeader() {
    return this.$route.meta.showHeader
  }

  longinOut() {
    // userModule.userLoginOut()
  }
}
</script>
