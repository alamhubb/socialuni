<template>
  <br>
  <main v-if="singer.length">
    <cover
        :image="item.picUrl"
        :name="item.name"
        v-for="item in singer" :key="item.id"></cover>
  </main>
  <el-empty v-else description="暂无相似歌手"></el-empty>
</template>

<script setup>
import {getSimiSinger} from "@/network/singer.js";
import {useRouter} from "vue-router";
import {useStore} from "vuex";
import {computed,ref,onMounted} from "vue";

const store = useStore()
const id = computed(() => store.state.singer.singerId)
let singer = ref([])
onMounted(async () => {
  let res = await getSimiSinger(id.value)
  singer.value = res.data.artists
})
const router = useRouter()

</script>

<style scoped lang="less">
main{
  width: 100%;
  height: 90%;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
}
</style>