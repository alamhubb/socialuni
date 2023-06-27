<template>
    <div class="flex-row h100p overflow-hidden">
        <div class="w200 bd-radius shadow h100p flex-none">
            <socialuni-chat-view></socialuni-chat-view>
        </div>

        <div class="flex-1 overflow-hidden h100p bg-white ml-sm">
            <socialuni-msg-view></socialuni-msg-view>
        </div>
    </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-property-decorator";
import SocialuniChatViewService from "socialuni-im-sdk/src/logic/SocialuniChatViewService";
import SocialuniMsgViewService from "socialuni-im-sdk/src/logic/SocialuniMsgViewService";
import SocialuniChatView from "socialuni-im-view-h5/src/views/SocialuniChatView.vue";
import SocialuniMsgView from "socialuni-im-view-h5/src/views/SocialuniMsgView.vue";

@Options({
    components: {SocialuniChatView, SocialuniMsgView}
})
export default class MessageView extends Vue {

}
</script>
