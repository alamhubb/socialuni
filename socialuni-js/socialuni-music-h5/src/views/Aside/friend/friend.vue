<template>
     <h3 class="h3">动态</h3>
     <el-button type="danger" size="medium" @click="send" round icon="el-icon-edit-outline">发动态</el-button>
     <el-divider content-position="right">网易云动态</el-divider>
  <skeleton1 :count="8" :loading="event.length" :image="{width:'90px',height:'90px'}" :row="1" show>
     <el-card shadow="hover" style="margin-top: 15px;" v-for="item in event" :key="item.id">
       <section class="box-card">
          <aside><el-avatar :size="50" :src="item.user.avatarUrl"></el-avatar></aside>
          <main>
            <div><el-link type="primary">{{item.user.nickname}}</el-link> <span class="time">分享单曲</span></div>
            <div class="time" style="margin: 5px 0;">{{$formatTime(item.showTime)}}</div>
            <div class="text">{{JSON.parse(item.json).msg}}</div>
          </main>
       </section>
     </el-card>
  </skeleton1>
</template>

<script setup>
import {getFriend} from "@/network/user.js";
import {getSongDetail} from "@/network/song.js";
import {ref} from 'vue'
import {ElMessage} from "element-plus";


let event = ref([])
getFriend().then(res => {
  event.value = res?.data.event
})
const send = () => {
  ElMessage.warning({
    type:'warning',
    message:'暂无接口'
  })
}
</script>

<style scoped lang="scss">
.h3{
  display: inline-block;
  margin-right: 10px;
}
.box-card{
  display: flex;
  justify-content: flex-start;
  main{
    margin-left: 10px;
    .time{
      font-size: 14px;
      color: #bebbbb;
    }
    .text{
      color: rgb(101, 97, 97);
    }
  }
}
</style>
