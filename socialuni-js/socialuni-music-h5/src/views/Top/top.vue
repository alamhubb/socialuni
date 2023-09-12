<template>
  <header>
    <el-row :gutter="22">
      <el-col :span="$store.state.boolean ? 1 : 3">
        <img v-if="!$store.state.boolean" class="logo" src="../../assets/image/logo-top2.png" alt="logo">
        <svg v-else class="svg" aria-hidden="true"><use xlink:href="#icon-wangyiyunyinle"></use></svg>
      </el-col>
      <el-col :span="6">
        <!--  搜索弹层-->
        <el-popover
            placement="bottom"
            :width="320"
            v-model:visible="isSearchPopup"
            trigger="focus">
          <template #reference>
            <el-input placeholder="请输入内容" clearable v-model.trim="value" @keydown.enter="search" class="input-with-select">
              <template #append>
                <el-button @click="search" icon="el-icon-search"></el-button>
              </template>
            </el-input>
          </template>
<!--          搜索弹出层-->
          <searchPopup :keyword="value"></searchPopup>
        </el-popover>
      </el-col>
      <el-col :span="1">
        <i class="iconfont icon-maikefeng"></i>
      </el-col>
      <el-col class="topContent"  :span="$store.state.boolean ? 13 : 11"  style="text-align: center;">
      <span class="name">{{name || $store.state.songDetail.songDetail.name}}</span>
      </el-col>
      <el-col :span="3">
        <el-dropdown v-if="profile">
          <span class="el-dropdown-link" @click="userLogin">
            <el-avatar style="vertical-align: middle;" :size="40" :src="profile.avatarUrl"></el-avatar>
            <span style="margin-left: 5px; font-size: 15px;">{{profile.nickname}}</span>
          </span>
          <template #dropdown>
            <el-dropdown-menu>
               <el-dropdown-item @click="upload1">设置</el-dropdown-item>
              <el-dropdown-item @click="logout">退出</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
        
        <span v-else class="el-dropdown-link" @click="userLogin">
            <el-avatar style="vertical-align: middle;" :size="40" src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"></el-avatar>
            <span style="margin-left: 5px; font-size: 15px;">请登录</span>
          </span>
      </el-col>
    </el-row>
  </header>
  
  <login ref="instance"></login>
  
<!--  头像上传-->
  <upload ref="uploadElement"/>
</template>

<script setup>
import upload from './children/upload.vue'
import eventBus from '@/utlis/eventbus.js'
import {getLogout} from "@/network/login.js";
import {getSearchResult} from "@/network/search.js";
import {ref,defineAsyncComponent,computed} from 'vue'
import {useRouter} from "vue-router";
import {useStore} from "vuex";
const router = useRouter()

const searchPopup = defineAsyncComponent(() => import('./children/searchPopup.vue'))
const login = defineAsyncComponent(() => import('./children/login.vue'))

let value = ref()
const instance = ref()
const logout = () => {
  getLogout().then(res => {
    console.log(res)
  })
  localStorage.clear();
  router.go(0)
}

const userLogin = () => {
  if (!profile.value){
    instance.value.dialogVisible = true
  }
}
const store = useStore()
const profile = computed(() => store.state.login.profile)
//搜索
let isSearchPopup = ref(false)
const search = () => {
  if (value.value){
    isSearchPopup.value = false
    router.push(`/search`)
    store.commit('setKeyword',value.value)
    getSearchResult({keywords:value.value,type:1}).then(res => {
      store.commit('setSongMusic',res.data.result.songs)
    })
  }
}
eventBus.on('hotSearch',keyword => {
  value.value = keyword
  search()
})

let name = ref()
eventBus.on('active',value => {
  name.value = value
})

//头像上传
const uploadElement = ref('')
const upload1 = () => {
  uploadElement.value.dialogVisible = true
}
</script>

<style scoped lang="less">
.topContent{
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
.name{
  font-weight:600;
  font-size:30px;
  margin-bottom:0px;
  cursor:pointer;
  background-image:-webkit-linear-gradient(bottom,red,#ff5f60,#f0c41b);
  -webkit-background-clip:text;
  -webkit-text-fill-color:transparent;
  font-family: '楷体',serif;
}
header{
  z-index: 1000;
  background-color: white;
  .logo{
    width: 120px;
    height: 60px;
  }
  .el-row{
    height: 60px;
  }
}
.svg{
  width: 30px;
  height: 60px;
}
</style>
