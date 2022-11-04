<template>
  <view class="container">
    <view class="statusBar"></view>
    <u-navbar class="navbar" :title="pageTitle" @leftClick="leftClick">
        <view slot="right"
          class="dot"
          id="addPic"
          @click="showOperationPop"
          v-show="!showCheckBox && showNavDot"
        >
          <text class="dot-text"></text>
          <text class="dot-text"></text>
          <text class="dot-text"></text>
        </view>
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
    <view class="memberList">
      <u-checkbox-group v-model="checkedList" placement="column">
        <view
          class="memberList-item"
          v-for="item in keyWordMemberList"
          :key="item.userID"
        >
          <u-checkbox
            v-show="showCheckBox"
            class="memberList-item-checkbox"
            :name="item.userID"
            shape="circle"
          >
          </u-checkbox>
          <Avatar
            @click="memberItemClick(item)"
            class="faceURL"
            :src="item.faceURL"
            :name="item.nickname"
            size="42px"
          />
          <view class="right" @click="memberItemClick(item)">
            <text class="nickname">{{ item.nickname }}</text>
            <text class="role role-admin" v-show="item.roleLevel === 2">
              群主
            </text>
            <text class="role role-manage" v-show="item.roleLevel === 3">
              管理员
            </text>
            <text class="role role-me" v-show="item.userID === userID">
              我
            </text>
          </view>
        </view>
      </u-checkbox-group>
    </view>
    <view class="footer" v-show="showCheckBox">
      <view class="footer-content">
        <view class="footer-content-left" @click="showPop = true">
          已选择: {{ checkedMemberListLength }}人
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
    <view
      v-show="operationPop.show"
      class="addContent"
      @click="operationPop.show = false"
    >
      <view class="addContent-pop" :style="operationPop.style">
        <view class="addContent-pop-item" @click="oprationPopConfirm('add')">
          <text class="text">邀请群员</text>
        </view>
        <view
          v-show="isGroupOwner || isManager"
          class="addContent-pop-item"
          @click="oprationPopConfirm('del')"
        >
          <text class="text">删除群员</text>
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
          <view class="text"> 已选择：{{ checkedMemberListLength }}人 </view>
          <view class="text" @click="showPop = false"> 确认 </view>
        </view>
        <view class="memberList">
          <view
            class="memberList-item"
            v-for="item in checkedMemberList"
            :key="item.userID"
          >
            <Avatar
              class="faceURL"
              :src="item.faceURL"
              :name="item.nickname"
              size="42px"
            />
            <view class="nickname">
              <text class="text">{{ item.nickname }}</text>
              <u-button
                class="button"
                type="primary"
                plain
                size="mini"
                @click="memberItemClick(item)"
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
      @confirm="btnConfirm"
    >
      <view class="modal-content">
        <view v-if="isShareUserCard" class="modal-content-title">
          确认发送名片：
        </view>
        <view v-else class="modal-content-title">
          {{ isAdd || isCreate ? "确认添加：" : "确认删除群成员：" }}
        </view>
        <view class="modal-content-list">
          <Avatar
            v-for="item in checkedMemberList"
            :key="item.userID"
            class="faceURL"
            :src="item.faceURL"
            :name="item.nickname"
            size="34px"
          />
        </view>
      </view>
    </u-modal>
    <u-modal
      class="modal"
      :show="newOwner.show"
      width="500rpx"
      showCancelButton
      confirmText="确定"
      @cancel="newOwner.show = false"
      @confirm="confirmTransfer"
    >
      <view class="modal-content2">
        确认将群主转让给： {{ newOwner.userInfo.nickname }}?
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
      pageStatus: "normal", //normal,del:删除成员,add:添加成员,transferGroup:转让群主,create:创建群聊
      operationPop: {
        show: false,
        style: {},
      },
      groupID: "",
      shareToUserID: "",
      addUserID: "",
      keyword: "",
      checkedList: [],
      showPop: false,
      modalShow: false,
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
      groupMemberList: [
        {
          groupID: "4039697698",
          userID: "870517699",
          nickname: "小红",
          faceURL:
            "https://storage-online.rentsoft.cn/openim/1657528184835086711-3337066551442961397.jpg",
          roleLevel: 2,
          joinTime: 1657522751,
          joinSource: 0,
          muteEndTime: 0,
          operatorUserID: "870517699",
          ex: "",
          attachedInfo: "",
        },
        {
          groupID: "4039697698",
          userID: "3798435312",
          nickname: "OpenIM-Blooming",
          faceURL: "ic_avatar_06",
          roleLevel: 1,
          joinTime: 1657522798,
          joinSource: 0,
          muteEndTime: 0,
          operatorUserID: "870517699",
          ex: "",
          attachedInfo: "",
        },
        {
          groupID: "4039697698",
          userID: "3935764022",
          nickname: "OpenIM",
          faceURL:
            "https://storage-online.rentsoft.cn/openim/1657264932276937661-4980599588781368494image_cropper_1657264929032.jpg",
          roleLevel: 3,
          joinTime: 1657522751,
          joinSource: 0,
          muteEndTime: 0,
          operatorUserID: "870517699",
          ex: "",
          attachedInfo: "",
        },
        {
          groupID: "4039697698",
          userID: "3493949860",
          nickname: "OpenIM-sk",
          faceURL:
            "https://storage-online.rentsoft.cn/openim/1655861026796104324-1488665021329751810image_cropper_1655861022512.jpg",
          roleLevel: 3,
          joinTime: 1657522798,
          joinSource: 0,
          muteEndTime: 0,
          operatorUserID: "870517699",
          ex: "",
          attachedInfo: "",
        },
      ],
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
      newOwner: {
        userInfo: {
          groupID: "ba752ab976519b29a584fcdf55c7d514",
          userID: "18886138906",
          nickname: "OpenIM测试3",
          faceURL:
            "https://echat-1302656840.cos.ap-chengdu.myqcloud.com/image_cropper_1647849601153.jpg",
          roleLevel: 1,
          joinTime: 1654940329,
          joinSource: 0,
          muteEndTime: 0,
          operatorUserID: "18886138905",
          ex: "",
          attachedInfo: "",
        },
        show: false,
      },
    };
  },
  methods: {
    getGroupInfo() {
      if (this.groupID) {
        this.$im.getGroupsInfo(this.operationID, [this.groupID], (res) => {
          if (res.errCode === 0) {
            let list = JSON.parse(res.data);
            const info = list[0];
            this.$store.commit("group/set_groupInfo", info);
            this.$store.commit("group/set_updateGroupInfoTimes");
            this.init();
          }
        });
      } else {
        this.init();
      }
    },
    init() {
      this.showPop = false;
      this.modalShow = false;
      this.keyword = "";
      this.checkedList = [];
      if (this.addUserID) {
        this.checkedList = [this.addUserID];
        this.addUserID = "";
      }
      if (this.groupID) {
        this.$im.getGroupMemberList(
          this.operationID,
          this.groupID,
          0,
          0,
          this.groupInfo.memberCount,
          (res) => {
            if (res.errCode === 0) {
              let data = JSON.parse(res.data);
              this.groupMemberList = data || [];
            }
          }
        );
        this.$im.getOneConversation(
          this.operationID,
          2,
          this.groupID,
          (res) => {
            if (res.errCode === 0) {
              const data = JSON.parse(res.data);
              this.conversationData = data;
            }
          }
        );
      }
      this.$im.getFriendList(this.operationID, (res) => {
        if (res.errCode === 0) {
          this.friendList = JSON.parse(res.data);
        }
      });
      console.log(this.keyWordMemberList);
    },
    showOperationPop() {
      const query = uni.createSelectorQuery().in(this);
      query
        .select("#addPic")
        .boundingClientRect((data) => {
          const { height, left, top } = data;
          this.operationPop.style = {
            "margin-top": top + height + "px",
            "margin-left": left - 130 + "px",
          };
          this.operationPop.show = true;
        })
        .exec();
    },
    oprationPopConfirm(status) {
      this.keyword = "";
      this.checkedList = [];
      this.pageStatus = status;
    },
    leftClick() {
      this.keyword = "";
      this.checkedList = [];
      if (this.showCheckBox && !this.isShareUserCard) {
        this.pageStatus = "normal";
      } else {
        uni.navigateBack();
      }
    },
    memberItemClick(item) {
      const userID = item.userID;
      if (userID === this.userID) return;
      if (this.pageStatus === "normal") {
        uni.navigateTo({
          url: "/pages/friend/info?id=" + userID + "&groupID=" + this.groupID,
        });
        return;
      }
      if (this.pageStatus === "transferGroup") {
        this.newOwner.userInfo = item;
        this.newOwner.show = true;
        return;
      }
      const index = this.checkedList.findIndex((i) => i === userID);
      if (index === -1) {
        this.checkedList.push(userID);
      } else {
        this.checkedList.splice(index, 1);
      }
    },
    btnConfirm() {
      if (this.isAdd) {
        this.$im.inviteUserToGroup(
          this.operationID,
          this.groupID,
          "",
          this.checkedList,
          (res) => {
            if (res.errCode !== 0) {
              this.$toast(res.errMsg);
            } else {
              this.$toast("邀请成功");
            }
            this.pageStatus = "normal";
            this.getGroupInfo();
          }
        );
      } else if (this.pageStatus === "del") {
        this.$im.kickGroupMember(
          this.operationID,
          this.groupID,
          "",
          this.checkedList,
          (res) => {
            if (res.errCode !== 0) {
              this.$toast(res.errMsg);
            } else {
              this.$toast("移除成功");
            }
            this.pageStatus = "normal";
            this.getGroupInfo();
          }
        );
      } else if (this.isCreate) {
        this.$im.createGroup(
          this.operationID,
          this.groupInfo,
          this.checkedList.map((i) => {
            return {
              userID: i, //用户ID
              roleLevel: 1, //设置角色 1:普通成员 3:管理员
            };
          }),
          (res) => {
            if (res.errCode !== 0) {
              this.$toast(res.errMsg);
            } else {
              this.$toast("创建成功");
              this.$store.commit("message/set_indexMessageTimes");
              setTimeout(() => {
                uni.switchTab({
                  url: "/pages/index/index",
                });
              }, 1000);
            }
          }
        );
      } else if (this.isShareUserCard) {
        this.checkedMemberList.map((memberItem) => {
          const memberCard = this.$im.createCardMessage(
            this.operationID,
            memberItem
          );
          // #ifdef APP-PLUS
          this.$im.sendMessage(
            this.operationID,
            memberCard,
            this.shareToUserID,
            this.groupID,
            {
              title: "你有一条新消息",
              desc: "",
              ex: "",
              iOSPushSound: "+1",
              iOSBadgeCount: true,
            }
          );
          // #endif
        });
        this.$toast("发送成功");
        setTimeout(() => {
          uni.navigateBack();
        }, 1000);
      }
    },
    confirmTransfer() {
      this.$im.transferGroupOwner(
        this.operationID,
        this.groupID,
        this.newOwner.userInfo.userID,
        (res) => {
          this.newOwner.show = false;
          if (res.errCode === 0) {
            this.$toast("转让成功");
            uni.navigateBack();
          } else {
            this.$toast(res.errMsg);
          }
        }
      );
    },
  },
  onLoad(param) {
    this.groupID = param.groupID || "";
    this.shareToUserID = param.userID || "";
    this.pageStatus = param.pageStatus || "normal";
    if (param.addUserID) {
      this.addUserID = param.addUserID;
    }
    // #ifdef APP-PLUS
    this.init();
    // #endif
  },
  computed: {
    ...mapGetters([
      "userID",
      "operationID",
      "operationMessageItem",
      "groupInfo",
    ]),
    btnDisabled() {
      return this.checkedMemberListLength <= 0;
    },
    memberList() {
      if (this.isAdd) {
        return this.notInFriendList;
      }
      if (this.isCreate || this.isShareUserCard) {
        return this.friendList.map((i) => i.friendInfo);
      }
      const adminList = this.groupMemberList.filter((i) => i.roleLevel === 2); //群主
      const manageList = this.groupMemberList.filter((i) => i.roleLevel === 3); //管理员
      const normalList = this.groupMemberList.filter((i) => i.roleLevel === 1); //普通成员
      const adminIndex = adminList.findIndex((i) => i.userID === this.userID);
      const manageIndex = manageList.findIndex((i) => i.userID === this.userID);
      const normalIndex = normalList.findIndex((i) => i.userID === this.userID);
      if (adminIndex >= 0) {
        adminList.unshift(adminList.splice(adminIndex, 1)[0]);
      } else if (manageIndex >= 0) {
        manageList.unshift(manageList.splice(manageIndex, 1)[0]);
      } else if (normalIndex >= 0) {
        normalList.unshift(normalList.splice(normalIndex, 1)[0]);
      }
      const list = [...adminList, ...manageList, ...normalList];
      console.log(adminIndex, manageIndex, normalIndex, list, this.userID);
      return list;
    },
    memberListNotSelf() {
      return this.memberList.filter((i) => {
        return i.userID !== this.userID;
      });
    },
    notInFriendList() {
      const groupUserIDList = this.groupMemberList.map((i) => i.userID);
      return this.friendList
        .filter((i) => {
          const userID = i.friendInfo.userID;
          return !groupUserIDList.includes(userID);
        })
        .map((i) => i.friendInfo);
    },
    keyWordMemberList() {
      if (!this.keyword) {
        return this.memberList;
      }
      return this.memberList.filter((i) => {
        return (
          i.userID.includes(this.keyword) || i.nickname.includes(this.keyword)
        );
      });
    },
    checkedMemberList() {
      return this.memberListNotSelf.filter((i) => {
        return this.checkedList.includes(i.userID);
      });
    },
    checkedMemberListLength() {
      return this.checkedMemberList.length || 0;
    },
    checkedTotal() {
      return this.checkedMemberListLength;
    },
    total() {
      return this.memberList.length;
    },
    confirmText() {
      return `确定（${this.checkedTotal}）`;
    },
    pageTitle() {
      switch (this.pageStatus) {
        case "normal":
          return `群成员 (${this.total})`;
        case "del":
          return "删除群成员";
        case "add":
          return "添加群成员";
        case "transferGroup":
          return "选择新群主";
        case "create":
          return "添加群成员";
        case "shareUserCard":
          return "分享名片";
        default:
          return "";
      }
    },
    showNavDot() {
      return (
        this.pageStatus !== "transferGroup" &&
        !this.isCreate &&
        !this.isShareUserCard
      );
    },
    showCheckBox() {
      return (
        this.pageStatus === "del" ||
        this.isAdd ||
        this.isCreate ||
        this.isShareUserCard
      );
    },
    isAdd() {
      return this.pageStatus === "add";
    },
    isCreate() {
      return this.pageStatus === "create";
    },
    isShareUserCard() {
      return this.pageStatus === "shareUserCard";
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
      return this.groupInfo.ownerUserID === this.userID ? true : false;
    },
    isManager() {
      //是否管理员
      return this.currentMemberLevel === 3 ? true : false;
    },
  },
};
</script>

