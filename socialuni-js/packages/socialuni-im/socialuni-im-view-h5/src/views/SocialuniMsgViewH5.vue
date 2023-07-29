<template>
    <div class="h100p flex-col">
        <div ref="messageBox" class="flex-1 overflow-auto">
            <div v-if="viewService.chat">
                <div class="w100p" v-for="msg in viewService.chat.messages" :id="'m'+msg.id" :key="msg.id">
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

                            <view class="row-end-center"
                                  @longpress="viewService.openMessageMoreHandleDialog(msg)">
                                <!--                                    <q-icon v-if="msg.status === 3" icon="mdi-alert-circle" size="25" class="mb-nm"/>-->
                                <!--                                <message-item-content :msg="msg"></message-item-content>-->

                                <div class="pd-xs bg-white bd-radius"> {{ msg.content }}</div>
                            </view>

                            <div class="col-all-center mt-xs">
                                <view class="date">
                                    {{ viewService.formatTime(msg.createTime) }}
                                </view>
                            </div>
                        </div>
                        <img class="size50 bd-radius flex-none"
                             :src="msg.user.avatar"
                             @click="viewService.toUserDetailVue(msg.user.id)"
                        />
                    </div>
                    <div v-else class="flex-row pd-sm">
                        <image class="size50 bd-radius flex-none"
                               :src="msg.user.avatar"
                               @click="viewService.toUserDetailVue(msg.user.id)"
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

                            <div class="row-start" @longpress="viewService.openMessageMoreHandleDialog(msg)">
                                <!--                                    <message-item-content :msg="msg"></message-item-content>-->
                                <div class="pd-xs bg-white bd-radius"> {{ msg.content }}</div>
                            </div>
                            <div class="col-all-center mt-xs">
                                <div class="date">
                                    {{ viewService.formatTime(msg.createTime) }}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="flex-row flex-none">
            <el-input
                    v-model="viewService.msgContent"
                    :rows="1"
                    :autosize="{minRows:1,maxRows:5}"
                    type="textarea"
                    placeholder="Please input"
            />
            <el-button @click="viewService.sendMsgClick()">发送</el-button>
        </div>
    </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-property-decorator";
import SScrollbar from "@socialuni/socialuni-ui-h5/src/components/SScrollbar.vue";
import SocialuniMsgViewService from "@socialuni/socialuni-im-sdk/src/logic/SocialuniMsgViewService";

@Options({
    components: {SScrollbar}
})
export default class SocialuniMsgView extends Vue {

    viewService = new SocialuniMsgViewService()

}
</script>
