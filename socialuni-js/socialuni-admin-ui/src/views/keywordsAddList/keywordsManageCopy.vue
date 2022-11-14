<template>
  <div class="h100r flex-col">
    <div class="flex-none row-all-center pa">
      关键词：
      <div class="w200">
        <el-input v-model.trim="inputKeyword" />
      </div>

      <div class="ml">
        <el-button type="primary" @click="queryKeyword">查询</el-button>
        <el-button type="primary" @click="addKeywords">新增</el-button>
        <el-button type="primary">删除</el-button>
        <el-button type="primary">启用</el-button>
        <el-button type="primary" @click="scanTalkKeywords">扫描talk</el-button>
        <el-button type="primary" @click="scanCommentKeywords">扫描comment</el-button>
        <el-button type="primary" @click="scanMessageKeywords">扫描message</el-button>
        <el-button type="primary" @click="testKeywords">测试</el-button>
        <el-radio-group v-model="matchType">
          <el-radio-button label="全部" />
          <el-radio-button label="拼音" />
          <el-radio-button label="文字" />
        </el-radio-group>
      </div>
    </div>
    <el-table
      class="flex-auto"
      height="100"
      :data="keywords"
      style="width: 100%"
      :default-sort="{prop:'pinyinNormalNum',order:'descending'}"
      border
    >
      <el-table-column
        width="60"
        label="id"
        type="id"
        prop="id"
      />

      <el-table-column
        label="关键词"
        width="70"
        prop="textShow"
      />

      <el-table-column
        label="触发次数"
        width="120"
        sortable
        prop="totalNum"
      />

      <el-table-column
        label="违规次数"
        width="120"
        sortable
        prop="violateNum"
      />

      <el-table-column
        label="不违规次数"
        width="120"
        sortable
        prop="normalNum"
      />

      <el-table-column
        label="违规比例"
        width="120"
        sortable
        prop="violateRatio"
      >
        <template #default="{row}">
          {{ parseInt(row.violateRatio * 100) }}%
        </template>
      </el-table-column>

      <el-table-column
        label="文本违规比例"
        width="120"
        sortable
        prop="textViolateRatio"
      >
        <template #default="{row}">
          {{ parseInt(row.textViolateRatio * 100) }}%
        </template>
      </el-table-column>

      <el-table-column
        label="文本不违规次数"
        width="120"
        sortable
        prop="textNormalNum"
      />

      <el-table-column
        label="拼音违规比例"
        sortable
        width="120"
        prop="pinyinViolateRatio"
      >
        <template #default="{row}">
          {{ parseInt(row.pinyinViolateRatio * 100) }}%
        </template>
      </el-table-column>

      <el-table-column
        label="拼音不违规次数"
        width="120"
        sortable
        prop="pinyinNormalNum"
      />

      <el-table-column
        label="状态"
        width="50"
      >
        <template #default="{row}">
          <div class="size30" :class="row.openPinyin?'bg-green':'bg-red'" />
        </template>
      </el-table-column>

      <el-table-column
        label="操作"
      >
        <template #default="{row}">
          <el-button type="primary" @click="openPinyinOrText(row.id,'pinyin')">
            {{ row.openPinyin ? '关闭' : '启用' }}拼音
          </el-button>
          <el-button type="primary" @click="openPinyinOrText(row.id,'text')"> {{
            row.openText ? '关闭' : '启用'
          }}文本
          </el-button>
          <el-button type="primary" @click="closeKeywords(row.id)">关闭关键词</el-button>
        </template>
      </el-table-column>

      //违规率高不高
      //违规次数、文本次数、拼音次数

      //违规率、文本违规率、拼音违规率

      //不违规率、文本不违规率、拼音不违规率
    </el-table>

    <div v-if="bottomTableDataList.length" class="flex-none flex-col h65vh">
      <el-table
        class="flex-auto"
        height="100"
        :data="bottomTableDataList"
        style="width: 100%"
        border
      >
        <el-table-column
          label="序号"
          type="index"
        />

        <el-table-column
          label="审核结果"
          prop="auditResult"
          width="120"
        />

        <el-table-column
          label="关键词"
          width="120"
        >
          <template #default="{row}">
            <div>
              {{ row.usePinyin ? row.pinyinText : row.text }}
            </div>
          </template>
        </el-table-column>

        <el-table-column
          label="匹配内容"
          prop="matchContent"
          width="120"
        />

        <el-table-column
          label="内容"
          prop="keywordsTrigger.content"
        />
        <el-table-column
          label="动态类型"
          prop="keywordsTrigger.contentType"
          width="120"
        />

        <el-table-column
          label="违规类型"
          prop="keywordsTrigger.violateType"
          width="120"
        />

        //违规率高不高
        //违规次数、文本次数、拼音次数

        //违规率、文本违规率、拼音违规率

        //不违规率、文本不违规率、拼音不违规率
      </el-table>
    </div>

  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import KeywordsQueryAPI from '@/api/KeywordsQueryAPI'
