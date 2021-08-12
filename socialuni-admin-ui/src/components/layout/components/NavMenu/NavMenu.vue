<template>
  <el-menu :default-active="activeMenu">
    <!--      mode="horizontal"-->
    <!--    如果router没有子节点，或者仅仅有一个展示的子节点，或者总是展示-->
    <nav-menu-item
        v-for="route in routes"
        :key="route.path"
        :route="route"
        :base-path="route.path"
    />
  </el-menu>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import NavMenuItem from '@/components/layout/components/NavMenu/NavMenuItem.vue'
import { userModule } from '@/store'

@Component({
  components: { NavMenuItem }
})
export default class NavMenu extends Vue {
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
    return this.$router.options.routes
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
