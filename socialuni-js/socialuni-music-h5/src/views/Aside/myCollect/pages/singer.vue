<template>
  <h4>收藏的歌手({{songList.length}})</h4>
  
      <skeleton1 :count="$store.state.data?.artistCount > 8 ? 8 : $store.state.data?.artistCount" :loading="songList.length" :image="{width:'80px',height:'80px'}" :row="1">
       <div v-for="item in songList" :key="item.id" style="margin-top: 10px;">
          <navContent @click="current(item.id)"
                :image="item.img1v1Url"
                :name="item.name"
                :label="'专辑: '+item.albumSize"
                :album="'MV: '+item.mvSize"/>
          </div>
      </skeleton1>
  
</template>

<script setup>
import {useRouter} from "vue-router";
import {useStore} from "vuex";
import navContent from '../children/navContent.vue'
import {getUserProfile} from "@/network/user.js";
import {ref} from 'vue'

let songList = ref([])
getUserProfile().then(res => {
  songList.value = res.data.data
})
const store = useStore()
const router = useRouter()

const current = id => {
  store.commit('setSingerId',id)
  router.push(`/SingerContent`)
}


</script>

<style scoped lang="less">

</style>
