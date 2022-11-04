<template>
  <view class="info">
    <view class="info-item">
      <view class="left">声音</view>
      <view class="right">
        <u-switch v-model="soundStatus" />
      </view>
    </view>
    <view class="info-item">
      <view class="left">群声音</view>
      <view class="right">
        <u-switch v-model="groupSoundStatus" />
      </view>
    </view>
    <view class="info-item">
      <view class="left">振动</view>
      <view class="right">
        <u-switch v-model="vibrateNoticeStatus" />
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      soundStatus: false,
      groupSoundStatus: false,
      vibrateNoticeStatus: false,
    };
  },
  methods: {},
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