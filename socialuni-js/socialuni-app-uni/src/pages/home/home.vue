<template>
  <view class="flex-col h100p bg-theme-bg">
    <talk-view class="h100p"></talk-view>
  </view>
</template>

<script lang="ts">
import {Component, Vue, Watch} from 'vue-facing-decorator'
import TalkView from "socialuni-community-view-uni/src/views/talk/TalkView.vue"

@Component({
  components: {
   TalkView
  }
})
export default class TalkPage extends Vue {
}
</script>
