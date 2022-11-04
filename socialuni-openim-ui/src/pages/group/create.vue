<template>
  <view class="edit">
    <view class="edit-item edit-group">
      <view class="faceURL">
        <Avatar
          v-if="groupInfo.faceURL"
          class="faceURL"
          :src="groupInfo.faceURL"
          :name="groupInfo.groupName"
          size="42px"
          @click="changeGroupFaceurlData.actionShow = true"
        />
        <u-icon
          class="icon"
          v-else
          name="photo"
          size="60"
          color="#999"
          @click="changeGroupFaceurlData.actionShow = true"
        />
      </view>
      <view class="content">
        <view class="input">
          <u--input
            placeholder="请输入群聊名称"
            border="none"
            clearable
            v-model="groupInfo.groupName"
          />
        </view>
      </view>
    </view>
    <view class="btn-group">
      <u-button class="button" type="primary" size="mini" @click="confirm">
        完成
      </u-button>
    </view>
    <u-action-sheet
      :show="changeGroupFaceurlData.actionShow"
      :actions="changeGroupFaceurlData.actionList"
      round="16"
      @select="confirmChooseCamera"
      @close="changeGroupFaceurlData.actionShow = false"
    />
  </view>
</template>

<script>
import Avatar from "@/components/Avatar.vue";
import { mapGetters } from "vuex";
import {
  checkPhotoLibraryPermission,
  checkCameraPermission,
} from "@/utils/checkPermission";
export default {
  components: { Avatar },
  data() {
    return {
      addUserID: "",
      groupInfo: {
        groupName: "", //群名称
        groupType: 0, //群类型
        notification: "", //群公告
        introduction: "", //群介绍
        faceURL: "", //群头像
        ex: "", //扩展字段
      },
      changeGroupFaceurlData: {
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
    };
  },
  methods: {
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
        sourceType: [sourceType], //从相册选择
        success: (res) => {
          let path = res.tempFilePaths[0];
          if (sourceType === "camera") {
            path = this.convertLocalFileSystemURL(path);
          }
          path = this.formatPath(path);
          this.$im.uploadFile(this.operationID, path);
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
    confirm() {
      if (!this.groupInfo.groupName) {
        this.$toast("请输入群聊名称");
        return;
      }
      this.setGroupInfo();
    },
    setGroupInfo() {
      this.$store.commit("group/set_groupInfo", this.groupInfo);
      let addUserID = "";
      if (this.addUserID) {
        addUserID = `&addUserID=${this.addUserID}`;
      }
      uni.navigateTo({
        url: "@/memberList?pageStatus=create" + addUserID,
      });
    },
  },
  onLoad(param) {
    this.addUserID = param.addUserID || "";
  },
  computed: {
    ...mapGetters([
      "operationID",
      "userID",
      "uploadFileTimes",
      "uploadFileRes",
    ]),
  },
  watch: {
    uploadFileTimes() {
      this.groupInfo.faceURL = this.uploadFileRes;
    },
  },
};
</script>

<style lang="scss" scoped>
.edit {
  &-item {
    margin-top: 160rpx;
    padding: 0 84rpx;
    text-align: center;
  }
  &-group {
    color: #333333;
    .faceURL {
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: row;
      margin-bottom: 24rpx;
    }
    .content {
      padding: 18rpx 0;
      border-top: 2rpx solid #dfdfdf;
      border-bottom: 2rpx solid #dfdfdf;
      display: flex;
      flex-direction: row;
      align-items: center;
      .input {
        flex: 1;
        margin-left: 24rpx;
      }
    }
  }
  .btn-group {
    margin: 500rpx auto 0;
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 300rpx;
    .button {
      height: 68rpx;
    }
  }
}
</style>
