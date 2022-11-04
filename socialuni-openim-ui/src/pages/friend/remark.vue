<template>
  <view class="send">
    <u-navbar title="设置备注" autoBack fixed placeholder>
      <u-button
        slot="right"
        type="primary"
        text="保存"
        size="mini"
        @click="confirm"
      />
    </u-navbar>
    <view class="content">
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
        remark: "",
      },
    };
  },
  methods: {
    init() {
      this.$im.getUsersInfo(this.operationID, [this.userID], (res) => {
        if (res.errCode !== 0) {
          this.$toast(res.errMsg);
        } else {
          let list = JSON.parse(res.data);
          let item = list[0];
          this.friendInfo = item;
          this.sendForm.remark = this.friendInfo.friendInfo.remark;
        }
      });
    },
    confirm() {
      console.log(this.sendForm.remark);
      this.$im.setFriendRemark(
        this.operationID,
        {
          toUserID: this.userID, // 用户ID
          remark: this.sendForm.remark, // 备注
        },
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
    this.userID = param.userID;
    this.init();
  },
  computed: {
    ...mapGetters(["operationID"]),
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