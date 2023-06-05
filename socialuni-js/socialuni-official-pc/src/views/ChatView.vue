<template>
    <main class="h100p mt-sm row-all-center">
        123
        <div class="w1200 h100p bg-white flex-row">

            <!--        分三块，第一块列表，第二块分类，第三块聊天信息。-->

            <div class="flex-col br">
                <div class="pd-sm use-click">测试</div>
            </div>
            <div class="br">
                <div v-for="chat in chatList" @click="setChatId(chat)">
                    <!--              {{chat.nickname}}-->
                    测试
                </div>
            </div>
            <div v-if="chat">
                测试
            </div>

            <!--      <div>123</div>
                  <el-button>123456</el-button>-->
        </div>
    </main>
</template>

<script lang="ts">
import {Options, Vue} from "vue-property-decorator";
import {socialuniChatModule} from "socialuni-im/src/store/SocialuniChatModule";
import type SocialuniChatRO from "socialuni-api-base/src/model/SocialuniChatRO";

@Options({})
export default class ChatView extends Vue {

    get chat() {
        return socialuniChatModule.chat
    }

    get chatList() {
        return socialuniChatModule.chats
    }


    setChatId(chat: SocialuniChatRO) {
        socialuniChatModule.setChatId(chat.id)
    }

}
</script>
