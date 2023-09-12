<template>
  <skeleton1 :loading="mySongList?.avatarUrl.length">
    <header>
      <div><el-image class="cover" :src="mySongList?.avatarUrl" alt="img"></el-image></div>
      <div class="content">
        <div class="A">
          <el-tag type="danger" size="mini">歌单</el-tag><h2>我的歌单</h2><i class="el-icon-edit"></i></div>
        <div class="B">
          <el-link type="info">{{mySongList?.nickname}}</el-link>
        </div>
        <div class="C">
          <el-button @click="createSongList" size="medium" type="danger" round icon="el-icon-edit">新建歌单</el-button>
          <el-button :disabled="isDisabled" @click="detail" size="medium" round icon="el-icon-delete">删除歌单
          </el-button>
          <el-button size="medium" round icon="el-icon-share">分享歌单</el-button>
        </div>
      </div>
    </header>
  </skeleton1>
  <br>
  <el-divider><h2>我的歌单列表</h2></el-divider>
  <el-checkbox v-model="checkAll">全选</el-checkbox>
  <br>
  <skeleton2 :count="10" :loading="mySongMenu.length" :size="{width:'215px',height:'220px'}">
    <footer>
      <div class="box" v-for="item in mySongMenu" :key="item.id">
        <el-checkbox v-model="item.checkbox">
          <i class="el-icon-delete"></i>删除
        </el-checkbox>
        <el-image @click="toDetail(item)" :src="item.coverImgUrl" class="image-cover"></el-image>
        <div class="label">{{item.name}}</div>
      </div>
    </footer>
  </skeleton2>
<!--  对话框-->
  <myDialog ref="dialog" @create="create"/>
</template>


<script setup>
import {detailSongList} from "@/network/topList.js";
import {ElMessage, ElMessageBox} from "element-plus";
import myDialog from './childComponents/myDialog.vue'
import {computed,ref} from 'vue'
import {useRouter} from "vue-router";
import {useStore} from "vuex";
import {getUserSongList} from "@/network/user.js";

const store = useStore()

let mySongList = computed(() => store.state.login.profile)
//用户歌单
let params = {
  uid:mySongList.value.userId,
  limit:10,
  offset:0,
  timestamp:Date.now()
}
let mySongMenu = ref([])
let isDisabled = computed(() => mySongMenu.value.every(item => !item?.checkbox))
const router = useRouter()
const toDetail = item => {
  if (item.name === '我喜欢的音乐'){
    router.push('/likeMusic')
  }else {
    router.push('/songDetail')
    store.dispatch('getSongList',item.id)
  }
}
//新建歌单
let dialog = ref('')
const createSongList = () => {
  dialog.value.isShow = true
}
function create(){
  getUserSongList(params).then(res => {
    res.data.playlist.forEach(item => item.checkbox = false)
    mySongMenu.value = res.data.playlist
  })
}
create()
//删除歌单
let isShow = ref(false)
let checkAll = computed({
  get(){
    return mySongMenu.value.length && mySongMenu.value.every(item => item?.checkbox)
  },
  set(newValue){
    mySongMenu.value.forEach(item => item.checkbox = newValue)
  }
})
let is = ref(false)
const detail = () => {
  ElMessageBox.confirm('此操作将永久删除该文件, 是否继续?', '提示', {
    confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning',
  }).then(() => {
    let arr = []
    mySongMenu.value.forEach(item => {
      if (item?.checkbox){
        arr.push(item.id)
        console.log(item.id)
      }
    })
    detailSongList(arr.join(',')).then(res => {
     if (res.data.code === 200){
         ElMessage({
           type: 'success', message: '删除成功!',
         });
          create()
       }
    })
  }).catch(() => {
    ElMessage({
      type: 'info', message: '已取消删除',
    });
  })
}
</script>

<style scoped lang="less">
footer{
  display: flex;
  justify-content: flex-start;
  flex-wrap: wrap;
  .box{
    width:  18%;
    height: 320px;
    text-align: center;
    margin: 10px;
    .image-cover{
      width: 100%;
      height: 220px;
      border-radius: 10px;
      &:hover{
        transition: all 1s;
        transform: translate3d(0,-5px,0);
        box-shadow: 1px 1px 20px;
      }
    }
    .label{
      width: 100%;
      height: 30px;
      text-align: center;
      margin-top: 5px;
    }
  }
}
header{
  display: flex;
  justify-content: flex-start;
  padding: 10px;
  width: 100%;
  .cover{
    display: block;
    height: 150px;
    width: 150px;
    border-radius: 10px;
    .info{
      margin-left: 5px;
    }
  }
  .content{
    margin-left: 20px;
    .A{
      width: 100%;
      height: 30px;
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
      margin: 10px 0;
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
  }
}
</style>
