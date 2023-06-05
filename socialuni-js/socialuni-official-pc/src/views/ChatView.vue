<template>
  <main class="h100p mt-sm row-all-center">
      123
    <div class="w1200 h100p bg-white flex-row">

        <div class="flex-col br">
            <div class="pd-sm use-click">热门</div>
        </div>
        <div>
          <div v-for="chat in chatList">
              {{chat.nickname}}
          </div>
        </div>

<!--      <div>123</div>
      <el-button>123456</el-button>-->
    </div>
  </main>
</template>

<script lang="ts">
import {Options, Vue} from "vue-property-decorator";
import {socialChatModule} from "socialuni-im/src/store/SocialChatModule";

@Options({})
export default class ChatView extends Vue {

    get chatList(){
        return socialChatModule.chats
    }

}
</script>
