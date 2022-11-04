<template>
  <view class="index">
    <view class="statusBar"></view>
    <u-sticky customNavHeight="0" bgColor="#fff">
      <view class="userInfo">
        <view class="userInfo-left">
          <Avatar :src="userInfo.faceURL" :name="userInfo.nickname"/>
        </view>
        <view class="userInfo-center">
          <view class="company" v-show="userInfo.attachedInfo">
            {{ userInfo.attachedInfo }}
          </view>
          <view class="name">
            <view class="nickname">{{ userInfo.nickname }}</view>
            <view class="loginStatus">手机在线</view>
          </view>
        </view>
        <view class="userInfo-right">
          <image class="image" src="@/static/images/index/tel.png"/>
          <image
              class="image"
              id="addPic"
              src="@/static/images/index/add.png"
              @click="showAdd"
          />
        </view>
      </view>
      <view class="search">
        <u-search
            shape="square"
            v-model="searchContent"
            :showAction="false"
            placeholder="搜索"
            disabled
            @click="routerGo('./search')"
        />
      </view>
    </u-sticky>
    <view class="container">
      <uni-list :border="false" class="list">
        <uni-swipe-action>
          <uni-swipe-action-item
              v-for="item in messageList"
              :key="item.conversationID"
              :right-options="filterActionOptions(item)"
              @click="(v) => swipeActionClick(v, item)"
          >
            <uni-list-item class="list-item">
              <view slot="header"></view>
              <MessageCard slot="body" class="MessageCard" :card="item"/>
              <view slot="footer"></view>
            </uni-list-item>
          </uni-swipe-action-item>
        </uni-swipe-action>
      </uni-list>
    </view>
    <view v-show="addPop.show" class="addContent" @click="addPop.show = false">
      <view class="addContent-pop" :style="addPop.style">
        <view class="addContent-pop-item" @click="scanCode">
          <image class="image" src="@/static/images/message/scan.png"></image>
          <text class="text">扫一扫</text>
        </view>
        <view
            class="addContent-pop-item"
            @click="routerGo('/pages/friend/add')"
        >
          <image
              class="image"
              src="@/static/images/message/addFriend.png"
          ></image>
          <text class="text">添加好友</text>
        </view>
        <view class="addContent-pop-item" @click="routerGo('/pages/group/add')">
          <image
              class="image"
              src="@/static/images/message/addGroup.png"
          ></image>
          <text class="text">添加群聊</text>
        </view>
        <view
            class="addContent-pop-item"
            @click="routerGo('/pages/group/create')"
        >
          <image
              class="image"
              src="@/static/images/message/createGroup.png"
          ></image>
          <text class="text">发起群聊</text>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
