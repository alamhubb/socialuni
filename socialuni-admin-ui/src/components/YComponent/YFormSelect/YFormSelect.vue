<template>
  <el-form-item :label="label" :label-width="labelWidth" :prop="prop">
    <y-select
      class="w100p"
      :model="model"
      v-bind="$attrs"
      :disabled="readonly"
      :options="options"
      :label="optionLabel"
      :multiple="multiple"
      :value="optionValue"
      @change="change"
    />
  </el-form-item>
</template>

<script lang="ts">
import { Component, Emit, Model, Prop, Vue } from 'vue-property-decorator'
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
export default class YFormSelect extends Vue {

  @Model('change') readonly model!: any

  @Prop() readonly type: string
  @Prop() readonly prop: string
  @Prop() readonly labelWidth: string
  @Prop() readonly label: string

  @Prop() readonly options: []
  @Prop() readonly readonly: boolean
  @Prop({ default: false }) readonly multiple: boolean
  @Prop({ default: 'name' }) readonly optionLabel: string
  @Prop({ default: 'value' }) readonly optionValue: string

  @Emit()
  change(value) {
    return value
  }
}
</script>
