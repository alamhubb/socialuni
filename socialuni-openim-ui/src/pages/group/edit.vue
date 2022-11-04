<template>
  <view class="edit">
    <view class="statusBar"></view>
    <u-navbar class="navbar" :title="pageTitle" autoBack />
    <view class="edit-item edit-group" v-show="!isEditUser">
      <view class="title">修改群聊名称</view>
      <view class="des">修改群聊名称后，将在群内通知其他成员。</view>
      <view class="content">
        <Avatar
          class="faceURL"
          :src="groupInfo.faceURL"
          :name="groupInfo.groupName"
          size="42px"
        />
        <view class="input">
          <u--input
            placeholder=" "
            border="none"
            clearable
            v-model="groupInfo.groupName"
          />
        </view>
      </view>
    </view>
    <view class="edit-item edit-group" v-show="isEditUser">
      <view class="title">我在群里的昵称</view>
      <view class="des">
        昵称修改后，只会在此群内显示，群内成员都可以看见。
      </view>
      <view class="content">
        <Avatar
          class="faceURL"
          :src="groupInfo.faceURL"
          :name="groupInfo.groupName"
          size="42px"
        />
        <view class="input">
          <u--input
            placeholder=" "
            border="none"
            clearable
            v-model="userInfo.nickname"
          />
        </view>
      </view>
    </view>
    <view class="btn-group">
      <u-button
        v-show="!isEditUser"
        class="button"
        type="primary"
        size="mini"
        @click="editGroupName"
      >
        完成
      </u-button>
      <u-button
        v-show="isEditUser"
        class="button"
        type="primary"
        size="mini"
        @click="editNickname"
      >
        完成
      </u-button>
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
      paramUserID: "",
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
      userInfo: {
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
      if (this.isEditUser) {
        this.$im.getGroupMembersInfo(
          this.operationID,
          this.groupID,
          [this.paramUserID],
          (res) => {
            if (res.errCode === 0) {
              let data = JSON.parse(res.data);
              this.userInfo = data[0];
            }
          }
        );
      }
    },
    editGroupName() {
      if (!this.groupInfo.groupName) {
        this.$toast("请输入群组名称");
        return;
      }
      this.setGroupInfo();
    },
    setGroupInfo() {
      const { groupName, notification, introduction, faceURL, ex } =
        this.groupInfo;
      this.$im.setGroupInfo(
        this.operationID,
        this.groupID,
        { groupName, notification, introduction, faceURL, ex },
        (res) => {
          if (res.errCode === 0) {
            this.$toast("修改成功");
            this.$store.commit("message/infoUpdateTimes");
          } else {
            this.$toast(res.errMsg);
          }
        }
      );
    },
    editNickname() {
      if (!this.userInfo.nickname) {
        this.$toast("请输入昵称");
        return;
      }
      this.$im.setGroupMemberNickname(
        this.operationID,
        this.groupID,
        this.paramUserID,
        this.userInfo.nickname,
        (res) => {
          if (res.errCode === 0) {
            this.$toast("修改成功");
            this.$store.commit("message/infoUpdateTimes");
          } else {
            this.$toast(res.errMsg);
          }
        }
      );
    },
  },
  onLoad(param) {
    this.groupID = param.groupID;
    this.paramUserID = param.userID;
    // #ifdef APP-PLUS
    this.init();
    // #endif
  },
  computed: {
    ...mapGetters(["operationID", "userID", "infoUpdateTimes"]),
    pageTitle() {
      return "";
    },
    isEditUser() {
      return this.paramUserID === "" ? false : true;
    },
    isSelf() {
      return this.paramUserID === this.userID ? true : false;
    },
  },
  watch: {
    infoUpdateTimes() {
      this.init();
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
    .title {
      font-size: 40rpx;
      font-weight: 500;
      margin-bottom: 20rpx;
    }
    .des {
      font-size: 30rpx;
      margin-bottom: 74rpx;
    }
    .content {
      padding: 18rpx 0;
      border-top: 2rpx solid #dfdfdf;
      border-bottom: 2rpx solid #dfdfdf;
      display: flex;
      flex-direction: row;
      align-items: center;
      .faceURL {
        flex-shrink: 0;
      }
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