<template>
    <div class="flex-row h100p overflow-hidden">
        <div class="w200 bd-radius shadow h100p flex-none">
            <socialuni-chat-view-h5></socialuni-chat-view-h5>
        </div>

        <div class="flex-1 overflow-hidden h100p bg-white ml-sm">
<!--            <socialuni-msg-view-h5></socialuni-msg-view-h5>-->
        </div>
    </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-property-decorator";


import SocialuniChatViewH5 from "@socialuni/socialuni-im-view-h5/src/views/SocialuniChatViewH5.vue"
import SocialuniMsgViewH5 from "@socialuni/socialuni-im-view-h5/src/views/SocialuniMsgViewH5.vue"

@Options({
    components: {SocialuniChatViewH5, SocialuniMsgViewH5}
})
export default class MessageView extends Vue {

  created(){
    console.log(123)

    //原创歌曲，可以分享出来， 听！心的乐曲

    //第一步，上传音乐。
    //请大家来听歌，选择你的歌曲。音源你来提供
  }

}
</script>
