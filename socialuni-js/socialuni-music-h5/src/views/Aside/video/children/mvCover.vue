<template>
    <div class="cover">
      <div class="left">
           <el-image :src="image" class="image"></el-image>
           <div class="play">
            <i class="el-icon-caret-right"></i>
            <span class="playCount">{{$formatNumber(count)}}</span>
           </div>
        <span class="time" v-if="time">{{$formatTime(time).slice(-5)}}</span>
      </div>
      <div class="right">
        <div class="name">{{name}}</div>
        <div class="label" v-if="Array.isArray(label)">
          <template v-for="(v,i) in label" :key="i">{{v.name || v.userName}}  </template>
        </div>
        <div class="label" v-else>{{label}}</div>
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
  time:{
    type:Number
  },
  label:{
    type:[String,Array]
  },
  count:{
    type:Number
  }
})
</script>

<style scoped lang="less">
.cover{
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: flex-start;
  .left{
    width: 40%;
    height: 100%;
    position: relative;
    .time{
      position: absolute;
      bottom: 5px;
      right: 5px;
      color: white;
    }
    .play{
      position: absolute;
      color: white;
      right: 2px;
      top: 2px;
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
    .image{
      width: 100%;
      height: 100%;
      border-radius: 10px;
    }
  }
  .right{
    margin-left: 4%;
    width: 55%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    .name{
      width: 100%;
      height: 40px;
      line-height: 20px;
      color: #656161;
    }
    .label{
      width: 100%;
      height: 20px;
      color: silver;
    }
  }
}
</style>