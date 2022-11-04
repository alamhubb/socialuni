<template>
  <view class="info">
    <view class="info-title">可以通过以下方式添加我</view>
    <view class="info-item">
      <view class="left">手机号</view>
      <view class="right">
        <u-switch v-model="enablePhone" />
      </view>
    </view>
    <view class="info-item">
      <view class="left">ID号</view>
      <view class="right">
        <u-switch v-model="enableUserID" />
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      enablePhone: false,
      enableUserID: false,
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
  &-title {
    padding: 0 44rpx 24rpx;
    font-size: 24rpx;
    color: #999;
  }
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