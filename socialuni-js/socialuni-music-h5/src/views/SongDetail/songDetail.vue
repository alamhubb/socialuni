<template>
  <top v-if="$store.state.songDetail.is"></top>
  <!--    歌单顶部-->
  <songListTop v-else></songListTop>
  <!--    分隔线-->
  <el-divider content-position="right">网易云音乐</el-divider>
  <!--  tabs-->
  <el-menu
      router
      :default-active="$route.path"
      mode="horizontal">
    <el-menu-item index="/songDetail">歌单列表</el-menu-item>
    <el-menu-item index="/songDetail/comment">评论 ({{count}})</el-menu-item>
    <el-menu-item index="/songDetail/collect">收藏者</el-menu-item>
  </el-menu>

  <router-view/>
</template>

<script setup>
import top from './children/top.vue'
import songListTop from './children/songListTop.vue'
import {computed, ref, watch} from "vue";
import {getComment} from '@/network/comment.js'
import {useStore} from 'vuex'
const store = useStore()
const id = computed(() => store.state.songDetail.commentID)

let params = {
  id:id.value,
  type:2,
  limit:0
}
let count = ref(0)
watch(id,newID => {
  params.id = newID
  getComment(params).then(res => {
    count.value = res.data.total
  })
},{immediate:true})
</script>

<style scoped lang="scss">

</style>
