<template>
  <!--  <div :class="classObj" class="app-wrapper">-->
  <div class="h100p flex-col">
    <nav-bar />
    <div class="flex-row flex-1 overflow-hidden">
      <nav-menu class="w200" />
      <div class="flex-1 overflow-auto pd">
        <transition name="fade-transform" mode="out-in">
          <router-view :key="$route.path" />
        </transition>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import NavMenu from '@/layout/components/NavMenu/NavMenu.vue'
import NavBreadcrumb from '@/layout/components/NavBreadcrumb.vue'
import NavBar from '@/layout/NavBar.vue'

@Component({
  components: { NavBar, NavBreadcrumb, NavMenu }
})
export default class Layout extends Vue {
  // 是x(横向,水平)轴布局
  isXAxisLayout = false
}
</script>
