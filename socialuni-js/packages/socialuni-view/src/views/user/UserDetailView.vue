<template>
  <view class="bg-default">
    <q-navbar show-back title="用户详情">
      <div class="row-end-center flex-1">
        <q-icon icon="list-dot" size="20" @click="openMoreMenu"></q-icon>
      </div>
    </q-navbar>
    <user-info :user.sync="user">
    </user-info>

    <q-popup ref="moreActionMenu" bottom>
      <uni-list class="pb-xl mt-sm">
        <uni-list-item title="解除好友" link @click="deleteFriend"></uni-list-item>
      </uni-list>
    </q-popup>
  </view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import CenterUserDetailRO from "socialuni-api/src/model/social/CenterUserDetailRO";
import UniUtil from "socialuni-sdk/src/utils/UniUtil";
import MsgInput from "socialuni-view/src/components/MsgInput.vue";
import {onHide, onLoad, onShow} from "@dcloudio/uni-app";
import UserInfo from "socialuni-view/src/views/user/UserInfo.vue";
import {socialChatModule} from "socialuni-sdk/src/store/store";
import {AddFriendParams} from "open-im-sdk";
import {onMounted} from "vue";
import SocialuniUserAPI from "socialuni-api/src/api/socialuni/SocialuniUserAPI";
import QNavbar from "../../components/QNavbar/QNavbar.vue";
import QIcon from "../../components/QIcon/QIcon.vue";
import QPopup from "../../components/QPopup/QPopup.vue";
import AlertUtil from "socialuni-sdk/src/utils/AlertUtil";
import ToastUtil from "socialuni-sdk/src/utils/ToastUtil";

@Options({
  components: {QPopup, QIcon, QNavbar, MsgInput, UserInfo}
})
export default class UserDetailView extends Vue {
  $refs: {
    moreActionMenu: QPopup
  }

  user: CenterUserDetailRO = null

  openMoreMenu() {
    this.$refs.moreActionMenu.open()
  }

  init(params: { userId: string }) {
    console.log(789)
    const userId = params.userId
    // 这里有问题，有时候直接进入页面没有userId
    SocialuniUserAPI.queryUserDetailAPI(userId).then((res: any) => {
      this.user = res.data
      console.log(this.user)
      if (!this.user.isMine) {
        socialChatModule.checkFriend(this.user);
        // socialChatModule.setCurChatByUserId(this.user.id)
      }
    })
  }


  /**
   * 从好友列表中删除用户。
   */
  deleteFriend() {
    AlertUtil.confirm('是否确认解除好友关系').then(res => {
      socialChatModule.openIm.deleteFriend(this.user.id).then(({data}) => {
        console.log('deleteFriend', data);
        ToastUtil.toast('成功解除好友关系')
        socialChatModule.checkFriend(this.user);
      }).catch(err => {
      })
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
}
</script>
