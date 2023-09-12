<template>
  <titleTop>{{title}}</titleTop>
  <section class="videoDetail">
    <div class="left">
      <div><video class="video" preload controls :src="url"></video></div>
      <div class="info">
        <el-avatar :size="50" :src="info.photo"></el-avatar>
        <span style="margin-left: 10px;">{{info.person}}</span>
      </div>
      <br>
      <el-collapse style="width: 90%;">
        <el-collapse-item>
          <template #title>
            <h2 style="margin-left: 10px;">{{info.name}}</h2>
            <span style="margin-left: 10px; color: silver;">发布: {{info.time}}</span>
            <span style="margin-left: 10px; color: silver;">播放: {{$formatNumber(info.count)}}</span>
          </template>
          <div>{{info.desc}}</div>
        </el-collapse-item>
      </el-collapse>
      <br>
       <div>
        <el-button size="medium"  round> <i style="font-size: 15px;" class="iconfont icon-zan">赞({{$formatNumber(info.count)}})</i></el-button>
        <el-button size="medium" round icon="el-icon-folder-add">收藏({{$formatNumber(info.subCount)}})</el-button>
        <el-button size="medium" round icon="el-icon-share">分享({{$formatNumber(info.shareCount)}})</el-button>
        <el-button size="medium" round icon="el-icon-upload">下载</el-button>
      </div>
<!--      评论数据-->
      <div style="width: 90%;">
        <mvComment @pageChange="pageChange" :comment="comment"/>
      </div>
      
    </div>
    <div class="right" v-if="similarMv.length">
      <span style="font-weight: 900;">相关推荐</span>
      <div class="item" v-for="item in similarMv" :key="item.id || item.vid">
         <mvCover :image="item.cover || item.coverUrl"
                  :count="item.playCount || item.playTime"
                  :label="item.artists ||item.creator"
                  :time="item.duration"
                  :name="item.name || item.title"/>
      </div>
    </div>
    <div class="right" v-else>
      <skeleton1 :count="5" :image="{width:'187px',height:'110px'}" :margin="{width: '300px',marginLeft:'10px'}"></skeleton1>
    </div>
  </section>
</template>

<script setup>
import mvComment from './children/mvComment.vue'
import mvCover from '@/views/Aside/video/children/mvCover.vue'
import {getVideoUrl,getVideoDetail,getMvDetail,getMvUrl,getSimilarMv,getSimilarVideo,getMvComment,getVideoComment} from "@/network/video.js";
import {useRoute, useRouter} from "vue-router";
import {ref,watch} from 'vue'
import {formatMvInfo,formatVideoInfo} from "@/utlis/formatData.js";


const route = useRoute()
const router = useRouter()

let id = route.query.vid || route.query.id
let url = ref('')
let title = ref('MV详情')
let similarMv = ref([])
let info = ref({})
let comment = ref([])

if (Object.keys(route.query)[0] === 'vid'){
  title.value = '视频详情'
  //视频数据
  getVideoDetail(id).then(res => {
    info.value = formatVideoInfo(res)
  })
  getVideoUrl(id).then(res => {
    url.value = res.data.urls[0].url
  })
  getSimilarVideo(id).then(res => {
    similarMv.value = res.data.data
  })
  getVideoComment(id).then(res => {
    comment.value = res.data.comments
  })
  //mv数据
}else {
  getMvDetail(id).then(res => {
    info.value = formatMvInfo(res)
  })
  getMvUrl(id).then(res => {
    url.value = res.data.data.url
  })
  getSimilarMv(id).then(res => {
    similarMv.value = res.data.mvs
  })
  getMvComment(id).then(res => {
    comment.value = res.data.hotComments
  })
}

const pageChange = page => {
  if (Object.keys(route.query)[0] === 'id'){
    getMvComment(id,page).then(res => {
      comment.value = res.data.comments
    })
  }else {
    getVideoComment(id,page).then(res => {
      comment.value = res.data.comments
    })
  }
}
</script>
<script>
export default {
  name:'videoDetail'
}
</script>
<style scoped lang="less">
.videoDetail{
  display: flex;
  justify-content: space-between;
  .left{
    width: 60%;
    .video{
      width: 650px;
    }
    .info{
      display: flex;
      align-items: center;
      margin-top: 10px;
    }
  }
  .right{
    width: 40%;
    .item{
      width: 99%;
      height: 110px;
      margin-top: 15px;
    }
  }
}

</style>
