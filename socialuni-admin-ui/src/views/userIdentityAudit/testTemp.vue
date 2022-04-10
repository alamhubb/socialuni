<template>
  <div class="w100r h100r flex-col" />
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import Talk from '@/model/talk/Talk'

@Component
export default class TestFlex extends Vue {
  content = '5'
  talks: Talk[] = []

  testAPI() {
    /* TestAPI.testKeywordsAPI(this.content).then(res => {
      this.talks = res.data
    })*/
  }
}
</script>

<style scoped>

</style>
