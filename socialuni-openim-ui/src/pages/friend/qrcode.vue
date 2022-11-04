<template>
  <view class="qrcode">
    <view class="statusBar"></view>
    <view class="qrcode-card">
      <view class="info">
        <Avatar
          class="Avatar"
          :src="userInfo.faceURL"
          :name="userInfo.nickname"
        />
        <text class="nickname">{{ userInfo.nickname }}</text>
      </view>
      <view class="notice">扫一扫下面的二维码，添加我为好友</view>
      <view class="code">
        <image class="bg" src="@/static/images/friend/qrcode-border.png" />
        <Qrcode
          class="Qrcode"
          ref="qrcode"
          :modal="modal_qr"
          :url="userInfo.userID"
          :height="140"
          :width="140"
        />
      </view>
    </view>
  </view>
</template>

<script>
import Qrcode from "@/components/Qrcode/ay-qrcode.vue";
import Avatar from "@/components/Avatar.vue";
import { mapGetters } from "vuex";
export default {
  components: { Qrcode, Avatar },
  data() {
    return {
      modal_qr: false,
      userInfo: {
        userID: "3798435312",
        nickname: "OpenIM-Blooming",
        faceURL: "ic_avatar_06",
        gender: 0,
        phoneNumber: "17396220460",
        birth: 907156400,
        email: "blooming2477@gmail.com",
        createTime: 0,
        ex: "",
        attachedInfo: "",
        globalRecvMsgOpt: 0,
      },
    };
  },
  methods: {
    init() {
      this.$im.getSelfUserInfo(this.operationID, (res) => {
        let data = res.data;
        if (data) {
          data = JSON.parse(data);
          this.userInfo = data;
          this.showQrcode();
        }
      });
    },
    showQrcode() {
      this.modal_qr = true;
      setTimeout(() => {
        this.$refs.qrcode.crtQrCode();
      }, 50);
    },
  },
  onLoad() {
    // #ifdef APP-PLUS
    this.init();
    // #endif
  },
  onReady() {
    // #ifdef H5
    this.showQrcode();
    // #endif
  },
  computed: {
    ...mapGetters(["operationID"]),
  },
};
</script>

<style lang="scss" scoped>
.qrcode {
  background: #f8f8f8;
  border-top: 2rpx solid #f8f8f8;
  min-height: 100vh;
  &-card {
    margin: 116rpx 44rpx 0;
    background: #ffffff;
    box-shadow: 0px 0px 7px 0px rgba(0, 0, 0, 0.1);
    border-radius: 10px;
    .info {
      display: flex;
      align-items: center;
      padding: 72rpx 72rpx 100rpx;
      .Avatar {
        flex-shrink: 0;
      }
      .nickname {
        flex: 1;
        margin-left: 36rpx;
        font-size: 20px;
        font-weight: 400;
        color: #000000;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
      }
    }
    .notice {
      text-align: center;
      font-size: 28rpx;
      color: #999999;
      margin-bottom: 60rpx;
    }
    .code {
      margin: 0 auto;
      width: 180px;
      height: 180px;
      padding-bottom: 160rpx;
      position: relative;
      .bg {
        width: 100%;
        height: 100%;
      }
      .Qrcode {
        position: absolute;
        z-index: 1;
        top: 21px;
        // #ifdef H5
        left: -27px;
        // #endif
        // #ifdef APP-PLUS
        left: 20px;
        // #endif
      }
    }
  }
}
</style>