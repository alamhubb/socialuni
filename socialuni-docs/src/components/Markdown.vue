<script setup lang="ts">
import {useSlots} from "vue";
import {marked} from 'marked'
import {getHighlighter, setCDN} from 'shiki'
import hljs from 'highlight.js'

import 'github-markdown-css/github-markdown.css'
import 'highlight.js/styles/github.css'

// const theme = 'material-palenight'
const props = defineProps({
  src: String
})


setCDN('https://unpkg.com/shiki/')

let mdText = ''
if (props.src) {
  //如果为src
  console.log(props.src)
  mdText = props.src
} else {
  const slots = useSlots();
  if (slots.default && slots.default()[0]) {
    mdText = slots.default()[0].children as any
// https://cdxapp-1257733245.cos.ap-beijing.myqcloud.com/qingchi/markdown/hello.md
  }
}


console.log(mdText)
const highlighter = await getHighlighter({
  theme: 'material-palenight'
})
const defaultOptions = {
  renderer: new marked.Renderer(),
  gfm: true,
  smartLists: true,
  smartypants: true,
  highlight(code) {
    // return highlighter.codeToHtml(code)
    return hljs.highlightAuto(code).value
  }

}
marked.setOptions(defaultOptions)
const mdHtml = marked(mdText)

</script>

<template>
  <div class="markdown-body" v-html="mdHtml">
  </div>
</template>
