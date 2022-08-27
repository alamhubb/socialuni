<script setup lang="ts">
import {useSlots} from "vue";
import {marked} from 'marked'
import hljs from 'highlight.js'

import 'github-markdown-css/github-markdown.css'
import 'highlight.js/styles/github.css'

const slots = useSlots();
const mdText = slots.default()[0].children

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

const mdHtml = marked(mdText)

</script>

<template>
  <div class="markdown-body">
    <div v-html="mdHtml">
    </div>
  </div>
</template>
