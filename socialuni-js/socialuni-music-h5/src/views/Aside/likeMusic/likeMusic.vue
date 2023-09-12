<template>
  <skeleton1 :image="{width:'170px',height:'170px'}" :loading="userSongList.length">
    <header>
    <div><el-image class="cover" :src="userSongList[0].coverImgUrl" alt="img"></el-image></div>
    <div class="content">
      <div class="A"><h2>{{userSongList[0].name}}</h2></div>
      <div class="B">
        <el-link type="info">{{userSongList[0].creator.nickname}}</el-link>
      </div>
      <div class="C">
        <el-button size="medium" type="danger" round icon="el-icon-caret-right">播放全部</el-button>
        <el-button size="medium" disabled round icon="el-icon-folder-add">收藏全部</el-button>
        <el-button size="medium" round icon="el-icon-share">分享</el-button>
        <el-button size="medium" round icon="el-icon-upload">VIP下载</el-button>
      </div>
    </div>
  </header>
  </skeleton1>
  <br>
  <SongList></SongList>
</template>

<script setup>
import singerDesc from '@/views/SingerContent/children/singerDesc.vue'
import SongList from '@/views/SongDetail/pages/SongList.vue'

import {getLikeMusic,getSongDetail} from '@/network/song.js'
import {getUserSongList} from '@/network/user.js'

import {computed, onMounted,ref} from "vue";
import {useStore} from "vuex";

const store = useStore()
const profile = computed(() => store.state.login.profile)
console.log (profile.value)
let params = {
  uid:profile.value?.userId,
}
let userSongList = ref([])
getUserSongList(params).then(res => {
  userSongList.value = res.data.playlist
})

onMounted(async () => {
  let res = await getLikeMusic(profile.value.userId)
  let musicData = await getSongDetail(res.data.ids.join())
  store.commit('setSongMusic',musicData.data.songs)
})

</script>

<style scoped lang="less">
header{
  display: flex;
  justify-content: flex-start;
  padding: 10px;
  width: 100%;
  .cover{
    display: block;
    height: 170px;
    width: 170px;
    border-radius: 10px;
  }
  .content{
    margin-left: 20px;
    .A{
      width: 100%;
      height: 30px;
      display: flex;
      align-items: center;
      h2{
        display: inline;
        margin-left: 10px;
      }
    }
    .B{
      width: 100%;
      height: 50px;
      display: flex;
      align-items: center;
      span{
        font-size: 14px;
        color: #748aad;
      }
      a{
        margin: 0 7px;
        text-decoration:none
      }
    }
  }
}
</style>
