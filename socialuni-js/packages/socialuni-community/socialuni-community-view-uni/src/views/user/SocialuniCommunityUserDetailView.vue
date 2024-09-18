<template>
  <socialuni-user-detail-view>
    <template #navBarRight>
      <slot name="navBarRight"></slot>
    </template>
    <template #userBaseInfoRight>
      <div class="col-center flex-none">
        <div class="row-end-center mr-xss">
          <div
              v-if="user.hasBeFollowed && !user.hasFollowed"
              class="px-5 py-1 bg-default">
            <div class="color-content font-12">
              对方关注了您
            </div>
          </div>
        </div>
        <!--              不为自己才展示-->
        <div class="row-col-center mt-sm">
          <!--                不为自己且未关注-->
          <!--            不为ios，或者不为付费，则显示-->
          <!--              <q-button v-if="!isIos||!user.chat.needPayOpen" @click="toMessagePage">-->

          <slot name="userBaseInfoRightPre">

          </slot>
          <socialuni-follow-tag></socialuni-follow-tag>
          <!--              <button v-else class="cu-btn round bd-gray bg-white" @click.stop="addFollow">已关注</button>-->
        </div>
      </div>
    </template>
    <template #userBaseInfoBottom>
      <div class="row-between-center mt-sm py-xs pr-xs">
        <div class="flex-row flex-1 row-around">
          <div class="px-lg line-height-1 col-all-center">
            <span class="text-lg font-bold color-main row-center">
              {{ user.followNum }}
            </span>
            <span class="font-12 color-content mt-xs">关注</span>
          </div>
          <div class="px-lg line-height-1 col-all-center">
            <span class="text-lg font-bold color-main row-center">
              {{ user.fansNum }}
            </span>
            <span class="font-12 color-content mt-xs">被关注</span>
          </div>
        </div>
      </div>
    </template>

    <q-popup ref="applyUserFriendDialog" title="申请添加好友111"
             @confirm="socialuniCommunityUserDetailViewService.addFriend()">
      <div class="pd">cccc</div>
    </q-popup>

    <talk-operate @deleteTalk="talkId=>socialuniCommunityUserDetailViewService.deleteTalk(talkId)"></talk-operate>


    <div class="px-smm mb-xs">动态</div>
    <div v-for="talk in socialuniCommunityUserDetailViewService.talks" :key="talk.id" class="px-sm">
      <talk-item :talk="talk"
                 @deleteTalk="talkId=>socialuniCommunityUserDetailViewService.deleteTalk(talkId)"></talk-item>
    </div>


    <msg-input class="w100p"></msg-input>
  </socialuni-user-detail-view>
</template>

<script lang="ts">
import QPopup from "qingjs-ui/src/components/QPopup.vue";
import {Inject, Component, Vue, toNative} from 'vue-facing-decorator'
import {socialuniCommunityUserDetailViewService}
  from "socialuni-community-sdk/src/logic/SocialuniCommunityUserDetailViewService";
import QIcon from "qingjs-ui/src/components/QIcon.vue"
import QButton from "qingjs-ui/src/components/QButton.vue"
import QNavbar from "qingjs-ui-uni/src/components/QNavbar/QNavbar.vue"
import {socialuniUserDetailViewService} from "socialuni-user-sdk/src/logic/SocialuniUserDetailViewService";
import SocialuniUserDetailView from "socialuni-user-view-uni/src/views/user/SocialuniUserDetailView.vue";
import SocialuniFollowTag from "../../components/SocialuniFollow/SocialuniFollowTag.vue";
import TalkOperate from "../talk/talkOperate.vue";
import MsgInput from "socialuni-ui/src/components/MsgInput.vue";
import TalkItem from "socialuni-community-ui/src/components/talkItem/TalkItem.vue";


@Component({
  components: {
    MsgInput,
    TalkOperate,
    TalkItem,
    QIcon,
    QButton,
    QNavbar,
    QPopup,
    SocialuniFollowTag,
    SocialuniUserDetailView
  }
})
class SocialuniCommunityUserDetailView extends Vue {
  get user() {
    return socialuniUserDetailViewService.user
  }

  socialuniCommunityUserDetailViewService = socialuniCommunityUserDetailViewService

  created(){
    this.socialuniCommunityUserDetailViewService.initService(this)
  }
}
export default toNative(SocialuniCommunityUserDetailView)
</script>
