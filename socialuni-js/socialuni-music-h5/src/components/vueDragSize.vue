<script setup lang="ts">
import {ref} from 'vue'
import {useDraggable} from '@vueuse/core'

const el = ref<HTMLElement | null>(null)

// `style` will be a helper computed for `left: ?px; top: ?px;`
const res = useDraggable(el)

console.log(res.x, res.y)

</script>

<template>
  <div ref="el" :data-test="res.y.value" :style="{ top:res.y.value  + 'px', left: res.x.value + 'px' }"
       style="position:absolute">
    Drag me! I am at {{ res }}
  </div>
</template>
