<template>
  <div>
    <el-table
      :data="reports"
      style="width: 100%"
    >
      <el-table-column
        prop="date"
        label="日期"
        width="180"
      />
      <el-table-column
        prop="name"
        label="姓名"
        width="180"
      />
      <el-table-column
        prop="address"
        label="地址"
      />
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
              <el-col
                  v-for="(img,index) in report.home.imgs"
                  :key="img.id"
              >
                <el-img class="max-w100"
                       :src="getImgUrl(img.src,report.home.userId)"
                       aspect-ratio="1"
                       @click.native="show(report.home,report.home.imgs,index)"
                >
                </el-img>
              </el-col>
            </el-row>
          </el-col>
          <el-col cols="4">
            <div>
              <el-avatar>
                <img
                    :src="report.user.avatar"
                    alt="John"
                >
              </el-avatar>
              {{ report.user.id }}-&#45;&#45;{{ report.user.nickname }}
            </div>
            {{ report.home.id }} -&#45;&#45; {{ report.home.content }}
          </el-col>
          <el-col cols="2">
            <div v-for="comment in report.home.comments">
              {{ comment.content }}
            </div>
            <div>
              <span v-for="tag in report.home.tags">{{ tag.name }} ,</span>
            </div>
          </el-col>
          <el-col cols="1" :class="{'bg-green':report.hasKeyword}">
            {{ report.hasKeyword }}
          </el-col>
          <el-col cols="1">
            {{ report.triggerKeywords }}
          </el-col>
          <el-col cols="1">
            {{ report.home.status }}
          </el-col>
          <el-col cols="1">
            {{ report.status }}
          </el-col>
        </el-row>
      </div>
    </div>
    <previewer ref="previewer" :list="imgList" :options="options"></previewer>
  </el-container>-->
</template>

<script lang="ts">
import { Vue, Component, Prop } from 'vue-facing-decorator'
import ReportAPI from '@/api/ReportAPI'
import ReportVO from '@/model/report/ReportVO'

@Component
export default class ViolationHistoryPage extends Vue {
  reports: ReportVO[] = []

  // 查询 违规的历史，然后是否被关键词匹配，被关键词匹配了哪个，然后看正常的会不会被匹配
  // 然后根据违规的内容生成关键词
  //

  // 预，预审核，不更改用户状态。
  // 将动态设置为仅自己可见哪种状态

  created() {
    ReportAPI.queryViolationHistoryAPI().then(res => {
      this.reports = res.data
    })
  }

  options = {}

  imgList = []

  imgUrl: string = process.env.VUE_APP_COS_URL

  getImgUrl(src: string, userId: number): string {
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
