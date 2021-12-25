<template>
  <div class="h100p flex-col">
    <div class="flex-none">
      <el-button class="w80p" type="primary" @click="reportPassList">审核全部</el-button>
    </div>
    <!--    <div class="flex-none flex-row pa-20">
          <div class="row-col-center w50r">
            <div class="w100">
              动态查询:
            </div>
            <div class="w200">
              <el-input
                  v-model="talkId"
              ></el-input>
            </div>
            <el-button type="primary" @click="queryTalk">查询</el-button>
            <el-button type="primary" @click="initQueryData" class="ml-5">重置</el-button>
          </div>
          <div class="row-col-center w50r">
            <div class="w100">
              用户查询:
            </div>
            <div class="w200">
              <el-input
                  v-model="userId"
              ></el-input>
            </div>
            <el-button type="primary" @click="queryUserTalks">查询</el-button>

    &lt;!&ndash;        <el-button type="primary" @click="getViolation">获取违规关键词</el-button>&ndash;&gt;
          </div>
        </div>-->
    <!--    <el-card></el-card>-->

    <div v-if="talk" class="flex-auto overflow-scroll">
      {{ talk.content }}
      <div
        v-for="img in talk.imgs"
        :key="img.id"
      >
        <el-image
          style="width: 200px; height: 200px"
          fit="contain"
          :src="getImgUrl(img.src,talk.userId)"
          aspect-ratio="1"
        />
      </div>
      <div v-for="comment in talk.comments">
        {{ comment.content }}
      </div>
    </div>
    <div v-else-if="userReports.length" class="flex-auto overflow-scroll">
      <div v-for="report in userReports">
        {{ report.talk.id }} --- {{ report.talk.content }}
        <div
          v-for="img in report.talk.imgs"
          :key="img.id"
        >
          <el-image
            class="index-sm"
            style="width: 200px; height: 200px"
            :src="getImgUrl(img.src,report.talk.userId)"
            aspect-ratio="1"
          />
        </div>
        <div v-for="comment in report.talk.comments">
          {{ comment.content }}
        </div>
      </div>
    </div>

    <el-table
      v-else
      class="flex-auto"
      height="100"
      :data="reports"
      border
    >
      <!--              @row-click="tableRowClick"-->
      <el-table-column
        label="序号"
        type="index"
      />
      <el-table-column
        width="40"
        :render-header="colHeaderRender"
      >
        <template #default="{row}">
          <div>
            <el-checkbox
              v-model="row.checked"
              @click.native.stop
            />
          </div>
        </template>
      </el-table-column>
      <el-table-column
        label="图片"
        width="100"
      >
        <template #default="{row}">
          <div class="flex-row">
            <el-image
              v-for="(img) in row.talk.imgs"
              :key="img.id"
              style="width: 100px; height: 200px"
              fit="contain"
              :src="getImgUrl(img.src,row.talk.userId)"
              :preview-src-list="row.talk.imgs.map(item=>getImgUrl(item.src))"
              :z-index="999"
            />
          </div>
        </template>
      </el-table-column>
      <el-table-column
        label="用户"
        width="100"
      >
        <template #default="{row}">
          <div>
            <el-avatar shape="square" :src="row.user.avatar" />
            <div>
              {{ row.user.id }}
            </div>
            --
            <div>
              {{ row.user.nickname }}
            </div>
          </div>
        </template>
      </el-table-column>
      <el-table-column
        label="动态"
        width="150"
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
        label="包含"
        width="60"
      >
        <template #default="{row}">
          <div
            v-if="row.triggerKeywords.length"
            class="w30 h20"
            :class="{'bg-red':row.triggerKeywords.length<2,'bg-green':row.triggerKeywords.length>1}"
          />
        </template>
      </el-table-column>
      <el-table-column label="违规关键词">
        <template #default="{row}">
          <div v-for="word in row.triggerKeywords">
            {{ word.keywordsText }} --- {{ word.matchText }}
            <div v-if="word.usePinyin">
              {{ word.keywordsPinyin }} --- {{ word.matchPinyin }}
            </div>
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
                :label="reportType"
                :value="reportType"
              />
            </div>
          </el-radio-group>
        </template>
      </el-table-column>
      <!--      <el-table-column
                label="举报类型"
                width="160">
              <template #default="{row}">
                <el-radio-group>
                  <div v-for="detail in row.childReports">
                    <el-radio
                        :key="detail.reportType"
                        :label="detail.reportType"
                        :value="detail.reportType"
                    ></el-radio>
                    {{ detail.content }}
                  </div>
                </el-radio-group>
              </template>
            </el-table-column>-->

      <el-table-column
        label="操作"
        width="300"
      >
        <template #default="{row}">
          <div
            class="w100r h100 row-all-center font-bigger"
            :class="row.violateType === violateType.noViolation?'bg-green':'bg-red'"
          >
            {{ row.violateType }}
          </div>
        </template>
      </el-table-column>
      <el-table-column
        label="违规原因"
        width="120"
      >
        <template #default="{row}">
          <el-input
            v-model="auditNote"
            type="textarea"
            placeholder="请输入内容"
          />
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        width="200"
      >
        <template #default="{row}">
          <el-button type="danger" @click="rowViolateClick(row)">违规</el-button>
          <el-button type="success" :disabled="!auditNote" @click="rowNoViolateClick(row)">不违规
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

