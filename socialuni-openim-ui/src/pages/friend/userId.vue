<template>
  <view class="send">
    <view class="content">
      <view class="label">ID号</view>
      <view class="input">
        <u-input
          placeholder=" "
          border="none"
          v-model="userID"
          :customStyle="{ padding: '18rpx' }"
          readonly
        >
          <u-button slot="suffix" @click="copy" type="primary" size="mini">
            复制
          </u-button>
        </u-input>
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      userID: "",
    };
  },
  methods: {
    copy() {
      uni.setClipboardData({
        data: this.userID,
        success: () => {
          this.$toast("复制成功");
        },
      });
    },
  },
  onLoad(param) {
    console.log(param);
    this.userID = param.userID;
  },
};
</script>

<style lang="scss" scoped>
.send {
  min-height: 100vh;
  background-color: #f6f6f6;
  .label {
    margin: 0 44rpx;
    padding: 20rpx 0;
    font-size: 28rpx;
    color: #666666;
  }
  .input {
    background-color: #fff;
  }
}
</style>