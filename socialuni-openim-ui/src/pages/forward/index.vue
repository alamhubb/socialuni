<template>
  <view class="forward">
    <view class="statusBar"></view>
    <u-navbar class="navbar" title="选择联系人" autoBack>
      <u-button
        slot="right"
        type="primary"
        text="发送"
        size="mini"
        :disabled="btnDisabled"
        @click="modalShow = true"
      />
    </u-navbar>
    <view class="search">
      <u-search
        class="search-item"
        placeholder="搜索"
        v-model="keyword"
        bgColor="#E8F2FF"
        :showAction="false"
      />
    </view>
    <view
      class="checkedList"
      v-show="checkedFriendListLength > 0 || checkedGroupListLength > 0"
    >
      <Avatar
        v-for="item in ckeckedFriendList"
        :key="item.friendInfo.userID"
        class="faceURL"
        :src="item.friendInfo.faceURL"
        :name="item.friendInfo.remark || item.friendInfo.nickname"
        size="39px"
        @click="friendItemClick(item)"
      />
      <Avatar
        v-for="item in ckeckedGroupList"
        :key="item.groupID"
        class="faceURL"
        :src="item.faceURL"
        :name="item.groupName"
        size="39px"
        @click="groupItemClick(item)"
      />
    </view>
    <u-sticky offset-top="0">
      <u-tabs
        class="tabs"
        :list="tabs.list"
        :current="tabs.index"
        :scrollable="false"
        lineWidth="60rpx"
        :itemStyle="{ width: '50%', 'box-sizing': 'border-box' }"
        @change="tabChange"
      />
    </u-sticky>
    <!-- <view class="recently"> 最近聊天 </view> -->
    <view class="friendList" v-show="tabs.index === 0">
      <u-checkbox-group v-model="checkedList" placement="column">
        <view
          class="friendList-item"
          v-for="item in keyWordFriendList"
          :key="item.friendInfo.userID"
        >
          <u-checkbox
            class="friendList-item-checkbox"
            :name="item.friendInfo.userID"
            shape="circle"
          >
          </u-checkbox>
          <Avatar
            @click="friendItemClick(item)"
            class="faceURL"
            :src="item.friendInfo.faceURL"
            :name="item.friendInfo.nickname"
            size="42px"
          />
          <view class="nickname" @click="friendItemClick(item)">
            {{ item.friendInfo.remark || item.friendInfo.nickname }}
          </view>
        </view>
      </u-checkbox-group>
    </view>
    <view class="friendList" v-show="tabs.index === 1">
      <u-checkbox-group v-model="checkedGroupList" placement="column">
        <view
          class="friendList-item"
          v-for="item in keyWordGroupList"
          :key="item.groupID"
        >
          <u-checkbox
            class="friendList-item-checkbox"
            :name="item.groupID"
            shape="circle"
          >
          </u-checkbox>
          <Avatar
            @click="groupItemClick(item)"
            class="faceURL"
            :src="item.faceURL"
            :name="item.groupName"
            size="42px"
          />
          <view class="nickname" @click="groupItemClick(item)">
            {{ item.groupName }}
          </view>
        </view>
      </u-checkbox-group>
    </view>
    <view class="footer">
      <view class="footer-content">
        <view class="footer-content-left" @click="showPop = true">
          已选择: {{ checkedFriendListLength }}人,
          {{ checkedGroupListLength }}
          个群组
          <u-icon class="icon" name="arrow-up" color="#1b72ec" size="28rpx" />
        </view>
        <view class="footer-content-right">
          <u-button
            class="button"
            type="primary"
            :disabled="btnDisabled"
            @click="modalShow = true"
          >
            确定（{{ checkedTotal }}/{{ total }}）
          </u-button>
        </view>
      </view>
    </view>
    <u-popup
      :show="showPop"
      mode="bottom"
      round="36rpx"
      @close="showPop = false"
    >
      <view class="popup">
        <view class="popup-title">
          <view class="text">
            已选择：{{ checkedFriendListLength }}人,
            {{ checkedGroupListLength }}
            个群组
          </view>
          <view class="text" @click="showPop = false"> 确认 </view>
        </view>
        <u-tabs
          class="popup-tabs tabs"
          :list="tabs.list"
          :current="tabs.index"
          :scrollable="false"
          lineWidth="60rpx"
          :itemStyle="{ width: '50%', 'box-sizing': 'border-box' }"
          @change="tabChange"
        />
        <view class="friendList" v-show="tabs.index === 0">
          <view
            class="friendList-item"
            v-for="item in ckeckedFriendList"
            :key="item.friendInfo.userID"
          >
            <Avatar
              class="faceURL"
              :src="item.friendInfo.faceURL"
              :name="item.friendInfo.remark || item.friendInfo.nickname"
              size="42px"
            />
            <view class="nickname">
              <text class="text">{{
                item.friendInfo.remark || item.friendInfo.nickname
              }}</text>
              <u-button
                class="button"
                type="primary"
                plain
                size="mini"
                @click="friendItemClick(item)"
              >
                移除
              </u-button>
            </view>
          </view>
        </view>
        <view class="friendList" v-show="tabs.index === 1">
          <view
            class="friendList-item"
            v-for="item in ckeckedGroupList"
            :key="item.groupID"
          >
            <Avatar
              class="faceURL"
              :src="item.faceURL"
              :name="item.groupName"
              size="42px"
            />
            <view class="nickname">
              <text class="text">{{ item.groupName }}</text>
              <u-button
                class="button"
                type="primary"
                plain
                size="mini"
                @click="groupItemClick(item)"
              >
                移除
              </u-button>
            </view>
          </view>
        </view>
      </view>
    </u-popup>
    <u-modal
      class="modal"
      :show="modalShow"
      width="500rpx"
      showCancelButton
      :confirmText="confirmText"
      @cancel="modalShow = false"
      @confirm="confirmSend"
    >
      <view class="modal-content">
        <view class="modal-content-title">确认发送给：</view>
        <view class="modal-content-list">
          <Avatar
            v-for="item in ckeckedFriendList"
            :key="item.friendInfo.userID"
            class="faceURL"
            :src="item.friendInfo.faceURL"
            :name="item.friendInfo.remark || item.friendInfo.nickname"
            size="34px"
          />
          <Avatar
            v-for="item in ckeckedGroupList"
            :key="item.groupID"
            class="faceURL"
            :src="item.faceURL"
            :name="item.groupName"
            size="34px"
          />
        </view>
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
      keyword: "",
      friendList: [
        {
          publicInfo: null,
          friendInfo: {
            ownerUserID: "17396220460",
            userID: "17788889999",
            remark: "",
            createTime: 1647523474,
            addSource: 0,
            operatorUserID: "17788889999",
            nickname: "1111",
            faceURL: "ic_avatar_01",
            gender: 0,
            phoneNumber: "17788889999",
            birth: 0,
            email: "",
            ex: "",
            attachedInfo: "",
          },
          blackInfo: null,
        },
        {
          publicInfo: null,
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
        {
          publicInfo: null,
          friendInfo: {
            ownerUserID: "17396220460",
            userID: "18666662412",
            remark: "",
            createTime: 1648099931,
            addSource: 0,
            operatorUserID: "18666662412",
            nickname: "2222",
            faceURL:
              "https://storage.rentsoft.cn/openim/1652351062441592471-39165896162871139371.jpg",
            gender: 0,
            phoneNumber: "18666662412",
            birth: 0,
            email: "",
            ex: "",
            attachedInfo: "",
          },
          blackInfo: null,
        },
        {
          publicInfo: null,
          friendInfo: {
            ownerUserID: "17396220460",
            userID: "18712345678",
            remark: "",
            createTime: 1647869100,
            addSource: 0,
            operatorUserID: "17396220460",
            nickname: "G",
            faceURL:
              "https://storage.rentsoft.cn/openim/1654163108582476344-6129484611666145821微信截图_20220318203825.png",
            gender: 0,
            phoneNumber: "",
            birth: 0,
            email: "",
            ex: "",
            attachedInfo: "",
          },
          blackInfo: null,
        },
        {
          publicInfo: null,
          friendInfo: {
            ownerUserID: "17396220460",
            userID: "12300000000",
            remark: "",
            createTime: 1647523513,
            addSource: 0,
            operatorUserID: "17396220460",
            nickname: "hihihi",
            faceURL:
              "https://storage.rentsoft.cn/openim/1652434359391075306-1975268024455964465cve_noti.png",
            gender: 0,
            phoneNumber: "",
            birth: 0,
            email: "",
            ex: "",
            attachedInfo: "",
          },
          blackInfo: null,
        },
        {
          publicInfo: null,
          friendInfo: {
            ownerUserID: "17396220460",
            userID: "13900000000",
            remark: "",
            createTime: 1647570002,
            addSource: 0,
            operatorUserID: "13900000000",
            nickname: "happy",
            faceURL:
              "http://127.0.0.1:10005/openim/1654591821374531779-6129484611666145821image_cropper_1654591819044.jpg",
            gender: 1,
            phoneNumber: "13900000000",
            birth: 0,
            email: "",
            ex: "",
            attachedInfo: "",
          },
          blackInfo: null,
        },
        {
          publicInfo: null,
          friendInfo: {
            ownerUserID: "17396220460",
            userID: "13944444444",
            remark: "",
            createTime: 1647596888,
            addSource: 0,
            operatorUserID: "17396220460",
            nickname: "Live Photos",
            faceURL: "ic_avatar_02",
            gender: 0,
            phoneNumber: "13944444444",
            birth: 0,
            email: "",
            ex: "",
            attachedInfo: "",
          },
          blackInfo: null,
        },
        {
          publicInfo: null,
          friendInfo: {
            ownerUserID: "17396220460",
            userID: "13944444445",
            remark: "",
            createTime: 1647596888,
            addSource: 0,
            operatorUserID: "17396220460",
            nickname: "Live Photos",
            faceURL: "ic_avatar_02",
            gender: 0,
            phoneNumber: "13944444444",
            birth: 0,
            email: "",
            ex: "",
            attachedInfo: "",
          },
          blackInfo: null,
        },
        {
          publicInfo: null,
          friendInfo: {
            ownerUserID: "17396220460",
            userID: "13944444446",
            remark: "",
            createTime: 1647596888,
            addSource: 0,
            operatorUserID: "17396220460",
            nickname: "Live Photos",
            faceURL: "ic_avatar_02",
            gender: 0,
            phoneNumber: "13944444444",
            birth: 0,
            email: "",
            ex: "",
            attachedInfo: "",
          },
          blackInfo: null,
        },
        {
          publicInfo: null,
          friendInfo: {
            ownerUserID: "17396220460",
            userID: "13944444447",
            remark: "",
            createTime: 1647596888,
            addSource: 0,
            operatorUserID: "17396220460",
            nickname: "Live Photos",
            faceURL: "ic_avatar_02",
            gender: 0,
            phoneNumber: "13944444444",
            birth: 0,
            email: "",
            ex: "",
            attachedInfo: "",
          },
          blackInfo: null,
        },
        {
          publicInfo: null,
          friendInfo: {
            ownerUserID: "17396220460",
            userID: "13944444448",
            remark: "",
            createTime: 1647596888,
            addSource: 0,
            operatorUserID: "17396220460",
            nickname: "Live Photos",
            faceURL: "ic_avatar_02",
            gender: 0,
            phoneNumber: "13944444444",
            birth: 0,
            email: "",
            ex: "",
            attachedInfo: "",
          },
          blackInfo: null,
        },
      ],
      checkedList: [],
      checkedGroupList: [],
      showPop: false,
      modalShow: false,
      tabs: {
        list: [
          {
            name: "好友",
          },
          {
            name: "群组",
          },
        ],
        index: 0,
      },
      groupList: [
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
    };
  },
  methods: {
    init() {
      this.checkedList = [];
      this.checkedGroupList = [];
      this.$im.getFriendList(this.operationID, (res) => {
        if (res.errCode === 0) {
          this.friendList = JSON.parse(res.data);
        }
      });
      this.$im.getJoinedGroupList(this.operationID, (res) => {
        if (res.errCode === 0) {
          this.groupList = JSON.parse(res.data);
        }
      });
    },
    tabChange({ index }) {
      this.tabs.index = index;
    },
    friendItemClick(item) {
      const userID = item.friendInfo.userID;
      const index = this.checkedList.findIndex((i) => i === userID);
      if (index === -1) {
        this.checkedList.push(userID);
      } else {
        this.checkedList.splice(index, 1);
      }
    },
    groupItemClick(item) {
      const groupID = item.groupID;
      const index = this.checkedGroupList.findIndex((i) => i === groupID);
      if (index === -1) {
        this.checkedGroupList.push(groupID);
      } else {
        this.checkedGroupList.splice(index, 1);
      }
    },
    confirmSend() {
      this.checkedList.map((userID) => {
        this.$im.sendMessage(
          this.operationID,
          this.operationMessageItem,
          userID,
          "",
          {
            title: "你有一条新消息",
            desc: "",
            ex: "",
            iOSPushSound: "+1",
            iOSBadgeCount: true,
          }
        );
      });
      this.checkedGroupList.map((groupID) => {
        this.$im.sendMessage(
          this.operationID,
          this.operationMessageItem,
          "",
          groupID,
          {
            title: "你有一条新消息",
            desc: "",
            ex: "",
            iOSPushSound: "+1",
            iOSBadgeCount: true,
          }
        );
      });
      uni.navigateBack();
    },
  },
  onLoad() {
    // #ifdef APP-PLUS
    this.init();
    // #endif
  },
  computed: {
    ...mapGetters(["userID", "operationID", "operationMessageItem"]),
    btnDisabled() {
      return this.checkedFriendListLength + this.checkedGroupListLength <= 0;
    },
    keyWordFriendList() {
      return this.friendList.filter((i) => {
        return (
          (i.friendInfo.phoneNumber.includes(this.keyword) ||
            i.friendInfo.nickname.includes(this.keyword)) &&
          !i.blackInfo
        );
      });
    },
    keyWordGroupList() {
      return this.groupList.filter((i) => {
        return i.groupName.includes(this.keyword);
      });
    },
    ckeckedFriendList() {
      return this.friendList.filter((i) => {
        return this.checkedList.includes(i.friendInfo.userID);
      });
    },
    ckeckedGroupList() {
      return this.groupList.filter((i) => {
        return this.checkedGroupList.includes(i.groupID);
      });
    },
    checkedFriendListLength() {
      return this.ckeckedFriendList.length || 0;
    },
    checkedGroupListLength() {
      return this.ckeckedGroupList.length || 0;
    },
    checkedTotal() {
      return this.checkedFriendListLength + this.checkedGroupListLength;
    },
    total() {
      return this.friendList.length + this.groupList.length;
    },
    confirmText() {
      return `发送（${this.checkedTotal}）`;
    },
  },
};
</script>

