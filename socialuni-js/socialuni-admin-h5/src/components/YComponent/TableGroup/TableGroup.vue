<template>
  <table-box
    ref="table"
    :table-title="tableTitle"
    :ordinary-select-options="selectOptions"
    :advancedSearchFrom="advancedSearchFrom"
    :hasTableOperate="hasTableOperate"
    :showSaveQueryData="showSaveQueryData"
    :showAdvance="showAdvance"
    @tableOrdinarySearch="tableSearch"
    @tableAdvancedSearch="resetPageableAndQueryTable"
    @tableAdvancedResetForm="resetSearchForm"
    @advancedSwitch="advancedSwitch"
    :onlyAdvancedSearch="onlyAdvancedSearch"
    :padding-class="!noPaddingClass"
    :search-area-show="!noShowSearchArea"
  >
    <template #tableHeader>
      <slot name="tableTitle">
      </slot>
    </template>
    <template #searchPre>
      <slot name="searchPre">
      </slot>
    </template>
    <template #tableSearch>
      <slot name="tableSearch">
      </slot>
    </template>
    <template #tableOperate>
      <slot name="tableOperate"></slot>
    </template>
    <template #table_tit-but-area>
      <slot name="button"></slot>
    </template>
    <template #advanced-area>
      <slot name="advanced">
        <el-form v-if="showAdvance && selectOptions && selectOptions.optionList" v-model="searchForm">
          <el-form-item v-for="(item,index) in selectOptions.optionList" :key="index" :label="item.label">
            <y-select
              clearable
              v-if="item.selectArr"
              v-model="searchForm[item.value]"
              class="w100p"
              :options="item.selectArr"
              label="label"
              value="value"
              :multiple="item.multiple"
            >
            </y-select>
            <el-date-picker
              v-else-if="item.type==='time'"
              v-model="searchForm[item.value]"
              type="datetimerange"
              :format="'yyyy-MM-dd HH:mm:ss'"
              :value-format="'yyyy-MM-dd HH:mm:ss'"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>
            <el-input v-else v-model="searchForm[item.value]"
                      clearable
                      :placeholder="'请输入'+item.label"></el-input>

          </el-form-item>
        </el-form>
      </slot>
    </template>
    <template #table-area>
      <div class="table-content h100p flex-col">
        <slot name="tableHeader">

        </slot>
        <!--      内部刷新一定显示loading-->
        <div class="flex-1 overflow-hidden">
          <y-table
            v-loading="(tableDataLoading || loading) && showLoading"
            ref="yTable"
            :height="tableHeight"
            :data="tableData"
            :checked="checked"
            :index="index"
            :index-label="indexLabel"
            v-on="$listeners"
            v-bind="$attrs"
            :tree-props="treeProps"
          >
            <slot>
            </slot>
          </y-table>
        </div>
        <div
          v-if="showPage"
          class="row-between-center mt-sm pb flex-none">
          <div>
            <slot
              name="pageLeft"
              :tableData="tableData">
            </slot>
          </div>
          <y-pagination
            v-if="!tableDataLoading"
            background
            @change="pageChange"
            v-model="pageable"
            layout="total, sizes, prev, pager, next, jumper"
            :page-sizes="pageSizes"
          />
        </div>
      </div>
    </template>
  </table-box>
</template>

<script lang="ts">
import { Component, Emit, Prop, Vue , toNative} from 'vue-facing-decorator'
import YTable from '@/components/YComponent/YTable/YTable.vue'
import TableSelect from '@/components/YComponent/TableGroup/TableSelect'
import Pageable from '@/components/YComponent/YPageable/Pageable'
import YPagination from '@/components/YComponent/YPageable/YPagination.vue'
import TableSearchItem from '@/components/YComponent/TableGroup/TableSearchItem'
import PageableRO from '@/components/YComponent/model/PageableRO'
import ResultRO from '@/components/YComponent/model/ResultRO'
import YSelect from '@/components/YComponent/YSelect/YSelect.vue'

@toNative
@Component({
  components: { YSelect, YPagination, YTable }
})
export default class TableGroup extends Vue {
  $refs: {
    table: any
    yTable: YTable
  }

  @Prop({
    default() {
      return
    }, type: Function
  }) readonly queryApi: {
    (source?: any, pageData?: Pageable, argList?: any[]): Promise<ResultRO<PageableRO<any>>>;
  }

  @Prop({
    default() {
      return
    }, type: Function
  })
  readonly resetToDefaultFun: () => any

  @Prop({
    default: () => {
      return [10, 20, 50, 100]
    }, type: Array
  }) readonly pageSizes: number[]

