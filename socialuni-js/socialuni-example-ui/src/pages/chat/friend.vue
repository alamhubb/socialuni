<template>
  <view class="bg-default h100p">
<!--    <input class="uni-input" focus placeholder="自动获得焦点" @confirm="addFriend"/>-->

    <uni-list v-if="type === 'sendFriendApplication'">
      <uni-title type="h1" align="center" title="我发出的好友请求列表"></uni-title>
      <uni-list-chat v-for="(sendFriendApplication,index) in sendFriendApplicationList" :title="sendFriendApplication.toNickname"   :avatar="sendFriendApplication.toFaceURL" :note="sendFriendApplication.reqMsg">
        <view class="chat-custom-right">
          <text class="chat-custom-text">{{ formatTime(sendFriendApplication.createTime) }}</text>

          <!-- 需要使用 uni-icons 请自行引入 -->
          <!--          <uni-icons type="star-filled" color="#999" size="18"></uni-icons>-->
        </view>
        <view class="chat-custom-right" >
          {{ friendRuestResult(sendFriendApplication.handleResult) }}
        </view>
      </uni-list-chat>
    </uni-list>



    <uni-list v-if="type === 'recvFriendApplication'">
      <uni-title type="h1" align="center" title="好友请求列表"></uni-title>
      <uni-list-chat v-for="(recvFriendApplication,index) in recvFriendApplicationList" :title="recvFriendApplication.fromNickname"   :avatar="recvFriendApplication.fromFaceURL" :note="recvFriendApplication.reqMsg">
        <view class="chat-custom-right">
          <text class="chat-custom-text">{{ formatTime(recvFriendApplication.createTime) }}</text>
        </view>
        <view class="chat-custom-right" v-if="recvFriendApplication.handleResult === 0">
          <uni-tag type="primary" text="同意" @click="acceptFriendApplication(recvFriendApplication)"></uni-tag>
          <uni-tag type="success" text="拒绝" @click="refuseFriendApplication(recvFriendApplication)"></uni-tag>
        </view>
        <view class="chat-custom-right" v-else>
          {{ friendRuestResult(recvFriendApplication.handleResult) }}
        </view>
      </uni-list-chat>
    </uni-list>

    <uni-list v-if="type === 'black'">
      <uni-title type="h1" align="center" title="黑名单列表"></uni-title>
      <uni-list-chat v-for="(black,index) in blackList" :title="black.nickname"   :avatar="black.fromFaceURL" :note="black.reqMsg">
<!--        <view class="chat-custom-right">
          <text class="chat-custom-text">{{  formatTime(black.createTime)   }}</text>
        </view>-->
        <view class="chat-custom-right">
          <text class="chat-custom-text" @click.stop="removeBlack(black)" >从黑名单移除</text>
        </view>
      </uni-list-chat>
    </uni-list>


    <uni-list v-if="type === 'friend'">
      <uni-title type="h1" align="center" title="已添加的好友列表"></uni-title>
      <view v-for="(friend,index) in friendList">
        <uni-list-chat  v-if="friend.friendInfo && !friend.blackInfo" :title="friend.friendInfo.nickname"   :avatar="friend.friendInfo.fromFaceURL" >
          <view class="chat-custom-right">
            <text class="chat-custom-text">{{ formatTime(friend.friendInfo.createTime)  }}</text>
          </view>
          <view class="chat-custom-right">
            <uni-tag type="warning" text="添加到黑名单" @click="addBlack(friend.friendInfo)"></uni-tag>
            <uni-tag type="error" text="删除好友" @click="deleteFriend(friend.friendInfo)"></uni-tag>
          </view>
        </uni-list-chat>
        <uni-list-chat  v-if="friend.blackInfo" :title="friend.blackInfo.nickname"   :avatar="friend.blackInfo.fromFaceURL" >
          <view class="chat-custom-right">
            <text class="chat-custom-text" @click.stop="removeBlack(friend.blackInfo)" >从黑名单移除</text>
          </view>
        </uni-list-chat>
      </view>
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
import {AccessFriendParams, AddFriendParams} from "open-im-sdk";

@Options({components: {}})
export default class ChatFriendPage extends Vue {
  sendFriendApplicationList: Object[] = []
  recvFriendApplicationList: Object[] = []
  blackList: Object[] = []
  friendList: Object[] = []
  hasFriend = false;
  type = "";
  formatTime(dateStr : number) {
    return DateUtil.parseTime(dateStr * 1000)
  }
  // addFriend(e) {
  //   console.error('=================',e);
  //   socialChatFriendModule.addFriend(e.detail.value,"请求加好友");
  // }
  created() {
    onLoad((params) => {
      this.type = params.type;
      this.refresh();
    })
    // 检查是否为好友
  }
  refresh(){
    this.getData(this.type);
  }
  getData(type : string){
    switch (type){
      case "sendFriendApplication":
          this.getSendFriendApplicationList();
          break;
      case "recvFriendApplication":
        this.getRecvFriendApplicationList();
        break;
      case "black":
        this.getBlacklist();
        break;
      case "friend":
        this.getFriendList();
        break;
    }
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
   * 从好友列表中删除用户。
   */
  deleteFriend(user) {
    socialChatModule.openIm.deleteFriend(user.userID).then(({data}) => {
      console.log('deleteFriend',data);
      this.refresh();
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
  addBlack(black) {
    socialChatModule.openIm.addBlack(black.userID).then(({data}) => {
      console.log('addBlack',data);
      this.refresh();
    }).catch(err => {
    })
  }
  /**
   * 将用户添加到黑名单。
   */
  removeBlack(black) {
    socialChatModule.openIm.removeBlack(black.userID).then(({data}) => {
      this.refresh();
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
      this.refresh();
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
      this.refresh();
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
