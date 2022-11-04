<template>
  <view class="blackList">
    <view class="blackList-empty" v-show="!blackList.length">
      <image class="image" src="@/static/images/user/black-empty.png" />
      <text class="text">你将不再接收黑名单用户的任何消息。</text>
    </view>
    <view class="blackList-content">
      <view
        class="blackList-content-item"
        v-for="item in blackList"
        :key="item.userID"
      >
        <Avatar
          class="Avatar"
          :src="item.faceURL"
          :name="item.nickname"
          size="42px"
        />
        <view class="right">
          <view class="nickname">{{ item.nickname }}</view>
          <view class="btn" @click="showModal(item)">移除</view>
        </view>
      </view>
    </view>
    <u-modal
      :show="blackModal.show"
      showCancelButton
      @confirm="confirm"
      @cancel="blackModal.show = false"
    >
      <view class="modal">
        <view class="modal-title2">确定将好友移除黑名单吗?</view>
      </view>
    </u-modal>
  </view>
</template>

<script>
import Avatar from "@/components/Avatar.vue";
import { mapGetters } from "vuex";
export default {
  components: { Avatar },
  data() {
    return {
      blackList: [
        {
          ownerUserID: "3798435312",
          userID: "565214579",
          nickname: "弔儿子",
          faceURL: "ic_avatar_06",
          gender: 1,
          createTime: 0,
          addSource: 0,
          operatorUserID: "",
          ex: "",
          attachedInfo: "",
        },
        {
          ownerUserID: "3798435312",
          userID: "5652145339",
          nickname: "弔儿子2",
          faceURL: "ic_avatar_06",
          gender: 1,
          createTime: 0,
          addSource: 0,
          operatorUserID: "",
          ex: "",
          attachedInfo: "",
        },
      ],
      blackModal: {
        show: false,
        userInfo: {},
      },
    };
  },
  methods: {
    init() {
      this.$im.getBlackList(this.operationID, (res) => {
        if (res.errCode === 0) {
          const list = JSON.parse(res.data);
          this.blackList = list;
        } else {
          this.$toast(res.errMsg);
        }
      });
    },
    showModal(item) {
      this.blackModal.userInfo = item;
      this.blackModal.show = true;
    },
    confirm() {
      this.$im.removeBlack(
        this.operationID,
        this.blackModal.userInfo.userID,
        (res) => {
          if (res.errCode === 0) {
            this.$toast("操作成功");
            this.init();
          } else {
            this.$toast(res.errMsg);
          }
        }
      );
      this.blackModal.show = false;
    },
  },
  computed: {
    ...mapGetters(["operationID"]),
  },
  onLoad() {
    this.init();
  },
};
</script>
<style lang="scss" scoped>
.blackList {
  min-height: 100vh;
  background-color: #f8f8f8;
  padding-top: 24rpx;
  &-empty {
    padding-top: 400rpx;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    text-align: center;
    .image {
      width: 256rpx;
      height: 200rpx;
    }
    .text {
      font-size: 16px;
      color: #999999;
      margin-top: 40rpx;
    }
  }
  &-content {
    background-color: #fff;
    &-item {
      display: flex;
      align-items: center;
      padding-left: 44rpx;
      &:last-of-type {
        .right {
          border-bottom: none;
        }
      }
      .Avatar {
        margin-right: 30rpx;
      }
      .right {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 40rpx 44rpx 40rpx 0;
        border-bottom: 2rpx solid #f0f0f0;
        .nickname {
          font-size: 32rpx;
          font-weight: 500;
          color: #333;
        }
        .btn {
          margin-left: 24rpx;
          font-size: 28rpx;
          color: #1b72ec;
        }
      }
    }
  }
  .modal {
    width: 100%;
    &-title2 {
      text-align: center;
      font-size: 32rpx;
      color: #333333;
      //   margin-bottom: 36rpx;
    }
  }
}
</style>