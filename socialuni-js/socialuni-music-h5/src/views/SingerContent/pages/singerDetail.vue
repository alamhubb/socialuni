<template>
  <br>
  <template v-if="singerDetail.length">
    <el-descriptions :title="item.ti" v-for="(item,index) in singerDetail" :key="index">
      <el-descriptions-item>{{item.txt}}</el-descriptions-item>
    </el-descriptions>
  </template>

  <el-empty v-else description="暂无歌手简绍"></el-empty>
</template>

<script setup>
import {computed, onMounted,ref} from "vue";
import {useStore} from "vuex";
import {getSingerDesc} from "@/network/singer.js";

const store = useStore()
const id = computed(() => store.state.singer.singerId)
let singerDetail = ref([])
onMounted(async () => {
  let res = await getSingerDesc(id.value)
  singerDetail.value = res.data.introduction
})
</script>

<style scoped lang="scss">

</style>
