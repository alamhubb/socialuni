<template>
  <el-menu router :default-active="$route.path" mode="horizontal">
    <el-menu-item index="/video/video">视频</el-menu-item>
    <el-menu-item index="/video">MV</el-menu-item>
    <el-menu-item index="/video/allMV">全部MV</el-menu-item>
    <el-menu-item index="/video/mvTopList">MV排行</el-menu-item>
  </el-menu>
  <br>
  <router-view v-slot="{Component}">
    <keep-alive>
      <component :is="Component"></component>
    </keep-alive>
  </router-view>
</template>