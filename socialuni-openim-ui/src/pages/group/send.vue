<template>
  <view class="send">
    <u-navbar title="群聊验证" autoBack>
      <u-button
        slot="right"
        type="primary"
        text="发送"
        size="mini"
        @click="confirm"
      />
    </u-navbar>
    <view class="statusBar"></view>
    <view class="content">
      <view class="label">发送入群申请</view>
      <view class="input">
        <u--textarea
          v-model="sendForm.reqMsg"
          placeholder=" "
          border="none"
          confirmType="done"
        />
      </view>
    </view>
  </view>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      groupID: "",
      userInfo: {
        groupId: "17396220460",
        nickname: "bbqa",
        faceURL:
          "https://echat-1302656840.cos.ap-chengdu.myqcloud.com/image_cropper_1647604182576.jpg",
        gender: 0,
        phoneNumber: "",
        birth: 0,
        email: "",
        createTime: 0,
        ex: "",
        attachedInfo: "",
      },
      groupInfo: {
        groupID: "13e2d8cda075d2f7ca464ce1f0c52ac2",
        groupName: "惹事",
        notification: "",
        introduction: "",
        faceURL: "",
        createTime: 1648820450,
        status: 0,
        creatorUserID: "18349115126",
        groupType: 0,
        ownerUserID: "18349115126",
        memberCount: 5,
        ex: "",
        attachedInfo: "",
      },
      sendForm: {
        reqMsg: "",
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
          let str = "";
          if (this.userInfo.attachedInfo) {
            str = `来自${this.userInfo.attachedInfo}的`;
          }
          this.sendForm.reqMsg = `我是${str}${this.userInfo.nickname}`;
        }
      });
      this.$im.getGroupsInfo(this.operationID, [this.groupID], (res) => {
        if (res.errCode !== 0) {
          this.$toast(res.errMsg);
        } else {
          let list = JSON.parse(res.data);
          this.groupInfo = list[0];
        }
      });
    },
    confirm() {
      this.$im.joinGroup(
        this.operationID,
        this.groupID,
        this.sendForm.reqMsg,
        (res) => {
          this.$toast(res.errMsg);
          if (res.errCode === 0) {
            setTimeout(() => {
              uni.navigateBack();
            }, 1000);
          }
        }
      );
    },
  },
  onLoad(param) {
    this.groupID = param.groupID;
    this.init();
  },
  computed: {
    ...mapGetters(["userID", "operationID"]),
  },
};
</script>

<style lang="scss" scoped>
.send {
  min-height: 100vh;
  background-color: #f6f6f6;
  .statusBar {
    height: 44px;
  }
  .label {
    margin: 20rpx 44rpx;
    font-size: 28rpx;
    color: #666666;
  }
  .input {
    background-color: #fff;
  }
}
</style>