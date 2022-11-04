<template>
  <view class="ConversationCard" @click="conversationCardItemClick">
    <view v-if="isNotice" class="ConversationCard-notice">
      {{ msg }}
    </view>
    <view
      v-else
      :class="[
        'ConversationCard-message',
        isSelf ? 'ConversationCard-self' : 'ConversationCard-other',
      ]"
    >
      <view class="checkbox">
        <slot name="checkbox" />
      </view>
      <view class="avatar">
        <Avatar
          :src="card.senderFaceUrl"
          :name="senderNickname"
          :isGroupChat="isGroupChat"
          size="42px"
          @click="toInfo"
          @createAtMessage="createAtMessage"
        />
        <!--  -->
      </view>
      <view class="content">
        <view class="content-nickname" v-show="!isSelf && isGroupChat">
          {{ senderNickname }}
        </view>
        <view :class="['content-message', 'content-message-' + contentType]">
          <view
            style="display: flex"
            @touchstart.stop="touchstart"
            @touchend.stop="touchend"
          >
            <MessageItem v-if="isTextMessage" :list="content101" />
            <image
              v-else-if="contentType === 102"
              :src="messageImage.normalSrc"
              class="messageImage"
              :style="messageImage.normalStyle"
            />
            <view
              class="messageSound"
              @click="playSound"
              v-else-if="contentType === 103"
            >
              <view class="messageSound-top">
                <image
                  v-show="!isSelf"
                  class="messageSound-top-image"
                  src="@/static/images/conversation/sound/sound-other.png"
                />
                <text class="messageSound-top-duration">
                  {{ messageSound.duration | formatSoundDuration }}``
                </text>
                <image
                  v-show="isSelf"
                  class="messageSound-top-image"
                  src="@/static/images/conversation/sound/sound-self.png"
                />
              </view>
              <view class="messageSound-bottom">
                {{ card.sendTime | formatTime2 }}
              </view>
            </view>
            <view class="messageVideo" v-else-if="contentType === 104">
              <image
                :src="messageVideo.snapshotUrl"
                :style="messageVideo.style"
              />
              <image class="play" src="@/static/images/conversation/play.png" />
              <text class="duration">
                {{ messageVideo.duration | formatDuration }}
              </text>
            </view>
            <view
              class="messageFile"
              @click="toFile"
              v-else-if="contentType === 105"
            >
              <image
                class="messageFile-image"
                src="@/static/images/conversation/file/document1.png"
              />
              <view class="messageFile-right">
                <text class="fileName">
                  {{ messageFile.fileName }}
                </text>
                <text class="fileSize">
                  {{ messageFile.fileSize | formatFileSize }}
                </text>
              </view>
            </view>
            <view v-else-if="contentType === 107" class="mergeContent">
              <view class="mergeContent-title">{{ mergeContent.title }}</view>
              <view
                class="mergeContent-item"
                v-for="(text, index) in mergeContent.abstractList"
                :key="index"
              >
                {{ text }}
              </view>
            </view>
            <MessageShareUserCard
              :card="card"
              v-else-if="contentType === 108"
            />
            <LocationCard
              v-else-if="contentType === 109"
              :locationElem="card.locationElem"
            />
            <view v-else class="msg">{{ msg }}</view>
          </view>
          <view
            v-show="isSelf && messageStatus === 1"
            :class="['isRead', card.isRead ? 'isRead-hasRead' : '']"
          >
            {{ card.isRead ? "已读" : "未读" }}
          </view>
          <view class="messageStatus" v-show="messageStatus !== 1">
            <u-icon
              @click="reSendMessage"
              v-show="messageStatus === -1"
              name="error"
              color="#ff0000"
              size="20"
              bold
            />
            <u-loading-icon v-show="messageStatus === 0" />
          </view>
          <view :class="['tool', getTooltipClass]" v-show="tooltip.show">
            <view
              class="tool-item"
              v-for="(item, index) in toolTipsList"
              :key="index"
              @click="toolItemClick(item.cb)"
            >
              <image class="image" :src="item.src" />
              <text class="text">{{ item.text }}</text>
            </view>
          </view>
        </view>
        <view class="content-quote" v-if="contentType === 114">
          <MessageItem :list="content114">
            回复{{ quoteMessage.quoteMessage.senderNickname }}:
          </MessageItem>
        </view>
      </view>
    </view>
    <u-modal
      :show="tooltip.showDel"
      title=""
      content="确山删除?"
      showCancelButton
      @cancel="tooltip.showDel = false"
      @confirm="confirmDel"
    />
  </view>
