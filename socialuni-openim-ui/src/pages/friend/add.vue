<template>
  <view class="add">
    <view class="search">
      <u-search
        shape="square"
        placeholder="通过手机号/ID号搜索添加"
        disabled
        :showAction="false"
        @click="routerGo('./search')"
      />
    </view>
    <view class="content">
      <view class="item" @click="routerGo('./qrcode')">
        <image class="image" src="@/static/images/friend/qrcode.png"></image>
        <view class="center">
          <view class="title">我的二维码</view>
          <view class="des">邀请对方扫码，添加好友</view>
        </view>
        <u-icon class="icon" size="22" name="arrow-right" color="#999"></u-icon>
      </view>
      <view class="item" @click="scanCode">
        <image class="image" src="@/static/images/friend/scan.png"></image>
        <view class="center">
          <view class="title">扫一扫</view>
          <view class="des">扫描二维码名片</view>
        </view>
        <u-icon class="icon" size="22" name="arrow-right" color="#999"></u-icon>
      </view>
    </view>
  </view>
</template>

<script>
import { scan } from "@/utils/scan";
export default {
  data() {
    return { searchContent: "" };
  },
  methods: {
    routerGo(url) {
      uni.navigateTo({
        url,
      });
    },
    scanCode() {
      scan("1",this.$im);
    },
  },
};
</script>

<style lang="scss" scoped>
.add {
  color: #333333;
  .search {
    padding: 24rpx 44rpx;
  }
  .content {
    border-top: 24rpx solid #f8f8f8;
    .item {
      display: flex;
      align-items: center;
      padding: 24rpx 48rpx;
      .image {
        width: 52rpx;
        height: 52rpx;
        margin-right: 42rpx;
      }
      .center {
        flex: 1;
        .title {
          font-size: 36rpx;
          font-weight: 500;
          margin-bottom: 8rpx;
        }
        .des {
          font-size: 24rpx;
          color: #999999;
        }
      }
      .icon {
      }
    }
  }
}
</style>