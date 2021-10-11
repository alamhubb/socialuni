<template>
  <div v-html="mdHtml" />
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import marked from 'marked'

@Component
export default class YMarkdown extends Vue {
  @Prop() private text: string

  get mdHtml() {
    if (this.text) {
      return marked(this.text)
    }
    return ''
  }
}
</script>
