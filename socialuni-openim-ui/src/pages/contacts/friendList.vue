<template>
  <view class="friendList">
    <u-sticky>
      <view class="search">
        <u-search
          v-model="searchContent"
          shape="square"
          placeholder="搜索好友"
          :showAction="false"
        />
      </view>
    </u-sticky>
    <view
      class="card"
      v-for="item in friendList"
      :key="item.friendInfo.userID"
      @click="toUserInfo(item)"
    >
      <Avatar
        class="Avatar"
        :src="item.friendInfo.faceURL"
        :name="item.friendInfo.remark || item.friendInfo.nickname"
        size="42px"
      />
      <view class="right">
        <view class="name">
          {{ item.friendInfo.remark || item.friendInfo.nickname }}
        </view>
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
      searchContent: "",
      timer: null,
    };
  },
  methods: {
    init() {
      this.$im.getFriendList(this.operationID, (res) => {
        if (res.errCode === 0) {
          this.list = JSON.parse(res.data);
          this.list = this.list.filter((i) => !i.blackInfo);
        }
      });
    },
    toUserInfo(item) {
      uni.navigateTo({
        url: "/pages/friend/info?id=" + item.friendInfo.userID,
      });
    },
  },
  computed: {
    ...mapGetters([
      "operationID",
      "userID",
      "indexMessageTimes",
      "frinendChangeTimes",
    ]),
    friendList() {
      let list = this.list;
      if (this.searchContent) {
        list = list.filter((i) => {
          return (
            i.friendInfo.nickname.includes(this.searchContent) ||
            i.friendInfo.userID.includes(this.searchContent) ||
            i.friendInfo.phoneNumber.includes(this.searchContent)
          );
        });
      }
      return list;
    },
  },
  allTimes() {
    return this.indexMessageTimes + this.frinendChangeTimes;
  },
  watch: {
    allTimes() {
      if (this.timer) {
        clearTimeout(this.timer);
      }
      this.timer = setTimeout(() => {
        this.init();
      }, 500);
    },
  },
  onLoad() {
    this.init();
  },
};
</script>

<style lang="scss" scoped>
$pagePadding: 44rpx;
.friendList {
  background-color: #f8f8f8;
  min-height: 100vh;
  .search {
    background-color: #fff;
    padding: 24rpx $pagePadding 0;
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
      align-items: center;
      .name {
        font-size: 32rpx;
        font-weight: 500;
        color: #333333;
      }
    }
  }
}
</style>