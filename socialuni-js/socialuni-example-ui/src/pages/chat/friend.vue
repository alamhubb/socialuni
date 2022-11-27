<template>
  <view class="bg-default h100p">
<!--    <input class="uni-input" focus placeholder="自动获得焦点" @confirm="addFriend"/>-->


    <view class="row-between-center py-xs pr-xs">
      <view class="flex-row flex-1" >
        <view v-if="user.hasFriend" class="px-lg line-height-1" @click.stop="deleteFriend">
          <text class="text-sm text-gray">解除好友</text>
        </view>
        <view v-else class="px-lg line-height-1" @click.stop="addFriend">
          <text class="text-sm text-gray">添加好友</text>
        </view>
        <view class="px-lg line-height-1" @click.stop="addBlack">
          <text class="text-sm text-gray">添加黑名单</text>
        </view>
      </view>
    </view>



    <uni-list>
      <uni-list-item title="我发出的好友请求列表::::"></uni-list-item>
      <uni-list-chat v-for="(sendFriendApplication,index) in sendFriendApplicationList" :title="sendFriendApplication.fromNickname"   :avatar="sendFriendApplication.fromFaceURL" :note="sendFriendApplication.reqMsg" :time="sendFriendApplication.createTime" badge-positon="left" badge-text="dot">
        <view class="chat-custom-right">
          <text class="chat-custom-text">{{ formatTime(sendFriendApplication.createTime) }}</text>

          <!-- 需要使用 uni-icons 请自行引入 -->
          <!--          <uni-icons type="star-filled" color="#999" size="18"></uni-icons>-->
        </view>
        <view class="chat-custom-right">
          <uni-tag type="primary" text="同意"></uni-tag>
          <uni-tag type="success" text="拒绝"></uni-tag>
        </view>
      </uni-list-chat>
    </uni-list>



    <uni-list>
      <uni-list-item title="好友请求列表::::"></uni-list-item>
      <uni-list-chat v-for="(recvFriendApplication,index) in recvFriendApplicationList" :title="recvFriendApplication.fromNickname"   :avatar="recvFriendApplication.fromFaceURL" :note="recvFriendApplication.reqMsg">
        <view class="chat-custom-right">
          <text class="chat-custom-text">{{ formatTime(recvFriendApplication.createTime) }}</text>
          <div class="font-xs" > 当前状态为: {{ friendRuestResult(recvFriendApplication.handleResult) }}</div>
        </view>
        <view class="chat-custom-right">
          <text class="chat-custom-text" @click.stop="acceptFriendApplication(recvFriendApplication)" >同意</text>
          <text class="chat-custom-text" @click.stop="refuseFriendApplication(recvFriendApplication)">拒绝</text>
        </view>
      </uni-list-chat>
    </uni-list>

    <uni-list>
      <uni-list-item title="黑名单列表::::"></uni-list-item>
      <uni-list-chat v-for="(black,index) in blackList" :title="black.nickname"   :avatar="black.fromFaceURL" :note="black.reqMsg">
        <view class="chat-custom-right">
          <text class="chat-custom-text">{{  formatTime(black.createTime)   }}</text>
          <div class="font-xs" > 当前状态为: {{ friendRuestResult(black.handleResult) }}</div>
        </view>
        <view class="chat-custom-right">
          <text class="chat-custom-text" @click.stop="removeBlack(black)" >从黑名单移除</text>
        </view>
      </uni-list-chat>
    </uni-list>


    <uni-list>
      <uni-list-item title="已添加的好友列表::::"></uni-list-item>
      <uni-list-chat v-for="(friend,index) in friendList" :title="friend.friendInfo?.nickname || friend.blackInfo?.nickname"   :avatar="friend.fromFaceURL" :note="friend.reqMsg">
        <view class="chat-custom-right">
          <text class="chat-custom-text">{{ formatTime(friend.friendInfo?.createTime)  }}</text>
          <div class="font-xs" > 当前状态为: {{ friendRuestResult(friend.handleResult) }}</div>
        </view>
        <view class="chat-custom-right">
          <text class="chat-custom-text" @click.stop="removeBlack(friend)" >从黑名单移除</text>
        </view>
      </uni-list-chat>
    </uni-list>


<!--    <view class="row-col-center" >
      <div class="font-xs">已添加的好友列表::::</div>
      <div v-for="(friend,index) in friendList" >
        <div class="font-xs" v-if="friend.friendInfo"> 没拉黑的好友: {{friend.friendInfo.nickname}}</div>
        <div class="font-xs" v-if="friend.blackInfo"> 被拉黑的好友: {{friend.blackInfo.nickname}}</div>
      </div>
    </view>-->

  </view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import {socialChatFriendModule, socialChatModule} from "socialuni-sdk/src/store/store"
