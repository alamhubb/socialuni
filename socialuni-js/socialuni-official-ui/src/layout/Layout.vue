<template>
  <!--  <div :class="classObj" class="app-wrapper">-->
  <div class="h100p flex-col color-main">
    <qingchi-nav-bar v-if="route.path.includes('qingchi')"></qingchi-nav-bar>
    <nav-bar v-else/>
    <div class="row-center flex-1 overflow-hidden">
      <!--      <nav-menu class="w200 flex-none"/>-->
      <div class="flex-1 pd-20 md:pd-30 overflow-y-auto max-w-900">
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
import {constantRouters} from "@/router";
import {qingchiRouters} from "@/router";

import socialuniBigLogo from '@/imgs/logo.jpg'
import socialuniLogo from '@/imgs/socialunilogo.jpg'
import QingchiNavBar from "@/layout/QingchiNavBar.vue";

console.log(constantRouters)

const route = useRoute()
</script>
