<template>
  <!--  <div :class="classObj" class="app-wrapper">-->
  <div class="h100p flex-col">
    <nav-bar/>
    <div class="flex-row flex-1 overflow-hidden">
      <nav-menu class="w150 flex-none"/>
      <div class="flex-1 overflow-auto pd-sm bg-default">
        <transition name="fade-transform" mode="out-in">
          <router-view :key="$route.path" class="h100p bg-white bd-radius"/>
        </transition>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue , toNative} from 'vue-facing-decorator'
import NavMenu from '@/layout/components/NavMenu/NavMenu.vue'
import NavBreadcrumb from '@/layout/components/NavBreadcrumb.vue'
import NavBar from '@/layout/NavBar.vue'

@toNative
@Component({
  components: { NavBar, NavBreadcrumb, NavMenu }
})
export default class Layout extends Vue {
  // 是x(横向,水平)轴布局
  isXAxisLayout = false
}
</script>
