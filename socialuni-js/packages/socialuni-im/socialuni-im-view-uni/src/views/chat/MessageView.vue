<template>
    <div class="pb-50 h100p">
        <q-navbar class="bb" show-back :title="viewService.pageTitle">
            <div class="row-end-center flex-1">
                <q-icon icon="list-dot" size="20" @click="viewService.openMoreMenu"></q-icon>
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
                     @scrolltoupper="viewService.upper"
                     :upper-threshold="viewService.upperThreshold"
                     :show-scrollbar="true"
                     :scroll-top="viewService.scrollTop"
        >
            <!--    <view class="cu-chat">-->
            <view class="w100p h100p">
                <!--      <view v-if="chat.status === waitOpenStatus||chat.status === closeStatus" class="w100p h100p col-row-center">-->
                <view class="mt-80px">
                    <div v-for="msg in viewService.messages" :id="'m'+msg.id" :key="msg.id">
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
                                        {{ viewService.formatTime(msg.createTime) }}
                                    </div>
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

                                <div class="row-start" @longpress="openMessageMoreHandleDialog(msg)">
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
                </view>
            </view>

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
                       @confirm="viewService.sendMsgCl ick"
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
import {Options, Vue} from "vue-property-decorator";
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

@Options(
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


    viewService = new SocialuniMsgViewService()


    screenHeight: number = socialuniSystemModule.screenHeight
    windowHeight: number = socialuniSystemModule.windowHeight
    msgContent = ''

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

    reportContentType: string = ReportContentType.message
    systemMsgType: string = SocialuniMessageType.system
    showMsgHint: boolean = uni.getStorageSync(Constants.showMsgHintKey) !== 'false'
    readonly waitOpenStatus: string = SocialuniCommonStatus.waitOpen
    readonly closeStatus: string = SocialuniCommonStatus.close

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

    get chatIndex() {
        return socialuniChatModule.chatIndex
    }






    get mineUser() {
        return socialuniUserModule.mineUser
    }

    async init(params: MessageViewParams) {
        //  不存在前台缓存的问题,防止页面数据没有被及时刷新。 缓存之前页面的数据。
        //  issue: I6EZ82
        //
        if (!params.chatId) {
            SocialuniAppUtil.AlertUtil.error('缺少会话信息')
        }
        // if (params.nickname) {
        //   chat.nickname = params.nickname
        // }
        socialuniChatModule.setChatId(params.chatId)

        this.queryTime = new Date()
        await this.queryMessages(true)

        socialuniChatModule.scrollToMessagePageBottom()


        // socialuniChatModule.chatId = params.receiveId
        // socialuniChatModule.setCurChatByUserId(params.userId)
        // this.userId = params.userId;
        /*else if (params.groupId) {
          // socialuniChatModule.setCurChatByGroupId(params.groupId)
          // this.groupId = params.groupId;
        }*/
    }


    onUnload() {
        socialuniChatModule.scrollTop = 0
    }

    get pageTitle() {
        return this.chat.nickname
    }


    openMessageMoreHandleDialog(message: MessageVO) {
        this.message = message
        this.$refs.messageMoreHandleDialog.open()
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
        SocialuniAppUtil.UniUtil.actionSheet(itemList).then((index: number) => {
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
        const imgFiles: DomFile[] = await SocialuniAppUtil.UniUtil.chooseVideo();

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
        const imgFiles: DomFile[] = await SocialuniAppUtil.UniUtil.chooseImage(1)
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
        SocialuniAppUtil.UniUtil.textCopy(this.message.content)
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

}
</script>
