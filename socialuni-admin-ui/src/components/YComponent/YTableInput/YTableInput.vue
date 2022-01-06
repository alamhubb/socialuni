<template>
  <el-table-column
    :label="label||prop"
    v-bind="$attrs"
  >
    <template #default="{row}">
      <el-input
        v-model="row[prop]"
        :readonly="readonly"
        size="small"
        placeholder="请输入"
        @click.native.stop
        v-on="$listeners"
      />
    </template>
  </el-table-column>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
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
export default class YTableInput extends Vue {
  @Prop() readonly prop: string
  @Prop() readonly tipProp: string
  @Prop() readonly propFun: Function
  @Prop() readonly label: string
  @Prop() readonly labelClass: string
  @Prop({ default: '请选择' }) readonly hint: string
  @Prop() readonly readonly: boolean
}
</script>
