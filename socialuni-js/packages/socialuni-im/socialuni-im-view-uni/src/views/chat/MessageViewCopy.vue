<template>
    <div class="pb-50 h100p">
        <q-navbar class="bb" show-back :title="viewLogic.pageTitle">
            <div class="row-end-center flex-1">
                <q-icon icon="list-dot" size="20" @click="openMoreMenu"></q-icon>
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

        <scroll-view scroll-y="true" class="flex-col h100p pb-60"
                     @scrolltoupper="upper"
                     :upper-threshold="upperThreshold"
                     :show-scrollbar="true"
                     :scroll-top="scrollTop"
        >
            <!--    <view class="cu-chat">-->
            <view class="w100p h100p">
                <!--      <view v-if="chat.status === waitOpenStatus||chat.status === closeStatus" class="w100p h100p col-row-center">-->
                <view class="mt-80px">
                    <!--          <view class="uni-tip-content text-bold">
                                <template v-if="chat.needPayOpen">
                                  会话未开启，为避免用户被频繁恶意骚扰，只能给关注您的和给您发过消息的用户直接发送消息
                                  &lt;!&ndash;              ，给其他用户发送消息，需要支付10贝壳开启对话&ndash;&gt;
                                </template>
                                <view v-else-if="chat.status === closeStatus" class="row-center">
                                  您已关闭会话，发送消息即可再次开启对话
                                </view>
                                <view v-else class="row-center">
                                  对方关注了您，发送消息即可开启对话
                                </view>
                              </view>-->

                    <!--<view v-if="chat.needPayOpen" class="uni-tip-group-button">
                      <button class="uni-tip-button w40r" type="default" :plain="true" @click="goBack">
                        返回
                      </button>
                      <button class="uni-tip-button w40r" type="primary" @click="payOpenChat">
                        开启对话
                      </button>
                    </view>-->
                    <!--          <view v-for="msg in messages" :id="'m'+msg.id" :key="msg.id"
                                    :class="[msg.type === systemMsgType?'row-center':'cu-item',msg.isMine?'self':'']">
                                <template v-if="msg.type === systemMsgType">
                                  <view class="cu-info round">
                                    {{ msg.content }}
                                  </view>
                                </template>
                                <template v-else-if="msg.isMine">

                                </template>
                                <template v-else>
                                  <image class="cu-avatar bd-radius"
                                         :src="msg.user.avatar"
                                         @click="toUserDetailVue(msg.user.id)"
                                  />
                                  <view class="flex-col w100p">
                                    <view class="ml-20 h44 row-col-center">
                                      {{ msg.user.nickname }}
                                      &lt;!&ndash;              <text class="text-sm" :class="[msg.user.vipFlag?'text-red':'text-gray']"
                                                          @click="toUserDetailVue(msg.user.id)">
                                                      {{ msg.user.nickname }}
                                                    </text>
                                                    <image v-if="msg.user.vipFlag" class="ml-6 size30 mt-10"
                                                           src="/static/img/crown.png"
                                                           @click="toVipVue"></image>&ndash;&gt;
                                    </view>
                                    <view class="main">
                                      <view class="content bg-white" @longpress="openMessageMoreHandleDialog(msg)">
                                        <text v-if="msg.status === 3"> 发送失败</text>

                                        <img v-if="msg.contentType === 102" class="bd-round size100" :src="msg.contentData.sourcePicture.url"/>
                                        <video v-else-if="msg.contentType === 104" class="bd-round size50" :src="msg.contentData.videoUrl"
                                               enable-danmu danmu-btn controls></video>
                                        <text v-else-if="msg.contentType !== 3"> {{ msg.content }}</text>

                                      </view>
                                    </view>
                                  </view>
                                  <view class="date">{{ formatTime(msg.createTime) }}</view>
                                </template>
                              </view>-->
                    <div v-for="msg in messages" :id="'m'+msg.id" :key="msg.id">


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

                                <div class="row-end-center" @longpress="openMessageMoreHandleDialog(msg)">
                                    <!--                                    <q-icon v-if="msg.status === 3" icon="mdi-alert-circle" size="25" class="mb-nm"/>-->
                                    <!--                                <message-item-content :msg="msg"></message-item-content>-->

                                    <div class="pd-xs bg-white bd-radius"> {{ msg.content }}</div>
                                </div>

                                <div class="col-all-center mt-xs">
                                    <div class="date">
                                        {{ formatTime(msg.createTime) }}
                                    </div>
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
                                        {{ formatTime(msg.createTime) }}
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!--                        <div v-if="msg.type === systemMsgType">
                                                    <view class="cu-info round row-all-center">
                                                        {{ formatTime(msg.createTime) }} , {{ msg.content }}
                                                    </view>

                                                </div>
                                                <div v-else-if="msg.user.isMine" class="flex-row pd-sm">
                                                    <div class="flex-1 flex-col mr overflow-hidden">
                                                        <view class="h44px row-end-center mb-xs">
                                                            {{ mineUser.nickname }}
                                                            &lt;!&ndash;              <text class="text-sm" :class="[msg.user.vipFlag?'text-red':'text-gray']"
                                                                                @click="toUserDetailVue(msg.user.id)">
                                                                            {{ msg.user.nickname }}
                                                                          </text>
                                                                          <image v-if="msg.user.vipFlag" class="ml-6 mr-6 size30 mt-n10"
                                                                                 src="/static/img/crown.png"
                                                                                 @click="toVipVue"></image>&ndash;&gt;
                                                        </view>

                                                        <view class="row-end-center" @longpress="openMessageMoreHandleDialog(msg)">
                                                            <q-icon v-if="msg.status === 3" icon="mdi-alert-circle" size="25" class="mb-nm"/>
                                                            <message-item-content :msg="msg"></message-item-content>
                                                        </view>

                                                        <div class="col-all-center mt-xs">
                                                            <view class="date">{{ formatTime(msg.createTime) }}</view>
                                                        </div>
                                                    </div>
                                                    <image class="size50 bd-radius flex-none"
                                                           :src="mineUser.avatar"
                                                           @click="toUserDetailVue(msg.user.id)"
                                                    />
                                                </div>
                                                <div v-else class="flex-row pd-sm">
                                                    <image class="size50 bd-radius flex-none"
                                                           :src="msg.user.avatar"
                                                           @click="toUserDetailVue(msg.user.id)"
                                                    />
                                                    <div class="flex-1 flex-col mr overflow-hidden">
                                                        <view class="h44px row-col-center mb-xs">
                                                            {{ msg.user.nickname }}
                                                            &lt;!&ndash;              <text class="text-sm" :class="[msg.user.vipFlag?'text-red':'text-gray']"
                                                                                @click="toUserDetailVue(msg.user.id)">
                                                                            {{ msg.user.nickname }}
                                                                          </text>
                                                                          <image v-if="msg.user.vipFlag" class="ml-6 mr-6 size30 mt-n10"
                                                                                 src="/static/img/crown.png"
                                                                                 @click="toVipVue"></image>&ndash;&gt;
                                                        </view>

                                                        <view class="row-start" @longpress="openMessageMoreHandleDialog(msg)">
                                                            <message-item-content :msg="msg"></message-item-content>
                                                        </view>
                                                        <div class="col-all-center mt-xs">
                                                            <view class="date">{{ formatTime(msg.createTime) }}</view>
                                                        </div>
                                                    </div>
                                                </div>-->
                    </div>
                </view>
            </view>
            <!--      <view v-else class="w100p row-center" :class="showMsgHint?'pt-70px':'pt-10px'">
                    <view v-if="chat.loadMore === noMore || messages.length===0" class="py-xs px bg-white bd-radius mt-sm">
                      会话已开启
                    </view>
                    &lt;!&ndash;        <uni-load-more v-else :status="chat.loadMore"></uni-load-more>&ndash;&gt;
                  </view>-->


        </scroll-view>

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
                       :focus="inputFocus"
                       @blur="inputBlur"
                       @focus="inputFocusEvent"
                       @confirm="viewService.sendMsgClick"
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
                <view v-else class="ml-sm">
                    <q-icon icon="plus-circle" size="28" @click="openPhoto"></q-icon>
                </view>
            </view>
            <!--      <view v-show="showEmoji" class="w100vw bg-blue" :style="{height:keyboardHeight+'px'}"></view>-->
        </view>

        <uni-popup ref="deleteReasonDialog" :show="false" :custom="true" :mask-click="false">
            <view class="uni-tip">
                <view class="uni-tip-title">删除原因</view>
                <view class="uni-textarea bd-1 bd-radius">
              <textarea placeholder="*必填，删除原因" v-model="deleteReason"
                        :show-confirm-bar="false"
              />
                </view>
                <view class="uni-common-mt">
                    是否封禁:
                    <switch class="ml-5px" @change="banChange"/>
                </view>
                <view class="uni-tip-group-button">
                    <button class="uni-tip-button w40r" type="default" @click="closeDeleteDialog" :plain="true">取消
                    </button>
                    <button class="uni-tip-button w40r" type="primary" @click="confirmDeleteTalk"
                            :disabled="!deleteReason">确定
                    </button>
                </view>
            </view>
        </uni-popup>

        <uni-popup ref="messageMoreHandleDialog" :custom="true" :mask-click="true">
            <view class="uni-tip w180px">
                <uni-list class="w100px">
                    <uni-list-item :show-arrow="true" title="复制" clickable @click="copyText"/>
                    <uni-list-item v-if="message&&message.isMine" :show-arrow="true" title="撤回消息"
                                   clickable @click="revokeMessage"/>
                    <uni-list-item v-if="message&&!message.isMine" :show-arrow="true" title="举报"
                                   clickable @click="openReportDialog"/>
                </uni-list>
            </view>
        </uni-popup>

        <!--<view v-show="showEmoji" class="emoji-model" :style="{height:emojiModelHeight+'px'}"
              @touchstart="inputBlur">
        </view>-->

        <socialuni-report-dialog ref="reportDialog" :report-info="message"
                                 :report-info-type="reportContentType"></socialuni-report-dialog>
    </div>
