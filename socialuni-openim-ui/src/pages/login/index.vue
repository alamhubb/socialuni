<template>
  <view class="login">
    <view class="title" @click="toSetting">欢迎使用OpenIM</view>
    <view class="loginType">
      <text
        :class="[
          'loginType-item',
          loginType.current === index ? 'loginType-active' : '',
        ]"
        v-for="(item, index) in loginType.list"
        :key="index"
        @click="loginTypeChange(index)"
        >{{ item.name }}</text
      >
    </view>
    <u-form
      class="loginForm"
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
      <u-form-item prop="phoneNumber" borderBottom v-show="!showEmail">
        <u-input
          v-model="userInfo.phoneNumber"
          border="none"
          fontSize="36rpx"
          placeholder="请输入手机号码"
          clearable
        >
          <view slot="prefix" class="phoneNumber-code" @click="showPicker">
            <text class="code">+{{ userInfo.areaCode }}</text>
            <u-icon class="icon" name="arrow-down"></u-icon>
          </view>
        </u-input>
      </u-form-item>
      <u-form-item prop="email" borderBottom v-show="showEmail">
        <u-input
          v-model="userInfo.email"
          border="none"
          fontSize="36rpx"
          placeholder="请输入邮箱"
          clearable
        >
        </u-input>
      </u-form-item>
      <u-form-item label="密码" prop="password" borderBottom>
        <u-input
          v-show="isPassword"
          v-model="userInfo.password"
          border="none"
          fontSize="36rpx"
          placeholder="请输入密码"
          type="password"
        >
          <view class="eye" slot="suffix">
            <image
              @click="changeType"
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
          <view class="eye" slot="right">
            <image
              @click="changeType"
              class="image"
              src="@/static/images/login/eye-open.png"
            />
          </view>
        </u-input>
      </u-form-item>
    </u-form>
    <view class="other">
      <text class="forget" @click="forget">忘记密码</text>
      <text class="register" @click="routerGo('normal')">新用户注册</text>
    </view>
    <view class="login-btn">
      <u-button type="primary" @click="startLogin" :disabled="!checked[0]">
        登录
      </u-button>
    </view>
    <view class="agreement">
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
    <AreaPicker ref="AreaPicker" @chooseArea="chooseArea" />
  </view>
</template>

