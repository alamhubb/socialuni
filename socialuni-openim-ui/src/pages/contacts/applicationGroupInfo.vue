<template>
  <view class="applicationInfo">
    <view class="userInfo">
      <view class="top" @click="toUserInfo">
        <Avatar
          class="Avatar"
          :src="groupNoticeItem.userFaceURL"
          :name="groupNoticeItem.nickname"
          size="42px"
        />
        <view class="right">
          <text class="name">{{ groupNoticeItem.nickname }}</text>
          <u-icon name="arrow-right" size="16" color="#666" />
        </view>
      </view>
      <view class="msg">
        <text class="reqMsg desc">
          申请加入:
          <text class="color-blue" @click.stop="toConversation">
            {{ groupNoticeItem.groupName }}
          </text>
        </text>
        <text class="reqMsg">申请理由:</text>
        <text class="reqMsg">{{ groupNoticeItem.reqMsg }}</text>
      </view>
    </view>
    <view class="accept" @click="acceptGroupApplication"> 通过入群申请 </view>
    <view class="refuse" @click="refuse.show = true">拒绝入群申请</view>
    <view class="others">
      <text @click="blackPop.show = true">
        {{ isBlack ? "移除" : "加入" }}黑名单</text
      >
      <text class="border"></text>
      <text>投诉</text>
    </view>
    <u-popup
      :show="refuse.show"
      class="refusePop commonPop"
      mode="center"
      round="6"
    >
      <view class="refusePop-content commonPop-content">
        <view class="refusePop-content-top commonPop-content-top">
          <view class="title">回复</view>
          <u--textarea
            v-model="refuse.content"
            placeholder=" "
            confirmType="done"
          />
        </view>
        <view class="btnGroup">
          <view class="btnGroup-button" @click="refuse.show = false">取消</view>
          <view class="btnGroup-button" @click="refuseGroupApplication">
            发送
          </view>
        </view>
      </view>
    </u-popup>
    <u-modal
      :show="blackPop.show"
      showCancelButton
      @confirm="confirmBlack"
      @cancel="blackPop.show = false"
    >
      <view>
        确认对
        <text class="color-blue">{{ groupNoticeItem.nickname }}</text>
        {{ isBlack ? "取消" : "进行" }}拉黑?
      </view>
    </u-modal>
  </view>
</template>

<script>
import { mapGetters } from "vuex";
import Avatar from "@/components/Avatar.vue";
export default {
  components: { Avatar },
  data() {
    return {
      refuse: {
        content: "",
        show: false,
      },
      friendInfo: {
        publicInfo: {
          userID: "870517699",
          nickname: "小红",
          faceURL:
            "https://storage-online.rentsoft.cn/openim/1655532712536247948-8273290538659802269image_cropper_1655532711426.jpg",
        },
        friendInfo: {
          ownerUserID: "3798435312",
          userID: "870517699",
          remark: "",
          createTime: 1655532611,
          addSource: 0,
          operatorUserID: "3798435312",
          nickname: "小红",
          faceURL:
            "https://storage-online.rentsoft.cn/openim/1655532712536247948-8273290538659802269image_cropper_1655532711426.jpg",
          gender: 0,
          phoneNumber: "18886138905",
          birth: 0,
          email: "",
          ex: "",
          attachedInfo: "",
        },
        blackInfo: null,
      },
      blackPop: {
        show: false,
      },
    };
  },
  methods: {
    init() {
      this.$im.getUsersInfo(
        this.operationID,
        [this.groupNoticeItem.fromUserID],
        (res) => {
          if (res.errCode === 0) {
            const data = JSON.parse(res.data);
            this.friendInfo = data[0];
          }
        }
      );
    },
    init2() {
      //#ifdef APP-PLUS
      this.$store.dispatch("contacts/get_groupNoticeList", this.$im);
      this.$store.dispatch("contacts/get_selfGroupNoticeList", this.$im);
      //#endif
    },
    acceptGroupApplication() {
      this.$im.acceptGroupApplication(
        this.operationID,
        this.groupNoticeItem.groupID,
        this.groupNoticeItem.userID,
        this.refuse.content, // 回复消息
        (res) => {
          console.log(res);
          if (res.errCode === 0) {
            this.$toast("已同意");
            this.init2()
            setTimeout(() => {
              uni.navigateBack();
            }, 1000);
          } else {
            this.$toast(res.errMsg);
          }
        }
      );
    },
    refuseGroupApplication() {
      this.$im.refuseGroupApplication(
        this.operationID,
        this.groupNoticeItem.groupID,
        this.groupNoticeItem.userID,
        this.refuse.content,
        (res) => {
          console.log(res);
          if (res.errCode === 0) {
            this.$toast("已拒绝");
            this.init2()
            setTimeout(() => {
              uni.navigateBack();
            }, 1000);
          } else {
            this.$toast(res.errMsg);
          }
        }
      );
    },
    toUserInfo() {
      uni.navigateTo({
        url: "/pages/friend/info?id=" + this.groupNoticeItem.userID,
      });
    },
    toConversation() {
      uni.navigateTo({
        url:
          "/pages/conversation/index?sessionType=2&sourceID=" +
          this.groupNoticeItem.groupID,
      });
    },
    confirmBlack() {
      this.blackPop.show = false;
      const fn = (res) => {
        if (res.errCode === 0) {
          this.$toast("操作成功");
          this.init();
        } else {
          this.$toast(res.errMsg);
        }
      };
      if (this.isBlack) {
        this.$im.removeBlack(
          operationID,
          this.groupNoticeItem.fromUserID,
          (res) => {
            fn(res);
          }
        );
      } else {
        this.$im.addBlack(
          operationID,
          this.groupNoticeItem.fromUserID,
          (res) => {
            fn(res);
          }
        );
      }
    },
  },
  onLoad() {
    this.init();
  },
  computed: {
    ...mapGetters(["operationID", "groupNoticeItem", "userID"]),
    isBlack() {
      return this.friendInfo && this.friendInfo.blackInfo === null
        ? false
        : true;
    },
  },
};
</script>