</template>

<script lang="tsx">
import { Component, Vue } from 'vue-property-decorator'
import ReportAPI from '@/api/ReportAPI'
import Talk from '@/model/talk/Talk'
import ReportVO from '@/model/report/ReportVO'
import ViolateType from './ViolateType'
import TalkAPI from '@/api/TalkAPI'
import { Message } from 'element-ui'

@Component
export default class PreAuditPage extends Vue {
  reports: ReportVO[] = []
  imgUrl: string = process.env.VUE_APP_COS_URL
  violateType: typeof ViolateType = ViolateType
  reportType: string = ViolateType.pornInfo
  reportTypes: string[] = []
  auditNote = '未发现违规内容'
  talkId: number = null
  userId: number = null
  talk: Talk = null
  userReports: ReportVO[] = []

  initQueryData() {
    this.talk = null
    this.userReports = []
  }

  resetData() {
    this.talkId = null
    this.userId = null
    this.initQueryData()
  }

  queryTalk() {
    this.initQueryData()
    TalkAPI.queryTalkAPI(this.talkId).then((res: any) => {
      this.talk = res.data
    })
  }

  queryUserTalks() {
    this.initQueryData()
    ReportAPI.queryUserReportsAPI(this.userId).then((res: any) => {
      this.userReports = res.data
    })
  }

  created() {
    console.log(123)
    ReportAPI.queryReportTypesAPI().then((res) => {
      this.reportTypes = res.data
    })
    this.queryReports()
  }

  queryReports() {
    this.reports = []
    ReportAPI.queryReportsAPI().then(res => {
      // const reports = res.data.map(item => Object.assign(item, {checked: false}))
      // this.reports = reports
      this.reports = res.data
    })
  }

  rowNoViolateClick(row) {
    row.violateType = ViolateType.noViolation
    this.reportPass(row)
  }

  rowViolateClick(row) {
    if (row.violateType === ViolateType.noViolation) {
      row.violateType = ViolateType.pornInfo
    }
    this.reportPass(row)
  }

  reportPass(row: ReportVO) {
    ReportAPI.reportAuditAPI(row).then((res: any) => {
      Message.success(
        {
          showClose: true,
          message: res.data || res.errorMsg,
          duration: 1500
        })
      this.reports.splice(this.reports.findIndex(item => item.id === row.id), 1)
      this.initData()
    })
  }

  reportPassList() {
    // 如果没有选中的，则选中全部
    /* if (this.reports.every(item => !item.checked)) {
      this.checkedAllChange()
    }*/
    ReportAPI.reportAuditListAPI(this.reports.filter(item => item.checked)).then((res: any) => {
      Message.success(
        {
          showClose: true,
          message: res.data || res.errorMsg,
          duration: 1500
        })
      this.queryReports()
      this.initData()
    })
  }

  initData() {
    this.$nextTick(() => {
      this.reportType = ViolateType.pornInfo
      this.auditNote = '未发现违规内容'
    })
  }

  getImgUrl(src: string, userId?: number): string {
    // 如果包含'/'则代表是新逻辑
    let imgUrl
    if (src.indexOf('https') > -1) {
      imgUrl = src
    } else {
      if (src.split('/').length > 1) {
        imgUrl = this.imgUrl + src
      } else {
        imgUrl = this.imgUrl + 'user/' + userId + '/talk/normal/' + src
      }
    }
    return imgUrl
  }

  /* getViolation() {
    ReportAPI.getViolationAPI()
  }*/

  // 单行点击取消和选中
  tableRowClick(row) {
    // 不为对比时才生效
    row.checked = !row.checked
    /* if (!this.showCompare) {
      this.drawDetailLine()
    }*/
  }

  get hasNoChecked(): boolean {
    return this.reports.some(item => !item.checked)
    // return true
  }

  // 选中全部
  checkedAllChange() {
    if (this.hasNoChecked) {
      // 有未选中的全选
      this.reports.forEach(item => item.checked = true)
    } else {
      // 全选时，取消全选
      this.reports.forEach(item => item.checked = false)
    }
  }

  colHeaderRender({ column, $index }) {
    return (
      <el-checkbox value={!this.hasNoChecked} onChange={this.checkedAllChange}
      >
      </el-checkbox>
    )
  }
}
</script>

<style scoped>

</style>
