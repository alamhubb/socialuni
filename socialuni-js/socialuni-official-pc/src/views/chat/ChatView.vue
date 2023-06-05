<template>
    <main class="h100p mt-sm row-all-center">
        123
        <div class="w1200 h100p bg-white flex-row">

            <!--        分三块，第一块列表，第二块分类，第三块聊天信息。-->

            <div class="flex-col br">
                <div class="pd-sm use-click">测试</div>
            </div>
            <div class="br">
                <div v-for="chat in logic.chatList" @click="logic.setChatId(chat)">
                    <!--              {{chat.nickname}}-->
                    测试
                </div>
            </div>
            <div v-if="logic.chat">
                测试

                <div v-for="msg in logic.chat.messages">
                    <!--                    {{msg.content}}-->
                </div>

                <div>
                    <el-input v-model="logic.msgContent"></el-input>
                    <el-button @click="logic.sendMsgClick()">发送</el-button>
                </div>
            </div>

            <!--      <div>123</div>
                  <el-button>123456</el-button>-->
        </div>
    </main>
</template>

<script lang="ts">
import {Options, Vue} from "vue-property-decorator";
import SocialuniChatViewLogic from "@/views/chat/SocialuniChatViewLogic";
import {getCurrentInstance, getCurrentScope} from "vue";

@Options({})
export default class ChatView extends Vue {
    logic = new SocialuniChatViewLogic()

    created() {
        console.log(getCurrentScope())
        console.log(123123)
        console.log(getCurrentInstance())
        this.logic.created()
    }
}
</script>
