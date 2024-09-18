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


@Component({})
class SLabelItem extends Vue {
  @Prop() label: string
}
export default toNative(SLabelItem)
</script>

<style scoped>

</style>
