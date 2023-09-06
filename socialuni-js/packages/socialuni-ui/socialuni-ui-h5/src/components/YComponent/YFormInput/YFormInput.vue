<template>
  <el-form-item :label="label" :label-width="labelWidth" :prop="prop" :required="required">
    <!--    为什么会有等于0的判断，因为否则null也会转为'null'-->
    <el-input
      :value="((model===0?String(model):model) || value)"
      :readonly="readonly"
      size="small"
      :clearable="clearable"
      @click.native.stop
      v-on="$listeners"
      @input="change"
      v-bind="$attrs"
    />
  </el-form-item>
</template>

<script lang="ts">
import { Component, Emit, Model, Prop, Vue } from 'vue-facing-decorator'
import YSelect from '@/components/YComponent/YSelect/YSelect.vue'

/**
 * @author 秦开远
 * @date 2021/1/21 15:25
 *
 * 在数据源业务基础上，封装基础table
 */
@Component({
  components: { YSelect }
})
export default class YFormInput extends Vue {
  @Model('change') readonly model!: any

  @Prop() readonly label: string
  @Prop() readonly value: string
  @Prop() readonly prop: string
  @Prop({ default: false, type: Boolean }) readonly required: boolean
  @Prop({ default: true, type: Boolean }) readonly clearable: boolean
  @Prop() readonly labelWidth: string

  @Prop() readonly options: []
  @Prop() readonly optionLabel: string
  @Prop() readonly readonly: boolean
  @Prop() readonly optionValue: string

  @Emit()
  change(value) {
    return value
  }
}
</script>
