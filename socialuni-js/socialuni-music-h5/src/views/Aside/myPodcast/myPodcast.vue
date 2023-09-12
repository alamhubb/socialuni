<template>
  <h4>收藏的播客({{radio.length}})</h4>
  
  <skeleton1 :count="$store.state.data?.subPlaylistCount > 8 ? 8 : $store.state.data?.subPlaylistCount" :loading="radio.length" :image="{width:'80px',height:'80px'}" :row="1" :margin="{width:'1050px',marginLeft:'50px'}">
      <navContent v-for="item in radio"
                  :image="item.picUrl"
                  :name="item.name"
                  @click="toDetail(item.id)"
                  :label="item.dj.nickname"
                  :album="'声音 ' + item.programCount"
                  :key="item.id"/>
  </skeleton1>
</template>

<script setup>
import {getUserRadio} from "@/network/radio.js";
import navContent from '@/views/Aside/myCollect/children/navContent.vue'
import {useRouter} from "vue-router";
import {useStore} from "vuex";
import {ref} from 'vue'

const store = useStore()
const router = useRouter()

let radio = ref([])
getUserRadio().then(res => {
  radio.value = res.data.djRadios
})

const toDetail = id => {
  router.push(`/program?id=${id}`)
}
</script>

<style>

</style>
