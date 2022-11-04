<template>
  <view class="ContactUserCard" @click="toApplicationInfo">
    <Avatar
      class="Avatar"
      :src="fromFaceURL"
      :name="fromNickname"
      size="42px"
      @click="toUserInfo"
    />
    <view class="right">
      <view class="name">
        <text class="fromNickname">{{ fromNickname }}</text>
        <text class="reqMsg">{{ card.reqMsg || isFriend }}</text>
      </view>
      <view
        v-show="card.handleResult === 0 && type === 0"
        class="application"
        @click.stop="acceptFriend"
      >
        接受
      </view>
      <view v-show="card.handleResult === 0 && type === 1" class="refused">
        处理中
      </view>
      <view
        v-show="card.handleResult === 1"
        class="accepted"
        @click.stop="toConversation"
      >
        打招呼
      </view>
      <view v-show="card.handleResult === -1" class="refused"> 已拒绝 </view>
    </view>
  </view>
</template>

<script>
import Avatar from "@/components/Avatar.vue";
import { mapGetters } from "vuex";
export default {
  components: { Avatar },
  props: {
    card: { type: Object, default: () => {} },
    type: {
      type: Number,
      default: 0,
    },
  },
  data() {
    return {};
  },
  methods: {
    acceptFriend() {
      this.$im.acceptFriendApplication(
        this.operationID,
        {
          toUserID: this.fromUserID, // 申请者ID
          handleMsg: "", // 回复消息
        },
        (res) => {
          console.log(res);
          if (res.errCode === 0) {
            this.$toast("操作成功");
            this.$emit("refresh");
          } else {
            this.$toast(res.errMsg);
          }
        }
      );
    },
    toConversation() {
      uni.navigateTo({
        url:
          "/pages/conversation/index?sessionType=1&sourceID=" + this.fromUserID,
      });
    },
    toUserInfo() {
      uni.navigateTo({
        url: "/pages/friend/info?id=" + this.fromUserID,
      });
    },
    toApplicationInfo() {
      if (this.type === 0 && this.card.handleResult === 0) {
        this.$store.commit("contacts/set_friendNoticeItem", this.card);
        uni.navigateTo({
          url: "@/applicationInfo",
        });
      }
    },
  },
  computed: {
    ...mapGetters(["operationID"]),
    fromFaceURL() {
      if (this.type === 1) {
        return this.card.toFaceURL;
      }
      return this.card.fromFaceURL;
    },
    fromNickname() {
      if (this.type === 1) {
        return this.card.toNickname;
      }
      return this.card.fromNickname;
    },
    fromUserID() {
      if (this.type === 1) {
        return this.card.toUserID;
      }
      return this.card.fromUserID;
    },
    isFriend() {
      if (this.card.handleResult === 1) {
        return `你和${this.fromNickname}已经是好友啦!`;
      }
      return "";
    },
  },
};
</script>

<style lang="scss" scoped>
$pagePadding: 44rpx;
.ContactUserCard {
  padding: 0 $pagePadding;
  display: flex;
  align-items: center;
  background-color: #fff;
  .Avatar {
    flex-shrink: 0;
  }
  .right {
    overflow: hidden;
    box-sizing: border-box;
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 28rpx 0;
    margin-left: 36rpx;
    border-bottom: 2rpx solid #f1f1f1;
    .name {
      overflow: hidden;
      flex: 1;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      .fromNickname {
        color: #333333;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
      }
      .reqMsg {
        margin-top: 10rpx;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        font-size: 24rpx;
        color: #666666;
      }
    }
    .application,
    .accepted,
    .refused {
      flex-shrink: 0;
      margin-left: 12rpx;
      font-size: 24rpx;
      width: 88rpx;
      height: 44rpx;
      line-height: 44rpx;
      text-align: center;
    }
    .application {
      border-radius: 6rpx;
      border: 2rpx solid #418ae5;
      text-align: center;
      color: #418ae5;
    }
    .accepted {
      color: #418ae5;
    }
    .refused {
      color: #666;
    }
  }
}
</style>
