<template>
  <s-table-column
    :label="label||prop"
  >
    <template #default="{row,$index}">
      {{row[prop]}}
      <s-select
        class="w100p"
        v-bind="$attrs"
        v-model="row[prop]"
        :disabled="readonly"
        :options="options"
        :label="optionLabel"
        :value="optionValue"
        @change="change"
      >
        <slot></slot>
      </s-select>
    </template>
  </s-table-column>
</template>

<script lang="ts">
import { Component, Emit, Prop, Vue } from 'vue-facing-decorator'
import SSelect from "../SSelect/SSelect.vue";
import STableColumn from "../STableColumn/STableColumn.vue";

/**
 * @author 秦开远
 * @date 2021/1/21 15:25
 *
 * 在数据源业务基础上，封装基础table
 */
@Component({
  components: {STableColumn, SSelect }
})
export default class STableSelect extends Vue {
  @Prop() readonly prop: string
  @Prop() readonly tipProp: string
  @Prop() readonly propFun: Function
  @Prop() readonly label: string
  @Prop() readonly labelClass: string
  @Prop({ default: '请选择' }) readonly hint: string

  @Prop() readonly options: any[]
  @Prop() readonly optionLabel: string
  @Prop() readonly readonly: boolean
  @Prop() readonly optionValue: string

  @Emit()
  change(value, index, row) {
    console.log(value)
    // return { value, index, row }
  }
}
</script>
