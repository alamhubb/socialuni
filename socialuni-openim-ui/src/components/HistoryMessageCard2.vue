<template>
  <view :class="['HistoryMessageCard']" @click="toConversation">
    <view class="name">
      <text class="nickname">{{ card.senderNickname }}</text>
      <text class="time">{{ card.sendTime | formatTime }}</text>
    </view>
    <view class="msgContent">
      <image
        v-if="contentType === 102"
        :src="messageImage.normalSrc"
        class="messageImage"
        :style="messageImage.normalStyle"
        @click.stop="showBigPhoto"
      />
      <view
        class="messageVideo"
        v-else-if="contentType === 104"
        @click.stop="showVideoPop"
      >
        <image :src="messageVideo.snapshotUrl" :style="messageVideo.style" />
        <image class="play" src="@/static/images/conversation/play.png" />
        <text class="duration">
          {{ messageVideo.duration | formatDuration }}
        </text>
      </view>
      <view
        class="messageFile"
        @click.stop="toFile"
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
      <view v-else class="msg">{{ msg }}</view>
    </view>
  </view>
</template>

<script>
import { parseTime } from "@/utils/index";
import { formatHistoryCardMessage } from "@/utils/formatMessage";
import Avatar from "@/components/Avatar.vue";
import { mapGetters } from "vuex";
export default {
  components: { Avatar },
  props: {
    card: {
      type: Object,
      default: () => {},
    },
    conversationType: {
      type: Number,
      default: 1,
    },
    conversationID: {
      type: String,
      default: "",
    },
  },
  data() {
    return {};
  },
  methods: {
    toConversation() {
      const clientMsgID = this.card.clientMsgID;
      uni.navigateTo({
        url: `/pages/conversation/index?sessionType=${this.conversationType}&sourceID=${this.conversationID}&clientMsgID=${clientMsgID}`,
      });
    },
    toFile() {
      this.$store.commit("message/set_openFile", this.messageFile);
      uni.navigateTo({
        url: "/pages/conversation/file",
      });
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
  },
  computed: {
    ...mapGetters(["userID", "systemInfo"]),
    msg() {
      return formatHistoryCardMessage(this.card);
    },
    contentType() {
      return this.card.contentType;
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
  },
  filters: {
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
    formatTime(time) {
      const gg = 5 * 60 * 1000;
      const oneDayTime = 24 * 60 * 60 * 1000;
      const now = new Date().getTime();
      const today0 = new Date(
        parseTime(new Date(), "{y}/{m}/{d} 00:00:00")
      ).getTime();
      if (now - time < gg) {
        //5分钟内
        return "刚刚";
      } else if (time >= today0) {
        //今天
        return parseTime(time, "{h}:{i}");
      } else if (now - time > oneDayTime && now - time < oneDayTime * 2) {
        return "昨天";
      }
      return parseTime(time, "{y}/{m}/{d}");
    },
    formatDuration(d) {
      let m = parseInt(d / 60); //分
      m = m < 10 ? "0" + m : m;
      let s = d % 60; //秒
      s = s < 10 ? "0" + s : s;
      return `${m}:${s}`;
    },
  },
};
</script>

<style lang="scss" scoped>
$pdLeft: 44rpx;
.HistoryMessageCard {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 12rpx 0;
  min-height: 42px;
  margin-left: 24rpx;
  border-bottom: 2rpx solid rgba(153, 153, 153, 0.4);
  .faceURL {
    margin-right: 24rpx;
    flex-shrink: 0;
  }
  .name {
    font-size: 28rpx;
    display: flex;
    align-items: center;
    justify-content: space-between;
    .nickname {
      font-weight: 500;
      color: #333;
    }
    .time {
      font-size: 24rpx;
      color: #999;
    }
  }
  .msgContent {
    font-size: 28rpx;
    color: #666;
    overflow: hidden;
    .messageImage {
      display: inline-block;
      border-radius: 24rpx;
      overflow: hidden;
    }
    .messageFile {
      margin-top: 12rpx;
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
    .msg {
      word-break: break-all;
    }
  }
}
</style>