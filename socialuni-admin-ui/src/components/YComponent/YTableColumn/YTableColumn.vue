<template>
  <el-table-column
    :label="label||prop"
    :prop="prop"
    :type="$const.DataTableColumnTypeEnum.elTableTypes.includes(type)?type:''"
    show-overflow-tooltip
    v-bind="$attrs"
  >
    <template v-if="$const.DataTableColumnTypeEnum.values.includes(type)" #default="{row,$index}">
      <!--<el-checkbox
        v-if="type === ColumnType.check || column.type === ColumnType.check"
        v-model="row.checked"
        @click.native.stop
        v-on="$listeners"
    />-->
      <!--      :label="optionLabel|| (column.option?column.option.label:null)"-->
      <!--      因为内不支持不输入label默认使用value-->
      <y-select
        v-if="type === $const.DataTableColumnTypeEnum.select"
        v-model="row[prop]"
        :readonly="readonly"
        :options="options"
        :label="optionLabel"
        :value="optionValue"
        v-on="$listeners"
        @change.native.stop
      />
      <y-select
        v-if="type === $const.DataTableColumnTypeEnum.select"
        v-model="row[prop]"
        :readonly="readonly"
        :options="options"
        :label="optionLabel"
        :value="optionValue"
        v-on="$listeners"
        @change.native.stop
      />
      <!--      ? option.label : optionValue-->
      <el-input
        v-else-if="type === $const.DataTableColumnTypeEnum.input"
        v-model="row[prop]"
        :readonly="readonly"
        size="small"
        placeholder="请输入"
        @click.native.stop
        v-on="$listeners"
      />
      <template v-else-if="type===$const.DataTableColumnTypeEnum.label">
        <slot name="default" :row="row">
          <el-tooltip v-if="tipProp && row[tipProp]" effect="dark" :content="row[tipProp]" placement="top-start">
            <div>
              <span :class="labelClass">{{ propFun ? propFun(row[prop], row) : row[prop] }}</span>
              <i class="el-icon-question ml-xs" />
            </div>
          </el-tooltip>
          <span v-else :class="labelClass">{{ propFun ? propFun(row[prop], row) : row[prop] }}</span>
        </slot>
        <!--        <slot name="default" :row="row">{{  row[prop] }}</slot>-->
      </template>

      <el-button
        v-else-if="type===$const.DataTableColumnTypeEnum.delete"
        size="small"
        type="danger"
        plain
        @click="deleteEvent($index)"
      >
        {{
          deleteTitle
        }}
      </el-button>
    </template>
  </el-table-column>
</template>

<script lang="ts">
import { Component, Emit, Prop, Vue } from 'vue-property-decorator'
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
export default class YTableColumn extends Vue {
  @Prop({ default: 'label' }) readonly type: string
  // @Prop({ default: false, type: Boolean }) readonly disabled: boolean

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
  @Prop({ default: '删除' }) readonly deleteTitle: string

  @Emit()
  deleteEvent(index) {
    return index
  }
}
</script>
