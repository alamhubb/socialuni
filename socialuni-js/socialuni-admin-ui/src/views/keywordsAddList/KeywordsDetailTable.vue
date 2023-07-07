<template>
  <div class="flex-col">
    <el-table
      class="flex-1 w100r"
      height="100"
      :data="keywords"
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
          <template v-if="isTemp">
            <el-button type="primary" @click="addKeywords(row)">新增</el-button>
          </template>

          <template v-else>
            <el-button type="primary" @click="closeKeywords(row)">删除（关闭）关键词</el-button>

            <el-button type="primary" @click="openPinyinOrText(row,'text')"> {{
              row.openText ? '关闭' : '启用'
            }}文本
            </el-button>

            <el-button type="primary" @click="openPinyinOrText(row,'pinyin')">
              {{ row.openPinyin ? '关闭' : '启用' }}拼音
            </el-button>

            <el-button type="primary" @click="query(row)">
              查询
            </el-button>
          </template>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import keywordsVO from '@/model/violateWord/KeywordsVO'

@Component
export default class KeywordsDetailTable extends Vue {
  @Prop()
  keywords: keywordsVO[]

  @Prop({ default: false })
  isTemp: boolean

  // @Emit()
  query(keyword: keywordsVO) {
    console.log('出发了查询')
    this.$emit('query', keyword.text)
    // return keyword.text
  }

  addKeywords(keyword: keywordsVO) {
    /* this.$prompt('请输入新增原因', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消'
    }).then(({ value }) => {
      KeywordsManageAPI.addKeywordsAPI(keyword.text, value || '').then(res => {
        this.$message.success({ message: '新增,新增原因：' + value, duration: 500 })
        this.query(keyword)
      })
    })*/
  }

  openPinyinOrText(keyword: keywordsVO, type: string) {
    /* MessageBox.confirm('是否确认开启/关闭').then(() => {
      KeywordsManageAPI.openPinyinOrTextAPI(keyword.id, type).then(res => {
        this.$message.success('成功了')
        this.query(keyword)
      })
    })*/
  }

  closeKeywords(keyword: keywordsVO) {
    /* this.$prompt('请输入关闭原因', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消'
    }).then(({ value }) => {
      KeywordsManageAPI.closeKeywordsAPI(keyword.id, value).then(res => {
        this.$message.success('关闭成功了,关闭原因：' + value)
        this.query(keyword)
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
