<template>
  <!--  应该有外部决定决定高度 100p或者flex-1，或者指定高度-->
  <el-table
    ref="table"
    v-bind="$attrs"
    :data="data"
    :header-cell-style="{background:'#f3f5fb',color:'#444444','font-weight': 'bold'}"
    v-on="$listeners"
  >
    <el-table-column
      width="50"
      v-if="index"
      type="index"
      :label="indexLabel"/>
    <el-table-column
      v-if="checked"
      width="35"
      align="left">
      <!--      不加scope不起作用-->
      <template slot="header">
        <el-checkbox
          :value="checkedAll"
          :disabled="checkedAllDisabled"
          @change="checkedAllClick"
        >
          <!--            <span class="color-th font-bold">全选</span>-->
        </el-checkbox>
      </template>
      <template #default="{row}">
        <div>
          <el-checkbox
            v-model="row.checked"
            :disabled="row.checkedDisabled || rowCheckedDisabledFun(row)"
            @change="selectionChange"
            @click.native.stop
          />
        </div>
      </template>
    </el-table-column>
    <slot/>
  </el-table>
</template>

<script lang="ts">
import { Component, Emit, Model, Prop, Vue, Watch , toNative} from 'vue-facing-decorator'
import YTableColumn from '@/components/YComponent/YTableColumn/YTableColumn.vue'
import ObjectUtil from '@/components/YComponent/utils/ObjectUtil'
import type { ElTable } from 'element-ui/types/table'

/**
 * @author 秦开远
 * @date 2021/1/21 15:25
 *
 * 在数据源业务基础上，封装基础table
 */

@Component({
  components: { YTableColumn }
})
class YTable extends Vue {
  @Model('change') model!: any
  @Prop() readonly data: any[]
  // 是否单选
  // @Prop({ default: false, type: Boolean }) readonly radio: any[]
  // 是否多选
  // 是否有索引
  @Prop({ default: false, type: Boolean }) readonly index: boolean
  @Prop({ default: '', type: String }) readonly indexLabel: string
  @Prop({ default: false, type: Boolean }) readonly checked: boolean
  @Prop({ default: false, type: Boolean }) readonly checkedAllDisabled: boolean
  @Prop({
    default: (row) => {
      return false
    }, type: Function
  }) readonly rowCheckedDisabledFun: any

  $refs: {
    table: ElTable;
  }

  checkedData = []
  checkedAll = false

  created() {
    this.updateCheckedAllStatus()
  }

  @Watch('data')
  tableDataWatch() {
    this.updateCheckedAllStatus()
  }

  private updateCheckedAllStatus() {
    this.checkedAll = this.data.length && this.data.every(item => item.checked)
  }

  /* get checkedAll() {
      return item.checked
    }))
    return this.data.every(item => item.checked)
  }*/

  // 单行点击取消和选中
  rowClick(row) {
    // 不为对比时才生效
    // row.checked = !row.checked
    if (this.checked) {
      this.$set(row, 'checked', !row.checked)
      this.selectionChange()
    } else {
      const lastRow = this.checkedData[0]
      if (row !== lastRow) {
        this.setCurrentRow(row)
        this.checkedData = [row]
      } else {
        this.setCurrentRow(null)
        this.checkedData = []
      }
      this.$refs.table.toggleRowSelection(row)
    }
  }

  @Emit('update:modelValue')
  change(currentRow) {
    return currentRow
  }

  // 设置高亮行
  setCurrentRow(row) {
    this.change(row)
    this.$nextTick(() => {
      this.$refs.table.setCurrentRow(row)
    })
  }

  checkedAllClick(checked?) {
    let checkedAll
    if (ObjectUtil.getValue(checked) === null) {
      checkedAll = !this.checkedAll
    } else {
      checkedAll = checked
    }
    for (const row of this.data) {
      this.$set(row, 'checked', checkedAll)
    }
    this.selectionChange()
  }

  // 必须有这行，通过这行来触发selectionChange，不通过这行直接修改checked无法触发selectionChange
  toggleRowSelection(row, checked?) {
    if (ObjectUtil.getValue(checked) === null) {
      this.$set(row, 'checked', !row.checked)
    } else {
      this.$set(row, 'checked', checked)
    }
    this.selectionChange()
  }

  @Emit()
  selectionChange() {
    this.updateCheckedAllStatus()
    return this.data.filter(item => item.checked)
  }

  clearSelection() {
    this.checkedAllClick(false)
  }
}
export default toNative(YTable)
</script>
