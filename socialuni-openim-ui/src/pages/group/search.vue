<template>
  <view class="container">
    <view class="statusBar" />
    <view class="content">
      <view class="search">
        <u-search
          shape="square"
          v-model="searchContent"
          placeholder="通过群ID搜索"
          actionText="取消"
          :actionStyle="{ color: '#1B72EC' }"
          @change="searchContentChange"
          @search="searchConfirm"
          @custom="back"
        />
        <view
          class="search-content"
          @click="searchConfirm"
          v-show="searchContent && !isEmpty"
        >
          <image class="image" src="@/static/images/friend/search-icon.png" />
          <text class="text">搜索:</text>
          <text class="userName">{{ searchContent }}</text>
        </view>
        <view class="search-empty" v-show="isEmpty"> 无法找到该群组 </view>
      </view>
    </view>
  </view>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      searchContent: "",
      isEmpty: false,
    };
  },
  onLoad() {},
  methods: {
    searchContentChange() {
      this.isEmpty = false;
    },
    searchConfirm() {
      if (!this.searchContent) return;
      // #ifdef APP-PLUS
      this.$im.getGroupsInfo(this.operationID, [this.searchContent], (res) => {
        if (res.errCode !== 0) {
          this.isEmpty = true;
        } else {
          const list = JSON.parse(res.data);
          if (list.length <= 0) {
            this.isEmpty = true;
          } else {
            uni.navigateTo({
              url: "@/info?id=" + this.searchContent + "&pageStatus=search",
            });
          }
        }
      });
      // #endif
      // #ifdef H5
      uni.navigateTo({
        url: "@/info?id=" + this.searchContent + "&pageStatus=search",
      });
      // #endif
    },
    back() {
      uni.navigateBack();
    },
  },
  computed: {
    ...mapGetters(["operationID"]),
  },
};
</script>

<style lang="scss" scoped>
.container {
  min-height: 100vh;
  background: #f8f8f8;
  .content {
    padding: 24rpx 44rpx;
    background-color: #fff;
  }
  .search {
    &-content {
      display: flex;
      flex-direction: row;
      flex-wrap: nowrap;
      align-items: center;
      font-size: 28r4px;
      color: #333333;
      margin-top: 32rpx;
      .image {
        flex-shrink: 0;
        width: 44rpx;
        height: 44rpx;
        margin-right: 16rpx;
      }
      .text {
        flex-shrink: 0;
        margin-right: 16rpx;
      }
      .userName {
        flex: 1;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
      }
    }
    &-empty {
      margin-top: 32rpx;
      text-align: center;
      font-size: 28rpx;
      color: #666666;
    }
  }
}
</style>
