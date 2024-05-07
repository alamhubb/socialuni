<template>
  <div class="h100p flex-col overflow-auto">
    <!--    头部行，查询条件和操作-->
    <div class="flex-none row-all-center pd">
      关键词：
      <div class="w200">
        <el-input v-model.trim="inputKeyword" @keydown.native.enter="queryKeyword(inputKeyword)" />
      </div>

      <div class="ml">
        <el-button type="primary" @click="queryKeyword(inputKeyword)">查询</el-button>

        <!--        <el-button type="primary" @click="scanTalkKeywords">扫描talk</el-button>
                <el-button type="primary" @click="scanCommentKeywords">扫描comment</el-button>
                <el-button type="primary" @click="scanMessageKeywords">扫描message</el-button>-->
        <!--        <el-button type="primary" @click="testKeywords">测试</el-button>-->
        <!--        只看某一种？ 看全部情况，还有时候想单独看一下为什么拼音的低，单看拼音的误触发情况 -->
        <el-radio-group v-model="matchType" class="ml">
          <el-radio-button label="全部" />
          <el-radio-button label="拼音" />
          <el-radio-button label="文字" />
        </el-radio-group>
      </div>
    </div>

    <!--    关键词数据库信息-->
    <!--    关键词及时信息-->
    <!--    违规记录-->
    <!--    不违规记录-->
    <keywords-detail-table
      v-if="keywords.length"
      class="flex-1"
      :keywords="keywords"
    />
    <keywords-detail-table
      class="h150"
      :keywords="tempKeywords"
      :is-temp="true"
      @query="queryKeyword"
    />

    <trigger-detail-table
      v-if="triggerDetails.length"
      class="flex-1"
      :trigger-details="triggerDetails"
      :audit-status="'不违规'"
    />
    <trigger-detail-table
      v-if="vioTriggerDetails.length"
      class="flex-1"
      :trigger-details="vioTriggerDetails"
      :audit-status="'违规'"
    />
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-facing-decorator'
import KeywordsQueryAPI from '@/api/KeywordsQueryAPI'
import keywordsVO from '@/model/violateWord/KeywordsVO'
import keywordsTriggerVO from '@/model/violateWord/KeywordsTriggerVO'
import KeywordsManageAPI from '@/api/KeywordsManageAPI'
import keywordsTriggerDetailVO from '@/model/violateWord/KeywordsTriggerDetailVO'
import KeywordsDetailTable from '@/views/keywordManage/KeywordsDetailTable.vue'
import TriggerDetailTable from '@/views/keywordManage/TriggerDetailTable.vue'
import KeywordsDetailVO from '@/model/violateWord/KeywordsDetailVO'
// 违规率高不高
// 违规次数、文本次数、拼音次数

// 违规率、文本违规率、拼音违规率

// 不违规率、文本不违规率、拼音不违规率
// 违规率高不高
// 违规次数、文本次数、拼音次数

// 违规率、文本违规率、拼音违规率

// 不违规率、文本不违规率、拼音不违规率
@toNative
@Component({
  components: { TriggerDetailTable, KeywordsDetailTable }
})
export default class KeywordManagePage extends Vue {
  tempKeywords: keywordsVO[] = []
  keywords: keywordsVO[] = []

  inputKeyword = ''

  triggers: keywordsTriggerVO[] = []

  // db的keywords
  tempKeyword: keywordsVO = null
  // 实时计算的keywords
  keyword: keywordsVO = null

  triggerDetails: keywordsTriggerDetailVO[] = []
  vioTriggerDetails: keywordsTriggerDetailVO[] = []

  matchType = '全部'

  // 查询 违规的历史，然后是否被关键词匹配，被关键词匹配了哪个，然后看正常的会不会被匹配
  // 然后根据违规的内容生成关键词
  //

  // 预，预审核，不更改用户状态。
  // 将动态设置为仅自己可见哪种状态

  created() {
    this.queryKeywords()
  }

  get bottomTableDataList() {
    if (this.tempKeyword) {
      if (this.matchType === '文字') {
        return this.tempKeyword.triggers.filter(item => !item.usePinyin)
      } else if (this.matchType === '拼音') {
        return this.tempKeyword.triggers.filter(item => item.usePinyin)
      } else {
        return this.tempKeyword.triggers
      }
    }
    return []
  }

  queryKeyword(keyword) {
    console.log('出发了查询11：' + keyword)
    this.tempKeyword = null
    this.keywords = []
    if (keyword) {
      KeywordsQueryAPI.queryKeywordAPI(keyword, 10000).then(res => {
        const keywordsDetail: KeywordsDetailVO = res.data
        this.keyword = keywordsDetail.keywords
        this.tempKeyword = keywordsDetail.tempKeywords

        if (this.keyword) {
          this.keywords = [this.keyword]
        }

        this.tempKeywords = [this.tempKeyword]

        this.triggerDetails = keywordsDetail.triggerDetails
        this.vioTriggerDetails = keywordsDetail.vioTriggerDetails

        this.inputKeyword = ''
        /* this.keywords = [this.keyword]
        console.log(this.keyword)*/
      })
    } else {
      this.queryKeywords()
    }
  }

  queryKeywords() {
    KeywordsQueryAPI.queryKeywordsAPI().then(res => {
      this.keywords = res.data
    })
  }

  testKeywords() {
    KeywordsManageAPI.testKeywordsAPI()
  }
}
</script>

<style scoped>

</style>
