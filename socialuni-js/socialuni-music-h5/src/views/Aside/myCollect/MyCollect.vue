<template>
    <el-menu :default-active="$route.path" mode="horizontal" router>
      <el-menu-item index="/myCollect">专辑</el-menu-item>
      <el-menu-item index="/myCollect/singer">歌手</el-menu-item>
      <el-menu-item index="/myCollect/video">视频</el-menu-item>
      <el-menu-item index="/myCollect/professional">专栏</el-menu-item>
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
