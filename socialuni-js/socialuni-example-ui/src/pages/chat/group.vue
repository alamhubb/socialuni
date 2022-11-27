<template>
  <view class="bg-default h100p">
    <uni-tag type="primary" text="创建群聊" @click="createGroup"></uni-tag>
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

  </view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import {socialChatFriendModule, socialChatModule} from "socialuni-sdk/src/store/store"
import {onLoad} from "@dcloudio/uni-app";
import UniUtil from "socialuni-sdk/src/utils/UniUtil";
import DateUtil from "socialuni-sdk/src/utils/DateUtil";
import {CreateGroupParams, GroupInitInfo, Member} from "open-im-sdk";

@Options({components: {}})
export default class ChatGroupPage extends Vue {
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
    })
    // 检查是否为好友
  }

  createGroup(){

    const groupBaseInfo:GroupInitInfo = {
      groupType:0,
      groupName: "",
      introduction: "",
      notification: "",
      faceURL: "",
      ex: ""
    }
    const memberList:Member[] = [
      {
        userID:"",
        roleLevel:1
      }
    ]
    const options:CreateGroupParams = {
      groupBaseInfo,
      memberList
    }

    socialChatModule.openIm.createGroup(options).then(({ data })=>{
      console.log('acceptFriendApplication',data);
    }).catch(err=>{
    })
  }
}
</script>
