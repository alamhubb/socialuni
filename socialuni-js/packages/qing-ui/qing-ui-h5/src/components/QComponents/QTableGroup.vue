<template>
  <!--            <el-step title="数据库连接信息" />-->
    <div
            class="flex-col"
            id="table_layout_control">
<!--        <div
                v-if="showTableHeader"
                class="flex-none pb-sm">
            <slot name="header">
                <div class="row-between-center px-mn pb-sm">
                    <slot name="headerLeft">
                        <div class="row-between-center flex-1">
                            <div
                                    v-if="title"
                                    class="row-col-center">
                                <slot name="title">
                                    <div class="y-piece-md mr-sm"/>
                                    <div class="flex-none">{{ title }}</div>
                                </slot>
                            </div>
                            <div class="row-col-center">
                                <y-search
                                        :placeholder="placeholder"
                                        v-model="searchForm.searchText"
                                        :title="searchTitle"
                                        :show-btn="showBtn"
                                        btn-title="刷新"
                                        @click="refresh"
                                ></y-search>
                            </div>
                        </div>
                    </slot>
                    <slot name="headerRight">
                    </slot>
                </div>
            </slot>
        </div>-->

        <q-table
                v-loading="loading || innerLoading"
                ref="table"
                class="flex-1"
                :data="filterTableList"
                v-bind="$attrs"
                :checked="checked"
                @current-change="currentRowChange"
                @selection-change="selectionChange"
        >
            <slot/>
        </q-table>

        <slot name="tableFooter">
            <div
                    v-if="showPage || checked"
                    class="flex-none row-between-center pt-smm"
            >
                <slot name="footerLeft">
                    <div
                            v-if="checked"
                            class="ml-sm">
                        已选 {{ checkedData.length }} 条
                    </div>
                </slot>
                <div
                        v-if="showPage"
                        class="flex-1 row-end">
                    <q-pagination
                            background
                            @change="filterTableData"
                            v-model="pageable"
                            layout="total, sizes, prev, pager, next, jumper"
                            :page-sizes="pageSizes"
                    />
                </div>
            </div>
        </slot>
    </div>
</template>

<script lang="ts">
import {Component, toNative, Emit, Model, Prop, Vue, Watch} from 'vue-facing-decorator'
import STable from "./QTable.vue";
import SPagination from "./QPageable/SPagination.vue";

/**
 * @author 秦开远
 * @date 2021/1/21 15:25
 *
 * 在数据源业务基础上，封装基础table
 */
@toNative
@Component({
    components: {STable, SPagination}
})
export default class QTableGroup extends Vue {
    $refs: {
        table: STable;
    }
    @Model('change') model!: any

    @Prop({default: ''}) readonly title: string
    @Prop({default: false, type: Boolean}) readonly showTitle: string
    @Prop({default: '检索'}) readonly searchTitle: string
    @Prop({default: false, type: Boolean}) readonly checked: boolean
    @Prop({default: false, type: Boolean}) readonly loading: boolean
    @Prop({default: '', type: String}) readonly placeholder: string
    @Prop() readonly tableData: any[]

    @Prop({default: false, type: Boolean}) readonly showPage: boolean
    @Prop({default: false, type: Boolean}) readonly showHeader: boolean
    @Prop({default: '', type: [Array, String]}) readonly searchField: boolean

    hasHeaderSlot = false

    get showTableHeader() {
        return this.showHeader || this.hasHeaderSlot
    }

    innerLoading = false

    @Prop({
        default: () => {
            return [10, 20, 50, 100]
        }, type: Array
    }) readonly pageSizes: number[]
    @Prop({
        default: () => {
            return {
                searchText: ''
            }
        },
        type: Object
    })
    readonly searchForm: any
    @Prop({
        default(tableData: []) {
            return tableData.filter((item: object) => {
                if (this.searchField) {
                    if (typeof this.searchField === 'string') {
                        const content = item[this.searchField]
                        if (
                            !this.searchForm.searchText ||
                            PinyinUtil.match(this.searchForm.searchText, content)
                        ) {
                            return true
                        }
                        return false
                    } else {
                        for (const itemKey in this.searchField) {
                            const content = this.searchField[itemKey]
                            if (
                                !this.searchForm.searchText ||
                                PinyinUtil.match(this.searchForm.searchText, item[content])
                            ) {
                                return true
                            }
                        }
                        return false
                    }
                } else {
                    for (const itemKey in item) {
                        const content = String(item[itemKey])
                        if (
                            !this.searchForm.searchText ||
                            PinyinUtil.match(this.searchForm.searchText, content)
                        ) {
                            return true
                        }
                    }
                    return false
                }
            })
        },
        type: Function
    })
    readonly tableDataFilter: Function

