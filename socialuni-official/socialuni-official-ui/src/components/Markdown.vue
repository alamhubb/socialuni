<script setup lang="ts">
// const theme = 'material-palenight'
const props = defineProps({
  src: Promise<{ default: any }>
})
const res = await props.src
const vueNode = res.default.render()

</script>

<template>
  <div class="markdown-body">
    <component :is="vueNode"></component>
  </div>
</template>
