<template>
  <header>
      <div><titleTop>MV排行榜</titleTop></div>
      <div class="right"><span v-for="(item,index) in tags"
                               @click="tagChange(item)"
                               :class="{active:current === item}"
                               :key="index">{{item}}</span></div>
  </header>
  <skeletonTop :count="10" :loading="mvTopList.length">
    <section>
    <main class="mvTopList" @click="goMvDetail(item.id)" v-for="(item,index) in mvTopList" :key="item.id">
      <div :class="{active:index < 3}" style="width: 30px; font-size: 25px; margin-right: 10px; font-weight: 900;">{{index+1 < 10 ? `0${index+1}` : index+1}}</div>
      <mvCover :image="item.cover"
               :count="item.score"
               :label="item.artists"
               :name="item.name">
      </mvCover>
    </main>
  </section>
  </skeletonTop>
  <br>
  <el-divider v-if="isShow && mvTopList.length > 9" @click="loading">点击加载更多</el-divider>
  <el-divider v-else>没有数据了</el-divider>
</template>

<script setup>
import skeletonTop from '../children/skeletonTop.vue'
import {useRouter} from "vue-router";
import mvCover from '../children/mvCover.vue'
import {getMvTopList} from "@/network/video.js";
import {onMounted} from "vue";
import {ref} from 'vue'

let isShow = ref(true)
let current = ref('内地')
let tags = ref(['内地','港台','欧美','日本','韩国'])
let mvTopList = ref([])
onMounted(async () => {
  let res = await getMvTopList(current.value)
  mvTopList.value = res.data.data
})
const tagChange = item => {
  current.value = item
  getMvTopList(current.value).then(res => {
    mvTopList.value = res.data.data
  })
}
const router = useRouter()
const goMvDetail = id => {
  router.push(`/videoDetail?id=${id}`)
}
let offset = 0
const loading = () => {
  offset+=10
  getMvTopList(current.value,offset).then(res => {
    mvTopList.value.push(...res.data.data)
  }).catch(err => {
    isShow.value = false
  })
}
</script>

<style scoped lang="scss">
.active{
  color: red;
  font-weight: 900;
}
header{
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  .right{
    width: 47%;
    display: flex;
    justify-content: space-evenly;
  }
}
section{
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  .mvTopList{
    width: 45%;
    height: 130px;
    margin-top: 20px;
    display: flex;
    justify-content: flex-start;
    align-items: center;
  }
}

</style>
