<template>
<div class="cover">
  <i class="el-icon-user" v-if="user">{{user}}</i>
  <div class="top" v-if="top">{{top}}</div>
  <div class="play-count" v-if="playCount">
    <span class="el-icon-caret-right"></span>
    <span>{{$formatNumber(playCount)}}</span>
  </div>
  <div class="el-icon-monitor" v-if="time"></div>
  <div class="time" v-if="time">{{date}}</div>
  <img class="image" :src="image" alt="">
  <div class="label">
  {{label}}
  </div>
  <i class="iconfont icon-bofang"></i>
</div>
</template>

<script setup>
import {onMounted, ref} from 'vue'
defineProps({
  top:{
    type:String
  },
  image:{
    type:String,
    default:''
  },
  label:{
    type:String
  },
  playCount:{
    type:Number
  },
  time:{
    type:Boolean
  },
  user:{
    type:String
  }
})

let date = ref()
let formatTime = new Date(Date.now())
onMounted(() => {
  date.value = formatTime.getDate()
})

</script>

<style scoped lang="scss">
.cover{
  width: 220px;
  height: 270px;
  overflow: hidden;
  position: relative;
  text-align: center;
  .el-icon-user{
    position: absolute;
    left: 10px;
    bottom: 60px;
    color: white;
  }
  .time{
    position: absolute;
    top: 32%;
    left: 50%;
    transform: translate(-50%,0);
    font-size: 30px;
    color: white;
  }
  .el-icon-monitor{
    position: absolute;
    top: 25%;
    left: 50%;
    transform: translate(-50%,0);
    font-size: 80px;
    color: white;
  }
  .play-count{
    position: absolute;
    top: 10px;
    right: 10px;
    font-size: 10px;
    span{
     color: #f6f6f6;
     font-size: 15px;
    }
  }
  .label{
    color: rgba(49, 48, 48, 0.8);
    margin-top: 10px;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp:2;
    -webkit-box-orient:vertical;
  }
  .top{
    width: 100%;
    font-size: 15px;
    height: 30px;
    position: absolute;
    top: 0;
    transform: translate(0,-30px);
    background: rgba(49, 48, 48, 0.5);
    line-height: 30px;
    color: #f6f6f6;
    overflow: hidden;
    text-overflow: ellipsis;
    border-top-left-radius: 10px;
    border-top-right-radius: 10px;
  }
  .icon-bofang{
    position: absolute;
    right: 10px;
    bottom: 50px;
    font-size: 40px;
    opacity: 0;
    color: whitesmoke;
  }
  .image{
    width: 100%;
    height: 220px;
    background-color: #9fb7de;
    overflow: hidden;
    border-radius: 10px;
  }

  &:hover .top{
    display: block;
    transition: all 1s;
    transform: none;
  }
  &:hover i{
    opacity: 1;
    transition: all 1s;
  }
}

</style>
