<template>
  <view class="userSetting">
    <view class="userSetting-item userInfo">
      <view class="userInfo-item">
        <Avatar
          :src="userInfo.publicInfo.faceURL"
          :name="nickname"
          size="48px"
          @click="toInfo"
        />
        <view class="nickname">{{ nickname }}</view>
      </view>
      <view class="userInfo-add" @click="toCreate">
        <image class="image" src="@/static/images/conversation/info/add.png" />
      </view>
    </view>
    <view class="userSetting-item search">
      <view class="search-title">查找聊天记录</view>
      <view class="search-icons">
        <view class="search-icons-item" @click="toSearch()">
          <image
            class="image"
            src="@/static/images/conversation/info/search.png"
          />
          <text class="text">搜索</text>
        </view>
        <view class="search-icons-item" @click="toSearch(102)">
          <image
            class="image"
            src="@/static/images/conversation/info/image.png"
          />
          <text class="text">图片</text>
        </view>
        <view class="search-icons-item" @click="toSearch(104)">
          <image
            class="image"
            src="@/static/images/conversation/info/video.png"
          />
          <text class="text">视频</text>
        </view>
        <view class="search-icons-item" @click="toSearch(105)">
          <image
            class="image"
            src="@/static/images/conversation/info/file.png"
          />
          <text class="text">文件</text>
        </view>
      </view>
    </view>
    <view class="userSetting-item operation">
      <view class="operation-item">
        <text class="label">置顶联系人</text>
        <view class="right">
          <u-switch
            v-model="conversationData.isPinned"
            @change="pinConversation"
          />
        </view>
      </view>
      <view class="operation-item">
        <text class="label">消息免打扰</text>
        <view class="right">
          <u-switch
            v-model="messageOptData.status"
            @change="messageOptChange"
          />
        </view>
      </view>
      <view
        class="operation-item"
        v-show="messageOptData.status"
        @click="messageOptData.actionShow = true"
      >
        <text class="label">好友消息设置</text>
        <view class="right">
          <text class="des">{{ currentConversationRecvMessageOpt }}</text>
          <u-icon name="arrow-right" size="24" color="#999" />
        </view>
      </view>
    </view>
    <view class="userSetting-item operation">
      <view class="operation-item">
        <text class="label">投诉</text>
        <view class="right">
          <u-icon name="arrow-right" size="24" color="#999" />
        </view>
      </view>
    </view>
    <view class="userSetting-item operation" @click="clearData.show = true">
      <view class="operation-item">
        <text class="label">清空聊天记录</text>
        <view class="right">
          <u-icon name="arrow-right" size="24" color="#999" />
        </view>
      </view>
    </view>
    <u-action-sheet
      :actions="messageOptData.actionList"
      :show="messageOptData.actionShow"
      round="16"
      cancelText="取消"
      @select="multipleSelect"
      @close="multipleClose"
    />
    <u-modal
      class="modal"
      :show="clearData.show"
      width="500rpx"
      showCancelButton
      confirmText="确定"
      @cancel="clearData.show = false"
      @confirm="clearC2CHistoryMessageFromLocalAndSvr"
    >
      <view class="modal-content"> 是否清空聊天记录? </view>
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
      userID: "",
      conversationData: {
        conversationID: "group_8f1f2680ccd152762d5fa9c86c063d4c",
        conversationType: 2,
        userID: "18381415165",
        groupID: "8f1f2680ccd152762d5fa9c86c063d4c",
        showName: "ces",
        faceURL: "",
        recvMsgOpt: 0,
        unreadCount: 2,
        groupAtType: 0,
        latestMsg:
          '{"clientMsgID":"64e94fb7791f5a55b92483f8c276c548","serverMsgID":"cf8d30e288844b0e86f95f2b8dbe835b","createTime":1654573113449,"sendTime":1654573113449,"sessionType":2,"sendID":"12300000000","recvID":"17396220460","msgFrom":0,"contentType":1502,"platformID":0,"groupID":"8f1f2680ccd152762d5fa9c86c063d4c","content":"{\\"detail\\":\\"CqYBCiA4ZjFmMjY4MGNjZDE1Mjc2MmQ1ZmE5Yzg2YzA2M2Q0YxILMTIzMDAwMDAwMDAYAiCrkPuUBioGaGloaWhpMlZodHRwczovL3N0b3JhZ2UucmVudHNvZnQuY24vb3BlbmltLzE2NTI0MzQzNTkzOTEwNzUzMDYtMTk3NTI2ODAyNDQ1NTk2NDQ2NWN2ZV9ub3RpLnBuZzgBSgsxMjMwMDAwMDAwMBpSCiA4ZjFmMjY4MGNjZDE1Mjc2MmQ1ZmE5Yzg2YzA2M2Q0YxoM5rWL6K+V5YWs5ZGKMgsxMjMwMDAwMDAwMDirkPuUBkACWgsxMjMwMDAwMDAwMA==\\",\\"defaultTips\\":\\"hihihi modified the group profile\\",\\"jsonDetail\\":\\"{\\\\\\"opUser\\\\\\":{\\\\\\"groupID\\\\\\":\\\\\\"8f1f2680ccd152762d5fa9c86c063d4c\\\\\\",\\\\\\"userID\\\\\\":\\\\\\"12300000000\\\\\\",\\\\\\"roleLevel\\\\\\":2,\\\\\\"joinTime\\\\\\":1654573099,\\\\\\"nickname\\\\\\":\\\\\\"hihihi\\\\\\",\\\\\\"faceURL\\\\\\":\\\\\\"https://storage.rentsoft.cn/openim/1652434359391075306-1975268024455964465cve_noti.png\\\\\\",\\\\\\"appMangerLevel\\\\\\":1,\\\\\\"operatorUserID\\\\\\":\\\\\\"12300000000\\\\\\"},\\\\\\"group\\\\\\":{\\\\\\"groupID\\\\\\":\\\\\\"8f1f2680ccd152762d5fa9c86c063d4c\\\\\\",\\\\\\"notification\\\\\\":\\\\\\"测试公告\\\\\\",\\\\\\"ownerUserID\\\\\\":\\\\\\"12300000000\\\\\\",\\\\\\"createTime\\\\\\":1654573099,\\\\\\"memberCount\\\\\\":2,\\\\\\"creatorUserID\\\\\\":\\\\\\"12300000000\\\\\\"}}\\"}","seq":191,"isRead":false,"status":2,"offlinePush":{},"pictureElem":{"sourcePicture":{"size":0,"width":0,"height":0},"bigPicture":{"size":0,"width":0,"height":0},"snapshotPicture":{"size":0,"width":0,"height":0}},"soundElem":{"dataSize":0,"duration":0},"videoElem":{"videoSize":0,"duration":0,"snapshotSize":0,"snapshotWidth":0,"snapshotHeight":0},"fileElem":{"fileSize":0},"mergeElem":{},"atElem":{"isAtSelf":false},"faceElem":{"index":0},"locationElem":{"longitude":0,"latitude":0},"customElem":{},"quoteElem":{},"notificationElem":{"detail":"{\\"opUser\\":{\\"groupID\\":\\"8f1f2680ccd152762d5fa9c86c063d4c\\",\\"userID\\":\\"12300000000\\",\\"roleLevel\\":2,\\"joinTime\\":1654573099,\\"nickname\\":\\"hihihi\\",\\"faceURL\\":\\"https://storage.rentsoft.cn/openim/1652434359391075306-1975268024455964465cve_noti.png\\",\\"appMangerLevel\\":1,\\"operatorUserID\\":\\"12300000000\\"},\\"group\\":{\\"groupID\\":\\"8f1f2680ccd152762d5fa9c86c063d4c\\",\\"notification\\":\\"测试公告\\",\\"ownerUserID\\":\\"12300000000\\",\\"createTime\\":1654573099,\\"memberCount\\":2,\\"creatorUserID\\":\\"12300000000\\"}}","defaultTips":"hihihi modified the group profile"},"attachedInfoElem":{"groupHasReadInfo":{"hasReadCount":0,"groupMemberCount":0},"isPrivateChat":false,"hasReadTime":0}}',
        latestMsgSendTime: 1654573113449,
        draftText: "",
        draftTextTime: 0,
        isPinned: false,
        isPrivateChat: false,
        isNotInGroup: false,
        attachedInfo: "",
        ex: "",
      },
      userInfo: {
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
      messageOptData: {
        status: false,
        statusValue: 0,
        actionShow: false,
        isPending: false,
        actionList: [
          {
            name: "接收消息但不提醒",
            color: "#000",
            fontSize: "16",
            status: "2",
          },
          {
            name: "屏蔽该好友",
            color: "#000",
            fontSize: "16",
            status: "1",
          },
        ],
      },
      clearData: {
        show: false,
      },
    };
  },
  methods: {
    init() {
      this.$im.getUsersInfo(this.operationID, [this.userID], (res) => {
        if (res.errCode === 0) {
          let list = JSON.parse(res.data);
          let item = list[0];
          this.userInfo = item;
        }
      });
      this.$im.getOneConversation(this.operationID, 1, this.userID, (res) => {
        if (res.errCode === 0) {
          const data = JSON.parse(res.data);
          this.conversationData = data;
          this.messageOptData.status =
            this.conversationData.recvMsgOpt === 0 ? false : true;
          this.messageOptData.statusValue = this.conversationData.recvMsgOpt;
        }
      });
    },
    messageOptChange(v) {
      if (
        (v &&
          (this.messageOptData.statusValue === 1 ||
            this.messageOptData.statusValue === 2)) ||
        (!v && this.messageOptData.statusValue === 0)
      ) {
      } else if (v) {
        this.messageOptData.actionShow = true;
      } else if (!v) {
        this.setOneConversationRecvMessageOpt(0);
      }
    },
    multipleSelect({ status }) {
      let s = Number(status);
      this.setOneConversationRecvMessageOpt(s);
    },
    multipleClose() {
      this.messageOptData.actionShow = false;
      if (this.messageOptData.isPending) {
        return;
      }
      if (
        this.messageOptData.status &&
        !(
          this.messageOptData.statusValue === 1 ||
          this.messageOptData.statusValue === 2
        )
      ) {
        this.messageOptData.status = false;
      } else if (
        !this.messageOptData.status &&
        this.messageOptData.statusValue !== 0
      ) {
        this.messageOptData.status = true;
      }
    },
    setOneConversationRecvMessageOpt(status) {
      this.messageOptData.isPending = true;
      // status:是否开启免打扰 0不开启 1不接受信息 2接收但不提醒
      this.$im.setOneConversationRecvMessageOpt(
        this.operationID,
        this.conversationData.conversationID,
        status,
        (res) => {
          this.messageOptData.isPending = false;
          if (res.errCode === 0) {
            this.$store.commit("message/set_indexMessageTimes");
            this.messageOptData.statusValue = status;
            if (
              this.messageOptData.statusValue === 1 ||
              this.messageOptData.statusValue === 2
            ) {
              this.messageOptData.status = true;
            } else {
              this.messageOptData.status = false;
            }
          } else {
            this.$toast(res.errMsg);
          }
        }
      );
    },
    pinConversation(isPinned) {
      // isPinned:为true时，代表置顶会话，为false时代表取消置顶
      this.$im.pinConversation(
        this.operationID,
        this.conversationData.conversationID,
        isPinned,
        (res) => {
          if (res.errCode !== 0) {
            this.$toast(res.errMsg);
            this.conversationData.isPinned = !this.conversationData.isPinned;
          }
        }
      );
    },
    clearC2CHistoryMessageFromLocalAndSvr() {
      this.clearData.show = false;
      this.$im.clearC2CHistoryMessageFromLocalAndSvr(
        this.operationID,
        this.userInfo.publicInfo.userID,
        (res) => {
          this.$store.commit("message/set_clearHistoryMessageTimes");
          this.$toast(res.errMsg);
        }
      );
    },
    toInfo() {
      uni.navigateTo({
        url: "/pages/friend/info?id=" + this.userInfo.publicInfo.userID,
      });
    },
    toSearch(messageType) {
      let param = "";
      if (messageType) {
        param = `&messageType=${messageType}`;
      }
      uni.navigateTo({
        url:
          "@/messageSearch?conversationID=" +
          this.conversationData.conversationID +
          param,
      });
    },
    toCreate() {
      uni.navigateTo({
        url: "/pages/group/create?addUserID=" + this.userInfo.publicInfo.userID,
      });
    },
  },
  computed: {
    ...mapGetters(["operationID"]),
    currentConversationRecvMessageOpt() {
      switch (this.messageOptData.statusValue) {
        case 1:
          return "屏蔽该好友";
        case 2:
          return "接收消息但不提醒";
        default:
          return "";
      }
    },
    nickname() {
      if (this.userInfo.friendInfo) {
        return (
          this.userInfo.friendInfo.remark || this.userInfo.friendInfo.nickname
        );
      }
      return this.userInfo.publicInfo.nickname;
    },
  },
  onLoad(param) {
    this.userID = param.userID;
    // #ifdef APP-PLUS
    this.init();
    // #endif
  },
};
</script>

