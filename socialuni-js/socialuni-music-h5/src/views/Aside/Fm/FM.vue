<template>
    <div class="fm">
        <img class="a" :class="{active:$store.state.isPlay}" src="../../../assets/image/button.png" alt="">
        <img class="b" src="../../../assets/image/disc.png" alt="img">
        <img :class="{active1:$store.state.isPlay}" :src="image.picUrl" alt="" class="c">
        <h1>{{image.name}}</h1>
    </div>
   
</template>

<script setup>
import {computed} from "vue";
import {useStore} from "vuex";
import {ref} from 'vue'

const store = useStore()
let image = computed(() => store.state.songDetail.songDetail.al)

</script>

<style scoped lang="less">
 .a{
   transition: all 1s;
   transform-origin: 20px 20px;
   width: 140px;
   transform: rotate(-30deg);
   z-index: 10;
   position: absolute;
   left: 49%;
   top: 3%;
 }
  .b,.c{
    width: 400px;
    height: 400px;
    position: absolute;
    left: 50%;
    top: 50%;
    margin-top: -200px;
    margin-left: -200px;
  }
  .c{
    width: 250px;
    height: 250px;
    border-radius: 50%;
    position: absolute;
    left: 50%;
    top: 50%;
    margin-top: -125px;
    margin-left: -125px;
  }
 h1{
   position: absolute;
   bottom: 1%;
   left: 50%;
   transform: translate(-50%);
   font-family: '楷体',serif;
 }

 @keyframes deg {
   0%{
     transform: rotate(0);
   }
   100%{
     transform: rotate(360deg);
   }
 }
.active{
  transform: rotate(0);
}
.active1{
  animation: deg 3s linear infinite;
}

.fm{
  width: 100%;
  height: 680px;
  position: relative;
}
</style>
