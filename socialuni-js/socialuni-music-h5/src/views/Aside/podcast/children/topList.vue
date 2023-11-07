<template>
  <nav class="item">
    <el-row class="item-newSong" align="middle"
            @click="goDetail(item.id)"
            @dblclick="current(item,index)"
            v-for="(item,index) in array" :key="item.id">
      <el-col :span="1">
        <div style="margin-left: 10px;">
          <span v-if="item.id === $store.state.songDetail.songDetail.id" class="iconfont icon-yangshengqi"></span>
          <span v-else>{{index+1}}</span>
        </div>
      </el-col>
      <el-col :span="2">
        <div class="cover">
          <el-image :src="item.al.picUrl" class="image"></el-image>
          <img class="icon" src="../../../../assets/image/play.png" alt="">
        </div>
      </el-col>
      <el-col :span="10">
        <div>{{item.name}}</div>
      </el-col>
      <el-col :span="7">
        <div class="label">{{item.label}}</div>
      </el-col>
      <el-col :span="3">
        <div class="label">
          <el-tag type="success">{{item.album}}</el-tag></div>
      </el-col>
      <el-col :span="1">
        <div class="label">
          <el-progress status="warning"
                       :show-text="false"
                       :percentage="Math.floor(item.long / item.home * 100)">
        </el-progress></div>
      </el-col>
    </el-row>
  </nav>
</template>

<script setup>
import eventbus from '@/utlis/eventbus.js'
import {ref} from 'vue'
defineProps({
  array:{
    type:Array
  }
})

let currentIndex = ref()
const emit = defineEmits(['goDetail','current'])

const current = (item,index) => {
  currentIndex.value = index
  emit('current',{item,index})
  eventbus.emit('playMusic')
}

const goDetail = id => {
  emit('goDetail',id)
}
</script>

<style scoped lang="scss">
.iconfont{
  color: red;
}
.label{
  color: #656161;
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
  display: flex;
  align-items: center;
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
    width: 70px;
    height: 70px;
    border-radius: 10px;
  }
}
</style>
