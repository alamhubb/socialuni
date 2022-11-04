<template>
  <view class="add">
    <view class="add-title">添加好友</view>
    <view class="content">
      <view class="item" @click="routerGo('/pages/friend/search')">
        <image class="image" src="@/static/images/contact/add/friend.png" />
        <view class="right">
          <view class="center">
            <view class="title">搜索好友</view>
            <view class="des">通过手机号/ID号/搜索添加</view>
          </view>
          <u-icon class="icon" size="22" name="arrow-right" color="#999" />
        </view>
      </view>
      <view class="item" @click="routerGo('/pages/group/search')">
        <image class="image" src="@/static/images/contact/add/group.png" />
        <view class="right">
          <view class="center">
            <view class="title">搜索群聊</view>
            <view class="des">通过群ID号搜索</view>
          </view>
          <u-icon class="icon" size="22" name="arrow-right" color="#999" />
        </view>
      </view>
      <view class="item" @click="scanCode">
        <image class="image" src="@/static/images/friend/scan.png" />

        <view class="right">
          <view class="center">
            <view class="title">扫一扫</view>
            <view class="des">扫描二维码名片</view>
          </view>
          <u-icon class="icon" size="22" name="arrow-right" color="#999" />
        </view>
      </view>
    </view>
  </view>
</template>

<script>
import { scan } from "@/utils/scan";
export default {
  data() {
    return {};
  },
  methods: {
    routerGo(url) {
      uni.navigateTo({
        url,
      });
    },
    scanCode() {
      scan("0",this.$im);
    },
  },
};
</script>

<style lang="scss" scoped>
.add {
  color: #333333;
  &-title {
    padding: 16rpx 44rpx;
    font-size: 24rpx;
    color: #999;
    background-color: #f8f8f8;
  }
  .content {
    .item {
      display: flex;
      align-items: center;
      padding: 0 48rpx;
      .image {
        width: 52rpx;
        height: 52rpx;
        margin-right: 42rpx;
      }
      .right {
        padding: 24rpx 0;
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: space-between;
        border-bottom: 2rpx solid #f1f1f1;
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
}
</style>