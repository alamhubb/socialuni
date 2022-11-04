<template>
  <view class="mergeInfo">
    <view class="statusBar"></view>
    <u-navbar class="navbar" autoBack :title="title">
        <view class="navbar-right" slot="right">
          <view class="navbar-right-item">
            <image
              @click="share"
              class="image"
              src="@/static/images/conversation/share2.png"
            />
          </view>
        </view>
    </u-navbar>
    <view class="container">
      <view class="dateStr">{{ dateStr }}</view>
      <view
        class="mergeCard"
        v-for="(card, index) in multiMessage"
        :key="card.serverMsgID"
      >
        <view class="mergeCard-avatar">
          <Avatar
            v-show="showUserInfo(index)"
            class="faceURL"
            :src="card.senderFaceUrl"
            :name="card.senderNickname"
            size="42px"
            @click="toInfo(card.sendID)"
          />
        </view>

        <view class="content">
          <view class="content-nickname">
            <text class="text">
              {{ card.senderNickname }}
            </text>
            <text class="text">
              {{ getMessageDateStr(card.sendTime) }}
            </text>
          </view>
          <view
            :class="['content-message', 'content-message-' + card.contentType]"
          >
            <MessageItem
              v-if="
                card.contentType === 101 ||
                card.contentType === 106 ||
                card.contentType === 114
              "
              :list="getContent101(card)"
            />
            <image
              v-else-if="card.contentType === 102"
              :src="getMessageImage(card).normalSrc"
              class="messageImage"
              @click="showBigPhoto(card)"
              :style="getMessageImage(card).normalStyle"
            />
            <view
              class="messageSound"
              @click="playSound"
              v-else-if="card.contentType === 103"
            >
              <view class="messageSound-top">
                <image
                  class="messageSound-top-image"
                  src="@/static/images/conversation/sound/sound-other.png"
                />
                <text class="messageSound-top-duration">
                  {{ messageSound(card).duration | formatSoundDuration }}``
                </text>
              </view>
              <view class="messageSound-bottom">
                {{ card.sendTime | formatTime2 }}
              </view>
            </view>
            <view
              @click="showVideoPop(card)"
              class="messageVideo"
              v-else-if="card.contentType === 104"
            >
              <image
                :src="getMessageVideo(card).snapshotUrl"
                :style="getMessageVideo(card).style"
              />
              <image class="play" src="@/static/images/conversation/play.png" />
              <text class="duration">
                {{ getMessageVideo(card).duration | formatDuration }}
              </text>
            </view>
            <view
              class="messageFile"
              @click="toFile"
              v-else-if="card.contentType === 105"
            >
              <image
                class="messageFile-image"
                src="@/static/images/conversation/file/document1.png"
              />
              <view class="messageFile-right">
                <text class="fileName">
                  {{ messageFile(card).fileName }}
                </text>
                <text class="fileSize">
                  {{ messageFile(card).fileSize | formatFileSize }}
                </text>
              </view>
            </view>
            <view v-else-if="card.contentType === 107" class="mergeContent">
              <view class="mergeContent-title">
                {{ getMergeContent(card).title }}
              </view>
              <view
                class="mergeContent-item"
                v-for="(text, index) in getMergeContent(card).abstractList"
                :key="index"
              >
                {{ text }}
              </view>
            </view>
            <MessageShareUserCard
              :card="card"
              v-else-if="card.contentType === 108"
            />
            <LocationCard
              v-else-if="card.contentType === 109"
              :locationElem="card.locationElem"
            />
          </view>
          <view class="content-quote" v-if="card.contentType === 114">
            <MessageItem :list="getContent101(card)">
              回复{{ card.quoteElem.quoteMessage.senderNickname }}:
            </MessageItem>
          </view>
        </view>
      </view>
    </view>
    <BigPhoto ref="BigPhoto" />
    <VideoPop ref="VideoPop" />
  </view>
</template>