</template>

<script>
import Avatar from "@/components/Avatar.vue";
import MessageItem from "@/components/MessageItem.vue";
import MessageShareUserCard from "@/components/MessageShareUserCard.vue";
import LocationCard from "@/components/LocationCard.vue";
import { parseTime } from "@/utils/index";
import { playAudio } from "@/utils/innerAudioContext";
import { mapGetters } from "vuex";
import {
  formatConversationCardMessage,
  getConversationCardContentType,
  isNoticeMessage,
  getEmojiContent,
} from "@/utils/formatMessage";
import copy from "@/static/images/conversation/copy.png";
import del from "@/static/images/conversation/del.png";
import share from "@/static/images/conversation/share.png";
import reply from "@/static/images/conversation/reply.png";
import back from "@/static/images/conversation/back.png";
import check from "@/static/images/conversation/check.png";
import translate from "@/static/images/conversation/translate.png";
// import { faceArr } from "@/pages/conversation/emojiList";
export default {
  components: { Avatar, MessageShareUserCard, MessageItem, LocationCard },
  props: {
    card: {
      type: Object,
      default: () => {},
    },
    isSingleChat: {
      type: Boolean,
      default: false,
    },
    isGroupChat: {
      type: Boolean,
      default: false,
    },
    checkboxShow: {
      type: Boolean,
      default: false,
    },
    hideAllToolTimes: {
      type: Number,
      default: 0,
    },
    sourceID: {
      type: String,
      default: "",
    },
    friendInfo: {
      type: Object,
      default: () => null,
    },
  },
  data() {
    return {
      tooltip: {
        show: false,
        list: [
          { text: "复制", cb: "copy", src: copy },
          { text: "删除", cb: "del", src: del },
          { text: "转发", cb: "share", src: share },
          { text: "回复", cb: "reply", src: reply },
          { text: "撤回", cb: "revoke", src: back },
          { text: "多选", cb: "doCheck", src: check },
          { text: "翻译", cb: "translate", src: translate },
        ],
        minTop: 170,
        isShowTop: true,
        showDel: false,
      },
      tapData: {
        //区分点击事件、长按事件
        startTime: null,
        clickTime: 140, //间隔小于140点击事件
        longtapTime: 200, //间隔大于200长按事件
        longtapTimer: null,
      },
      content101: [],
      content114: [],
    };
  },
  created() {
    this.init();
  },
  methods: {
    init() {
      if (this.contentType === 101) {
        const item = {
          type: "text",
          content: this.card.content,
          startIndex: 0,
          endIndex: this.card.content.length - 1,
        };
        this.content101 = getEmojiContent([item]);
      } else if (this.contentType === 114) {
        const item = {
          type: "text",
          content: this.quoteMessage.text,
          startIndex: 0,
          endIndex: this.quoteMessage.text.length - 1,
        };
        const quoteMessage = formatConversationCardMessage(
          this.quoteMessage.quoteMessage
        );
        const item2 = {
          type: "text",
          content: quoteMessage,
          startIndex: 0,
          endIndex: quoteMessage.length - 1,
        };
        this.content101 = getEmojiContent([item]);
        this.content114 = getEmojiContent([item2]);
      } else if (this.contentType === 106 && this.isGroupChat) {
        this.content101 = this.getGroupMembersInfo();
      }
    },
    getGroupMembersInfo() {
      const content = JSON.parse(this.card.content) || null;
      if (!content) return [];
      console.log(content);
      let textArr = content.text.split("@");
      let atUsersInfo = content.atUsersInfo || [];
      let atUserList = content.atUserList || [];
      let textArrCurrentIndex = 0;
      textArr = textArr.map((t, j) => {
        const text = j === 0 ? t : "@" + t;
        const length = text.length;
        const index = textArrCurrentIndex;
        textArrCurrentIndex += length;
        return { text, index };
      });
      if (textArr[0].text === "") {
        textArr.splice(0, 1);
      }
      let atMemberPositionList = [];
      const fn = (atUserID, groupNickname) => {
        const nickname = "@" + atUserID;
        const index = textArr.findIndex((item) => {
          return item.text.indexOf(nickname) === 0;
        });
        if (index !== -1) {
          const textArrItem = textArr[index];
          let startIndex = textArrItem.index;
          let endIndex = startIndex + nickname.length - 1;
          atMemberPositionList.push({
            startIndex,
            endIndex,
            type: "atUser",
            content: content.text.slice(startIndex, endIndex + 1),
            userID: atUserID,
            nickname: groupNickname,
          });
          let startIndex2 = endIndex + 1;
          let endIndex2 = startIndex + textArrItem.text.length - 1;
          if (startIndex2 <= endIndex2) {
            atMemberPositionList.push({
              startIndex: startIndex2,
              endIndex: endIndex2,
              type: "text",
              content: content.text.slice(startIndex2, endIndex2 + 1),
            });
          }
          textArr.splice(index, 1);
        }
      };
      if (atUsersInfo.length) {
        atUsersInfo.map((member) => {
          fn(member.atUserID, member.groupNickname);
        });
      } else {
        atUserList.map((id) => {
          fn(id, id);
        });
      }
      textArr.map((textArrItem) => {
        let startIndex = textArrItem.index;
        const index = atMemberPositionList.findIndex(
          (i) => i.startIndex === startIndex
        );
        if (index === -1) {
          let endIndex = startIndex + textArrItem.text.length - 1;
          atMemberPositionList.push({
            startIndex,
            endIndex,
            type: "text",
            content: content.text.slice(startIndex, endIndex + 1),
          });
        }
      });
      atMemberPositionList = atMemberPositionList.sort((a, b) => {
        return a.startIndex - b.startIndex;
      });
      return getEmojiContent(atMemberPositionList);
    },
    toInfo() {
      if (!this.isSelf && !this.checkboxShow) {
        const id = this.card.sendID || "";
        const groupID = this.card.groupID || "";
        uni.navigateTo({
          url: "/pages/friend/info?id=" + id + "&groupID=" + groupID,
        });
      }
    },
    getImageUrl() {
      const { conversationType, faceURL } = this.card;
      if (conversationType === 2) {
        return "@/static/images/message/group-icon.png";
      }
      return faceURL;
    },
    touchstart() {
      this.tapData.startTime = new Date().getTime();
      this.tapData.longtapTimer = setTimeout(() => {
        this.messageItemLongtap();
      }, this.tapData.longtapTime);
    },
    touchend() {
      if (this.tapData.longtapTimer) {
        clearTimeout(this.tapData.longtapTimer);
      }
      if (this.isNotice) return;
      const endTime = new Date().getTime();
      const duration = endTime - this.tapData.startTime;
      if (duration <= this.tapData.clickTime) {
        this.messageItemClick();
      } else if (duration >= this.tapData.longtapTime && !this.tooltip.show) {
        // this.messageItemLongtap();
      }
    },
    createAtMessage() {
      if (this.isSelf || this.isBlackUser) return;
      this.$emit("createAtMessage", this.card.sendID, this.card.senderNickname);
    },
    messageItemClick() {
      this.$emit("addHideAllToolTimes");
      if (this.contentType === 102) {
        this.showBigPhoto();
      } else if (this.contentType === 104) {
        this.showVideoPop();
      } else if (this.contentType === 107) {
        this.$store.commit("message/set_operationMessageItem", this.card);
        uni.navigateTo({
          url: "@/mergeInfo",
        });
      } else if (this.contentType === 109) {
        uni.openLocation({
          latitude: this.card.locationElem.latitude,
          longitude: this.card.locationElem.longitude,
          name: this.card.locationElem.description,
          complete: (res) => {
            // console.log(res);
          },
        });
      }
    },
    messageItemLongtap() {
      const query = uni.createSelectorQuery().in(this);
      query
        .select(".content-message")
        .boundingClientRect((res) => {
          const elTop = res.top;
          if (elTop < this.tooltip.minTop) {
            this.tooltip.isShowTop = false;
          } else {
            this.tooltip.isShowTop = true;
          }
          this.$emit("addHideAllToolTimes");
          this.$nextTick(() => {
            setTimeout(() => {
              this.tooltip.show = true;
            }, 0);
          });
        })
        .exec();
    },
    hideTool() {
      this.tooltip.show = false;
    },
    toolItemClick(cb) {
      this[cb]();
    },
    copy() {
      uni.setClipboardData({
        data: this.card.content,
        success: () => {
          this.$toast("复制成功");
        },
      });
    },
    del() {
      this.tooltip.showDel = true;
    },
    confirmDel() {
      this.tooltip.showDel = false;
      this.$im.deleteMessageFromLocalAndSvr(
        this.operationID,
        this.card,
        (res) => {
          if (res.errCode === 0) {
            this.$emit("delMessageItem", this.card);
          } else {
            this.$toast(res.errMsg);
          }
        }
      );
    },
    share() {
      //创建转发消息
      const messageItem = this.$im.createForwardMessage(
        this.operationID,
        this.card
      );
      this.$store.commit("message/set_operationMessageItem", messageItem);
      uni.navigateTo({
        url: "/pages/forward/index",
      });
    },
    reply() {
      //回复消息
      this.$emit("setQuoteMessage", this.card);
    },
    revoke() {
      //撤回消息
      this.$emit("revoke", this.card);
    },
    doCheck() {
      //多选消息
      this.$emit("doCheck", this.card);
    },
    conversationCardItemClick() {
      if (this.checkboxShow) {
        this.$emit("conversationCardItemClick", this.card);
      }
    },
    translate() {},
    showBigPhoto() {
      this.$emit(
        "showBigPhoto",
        this.messageImage.bigPhotoSrc,
        this.messageImage.bigPhotoStyle
      );
    },
    showVideoPop() {
      this.$emit("showVideoPop", this.card.videoElem);
    },
    playSound() {
      playAudio(this.messageSound.sourceUrl);
    },
    toFile() {
      this.$store.commit("message/set_openFile", this.messageFile);
      uni.navigateTo({
        url: "/pages/conversation/file",
      });
    },
    reSendMessage() {
      if (this.isBlackUser) {
        this.$toast("对方已将你加入黑名单");
        return;
      }
      // #ifdef APP-PLUS
      const { messageItem, type } = this.currentMessageStatusItem;
      switch (type) {
        case "normal":
          this.$im.sendMessage(
            this.operationID,
            messageItem.messageContent,
            messageItem.userID,
            messageItem.groupID,
            messageItem.desc
          );
          break;
        case "notoss":
          this.$im.sendMessageNotOss(
            this.operationID,
            messageItem.messageContent,
            messageItem.userID,
            messageItem.groupID,
            messageItem.desc
          );
          break;
      }
      // #endif
    },
    //绝对路径转本地路径
    convertLocalFileSystemURL(path = "") {
      if (path && path.startsWith("/storage")) {
        // #ifdef APP-PLUS
        path = plus.io.convertAbsoluteFileSystem(path);
        // #endif
      }
      return path;
    },
  },
  computed: {
    ...mapGetters([
      "userID",
      "operationID",
      "systemInfo",
      "sendMessageStatusList",
    ]),
    msg() {
      return formatConversationCardMessage(this.card);
    },
    currentMessageStatusItem() {
      const item =
        this.sendMessageStatusList.filter(
          (i) =>
            i.messageItem.messageContent.clientMsgID === this.card.clientMsgID
        )[0] || null;
      return item;
    },
    messageStatus() {
      let status = 1;
      //0：发送中,1：发送成功,-1:发送失败
      if (this.isSelf && this.currentMessageStatusItem) {
        status = this.currentMessageStatusItem.status;
      }
      if (this.haveSendMessageStatus === 3) {
        status = -1;
      }
      return status;
    },
    haveSendMessageStatus() {
      return this.card.status;
    },
    isBlackUser() {
      return (
        (this.isSingleChat &&
          this.currentMessageStatusItem &&
          this.currentMessageStatusItem.errCode &&
          this.currentMessageStatusItem.errCode === 600) ||
        this.haveSendMessageStatus === 3
      );
    },
    contentType() {
      return getConversationCardContentType(this.card);
    },
    mergeContent() {
      const obj = {
        title: "",
        abstractList: [],
      };
      //合并消息内容
      if (this.contentType === 107) {
        obj.title = this.card.mergeElem.title;
        obj.abstractList = this.card.mergeElem.abstractList;
      }
      return obj;
    },
    isSelf() {
      return this.card.sendID === this.userID;
    },
    senderNickname() {
      let name = this.card.senderNickname;
      if (
        !this.isSelf &&
        this.friendInfo &&
        this.friendInfo.friendInfo &&
        this.friendInfo.friendInfo.remark
      ) {
        name = this.friendInfo.friendInfo.remark;
      }
      return name;
    },
    isNotice() {
      return isNoticeMessage(this.contentType, this.card.msgFrom);
    },
    getTooltipClass() {
      return `tool-${this.isSelf ? "self" : "other"}-${
        this.tooltip.isShowTop ? "top" : "bottom"
      }`;
    },
    toolTipsList() {
      let list = [...this.tooltip.list];
      if (!this.isSelf) {
        list = list.filter((i) => i.cb !== "revoke"); //撤回
      }
      const textMessageType = [101, 106];
      if (!textMessageType.includes(this.contentType)) {
        list = list.filter((i) => i.cb !== "copy" && i.cb !== "translate");
      }
      return list;
    },
    messageImage() {
      let normalSrc = "";
      let bigPhotoSrc = "";
      let normalStyle = { width: 0, height: 0 };
      let bigPhotoStyle = { width: 0, height: 0 };
      if (this.contentType === 102) {
        let maxWidth = (190 / 375) * this.systemInfo.screenWidth;
        let { url, width, height } = this.card.pictureElem.snapshotPicture;
        normalSrc = url || this.card.pictureElem.sourcePath;
        normalSrc = this.convertLocalFileSystemURL(normalSrc);
        if (width > maxWidth) {
          normalStyle.width = maxWidth + "px";
          normalStyle.height = (maxWidth / width) * height + "px";
        } else {
          normalStyle.width = width === 0 ? "120px" : width + "px";
          normalStyle.height = height === 0 ? "120px" : width + "px";
        }
        bigPhotoSrc =
          this.card.pictureElem.sourcePicture.url ||
          this.card.pictureElem.sourcePath;
        bigPhotoSrc = this.convertLocalFileSystemURL(bigPhotoSrc);
        bigPhotoStyle.width =
          this.card.pictureElem.sourcePicture.width === 0
            ? "120px"
            : this.card.pictureElem.sourcePicture.width;
        bigPhotoStyle.height =
          this.card.pictureElem.sourcePicture.height === 0
            ? "120px"
            : this.card.pictureElem.sourcePicture.height;
      }
      return { normalSrc, normalStyle, bigPhotoSrc, bigPhotoStyle };
    },
    messageVideo() {
      let duration = "";
      let videoUrl = "";
      let snapshotUrl = "";
      let snapshotWidth = "";
      let snapshotHeight = "";
      let style = { width: 0, height: 0 };
      if (this.contentType === 104) {
        duration = this.card.videoElem.duration;
        videoUrl = this.convertLocalFileSystemURL(
          this.card.videoElem.videoUrl || this.card.videoElem.videoPath
        );
        snapshotUrl = this.convertLocalFileSystemURL(
          this.card.videoElem.snapshotUrl || this.card.videoElem.snapshotPath
        );
        snapshotWidth = this.card.videoElem.snapshotWidth;
        snapshotHeight = this.card.videoElem.snapshotHeight;
        let maxWidth = (190 / 375) * this.systemInfo.screenWidth;
        if (snapshotWidth > maxWidth) {
          style.width = maxWidth + "px";
          style.height = (maxWidth / snapshotWidth) * snapshotHeight + "px";
        } else {
          style.width = snapshotWidth + "px";
          style.height = snapshotHeight + "px";
        }
      }
      return {
        duration,
        videoUrl,
        snapshotUrl,
        style,
        snapshotWidth,
        snapshotHeight,
      };
    },
    messageFile() {
      let obj = {
        url: "",
        fileName: "",
        fileSize: "",
      };
      if (this.contentType === 105) {
        obj.url = this.card.fileElem.sourceUrl;
        obj.fileName = this.card.fileElem.fileName;
        obj.fileSize = this.card.fileElem.fileSize;
      }
      return obj;
    },
    messageSound() {
      let obj = {
        sourceUrl: "",
        dataSize: "",
        duration: "",
      };
      if (this.contentType === 103) {
        obj = this.card.soundElem;
        obj.sourceUrl = this.convertLocalFileSystemURL(
          obj.sourceUrl || obj.soundPath
        );
      }
      return obj;
    },
    isTextMessage() {
      return (
        this.contentType === 101 ||
        this.contentType === 106 ||
        this.contentType === 114
      );
    },
    quoteMessage() {
      if (this.contentType === 114) {
        let content = JSON.parse(this.card.content);
        return content;
      }
      return {};
    },
  },
  filters: {
    formatTime(time) {
      const oneDayTime = 24 * 60 * 60 * 1000;
      const now = new Date().getTime();
      if (now - time < oneDayTime) {
        return "昨天";
      }
      return parseTime(time, "{y}/{m}/{d}");
    },
    formatTime2(time) {
      time = new Date(time);
      return parseTime(time, "{m}月{d}日 {h}:{m}");
    },
    formatDuration(d) {
      let m = parseInt(d / 60); //分
      m = m < 10 ? "0" + m : m;
      let s = d % 60; //秒
      s = s < 10 ? "0" + s : s;
      return `${m}:${s}`;
    },
    formatSoundDuration(d) {
      return parseInt(d);
    },
    formatFileSize(fileSize) {
      fileSize = fileSize / 1024;
      let size = "";
      const size1M = 1024;
      const size1G = size1M * 1024;
      if (fileSize && fileSize >= size1G) {
        size = (fileSize / size1G).toFixed(2);
        size += "GB";
      } else {
        size = (fileSize / size1M).toFixed(2);
        size += "MB";
      }
      return size;
    },
  },
  watch: {
    hideAllToolTimes() {
      this.hideTool();
    },
  },
};
</script>

