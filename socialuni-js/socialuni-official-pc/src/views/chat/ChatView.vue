<template>
    <main class="h100p overflow-hidden row-all-center">
        <div class="w600 h100p bg-white px-sm">

            <!--        分三块，第一块列表，第二块分类，第三块聊天信息。-->

            <!--            <div class="flex-col br">
                            <div class="pd-sm use-click">测试</div>
                        </div>
                        <div class="br">
                            <div v-for="chat in logic.chatList" @click="logic.setChatId(chat)">
                                &lt;!&ndash;              {{chat.nickname}}&ndash;&gt;
                                测试
                            </div>
                        </div>-->
            <div class="h100p flex-col">
                <y-scrollbar id="tesfasdfaf" ref="messageBox" class="flex-1">
                    <div v-if="logic.chat">
                        <div class="w100p" v-for="msg in logic.chat.messages" :id="'m'+msg.id" :key="msg.id">
                            <div v-if="msg.user.isMine" class="flex-row pd-sm">
                                <div class="flex-1 flex-col mr overflow-hidden">
                                    <div class="h44px row-end-center mb-xs">
                                        {{ msg.user.nickname }}
                                        <!--              <text class="text-sm" :class="[msg.user.vipFlag?'text-red':'text-gray']"
                                                            @click="toUserDetailVue(msg.user.id)">
                                                        {{ msg.user.nickname }}
                                                      </text>
                                                      <image v-if="msg.user.vipFlag" class="ml-6 mr-6 size30 mt-n10"
                                                             src="/static/img/crown.png"
                                                             @click="toVipVue"></image>-->
                                    </div>

                                    <view class="row-end-center" @longpress="openMessageMoreHandleDialog(msg)">
                                        <!--                                    <q-icon v-if="msg.status === 3" icon="mdi-alert-circle" size="25" class="mb-nm"/>-->
                                        <!--                                <message-item-content :msg="msg"></message-item-content>-->

                                        <div class="pd-xs bg-white bd-radius"> {{ msg.content }}</div>
                                    </view>

                                    <div class="col-all-center mt-xs">
                                        <view class="date">
                                            {{ logic.formatTime(msg.createTime) }}
                                        </view>
                                    </div>
                                </div>
                                <img class="size50 bd-radius flex-none"
                                     :src="msg.user.avatar"
                                     @click="toUserDetailVue(msg.user.id)"
                                />
                            </div>
                            <div v-else class="flex-row pd-sm">
                                <image class="size50 bd-radius flex-none"
                                       :src="msg.user.avatar"
                                       @click="toUserDetailVue(msg.user.id)"
                                />
                                <div class="flex-1 flex-col mr overflow-hidden">
                                    <div class="h44px row-col-center mb-xs">
                                        {{ msg.user.nickname }}
                                        <!--              <text class="text-sm" :class="[msg.user.vipFlag?'text-red':'text-gray']"
                                                            @click="toUserDetailVue(msg.user.id)">
                                                        {{ msg.user.nickname }}
                                                      </text>
                                                      <image v-if="msg.user.vipFlag" class="ml-6 mr-6 size30 mt-n10"
                                                             src="/static/img/crown.png"
                                                             @click="toVipVue"></image>-->
                                    </div>

                                    <div class="row-start" @longpress="openMessageMoreHandleDialog(msg)">
                                        <!--                                    <message-item-content :msg="msg"></message-item-content>-->
                                        <div class="pd-xs bg-white bd-radius"> {{ msg.content }}</div>
                                    </div>
                                    <div class="col-all-center mt-xs">
                                        <div class="date">
                                            {{ logic.formatTime(msg.createTime) }}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </y-scrollbar>

                <div class="flex-row flex-none">
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
import SocialuniChatViewLogic, {SocialuniChatViewLogicRefs} from "@/views/chat/SocialuniChatViewLogic";
import YScrollbar from "@/components/YScrollbar.vue";

@Options({
    components: {YScrollbar}
})
export default class ChatView extends Vue {
    $refs: SocialuniChatViewLogicRefs

    logic = new SocialuniChatViewLogic()

    mounted() {
        console.log(this.$refs)
        this.logic.init(this.$refs)
    }
}
</script>
