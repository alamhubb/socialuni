<template>
  <view class="row-col-center">
    <switch :checked="modelValue" @change="switchChange"></switch>
    <view class="ml-xs" @click="change(!modelValue)">
      <slot></slot>
    </view>
  </view>
</template>

<script lang="ts">
import {Emit, Model, Component, Vue} from 'vue-facing-decorator'

@toNative
@Component({})
export default class SSwitch extends Vue {
  @Model('modelValue') modelValue

  switchChange({detail}: any) {
    return this.change(detail.value)
  }

  @Emit('update:modelValue')
  change(value) {
    return value
  }
}
</script>
