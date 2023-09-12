<template>
    <el-menu :default-active="$route.path" mode="horizontal" router class="nav">
      <el-menu-item index="/findMusic">个性推荐</el-menu-item>
      <el-menu-item index="/findMusic/exclusive">专属定制</el-menu-item>
      <el-menu-item index="/findMusic/songMenu">歌单</el-menu-item>
      <el-menu-item index="/findMusic/topList">排行榜</el-menu-item>
      <el-menu-item index="/findMusic/singer">歌手</el-menu-item>
      <el-menu-item index="/findMusic/newMusic">最新音乐</el-menu-item>
    </el-menu>
  
    <router-view v-slot="{Component}">
      <keep-alive>
        <component :is="Component"></component>
      </keep-alive>
    </router-view>
</template>

<script setup>
</script>
<script>
export default {
  name:'findMusic'
}
</script>
<style scoped lang="less">
.nav{
  position: fixed;
  z-index: 1000;
  width: 100%;
  top: 60px;
}
.is-active{
  font-weight: 900;
}
</style>
