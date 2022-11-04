<template>
  <view class="register commonPage">
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
    <view class="title" v-show="isNormalPage">新用户注册</view>
    <view class="title" v-show="isForgetPage">忘记密码</view>
    <u-form
      class="commonPage-form"
      labelPosition="top"
      :model="userInfo"
      :rules="rules"
      :labelStyle="{
        fontWeight: 400,
        marginTop: '20rpx',
        marginBottom: '10rpx',
      }"
      ref="loginForm"
    >
      <u-form-item prop="phoneNumber" borderBottom>
        <u-input
          v-model="userInfo.phoneNumber"
          border="none"
          fontSize="36rpx"
          placeholder="请输入手机号码"
          clearable
        >
          <view slot="right" class="phoneNumber-code" @click="showPicker">
            <text class="code">+{{ userInfo.areaCode }}</text>
            <u-icon class="icon" name="arrow-down"></u-icon>
          </view>
        </u-input>
      </u-form-item>
    </u-form>
    <view class="login-btn">
      <u-button
        v-show="isNormalPage"
        type="primary"
        @click="sendCode"
        :disabled="!checked[0] || !userInfo.phoneNumber || register.isPending"
      >
        {{ register.tips }}
      </u-button>
      <u-button
        :disabled="!userInfo.phoneNumber || register.isPending"
        v-show="isForgetPage"
        type="primary"
        @click="sendCode"
      >
        {{ register.tips }}
      </u-button>
    </view>
    <view class="agreement" v-show="isNormalPage">
      <u-checkbox-group v-model="checked">
        <u-checkbox
          iconSize="12"
          labelSize="12"
          shape="circle"
          label="我已阅读并同意："
          :name="true"
        ></u-checkbox>
      </u-checkbox-group>
      <text class="detail">《服务协议》，《隐私权政策》</text>
    </view>
    <u-code
      ref="registerCode"
      :seconds="60"
      :startText="codeData.startText"
      changeText="X秒后重发验证码"
      @end="timeEnd"
      @change="codeChange"
    />
    <AreaPicker ref="AreaPicker" @chooseArea="chooseArea" />
  </view>
</template>

<script>
import { mapGetters } from "vuex";
import AreaPicker from "@/components/AreaPicker";
import { app_sendCode } from "@/service/api/user";
export default {
  components: { AreaPicker },
  data() {
    return {
      userInfo: {
        phoneNumber: "",
        email: "",
        password: "",
        areaCode: "86",
      },
      checked: [false],
      rules: {
        phoneNumber: [
          {
            type: "string",
            required: true,
            message: "请输入手机号码",
            trigger: ["blur", "change"],
          },
        ],
      },
      pageStatus: "normal",
      tips1: "",
      register: {
        tips: "",
        isPending: false,
      },
    };
  },
  onLoad(param) {
    this.pageStatus = param.pageStatus || "normal";
  },
  methods: {
    sendCode() {
      if (this.$refs.registerCode.canGetCode) {
        this.toSmsCode();
      }
    },
    timeEnd() {
      this.register.isPending = false;
    },
    codeChange(text) {
      this.register.tips = text;
    },
    toSmsCode() {
      this.$refs.loginForm.validate().then((valid) => {
        if (valid) {
          const param = {
            phoneNumber: this.userInfo.phoneNumber,
            areaCode: this.userInfo.areaCode,
            usedFor: 1,
            operationID: this.operationID,
          };
          app_sendCode(param).then((res) => {
            if (res.errCode === 0) {
              this.routerGo();
            } else {
              this.$toast(res.errMsg);
            }
          });
        }
      });
    },
    back() {
      uni.navigateBack();
    },
    showPicker() {
      this.$refs.AreaPicker.init();
    },
    chooseArea(areaCode) {
      this.userInfo.areaCode = areaCode;
    },
    routerGo() {
      this.$refs.registerCode.start();
      this.register.isPending = true;
      this.$store.commit("user/set_loginUserInfo", {
        ...this.userInfo,
        sendCodeTime: new Date().getTime(),
      });
      uni.navigateTo({
        url: "@/smsCode?pageStatus=" + this.pageStatus,
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
    codeData() {
      if (this.isNormalPage) {
        return { startText: "立即注册" };
      }
      return { startText: "发送验证码" };
    },
  },
};
</script>
<style lang="scss" scoped>
@import "@/style/common.scss";
.register {
}
</style>
