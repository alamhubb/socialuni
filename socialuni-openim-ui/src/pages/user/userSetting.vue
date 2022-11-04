<template>
  <view class="info">
    <view class="info-item">
      <view class="left">勿扰模式</view>
      <view class="right">
        <u-switch v-model="soundStatus" />
      </view>
    </view>
    <view class="info-item" @click="routerGo('./addMethod')">
      <view class="left">添加我的方式</view>
      <view class="right">
        <u-icon class="icon" name="arrow-right" size="18" color="#999" />
      </view>
    </view>
    <view class="info-item" @click="routerGo('./blackList')">
      <view class="left">通讯录黑名单</view>
      <view class="right">
        <u-icon class="icon" name="arrow-right" size="18" color="#999" />
      </view>
    </view>
    <view class="info-item" @click="languageData.actionShow = true">
      <view class="left">中英文切换</view>
      <view class="right">
        <text class="text">中文</text>
        <u-icon class="icon" name="arrow-right" size="18" color="#999" />
      </view>
    </view>
    <u-action-sheet
      :actions="languageData.actionList"
      :show="languageData.actionShow"
      round="16"
      cancelText="取消"
      @select="changeLanguage"
      @close="languageData.actionShow = false"
    />
  </view>
</template>

<script>
export default {
  data() {
    return {
      soundStatus: false,
      languageData: {
        actionShow: false,
        actionList: [
          {
            name: "中文",
            color: "#000",
            fontSize: "16",
            value: "zh",
          },
          {
            name: "English",
            color: "#000",
            fontSize: "16",
            value: "en",
          },
        ],
      },
    };
  },
  methods: {
    changeLanguage(item) {
      console.log(item);
      this.languageData.actionShow = false;
    },
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