</template>

<script lang="ts">
import {Component, Vue} from "vue-facing-decorator";
import LoadMoreType from "socialuni-constant/constant/LoadMoreType";
import ReportContentType from "socialuni-constant/constant/ReportContentType";
import Constants from "socialuni-constant/constant/Constant";
import SocialuniCommonStatus from "socialuni-constant/constant/status/SocialuniCommonStatus";
import UserType from "socialuni-constant/constant/UserType";
import SelectorQuery = UniNamespace.SelectorQuery;
import NodesRef = UniNamespace.NodesRef;
import DateUtil from "socialuni-util/src/util/DateUtil";
import MessageViewParams from "./MessageViewParams";
import SocialuniMessageType from "socialuni-constant/constant/mesaage/SocialuniMessageType";
import AppMsg from "socialuni-constant/constant/AppMsg";
import CosAuthRO from "socialuni-api-base/src/model/cos/CosAuthRO";
import SocialuniProviderType from "socialuni-constant/constant/SocialuniProviderType";
import {onLoad} from "@dcloudio/uni-app";
import {onMounted} from "vue";
import MessageAPI from "socialuni-im-api/src/api/MessageAPI";
import MessageVO from "socialuni-im-api/src/model/RO/MessageVO";
import NodeInfo = UniNamespace.NodeInfo;
import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";
import SocialuniAppUtil from 'socialuni-native-util/src/util/SocialuniAppUtil'
import MsgUtil from 'socialuni-app-sdk/src/util/MsgUtil'
import UserPageUtil from 'socialuni-user-sdk/src/util/UserPageUtil'
import DomFile from 'socialuni-app-sdk/src/model/DomFile'
import CosService from 'socialuni-app-sdk/src/util/CosService'
import {socialAppModule} from 'socialuni-app-sdk/src/store/SocialAppModule'
import SocialuniAppAPI from 'socialuni-app-api/src/api/SocialuniAppAPI'
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import SocialuniMsgViewService from "socialuni-im-sdk/src/logic/SocialuniMsgViewService";
import {socialuniChatModule} from "socialuni-im-sdk/src/store/SocialuniChatModule";
import SocialuniMsgViewLogic from "socialuni-im-sdk/src/viewLogic/SocialuniMsgViewLogic";
import QIcon from "socialuni-ui-uni/src/components/QIcon/QIcon.vue";
import QNavbar from "socialuni-ui-uni/src/components/QNavbar/QNavbar.vue";

