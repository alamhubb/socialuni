<template>
  <!--  应该有外部决定决定高度 100p或者flex-1，或者指定高度-->
  <el-table
    ref="table"
    v-bind="$attrs"
    :data="data"
    header-row-class-name="bg-text-grey"
    size="small"
    v-on="$listeners"
    @row-click="rowClick"
    @selection-change="tableSelectionChange"
  >
    <el-table-column v-if="selection" type="selection"/>
    <el-table-column v-if="index" type="index"/>
    <el-table-column v-if="checked" width="50">
      <!--      不加scope不起作用-->
      <template slot="header" slot-scope="scope">
        <div>
          <el-checkbox
            :value="checkedAll"
            @change="checkedAllClick"
          />
        </div>
      </template>
      <template #default="{row}">
        <el-checkbox
          v-model="row.checked"
          @change="updateCheckedAllStatus"
          @click.native.stop
        />
      </template>
    </el-table-column>
    <slot/>
  </el-table>
</template>

<script lang="ts">
import { Component, Prop, Vue, Watch } from 'vue-property-decorator'
import YTableColumn from '@/components/YComponent/YTableColumn/YTableColumn.vue'
import { ElTable } from 'element-ui/types/table'

/**
 * @author 秦开远
 * @date 2021/1/21 15:25
 *
 * 在数据源业务基础上，封装基础table
 */
@Component({
  components: { YTableColumn }
})
export default class YTable extends Vue {
  @Prop() readonly data: any[]
  // 是否单选
  // @Prop({ default: false, type: Boolean }) readonly radio: any[]
  // 是否多选
  @Prop({ default: false, type: Boolean }) readonly selection: boolean
  // 是否有索引
  @Prop({ default: false, type: Boolean }) readonly index: boolean
  @Prop({ default: false, type: Boolean }) readonly checked: boolean

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

  updateCheckedAllStatus() {
    this.checkedAll = this.data.length && this.data.every(item => item.checked)
  }

  /* get checkedAll() {
    console.log(this.data.every(item => {
      console.log(item.checked)
      return item.checked
    }))
    return this.data.every(item => item.checked)
  }*/

  tableSelectionChange(checkedData) {
    if (this.selection) {
      this.checkedData = checkedData
    }
  }

  // 单行点击取消和选中
  rowClick(row) {
    // 不为对比时才生效
    // row.checked = !row.checked
    if (this.selection) {
      this.$refs.table.toggleRowSelection(row)
    } else if (this.checked) {
      row.checked = !row.checked
      this.updateCheckedAllStatus()
    } else {
      const lastRow = this.checkedData[0]
      if (row !== lastRow) {
        this.setCurrentRow(row)
        this.checkedData = [row]
      } else {
        this.setCurrentRow(null)
        this.checkedData = []
      }
    }
  }

  // 设置高两行
  setCurrentRow(row) {
    this.$nextTick(() => {
      this.$refs.table.setCurrentRow(row)
    })
  }

  checkAllSelection() {
    this.$refs.table.clearSelection()
    this.$nextTick(() => {
      this.$refs.table.toggleAllSelection()
    })
  }

  toggleRowSelection(row) {
    this.$nextTick(() => {
      this.$refs.table.toggleRowSelection(row)
    })
  }

  checkedAllClick() {
    this.checkedAll = !this.checkedAll
    for (const datum of this.data) {
      datum.checked = this.checkedAll
    }
  }
}
</script>