    @Prop({
        default(tableData: []) {
            return tableData
        },
        type: Function
    })
    readonly tableDataFilterAppend: Function

    @Prop({
        default() {
            return null
        },
        type: Function
    })
    readonly queryDataFun: Function

    @Prop({default: false, type: Boolean}) readonly showBtn: boolean

    pageable: Pageable = new Pageable(1, 10)

    get checkedData() {
        return this.tableData.filter((item) => item.checked)
    }

    getCheckedData() {
        return this.tableData.filter((item) => item.checked)
    }

    filterTextInput(e) {
        this.searchForm.searchText = e.target.value
    }

    created() {
        if (this.tableData) {
            this.filterTableList = [...this.tableData]
        }
        this.pageable = new Pageable(1, this.pageSizes[0])
    }

    mounted() {
        if (this.$slots.header) {
            this.hasHeaderSlot = true
        }
    }

    @Watch('searchForm', {deep: true})
    searchFormWatch() {
        this.pageable = new Pageable(1, this.pageSizes[0])
        this.filterTableData()
    }

    @Watch('pageable', {deep: true})
    pageableWatch() {
        this.filterTableData()
    }

    @Watch('tableData')
    tableDataWatch() {
        // table替换需要重置
        this.pageable = new Pageable(1, this.pageSizes[0])
        this.filterTableData()
    }

    reset() {
        this.pageable = new Pageable(1, this.pageSizes[0])
        this.searchForm.searchText = ''
        this.filterTableData()
    }

    filterTableList: any[] = []

    // 默认搜索全字段
    filterTableData() {
        let data: any[] = this.tableDataFilterAppend(
            this.tableDataFilter(this.tableData)
        )
        if (this.showPage) {
            this.pageable.total = data.length
            this.pageable.lastPageNum = Math.ceil(this.pageable.total / this.pageable.pageSize)
            const startIndex =
                (this.pageable.pageNum - 1) * this.pageable.pageSize
            const endIndex = this.pageable.pageNum * this.pageable.pageSize
            data = data.slice(startIndex, endIndex)
        }
        // 内容发生变化，或者对象地址发生变化
        if (ObjectUtil.toJson(data) !== ObjectUtil.toJson(this.filterTableList) || (data.length && data[0] !== this.filterTableList[0])) {
            this.filterTableList = data
        }
    }

    setCurrentRow(row) {
        this.$refs.table.setCurrentRow(row)
    }

    toggleRowSelection(row) {
        this.$refs.table.toggleRowSelection(row)
    }

    /* checkAllSelection() {
      this.$refs.table.checkAllSelection()
    }

    clearSelection() {
      this.$refs.table.clearSelection()
    }*/

    @Emit('update:modelValue')
    change(currentRow) {
        return currentRow
    }

    currentRowChange(currentRow: any) {
        this.change(currentRow)
    }

    @Emit()
    selectionChange() {
        return this.tableData.filter((item) => item.checked)
    }

    get selectWidth() {
        if (this.searchTitle.length > 1) {
            return this.searchTitle.length * 15 + 10 + 'px'
        } else {
            return '40px'
        }
    }

    @Emit()
    async refresh() {
        if (this.queryDataFun) {
            this.innerLoading = true
            try {
                await this.queryDataFun()
            } finally {
                this.innerLoading = false
            }
        }
        return
    }
}
</script>
<style lang="scss" scoped>
#table_layout_control {
  .el-input-group__prepend {
    font-size: 14px;
    background: white;
    color: black;
  }
}
</style>