<style lang="scss" scoped>
$pdLeft: 44rpx;
.ConversationCard {
  padding: 0 $pdLeft;
  &-notice {
    text-align: center;
    font-size: 24rpx;
    color: #999;
    margin-bottom: 30rpx;
  }
  &-message {
    display: flex;
    flex-wrap: nowrap;
    align-items: flex-start;
    margin-bottom: 40rpx;
    .avatar,
    .isRead,
    .checkbox,
    .messageStatus {
      flex-shrink: 0;
    }
    .content {
      display: flex;
      flex-direction: column;
      // overflow: hidden;
      &-nickname {
        font-size: 24rpx;
        color: #666666;
        margin-bottom: 4rpx;
      }
      &-message {
        display: flex;
        flex-wrap: nowrap;
        justify-content: flex-start;
        background: #f0f0f0;
        padding: 14rpx;
        border-radius: 8rpx;
        position: relative;
        .tool {
          position: absolute;
          width: 400rpx;
          z-index: 1;
          padding-top: 24rpx;
          background: #666666;
          border-radius: 8rpx;
          display: flex;
          flex-wrap: wrap;
          &-item {
            width: 25%;
            display: flex;
            flex-direction: column;
            text-align: center;
            align-items: center;
            // margin: 0 0 24rpx;
            margin-bottom: 24rpx;
            .image {
              width: 36rpx;
              height: 36rpx;
            }
            .text {
              font-size: 20rpx;
              color: #ffffff;
            }
          }
          &::before {
            position: absolute;
            content: "";
            width: 0;
            height: 0;
            border: 12rpx solid transparent;
          }
          &-self {
            &-top {
              right: 0;
              bottom: calc(100% + 40rpx);
              &::before {
                border-top-color: #666666;
                top: 100%;
                right: 50rpx;
              }
            }
            &-bottom {
              right: 0;
              top: calc(100% + 40rpx);
              &::before {
                border-bottom-color: #666666;
                bottom: 100%;
                right: 50rpx;
              }
            }
          }
          &-other {
            &-top {
              left: 0;
              bottom: calc(100% + 40rpx);
              &::before {
                border-top-color: #666666;
                top: 100%;
                left: 50rpx;
              }
            }
            &-bottom {
              left: 0;
              top: calc(100% + 40rpx);
              &::before {
                border-bottom-color: #666666;
                bottom: 100%;
                left: 50rpx;
              }
            }
          }
        }
        .msg {
          font-size: 26rpx;
          color: #666666;
          overflow: hidden;
          text-overflow: ellipsis;
          white-space: nowrap;
        }
        &::before {
          position: absolute;
          right: 100%;
          top: 16rpx;
          content: "";
          width: 0;
          height: 0;
          border: 12rpx solid transparent;
          border-right-color: #f0f0f0;
        }
        &::after {
          position: absolute;
          left: 100%;
          top: 16rpx;
          content: "";
          width: 0;
          height: 0;
          border: 12rpx solid transparent;
          border-left-color: #f0f0f0;
        }
        &-102,
        &-104,
        &-108,
        &-109 {
          padding: 0;
          &::before,
          &::after {
            display: none;
          }
        }
        &-109 {
          border-radius: 12rpx;
          border: 2rpx solid #e6e6e6;
          background-color: #fff;
        }
        .isRead {
          content: "";
          position: absolute;
          bottom: 4rpx;
          right: 100%;
          font-size: 24rpx;
          margin-right: 12rpx;
          color: #006aff;
          white-space: nowrap;
          &-hasRead {
            color: #999999;
          }
        }
        .messageStatus {
          content: "";
          position: absolute;
          bottom: 4rpx;
          right: 100%;
          margin-right: 12rpx;
        }
        .mergeContent {
          &-title {
            padding: 0 8rpx 14rpx;
            border-bottom: 2rpx solid #d8d8d8;
            font-size: 30rpx;
            color: #333333;
          }
          &-item {
            padding: 0 8rpx;
            margin-top: 8rpx;
            font-size: 22rpx;
            color: #666666;
          }
        }
        .messageImage {
          display: inline-block;
          border-radius: 24rpx;
          overflow: hidden;
        }
        .messageVideo {
          border-radius: 24rpx;
          overflow: hidden;
          position: relative;
          display: flex;
          .play {
            position: absolute;
            z-index: 1;
            left: 50%;
            top: 50%;
            transform: translate3d(-50%, -50%, 0);
            width: 60rpx;
            height: 60rpx;
          }
          .duration {
            position: absolute;
            z-index: 1;
            right: 12rpx;
            bottom: 12rpx;
            color: #fff;
            font-size: 24rpx;
          }
          &::before {
            content: "";
            width: 100%;
            height: 100%;
            position: absolute;
            left: 0;
            top: 0;
            z-index: 1;
            background-color: rgba(0, 0, 0, 0.5);
          }
        }
        .messageFile {
          border-radius: 24rpx;
          display: flex;
          flex-direction: row;
          flex-wrap: nowrap;
          align-items: center;
          &-image {
            width: 44rpx;
            height: 56rpx;
          }
          &-right {
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            align-items: flex-end;
            margin-left: 12rpx;
            .fileName {
              overflow: hidden;
              text-overflow: ellipsis;
              white-space: nowrap;
              font-size: 30rpx;
              color: #333333;
            }
            .fileSize {
              overflow: hidden;
              text-overflow: ellipsis;
              white-space: nowrap;
              font-size: 22rpx;
              color: #666666;
            }
          }
        }
        .messageSound {
          min-width: 200rpx;
          &-top {
            display: flex;
            flex-direction: row;
            align-items: center;
            &-image {
              width: 26rpx;
              height: 38rpx;
            }
            &-duration {
              font-size: 26rpx;
              margin: 0 12rpx;
              line-height: 32rpx;
            }
          }
          &-bottom {
            margin-top: 4rpx;
            text-align: right;
            font-size: 20rpx;
            color: #999999;
          }
        }
      }
      &-quote {
        margin-top: 8rpx;
        background-color: #f0f0f0;
        font-size: 24rpx;
        color: #666666;
        padding: 18rpx 12rpx;
      }
    }
  }
  &-other {
    margin-right: 128rpx;
    .avatar {
      margin-right: 24rpx;
    }
    .checkbox {
      margin-right: 12rpx;
    }
    .content {
      &-message {
        &::after {
          display: none;
        }
      }
    }
  }
  &-self {
    margin-left: 128rpx;
    flex-direction: row-reverse;
    .avatar {
      margin-left: 24rpx;
    }
    .checkbox {
      margin-left: 12rpx;
    }

    .content {
      &-message {
        &::before {
          display: none;
        }
      }
    }
  }
}
</style>
