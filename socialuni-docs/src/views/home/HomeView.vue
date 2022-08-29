<template>
  <div>
    <div class="p-8 bg-red-50">123</div>
    <markdown :src="import('/src/views/home/home.md')">
    </markdown>
  </div>
</template>
<script setup lang="ts">
import Markdown from "@/components/Markdown.vue";</script>
