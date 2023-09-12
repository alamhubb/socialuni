<template>
  <skeleton1 :count="10" :loading="album.length" :image="{width:'50px',height:'50px'}" :row="1" :margin="{width: '1111px',marginLeft:'10px'}">
    <searchCover :data="album" @goDetail="goDetail"/>
  </skeleton1>
</template>

<script setup>
import {computed,ref} from "vue";
import {useRouter} from "vue-router";
import {useStore} from "vuex";
import {getSearchResult} from "@/network/search.js";
import {getAlbumContent} from "@/network/comment.js";
import {formatAlbum} from "@/utlis/formatData.js";


let album = ref([])
const store = useStore()
const keywords = computed(() => store.state.songDetail.keywords)

getSearchResult({keywords:keywords.value,type:10}).then(res => {
  album.value = res.data.result.albums
})
const router = useRouter()
const goDetail = id => {
  store.commit('setHeader')
  getAlbumContent(id).then(res => {
    store.commit('setSongList',formatAlbum(res.data.album))
    store.commit('setSongMusic',res.data.songs)
  })
  router.push('/songDetail')
}
</script>

<style scoped lang="scss">

</style>
