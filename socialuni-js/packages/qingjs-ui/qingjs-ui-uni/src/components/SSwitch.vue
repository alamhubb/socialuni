<template>
  <div class="row-col-center">
    <switch :checked="modelValue" @change="switchChange"></switch>
    <div class="ml-xs" @click="change(!modelValue)">
      <slot></slot>
    </div>
  </div>
</template>

<script lang="ts">
import {Emit, Model, Component, Vue, toNative} from 'vue-facing-decorator'


@Component({})
class SSwitch extends Vue {
  @Model('modelValue') modelValue

  switchChange({detail}: any) {
    return this.change(detail.value)
  }

  @Emit('update:modelValue')
  change(value) {
    return value
  }
}
export default toNative(SSwitch)
</script>
