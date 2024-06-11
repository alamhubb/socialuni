<template>
  <div class="flex-col h100p overflow-hidden color-black">
    <div class="flex-row h100p overflow-hidden">
      <div class="w200 bd-radius shadow h100p flex-none br bg-default">
        <socialuni-chat-view-h5></socialuni-chat-view-h5>
      </div>

      <div class="h100p flex-col flex-1 bg-white">
        <!--          <vue-drag-resize>-->
<!--        <music-view class="shadow-bottom"></music-view>-->
        <!--          </vue-drag-resize>-->
        <div class="flex-1 overflow-hidden">
          <socialuni-msg-view-h5></socialuni-msg-view-h5>
        </div>
        <div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator';
import SocialuniChatViewH5 from "socialuni-im-view-h5/src/views/SocialuniChatViewH5.vue";
import SocialuniMsgViewH5 from "socialuni-im-view-h5/src/views/SocialuniMsgViewH5.vue";
@toNative
@Component({
  components: {
    SocialuniChatViewH5,
    SocialuniMsgViewH5,
  }
})
export default class SocialuniChatMessageViewH5 extends Vue {


  mounted() {

  }
}
</script>
