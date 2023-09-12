<template>
 <titleTop @click="goSongMenu">推荐歌单</titleTop>
    <section class="section" ref="playList">
      <coverPicture
          v-if="songList.length"
          @click="everySong"
          :image="require('../../../../../assets/image/cover.png')"
          :time="true"
          top="根据你的音乐口味生成每日更新"
          label="每日歌曲推荐">
      </coverPicture>
      
      <template v-if="songList.length">
        <coverPicture v-for="item in songList" :key="item.id"
                      @click="songListDetail(item.id)"
                      :playCount="item.playCount"
                      :image="item.picUrl"
                      :label="item.name">
        </coverPicture>
      </template>
      <!--      骨架屏-->
      <el-skeleton v-else style="width: 220px;" animated v-for="item in 10" :key="item">
          <template #template>
            <el-skeleton-item variant="image" style="width: 220px; height: 220px;" />
            <div style="margin-top: 10px;">
              <el-skeleton-item variant="p" style="width: 100%;"/>
              <el-skeleton-item variant="p" style="width: 100%; margin-top: 10px;" />
            </div>
          </template>
        </el-skeleton>
    </section>
</template>

<script setup>
import {getSongList} from "@/network/recommend.js";
import {ref,onMounted} from "vue"
import {useRouter}  from 'vue-router'
import {useStore} from "vuex";

const store = useStore()
const router = useRouter()
//推荐歌单
let playList = ref()
let songList = ref([])
let num = undefined
onMounted(async () => {
  num = Math.floor(playList.value.clientWidth / 220) * 2 -1
  let res = await getSongList(num)
  songList.value = res?.data?.result
})

//每日推荐歌曲
const everySong = () => {
  store.commit('setIs',true)
  store.dispatch('getEverySong')
  router.push('/songDetail')
}
//点击歌单跳转
const songListDetail = id => {
  store.commit('setIs',false)
  store.dispatch('getSongList',id)
  router.push('/songDetail')
}
const goSongMenu = () => {
  router.push('/findMusic/songMenu')
}
</script>

<style scoped lang="less">
.section{
  width: 100%;
  height: 600px;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
}
</style>
