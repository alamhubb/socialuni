<template>
  <div class="row-col-center flex-1">
    <div>{{ label }}</div>
    <div>
      <slot></slot>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, toNative, Prop, Vue} from 'vue-facing-decorator'

@toNative
@Component
export default class SLabelItem extends Vue {
  @Prop() label: string
}
</script>

<style scoped>

</style>
