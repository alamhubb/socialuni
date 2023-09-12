<template>
      <skeleton1 :count="8" :loading="programsTop.length" :image="{width:'70px',height:'70px'}" :row="1" show>
        <topList :array="programsTop" @goDetail="goDetail"></topList>
      </skeleton1>
</template>

<script setup>
import {useRouter} from "vue-router";
import topList from '../children/topList.vue'
import {getNewTopList} from '@/network/radio.js'
import {formatNewData} from "@/utlis/formatData.js";
import {ref,computed} from 'vue'


let programsTop = ref([])

getNewTopList('hot').then(res => {
  programsTop.value = formatNewData(res.data.toplist.slice(0,30))
})
const router = useRouter()
const goDetail = id => {
  router.push(`/program?id=${id}`)
}

</script>

<style scoped lang="scss">

</style>
