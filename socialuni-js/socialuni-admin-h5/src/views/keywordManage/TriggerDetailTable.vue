<template>
  <div class="flex-col">
    <el-table
      class="flex-1 w100r"
      height="100"
      :data="triggerDetails"
      border
    >
      <el-table-column
        label="序号"
        type="index"
      />

      <el-table-column
        label="审核结果"
        width="120"
      >
        <template #default="{row}">
          <div>
            {{ auditStatus ? auditStatus : row.auditStatus }}
          </div>
        </template>
      </el-table-column>

      <!--      <el-table-column
          label="关键词"
          width="120">
        <template #default="{row}">
          <div>
            {{ row.usePinyin ? row.pinyinText : row.text }}
          </div>
        </template>
      </el-table-column>-->

      <el-table-column
        label="类型"
        width="120"
      >
        <template #default="{row}">
          <div>
            {{ row.usePinyin ? '拼音':'文本' }}
          </div>
        </template>
      </el-table-column>

      <el-table-column
        label="匹配内容"
        prop="matchText"
        width="120"
      />

      <el-table-column
        label="内容"
        prop="content"
      />
      <el-table-column
        label="动态类型"
        prop="contentType"
        width="120"
      />
      <!--      <el-table-column
                label="违规类型"
                prop="keywordsTrigger.violateType"
                width="120">
            </el-table-column>-->
    </el-table>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Prop } from 'vue-facing-decorator'
import keywordsTriggerDetailVO from '@/model/violateWord/KeywordsTriggerDetailVO'

@toNative
@Component
export default class KeywordsDetailTable extends Vue {
  @Prop()
  triggerDetails: keywordsTriggerDetailVO[]

  @Prop()
  auditStatus: string
}
</script>

<style scoped>

</style>
