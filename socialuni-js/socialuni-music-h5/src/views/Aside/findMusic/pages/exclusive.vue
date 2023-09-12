<template>
<!--专属定制-->
  <header>
    <skeleton1 :loading="songList.length">
      <songListHeader :songList="songList"/>
    </skeleton1>
  </header>
  <br>
  <div class="title">
    <span>精品歌单</span>
    <!--  弹出框-->
    <el-popover
        v-model:visible="isShow"
        placement="bottom"
        :width="530"
        trigger="click">
      <template #reference>
        <el-button type="success" round size="small" icon="el-icon-cold-drink">{{selectTile}}</el-button>
      </template>
      <p class="all" @click="select(undefined,-1)" :class="{active:current === -1}" style="padding: 0 40px;">全部歌单</p>
      <el-divider></el-divider>
      <ul class="tags">
        <li class="tag" :class="{active:current === index}"
            v-for="(item,index) in tags" :key="item.id"
            @click="select(item.name,index)">
          {{item.name}}
        </li>
      </ul>
    </el-popover>
  </div>
<!--  歌单列表-->
    <skeleton1 :count="12" :loading="songList.length" :width="{width:'33%'}" :margin="{width:'222px',marginLeft: '10px'}">
      <main class="main">
          <section @click="toSongList(item.id)" class="cover" v-for="item in songList" :key="item.id">
            <div class="left">
              <img class="img" :src="item.coverImgUrl" :alt="item.name">
              <i class="iconfont icon-bofang"></i>
              <div class="right-top">
                <span class="el-icon-caret-right"></span>
                <span style="color: white;">{{$formatNumber(item.playCount)}}</span>
              </div>
            </div>
            <div class="label">
              <p class="name">{{item.name }}</p>
              <p class="tags">{{item.creator.nickname}}</p>
              <p class="tags"><el-tag size="mini" type="danger">{{item.tag}}</el-tag>{{item.copywriter}} </p>
            </div>
          </section>
        </main>
    </skeleton1>

  <div @click="load" v-if="songList.length">
    <el-affix target=".main" :offset="60">
      <el-divider>点击加载更多</el-divider>
    </el-affix>
  </div>
</template>

<script setup>
console.log(2)
import {useRouter} from "vue-router";
import songListCover from '../children/songListCover.vue'
import {getExclusiveSong,getSongListTags} from '@/network/songList.js'
import {onMounted,computed,reactive,ref} from "vue";
import {useStore} from "vuex";
import songListHeader from '../children/songListHeader.vue'

const store = useStore()
const userID = computed(() => store.state.login.profile.userId)

let songList = ref([])
const params = reactive({
  cat:'',
  limit:12,
  before:undefined
})
function getSongList(boolean){
  getExclusiveSong(params).then(res => {
      if (boolean){
        songList.value= res.data.playlists
      }else {
        songList.value.push(...res.data.playlists)
      }
      let len = res.data.playlists.length
      params.before = res.data.playlists[len -1].updateTime
  })
}
getSongList()
//加载更多
const load = () => {
  getSongList()
}
//精品标签
let tags = ref([])
let isShow = ref(false)
let current = ref(-1)
let selectTile = ref('全部歌单')
getSongListTags().then(res => {
  tags.value = res?.data.tags
})
const select = (tag,index) => {
  isShow.value = false
  if (index === -1){
    selectTile.value = '全部歌单'
  }else {
    selectTile.value = tag
  }
  params.cat = tag
  params.before = ''
  current.value = index
  getSongList(true)
}
//点击精品歌单跳转
const router = useRouter()
const toSongList = id => {
  store.dispatch('getSongList',id)
  router.push('/songDetail')
}
</script>

<style scoped lang="less">
.active{
  color: red;
  font-weight: 900 !important;
}
header{
    margin-top: 60px;
}
.title{
  margin-bottom: 20px;
  width: 100%;
  height: 30px;
  line-height: 30px;
  display: flex;
  justify-content: space-between;
  span{
    font-weight: 900;
    font-size: 20px;
  }
}

main{
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
}

.cover{
  width: 32%;
  display: flex;
  justify-content: flex-start;
  height: 160px;
  .left{
    position: relative;
    &:hover .iconfont{
      opacity: 1;
      transition: all 1s;
    }
    .iconfont{
      position: absolute;
      right: 10px;
      bottom: 10px;
      font-size: 30px;
      opacity: 0;
      color: white;
    }
    .right-top{
      font-size: 12px;
      display: flex;
      align-items: center;
      position: absolute;
      right: 5px;
      top: 1px;
    }
    .el-icon-caret-right{
      color: white;
      font-size: 25px;
    }
  }
  .label{
    margin-left: 10px;
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    p{
      color: #625c5c; font-size: 15px;
    }
    .tags{
      font-size: 12px;
    }
  }
  .img{
    width: 150px;
    height: 150px;
    display: block;
    border-radius: 10px;
  }
}
.all{
  font-weight: 600;
  &:hover{
    color: red;
  }
}
.tags{
  display: flex;
  justify-content: flex-start;
  flex-wrap: wrap;
  .tag{
    width: 20%;
    height: 45px;
    font-weight: 500;
    &:hover{
      color: red;
    }
  }
}
</style>