<style lang="scss" scoped>
.userSetting {
  background: #f6f6f6;
  min-height: 100vh;
  padding-top: 12rpx;
  &-item {
    margin-bottom: 24rpx;
    background-color: #fff;
  }
  .userInfo {
    display: flex;
    flex-direction: row;
    padding: 40rpx;
    &-item {
      display: flex;
      flex-direction: column;
      align-items: center;
      text-align: center;
      margin-right: 23rpx;
      .nickname {
        margin-top: 28rpx;
        font-size: 36rpx;
        color: #666666;
      }
    }
    &-add {
      width: 96rpx;
      height: 96rpx;
      .image {
        width: 100%;
        height: 100%;
      }
    }
  }
  .search {
    padding: 36rpx 44rpx 56rpx;
    &-title {
      font-size: 32rpx;
      color: #333333;
      margin-bottom: 46rpx;
    }
    &-icons {
      display: flex;
      flex-direction: row;
      justify-content: space-around;
      &-item {
        display: flex;
        flex-direction: column;
        .image {
          width: 48rpx;
          height: 48rpx;
          margin-bottom: 16rpx;
        }
        .text {
          font-size: 24rpx;
          color: #666666;
        }
      }
    }
  }
  .operation {
    &-item {
      padding: 28rpx 44rpx;
      border-bottom: 2rpx solid #f6f6f6;
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      .label {
        color: #333333;
      }
      .right {
        display: flex;
        flex-direction: row;
        font-size: 28rpx;
        color: #999;
        .des {
          margin-right: 12rpx;
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
      padding: 24rpx 40rpx;
      width: 100%;
    }
  }
}
</style>
