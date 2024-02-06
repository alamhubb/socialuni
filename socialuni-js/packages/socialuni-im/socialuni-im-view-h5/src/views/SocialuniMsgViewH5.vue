<template>
  <div class="h100p flex-col">
    <div ref="messageBox" class="flex-1 overflow-auto">
      <div v-if="viewService.chat">
        <div class="w100p" v-for="msg in viewService.chat.messages" :id="'m'+msg.id" :key="msg.id">
          <div v-if="msg.user.isMine" class="flex-row pd-sm">
            <div class="flex-1 flex-col mr overflow-hidden">
              <div class="h44px row-end-center mb-xs">
                {{ msg.user.nickname }}
                <!--              <text class="text-sm" :class="[msg.user.vipFlag?'color-red':'text-gray']"
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
                <!--              <text class="text-sm" :class="[msg.user.vipFlag?'color-red':'text-gray']"
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

    <div class="flex-col flex-none position-relative pd-sm bg-white shadow-top">
      <div class="flex-row">
        <q-icon icon="mdi-emoticon-outline color-content" size="28"></q-icon>
      </div>
<!--      <el-input
          class="w100p"
          v-model="viewService.msgContent"
          :rows="5"
          type="textarea"
          resize="none"
          placeholder="Please input"
      />-->
      <textarea
          class="w100p bd-none resize-none mt-sm font-16 color-content pd-xs" style="caret-color: #bfbfbf;"
          v-model="viewService.msgContent"
          :rows="5"
          type="textarea"
      />
      <div class="flex-none position-absolute bottom-0 right-0 mb-smm mr-smm">
        <el-button type="primary" @click="viewService.sendMsgClick()">发送</el-button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue} from "vue-facing-decorator";
import SScrollbar from "qing-ui-h5/src/components/QComponents/QScrollbar.vue";
import SIcon from "qing-ui-h5/src/components/QComponents/QIcon.vue";
import SocialuniMsgViewService from "socialuni-im-sdk/src/logic/SocialuniMsgViewService";
import MessageViewParams from "socialuni-im-view-uni/src/views/chat/MessageViewParams";
import {getCurrentInstance, watch} from "vue";

@Component({
  components: {SScrollbar, SIcon}
})
export default class SocialuniMsgViewH5 extends Vue {

  viewService = new SocialuniMsgViewService()

  created() {
    watch(() => this.$route.query, () => {
      this.viewService.initService(getCurrentInstance(), this.$route.query)
    }, {immediate: true})
  }
}
</script>
