<template>
  <div class="h100p flex-col bg-default">
    <q-navbar class="flex-none" show-back :title="viewService.pageTitle">
      <div class="row-end-center flex-1">
        <!--                <button @click="viewService.scrollToMessagePageBottom">滚动到底部</button>-->
        <q-icon icon="list-dot" size="20" @click="viewService.openMoreMenu()"></q-icon>
      </div>
    </q-navbar>
    <!--    <view v-if="showMsgHint" class="fixed-105 row-col-center bg-orange">
          <view class="flex-1 card-text-row">
            长按消息可进行举报，欢迎大家积极举报不良内容获取正义值
          </view>
          <view class="flex-none mr-10px">
            <q-button @click="consoleMessage">{{ scrollTop }}</q-button>
            <q-icon icon="close-circle-fill" size="36" @click="closeShowMsgHint"></q-icon>
          </view>
        </view>-->

    <div class="flex-1 overflow-hidden">
      <scroll-view scroll-y="true" class="h100p"
                   @scrolltoupper="viewService.upper()"
                   :upper-threshold="viewService.upperThreshold"
                   :show-scrollbar="true"
                   :scroll-top="viewService.scrollTop"
      >
        <!--    <view class="cu-chat">-->
        <!--      <view v-if="chat.status === waitOpenStatus||chat.status === closeStatus" class="w100p h100p col-row-center">-->
        <div class="pb-60">
          <div v-for="msg in viewService.messages" :id="'m'+msg.id" :key="msg.id">
            <div v-if="msg.type === '喜欢1'">
              喜欢
            </div>
            <div v-else>
              <div v-if="msg.user.isMine" class="flex-row pr-sm pl-70">
                <div class="flex-1 flex-col mr-sm overflow-hidden">
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

                  <div class="row-end-center" @longpress="viewService.openMessageMoreHandleDialog(msg)">
                    <!--                                    <q-icon v-if="msg.status === 3" icon="mdi-alert-circle" size="25" class="mb-nm"/>-->
                    <!--                                <message-item-content :msg="msg"></message-item-content>-->

                    <div class="pd-xs bg-white bd-radius"> {{ msg.content }}</div>
                  </div>

                  <div class="col-all-center mt-xs">
                    <div class="date">
                      {{ viewService.formatTime(msg.createTime) }}
                    </div>
                  </div>
                </div>
                <img class="size50 bd-radius flex-none"
                     :src="msg.user.avatar"
                     @click="viewService.toUserDetailVue(msg.user.id)"
                />
              </div>
              <div v-else class="flex-row pl-sm pr-70">
                <image class="size50 bd-radius flex-none"
                       :src="msg.user.avatar"
                       @click="viewService.toUserDetailVue(msg.user.id)"
                />
                <div class="flex-1 flex-col ml-sm overflow-hidden">
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
      </scroll-view>
    </div>

    <view class="fixed-footer">
      <view class="cu-bar footer input">
        <!--<view class="action">
            <text class="cuIcon-sound text-grey"></text>
        </view>-->
        <!--                @focus="inputFocusEvent"-->
        <input class="solid-bottom"
               v-model.trim="viewService.msgContent"
               maxlength="300"
               :cursor-spacing="8"
               :focus="viewService.inputFocus"
               @blur="viewService.inputBlur()"
               @focus="viewService.inputFocusEvent()"
               @confirm="viewService.sendMsgClick()"
               :hold-keyboard="true"
               :confirm-hold="true"
               confirm-type="send"
        >
        <!--<view class="action" @click="showEmojiClick">
            <text class="cuIcon-emojifill text-grey"></text>
        </view>-->
        <button v-if="viewService.msgContent" class="cu-btn bg-green shadow color-white"
                @click.prevent="viewService.sendMsgClick()">发送
        </button>
        <!--        <view v-else class="ml-sm">
                  <q-icon icon="plus-circle" size="28" @click="viewService.openPhoto()"></q-icon>
                </view>-->
      </view>
      <!--      <view v-show="showEmoji" class="w100vw bg-blue" :style="{height:keyboardHeight+'px'}"></view>-->
    </view>

    <uni-popup ref="deleteReasonDialog" :show="false" :custom="true" :mask-click="false">
      <view class="uni-tip">
        <view class="uni-tip-title">删除原因</view>
        <view class="uni-textarea bd-1 bd-radius">
              <textarea placeholder="*必填，删除原因" v-model="viewService.deleteReason"
                        :show-confirm-bar="false"
              />
        </view>
        <!--                <view class="uni-common-mt">
                            是否封禁:
                            <switch class="ml-5px" @change="viewService.banChange()"/>
                        </view>-->
        <view class="uni-tip-group-button">
          <button class="uni-tip-button w40r" type="default" @click="viewService.closeDeleteDialog()"
                  :plain="true">取消
          </button>
          <button class="uni-tip-button w40r" type="primary" @click="viewService.confirmDeleteTalk()"
                  :disabled="!viewService.deleteReason">确定
          </button>
        </view>
      </view>
    </uni-popup>

    <uni-popup ref="messageMoreHandleDialog" :custom="true" :mask-click="true">
      <view class="uni-tip w180px">
        <uni-list class="w100px">
          <uni-list-item :show-arrow="true" title="复制" clickable @click="viewService.copyText()"/>
          <uni-list-item v-if="viewService.message&&viewService.message.isMine" :show-arrow="true"
                         title="撤回消息"
                         clickable @click="viewService.revokeMessage()"/>
          <uni-list-item v-if="viewService.message&&!viewService.message.isMine" :show-arrow="true"
                         title="举报"
                         clickable @click="viewService.openReportDialog()"/>
        </uni-list>
      </view>
    </uni-popup>

    <!--<view v-show="showEmoji" class="emoji-model" :style="{height:emojiModelHeight+'px'}"
          @touchstart="inputBlur">
    </view>-->

    <socialuni-report-dialog ref="reportDialog" :report-info="viewService.message"
                             :report-info-type="viewService.reportContentType"></socialuni-report-dialog>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from "vue-facing-decorator";
import SocialuniMsgViewService, {
  socialuniMsgViewService
} from "socialuni-im-sdk/src/logic/SocialuniMsgViewService";
import QIcon from "qing-ui-uni/src/components/QIcon/QIcon.vue";
import QNavbar from "qing-ui-uni/src/components/QNavbar/QNavbar.vue";
import SocialuniReportDialog from "socialuni-user-view-uni/src/components/SocialuniReportDialog.vue"
import MessageViewParams from "./MessageViewParams";
import {socialuniMsgModule} from "socialuni-im-sdk/src/store/SocialMessageModule";
import {getCurrentInstance} from "vue";
import {onLoad} from "@dcloudio/uni-app";

@Component(
    {
      components: {QIcon, QNavbar, SocialuniReportDialog}
    }
)
class SocialuniMessageView extends Vue {
  public $refs!: {
    // reportDialog: SocialuniReportDialog;
    messageMoreHandleDialog: any;
    deleteReasonDialog: any;
  }

  viewService = new SocialuniMsgViewService()

  created() {
    //TODO 同一会话时，这里要改成onRead，不然需要刷新页面才会触发已读的标志。
    onLoad((params: MessageViewParams) => {
      this.viewService.initService(getCurrentInstance(), params)
    })
  }
}

export default toNative(SocialuniMessageView)
</script>
