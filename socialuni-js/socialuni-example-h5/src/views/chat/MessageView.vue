<template>
    <div class="flex-row h100p overflow-hidden">
        <div class="w200 bd-radius shadow h100p flex-none">
            <socialuni-chat-view-h5></socialuni-chat-view-h5>
        </div>

        <div class="flex-1 overflow-hidden h100p bg-white ml-sm">
            <socialuni-msg-view-h5></socialuni-msg-view-h5>
        </div>
    </div>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-facing-decorator';


import SocialuniChatViewH5 from "@socialuni/socialuni-im-view-h5/src/views/SocialuniChatViewH5.vue"
import SocialuniMsgViewH5 from "@socialuni/socialuni-im-view-h5/src/views/SocialuniMsgViewH5.vue"

@Component({
    components: {SocialuniChatViewH5, SocialuniMsgViewH5}
})
export default class MessageView extends Vue {

    created() {
        console.log(123)
    }

}
</script>
