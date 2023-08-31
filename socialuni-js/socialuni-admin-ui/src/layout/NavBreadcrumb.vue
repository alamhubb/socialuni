<template>
  <div class="py-15 box-shadow row-between-center px-30">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">
        <span class="position-relative ml">
          <img
            style="left: -20px"
            class="position-absolute"
            alt="categoryLogo"
          >
          <!--            src="@/assets/img/navMenu/categoryLogo.png"-->
          建设部员工轮调管理
        </span>
      </el-breadcrumb-item>
      <el-breadcrumb-item v-for="(route) in currentRoutes" :key="route.fullPath" :to="{ path: route.fullPath }">
        <span>{{ route.meta.title }}</span>
      </el-breadcrumb-item>
    </el-breadcrumb>

    <!--    可能多页面都有此部分，需要根据页面进行判断展示，可提取组件，减少本页代码-->
    <div>
      <!--      为员工监控显示-->
      <template v-if="currentRoutes.find(item=>item.name === 'strengthMonitoring')">
        <el-tag closable class="mr-10">**工程局</el-tag>
        <el-tag closable class="mr-10">**工程局</el-tag>
        <el-tag closable>**工程局</el-tag>
        <el-divider direction="vertical" class="mx-10" />
        <el-select value="1" class="w120 mr-10" size="small">
          <el-option value="1" label="北京总部" />
        </el-select>
        <el-select value="1" class="w120px" size="small">
          <el-option value="1" label="建设部" />
        </el-select>
      </template>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-facing-decorator'

@Component
export default class NavBreadcrumb extends Vue {
  get currentRoutes() {
    const matched = this.$route.matched.filter(item => item.meta && item.meta.title)
    return matched.filter(item => item.meta && item.meta.title && item.meta.breadcrumb !== false)
  }
}
</script>
