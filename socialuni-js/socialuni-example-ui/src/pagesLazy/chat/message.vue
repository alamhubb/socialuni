<template>
  <view class="bg-default h100p">
    <message-view ref="message" class="h100p"></message-view>
  </view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'

import MessageView from 'socialuni-sdk/src/views/chat/MessageView.vue'
import UniUtil from "socialuni-sdk/src/utils/UniUtil";
import {onLoad} from '@dcloudio/uni-app';
import {onMounted} from "vue";

@Options({components: {MessageView}})
export default class MessagePage extends Vue {
  $refs: {
    message: MessageView
  }

  created() {
    onLoad((params) => {
      //不这么写refs是空
      onMounted(()=>{
        console.log(3333333)
        //979c23c05df54beb9c0062170882f452
        console.log(params)
        console.log(this.$refs)
        console.log(this.$refs.message)
        this.$refs.message.init({userId: params.userId})
      })
    })
  }
}
</script>
