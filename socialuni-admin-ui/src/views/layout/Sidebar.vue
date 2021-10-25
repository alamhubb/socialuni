<template>
  <el-menu
      class="el-menu-vertical-demo"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      :collapse="true"
      router
  >
    <el-menu-item v-for="item in routers" :index="item.path">
      <i class="el-icon-menu"></i>
      <span slot="title">{{item.name}}</span>
    </el-menu-item>
  </el-menu>
</template>

<script lang="ts">
  import {routers} from '@/plugins/router'
  import {Component, Vue} from 'vue-property-decorator'
  import {RouteConfig} from 'vue-router'

  @Component({})
  export default class Sidebar extends Vue {
    routers: RouteConfig [] = routers
  }
</script>
<style lang="scss">
  aside.el-aside {
    width: 60px;
  }
</style>
