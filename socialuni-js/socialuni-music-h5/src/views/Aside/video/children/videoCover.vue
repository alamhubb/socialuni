<template>
    <div @click="goDetail(item.data.vid)" class="video" v-for="item in videoArray" :key="item.data.vid">
    <div class="top">
      <el-image :src="item.data.coverUrl" class="image"></el-image>
      <div class="play">
        <i class="el-icon-caret-right"></i>
        <span class="playCount">{{$formatNumber(item.data.cover)}}</span>
      </div>
      <span class="time" v-if="item.data.durationms">{{$formatTime(item.data.durationms).slice(-5)}}</span>
    </div>
    <div class="bottom">
      <div class="name">{{item.data.title}}</div>
      <div class="label">{{item.data.creator?.nickname}}</div>
    </div>
  </div>
</template>

<script setup>
defineProps({
  videoArray:{
    type:Array
  }
})
const emit = defineEmits(['goDetail'])
const goDetail = id => {
  emit('goDetail',id)
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
