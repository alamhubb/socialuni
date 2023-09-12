<template>
  <header>
    <div class="left">
       <el-popover
           placement="bottom-start"
           :width="550"
           v-model:visible="isPopup"
           trigger="click">
      <template #reference>
          <div class="left"><el-button type="success" size="mini" round>{{current}}<i class="el-icon-arrow-right"></i></el-button></div>
      </template>
      <div class="title" :class="{active:current === '全部视频'}">全部视频</div>
      <el-divider></el-divider>
      <section class="category">
        <nav class="tag" v-for="item in tags"
             @click="change(item)"
             :class="{active:current === item.name}"
             :key="item.id">{{item.name}}
        </nav>
      </section>
    </el-popover>
    </div>
    <div class="right">
      <div class="rightTag" v-for="item in rightTags" :key="item.id" :class="{active:current === item.name}"  @click="change(item)">{{item.name}}</div>
    </div>
  </header>
  
  
  <skeleton :count="8" :loading="videoContent.length">
    <main class="videoContent">
      <videoCover @goDetail="goVideoDetail" :videoArray="videoContent"/>
  </main>
  </skeleton>
  <footer class="bottom-pages">
     <el-pagination background layout="prev, pager, next"
                    @current-change="changePage"
                    :total="1000">
     </el-pagination>
  </footer>
</template>

<script setup>
import skeleton from '../children/skeleton.vue'
import {useRouter} from "vue-router";
import videoCover from '../children/videoCover.vue'
import {getVideoAllTag,getVideoTags,getAllVideo,getCategoryVideo,getRecommendVideo} from "@/network/video.js";
import {ref} from 'vue'
//跳转视频详情
const router = useRouter()
const goVideoDetail = id => {
  router.push(`/videoDetail?vid=${id}`)
}
//视频标签
let isPopup = ref(false)
let tags = ref([])
let current = ref('全部视频')
getVideoAllTag().then(res => {
  tags.value = res.data.data
})
let rightTags = ref([])
getVideoTags().then(res => {
  rightTags.value = res.data.data
})
//全部视频
let videoContent = ref([])
getAllVideo().then(res => {
  videoContent.value = res.data.datas
})
//标签改变获取视频
const params = {
  id:'',
  offset:0
}
const change = item => {
  params.id = item.id
  isPopup.value = false
  current.value = item.name
  //分类视频
  getCategoryVideo(params).then(res => {
    videoContent.value = res.data.datas
  })
}
const changePage = value => {
  params.offset = value
  if (params.id){
    getCategoryVideo(params).then(res => {
      videoContent.value = res.data.datas
    })
  }else {
    getAllVideo(value).then(res => {
      videoContent.value = res.data.datas
    })
  }
}
</script>

<style scoped lang="less">
.bottom-pages{
  width: 100%;
  display: flex;
  justify-content: center;
  margin-top: 50px;
}
.videoContent{
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  height: 500px;
}
header{
  width: 100%;
  display: flex;
  justify-content: space-between;
  .left{
    width: 40%;
  }
  .right{
    width: 60%;
    display: flex;
    justify-content: space-between;
    .rightTag{
      width: 80px;
      text-align: center;
      line-height: 30px;
      color: #656161;
      height: 30px;
      &:hover{
        color: #000;
      }
    }
  }

}
.active{
  border-radius: 30px;
  background: #fcebeb;
  color: red !important;
}
.title{
  width: 80px;
  height: 30px;
  text-align: center;
  line-height: 30px;
}
.category{
  width: 100%;
  height: 400px;
  overflow-y: auto;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  .tag{
    width: 80px;
    height: 30px;
    margin-top: 10px;
    text-align: center;
    line-height: 30px;
    color: rgba(49, 48, 48, 0.6);
    &:hover{
      color: red;
    }
  }
}
</style>
