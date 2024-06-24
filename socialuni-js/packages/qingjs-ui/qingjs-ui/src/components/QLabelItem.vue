<template>
  <div class="row-col-center flex-1">
    <div>{{ label }}</div>
    <div>
      <slot></slot>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Prop, Vue, toNative} from 'vue-facing-decorator'

@toNative
@Component({})
export default class QLabelItem extends Vue {
  @Prop() label: string
}
</script>

<style scoped>

</style>
