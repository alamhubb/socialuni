<template>
  <view class="info">
    <view class="info-item" @click="avatarData.actionShow = true">
      <view class="left">头像</view>
      <view class="right">
        <u--image
          class="faceURL"
          :src="userInfo.faceURL"
          width="42px"
          height="42px"
          radius="6"
        >
          <u-icon slot="error" name="account-fill" size="20" color="#666" />
        </u--image>
        <u-icon class="icon" name="arrow-right" size="18" color="#999" />
      </view>
    </view>
    <view class="info-item" @click="nicknameModal.show = true">
      <view class="left">昵称</view>
      <view class="right">
        <text class="text">{{ userInfo.nickname }}</text>
        <u-icon class="icon" name="arrow-right" size="18" color="#999" />
      </view>
    </view>
    <view class="info-item" @click="phoneNumberModal.show = true">
      <view class="left">手机号码</view>
      <view class="right">
        <text class="text">{{ userInfo.phoneNumber }}</text>
        <u-icon class="icon" name="arrow-right" size="18" color="#999" />
      </view>
    </view>
    <view class="info-item" @click="routerGo('/pages/friend/qrcode')">
      <view class="left">二维码名片</view>
      <view class="right">
        <image class="qrcode" src="@/static/images/user/qrcode2.png" />
        <u-icon class="icon" name="arrow-right" size="18" color="#999" />
      </view>
    </view>
    <view class="info-item" @click="userIDModal.show = true">
      <view class="left">ID号</view>
      <view class="right">
        <u-icon class="icon" name="arrow-right" size="18" color="#999" />
      </view>
    </view>
    <u-modal
      :show="phoneNumberModal.show"
      content="手机号码暂时不可修改"
      showCancelButton
      @confirm="phoneNumberModal.show = false"
      @cancel="phoneNumberModal.show = false"
    />
    <u-modal
      :show="nicknameModal.show"
      showCancelButton
      @confirm="confirm"
      @cancel="nicknameModal.show = false"
    >
      <view class="modal">
        <view class="modal-title">修改昵称</view>
        <view class="modal-content">
          <u--input
            class="input"
            placeholder=" "
            border="bottom"
            v-model="userInfo2.nickname"
          />
        </view>
      </view>
    </u-modal>
    <u-modal
      :show="userIDModal.show"
      showCancelButton
      confirmText="复制"
      @confirm="copy"
      @cancel="userIDModal.show = false"
    >
      <view class="modal">
        <view class="modal-title">ID号</view>
        <view class="modal-content">
          <u--input
            class="input"
            placeholder=" "
            border="bottom"
            readonly
            v-model="userInfo2.userID"
          />
        </view>
      </view>
    </u-modal>
    <u-action-sheet
      :actions="avatarData.actionList"
      :show="avatarData.actionShow"
      round="16"
      cancelText="取消"
      @select="confirmChooseCamera"
      @close="avatarData.actionShow = false"
    />
  </view>
</template>

<script>
import { mapGetters } from "vuex";
import {
  checkPhotoLibraryPermission,
  checkCameraPermission,
} from "@/utils/checkPermission";
export default {
  data() {
    return {
      phoneNumberModal: {
        show: false,
      },
      nicknameModal: {
        show: false,
      },
      userIDModal: {
        show: false,
      },
      avatarData: {
        actionShow: false,
        actionList: [
          {
            name: "拍照",
            color: "#000",
            fontSize: "16",
            sourceType: "camera",
          },
          {
            name: "从相册选择",
            color: "#000",
            fontSize: "16",
            sourceType: "album",
          },
        ],
      },
      userInfo2: {},
    };
  },
  methods: {
    init() {
      this.userInfo2 = { ...this.userInfo };
    },
    confirm() {
      this.phoneNumberModal.show = false;
      this.nicknameModal.show = false;
      this.$im.setSelfInfo(this.operationID, this.userInfo2, (res) => {
        if (res.errCode === 0) {
          this.$toast("修改成功");
          this.$im.getSelfUserInfo(this.operationID, (res2) => {
            const data = JSON.parse(res2.data);
            this.$store.commit("user/set_userInfo", data);
            this.init();
          });
        } else {
          this.$toast("修改失败");
        }
      });
    },
    getSelfUserInfo() {
      this.$im.getSelfUserInfo(this.operationID, (res) => {
        if (res.errCode === 0) {
          const data = JSON.parse(res.data);
          this.$store.commit("user/set_userInfo", data);
          this.init();
        }
      });
    },
    copy() {
      uni.setClipboardData({
        data: this.userInfo2.userID,
        success: () => {
          this.$toast("复制成功");
        },
      });
    },
    routerGo(url) {
      uni.navigateTo({
        url,
      });
    },
    confirmChooseCamera({ sourceType }) {
      switch (sourceType) {
        case "camera":
          this.checkCamera();
          break;
        case "album":
          this.checkPhotoLibrary();
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
  },
  computed: {
    ...mapGetters([
      "userInfo",
      "uploadFileTimes",
      "uploadFileRes",
      "operationID",
    ]),
  },
  watch: {
    uploadFileTimes() {
      this.userInfo2.faceURL = this.uploadFileRes;
      this.confirm();
    },
  },
  onLoad() {
    this.init();
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
  .modal {
    width: 100%;
    &-title {
      font-size: 32rpx;
      color: #333333;
      margin-bottom: 36rpx;
    }
    &-content {
      ::v-deep.input {
        padding-left: 0 !important;
        padding-right: 0 !important;
      }
    }
  }
}
</style>
