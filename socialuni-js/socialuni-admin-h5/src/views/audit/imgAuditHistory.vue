<template>
  <div class="h100p flex-col">
    <div class="row-all-center flex-none">
      <div>
        <el-input v-model="imgScore" />
      </div>
      <el-button type="primary" class="w200 h50 mb ml" @click="queryTable">查询</el-button>
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

    <data-table checked class="flex-1 overflow-hidden" height="100" :table-data="imgAuditList" border>
      <el-table-column
        width="200"
      >
        <template #default="{row}">
          <el-image
            style="width: 150px; height: 200px"
            fit="contain"
            :src="row.url"
            :preview-src-list="[row.url]"
            :z-index="999"
            @click.native.stop
          />
        </template>
      </el-table-column>

      <el-table-column prop="score" label="审核分数" width="50" />
      <el-table-column
        label="用户"
        width="100"
      >
        <template #default="{row}">
          <div>
            <el-avatar shape="square" :src="row.avatar" />
            --
            <div>
              {{ row.nickname }}
            </div>
          </div>
        </template>
      </el-table-column>
      <el-table-column
        label="动态"
        width="300"
      >
        <template #default="{row}">
          {{ row.talk.id }} --- {{ row.talk.content }}
        </template>
      </el-table-column>
      <el-table-column
        label="评论"
        width="100"
      >
        <template #default="{row}">
          <div v-for="comment in row.talk.comments">
            {{ comment.content }}
          </div>
          <div>
            <span v-for="tag in row.talk.tags">{{ tag.name }} ,</span>
          </div>
        </template>
      </el-table-column>
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
      <!--      <el-table-column label="审核结果">
        <template #default="{row}">
          {{ $const.CosImgResultType.getTypeName(row.result) }}
        </template>
      </el-table-column>-->

      <!--      <el-table-column label="命中审核分类">
        <template #default="{row}">
          {{ $const.CosImgResultType.getTypeName(row.hitFlag) }}
        </template>
      </el-table-column>-->
    </data-table>
  </div>
</template>

<script lang="ts">
import { Component, Vue , toNative} from 'vue-facing-decorator'
import DataTable from '@/components/dataTable/DataTable.vue'
import TencentCosAuditHistoryRO from '@/model/audit/TencentCosAuditHistoryRO'
import AuditAPI from '@/api/AuditAPI'
import YTableColumn from '@/components/YComponent/YTableColumn/YTableColumn.vue'
import ReportAPI from '@/api/ReportAPI'


@Component({
  components: { YTableColumn, DataTable }
})
class ImgAuditHistoryPage extends Vue {
  imgAuditList: TencentCosAuditHistoryRO[] = []
  reportTypes: string[] = []
  imgScore = 10

  created() {
    console.log(123)
    this.query()
  }

  query() {
    this.queryTable()
    ReportAPI.queryReportTypesAPI().then((res) => {
      this.reportTypes = res.data
    })
  }

  queryTable() {
    AuditAPI.queryImgAuditHistoryAPI({ number: this.imgScore }).then(res => {
      this.imgAuditList = res.data
    })
  }

  testRequet() {
    const data = this.imgAuditList.filter(item => item.checked)
    AuditAPI.auditImgListAPI(data).then(() => {
      this.queryTable()
    })
  }
}
export default toNative(ImgAuditHistoryPage)
</script>
