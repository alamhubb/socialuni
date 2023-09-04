<template>
  <el-pagination
    v-bind="$attrs"
    :current-page="pageable.pageNum"
    :page-size="pageable.pageSize"
    :total="pageable.total"
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
  />
</template>

<script lang="ts">
import { Component, Emit, Model, Vue } from 'vue-property-decorator'
import Pageable from '@/components/YComponent/YPageable/Pageable'

@Component
export default class YPagination extends Vue {
  @Model('change') pageable: Pageable

  @Emit()
  change(value) {
    return value
  }

  handleSizeChange(val) {
    this.pageable.pageSize = val
    this.pageable.limit = val
    this.change(this.pageable)
  }

  handleCurrentChange(val) {
    this.pageable.pageNum = val
    this.pageable.page = val
    this.change(this.pageable)
  }
}
</script>
