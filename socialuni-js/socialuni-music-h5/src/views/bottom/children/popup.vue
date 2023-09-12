<template>
  <section id="section">
    <i class="el-icon-close" @click="close"></i>
     <header id="header">{{songDetail.name}}</header>
      <main id="main" ref="dom">
         <nav id="nav" :class="{activeLyric:currentTime >= item.time && currentTime < lyricArr[index+1].time}"
               v-for="(item,index) in lyricArr" :key="index">
           {{item.lyric}}
         </nav>
      </main>
        <div class="nav" v-show="isShow" @click="scrollPlay">
          <span>{{$formatTime(scrollTime).slice(-5)}}</span><el-divider></el-divider><span class="el-icon-caret-right"></span>
        </div>
  </section>
</template>

<script setup>
import {getLyric} from "@/network/song.js";
import {computed, nextTick, onMounted, ref, watch} from "vue";
import {useStore} from "vuex";
import {formatLyric} from "@/utlis/formatData.js";
import eventBus from '@/utlis/eventbus.js'

const props = defineProps({
  currentTime:{
    type:Number
  }
})
const emit = defineEmits(['close','scrollPlay'])

let dom = ref()
const store = useStore()
const songDetail = computed(() => store.state.songDetail.songDetail)
let lyricArr = ref([])
onMounted(async () => {
  let res = await getLyric(songDetail.value.id)
  if (res.data.lrc?.lyric){
    lyricArr.value =  formatLyric(res.data.lrc?.lyric)
  }
})

let index
watch(() => lyricArr?.value.findIndex((item,index) => props.currentTime >= item.time && props.currentTime < lyricArr.value[index+1].time),newIndex => {
  eventBus.emit('active',lyricArr?.value[newIndex]?.lyric)
 if (newIndex > 5){
   dom.value.scrollTop = 43 * (newIndex -5)
   index = newIndex-5
 }
})

watch(songDetail,async newValue => {
  let res = await getLyric(newValue.id)
  lyricArr.value =  formatLyric(res.data.lrc.lyric)
})

const close = () => {
  emit('close')
}

let isShow = ref(false)
let scrollTime = ref()
let timer
addEventListener('mousewheel',() => {
    isShow.value = true
    const nav = document.querySelectorAll('#nav')
    let index = [...nav].findIndex(item => item.offsetTop >= dom.value.scrollTop)
    if (index !== -1){
      [...nav].forEach(item => {
        item.style.color = ''
      })
      nav[index+5].style.color = 'red'
      if (lyricArr.value[index + 5].time){
        scrollTime.value = lyricArr.value[index + 5].time
      }
    }
    clearTimeout(timer)
    timer = setTimeout(() => {
      isShow.value = false
      nav.forEach(item => {
        item.style.color = ''
      })
    },3000)
})
const scrollPlay = () => {
  const nav = document.querySelectorAll('#nav')
  nav.forEach(item => {
    item.style.color = ''
  })
  emit('scrollPlay',scrollTime.value)
}

</script>

<style scoped lang="scss">
.activeLyric{
  cursor:pointer;
  background-image:-webkit-linear-gradient(bottom,red,#ff5f60,#f0c41b);
  -webkit-background-clip:text;
  -webkit-text-fill-color:transparent;
  transition: all 1s;
  font-weight: 700;
  transform: translate3d(0,-3px,0);
  text-shadow: 0 3px 8px rgba(0,0,0,.1);
}
#section{
  width: 100%;
  height: 100%;
  background: #D9AFD9 linear-gradient(0deg, #D9AFD9 0%, #97D9E1 100%);
  position: relative;
  .nav{
    position: absolute;
    width: 60%;
    top: 48%;
    left: 50%;
    transform: translate(-50%,-50%);
    display: flex;
    justify-content: space-between;
    align-items: center;
    .el-icon-caret-right{
      font-size: 30px;
    }
    span{
      margin: 0 40px;
      color: white;
    }
  }
  i{
    position: absolute;
    right: 1%;
    top: 1%;
    font-size: 60px;
    color: white;
  }
  #header{
    position:absolute;
    left: 50%;
    top: 8%;
    transform: translate(-50%,-50%);
    color: white;
    font-size: 40px;
  }
  #main{
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%,-50%);
    text-align: center;
    width: 100%;
    height: 68%;
    overflow-y: auto;
    #nav{
      font-size: 25px;
      color: rgba(241, 236, 236, 0.99);
      height: 50px;
      line-height: 50px;
    }
  }
}
</style>
