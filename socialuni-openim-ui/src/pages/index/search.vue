<template>
  <view class="search">
    <view class="statusBar"></view>
    <view class="search-input">
      <u-search
        shape="square"
        v-model="searchContent"
        placeholder="搜索"
        actionText="取消"
        :actionStyle="{ color: '#1B72EC' }"
        @change="searchContentChange"
        @custom="back"
      ></u-search>
    </view>
    <view class="search-friend" v-show="friendList.length">
      <view class="text">联系人</view>
      <view
        class="friendListItem"
        v-for="(item, index) in friendList"
        :key="index"
      >
        <HistoryFriendCard :card="item" />
      </view>
    </view>
    <view class="search-message" v-show="historyMessageList.length">
      <view class="text">聊天记录</view>
      <view
        class="historyMessageItem"
        v-for="(item, index) in historyMessageList"
        :key="index"
      >
        <HistoryMessageCard :card="item" />
      </view>
    </view>
  </view>
</template>

<script>
import { mapGetters } from "vuex";
import HistoryMessageCard from "@/components/HistoryMessageCard";
import HistoryFriendCard from "@/components/HistoryFriendCard";
export default {
  components: { HistoryMessageCard, HistoryFriendCard },
  data() {
    return {
      searchContent: "",
      friendList: [],
      messageData: {
        searchResultItems: [],
      },
    };
  },
  methods: {
    searchContentChange() {
      if (this.searchContent) {
        this.searchFriend();
        this.searchMessage();
      } else {
        this.friendList = [];
        this.messageData.searchResultItems = [];
      }
    },
    searchFriend() {
      this.$im.searchFriends(
        this.operationID,
        {
          keywordList: [this.searchContent],
          isSearchUserID: true,
          isSearchNickname: true,
          isSearchRemark: true,
        },
        (res) => {
          if (res.errCode === 0) {
            let list = JSON.parse(res.data);
            this.friendList = list;
            console.log(this.friendList);
          }
        }
      );
    },
    searchMessage() {
      this.$im.searchLocalMessages(
        this.operationID,
        {
          conversationID: "",
          keywordList: [this.searchContent],
          keywordListMatchType: 0,
          senderUserIDList: [],
          messageTypeList: [],
          searchTimePosition: 0,
          searchTimePeriod: 0,
          pageIndex: 0,
          count: 0,
        },
        (res) => {
          if (res.errCode === 0) {
            let data = JSON.parse(res.data);
            this.messageData.searchResultItems = data.searchResultItems || [];
          }
        }
      );
    },
    back() {
      uni.navigateBack();
    },
    editorReady(className, item) {
      uni
        .createSelectorQuery()
        .select("." + className)
        .context((res) => {
          const editorContext = res.context;
          editorContext.clear();
          editorContext.setContents({ html: item.messageItem.content });
        })
        .exec();
    },
  },
  onLoad() {},
  computed: {
    ...mapGetters(["operationID", "userID"]),
    historyMessageList() {
      const list = [];
      this.messageData.searchResultItems.map((item) => {
        item.messageList.map((i) => {
          list.push({ ...item, messageItem: i });
        });
      });
      return list;
    },
  },
};
</script>

<style lang="scss" scoped>
.search {
  min-height: 100vh;
  background: #f8f8f8;
  &-input {
    background-color: #fff;
    padding: 44rpx 44rpx 24rpx;
  }
  &-friend {
    padding: 0 44rpx 28rpx;
    background-color: #fff;
    .text {
      font-size: 24rpx;
      color: #adadad;
      margin-bottom: 12rpx;
    }
  }
  &-message {
    margin-top: 24rpx;
    background-color: #fff;
    padding: 16rpx 44rpx 32rpx;
    .text {
      font-size: 24rpx;
      color: #adadad;
      margin-bottom: 12rpx;
    }
  }
}
</style>