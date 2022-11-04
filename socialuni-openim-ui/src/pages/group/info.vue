<template>
  <view class="info">
    <view class="top">
      <Avatar
        class="faceURL"
        :src="groupInfo.faceURL"
        :name="groupInfo.groupName"
      />
      <view class="name">
        <view class="nickname">{{ groupInfo.groupName }}</view>
      </view>
      <image
        v-show="isGroupOwner || isManager"
        class="image"
        @click="changeGroupFaceurlData.actionShow = true"
        src="@/static/images/conversation/group/edit.png"
      />
    </view>
    <view class="center">
      <view
        class="center-title"
        @click="checkPermissionRouterGo('./memberList')"
      >
        <view>群成员</view>
        <view class="text2">
          <text>{{ isDismiss ? 0 : groupInfo.memberCount }}人</text>
          <u-icon class="icon" name="arrow-right" size="14" color="#999" />
        </view>
      </view>
      <view class="center-content">
        <view
          class="center-content-item"
          v-for="(item, index) in groupMemberList2"
          :key="index"
        >
          <Avatar
            :src="item.faceURL"
            :name="item.nickname"
            @click="toFriendInfo(item)"
            size="42px"
          />
        </view>
        <image
          v-show="isInGroupAndNormalPage"
          class="center-content-item"
          src="@/static/images/conversation/group/add.png"
          @click="toMemberList('add')"
        />
        <image
          v-show="isGroupOwner || isManager"
          class="center-content-item"
          src="@/static/images/conversation/group/del.png"
          @click="toMemberList('del')"
        />
      </view>
    </view>
    <view class="info-group">
      <view
        class="info-group-item"
        v-show="isInGroupAndNormalPage"
        @click="toEdit()"
      >
        <view class="left">群聊名称</view>
        <view class="right">
          <text class="des">
            {{ groupInfo.groupName }}
          </text>
          <u-icon class="icon" name="arrow-right" size="14" color="#999" />
        </view>
      </view>
      <view
        class="info-group-item"
        v-show="isInGroupAndNormalPage"
        @click="routerGo('./notification')"
      >
        <view class="left">群公告</view>
        <view class="right">
          <u-icon class="icon" name="arrow-right" size="14" color="#999" />
        </view>
      </view>
      <view
        class="info-group-item"
        v-show="isGroupOwner"
        @click="toMemberList('transferGroup')"
      >
        <view class="left">群主管理权转让</view>
        <view class="right">
          <u-icon class="icon" name="arrow-right" size="14" color="#999" />
        </view>
      </view>
      <view
        class="info-group-item"
        v-show="isInGroupAndNormalPage"
        @click="toEdit(userID)"
      >
        <view class="left">我在群里的昵称</view>
        <view class="right">
          <u-icon class="icon" name="arrow-right" size="14" color="#999" />
        </view>
      </view>
    </view>
    <view class="info-group" v-show="isInGroupAndNormalPage">
      <view class="info-group-item" @click="routerGo('./qrcode')">
        <view class="left">群二维码</view>
        <view class="right">
          <image
            class="des image"
            src="@/static/images/conversation/group/qrcode.png"
          />
          <u-icon class="icon" name="arrow-right" size="14" color="#999" />
        </view>
      </view>
    </view>
    <view class="info-group">
      <view class="info-group-item" @click="routerGo('./groupID')">
        <view class="left">群聊ID号</view>
        <view class="right">
          <u-icon class="icon" name="arrow-right" size="14" color="#999" />
        </view>
      </view>
    </view>
    <view class="info-group" v-show="isInGroupAndNormalPage">
      <view class="info-group-item" @click="toSearch">
        <view class="left">查看聊天记录</view>
        <view class="right">
          <u-icon class="icon" name="arrow-right" size="14" color="#999" />
        </view>
      </view>
    </view>
    <view class="info-group" v-show="isInGroupAndNormalPage">
      <view class="info-group-item">
        <view class="left">消息免打扰</view>
        <view class="right">
          <u-switch
            v-model="messageOptData.status"
            @change="messageOptChange"
          />
        </view>
      </view>
      <view
        class="info-group-item"
        v-show="messageOptData.status"
        @click="messageOptData.actionShow = true"
      >
        <view class="left">群消息设置</view>
        <view class="right">
          <text class="des">{{ currentConversationRecvMessageOpt }}</text>
          <u-icon name="arrow-right" size="24" color="#999" />
        </view>
      </view>
      <view class="info-group-item">
        <view class="left">聊天置顶</view>
        <view class="right">
          <u-switch
            v-model="conversationData.isPinned"
            @change="pinConversation"
          />
        </view>
      </view>
      <view class="info-group-item" @click="modalOperation('clear')">
        <view class="left">清空聊天记录</view>
        <view class="right">
          <u-icon class="icon" name="arrow-right" size="14" color="#999" />
        </view>
      </view>
    </view>
    <view class="info-group" v-show="isInGroupAndNormalPage">
      <view class="info-group-item">
        <view class="left">投诉</view>
        <view class="right">
          <u-icon class="icon" name="arrow-right" size="14" color="#999" />
        </view>
      </view>
    </view>
    <view
      v-show="isInGroupAndNormalPage && !isGroupOwner"
      class="operation operation-quit"
      @click="modalOperation('quit')"
    >
      退出群聊
    </view>
    <view
      v-show="isGroupOwner"
      class="operation operation-quit"
      @click="modalOperation('dismiss')"
    >
      解散该群
    </view>
    <view
      v-show="isInGroup && !isNormalPage && !isDismiss"
      class="operation operation-send"
      @click="toConversation"
    >
      进入群聊
    </view>
    <view
      v-show="!isInGroup && !isDismiss"
      class="operation operation-send"
      @click="routerGo('./send')"
    >
      申请加入该群
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
      :show="groupData.showModal"
      width="500rpx"
      showCancelButton
      confirmText="确定"
      @cancel="groupData.showModal = false"
      @confirm="modalConfirm"
    >
      <view class="modal-content">
        <view class="modal-content-content"> {{ modalContent }}</view>
      </view>
    </u-modal>
    <u-action-sheet
      :show="changeGroupFaceurlData.actionShow"
      :actions="changeGroupFaceurlData.actionList"
      round="16"
      @select="confirmChooseCamera"
      @close="changeGroupFaceurlData.actionShow = false"
    />
  </view>
