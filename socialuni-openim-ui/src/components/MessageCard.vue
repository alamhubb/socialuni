<!--
<template>
  <view
    :class="['MessageCard', card.isPinned ? 'MessageCard-isPinned' : '']"
    @click="toConversation"
  >
    <Avatar
      v-if="card.conversationType === 1"
      class="faceURL"
      :src="card.faceURL"
      :name="card.showName"
    />
    <u-image
      v-else-if="card.conversationType === 2 && card.faceURL"
      class="faceURL"
      :src="card.faceURL"
      width="48px"
      height="48px"
      radius="6"
    />
    <u-image
      v-else
      class="faceURL"
      src="@/static/images/message/group-icon.png"
      width="48px"
      height="48px"
      radius="6"
    />
    <view class="content">
      <view class="header">
        <text class="name">{{ card.showName }}</text>
        <text class="date">{{ formatTime(card.latestMsgSendTime) }}</text>
      </view>
      <view class="msgContent">
        <MessageItem
          class="MessageItem"
          :list="messageList"
          :hasDraftText="hasDraftText"
          fontSize="26rpx"
          color="#666"
        />
        &lt;!&ndash; <view class="msg">{{ msg }}</view> &ndash;&gt;
        <text v-show="showTotal && unreadCount" class="unReadCount">
          {{ unreadCount }}
        </text>
        <image
          v-show="!showTotal"
          class="recvMsgOpt"
          src="@/static/images/message/recvMsgOpt.png"
        />
      </view>
    </view>
  </view>
</template>

<script>
import { parseTime } from "@/utils/index";
import {
  formatMessageCardMessage,
  getMessageCardContentType,
  getEmojiContent,
} from "@/utils/formatMessage";
import Avatar from "@/components/Avatar.vue";
import MessageItem from "@/components/MessageItem.vue";
export default {
  components: { Avatar, MessageItem },
  props: {
    card: {
      type: Object,
      default: () => {},
    },
  },
  data() {
    return {
      showAction: true,
    };
  },
  methods: {
    getImageUrl() {
      const { conversationType, faceURL } = this.card;
      if (conversationType === 2) {
        return "@/static/images/message/group-icon.png";
      }
      return faceURL;
    },
    toConversation() {
      // console.log(this.card);
      const sourceID =
        this.card.conversationType === 1 ? this.card.userID : this.card.groupID;
      // const conversationType = this.card.conversationType === 1 ? 1 : 2;
      const conversationType = this.card.conversationType;
      uni.navigateTo({
        url: `/pages/conversation/index?sessionType=${conversationType}&sourceID=${sourceID}`,
      });
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
  },
  computed: {
    msg() {
      return formatMessageCardMessage(this.card);
    },
    hasDraftText() {
      return this.draftText ? true : false;
    },
    draftText() {
      return this.card.draftText || "";
    },
    messageList() {
      const content = this.draftText || this.msg || "";
      const item = {
        type: "text",
        content,
        startIndex: 0,
        endIndex: content.length - 1,
      };
      let list = getEmojiContent([item]);
      return list;
    },
    contentType() {
      return getMessageCardContentType(this.card);
    },
    unreadCount() {
      const count = Number(this.card.unreadCount) || 0;
      return count > 99 ? "99" : count;
    },
    showTotal() {
      return this.card && this.card.recvMsgOpt !== 0 ? false : true;
    },
  },
};
</script>

<style lang="scss" scoped>
$pdLeft: 44rpx;
.MessageCard {
  padding: 18rpx $pdLeft;
  display: flex;
  flex-wrap: nowrap;
  border-bottom: 2rpx solid #fff;
  &-isPinned {
    background-color: #f7f7f7;
  }
  .faceURL {
    margin-right: 24rpx;
  }
  .content {
    flex: 1;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    overflow: hidden;
    .header {
      display: flex;
      align-items: center;
      justify-content: space-between;
      .name {
        font-size: 32rpx;
        font-weight: 500;
        color: #333333;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
      }
      .date {
        font-size: 24rpx;
        color: #999999;
      }
    }
    .msgContent {
      display: flex;
      flex-wrap: nowrap;
      align-items: center;
      .msg {
        font-size: 26rpx;
        color: #666666;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        flex: 1;
      }
      .MessageItem {
        height: 36rpx;
        line-height: 36rpx;
        flex: 1;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
      }
      .unReadCount {
        flex-shrink: 0;
        border-radius: 50%;
        background-color: #f44038;
        width: 40rpx;
        height: 40rpx;
        line-height: 40rpx;
        text-align: center;
        font-size: 24rpx;
        color: #ffffff;
        margin-left: 8rpx;
      }
      .recvMsgOpt {
        width: 40rpx;
        height: 40rpx;
      }
    }
  }
}
</style>-->