@Component(
    {
        components: {QIcon, QNavbar}
    }
)
export default class MessageView extends Vue {
    public $refs!: {
        // reportDialog: SocialuniReportDialog;
        messageMoreHandleDialog: any;
        deleteReasonDialog: any;
    }

    viewLogic = new SocialuniMsgViewLogic()


    viewService = new SocialuniMsgViewService()

    sendclick() {
        this.viewService.sendMsgClick()
    }


    screenHeight: number = socialuniSystemModule.screenHeight
    windowHeight: number = socialuniSystemModule.windowHeight
    msgContent = ''
    inputFocus = false
    noMore: string = LoadMoreType.noMore
    lazyLoadNum = 30
    topId = ''
    deleteReason: string = null
    // 是否封禁
    violation = false
    curMsg: MessageVO = null
    showEmoji = false
    keyboardHeight = 200
    emojiModelHeight = 300
    message: MessageVO = null
    reportContentType: string = ReportContentType.message
    systemMsgType: string = SocialuniMessageType.system
    showMsgHint: boolean = uni.getStorageSync(Constants.showMsgHintKey) !== 'false'
    readonly waitOpenStatus: string = SocialuniCommonStatus.waitOpen
    readonly closeStatus: string = SocialuniCommonStatus.close
    upperThreshold = 300
    userId: string = null
    groupId: string = null
    title: string = '聊天'
    queryTime: Date = null


