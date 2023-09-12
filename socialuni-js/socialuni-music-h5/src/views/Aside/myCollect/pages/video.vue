<template>
  <skeleton2 :count="12" :loading="array.length" :size="{width:'285px',height:'150px'}">
    <div class="video">
     <videoCover @goDetail="goDetail" :videoArray="array"/>
    </div>
  </skeleton2>
</template>

<script setup>
import {getMvList} from "@/network/mv.js";
import {useRouter} from "vue-router";
import videoCover from '../../video/children/videoCover.vue'
import {ref} from 'vue'

let array = ref([])

getMvList().then(res => {
  array.value = res.data.data.map(item => {
    return{
      data:{
        coverUrl:item.coverUrl,
        vid:item.vid,
        cover:item.playTime,
        title:item.title,
        durationms:item.durationms
      }
    }
  })
})
const router = useRouter()
const goDetail = id => {
  router.push(`/videoDetail?vid=${id}`)
}
</script>

<style scoped lang="less">
.video{
  width: 100%;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
}
</style>
