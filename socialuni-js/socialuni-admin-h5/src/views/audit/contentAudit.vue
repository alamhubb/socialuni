<template>
  <div class="h100p flex-col">
    <div class="flex-none flex-row">
      <el-button class="w30p h50" type="primary" :disabled="userReports.length" @click="reportPassList">审核全部
      </el-button>
      <!--      <el-button @click="testRequet">test测试</el-button>-->
      <div class="flex-none flex-row w68p">
        <!--        <div class="row-col-center w50r">
          <div class="w100">
            动态查询:
          </div>
          <div class="w200">
            <el-input
              v-model="talkId"
            />
          </div>
          <el-button type="primary" @click="queryTalk">查询</el-button>
          <el-button type="primary" class="ml-5" @click="initQueryData">重置</el-button>
        </div>-->
        <div class="row-col-center w50p ml">
          <div class="mr-sm flex-none">
            用户查询手机号:
          </div>
          <div class="w200">
            <!--            <el-input
              v-model="userId"
            />-->
            <el-input
                v-model="phoneNum"
                @click.native.stop="queryUserContentsByPhoneNums"
            />
          </div>
          <el-button type="primary" class="ml-sm" @click="queryUserContentsByPhoneNums">查询</el-button>
          <el-button type="primary" class="ml-sm" @click="clearPhoneNum">清空</el-button>
          <el-button
              type="primary"
              class="ml-sm"
              :disabled="!(phoneNum && userReports.length > 0 && (userReports[0].user.status === $const.UserStatus.violation))"
              @click="removeUserBanByPhoneNum"
          >解封
          </el-button>

          <!--        <el-button type="primary" @click="getViolation">获取违规关键词</el-button>-->
        </div>
      </div>
    </div>
    <!--    <el-card></el-card>-->

    <div v-if="talk" class="flex-1 overflow-scroll">
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

    <el-table
        v-else
        class="flex-1"
        height="100"
        :data="tableData"
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
          label="用户"
          width="120"
      >
        <template #default="{row}">
          <div>
            <el-image
                style="width: 100px; height: 100px"
                fit="contain"
                :src="row.user.avatar"
                :preview-src-list="[row.user.avatar]"
                :z-index="999"
            />
            <div>
              {{ row.user.id }}--{{ row.user.status }}
            </div>
            --
            <div>
              {{ row.user.nickname }}
            </div>
          </div>
        </template>
      </el-table-column>
      <el-table-column
          label="违规原因"
          width="160"
      >
        <template #default="{row}">
          <!--          区分是否查询用户的-->
          <div v-if="userReports.length"> {{ row.violateType }}</div>
          <template v-else>
            <el-radio-group v-if="row.auditContentType === $const.SocialuniAuditContentType.report"
                            v-model="row.violateType"
            >
              <div v-for="reportType in reportTypesFilter">
                <el-radio
                    :key="reportType"
                    class="pt-sm"
                    :label="reportType"
                    :value="reportType"
                />
              </div>
            </el-radio-group>
            <el-radio-group v-else v-model="row.violateType">
              <div v-for="reportType in reportTypesUnderage">
                <el-radio
                    :key="reportType"
                    class="pt-sm"
                    :label="reportType"
                    :value="reportType"
                />
              </div>
            </el-radio-group>
          </template>
        </template>
      </el-table-column>
      <el-table-column label="违规关键词" width="150">
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
          label="图片"
          width="300"
      >
        <template #default="{row}">
          <div class="flex-row">
            <el-image
                v-for="(img) in row.talk.imgs"
                :key="img.id"
                style="width: 200px; height: 200px"
                fit="contain"
                :src="getImgUrl(img.src,row.talk.userId)"
                :preview-src-list="row.talk.imgs.map(item=>getImgUrl(item.src))"
                :z-index="999"
            />
          </div>
        </template>
      </el-table-column>
      <el-table-column
          label="动态"
      >
        <template #default="{row}">
          {{ row.talk.id }} --- {{ row.talk.content }}
        </template>
      </el-table-column>
      <el-table-column
          label="评论"
          width="150"
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
      <!--      <el-table-column
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
      </el-table-column>-->
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

      <!--      <el-table-column
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
      </el-table-column>-->
      <!--      <el-table-column
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
      </el-table-column>-->
      <!--      <el-table-column
        label="操作"
        width="200"
      >
        <template #default="{row}">
          <el-button type="danger" @click="rowViolateClick(row)">违规</el-button>
          <el-button type="success" :disabled="!auditNote" @click="rowNoViolateClick(row)">不违规
          </el-button>
        </template>
      </el-table-column>-->
    </el-table>
  </div>

</template>

<script lang="ts">
import { Component, Vue , toNative} from 'vue-facing-decorator'
import ReportAPI from '@/api/ReportAPI'
import Talk from '@/model/talk/Talk'
import ReportVO from '@/model/report/ReportVO'
import TalkAPI from '@/api/TalkAPI'
import { Message } from 'element-ui'
import ViolateType from '@/constant/ViolateType'
import request from '@/plugins/request'
import AdminUserAPI from '@/api/AdminUserAPI'


@Component({})
class ContentAuditView extends Vue {
  reports: ReportVO[] = []
  imgUrl: string = process.env.VUE_APP_COS_URL
  violateType: typeof ViolateType = ViolateType
  reportType: string = ViolateType.pornInfo
  reportTypes: string[] = []
  auditNote = '未发现违规内容'
  talkId: number = null
  userId: number = null
  phoneNum = null
  talk: Talk = null
  userReports: ReportVO[] = []

  get tableData() {
    if (this.userReports.length) {
      return this.userReports
    }
    return this.reports
  }

  initQueryData() {
    this.talk = null
    this.userReports = []
  }

  clearPhoneNum() {
    this.phoneNum = null
    this.initQueryData()
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
    if (this.userId) {
      ReportAPI.queryUserReportsAPI(this.userId).then((res: any) => {
        this.userReports = res.data
      })
    }
  }

  queryUserContentsByPhoneNums() {
    this.initQueryData()
    if (this.phoneNum) {
      ReportAPI.queryUserContentsByPhoneNumAPI(this.phoneNum).then((res: any) => {
        this.userReports = res.data
      })
    } else {
      this.queryReports()
    }
  }

  removeUserBanByPhoneNum() {
    if (this.phoneNum) {
      AdminUserAPI.removeUserBanByPhoneNumAPI(this.phoneNum)
    }
  }

  created() {
    console.log(123)
    ReportAPI.queryReportTypesAPI().then((res) => {
      this.reportTypes = res.data
    })
    this.queryReports()
  }

  get reportTypesFilter() {
    return this.reportTypes.slice(1, this.reportTypes.length)
  }

  get reportTypesUnderage() {
    return this.reportTypes.slice(0, this.reportTypes.length - 1)
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

  testRequet() {
    request.post('audit/test')
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
        imgUrl = this.imgUrl + 'user/' + userId + '/home/normal/' + src
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
export default toNative(ContentAuditView)
</script>

<style scoped>

</style>