<script>
import { mapGetters } from "vuex";
import md5 from "js-md5";
import AreaPicker from "@/components/AreaPicker";
export default {
  components: { AreaPicker },
  data() {
    return {
      pageStatus: "normal",
      loginType: { list: [{ name: "手机号码" }, { name: "邮箱" }], current: 0 },
      userInfo: {
        phoneNumber: "",
        email: "",
        password: "",
        areaCode: "86",
      },
      passwordType: "password",
      isPassword: true,
      checked: [false],
    };
  },
  onLoad(param) {
    this.pageStatus = param.pageStatus || "normal";
  },
  onReady() {
    this.init();
  },
  methods: {
    init() {
      if (this.isForgetPage && this.loginUserInfo) {
        this.userInfo.phoneNumber = this.loginUserInfo.phoneNumber;
        this.userInfo.password = this.loginUserInfo.password;
      }
    },
    loginTypeChange(index) {
      this.loginType.current = index;
    },
    startLogin() {
      this.$refs.loginForm.validate().then((valid) => {
        if (this.showEmail && valid) {
          this.$toast("暂不支持邮箱登录");
        } else if (valid) {
          this.$store
            .dispatch("user/login", {
              phoneNumber: this.userInfo.phoneNumber,
              password: md5(this.userInfo.password),
            })
            .then(() => {
              this.loginCb();
            })
            .catch((error) => {
              console.log(error);
              this.$toast(error.errMsg || error.message);
            });
        }
      });
    },
    loginCb() {
      if (this.connectStatus === 1) {
        uni.showLoading();
        this.appLogin()
          .then(() => {
            this.$im.getSelfUserInfo(this.operationID, (res) => {
              if (res.errCode === 0) {
                const data = JSON.parse(res.data);
                this.$store.commit("user/set_userInfo", data);
                // console.log(data);
                this.$store.commit("user/set_loginStatus", true);
                this.$store.dispatch("contacts/get_friendNoticeList", this.$im);
                this.$store.dispatch(
                  "contacts/get_selfFriendNoticeList",
                  this.$im
                );
                this.$store.dispatch("contacts/get_groupNoticeList", this.$im);
                this.$store.dispatch(
                  "contacts/get_selfGroupNoticeList",
                  this.$im
                );
                uni.hideLoading();
                uni.switchTab({
                  url: "/pages/index/index",
                });
              } else {
                this.$toast(res.errMsg);
              }
            });
          })
          .catch((msg) => {
            this.$toast(msg);
          });
      } else if (this.connectStatus === 2) {
        this.$toast("服务器连接中，请稍后再试");
      } else {
        this.$toast("服务器连接超时，请稍后再试");
      }
    },
    appLogin() {
      return new Promise((resolve, reject) => {
        this.$im.login(this.operationID, this.userID, this.token, (res) => {
          if (res.errCode === 0) {
            const loginStatus = this.getLoginStatus();
            console.log(res, loginStatus);
            if (loginStatus) {
              resolve(loginStatus);
            } else {
              reject("登录失败");
            }
          } else {
            reject(res.errMsg);
          }
        });
      });
    },
    getLoginStatus() {
      const status = this.$im.getLoginStatus(); // 返回值为 101:登录成功 102:登陆中 103:登录失败 201:登出
      if (status === 101) {
        return true;
      } else {
        return false;
      }
    },
    changeType() {
      this.isPassword = !this.isPassword;
      this.passwordType = this.isPassword ? "password" : "text";
    },
    showPicker() {
      this.$refs.AreaPicker.init();
    },
    chooseArea(areaCode) {
      this.userInfo.areaCode = areaCode;
    },
    forget() {
      this.$store.commit("user/set_loginUserInfo", this.userInfo);
      this.routerGo("forget");
    },
    routerGo(pageStatus) {
      uni.navigateTo({
        url: "@/register?pageStatus=" + pageStatus,
      });
    },
    toSetting() {
      uni.navigateTo({
        url: "/pages/setting/index",
      });
    },
  },
  computed: {
    ...mapGetters([
      "operationID",
      "userID",
      "token",
      "connectStatus",
      "loginUserInfo",
    ]),
    showEmail() {
      return this.loginType.current === 1;
    },
    rules() {
      const r = {
        password: [
          {
            type: "string",
            required: true,
            message: "请输入密码",
            trigger: ["blur", "change"],
          },
        ],
      };
      if (!this.showEmail) {
        r.phoneNumber = [
          {
            type: "string",
            required: true,
            message: "请输入手机号码",
            trigger: ["blur", "change"],
          },
        ];
      } else {
        r.email = [
          {
            required: true,
            message: "请输入邮箱",
            trigger: ["blur", "change"],
          },
          {
            validator: (rule, value, callback) => {
              return uni.$u.test.email(value);
            },
            message: "邮箱有误",
            trigger: ["change", "blur"],
          },
        ];
      }
      return r;
    },
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
.login {
  color: #333333;
  padding: 252rpx 80rpx 0;
  min-height: 100vh;
  .title {
    font-size: 64rpx;
    font-weight: 600;
    margin-bottom: 116rpx;
  }
  .loginType {
    margin-bottom: 64rpx;
    &-item {
      margin-right: 68rpx;
      font-size: 28rpx;
      font-weight: 400;
      border-radius: 4rpx;
      padding: 2rpx 0;
    }
    &-active {
      color: $u-primary;
      border-bottom: 4rpx solid $u-primary;
    }
  }
  .loginForm {
    .phoneNumber-code {
      display: flex;
      align-items: center;
      font-size: 36rpx;
      border-right: 2rpx solid #d8d8d8;
      margin-right: 58rpx;
      .code {
        font-weight: 400;
        margin-right: 20rpx;
      }
      .icon {
        margin-right: 40rpx;
      }
    }
    .eye {
      .image {
        width: 44rpx;
        height: 32rpx;
      }
    }
  }
  .other {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-top: 29rpx;
    font-size: 24rpx;
    font-weight: 400;
    .register {
      color: $u-primary;
    }
  }
  .login-btn {
    margin-top: 168rpx;
  }
  .agreement {
    display: flex;
    align-items: flex-start;
    margin-top: 36rpx;
    .detail {
      font-size: 24rpx;
      font-weight: 400;
      color: $u-primary;
    }
  }
}
</style>
