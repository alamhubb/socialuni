<template>
  <nav>
    <div @click="change(item)"
         :class="{active:current === item.name}"
         v-for="(item,index) in tabs" :key="index">
      {{item.name}}
    </div>
  </nav>
</template>

<script setup>
import {ref} from 'vue'
let current = ref('全部')
let tabs = ref([
  {name:'全部',area:'ALL',type:0},
  {name:'华语',area:'ZH',type:7},
  {name:'欧美',area:'EA',type:96},
  {name:'韩国',area:'KR',type:16},
  {name:'日本',area:'JP',type:8},
])

let emit = defineEmits(['change'])
const change = value => {
  current.value = value.name
  emit('change',value)
}
</script>

<style scoped lang="scss">
nav{
  display: flex;
  justify-content: flex-start;
  color: #656161;
  & div:not(:nth-child(1)){
    margin-left: 20px;
  }
}
.active{
  font-weight: 900;
  color: black;
}



</style>
