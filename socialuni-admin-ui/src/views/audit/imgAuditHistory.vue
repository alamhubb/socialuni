<template>
  <div class="h100p flex-col">
    <div class="row-all-center flex-none">
      <div>
        <el-input v-model="imgScore"/>
      </div>
      <el-button type="primary" class="w200 h50 mb ml" @click="query">查询</el-button>
      <el-button type="primary" class="w200 h50 mb" @click="testRequet">审核</el-button>
    </div>

    <!--    <div class="row-wrap">
          <div v-for="row in imgAuditList">
            <el-image
              style="width: 300px; height: 300px"
              fit="contain"
              :src="row.url"
              :preview-src-list="imgAuditList.map(item=>item.url)"
              :z-index="999"
            />
          </div>
        </div>-->

    <data-table checked class="flex-1" height="100" :table-data="imgAuditList" border show-page>
      <el-table-column>
        <template #default="{row}">
          <el-image
              style="width: 150px; height: 200px"
              fit="contain"
              :src="row.url"
              :preview-src-list="[row.url]"
              :z-index="999"
          />
        </template>
      </el-table-column>
      <el-table-column
          label="用户"
          width="100"
      >
        <template #default="{row}">
          <div>
            <el-avatar shape="square" :src="row.avatar"/>
            --
            <div>
              {{ row.nickname }}
            </div>
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="content" label="动态"/>
      <el-table-column
          label="违规原因"
          width="160"
      >
        <template #default="{row}">
          <el-radio-group v-model="row.violateType">
            <div v-for="reportType in reportTypes">
              <el-radio
                  :key="reportType"
                  class="py-xs"
                  :label="reportType"
                  :value="reportType"
              />
            </div>
          </el-radio-group>
        </template>
      </el-table-column>
      <el-table-column prop="score" label="审核分数"/>
      <!--      <el-table-column label="审核结果">
        <template #default="{row}">
          {{ $const.CosImgResultType.getTypeName(row.result) }}
        </template>
      </el-table-column>-->
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
    </data-table>
  </div>
</template>

<script lang="tsx">
import {Component, Vue} from 'vue-property-decorator'
import DataTable from '@/components/dataTable/DataTable.vue'
import TencentCosAuditHistoryRO from '@/model/audit/TencentCosAuditHistoryRO'
import AuditAPI from '@/api/AuditAPI'
import YTableColumn from '@/components/YComponent/YTableColumn/YTableColumn.vue'
import ReportAPI from '@/api/ReportAPI'
import request from "@/plugins/request";

@Component({
  components: {YTableColumn, DataTable}
})
export default class ImgAuditHistoryPage extends Vue {
  imgAuditList: TencentCosAuditHistoryRO[] = []
  reportTypes: string[] = []
  imgScore = 10

  created() {
    console.log(123)
    this.query()
  }

  query() {
    AuditAPI.queryImgAuditHistoryAPI({number: this.imgScore}).then(res => {
      this.imgAuditList = res.data
    })
    ReportAPI.queryReportTypesAPI().then((res) => {
      this.reportTypes = res.data
    })
  }

  testRequet() {
    request.post('openService/tencent/cos/contentAuditCallback', {
      "code": 0,
      "data": {
        "forbidden_status": 0,
        "porn_info": {
          "hit_flag": 0,
          "label": "",
          "score": 9
        },
        "result": 0,
        "trace_id": "test_trace_id",
        "url": "test_image"
      },
      "message": "Test request when setting callback url"
    })
  }
}
</script>