    created() {
        //TODO 同一会话时，这里要改成onRead，不然需要刷新页面才会触发已读的标志。
        onLoad((params: MessageViewParams) => {
            //不这么写refs是空
            onMounted(() => {
                this.init(params)
            })
        })
    }

    get chatId() {
        return socialuniChatModule.chatId
    }

    get chatIndex() {
        return socialuniChatModule.chatIndex
    }

    get chat() {
        return socialuniChatModule.chat
    }


    get messages() {
        return socialuniChatModule.messages
    }

    get scrollTop() {
        return socialuniChatModule.scrollTop
    }

    get mineUser() {
        return socialuniUserModule.mineUser
    }

    get pageTitle() {
        return this.chat.nickname
    }


    openMessageMoreHandleDialog(message: MessageVO) {
        this.message = message
        this.$refs.messageMoreHandleDialog.open()
    }

    upper() {
        //只有为more才允许加载
        if (this.chat.loadMore === LoadMoreType.more) {
            // 执行正在加载动画
            this.chat.loadMore = LoadMoreType.loading
            this.queryMessages()
        }
    }

    consoleMessage() {
        console.log(this.chat)
        console.log(this.chat.messages)
        console.log(this.chat.messages.length)
    }

    closeShowMsgHint() {
        this.showMsgHint = false
        uni.setStorageSync(Constants.showMsgHintKey, 'false')
    }

    banChange({detail}) {
        this.violation = detail.value
    }

    formatTime(time) {
        return DateUtil.formatTime(time)
    }

    /* showEmojiClick () {
      SocialuniAppUtil.UniUtil.s.delayTime(100).then(() => {
        this.showEmoji = true
      })
    }

    fixedClick () {
    }
     */

    // { detail }
    /* inputFocusEvent () {
      /!* this.showEmoji = false
      const height: number = detail.height
      if (height > 60) {
        this.keyboardHeight = height - 50
      }
      // 算出来输入框的高度余量
      this.emojiModelHeight = this.windowHeight - (this.keyboardHeight + 56) *!/
      this.inputFocus = true
    } */

