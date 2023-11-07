<template>
  <skeleton1 :margin="{width: '900px',marginLeft:'20px'}" :loading="songList.boolean" :image="{width:'220px',height:'220px'}" :row="5">
    <header>
      <div>
        <el-image class="cover" :src="songList?.coverImgUrl" alt="img"></el-image>
      </div>
      <div class="content">
        <div class="A"><el-tag type="danger" size="mini">歌单</el-tag><h2>{{songList.name}}</h2></div>
        <div class="B">
          <el-avatar v-if="songList.creator.avatarUrl" class="img" :size="30" :src="songList?.creator?.avatarUrl "></el-avatar>
          <a href="">{{songList?.creator.nickname}}</a>
          <span style="margin-left: 10px;">{{$formatTime(songList.createTime)}} 创建</span>
        </div>
          <div class="C">
            <el-button @click="playAll" size="medium" type="danger" round icon="el-icon-caret-right">播放全部</el-button>
            <el-button size="medium" round icon="el-icon-folder-add" @click="like">收藏全部({{$formatNumber(songListData?.bookedCount) || '888'}})</el-button>
            <el-button size="medium" round icon="el-icon-share">分享({{songListData?.shareCount || '999'}})</el-button>
            <el-button size="medium" round icon="el-icon-upload">VIP下载</el-button>
          </div>
        <div class="D">
          <p>标签: <span class="tags" v-for="(item,index) in songList.tags" :key="index">{{item}} / </span></p>
          <p>歌曲:{{songList?.trackCount}} 播放: {{$formatNumber(songList?.playCount)}}</p>
          <p v-if="songList.description ? songList.description.length < 80 : '' ">{{songList.description}}</p>
          <el-collapse v-else>
            <el-collapse-item title="点击展开更多">
              <p>{{songList?.description}}</p>
            </el-collapse-item>
          </el-collapse>
        </div>
      </div>
    </header>
  </skeleton1>
</template>

<script setup>
import {computed, watch,ref} from "vue";
import {useStore} from "vuex";
import { ElMessage } from 'element-plus'
import eventbus from "@/utlis/eventbus.js";
import {debounce} from "@/utlis/debounce.js";
import {throttle} from "@/utlis/throttle.js";

const store = useStore()
const songList = computed(() => store.state.songDetail.songList)
const songListData = computed(()=> store.state.songDetail.commentData)

const like = debounce(() => {
      const id = store.state.songDetail.commentID
      ElMessage({
        message: '收藏成功!',
        center: true,
        type:'success'
      });
})

const playAll = throttle(() => {
  eventbus.emit('playAll1')
},1)
</script>

<style scoped lang="scss">
header{
  display: flex;
  justify-content: flex-start;
  padding: 10px;
  width: 100%;
  .cover{
    display: block;
    height: 220px;
    width: 220px;
    border-radius: 10px;
  }
  .content{
    margin-left: 20px;
    .A{
      width: 100%;
      height: 40px;
      display: flex;
      align-items: center;
      h2{
        display: inline;
        margin-left: 10px;
      }
    }
    .B{
      width: 100%;
      height: 50px;
      display: flex;
      align-items: center;
      span{
        font-size: 14px;
        color: #748aad;
      }
      a{
        margin: 0 7px;
        text-decoration:none
      }
    }
    .D{
      margin-left: 5px;
      .tags{
        color: #85b9c8;
      }
      p{
        font-size: 12px;
        color: #656161;
      }
    }

  }
}
</style>
