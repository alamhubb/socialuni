<template>
    <message-view class="h100p"></message-view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import MessageView from "socialuni-im-view-uni/src/views/chat/MessageView.vue"

@Options({components: {MessageView}})
export default class MessagePage extends Vue {
}
</script>
