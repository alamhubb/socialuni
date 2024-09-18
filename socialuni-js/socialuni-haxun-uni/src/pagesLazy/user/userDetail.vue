<template>
  <div class="bg-default">
    <!--        <socialuni-user-detail-view></socialuni-user-detail-view>-->
    <socialuni-community-user-detail-view>
      <template #navBarRight>
        <div class="row-end-center flex-1">
          <q-icon icon="list-dot" size="20" @click="openMoreMenu"></q-icon>
        </div>
      </template>
      <template #userBaseInfoRightPre>
        <q-button @click="toMessagePage" class="mr-sm">
          <q-icon icon="mdi-chat-outline" size="14"></q-icon>
          私信
        </q-button>
      </template>
    </socialuni-community-user-detail-view>

    <q-popup ref="moreActionMenu" bottom>
      <uni-list class="pb-xl">
        <uni-list-item title="解除好友" link
                       @click="deleteFriend"></uni-list-item>
        <uni-list-item title="加入黑名单" link
                       @click="addBlack"></uni-list-item>
        <uni-list-item title="从黑名单移除" link
                       @click="removeBlack"></uni-list-item>
      </uni-list>
    </q-popup>
  </div>
</template>

<script lang="ts">
import SocialuniCommunityUserDetailView
  from "socialuni-community-view-uni/src/views/user/SocialuniCommunityUserDetailView.vue";
import QButton from "qingjs-ui/src/components/QButton.vue";
import QIcon from "qingjs-ui/src/components/QIcon.vue";
import QPopup from "qingjs-ui/src/components/QPopup.vue";
import {Component, Vue, toNative} from 'vue-facing-decorator';
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";
import SocialuniUserDetailView from "socialuni-user-view-uni/src/views/user/SocialuniUserDetailView.vue";
import {socialuniChatModule} from "socialuni-im-sdk/src/store/SocialuniChatModule";
import {socialuniUserDetailViewService} from "socialuni-user-sdk/src/logic/SocialuniUserDetailViewService";


@Component({
  components: {
    QPopup,
    QButton,
    QIcon,
    SocialuniUserDetailView,
    SocialuniCommunityUserDetailView
  }
})
class UserDetailPage extends Vue {
  $refs: {
    moreActionMenu: QPopup
  }

  openMoreMenu() {
    this.$refs.moreActionMenu.open()
  }


  /**
   * 从好友列表中删除用户。
   */
  async deleteFriend() {
    QingAppUtil.AlertUtil.confirm('是否确认解除好友关系').then(async () => {
      /*(await socialChatModule.openIm()).deleteFriend(socialuniUserDetailViewService.user.id).then(({data}) => {
          console.log('deleteFriend', data)
          QingAppUtil.ToastUtil.toast('成功解除好友关系')
          socialChatModule.checkFriend(socialuniUserDetailViewService.user)
      }).catch(err => {
      })*/
    })
  }


  /**
   * 将用户添加到黑名单。
   */
  async addBlack() {
    /* QingAppUtil.AlertUtil.confirm('是否确认添加到黑名单').then(async () => {
         (await socialChatModule.openIm()).addBlack(this.user.id).then(({data}) => {
             socialChatModule.checkFriend(this.user)
         }).catch(err => {
         })
     })*/

  }


  /**
   * 将用户从黑名单移除。
   */
  async removeBlack() {
    /*QingAppUtil.AlertUtil.confirm('是否确认从黑名单移除').then(async () => {
        (await socialChatModule.openIm()).removeBlack(this.user.id).then(({data}) => {
            socialChatModule.checkFriend(this.user)
        }).catch(err => {
        })
    })*/
  }

  async toMessagePage() {
    socialuniChatModule.setChatIdToMessagePage(socialuniUserDetailViewService.user.id)
  }

}
export default toNative(UserDetailPage)
</script>
