<template>
  <view class="bg-default center">
    <q-navbar show-back :title="title">
      <div class="row-end-center flex-1">
        <q-icon icon="list-dot" size="20" @click="openMoreMenu"></q-icon>
      </div>
    </q-navbar>
    <uni-grid class="grid" :column="5" :show-border="false" :square="false" @change="change">
      <uni-grid-item v-for="(groupMember ,index) in groupMemberList" :index="index" :key="index">
        <view class="grid-item-box">
          <image class="size100" :src="groupMember.faceURL" mode="" />
          <text class="text">{{groupMember.nickname}}</text>
          <view v-if="groupMember.badge" class="grid-dot">
            <uni-badge :text="groupMember.badge" :type="groupMember.type" />
          </view>
        </view>
      </uni-grid-item>


    </uni-grid>
    <view  v-show="moreGroupMember" class="row-end-center uni-loadmore" @click="getGroupMemberList">
      查看更多用户
    </view>
<!--    <uni-load-more :status="moreGroupMember"-->
<!--                   @click="getGroupMemberList"-->
<!--                   :contentText="loadMoreText"></uni-load-more>-->
    <uni-list class="center-list" >
      <uni-list-item title="退出群" link :clickable="true"  @click="quitGroup"></uni-list-item>
      <uni-list-item v-if="meRoleLevel == 2" title="解散群" link :clickable="true"  @click="dismissGroup"></uni-list-item>
      <uni-list-item v-if="meRoleLevel > 2" title="开启群禁言，所有普通群成员禁止发言" link :clickable="true"  @click="changeGroupMute(true)"></uni-list-item>
      <uni-list-item v-if="meRoleLevel > 2" title="关闭群禁言" link :clickable="true"  @click="changeGroupMute(false)"></uni-list-item>
      <uni-list-item v-if="meRoleLevel > 2" title="设置进群验证规则:邀请需要验证" link :clickable="true"  @click="setGroupVerification(0)"></uni-list-item>
      <uni-list-item v-if="meRoleLevel > 2" title="设置进群验证规则:都需要验证" link :clickable="true"  @click="setGroupVerification(1)"></uni-list-item>
      <uni-list-item v-if="meRoleLevel > 2" title="设置进群验证规则:都不需要验证" link :clickable="true"  @click="setGroupVerification(2)"></uni-list-item>
      <uni-list-item title="标记群聊会话已读" link :clickable="true"  @click="markGroupMessageHasRead"></uni-list-item>
      <uni-list-item title="清空本地群聊消息记录" link :clickable="true"  @click="clearGroupHistoryMessage"></uni-list-item>
      <uni-list-item title="删除本地跟服务器的群聊天记录" link :clickable="true"  @click="clearGroupHistoryMessageFromLocalAndSvr"></uni-list-item>
      <!--   退出群，解散群，开启群禁言，设置进群验证规则，清空群聊消息记录   -->

    </uni-list>

<!--
    <uni-list class="center-list" v-for="(sublist , index) in ucenterList" :key="index">
      <uni-list-item v-for="(item,i) in sublist" :title="item.title" link :rightText="item.rightText" :key="i"
                     :clickable="true" :to="item.to" @click="ucenterListClick(item)" :show-extra-icon="true"
                     :extraIcon="{type:item.icon,color:'#999'}">
      </uni-list-item>
    </uni-list>
-->

  </view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import {socialChatFriendModule, socialChatModule, socialuniUserModule} from "socialuni-sdk/src/store/store"
import {onLoad} from "@dcloudio/uni-app";
import UniUtil from "socialuni-app/src/util/UniUtil";
import DateUtil from "socialuni-util/src/util/DateUtil";
import {
  ChangeGroupMemberMuteParams, ChangeGroupMuteParams,
  CreateGroupParams,
  GetGroupMemberByTimeParams, GetGroupMemberParams,
  GroupInitInfo, InviteGroupParams,
  Member, MemberNameParams, SetGroupRoleParams,
  SetGroupVerificationParams, TransferGroupParams
} from "socialuni-sdk/src/plugins/open-im-sdk";
import {GroupMemberItem, GroupRole, GroupVerificationType} from "socialuni-sdk/src/plugins/open-im-sdk";
import QNavbar from "../../components/QNavbar/QNavbar.vue";
import MessageViewParams from "./MessageViewParams";
import PageUtil from "socialuni-util/src/util/PageUtil";

