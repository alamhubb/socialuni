<template>
  <div class="h50 box-shadow row-col-center">
    <div class="text-18 text-bold w200 row-center">
      yoyo-ui
    </div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item v-for="(route) in currentRoutes" :key="route.fullPath" :to="{ path: route.fullPath }">
        <span>{{ route.meta.title }}</span>
      </el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>

<script lang="ts">
import { Component, Vue , toNative} from 'vue-facing-decorator'


@Component
class NavBreadcrumb extends Vue {
  get currentRoutes() {
    const matched = this.$route.matched.filter(item => item.meta && item.meta.title)
    return matched.filter(item => item.meta && item.meta.title && item.meta.breadcrumb !== false)
  }
}
export default toNative(NavBreadcrumb)
</script>
