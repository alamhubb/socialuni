<template>
  <view class="ContactGroupCard" @click="toApplicationInfo">
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
        <text class="reqMsg desc">
          申请加入:
          <text class="color-blue" @click.stop="toConversation">
            {{ card.groupName }}
          </text>
        </text>
        <text class="reqMsg">申请理由:</text>
        <text class="reqMsg">{{ card.reqMsg }}</text>
      </view>
      <view
        v-show="card.handleResult === 0 && type === 0"
        class="application"
        @click.stop="acceptGroupApplication"
      >
        同意
      </view>
      <view v-show="card.handleResult === 0 && type === 1" class="refused">
        正在审核
      </view>
      <view v-show="card.handleResult === 1" class="accepted"> 已通过 </view>
      <view v-show="card.handleResult === -1" class="refused"> 未通过 </view>
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
    acceptGroupApplication() {
      this.$im.acceptGroupApplication(
        this.operationID,
        this.card.groupID,
        this.card.userID,
        "", // 回复消息
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
          "/pages/conversation/index?sessionType=2&sourceID=" +
          this.card.groupID,
      });
    },
    toUserInfo() {
      uni.navigateTo({
        url: "/pages/friend/info?id=" + this.card.userID,
      });
    },
    toApplicationInfo() {
      if (this.type === 0 && this.card.handleResult === 0) {
        this.$store.commit("contacts/set_groupNoticeItem", this.card);
        uni.navigateTo({
          url: "@/applicationGroupInfo",
        });
      }
    },
  },
  computed: {
    ...mapGetters(["operationID"]),
    fromFaceURL() {
      if (this.type === 1) {
        return this.card.userFaceURL;
      }
      return this.card.userFaceURL;
    },
    fromNickname() {
      if (this.type === 1) {
        return this.card.nickname;
      }
      return this.card.nickname;
    },
  },
};
</script>

<style lang="scss" scoped>
$pagePadding: 44rpx;
.ContactGroupCard {
  padding: 28rpx $pagePadding 0;
  display: flex;
  align-items: flex-start;
  background-color: #fff;
  .Avatar {
    flex-shrink: 0;
  }
  .right {
    overflow: hidden;
    box-sizing: border-box;
    flex: 1;
    display: flex;
    align-items: flex-start;
    justify-content: space-between;
    padding-bottom: 28rpx;
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
      .desc {
        margin-bottom: 24rpx;
      }
      .reqMsg {
        margin-top: 10rpx;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        font-size: 24rpx;
        color: #666666;
        .color-blue {
          color: #418ae5;
          margin-left: 12rpx;
        }
      }
    }
    .application,
    .accepted,
    .refused {
      flex-shrink: 0;
      margin-left: 12rpx;
      font-size: 24rpx;
      width: 100rpx;
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
