<template>
  <view class="info">
    <view class="top">
      <Avatar :src="userInfo.publicInfo.faceURL" :name="nickname" />
      <view class="name">
        <view class="nickname">{{ nickname }}</view>
        <!-- <view class="userStatus">手机在线</view> -->
      </view>
    </view>
    <view class="bottom">
      <view class="bottom-item" v-show="isFriend" @click="toRemark">
        <text class="text">备注</text>
        <u-icon class="icon" size="22" name="arrow-right" color="#999" />
      </view>
      <view class="bottom-item" @click="toUserId">
        <text class="text">ID号</text>
        <u-icon class="icon" size="22" name="arrow-right" color="#999" />
      </view>
      <view class="bottom-item" @click="shareCard" v-if="isFriend">
        <text class="text">把他推荐给朋友</text>
        <u-icon class="icon" size="22" name="arrow-right" color="#999" />
      </view>
      <view class="bottom-item" v-if="isFriend">
        <text class="text">加入黑名单</text>
        <u-switch v-model="blackStatus" @change="setUserBlack" />
      </view>
      <view class="bottom-item" v-if="isManage">
        <text class="text">禁言</text>
        <view class="right">
          <text class="des">{{ muteTime }}</text>
          <u-switch
            v-show="isManage"
            v-model="datePicker.isMute"
            @change="muteSwitchChange"
          />
          <u-icon
            v-show="isManage"
            @click="datePickerShow"
            class="icon"
            size="22"
            name="arrow-right"
            color="#999"
          />
        </view>
      </view>
    </view>
    <view class="deleteUser" v-if="isFriend" @click="delPopShow = true">
      解除好友关系
    </view>
    <view class="operation">
      <view class="operation-item" @click="toConversation" v-if="!isBlack">
        <image class="image" src="@/static/images/friend/message.png" />
        <text class="text text1">发消息</text>
      </view>
      <view class="operation-item" v-else>
        <image
          class="image"
          src="@/static/images/friend/message-disabled.png"
        />
        <text class="text text-disabled">发消息</text>
      </view>
      <view class="operation-item" v-if="isFriend">
        <image class="image" src="@/static/images/friend/phone.png" />
        <text class="text text1">电话</text>
      </view>
      <view class="operation-item" v-else>
        <image class="image" src="@/static/images/friend/phone-disabled.png" />
        <text class="text text-disabled">电话</text>
      </view>
      <view class="operation-item" v-if="!isFriend" @click="toSend">
        <image class="image" src="@/static/images/friend/add.png" />
        <text class="text text2">添加好友</text>
      </view>
    </view>
    <u-modal
      :show="blackPopShow"
      showCancelButton
      @confirm="confirmBlack"
      @cancel="cancelBlack"
    >
      <view class="blackPop">
        确认对
        <text class="color-blue">
          {{ nickname }}
        </text>
        进行拉黑?
      </view>
    </u-modal>
    <u-modal
      :show="delPopShow"
      showCancelButton
      @confirm="confirmDel"
      @cancel="delPopShow = false"
    >
      确认删除好友?
    </u-modal>
    <u-datetime-picker
      :show="datePicker.show"
      :minDate="datePicker.minDate"
      v-model="datePicker.value"
      mode="datetime"
      @cancel="datePickerHide"
      @confirm="setGroupMemberMute"
    />
  </view>
</template>

