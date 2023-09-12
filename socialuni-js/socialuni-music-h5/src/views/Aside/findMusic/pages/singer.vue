<template>
  <section>
    <el-card class="box-card">
      <div><strong>语种:</strong>
        <span :class="{active:params.area === item.id}"  @click="change(item)" v-for="item in category[0]" :key="item.id" style="margin-left: 20px;">
            {{item.area }}
        </span>
      </div>
      <div class="category"><strong>分类:</strong>
        <span :class="{active:params.type === item.id}" @click="change(item)" v-for="item in category[1]" :key="item.id" style="margin-left: 20px;">
            {{item.type }}
        </span>
      </div>
      <div style="display: flex;justify-content: space-between; flex-wrap: wrap;">
        <strong>筛选:</strong>
        <span style="margin-left: 12px;" @click="change(-1)" :class="{active:params.initial === -1}">热门</span>
        <span :class="{active:params.initial === item}" @click="change(item)" v-for="(item,index) in array" :key="index" style="margin-left: 20px;">
            {{item}}
        </span>
        <span style="margin-left: 20px;" @click="change(0)" :class="{active:params.initial === 0}">#</span>
      </div>
    </el-card>
    <br>
<!--    歌手列表-->
    <skeleton2 :loading="loading" :count="10" :size="{width:'210px',height:'220px'}" :show="false">
      <footer>
        <div class="box" v-for="item in singer" :key="item.id">
          <el-image @click="getSong(item.id)" :src="item.img1v1Url" class="image"></el-image>
          <div>{{item.name}}</div>
        </div>
      </footer>
    </skeleton2>
    <br>
    <br>
    <div v-if="singer.length" style="display: flex;justify-content: center;">
      <el-pagination background layout="prev, pager, next"
                     @current-change="changePages"
                     :total="1000">
      </el-pagination>
    </div>
  </section>
</template>

<script setup>
import {getSingerTop,getSingerCategory} from '@/network/singer.js'
import {reactive,ref} from 'vue'
import {useRouter} from "vue-router";
import {useStore} from "vuex";

let singer = ref([])
let loading = ref(0)
const params = reactive({
  limit:10,
  offset:0,
  initial:-1,
  type:-1,
  area:-1
})
getSingerCategory(params).then(res => {
  singer.value = res.data.artists
  loading.value = res.data.artists.length
})
let array = ref([])
for(let i=0;i<26;i++){
  array.value.push(String.fromCharCode(65+i))
}
let category = [
  [{area:'全部',id:-1}, {area:'华语',id:7}, {area:'欧美',id:96}, {area:'日本',id:8}, {area:'韩国',id:16}, {area:'其他',id:0}],
  [{type:'全部',id:-1}, {type:'男歌手',id:1}, {type:'女歌手',id:2}, {type:'乐队',id:3}],
]
const change = value => {
  loading.value = 0
  if (Object.entries(value).flat(1)[0] === 'area'){
    params.area = value.id
  }else if(Object.entries(value).flat(1)[0] === 'type'){
    params.type = value.id
  }else {
    params.initial = value
  }
  getSingerCategory(params).then(res => {
    singer.value = res.data.artists
    loading.value = res.data.artists.length
  })
}
const changePages = value => {
  loading.value = 0
  params.offset = (value-1) * 10
  getSingerCategory(params).then(res => {
    singer.value = res.data.artists
    loading.value = res.data.artists.length
  })
}
const router = useRouter()
const store = useStore()
const getSong = id => {
  store.commit('setSingerId',id)
  router.push(`/SingerContent`)
}

</script>

<style scoped lang="less">
footer{
  width: 100%;
  overflow: hidden;
  display: grid;
  grid-template-columns: repeat(5,1fr);
  gap: 20px 0;
  .box{
    text-align: center;
  }
  .image{
    width: 210px;
    height: 220px;
    border-radius: 10px;
    &:hover{
      transition: all 1s;
      transform: translate3d(0,-5px,0);
      box-shadow: 0 3px 8px rgba(0,0,0,.8);
    }
  }
}
.active{
  color: red;
  font-weight: 900;
}
section{
  margin-top: 60px;
  .box-card{
    height: 150px;
    .category{
      margin: 30px 0;
    }
  }
  strong{
    color: rgba(49, 48, 48, 0.8);
  }
  span{
    color: #656161;
  }
}
</style>
