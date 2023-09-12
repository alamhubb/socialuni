<template>
  <skeleton1 :loading="singerDetail?.artist.id" :image="{width:'210px',height:'220px'}" :margin="{width: '900px',marginLeft:'20px'}">
    <singerDesc :singerDetail="singerDetail"></singerDesc>
  </skeleton1>
  <el-menu :default-active="$route.path" router mode="horizontal">
    <el-menu-item index="/SingerContent">单曲</el-menu-item>
    <el-menu-item index="/SingerContent/album">专辑</el-menu-item>
    <el-menu-item index="/SingerContent/mv">MV</el-menu-item>
    <el-menu-item index="/SingerContent/singerDetail">歌手详情</el-menu-item>
    <el-menu-item index="/SingerContent/similaritySinger">相似歌手</el-menu-item>
  </el-menu>

  <Suspense>
    <template #default>
      <router-view v-slot="{Component,route}">
        <keep-alive>
          <component :is="Component"></component>
        </keep-alive>
      </router-view>
    </template>
    <template #fallback> Loading... </template>
  </Suspense>
</template>

<script setup>
import {onMounted,ref,computed} from 'vue'
import {useStore} from "vuex";
import {useRoute} from 'vue-router'
import singerDesc from './children/singerDesc.vue'
import {getSingerDetail} from "@/network/singer.js";

const store = useStore()
const id = computed(() => store.state.singer.singerId)
let singerDetail = ref(null)
onMounted(async () => {
  let res = await getSingerDetail(id.value)
  singerDetail.value = res.data.data
})

</script>

<style scoped lang="scss">

</style>
<script>
export default {
  name:'singerContent'
}
</script>
