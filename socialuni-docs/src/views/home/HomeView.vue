<template>
  <div>
    123
    <markdown :src="import('/src/views/home/home.md')">
    </markdown>
  </div>
</template>
<script setup lang="ts">
import Markdown from "@/components/Markdown.vue";

console.log(Markdown)
</script>
