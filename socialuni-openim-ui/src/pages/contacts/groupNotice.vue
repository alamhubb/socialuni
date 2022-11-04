<template>
  <view class="groupNotice">
    <view class="search">
      <u-search
        v-model="searchContent"
        shape="square"
        placeholder="通过手机号/ID号搜索添加"
        :showAction="false"
      />
    </view>
    <u-tabs
      class="tabs"
      :list="tab.list"
      :current="tab.current"
      @change="tabChange"
      :scrollable="false"
    />
    <view
      class="groupNotice-content groupNotice-new"
      v-show="tab.current === 0"
    >
      <view class="title">入群申请</view>
      <ContactGroupCard
        class="ContactGroupCard"
        v-for="(item, index) in groupNoticeList2"
        :key="index"
        :card="item"
        :type="0"
        @refresh="init"
      >
      </ContactGroupCard>
      <view class="more" v-show="showGroupNoticeMore">
        <view class="btn" @click="toApplicationList('0')">
          查看全部入群申请
        </view>
      </view>
    </view>
    <view class="groupNotice-content groupNotice-my" v-show="tab.current === 1">
      <view class="title">我的申请</view>
      <ContactGroupCard
        class="ContactGroupCard"
        v-for="(item, index) in selfGroupNoticeList2"
        :key="index"
        :card="item"
        :type="1"
        @refresh="init"
      >
      </ContactGroupCard>
      <view class="more" v-show="showSelfGroupNoticeMore">
        <view class="btn" @click="toApplicationList('1')"
          >查看我的入群申请</view
        >
      </view>
    </view>
  </view>
</template>

<script>
import ContactGroupCard from "@/components/ContactGroupCard";
import { mapGetters } from "vuex";
export default {
  components: { ContactGroupCard },
  data() {
    return {
      searchContent: "",
      tab: { current: 0, list: [{ name: "入群申请" }, { name: "我的申请" }] },
      maxLength: { groupNotice: 4, selfGroupNotice: 4 },
    };
  },
  methods: {
    init() {
      //#ifdef APP-PLUS
      this.$store.dispatch("contacts/get_groupNoticeList", this.$im);
      this.$store.dispatch("contacts/get_selfGroupNoticeList", this.$im);
      //#endif
    },
    tabChange({ index }) {
      this.tab.current = index;
    },
    toApplicationList(type) {
      uni.navigateTo({
        url: "@/applicationGroupList?type=" + type,
      });
    },
  },
  onShow() {
    this.init();
  },
  computed: {
    ...mapGetters([
      "operationID",
      "groupNoticeList",
      "selfGroupNoticeList",
      "userID",
    ]),
    groupNoticeList2() {
      let list = this.groupNoticeList;
      if (this.searchContent) {
        list = list.filter((i) => {
          return (
            i.groupID.includes(this.searchContent) ||
            i.groupName.includes(this.searchContent) ||
            i.userID.includes(this.searchContent) ||
            i.nickname.includes(this.searchContent)
          );
        });
      }
      return list.slice(0, this.maxLength.groupNotice);
    },
    showGroupNoticeMore() {
      return this.groupNoticeList.length > this.maxLength.groupNotice
        ? true
        : false;
    },
    selfGroupNoticeList2() {
      let list = this.selfGroupNoticeList;
      if (this.searchContent) {
        list = list.filter((i) => {
          return (
            i.groupID.includes(this.searchContent) ||
            i.groupName.includes(this.searchContent) ||
            i.userID.includes(this.searchContent) ||
            i.nickname.includes(this.searchContent)
          );
        });
      }
      return list.slice(0, this.maxLength.selfGroupNotice);
    },
    showSelfGroupNoticeMore() {
      return this.selfGroupNoticeList.length > this.maxLength.selfGroupNotice
        ? true
        : false;
    },
  },
};
</script>

<style lang="scss" scoped>
$pagePadding: 44rpx;
.groupNotice {
  background-color: #f8f8f8;
  min-height: 100vh;
  .search,
  .tabs,
  .groupNotice-content {
    background-color: #fff;
  }
  .search {
    padding: 24rpx $pagePadding 0;
  }
  &-content {
    .title {
      padding: 16rpx $pagePadding;
      font-size: 24rpx;
      color: #999;
      background-color: #f8f8f8;
    }
    ::v-deep .ContactGroupCard {
      &:last-of-type {
        .right {
          border-bottom: none;
        }
      }
    }
    .more {
      background-color: #f8f8f8;
      padding-top: 24rpx;
      .btn {
        text-align: center;
        height: 86rpx;
        line-height: 86rpx;
        font-size: 28rpx;
        color: #1d6bed;
        background-color: #fff;
      }
    }
  }
  &-new {
  }
  &-my {
  }
}
</style>
