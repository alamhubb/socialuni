<template>
  <!--            <el-step title="数据库连接信息" />-->
  <div class="flex-col">
    <slot name="tableHeader">
      <div v-if="showHeader" class="flex-none pb-sm">
        <slot name="header">
          <div class="row-between-center px-xs pb-sm">
            <slot name="headerLeft">
              <div class="row-between-center flex-1">
                <div v-if="showTitle" class="row-col-center">
                  <slot name="title">
                    <div class="y-piece-md mr-sm"/>
                    <div class="flex-none">{{ title }}</div>
                  </slot>
                </div>
                <div class="row-col-center">
                  <div class="flex-none">{{ searchTitle }}：</div>
                  <el-input
                    v-model="searchForm.searchText"
                    size="small"
                    placeholder="请输入要搜索的内容"
                    class="w200"
                    @input.native="filterTextInput"
                  />
                  <slot name="headerLeftAppend"></slot>
                </div>
              </div>
            </slot>
            <slot name="headerRight">
            </slot>
          </div>

          <slot name="headerBottom">

          </slot>
        </slot>
      </div>
    </slot>

    <y-table
      ref="table"
      class="flex-1"
      :data="filterTableList"
      v-bind="$attrs"
      :checked="checked"
      @current-change="currentRowChange"
      @selection-change="selectionChange"
    >
      <slot/>
    </y-table>

    <slot name="tableFooter">
      <div v-if="showPage || checked" class="flex-none row-between-center pt-smm">
        <slot name="footerLeft">
          <div v-if="checked" class="ml-sm">
            已选 {{checkedData.length}} 条
          </div>
        </slot>
        <div v-if="showPage" class="flex-1 row-end">
          <y-pagination
            @change="filterTableData"
            v-model="pageable"
            layout="total,sizes,prev,pager,next"
            :page-sizes="[10, 20, 50, 100]"
          />
        </div>
      </div>
    </slot>
  </div>
</template>

<script lang="ts">
import { Component, Emit, Model, Prop, Vue, Watch } from 'vue-property-decorator'
import YTable from '@/components/YComponent/YTable/YTable.vue'
import ObjectUtil from '../../utils/ObjectUtil'
import YPagination from '@/components/YComponent/YPageable/YPagination.vue'
import Pageable from '@/components/YComponent/YPageable/Pageable'
import PinyinUtil from '@/utils/PinyinUtil'

/**
 * @author 秦开远
 * @date 2021/1/21 15:25
 *
 * 在数据源业务基础上，封装基础table
 */
@Component({
  components: { YPagination, YTable }
})
export default class DataTable extends Vue {
  $refs: {
    table: YTable;
  }
  @Model('change') model!: any

  @Prop({ default: '数据列表' }) readonly title: string
  @Prop({ default: false, type: Boolean }) readonly showTitle: string
  @Prop({ default: '检索' }) readonly searchTitle: string
  @Prop({ default: false, type: Boolean }) readonly checked: boolean
  @Prop() readonly tableData: any[]

  @Prop({ default: false, type: Boolean }) readonly showPage: boolean
  @Prop({ default: false, type: Boolean }) readonly showHeader: boolean
  @Prop({ default: '', type: [Array, String] }) readonly searchField: boolean
  @Prop({
    default: () => {
      return {
        searchText: ''
      }
    }, type: Object
  }) readonly searchForm: any
  @Prop({
    default(tableData: []) {
      return tableData.filter((item: object) => {
        if (this.searchField) {
          if (typeof this.searchField === 'string') {
            const content = item[this.searchField]
            if (!this.searchForm.searchText || PinyinUtil.match(this.searchForm.searchText, content)) {
              return true
            }
            return false
          } else {
            for (const itemKey in this.searchField) {
              const content = this.searchField[itemKey]
              if (!this.searchForm.searchText || PinyinUtil.match(this.searchForm.searchText, content)) {
                return true
              }
            }
            return false
          }
        } else {
          for (const itemKey in item) {
            const content = String(item[itemKey])
            if (!this.searchForm.searchText || PinyinUtil.match(this.searchForm.searchText, content)) {
              return true
            }
          }
          return false
        }

      })
    }, type: Function
  }) readonly tableDataFilter: Function
  @Prop({
    default(tableData: []) {
      return tableData
    }, type: Function
  }) readonly tableDataFilterAppend: Function

  pageable: Pageable = new Pageable(1, 10)

  get checkedData(){
    return this.tableData.filter(item => item.checked)
  }

  getCheckedData() {
    return this.tableData.filter(item => item.checked)
  }

  filterTextInput(e) {
    this.searchForm.searchText = e.target.value
  }

  created() {
    if (this.tableData) {
      this.filterTableList = [...this.tableData]
    }
  }

  @Watch('searchForm', { deep: true })
  searchFormWatch() {
    this.pageable = new Pageable(1, 10)
    this.filterTableData()
  }

  @Watch('pageable', { deep: true })
  pageableWatch() {
    this.filterTableData()
  }

  @Watch('tableData')
  tableDataWatch() {
    //table替换需要重置
    this.pageable = new Pageable(1, 10)
    this.filterTableData()
  }

  filterTableList: any [] = []

  // 默认搜索全字段
  filterTableData() {
    let data: any[] = this.tableDataFilterAppend(this.tableDataFilter(this.tableData))
    if (this.showPage) {
      this.pageable.total = data.length
      const startIndex = (this.pageable.currentPage - 1) * this.pageable.pageSize
      const endIndex = this.pageable.currentPage * this.pageable.pageSize
      data = data.slice(startIndex, endIndex)
    }
    if (ObjectUtil.toJson(data) !== ObjectUtil.toJson(this.filterTableList)) {
      this.filterTableList = data
    }
  }

  setCurrentRow(row) {
    this.$refs.table.setCurrentRow(row)
  }

  toggleRowSelection(row) {
    this.$refs.table.toggleRowSelection(row)
  }

  /*checkAllSelection() {
    this.$refs.table.checkAllSelection()
  }

  clearSelection() {
    this.$refs.table.clearSelection()
  }*/

  @Emit()
  change(currentRow) {
    return currentRow
  }

  currentRowChange(currentRow: any) {
    if (currentRow) {
      this.change(currentRow)
    }
  }

  @Emit()
  selectionChange() {
    return this.tableData.filter(item => item.checked)
  }
}
</script>
