<template>
  <section ref="dom">
    <skeleton1 :loading="categoryRadio.length" :count="36" :image="{width:'50px',height:'50px'}" :margin="{width: '300px',marginLeft:'5px'}" :row="2" top="10px">
      <div class="hotRadio" ref="dom" v-for="item in categoryRadio" :key="item.categoryId">
          <nav @click="goDetail(i.id)" class="item" v-for="i in item.radios" :key="i.id">
            <div class="left">
              <el-image :src="i.picUrl" class="image"></el-image>
              <img class="icon" src="../../../../assets/image/play.png" alt="">
            </div>
            <div class="right">
              <div class="title">{{i.name}}</div>
              <div class="tags">
               {{i.rcmdText}}
              </div>
            </div>
          </nav>
        </div>
    </skeleton1>
  </section>
  
  
</template>

<script setup>
import {getCategoryRecommend,getProgram} from "@/network/radio.js";
import {onMounted, ref} from 'vue'
import {useRouter} from "vue-router";
import {dataLazyLoading} from "@/utlis/dataLazyLoading.js";

let dom = ref('')
let categoryRadio = ref([])
onMounted(async () => {
  await dataLazyLoading(dom)
  let res = await getCategoryRecommend()
  categoryRadio.value = res.data.data
})
const router = useRouter()
const goDetail = id => {
  router.push(`/program?id=${id}`)
}
</script>

<style scoped lang="less">
.hotRadio{
  width: 100%;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  .item{
    width: 33%;
    height: 50px;
    display: flex;
    justify-content: flex-start;
    margin-top: 10px;
    &:hover{
      background: #ededed;
    }
    .left{
      width: 50px;
      height: 50px;
      position: relative;
      .image{
        border-radius: 10px;
      }
      .icon{
        width: 20px;
        height: 20px;
        background: white;
        border-radius: 50%;
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%,-50%);
      }
    }
    .right{
      width: 80%;
      margin-left: 10px;
      display: flex;
      flex-direction: column;
      justify-content: space-evenly;
      .tags{
        color: silver;
        font-size: 13px;
      }
      .title{
        width: 100%;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
      }
    }
  }
}


</style>
