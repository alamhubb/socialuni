<script setup lang="ts">
import 'github-markdown-css/github-markdown.css'
import 'highlight.js/styles/github.css'
// const theme = 'material-palenight'
const props = defineProps({
  src: Promise
})
const res = await props.src
const vueNode = res.default.render()

</script>

<template>
  <div class="markdown-body">
    <component :is="vueNode"></component>
  </div>
</template>
