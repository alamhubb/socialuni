<template>
  <!--  <div :class="classObj" class="app-wrapper">-->
  <div class="h100% flex-col color-main">
    <nav-bar/>
    <div class="flex-row flex-1 overflow-hidden">
<!--      <nav-menu class="w200 flex-none"/>-->
      <div class="flex-1 h100% pd-30 overflow-y-auto">
        <transition name="fade-transform" mode="out-in">
          <router-view :key="$route.path"/>
        </transition>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import NavMenu from '@/layout/components/NavMenu/NavMenu.vue'
import NavBar from './NavBar.vue'
import {useRoute} from "vue-router";
const route = useRoute()
</script>
