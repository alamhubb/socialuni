<template>
  <view class="friendNotice">
    <view class="search">
      <u-search
        v-model="searchContent"
        shape="square"
        placeholder="通过手机号/ID号搜索添加"
        :showAction="false"
      />
    </view>
    <u-tabs
      class="tabs"
      :list="tab.list"
      :current="tab.current"
      @change="tabChange"
      :scrollable="false"
    />
    <view
      class="friendNotice-content friendNotice-new"
      v-show="tab.current === 0"
    >
      <view class="title">新的好友请求</view>
      <ContactUserCard
        class="ContactUserCard"
        v-for="item in friendNoticeList2"
        :key="item.fromUserID"
        :card="item"
        :type="0"
        @refresh="init"
      >
      </ContactUserCard>
      <view class="more" v-show="showFriendNoticeMore">
        <view class="btn" @click="toApplicationList('0')"
          >查看全部好友请求</view
        >
      </view>
    </view>
    <view
      class="friendNotice-content friendNotice-my"
      v-show="tab.current === 1"
    >
      <view class="title">我的请求</view>
      <ContactUserCard
        class="ContactUserCard"
        v-for="item in selfFriendNoticeList2"
        :key="item.toUserID"
        :card="item"
        :type="1"
        @refresh="init"
      >
      </ContactUserCard>
      <view class="more" v-show="showSelfFriendNoticeMore">
        <view class="btn" @click="toApplicationList('1')">查看全部请求</view>
      </view>
    </view>
  </view>
</template>

<script>
import ContactUserCard from "@/components/ContactUserCard";
import { mapGetters } from "vuex";
export default {
  components: { ContactUserCard },
  data() {
    return {
      searchContent: "",
      tab: { current: 0, list: [{ name: "好友请求" }, { name: "我的请求" }] },
      maxLength: { friendNotice: 4, selfFriendNotice: 4 },
    };
  },
  methods: {
    init() {
      //#ifdef APP-PLUS
      this.$store.dispatch("contacts/get_friendNoticeList", this.$im);
      this.$store.dispatch("contacts/get_selfFriendNoticeList", this.$im);
      //#endif
    },
    tabChange({ index }) {
      this.tab.current = index;
    },
    toApplicationList(type) {
      uni.navigateTo({
        url: "@/applicationList?type=" + type,
      });
    },
  },
  onShow() {
    this.init();
  },
  computed: {
    ...mapGetters([
      "operationID",
      "friendNoticeList",
      "selfFriendNoticeList",
      "userID",
    ]),
    friendNoticeList2() {
      let list = this.friendNoticeList;
      if (this.searchContent) {
        list = list.filter((i) => {
          return (
            i.fromUserID.includes(this.searchContent) ||
            i.fromNickname.includes(this.searchContent)
          );
        });
      }
      return list.slice(0, this.maxLength.friendNotice);
    },
    showFriendNoticeMore() {
      return this.friendNoticeList.length > this.maxLength.friendNotice
        ? true
        : false;
    },
    selfFriendNoticeList2() {
      let list = this.selfFriendNoticeList;
      if (this.searchContent) {
        list = list.filter((i) => {
          return (
            i.toUserID.includes(this.searchContent) ||
            i.toNickname.includes(this.searchContent)
          );
        });
      }
      return list.slice(0, this.maxLength.friendNotice);
    },
    showSelfFriendNoticeMore() {
      return this.selfFriendNoticeList.length > this.maxLength.friendNotice
        ? true
        : false;
    },
  },
};
</script>

<style lang="scss" scoped>
$pagePadding: 44rpx;
.friendNotice {
  background-color: #f8f8f8;
  min-height: 100vh;
  .search,
  .tabs,
  .friendNotice-content {
    background-color: #fff;
  }
  .search {
    padding: 24rpx $pagePadding 0;
  }
  &-content {
    .title {
      padding: 16rpx $pagePadding;
      font-size: 24rpx;
      color: #999;
      background-color: #f8f8f8;
    }
    ::v-deep .ContactUserCard {
      &:last-of-type {
        .right {
          border-bottom: none;
        }
      }
    }
    .more {
      background-color: #f8f8f8;
      padding-top: 24rpx;
      .btn {
        text-align: center;
        height: 86rpx;
        line-height: 86rpx;
        font-size: 28rpx;
        color: #1d6bed;
        background-color: #fff;
      }
    }
  }
  &-new {
  }
  &-my {
  }
}
</style>