    inputFocusEvent() {
        MsgUtil.cantPopupPromptToast()
    }

    inputBlur() {
        if (this.inputFocus) {
            this.inputFocus = false
        }
        // #ifndef MP-WEIXIN
        // #endif
        /*
        this.showEmoji = false */
    }

    test111() {
        console.log(123)
    }

    openPhoto() {
        const that = this;
        const itemList: string[] = ['图片', '视频', '录音'];
        if (this.userId) {
            itemList.push('删除对方聊天记录');
        }

        //调用相册api，可选择拍照和引用相册
        SocialuniAppUtil.NativeUtil.actionSheet(itemList).then((index: number) => {
            switch (itemList[index]) {
                case '图片':
                    that.chooseImage();
                    break;
                case '视频':
                    that.chooseVideo();
                    break;
                case '录音':
                    SocialuniAppUtil.AlertUtil.error('暂不支持,开发中');
                    break;
                case '删除对方聊天记录':
                    socialuniChatModule.pushCustomMessage(socialuniUserModule.userId, "{}", "发送删除对方聊天记录");
                    break;
                default :
                    break;
            }
        }).catch(() => {

        })
    }

    async chooseVideo() {
        //获取cos认证信息
        const cosAuthRO: CosAuthRO = await CosService.getCosAuthRO()
        //获取cos认证信息
        const imgFiles: DomFile[] = await SocialuniAppUtil.NativeUtil.chooseVideo();

        if (cosAuthRO) {
            imgFiles.forEach(item => {
                item.src = cosAuthRO.uploadImgPath + 'im/video/' + item.fileName
                item.url = socialAppModule.cosHttpPath + item.src;
            })
            // 上传
            await CosService.postImgList(imgFiles, cosAuthRO)
            // 发送图片
            imgFiles.forEach(item => {
                socialuniChatModule.pushVideoMessage(item.url);
            });
        } else {
            SocialuniAppAPI.sendErrorLogAPI(null, '用户发表动态失败，未获取上传图片所需要的认证信息')
            SocialuniAppUtil.AlertUtil.error(AppMsg.uploadFailMsg)
        }
    }

    /**
     * 图片前台压缩，往后台传一个压缩后的可看清的图，然后后台弄出来一个压缩图，
     */
    async chooseImage() {
        //获取cos认证信息
        const cosAuthRO: CosAuthRO = await CosService.getCosAuthRO()
        const imgFiles: DomFile[] = await SocialuniAppUtil.NativeUtil.chooseImage(1)
        //  this.showImgFiles.push(...imgFiles)
        if (cosAuthRO) {
            imgFiles.forEach(item => {
                //只有不包含，才赋值src，有值代表已经赋值过了
                if (item.src.indexOf('https') < 0) {
                    item.src = cosAuthRO.uploadImgPath + 'im/img/' + item.src
                    item.url = socialAppModule.cosHttpPath + item.src;
                }
            })
            console.log('-----------imgFiles--------', imgFiles);
            // 上传
            await CosService.postImgList(imgFiles, cosAuthRO)
            // 发送图片
            imgFiles.forEach(item => {
                socialuniChatModule.pushImageMessage(item.url);
            });
        } else {
            SocialuniAppAPI.sendErrorLogAPI(null, '用户发表动态失败，未获取上传图片所需要的认证信息')
            SocialuniAppUtil.AlertUtil.error(AppMsg.uploadFailMsg)
        }
    }


    confirmDeleteTalk(msg: MessageVO) {
        if (this.mineUser.type === UserType.systemUser) {
            this.deleteMsgAction(this.curMsg)
        } else {
            SocialuniAppUtil.AlertUtil.confirm('是否确定删除此条消息，此操作无法恢复').then(() => {
                this.deleteMsgAction(msg)
            })
        }
        this.closeDeleteDialog()
    }

    deleteMsgAction(msg: MessageVO) {
        // socialuniChatModule.deleteMsgAction(msg.id)
        // MessageAPI.deleteMsgAPI(msg.id, this.deleteReason, this.violation)
    }

