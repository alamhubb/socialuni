<template>
  <view class="MessageShareUserCard" @click.stop="toInfo">
    <view class="header">
      <Avatar
        class="faceURL"
        :src="content.faceURL"
        :name="content.nickname"
        size="42px"
      />
      <text class="nickname">{{ content.nickname }}</text>
    </view>
    <view class="footer">名片</view>
  </view>
</template>

<script>
import Avatar from "@/components/Avatar.vue";
export default {
  components: { Avatar },
  props: {
    card: {
      type: Object,
      default: () => {},
    },
  },
  methods: {
    toInfo() {
      uni.navigateTo({
        url: "/pages/friend/info?id=" + this.content.userID,
      });
    },
  },
  computed: {
    content() {
      let c = this.card.content;
      if (c) {
        c = JSON.parse(c);
        c = c.publicInfo || c;
      }
      return c || {};
    },
  },
};
</script>

<style lang="scss" scoped>
.MessageShareUserCard {
  width: 400rpx;
  background-color: #fbfbfb;
  box-shadow: 0 4rpx 8rpx 0 rgba(0, 0, 0, 0.1);
  border-radius: 12rpx;
  border: 2rpx solid #ececec;
  .header {
    border-bottom: 2rpx solid #e9e9e9;
    padding: 24rpx 32rpx;
    display: flex;
    align-items: center;
    .faceURL {
      margin-right: 16rpx;
    }
    .nickname {
      font-size: 32rpx;
      color: #333333;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }
  }
  .footer {
    font-size: 22rpx;
    color: #999999;
    padding: 6rpx 52rpx;
  }
}
</style>