<template>
  <view class="SoundPop" v-show="show">
    <view class="wave wave-normal" v-show="!isCancel && !isText">
      <image
        class="wave-normal-image"
        src="@/static/images/conversation/sound/wave.png"
      />
    </view>
    <view class="wave wave-cancel" v-show="isCancel">
      <image
        class="wave-cancel-image"
        src="@/static/images/conversation/sound/wave2.png"
      />
    </view>
    <view class="wave wave-text" v-show="isText">
      <view class="wave-text-top">暂不支持此功能</view>
      <view class="wave-text-bottom">
        <image
          class="wave-text-image"
          src="@/static/images/conversation/sound/wave2.png"
        />
      </view>
    </view>
    <view class="bottom">
      <view class="bottom-header">
        <view class="bottom-header-item item1">
          <text class="des" v-show="isCancel">松开取消</text>
          <view :class="['icon', 'icon1', isCancel ? 'active active1' : '']">
            <u-icon
              name="close"
              size="24"
              :color="isCancel ? '#666' : '#bebebe'"
              bold
            />
          </view>
        </view>
        <view class="bottom-header-item item2">
          <text class="des" v-show="isText">转文字</text>
          <view :class="['icon', 'icon2', isText ? 'active active2' : '']">
            文
          </view>
        </view>
      </view>
      <view class="bottom-footer">
        <image
          class="voice"
          src="@/static/images/conversation/sound/voice.png"
        />
        <image class="bg" src="@/static/images/conversation/sound/bg.png" />
      </view>
    </view>
  </view>