<style lang="scss" scoped>
$pagePadding: 44rpx;
.applicationInfo {
  background-color: #f8f8f8;
  min-height: 100vh;
  .userInfo {
    background-color: #fff;
    padding: 74rpx $pagePadding 30rpx;
    .top {
      display: flex;
      align-items: center;
      .Avatar {
        margin-right: 36rpx;
      }
      .right {
        display: flex;
        align-items: center;
        justify-content: space-between;
        flex: 1;
        overflow: hidden;
        .name {
          font-size: 40rpx;
          font-weight: 500;
          color: #333333;
        }
      }
    }
    .msg {
      margin-top: 66rpx;
      min-height: 248rpx;
      background: #eee;
      border-radius: 12px;
      padding: 36rpx;
      box-sizing: border-box;
      font-size: 28rpx;
      color: #333;
      display: flex;
      flex-direction: column;
      .desc {
        margin-bottom: 24rpx;
      }
      .reqMsg {
        margin-top: 10rpx;
        font-size: 24rpx;
        color: #666666;
        .color-blue {
          color: #418ae5;
          margin-left: 12rpx;
        }
      }
    }
  }
  .accept,
  .refuse {
    height: 110rpx;
    line-height: 110rpx;
    background-color: #ffffff;
    font-size: 36rpx;
    font-weight: 500;
    text-align: center;
  }
  .accept {
    color: #1b61d6;
    margin-top: 48rpx;
  }
  .refuse {
    color: #999;
    margin-top: 22rpx;
  }
  .others {
    margin-top: 140rpx;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 28rpx;
    color: #1b61d6;
    .border {
      margin: 0 12rpx;
      width: 2rpx;
      height: 30rpx;
      background-color: #1b61d6;
    }
  }
  .commonPop {
    &-content {
      width: 650rpx;
      border-radius: 12rpx;
      overflow: hidden;
      &-top {
        padding: 32rpx 40rpx;
        box-sizing: border-box;
      }
    }
  }
  .refusePop {
    &-content {
      &-top {
        .title {
          margin-bottom: 12rpx;
        }
      }
      .btnGroup {
        display: flex;
        border-top: 1rpx solid rgba(151, 151, 151, 0.5);
        &-button {
          width: 50%;
          height: 92rpx;
          line-height: 92rpx;
          text-align: center;
          color: #333;
          &:nth-of-type(2) {
            background: #e8f2ff;
            border-left: 1rpx solid rgba(151, 151, 151, 0.5);
            color: #1b72ec;
          }
        }
      }
    }
  }
  .color-blue {
    color: #1b61d6;
  }
}
</style>