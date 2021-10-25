<template>
  <el-form-item :label="label" :label-width="labelWidth">
    <el-input
      :value="model"
      :readonly="readonly"
      size="small"
      @click.native.stop
      v-on="$listeners"
      @input="change"
      v-bind="$attrs"
    />
  </el-form-item>
</template>

<script lang="ts">
import {Component, Emit, Model, Prop, Vue} from 'vue-property-decorator'
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
  @Prop() readonly labelWidth: string

  @Prop() readonly options: []
  @Prop() readonly optionLabel: string
  @Prop() readonly readonly: boolean
  @Prop() readonly optionValue: string

  @Emit()
  change(value) {
    console.log(value)
    return value
  }
}
</script>