</template>
<script>
// #ifdef APP-PLUS
const recorderManager = uni.getRecorderManager();
import permision from "@/js_sdk/wa-permission/permission.js";
// #endif
export default {
  data() {
    return {
      show: false,
      isCancel: false,
      isText: false,
      positionObj: {
        cancel: {},
        text: {},
      },
      soundData: {
        startTime: null,
      },
    };
  },
  methods: {
    init() {
      const queryList = [
        { className: ".icon1", key: "cancel" },
        { className: ".icon2", key: "text" },
      ];
      const query = uni.createSelectorQuery().in(this);
      queryList.forEach((i) => {
        query
          .select(i.className)
          .boundingClientRect((item) => {
            const { top, height, left, width } = item;
            const minY = top;
            const maxY = top + height;
            const minX = left;
            const maxX = left + width;
            this.positionObj[i.key] = { minY, maxY, minX, maxX };
          })
          .exec();
      });
    },
    checkPermission() {
      return new Promise((resolve, reject) => {
        let isIos = false;
        isIos = plus.os.name == "iOS";
        const cb = (res) => {
          if (res === 1) {
            resolve();
          } else {
            this.$toast("请打开麦克风权限");
            setTimeout(() => {
              permision.gotoAppPermissionSetting();
            }, 1000);
            reject();
          }
        };
        if (isIos) {
          const res = permision.judgeIosPermission("record");
          cb(res);
        } else {
          const timer = setTimeout(() => {
            this.isCancel = false;
            this.isText = false;
            this.show = false;
          }, 50);
          permision
            .requestAndroidPermission("android.permission.RECORD_AUDIO")
            .then((res) => {
              clearTimeout(timer);
              cb(res);
            });
        }
      });
    },
    start() {
      // #ifdef APP-PLUS
      const startTime = new Date().getTime();
      this.checkPermission().then(() => {
        const endTime = new Date().getTime();
        if (endTime - startTime > 50) {
          return;
        }
        this.isCancel = false;
        this.isText = false;
        this.soundData.startTime = new Date().getTime();
        recorderManager.start();
        this.show = true;
        this.$nextTick(() => {
          this.init();
        });
      });
      // #endif
      // #ifdef H5
      this.isCancel = false;
      this.isText = false;
      this.soundData.startTime = new Date().getTime();
      this.show = true;
      this.$nextTick(() => {
        this.init();
      });
      // #endif
    },
    end() {
      // #ifdef APP-PLUS
      if (this.soundData.startTime) {
        recorderManager.stop();
      }
      // #endif
      this.show = false;
    },
    move(x, y) {
      const isCancel =
        x > this.positionObj.cancel.minX &&
        x < this.positionObj.cancel.maxX &&
        y > this.positionObj.cancel.minY &&
        y < this.positionObj.cancel.maxY
          ? true
          : false;
      const isText =
        x > this.positionObj.text.minX &&
        x < this.positionObj.text.maxX &&
        y > this.positionObj.text.minY &&
        y < this.positionObj.text.maxY
          ? true
          : false;
      this.isCancel = isCancel;
      this.isText = isText;
    },
    sendSound(res) {
      const endTime = new Date().getTime();
      const duration = parseInt((endTime - this.soundData.startTime) / 1000);
      this.soundData.startTime = 0;
      if (this.isCancel) {
        return;
      }
      if (this.isText) {
        this.$toast("暂不支持此功能");
        return;
      }
      if (duration <= 0) {
        this.$toast("说话时间太短");
        return;
      }
      const filePath = plus.io.convertLocalFileSystemURL(res.tempFilePath);
      this.$emit("createSoundMessage", filePath, duration);
    },
  },
  created() {
    // #ifdef APP-PLUS
    // this.checkPermission();
    recorderManager.onStop((res) => {
      this.sendSound(res);
    });
    // #endif
  },
};
</script>
<style lang="scss" scoped>
.SoundPop {
  position: fixed;
  z-index: 99;
  width: 100%;
  height: 100%;
  left: 0;
  top: 0;
  background-color: rgba(0, 0, 0, 0.6);
  .wave {
    box-sizing: border-box;
    background: #ffffff;
    border-radius: 12rpx;
    position: absolute;
    z-index: 1;
    top: 50%;
    &::after {
      content: "";
      position: absolute;
      top: 100%;
    }
    &-normal {
      display: flex;
      flex-direction: row;
      justify-content: center;
      align-items: center;
      width: 388rpx;
      height: 130rpx;
      left: 50%;
      transform: translate3d(-50%, -50%, 0);
      &::after {
        left: 50%;
        transform: translateX(-50%);
        width: 0;
        height: 0;
        border: 12rpx solid transparent;
        border-top-color: #fff;
      }
      &-image {
        width: 280rpx;
        height: 70rpx;
      }
    }
    &-cancel {
      display: flex;
      flex-direction: row;
      justify-content: center;
      align-items: center;
      width: 208rpx;
      height: 190rpx;
      background-color: #fa5251;
      left: 22rpx;
      transform: translateY(-50%);
      &::after {
        left: 50%;
        transform: translateX(-50%);
        width: 0;
        height: 0;
        border: 12rpx solid transparent;
        border-top-color: #fa5251;
      }
      &-image {
        width: 82rpx;
        height: 12rpx;
      }
    }
    &-text {
      width: 662rpx;
      padding: 28rpx 44rpx;
      left: 50%;
      transform: translate3d(-50%, -50%, 0);
      text-align: left;
      &-top {
        font-size: 28rpx;
        font-weight: 500;
        color: #000000;
      }
      &-bottom {
        display: flex;
        flex-direction: row;
        justify-content: flex-end;
        margin-top: 20rpx;
      }
      &::after {
        right: 72rpx;
        width: 0;
        height: 0;
        border: 12rpx solid transparent;
        border-top-color: #fff;
      }
      &-image {
        width: 82rpx;
        height: 12rpx;
      }
    }
  }
  .bottom {
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    &-header {
      display: flex;
      flex-direction: row;
      align-items: flex-end;
      justify-content: space-between;
      text-align: center;
      &-item {
        margin: 0 60rpx 50rpx;
        .des {
          font-size: 24rpx;
          color: #bebebe;
        }
        .icon {
          width: 140rpx;
          height: 140rpx;
          display: flex;
          justify-content: center;
          align-items: center;
          text-align: center;
          background-color: #48484a;
          border-radius: 50%;
          margin-top: 20rpx;
          color: #bebebe;
          font-size: 48rpx;
        }
        .icon1 {
          transform: rotate(-30deg);
        }
        .icon2 {
          transform: rotate(30deg);
        }
        .active {
          background-color: #fff;
          color: #666;
        }
        .active1 {
          transform: scale(1.1) rotate(-30deg);
        }
        .active2 {
          transform: scale(1.1) rotate(30deg);
        }
      }
    }
    &-footer {
      width: 100%;
      height: 260rpx;
      position: relative;
      display: flex;
      flex-direction: row;
      justify-content: center;
      align-items: center;
      .voice {
        width: 72rpx;
        height: 72rpx;
      }
      .bg {
        position: absolute;
        z-index: -1;
        width: 100%;
        height: 100%;
      }
    }
  }
}
</style>