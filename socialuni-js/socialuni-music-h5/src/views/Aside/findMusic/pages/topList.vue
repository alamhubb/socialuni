<template>
  <div style="margin-top: 60px;">
    <skeleton1 :count="4" :loading="topList.length">
        <section class="topList">
         <h2 style="margin-left: 10px;"> 官方榜</h2>
          <main @click="toDetail(item.id)" class="box-card" v-for="item in topList.slice(0,4)" :key="item.id">
            <div v-if="item.tracks.length" class="cover">
              <img :src="item.coverImgUrl" alt="">
            </div>
            <div class="top">
              <div class="top-item" v-for="(value,index) in item.tracks" :key="index">
                  <span><span class="index">{{index+1}}</span>{{value.first}}</span>
                  <span class="right">{{value.second}}</span>
              </div>
            </div>
          </main>
          <br>
          <el-divider content-position="left"><h2>全球榜</h2></el-divider>
          <template v-for="item in topList.slice(4)" :key="item.id">
            <div class="coverBottom">
              <img class="img-topList" @click="toDetail(item.id)" :src="item.coverImgUrl" alt="">
            </div>
          </template>
        </section>
      </skeleton1>
  </div>
</template>
<script setup>
import {getTopList} from '@/network/topList.js'
import {ref} from 'vue'
import {useRouter} from "vue-router";
import {useStore} from 'vuex'

let topList = ref([])
getTopList().then(res => {
  topList.value = res.data.list
})
const router = useRouter()
const store = useStore()
const toDetail = id => {
  store.dispatch('getSongList',id)
  router.push('/songDetail')
}
</script>

<style lang="less">
.coverBottom{
  width: 100%;
  position: relative;
  .img-topList{
    width: 200px;
    margin-right: 35px;
    margin-top: 20px;
    height: 200px;
    float: left;
    border-radius: 10px;
    display: block;
    &:hover{
      transition: all 1s;
      transform: translate3d(0,-10px,0);
      box-shadow: 0 3px 8px rgba(0,0,0,.8);
    }
  }
}
.topList{
  height: 100%;
  .box-card{
    display: flex;
    justify-content: flex-start;
    margin-top: 15px;
    .cover{
      img{
        border-radius: 10px;
      }
      &:hover img{
        transition: all 1s;
        transform: rotate(360deg);
      }
    }
    .cover img{
      width: 150px;
      height: 150px;
    }
    .top{
      width: 100%;
      height: 150px;
      margin-left: 30px;
      display: flex;
      flex-direction: column;
      justify-content: space-around;
      .top-item{
        height: 33.3%;
        width: 100%;
        padding: 0 20px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        &:hover{
          background: #f1ecec;
        }
        .index{
          margin-right: 20px;
          color: red;
          font-weight: 900;
        }
        .right{
          color: #656161;
        }
      }
    }
  }
}

</style>
