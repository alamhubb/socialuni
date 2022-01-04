<template>
  <div class="h100p flex-col">
    <div class="row-all-center">
      <el-button type="primary" class="w50p h50 mb">审核</el-button>
    </div>
    <data-table checked class="flex-1" height="100" :table-data="imgAuditList" border show-page>
      <el-table-column prop="trace_id" label="trace_id" />
      <el-table-column prop="score" label="审核分数" />
      <el-table-column label="审核结果">
        <template #default="{row}">
          {{ $const.CosImgResultType.getTypeName(row.result) }}
        </template>
      </el-table-column>
      <el-table-column label="是否已冻结">
        <template #default="{row}">
          {{ $const.ForbidStatus.getTypeName(row.forbiddenStatus) }}
        </template>
      </el-table-column>
      <!--      <el-table-column label="命中审核分类">
        <template #default="{row}">
          {{ $const.CosImgResultType.getTypeName(row.hitFlag) }}
        </template>
      </el-table-column>-->
      <el-table-column>
        <template #default="{row}">
          <el-image
              style="width: 200px; height: 200px"
              fit="contain"
              :src="row.url"
              :preview-src-list="[row.url]"
              :z-index="999"
          />
        </template>
      </el-table-column>
    </data-table>
  </div>
</template>

<script lang="tsx">
import { Component, Vue } from 'vue-property-decorator'
import DataTable from '@/components/dataTable/DataTable.vue'
import TencentCosAuditHistoryRO from '@/model/audit/TencentCosAuditHistoryRO'
import AuditAPI from '@/api/AuditAPI'
import YTableColumn from '@/components/YComponent/YTableColumn/YTableColumn.vue'

@Component({
  components: { YTableColumn, DataTable }
})
export default class ImgAuditHistoryPage extends Vue {
  imgAuditList: TencentCosAuditHistoryRO[] = []

  created() {
    console.log(123)
    AuditAPI.queryImgAuditHistoryAPI().then(res => {
      this.imgAuditList = res.data
    })
  }
}
</script>
