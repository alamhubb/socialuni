<template>
  <el-menu :collapse="$store.state.boolean" class="aside" :default-active="$route.path" router popper-append-to-body>
    <el-menu-item index="/findMusic" >
      <i class="iconfont icon-yinle1"></i>
      <template #title>发现音乐</template>
    </el-menu-item>
    <el-menu-item index="/podcast">
      <i class="iconfont icon-faxian"></i>
      <template #title>播客</template>
    </el-menu-item>
    <el-menu-item index="/video">
      <i class="iconfont icon-video1"></i>
      <template #title>视频</template>
    </el-menu-item>
    <el-menu-item index="/friend">
      <i class="iconfont icon-tianjiapengyou"></i>
      <template #title>动态</template>
    </el-menu-item>
    <el-menu-item index="/FM">
      <i class="iconfont icon-FM_weixuanze"></i>
      <template #title>私人FM</template>
    </el-menu-item>
    <el-menu-item index="/RecentPlay">
      <i class="iconfont icon-time"></i>
      <template #title>最近播放</template>
    </el-menu-item>
    <el-menu-item index="/myPodcast">
      <i class="iconfont icon-tuijian"></i>
      <template #title>我的播客</template>
    </el-menu-item>
    <el-menu-item index="/myCollect">
      <i class="iconfont icon-shoucang"></i>
      <template #title>我的收藏</template>
    </el-menu-item>
    <el-menu-item index="/likeMusic">
      <i class="iconfont icon-aixin"></i>
      <template #title>我喜欢的音乐</template>
    </el-menu-item>
    <el-menu-item index="/mySongList">
      <i class="iconfont icon-gedan"></i>
      <template #title>我的歌单</template>
    </el-menu-item>
  </el-menu>
</template>

<script setup>
import {getUserSongList} from "@/network/user.js";
import {useStore} from "vuex";
import {computed, onMounted, ref} from "vue";

const store = useStore()
let width = computed(() => store.state.boolean ? '70px' : '196px')

//用户歌单
let userId = computed(() => store.state.login.profile?.userId)
let params = {
  uid:userId.value,
  limit:10,
  offset:0
}
</script>

<style scoped lang="less">
.iconfont{
  margin-right: 10px;
  font-size: 20px;
}
.aside{
  height: 100%;
  width: v-bind('width');
  position: fixed;
}
.is-active{
  font-weight: 900;
}
</style>
