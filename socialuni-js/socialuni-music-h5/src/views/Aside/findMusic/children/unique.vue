<template>
  <titleTop @click="getAllContent">独家放送</titleTop>
  <div ref="dom">
    <el-skeleton :loading="!Boolean(unique.length)" animated style="width: 100%; display: flex;justify-content: space-between;" :count="3">
      <template #template>
        <div style="width: 33%;">
          <el-skeleton-item variant="image" style="width:100%; height: 160px;" />
          <br>
          <el-skeleton-item variant="p" style="width:100%;"/>
        </div>
      </template>
      <template #default>
        <div class="box">
          <div class="cover" v-for="item in unique" :key="item.id" @click="toDetail(item.id)">
            <el-image :src="item.picUrl" class="image"></el-image>
            <div class="name">{{item.name}}</div>
          </div>
        </div>
      </template>
  </el-skeleton>
  </div>
  <br>
</template>

<script setup>
import {dataLazyLoading} from "@/utlis/dataLazyLoading.js";
import {getUnique} from '@/network/radio.js'
import {onMounted,ref} from "vue";
import {useRouter} from "vue-router";

const router =  useRouter()
let unique = ref([])
const dom = ref('')
onMounted(async() => {
  await dataLazyLoading(dom)
  let res = await getUnique()
  unique.value = res.data.result
})

const toDetail = id => {
  router.push('/videoDetail?id='+id)
}
const getAllContent = () => {
  router.push('/unique')
}
</script>

<style scoped lang="scss">
.box{
  width: 100%;
  margin-bottom: -30px;
  height: 215px;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  .cover{
    width: 32%;
    .name{
      text-align: center;
      margin-top: 10px;
    }
  }
  .image{
    width: 100%;
    height: 160px;
    border-radius: 10px;
  }
}
</style>