<script>
import Avatar from "@/components/Avatar.vue";
import { mapGetters } from "vuex";
import { parseTime } from "@/utils/index";
export default {
  components: { Avatar },
  data() {
    return {
      userID: "",
      groupID: "",
      userInfo: {
        publicInfo: {
          userID: "18381415165",
          nickname: "mm",
          faceURL:
            "https://echat-1302656840.cos.ap-chengdu.myqcloud.com/rc-upload-1650949055268-5icon.png",
          gender: 1,
        },
        friendInfo: {
          ownerUserID: "17396220460",
          userID: "18381415165",
          remark: "",
          createTime: 1647569855,
          addSource: 0,
          operatorUserID: "18381415165",
          nickname: "mm",
          faceURL:
            "https://echat-1302656840.cos.ap-chengdu.myqcloud.com/rc-upload-1650949055268-5icon.png",
          gender: 1,
          phoneNumber: "18381415165",
          birth: 0,
          email: "",
          ex: "",
          attachedInfo: "",
        },
        blackInfo: null,
      },
      groupUserInfo: {
        groupID: "59ee812e81a02c7446851c6a95626c3b",
        userID: "17396220460",
        nickname: "blooming",
        faceURL: "ic_avatar_02",
        roleLevel: 1,
        joinTime: 1655135855,
        joinSource: 0,
        muteEndTime: 0,
        operatorUserID: "17726420827",
        ex: "",
        attachedInfo: "",
      },
      blackStatus: false,
      blackPopShow: false,
      delPopShow: false,
      datePicker: {
        isMute: false,
        show: false,
        minDate: null,
        now: null,
        value: "",
      },
    };
  },
  methods: {
    muteSwitchChange(v) {
      if (v) {
        let now = new Date().getTime();
        this.datePicker.minDate = now;
        this.datePicker.now = now;
        this.datePickerShow();
      } else {
        this.changeGroupMemberMute();
      }
    },
    datePickerShow() {
      if (this.datePicker.isMute) {
        this.datePicker.show = true;
      }
    },
    datePickerHide() {
      this.datePicker.show = false;
      if (this.groupUserInfo.muteEndTime === 0) {
        this.datePicker.isMute = false;
      }
    },
    setGroupMemberMute({ value }) {
      let mutedSeconds = value;
      mutedSeconds = value - this.datePicker.now;
      mutedSeconds = parseInt(mutedSeconds / 1000);
      mutedSeconds = mutedSeconds < 0 ? 0 : mutedSeconds;
      this.changeGroupMemberMute(mutedSeconds);
      this.datePicker.show = false;
    },
    changeGroupMemberMute(mutedSeconds = 0) {
      this.$im.changeGroupMemberMute(
        this.operationID,
        this.groupID,
        this.userID,
        mutedSeconds,
        (res) => {
          if (res.errCode !== 0) {
            this.datePicker.isMute = false;
            this.$toast(res.errMsg);
          } else if (mutedSeconds === 0) {
            this.$toast("取消禁言成功");
            this.getGroupMembersInfo();
          } else if (mutedSeconds > 0) {
            this.$toast("禁言成功");
            this.getGroupMembersInfo();
          }
        }
      );
    },
    getUsersInfo() {
      this.$im.getUsersInfo(this.operationID, [this.userID], (res) => {
        if (res.errCode !== 0) {
          this.$toast(res.errMsg);
        } else {
          let list = JSON.parse(res.data);
          let item = list[0];
          this.userInfo = item;
          this.blackStatus = this.isBlack;
        }
      });
    },
    getGroupMembersInfo() {
      if (this.groupID) {
        this.$im.getGroupMembersInfo(
          this.operationID,
          this.groupID,
          [this.$store.getters.userID],
          (res) => {
            if (res.errCode === 0) {
              let data = JSON.parse(res.data);
              this.groupUserInfo = data[0];
              this.datePicker.isMute =
                this.groupUserInfo.muteEndTime === 0 ? false : true;
            }
          }
        );
      }
    },
    toConversation() {
      uni.navigateTo({
        url: "/pages/conversation/index?sessionType=1&sourceID=" + this.userID,
      });
    },
    toSend() {
      uni.navigateTo({
        url: "@/send?userID=" + this.userInfo.publicInfo.userID,
      });
    },
    toRemark() {
      uni.navigateTo({
        url: "@/remark?userID=" + this.userInfo.publicInfo.userID,
      });
    },
    toUserId() {
      uni.navigateTo({
        url: "@/userId?userID=" + this.userInfo.publicInfo.userID,
      });
    },
    shareCard() {
      const res = this.$im.createCardMessage(this.operationID, this.userInfo);
      this.$store.commit("message/set_operationMessageItem", res);
      uni.navigateTo({
        url: "/pages/forward/index",
      });
    },
    setUserBlack() {
      if (this.blackStatus !== this.isBlack && this.blackStatus) {
        this.blackPopShow = true;
      } else if (this.blackStatus !== this.isBlack && !this.blackStatus) {
        this.confirmBlack();
      } else {
        this.blackPopShow = false;
      }
    },
    cancelBlack() {
      this.blackPopShow = false;
      this.blackStatus = !this.blackStatus;
    },
    confirmBlack() {
      if (this.blackStatus) {
        this.$im.addBlack(
          this.operationID,
          this.userInfo.publicInfo.userID,
          (res) => {
            this.blackPopShow = false;
            if (res.errCode !== 0) {
              this.$toast(res.errMsg);
              this.blackStatus = !this.blackStatus;
            }
          }
        );
      } else {
        this.$im.removeBlack(
          this.operationID,
          this.userInfo.publicInfo.userID,
          (res) => {
            this.blackPopShow = false;
            if (res.errCode !== 0) {
              this.$toast(res.errMsg);
              this.blackStatus = !this.blackStatus;
            }
          }
        );
      }
    },
    confirmDel() {
      this.delPopShow = false;
      this.$im.deleteFriend(
        this.operationID,
        this.userInfo.publicInfo.userID,
        (res) => {
          this.$toast(res.errMsg);
          if (res.errCode === 0) {
            this.$store.commit("message/set_indexMessageTimes");
            setTimeout(() => {
              uni.navigateBack();
            }, 1000);
          }
        }
      );
    },
  },
  onLoad(param) {
    this.userID = param.id || "";
    this.groupID = param.groupID || "";
    this.getUsersInfo();
    this.getGroupMembersInfo();
  },
  computed: {
    ...mapGetters(["operationID", "frinendInfoChangeTimes"]),
    allTimes() {
      return this.frinendInfoChangeTimes;
    },
    isFriend() {
      return this.userInfo.friendInfo !== null;
    },
    nickname() {
      if (this.userInfo.friendInfo) {
        return (
          this.userInfo.friendInfo.remark || this.userInfo.friendInfo.nickname
        );
      }
      return this.userInfo.publicInfo.nickname;
    },
    isBlack() {
      return this.userInfo.blackInfo !== null;
    },
    isManage() {
      return (
        this.groupID &&
        this.groupUserInfo &&
        (this.groupUserInfo.roleLevel === 2 ||
          this.groupUserInfo.roleLevel === 3)
      );
    },
    muteTime() {
      let t = "";
      if (
        this.groupID &&
        this.groupUserInfo &&
        this.groupUserInfo.muteEndTime !== 0
      ) {
        t = parseTime(
          new Date(this.groupUserInfo.muteEndTime),
          "{y}-{m}-{d} {h}:{i}"
        );
        t += "解禁";
      }
      return t;
    },
  },
  watch: {
    allTimes() {
      this.getUsersInfo();
      this.getGroupMembersInfo();
    },
  },
};
</script>
<style lang="scss" scoped>
.info {
  background: #f6f6f6;
  min-height: 100vh;
  .top {
    padding: 74rpx 42rpx;
    margin-bottom: 24rpx;
    display: flex;
    align-items: center;
    background-color: #fff;
    .name {
      margin-left: 36rpx;
      .nickname {
        font-size: 36rpx;
        margin-right: 24rpx;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
      }
      .userStatus {
        font-size: 24rpx;
        padding-left: 20rpx;
        position: relative;
        white-space: nowrap;
        &::before {
          content: "";
          background-color: #10cc64;
          width: 12rpx;
          height: 12rpx;
          border-radius: 50%;
          position: absolute;
          left: 0;
          top: 50%;
          transform: translateY(-50%);
        }
      }
    }
  }
  .bottom {
    padding: 0 44rpx;
    background-color: #fff;
    &-item {
      padding: 30rpx 0;
      display: flex;
      align-items: center;
      justify-content: space-between;
      .right {
        display: flex;
        flex-direction: row;
        align-items: center;
        .des {
          margin-right: 12rpx;
          font-size: 24rpx;
          color: #999;
        }
        .icon {
          margin-left: 12rpx;
        }
      }
    }
  }
  .deleteUser {
    padding: 30rpx 44rpx;
    background-color: #fff;
    margin-top: 44rpx;
    font-size: 36rpx;
    color: #d9350d;
    text-align: center;
  }
  .operation {
    position: fixed;
    left: 0;
    bottom: 100rpx;
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: space-around;
    &-item {
      display: flex;
      flex-direction: column;
      justify-content: center;
      text-align: center;
      .image {
        width: 100rpx;
        height: 100rpx;
      }
      .text {
        font-size: 28rpx;
        margin-top: 12rpx;
        &-disabled {
          color: #b8b8b8;
        }
      }
      .text1 {
        color: #1d6bed;
        // color: #b8b8b8;x
      }
      .text2 {
        color: #1d6bed;
      }
    }
  }
  .blackPop {
    .color-blue {
      color: #1b72ec;
    }
  }
}
</style>
