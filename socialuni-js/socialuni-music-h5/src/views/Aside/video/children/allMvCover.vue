<template>
  <div @click="goDetail(item.id)" class="video" v-for="item in videoArray" :key="item.id">
    <div class="top">
      <el-image :src="item.cover" class="image"></el-image>
      <div class="play">
        <i class="el-icon-caret-right"></i>
        <span class="playCount">{{$formatNumber(item.playCount)}}</span>
      </div>
      <span class="time" v-if="item.duration">{{$formatTime(item.duration).slice(-5)}}</span>
    </div>
    <div class="bottom">
      <div class="name">{{item.name}}</div>
      <div class="label"><span v-for="v in item.artists" :key="v.id">{{v.name}}</span></div>
    </div>
  </div>
</template>

<script setup>
import {useRouter} from "vue-router";


defineProps({
  videoArray:{
    type:Array
  }
})
const router = useRouter()
const goDetail = id => {
  router.push(`/videoDetail?id=${id}`)
}
</script>

<style scoped lang="scss">
.video{
  width: 24%;
  height: 200px;
  margin-top: 20px;
  .top{
    width: 100%;
    height: 150px;
    position: relative;
    .play{
      position: absolute;
      color: white;
      right: 10px;
      top: 5px;
      display: flex;
      justify-content: flex-start;
      align-items: center;
      i{
        font-size: 25px;
        .playCount{
          font-size: 15px;
        }
      }
    }
    .time{
      position: absolute;
      bottom: 5px;
      right: 5px;
      color: white;
    }
    .image{
      width: 100%;
      height: 100%;
      border-radius: 10px;
    }
  }
  .bottom{
    text-align: center;
    height: 40px;
    width: 100%;
    margin-top: 5px;
    .name{
      width: 100%;
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
      color: #656161;
    }
    .label{
      color: silver;
      margin-top: 4px;
    }
  }
}
</style>
