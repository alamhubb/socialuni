<template>
  <!--  <div :class="classObj" class="app-wrapper">-->
  <div class="h100p flex-col">
    <nav-bar />
    <div class="flex-row flex-1">
      <app-main class="flex-1" />
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import AppMain from '@/components/layout/AppMain.vue'
import NavMenu from '@/components/layout/components/NavMenu/NavMenu.vue'
import NavBreadcrumb from '@/components/layout/components/NavBreadcrumb.vue'
import NavBar from '@/components/layout/NavBar.vue'

@Component({
  components: { NavBar, NavBreadcrumb, NavMenu, AppMain }
})
export default class Layout extends Vue {
  // 是x(横向,水平)轴布局
  isXAxisLayout = false
}
</script>
