<script setup lang="ts">
import {useSlots} from "vue";
import {marked} from 'marked'
import hljs from 'highlight.js'

import 'github-markdown-css/github-markdown.css'
import 'highlight.js/styles/github.css'
import axios from "axios";

const props = defineProps({
  src: String
})


let mdText
if (props.src) {
  //如果为src
  console.log(111111111)
  console.log(props.src)
  axios.get(props.src).then(res => {
    console.log(res)
    console.log(res.data)
    this.remoteMdHello = res.data
  })
} else {
  const slots = useSlots();
  if (slots.default && slots.default()[0]) {
    const mdText = slots.default()[0].children
// https://cdxapp-1257733245.cos.ap-beijing.myqcloud.com/qingchi/markdown/hello.md
  }
}
const defaultOptions = {
  renderer: new marked.Renderer(),
  gfm: true,
  smartLists: true,
  smartypants: true,
  highlight(code) {
    return hljs.highlightAuto(code).value
  }
}
marked.setOptions(defaultOptions)
const dHtml = marked(mdText)

</script>

<template>
  <div class="markdown-body">
    <div v-html="mdHtml">
    </div>
  </div>
</template>
