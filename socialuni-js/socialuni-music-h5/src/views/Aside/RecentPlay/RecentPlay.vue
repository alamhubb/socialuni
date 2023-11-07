<template>
  <header>
    <span style="font-size: 25px;font-weight: 900; margin-right: 10px;">最近播放</span>
    <span style="color: #bebbbb;">共100首</span>
  </header>
  <br>
  <el-button type="danger" icon="el-icon-caret-right" round>播放全部</el-button>
  <br>
  <SongList/>
</template>

<script setup>
import SongList from '@/views/SongDetail/pages/SongList.vue'
import {getPlayInfo} from "@/network/user.js";
import {useStore} from "vuex";

const store = useStore()
let id = store.state.login.profile.userId
getPlayInfo(id).then(res => {
  store.commit('setSongMusic',res?.data.allData.map(item => item.song))
})

</script>

<style scoped lang="scss">

</style>

<script>
export default {
  name:'RecentPlay'
}
</script>
