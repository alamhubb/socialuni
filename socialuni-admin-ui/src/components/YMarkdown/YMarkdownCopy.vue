<template>
  <el-button size="mini" v-clipboard:copy="text" v-clipboard:success="onCopy">复制</el-button>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import ToastUtil from '@/utils/ToastUtil'

@Component
export default class YMarkdownCopy extends Vue {
  @Prop() private text: string

  onCopy() {
    ToastUtil.success('复制成功')
  }
}
</script>

