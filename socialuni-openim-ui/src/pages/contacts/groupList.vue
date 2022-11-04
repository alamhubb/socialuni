<template>
  <view class="groupList">
    <u-navbar class="navbar" title="我的群组" autoBack fixed placeholder>
      <view class="navbar-right" slot="right">
        <view class="navbar-right-item" @click="createGroup"> 发起群聊 </view>
      </view>
    </u-navbar>
    <u-sticky>
      <view class="search">
        <u-search
          v-model="searchContent"
          shape="square"
          placeholder="搜索：群组"
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
    </u-sticky>
    <view
      class="card"
      v-for="item in groupList"
      :key="item.groupID"
      @click="toGroupInfo(item)"
    >
      <Avatar
        class="Avatar"
        :src="item.faceURL"
        :name="item.groupName"
        size="42px"
      />
      <view class="right">
        <view class="name">{{ item.groupName }}</view>
        <view class="memberCount">{{ item.memberCount || 0 }}人</view>
      </view>
    </view>
  </view>
</template>

<script>
import { mapGetters } from "vuex";
import Avatar from "@/components/Avatar.vue";
export default {
  components: { Avatar },
  data() {
    return {
      list: [
        {
          groupID: "2de9d19e73f7b314978b42a8744e664d",
          groupName: "llllll",
          notification: "",
          introduction: "",
          faceURL:
            "https://echat-1302656840.cos.ap-chengdu.myqcloud.com/rc-upload-1648028744201-33icon.png",
          createTime: 1648029996,
          status: 0,
          creatorUserID: "18381415165",
          groupType: 0,
          ownerUserID: "18381415165",
          memberCount: 6,
          ex: "",
          attachedInfo: "",
        },
        {
          groupID: "413dcd143622d1a91a8afd4987cff651",
          groupName: "rtc",
          notification: "",
          introduction: "",
          faceURL:
            "https://echat-1302656840.cos.ap-chengdu.myqcloud.com/rc-upload-1647523487106-12logo.png",
          createTime: 1647523680,
          status: 0,
          creatorUserID: "17396220460",
          groupType: 0,
          ownerUserID: "17396220460",
          memberCount: 10,
          ex: "",
          attachedInfo: "",
        },
        {
          groupID: "42c9f515cb84ee0e82b3f3ce71eb14d6",
          groupName: "debug group name",
          notification: "",
          introduction: "",
          faceURL: "",
          createTime: 1651049591,
          status: 0,
          creatorUserID: "openIM123456",
          groupType: 0,
          ownerUserID: "13811112222",
          memberCount: 505,
          ex: "",
          attachedInfo: "",
        },
        {
          groupID: "4eae41ff105eeb4d301294c2aafe3a22",
          groupName: "想",
          notification: "",
          introduction: "",
          faceURL: "",
          createTime: 1651897671,
          status: 0,
          creatorUserID: "17726378428",
          groupType: 0,
          ownerUserID: "17726378428",
          memberCount: 3,
          ex: "",
          attachedInfo: "",
        },
        {
          groupID: "8e691df015ad009edd50ba21da13deb0",
          groupName: "OK",
          notification: "12345678",
          introduction: "",
          faceURL: "",
          createTime: 1648545890,
          status: 0,
          creatorUserID: "13900000000",
          groupType: 0,
          ownerUserID: "13900000000",
          memberCount: 4,
          ex: "",
          attachedInfo: "",
        },
        {
          groupID: "8f1f2680ccd152762d5fa9c86c063d4c",
          groupName: "ces",
          notification: "测试公告",
          introduction: "",
          faceURL: "",
          createTime: 1654573099,
          status: 0,
          creatorUserID: "12300000000",
          groupType: 0,
          ownerUserID: "12300000000",
          memberCount: 2,
          ex: "",
          attachedInfo: "",
        },
        {
          groupID: "91e1fea9945314ecb4aa118559d0705e",
          groupName: "ara",
          notification: "",
          introduction: "",
          faceURL: "",
          createTime: 1648695988,
          status: 0,
          creatorUserID: "17726378428",
          groupType: 0,
          ownerUserID: "17726378428",
          memberCount: 3,
          ex: "",
          attachedInfo: "",
        },
        {
          groupID: "936cfda362e7e9c02b4e3e75f24e30ab",
          groupName: "才",
          notification: "",
          introduction: "",
          faceURL: "",
          createTime: 1654514619,
          status: 0,
          creatorUserID: "17726378428",
          groupType: 0,
          ownerUserID: "17726378428",
          memberCount: 26,
          ex: "",
          attachedInfo: "",
        },
        {
          groupID: "a8dad184ecacad8c326890d660859919",
          groupName: "group",
          notification: "",
          introduction: "",
          faceURL: "",
          createTime: 1649304961,
          status: 0,
          creatorUserID: "13900000000",
          groupType: 0,
          ownerUserID: "13900000000",
          memberCount: 3,
          ex: "",
          attachedInfo: "",
        },
        {
          groupID: "b34d2333e54f47aa86e143d45741c1a6",
          groupName: "测试群修改",
          notification: "",
          introduction: "",
          faceURL: "",
          createTime: 1651055402,
          status: 0,
          creatorUserID: "18666662412",
          groupType: 0,
          ownerUserID: "18666662412",
          memberCount: 73,
          ex: "",
          attachedInfo: "",
        },
        {
          groupID: "b71be305e4f91da7e0fe837774311764",
          groupName: "textGroup",
          notification: "",
          introduction: "",
          faceURL:
            "https://echat-1302656840.cos.ap-chengdu.myqcloud.com/rc-upload-1648028744201-56Oval.png",
          createTime: 1648031981,
          status: 0,
          creatorUserID: "18381415165",
          groupType: 0,
          ownerUserID: "18381415165",
          memberCount: 8,
          ex: "",
          attachedInfo: "",
        },
        {
          groupID: "ce4a131c8f951c3cad024be7c96cb4dc",
          groupName: "kkk",
          notification: "",
          introduction: "",
          faceURL: "",
          createTime: 1649296265,
          status: 0,
          creatorUserID: "13900000000",
          groupType: 0,
          ownerUserID: "13944444444",
          memberCount: 3,
          ex: "",
          attachedInfo: "",
        },
        {
          groupID: "d574ef824e152d9377f5ad05efe1011b",
          groupName: "work",
          notification: "",
          introduction: "",
          faceURL: "",
          createTime: 1648031045,
          status: 0,
          creatorUserID: "13900000000",
          groupType: 0,
          ownerUserID: "13900000000",
          memberCount: 4,
          ex: "",
          attachedInfo: "",
        },
        {
          groupID: "d9299294fcb5262b5f9e9e0501840a0b",
          groupName: "1233",
          notification: "",
          introduction: "",
          faceURL:
            "http://127.0.0.1:9000/openim/1649157082381642368-557700679194777941032*32.png",
          createTime: 1649157090,
          status: 0,
          creatorUserID: "17396220460",
          groupType: 0,
          ownerUserID: "17396220460",
          memberCount: 2,
          ex: "",
          attachedInfo: "",
        },
        {
          groupID: "e6809963c819521757566fe13bbbdbee",
          groupName: "分",
          notification: "",
          introduction: "",
          faceURL: "",
          createTime: 1648695935,
          status: 0,
          creatorUserID: "17726378428",
          groupType: 0,
          ownerUserID: "17726378428",
          memberCount: 3,
          ex: "",
          attachedInfo: "",
        },
        {
          groupID: "ef41496200ef75c0f00b12748a70739c",
          groupName: "cache002",
          notification: "",
          introduction: "",
          faceURL: "",
          createTime: 1651903502,
          status: 0,
          creatorUserID: "17726378428",
          groupType: 0,
          ownerUserID: "17726378428",
          memberCount: 3,
          ex: "",
          attachedInfo: "",
        },
        {
          groupID: "1989e9fcc82da94b81ca683e1bcc4571",
          groupName: "哈是",
          notification: "",
          introduction: "",
          faceURL: "",
          createTime: 1654740811,
          status: 0,
          creatorUserID: "17726378428",
          groupType: 0,
          ownerUserID: "17726378428",
          memberCount: 18,
          ex: "",
          attachedInfo: "",
        },
        {
          groupID: "2221769978",
          groupName: "测试群id",
          notification: "",
          introduction: "",
          faceURL: "",
          createTime: 1654839537,
          status: 0,
          creatorUserID: "18666662412",
          groupType: 0,
          ownerUserID: "18666662412",
          memberCount: 4,
          ex: "",
          attachedInfo: "",
        },
        {
          groupID: "2682215616",
          groupName: "想8测试群",
          notification: "",
          introduction: "",
          faceURL: "",
          createTime: 1654840864,
          status: 0,
          creatorUserID: "13900000000",
          groupType: 0,
          ownerUserID: "13900000000",
          memberCount: 4,
          ex: "",
          attachedInfo: "",
        },
      ],
      searchContent: "",
      tab: { current: 0, list: [{ name: "我创建的" }, { name: "我加入的" }] },
      timer: null,
    };
  },
  methods: {
    init() {
      this.$im.getJoinedGroupList(this.operationID, (res) => {
        if (res.errCode === 0) {
          this.list = JSON.parse(res.data);
          console.log(this.list);
        }
      });
    },
    toGroupInfo(item) {
      this.$im.getOneConversation(this.operationID, 2, item.groupID, (res) => {
        const data = JSON.parse(res.data);
        const { groupID } = data;
        uni.navigateTo({
          url: "/pages/conversation/index?sessionType=2&sourceID=" + groupID,
        });
      });
    },
    createGroup() {
      uni.navigateTo({
        url: "/pages/group/create",
      });
    },
    tabChange({ index }) {
      this.tab.current = index;
    },
  },
  computed: {
    ...mapGetters([
      "operationID",
      "userID",
      "joinedGroupDeletedTimes",
      "indexMessageTimes",
    ]),
    groupList() {
      let list = this.list;
      if (this.searchContent) {
        list = list.filter((i) => {
          return (
            i.groupID.includes(this.searchContent) ||
            i.groupName.includes(this.searchContent)
          );
        });
      }
      if (this.tab.current === 0) {
        list = list.filter((i) => {
          return i.ownerUserID === this.userID;
        });
      } else if (this.tab.current === 1) {
        list = list.filter((i) => {
          return i.ownerUserID !== this.userID;
        });
      }
      return list;
    },
  },
  onShow() {
    this.init();
  },
  watch: {
    joinedGroupDeletedTimes() {
      if (this.timer) {
        clearTimeout(this.timer);
      }
      this.timer = setTimeout(() => {
        this.init();
      }, 500);
    },
    indexMessageTimes() {
      if (this.timer) {
        clearTimeout(this.timer);
      }
      this.timer = setTimeout(() => {
        this.init();
      }, 500);
    },
  },
};
</script>

<style lang="scss" scoped>
$pagePadding: 44rpx;
.groupList {
  background-color: #f8f8f8;
  min-height: 100vh;
  .search {
    background-color: #fff;
    padding: 24rpx $pagePadding 0;
  }
  .tabs {
    background-color: #fff;
  }
  .card {
    background-color: #fff;
    padding: 0 $pagePadding;
    display: flex;
    align-items: center;
    .Avatar {
      flex-shrink: 0;
    }
    .right {
      height: 42px;
      padding: 22rpx 0;
      flex: 1;
      margin-left: 24rpx;
      border-bottom: 2rpx solid #f0f0f0;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      .name {
        font-size: 32rpx;
        font-weight: 500;
        color: #333333;
      }
      .memberCount {
        font-size: 24rpx;
        color: #999;
      }
    }
  }
}
</style>