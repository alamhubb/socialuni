<template>
  <view class="bg-default h100p">
    <socialuni-message-view></socialuni-message-view>
  </view>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-facing-decorator'
import SocialuniMessageView from "socialuni-im-view-uni/src/views/chat/SocialuniMessageView.vue";

@Component({components: {SocialuniMessageView}})
export default class MessagePage extends Vue {
  $refs: {
    message: SocialuniMessageView
  }

  created() {

  }
}
</script>
