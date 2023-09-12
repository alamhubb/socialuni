<template>
<div class="cover">
  <el-image :src="image" class="image"></el-image>
  <div class="title" v-if="name">
    <div>{{name}}</div>
    <div class="time" v-if="time">{{$formatTime(time).slice(0,9)}}</div>
  </div>
  <img class="icon" src="../../assets/image/play.png" alt="">
</div>
</template>

<script setup>
defineProps({
  image:{
    type:String
  },
  name:{
    type:String
  },
  time:{
    type:Number
  }
})
</script>

<style scoped lang="scss">
.cover{
  width: 220px;
  height: 260px;
  position: relative;
  &:hover .icon{
    display: block;
    transition: all 1s;
  }
  .icon{
    display: none;
    position: absolute;
    top: 100px;
    left: 49%;
    transform: translate(-50%,-50%);
    width: 60px;
    height: 60px;
    background: white;
    border-radius: 50%;
  }
  .image{
    width: 200px;
    height: 200px;
    border-radius: 10px;
  }
  .title{
    margin-top: 5px;
    text-align: center;
  }
  .time{
    color: silver;
    font-size: 15px;
    margin-top: 10px;
  }
}
</style>
