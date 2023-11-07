<template>
    <h4>收藏的专辑({{songList.length}})</h4>
    <skeleton1 :count="6" :loading="songList.length" :image="{width:'80px',height:'80px'}" :row="1">
      <div v-for="item in songList" :key="item.id" style="margin-top: 10px;">
       <navContent
           @click="current(item.id)"
           :image="item.picUrl"
           :name="item.name"
           :label="item.artists[0].name"
           :album="item.size + '首'"/>
      </div>
    </skeleton1>
</template>
<script setup>
import {useRouter} from "vue-router";
import {useStore} from "vuex";
import navContent from '../children/navContent.vue'
import {getMyAlbum} from "@/network/user.js";
import {getAlbumContent} from "@/network/comment.js";
import {formatAlbum} from "@/utlis/formatData.js";
import {ref} from 'vue'

let songList = ref([])
getMyAlbum().then(res => {
  songList.value = res.data.data
})

const router = useRouter()
const store = useStore()

const current = id => {
  getAlbumContent(id).then(res => {
    store.commit('setSongList',formatAlbum(res.data.album))
    store.commit('setSongMusic',res.data.songs)
  })
  router.push('/songDetail')
}
</script>

<style scoped lang="scss">

</style>
