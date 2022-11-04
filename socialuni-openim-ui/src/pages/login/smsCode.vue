<template>
  <view class="smsCode commonPage">
    <view class="statusBar"></view>
    <view class="back">
      <u-icon
        name="arrow-leftward"
        bold
        size="36"
        color="#1D6BED"
        @click="back"
      />
    </view>
    <view class="title">验证码已发送至手机</view>
    <view class="phone">
      +{{ loginUserInfo.areaCode }}
      {{ loginUserInfo.phoneNumber }}
    </view>
    <view class="code">
      <view class="code-title">请输入验证码(测试验证码:666666)</view>
      <u-code-input
        v-model="codeValue"
        mode="line"
        hairline
        space="20"
        @finish="checkCode"
      />
      <view class="code-des">
        <text class="color-blue">
          {{ register.tips }}
        </text>
        {{ register.isPending ? "后重发验证码" : "" }}
      </view>
    </view>
    <u-code
      ref="registerCode"
      :seconds="60"
      startText="重新发送"
      changeText="XS"
      @end="timeEnd"
      @change="codeChange"
    />
  </view>
</template>
<script>
import { app_sendCode, app_verifyCode } from "@/service/api/user";
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      pageStatus: "",
      codeValue: "",
      register: {
        tips: "",
        isPending: false,
        seconds: 60,
      },
    };
  },
  onLoad(param) {
    this.pageStatus = param.pageStatus || "normal";
  },
  onReady() {
    this.initCodeTime();
    this.showDevNotice();
  },
  methods: {
    showDevNotice() {
      this.$toast("测试验证码:666666");
    },
    initCodeTime() {
      const now = new Date().getTime();
      const sendCodeTime = this.loginUserInfo.sendCodeTime;
      if (sendCodeTime) {
        const d = 60 - parseInt((now - sendCodeTime) / 1000);
        if (d > 0) {
          this.register.seconds = d;
          this.$refs.registerCode.start();
          this.register.isPending = true;
        }
      }
    },
    timeEnd() {
      this.register.isPending = false;
      this.register.seconds = 60;
    },
    codeChange(text) {
      this.register.tips = text;
    },
    sendCode() {
      if (this.$refs.registerCode.canGetCode) {
        this.toSmsCode();
      }
    },
    toSmsCode() {
      const param = {
        phoneNumber: this.loginUserInfo.phoneNumber,
        areaCode: this.loginUserInfo.areaCode,
        usedFor: 1,
        operationID: this.operationID,
      };
      app_sendCode(param).then((r) => {
        if (r.errCode === 0) {
          this.$refs.registerCode.start();
          this.register.isPending = true;
        } else {
          this.$toast(r.errMsg);
        }
      });
    },
    back() {
      uni.navigateBack();
    },
    checkCode(verificationCode) {
      const param = {
        phoneNumber: this.loginUserInfo.phoneNumber,
        areaCode: this.loginUserInfo.areaCode,
        verificationCode,
        usedFor: this.loginUserInfo.usedFor,
        operationID: this.operationID,
      };
      app_verifyCode(param).then((res) => {
        if (res.errCode === 0) {
          this.routerGo(param);
        } else {
          this.$toast(res.errMsg);
        }
      });
    },
    routerGo(userInfo) {
      this.$store.commit("user/set_loginUserInfo", userInfo);
      uni.navigateTo({
        url: "@/setPwd?pageStatus=" + this.pageStatus,
      });
    },
  },
  computed: {
    ...mapGetters(["operationID", "loginUserInfo"]),
    isNormalPage() {
      return this.pageStatus === "normal";
    },
    isForgetPage() {
      return this.pageStatus === "forget";
    },
  },
};
</script>
<style lang="scss" scoped>
@import "@/style/common.scss";
.smsCode {
  .phone {
    font-size: 32rpx;
    margin-top: 8rpx;
    color: #1d6bed;
  }
  .code {
    margin-top: 56rpx;
    &-title {
      font-size: 28rpx;
      color: #333;
      margin-bottom: 24rpx;
    }
    &-des {
      font-size: 24rpx;
      color: #333;
      margin-top: 36rpx;
      .color-blue {
        color: #1d6bed;
        margin-right: 4rpx;
      }
    }
  }
}
</style>
