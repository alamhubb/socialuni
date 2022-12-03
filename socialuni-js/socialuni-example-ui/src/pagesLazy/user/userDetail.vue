<template>
  <view class="bg-default">
    <user-info :user.sync="user">
      <template v-slot:list>
        <view class="row-between-center py-xs pr-xs" >
          <view class="flex-row flex-1" :class="{'row-around':isMine}">
            <view v-if="hasFriend" class="px-lg line-height-1" @click.stop="deleteFriend">
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
      </template>
    </user-info>
    <msg-input v-if="showMsgInput">
    </msg-input>
  </view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import CenterUserDetailRO from "socialuni-api/src/model/social/CenterUserDetailRO";
import UniUtil from "socialuni-sdk/src/utils/UniUtil";
import MsgInput from "socialuni-view/src/components/MsgInput.vue";
import {onHide, onLoad, onShow} from "@dcloudio/uni-app";
import SocialuniUserAPI from "socialuni-api/src/api/socialuni/SocialuniUserAPI";
import UserInfo from "socialuni-view/src/views/user/UserInfo.vue";
import {socialChatModule} from "socialuni-sdk/src/store/store";

@Options({
  components: {MsgInput, UserInfo}
})
export default class UserDetail extends Vue {
  user: CenterUserDetailRO = null
  showMsgInput = false
  hasFriend = false

  created() {
    UniUtil.showShareMenu()

    onShow(() => {
      this.showMsgInput = true
    })

    onHide(() => {
      this.showMsgInput = false
    })


    onLoad((params) => {
      const userId = params.userId
      // 这里有问题，有时候直接进入页面没有userId
      SocialuniUserAPI.queryUserDetailAPI(userId).then((res: any) => {
        this.user = res.data
        if (!this.user.isMine){
          socialChatModule.checkFriend(this.user);
          // socialChatModule.setCurChatByUserId(this.user.id)
          this.checkFriend();
        }
      });

    })
  }

  checkFriend(){
    socialChatModule.openIm.checkFriend([this.user.id]).then(({data}) => {
      // console.log('checkFriend',data,this.hasFriend,typeof data);
      // 他是string需要手动转化一下。
      data = JSON.parse(data);
      console.log('checkFriend222222222222222222',data,this.hasFriend,typeof data);
      for (let i = 0; i < data.length; i++) {
        let datum = data[i];
        // console.log('==============datum===============',datum,this.user.id,datum.userID );
        if (datum.userID == this.user.id) {
          this.hasFriend = datum.result != 0;
          // console.log('checkFriend',data,this.hasFriend);
        }
      }

    }).catch(err => {
      console.log('checkFriend--err',err);
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
      uni.showToast({
        title: '成功发送申请',
        duration: 2000
      });
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
      this.checkFriend();
    }).catch(err => {
    })
  }
  /**
   * 将用户添加到黑名单。
   */
  addBlack() {
    socialChatModule.openIm.addBlack(this.user.id).then(({data}) => {
      this.checkFriend(this.user);
    }).catch(err => {
    })
  }
}
</script>
