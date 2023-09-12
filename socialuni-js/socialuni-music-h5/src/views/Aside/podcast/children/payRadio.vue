<template>
  <div ref="dom">
    <el-card shadow="always" class="box-card">
      <template #header>
        <div class="card-header">
          <span>付费精选</span>
        </div>
      </template>
      <div class="payRadio">
        <skeleton2 :count="12" :loading="payRadio.length">
              <radio v-for="item in payRadio"
                     @click="goDetail(item.id)"
                     :image="item.picUrl"
                     :count="item.score"
                     :name="item.name"
                     :key="item.id">
              </radio>
        </skeleton2>
      </div>
    </el-card>
  </div>

</template>

<script setup>
import {getGoodRadio} from '@/network/radio.js'
import {onMounted, ref} from 'vue'
import {useRouter} from "vue-router";

import {dataLazyLoading} from "@/utlis/dataLazyLoading.js";

let dom = ref('')
let payRadio = ref([])
const router = useRouter()
onMounted(async () => {
  await dataLazyLoading(dom)
  let res = await getGoodRadio()
  payRadio.value = res.data.data.list
})
const goDetail = id => {
  router.push(`/program?id=${id}`)
}
</script>

<style scoped lang="less">
.payRadio{
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
}
</style>
