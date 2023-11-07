<template>
    <titleTop @click="$router.push('/podcast')">热门播客</titleTop>
    <div ref="dom" class="radio-box">
      <coverBox v-for="item in radioData"
                @click="goDetail(item.id)"
                :image="item.picUrl"
                :tags="item.category"
                :label="item.dj.nickname"
                :time="item.createTime"
                :count="item.subCount"
                :title="item.name"
                :key="item.id">
      </coverBox>
    </div>
    <section v-if="!radioData.length" style="height: 320px; display: flex;justify-content: space-between;flex-wrap: wrap;">
      <div v-for="item in 6" style="width: 50%;">
       <el-skeleton animated>
        <template #template>
          <section style="display: flex; justify-content: flex-start;">
            <el-skeleton-item variant="image" style="width: 100px; height: 100px;" />
           <div style="width: 70%; height: 100px; margin-left: 10px; display: flex;flex-direction: column; justify-content: space-evenly;">
             <el-skeleton-item variant="p"/>
             <el-skeleton-item variant="p"/>
             <el-skeleton-item variant="p"/>
           </div>
          </section>
        </template>
        </el-skeleton>
    </div>
  </section>
  <br>
  <br>
</template>

<script setup>
import {ref,onMounted} from "vue"
import {dataLazyLoading} from "@/utlis/dataLazyLoading.js";
import {getRadioRecommend} from "@/network/radio.js";
import {useRouter}  from 'vue-router'

const router = useRouter()
//播客
let dom = ref('')
let radioData = ref([])
onMounted(async() => {
  await dataLazyLoading(dom)
  let res = await getRadioRecommend()
  radioData.value = res.data.data
})
const goDetail = id => {
  router.push(`/program?id=${id}`)
  window.scrollTo(0,0)
}
</script>

<style scoped lang="scss">
.radio-box{
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
}
</style>
