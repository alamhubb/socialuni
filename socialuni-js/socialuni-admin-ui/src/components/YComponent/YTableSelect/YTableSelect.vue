<template>
  <y-table-column
    :label="label||prop"
    v-bind="$attrs"
  >
    <template #default="{row,$index}">
      <y-select
        class="w100p"
        v-model="row[prop]"
        :disabled="readonly"
        :options="options"
        :label="optionLabel"
        :value="optionValue"
        @change="change(row[prop],$index,row)"
      >
        <slot></slot>
      </y-select>
    </template>
  </y-table-column>
</template>

<script lang="ts">
import { Component, Emit, Prop, Vue } from 'vue-facing-decorator'
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
export default class YTableSelect extends Vue {
  @Prop() readonly prop: string
  @Prop() readonly tipProp: string
  @Prop() readonly propFun: Function
  @Prop() readonly label: string
  @Prop() readonly labelClass: string
  @Prop({ default: '请选择' }) readonly hint: string

  @Prop() readonly options: []
  @Prop() readonly optionLabel: string
  @Prop() readonly readonly: boolean
  @Prop() readonly optionValue: string

  @Emit('update:modelValue')
  change(value, index, row) {
    return { value, index, row }
  }
}
</script>
