<template>
  <br>
  <main class="box" v-if="data.length">
    <div class="cover" v-for="item in data" :key="item.id" @click="toDetail(item.id)">
      <el-image :src="item.imgurl" class="image"></el-image>
      <div class="title">{{item.name}}</div>
      <i class="el-icon-caret-right"><span>{{$formatNumber(item.playCount)}}</span></i>
      <span class="time">{{$formatTime(item.duration).slice(-5)}}</span>
    </div>
  </main>
  
  <el-empty v-else description="暂无MV"></el-empty>
</template>

<script setup>
import {getSingerMV} from "@/network/singer.js";
import {computed, onMounted, ref} from "vue";
import {useRouter} from "vue-router";
import {useStore} from "vuex";

const router = useRouter()
const store = useStore()
const id = computed(() => store.state.singer.singerId)
const data = ref([])

onMounted(async () => {
  let res = await getSingerMV(id.value)
  data.value = res.data.mvs
})
const toDetail = id => {
  router.push(`/videoDetail?id=${id}`)
}
</script>

<style scoped lang="less">
.box{
  height: 400px;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  .cover{
    width: 220px;
    height: 160px;
    position: relative;
    text-align: center;
    .title{
      margin-top: 5px;
      color: rgba(49, 48, 48, 0.8);
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
    }
    .image{
      width: 100%;
      height: 140px;
      border-radius: 10px;
    }
    i{
      position: absolute;
      color: white;
      font-size: 20px;
      top: 5px;
      right: 10px;
      span{
        font-size: 15px;
      }
    }
    .time{
      position: absolute;
      bottom: 10px;
      right: 10px;
      color: #f1ecec;
    }
  }
}
</style>