<template>
    <br><br><br>
      <el-skeleton style="display: flex;justify-content: space-between; align-items: center;" :loading="!Boolean(banners.length)" :count="1" animated>
          <template #template>
            <el-skeleton-item variant="image" style="width: 25%; height: 190px;"/>
            <el-skeleton-item variant="image" style="width: 50%; height: 220px; border-radius: 5px;"/>
            <el-skeleton-item variant="image" style="width: 25%; height: 190px;"/>
          </template>
          <template #default>
              <el-carousel trigger="click" indicator-position="outside" type="card" height="230px">
                <el-carousel-item :label="item.typeTitle" v-for="item in banners" :key="item.targetId" @click="banner(item)">
                  <el-image style="width:100%; height: 220px; border-radius: 5px;" :src="item.imageUrl"></el-image>
                </el-carousel-item>
              </el-carousel>
          </template>
      </el-skeleton>
</template>

<script setup>
import {getBanner} from "@/network/recommend.js";
import {ref} from "vue";
import {useRouter}  from 'vue-router'
import {useStore} from "vuex";
import eventbus from "@/utlis/eventbus.js";
import {getAlbumContent} from "@/network/comment.js";
import {formatAlbum} from "@/utlis/formatData.js";

const store = useStore()
const router = useRouter()
//轮播图
let banners = ref([])
getBanner().then(res => {
  banners.value = res?.data.banners
})
const banner = value => {
  if (value.targetType === 1){
    store.dispatch('getSongDetailData',value.targetId).then(() => {
      eventbus.emit('playMusic')
    })
  }else if (value.url){
    open(value.url)
  }else if (value.targetType === 10){
    getAlbumContent(value.targetId).then(res => {
      store.commit('setSongList',formatAlbum(res.data.album))
      store.commit('setSongMusic',res.data.songs)
    })
    router.push('/songDetail')
  }else if (value.targetType === 1000){
    store.dispatch('getSongList',value.targetId)
    router.push('/songDetail')
  }else if (value.targetType === 1004){
    router.push(`/videoDetail?id=${value.targetId}`)
  }
}
</script>

<style scoped lang="scss">

</style>
