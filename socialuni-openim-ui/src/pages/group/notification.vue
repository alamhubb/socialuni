<template>
  <view class="notification">
    <u-navbar class="navbar" title="群公告" autoBack fixed placeholder>
      <view class="navbar-right" slot="right">
        <view
          class="navbar-right-item"
          v-show="canDoEdit && !isEdit"
          @click="isEdit = true"
        >
          <image class="image" src="@/static/images/friend/edit.png" />
        </view>
        <view
          class="navbar-right-item"
          v-show="canDoEdit && isEdit"
          @click="setGroupInfo"
        >
          <u-button type="primary" size="mini">完成</u-button>
        </view>
      </view>
    </u-navbar>
    <view class="content">
      <u--textarea
        :disabled="!isEdit"
        class="textarea"
        v-model="groupInfo.notification"
        placeholder="请编辑群公告"
        confirmType="done"
      />
    </view>
  </view>
</template>

<script>
import { mapGetters } from "vuex";
export default {
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
        creatorUserID: "17396220460",
        groupType: 0,
        ownerUserID: "17396220460",
        memberCount: 6,
        ex: "",
        attachedInfo: "",
      },
      memberInfo: {
        groupID: "857447475",
        userID: "3798435312",
        nickname: "OpenIM-Blooming",
        faceURL: "ic_avatar_06",
        roleLevel: 2,
        joinTime: 1655970064,
        joinSource: 0,
        muteEndTime: 0,
        operatorUserID: "3798435312",
        ex: "",
        attachedInfo: "",
      },
      isEdit: false,
    };
  },
  methods: {
    init() {
      this.getGroupsInfo();
      this.getGroupMembersInfo();
    },
    getGroupsInfo() {
      this.$im.getGroupsInfo(this.operationID, [this.groupID], (res) => {
        if (res.errCode === 0) {
          let list = JSON.parse(res.data);
          this.groupInfo = list[0];
        }
      });
    },
    getGroupMembersInfo() {
      this.$im.getGroupMembersInfo(
        this.operationID,
        this.groupID,
        [this.userID],
        (res) => {
          if (res.errCode === 0) {
            let list = JSON.parse(res.data);
            this.memberInfo = list[0];
            console.log(this.memberInfo);
          }
        }
      );
    },
    setGroupInfo() {
      const { groupName, introduction, faceURL, ex, notification } =
        this.groupInfo;
      const groupInfo = {
        notification,
        groupName,
        introduction,
        faceURL,
        ex,
      };
      this.$im.setGroupInfo(
        this.operationID,
        this.groupID,
        groupInfo,
        (res) => {
          if (res.errCode === 0) {
            this.$toast("修改成功");
          } else {
            this.$toast("请稍后再试");
          }
        }
      );
    },
  },
  computed: {
    ...mapGetters(["operationID", "userID"]),
    canDoEdit() {
      return this.memberInfo.roleLevel === 2 || this.memberInfo.roleLevel === 3
        ? true
        : false;
    },
  },
  onLoad(params) {
    this.groupID = params.groupID;
    // #ifdef APP-PLUS
    this.init();
    // #endif
  },
};
</script>
<style lang="scss" scoped>
.notification {
  background-color: #f8f8f8;
  min-height: 100vh;
  .navbar {
    &-right {
      &-item {
        .image {
          width: 44rpx;
          height: 44rpx;
        }
      }
    }
  }
  .content {
    background-color: #fff;
    margin-top: 40rpx;
    .textarea {
      background-color: #fff;
      border: none;
    }
  }
}
</style>