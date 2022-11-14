<template>
  <text class="bg-click mdi" :class="[
      icon,
      addClass
      ]"
        :style="{'font-size':iconSize+'px','color':color}" @click="click">
  </text>
</template>

<script setup lang="ts">
import {computed} from "vue";


const props = defineProps({
  size: [String, Number],
  icon: {
    type: String,
    default: ''
  },
  color: String,
  addClass: String
})

const isMdi = computed(() => {
  const prefixs: string[] = this.icon.split('-')
  if (prefixs.length > 1) {
    if (prefixs[0] === 'mdi') {
      return true
    }
  }
  return false
})

const prefixClass = computed(() => {
  if (isMdi) {
    return 'mdi'
  }
  return ''
})


const iconSize = computed(() => {
  if (props.size) {
    return props.size
  } else {
    if (isMdi) {
      return 18
    } else {
      return 18
    }
  }
})

const emit = defineEmits(['click'])

function click() {
  emit('click')
}
</script>
