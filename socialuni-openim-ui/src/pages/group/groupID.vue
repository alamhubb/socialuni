<template>
  <view class="qrcode">
    <view class="statusBar"></view>
    <view class="qrcode-card">
      <view class="info">
        <Avatar
          class="Avatar"
          :src="groupInfo.faceURL"
          :name="groupInfo.groupName"
        />
        <text class="nickname">{{ groupInfo.groupName }}</text>
      </view>
      <view class="notice">搜索群聊ID号，立刻加入该群。</view>
      <view class="code">
        {{ groupInfo.groupID }}
      </view>
      <view class="btn">
        <u-button class="button" type="primary" size="mini" @click="copy">
          复制团队ID
        </u-button>
      </view>
    </view>
  </view>
</template>

<script>
import Avatar from "@/components/Avatar.vue";
import { mapGetters } from "vuex";
export default {
  components: { Avatar },
  data() {
    return {
      groupID: "",
      groupInfo: {
        groupID: "ba752ab976519b29a584fcdf55c7d514",
        groupName: "OpenIM",
        notification: "。。。。",
        introduction: "",
        faceURL:
          "https://open-im-online.rentsoft.cn/storage/openim/1651894363384809106-941881807460162701image_cropper_1651894362085.jpg",
        createTime: 1650955940,
        status: 0,
        creatorUserID: "18886138905",
        groupType: 0,
        ownerUserID: "18886138905",
        memberCount: 6,
        ex: "",
        attachedInfo: "",
      },
    };
  },
  methods: {
    init() {
      this.$im.getGroupsInfo(this.operationID, [this.groupID], (res) => {
        if (res.errCode === 0) {
          let list = JSON.parse(res.data);
          this.groupInfo = list[0];
        }
      });
    },
    copy() {
      uni.setClipboardData({
        data: this.groupInfo.groupID,
        success: () => {
          this.$toast("复制成功");
        },
      });
    },
  },
  onLoad(param) {
    this.groupID = param.groupID;
    // #ifdef APP-PLUS
    this.init();
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
      margin-bottom: 56rpx;
    }
    .code {
      font-size: 56rpx;
      font-weight: 500;
      color: #333333;
      letter-spacing: 12rpx;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
      padding-bottom: 16rpx;
      border-bottom: 2rpx solid #d2d2d2;
      margin: 0 96rpx;
    }
    .btn {
      margin: 180rpx auto 0;
      padding-bottom: 156rpx;
      .button {
        width: 460rpx;
        height: 88rpx;
      }
    }
  }
}
</style>