<template>
    <v-container class="h100p">
        <div class="markdown-body">
            <q-markdown :text="markdown"></q-markdown>
        </div>
    </v-container>
</template>

<script lang="ts">

  import {
    Vue,
    Component,
  , toNative} from 'vue-facing-decorator'

  //@ts-ignore
  import QMarkdown from 'qing-markdown/src/components/q-markdown/q-markdown'
  import http from '@/plugins/http'

  //@ts-ignore
  import agreement from './agreement.md'


@Component({
    components: {
      QMarkdown
    }
  })
  class AgreementVue extends Vue {
    markdown = agreement

    /*created() {
      http.get('https://cdxapp-1257733245.file.myqcloud.com/qingchi/markdown/hello.md').then(res => {
        this.markdown = res.data
      })
    }*/
  }
export default toNative(AgreementVue)
</script>
<style>
    .markdown-body {
        box-sizing: border-box;
        min-width: 200px;
        max-width: 980px;
        margin: 0 auto;
    }
</style>