@Options({components: {QNavbar}})
export default class GroupMemberView extends Vue {
  groupMemberList: GroupMemberItem[] = []
  moreGroupMember = true;
  offset = 0;
  groupID = "";
  groupInfo = {
    roleLevel : 0
  };
  loadMoreText = {
    contentdown: '点击显示更多',
    contentrefresh: '正在加载...',
    contentnomore: '没有更多数据了,点击刷新'
  }
  ucenterList=  [
    [],
    [{
      "title": '退出群，解散群，开启群禁言，设置进群验证规则，清空群聊消息记录，删除本地跟服务器的群聊天记录  // 会话',
      "to": '/pages/ucenter/userinfo/userinfo',
      "icon": "gear"
    }/* , {
						"title": this.$t('mine.settings'),
						"to": '/pages/ucenter/settings/settings',
						"icon": "gear"
					} */],
    []
  ]

  /**
   * 当前的权限：
   * 群成员角色类型 1:普通成员 2:群主 3:管理员
   */
  get meRoleLevel() {
    return this.groupInfo.roleLevel;
  }

  formatTime(dateStr : number) {
    return DateUtil.parseTime(dateStr * 1000)
  }

  init(groupID: string) {
    this.groupID = groupID;
    console.log('this.groupID',this.groupID);
    this.getGroupMemberList();
    // 设置菜单权限。
    this.getGroupMembersInfo();
  }
  /**
   * 置零群聊会话未读数
   */
  async markGroupMessageHasRead(){
    (await socialChatModule.openIm()).markGroupMessageHasRead(this.groupID).then(({ data })=>{
      console.log('markGroupMessageHasRead',data);
    }).catch(err=>{
    })
  }
  /**
   * 删除本地跟服务器的群聊天记录
   */
  async clearGroupHistoryMessageFromLocalAndSvr(){
    (await socialChatModule.openIm()).clearGroupHistoryMessageFromLocalAndSvr(this.groupID).then(({ data })=>{
      console.log('clearGroupHistoryMessageFromLocalAndSvr',data);
    }).catch(err=>{
    })
  }
  /**
   * 从本地删除指定群聊会话中所有消息
   */
  async clearGroupHistoryMessage(){
    (await socialChatModule.openIm()).clearGroupHistoryMessage(this.groupID).then(({ data })=>{
      console.log('clearGroupHistoryMessage',data);
    }).catch(err=>{
    })
  }
  /**
   * 设置进群验证规则
   */
  async setGroupVerification(verification: GroupVerificationType){
    const options:SetGroupVerificationParams = {
      groupID: this.groupID ,
      verification
    }
    ;(await socialChatModule.openIm()).setGroupVerification(options).then(({ data })=>{
      console.log('setGroupVerification',data);
    }).catch(err=>{
    })
  }
  /**
   * 开启群禁言，所有普通群成员禁止发言
   */
  async changeGroupMute(isMute : boolean){
    const options:ChangeGroupMuteParams = {
      groupID: this.groupID ,
      isMute
    }
    ;(await socialChatModule.openIm()).changeGroupMute(options).then(({ data })=>{
      console.log('changeGroupMute',data);
    }).catch(err=>{
    })
  }
  /**
   * 解散群聊
   */
  async dismissGroup(){
    (await socialChatModule.openIm()).dismissGroup(this.groupID).then(({ data })=>{
      console.log('dismissGroup',data);
    }).catch(err=>{
    })
  }
  /**
   * 退出群聊
   */
  async quitGroup(){
    (await socialChatModule.openIm()).quitGroup(this.groupID).then(({ data })=>{
      console.log('quitGroup',data);
    }).catch(err=>{
    })
  }
  /**
   * 获取群成员列表。
   */
  async getGroupMemberList(){
    const count = 20;
    const options:GetGroupMemberParams = {
      groupID:this.groupID,
      filter:0,
      offset:this.offset,
      count
    }
    ;(await socialChatModule.openIm()).getGroupMemberList(options).then(({ data })=>{
      let parse = JSON.parse(data);
      this.groupMemberList.push(...parse);
      //
      this.offset += count;
      // 设置不展示更多。
      if(parse.length != count){
        this.moreGroupMember = false;
      }
      console.log('getGroupMemberListByJoinTime',data,this.groupMemberList,this.offset);
    }).catch(err=>{
    })


  }

