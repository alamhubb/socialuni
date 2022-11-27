<template>
  <view class="bg-default h100p">
    <uni-tag type="primary" text="创建群聊" @click="createGroup"></uni-tag>
    <uni-list >
      <uni-title type="h1" align="center" title="我发出的好友请求列表"></uni-title>
      <uni-list-chat v-for="(joinedGroup,index) in joinedGroupList" :title="joinedGroup.groupName"   :avatar="joinedGroup.faceURL" :note="joinedGroup.introduction">
        <view class="chat-custom-right">
          <text class="chat-custom-text">{{ formatTime(joinedGroup.createTime) }}</text>
          <!-- 需要使用 uni-icons 请自行引入 -->
          <!--          <uni-icons type="star-filled" color="#999" size="18"></uni-icons>-->
        </view>
        <view class="chat-custom-right" >
          {{ friendRuestResult(joinedGroup.status) }}
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
import {CreateGroupParams, GroupInitInfo, Member, SetGroupVerificationParams} from "open-im-sdk";

@Options({components: {}})
export default class ChatGroupPage extends Vue {
  joinedGroupList: Object[] = []
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
      this.getJoinedGroupList();
    })
    // 检查是否为好友
  }

  /**
   * 创建群聊
   */
  createGroup(){
    const groupBaseInfo:GroupInitInfo = {
      groupType:0,
      groupName: "", // 群名称
      introduction: "", // 群介绍
      notification: "",  // 群公告
      faceURL: "",  //
      ex: ""   // 扩展字段
    }
    /**
     * 经过测试: 会自动添加群主。 roleLevel: 默认为2 。
     * 还有下面这个是邀请成员的 level为1 。  类似于微信的拉群聊。不需要同意就直接能拉进群聊。
     */
    const memberList:Member[] = [
      /*{
        userID:"openIM333",
        roleLevel:1
      }*/
    ];
    const options:CreateGroupParams = {
      groupBaseInfo,
      memberList
    }
    socialChatModule.openIm.createGroup(options).then(({ data })=>{
      console.log('createGroup',data);
    }).catch(err=>{
    })
  }

  /**
   * 查询已加入的群列表
   */
  getJoinedGroupList(){
    socialChatModule.openIm.getJoinedGroupList().then(({ data })=>{
      this.joinedGroupList = JSON.parse(data);
      console.log('getJoinedGroupList',data,this.joinedGroupList);
    }).catch(err=>{
    })
  }
  /**
   * 设置进群验证规则
   */
  setGroupVerification(){
    const options:SetGroupVerificationParams = {
      groupID:"",
      verification:0,
    }
    socialChatModule.openIm.setGroupVerification(options).then(({ data })=>{
      console.log('setGroupVerification',data);
    }).catch(err=>{
    })
  }
}
</script>
