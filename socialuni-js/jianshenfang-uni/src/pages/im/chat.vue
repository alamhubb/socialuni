<template>
  <socialuni-chat-view></socialuni-chat-view>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'

import SocialuniChatView from "socialuni-im-view-uni/src/views/chat/SocialuniChatView.vue";
@toNative
@Component({
  components: {SocialuniChatView}
})
export default class ChatPage extends Vue {

}
</script>
