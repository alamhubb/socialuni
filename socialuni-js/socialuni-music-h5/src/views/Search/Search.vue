<template>
      <el-menu :default-active="$route.path" mode="horizontal" router>
          <el-menu-item index="/search">单曲</el-menu-item>
          <el-menu-item index="/search/singer">歌手</el-menu-item>
          <el-menu-item index="/search/album">专辑</el-menu-item>
          <el-menu-item index="/search/video">视频</el-menu-item>
          <el-menu-item index="/search/songMenu">歌单</el-menu-item>
        </el-menu>

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
