<template>
  <!--  <div :class="classObj" class="app-wrapper">-->
  <div class="h100p flex-col">
    <nav-bar />
    <div class="flex-row flex-1 overflow-hidden">
      <nav-menu class="w200 flex-none" />
      <div class="flex-1 overflow-auto pd">
        <transition name="fade-transform" mode="out-in">
          <router-view :key="$route.path" class="h100p" />
        </transition>
      </div>
    </div>
  </div>
</template>

<script setup>
import NavMenu from '@/layout/components/NavMenu/NavMenu.vue'
import NavBar from '@/layout/NavBar.vue'
</script>