    openDeleteDialog() {
        this.$refs.deleteReasonDialog.open()
    }

    closeDeleteDialog() {
        this.$refs.deleteReasonDialog.close()
        this.initData()
    }

    initData() {
        this.curMsg = null
        this.deleteReason = ''
    }

    toUserDetailVue(userId: string) {
        UserPageUtil.toUserDetail(userId)
    }

    openMoreMenu() {
        if (this.groupId) {
            UserPageUtil.toIMGroupMember(this.groupId); // 权限问题，内容有问题。
        } else {
            UserPageUtil.toUserDetail(this.userId);
        }

    }

    toVipVue() {
        UserPageUtil.toVipPage()
    }

    get showLoadMore() {
        /**
         * 这里有坑，如果使用加载更多，则加载更多msg后滚动会出现问题，滚动不到之前的那条，待修复的问题
         */
        return this.chat.loadMore !== LoadMoreType.more
    }

    get msgIds() {
        if (this.messages.length) {
            return this.messages.map(item => {
                if (item && item.id) {
                    return item.id
                } else {
                    return ''
                }
            })
        }
        return []
    }

    queryChat() {

    }

    queryMessages(initQuery: boolean = false) {
        // console.trace('chaxun')
        return MessageAPI.queryMessagesAPI(this.chat.id, this.queryTime).then((res) => {
            const resMessages: MessageVO[] = res.data
            //获取拼接消息之前，顶部消息的位置
            // const nodeBox: NodesRef = query.select('.scrollView')
            const query: SelectorQuery = uni.createSelectorQuery().in(this)
            //保持当前位置使用
            let preFirstMsgId: string = null
            if (this.messages.length) {
                preFirstMsgId = '#m' + this.messages[0].id
            }
            const nodeBox: NodesRef = query.select(preFirstMsgId)
            nodeBox.boundingClientRect((preNodeRes: NodeInfo) => {
                let preTop = 0
                if (preNodeRes) {
                    preTop = preNodeRes.top
                }
                // this.topId = lastFirstMsgId
                // 如果还有大于等于30个就还可以加载
                if (resMessages && resMessages.length >= this.lazyLoadNum) {
                    this.chat.loadMore = LoadMoreType.more
                } else {
                    // 否则没有了
                    this.chat.loadMore = LoadMoreType.noMore
                }
                if (resMessages.length) {
                    this.queryTime = resMessages[0].createTime
                    if (initQuery) {
                        socialuniChatModule.chat.messages = resMessages
                        socialuniChatModule.readChatAction(socialuniChatModule.chat.messages)
                    } else {
                        socialuniChatModule.chat.messages.unshift(...resMessages)
                        socialuniChatModule.readChatAction(socialuniChatModule.chat.messages)
                        //获取添加后的之前顶部位置，然后滚动到此位置
                        this.$nextTick(() => {
                            const query: SelectorQuery = uni.createSelectorQuery().in(this)
                            // const nodeBox: NodesRef = query.select('.scrollView')
                            const nodeBox: NodesRef = query.select(preFirstMsgId)
                            nodeBox.boundingClientRect((lastNodeRes: NodeInfo) => {
                                if (lastNodeRes) {
                                    socialuniChatModule.scrollTop = lastNodeRes.top - preTop
                                }
                            }).exec()
                        })
                    }
                }
            }).exec()
            /* setTimeout(() => {
               const query: SelectorQuery = uni.createSelectorQuery().in(this)
               // const nodeBox: NodesRef = query.select('.scrollView')
               const nodeBox: NodesRef = query.select(preFirstMsgId)
               nodeBox.boundingClientRect((lastNodeRes) => {
                 if (res) {
                   console.log(lastNodeRes)
                   console.log(preTop)
                   chatModule.scrollTop = lastNodeRes.top - preTop
                   console.log(chatModule.scrollTop)
                 }
               }).exec()
               // this.topId = lastFirstMsgId
               // 如果还有大于等于30个就还可以加载
               if (res.data && res.data.length >= this.lazyLoadNum) {
                 this.chat.loadMore = LoadMoreType.more
               } else {
                 // 否则没有了
                 this.chat.loadMore = LoadMoreType.noMore
               }
             }, 100)*/
        })
    }

