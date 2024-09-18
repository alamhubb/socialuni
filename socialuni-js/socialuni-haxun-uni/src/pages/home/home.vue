<template>
  <div class="flex-col h100p bg-theme-bg">
    <talk-view class="h100p"></talk-view>
  </div>
</template>

<script lang="ts">
import {Component, Vue, Watch, toNative} from 'vue-facing-decorator'
import TalkView from "socialuni-community-view-uni/src/views/talk/TalkView.vue"


@Component({
  components: {
   TalkView
  }
})
class TalkPage extends Vue {
}
export default toNative(TalkPage)
</script>