  async getGroupMembersInfo(){
    const options:Omit<InviteGroupParams, "reason"> = {
      groupID:this.groupID,
      userIDList:[socialuniUserModule.userId]
    }
    //
    ;(await socialChatModule.openIm()).getGroupMembersInfo(options).then(({ data })=>{
      const arr = JSON.parse(data);
      this.groupInfo = arr[0];
      console.log('----getGroupMembersInfo-----',JSON.parse(data),this.groupInfo);
    }).catch(err=>{
    })
  }

  change(e){
    const that = this;
    //
    let {
      index
    } = e.detail


    const groupMember =  this.groupMemberList[index];
    let itemList = ['查看信息',];
    // 不是选中的自己
    if(groupMember.userID != this.groupInfo.userID){
      // 有管理权限
      if(this.meRoleLevel > 1){
        itemList.push(...['禁言1天', '踢出群聊','设置昵称']);
      }
      // 群主特殊处理
      if(this.meRoleLevel == 2){
        // 放入群主.
        if(groupMember.roleLevel !== 2 ){
          itemList.push('转让为群主');
        }
        // 判断是否是管理员还是普通成员
        if(groupMember.roleLevel !== 2){
          itemList.push(  groupMember.roleLevel === 1 ?  '设为管理员' : '设为普通成员');
        }
      }

    }




    //
    uni.showActionSheet({
      itemList,
      success: function (res) {
        console.log('选中了第' + (res.tapIndex + 1) + '个按钮',res,itemList[res.tapIndex]);
        switch (itemList[res.tapIndex]) {
          case '查看信息':
            PageUtil.toUserDetail( groupMember.userID);
            break;
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
  async changeGroupMemberMute(groupMember : GroupMemberItem){
    const options:ChangeGroupMemberMuteParams = {
      groupID:this.groupID,
      userID:groupMember.userID,
      mutedSeconds: 60 * 60 * 24   // 禁言时长，为0时即解除禁言
    }
    ;(await socialChatModule.openIm()).changeGroupMemberMute(options).then(({ data })=>{
      console.log('changeGroupMemberMute',data);
    }).catch(err=>{
    })
  }

  /**
   * 踢出群聊
   * 移除群成员
   */
  async kickGroupMember(groupMember : GroupMemberItem){
    const options:InviteGroupParams = {
      groupID: this.groupID,
      reason:"",
      userIDList:[groupMember.userID]
    }
    ;(await socialChatModule.openIm()).kickGroupMember(options).then(({ data })=>{
      console.log('kickGroupMember',data);
    }).catch(err=>{
    })
  }

  /**
   * 转让群主。
   * @param groupMember
   */
  async transferGroupOwner(groupMember : GroupMemberItem){
    const options:TransferGroupParams = {
      groupID: this.groupID,
      newOwnerUserID:groupMember.userID
    }
    ;(await socialChatModule.openIm()).transferGroupOwner(options).then(({ data })=>{
      console.log('transferGroupOwner',data);
    }).catch(err=>{
    })
  }

  /**
   * 修改某个群员在群内的昵称。
   * @param groupMember
   */
  async setGroupMemberNickname(groupMember : GroupMemberItem , GroupMemberNickname : string){
    const options:MemberNameParams = {
      groupID:this.groupID,
      userID:groupMember.userID,
      GroupMemberNickname
    }
    ;(await socialChatModule.openIm()).setGroupMemberNickname(options).then(({ data })=>{
      console.log('setGroupMemberNickname',data);
    }).catch(err=>{
    })
  }

  /**
   * 修改群成员权限（角色）。
   * @param groupMember
   * @param roleLevel 群成员角色类型 1:普通成员 2:群主 3:管理员
   */
  async setGroupMemberRoleLevel(groupMember : GroupMemberItem,roleLevel : GroupRole){
    const options:SetGroupRoleParams = {
      groupID:this.groupID,
      userID:groupMember.userID,
      roleLevel
    }
    ;(await socialChatModule.openIm()).setGroupMemberRoleLevel(options).then(({ data })=>{
      console.log('setGroupMemberRoleLevel',data);
    }).catch(err=>{
    })
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
