<template>
  <view class="contacts">
    <u-navbar class="navbar" fixed placeholder>
      <view class="navbar-left" slot="left"> 通讯录 </view>
      <view class="navbar-right" slot="right">
        <image
          @click="routerGo('./add')"
          class="image"
          src="@/static/images/contact/search.png"
        />
        <image
          @click="routerGo('./add')"
          class="image"
          src="@/static/images/contact/add.png"
        />
      </view>
    </u-navbar>
    <view class="top">
      <view class="top-item" @click="routerGo('./friendNotice')">
        <image class="image" src="@/static/images/contact/friend-notice.png" />
        <view class="top-item-content">
          <text class="type">新的好友</text>
          <view class="icon">
            <text class="dot" v-show="friendNoticeListNum">
              {{ friendNoticeListNum }}
            </text>
            <u-icon name="arrow-right" size="16" color="#666" />
          </view>
        </view>
      </view>
      <view class="top-item" @click="routerGo('./groupNotice')">
        <image class="image" src="@/static/images/contact/group-notice.png" />
        <view class="top-item-content">
          <text class="type">群通知</text>
          <view class="icon">
            <text class="dot" v-show="groupNoticeListNum">
              {{ groupNoticeListNum }}
            </text>
            <u-icon name="arrow-right" size="16" color="#666" />
          </view>
        </view>
      </view>
      <view class="top-item" @click="routerGo('./friendList')">
        <image class="image" src="@/static/images/contact/friend-self.png" />
        <view class="top-item-content">
          <text class="type">我的好友</text>
          <view class="icon">
            <u-icon name="arrow-right" size="16" color="#666" />
          </view>
        </view>
      </view>
      <view class="top-item" @click="routerGo('./groupList')">
        <image class="image" src="@/static/images/contact/group-self.png" />
        <view class="top-item-content">
          <text class="type">我的群组</text>
          <view class="icon">
            <u-icon name="arrow-right" size="16" color="#666" />
          </view>
        </view>
      </view>
    </view>
    <view class="title">常用联系人</view>
  </view>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  data() {
    return {};
  },
  onLoad() {},
  onShow() {
    this.init();
  },
  methods: {
    init() {
      //#ifdef APP-PLUS
      this.$store.dispatch("contacts/get_friendNoticeList", this.$im);
      this.$store.dispatch("contacts/get_groupNoticeList", this.$im);
      //#endif
    },
    routerGo(url) {
      uni.navigateTo({
        url: url,
      });
    },
  },
  computed: {
    ...mapGetters(["friendNoticeList", "groupNoticeList"]),
    friendNoticeListNum() {
      return (
        this.friendNoticeList.filter((i) => i.handleResult === 0).length || 0
      );
    },
    groupNoticeListNum() {
      return (
        this.groupNoticeList.filter((i) => i.handleResult === 0).length || 0
      );
    },
  },
};
</script>
<style lang="scss" scoped>
$pagePadding: 44rpx;
.contacts {
  .navbar {
    ::v-deep .u-navbar__content__left {
      padding: 0 0 0 $pagePadding;
    }
    ::v-deep .u-navbar__content__right {
      padding: 0 $pagePadding 0 0;
    }
    &-left {
      font-size: $pagePadding;
      font-weight: 500;
      color: #1b72ec;
    }
    &-right {
      display: flex;
      align-items: center;
      .image {
        width: 48rpx;
        height: 48rpx;
        &:nth-of-type(1) {
          margin-right: 24rpx;
        }
      }
    }
  }
  .top {
    margin-top: 12rpx;
    padding: 0 $pagePadding;
    &-item {
      display: flex;
      align-items: center;
      .image {
        width: 84rpx;
        height: 84rpx;
        margin-right: 36rpx;
      }
      &-content {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 36rpx 0;
        border-bottom: 2rpx solid #f1f1f1;
        .type {
          font-size: 36rpx;
          color: #333;
        }
        .icon {
          display: flex;
          align-items: center;
          .dot {
            margin-right: 20rpx;
            font-size: 24rpx;
            font-weight: 600;
            color: #ffffff;
            width: 40rpx;
            height: 40rpx;
            line-height: 40rpx;
            text-align: center;
            background-color: #f44038;
            border-radius: 50%;
          }
        }
      }
    }
  }
  .title {
    padding: 16rpx $pagePadding;
    font-size: 24rpx;
    color: #999;
    background-color: #f8f8f8;
  }
}
</style>
