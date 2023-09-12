<template>
  <div class="cover">
    <div class="image-box">
      <el-image :src="image" class="image"></el-image>
      <div class="play-count"><i class="el-icon-caret-right"></i><span>{{$formatNumber(count)}}</span></div>
    </div>
    <div class="title">
     {{name}}
    </div>
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
  count:{
    type:Number
  },
  tag:{
    type:String
  }
})
</script>

<style scoped lang="less">
.cover{
  width: 180px;
  height: 240px;
  position: relative;
  .image-box{
    width: 100%;
    height: 180px;
    position: relative;
    .play-count{
      display: flex;
      align-items: center;
      position: absolute;
      top: 5px;
      right: 5px;
      color: white;
      font-size: 20px;
      span{
        font-size: 14px;
      }
    }
  }
  .image{
    width: 100%;
    height: 100%;
    border-radius: 10px;
  }
  .title{
    color: #656161;
    margin-top: 5px;
    text-align: center;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
    overflow: hidden;
    text-overflow: ellipsis;
  }
}
</style>