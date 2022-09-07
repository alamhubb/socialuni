<template>
  <div>
    {{ text }}
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'

@Component
export default class LoadComponent extends Vue {
  text = 'this is dynamic component'
}
</script>
