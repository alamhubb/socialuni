<template>
      <br>
    <skeleton1 :count="10" :loading="singerData.length" :image="{width:'50px',height:'50px'}" :row="1" :margin="{width: '1111px',marginLeft:'10px'}">
    <searchCover :data="singerData" @goDetail="goDetail"/>
    </skeleton1>
</template>

<script setup>
import {computed,ref} from "vue";
import {useRouter} from "vue-router";
import {useStore} from "vuex";
import {getSearchResult} from "@/network/search.js";

let singerData = ref([])

const store = useStore()
const keywords = computed(() => store.state.songDetail.keywords)

getSearchResult({keywords:keywords.value,type:100}).then(res => {
  singerData.value = res.data.result.artists
})
const router = useRouter()
const goDetail = id => {
  store.commit('setSingerId',id)
  router.push(`/SingerContent`)
}
</script>

<style scoped lang="less">

</style>
