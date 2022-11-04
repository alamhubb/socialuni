<template>
  <view class="send">
    <u-navbar title="好友验证" autoBack fixed placeholder>
      <u-button
        slot="right"
        type="primary"
        text="发送"
        size="mini"
        @click="confirm"
      />
    </u-navbar>
    <view class="content">
      <view class="label">发送添加好友申请</view>
      <view class="input">
        <u--textarea
          v-model="sendForm.reqMsg"
          placeholder=" "
          border="none"
          confirmType="done"
        />
      </view>
      <view class="label">备注名</view>
      <view class="input">
        <u--input
          placeholder=" "
          border="none"
          v-model="sendForm.remark"
          :customStyle="{ padding: '18rpx' }"
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
      userID: "",
      userInfo: {
        userID: "17396220460",
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
      friendInfo: {
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
      sendForm: {
        reqMsg: "",
        remark: "",
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
      this.$im.getUsersInfo(this.operationID, [this.userID], (res) => {
        if (res.errCode !== 0) {
          this.$toast(res.errMsg);
        } else {
          let list = JSON.parse(res.data);
          let item = list[0];
          this.friendInfo = item;
          console.log(this.friendInfo);
          this.sendForm.remark = this.friendInfo.friendInfo
            ? this.friendInfo.friendInfo.remark
            : "";
        }
      });
    },
    setRemark() {
      if (this.isFriend) {
        this.$im.setFriendRemark(this.operationID, {
          toUserID: this.userID, // 用户ID
          remark: this.sendForm.remark, // 备注
        });
      }
    },
    confirm() {
      this.setRemark();
      this.$im.addFriend(
        this.operationID,
        {
          toUserID: this.userID, // 用户ID
          reqMsg: this.sendForm.reqMsg, // 验证消息
        },
        (res) => {
          this.$toast(res.errMsg);
          if (res.errCode === 0) {
            setTimeout(() => {
              uni.navigateBack();
            }, 1000);
          } else {
            this.$toast(res.errMsg);
          }
        }
      );
    },
  },
  computed: {
    ...mapGetters(["operationID"]),
    isFriend() {
      return this.friendInfo.friendInfo ? true : false;
    },
  },
  onLoad(param) {
    this.userID = param.userID;
    this.init();
  },
};
</script>

<style lang="scss" scoped>
.send {
  min-height: 100vh;
  background-color: #f6f6f6;
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