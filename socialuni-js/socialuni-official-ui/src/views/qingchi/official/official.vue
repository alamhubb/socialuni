<template>
  <v-container class="h100p">
    <h1 align="center">用户意见征求</h1>
    <div class="markdown-body mt-2 col-row-center">
      <div class="font-weight-bold text-h6 row-center">
        扫描微信公众号二维码
      </div>
      <div class="body-1 mt-1 row-center">
        在第一篇文章《清池app用户意见征求》内进行投票和留言提出意见，谢谢大家的支持
      </div>
      <img class="size200"
           src="https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/wx_gzh_code.jpg">
    </div>
    <div class="markdown-body">
      <q-markdown :text="markdown"></q-markdown>
    </div>
  </v-container>
</template>

<script lang="ts">

  import {
    Vue,
    Component,
  } from 'vue-facing-decorator'
  //@ts-ignore
  import QMarkdown from 'qing-markdown/src/components/q-markdown/q-markdown'
  //@ts-ignore
  import official from './official.md'

  @Component({
    components: {
      QMarkdown
    }
  })
  export default class AgreementVue extends Vue {
    markdown = official
  }
</script>
<style>
  .markdown-body {
    box-sizing: border-box;
    min-width: 200px;
    max-width: 980px;
    margin: 0 auto;
  }
</style>
