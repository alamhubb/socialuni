<template>
  <header class="header">
    <navTabs @change="change"></navTabs>
    <div>
      <el-button type="danger" size="mini" icon="el-icon-video-play" round>播放全部</el-button>
      <el-button type="success" size="mini" icon="el-icon-folder-add" round>收藏全部</el-button>
    </div>
  </header>
  <skeleton1 :count="8" :loading="newSong.length" :image="{width:'70px',height:'70px'}" :row="1" show>
     <nav class="item">
      <el-row class="item-newSong" align="middle" @dblclick="current(item,index)" v-for="(item,index) in newSong" :key="item.id">
        <el-col :span="1">
          <div style="margin-left: 10px;">
            <span v-if="item.id === $store.state.songDetail.songDetail.id" class="iconfont icon-yangshengqi"></span>
            <span v-else>{{index+1}}</span>
          </div>
        </el-col>
        <el-col :span="2">
          <div class="cover">
            <el-image :src="item.al.picUrl" class="image"></el-image>
            <img class="icon" src="../../../../../assets/image/play.png" alt="">
          </div>
        </el-col>
        <el-col :span="10">
          <div>{{item.name}}</div>
        </el-col>
        <el-col :span="3">
          <div class="label">{{item.label}}</div>
        </el-col>
        <el-col :span="7">
          <div class="label">{{item.album}}</div>
        </el-col>
        <el-col :span="1">
          <div class="label">{{$formatTime(item.dt).slice(-5)}}</div>
        </el-col>
      </el-row>
    </nav>
      </skeleton1>


</template>

<script setup>
import eventbus from '@/utlis/eventbus.js'
import navTabs from '../../children/navTabs.vue'
import {getNewMusic} from "@/network/song.js";
import {computed, ref} from 'vue'
import {formatNewMusic} from "@/utlis/formatData.js";

import {useStore} from "vuex";
const store = useStore()
let length = ref(0)
let newSong = computed(() => store.state.songDetail.songArray.slice(0,20))
function getNewMusicData(res){
  store.commit('setSongMusic',formatNewMusic(res?.data?.data))
}

const current = (item,index) => {
  store.commit('setSongDetail',item)
  store.commit('play',index)
  eventbus.emit('playMusic')
}

const change = value => {
  getNewMusic(value.type).then(res => {
    getNewMusicData(res)
  })
}

</script>

<style scoped lang="scss">
.iconfont{
  color: red;
}
.label{
  color: #656161;
}
.header{
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}
.item-newSong{
  margin-top: 5px;
}
.item :hover{
  background: #ededed;
  border-radius: 10px;
}

.cover{
  width: 80px;
  height: 80px;
  position: relative;
  .icon{
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%,-50%);
    width: 30px;
    height: 30px;
    background: white;
    border-radius: 50%;
  }
  .image{
    width: 80px;
    height: 80px;
    border-radius: 10px;
  }
}
</style>