<script>
import Avatar from "@/components/Avatar.vue";
import MessageItem from "@/components/MessageItem.vue";
import MessageShareUserCard from "@/components/MessageShareUserCard.vue";
import BigPhoto from "@/components/BigPhoto";
import VideoPop from "@/components/VideoPop";
import { parseTime } from "@/utils/index";
import { mapGetters } from "vuex";
import {
  formatConversationCardMessage,
  getEmojiContent,
} from "@/utils/formatMessage";
import { playAudio } from "@/utils/innerAudioContext";
import LocationCard from "@/components/LocationCard.vue";
export default {
  components: {
    Avatar,
    MessageShareUserCard,
    BigPhoto,
    VideoPop,
    MessageItem,
    LocationCard,
  },
  data() {
    return {
      messageInfo: {},
      content101: [],
    };
  },
  methods: {
    getMessage(item) {
      return formatConversationCardMessage(item);
    },
    getMessageDateStr(date) {
      return parseTime(new Date(date), "{m}月{d}日 {h}:{i}");
    },
    toInfo(id) {
      uni.navigateTo({
        url: "/pages/friend/info?id=" + id,
      });
    },
    getContent101(card) {
      if (card.contentType === 101) {
        const item = {
          type: "text",
          content: card.content,
          startIndex: 0,
          endIndex: card.content.length - 1,
        };
        return getEmojiContent([item]);
      } else if (card.contentType === 114) {
        const quoteMessage = formatConversationCardMessage(
          card.quoteElem.quoteMessage
        );
        const item2 = {
          type: "text",
          content: quoteMessage,
          startIndex: 0,
          endIndex: quoteMessage.length - 1,
        };
        return getEmojiContent([item2]);
      }
      return [];
    },
    getMessageImage(card) {
      let normalSrc = "";
      let bigPhotoSrc = "";
      let normalStyle = { width: 0, height: 0 };
      let bigPhotoStyle = { width: 0, height: 0 };
      if (card.contentType === 102) {
        let maxWidth = (190 / 375) * this.systemInfo.screenWidth;
        let { url, width, height } = card.pictureElem.snapshotPicture;
        normalSrc = url;
        if (width > maxWidth) {
          normalStyle.width = maxWidth + "px";
          normalStyle.height = (maxWidth / width) * height + "px";
        } else {
          normalStyle.width = width + "px";
          normalStyle.height = height + "px";
        }
        bigPhotoSrc = card.pictureElem.sourcePicture.url;
        bigPhotoStyle.width = card.pictureElem.sourcePicture.width;
        bigPhotoStyle.height = card.pictureElem.sourcePicture.height;
      }
      return { normalSrc, normalStyle, bigPhotoSrc, bigPhotoStyle };
    },
    getMessageVideo(card) {
      let duration = "";
      let videoUrl = "";
      let snapshotUrl = "";
      let snapshotWidth = "";
      let snapshotHeight = "";
      let style = { width: 0, height: 0 };
      if (card.contentType === 104) {
        duration = card.videoElem.duration;
        videoUrl = card.videoElem.videoUrl;
        snapshotUrl = card.videoElem.snapshotUrl;
        snapshotWidth = card.videoElem.snapshotWidth;
        snapshotHeight = card.videoElem.snapshotHeight;
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
    getMergeContent(card) {
      const obj = {
        title: "",
        abstractList: [],
      };
      //合并消息内容
      if (card.contentType === 107) {
        obj.title = card.mergeElem.title;
        obj.abstractList = card.mergeElem.abstractList;
      }
      return obj;
    },
    showUserInfo(index) {
      let show = true;
      const last = this.multiMessage[index - 1] || null;
      if (!last) {
        return show;
      }
      const current = this.multiMessage[index];
      if (current.sendID === last.sendID) {
        show = false;
      }
      return show;
    },
    showBigPhoto(card) {
      const { src, bigPhotoStyle } = this.getMessageImage(card);
      this.$refs.BigPhoto.init(src, bigPhotoStyle);
    },
    showVideoPop(card) {
      this.$refs.VideoPop.init(card.videoElem);
    },
    share() {
      //创建转发消息
      const messageItem = this.$im.createForwardMessage(
        this.operationID,
        this.operationMessageItem
      );
      this.$store.commit("message/set_operationMessageItem", messageItem);
      uni.navigateTo({
        url: "/pages/forward/index",
      });
    },
    messageSound(card) {
      let obj = {
        sourceUrl: "",
        dataSize: "",
        duration: "",
      };
      if (card.contentType === 103) {
        obj = card.soundElem;
        obj.sourceUrl = this.convertLocalFileSystemURL(
          obj.sourceUrl || obj.soundPath
        );
      }
      return obj;
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
    playSound() {
      playAudio(this.messageSound.sourceUrl);
    },
    messageFile(card) {
      let obj = {
        url: "",
        fileName: "",
        fileSize: "",
      };
      if (card.contentType === 105) {
        obj.url = card.fileElem.sourceUrl;
        obj.fileName = card.fileElem.fileName;
        obj.fileSize = card.fileElem.fileSize;
      }
      return obj;
    },
    toFile(card) {
      this.$store.commit("message/set_openFile", this.messageFile(card));
      uni.navigateTo({
        url: "/pages/conversation/file",
      });
    },
  },
  onLoad() {
    // console.log(this.mergeElem);
  },
  computed: {
    ...mapGetters(["operationMessageItem", "systemInfo", "operationID"]),
    mergeElem() {
      return this.operationMessageItem.mergeElem;
    },
    title() {
      return this.mergeElem.title;
    },
    multiMessage() {
      return this.mergeElem.multiMessage;
    },
    dateStr() {
      const startTime = this.multiMessage[0].sendTime;
      const endTime = this.multiMessage[this.multiMessage.length - 1].sendTime;
      return `${parseTime(new Date(startTime), "{y}年{m}月{d}日")} - 
      ${parseTime(new Date(endTime), "{y}年{m}月{d}日")}`;
    },
  },
  filters: {
    formatDuration(d) {
      let m = parseInt(d / 60); //分
      m = m < 10 ? "0" + m : m;
      let s = d % 60; //秒
      s = s < 10 ? "0" + s : s;
      return `${m}:${s}`;
    },
    formatTime2(time) {
      time = new Date(time);
      return parseTime(time, "{m}月{d}日 {h}:{m}");
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
};
</script>
<style lang="scss" scoped>
.mergeInfo {
  background: #f8f8f8;
  min-height: 100vh;
  .navbar {
    height: 44px;
    &-right {
      &-item {
        .image {
          width: 48rpx;
          height: 48rpx;
        }
      }
    }
  }
  .container {
    .dateStr {
      margin: 32rpx 0;
      text-align: center;
      font-size: 12px;
      color: #999999;
    }
    .mergeCard {
      display: flex;
      position: relative;
      margin-top: 24rpx;
      &::after {
        content: "";
        position: absolute;
        top: 100%;
        left: 132rpx;
        height: 2rpx;
        width: 100%;
        background-color: #dfdfdf;
      }
      &-avatar {
        flex-shrink: 0;
        width: 42px;
        height: 42px;
        margin: 0 24rpx;
      }
      .content {
        display: flex;
        flex-direction: column;
        padding-bottom: 24rpx;
        &-nickname {
          font-size: 24rpx;
          color: #666666;
          margin-bottom: 12rpx;
          display: flex;
          align-items: center;
          justify-content: space-between;
          margin-right: 24rpx;
          .text {
            &:nth-of-type(1) {
              margin-right: 24rpx;
              overflow: hidden;
              text-overflow: ellipsis;
              white-space: nowrap;
            }
          }
        }
        &-message {
          display: flex;
          flex-wrap: nowrap;
          justify-content: flex-start;
          background: #f0f0f0;
          padding: 14rpx;
          border-radius: 8rpx;
          position: relative;
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
          &-108 {
            padding: 0;
            &::before,
            &::after {
              display: none;
            }
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
  }
}
</style>