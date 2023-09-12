<template>
  <header>
    <nav class="nav">
      <router-link to="/findMusic/newMusic" v-slot="{scope}">
        <el-button class="button" round size="small" :class="{active:$route.path === '/findMusic/newMusic'}">新歌速递</el-button>
      </router-link>
      <router-link to="/findMusic/newMusic/newAlbum">
        <el-button class="button" round size="small" :class="{active:$route.path === '/findMusic/newMusic/newAlbum'}">新碟上架</el-button>
      </router-link>
    </nav>
  </header>
  <br>
  <br>
  <router-view v-slot="{Component}">
      <keep-alive>
        <component :is="Component"></component>
      </keep-alive>
  </router-view>
</template>

<script setup>

</script>

<style scoped lang="less">
.active{
  background: silver;
  color: whitesmoke;
}
header{
  margin-top: 60px;
  display: flex;
  justify-content: center;
  .nav{
    border-radius: 20px;
    border: 1px solid #ededed;
    .button{
      width: 150px;
    }
  }
}
</style>