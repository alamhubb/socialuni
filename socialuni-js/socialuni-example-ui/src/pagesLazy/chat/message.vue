<template>
  <view class="bg-default h100p">
    <message-view ref="message" class="h100p"></message-view>
  </view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'

import MessageView from 'socialuni-view/src/views/chat/MessageView.vue'
import UniUtil from "socialuni-sdk/src/utils/UniUtil";
import {onLoad} from '@dcloudio/uni-app';
import {onMounted} from "vue";
import MessageViewParams from "socialuni-view/src/views/chat/MessageViewParams";

@Options({components: {MessageView}})
export default class MessagePage extends Vue {
  $refs: {
    message: MessageView
  }

  created() {
    onLoad((params: MessageViewParams) => {
      //不这么写refs是空
      onMounted(() => {
        this.$refs.message.init(params)
      })
    })
  }
}
</script>
