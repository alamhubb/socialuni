<script setup lang="ts">
import { RouterView } from 'vue-router'

import request from "@/plugins/request";

request.get('statistics')
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
