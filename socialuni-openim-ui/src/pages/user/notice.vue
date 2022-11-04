<template>
  <view class="info">
    <view class="info-item">
      <view class="left">新消息通知</view>
      <view class="right">
        <u-switch v-model="noticeStatus" />
      </view>
    </view>
    <view class="info-item">
      <view class="left">语音和视频通话提醒</view>
      <view class="right">
        <u-switch v-model="phoneNoticeStatus" />
      </view>
    </view>
    <view class="info-item" @click="routerGo('./noticeSetting')">
      <view class="left">消息通知设置</view>
      <view class="right">
        <u-icon class="icon" name="arrow-right" size="18" color="#999" />
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      noticeStatus: false,
      phoneNoticeStatus: false,
    };
  },
  methods: {
    routerGo(url) {
      uni.navigateTo({
        url,
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.info {
  min-height: 100vh;
  background-color: #f8f8f8;
  padding-top: 24rpx;
  &-item {
    background-color: #fff;
    display: flex;
    align-items: center;
    justify-content: space-between;
    flex-direction: row;
    height: 116rpx;
    padding: 0 44rpx;
    border-bottom: 2rpx solid rgba(153, 153, 153, 0.2);
    .left {
      font-size: 36rpx;
      color: #333;
    }
    .right {
      display: flex;
      align-items: center;
      flex-direction: row;
      .icon {
        margin-left: 24rpx;
      }
      .text {
        color: #999;
        font-size: 32rpx;
      }
      .qrcode {
        width: 44rpx;
        height: 44rpx;
      }
    }
  }
}
</style>