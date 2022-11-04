<template>
  <view class="setPwd commonPage">
    <view class="statusBar"></view>
    <view class="title">请设置账号密码</view>
    <view class="secondTitle">登陆密码用于登陆OpenIM账号</view>
    <u-form
      class="loginForm commonPage-form"
      labelPosition="top"
      :model="userInfo"
      :rules="rules"
      :labelStyle="{
        fontWeight: 400,
        marginTop: '20rpx',
        marginBottom: '10rpx',
        width: '200rpx',
      }"
      ref="loginForm"
    >
      <u-form-item label="密码" prop="password" borderBottom>
        <u-input
          v-show="isPassword"
          v-model="userInfo.password"
          border="none"
          fontSize="36rpx"
          placeholder="请输入密码"
          type="password"
        >
          <view class="eye" slot="right">
            <image
              @click="changePasswordType"
              class="image"
              src="@/static/images/login/eye-off.png"
            />
          </view>
        </u-input>
        <u-input
          v-show="!isPassword"
          v-model="userInfo.password"
          border="none"
          fontSize="36rpx"
          placeholder="请输入密码"
          type="text"
        >
          <view class="eye" slot="suffix">
            <image
              @click="changePasswordType"
              class="image"
              src="@/static/images/login/eye-open.png"
            />
          </view>
        </u-input>
      </u-form-item>
      <u-form-item label="确认密码" prop="confirmPassword" borderBottom>
        <u-input
          v-show="isConfirmPassword"
          v-model="userInfo.confirmPassword"
          border="none"
          fontSize="36rpx"
          placeholder="请输入密码"
          type="password"
        >
          <view class="eye" slot="suffix">
            <image
              @click="changeConfirmPasswordType"
              class="image"
              src="@/static/images/login/eye-off.png"
            />
          </view>
        </u-input>
        <u-input
          v-show="!isConfirmPassword"
          v-model="userInfo.confirmPassword"
          border="none"
          fontSize="36rpx"
          placeholder="请输入密码"
          type="text"
        >
          <view class="eye" slot="suffix">
            <image
              @click="changeConfirmPasswordType"
              class="image"
              src="@/static/images/login/eye-open.png"
            />
          </view>
        </u-input>
      </u-form-item>
    </u-form>
    <view class="form-des color-blue">需6～20位字符</view>
    <view class="btn">
      <u-button type="primary" @click="doNext">
        {{ isNormalPage ? "下一步" : "重置密码" }}
      </u-button>
    </view>
  </view>
</template>

<script>
import { mapGetters } from "vuex";
import md5 from "js-md5";
import { app_resetPassword } from "@/service/api/user";
export default {
  data() {
    return {
      pageStatus: "",
      userInfo: {
        password: "",
        confirmPassword: "",
      },
      passwordType: "password",
      confirmPasswordType: "password",
      isPassword: true,
      isConfirmPassword: true,
      rules: {
        password: [
          {
            type: "string",
            required: true,
            message: "请输入密码",
            trigger: ["blur", "change"],
          },
          {
            validator: (rule, value, callback) => {
              return value.length >= 6;
            },
            message: "密码太短",
            trigger: ["change", "blur"],
          },
        ],
        confirmPassword: [
          {
            type: "string",
            required: true,
            message: "请输入确认密码",
            trigger: ["blur", "change"],
          },
          {
            validator: (rule, value, callback) => {
              return value === this.userInfo.password;
            },
            message: "两次密码不一致",
            trigger: ["change", "blur"],
          },
        ],
      },
    };
  },
  onLoad(param) {
    this.pageStatus = param.pageStatus || "normal";
    // console.log(this.loginUserInfo);
  },
  methods: {
    changePasswordType() {
      this.isPassword = !this.isPassword;
      this.passwordType = this.isPassword ? "password" : "text";
    },
    changeConfirmPasswordType() {
      this.isConfirmPassword = !this.isConfirmPassword;
      this.confirmPasswordType = this.isConfirmPassword ? "password" : "text";
    },
    doNext() {
      this.$refs.loginForm.validate().then((valid) => {
        if (valid) {
          if (this.isForgetPage) {
            this.resetPwd();
          } else {
            this.routerGo();
          }
        }
      });
    },
    resetPwd() {
      const param = {
        phoneNumber: this.loginUserInfo.phoneNumber,
        areaCode: this.loginUserInfo.areaCode,
        verificationCode: this.loginUserInfo.verificationCode,
        newPassword: md5(this.userInfo.password),
        platform: this.platform,
        operationID: this.operationID,
      };
      app_resetPassword(param).then((res) => {
        if (res.errCode === 0) {
          this.$toast("重置成功");
          this.$store.commit("user/set_loginUserInfo", {
            phoneNumber: this.loginUserInfo.phoneNumber,
            areaCode: this.loginUserInfo.areaCode,
            verificationCode: this.loginUserInfo.verificationCode,
            password: this.userInfo.password,
          });
          setTimeout(() => {
            uni.redirectTo({
              url: "/pages/login/index?pageStatus=" + this.pageStatus,
            });
          }, 1000);
        } else {
          this.$toast(res.errMsg);
        }
      });
    },
    routerGo() {
      const info = {
        phoneNumber: this.loginUserInfo.phoneNumber,
        areaCode: this.loginUserInfo.areaCode,
        verificationCode: this.loginUserInfo.verificationCode,
        password: this.userInfo.password,
      };
      this.$store.commit("user/set_loginUserInfo", info);
      uni.navigateTo({
        url: "@/setInfo",
      });
    },
  },
  computed: {
    ...mapGetters(["operationID", "platform", "loginUserInfo"]),
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
.setPwd {
  .statusBar {
    padding-top: 146rpx;
  }
  .loginForm {
    .eye {
      .image {
        width: 44rpx;
        height: 32rpx;
      }
    }
  }
  .form-des {
    margin-top: 28rpx;
    font-size: 24rpx;
  }
  .color-blue {
    color: #1d6bed;
  }
  .btn {
    margin-top: 80rpx;
  }
}
</style>
