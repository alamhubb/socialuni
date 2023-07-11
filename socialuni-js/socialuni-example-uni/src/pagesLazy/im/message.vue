<template>
  <view class="bg-default h100p">
    <socialuni-message-view></socialuni-message-view>
  </view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import SocialuniMessageView from "@socialuni/socialuni-im-view-uni/src/views/chat/SocialuniMessageView.vue";

@Options({components: {SocialuniMessageView}})
export default class MessagePage extends Vue {
  $refs: {
    message: SocialuniMessageView
  }

  created() {

  }
}
</script>
