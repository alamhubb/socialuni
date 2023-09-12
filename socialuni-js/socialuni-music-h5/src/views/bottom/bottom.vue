<template>
  <audio ref="audio" loop id="audio" :src="`https://music.163.com/song/media/outer/url?id=${songDetail.id}.mp3`"></audio>
  <el-row :gutter="20" style="z-index: 10000000 !important;" :style="{background:color}">
    <el-col :span="7">
      <div class="title" @mouseenter="open = true" @mouseleave="open = false">
        <i v-show="open" @click="drawer = true" class="iconfont icon-top" style="color: white;"></i>
        <el-avatar :class="[open ? 'active' : '']" @click="drawer = true" shape="square" :size="50" :src="songDetail.al.picUrl"></el-avatar>
        <div class="music-title">
          {{songDetail.name}}
        </div>
      </div>
    </el-col>
    <el-col :span="9">
      <main class="music">
        <div class="music-button">
          <i @click="isChangeLike" v-if="!isLike" title="收藏" class="iconfont icon-aixin"></i>
          <i @click="isChangeLike" v-else style="color: red;font-size: 22px;" title="已收藏" class="iconfont icon-xihuan"></i>
          <i title="上一曲" @click="next(-1)" class="iconfont icon-shangyiqu"></i>
          <i @click="playMusic" style="font-size: 40px; color: #cc7013;" class="iconfont" :class="[is ? 'icon-bofang1' :'icon-icon-bofang']"></i>
          <i title="下一曲" @click="next(1)" class="iconfont icon-xiayiqu"></i>
          <svg class="svg" @click="openLyric" aria-hidden="true"><use xlink:href="#icon-minganci"></use></svg>
        </div>
      </main>
    </el-col>
    <el-col :span="3">
      <div> {{$formatTime(currentTime).slice(-5)}} / {{$formatTime(songDetail.dt).slice(-5)}}</div>
    </el-col>
    <el-col :span="5">
      <el-slider @change="sliderChange" style="margin-top: 12px;" v-model="value" :show-tooltip="false" input-size="mini"></el-slider>
    </el-col>
  </el-row>
<!--  弹出层-->
  <el-drawer :with-header="false" :append-to-body="true" v-model="drawer" direction="btt" :size="`${size}%`">
    <popup @scrollPlay="scrollPlay" ref="dom" @close="close" :currentTime="currentTime"></popup>
  </el-drawer>
</template>

<script setup>
import {getLikeMusic} from "@/network/song.js";
import { ElMessage } from 'element-plus'
import {isLikeMusic} from "@/network/user.js";
import {debounce} from "@/utlis/debounce.js";
import eventBus from '@/utlis/eventbus.js'
import {defineAsyncComponent, computed, watch, ref, onMounted, nextTick} from "vue";
const popup = defineAsyncComponent(() => import('./children/popup.vue'))
import {useStore} from "vuex";
let value = ref(0)
let is = ref(false)
let open = ref(false)
let drawer = ref(true)
const audio = ref()
let isLike = ref(false)
let currentTime = ref(0)
let size = ref(0)
let dom = ref()
let color = ref('#ffffff')


queueMicrotask(() => {
  drawer.value = false
  watch(drawer,newValue => {
    const el = document.querySelector('.el-drawer__body')
    el.style.padding = 0
      size.value = 100
    setTimeout(() => {
      if (newValue){
        color.value ='#d7b0d9'
      }else {
        color.value ='#ffffff'
      }
    },200)
  })
})
const close = () => {
  drawer.value = false
  console.log('关闭')
}
const openLyric = () => {
  size.value = 100
  drawer.value = true
}
//收藏
const isChangeLike = debounce(() => {
  if (store.state.login.profile){
    isLike.value = !isLike.value
    isLikeMusic(songDetail.value.id,isLike.value).then(res => {
      if (isLike.value && res.data.code === 200){
        ElMessage.success({
          type:'success',
          message:'收藏成功'
        })
      }else {
        ElMessage.success({
          type:'success',
          message:'取消收藏'
        })
      }
    })
  }else {
    ElMessage.error({
      type:'error',
      message:'未登录'
    })
  }
},1000)
eventBus.on('playMusic',() => {
  setTimeout(() => {
    playMusic()
    audio.value.play()
  })
})
//播放音乐
let timer
const play = () => {
  currentTime.value = parseInt(audio.value.currentTime * 1000)
  value.value = parseInt(audio.value.currentTime/audio.value.duration*100)
  //自动切换
  if(audio.value.currentTime >= audio.value.duration-0.5){
    console.log('播放结束')
    next(1)
  }
}
let timer1
const playMusic = () => {
  nextTick(() => {
    audio.value.addEventListener("error", function() {
      if (timer1)clearTimeout(timer1)
      timer1 = setTimeout(() => {
        ElMessage.error('暂无音频已自动切换下一首')
        next(1)
      },200)
    }, true);
  })
  if (audio.value.paused){
    audio.value.play()
    is.value = true
    store.commit('setPlay')
    timer = setInterval(play,500)
  }else {
    audio.value.pause()
    is.value = false
    store.commit('setPlay')
    clearInterval(timer)
  }
}
//滑动
const sliderChange = (value,boolean=true) => {
    clearInterval(timer)
    setTimeout(() => {
      if (boolean){
        audio.value.currentTime = audio.value.duration * value / 100
      }else {
        audio.value.currentTime = value / 1000
      }
      audio.value.play()
      is.value = true
      timer = setInterval(play,500)
    })
}
const scrollPlay = time => {
  sliderChange(time,false)
}
//切换歌曲
const store = useStore()
const next = value => {
  let musicNum = computed(() => store.state.songDetail.songArray.length)
  if (musicNum.value > 1){
    store.commit('change',value)
    is.value = true
    setTimeout(() => {
      playMusic()
      audio.value.play()
    })
  }else {
    ElMessage.warning({
      message: '无可播放的歌曲列表',
      type: 'warning',
    })
  }
}

eventBus.on('login1',() => {
  getLikeMusic(store.state.login.profile?.userId).then(res => {
		if (!res)return
    store.commit('setLikeMusic',res?.data.ids)
    isLike.value = store.state.singer.likeMusic.includes(songDetail.value.id)
  })
})

const songDetail = computed(() => store.state.songDetail.songDetail)
watch(songDetail,() => {
  songDetail.value.id
  isLike.value = store.state.singer.likeMusic.includes(songDetail.value.id)
},{deep:true,immediate:true})
</script>

<style scoped lang="less">
.svg{
  width: 30px;
  height: 30px;
}
.icon-aixin{
  color: red;
}
.active{
  transition: all 1s;
  transform: scale(1.1);
}
.title{
    position: relative;
    display: flex;
    align-items: center;
    .music-title{
      width: 90%;
      margin-left: 10px;
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
    }
    i{
      position: absolute;
      top: 50%;
      transform: translate(-50%,-50%);
      left: 25px;
      font-size: 35px;
    }
}
.play{
  font-size: 30px;
  color: pink;
}
.music-button{
  display: flex;
  justify-content: space-evenly;
  align-items: center;
}
.iconfont{
  color: rgba(49, 48, 48, 0.8);
  z-index: 100;
}
</style>