import {onLoad} from "@dcloudio/uni-app";
import UniUtil from "socialuni-sdk/src/utils/UniUtil";
import DateUtil from "socialuni-sdk/src/utils/DateUtil";

@Options({components: {}})
export default class ChatFriendPage extends Vue {
  sendFriendApplicationList: Object[] = []
  recvFriendApplicationList: Object[] = []
  blackList: Object[] = []
  friendList: Object[] = []
  hasFriend = false;

  formatTime(dateStr) {
    return DateUtil.parseTime(dateStr*1000)
  }
  // addFriend(e) {
  //   console.error('=================',e);
  //   socialChatFriendModule.addFriend(e.detail.value,"请求加好友");
  // }
  created() {
    onLoad((params) => {
      this.getSendFriendApplicationList();
      this.getRecvFriendApplicationList();
      this.getBlacklist();
      this.getFriendList();
    })

    // 检查是否为好友

  }
  /**
   * 获取好友列表。
   */
  getFriendList(){
    socialChatModule.openIm.getFriendList().then(({ data })=>{
      this.friendList = JSON.parse(data);
      console.log('friendList',data,this.friendList);
    }).catch(err=>{
    })
  }
  /**
   * 添加好友申请。
   */
  addFriend() {
    // socialChatFriendModule.addFriend(this.user.id, "请求加好友");
    const options:AddFriendParams = {
      toUserID: this.user.id,
      reqMsg: "请求加好友"
    };
    socialChatModule.openIm.addFriend(options).then(({ data })=>{
      console.error('addFriend susueces',data);
      // 需要同意才会现实的。所以这里再查也是浪费。
      // this.checkFriend();
    }).catch(err=>{
      console.error(err);
    })
  }

  /**
   * 从好友列表中删除用户。
   */
  deleteFriend() {
    socialChatModule.openIm.deleteFriend(this.user.id).then(({data}) => {
      console.log('deleteFriend',data);
      // socialChatModule.checkFriend(this.user);
    }).catch(err => {
    })
  }

  /**
   * 获取黑名单列表
   */
  getBlacklist(){
    socialChatModule.openIm.getBlackList().then(({ data })=>{
      this.blackList = JSON.parse(data);
      console.log('getBlackList',data,this.blackList);
    }).catch(err=>{
    })
  }
  /**
   * 将用户添加到黑名单。
   */
  addBlack() {
    socialChatModule.openIm.addBlack(this.user.id).then(({data}) => {
      socialChatModule.checkFriend(this.user);
    }).catch(err => {
    })
  }
  /**
   * 将用户添加到黑名单。
   */
  removeBlack(black) {
    socialChatModule.openIm.removeBlack(black.userID).then(({data}) => {
      socialChatModule.checkFriend(this.user);
      this.getBlacklist();
    }).catch(err => {
    })
  }

  /**
   * 获取发出的好友请求列表
   */
  getSendFriendApplicationList(){
    socialChatModule.openIm.getSendFriendApplicationList().then(({ data })=>{
      this.sendFriendApplicationList = JSON.parse(data);
      console.log('getSendFriendApplicationList',data,this.sendFriendApplicationList);
    }).catch(err=>{
    })

  }
  /**
   * 获取收到的好友请求列表
   */
  getRecvFriendApplicationList(){
    socialChatModule.openIm.getRecvFriendApplicationList().then(({ data })=>{
      this.recvFriendApplicationList = JSON.parse(data);
      console.log('getRecvFriendApplicationList',data,this.recvFriendApplicationList);
    }).catch(err=>{
    })
  }
  /**
   * 接受好友请求。
   * @param item
   */
  acceptFriendApplication(item){
    const options:AccessFriendParams = {
      toUserID:item.fromUserID,
      handleMsg:"接受您的好友请求"
    }
    socialChatModule.openIm.acceptFriendApplication(options).then(({ data })=>{
      console.log('acceptFriendApplication',data);
      // 刷新请求列表
      this.getRecvFriendApplicationList();
    }).catch(err=>{
    })
  }

  /**
   * 拒绝好友请求。
   * @param item
   */
  refuseFriendApplication(item){
    const options:AccessFriendParams = {
      toUserID:item.fromUserID,
      handleMsg:"拒绝您的好友请求"
    }
    socialChatModule.openIm.refuseFriendApplication(options).then(({ data })=>{
      console.log('refuseFriendApplication',data);
      // 刷新请求列表
      this.getRecvFriendApplicationList();
    }).catch(err=>{
    })
  }
  friendRuestResult(status:number){
    switch (status){
      case 0:
        return "未处理中";
      case 1:
        return "已同意";
      case -1:
        return "已拒绝";
    }
  }
}
</script>
