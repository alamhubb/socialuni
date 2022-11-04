<template>
  <view :class="['HistoryMessageCard']" @click="toConversation">
    <Avatar
      class="faceURL"
      :src="card.faceURL || 'error'"
      :name="card.showName"
      size="24px"
      fontSize="24rpx"
    />
    <text class="name">{{ card.showName }}:</text>
    <view class="msgContent">
      <view class="messageFile" @click.stop="toFile" v-if="contentType === 105">
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
import {
  formatHistoryCardMessage,
  getHistoryCardContentType,
} from "@/utils/formatMessage";
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
      const sourceID =
        this.card.conversationType === 1 ? this.sourceID : this.card2.groupID;
      const clientMsgID = this.card2.clientMsgID;
      uni.navigateTo({
        url: `/pages/conversation/index?sessionType=${this.card.conversationType}&sourceID=${sourceID}&clientMsgID=${clientMsgID}`,
      });
    },
    toFile() {
      this.$store.commit("message/set_openFile", this.messageFile);
      uni.navigateTo({
        url: "/pages/conversation/file",
      });
    },
  },
  computed: {
    ...mapGetters(["userID"]),
    msg() {
      return formatHistoryCardMessage(this.card2);
    },
    contentType() {
      return getHistoryCardContentType(this.card2);
    },
    sourceID() {
      return this.card2.sendID === this.userID
        ? this.card2.recvID
        : this.card2.sendID;
    },
    card2() {
      return this.card.messageItem;
    },
    messageFile() {
      let obj = {
        url: "",
        fileName: "",
        fileSize: "",
      };
      if (this.contentType === 105) {
        obj.url = this.card2.fileElem.sourceUrl;
        obj.fileName = this.card2.fileElem.fileName;
        obj.fileSize = this.card2.fileElem.fileSize;
      }
      return obj;
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
  },
};
</script>

<style lang="scss" scoped>
$pdLeft: 44rpx;
.HistoryMessageCard {
  display: flex;
  align-items: center;
  flex-wrap: nowrap;
  padding: 12rpx 0;
  .faceURL {
    margin-right: 24rpx;
    flex-shrink: 0;
  }
  .name {
    flex-shrink: 0;
    font-size: 28rpx;
    font-weight: 500;
    color: #333;
    margin-right: 10rpx;
  }
  .msgContent {
    flex: 1;
    font-size: 28rpx;
    color: #333;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
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
        flex-shrink: 0;
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
    .msg {
      word-break: break-all;
    }
  }
}
</style>