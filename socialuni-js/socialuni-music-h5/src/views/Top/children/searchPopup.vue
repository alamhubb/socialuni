<template>
  <keep-alive>
    <component :is="current[index]" :keyword="keyword" :searchData="searchData"></component>
  </keep-alive>
</template>

<script setup>
import {watch,ref} from "vue";
import {getSearchSuggest} from "@/network/search.js";
import hotSearch from './hotSearch.vue'
import searchSuggest from './searchSuggest.vue'

let index = ref(0)
let current = [hotSearch,searchSuggest]
let searchData = ref({})

const props = defineProps({
  keyword:{
    type:String
  }
})

let timer
watch(() => props.keyword,newValue => {
  if (newValue) {
    if(timer)clearTimeout(timer)
    timer = setTimeout(() => {
      if (newValue !== '' && newValue.length>1){
        console.log(newValue)
        index.value = 1
        getSearchSuggest(newValue).then(res => {
          searchData.value = res.data.result
        })
      }
    },1000)
  }
  else {
    index.value = 0
  }
})
</script>

<style scoped lang="less">

</style>