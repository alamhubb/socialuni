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
  //@ts-ignore
  import privacy from './rules.md'


@Component({
    components: {
      QMarkdown
    }
  })
  class PrivacyVue extends Vue {
    markdown = privacy

    /*created() {
      http.get('https://cdxapp-1257733245.file.myqcloud.com/qingchi/markdown/hello.md').then(res => {
        this.markdown = res.data
      })
    }*/
  }
export default toNative(PrivacyVue)
</script>
<style>
    .markdown-body {
        box-sizing: border-box;
        min-width: 200px;
        max-width: 980px;
        margin: 0 auto;
    }
</style>
