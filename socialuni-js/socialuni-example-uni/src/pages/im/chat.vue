<template>
  <socialuni-chat-view></socialuni-chat-view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'

import SocialuniChatView from "@socialuni/socialuni-im-view-uni/src/views/chat/SocialuniChatView.vue";
@Options({
  components: {SocialuniChatView}
})
export default class ChatPage extends Vue {

}
</script>
