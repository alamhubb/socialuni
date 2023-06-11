<template>
  <chat-view></chat-view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import ChatView from 'socialuni-im/src/view/chat/ChatView.vue'

@Options({
  components: {ChatView}
})
export default class ChatPage extends Vue {

}
</script>
