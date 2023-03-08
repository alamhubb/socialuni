<template>
  <view class="flex-col h100p bg-theme-bg">
<!--    <talk-view class="h100p"></talk-view>-->
  </view>
</template>

<script lang="ts">
import {Options, Vue, Watch} from 'vue-property-decorator'
import TalkView from "socialuni-view/src/views/talk/TalkView.vue"

@Options({
  components: {
   TalkView
  }
})
export default class TalkPage extends Vue {

}
</script>
