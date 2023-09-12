<template>
   <header>搜<el-link type="primary">"{{keyword}}"</el-link>相关的结果></header>
    <br>
    <main v-if="searchData.songs?.length">
      <nav class="title">单曲<svg class="svg" aria-hidden="true"><use xlink:href="#icon-yinle"></use></svg></nav>
      <nav class="nav" @click="goSearch({id:item.name,type:0})" v-for="item in searchData.songs" :key="item.id">{{item.name}}-<template v-for="v in item.artists">{{v.name}}</template></nav>
      <nav class="nav title" v-if="searchData.artists?.length">歌手 <svg class="svg" aria-hidden="true"><use xlink:href="#icon-user"></use></svg></nav>
      <nav class="nav" @click="goSearch({id:item.id,type:1})" v-for="item in searchData.artists" :key="item.id">{{item.name}}</nav>
      <nav class="nav title" v-if="searchData.albums?.length">专辑 <svg class="svg" aria-hidden="true"><use xlink:href="#icon-zhuanjiguangpan"></use></svg></nav>
      <nav class="nav" @click="goSearch({id:item.id,type:2})" v-for="item in searchData.albums" :key="item.id">{{item.name}}-{{item.artist.name}}</nav>
      <nav class="nav title" v-if="searchData.playlists?.length">歌单 <svg class="svg" aria-hidden="true"><use xlink:href="#icon-gedan"></use></svg></nav>
      <nav class="nav" @click="goSearch({id:item.id,type:3})" v-for="item in searchData.playlists" :key="item.id">{{item.name}}</nav>
    </main>
    <div v-else style="display: flex; justify-content: center; align-items: center;"><i style="font-size: 30px;" class="el-icon-loading"></i></div>
</template>

<script setup>
import eventBus from '@/utlis/eventbus.js'
import {useRouter} from "vue-router";
import {formatAlbum} from "@/utlis/formatData.js";
import {useStore} from "vuex";
import {getAlbumContent} from "@/network/comment.js";

defineProps({
  keyword:{
    type:String
  },
  searchData:{
    type:Object
  }
})

const router = useRouter()
const store = useStore()
const goSearch = ({id,type}) => {
  switch (type){
      case 0:
        eventBus.emit('hotSearch',id)
          break
      case 1:
        store.commit('setSingerId',id)
        router.push(`/SingerContent`)
          break
      case 2:
        getAlbumContent(id).then(res => {
          store.commit('setSongList',formatAlbum(res.data.album))
          store.commit('setSongMusic',res.data.songs)
          router.push('/songDetail')
        })
          break
      case 3:
        store.dispatch('getSongList',id)
        router.push('/songDetail')
          break
  }
}
</script>

<style scoped lang="scss">
.title{
  background: #f1ecec;
}
.nav{
  margin-top: 5px;
  &:hover{
    background: #f5f5f5;
  }
}
 .svg {
   width: 1em;
   height: 1em;
   vertical-align: -0.15em;
   fill: currentColor;
   overflow: hidden;
 }
</style>
