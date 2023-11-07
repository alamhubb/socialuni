<template>
    <el-card class="box-card" shadow="always" style="height: 340px;" type="text">
    <template #header>
      <div class="clearfix">
        <span>热门推荐</span>
        <el-pagination @current-change="change"
                       small
                       :hide-on-single-page="false"
                       layout="prev,pager,next" :total="100">
        </el-pagination>
      </div>
    </template>
      <skeleton2 :count="6" :loading="hotRadio.length">
        <main class="footer">
            <radio v-for="item in hotRadio"
                   @click="goDetail(item.id)"
                   :image="item.picUrl"
                   :count="item.subCount"
                   :name="item.name"
                   :key="item.id"></radio>
        </main>
      </skeleton2>
  </el-card>
</template>

<script setup>
import {getHotRadio} from '@/network/radio.js'
import {ref} from 'vue'
import {useRouter} from "vue-router";

let hotRadio = ref([])
getHotRadio().then(res => {
  hotRadio.value = res.data.djRadios
})
const change = value => {
  getHotRadio(value * 6).then(res => {
    hotRadio.value = res.data.djRadios
  })
}
const router = useRouter()
const goDetail = id => {
  router.push(`/program?id=${id}`)
}
</script>

<style scoped lang="scss">
.clearfix,.footer{
  display: flex;
  justify-content: space-between;
}

.footer{
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
}

</style>
