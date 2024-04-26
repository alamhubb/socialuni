<template>
  <div class="h100p overflow-hidden flex-col bg-default">
    <!--        <nav-bar/>-->
    <div class="flex-1 overflow-hidden">
      <RouterView/>
    </div>
  </div>
</template>

<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import {Component, Vue} from 'vue-facing-decorator';
import QButton from "qing-ui-uni/src/components/QButton/QButton.vue";

console.log(456465)
console.log(Component)

</script>
