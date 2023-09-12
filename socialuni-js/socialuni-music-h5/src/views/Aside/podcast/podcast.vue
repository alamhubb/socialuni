<template>
  <el-menu :default-active="$route.path" mode="horizontal" router>
    <el-menu-item index="/podcast">电台中心</el-menu-item>
    <el-menu-item index="/podcast/programTop">声音榜</el-menu-item>
    <el-menu-item index="/podcast/newComer">新晋播客榜</el-menu-item>
    <el-menu-item index="/podcast/hotTopList">热门播客榜</el-menu-item>
    <el-menu-item index="/podcast/hoursTopList">24小时节目榜</el-menu-item>
  </el-menu>
  <br>
  <router-view v-slot="{Component}">
    <keep-alive>
      <component :is="Component"></component>
    </keep-alive>
  </router-view>
</template>

<script setup>

</script>
<style scoped lang="scss">

</style>
