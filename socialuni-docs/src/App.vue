<script setup lang="ts">
import { RouterView } from 'vue-router'
</script>

<template>
  <suspense>
    <template #default>
      <RouterView />
    </template>
    <template #fallback>
      <p>Loading...</p>
    </template>
  </suspense>
</template>
