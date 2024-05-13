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
    <!--  #ifdef MP-WEIXIN -->
    <!--      <ad v-if="talks.length>1" class="bg-white mb-5 w100vw" unit-id="adunit-65c8911d279d228f" ad-type="video"
              ad-theme="white"></ad>-->
    <!--  #endif -->
    <!--qq平台显示的广告-->
    <!--  #ifdef MP-QQ -->
    <!--      <ad v-if="talks.length>0" class="bg-white mb-5 w100vw" unit-id="72d8cb09a1bae9fa30d9e03e7cb8a25d"
              type="feeds" ad-height="160"></ad>-->
    <!--  #endif -->
    <!--  #ifdef APP-PLUS -->
    <!--      <ad v-if="talks.length>0" class="bg-white mb-5 w100vw" adpid="1890536227"></ad>-->
    <!--  #endif -->

    <div class="px-smm mb-xs">动态</div>
    <div v-for="talk in socialuniCommunityUserDetailViewService.talks" :key="talk.id" class="px-sm">
      <talk-item :talk="talk"
                 @deleteTalk="talkId=>socialuniCommunityUserDetailViewService.deleteTalk(talkId)"></talk-item>
    </div>

    <!--wx平台显示的广告-->
    <!--  #ifdef MP-WEIXIN -->
    <ad class="bg-white mt-10 w100vw" unit-id="adunit-ffa7bc1c73c7d46a"></ad>
    <!--  #endif -->
    <!--qq平台显示的广告-->
    <!--  #ifdef MP-QQ -->
    <ad class="bg-white mt-10 w100vw" unit-id="b10fe0e7c39b9ca9e7ce19660f6d0761" test-banner-type="one"></ad>
    <!--  #endif -->
    <!--  #ifdef APP-PLUS -->
    <ad class="bg-white mt-10 w100vw" adpid="1890536227"></ad>
    <!--  #endif -->

    <msg-input></msg-input>
  </socialuni-user-detail-view>
</template>

<script lang="ts">
import QPopup from "qing-ui/src/components/QPopup.vue";
import {Inject, Component, Vue, toNative} from 'vue-facing-decorator'
import {socialuniCommunityUserDetailViewService}
  from "socialuni-community-sdk/src/logic/SocialuniCommunityUserDetailViewService";
import {getCurrentInstance} from "vue";
import QIcon from "qing-ui/src/components/QIcon.vue"
import QButton from "qing-ui/src/components/QButton.vue"
import QNavbar from "qing-ui-uni/src/components/QNavbar/QNavbar.vue"
import {socialuniUserDetailViewService} from "socialuni-user-sdk/src/logic/SocialuniUserDetailViewService";
import SocialuniUserDetailView from "socialuni-user-view-uni/src/views/user/SocialuniUserDetailView.vue";
import SocialuniFollowTag from "../../components/SocialuniFollow/SocialuniFollowTag.vue";
import TalkItem from "../../components/talkItem/TalkItem.vue";
import TalkOperate from "../talk/talkOperate.vue";
import MsgInput from "socialuni-ui/src/components/MsgInput.vue";
@toNative
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
export default class SocialuniCommunityUserDetailView extends Vue {
  get user() {
    return socialuniUserDetailViewService.user
  }

  socialuniCommunityUserDetailViewService = socialuniCommunityUserDetailViewService

  created(){
    this.socialuniCommunityUserDetailViewService.initService(this)
  }
}
</script>
