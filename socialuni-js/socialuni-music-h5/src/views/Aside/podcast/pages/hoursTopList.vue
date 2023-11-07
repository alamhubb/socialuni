<template>
    <skeleton1 :count="8" :loading="length" :image="{width:'70px',height:'70px'}" :row="1" show>
      <topList :array="programsTop" @current="current"></topList>
    </skeleton1>
</template>

<script setup>
import {useStore} from "vuex";
import topList from '../children/topList.vue'
import {getHoursTopList} from '@/network/radio.js'
import {formatData} from "@/utlis/formatData.js";
import {computed,ref} from 'vue'

const store = useStore()
let length = ref(0)
getHoursTopList().then(res => {
  let mapArray = formatData(res.data.data.list)
  length.value = res.data.data.list.length
  store.commit('setSongMusic',mapArray)
})

let programsTop = computed(() => store.state.songDetail.songArray)

const current = ({item,index}) => {
  store.commit('setSongDetail',item)
  store.commit('play',index)
}
</script>

<style scoped lang="scss">

</style>
