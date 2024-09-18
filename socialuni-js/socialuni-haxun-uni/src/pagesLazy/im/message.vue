<template>
  <div class="bg-default h100p">
    <socialuni-message-view></socialuni-message-view>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import SocialuniMessageView from "socialuni-im-view-uni/src/views/chat/SocialuniMessageView.vue";


@Component({components: {SocialuniMessageView}})
class MessagePage extends Vue {
  $refs: {
    message: SocialuniMessageView
  }

  created() {

  }
}
export default toNative(MessagePage)
</script>
