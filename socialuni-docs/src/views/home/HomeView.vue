<template>
  <div>
    <markdown :src="require('./home.md')">
    </markdown>
  </div>
</template>
<script setup lang="ts">
import Markdown from "@/components/Markdown.vue";</script>
