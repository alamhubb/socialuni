<template>
   <skeleton2 :count="12" :loading="video.length" :size="{width:'286px',height: '150px'}">
    <div style="display: flex; justify-content: space-between; flex-wrap: wrap;">
      <videoCover :videoArray="video" @goDetail="goDetail"/>
    </div>
   </skeleton2>
</template>

<script setup>
import videoCover from '@/views/Aside/video/children/videoCover.vue'
import {computed,ref} from "vue";
import {useRouter} from "vue-router";
import {useStore} from "vuex";
import {getSearchResult} from "@/network/search.js";
import {formatVideoData} from "@/utlis/formatData.js";


let video = ref([])

const store = useStore()
const keywords = computed(() => store.state.songDetail.keywords)


let params = {
  keywords:keywords.value,
  type:1014,
  limit:16
}
getSearchResult(params).then(res => {
  video.value = formatVideoData(res.data.result.videos)
})
const router = useRouter()
const goDetail = id => {
  router.push(`/videoDetail?${id.length < 20 ? 'id' : 'vid'}=${id}`)
}

</script>

<style scoped lang="scss">

</style>
