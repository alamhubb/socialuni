<template>
  <y-table-column
    :label="label||prop"
  >
    <template #default="{row}">
      <el-tooltip
        v-if="tipProp && row[tipProp]"
        effect="dark"
        :content="row[tipProp] || (propFun ? propFun(row[prop], row) : row[prop])"
        placement="top-start">
        <el-input
          v-model="row[prop]"
          size="small"
          :clearable="clearable"
          :placeholder="placeholder"
          @click.native.stop
          v-on="$listeners"
          v-bind="$attrs"
        />
      </el-tooltip>
      <el-input
        v-else
        v-model="row[prop]"
        size="small"
        :clearable="clearable"
        :placeholder="placeholder"
        @click.native.stop
        v-on="$listeners"
        v-bind="$attrs"
      />
    </template>
  </y-table-column>
</template>

<script lang="ts">
import { Component, Prop, Vue , toNative} from 'vue-facing-decorator'
import YSelect from '@/components/YComponent/YSelect/YSelect.vue'

/**
 * @author 秦开远
 * @date 2021/1/21 15:25
 *
 * 在数据源业务基础上，封装基础table
 */
@toNative
@Component({
  components: { YSelect }
})
export default class YTableInput extends Vue {
  @Prop() readonly prop: string
  @Prop() readonly tipProp: string
  @Prop() readonly propFun: Function
  @Prop() readonly label: string
  @Prop() readonly labelClass: string
  @Prop({ default: '请输入' }) readonly placeholder: string
  @Prop({ default: true, type: Boolean }) readonly clearable: boolean
}
</script>
