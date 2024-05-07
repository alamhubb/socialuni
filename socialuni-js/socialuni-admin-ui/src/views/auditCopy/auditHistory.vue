<template>
  <div class="h100p">
    <el-table
      height="100%"
      :data="reports"
      style="width: 100%"
      border
    >
      <el-table-column
        label="图片"
        width="200"
      >
        <template #default="{row}">
          <div class="flex-row">
            <el-image
              v-for="(img,index) in row.talk.imgs"
              :key="img.id"
              style="width: 100px; height: 200px"
              fit="contain"
              :src="getImgUrl(img.src,row.talk.userId)"
              :preview-src-list="row.talk.imgs.map(item=>getImgUrl(item.src))"
              :z-index="index"
            />
          </div>
        </template>
      </el-table-column>
      <el-table-column
        label="用户"
        width="180"
      >
        <template #default="{row}">
          <div class="flex-row">
            <el-avatar shape="square" :src="row.user.avatar" />
            --
            <div>
              <div>
                {{ row.user.id }}
              </div>
              <div>
                {{ row.user.nickname }}

              </div>
            </div>
          </div>
        </template>
      </el-table-column>
      <el-table-column
        label="动态"
        width="400"
      >
        <template #default="{row}">
          {{ row.talk.id }} --- {{ row.talk.content }}
        </template>
      </el-table-column>
      <el-table-column
        label="包含"
        width="60"
      >
        <template #default="{row}">
          <div
            v-if="row.hasKeyword"
            class="w30 h20"
            :class="{'bg-red':row.triggerKeywords.length<2,'bg-green':row.triggerKeywords.length>1}"
          />
        </template>
      </el-table-column>
      <el-table-column
        label="违规关键词"
      >
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
        label="审核结果"
      >
        <template #default="{row}">
          {{ row.status }}
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        width="200"
      >
        <template #default="{row}">
          <el-button type="primary" @click="reviewReportClick(row)">通过</el-button>
          <el-button type="primary" @click="reviewReportAllClick">全部通过</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
  <!--<el-container fluid class="h100vh">
    <div class="h100p flex-col">
      <div class="flex-none">
        <el-row>
          <el-col cols="2">图片</el-col>
          <el-col cols="4">用户，动态</el-col>
          <el-col cols="2">评论</el-col>
          <el-col cols="1">结果验证</el-col>
          <el-col cols="1">用户状态</el-col>
          <el-col cols="1">动态状态</el-col>
          <el-col cols="1">审核状态</el-col>
        </el-row>
      </div>
      <div class="flex-1 overflow-auto">
        <el-row v-for="report in reports">
          <el-col cols="2">
            <el-row>

            </el-row>
          </el-col>
          <el-col cols="4">
            <div>

            </div>

          </el-col>
          <el-col cols="2">
            <div v-for="comment in report.home.comments">
              {{ comment.content }}
            </div>
            <div>
              <span v-for="tag in report.home.tags">{{ tag.name }} ,</span>
            </div>
          </el-col>

        </el-row>
      </div>
    </div>
    <previewer ref="previewer" :list="imgList" :options="options"></previewer>
  </el-container>-->
</template>

<script lang="ts">
import { Vue, Component, Prop , toNative} from 'vue-facing-decorator'
import ReportAPI from '@/api/ReportAPI'
import ReportVO from '@/model/report/ReportVO'

@toNative
@Component
export default class ViolationHistoryPage extends Vue {
  reports: ReportVO[] = []

  // 查询 违规的历史，然后是否被关键词匹配，被关键词匹配了哪个，然后看正常的会不会被匹配
  // 然后根据违规的内容生成关键词
  //

  // 预，预审核，不更改用户状态。
  // 将动态设置为仅自己可见哪种状态

  created() {
    this.queryViolationHistory()
  }

  queryViolationHistory() {
    ReportAPI.queryViolationHistoryAPI().then(res => {
      this.reports = res.data
    })
  }

  options = {}

  imgList = []

  imgUrl: string = process.env.VUE_APP_COS_URL

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

  reviewReportClick(report: ReportVO) {
    ReportAPI.reviewReportsAPI([report]).then(() => {
      const index = this.reports.findIndex(item => item.id === report.id)
      this.reports.splice(index, 1)
      this.$message.success('审核成功')
    })
  }

  reviewReportAllClick() {
    ReportAPI.reviewReportsAPI(this.reports).then(() => {
      this.queryViolationHistory()
      this.$message.success('审核成功')
    })
  }

  get allClickEnabled() {
    return this.reports.every(item => item.hasKeyword)
  }

  // 用户本来 被举报了两个，一个违规，一个不违规，则以违规的为准，如果用户当前状态为违规， 判断违规时候，不管用户当前状态是什么，都修改为违规，如果用户状态为违规，则不修改，否则，
  // 无论用户为正常还是审核中都修改为违规

  // 这页面要查询历史，要查出来所有不为审核中的。查看举报状态和动态状态是否相符

  // todo 有个问题，为什么还存在审核中的举报。

  // 用户状态，动态状态，report状态，

  // 发帖时间控制
}
</script>

<style scoped>

</style>
