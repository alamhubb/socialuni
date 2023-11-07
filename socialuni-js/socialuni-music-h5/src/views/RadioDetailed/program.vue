<template>
    <skeleton1 :loading="detail.length" :image="{width:'220px',height:'220px'}" :row="5">
        <header>
        <div><el-image class="cover" :src="detail[0].picUrl" alt="img"></el-image></div>
        <div class="content">
          <div class="A"><el-tag type="danger" size="mini">播客</el-tag><h2>{{detail[0].name}}</h2></div>
          <div class="B">
            <el-avatar class="img" :size="30" :src="detail[0].dj.avatarDetail?.identityIconUrl"></el-avatar>
            <a href="">{{detail[0].dj.nickname}}</a>
            <span style="margin-left: 10px;">{{$formatTime(detail[0].createTime)}} 创建</span>
          </div>
          <div class="C">
            <el-button size="medium" type="danger" round icon="el-icon-caret-right">播放全部</el-button>
            <el-button size="medium" round icon="el-icon-folder-add">收藏全部</el-button>
            <el-button size="medium" round icon="el-icon-share">分享</el-button>
          </div>
          <div class="D">
            <p><span class="tags"><el-tag type="danger" size="mini">{{detail[0].secondCategory}}</el-tag></span></p>
            <p>电台:{{detail[0].programCount}} 播放: {{$formatNumber(detail[0].subCount)}}</p>
            <p v-if="detail[0].desc ? detail[0].desc < 80 : '' ">{{detail[0].desc}}</p>
            <el-collapse v-else>
              <el-collapse-item title="点击展开更多">
                <p>{{detail[0].desc}}</p>
              </el-collapse-item>
            </el-collapse>
          </div>
        </div>
      </header>
    </skeleton1>
  <br>
  <el-divider content-position="left"><h2>声音</h2></el-divider>
  <nav class="item">
    <el-row class="item-newSong" align="middle" @dblclick="current(item,index)" v-for="(item,index) in programs" :key="item.id">
      <el-col :span="1">
        <div style="margin-left: 10px;">
          <span v-if="item.id === $store.state.songDetail.songDetail.id" class="iconfont icon-yangshengqi"></span>
          <span v-else>{{index+1}}</span>
        </div>
      </el-col>
      <el-col :span="2">
        <div class="cover">
          <el-image :src="item.al.picUrl" class="image"></el-image>
          <img class="icon" src="../../assets/image/play.png" alt="">
        </div>
      </el-col>
      <el-col :span="10">
        <div>{{item.name}}</div>
      </el-col>
      <el-col :span="5">
        <div class="label">{{item.label}}</div>
      </el-col>
      <el-col :span="5">
        <div class="label">{{$formatTime(item.album).slice(0,10)}}</div>
      </el-col>
      <el-col :span="1">
        <div class="label">{{$formatTime(item.dt).slice(-5)}}</div>
      </el-col>
    </el-row>
  </nav>
</template>

<script setup>
import {getProgram,getDjDetail} from "@/network/radio.js";
import {useRoute} from "vue-router";
import {ref,computed} from 'vue'
import eventbus from '@/utlis/eventbus.js'
import {useStore} from "vuex";

const route = useRoute()
//电台节目列表
const store = useStore()
let programs = computed(() => store.state.songDetail.songArray)
getProgram(route.query.id).then(res => {
  let mapArray = res.data.programs.map(item => {
    return{
      al:{picUrl:item.coverUrl},
      id:item.mainSong.id,
      name:item.name,
      dt:item.mainSong.duration,
      label:item.channels[0],
      album:item.createTime
    }
  })
  store.commit('setSongMusic',mapArray)
})
let currentIndex = ref()
const current = (item,index) => {
  currentIndex.value = index
  store.commit('setSongDetail',item)
  store.commit('play',index)
  eventbus.emit('playMusic')
}
//电台详情
let detail = ref([])
getDjDetail(route.query.id).then(res => {
  detail.value.push(res.data.data)
})
</script>
<script>
export default {
  name:'program'
}
</script>
<style scoped lang="scss">
.iconfont{
  color: red;
}
.label{
  color: #656161;
}
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

.item-newSong{
  margin-top: 5px;
}
.item :hover{
  background: #ededed;
  border-radius: 10px;
}
.cover{
  width: 80px;
  height: 80px;
  position: relative;
  .icon{
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%,-50%);
    width: 30px;
    height: 30px;
    background: white;
    border-radius: 50%;
  }
  .image{
    width: 80px;
    height: 80px;
    border-radius: 10px;
  }
}
</style>