  @Prop() selectOptions: TableSelect
  @Prop({
    default: () => {
      return {}
    },
    type: Object
  })
  readonly searchForm: any
  @Prop({
    default: () => {
      return []
    },
    type: Array
  })
  readonly advancedSearchFrom: any
  @Prop({ default: false, type: Boolean }) showPage: boolean
  @Prop({ default: false, type: Boolean }) showSaveQueryData: boolean
  @Prop({ default: false, type: Boolean }) showAdvance: boolean
  @Prop({ default: '', type: String }) tableTitle: boolean
  @Prop({ default: false, type: Boolean }) readonly index: boolean
  @Prop({ default: '', type: String }) readonly indexLabel: string
  @Prop({ default: false, type: Boolean }) readonly checked: boolean
  @Prop({ default: false, type: Boolean }) readonly onlyAdvancedSearch: boolean
  @Prop({ default: false, type: Boolean }) readonly noPaddingClass: boolean
  @Prop({ default: false, type: Boolean }) readonly noShowSearchArea: boolean
  @Prop({ default: false, type: Boolean }) readonly loading: boolean
  @Prop({ default: false, type: Boolean }) readonly noLoading: boolean
  @Prop({ default: false, type: Boolean }) readonly hasTableOperate: boolean
  @Prop({ default: 'id', type: String }) readonly idKey: string
  @Prop({ default: '', type: String }) readonly tableHeight: string

  @Prop({
    default: () => {
      return { children: 'children', hasChildren: 'hasChildren' }
    }
  }) readonly treeProps: any

  @Prop() activeState

  showHeader = false
  pageable: Pageable = new Pageable(1, 10)
  tableData: any[] = []
  tableDataLoading = false
  innerQuery = false

  mounted() {
    if (this.$slots.tableHeader) {
      this.showHeader = true
    }
  }

  tableSearch(searchItems: TableSearchItem[]) {
    console.log(searchItems, 'searchItems')
    // 为清空
    this.resetSearchFormMine()
    for (const searchItem of searchItems) {
      this.$set(this.searchForm, searchItem.label, searchItem.value)
    }
    this.resetPageableAndQueryTable()
    this.search()
  }

  resetPageable() {
    this.pageable = new Pageable()
  }

  resetPageableAndFormAndQuery() {
    this.$nextTick(() => {
      this.$refs.table.advancedResetForm()
    })
  }

  checkedAllClick(checked) {
    this.$nextTick(() => {
      this.$refs.yTable.checkedAllClick(checked)
    })
  }

  toggleRowSelection(row, checked) {
    this.$nextTick(() => {
      this.$refs.yTable.toggleRowSelection(row, checked)
    })
  }

  @Emit()
  resetSearchForm() {
    this.resetSearchFormMine()
    this.resetToDefaultFun()
    this.resetPageableAndQueryTable()
  }

  @Emit()
  advancedSwitch(advancedShow) {
    return advancedShow
  }

  onlyResetFrom() {
    this.$refs.table.onlyResetFrom()
  }

  private resetSearchFormMine() {
    for (const searchFormKey in this.searchForm) {
      this.$set(this.searchForm, searchFormKey, null)
    }
  }

  resetPageableAndQueryTable() {
    this.resetPageable()
    this.pageChange()
  }

  get checkedTableData(): any[] {
    return this.tableData.filter(item => item.checked)
  }

  pageChange() {
    this.innerQuery = true
    const param = { status: this.activeState }
    this.queryTableData(param)
  }

  get showLoading() {
    return (!this.noLoading || this.innerQuery)
  }

  async queryTableData(...args) {
    this.tableDataLoading = true
    // 如果不loading，则代表不想出现刷新效果
    if (this.showLoading) {
      this.tableData = []
      this.$emit('selection-change', [])
    }
    // 清空外部的选项
    try {
      const res = await this.queryApi(this.searchForm, this.pageable, ...args)
      const data = res.data
      this.$nextTick(() => {
        this.innerQuery = false
      })
      const resList: any[] = data.list || []
      // 如果不loading，则代表不想出现刷新效果
      if (!this.showLoading) {
        // 如果数量相等
        if (resList.length === this.tableData.length) {
          const resKeys = resList.map(item => item[this.idKey])
          const tableKeys = this.tableData.map(item => item[this.idKey])
          // 如果key相等
          if (resKeys.toString() === tableKeys.toString()) {
            // 则可以逐个替换，不出现刷新效果
            resList.forEach((item, index) => {
              this.tableData.splice(index, 1, item)
            })
            return
          }
        }
      }
      // 否则，只能直接替换，出现刷新效果
      this.tableData = data.list || []
      this.pageable.total = data.total || data.totalCount || data.pageTotal
      this.dataChange(data)
      return data
    } finally {
      this.tableDataLoading = false
    }
  }

  @Emit()
  dataChange(data) {
    return data
  }

  @Emit()
  search() {
    return null
  }
}
</script>