    copyText() {
        SocialuniAppUtil.NativeUtil.textCopy(this.message.content)
        this.closeMessageMoreDialog()
        this.initChooseCommentData()
    }

    /**
     * 撤回某条消息
     */
    async revokeMessage() {
        (await socialuniChatModule.openIm()).revokeMessage(JSON.stringify(this.message.originalMsg)).then(({data}) => {
            console.log('revokeMessage', data);
        }).catch(err => {
            console.log('revokeMessage---err', err);
        })
        this.closeMessageMoreDialog()
        this.initChooseCommentData()
    }

    closeMessageMoreDialog() {
        this.$refs.messageMoreHandleDialog.close()
    }

    initChooseCommentData() {
        this.message = null
    }

    openReportDialog() {

        this.closeMessageMoreDialog()
        SocialuniAppUtil.AlertUtil.error('暂不支持,开发中');
        console.log('-----举报--openReportDialog-------')
        // this.$refs.reportDialog.openReport()
    }

    //正在开启Chat
    isOpeningChatDisableBtn = false

    //1，如果为关闭，则显示发起开启，和待开启一样，只要状态不为代开起，就没有pay
    //2.如果为代开起，付费，则需要点击开启，或者发送时提示是否要给对方发送并开启会话
    //3. 如果为被关闭，则不显示，发送消息报错
    // 先判断消息，然后那些状态那里无所谓，取消了也无所谓，就是消息不发送
    //刚触发点击开启的方法
    async openChatPromise(content) {
        if (!this.isOpeningChatDisableBtn) {
            this.isOpeningChatDisableBtn = true
            try {
                //需要付费
                if (this.chat.needPayOpen) {
                    const userShell = this.mineUser.shell
                    //不需要充值提示
                    if (userShell >= 10) {
                        await this.openChatAndPrompt('会话未开启，是否消耗10个贝壳开启与 ' + this.chat.nickname + ' 的对话，并给对方发送消息：' + content, content)
                        //需要充值提示
                    } else {
                        await SocialuniAppUtil.AlertUtil.confirm('会话未开启，您没有贝壳了，是否直接使用现金支付开启开启与 ' + this.chat.nickname + ' 的对话，并给对方发送消息：' + content, content)
                        const provider = socialuniSystemModule.isMp ? socialuniSystemModule.provider : SocialuniProviderType.wx
                        try {
                            // await PlatformUtils.payCoin(1)
                        } catch (e) {
                            MsgUtil.notPay()
                        }
                        //校验了有用户后清空消息
                        this.msgContent = ''
                        await socialuniChatModule.openChatAction(content)
                    }
                    //不需要付费
                } else {
                    await this.openChatAndPrompt('是否确认开启与 ' + this.chat.nickname + ' 的对话，并给对方发送消息：' + content, content)
                }
                return
            } catch (e) {
                throw Error(e)
            }
        } else {
            await SocialuniAppUtil.ToastUtil.toast('会话开启中，请稍等')
        }
        throw Error()
    }

    //只有待开启，需付费，才会触发此方法
    payOpenChat() {
        MsgUtil.notPay()
        /*this.openChatPromise(this.msgContent || HintMsg.payOpenDefaultMsg).finally(() => {
          this.isOpeningChatDisableBtn = false
        })*/
    }

    //校验已通过，最后一个确认， 是否确认开启
    openChatAndPrompt(hintMsg: string, content: string) {
        return SocialuniAppUtil.AlertUtil.confirm(hintMsg).then(() => {
            //校验了有用户后清空消息
            this.msgContent = ''
            return socialuniChatModule.openChatAction(content)
        })
    }

    //开启聊天支付
    shellPayForUserContact() {

    }

    goBack() {
        SocialuniAppUtil.RouterUtil.goBack()
    }
}
</script>
