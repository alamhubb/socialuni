<template>
  <skeleton1 :count="10" :loading="songMenu.length" :image="{width:'70px',height:'70px'}" :row="1" :margin="{width: '1111px',marginLeft:'10px'}">
      <div class="item" v-for="item in songMenu" :key="item.id" @click="goDetail(item.id)">
      <div class="left">
         <el-avatar shape="square" :size="70" :src="item.picUrl"></el-avatar>
         <span class="name">{{item.name}}</span>
      </div>
      <div style="color: #656161;">{{item.num}}首</div>
      <div class="right" style="color: #656161;">
          {{item.label}}
      </div>
    </div>
  </skeleton1>
</template>

<script setup>
import {computed,ref} from "vue";
import {useRouter} from "vue-router";
import {useStore} from "vuex";
import {getSearchResult} from "@/network/search.js";
import {formatSongMenuData} from '@/utlis/formatData.js'

let songMenu = ref([])

const store = useStore()
const keywords = computed(() => store.state.songDetail.keywords)

getSearchResult({keywords:keywords.value,type:1000}).then(res => {
  songMenu.value = formatSongMenuData(res.data.result.playlists)
})
const router = useRouter()
const goDetail = id => {
  store.dispatch('getSongList',id)
  router.push('/songDetail')
}
</script>

<style scoped lang="scss">
.item{
  width: 100%;
  height: 80px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-top: 10px;
  &:hover{
    background: #ededed;
  }
  .left{
    width: 50%;
    display: flex;
    align-items: center;
  }
  .name{
    padding-left: 10px;
  }
  .right{
    width: 20%;
    i{
      font-size: 30px;
      color: red;
    }
  }
}
</style>
