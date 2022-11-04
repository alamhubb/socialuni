<template>
  <u-overlay :show="show" opacity="1">
    <view class="VideoPop">
      <video
        id="VideoPop-video"
        class="VideoPop-video"
        :src="videoUrl"
        :controls="false"
        :show-play-btn="false"
        :show-center-play-btn="false"
        @timeupdate="timeupdate"
        @ended="ended"
      />
      <image
        v-show="!isPlay"
        class="play"
        src="@/static/images/conversation/play.png"
        @click="startPlay"
      />
      <view class="operation">
        <view class="top">
          <image
            v-show="!isPlay"
            class="image"
            src="@/static/images/conversation/video/play.png"
            @click="startPlay"
          />
          <image
            v-show="isPlay"
            class="image"
            src="@/static/images/conversation/video/stop.png"
            @click="stopPlay"
          />
          <u-slider
            class="slider"
            v-model="currentTime"
            :max="maxTime"
            @change="seek"
          />
          <text class="duration">
            {{ (maxTime - currentTime) | formatDuration }}
          </text>
        </view>
        <view class="bottom">
          <image
            class="image"
            src="@/static/images/conversation/video/close.png"
            @click="close"
          />
          <image
            class="image"
            src="@/static/images/conversation/video/download.png"
            @click="download"
          />
        </view>
      </view>
    </view>
  </u-overlay>
</template>

<script>
export default {
  data() {
    return {
      show: false,
      videoUrl: "",
      videoContext: null,
      currentTime: 0,
      maxTime: 0,
      isPlay: false,
    };
  },
  methods: {
    init(videoElem) {
      this.videoUrl = videoElem.videoUrl;
      this.maxTime = videoElem.duration;
      this.isPlay = false;
      this.show = true;
      this.currentTime = 0;
      this.videoContext = uni.createVideoContext("VideoPop-video");
      this.seek(0);
    },
    startPlay() {
      this.videoContext.play();
      this.isPlay = true;
    },
    timeupdate(e) {
      this.currentTime = parseInt(e.detail.currentTime);
    },
    stopPlay() {
      this.videoContext.pause();
      this.isPlay = false;
    },
    seek(v) {
      this.videoContext.seek(v);
    },
    close() {
      this.stopPlay();
      this.show = false;
    },
    ended() {
      this.isPlay = false;
    },
    download() {
      uni.downloadFile({
        url: this.videoUrl,
        success: (res) => {
          if (res.statusCode === 200) {
            uni.saveFile({
              tempFilePath: res.tempFilePath,
              success: () => {
                this.$toast("下载成功");
              },
              fail: () => {
                this.$toast("下载失败");
              },
            });
          } else {
            this.$toast("下载失败");
          }
        },
        fail: () => {
          this.$toast("下载失败");
        },
      });
    },
  },
  filters: {
    formatDuration(d) {
      let m = parseInt(d / 60); //分
      m = m < 10 ? "0" + m : m;
      let s = d % 60; //秒
      s = s < 10 ? "0" + s : s;
      return `${m}:${s}`;
    },
  },
};
</script>
<style lang="scss" scoped>
.VideoPop {
  width: 100%;
  height: 100vh;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  position: relative;
  &-video {
    width: 100%;
    height: 800rpx;
  }
  .play {
    position: absolute;
    z-index: 1;
    left: 50%;
    top: 50%;
    transform: translate3d(-50%, -50%, 0);
    width: 100rpx;
    height: 100rpx;
  }
  .operation {
    position: absolute;
    z-index: 1;
    left: 0;
    bottom: 100rpx;
    width: 100%;
    color: #fff;
    padding: 0 44rpx;
    box-sizing: border-box;
    .top,
    .bottom {
      width: 100%;
      display: flex;
      flex-direction: row;
      align-items: center;
      overflow: hidden;
      .image {
        flex-shrink: 0;
        width: 44rpx;
        height: 44rpx;
      }
    }
    .top {
      margin-bottom: 20rpx;
      .slider {
        width: 100%;
        margin-left: 20rpx;
        flex: 1;
      }
      .duration {
      }
    }
    .bottom {
      justify-content: space-between;
    }
  }
}
</style>