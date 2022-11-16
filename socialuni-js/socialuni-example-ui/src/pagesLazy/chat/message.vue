<template>
  <view class="bg-default h100p">
    <message-view class="h100p"></message-view>
  </view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'

import MessageView from 'socialuni-sdk/src/views/chat/MessageView.vue'

@Options({components: {MessageView}})
export default class MessagePage extends Vue {

}
</script>
