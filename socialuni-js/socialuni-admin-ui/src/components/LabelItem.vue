<template>
  <div class="row-col-center flex-1">
    <div>{{ label }}</div>
    <div class="row-col-center">
      <slot></slot>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Prop, Vue, toNative} from 'vue-facing-decorator'


@Component
class LabelItem extends Vue {
  @Prop() label: string
}
export default toNative(LabelItem)
</script>

<style scoped>

</style>