<style lang="scss" scoped>
$pdLR: 44rpx;
.forward {
  background-color: #fff;
  .navbar {
    height: 44px;
  }
  .search {
    padding: 28rpx $pdLR;
    &-item {
      ::v-deep .u-search__content {
        border-radius: 8rpx !important;
      }
    }
  }
  .checkedList {
    padding: 12rpx 44rpx;
    background: #f8f8f8;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    margin-bottom: 24rpx;
    .faceURL {
      margin: 6rpx 12rpx 6rpx 0;
    }
  }
  .tabs {
    background-color: #fff;
    ::v-deep .u-tabs__wrapper__nav {
      padding-bottom: 10rpx;
      .u-tabs__wrapper__nav__line {
        bottom: 0;
      }
    }
  }
  .recently {
    padding: 10rpx $pdLR;
    font-size: 24rpx;
    font-weight: 600;
    color: #bcbcbc;
    background: #f8f8f8;
  }
  .friendList {
    &-item {
      padding-top: 28rpx;
      display: flex;
      flex-direction: row;
      align-items: flex-start;
      &-checkbox {
        margin: 0 24rpx;
        height: 42px;
      }
      .nickname {
        margin-left: 24rpx;
        flex: 1;
        height: 42px;
        line-height: 42px;
        padding-bottom: 12rpx;
        padding-right: 24rpx;
        border-bottom: 2rpx solid #f0f0f0;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
      }
    }
  }
  .footer {
    height: 164rpx;
    &-content {
      height: 164rpx;
      width: 100%;
      position: fixed;
      bottom: 0;
      left: 0;
      background-color: #fff;
      box-shadow: 0px 0px 4px 0px rgba(0, 0, 0, 0.15);
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      align-items: flex-start;
      padding: 18rpx 44rpx;
      box-sizing: border-box;
      &-left {
        display: flex;
        flex-direction: row;
        align-items: center;
        font-size: 28rpx;
        font-weight: 500;
        color: #1b72ec;
        .icon {
          font-weight: 500;
          margin-left: 8rpx;
        }
      }
      &-right {
        .button {
          height: 52rpx;
        }
      }
    }
  }
  .popup {
    &-title {
      padding: 32rpx 44rpx;
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      border-bottom: 2rpx solid #f0f0f0;
      .text {
        font-size: 28rpx;
        font-weight: 500;
        &:nth-of-type(1) {
          color: #333333;
          flex: 1;
          margin-right: 24rpx;
        }
        &:nth-of-type(2) {
          color: #1b72ec;
        }
      }
    }
    &-tabs {
      margin-top: 12rpx;
    }
    .friendList {
      height: 800rpx;
      overflow-y: scroll;
      &-item {
        padding-top: 28rpx;
        display: flex;
        flex-direction: row;
        align-items: flex-start;
        .faceURL {
          margin-left: 44rpx;
        }
        .nickname {
          display: flex;
          flex-direction: row;
          align-items: center;
          margin-left: 24rpx;
          flex: 1;
          height: 42px;
          line-height: 42px;
          padding-bottom: 12rpx;
          padding-right: 80rpx;
          border-bottom: 2rpx solid #f0f0f0;
          .text {
            flex-grow: 1;
            margin-right: 12rpx;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
          }
          .button {
            width: 40rpx;
          }
        }
      }
    }
  }
  .modal {
    ::v-deep .u-modal__content {
      padding: 0;
      padding-top: 0 !important;
    }
    &-content {
      padding: 24rpx 0;
      width: 100%;
      &-title {
        padding: 0 40rpx;
        font-size: 32rpx;
        color: #333333;
        margin-bottom: 36rpx;
      }
      &-list {
        padding: 0 40rpx;
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        .faceURL {
          margin-bottom: 12rpx;
          margin-right: 16rpx;
        }
      }
    }
  }
}
</style>
