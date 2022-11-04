<template>
  <view class="setInfo commonPage">
    <view class="statusBar"></view>
    <view class="title">欢迎使用OpenIM</view>
    <view class="secondTitle2">请完善个人信息</view>
    <view class="avatar" @click="fileImageData.actionShow = true">
      <view class="avatar-item">
        <image
          v-show="userInfo.faceURL"
          class="image"
          :src="userInfo.faceURL"
        />
        <u-icon
          v-show="!userInfo.faceURL"
          name="camera-fill"
          size="46"
          color="#999"
        />
      </view>
      <view class="avatar-des"> 点击上传头像 </view>
    </view>
    <u-form
      class="loginForm commonPage-form"
      labelPosition="left"
      :model="userInfo"
      :rules="rules"
      labelWidth="160rpx"
      :labelStyle="{
        fontWeight: 400,
        marginTop: '20rpx',
        marginBottom: '10rpx',
      }"
      ref="loginForm"
    >
      <u-form-item label="你的姓名" prop="nickname" borderBottom>
        <u-input
          v-model="userInfo.nickname"
          border="none"
          fontSize="36rpx"
          placeholder="请填写真实姓名"
          clearable
        >
        </u-input>
      </u-form-item>
    </u-form>
    <view class="btn">
      <u-button :loading="isLoading" type="primary" @click="doNext">
        进入OpenIM
      </u-button>
    </view>
    <u-action-sheet
      :actions="fileImageData.actionList"
      :show="fileImageData.actionShow"
      round="16"
      cancelText="取消"
      @select="confirmChooseAlbum"
      @close="fileImageData.actionShow = false"
    />
  </view>
</template>

<script>
import { mapGetters } from "vuex";
import md5 from "js-md5";
import { app_register } from "@/service/api/user";
import {
  checkPhotoLibraryPermission,
  checkCameraPermission,
} from "@/utils/checkPermission";
export default {
  data() {
    return {
      userInfo: {
        nickname: "",
        faceURL: "",
      },
      rules: {
        nickname: [
          {
            type: "string",
            required: true,
            message: "请填写真实姓名",
            trigger: ["blur", "change"],
          },
        ],
      },
      fileImageData: {
        actionShow: false,
        actionList: [
          {
            name: "相册",
            color: "#000",
            fontSize: "16",
            type: "album",
          },
          {
            name: "拍摄",
            color: "#000",
            fontSize: "16",
            type: "camera",
          },
        ],
      },
      isLoading: false,
    };
  },
  onLoad() {},
  methods: {
    confirmChooseAlbum(item) {
      switch (item.type) {
        case "album":
          this.checkPhotoLibrary();
          break;
        case "camera":
          this.checkCamera();
          break;
      }
    },
    async checkPhotoLibrary() {
      const status = await checkPhotoLibraryPermission();
      if (status === true) {
        this.chooseImage("album");
      }
    },
    async checkCamera() {
      const status = await checkCameraPermission();
      if (status === true) {
        this.chooseImage("camera");
      }
    },
    chooseImage(sourceType) {
      wx.chooseImage({
        count: 1,
        sourceType: [sourceType],
        success: (res) => {
          let path = res.tempFilePaths[0];
          if (sourceType === "camera") {
            path = this.convertLocalFileSystemURL(path);
          }
          path = this.formatPath(path);
          this.$im.uploadFile(this.operationID, path);
        },
        fail: (error) => {
          console.log(error);
        },
      });
    },
    convertLocalFileSystemURL(path) {
      //相对路径转换为绝对路径
      // #ifdef H5
      return path;
      // #endif
      // #ifdef APP-PLUS
      return plus.io.convertLocalFileSystemURL(path);
      // #endif
    },
    formatPath(path) {
      const pathList = path.split("file://");
      return pathList[1] || path;
    },
    doNext() {
      this.$refs.loginForm.validate().then((valid) => {
        if (valid) {
          this.doRegister();
        }
      });
    },
    doRegister() {
      const param = {
        phoneNumber: this.loginUserInfo.phoneNumber,
        areaCode: this.loginUserInfo.areaCode,
        verificationCode: this.loginUserInfo.verificationCode,
        password: md5(this.loginUserInfo.password),
        faceURL: this.userInfo.faceURL,
        nickname: this.userInfo.nickname,
        platform: this.platform,
        operationID: this.operationID,
      };
      this.isLoading = true;
      app_register(param).then((res) => {
        console.log(res);
        if (res.errCode === 0) {
          this.$toast("注册成功");
          this.startLogin(res);
        } else {
          this.$toast(res.errMsg);
          this.isLoading = false;
        }
      });
    },
    startLogin(res) {
      this.$store.commit("user/set_token", res.data.token);
      this.$store.commit("user/set_userID", res.data.userID);
      this.loginCb();
    },
    loginCb() {
      this.appLogin()
        .then(() => {
          this.$im.getSelfUserInfo(this.operationID, (res) => {
            console.log(res);
            if (res.errCode === 0) {
              const data = JSON.parse(res.data);
              this.$store.commit("user/set_userInfo", data);
              this.$store.commit("user/set_loginStatus", true);
              this.$store.commit("user/set_loginUserInfo", null);
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
              uni.switchTab({
                url: "/pages/index/index",
              });
            } else {
              this.isLoading = false;
              this.$toast(res.errMsg);
            }
          });
        })
        .catch((msg) => {
          this.isLoading = false;
          this.$toast(msg);
        });
    },
    appLogin() {
      return new Promise((resolve, reject) => {
        this.$im.login(this.operationID, this.userID, this.token, (res) => {
          console.log(res);
          if (res.errCode === 0) {
            const loginStatus = this.getLoginStatus();
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
  },
  computed: {
    ...mapGetters([
      "operationID",
      "platform",
      "loginUserInfo",
      "uploadFileTimes",
      "uploadFileRes",
      "userID",
      "token",
    ]),
  },
  watch: {
    uploadFileTimes() {
      this.userInfo.faceURL = this.uploadFileRes;
    },
  },
};
</script>
<style lang="scss" scoped>
@import "@/style/common.scss";
.setInfo {
  .statusBar {
    padding-top: 120rpx;
  }
  .avatar {
    margin-top: 100rpx;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    &-item {
      background-color: #d8d8d8;
      width: 180rpx;
      height: 180rpx;
      display: flex;
      justify-content: center;
      align-items: center;
      border-radius: 14rpx;
      .image {
        width: 100%;
        height: 100%;
      }
    }
    &-des {
      font-size: 24rpx;
      color: #999;
      margin-top: 14rpx;
    }
  }
  .btn {
    margin-top: 200rpx;
  }
}
</style>