</template>

<script>
import Avatar from "@/components/Avatar.vue";
import { mapGetters } from "vuex";
import {
  checkPhotoLibraryPermission,
  checkCameraPermission,
} from "@/utils/checkPermission";
export default {
  components: { Avatar },
  data() {
    return {
      groupID: "",
      groupInfo: {
        groupID: "ba752ab976519b29a584fcdf55c7d514",
        groupName: "OpenIM",
        notification: "。。。。",
        introduction: "",
        faceURL:
          "https://open-im-online.rentsoft.cn/storage/openim/1651894363384809106-941881807460162701image_cropper_1651894362085.jpg",
        createTime: 1650955940,
        status: 0,
        creatorUserID: "17396220460",
        groupType: 0,
        ownerUserID: "17396220460",
        memberCount: 6,
        ex: "",
        attachedInfo: "",
      },
      groupMemberList: [{}],
      conversationData: {
        conversationID: "group_ba752ab976519b29a584fcdf55c7d514",
        conversationType: 2,
        userID: "",
        groupID: "ba752ab976519b29a584fcdf55c7d514",
        showName: "OpenIM",
        faceURL:
          "https://open-im-online.rentsoft.cn/storage/openim/1651894363384809106-941881807460162701image_cropper_1651894362085.jpg",
        recvMsgOpt: 0,
        unreadCount: 3,
        groupAtType: 0,
        latestMsg:
          '{"clientMsgID":"7b49ea509098fc488b42f6dae1507a9d","serverMsgID":"b85b0c9b6408d12e4575b3c07e36eca8","createTime":1655090999240,"sendTime":1655090999241,"sessionType":2,"sendID":"xiaohong7","recvID":"17396220460","msgFrom":0,"contentType":1504,"platformID":0,"groupID":"ba752ab976519b29a584fcdf55c7d514","content":"{\\"detail\\":\\"CtMBCiBiYTc1MmFiOTc2NTE5YjI5YTU4NGZjZGY1NWM3ZDUxNBIGT3BlbklNGgzjgILjgILjgILjgIIqd2h0dHBzOi8vb3Blbi1pbS1vbmxpbmUucmVudHNvZnQuY24vc3RvcmFnZS9vcGVuaW0vMTY1MTg5NDM2MzM4NDgwOTEwNi05NDE4ODE4MDc0NjAxNjI3MDFpbWFnZV9jcm9wcGVyXzE2NTE4OTQzNjIwODUuanBnMgsxODg4NjEzODkwNTikrZ6TBkAGWgsxODg4NjEzODkwNRI6CiBiYTc1MmFiOTc2NTE5YjI5YTU4NGZjZGY1NWM3ZDUxNBIJeGlhb2hvbmc3Kgl4aWFvaG9uZzc4AQ==\\",\\"defaultTips\\":\\"xiaohong7 quit group chat\\",\\"jsonDetail\\":\\"{\\\\\\"group\\\\\\":{\\\\\\"groupID\\\\\\":\\\\\\"ba752ab976519b29a584fcdf55c7d514\\\\\\",\\\\\\"groupName\\\\\\":\\\\\\"OpenIM\\\\\\",\\\\\\"notification\\\\\\":\\\\\\"。。。。\\\\\\",\\\\\\"faceURL\\\\\\":\\\\\\"https://open-im-online.rentsoft.cn/storage/openim/1651894363384809106-941881807460162701image_cropper_1651894362085.jpg\\\\\\",\\\\\\"ownerUserID\\\\\\":\\\\\\"18886138905\\\\\\",\\\\\\"createTime\\\\\\":1650955940,\\\\\\"memberCount\\\\\\":6,\\\\\\"creatorUserID\\\\\\":\\\\\\"18886138905\\\\\\"},\\\\\\"quitUser\\\\\\":{\\\\\\"groupID\\\\\\":\\\\\\"ba752ab976519b29a584fcdf55c7d514\\\\\\",\\\\\\"userID\\\\\\":\\\\\\"xiaohong7\\\\\\",\\\\\\"nickname\\\\\\":\\\\\\"xiaohong7\\\\\\",\\\\\\"appMangerLevel\\\\\\":1}}\\"}","seq":4960,"isRead":false,"status":2,"offlinePush":{},"pictureElem":{"sourcePicture":{"size":0,"width":0,"height":0},"bigPicture":{"size":0,"width":0,"height":0},"snapshotPicture":{"size":0,"width":0,"height":0}},"soundElem":{"dataSize":0,"duration":0},"videoElem":{"videoSize":0,"duration":0,"snapshotSize":0,"snapshotWidth":0,"snapshotHeight":0},"fileElem":{"fileSize":0},"mergeElem":{},"atElem":{"isAtSelf":false},"faceElem":{"index":0},"locationElem":{"longitude":0,"latitude":0},"customElem":{},"quoteElem":{},"notificationElem":{"detail":"{\\"group\\":{\\"groupID\\":\\"ba752ab976519b29a584fcdf55c7d514\\",\\"groupName\\":\\"OpenIM\\",\\"notification\\":\\"。。。。\\",\\"faceURL\\":\\"https://open-im-online.rentsoft.cn/storage/openim/1651894363384809106-941881807460162701image_cropper_1651894362085.jpg\\",\\"ownerUserID\\":\\"18886138905\\",\\"createTime\\":1650955940,\\"memberCount\\":6,\\"creatorUserID\\":\\"18886138905\\"},\\"quitUser\\":{\\"groupID\\":\\"ba752ab976519b29a584fcdf55c7d514\\",\\"userID\\":\\"xiaohong7\\",\\"nickname\\":\\"xiaohong7\\",\\"appMangerLevel\\":1}}","defaultTips":"xiaohong7 quit group chat"},"attachedInfoElem":{"groupHasReadInfo":{"hasReadCount":0,"groupMemberCount":0},"isPrivateChat":false,"hasReadTime":0}}',
        latestMsgSendTime: 1655090999241,
        draftText: "",
        draftTextTime: 0,
        isPinned: false,
        isPrivateChat: false,
        isNotInGroup: false,
        attachedInfo: "",
        ex: "",
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
            name: "屏蔽群消息",
            color: "#000",
            fontSize: "16",
            status: "1",
          },
        ],
      },
      groupData: {
        roleLevel_1_num: 1, //获取群主数量
        roleLevel_2_num: 4, //获取管理员数量
        showModal: false,
        modalType: "", //clear:清空聊天记录,quit:退出群聊,dismiss:解散群聊
      },
      changeGroupFaceurlData: {
        actionShow: false,
        actionList: [
          {
            name: "拍照",
            color: "#000",
            fontSize: "16",
            sourceType: "camera",
          },
          {
            name: "从相册选择",
            color: "#000",
            fontSize: "16",
            sourceType: "album",
          },
        ],
      },
      pageStatus: "normal",
    };
  },
  methods: {
    init() {
      this.getGroupsInfo();
      this.getOneConversation();
      this.getGroupMemberList();
    },
    getGroupsInfo() {
      this.$im.getGroupsInfo(this.operationID, [this.groupID], (res) => {
        if (res.errCode === 0) {
          let list = JSON.parse(res.data);
          this.groupInfo = list[0];
          console.log(this.groupInfo);
        }
      });
    },
    getOneConversation() {
      if (this.isInGroupAndNormalPage) {
        this.$im.getOneConversation(
          this.operationID,
          2,
          this.groupID,
          (res) => {
            if (res.errCode === 0) {
              const data = JSON.parse(res.data);
              this.conversationData = data;
              this.messageOptData.status =
                this.conversationData.recvMsgOpt === 0 ? false : true;
              this.messageOptData.statusValue =
                this.conversationData.recvMsgOpt;
            }
          }
        );
      }
    },
    getGroupMemberList() {
      this.groupMemberList = [];
      if (this.groupInfo.memberCount <= 5) {
        this.$im.getGroupMemberList(
          this.operationID,
          this.groupID,
          0,
          0,
          this.groupInfo.memberCount,
          (res) => {
            if (res.errCode === 0) {
              let data = JSON.parse(res.data);
              this.groupMemberList = [...this.groupMemberList, ...data];
            }
          }
        );
      } else {
        this.$im.getGroupMemberList(
          this.operationID,
          this.groupID,
          1,
          0,
          this.groupData.roleLevel_1_num,
          (res) => {
            if (res.errCode === 0) {
              let data = JSON.parse(res.data);
              this.groupMemberList = [...this.groupMemberList, ...data];
            }
          }
        );
        this.$im.getGroupMemberList(
          this.operationID,
          this.groupID,
          2,
          0,
          this.groupData.roleLevel_2_num,
          (res) => {
            if (res.errCode === 0) {
              let data = JSON.parse(res.data);
              this.groupMemberList = [...this.groupMemberList, ...data];
            }
          }
        );
      }
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
          } else {
            this.$store.commit("message/set_indexMessageTimes");
          }
        }
      );
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
    toFriendInfo(item) {
      uni.navigateTo({
        url:
          "/pages/friend/info?id=" + item.userID + "&groupID=" + this.groupID,
      });
    },
    toMemberList(pageStatus) {
      this.$store.commit("group/set_groupInfo", this.groupInfo);
      uni.navigateTo({
        url:
          "@/memberList?groupID=" + this.groupID + "&pageStatus=" + pageStatus,
      });
    },
    toEdit(userID = "") {
      if (this.isGroupOwner || this.isManager || this.userID === userID) {
        uni.navigateTo({
          url: "@/edit?groupID=" + this.groupID + "&userID=" + userID,
        });
      }
    },
    toConversation() {
      uni.navigateTo({
        url: "/pages/conversation/index?sessionType=2&sourceID=" + this.groupID,
      });
    },
    routerGo(url) {
      this.$store.commit("group/set_groupInfo", this.groupInfo);
      uni.navigateTo({
        url: url + "?groupID=" + this.groupID,
      });
    },
    checkPermissionRouterGo(url) {
      if (this.isInGroupAndNormalPage) {
        this.routerGo(url);
      }
    },
    toSearch() {
      uni.navigateTo({
        url:
          "/pages/conversation/messageSearch?conversationID=" +
          this.conversationData.conversationID,
      });
    },
    modalOperation(type) {
      this.groupData.modalType = type;
      this.groupData.showModal = true;
    },
    modalConfirm() {
      switch (this.groupData.modalType) {
        case "clear":
          this.$im.clearGroupHistoryMessageFromLocalAndSvr(
            this.operationID,
            this.groupID,
            (res) => {
              if (res.errCode === 0) {
                this.$store.commit("message/set_clearHistoryMessageTimes");
                this.$toast("清除成功");
              } else {
                this.$toast("清除失败");
              }
              this.groupData.showModal = false;
            }
          );
          break;
        case "quit":
          this.$im.quitGroup(this.operationID, this.groupID, (res) => {
            this.$toast(res.errMsg);
            if (res.errCode === 0) {
              this.$store.commit("message/set_indexMessageTimes");
              uni.switchTab({
                url: "/pages/index/index",
              });
            }
          });
          break;
        case "dismiss":
          this.$im.dismissGroup(this.operationID, this.groupID, (res) => {
            this.$toast(res.errMsg);
            if (res.errCode === 0) {
              this.$store.commit("message/set_indexMessageTimes");
              uni.switchTab({
                url: "/pages/index/index",
              });
            }
          });
          break;
        default:
          break;
      }
    },
    confirmChooseCamera({ sourceType }) {
      switch (sourceType) {
        case "camera":
          this.checkCamera();
          break;
        case "album":
          this.checkPhotoLibrary();
          break;
      }
    },
    async checkPhotoLibrary() {
      const status = await checkPhotoLibraryPermission();
      if (status === true) {
        this.chooseImage("album");
      }
    },
    async checkCamera() {
      const status = await checkCameraPermission();
      if (status === true) {
        this.chooseImage("camera");
      }
    },
    chooseImage(sourceType) {
      wx.chooseImage({
        count: 1,
        sourceType: [sourceType], //从相册选择
        success: (res) => {
          let path = res.tempFilePaths[0];
          if (sourceType === "camera") {
            path = this.convertLocalFileSystemURL(path);
          }
          path = this.formatPath(path);
          this.$im.uploadFile(this.operationID, path);
        },
      });
    },
    convertLocalFileSystemURL(path) {
      //相对路径转换为绝对路径
      // #ifdef H5
      return path;
      // #endif
      // #ifdef APP-PLUS
      return plus.io.convertLocalFileSystemURL(path);
      // #endif
    },
    formatPath(path) {
      const pathList = path.split("file://");
      return pathList[1] || path;
    },
    editGroupFaceurl() {
      if (this.uploadFileRes) {
        const { groupName, notification, introduction, ex } = this.groupInfo;
        this.$im.setGroupInfo(
          this.operationID,
          this.groupID,
          {
            groupName,
            notification,
            introduction,
            faceURL: this.uploadFileRes,
            ex,
          },
          (res) => {
            if (res.errCode === 0) {
              this.$toast("修改成功");
              this.$store.commit("message/set_infoUpdateTimes");
            } else {
              this.$toast(res.errMsg);
            }
          }
        );
      } else {
        this.$toast("图片上传失败");
      }
    },
  },
  onLoad(param) {
    console.log(param);
    this.groupID = param.id;
    this.pageStatus = param.pageStatus || "normal"; //pageStatus:normal,search
    // #ifdef APP-PLUS
    this.init();
    // #endif
  },
  computed: {
    ...mapGetters([
      "operationID",
      "userID",
      "infoUpdateTimes",
      "uploadFileTimes",
      "uploadFileRes",
      "updateGroupInfoTimes",
    ]),
    isNormalPage() {
      return this.pageStatus === "normal";
    },
    isInGroup() {
      return !this.conversationData.isNotInGroup && this.groupMemberList.length
        ? true
        : false;
    },
    isInGroupAndNormalPage() {
      return this.isInGroup && this.isNormalPage ? true : false;
    },
    currentMemberLevel() {
      let level = 0;
      const item =
        this.groupMemberList.filter((i) => i.userID === this.userID)[0] || null;
      if (item) {
        level = item.roleLevel;
      }
      return level;
    },
    isGroupOwner() {
      //是否群主
      return this.isInGroupAndNormalPage &&
        this.groupInfo.ownerUserID === this.userID
        ? true
        : false;
    },
    isManager() {
      //是否管理员
      return this.isInGroupAndNormalPage && this.currentMemberLevel === 3
        ? true
        : false;
    },
    currentConversationRecvMessageOpt() {
      switch (this.messageOptData.statusValue) {
        case 1:
          return "屏蔽群消息";
        case 2:
          return "接收消息但不提醒";
        default:
          return "";
      }
    },
    modalContent() {
      switch (this.groupData.modalType) {
        case "clear":
          return "是否清空聊天记录?";
        case "quit":
          return "是否退出群聊?";
        case "dismiss":
          return "是否解散该群?";
        default:
          return "";
      }
    },
    isDismiss() {
      return this.groupInfo.status === 2 ? true : false;
    },
    groupMemberList2() {
      if (this.isDismiss) {
        return [];
      }
      return this.groupMemberList;
    },
  },
  watch: {
    uploadFileTimes() {
      this.editGroupFaceurl();
    },
    infoUpdateTimes() {
      this.getGroupsInfo();
    },
    updateGroupInfoTimes() {
      this.getGroupsInfo();
    },
  },
};
</script>
<style lang="scss" scoped>
.info {
  background: #f6f6f6;
  min-height: 100vh;
  padding-top: 24rpx;
  .top {
    padding: 36rpx 44rpx;
    margin-bottom: 24rpx;
    display: flex;
    flex-direction: row;
    align-items: center;
    background-color: #fff;
    .name {
      margin-left: 36rpx;
      flex: 1;
      .nickname {
        font-size: 36rpx;
        margin-right: 24rpx;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
      }
    }
    .image,
    .faceURL {
      flex-shrink: 0;
    }
    .image {
      width: 32rpx;
      height: 32rpx;
    }
  }
  .center {
    margin-bottom: 24rpx;
    padding: 36rpx 44rpx 24rpx;
    background-color: #fff;
    &-title {
      // font-size: 26rpx;
      color: #000000;
      display: flex;
      flex-direction: row;
      align-items: center;
      justify-content: space-between;
      .text2 {
        display: flex;
        flex-direction: row;
        color: #999;
        .icon {
          margin-left: 12rpx;
        }
      }
    }
    &-content {
      margin-top: 24rpx;
      display: flex;
      flex-direction: row;
      align-items: center;
      &-item {
        margin-right: 12rpx;
        width: 42px;
        height: 42px;
      }
    }
  }
  &-group {
    background-color: #fff;
    margin-top: 24rpx;
    &-item {
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      align-items: center;
      padding: 28rpx 44rpx;
      border-bottom: 2rpx solid #efefef;
      .left {
        color: #333;
      }
      .right {
        display: flex;
        flex-direction: row;
        font-size: 28rpx;
        color: #999;
        .des {
          margin-right: 12rpx;
        }
        .image {
          width: 36rpx;
          height: 36rpx;
        }
      }
    }
  }
  .operation {
    margin-top: 104rpx;
    margin-bottom: 104rpx;
    font-size: 28rpx;
    padding: 24rpx 0;
    background-color: #fff;
    text-align: center;
    &-quit {
      color: #f85050;
    }
    &-send {
      color: #1d6bed;
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