import MessageCard from "@/components/MessageCard.vue";
import Avatar from "@/components/Avatar.vue";
export default {
  components: { MessageCard, Avatar },
  data() {
    return {
      searchContent: "",
      actionOptions: [
        {
          text: "置顶",
          style: {
            backgroundColor: "#1B72EC",
          },
        },
        {
          text: "取消置顶",
          style: {
            backgroundColor: "#ccc",
          },
        },
        {
          text: "移除",
          style: {
            backgroundColor: "#FFAB41",
          },
        },
      ],
      addPop: { show: false, style: {} },
      isPending: false,
      isRefresh: false,
      initTimer: null,
      firstInitData: { isFirstInit: true, list: [], pageNo: 1, pageSize: 10 },
      total: 0,
    };
  },
  onLoad() {
    // #ifdef APP-PLUS
    this.$store.commit("message/set_messageList", []);
    if (this.loginStatus) {
      this.init2();
    }
    // #endif
  },
  methods: {
    init2() {
      this.isRefresh = true;
      this.init();
    },
    init() {
      if (this.isPending) {
        this.isRefresh = true;
      } else if (this.isRefresh) {
        this.isRefresh = false;
        this.getAllConversationList();
        this.getTotalUnreadMsgCount();
      }
      // if (this.initTimer) {
      //   clearTimeout(this.initTimer);
      // }
      // this.initTimer = setTimeout(() => {
      //   if (this.isPending) {
      //     this.isRefresh = true;
      //   } else if (this.isRefresh) {
      //     this.isRefresh = false;
      //     this.getAllConversationList();
      //     this.getTotalUnreadMsgCount();
      //   }
      // }, 1000);
    },
    getAllConversationList() {
      this.isPending = true;
      this.$im.getAllConversationList(this.operationID, (res) => {
        if (res.errCode !== 0) {
          this.$toast(res.errMsg);
          this.isPending = false;
          this.init();
        } else {
          const data = JSON.parse(res.data);
          // console.log(JSON.parse(res.data));
          this.checkFirstInit(data);
          // if (this.isIos) {
          //   this.checkFirstInit2(data);
          // } else {
          //   this.checkFirstInit(data);
          // }
        }
      });
    },
    getTotalUnreadMsgCount() {
      this.$im.getTotalUnreadMsgCount(this.operationID, (res) => {
        if (res.errCode !== 0) {
          this.$toast(res.errMsg);
        } else {
          this.total = res.data;
          this.setTabBarBadge();
        }
      });
    },
    checkFirstInit2(list) {
      this.$store.commit("message/set_messageList", list);
      setTimeout(() => {
        this.isPending = false;
        this.init();
      }, 200);
    },
    checkFirstInit(list) {
      if (this.firstInitData.isFirstInit) {
        const length = list.length;
        const num = this.firstInitData.pageNo * this.firstInitData.pageSize;
        this.$store.commit("message/set_messageList", list.slice(0, num));
        if (num < length) {
          setTimeout(() => {
            this.firstInitData.pageNo++;
            this.checkFirstInit(list);
          }, 200);
        } else {
          this.firstInitData.isFirstInit = false;
          setTimeout(() => {
            this.isPending = false;
            this.init();
          }, 200);
        }
      } else {
        this.$store.commit("message/set_messageList", list);
        setTimeout(() => {
          this.isPending = false;
          this.init();
        }, 200);
      }
    },
    showAdd() {
      const query = uni.createSelectorQuery().in(this);
      query
        .select("#addPic")
        .boundingClientRect((data) => {
          const { height, left, top } = data;
          this.addPop.style = {
            "margin-top": top + height + "px",
            "margin-left": left - 130 + "px",
          };
          this.addPop.show = true;
        })
        .exec();
    },
    scanCode() {
      scan("0", this.$im);
    },
    routerGo(url) {
      if (url) {
        uni.navigateTo({
          url,
        });
      }
    },
    filterActionOptions(item) {
      const { isPinned } = item;
      const list = [...this.actionOptions];
      if (isPinned) {
        list.splice(0, 1);
      } else {
        list.splice(1, 1);
      }
      return list;
    },
    swipeActionClick({ index }, item) {
      const { conversationID, isPinned } = item;
      if (index === 0) {
        this.$im.pinConversation(
          this.operationID,
          conversationID,
          !isPinned,
          (res) => {
            if (res.errCode === 0) {
              this.init2();
            }
          }
        );
      } else if (index === 1) {
        this.$im.deleteConversation(this.operationID, conversationID, (res) => {
          if (res.errCode === 0) {
            this.init2();
          }
        });
      }
    },
    setTabBarBadge() {
      if (this.total > 0) {
        const text = this.total > 99 ? "99" : this.total;
        uni.setTabBarBadge({
          index: 0,
          text,
        });
      } else {
        uni.removeTabBarBadge({ index: 0 });
      }
    },
  },
  computed: {
    ...mapGetters([
      "userInfo",
      "messageList",
      "operationID",
      "newMessageTimes",
      "indexMessageTimes",
      "loginStatus",
      "userID",
      "token",
      "systemInfo",
    ]),
    hasLastLoginData() {
      return this.userID && this.token ? true : false;
    },
    isIos() {
      return this.systemInfo.platform === "ios";
    },
  },
  watch: {
    newMessageTimes() {
      this.init2();
    },
    indexMessageTimes() {
      this.init2();
    },
    loginStatus(v) {
      if (v) {
        this.init2();
      }
    },
  },
};
</script>
<style lang="scss" scoped>
$pdLeft: 44rpx;
.index {
  min-height: 100vh;
  .userInfo {
    padding: $pdLeft;
    display: flex;
    align-items: center;
    justify-content: space-between;
    &-left {
      flex-shrink: 0;
      width: 96rpx;
      height: 96rpx;
    }
    &-center {
      flex: 1;
      padding: 0 22rpx;
      color: #333;
      display: flex;
      flex-direction: column;
      justify-content: center;
      width: 100%;
      overflow: hidden;
      .company {
        font-size: 24rpx;
        margin-bottom: 4rpx;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        display: block;
      }
      .name {
        display: flex;
        align-items: center;
        flex-wrap: nowrap;
        .nickname {
          font-size: 36rpx;
          margin-right: 24rpx;
          overflow: hidden;
          text-overflow: ellipsis;
          white-space: nowrap;
        }
        .loginStatus {
          font-size: 24rpx;
          padding-left: 20rpx;
          position: relative;
          white-space: nowrap;
          margin-top: 8rpx;
          &::before {
            content: "";
            background-color: #10cc64;
            width: 12rpx;
            height: 12rpx;
            border-radius: 50%;
            position: absolute;
            left: 0;
            top: 50%;
            transform: translateY(-50%);
          }
        }
      }
    }
    &-right {
      display: flex;
      align-items: center;
      .image {
        width: 46rpx;
        height: 46rpx;
        &:nth-of-type(1) {
          margin-right: 32rpx;
        }
      }
    }
  }
  .search {
    padding: 16rpx $pdLeft 8rpx;
  }
  .container {
    margin-top: 32rpx;
    .list {
      &-item {
        ::v-deep .uni-list-item__container {
          padding: 0;
        }
        ::v-deep .uni-list--border {
          display: none;
        }
        .MessageCard {
          width: 100%;
        }
      }
    }
  }
  .addContent {
    background-color: rgba(0, 0, 0, 0.5);
    position: fixed;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    z-index: 999;
    &-pop {
      background-color: #fff;
      width: 130px;
      border-radius: 12rpx;
      &-item {
        display: flex;
        align-items: center;
        padding: 18rpx 0;
        border-bottom: 2rpx solid #f0f0f0;
        .image {
          margin-left: 12px;
          margin-right: 16px;
          width: 24px;
          height: 24px;
        }
        .text {
          font-size: 14px;
          color: #333333;
        }
        &:last-of-type {
          border-bottom: none;
        }
      }
    }
  }
}
</style>
