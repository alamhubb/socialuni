<template>
  <nav class="item">
    <div class="left">
      <el-image :src="image" class="image"></el-image>
      <img class="icon" src="../../assets/image/play.png" alt="">
    </div>
    <div class="right">
      <div class="title">{{title}}</div>
      <div><el-tag size="mini" type="danger">{{tags}}</el-tag></div>
      <div class="label">
        <span class="label-bottom">{{label}}</span>
        <i class="el-icon-caret-right"><span class="count">{{$formatNumber(count)}}</span></i>
        <i class="iconfont icon-time"><span class="time">{{$formatTime(time).slice(-5)}}</span></i>
      </div>
    </div>
  </nav>
</template>

<script setup>
defineProps({
  image:{
    type:String,
    default:'在你的身边'
  },
  title:{
    type:String,
    default:'在你的身边'
  },
  tags:{
    type:String,
    default:'在你的身边'
  },
  label:{
    type:String,
    default:'在你的身边'
  },
  count:{
    type:Number,
    default:11111111
  },
  time:{
    type:Number,
    default:1123124124
  },
})


</script>

<style scoped lang="scss">
.item{
  width: 49%;
  margin-top: 10px;
  height: 100px;
  display: flex;
  justify-content: flex-start;
  &:hover{
    background: #f1ecec;
  }
  .left{
    width: 100px;
    height: 100px;
    position: relative;
    .image{
      border-radius: 10px;
    }
    .icon{
      width: 30px;
      height: 30px;
      background: white;
      border-radius: 50%;
      position: absolute;
      right: 5px;
      bottom: 5px;
    }
  }
  .right{
    margin-left: 20px;
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    .title{
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
    }
    .label{
      color: #656161;
      .label-bottom{
        margin-right: 10px;
        color: #748aad;
      }
      i{
        font-size: 16px;
        color: #656161;
      }
      .time{
        color: silver;
      }
      .count{
        margin-right: 10px;
      }
    }
  }
}
</style>
