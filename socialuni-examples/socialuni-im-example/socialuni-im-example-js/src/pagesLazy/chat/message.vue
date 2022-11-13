<template>
  <view class="bg-default h100p">
    <message-view class="h100p"></message-view>
  </view>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-property-decorator'
import ChatView from "socialuni-im/src/views/chat/ChatView.vue";
import MessageView from "socialuni-im/src/views/message/MessageView.vue";

@Options({
  components:{
    ChatView,
    MessageView
  }
})
export default class MessagePage extends Vue {

}
</script>
