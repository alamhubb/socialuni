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
  } from 'vue-property-decorator'

  //@ts-ignore
  import QMarkdown from 'qing-markdown/src/components/q-markdown/q-markdown'
  //@ts-ignore
  import group from './group.md'

  @Component({
    components: {
      QMarkdown
    }
  })
  export default class GroupVue extends Vue {
    markdown = group

    /*created() {
      http.get('https://cdxapp-1257733245.file.myqcloud.com/qingchi/markdown/hello.md').then(res => {
        this.markdown = res.data
      })
    }*/
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
