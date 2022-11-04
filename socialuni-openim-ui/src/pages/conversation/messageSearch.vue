<template>
  <view class="container">
    <view class="statusBar" />
    <view class="content">
      <view class="search">
        <u-search
          shape="square"
          v-model="searchContent"
          placeholder="搜索"
          actionText="取消"
          :actionStyle="{ color: '#1B72EC' }"
          @change="searchContentChange"
          @search="searchConfirm"
          @custom="back"
        />
        <view
          class="search-tips"
          v-show="
            !searchContent &&
            !isEmpty &&
            !messageListObj.searchResultItems.length
          "
        >
          <view class="search-tips-title">搜索指定内容</view>
          <view class="search-tips-types">
            <text class="text" @click="changeType(102)">图片</text>
            <text class="text" @click="changeType(104)">视频</text>
            <text class="text" @click="changeType(105)">文件</text>
          </view>
        </view>
        <view class="search-empty" v-show="isEmpty">
          没有找到“
          <text v-if="messageType === 102" class="color-blue">图片</text>
          <text v-else-if="messageType === 104" class="color-blue">视频</text>
          <text v-else-if="messageType === 105" class="color-blue">文件</text>
          <text v-else class="color-blue">{{ searchContent }}</text>
          ”相关结果
        </view>
      </view>
      <view
        class="messageList"
        v-for="conversation in messageListObj.searchResultItems"
        :key="conversation.conversationID"
      >
        <!-- <view class="messageList-info">
          <Avatar
            :src="conversation.faceURL"
            :name="conversation.showName"
            :isGroupChat="conversation.conversationType === 2"
            size="42px"
            @click="toInfo"
          />
        </view> -->
        <view
          class="messageList-item"
          v-for="card in filterMessageList(conversation.messageList)"
          :key="card.clientMsgID"
        >
          <Avatar
            :src="card.senderFaceUrl"
            :name="card.senderNickname"
            :isGroupChat="conversation.conversationType === 2"
            size="42px"
            @click="toInfo"
          />
          <HistoryMessageCard2
            class="HistoryMessageCard2"
            :card="card"
            :conversationType="conversation.conversationType"
            :conversationID="getConversationID(conversation, card)"
            @showBigPhoto="showBigPhoto"
            @showVideoPop="showVideoPop"
          />
        </view>
      </view>
    </view>
    <BigPhoto ref="BigPhoto" />
    <VideoPop ref="VideoPop" />
  </view>
</template>

<script>
import { mapGetters } from "vuex";
import Avatar from "@/components/Avatar.vue";
import HistoryMessageCard2 from "@/components/HistoryMessageCard2.vue";
import BigPhoto from "@/components/BigPhoto.vue";
import VideoPop from "@/components/VideoPop.vue";
export default {
  components: { Avatar, HistoryMessageCard2, BigPhoto, VideoPop },
  data() {
    return {
      conversationID: "",
      searchContent: "",
      isEmpty: false,
      messageList: [],
      messageListObj: {
        totalCount: 0,
        searchResultItems: [],
      },
      messageType: 101,
    };
  },
  methods: {
    searchContentChange() {
      this.isEmpty = false;
    },
    searchConfirm() {
      this.messageType = 101;
      this.searchLocalMessages();
    },
    changeType(type) {
      this.messageType = type;
      this.searchLocalMessages();
    },
    searchLocalMessages() {
      this.$im.searchLocalMessages(
        this.operationID,
        {
          conversationID: this.conversationID,
          keywordList: [this.searchContent],
          keywordListMatchType: 0,
          senderUserIDList: [],
          messageTypeList: [this.messageType],
          searchTimePosition: 0,
          searchTimePeriod: 0,
          pageIndex: 1,
          count: 50,
        },
        (res) => {
          if (res.errCode !== 0) {
            this.isEmpty = true;
          } else {
            const data = JSON.parse(res.data);
            console.log(JSON.parse(res.data));
            this.messageListObj.totalCount = data.totalCount;
            this.messageListObj.searchResultItems =
              data.searchResultItems || [];
            if (this.messageListObj.totalCount > 0) {
              this.isEmpty = false;
            } else {
              this.isEmpty = true;
            }
          }
        }
      );
    },
    back() {
      if (
        this.searchContent ||
        this.isEmpty ||
        this.messageListObj.searchResultItems.length
      ) {
        this.isEmpty = false;
        this.searchContent = "";
        this.messageListObj.searchResultItems = [];
      } else {
        uni.navigateBack();
      }
    },
    toInfo() {},
    filterMessageList(list) {
      return list.filter((i) => i.senderNickname || i.senderFaceUrl);
    },
    getConversationID(conversation, card) {
      if (conversation.conversationType === 2) {
        return card.groupID;
      } else if (
        conversation.conversationType === 1 &&
        card.sendID === this.userID
      ) {
        return card.recvID;
      } else {
        return card.sendID;
      }
    },
    showBigPhoto(src, bigPhotoStyle) {
      this.$refs.BigPhoto.init(src, bigPhotoStyle);
    },
    showVideoPop(videoElem) {
      this.$refs.VideoPop.init(videoElem);
    },
  },
  onLoad(param) {
    // #ifdef APP-PLUS
    this.messageListObj.searchResultItems = [];
    // #endif
    this.conversationID = param.conversationID;
    if (param.messageType) {
      this.messageType = Number(param.messageType);
      this.searchLocalMessages();
    }
  },
  computed: {
    ...mapGetters(["operationID", "userID"]),
  },
};
</script>

<style lang="scss" scoped>
.container {
  min-height: 100vh;
  background: #fff;
  .content {
    padding: 24rpx 44rpx;
  }
  .search {
    margin-bottom: 40rpx;
    &-tips {
      padding: 0 60rpx;
      margin-top: 32rpx;
      text-align: center;
      font-size: 28rpx;
      color: #666666;
      &-title {
        margin-top: 114rpx;
        margin-bottom: 48rpx;
        text-align: center;
        font-size: 28rpx;
        color: #666666;
      }
      &-types {
        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: space-between;
        .text {
          font-size: 32rpx;
          color: #1b72ec;
        }
      }
    }
    &-empty {
      margin-top: 196rpx;
      text-align: center;
      font-size: 28rpx;
      color: #666666;
      .color-blue {
        color: #1b72ec;
      }
    }
  }
  .messageList {
    &-item {
      display: flex;
      justify-content: space-between;
      flex-direction: row;
      .HistoryMessageCard2 {
        flex: 1;
      }
    }
  }
}
</style>