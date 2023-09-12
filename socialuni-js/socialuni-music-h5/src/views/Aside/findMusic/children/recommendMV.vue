<template>
  <br>
  <titleTop @click="$router.push('/video')">推荐MV</titleTop>
  <section  ref="dom">
    <el-skeleton style="width: 100%; display: flex;justify-content: space-between;" animated :count="4" :loading="!Boolean(dataMv.length)">
    <template #template>
      <div style="width: 24%;">
        <el-skeleton-item variant="image" style="width:100%; height: 200px;" />
        <br>
        <el-skeleton-item variant="p" style="width:100%;"/>
      </div>
    </template>
     <template #default>
       <div class="mv">
          <div class="box" v-for="item in dataMv" :key="item.id" @click="goDetail(item.id)">
            <el-image :src="item.picUrl" class="img"></el-image>
            <div class="label">
                {{item.name}}
              <div style="color: #bebbbb;"><template v-for="i in item.artists">{{i.name }}</template></div>
            </div>
            <i class="el-icon-caret-right"><span>{{item.playCount}}</span></i>
          </div>
        </div>
     </template>
  </el-skeleton>
  </section>
</template>

<script setup>
import {onMounted,ref} from "vue";
import {getRecommendMV} from '@/network/mv.js'
import {dataLazyLoading} from "@/utlis/dataLazyLoading.js";
import {useRouter} from "vue-router";

let dataMv = ref([])
let dom = ref('')
const emit = defineEmits(['load'])
onMounted(async () => {
  await dataLazyLoading(dom)
  let res = await getRecommendMV()
  dataMv.value = res.data.result
})
const router = useRouter()
const goDetail = id => {
  router.push(`videoDetail?id=${id}`)
}
</script>

<style scoped lang="less">
.mv{
  width: 100%;
  height: 270px;
  display: flex;
  justify-content: space-between;
  .box{
    width: 24%;
    height: 200px;
    position: relative;
    &:hover{
      transition: all 1s;
      transform: translate3d(0,-5px,0);
      box-shadow: 10px 10px 10px rgba(0,0,0,.8);
    }
    i{
      position: absolute;
      right: 10px;
      font-size: 20px;
      top: 3px;
      color: #f1ecec;
      span{
        font-size: 14px;
      }
    }
    .label{
      width: 100%;
      height: 50px;
      text-align: center;
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
      margin-top: 5px;
    }
    .img{
      width: 100%;
      height: 100%;
      border-radius: 10px;
    }
  }
  
}
</style>
