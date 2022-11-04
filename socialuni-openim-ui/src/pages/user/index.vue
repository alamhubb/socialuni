<template>
  <view class="user">
    <view class="header">
      <image class="bg" src="@/static/images/user/bg.png" />
      <u--image
        class="faceURL"
        :src="userInfo.faceURL"
        width="73px"
        height="73px"
        radius="6"
      >
        <view slot="error" class="faceURL-error">
          {{ shortName }}
        </view>
      </u--image>
      <text class="nickname">{{ userInfo.nickname }}</text>
      <view class="userID" @click="routerGo('/pages/friend/qrcode')">
        <text class="id">ID：{{ userInfo.userID }}</text>
        <image class="qrcode" src="@/static/images/user/qrcode.png" />
        <u-icon name="arrow-right" size="12" color="#fff" />
      </view>
    </view>
    <view class="container">
      <view class="container-item" @click="routerGo('./info')">
        <image class="image" src="@/static/images/user/info.png" />
        <view class="right">
          <text class="text">我的信息</text>
          <u-icon name="arrow-right" size="16" color="#666" />
        </view>
      </view>
      <view class="container-item" @click="routerGo('./notice')">
        <image class="image" src="@/static/images/user/notice.png" />
        <view class="right">
          <text class="text">新消息通知</text>
          <u-icon name="arrow-right" size="16" color="#666" />
        </view>
      </view>
      <view class="container-item" @click="routerGo('./userSetting')">
        <image class="image" src="@/static/images/user/lock.png" />
        <view class="right">
          <text class="text">账号设置</text>
          <u-icon name="arrow-right" size="16" color="#666" />
        </view>
      </view>
      <view class="container-item">
        <image class="image" src="@/static/images/user/about.png" />
        <view class="right">
          <text class="text">关于我们</text>
          <u-icon name="arrow-right" size="16" color="#666" />
        </view>
      </view>
      <view class="container-item" @click="showLogout = true">
        <image class="image" src="@/static/images/user/logout.png" />
        <view class="right">
          <text class="text">退出登录</text>
          <u-icon name="arrow-right" size="16" color="#666" />
        </view>
      </view>
    </view>
    <u-modal
      :show="showLogout"
      showCancelButton
      @confirm="logOut"
      @cancel="showLogout = false"
    >
      <view class="modal">
        <view class="modal-title2">您确定要退出登录吗?</view>
      </view>
    </u-modal>
  </view>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  components: {},
  data() {
    return {
      showLogout: false,
    };
  },
  methods: {
    routerGo(url) {
      uni.navigateTo({
        url,
      });
    },
    logOut() {
      this.$store.dispatch("user/logOut", this.$im);
    },
  },
  computed: {
    ...mapGetters(["userInfo", "operationID"]),
    shortName() {
      const name = this.userInfo.nickname.substr(0, 2);
      const enNameReg = /[A-Za-z0-9]$/g; //是否英文名
      if (this.isGroup) {
        return this.name;
      } else if (enNameReg.test(name)) {
        return name;
      } else {
        return name.substr(0, 1);
      }
    },
  },
  onLoad() {},
};
</script>
<style lang="scss" scoped>
.user {
  min-height: 100vh;
  .header {
    position: relative;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    .bg {
      position: absolute;
      left: 0;
      top: 0;
      width: 100%;
      height: 444rpx;
      z-index: -1;
    }
    .faceURL {
      margin-top: 120rpx;
      ::v-deep .u-image__error {
        background: transparent;
        display: flex;
        align-items: center;
        justify-content: center;
        background-color: #0773ff;
      }
      &-error {
        width: 100%;
        overflow: hidden;
        text-align: center;
        font-size: 32rpx;
        font-weight: 600;
        color: #ffffff;
        margin: 0 16rpx;
        white-space: nowrap;
      }
    }
    .nickname {
      margin-top: 30rpx;
      font-size: 40rpx;
      font-weight: 500;
      color: #fff;
    }
    .userID {
      margin-top: 12rpx;
      font-size: 28rpx;
      color: #fff;
      display: flex;
      flex-wrap: nowrap;
      align-items: center;
      .id {
        margin: 20rpx;
      }
      .qrcode {
        width: 36rpx;
        height: 36rpx;
        margin-right: 16rpx;
      }
    }
  }
  .container {
    padding: 30rpx 44rpx;
    &-item {
      padding: 30rpx 0;
      display: flex;
      align-items: center;
      .image {
        width: 44rpx;
        height: 44rpx;
        margin-right: 24rpx;
      }
      .right {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: space-between;
        .text {
          font-size: 32rpx;
          color: #333333;
        }
      }
    }
  }
}
</style>
