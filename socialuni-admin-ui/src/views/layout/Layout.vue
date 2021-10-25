<template>
  <el-container id="app-box">
    <el-header class="flex-none bg-success">
      <h2 class="ml-50">
      清池管理系统
      </h2>
    </el-header>
    <el-container class="flex-auto">
      <sidebar class="flex-none"></sidebar>
      <el-main class="flex-col flex-auto">
        <router-view class="flex-col flex-auto"/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script lang="ts">
  import {routers} from '@/plugins/router'
  import {Component, Vue} from 'vue-property-decorator'
  import {RouteConfig} from 'vue-router'
  import Sidebar from '@/views/layout/Sidebar.vue'

  @Component({
    components: {Sidebar}
  })
  export default class Layout extends Vue {

  }
</script>

