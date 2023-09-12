<template>
  <el-container :style="app">
    <el-header><top></top></el-header>
    <el-container>
        <el-aside :style="aside">
            <AsideBar></AsideBar>
        </el-aside>
      <el-main>
        <el-scrollbar>
          <router-view v-slot="{Component}">
            <keep-alive include="findMusic" :exclude="['program','categoryList','videoDetail','singerContent','RecentPlay']">
              <component :is="Component"></component>
            </keep-alive>
          </router-view>
        </el-scrollbar>
      </el-main>
    </el-container>
  </el-container>
  <footer class="footer666">
    <el-footer :style="app"><bottom></bottom></el-footer>
  </footer>
 <el-backtop :bottom="100">top</el-backtop>
</template>

<script setup>
import top from '@/views/Top/top.vue'
import AsideBar from '@/views/Aside/AsideBar.vue'
import bottom from '@/views/bottom/bottom.vue'


document.documentElement.style.setProperty("--el-color-primary", 'red');
import {reactive, ref} from "vue"
import {useStore} from "vuex";
const store = useStore()
let app = reactive({
  width:'1423px',
})
let aside = reactive({
  width:'196px'
})
if (document.documentElement.clientWidth < 1440){
  store.commit('setBoolean')
  app.width = '1240px'
  aside.width = '70px'
}


</script>
<style>
@import "./assets/style/global.css";
</style>
