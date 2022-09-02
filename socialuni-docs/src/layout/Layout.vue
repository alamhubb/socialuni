<template>
  <!--  <div :class="classObj" class="app-wrapper">-->
  <div class="h100p flex-col">
<!--    <nav-bar />-->
    <div class="flex-row flex-1 overflow-hidden">
<!--      <nav-menu v-if="route.name !== 'home'" class="w200 flex-none" />-->
      <div class="h100p pd-30 overflow-y-auto">
        <transition name="fade-transform" mode="out-in">
          <router-view :key="$route.path" />
        </transition>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import NavMenu from '@/layout/components/NavMenu/NavMenu.vue'
import NavBar from '@/layout/NavBar.vue'
import {useRoute} from "vue-router";

const route = useRoute()
console.log(route.path)
console.log(route.name)
</script>