import keywordsVO from '@/model/violateWord/KeywordsVO'
import keywordsTriggerVO from '@/model/violateWord/KeywordsTriggerVO'
import KeywordsManageAPI from '@/api/KeywordsManageAPI'
import keywordsTriggerDetailVO from '@/model/violateWord/KeywordsTriggerDetailVO'

@Component
export default class ViolationHistoryPage extends Vue {
  keywords: keywordsVO[] = []

  inputKeyword = ''

  triggers: keywordsTriggerVO[] = []

  // db的keywords
  keyword: keywordsVO = null
  // 实时计算的keywords
  dbKeyword: keywordsVO = null

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
    if (this.keyword) {
      if (this.matchType === '文字') {
        return this.keyword.triggers.filter(item => !item.usePinyin)
      } else if (this.matchType === '拼音') {
        return this.keyword.triggers.filter(item => item.usePinyin)
      } else {
        return this.keyword.triggers
      }
    }
    return []
  }

  queryKeyword() {
    this.keyword = null
    this.keywords = []
    if (this.inputKeyword) {
      KeywordsQueryAPI.queryKeywordAPI(this.inputKeyword).then(res => {
        const keywordsDetail = res.data

        this.keyword = keywordsDetail.keywords
        // this.dbKeyword = keywordsDetail.dbKeywords
        this.triggerDetails = keywordsDetail.triggerDetails
        this.vioTriggerDetails = keywordsDetail.vioTriggerDetails

        /* this.keywords = [this.keyword]
        console.log(this.keyword)*/
      })
    } else {
      this.queryKeywords()
    }
  }

  addKeywords() {
    /* this.$prompt('请输入新增原因', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消'
    }).then(({ value }) => {
      KeywordsManageAPI.addKeywordsAPI(this.inputKeyword, value || '').then(res => {
        this.$message.success('新增,新增原因：' + value)
        this.queryKeywords()
      })
    }).catch(() => {
      this.$message({
        type: 'info',
        message: '取消输入'
      })
    })*/
  }

  queryKeywords() {
    KeywordsQueryAPI.queryKeywordsAPI().then(res => {
      this.keywords = res.data
    })
  }

  scanTalkKeywords() {
    KeywordsQueryAPI.scanTalkKeywordsAPI()
  }

  scanCommentKeywords() {
    KeywordsQueryAPI.scanCommentKeywordsAPI()
  }

  scanMessageKeywords() {
    KeywordsQueryAPI.scanMessageKeywordsAPI()
  }

  testKeywords() {
    KeywordsManageAPI.testKeywordsAPI()
  }

  openPinyinOrText(id: number, type: string) {
    /* KeywordsManageAPI.openPinyinOrTextAPI(id, type).then(res => {
      ToastUtil.success('成功了')
      this.queryKeywords()
    })*/
  }

  closeKeywords(id: number) {
    /* this.$prompt('请输入关闭原因', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消'
    }).then(({ value }) => {
      KeywordsManageAPI.closeKeywordsAPI(id, value).then(res => {
        this.$message.success('关闭成功了,关闭原因：' + value)
        this.queryKeywords()
      })
    }).catch(() => {
      this.$message({
        type: 'info',
        message: '取消输入'
      })
    })*/
  }
}
</script>

<style scoped>

</style>
