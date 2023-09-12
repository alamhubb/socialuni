<template>
  <br>
  <titleTop @click="goNewMusic">最新音乐</titleTop>
  <section style="width: 100%;" ref="dom">
    <el-skeleton :loading="!Boolean(newMusicData.length)" :count="12" animated style="display: flex;justify-content: space-between;flex-wrap: wrap;">
      <template #template>
        <div style="display: flex;justify-content: flex-start; width: 33%; margin-top: 10px;">
          <el-skeleton-item style="width: 50px;height: 50px;" variant="image"/>
          <div style="width: 100%; margin-left: 10px; display: flex;flex-direction: column;justify-content: space-evenly;">
            <el-skeleton-item variant="p" style="width: 80%;"/>
            <el-skeleton-item variant="p" style="width: 80%;"/>
          </div>
        </div>
      </template>
      <template #default>
        <div class="newMusic" style="height: 250px;">
          <nav class="item" @dblclick="playMusic(item)" v-for="item in newMusicData" :key="item.id">
            <div class="left" @click="playMusic(item)">
              <el-image :src="item.album.picUrl" class="image"></el-image>
              <img class="icon" src="../../../../assets/image/play.png" alt="">
            </div>
            <div class="right">
              <div class="title">{{item.name}}</div>
              <div class="tags">
                <el-tag @click="toMv(item.mvid)" style="margin-right: 10px;" size="mini" type="danger" v-if="item.mvid">MV</el-tag>
                <span class="hover" v-for="i in item.artists.map(v => v.name).join(' /, ').split(',')" :key="i.id">
                  {{i}}
                </span>
              </div>
            </div>
          </nav>
        </div>
      </template>
    </el-skeleton>
  </section>
</template>

<script setup>
import {getNewMusic} from "@/network/song.js";
import {dataLazyLoading} from "@/utlis/dataLazyLoading.js";
import {formatNewMusic} from "@/utlis/formatData.js";
import {onMounted,ref} from "vue";
import {useRouter} from "vue-router";
import {useStore} from "vuex";
import eventbus from '@/utlis/eventbus.js'

let newMusicData = ref([])
const dom = ref('')
onMounted(async() => {
  await dataLazyLoading(dom)
  let res = await getNewMusic()
  newMusicData.value = res.data.data.slice(0,12)
})

const router = useRouter()
const store = useStore()
const goNewMusic = () => {
  router.push('/findMusic/newMusic')
}

const toMv = id => {
  router.push(`/videoDetail?id=${id}`)
}

const playMusic = item => {
  store.commit('setSongDetail',...formatNewMusic([item]))
  eventbus.emit('playMusic')
}
</script>

<style scoped lang="less">
.hover:hover{
  color: rgba(49, 48, 48, 0.8);
}
.newMusic{
  width: 100%;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  .item{
    width: 33%;
    height: 50px;
    display: flex;
    justify-content: flex-start;
    margin-top: 10px;
    &:hover{
      background: #ededed;
    }
    .left{
      width: 50px;
      height: 50px;
      position: relative;
      .image{
        border-radius: 10px;
      }
      .icon{
        width: 20px;
        height: 20px;
        background: white;
        border-radius: 50%;
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%,-50%);
      }
    }
    .right{
      width: 80%;
      margin-left: 10px;
      display: flex;
      flex-direction: column;
      justify-content: space-evenly;
      .tags{
        color: silver;
        font-size: 13px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
      }
      .title{
        width: 100%;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
      }
    }
  }
}

:deep(.item){
  width: 33%;
}
:deep(.left){
  width: 50px;
  height: 50px;
}
</style>
