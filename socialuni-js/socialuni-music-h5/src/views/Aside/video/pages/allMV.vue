<template>
  <el-card>
    <div class="tags" v-for="(item,index) in tags" :key="index">
      <div style="margin-top: 10px;">{{item.type}}:</div>
      <div class="item" :class="{active:params[item.params] === v.value}"
           @click="change(v)" v-for="(v,i) in item.tag" :key="i">{{v.name}}
      </div>
    </div>
  </el-card>
  <br>
  <skeleton :count="12" :loading="allMvData?.length">
      <section style="display: flex;justify-content: space-between;flex-wrap: wrap; overflow: auto;">
        <allMvCover :videoArray="allMvData"/>
      </section>
  </skeleton>
  <el-divider @click="loading">点击加载更多</el-divider>
</template>

<script setup>
import skeleton from '../children/skeleton.vue'
import {getAllMv} from '@/network/video.js'
import allMvCover from '../children/allMvCover.vue'
import {ref,computed,reactive} from 'vue'

let params = reactive({
  area:'',
  type:'',
  order:'',
  limit:12,
  offset:0
})
let allMvData = ref([])
getAllMv(params).then(res => {
  allMvData.value = res.data.data
})
const loading = () => {
  params.offset += 12
  getAllMv(params).then(res => {
    allMvData.value.push(...res.data.data)
  })
}
const change = value => {
  if (value.type === 0){
    params.area = value.value
  }else if(value.type === 1){
    params.type = value.value
  }else {
    params.order = value.value
  }
  getAllMv(params).then(res => {
    allMvData.value = res.data.data
  })
}
let tags = [
  {params:'area',type:'地区',tag:[{type:0,value:'',name:'全部'}, {type:0,value:'内地',name:'内地'}, {type:0,value:'港台',name:'港台'}, {type:0,value:'欧美',name:'欧美'}, {type:0,value:'日本',name:'日本'}, {type:0,value:'韩国',name:'韩国'}]},
  {params:'type',type:'类型',tag:[{type:1,value:'',name:'全部'}, {type:1,value:'官方版',name:'官方版'}, {type:1,value:'原生',name:'原生'}, {type:1,value:'现场版',name:'现场版'}, {type:1,value:'网易出品',name:'网易出品'}]},
  {params:'order',type:'排序',tag:[{type:2,value:'',name:'上升最快'},{type:2,value:'最热',name:'最热'}, {type:2,value:'最新',name:'最新'}]}
]
</script>

<style scoped lang="less">
.tags{
  display: flex;
  justify-content: flex-start;
  .item{
    width: 100px;
    text-align: center;
    margin-top: 10px;
  }
}
.active{
  color: red;
}
.list{
  border: 1px solid red;
  li{
    width: 100%;
    height: 50px;
    background: pink;
    margin-top: 20px;
  }
}
</style>
