<script setup lang="ts">
import {useSlots} from "vue";
import {marked} from 'marked'
import {getHighlighter, setCDN} from 'shiki'


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
  theme: 'nord'
})
const defaultOptions = {
  renderer: new marked.Renderer(),
  gfm: true,
  smartLists: true,
  smartypants: true,
  highlight(code) {
    return highlighter
        .codeToHtml(code)
  }
}
marked.setOptions(defaultOptions)
const mdHtml = marked(mdText)

</script>

<template>
  <div v-html="mdHtml" class="pd">
  </div>
</template>
