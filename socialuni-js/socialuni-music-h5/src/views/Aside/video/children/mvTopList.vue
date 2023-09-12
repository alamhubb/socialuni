<template>
  <header>
      <div><titleTop>MV排行榜</titleTop></div>
      <div class="right"><span v-for="(item,index) in tags"
                               @click="tagChange(item)"
                               :class="{active:current === item}"
                               :key="index">{{item}}</span></div>
  </header>
  <section ref="dom">
    <skeletonTop :count="10" :loading="mvTopList.length">
      <main class="mvTopList" @click="goMvDetail(item.id)" v-for="item in mvTopList" :key="item.id">
        <mvCover :image="item.cover"
                 :count="item.score"
                 :label="item.artists"
                 :name="item.name">
        </mvCover>
      </main>
  </skeletonTop>
  </section>
</template>

<script setup>
import skeletonTop from './skeletonTop.vue'
import {useRouter} from "vue-router";
import mvCover from '../children/mvCover.vue'
import {dataLazyLoading} from "@/utlis/dataLazyLoading.js";
import {getMvTopList} from "@/network/video.js";
import {onMounted} from "vue";
import {ref} from 'vue'

let dom = ref('')
let current = ref('内地')
let tags = ref(['内地','港台','欧美','日本','韩国'])
let mvTopList = ref([])
const emit = defineEmits(['load'])
onMounted(async () => {
  await dataLazyLoading(dom)
  let res = await getMvTopList(current.value)
  mvTopList.value = res.data.data
  emit('load')
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

</script>

<style scoped lang="less">
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
    width: 40%;
    display: flex;
    justify-content: space-evenly;
  }
}
section{
  height: 750px;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  .mvTopList{
    width: 49%;
    height: 130px;
    margin-top: 20px;
  }
}

</style>
