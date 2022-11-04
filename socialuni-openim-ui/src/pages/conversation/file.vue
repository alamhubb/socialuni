<template>
  <view class="file">
    <view class="top">
      <image
        class="file-image"
        src="@/static/images/conversation/file/document2.png"
      />
      <view class="file-name">
        {{ localFile ? localFile.fileName : openFile.fileName }}
      </view>
      <view class="file-size"> 文件大小：{{ fileSize }} </view>
    </view>
    <view class="operation" v-show="localFile">
      <u-button class="operation-btn" type="primary" @click="openLocalFile"
        >打开</u-button
      >
    </view>
    <view class="operation" v-show="!localFile">
      <circle-progress-bar
        :pro="progressData.progress"
        border_back_color="#ccc"
        background="#f6f6f6"
        border_color="#1d6bed"
      >
        <view class="operation-icons" @click="doDownload">
          <u-icon
            v-show="progressData.isPending"
            size="24"
            name="pause"
            color="#1d6bed"
          />
          <u-icon
            v-show="progressData.isStop"
            size="24"
            name="play-right-fill"
            color="#1d6bed"
          />
        </view>
      </circle-progress-bar>
    </view>
  </view>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      progressData: {
        progress: 0.48,
        isPending: false,
        isStop: false,
      },
      downloadTask: null,
    };
  },
  methods: {
    init() {
      if (!this.localFile) {
        this.progressData.isPending = false;
        this.progressData.isStop = true;
        this.progressData.progress = 0;
      }
    },
    doDownload() {
      if (this.progressData.isPending) {
        //停止下载
        this.progressData.isPending = false;
        this.progressData.isStop = true;
        if (this.downloadTask) {
          this.downloadTask.abort();
          //   this.downloadTask = null;
        }
      } else if (this.progressData.isStop) {
        //开始下载
        this.progressData.isStop = false;
        this.progressData.isPending = true;
        this.startDownload();
      }
    },
    startDownload() {
      this.downloadTask = uni.downloadFile({
        url: this.openFile.url,
        filePath: "/storage/emulated/0/Download/openIm",
        success: (res) => {
          this.downloadSuccess(res.tempFilePath);
        },
        fail: () => {
          this.progressData.isPending = false;
          this.progressData.isStop = true;
          this.progressData.progress = 0;
          if (this.downloadTask) {
            this.downloadTask.abort();
            this.downloadTask = null;
          }
        },
      });
      this.downloadTask.onProgressUpdate((res) => {
        this.progressData.progress = Number((res.progress / 100).toFixed(2));
      });
    },
    downloadSuccess(filePath) {
      uni.saveFile({
        tempFilePath: filePath,
        success: (res) => {
          const savedFilePath = res.savedFilePath;
          this.$store.commit("message/push_fileHistory", {
            url: this.openFile.url,
            fileName: this.openFile.fileName,
            fileSize: this.openFile.fileSize,
            filePath: savedFilePath,
          });
          this.openDocument(savedFilePath);
        },
        fail: (error) => {
          console.log(error);
        },
      });
    },
    openDocument(filePath) {
      uni.openDocument({
        filePath,
      });
    },
    openLocalFile() {
      uni.getSavedFileInfo({
        filePath: this.localFile.filePath,
        success: () => {
          this.openDocument(this.localFile.filePath);
        },
        fail: () => {
          this.$store.commit(
            "message/remove_fileHistory",
            this.localFile.filePath
          );
          this.progressData.isPending = false;
          this.progressData.isStop = true;
          this.progressData.progress = 0;
        },
      });
    },
  },
  onLoad() {
    this.init();
  },
  computed: {
    ...mapGetters(["openFile", "fileHistory"]),
    fileSize() {
      let size = "";
      const size1M = 1024;
      const size1G = size1M * 1024;
      if (
        this.localFile &&
        this.localFile.fileSize &&
        this.localFile.fileSize / 1024 >= size1G
      ) {
        size = (this.localFile.fileSize / 1024 / size1G).toFixed(2);
        size += "GB";
      } else if (this.localFile && this.localFile.fileSize) {
        size = (this.localFile.fileSize / 1024 / size1M).toFixed(2);
        size += "MB";
      } else if (
        this.openFile &&
        this.openFile.fileSize &&
        this.openFile.fileSize / 1024 >= size1G
      ) {
        size = (this.openFile.fileSize / 1024 / size1G).toFixed(2);
        size += "GB";
      } else {
        size = (this.openFile.fileSize / 1024 / size1M).toFixed(2);
        size += "MB";
      }
      return size;
    },
    localFile() {
      const { url } = this.openFile;
      const item = this.fileHistory.filter((i) => i.url === url)[0] || null;
      return item;
    },
  },
};
</script>
<style lang="scss" scoped>
.file {
  height: 100vh;
  overflow-y: hidden;
  background-color: #f6f6f6;
  .top {
    padding-top: 40%;
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
    .file-name {
      font-size: 40rpx;
      font-weight: 500;
      color: #333333;
      margin-top: 64rpx;
      margin-bottom: 12rpx;
    }
    .file-size {
      color: #333333;
    }
    .file-image {
      width: 112rpx;
      height: 112rpx;
    }
  }
  .operation {
    display: flex;
    justify-content: center;
    margin-top: 40%;
    &-btn {
      width: 50%;
      margin: 0 auto;
    }
    &-icons {
      width: 100rpx;
      height: 100rpx;
      display: flex;
      justify-content: center;
      align-items: center;
    }
  }
}
</style>