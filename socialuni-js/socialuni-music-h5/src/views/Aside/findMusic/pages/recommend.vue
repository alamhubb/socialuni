<template>
        <banner/><!--轮播图-->
        <songList/><!--每日歌曲推荐-->
        <radio/><!--播客-->
        <unique/><!--独家放送-->
        <newMusic/><!--最新音乐-->
        <recommendMV/><!--推荐MV-->
</template>

<script setup>
import {defineAsyncComponent,ref} from "vue";
import banner from './recommend/banner.vue'
import songList from './recommend/songList.vue'
const radio = defineAsyncComponent(() => import('./recommend/radio.vue'))
const unique = defineAsyncComponent(() => import('../children/unique.vue'))
const newMusic = defineAsyncComponent(() => import('../children/newMusic.vue'))
const recommendMV = defineAsyncComponent(() => import('../children/recommendMV.vue'))
</script>
