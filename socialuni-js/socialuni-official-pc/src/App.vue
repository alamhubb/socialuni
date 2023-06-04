<script setup lang="ts">
import {RouterView} from 'vue-router'
import NavBar from "@/layout/NavBar.vue";
</script>

<template>
    <div class="h100p flex-col">
        <nav-bar/>
        <RouterView/>
    </div>
</template>
