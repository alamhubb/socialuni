<template>
  <group-member-view ref="groupMemberView"></group-member-view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import {socialChatFriendModule, socialChatModule, socialUserModule} from "socialuni-sdk/src/store/store"
import {onLoad} from "@dcloudio/uni-app";
import UniUtil from "socialuni-sdk/src/utils/UniUtil";
import DateUtil from "socialuni-sdk/src/utils/DateUtil";
import {
  ChangeGroupMemberMuteParams, ChangeGroupMuteParams,
  CreateGroupParams,
  GetGroupMemberByTimeParams, GetGroupMemberParams,
  GroupInitInfo, InviteGroupParams,
  Member, MemberNameParams, SetGroupRoleParams,
  SetGroupVerificationParams, TransferGroupParams
} from "open-im-sdk";


import GroupMemberView from 'socialuni-view/src/views/chat/GroupMemberView.vue'
import {GroupMemberItem, GroupRole, GroupVerificationType} from "open-im-sdk/types";
import {onMounted} from "vue";

@Options({components: {GroupMemberView}})
export default class ChatGroupMemberPage extends Vue {

  created() {
    onLoad((params) => {
      //不这么写refs是空
      onMounted(() => {
        this.$refs.groupMemberView.init(params.id as string);
      })

    })
    // 检查是否为好友
  }

}
</script>

<style lang="scss" scoped>
/* #ifndef APP-NVUE */
view {
  display: flex;
  box-sizing: border-box;
  flex-direction: column;
}

page {
  background-color: #f8f8f8;
}
/* #endif*/

.center {
  flex: 1;
  flex-direction: column;
  background-color: #f8f8f8;
}

.userInfo {
  padding: 20rpx;
  padding-top: 50px;
  background-image: url(../../static/uni-center/headers.png);
  flex-direction: column;
  align-items: center;
}

.logo-img {
  width: 150rpx;
  height: 150rpx;
  border-radius: 150rpx;
}

.logo-title {
  flex: 1;
  align-items: center;
  justify-content: space-between;
  flex-direction: row;
}

.uer-name {
  height: 100rpx;
  line-height: 100rpx;
  font-size: 38rpx;
  color: #FFFFFF;
}

.center-list {
  margin-bottom: 30rpx;
  background-color: #f9f9f9;
}

.center-list-cell {
  width: 750rpx;
  background-color: #007AFF;
  height: 40rpx;
}

.grid {
  background-color: #FFFFFF;
  margin-bottom: 6px;
}

.uni-grid .text {
  font-size: 16px;
  height: 25px;
  line-height: 25px;
  color: #817f82;
}

.uni-grid .item ::v-deep .uni-grid-item__box {
  justify-content: center;
  align-items: center;
}


/*修改边线粗细示例*/
/* #ifndef APP-NVUE */
.center-list ::v-deep .uni-list--border:after {
  -webkit-transform: scaleY(0.2);
  transform: scaleY(0.2);
  margin-left: 80rpx;
}

.center-list ::v-deep .uni-list--border-top,
.center-list ::v-deep .uni-list--border-bottom {
  display: none;
}

/* #endif */
.item-footer {
  flex-direction: row;
  align-items: center;
}

.item-footer-text {
  color: #999;
  font-size: 24rpx;
  padding-right: 10rpx;
}

.item-footer-badge {
  width: 20rpx;
  height: 20rpx;
  /* #ifndef APP-NVUE */
  border-radius: 50%;
  /* #endif */
  /* #ifdef APP-NVUE */
  border-radius: 10rpx;
  /* #endif */
  background-color: #DD524D;
}
</style>