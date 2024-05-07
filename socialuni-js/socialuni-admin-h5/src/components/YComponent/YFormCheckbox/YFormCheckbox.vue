<template>
  <el-form-item :label="label" :label-width="labelWidth" :prop="prop">
    <el-checkbox
      :value="model||value"
      :readonly="readonly"
      size="small"
      @click.native.stop
      v-on="$listeners"
      @change="change"
      v-bind="$attrs">
      <slot></slot>
    </el-checkbox>
  </el-form-item>
</template>

<script lang="ts">
import { Component, Emit, Model, Prop, Vue , toNative} from 'vue-facing-decorator'

/**
 * @author 秦开远
 * @date 2021/1/21 15:25
 *
 * 在数据源业务基础上，封装基础table
 */
@toNative
@Component
export default class YFormCheckbox extends Vue {
  @Model('change') readonly model!: any

  @Prop() readonly label: string
  @Prop() readonly value: string
  @Prop() readonly prop: string
  @Prop() readonly labelWidth: string

  @Prop() readonly readonly: boolean

  @Emit('update:modelValue')
  change(value) {
    return value
  }
}
</script>
