<template>
  <header>
    <div>
      <el-popover
          v-model:visible="isShow"
          placement="bottom-start"
          :width="650"
          trigger="click">
        <template #reference>
          <el-button type="success" size="small" round>
            {{title}}
            <i class="el-icon-arrow-right el-icon--right"></i>
          </el-button>
        </template>
        <strong style="margin-left: 10px;"
                :class="{active:title === '全部歌单'}"
                @click="changeTag('全部歌单')">
          全部歌单
        </strong>
        <el-divider></el-divider>
          <nav v-if="tags[0].length">
            <main v-for="(item,index) in tags" :key="index">
              <div class="left">
                <i :class="item[0].icon"></i>
                <span>{{item[0].label}}</span>
              </div>
              <div class="right">
                  <div :class="{active:title === v.name}" @click="changeTag(v.name)" v-for="(v,i) in item" :key="i" class="tag">
                    {{v.name}}<span style="font-size: 10px;color: red;" v-if="v.is">hot</span>
                  </div>
              </div>
            </main>
          </nav>
      </el-popover>
    </div>
    <div><span v-for="item in hotTags" :key="item.id" @click="changeTag(item.name)">{{item.name}}</span></div>
  </header>
  <section>
    <template v-if="songMenu.length" class="cover">
      <coverPicture
          v-for="item in songMenu" :key="item.id"
          @click="toDetail(item.id)"
          :label="item.name"
          :user="item.creator.nickname"
          :playCount="item.playCount"
          :image="item.coverImgUrl">
      </coverPicture>
    </template>
    
    <template v-else class="cover">
      <el-skeleton v-for="item in 15" style="width: 220px; margin-top: 10px;">
        <template #template>
          <el-skeleton-item variant="image" style="width: 220px; height: 220px;" />
          <div style="margin-top: 10px;">
            <el-skeleton-item variant="h1"/>
            <el-skeleton-item variant="h1"/>
          </div>
        </template>
      </el-skeleton>
    </template>
  </section>
  <br>
  <footer>
    <el-pagination @current-change="change"
                   background layout="prev, pager, next"
                   :total="1300">
    </el-pagination>
  </footer>
</template>

<script setup>
import {getSongMenu,getSongMenuCategory,getSongMenuHotCategory} from '@/network/songList.js'
import {ref} from 'vue'
import {useRouter} from "vue-router";
import {useStore} from "vuex";
//热门分类
let hotTags = ref([])
getSongMenuHotCategory().then(res => {
  hotTags.value = res.data.tags
})

// 全部分类
let isShow = ref(false)
let title = ref('全部歌单')
let tags = ref([[],[],[],[],[]])
getSongMenuCategory().then(res => {
   res.data.sub.forEach(item => {
      if (item.category === 0){
        tags.value[0].push({name:item.name,is:item.hot,icon:'iconfont icon-wangluo',label:'语种'})
      }else if (item.category === 1){
        tags.value[1].push({name:item.name,is:item.hot,icon:'iconfont icon-fengge',label:'风格'})
      }else if (item.category === 2){
        tags.value[2].push({name:item.name,is:item.hot,icon:'iconfont icon-kafei',label:'场景'})
      }else if (item.category === 3){
        tags.value[3].push({name:item.name,is:item.hot,icon:'iconfont icon-iconweixiao',label:'情感'})
      }else {
        tags.value[4].push({name:item.name,is:item.hot,icon:'iconfont icon-fenlei',label:'主题'})
      }
   })
})
const changeTag = tag => {
  title.value = tag
  params.cat = tag
  isShow.value = false
  getSongMenu(params).then(res => {
    let {playlists} = res.data
    songMenu.value = playlists
  })
}
//歌单列表
let params = {
  order:'hot',
  cat:'全部',
  limit:15,
  offset:0
}
let songMenu = ref([])
let count = ref()
getSongMenu(params).then(res => {
  let {playlists,total} = res.data
  songMenu.value = playlists
  count.value = total
})
const change = value => {
  params.offset = value
  getSongMenu(params).then(res => {
    let {playlists} = res.data
    songMenu.value = playlists
  })
}
const router = useRouter()
const store = useStore()
const toDetail = id => {
  store.dispatch('getSongList',id)
  router.push('/songDetail')
}
</script>

<style scoped lang="less">
.active{
  font-weight: 900;
  color: red!important;
  transition: all 1s;
}
nav{
  width: 100%;
  main{
    width: 100%;
    display: flex;
    justify-content: flex-start;
    margin-top: 10px;
    .left{
      width: 20%;
      height: 100%;
      display: flex;
      justify-content: flex-start;
      align-items: center;
      margin-top: -10px;
      span{
        margin-left: 10px;
      }
    }
    .right{
      width: 80%;
      height: 100%;
      display: flex;
      justify-content: flex-start;
      align-items: center;
      flex-wrap: wrap;
      .tag{
        width: 16.6%;
        height: 50px;
        color: rgb(101, 97, 97);
        &:hover{
          color: red;
          transition: all 1s;
          font-weight: 600;
        }
      }
    }
  }
}
header{
  margin: 60px 0 20px 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  div{
    width: 60%;
    display: flex;
    justify-content: space-between;
    span{
      color: #656161;
      &:hover{
        color: pink;
        transform: scale(1.1);
        transition: all 1s;
      }
    }
  }
}
section{
  display: grid;
  grid-template-columns: repeat(5,1fr);
  .cover{
    margin-top: 15px;
  }
}
footer{
  display: flex;
  justify-content: center;
}
</style>
