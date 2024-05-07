<template>
  <div class="w100r h100p flex-col">
    <div class="w1000">
      <el-input v-model="content" type="textarea" rows="6" />
    </div>
    <div>
      <el-button @click="testAPI">测试</el-button>

      <a href="weixin://dl/business/?t=jI5iGuT1ers">123</a>

      <wx-open-launch-weapp
        id="launch-btn"
        username="gh_51546f2d5ace"
      >
        <template>
          <button class="btn">打开小程序</button>
        </template>
      </wx-open-launch-weapp>
    </div>

    <!--      <iframe class="h50r w50r" src="https://www.baidu.com"></iframe>-->
    <div class="flex-1 overflow-auto">
      <div v-for="talk in talks">
        {{ talk.content }}
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-facing-decorator'
import Talk from '@/model/talk/Talk'

@toNative
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
