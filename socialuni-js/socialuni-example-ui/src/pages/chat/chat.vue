<template>
  <view class="bg-default h100p">
    <chat-view class="h100p"></chat-view>
  </view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'

import ChatView from 'socialuni-sdk/src/views/chat/ChatView.vue'

@Options({components: {ChatView}})
export default class ChatPage extends Vue {

}
</script>
