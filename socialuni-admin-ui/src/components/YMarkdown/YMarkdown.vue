<template>
  <div>
    <div :id="htmlId"></div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue, Watch } from 'vue-property-decorator'
import 'highlight.js/styles/github.css'
import 'github-markdown-css/github-markdown.css'
import marked from 'marked'
import hljs from 'highlight.js'
import UUIDUtil from '@/utils/UUIDUtil'
import YMarkdownCopy from '@/components/YMarkdown/YMarkdownCopy.vue'

@Component({
  components: { YMarkdownCopy }
})
export default class YMarkdown extends Vue {
  @Prop() private text: string

  htmlId = UUIDUtil.getUUID()

  mounted() {
    this.renderMarkdown()
  }

  @Watch('text')
  textWatch() {
    this.renderMarkdown()
  }

  renderMarkdown() {
    marked.setOptions({
      renderer: new marked.Renderer(),
      gfm: true,
      smartLists: true,
      smartypants: true,
      highlight(code) {
        return hljs.highlightAuto(code).value
      }
    })

    const html: string = marked(this.text)

    const reg = /<pre>([\s\S]+?)<\/pre>/g

    const htmlT = html.replace(reg, (match, matchVal, index) => {
      const tmpHtml = document.createElement('div')
      tmpHtml.innerHTML = matchVal
      const copyValue = tmpHtml.innerText.trim()
      return `
<div class="position-relative">
<y-markdown-copy class="position-absolute top-0 right-0 mt-3px mr-3px" text="${copyValue}"></y-markdown-copy>
<pre> ${matchVal}</pre>
</div>`
    })

    const container = document.getElementById(this.htmlId)

    new Vue({
      components: { YMarkdownCopy },
      el: container,
      template: htmlT
    })
  }
}
</script>

