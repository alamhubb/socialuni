<template>
  <el-menu
    :default-active="activeMenu"
    active-text-color="#409EFF"
    :mode="mode==='x'?'horizontal':'vertical'"
    :class="[mode==='x'?'flex-row':'flex-col']"
  >
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
import { Component, Prop, Vue } from 'vue-property-decorator'
import NavMenuItem from '@/components/layout/components/NavMenu/NavMenuItem.vue'
import SocialPhoneNumQO from '@/model/base/SocialPhoneNumQO'
@Component({
  components: { NavMenuItem }
})
export default class NavMenu extends Vue {
  @Prop({ default: 'x' }) mode

  /*

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
}
</script>
