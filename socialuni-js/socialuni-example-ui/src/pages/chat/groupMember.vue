<template>
  <view class="bg-default h100p">
    <uni-tag type="primary" text="创建群聊"></uni-tag>
    <uni-list >
      <uni-title type="h1" align="center" title="群成员列表"></uni-title>

    </uni-list>
    <uni-grid :column="6" :show-border="false" :square="false" @change="change">
      <uni-grid-item v-for="(groupMember ,index) in groupMemberList" :index="index" :key="index">
        <view class="grid-item-box">
          <image class="image" :src="groupMember.faceURL" mode="aspectFill" />
          <text class="text">{{groupMember.nickname}}</text>
          <view v-if="groupMember.badge" class="grid-dot">
            <uni-badge :text="groupMember.badge" :type="groupMember.type" />
          </view>
        </view>
      </uni-grid-item>
    </uni-grid>

  </view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import {socialChatFriendModule, socialChatModule, socialUserModule} from "socialuni-sdk/src/store/store"
import {onLoad} from "@dcloudio/uni-app";
import UniUtil from "socialuni-sdk/src/utils/UniUtil";
import DateUtil from "socialuni-sdk/src/utils/DateUtil";
import {
  ChangeGroupMemberMuteParams,
  CreateGroupParams,
  GetGroupMemberByTimeParams, GetGroupMemberParams,
  GroupInitInfo, InviteGroupParams,
  Member, MemberNameParams, SetGroupRoleParams,
  SetGroupVerificationParams, TransferGroupParams
} from "open-im-sdk";
import {GroupMemberItem, GroupRole} from "open-im-sdk/types";

@Options({components: {}})
export default class ChatGroupMemberPage extends Vue {
  groupMemberList: GroupMemberItem[] = []
  groupID = "";
  formatTime(dateStr : number) {
    return DateUtil.parseTime(dateStr * 1000)
  }
  created() {
    onLoad((params) => {
      this.groupID = params.id;
      console.log('this.groupID',this.groupID);
      this.getGroupMemberList();
    })
    // 检查是否为好友
  }

  /**
   * 获取群成员列表。
   */
  getGroupMemberList(){
    const options:GetGroupMemberParams = {
      groupID:this.groupID,
      filter:0,
      offset:0,
      count:20
    }
    socialChatModule.openIm.getGroupMemberList(options).then(({ data })=>{
      this.groupMemberList = JSON.parse(data);
      console.log('getGroupMemberListByJoinTime',data,this.groupMemberList);
    }).catch(err=>{
    })
  }
  change(e){
    const that = this;
    //
    let {
      index
    } = e.detail

    uni.showToast({
      title: `点击第${index+1}个宫格`,
      icon: 'none'
    })
    let groupMember = this.groupMemberList[index];
    //
    console.log(groupMember);
    let itemList = ['禁言1天', '踢出群聊', '转让为群主','设置昵称'];
    // 放入群主.
    if(groupMember.roleLevel !== 2 ){
      itemList.push('转让为群主');
    }
    // 判断是否是管理员还是普通成员
    if(groupMember.roleLevel !== 2){
      itemList.push(  groupMember.roleLevel === 1 ?  '设为管理员' : '设为普通成员');
    }
    //
    uni.showActionSheet({
      itemList,
      success: function (res) {
        console.log('选中了第' + (res.tapIndex + 1) + '个按钮',res,itemList[res.tapIndex]);
        switch (itemList[res.tapIndex]) {
          case '禁言1天':
            that.changeGroupMemberMute(groupMember);
            break;
          case '踢出群聊':
            that.kickGroupMember(groupMember);
            break;
          case '转让为群主':
            that.transferGroupOwner(groupMember);
            break;
          case '设置昵称':
            uni.showModal({
              title: '昵称',
              editable : true,
              placeholderText : '请输入昵称',
              content: groupMember.nickname,
              success: function (res) {
                if (res.confirm) {
                  console.log('用户点击确定',res);
                  that.setGroupMemberNickname(groupMember,res.content);
                } else if (res.cancel) {
                  console.log('用户点击取消');
                }
              }
            });
            break;
          case '设为普通成员':
            that.setGroupMemberRoleLevel(groupMember,1);
          case '设为管理员':
            that.setGroupMemberRoleLevel(groupMember,3);
            break;
        }
      },
      fail: function (res) {
        console.log(res.errMsg);
      }
    });
  }

  /**
   * 修改针对某个群员的禁言状态
   */
  changeGroupMemberMute(groupMember : GroupMemberItem){
    const options:ChangeGroupMemberMuteParams = {
      groupID:this.groupID,
      userID:groupMember.userID,
      mutedSeconds: 60 * 60 * 24   // 禁言时长，为0时即解除禁言
    }
    socialChatModule.openIm.changeGroupMemberMute(options).then(({ data })=>{
      console.log('changeGroupMemberMute',data);
    }).catch(err=>{
    })
  }

  /**
   * 踢出群聊
   * 移除群成员
   */
  kickGroupMember(groupMember : GroupMemberItem){
    const options:InviteGroupParams = {
      groupID: this.groupID,
      reason:"",
      userIDList:[groupMember.userID]
    }
    socialChatModule.openIm.kickGroupMember(options).then(({ data })=>{
      console.log('kickGroupMember',data);
    }).catch(err=>{
    })
  }

  /**
   * 转让群主。
   * @param groupMember
   */
  transferGroupOwner(groupMember : GroupMemberItem){
    const options:TransferGroupParams = {
      groupID: this.groupID,
      newOwnerUserID:groupMember.userID
    }
    socialChatModule.openIm.transferGroupOwner(options).then(({ data })=>{
      console.log('transferGroupOwner',data);
    }).catch(err=>{
    })
  }

  /**
   * 修改某个群员在群内的昵称。
   * @param groupMember
   */
  setGroupMemberNickname(groupMember : GroupMemberItem , GroupMemberNickname : string){
    const options:MemberNameParams = {
      groupID:this.groupID,
      userID:groupMember.userID,
      GroupMemberNickname
    }
    socialChatModule.openIm.setGroupMemberNickname(options).then(({ data })=>{
      console.log('setGroupMemberNickname',data);
    }).catch(err=>{
    })
  }

  /**
   * 修改群成员权限（角色）。
   * @param groupMember
   * @param roleLevel 群成员角色类型 1:普通成员 2:群主 3:管理员
   */
  setGroupMemberRoleLevel(groupMember : GroupMemberItem,roleLevel : GroupRole){
    const options:SetGroupRoleParams = {
      groupID:this.groupID,
      userID:groupMember.userID,
      roleLevel
    }
    socialChatModule.openIm.setGroupMemberRoleLevel(options).then(({ data })=>{
      console.log('setGroupMemberRoleLevel',data);
    }).catch(err=>{
    })
  }
}
</script>
