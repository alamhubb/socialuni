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
import { Component, Emit, Model, Vue , toNative} from 'vue-facing-decorator'
import Pageable from '@/components/YComponent/YPageable/Pageable'


@Component
class YPagination extends Vue {
  @Model('change') pageable: Pageable

  @Emit('update:modelValue')
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
export default toNative(YPagination)
</script>
