<template>
  <div class="flex-col h100p overflow-hidden color-black">
    <div class="flex-row h100p overflow-hidden">
      <div class="w240 bd-radius shadow h100p flex-none br bg-default">
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
import socialuniMusicStore from "socialuni-music-sdk/src/store/SocialuniMusicStore";
import SocialuniMusicAPI from "socialuni-music-sdk/src/api/SocialuniMusicAPI";
import {socialuniChatModule} from "socialuni-im-sdk/src/store/SocialuniChatModule";

@toNative
@Component({
  components: {
    SocialuniChatViewH5,
    SocialuniMsgViewH5,
  }
})
export default class SocialuniChatMessageViewH5 extends Vue {

  created() {
    this.initRoomId()
    // this.searchSongList()
    console.log(navigator)
  }

  //查询房间的播放信息

  async initRoomId() {
    // 如果没有房间id，则查询默认系统开放大厅id
    let chatId = this.$route.query.chatId as string
    if (!chatId) {
      const res = await SocialuniMusicAPI.getPublicRoomId();
      chatId = res.data
    }
    await socialuniChatModule.init({chatId})
    console.log(socialuniChatModule.chatId)
  }

  mounted() {

  }
}
</script>
