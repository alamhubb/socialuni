<template>
  <el-skeleton :loading="!Boolean(banner.length)" :count="3" :animated="true" style="display: flex;justify-content: space-between;">
        <template #template>
          <el-skeleton-item variant="image" style="width: 32%; height: 150px;"/>
        </template>
        <template #default>
          <div class="box">
            <el-image class="banner" :src="item.pic" v-for="item in banner" :key="item.id"></el-image>
          </div>
        </template>
  </el-skeleton>
      <br>
    <el-card shadow="always" style="height: 170px;">
      <template #header>
        <span>分类列表</span>
      </template>
      <el-skeleton :loading="!Boolean(category.length)" :count="18" :animated="true" style="display: flex;justify-content: space-between;">
          <template #template>
            <div>
              <el-skeleton-item variant="image" style="width: 50px; height: 50px;"/>
              <el-skeleton-item variant="p" style="width: 50px; margin-top: 5px;"/>
            </div>
          </template>
          <template #default>
            <div class="category">
              <div @click="goCategoryList(item.id,item.name)" class="item" v-for="item in category" :key="item.id">
                <div><el-image :src="item.pic56x56Url"></el-image></div>
                <div class="tags">{{item.name}}</div>
              </div>
            </div>
          </template>
        </el-skeleton>
    </el-card>
  <br>
  <headerRadio/>
  <br>
  <el-card shadow="always" style="height: 340px;">
    <template #header>
      <span>猜你喜欢</span>
    </template>
    <skeleton2 :count="6" :loading="recommend.length">
    <section class="like">
      <div @click="playLike(item)" v-for="item in recommend" :key="item.id">
        <radio :image="item.picUrl" :name="item.name"
               :count="item.program.adjustedPlayCount">
        </radio>
      </div>
    </section>
    </skeleton2>
  </el-card>
  <br>
<!--  热门电台-->
  <el-card shadow="always" style="height: 820px;">
    <template #header>
      <span>本周上新</span>
    </template>
    <hotRadio></hotRadio>
  </el-card>
  <br>
<!--  付费精选-->
  <payRadio></payRadio>
</template>
<script setup>
import headerRadio from '../children/headerRadio.vue'
import hotRadio from '../children/hotRadio.vue'
import payRadio from '../children/payRadio.vue'
import {getCategory,getRadio,getDjBanner} from '@/network/radio.js'
import {onMounted,ref} from "vue";
import {useRouter} from "vue-router";
import {useStore} from "vuex";

let banner = ref([])
getDjBanner().then(res => {
  banner.value = res.data.data
})
// 推荐电台
let recommend = ref([])
getRadio().then(res => {
  recommend.value = res.data.result
})
//分类
let category = ref([])
onMounted(async () => {
  let res = await getCategory()
  category.value = res.data.categories
})
const router = useRouter()
const goCategoryList = (id,name) => {
  router.push(`/categoryList?id=${id}&name=${name}`)
}
let object = {
  al:{picUrl:''},
  dt:'',
  id:'',
  name:''
}
//播放喜欢
const store = useStore()
const playLike = item => {
  object.al.picUrl = item.picUrl
  let {duration,id,name} = item.program.mainSong
  object.dt = duration
  object.id = id
  object.name = name
  store.commit('setSongDetail',object)
  store.commit('play',0)
}

</script>
<script>
export default {
  name:'podcastContent'
}
</script>
<style scoped lang="scss">
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.box{
  display: flex;
  height: 150px;
  justify-content: space-between;
  .banner{
    width: 32%;
    border-radius: 10px;
  }
}
.like{
  width: 100%;
  display: flex;
  justify-content: space-between;
}
.category{
  display: flex;
  justify-content: space-between;
  height: 70px;
  text-align: center;
  .tags{
    font-size: 12px;
    color: silver;
  }
  .item{
    width: 50px;
    height: 50px;
  }
}
</style>