<style lang="scss" scoped>
$pdLR: 24rpx;
.container {
  background-color: #fff;
  .navbar {
    height: 44px;
    .dot {
      height: 100%;
      display: flex;
      flex-direction: row;
      align-items: center;
      justify-content: space-between;
      &-text {
        border-radius: 50%;
        width: 10rpx;
        height: 10rpx;
        background: #000000;
        &:nth-of-type(2) {
          margin: 0 6rpx;
        }
      }
    }
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
  .memberList {
    &-item {
      padding-top: 28rpx;
      display: flex;
      flex-direction: row;
      align-items: center;
      &-checkbox {
        margin-left: 24rpx;
        height: 42px;
        ::v-deep .u-checkbox__icon-wrap {
          margin-right: 0;
        }
      }
      .faceURL {
        margin-left: $pdLR;
        flex-shrink: 0;
      }
      .right {
        flex: 1;
        margin-left: $pdLR;
        padding-bottom: 12rpx;
        padding-right: 24rpx;
        border-bottom: 2rpx solid #f0f0f0;
        display: flex;
        flex-direction: row;
        align-items: center;
        .nickname {
          height: 42px;
          line-height: 42px;
          overflow: hidden;
          text-overflow: ellipsis;
          white-space: nowrap;
        }
        .role {
          flex-shrink: 0;
          width: 100rpx;
          height: 38rpx;
          line-height: 38rpx;
          border-radius: 18rpx;
          text-align: center;
          margin-left: 16rpx;
          font-size: 24rpx;
          color: #fff;
          font-weight: 500;
          &-admin {
            background-color: #fddfa1;
            color: #ff8c00;
          }
          &-manage {
            background-color: #a2c9f8;
            color: #2691ed;
          }
          &-me {
            background-color: #2691ed;
            color: #fff;
          }
        }
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
    .memberList {
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
  .addContent {
    background-color: rgba(0, 0, 0, 0.5);
    position: fixed;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    z-index: 999;
    &-pop {
      background-color: #fff;
      width: 130px;
      border-radius: 12rpx;
      &-item {
        display: flex;
        align-items: center;
        justify-content: center;
        padding: 18rpx 0;
        border-bottom: 2rpx solid #f0f0f0;
        .text {
          font-size: 14px;
          color: #333333;
        }
        &:last-of-type {
          border-bottom: none;
        }
      }
    }
  }
  .modal {
    ::v-deep .u-modal__content {
      padding: 0;
      padding-top: 0 !important;
    }
    &-content2 {
      padding: 24rpx 40rpx;
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
