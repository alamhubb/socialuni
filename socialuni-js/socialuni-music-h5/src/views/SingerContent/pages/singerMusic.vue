<template>
  <SongList></SongList>
</template>

<script setup>
import SongList from '@/views/SongDetail/pages/SongList.vue'
import {onMounted,ref,computed} from 'vue'
import {useStore} from "vuex";
import {useRoute} from 'vue-router'
import {getSingerSong} from "@/network/singer.js";

const store = useStore()
const id = computed(() => store.state.singer.singerId)
onMounted(async () => {
  let res = await getSingerSong(id.value)
  store.commit('setSongMusic',res.data.songs)
})
</script>

<style scoped lang="scss">

</style>
