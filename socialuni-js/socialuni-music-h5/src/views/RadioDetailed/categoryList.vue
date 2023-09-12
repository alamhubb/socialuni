<template>
  <el-divider content-position="left"><h2>{{$route.query.name}}</h2></el-divider>
  <skeleton1 :loading="categoryData.length" :count="10" :margin="{width:'400px',marginLeft:'15px'}">
    <section class="category">
    <main @click="goProgram(item.id)" class="box" v-for="item in categoryData" :key="item.id">
      <div class="left"><el-image class="image" :src="item.picUrl"></el-image></div>
      <div class="right">
        <div class="name">{{item.name}}</div>
        <div class="label">{{item.rcmdtext}}</div>
        <div class="order">声音: {{item.programCount}} 收藏: {{item.subCount}}</div>
      </div>
    </main>
  </section>
  </skeleton1>

</template>

<script setup>
import {getCategoryRadio} from '@/network/radio.js'
import {useRoute, useRouter} from "vue-router";
import {ref} from 'vue'

//分类下的电台列表
let categoryData = ref([])
const route = useRoute()
getCategoryRadio(route.query.id).then(res => {
  categoryData.value = res.data.djRadios
})
const router = useRouter()
const goProgram = id => {
  router.push(`/program?id=${id}`)
}
</script>
<script>
export default {
  name:'categoryList'
}
</script>
<style scoped lang="less">
.category{
  width: 100%;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  .box{
    width: 49%;
    height: 150px;
    display: flex;
    justify-content: flex-start;
    margin-top: 10px;
    .left{
      width: 25%;
      height: 100%;
      .image{
        width: 100%;
        height: 100%;
        border-radius: 10px;
      }
    }
    .right{
      width: 67%;
      height: 100%;
      margin-left: 3%;
      display: flex;
      flex-direction: column;
      justify-content: space-evenly;
      .label{
        color: #656161;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 4;
        overflow: hidden;
        text-overflow: ellipsis;
      }
      .order{
        color: #7a6c6c;
      }
    }
  }
}
</style>
