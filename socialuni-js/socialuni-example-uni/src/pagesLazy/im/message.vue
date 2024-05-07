<template>
  <view class="bg-default h100p">
    <socialuni-message-view></socialuni-message-view>
  </view>
</template>

<script lang="ts">
import {Component, toNative, Vue} from 'vue-facing-decorator'
import SocialuniMessageView from "socialuni-im-view-uni/src/views/chat/SocialuniMessageView.vue";

@toNative
@Component({Component, toNatives: {SocialuniMessageView}})
export default class MessagePage extends Vue {
  $refs: {
    message: SocialuniMessageView
  }

  created() {

  }
}
</script>
